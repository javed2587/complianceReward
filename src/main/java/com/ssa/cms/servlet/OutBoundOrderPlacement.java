/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.servlet;

import com.ssa.cms.model.Drug;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.service.DoDirectPayment;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.service.RefillReminderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;

@Controller
@RequestMapping
public class OutBoundOrderPlacement {

    private static final Logger log = Logger.getLogger(OutBoundOrderPlacement.class);
    @Autowired
    RefillReminderService refillReminderDAO;
    @Autowired
    RedemptionService redemptionDAO;

    @RequestMapping(value = "/AutoOrderPlacement", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/AutoOrderPlacement", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String phoneNumber = request.getParameter("from");
        int redemptionId = (int) (request.getAttribute("redemptionId") == null ? 0 : request.getAttribute("redemptionId"));

        log.info("Phone Number : " + phoneNumber);
        log.info("Redemption Id : " + redemptionId);
        try {
            InstantRedemption irf = refillReminderDAO.getInstantRedemptionDetailByRedemptionId(redemptionId);
            if (irf == null) {
                log.info("IRF record not found (System will continue)");
                return;
            }

            String communicatoinMethod = irf.getCommunicationMethod();
            if (communicatoinMethod == null || communicatoinMethod.trim().length() == 0) {
                log.info("No communicaiton method defined (System will continue)");
                return;
            }

            communicatoinMethod = communicatoinMethod.trim();
            if (communicatoinMethod.equalsIgnoreCase("I")) {

                Order order = refillReminderDAO.getOrderDetailByTransactionNumber(irf.getCardholderFirstName(), irf.getCardholderLastName(), irf.getCampaignId(), irf.getCardholderDob());
                if (order == null) {
                    log.info("Order is null in thread");
                    return;
                }

                Order ord = new Order();
                BeanUtils.copyProperties(order, ord, new String[]{"id"});

                DoDirectPayment dopayment = new DoDirectPayment();
                DoDirectPaymentResponseType res = dopayment.doDirectPayment(ord.getCardType(), ord.getCardNumber(), ord.getCardExpiry(),
                        ord.getCardHolderName(), ord.getPayment().toString(), ord.getCardCvv());

                if (res.getAck().getValue().equalsIgnoreCase("success")) {
                    placeOrder(irf, ord);
                    out.write("Refill Auto Order Success");
                } else {
                    out.write("Refill Auto Order Fail");
                }
            } else {
                out.write("Refill Auto Order Fail");
            }
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

    private void placeOrder(InstantRedemption irf, Order ord) {
        Double copay = 0.0;
        List<RedemptionIngredient> ingredients = redemptionDAO.getRedemptionIngredientByTransactionNumber(irf.getId().getTransactionNumber());
        if (ingredients != null && !ingredients.isEmpty()) {
            for (RedemptionIngredient ingredient : ingredients) {
                Drug drug = redemptionDAO.getMaxOfferByNdc(ingredient.getNdc());
//                if (drug != null) {
//                    copay += drug.getMaxOffer();
//                }
            }
        }
        ord.setOrderTrackingNo(null);
        ord.setCreatedOn(new Date());
        ord.setUpdatedOn(new Date());
        Double paymentAmount = irf.getPtOutOfPocket().doubleValue() - copay;
        log.info("Copay: " + copay);
        log.info("PtOutOfPocket: " + irf.getPtOutOfPocket());
        log.info("PaymentAmount: " + paymentAmount);

       // ord.setCopay(copay);
        ord.setOutOfPocket(irf.getPtOutOfPocket());
        ord.setTransactionNo(irf.getId().getTransactionNumber());
        ord.setPayment(paymentAmount);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(2);
        ord.setOrderStatus(orderStatus);
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setComments("Order Placed!");
        orderHistory.setOrder(ord);
        orderHistory.setOrderStatus(orderStatus);
        orderHistory.setCreatedOn(new Date());
        orderHistory.setUpdatedOn(new Date());
        List<OrderHistory> orderHistorys = new ArrayList<>();
        orderHistorys.add(orderHistory);
        ord.setOrderHistory(orderHistorys);
        refillReminderDAO.save(ord);
    }
}
