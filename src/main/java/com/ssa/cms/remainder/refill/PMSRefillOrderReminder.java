package com.ssa.cms.remainder.refill;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.AggregatorMessageResponse;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class PMSRefillOrderReminder {

    private static final Logger logger = Logger.getLogger(PMSRefillOrderReminder.class);

    public void sendRefillOrderReminder(RefillReminderService refillReminderDAO) {

        List<InstantRedemption> list = null;

        int campainId = 0;
        int redemptionId = 0;
        int shortCode = 0;
        String communicationId = "";
        String communicationPath = "";
        String communicationMethod = "";
        int folderId = 0;
        int messageTypeId = 0;
        boolean isWebEnabled = false;
        String isValid = "";

        AggregatorMessageRequest aggregatorMessageRequestForRed = null;
        AggregatorMessageRequest aggregatorMessageRequestForRefillSuccess = null;
        OptInOut optInOut = null;
        ShortCodes shortCodes = null;
        GatewayInfo gatewayInfo = null;
        CampaignMessagesResponse campaignMessagesResponse = null;
        List<CampaignMessages> campaignMessages = null;
        CampaignMessages campaignMessage = null;
        MTDecorator decorator = null;
        AggregatorMessageRequest aggregatorMessageRequest = null;
        AggregatorMessageResponse aggregatorMessageResponse = null;

        try {
            List<Campaigns> campaignses = refillReminderDAO.getAllRefillFailureCandidateActiveCampaign();

            if (campaignses == null || campaignses.isEmpty()) {
                logger.info("No Refil Failure Candidate Canpaigns found.");
                return;
            }

            for (Campaigns campaigns : campaignses) {
                campainId = campaigns.getCampaignId();
                shortCodes = campaigns.getShortCodes();

                if (shortCodes == null) {
                    logger.info("Short code not found.");
                    continue;
                }

                shortCode = shortCodes.getShortCode();
                gatewayInfo = refillReminderDAO.getGatewayInfo(shortCode);

                if (gatewayInfo == null) {
                    logger.info("No gateway info record found.");
                    continue;
                }

                list = refillReminderDAO.getDRFInLast30Mints(campainId);

                if (list == null) {
                    logger.info("No DRF record found for processing");
                    continue;
                }

                for (InstantRedemption instantRedemption : list) {
                    communicationId = instantRedemption.getCommunicationId();
                    communicationMethod = instantRedemption.getCommunicationMethod();

                    if (communicationId == null || communicationId.trim().length() == 0) {
                        logger.info("Communication Id not found.");
                        continue;
                    }

                    if (communicationMethod == null || communicationMethod.trim().length() == 0) {
                        logger.info("communication method not found.");
                        continue;
                    }

                    if (communicationMethod.equalsIgnoreCase("T")) {

                        isValid = instantRedemption.getIsValidPhone();

                        if (!isValid.equalsIgnoreCase("Yes")) {
                            logger.info("Commnunication ID is invalid.");
                            continue;
                        }

                        optInOut = refillReminderDAO.getTextOptInOut(campainId, communicationId);

                        String optInOutFlag = null;

                        if (optInOut != null) {
                            optInOutFlag = optInOut.getOptInOut();
                        }

                        if (optInOutFlag != null && optInOutFlag.trim().equalsIgnoreCase("O")) {
                            logger.info(communicationId + " has opted out from Campaign : " + campainId);
                            logger.info("System will return");
                            continue;
                        }

                        aggregatorMessageRequestForRed = refillReminderDAO.getLastestAggregatorMessageRequest(instantRedemption.getRedemptionId());

                        if (aggregatorMessageRequestForRed == null) {
                            logger.info("Thank you message not found.");
                            continue;
                        }

                        redemptionId = refillReminderDAO.getPreviousRedemptionId(instantRedemption);

                        if (redemptionId == 0) {
                            logger.info("Previous Redemption Not found.");
                            continue;
                        }

                        aggregatorMessageRequestForRefillSuccess = refillReminderDAO.getRefillSuccessfullMessage(redemptionId);

                        if (aggregatorMessageRequestForRefillSuccess == null) {
                            logger.info("Refill Success Message not found.");
                            continue;
                        }

                        folderId = aggregatorMessageRequestForRefillSuccess.getFolderId();

                        communicationPath = Constants.SMS;

                        campaignMessagesResponse = refillReminderDAO.getCampaignMessagesResponseByResComm(campainId, folderId, Constants.REFILL_ORDER_REMINDER, communicationPath);

                        if (campaignMessagesResponse == null) {
                            logger.info("CampaignMessagesResponse is null");
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
                            logger.info("campaignMessages is empty.");
                            continue;
                        }

                        campaignMessage = campaignMessages.get(0);

                        String messageText = campaignMessage.getSmstext();

                        if (messageText != null && messageText.length() > 0) {
                            decorator = SMSUtil.sendSmsMessage(communicationId, campaignMessage.getSmstext());
                            aggregatorMessageRequest = new AggregatorMessageRequest();
                            aggregatorMessageRequest.setCampaignId(campainId);
                            aggregatorMessageRequest.setCardholderDob(instantRedemption.getCardholderDob());
                            aggregatorMessageRequest.setClaimStatus(instantRedemption.getId().getClaimStatus());
                            aggregatorMessageRequest.setCommunicationPath(communicationPath);
                            aggregatorMessageRequest.setEffectiveDate(new Date());
                            aggregatorMessageRequest.setEventDetail(eventDesc);
                            aggregatorMessageRequest.setFileName(instantRedemption.getFileName());
                            aggregatorMessageRequest.setFileTypeCode(instantRedemption.getFileTypeCode());
                            aggregatorMessageRequest.setFillDate(instantRedemption.getFillDate());
                            aggregatorMessageRequest.setFolderId(folderId);
                            aggregatorMessageRequest.setInputReferenceNumber(0);
                            aggregatorMessageRequest.setIntervalDescription(intervalDesc);
                            aggregatorMessageRequest.setIntervalId(intervalId);
                            aggregatorMessageRequest.setMessageContext(Constants.MSG_CONTEXT_REFILL_ORDER_REMINDER);
                            aggregatorMessageRequest.setMessageTypeId(messageTypeId);
                            aggregatorMessageRequest.setMessageText(messageText);
                            aggregatorMessageRequest.setNdcNumber(instantRedemption.getNdcNumber());
                            aggregatorMessageRequest.setPhoneNumber(communicationId);
                            aggregatorMessageRequest.setRedemptionId(instantRedemption.getRedemptionId());
                            aggregatorMessageRequest.setRxGroupNumber(instantRedemption.getRxGroupNumber());
                            aggregatorMessageRequest.setMessageRequest(decorator.getRequestXML());

                            int sentCount = refillReminderDAO.retrieveMessageCountByTypeForRedemption(aggregatorMessageRequest);
                            if (sentCount > 0) {
                                logger.info("Order Reminder already sent for redemtption : " + instantRedemption.getRedemptionId());
                                continue;
                            }

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
                        }

                    } else if (communicationMethod.equalsIgnoreCase("E")) {
                    }

                }

            }

        } catch (Exception e) {
            logger.error("Exception:: ", e);
        }

    }
}
