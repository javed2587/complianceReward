package com.ssa.cms.thread;

import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.AggregatorMessageResponse;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.Order;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.RedemptionUtil;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.util.TextFlowUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class RefillReminderMessageThread implements Runnable {

    private int folderId;
    private int messageTypeId;
    private RefillReminderService refillReminderDAO;
    private Campaigns campaign;
    private GatewayInfo gatewayInfo;
    private InstantRedemption instantRedemption;
    private String messageContext;
    private boolean markEndDate = false;
    private long inputReferenceNumber;
    private String communicationPath;
    private static final Logger logger = Logger.getLogger(RefillReminderMessageThread.class);

    @Override
    public void run() {

        try {

            int campaignId = campaign.getCampaignId();
            CampaignMessagesResponse campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread");
                return;
            }
            boolean pairedFlag;
            pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);
            if (!pairedFlag) {
                logger.info("No pair message found continue");
                return;
            }
            Integer nextMessageTypeId = campaignMessageResponses.getNextMessage();

            logger.info("Next Message Type Id : " + nextMessageTypeId);
            if (nextMessageTypeId == null || nextMessageTypeId == 0) {
                logger.info("Next message type id is 0");
                return;
            }
            campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread for next message");
                return;
            }

            List<CampaignMessages> campaignMessagesList = refillReminderDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                logger.info("No messages found for (System will return)");
                return;
            }

            CampaignMessages campaignMessages = campaignMessagesList.get(0);

            if (campaignMessages == null) {
                logger.info("CampaignMessages object not found. (System will return)");
                return;
            }

            String message = campaignMessages.getSmstext();
            String phoneNumber = instantRedemption.getCommunicationId();

            logger.info("SMS Text : " + message);
            Order order = refillReminderDAO.getOrderDetailByTransactionNumber(instantRedemption.getCardholderFirstName(), instantRedemption.getCardholderLastName(), campaignId, instantRedemption.getCardholderDob());
            Double payment = null;
            String pharmacyName = null;
            String pharmacyPhone = null;
            String orderPlaceUrl = null;
            
            if (order != null) {
                payment = order.getPayment();
                pharmacyName = order.getPharmacy().getTitle();
                pharmacyPhone = order.getPharmacy().getPhone();
                orderPlaceUrl = RedemptionUtil.preparePlaceOrderURL(instantRedemption.getId().getTransactionNumber());
                message = TextFlowUtil.prepareMessage(null, null, pharmacyName, pharmacyPhone,
                    message, instantRedemption.getPrescriptionNumber(), null, orderPlaceUrl, payment, null, order.getCardType(), order.getCardNumber());
            } else {
                logger.info("Order value is null: " + order);
                payment = null;
                pharmacyName = instantRedemption.getPharmacyName();
                pharmacyPhone = instantRedemption.getPharmacyPhone();
                orderPlaceUrl = null;
                message = TextFlowUtil.prepareMessage(null, null, pharmacyName, pharmacyPhone,
                    message, instantRedemption.getPrescriptionNumber(), null, orderPlaceUrl, payment, null, null, null);
            }
            
            Intervals interval = campaignMessageResponses.getIntervals();
            IntervalsType intervalType = interval.getIntervalsType();

            int intervalValue = interval.getIntervalValue();
            int intervalUnitInSecond = intervalType.getUnitInSecond();
            long secondsDelay = intervalValue * intervalUnitInSecond;
            int intervalId = interval.getIntervalId();
            String intervalDesc = +interval.getIntervalValue() + " " + intervalType.getIntervalsTypeTitle();
            String eventDesc = refillReminderDAO.getEventsDescription(campaignId, folderId);

            logger.info("Interval Value : " + intervalValue);
            logger.info("Intervl Unit In Seconds : " + intervalUnitInSecond);
            logger.info("Final interval magnitude in seconds : " + secondsDelay);

            logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");
            Thread.sleep(1000 * secondsDelay);

            MTDecorator decorator = SMSUtil.sendSmsMessage(phoneNumber, message);
            AggregatorMessageRequest messageRequest;
            AggregatorMessageResponse messageResponse;
            messageRequest = new AggregatorMessageRequest();
            messageRequest.setCardholderDob(instantRedemption.getCardholderDob());
            messageRequest.setClaimStatus(instantRedemption.getId().getClaimStatus());
            messageRequest.setFileTypeCode(instantRedemption.getFileTypeCode());
            messageRequest.setFolderId(folderId);
            messageRequest.setMessageRequest(decorator.getRequestXML());
            messageRequest.setMessageText(decorator.getMessageText());
            messageRequest.setMessageTypeId((campaignMessages.getMessageType().getId().getMessageTypeId()));
            messageRequest.setCampaignId(campaign.getCampaignId());
            messageRequest.setNdcNumber(instantRedemption.getNdcNumber());
            messageRequest.setPhoneNumber(phoneNumber);
            messageRequest.setRedemptionId(instantRedemption.getRedemptionChannelId());
            messageRequest.setRxGroupNumber(instantRedemption.getRxGroupNumber());
            messageRequest.setEffectiveDate(new Date());
            messageRequest.setFileTypeCode(instantRedemption.getFileTypeCode());
            messageRequest.setFileName(instantRedemption.getFileName());
            messageRequest.setRedemptionId(instantRedemption.getRedemptionId());
            messageRequest.setIntervalDescription(intervalDesc);
            messageRequest.setIntervalId(intervalId);
            messageRequest.setEventDetail(eventDesc);
            messageRequest.setCommunicationPath(communicationPath);

            if (markEndDate) {
                messageRequest.setEndDate(new Date());
            }

            messageRequest.setFillDate(instantRedemption.getFillDate());

            messageRequest.setMessageContext(this.messageContext);

            refillReminderDAO.save(messageRequest);
            messageResponse = new AggregatorMessageResponse();
            messageResponse.setErrorCode(decorator.getErrorCode());
            messageResponse.setErrorDescription(decorator.getErrorDescription());
            messageResponse.setMessageReqNo(messageRequest.getMessageReqNo());
            messageResponse.setMessageResponse(decorator.getResponseXML());
            messageResponse.setTicketId(decorator.getTicketId());
            messageResponse.setMtsId(decorator.getMtsId());
            messageResponse.setEffectiveDate(new Date());
            refillReminderDAO.save(messageResponse);

            pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);
            if (pairedFlag) {
                RefillReminderMessageThread messageThread = new RefillReminderMessageThread();

                messageThread.setFolderId(folderId);
                messageThread.setGatewayInfo(gatewayInfo);
                messageThread.setMessageTypeId(nextMessageTypeId);
                messageThread.setRefillReminderDAO(refillReminderDAO);
                messageThread.setCampaign(campaign);
                messageThread.setMessageContext(messageContext);
                messageThread.setInstantRedemption(instantRedemption);
                messageThread.setCommunicationPath(communicationPath);

                Thread thread = new Thread(messageThread);
                thread.start();
            }

        } catch (Exception e) {
            logger.error("Exception->RefillReminderMessageThread: ", e);
        }
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }

    public RefillReminderService getRefillReminderDAO() {
        return refillReminderDAO;
    }

    public void setRefillReminderDAO(RefillReminderService refillReminderDAO) {
        this.refillReminderDAO = refillReminderDAO;
    }

    public GatewayInfo getGatewayInfo() {
        return gatewayInfo;
    }

    public void setGatewayInfo(GatewayInfo gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }

    public InstantRedemption getInstantRedemption() {
        return instantRedemption;
    }

    public void setInstantRedemption(InstantRedemption instantRedemption) {
        this.instantRedemption = instantRedemption;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public boolean isMarkEndDate() {
        return markEndDate;
    }

    public void setMarkEndDate(boolean markEndDate) {
        this.markEndDate = markEndDate;
    }

    public long getInputReferenceNumber() {
        return inputReferenceNumber;
    }

    public void setInputReferenceNumber(long inputReferenceNumber) {
        this.inputReferenceNumber = inputReferenceNumber;
    }

    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }
}
