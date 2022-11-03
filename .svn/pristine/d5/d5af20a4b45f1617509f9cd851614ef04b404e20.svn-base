package com.ssa.cms.controller;

import com.ssa.cms.bean.OrderSearch;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRedemptionId;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.DoDirectPayment;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.TextFlowUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;

/**
 *
 * @author msheraz
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RedemptionService redemptionService;

    SessionBean sessionBean;

    OrderSearch orderSearch;

    DailyRedemption dailyRedemption;

    private static final Logger logger = Logger.getLogger(OrderController.class);
    Order ord;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        orderSearch = (OrderSearch) request.getSession().getAttribute("orderSearch");
        ord = (Order) request.getSession().getAttribute("ord");
    }

    @RequestMapping(value = "/placeorder/{transactionNumber}", method = RequestMethod.GET)
    public ModelAndView viewPlaceOrder(@ModelAttribute Order order, @PathVariable String transactionNumber) {

        ModelAndView modelAndView = new ModelAndView();
        DailyRedemptionId id = new DailyRedemptionId();
        id.setClaimStatus(0);
        id.setTransactionNumber(transactionNumber);

        dailyRedemption = orderService.getRedemptionDetail(id);
        logger.info("DailyRedemption: " + dailyRedemption);
        //If an user optout do not place another order
        //If an order is in process do not place another order
        if (orderService.getOrderByTransactionNumber(transactionNumber).size() > 0) {
            modelAndView.addObject("message", "Order with same transaction number is already placed.");
            modelAndView.setViewName("/displaymessage");
            return modelAndView;
        }
        order = orderService.getPatientProfileDetailByTransactionNo(transactionNumber);
        if (ord != null) {
            order = ord;
        }

        List<RedemptionIngredient> ingredients = redemptionService.getRedemptionIngredientByTransactionNumber(dailyRedemption.getId().getTransactionNumber());
        Double copay = 0.0;
        if (ingredients != null && !ingredients.isEmpty()) {
            for (RedemptionIngredient ingredient : ingredients) {
                Drug drug = redemptionService.getMaxOfferByNdc(ingredient.getNdc());
//                if (drug != null) {
//                    copay += drug.getMaxOffer();
//                }
            }
        }
        
        logger.info("Transaction Number is: " + transactionNumber);
        modelAndView.addObject("order", order);
        modelAndView.addObject("redemption", dailyRedemption);
        modelAndView.addObject("transactionNumber", transactionNumber);
        modelAndView.setViewName("placeorder");
        return modelAndView;

    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public ModelAndView viewOrderSummary(@ModelAttribute Order order, HttpServletRequest request, RedirectAttributes ra) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prescriptionNumber", dailyRedemption.getPrescriptionNumber());
        request.getSession().setAttribute("ord", order);
        List<RedemptionIngredient> ingredients = redemptionService.getRedemptionIngredientByTransactionNumber(dailyRedemption.getId().getTransactionNumber());
        Double copay = 0.0;
        if (ingredients != null && !ingredients.isEmpty()) {
            for (RedemptionIngredient ingredient : ingredients) {
                Drug drug = redemptionService.getMaxOfferByNdc(ingredient.getNdc());
                
            }
        }
        logger.info("Copay: " + copay);
        logger.info("PtOutOfPocket: " + dailyRedemption.getPtOutOfPocket());

        
        order.setOutOfPocket(dailyRedemption.getPtOutOfPocket());

        Double paymentAmount = dailyRedemption.getPtOutOfPocket().doubleValue() - copay;
        if (dailyRedemption.getPtOutOfPocket().compareTo(BigDecimal.valueOf(8)) == 1 && paymentAmount.intValue() < 8) {
            paymentAmount = 8.0;
        }
        order.setPayment(paymentAmount);
        Integer currentUserId = null;
        modelAndView.setViewName("ordersummary");
        if ("placeorder".equals(order.getType())) {

            logger.info("CardType: " + order.getCardType());
            logger.info("CardNumber: " + order.getCardNumber());
            logger.info("CardExpiry: " + order.getCardExpiry());
            logger.info("CardHoderName: " + order.getCardHolderName());
            logger.info("PaymentAmount: " + paymentAmount.toString());
            logger.info("CardCvv: " + order.getCardCvv());

            //todo copay + email
            DoDirectPayment payment = new DoDirectPayment();
            DoDirectPaymentResponseType response = payment.doDirectPayment(order.getCardType(),
                    order.getCardNumber(), order.getCardExpiry(),
                    order.getCardHolderName(), paymentAmount.toString(), order.getCardCvv());
            logger.info("DoDirectPaymentResponseType: " + response);
            if (response.getAck().getValue().equalsIgnoreCase("success")) {
                logger.info("Session Id: " + sessionBean);
                if (sessionBean != null) {
                    currentUserId = sessionBean.getUserId();
                }
                order.setDoDirectTransactionId(response.getTransactionID());
                order = orderService.saveOrder(order, currentUserId);
                //send order confirmation message
                sendOrderConfirmationMessage(order);
                request.getSession().setAttribute("ord", null);

                modelAndView = new ModelAndView(new RedirectView("status/" + order.getId()));
                ra.addFlashAttribute("order", order);
            } else {
                List<ErrorType> errorList = response.getErrors();
                modelAndView.addObject("errorMessage", errorList.get(0).getLongMessage());
                modelAndView.addObject("order", order);
            }
        }
        return modelAndView;
    }

    private void sendOrderConfirmationMessage(Order order) {
        Campaigns campaign = redemptionService.getCampaignById(Constants.campaignId);
        int campaignId = campaign.getCampaignId();
        String communicationPath = Constants.SMS;
        String messageContext = Constants.REDEMPTION;
        String messageTitle = "";
        String optInOutFlag = null;
        String pharmacyName = null;
        String pharmacyPhone = null;

        String servlet = "/order/status/" + order.getId();
        String orderStatusUrl = Constants.APP_PATH + servlet;
        InstantRedemptionId id = new InstantRedemptionId();
        if (dailyRedemption != null) {
            id.setClaimStatus(dailyRedemption.getId().getClaimStatus());
            id.setTransactionNumber(dailyRedemption.getId().getTransactionNumber());
        } else {
            logger.info("DailyRedemption is null." + dailyRedemption);
            return;
        }
        if (order.getPharmacy() != null) {
            pharmacyName = order.getPharmacy().getTitle();
            pharmacyPhone = order.getPharmacy().getPhone();
        } else {
            logger.info("Pharmacy is not assigned. " + order.getPharmacy());
        }
        InstantRedemption instantRedemptionFile = redemptionService.getIRFById(id);
        if (null != instantRedemptionFile.getCommunicationMethod()) {
            switch (instantRedemptionFile.getCommunicationMethod()) {
                case "T": {
                    List<EventHasFolderHasCampaigns> eventHasFolderHasCampaignses = redemptionService.getEventHasFolderHasCampaign(campaignId, Constants.REDEMPTION, communicationPath);
                    if (eventHasFolderHasCampaignses == null || eventHasFolderHasCampaignses.isEmpty()) {
                        logger.info("No Redemption folder associated to this campaign (System will continue)");
                        return;
                    }
                    for (EventHasFolderHasCampaigns ehfhc : eventHasFolderHasCampaignses) {
                        int redemptionFolderId = ehfhc.getFolderId();
                        if (null != order.getOrderStatus().getId()) switch (order.getOrderStatus().getId()) {
                            case 1:
                                messageTitle = Constants.ORDER_PLACEMENT_CONFIRMATION;
                                break;
                            case 3:
                                messageTitle = Constants.ORDER_FILLED;
                                break;
                            case 4:
                                messageTitle = Constants.ORDER_SHIPPED;
                                break;
                            case 5:
                                messageTitle = Constants.ORDER_DENIED;
                                break;
                            default:
                                break;
                        }
                        CampaignMessagesResponse campaignMessagesResponse = redemptionService.getCampaignMessagesResponseByResComm(campaignId, redemptionFolderId, messageTitle, communicationPath);
                        if (campaignMessagesResponse == null) {
                            logger.info("Redemption Thank you and Max Benefit campaignMessagesResponse is null.");
                            return;
                        }
                        Intervals interval = campaignMessagesResponse.getIntervals();
                        IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
                        int intervalId = interval.getIntervalId();
                        String intervalDesc = interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                        String eventDesc = redemptionService.getEventsDescription(campaignId, redemptionFolderId, communicationPath);
                        int redemptionMsgTypeId = campaignMessagesResponse.getMessageTypeId();
                        List<CampaignMessages> campaignMessagesList = redemptionService.getCampaignMessagesByCommunicationPath(campaignId, redemptionFolderId, redemptionMsgTypeId, communicationPath);
                        if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                            logger.info("No Redemption messages found for (System will return)");
                            return;
                        }
                        CampaignMessages campaignMessages = campaignMessagesList.get(0);
                        int messageId = campaignMessages.getMessageId();
                        int messageTypeId = campaignMessages.getMessageType().getId().getMessageTypeId();
                        String messageText = campaignMessages.getSmstext();
                        int intervalValue = interval.getIntervalValue();
                        int intervalUnitInSecond = intervalsType.getUnitInSecond();
                        long secondsDelay = intervalValue * intervalUnitInSecond;
                        AggregatorMessageRequest messageRequest = new AggregatorMessageRequest();
                        ShortCodes shortCode = campaign.getShortCodes();
                        GatewayInfo gatewayInfo = redemptionService.getGatewayInfo(shortCode.getShortCode());

                        if (messageText != null && messageText.trim().length() > 0) {
                            logger.info("Message Id : " + messageId + "Message Type Id : " + messageTypeId + "Redemption Message : " + messageText);

                            int sentMessageCount = redemptionService.retrieveMessageCountByTypeForRedemption(instantRedemptionFile.getCommunicationId(), instantRedemptionFile.getRxGroupNumber(), instantRedemptionFile.getCardholderDob(), instantRedemptionFile.getFillDate(), instantRedemptionFile.getNdcNumber(), instantRedemptionFile.getId().getClaimStatus(), ehfhc.getFolderId(), messageTypeId);
                            if (sentMessageCount > 1) {
                                logger.info("Message already sent for phone number: " + instantRedemptionFile.getCommunicationId());
                                return;
                            }
                            logger.info("Message Id : " + messageId + "Message Type Id : " + messageTypeId + "Redemption Message : " + messageText);

                            messageText = TextFlowUtil.prepareMessage(null, null, pharmacyName,
                                    pharmacyPhone, messageText,
                                    order.getId(), null, orderStatusUrl, null, null, order.getCardType(), order.getCardNumber());

                            campaignMessages.setSmstext(messageText);

                            messageRequest = setMessageRequest(instantRedemptionFile, ehfhc.getFolderId(),
                                    messageText, messageTypeId, messageContext, intervalId, intervalDesc, eventDesc);
                        }
                        CampaignTrigger campaignTrigger = redemptionService.getTriggerByCampaignId(campaignId);
                        String programCode = null;
                        if (campaignTrigger != null) {
                            programCode = campaignTrigger.getId().getKeyword();
                        }
                        //instantRedemptionFile.setCopay(order.getCopay());
                        TextFlowUtil.startMessageThread(gatewayInfo, messageRequest, campaignMessages, programCode, secondsDelay, campaign, ehfhc, messageTypeId,
                                intervalId, intervalDesc, eventDesc, instantRedemptionFile, dailyRedemption, redemptionService);
                        break;
                    }
                    break;
                }
                case "I": {
                    
                }
            }
        }
    }

    private AggregatorMessageRequest setMessageRequest(InstantRedemption instantRedemption,
            int folderId, String messageText,
            int messageTypeId,
            String messageContext,
            int intervalId, String intervalDesc, String eventDesc) {

        AggregatorMessageRequest messageRequest = new AggregatorMessageRequest();
        if (instantRedemption.getCardholderDob() != null) {
            messageRequest.setCardholderDob(instantRedemption.getCardholderDob());
        } else {
            messageRequest.setCardholderDob(null);
        }
        messageRequest.setClaimStatus(instantRedemption.getId().getClaimStatus());
        messageRequest.setFileTypeCode(Constants.DRF);
        messageRequest.setFolderId(folderId);
        messageRequest.setMessageText(messageText);
        messageRequest.setMessageTypeId((messageTypeId));
        messageRequest.setNdcNumber(instantRedemption.getNdcNumber());
        messageRequest.setPhoneNumber(instantRedemption.getCommunicationId());
        messageRequest.setRedemptionId(instantRedemption.getRedemptionId());
        messageRequest.setRxGroupNumber(instantRedemption.getRxGroupNumber());
        messageRequest.setFillDate(instantRedemption.getFillDate());
        messageRequest.setCampaignId(instantRedemption.getCampaignId());
        messageRequest.setMessageContext(messageContext);
        messageRequest.setIntervalId(intervalId);
        messageRequest.setIntervalDescription(intervalDesc);
        messageRequest.setEventDetail(eventDesc);
        if (instantRedemption.getCommunicationMethod().equalsIgnoreCase("T")) {
            messageRequest.setCommunicationPath(Constants.SMS);
        }
        return messageRequest;
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public ModelAndView viewOrderStatus(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("orderstatus");
        modelAndView.addObject("order", orderService.getOrderDetail(id));
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listUser(@ModelAttribute Order order, HttpServletRequest request) throws ParseException, Exception {
        ModelAndView modelAndView = new ModelAndView("orderlist");
        if (this.orderSearch != null) {
            return viewOrderList(orderSearch.getOrder(), request);
        } else {
            order.setFromDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
            order.setToDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
            List<Order> list = orderService.getAllPharmacyOrder();
            dropDownPharmacy(modelAndView);
            modelAndView.addObject("totalRecords", list.size());
            modelAndView.addObject("list", list);
            modelAndView.addObject("order", order);
        }
        return modelAndView;
    }

    private void dropDownPharmacy(ModelAndView modelAndView) throws NumberFormatException {
        modelAndView.addObject("orderStatusList", orderService.getOrderStatusList());
        modelAndView.addObject("pharmacyList", orderService.getPharmacyList(sessionBean.getUserNameDB(), sessionBean.getUserId()));
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView viewOrderList(@ModelAttribute Order order, HttpServletRequest request) throws ParseException, Exception {
        ModelAndView modelAndView = new ModelAndView("orderlist");
        dropDownPharmacy(modelAndView);

        if (order.getOrderStatusId() == null) {
            order.setOrderStatusId(1);
        }

        if (!validateDate(order, modelAndView)) {
            return modelAndView;
        }
        List<Order> list = orderService.getOrderList(order.getOrderStatusId(), order.getPharmacyId(), order.getFromDate(), order.getToDate());
        modelAndView.addObject("totalRecords", list.size());
        modelAndView.addObject("order", order);
        OrderSearch orderSearch1 = new OrderSearch();
        orderSearch1.setOrder(order);
        request.getSession().setAttribute("orderSearch", orderSearch1);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    private boolean validateDate(Order order, ModelAndView modelAndView) throws ParseException {
        Date fromdate = DateUtil.stringToDate(order.getFromDate(), "MM/dd/yyyy");
        Date toDate = DateUtil.stringToDate(order.getToDate(), "MM/dd/yyyy");
        Date currentDate = DateUtil.formatDate(new Date(),"MM/dd/yyyy");
        if (fromdate.compareTo(toDate) > 0) {
            modelAndView.addObject("fromToDate", "From Date should be less than or equal to date");
            return false;
        }
        if (toDate.compareTo(currentDate) > 0) {
            modelAndView.addObject("errorToDate", "To Date should be less than or equal to current date");
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView orderDetail(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("orderdetail");
        Order order = orderService.getOrderDetail(id);
        List<OrderStatus> statusList = filteredStatusList(order);
        modelAndView.addObject("orderStatusList", statusList);
        modelAndView.addObject("order", order);
        modelAndView.addObject("redemption", setDailyRedemption(order));
        modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(order.getTransactionNo()));
        return modelAndView;
    }

    private DailyRedemption setDailyRedemption(Order order) {
        DailyRedemptionId dailyRedemptionId = new DailyRedemptionId();
        dailyRedemptionId.setClaimStatus(0);
        dailyRedemptionId.setTransactionNumber(order.getTransactionNo());
        dailyRedemption = orderService.getRedemptionDetail(dailyRedemptionId);
        return dailyRedemption;
    }

    private List<OrderStatus> filteredStatusList(Order order) {
        List<OrderStatus> statusList = orderService.getOrderStatusList();
        for (Iterator<OrderStatus> iterator = statusList.iterator(); iterator.hasNext();) {
            OrderStatus status = iterator.next();
            if ((order.getOrderStatus().getId() == 2 && (status.getId() == 1 || status.getId() == 2 || status.getId() == 4 || status.getId() == 6 || status.getId() == 7))
                    || (order.getOrderStatus().getId() == 3 && (status.getId() == 1 || status.getId() == 2 || status.getId() == 3 || status.getId() == 5 || status.getId() == 6 || status.getId() == 7))) {
                iterator.remove();
            }
        }
        return statusList;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView addOrderDetail(@ModelAttribute Order order, @PathVariable String id, RedirectAttributes redirectAttributes, @RequestParam("logo") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("orderdetail");
        Order ordr = orderService.getOrderDetail(id);
        ordr.setSelectedStatus(order.getOrderStatus().getId());
        List<OrderStatus> statusList = filteredStatusList(ordr);
        if (order.getOrderTrackingNo() != null && order.getOrderTrackingNo().trim().isEmpty()) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("order.orderTrackNo.required", null, null));
            modelAndView.addObject("orderStatusList", statusList);
            modelAndView.addObject("order", ordr);
            modelAndView.addObject("redemption", setDailyRedemption(ordr));
            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(ordr.getTransactionNo()));
            return modelAndView;
        }
        if (order.getOrderStatus().getId() == 0) {
            modelAndView.addObject("orderStatusList", statusList);
            modelAndView.addObject("errorMessage", messageSource.getMessage("order.status.error", null, null));
            modelAndView.addObject("order", ordr);
            modelAndView.addObject("redemption", setDailyRedemption(ordr));
            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(ordr.getTransactionNo()));
            return modelAndView;
        }
        if (order.getOrderHistory().size() > 0) {
            for (OrderHistory orderHistory : order.getOrderHistory()) {
                if (orderHistory.getComments() != null && orderHistory.getComments().isEmpty()) {
                    modelAndView.addObject("orderStatusList", statusList);
                    modelAndView.addObject("errorMessage", messageSource.getMessage("orderhistory.comments.error", null, null));
                    modelAndView.addObject("order", ordr);
                    modelAndView.addObject("redemption", setDailyRedemption(ordr));
                    modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(ordr.getTransactionNo()));
                    return modelAndView;
                }
            }
        }
        manageOrderHistory(order);
        if (order.getOrderStatus().getId() == 4) {
            boolean isDuplicate = orderService.isTrackingNoDuplicate(order.getOrderTrackingNo(), order.getId());
            if (isDuplicate) {
                modelAndView.addObject("orderStatusList", statusList);
                modelAndView.addObject("errorMessage", messageSource.getMessage("field.OrderTrackNo.duplicate", null, null));
                modelAndView.addObject("order", ordr);
                modelAndView.addObject("redemption", setDailyRedemption(ordr));
                modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(ordr.getTransactionNo()));
                return modelAndView;
            }
        }
        boolean isSaved = orderService.saveOrderDetail(order, sessionBean.getUserId());
        if (isSaved) {
            if (order.getOrderStatus().getId() == 3 || order.getOrderStatus().getId() == 4 || order.getOrderStatus().getId() == 5) {
                Order newOrder = orderService.getOrderById(id);
                sendOrderConfirmationMessage(newOrder);
            }
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("orderStatusList", statusList);
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("order", ordr);
            modelAndView.addObject("redemption", setDailyRedemption(ordr));
            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(ordr.getTransactionNo()));
            return modelAndView;
        }
        return new ModelAndView("redirect:/order/list");
    }

    @RequestMapping(value = "/comments/{orderId}/{orderStatusId}", produces = "application/json")
    public @ResponseBody
    String getCommentsHandler(@PathVariable String orderId, @PathVariable String orderStatusId,
            HttpServletRequest request) throws Exception {
        return orderService.getOrderHistorysById(Integer.parseInt(orderId), Integer.parseInt(orderStatusId));
    }

    @RequestMapping(value = "/update/{orderId}/{status}", method = RequestMethod.GET)
    public ModelAndView updateOrderStatusHandler(@PathVariable String orderId, @PathVariable String status, RedirectAttributes redirectAttributes) throws Exception {
        //orderService.updateOrderStatus(orderId, Integer.parseInt(status), sessionBean.getUserId());
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("order.status.successfully", null, null));
        return new ModelAndView("redirect:/order/list");
    }

    private List<OrderHistory> manageOrderHistory(Order order) {
        List<OrderHistory> orderHistoryremove = new ArrayList<>();
        if (order.getOrderHistory() != null) {
            for (Iterator<OrderHistory> i = order.getOrderHistory().iterator(); i.hasNext();) {
                OrderHistory orderHistory = i.next();
                // If the remove flag is true, remove the employee from the list
                if (orderHistory.getComments() == null || orderHistory.getComments().isEmpty()) {
                    orderHistoryremove.add(orderHistory);
                    i.remove();
                } else {
                    orderHistory.setOrder(order);
                }
            }
        }
        return orderHistoryremove;
    }

    @RequestMapping(value = "/pdfDownload.pdf", method = RequestMethod.GET)
    public ModelAndView exportPDF(@RequestParam(value = "pharmacy", required = false) int pharmacy,
            @RequestParam(value = "status", required = false) int status,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate) throws ParseException {

        List<Order> list = orderService.getOrderList(status, pharmacy, fromDate, toDate);
        ModelAndView modelAndView = new ModelAndView("pdfView", "list", list);
        modelAndView.addObject("key", "ViewOrders");
        return modelAndView;
    }

    @RequestMapping(value = "/getLogo/{id}")
    public void getLogo(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
        Order order = orderService.getOrderDetail(id);
        response.setContentType("image/jpeg, image/jpg, image/png,image/PNG,image/JPEG,image/JPG");
        InputStream in1 = null;
        
        IOUtils.copy(in1, response.getOutputStream());
    }

    @RequestMapping(value = "/excelDownload.xls", method = RequestMethod.GET)
    public ModelAndView exportExcel(@RequestParam(value = "pharmacy", required = false) int pharmacy,
            @RequestParam(value = "status", required = false) int status,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate) throws ParseException {

        List<Order> list = orderService.getOrderList(status, pharmacy, fromDate, toDate);
        ModelAndView modelAndView = new ModelAndView("excelView", "list", list);
        modelAndView.addObject("key", "ViewOrders");
        return modelAndView;
    }

    @RequestMapping(value = "/prescription/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView sendPrescriptionOrder(@PathVariable String id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/PharmacyPortal/prescription");
        Order ordr = orderService.getOrderDetail(id);
        dailyRedemption = setDailyRedemption(ordr);
        Order newOrder = orderService.getOrderById(id);
        sendOrderConfirmationMessage(newOrder);
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        return modelAndView;
    }
}
