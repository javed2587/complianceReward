package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.ReminderPOJO;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.thread.TextIntervalMessageRequestThread;
import com.ssa.cms.util.SMSUtil;
import java.util.List;

public class PMSGenericCouponReminder {

    public void processCouponReminder(PMSTextFlowService textFlowDAO) {

        try {

            List<ReminderPOJO> list = textFlowDAO.getCouponRemindeRecords();

            if (list == null || list.isEmpty()) {
                System.out.println("No campaign reminder record found. (System will return)");
                return;
            }

            System.out.println("List Size : " + list.size());

            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            int shortCode = 0;
            String campaignName = "";
            long crSeqNo = 0;
            int messageId = 0;
            long inputReferenceNumber = 0;
            String phoneNumber = "";
            int count = 0;

            CampaignMessagesResponse campaignMessagesResponseSent = null;

            CampaignMessagesResponse campaignMessagesResponse = null;
            CampaignMessageRequest campaignMessageRequest = null;

            SMSUtil smsUtil = new SMSUtil();

            CustomerRequest customerRequest = null;
            ShortCodes shortCodePojo = null;
            Campaigns campaign = null;
            String keywordCode = "";
            long seconds = 0;
            String cardNumber = "";
            String communicationPath = "";

            int irfCount = 0;
            long irfLastRedemptionSeconds = 0;

            for (ReminderPOJO reminderPOJO : list) {

                campaignId = reminderPOJO.getCampaignId();
                folderId = reminderPOJO.getFolderId();
                messageTypeId = reminderPOJO.getMessageTypeId();
                phoneNumber = reminderPOJO.getPhoneNumber();
                keywordCode = reminderPOJO.getKeywordCode();
                seconds = reminderPOJO.getSeconds();
                cardNumber = reminderPOJO.getCardNumber();
                communicationPath = reminderPOJO.getCommunicationPath();
                count = 0;

                System.out.println("Phone Number : " + phoneNumber);

                irfCount = textFlowDAO.getIRFRedemptionCountByPhone(phoneNumber, campaignId);

                System.out.println("IRF Redemption Count : " + irfCount);

                if (irfCount > 0) {
                    irfLastRedemptionSeconds = textFlowDAO.getIRFLastRedemptionSecondsByPhone(phoneNumber, campaignId);

                    System.out.println("IRF last redemption seconds passed : " + irfLastRedemptionSeconds);

                    if (irfLastRedemptionSeconds > 0 && irfLastRedemptionSeconds <= (seconds + (24 * 3600))) {
                        System.out.println("IRF Redemption already there in last " + irfLastRedemptionSeconds + " System will continue");
                        continue;
                    }
                }

                campaignMessagesResponseSent = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);

                if (campaignMessagesResponseSent == null) {
                    System.out.println("campaignMessagesResponseSent is null.");
                    continue;
                }

                int repeatMessageTypeId = campaignMessagesResponseSent.getRepeatMessageId();

                System.out.println("Repeat Message Type Id to be sent : " + repeatMessageTypeId);

                campaignMessagesResponse = textFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, repeatMessageTypeId, communicationPath);

                if (campaignMessagesResponse == null) {
                    System.out.println("campaignMessagesResponse is null.");
                    continue;
                }

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
                    System.out.println("No messages found for (System will continue)");
                    continue;
                }

                CampaignMessages campaignMessages = campaignMessagesList.get(0);

                if (campaignMessages == null) {
                    System.out.println("CampaignMessages object not found. (System will continue)");
                    continue;
                }

                String message = campaignMessages.getSmstext();

                System.out.println("Message Text : " + message);

                if (cardNumber != null) {
                    message = message.replaceAll(Constants.CARD_NUMBER_PLACE_HOLDER, cardNumber);
                    campaignMessages.setSmstext(message);
                }

                crSeqNo = reminderPOJO.getCrSeqNo();
                campaignName = reminderPOJO.getCampaignName();
                shortCode = reminderPOJO.getShortCode();
                messageId = campaignMessages.getMessageId();
                inputReferenceNumber = reminderPOJO.getInputReferenceNumber();

                System.out.println("Going to sleep : " + secondsDelay + " Seconds");

                GatewayInfo gatewayInfo = textFlowDAO.getGatewayInfo(shortCode);

                if (messageTypeId == repeatMessageTypeId) {
                    count = 1;
                }

                if (message != null && message.trim().length() > 0) {

                    int sentMessageCount = textFlowDAO.sentMessageCountCampaign(phoneNumber, campaignId, folderId, repeatMessageTypeId, "Yes");

                    if (sentMessageCount > 0) {
                        System.out.println("Message already sent to Phone Number : " + phoneNumber);
                        continue;
                    }

                    campaignMessageRequest = new CampaignMessageRequest();

                    campaignMessageRequest.setCrSeqNo(crSeqNo);
                    campaignMessageRequest.setCampaignId(campaignId);
                    campaignMessageRequest.setCampaignName(campaignName);
                    campaignMessageRequest.setShortCode(shortCode);
                    campaignMessageRequest.setMessageId(messageId);
                    campaignMessageRequest.setMessageTypeId(repeatMessageTypeId);
                    campaignMessageRequest.setFolderId(folderId);
                    campaignMessageRequest.setInputReferenceNumber(inputReferenceNumber);
                    campaignMessageRequest.setIntervalDescription(intervalDesc);
                    campaignMessageRequest.setIntervalId(intervalId);
                    campaignMessageRequest.setEventDetail(eventDesc);
                    campaignMessageRequest.setCommunicationPath(communicationPath);
                    campaignMessageRequest.setIsRepeat("Yes");

                }

                customerRequest = textFlowDAO.getCustomerRequestById(crSeqNo);
                campaign = textFlowDAO.getCampaigns(campaignId);
                shortCodePojo = textFlowDAO.getShortCodeByCode(shortCode);
                boolean isWebEnabled = false;
                TextIntervalMessageRequestThread intervalMessageThread = new TextIntervalMessageRequestThread();
                intervalMessageThread.setGatewayInfo(gatewayInfo);
                intervalMessageThread.setCampaignMessages(campaignMessages);
                intervalMessageThread.setAppName("PMS coupon reminder");
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
                intervalMessageThread.setIsWebEnabled(isWebEnabled);
                intervalMessageThread.setIntervalId(intervalId);
                intervalMessageThread.setIntervalDesc(intervalDesc);
                intervalMessageThread.setEventDesc(eventDesc);
                intervalMessageThread.setThreadType("text_reminder");
                intervalMessageThread.setCampaignMessageRequest(campaignMessageRequest);
                //intervalMessageThread.setGroupNumber(groupNumber);
                Thread smsIntervalMessageThread = new Thread(intervalMessageThread);
                smsIntervalMessageThread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
