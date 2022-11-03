package com.ssa.cms.thread;

import com.ssa.cms.model.CampaignMessageReqRes;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.util.TextFlowUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class TextFlowRepeatMessageThread implements Runnable {

    private int folderId;
    private int messageTypeId;
    private PMSTextFlowService textFlowDAO;
    private CustomerRequest customerRequest;
    private Campaigns campaign;
    private ShortCodes shortCode;
    private long inputReferenceNumber;
    private String cardNumber;
    private String communicationPath;
    private int intervalId;
    private static final Logger logger = Logger.getLogger(TextFlowRepeatMessageThread.class);

    @Override

    public void run() {

        try {

            int campaignId = campaign.getCampaignId();
            CampaignMessagesResponse campaignMessageResponses = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);

            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread");
                return;
            }

            boolean pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);

            if (!pairedFlag) {
                logger.info("No more paired message (Thread will return)");
                return;
            }

            Integer nextMessageTypeId = campaignMessageResponses.getNextMessage();

            logger.info("Next Message Type Id : " + nextMessageTypeId);

            if (nextMessageTypeId == null || nextMessageTypeId == 0) {
                logger.info("Next message type id is 0");
                return;
            }

            campaignMessageResponses = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessageResponses == null) {
                logger.info("CampaignMessagesResponse is null in thread for next message");
                return;
            }

            List<CampaignMessages> campaignMessagesList = textFlowDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                logger.info("No messages found for (System will return)");
                return;
            }

            CampaignMessages campaignMessages = campaignMessagesList.get(0);

            if (campaignMessages == null) {
                logger.info("CampaignMessages object not found. (System will return)");
                return;
            }

            int messageId = campaignMessages.getMessageId();
            String message = campaignMessages.getSmstext();
            String phoneNumber = customerRequest.getPhoneNumber();
            logger.info("SMS Text : " + message);

            Intervals interval = campaignMessageResponses.getIntervals();
            IntervalsType intervalType = interval.getIntervalsType();

            int intervalValue = interval.getIntervalValue();
            int intervalUnitInSecond = intervalType.getUnitInSecond();
            long secondsDelay = intervalValue * intervalUnitInSecond;
            String intervalDesc = interval.getIntervalValue() + " " + intervalType.getIntervalsTypeTitle();
            String eventDesc = textFlowDAO.getEventsDescription(campaignId, folderId, communicationPath);

            logger.info("Interval Value : " + intervalValue);
            logger.info("Intervl Unit In Seconds : " + intervalUnitInSecond);
            logger.info("Final interval magnitude in seconds : " + secondsDelay);

            long crSeqNo = customerRequest.getCrSeqNo();
            String campaignName = campaign.getCampaignName();
            Integer shortCodeValue = shortCode.getShortCode();

            logger.info("Going to sleep for " + secondsDelay + " Seconds");
            Thread.sleep(1000 * secondsDelay);
            if (message != null && message.trim().length() > 0) {

                int sentMessageCount = textFlowDAO.sentMessageCountCampaign(phoneNumber, campaignId, folderId, nextMessageTypeId, "Yes");

                if (sentMessageCount > 0) {
                    logger.info("Message already sent to Phone Number : " + phoneNumber);
                    return;
                }

                CampaignMessageRequest campaignMessageRequest = new CampaignMessageRequest();

                campaignMessageRequest.setCrSeqNo(crSeqNo);
                campaignMessageRequest.setCampaignId(campaignId);
                campaignMessageRequest.setCampaignName(campaignName);
                campaignMessageRequest.setEffectiveDate(new Date());
                campaignMessageRequest.setShortCode(shortCodeValue);
                campaignMessageRequest.setMessageId(messageId);
                campaignMessageRequest.setMessageTypeId(nextMessageTypeId);
                campaignMessageRequest.setFolderId(folderId);
                campaignMessageRequest.setInputReferenceNumber(inputReferenceNumber);
                campaignMessageRequest.setIntervalId(intervalId);
                campaignMessageRequest.setIntervalDescription(intervalDesc);
                campaignMessageRequest.setEventDetail(eventDesc);
                campaignMessageRequest.setCommunicationPath(communicationPath);
                campaignMessageRequest.setIsRepeat("Yes");

                textFlowDAO.save(campaignMessageRequest);

                MTDecorator decorator = SMSUtil.sendSmsMessage(phoneNumber, message);

                CampaignMessageReqRes campaignMessageReqRes = new CampaignMessageReqRes();

                campaignMessageReqRes.setCmrSeqNo(campaignMessageRequest.getCmrSeqNo());

                campaignMessageReqRes.setCmrSeqNo(campaignMessageRequest.getCmrSeqNo());
                campaignMessageReqRes.setErrorCode(decorator.getErrorCode());
                campaignMessageReqRes.setErrorDescription(decorator.getErrorDescription());
                campaignMessageReqRes.setSmsRequest(decorator.getRequestXML());
                campaignMessageReqRes.setSmsResponse(decorator.getResponseXML());
                campaignMessageReqRes.setMtsId(decorator.getMtsId());
                campaignMessageReqRes.setMessageText(decorator.getMessageText());
                campaignMessageReqRes.setTicketId(decorator.getTicketId());

                campaignMessageReqRes.setShortCode(shortCodeValue);
                campaignMessageReqRes.setEffectiveDate(new Date());
                campaignMessageReqRes.setCampaignId(campaignId);
                campaignMessageReqRes.setCampaignName(campaignName);
                campaignMessageReqRes.setMessageId(messageId);
                campaignMessageReqRes.setFolderId(folderId);
                campaignMessageReqRes.setMessageTypeId(nextMessageTypeId);
                campaignMessageReqRes.setInputReferenceNumber(inputReferenceNumber);
                textFlowDAO.save(campaignMessageReqRes);
            }

            pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);

            if (pairedFlag) {

                TextFlowRepeatMessageThread messageThread = new TextFlowRepeatMessageThread();

                messageThread.setFolderId(folderId);
                messageThread.setMessageTypeId(nextMessageTypeId);
                messageThread.setCustomerRequest(customerRequest);
                messageThread.setTextFlowDAO(textFlowDAO);
                messageThread.setCampaign(campaign);
                messageThread.setShortCode(shortCode);
                messageThread.setInputReferenceNumber(inputReferenceNumber);
                messageThread.setCardNumber(cardNumber);
                messageThread.setCommunicationPath(communicationPath);
                messageThread.setIntervalId(intervalId);
                Thread thread = new Thread(messageThread);
                thread.start();
            }

        } catch (Exception e) {
            logger.error(e);
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

    public PMSTextFlowService getTextFlowDAO() {
        return textFlowDAO;
    }

    public void setTextFlowDAO(PMSTextFlowService textFlowDAO) {
        this.textFlowDAO = textFlowDAO;
    }

    public CustomerRequest getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }

    public ShortCodes getShortCode() {
        return shortCode;
    }

    public void setShortCode(ShortCodes shortCode) {
        this.shortCode = shortCode;
    }

    public long getInputReferenceNumber() {
        return inputReferenceNumber;
    }

    public void setInputReferenceNumber(long inputReferenceNumber) {
        this.inputReferenceNumber = inputReferenceNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }

    /**
     * @return the intervalId
     */
    public int getIntervalId() {
        return intervalId;
    }

    /**
     * @param intervalId the intervalId to set
     */
    public void setIntervalId(int intervalId) {
        this.intervalId = intervalId;
    }
}
