package com.ssa.cms.remainder;

import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.ReminderPOJO;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.thread.TextIntervalMessageRequestThread;
import com.ssa.cms.util.TextFlowUtil;
import java.util.List;
import org.apache.log4j.Logger;

public class PMSGenericTextFlowReminder {

    private static final Logger logger = Logger.getLogger(PMSGenericTextFlowReminder.class);

    public void processTextFlowReminder(int yCount, PMSTextFlowService textFlowDAO) {
        try {

            logger.info("yCount : " + yCount);
            List<ReminderPOJO> list = textFlowDAO.getTextFlowReminderRecords(yCount);

            if (list == null || list.isEmpty()) {
                logger.info("No campaign reminder record found. (System will return)");
                return;
            }

            logger.info("List Size : " + list.size());
            for (ReminderPOJO reminderPOJO : list) {

                int campaignId = reminderPOJO.getCampaignId();
                int folderId = reminderPOJO.getFolderId();
                int messageTypeId = reminderPOJO.getMessageTypeId();
                String phoneNumber = reminderPOJO.getPhoneNumber();
                String communicationPath = reminderPOJO.getCommunicationPath();
                long crSeqNo = reminderPOJO.getCrSeqNo();
                logger.info("Phone Number : " + phoneNumber);

                OptInOut optInOut = textFlowDAO.getOptInOut(crSeqNo);
                String optInOutFlag = null;
                if (optInOut != null) {
                    optInOutFlag = optInOut.getOptInOut();
                }

                if (optInOutFlag != null && optInOutFlag.trim().equalsIgnoreCase("O")) {
                    logger.info(phoneNumber + " has opted out from Campaign : " + campaignId);
                    return;
                }

                CampaignMessagesResponse campaignMessagesResponseSent = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
                if (campaignMessagesResponseSent == null) {
                    logger.info("campaignMessageResponseSent is null(Continue)");
                    continue;
                }

                int repeatMessageTypeId = campaignMessagesResponseSent.getRepeatMessageId();
                logger.info("Repeat Message Type Id to be sent : " + repeatMessageTypeId);

                CampaignMessagesResponse campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, repeatMessageTypeId, communicationPath);

                Intervals interval = campaignMessagesResponse.getIntervals();
                IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
                int intervalId = reminderPOJO.getIntervalId();
                String intervalDesc = reminderPOJO.getIntervalValue();
                String eventDesc = textFlowDAO.getEventsDescription(campaignId, folderId, communicationPath);

                int intervalVal = interval.getIntervalValue();
                int intervalUnitInSecond = intervalsType.getUnitInSecond();
                long secondsDelay = intervalVal * intervalUnitInSecond;

                List<CampaignMessages> campaignMessagesList = textFlowDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, repeatMessageTypeId, communicationPath);
                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will return)");
                    continue;
                }

                CampaignMessages campaignMessages = campaignMessagesList.get(0);
                if (campaignMessages == null) {
                    logger.info("CampaignMessages object not found. (System will return)");
                    continue;
                }

                String message = campaignMessages.getSmstext();
                logger.info("Message Text : " + message);

                String campaignName = reminderPOJO.getCampaignName();
                int shortCode = reminderPOJO.getShortCode();
                int messageId = campaignMessages.getMessageId();
                long inputReferenceNumber = reminderPOJO.getInputReferenceNumber();

                GatewayInfo gatewayInfo = textFlowDAO.getGatewayInfo(shortCode);
                CampaignMessageRequest campaignMessageRequest = new CampaignMessageRequest();
                if (message != null && message.trim().length() > 0) {
                    int sentMessageCount = textFlowDAO.sentMessageCountCampaign(phoneNumber, campaignId, folderId, repeatMessageTypeId, "Yes");

                    if (sentMessageCount > 0) {
                        logger.info("Message already sent to Phone Number : " + phoneNumber);
                        continue;
                    }
                    campaignMessageRequest = TextFlowUtil.setCampaignMessageRequest(crSeqNo, campaignId, campaignName, shortCode, messageId,
                            repeatMessageTypeId, folderId, inputReferenceNumber, intervalId, intervalDesc, eventDesc, communicationPath, "Yes");

                }

                CustomerRequest customerRequest = textFlowDAO.getCustomerRequestById(crSeqNo);
                Campaigns campaign = textFlowDAO.getCampaigns(campaignId);

                TextIntervalMessageRequestThread intervalMessageThread = new TextIntervalMessageRequestThread();
                intervalMessageThread.setGatewayInfo(gatewayInfo);
                intervalMessageThread.setCampaignMessages(campaignMessages);
                intervalMessageThread.setAppName("PMS text flow reminder");
                intervalMessageThread.setCustomerRequest(customerRequest);
                intervalMessageThread.setPhoneNumber(phoneNumber);
                intervalMessageThread.setInputReferenceNumber(inputReferenceNumber);
                intervalMessageThread.setSecondsDelay(secondsDelay);
                intervalMessageThread.setCmrSeqNo(campaignMessageRequest.getCmrSeqNo());
                intervalMessageThread.setCampaign(campaign);
                intervalMessageThread.setTextFlowDAO(textFlowDAO);
                intervalMessageThread.setFolderId(folderId);
                intervalMessageThread.setMessageTypeId(repeatMessageTypeId);
                intervalMessageThread.setCommunicationType(communicationPath);
                intervalMessageThread.setIsWebEnabled(false);
                intervalMessageThread.setIntervalId(intervalId);
                intervalMessageThread.setIntervalDesc(intervalDesc);
                intervalMessageThread.setEventDesc(eventDesc);
                intervalMessageThread.setThreadType("text_reminder");
                intervalMessageThread.setCampaignMessageRequest(campaignMessageRequest);
                Thread smsIntervalMessageThread = new Thread(intervalMessageThread);
                smsIntervalMessageThread.start();
            }

        } catch (Exception e) {
            logger.error("Exception:: ", e);
        }
    }
}
