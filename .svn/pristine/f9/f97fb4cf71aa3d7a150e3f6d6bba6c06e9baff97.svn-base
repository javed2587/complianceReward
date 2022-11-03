package com.ssa.cms.thread;

import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.util.RedemptionUtil;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.util.TextFlowUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextFlowMessageThread implements Runnable {

    @Autowired
    private PMSTextFlowService textFlowDAO;
    private int folderId;
    private int messageTypeId;
    private long inputReferenceNumber;

    private String groupNumber;
    private String communicationPath;

    private CustomerRequest customerRequest;
    private Campaigns campaign;
    private ShortCodes shortCode;

    private static final Logger logger = Logger.getLogger(TextFlowMessageThread.class);

    @Override
    public void run() {

        try {

            int campaignId = campaign.getCampaignId();
            CampaignMessagesResponse campaignMessageResponses = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);

            if (campaignMessageResponses == null) {
                logger.info("TextFlowMessageThread >> CampaignMessagesResponse is null in thread");
                return;
            }
        boolean pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);
            if (!pairedFlag) {
                logger.info("TextFlowMessageThread >> No next paired message");

                boolean endFlag = TextFlowUtil.isHierarchyEnd(campaignMessageResponses);

                if (endFlag) {
                    TextFlowUtil.markCampaignEnd(customerRequest, campaign, groupNumber, textFlowDAO);
                }
                return;
            }

            Integer nextMessageTypeId = campaignMessageResponses.getNextMessage();
            logger.info("TextFlowMessageThread >> Next Message Type Id : " + nextMessageTypeId);

            if (nextMessageTypeId == null || nextMessageTypeId == 0) {
                logger.info("TextFlowMessageThread >> Next message type id is 0");
                return;
            }

            campaignMessageResponses = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessageResponses == null) {
                logger.info("TextFlowMessageThread >> CampaignMessagesResponse is null in thread for next message");
                return;
            }

            List<CampaignMessages> campaignMessagesList = textFlowDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

            if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                logger.info("TextFlowMessageThread >> No messages found for (System will return)");
                return;
            }

            CampaignMessages campaignMessages = campaignMessagesList.get(0);
            if (campaignMessages == null) {
                logger.info("TextFlowMessageThread >> CampaignMessages object not found. (System will return)");
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

            int intervalId = interval.getIntervalId();
            String intervalDesc = interval.getIntervalValue() + " " + intervalType.getIntervalsTypeTitle();
            String eventDesc = textFlowDAO.getEventsDescription(campaignId, folderId, communicationPath);

            logger.info("Interval Value : " + intervalValue);
            logger.info("Intervl Unit In Seconds : " + intervalUnitInSecond);
            logger.info("Final interval magnitude in seconds : " + secondsDelay);

            logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");

            Thread.sleep(1000 * secondsDelay);

            long crSeqNo = customerRequest.getCrSeqNo();
            String campaignName = campaign.getCampaignName();
            Integer shortCodeValue = shortCode.getShortCode();

            if (message != null && message.trim().length() > 0) {
                if (message.contains("PT_INFO_URL")) {
                    message = message.replace("PT_INFO_URL", RedemptionUtil.preparePatientProfileURL(phoneNumber));
                }
                CampaignMessageRequest campaignMessageRequest = saveCampaignMessageRequest(crSeqNo, campaignId, campaignName, shortCodeValue,
                        messageId, nextMessageTypeId, intervalId, intervalDesc, eventDesc);

                //send message
                MTDecorator decorator = SMSUtil.sendSmsText(phoneNumber, message);

                textFlowDAO.saveCampaignMessageReqRes(decorator, campaignMessageRequest.getCmrSeqNo(), shortCodeValue,
                        campaignId, campaignName, messageId, nextMessageTypeId, folderId, inputReferenceNumber);
            }

            pairedFlag = TextFlowUtil.hasPairedMessage(campaignMessageResponses);

            if (pairedFlag) {
                startTextFlowMessageThread(nextMessageTypeId);
            } else {

                boolean endFlag = TextFlowUtil.isHierarchyEnd(campaignMessageResponses);

                if (endFlag) {
                    TextFlowUtil.markCampaignEnd(customerRequest, campaign, groupNumber, textFlowDAO);
                }
            }
        } catch (Exception e) {
            logger.error("TextFlowMessageThread >> IOException: " + e);
        }

    }

    private void startTextFlowMessageThread(Integer nextMessageTypeId) {
        TextFlowMessageThread messageThread = new TextFlowMessageThread();
        messageThread.setFolderId(folderId);
        messageThread.setMessageTypeId(nextMessageTypeId);
        messageThread.setCustomerRequest(customerRequest);
        messageThread.setTextFlowDAO(textFlowDAO);
        messageThread.setCampaign(campaign);
        messageThread.setShortCode(shortCode);
        messageThread.setInputReferenceNumber(inputReferenceNumber);
        messageThread.setGroupNumber(groupNumber);
        messageThread.setCommunicationPath(communicationPath);
        Thread thread = new Thread(messageThread);
        thread.start();
    }

    private CampaignMessageRequest saveCampaignMessageRequest(long crSeqNo, int campaignId, String campaignName, Integer shortCodeValue, int messageId, Integer nextMessageTypeId, int intervalId, String intervalDesc, String eventDesc) {
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
        textFlowDAO.save(campaignMessageRequest);
        return campaignMessageRequest;
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

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }
}
