package com.ssa.cms.remainder.refill;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.AggregatorMessageResponse;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.RefillOrders;
import com.ssa.cms.model.RefillReminderPOJO;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;

public class PMSAutoRefillFailure {

    public void sendRefillFailure(RefillReminderService refillReminderDAO) {

        int shortCode = 0;
        String communicationId = "";
        String communicationPath = "";
        String communicationMethod = "";
        int folderId = 0;
        int messageTypeId = 0;
        String isValid = "";

        ShortCodes shortCodes = null;
        GatewayInfo gatewayInfo = null;
        CampaignMessagesResponse campaignMessagesResponse = null;
        List<CampaignMessages> campaignMessages = null;
        CampaignMessages campaignMessage = null;
        CampaignTrigger campaignTrigger = null;
        MTDecorator decorator = null;
        AggregatorMessageRequest aggregatorMessageRequest = null;
        AggregatorMessageResponse aggregatorMessageResponse = null;

        try {
            List<Campaigns> campaignses = refillReminderDAO.getAllRefillFailureCandidateActiveCampaign();

            if (campaignses == null || campaignses.isEmpty()) {
                System.out.println("No Refil Failure Candidate Canpaigns found.");
                return;
            }

            for (Campaigns campaigns : campaignses) {
                int campainId = campaigns.getCampaignId();
                shortCodes = campaigns.getShortCodes();

                if (shortCodes == null) {
                    System.out.println("Short code not found.");
                    continue;
                }

                shortCode = shortCodes.getShortCode();
                gatewayInfo = refillReminderDAO.getGatewayInfo(shortCode);

                if (gatewayInfo == null) {
                    System.out.println("No gateway info record found.");
                    continue;
                }

                List<RefillReminderPOJO> reminderPojoList = refillReminderDAO.getTextRefill2Records(campainId);

                if (reminderPojoList == null || reminderPojoList.isEmpty()) {
                    System.out.println("No Records for processing.");
                    continue;
                }

                for (RefillReminderPOJO pojo : reminderPojoList) {

                    communicationId = pojo.getPhoneNumber();
                    communicationMethod = pojo.getCommunicationMethod();
                    folderId = pojo.getFolderId();
                    isValid = pojo.getIsValidPhone();

                    if (communicationMethod.equalsIgnoreCase("") || communicationMethod.length() == 0) {
                        System.out.println("communicationMethod is missing");
                        continue;
                    }

                    MessagePriority messagePriority = refillReminderDAO.getMessagePriority(communicationId, shortCode);

                    if (messagePriority == null) {
                        System.out.println("MessagePriority is null.");
                        continue;
                    }

                    if (isValid == null || isValid.equalsIgnoreCase("No")) {
                        System.out.println("Phone is not Valid.");
                        continue;
                    }

                    OptInOut optInOut = refillReminderDAO.getTextOptInOut(campainId, communicationId);
                    String optInOutFlag = null;

                    if (optInOut != null) {
                        optInOutFlag = optInOut.getOptInOut();
                    }

                    if (optInOutFlag != null && optInOutFlag.trim().equalsIgnoreCase("O")) {
                        System.out.println(communicationId + " has opted out from Campaign : " + campainId);
                        System.out.println("System will return");
                        continue;
                    }

                    String msgContext = messagePriority.getMessageContext();

                    if (msgContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REFILL_SUCCESS) || msgContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REFILL_DENIED)) {
                        System.out.println("Response already send.");
                        continue;
                    }

                    AggregatorMessageRequest messageRequest = refillReminderDAO.getRefillReminderAggregatorMessageRequestByRedemptionId(pojo.getRedemptionId());

                    if (messageRequest == null) {
                        System.out.println("Refill Reminder or Repeat Refill Reminder not Found.");
                        continue;
                    }

                    communicationPath = Constants.SMS;

                    campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResComm(campainId, folderId, Constants.DENIED, communicationPath);

                    if (campaignMessagesResponse == null) {
                        System.out.println("CampaignMessagesResponse is null");
                        continue;
                    }

                    messageTypeId = campaignMessagesResponse.getMessageTypeId();

                    Intervals intervals = campaignMessagesResponse.getIntervals();
                    IntervalsType intervalsType = intervals.getIntervalsType();

                    int intervalId = intervals.getIntervalId();
                    String intervalDesc = intervals.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                    String eventDesc = refillReminderDAO.getEventsDescription(campainId, folderId);

                    campaignMessages = refillReminderDAO.getCampaignMessagesByCommunicationPath(campainId, folderId, messageTypeId, communicationPath);

                    if (campaignMessages == null || campaignMessages.isEmpty()) {
                        System.out.println("campaignMessages is empty.");
                        continue;
                    }

                    campaignMessage = campaignMessages.get(0);

                    String messageText = campaignMessage.getSmstext();

                    if (messageText != null && messageText.length() > 0) {

                        messageText = messageText.replace("###-###-####", pojo.getPharmacyPhone());

                        campaignMessage.setSmstext(messageText);

                        decorator = SMSUtil.sendSmsMessage(communicationId, campaignMessage.getSmstext());

                        aggregatorMessageRequest = new AggregatorMessageRequest();
                        aggregatorMessageRequest.setCampaignId(campainId);
                        aggregatorMessageRequest.setCardholderDob(pojo.getCardholderDob());
                        aggregatorMessageRequest.setClaimStatus(pojo.getClaimStatus());
                        aggregatorMessageRequest.setCommunicationPath(communicationPath);
                        aggregatorMessageRequest.setEffectiveDate(new Date());
                        aggregatorMessageRequest.setEventDetail(eventDesc);
                        aggregatorMessageRequest.setFileName(pojo.getFileName());
                        aggregatorMessageRequest.setFileTypeCode(pojo.getFileTypeCode());
                        aggregatorMessageRequest.setFillDate(pojo.getFillDate());
                        aggregatorMessageRequest.setFolderId(folderId);
                        aggregatorMessageRequest.setInputReferenceNumber(0);
                        aggregatorMessageRequest.setIntervalDescription(intervalDesc);
                        aggregatorMessageRequest.setIntervalId(intervalId);
                        aggregatorMessageRequest.setMessageContext(Constants.MSG_CONTEXT_REFILL_DENIED);
                        aggregatorMessageRequest.setMessageTypeId(messageTypeId);
                        aggregatorMessageRequest.setMessageText(messageText);
                        aggregatorMessageRequest.setNdcNumber(pojo.getNdcNumber());
                        aggregatorMessageRequest.setPhoneNumber(communicationId);
                        aggregatorMessageRequest.setRedemptionId(pojo.getRedemptionId());
                        aggregatorMessageRequest.setRxGroupNumber(pojo.getRxGroupNumber());
                        aggregatorMessageRequest.setMessageRequest(decorator.getRequestXML());

                        refillReminderDAO.save(aggregatorMessageRequest);

                        aggregatorMessageResponse = new AggregatorMessageResponse();
                        aggregatorMessageResponse.setEffectiveDate(new Date());
                        aggregatorMessageResponse.setErrorCode(decorator.getErrorCode());
                        aggregatorMessageResponse.setErrorDescription(decorator.getErrorDescription());
                        aggregatorMessageResponse.setMessageReqNo(aggregatorMessageRequest.getMessageReqNo());
                        aggregatorMessageResponse.setMessageResponse(decorator.getResponseXML());
                        aggregatorMessageResponse.setMtsId(decorator.getMtsId());
                        aggregatorMessageResponse.setTicketId(decorator.getTicketId());

                        refillReminderDAO.save(aggregatorMessageResponse);

                        refillReminderDAO.markAggregatorMessageRequestEndDate(pojo.getMessageReqNo());

                        refillReminderDAO.markRARYESReceivedEndDate(pojo.getRedemptionId());

                        RefillOrders refillOrders = new RefillOrders();
                        refillOrders.setActionBySystem("Sutomated Process");
                        refillOrders.setActionByUserID("0");
                        refillOrders.setCampaignId(campainId);
                        refillOrders.setComments("Denied marked by automated failure process");
                        refillOrders.setEffectiveDate(new Date());
                        refillOrders.setFolderId(folderId);
                        refillOrders.setMessageReqNo(messageRequest.getMessageReqNo());
                        refillOrders.setMessageReqNoReminder(aggregatorMessageRequest.getMessageReqNo());
                        refillOrders.setMessageTypeId(messageTypeId);
                        refillOrders.setRefillFrom(communicationMethod);
                        refillOrders.setRefillStatus("Refill Denied");
                        refillOrders.setSubmittedID(communicationId);

                        refillReminderDAO.save(refillOrders);

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
