package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.EventDetail;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRedemptionId;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.service.PhoneValidationService;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.service.SurveyService;
import com.ssa.cms.thread.TextIntervalReminderThread;
import com.ssa.cms.util.RedemptionUtil;
import com.ssa.cms.util.RefillUtil;
import com.ssa.cms.util.TextFlowUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author msheraz
 */
@Component
public class CampaignSurveyReminder {

    private static final Logger logger = Logger.getLogger(CampaignSurveyReminder.class);

    @Autowired
    RedemptionService redemptionService;
    @Autowired
    RefillReminderService refillReminderService;
    @Autowired
    SurveyService surveyService;

    public void sendSurveyAlerts() {
        try {
            List<Campaigns> campaignList = surveyService.getAllSurveyCandidateActiveCampaign();

            if (campaignList == null || campaignList.isEmpty()) {
                logger.info("No active campaigns for survey reminder. (System will return)");
                return;
            }

            String PVURL = refillReminderService.getURL(Constants.PVURL);
            PhoneValidationService phoneValidationService = new PhoneValidationService(PVURL);

            for (Campaigns campaign : campaignList) {

                int campaignId = campaign.getCampaignId();
                String campaignName = campaign.getCampaignName();

                int interval = 3; //send survey to 3 days old redemptions
                List<DailyRedemption> list = surveyService.getDRFSurveyReminderRecordsByCampaignId(campaignId, interval);
                if (list == null || list.isEmpty()) {
                    logger.info("No drf records found (System will continue) Campaign ID: " + campaignId);
                    continue;
                }

                int totalEligibleRecords = list.size();
                logger.info("Survey Remidner record count  : " + totalEligibleRecords + " for campaign : " + campaignId);

                CampaignTrigger campaignTrigger = refillReminderService.getTriggerByCampaignId(campaignId);
                String trigger = campaignTrigger.getId().getKeyword();
                int shortCode = campaign.getShortCodes().getShortCode();

                GatewayInfo gatewayInfo = refillReminderService.getGatewayInfo(shortCode);
                String appName = "PMS " + campaignName + " refill reminder";
                String appCode = gatewayInfo.getAppCode();

                List<EventHasFolderHasCampaigns> eventHasFolderHasCampaignses = refillReminderService.getEventHasFolderHasCampaign(campaignId, Constants.REDEMPTION);

                if (eventHasFolderHasCampaignses == null) {
                    logger.info("No folder associated to this campaign (System will continue)");
                    continue;
                }

                int folderId = 0;
                String path = "";
                for (EventHasFolderHasCampaigns ehfhc : eventHasFolderHasCampaignses) {
                    if (path.equals(ehfhc.getCommunicationPath()) && folderId == ehfhc.getFolderId()) {
                        continue;
                    }

                    if (processFolder(folderId, ehfhc, eventHasFolderHasCampaignses, list, refillReminderService,
                            campaignId, phoneValidationService, appCode, trigger, shortCode, gatewayInfo, appName, campaign)) {
                        continue;
                    }
                    path = ehfhc.getCommunicationPath();
                    folderId = ehfhc.getFolderId();
                }
            } // for (Campaigns campaign : campaignList)

        } catch (Exception e) {
            logger.error("Exception:: -> sendSurveyAlerts ", e);
        }

    }

