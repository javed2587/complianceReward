package com.ssa.cms.util;

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
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.thread.RefillReminderMessageThread;
import com.ssa.decorator.MTDecorator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class RefillUtil {

    private static final Logger logger = Logger.getLogger(RefillUtil.class);

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.EFFECTIVE_DATE_FORMAT);
        return simpleDateFomrat.format(date);
    }

    public static String formatDateShort(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
        return simpleDateFomrat.format(date);
    }

    public static Date parseDate(String dateStr) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMATE);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            logger.error(e);
        }

        return date;
    }

    public static Date parseDateShort(String dateStr) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            logger.error(e);
        }

        return date;
    }

    /*
     ***********************************************************************************************
     ***********************************************************************************************
     ***********************************************************************************************
     */
    public static void sendNextMessage(RefillReminderService refillReminderDAO,
            AggregatorMessageRequest aggregatorMessageRequest, Campaigns campaigns, InstantRedemption irf) throws Exception {
        String phoneNumber = aggregatorMessageRequest.getPhoneNumber();
        CampaignMessagesResponse campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponse(aggregatorMessageRequest.getCampaignId(), aggregatorMessageRequest.getFolderId(), aggregatorMessageRequest.getMessageTypeId());

        if (campaignMessageResponses == null) {
            logger.info("CampaignMessagesResponse is null in thread");
            return;
        }
        Integer nextMessageTypeId = campaignMessageResponses.getNextMessage();

        logger.info("Next Message Type Id : " + nextMessageTypeId);

        if (nextMessageTypeId == null || nextMessageTypeId == 0) {
            logger.info("Next message type id is 0");
            return;
        }

        campaignMessageResponses = refillReminderDAO.getCampaignMessagesResponse(aggregatorMessageRequest.getCampaignId(), aggregatorMessageRequest.getFolderId(), nextMessageTypeId);

        if (campaignMessageResponses == null) {
            logger.info("CampaignMessagesResponse is null in thread for next message");
            return;
        }

        List<CampaignMessages> campaignMessagesList = refillReminderDAO.getCampaignMessages(aggregatorMessageRequest.getCampaignId(), aggregatorMessageRequest.getFolderId(), nextMessageTypeId);

        if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
            logger.info("No messages found for (System will return)");
            return;
        }

        CampaignMessages campaignMessages = campaignMessagesList.get(0);

        if (campaignMessages == null) {
            logger.info("CampaignMessages object not found. (System will return)");
            return;
        }

        Intervals interval = campaignMessageResponses.getIntervals();
        IntervalsType intervalType = interval.getIntervalsType();

        int intervalValue = interval.getIntervalValue();
        int intervalUnitInSecond = intervalType.getUnitInSecond();
        long secondsDelay = intervalValue * intervalUnitInSecond;

        logger.info("Interval Value : " + intervalValue);
        logger.info("Intervl Unit In Seconds : " + intervalUnitInSecond);
        logger.info("Final interval magnitude in seconds : " + secondsDelay);

        GatewayInfo gatewayInfo = refillReminderDAO.getGatewayInfo(campaigns.getShortCodes().getShortCode());
        MTDecorator decorator = SMSUtil.sendSmsMessage(phoneNumber, campaignMessages.getSmstext());
        AggregatorMessageRequest messageRequest;
        AggregatorMessageResponse messageResponse;
        messageRequest = new AggregatorMessageRequest();
        messageRequest.setCardholderDob(irf.getCardholderDob());
        messageRequest.setClaimStatus(irf.getId().getClaimStatus());
        messageRequest.setFileTypeCode(aggregatorMessageRequest.getFileTypeCode());
        messageRequest.setFolderId(aggregatorMessageRequest.getFolderId());
        messageRequest.setMessageRequest(decorator.getRequestXML());
        messageRequest.setMessageText(decorator.getMessageText());
        messageRequest.setMessageTypeId((nextMessageTypeId));
        messageRequest.setCampaignId(campaigns.getCampaignId());
        messageRequest.setNdcNumber(irf.getNdcNumber());
        messageRequest.setPhoneNumber(phoneNumber);
        messageRequest.setRedemptionId(irf.getRedemptionId());
        messageRequest.setRxGroupNumber(irf.getRxGroupNumber());
        messageRequest.setEffectiveDate(new Date());
        messageRequest.setFillDate(irf.getFillDate());
        messageRequest.setMessageContext(aggregatorMessageRequest.getMessageContext());
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

        RefillReminderMessageThread messageThread = new RefillReminderMessageThread();
        messageThread.setFolderId(aggregatorMessageRequest.getFolderId());
        messageThread.setMessageTypeId(nextMessageTypeId);
        messageThread.setRefillReminderDAO(refillReminderDAO);
        messageThread.setCampaign(campaigns);
        messageThread.setInstantRedemption(irf);
        messageThread.setMessageContext(aggregatorMessageRequest.getMessageContext());
        messageThread.setGatewayInfo(gatewayInfo);
        Thread thread = new Thread(messageThread);
        thread.start();
    }

}
