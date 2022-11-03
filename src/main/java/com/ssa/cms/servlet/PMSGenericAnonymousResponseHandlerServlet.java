/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.servlet;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.service.DoDirectPayment;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.thread.TextIntervalReminderThread;
import com.ssa.cms.util.TextFlowUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import urn.ebay.apis.eBLBaseComponents.ErrorType;

@Controller
@RequestMapping
public class PMSGenericAnonymousResponseHandlerServlet {

    private static final Logger log = Logger.getLogger(PMSGenericAnonymousResponseHandlerServlet.class);
    @Autowired
    RefillReminderService refillReminderDAO;
    @Autowired
    RedemptionService redemptionDAO;

    @RequestMapping(value = "/PMSGenericAnonymousResponseHandler", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/PMSGenericAnonymousResponseHandler", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String phoneNumber = request.getParameter("from");
        Object messageReqNObj = request.getAttribute("messageReqNo");
        String message = request.getParameter("message");
        String inputRefNumber = request.getParameter("inputReferenceNumber");

        PrintWriter out = response.getWriter();

        log.info("Phone Number : " + phoneNumber);
        log.info("Message : " + message);
        log.info("Message Request Number : " + messageReqNObj);
        String appName = "";
        try {

            long inputReferenceNumber = 0;
            if (inputRefNumber != null && inputRefNumber.trim().length() == 0) {
                try {
                    inputRefNumber = inputRefNumber.trim();
                    Pattern pattern = Pattern.compile("^\\d{1,20}$");

                    Matcher matcher = pattern.matcher(inputRefNumber);

                    if (matcher.matches()) {
                        inputReferenceNumber = Long.valueOf(inputRefNumber);
                    }

                } catch (Exception e) {
                    log.error("Exception::", e);
                }
            }

            int messageReqNo = 0;

            log.info("Message Request Number : " + messageReqNObj);

            if (messageReqNObj != null) {
                log.info("Message Request Number : " + messageReqNObj.toString());
                messageReqNo = Integer.valueOf(messageReqNObj.toString());
                log.info("Message Request Integer Value : " + messageReqNo);

                if (messageReqNo == 0) {
                    out.println("Message Req No number empty");
                    return;
                }
            }

            message = message.trim();

            //get Aggreegate message request table
            String path = (String) request.getAttribute("path");

            if (path == null) {
                log.info("Path is Null.");
                return;
            }

            if (phoneNumber.length() == 11) {
                phoneNumber = phoneNumber.substring(1);
            }

            int campaignId = 0;
            String messageContext = "";
            int folderId = 0;
            String communicationPath = "";
            int redemptionId = 0;
            int messageTypeId = 0;
            String fileTypeCode = "";

            AggregatorMessageRequest aggregatorMessageRequest = null;
            if (path.equalsIgnoreCase("Text")) {

                aggregatorMessageRequest = refillReminderDAO.getAggregatorMessageRequestById(messageReqNo);

                if (aggregatorMessageRequest == null) {
                    out.println("Message Req No entry not found in aggregateMessage Request");
                    return;
                }

                campaignId = aggregatorMessageRequest.getCampaignId();
                messageContext = aggregatorMessageRequest.getMessageContext();
                folderId = aggregatorMessageRequest.getFolderId();
                communicationPath = aggregatorMessageRequest.getCommunicationPath();
                redemptionId = aggregatorMessageRequest.getRedemptionId();
                messageTypeId = aggregatorMessageRequest.getMessageTypeId();
                fileTypeCode = aggregatorMessageRequest.getFileTypeCode();

            }

            log.info("Campaign Id : " + campaignId);
            log.info("Message Context : " + messageContext);
            log.info("Redemption Id: " + redemptionId);
            // Processing of Y,YES,YEP e.t.c
            ValidResponse validResponse = refillReminderDAO.getValidResponse(message);

            if (validResponse == null) {
                log.info("No valid response found. (System will return)");
                return;
            }

            InstantRedemption irf = refillReminderDAO.getInstantRedemptionDetailByRedemptionId(redemptionId);
            if (irf == null) {
                log.info("IRF record not found (System will continue)");
                return;
            }

            if (messageContext == null || messageContext.trim().length() == 0) {
                log.info("Message Context is null. (Return)");
                return;
            }

            messageContext = messageContext.trim();

            String communicatoinMethod = irf.getCommunicationMethod();

            if (communicatoinMethod == null || communicatoinMethod.trim().length() == 0) {
                log.info("No communicaiton method defined (System will continue)");
                return;
            }

            communicatoinMethod = communicatoinMethod.trim();

            if (!(communicatoinMethod.equalsIgnoreCase("T") || communicatoinMethod.equalsIgnoreCase("E"))) {
                log.info("Communication method has not value of E or T (System will continue)");
                return;
            }

            Campaigns campaigns = refillReminderDAO.getCampaignsById(campaignId);

            if (campaigns == null) {
                log.info("Campaign detail not found against CampaignId: " + campaignId);
                log.info("(System will continue)");
                return;
            }

            int shortCode = campaigns.getShortCodes().getShortCode();
            String campaignName = campaigns.getCampaignName();

            if (messageContext.equalsIgnoreCase(Constants.REDEMPTION) || messageContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REFILL) || messageContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REPEAT_REFILL)) {

                String refillFlag = campaigns.getIsRefill();
                log.info("Refill Flag at comapgn level : " + refillFlag);
                if (!messageContext.equalsIgnoreCase(Constants.REDEMPTION)) {
                    if (refillFlag == null || refillFlag.trim().length() == 0) {
                        log.info("No refill configured at campaign level. (System will return)");
                        return;
                    }

                    refillFlag = refillFlag.trim();

                    if (!refillFlag.equalsIgnoreCase("YES")) {
                        log.info("Refill flag value not equal to YES. (System will return)");
                        return;
                    }
                }

                if (messageContext.equalsIgnoreCase(Constants.REDEMPTION) && communicatoinMethod.equalsIgnoreCase("T")) {
                    CampaignMessagesResponse campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);

                    if (campaignMessageResponses == null) {
                        log.info("CampaignMessagesResponse is null in thread");
                        return;
                    }

                    Integer nextMessageTypeId = campaignMessageResponses.getNextMessage();

                    log.info("Next Message Type Id : " + nextMessageTypeId);

                    if (nextMessageTypeId == null || nextMessageTypeId == 0) {
                        log.info("Next message type id is 0");
                        return;
                    }

                    campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

                    sendMessage(campaignMessageResponses, campaignId, folderId, communicationPath, irf, null, campaigns, phoneNumber, fileTypeCode, messageContext, appName, campaignName, inputReferenceNumber, path, aggregatorMessageRequest, Boolean.FALSE);

                } else if (communicatoinMethod.equalsIgnoreCase("T")) {

                    Order order = refillReminderDAO.getOrderDetailByTransactionNumber(irf.getCardholderFirstName(), irf.getCardholderLastName(), irf.getCampaignId(), irf.getCardholderDob());
                    if (order == null) {
                        log.info("Order is null in thread");
                        return;
                    }
                    Order ord = new Order();
                    BeanUtils.copyProperties(order, ord, new String[]{"id"});

                    DoDirectPayment payment = new DoDirectPayment();
                    DoDirectPaymentResponseType res = payment.doDirectPayment(ord.getCardType(), ord.getCardNumber(), ord.getCardExpiry(),
                            ord.getCardHolderName(), ord.getPayment().toString(), ord.getCardCvv());
                    boolean doDirectRes = false;
                    CampaignMessagesResponse campaignMessagesResponse;
                    if (res.getAck().getValue().equalsIgnoreCase("success")) {
                        Double copay = 0.0;
                        List<RedemptionIngredient> ingredients = redemptionDAO.getRedemptionIngredientByTransactionNumber(irf.getId().getTransactionNumber());
                        if (ingredients != null && !ingredients.isEmpty()) {
                            for (RedemptionIngredient ingredient : ingredients) {
                                Drug drug = redemptionDAO.getMaxOfferByNdc(ingredient.getNdc());
//                                if (drug != null) {
//                                    copay += drug.getMaxOffer();
//                                }
                            }
                        }
                        ord.setOrderTrackingNo(null);
                        ord.setCreatedOn(new Date());
                        ord.setUpdatedOn(new Date());
                        log.info("Copay: " + copay);
                        log.info("PtOutOfPocket: " + irf.getPtOutOfPocket());
                        Double paymentAmount = irf.getPtOutOfPocket().doubleValue() - copay;
                        log.info("PaymentAmount: " + paymentAmount);
                        //ord.setCopay(copay);
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
                        order = ord;
                        doDirectRes = Boolean.TRUE;
                        //success message
                        campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResComm(campaignId, folderId, Constants.MSG_CONTEXT_REFILL_SUCCESS, communicationPath);
                    } else {
                        List<ErrorType> errorList = res.getErrors();
                        //denied message
                        campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResComm(campaignId, folderId, Constants.MSG_CONTEXT_REFILL_DENIED, communicationPath);
                    }
                    sendMessage(campaignMessagesResponse, campaignId, folderId, communicationPath, irf, order, campaigns, phoneNumber, fileTypeCode, messageContext, appName, campaignName, inputReferenceNumber, path, aggregatorMessageRequest, doDirectRes);
                }
                return;
            }

        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

    private boolean sendMessage(CampaignMessagesResponse campaignMessagesResponse, int campaignId, int folderId, String communicationPath, InstantRedemption irf, Order order, Campaigns campaigns, String phoneNumber, String fileTypeCode, String messageContext, String appName, String campaignName, long inputReferenceNumber, String path, AggregatorMessageRequest aggregatorMessageRequest, boolean doDirectRes) {
        CampaignTrigger campaignTrigger;
        String trigger;
        int shortCode;
        GatewayInfo gatewayInfo;
        int sentMessageCount;
        Double payment = null;
        if (order != null) {
            payment = order.getPayment();
        }
        if (campaignMessagesResponse == null) {
            log.info("CampaignMessagesResponse is null in thread");
            return true;
        }
        Intervals interval = campaignMessagesResponse.getIntervals();
        IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
        int intervalId = interval.getIntervalId();
        String intervalDesc = +interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
        String eventDesc = refillReminderDAO.getEventsDescription(campaignId, folderId);
        int intervalVal = interval.getIntervalValue();
        int intervalUnitInSecond = intervalsType.getUnitInSecond();
        long secondsDelay = intervalVal * intervalUnitInSecond;
        int nextMessageTypeId = campaignMessagesResponse.getMessageTypeId();
        List<CampaignMessages> campaignMessagesList = refillReminderDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, campaignMessagesResponse.getMessageTypeId(), communicationPath);
        if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
            log.info("No messages found for (System will return)");
            return true;
        }
        CampaignMessages campaignMessages = campaignMessagesList.get(0);
        if (campaignMessages == null) {
            log.info("CampaignMessages object not found. (System will return)");
            return true;
        }
        String smsMessage = campaignMessages.getSmstext();
        log.info("IRF Transction Number is :" + irf.getId().getTransactionNumber());
        String servlet = "/order/placeorder/" + irf.getId().getTransactionNumber();
        String orderPlaceUrl = Constants.APP_PATH + servlet;
        String pharmacyName = null;
        String pharmacyPhone = null;
        if (order != null && doDirectRes == Boolean.TRUE) {
            smsMessage = smsMessage.replaceAll("CARD_TYPE", order.getCardType());
            smsMessage = smsMessage.replaceAll("NNNN", order.getCardNumber());

            orderPlaceUrl = Constants.APP_PATH + "/order/status/" + order.getId();
            pharmacyName = order.getPharmacy().getTitle();
            pharmacyPhone = order.getPharmacy().getPhone();
        }
        smsMessage = TextFlowUtil.prepareMessage(null, null, pharmacyName, pharmacyPhone,
                smsMessage, irf.getPrescriptionNumber(), null, orderPlaceUrl, payment, null, null, null);
        campaignTrigger = refillReminderDAO.getTriggerByCampaignId(campaignId);
        trigger = campaignTrigger.getId().getKeyword();
        shortCode = campaigns.getShortCodes().getShortCode();
        gatewayInfo = refillReminderDAO.getGatewayInfo(shortCode);
        OptInOut optInOut = refillReminderDAO.checkOptInOut(phoneNumber, campaignId, shortCode);
        String optInOutFlag = null;
        if (optInOut != null) {
            optInOutFlag = optInOut.getOptInOut();
        }
        if (optInOutFlag != null && optInOutFlag.trim().equalsIgnoreCase("O")) {
            log.info("PhoneNumber : " + phoneNumber + " has opted opted out from campaign : " + campaignId);
            return true;
        }

        log.info("Thread going to sleep for " + secondsDelay + " Seconds.");
        AggregatorMessageRequest aggregatorMessageRequestNext = null;
        if (smsMessage != null && smsMessage.trim().length() > 0) {
            campaignMessages.setSmstext(smsMessage);
            aggregatorMessageRequestNext = new AggregatorMessageRequest();
            aggregatorMessageRequestNext.setCardholderDob(irf.getCardholderDob());
            aggregatorMessageRequestNext.setClaimStatus(irf.getId().getClaimStatus());
            aggregatorMessageRequestNext.setFileTypeCode(fileTypeCode);
            aggregatorMessageRequestNext.setFolderId(folderId);
            aggregatorMessageRequestNext.setMessageTypeId((nextMessageTypeId));
            aggregatorMessageRequestNext.setCampaignId(campaignId);
            aggregatorMessageRequestNext.setNdcNumber(irf.getNdcNumber());
            aggregatorMessageRequestNext.setPhoneNumber(phoneNumber);
            aggregatorMessageRequestNext.setRedemptionId(irf.getRedemptionId());
            aggregatorMessageRequestNext.setRxGroupNumber(irf.getRxGroupNumber());
            aggregatorMessageRequestNext.setFillDate(irf.getFillDate());
            if (messageContext.equalsIgnoreCase(Constants.REDEMPTION)) {
                aggregatorMessageRequestNext.setMessageContext(Constants.REDEMPTION);
            } else {
                aggregatorMessageRequestNext.setMessageContext(Constants.MSG_CONTEXT_REFILL_CONFIRMATION);
                appName = "PMS " + campaignName + " " + Constants.MSG_CONTEXT_REFILL_CONFIRMATION;
            }
            aggregatorMessageRequestNext.setInputReferenceNumber(inputReferenceNumber);
            aggregatorMessageRequestNext.setIntervalDescription(intervalDesc);
            aggregatorMessageRequestNext.setIntervalId(intervalId);
            aggregatorMessageRequestNext.setEventDetail(eventDesc);
            aggregatorMessageRequestNext.setCommunicationPath(communicationPath);
            sentMessageCount = refillReminderDAO.retrieveMessageCountByTypeForRedemption(aggregatorMessageRequestNext);
            log.info("Sent Message Count : " + sentMessageCount);
            if (sentMessageCount > 0) {
                log.info("Refill Message already sent (for phone number " + phoneNumber);
                return true;
            }
        }
        if (path.equalsIgnoreCase("Text")) {
            aggregatorMessageRequest.setEndDate(new Date());
            refillReminderDAO.update(aggregatorMessageRequest);
        }
        TextIntervalReminderThread intervalMessageThread = new TextIntervalReminderThread();
        intervalMessageThread.setGatewayInfo(gatewayInfo);
        intervalMessageThread.setAggregatorMessageRequest(aggregatorMessageRequestNext);
        intervalMessageThread.setCampaignMessages(campaignMessages);
        intervalMessageThread.setAppName(appName);
        intervalMessageThread.setProgramCode(trigger);
        intervalMessageThread.setPhoneNumber(phoneNumber);
        intervalMessageThread.setInputReferenceNumber(0);
        intervalMessageThread.setSecondsDelay(secondsDelay);
        intervalMessageThread.setCampaign(campaigns);
        intervalMessageThread.setRefillReminderDAO(refillReminderDAO);
        intervalMessageThread.setFolderId(folderId);
        intervalMessageThread.setMessageTypeId((nextMessageTypeId));
        intervalMessageThread.setCommunicationType(communicationPath);
        intervalMessageThread.setIntervalId(intervalId);
        intervalMessageThread.setIntervalDesc(intervalDesc);
        intervalMessageThread.setEventDesc(eventDesc);
        intervalMessageThread.setInstantRedemption(irf);
        intervalMessageThread.setThreadType("res_handler");
        Thread smsIntervalMessageThread = new Thread(intervalMessageThread);
        smsIntervalMessageThread.start();
        return false;
    }

}