    private boolean processFolder(int folderId, EventHasFolderHasCampaigns ehfhc, List<EventHasFolderHasCampaigns> eventHasFolderHasCampaignses, List<DailyRedemption> list, RefillReminderService refillReminderService1, int campaignId, PhoneValidationService phoneValidationService, String appCode, String trigger, int shortCode, GatewayInfo gatewayInfo, String appName, Campaigns campaign) throws Exception {
        Intervals interval;
        int intervalId;
        if (folderId == ehfhc.getFolderId()) {
            return true;
        }
        folderId = ehfhc.getFolderId();
        List<CampaignMessages> campaignMessagesList = null;
        String eventsIds = "";
        for (EventHasFolderHasCampaigns ehfhc1 : eventHasFolderHasCampaignses) {
            if (ehfhc1.getFolderId() == ehfhc.getFolderId()) {
                eventsIds += ehfhc1.getEventId() + ",";
            }
        }
        eventsIds = eventsIds.substring(0, eventsIds.length() - 1);
        for (DailyRedemption drf : list) {
            String communicationId = drf.getCommunicationId();
            String communicatoinMethod = drf.getCommunicationMethod();

            List<EventDetail> eventDetails = refillReminderService.getEventDetail(eventsIds, "DailyRedemption");
            InstantRedemptionId instantRedemptionId = new InstantRedemptionId();
            instantRedemptionId.setTransactionNumber(drf.getId().getTransactionNumber());
            instantRedemptionId.setClaimStatus(drf.getId().getClaimStatus());
            if (eventDetails != null && eventDetails.size() > 0) {
                boolean send = refillReminderService1.isEventDetailVerifiedForDailyRedemption(eventDetails, drf.getId());
                if (!send) {
                    logger.info("Event Detail Criteria doesnt match for Refill.");
                    continue;
                }
            }
            if (communicatoinMethod == null || communicatoinMethod.trim().length() == 0) {
                logger.info("No communicaiton method defined (System will continue)");
                continue;
            }
            communicatoinMethod = communicatoinMethod.trim();
            if (!(communicatoinMethod.equalsIgnoreCase("T") || communicatoinMethod.equalsIgnoreCase("E") || communicatoinMethod.equalsIgnoreCase("I"))) {
                logger.info("Communication method has not value of E,I or T (System will continue)");
                continue;
            }

            if (communicatoinMethod.equalsIgnoreCase("T")) {
                String communicationPath = Constants.SMS;
                CampaignMessagesResponse campaignMessagesResponse = refillReminderService1.getCampaignMessagesResponseByResComm(campaignId, folderId, Constants.SURVEY, communicationPath);
                if (campaignMessagesResponse == null) {
                    logger.info("Refill campaignMessagesResponse for MMS is null.");
                    campaignMessagesResponse = refillReminderService1.getCampaignMessagesResponse(campaignId, folderId, communicationPath);
                    if (campaignMessagesResponse == null) {
                        logger.info("Refill campaignMessagesResponse is null.");
                        continue;
                    }
                }
                interval = campaignMessagesResponse.getIntervals();
                IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
                intervalId = interval.getIntervalId();
                String intervalDesc = interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                String eventDesc = refillReminderService1.getEventsDescription(campaignId, folderId);
                int intervalVal = interval.getIntervalValue();
                int intervalUnitInSecond = intervalsType.getUnitInSecond();
                long secondsDelay = intervalVal * intervalUnitInSecond;
                int refillMessageTypeId = campaignMessagesResponse.getMessageTypeId();
                campaignMessagesList = refillReminderService1.getCampaignMessagesByCommunicationPath(campaignId, folderId, refillMessageTypeId, communicationPath);
                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will continue)");
                    logger.info("Folder ID: " + ehfhc.getFolderId());
                    continue;
                }
                CampaignMessages campaignMessages = campaignMessagesList.get(0);
                String refillMessage = campaignMessages.getSmstext();
                int messageTypeId = campaignMessages.getMessageType().getId().getMessageTypeId();
                int messageId = campaignMessages.getMessageId();
                String patientMessage = refillMessage;
                if (patientMessage == null || patientMessage.length() == 0) {
                    logger.info("No Message found System will continue");
                    logger.info("Message Type ID: " + messageTypeId);
                    continue;
                }
                boolean phoneValidity = phoneValidationService.checkPhoneValidity(communicationId, appCode, trigger);
                if (!phoneValidity) {
                    logger.info("Phone number : " + communicationId + " not a valid cell phone. (Continue) ");
                    continue;
                }
                OptInOut optInOut = refillReminderService1.checkOptInOut(communicationId, campaignId, shortCode);
                String optInOutFlag = null;
                if (optInOut != null) {
                    optInOutFlag = optInOut.getOptInOut();
                }
                if (optInOutFlag != null && optInOutFlag.trim().equalsIgnoreCase("O")) {
                    logger.info("PhoneNumber : " + communicationId + " has opted opted out from campaign : " + campaignId);
                    continue;
                }
                logger.info("Transaction Number is: " + drf.getId().getTransactionNumber());
                Order order = refillReminderService.getOrderByTransactionNumber(drf.getId().getTransactionNumber());
                Double payment = null;
                if (order != null) {
                    payment = order.getPayment();
                }
                logger.info("Prescription Number is: " + drf.getPrescriptionNumber());
                String surveyUrl = RedemptionUtil.prepareSurveyURL(campaign.getSurvey().getId(), communicationId);
                patientMessage = TextFlowUtil.prepareMessage(null, null, drf.getPharmacyName(), drf.getPharmacyPhone(),
                        patientMessage, drf.getPrescriptionNumber(), null, null, payment, surveyUrl, null, null);
                logger.info("Finally Message : " + patientMessage);
                logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");
                InstantRedemptionId irfId = new InstantRedemptionId();
                InstantRedemption irf = new InstantRedemption();
                irfId.setClaimStatus(drf.getId().getClaimStatus());
                irf.setId(irfId);
                BeanUtils.copyProperties(drf.getId(), irfId);
                BeanUtils.copyProperties(drf, irf, new String[]{"id"});
                if (patientMessage != null && patientMessage.trim().length() > 0) {
                    campaignMessages.setSmstext(patientMessage);
                    AggregatorMessageRequest aggregatorMessageRequest = setAggregatorMessageRequest(drf, communicationId, ehfhc, campaignId, messageTypeId,
                            refillMessage, intervalDesc, eventDesc, intervalId, communicationPath);
                    int sentMessageCount = refillReminderService1.retrieveMessageCountByTypeForRedemption(aggregatorMessageRequest);
                    logger.info("Sent Message Count : " + sentMessageCount);
                    if (sentMessageCount > 0) {
                        logger.info("Refill Message already sent (for fill date " + RefillUtil.formatDateShort(drf.getFillDate()));
                        continue;
                    }
                    logger.info("Message Id : " + messageId);
                    logger.info("Message Type Id : " + messageTypeId);
                    logger.info("Welcome Message : " + refillMessage);
                    startTextIntervalReminderThread(gatewayInfo, aggregatorMessageRequest, campaignMessages, appName, trigger, communicationId, secondsDelay, campaign, refillReminderService1, ehfhc, messageTypeId, communicationPath, intervalId, intervalDesc, eventDesc, irf, drf);
                }
            }
        } //
        //
        //just process first event only
        //just process first event only
        return false;
    }

    private void startTextIntervalReminderThread(GatewayInfo gatewayInfo, AggregatorMessageRequest aggregatorMessageRequest, CampaignMessages campaignMessages, String appName, String trigger, String communicationId, long secondsDelay, Campaigns campaign, RefillReminderService refillReminderService1, EventHasFolderHasCampaigns ehfhc, int messageTypeId, String communicationPath, int intervalId, String intervalDesc, String eventDesc, InstantRedemption irf, DailyRedemption drf) {
        TextIntervalReminderThread intervalMessageThread = new TextIntervalReminderThread();
        intervalMessageThread.setGatewayInfo(gatewayInfo);
        intervalMessageThread.setAggregatorMessageRequest(aggregatorMessageRequest);
        intervalMessageThread.setCampaignMessages(campaignMessages);
        intervalMessageThread.setAppName(appName);
        intervalMessageThread.setProgramCode(trigger);
        intervalMessageThread.setPhoneNumber(communicationId);
        intervalMessageThread.setInputReferenceNumber(0);
        intervalMessageThread.setSecondsDelay(secondsDelay);
        intervalMessageThread.setCampaign(campaign);
        intervalMessageThread.setRefillReminderDAO(refillReminderService1);
        intervalMessageThread.setFolderId(ehfhc.getFolderId());
        intervalMessageThread.setMessageTypeId(messageTypeId);
        intervalMessageThread.setCommunicationType(communicationPath);
        intervalMessageThread.setIntervalId(intervalId);
        intervalMessageThread.setIntervalDesc(intervalDesc);
        intervalMessageThread.setEventDesc(eventDesc);
        intervalMessageThread.setInstantRedemption(irf);
        intervalMessageThread.setThreadType(Constants.REDEMPTION);
        intervalMessageThread.setDailyRedemption(drf);
        Thread smsIntervalMessageThread = new Thread(intervalMessageThread);
        smsIntervalMessageThread.start();
    }

    private AggregatorMessageRequest setAggregatorMessageRequest(DailyRedemption drf, String communicationId, EventHasFolderHasCampaigns ehfhc, int campaignId, int messageTypeId, String refillMessage, String intervalDesc, String eventDesc, int intervalId, String communicationPath) {
        AggregatorMessageRequest aggregatorMessageRequest = new AggregatorMessageRequest();
        aggregatorMessageRequest.setRxGroupNumber(drf.getRxGroupNumber());
        aggregatorMessageRequest.setPhoneNumber(communicationId);
        aggregatorMessageRequest.setCardholderDob(drf.getCardholderDob());
        aggregatorMessageRequest.setFillDate(drf.getFillDate());
        aggregatorMessageRequest.setNdcNumber(drf.getNdcNumber());
        aggregatorMessageRequest.setClaimStatus(drf.getId().getClaimStatus());
        aggregatorMessageRequest.setFolderId((ehfhc.getFolderId()));
        aggregatorMessageRequest.setCampaignId(campaignId);
        aggregatorMessageRequest.setRedemptionId(drf.getRedemptionId());
        aggregatorMessageRequest.setMessageTypeId((messageTypeId));
        aggregatorMessageRequest.setFileTypeCode(drf.getFileTypeCode());
        aggregatorMessageRequest.setMessageText(refillMessage);
        aggregatorMessageRequest.setMessageContext(Constants.REDEMPTION);
        aggregatorMessageRequest.setFileName(drf.getFileName());
        aggregatorMessageRequest.setIntervalDescription(intervalDesc);
        aggregatorMessageRequest.setEventDetail(eventDesc);
        aggregatorMessageRequest.setIntervalId(intervalId);
        aggregatorMessageRequest.setCommunicationPath(communicationPath);
        return aggregatorMessageRequest;
    }
}
