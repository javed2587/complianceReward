package com.ssa.cms.thread;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.AggregatorMessageResponse;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.util.TextFlowUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RedemptionMessageThread implements Runnable {

    private int folderId;
    private int messageTypeId;
    private RedemptionService redemptionDAO;
    private Campaigns campaign;
    private GatewayInfo gatewayInfo;
    private InstantRedemption instantRedemption;
    private String messageContext;
    private boolean markEndDate = false;
    private long inputReferenceNumber;
    private String communicationPath;
    private ShortCodes shortCodes;
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public void run() {

        try {

            int campaignId = campaign.getCampaignId();

            CampaignMessagesResponse campaignMessageResponses = redemptionDAO.getCampaignMessagesResponseBycommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread");
                return;
            }

            boolean pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);
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

            campaignMessageResponses = redemptionDAO.getCampaignMessagesResponseBycommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);
            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread for next message");
                return;
            }

            List<CampaignMessages> campaignMessagesList = redemptionDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);
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
            Double copay = instantRedemption.getCopay();

            String servlet = null;
            String orderStatusUrl = null;
            Order order = redemptionDAO.getOrderByTransactionsNo(instantRedemption.getId().getTransactionNumber());
            String orderNumber = null;
            if (order != null) {
                servlet = "/order/status/" + order.getId();
                orderStatusUrl = Constants.APP_PATH + servlet;
                orderNumber = order.getId();
            } else {
                logger.info("Order value null against this TransactionsNo thats why order status url cannot replace: " + instantRedemption.getId().getTransactionNumber());
            }
            int refillAllowed = instantRedemption.getRefillAllowed();
            int refillUsed = instantRedemption.getRefillsUsed();
            int remainingRefill = refillAllowed - refillUsed;
            message = TextFlowUtil.prepareMessage(copay, instantRedemption.getPtOutOfPocket(),
                    instantRedemption.getPharmacyName(), instantRedemption.getPharmacyPhone(), message,
                    orderNumber, remainingRefill, orderStatusUrl, null, null, null, null);
            campaignMessages.setSmstext(message);

            String phoneNumber = instantRedemption.getCommunicationId();

            logger.info("SMS Text : " + message);

            Intervals interval = campaignMessageResponses.getIntervals();
            IntervalsType intervalType = interval.getIntervalsType();
            int intervalValue = interval.getIntervalValue();
            int intervalUnitInSecond = intervalType.getUnitInSecond();
            long secondsDelay = intervalValue * intervalUnitInSecond;
            int intervalId = interval.getIntervalId();
            String intervalDesc = +interval.getIntervalValue() + " " + intervalType.getIntervalsTypeTitle();
            String eventDesc = redemptionDAO.getEventsDescription(campaignId, folderId, communicationPath);

            logger.info("Interval Value : " + intervalValue + " Intervl Unit In Seconds : " + intervalUnitInSecond + " Final interval magnitude in seconds : " + secondsDelay);

            Thread.sleep(1000 * secondsDelay);

            if (message != null && message.trim().length() > 0) {
                MTDecorator decorator = SMSUtil.sendSmsMessage(phoneNumber, campaignMessages.getSmstext());
                saveMessageRequestResponse(decorator, campaignMessages, phoneNumber, intervalId, intervalDesc, eventDesc);
            }

            pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);

            if (pairedFlag) {
                setRedemptionThread(nextMessageTypeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setRedemptionThread(Integer nextMessageTypeId) {
        RedemptionMessageThread messageThread = new RedemptionMessageThread();
        messageThread.setCampaign(campaign);
        messageThread.setFolderId(folderId);
        messageThread.setGatewayInfo(gatewayInfo);
        messageThread.setInputReferenceNumber(inputReferenceNumber);
        messageThread.setInstantRedemption(instantRedemption);
        messageThread.setMessageContext(messageContext);
        messageThread.setMessageTypeId(nextMessageTypeId);
        messageThread.setRedemptionDAO(redemptionDAO);
        messageThread.setCommunicationPath(communicationPath);
        messageThread.setShortCodes(shortCodes);
        Thread thread = new Thread(messageThread);
        thread.start();
    }

    private void saveMessageRequestResponse(MTDecorator decorator, CampaignMessages campaignMessages, String phoneNumber, int intervalId, String intervalDesc, String eventDesc) {
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
        messageRequest.setIntervalId(intervalId);
        messageRequest.setIntervalDescription(intervalDesc);
        messageRequest.setEventDetail(eventDesc);
        messageRequest.setCommunicationPath(communicationPath);

        if (markEndDate) {
            messageRequest.setEndDate(new Date());
        }

        messageRequest.setFillDate(instantRedemption.getFillDate());

        messageRequest.setMessageContext(this.messageContext);

        redemptionDAO.save(messageRequest);
        messageResponse = new AggregatorMessageResponse();
        messageResponse.setErrorCode(decorator.getErrorCode());
        messageResponse.setErrorDescription(decorator.getErrorDescription());
        messageResponse.setMessageReqNo(messageRequest.getMessageReqNo());
        messageResponse.setMessageResponse(decorator.getResponseXML());
        messageResponse.setTicketId(decorator.getTicketId());
        messageResponse.setMtsId(decorator.getMtsId());
        messageResponse.setEffectiveDate(new Date());
        redemptionDAO.save(messageResponse);
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

    public RedemptionService getRedemptionDAO() {
        return redemptionDAO;
    }

    public void setRedemptionDAO(RedemptionService redemptionDAO) {
        this.redemptionDAO = redemptionDAO;
    }

    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }

    public ShortCodes getShortCodes() {
        return shortCodes;
    }

    public void setShortCodes(ShortCodes shortCodes) {
        this.shortCodes = shortCodes;
    }
}
