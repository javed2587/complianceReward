package com.ssa.cms.util;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;
import com.ssa.cms.common.Constants;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CouponOffered;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRequest;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.thread.TextIntervalReminderThread;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TextFlowUtil {

    private static final Log logger = LogFactory.getLog(TextFlowUtil.class);

    public static boolean isHierarchyEnd(CampaignMessagesResponse campaignMessageResponses) {
        boolean flag = false;

        try {
            if (campaignMessageResponses == null) {
                flag = true;
            } else {

                Integer nmtId = campaignMessageResponses.getNextMessage();
                if (nmtId == null || nmtId == 0) {
                    flag = true;
                }
            }

        } catch (Exception e) {
            logger.error("Exception: TextFlowUtil -> isHierarchyEnd", e);

        }

        return flag;
    }

    public static boolean hasPairedMessage(CampaignMessagesResponse campaignMessageResponses) {
        boolean flag = false;

        try {
            if (campaignMessageResponses == null) {
                flag = false;
            } else {

                String paired = campaignMessageResponses.getPaired();

                if (paired == null) {
                    flag = false;
                } else {
                    paired = paired.trim();

                    if (paired.equalsIgnoreCase("YES")) {
                        logger.info("Paired flag value : " + paired + " (System will return)");
                        flag = true;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception: TextFlowUtil -> hasPairedMessage", e);
        }

        return flag;
    }

    public static void markCampaignEnd(CustomerRequest customerRequest, Campaigns campaign, String group, PMSTextFlowService textFlowDAO) {

        try {

            customerRequest.setStatusCode(StatusEnum.COMPLETED.getValue());
            customerRequest.setLastUpdatedOn(new Date());

            textFlowDAO.update(customerRequest);

            OptInOut optInOut = textFlowDAO.getOptInOut(customerRequest.getCrSeqNo());
            if (optInOut != null) {
                optInOut.setStatusCode(StatusEnum.COMPLETED.getValue());
                textFlowDAO.update(optInOut);
            }

            //TODO
//            CouponOffered couponOffered = new CouponOffered();
//            couponOffered.setPhoneNumber(customerRequest.getPhoneNumber());
//            couponOffered.setCrSeqNo(customerRequest.getCrSeqNo());
//            couponOffered.setCampaignId(campaign.getCampaignId());
//            couponOffered.setCampaignName(campaign.getCampaignName());
//            couponOffered.setEffectiveDate(new Date());
//            couponOffered.setKeywordCode(customerRequest.getKeywordCode());
//            couponOffered.setCardNumber(customerRequest.getCardNumber());
//            textFlowDAO.save(couponOffered);
//
//            InstantRequest instantRequest = new InstantRequest();
//            instantRequest.setText(customerRequest.getPhoneNumber());
//            instantRequest.setFlagMessage("T");
//            if ("I".equalsIgnoreCase(group)) {
//                instantRequest.setFlagMessage("I"); //IVR
//            }
//            instantRequest.setMemberId(customerRequest.getCardNumber());
//            instantRequest.setCampaigns(campaign);
//            instantRequest.setIsValidPhone("YES");
//            instantRequest.setSessionId(1l);
//            instantRequest.setEffectiveDate(new Date());
//            instantRequest.setTimeStamp(new Date());
//            instantRequest.setGroupNumber1(group);
//            instantRequest.setInsertedBy("PMS " + campaign.getCampaignName() + " Text Flow ");
//
//            textFlowDAO.save(instantRequest);

            logger.info("Update campaign status flag : markCampaignEnd");

        } catch (Exception e) {
            logger.error("Exception: TextFlowUtil -> hasPairedMessage", e);
        }
    }

    public static void startMessageThread(GatewayInfo gatewayInfo, AggregatorMessageRequest messageRequest, CampaignMessages campaignMessages,
            String programCode, long secondsDelay, Campaigns campaign, EventHasFolderHasCampaigns ehfhc,
            int messageTypeId, int intervalId, String intervalDesc, String eventDesc,
            InstantRedemption instantRedemptionFile, DailyRedemption dailyRedemption, RedemptionService redemptionDAO) {

        TextIntervalReminderThread intervalMessageThread = new TextIntervalReminderThread();
        intervalMessageThread.setGatewayInfo(gatewayInfo);
        intervalMessageThread.setAggregatorMessageRequest(messageRequest);
        intervalMessageThread.setCampaignMessages(campaignMessages);
        intervalMessageThread.setAppName("PMS Campaign " + programCode + " IRF Processor");
        intervalMessageThread.setProgramCode(programCode);
        intervalMessageThread.setPhoneNumber(dailyRedemption.getCommunicationId());
        intervalMessageThread.setInputReferenceNumber(0);
        intervalMessageThread.setSecondsDelay(secondsDelay);
        intervalMessageThread.setCampaign(campaign);
        intervalMessageThread.setRedemptionDAO(redemptionDAO);
        intervalMessageThread.setFolderId(ehfhc.getFolderId());
        intervalMessageThread.setMessageTypeId(messageTypeId);
        if (dailyRedemption.getCommunicationMethod().equalsIgnoreCase("T")) {
            intervalMessageThread.setCommunicationType(Constants.SMS);
        } else {
            intervalMessageThread.setCommunicationType(dailyRedemption.getCommunicationMethod());
        }
        intervalMessageThread.setIntervalId(intervalId);
        intervalMessageThread.setIntervalDesc(intervalDesc);
        intervalMessageThread.setEventDesc(eventDesc);
        intervalMessageThread.setInstantRedemption(instantRedemptionFile);
        intervalMessageThread.getInstantRedemption().setIngredientList(dailyRedemption.getIngredientList());
        intervalMessageThread.setDailyRedemption(dailyRedemption);
        intervalMessageThread.setMessageSent(1);
        intervalMessageThread.setThreadType("redemption");
        Thread smsIntervalMessageThread = new Thread(intervalMessageThread);

        smsIntervalMessageThread.start();
    }

    public static String prepareMessage(Double copay, BigDecimal outOfPocket, String pharmacyName,
            String pharmacyPhone, String messageText,
            String prescriptionNumber, Integer remainingRefill,
            String orderStatusUrl, Double payment, String surveyUrl, String cardType, String cardNumber) {

        DecimalFormat df = new DecimalFormat("#0.00");

        if (copay != null) {
            messageText = messageText.replace("ZZ.ZZ", df.format(copay));
        }

        if (payment != null) {
            messageText = messageText.replace("WW.WW", df.format(payment));
            logger.info("Payment : " + messageText);
        }

        if (outOfPocket != null) {
            messageText = messageText.replace("XX.XX", df.format(outOfPocket.doubleValue()));
        }

        if (pharmacyName != null) {
            if (pharmacyName.trim().length() > 18) {
                pharmacyName = pharmacyName.substring(0, 18);
            }
            messageText = messageText.replaceAll("PHARMACY_NAME", pharmacyName);
        }

        if (pharmacyPhone != null) {
            String formatedPharmacyPhone = RedemptionUtil.formatPhone(pharmacyPhone);
            messageText = messageText.replaceAll("###-###-####", formatedPharmacyPhone);
        }

        if (prescriptionNumber != null) {
            messageText = messageText.replaceAll("RX_NO", prescriptionNumber);
        }

        if (remainingRefill != null) {
            messageText = messageText.replaceAll("YY", remainingRefill.toString());
        }

        if (orderStatusUrl != null) {
            logger.info("Before Uri: " + orderStatusUrl);
            try {
                orderStatusUrl = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(orderStatusUrl)).getShortUrl();
            } catch (Exception ex) {
                logger.error(ex.getStackTrace());
            }

            logger.info("Shortened Uri: " + orderStatusUrl);
        }
        if (orderStatusUrl != null) {
            messageText = messageText.replace("ORDER_STATUS_URL", orderStatusUrl);
        }

        if (orderStatusUrl != null) {
            messageText = messageText.replaceAll("ORDER_URL", orderStatusUrl);
        }
        if (surveyUrl != null) {
            surveyUrl = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(surveyUrl)).getShortUrl();
            logger.info("Shortened Uri: " + surveyUrl);
            messageText = messageText.replaceAll("_surveyurl_", surveyUrl);
        }

        if (cardType != null) {
            messageText = messageText.replace("CARD_TYPE", cardType);
        }

        if (cardNumber != null) {
            messageText = messageText.replace("NNNN", cardNumber.substring(cardNumber.length() - 4));
        }

        return messageText;
    }

    public static CampaignMessageRequest setCampaignMessageRequest(Long crSeqNo, Integer campaignId, String campaignName, Integer shortCode,
            Integer messageId, Integer repeatMessageTypeId, Integer folderId,
            Long inputReferenceNumber, Integer intervalId, String intervalDesc, String eventDesc, String communicationPath, String repeat) {

        CampaignMessageRequest campaignMessageRequest = new CampaignMessageRequest();
        campaignMessageRequest.setCrSeqNo(crSeqNo);
        campaignMessageRequest.setCampaignId(campaignId);
        campaignMessageRequest.setCampaignName(campaignName);
        campaignMessageRequest.setShortCode(shortCode);
        campaignMessageRequest.setMessageId(messageId);
        campaignMessageRequest.setMessageTypeId(repeatMessageTypeId);
        campaignMessageRequest.setFolderId(folderId);
        campaignMessageRequest.setInputReferenceNumber(inputReferenceNumber);
        campaignMessageRequest.setIntervalId(intervalId);
        campaignMessageRequest.setIntervalDescription(intervalDesc);
        campaignMessageRequest.setEventDetail(eventDesc);
        campaignMessageRequest.setCommunicationPath(communicationPath);
        campaignMessageRequest.setIsRepeat(repeat);

        return campaignMessageRequest;
    }

    public static String parseMessage(String messageText, Double qualifyAmount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (qualifyAmount != null) {
            logger.info("Before MessageText replace: " + messageText);
            messageText = messageText.replaceAll("QUALIFY_AMOUNT", df.format(qualifyAmount.doubleValue()));
            logger.info("After MessageText replace: " + messageText);
        }
        return messageText;
    }

    public static String setMessagePlaceHolder(String messageText, Integer rewardPoint) {
        try 
        {
            System.out.println("Before Reward Point replace: " + rewardPoint);
            if (rewardPoint != null) 
            {

                logger.info("Before Reward Point replace: " + rewardPoint);

                    messageText =AppUtil.getSafeStr(messageText,"").replace("_EARNED-POINTS_", rewardPoint.toString());
            }
            messageText =AppUtil.getSafeStr(messageText,"").replace("[date_time]", DateUtil.dateToString(new Date(), "MM/dd/YYYY hh:mm a"));
        }
        catch (Exception ex) 
        {
//                Logger.getLogger(TextFlowUtil.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            
        }
        return messageText;
    }
}
