package com.ssa.cms.thread;

import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.AggregatorMessageResponse;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TextIntervalReminderThread implements Runnable {

    private GatewayInfo gatewayInfo;
    private CampaignMessages campaignMessages;
    private RedemptionService redemptionDAO;
    private RefillReminderService refillReminderDAO;
    private Campaigns campaign;
    private InstantRedemption instantRedemption;
    private DailyRedemption dailyRedemption;
    private AggregatorMessageRequest aggregatorMessageRequest;
    private MTDecorator decorator;
    private int folderId;
    private int messageTypeId;
    private String appName;
    private String phoneNumber;
    private long inputReferenceNumber;
    private long secondsDelay;
    private String communicationType;
    private int intervalId;
    private String intervalDesc;
    private String eventDesc;
    private String programCode;
    private int messageSent;
    private String threadType;
    private int dhdReferenceNumber;

    private static final Log logger = LogFactory.getLog(TextIntervalMessageRequestThread.class);

    @Override
    public void run() {

        logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");
        try {
            Thread.sleep(1000 * secondsDelay);
            decorator = SMSUtil.sendSmsMessage(phoneNumber, campaignMessages.getSmstext());
            setDecorator(decorator);
            process();
        } catch (InterruptedException ex) {
            Logger.getLogger(TextIntervalReminderThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            logger.error(Level.SEVERE, ex);
        }
    }

    private void process() {

        aggregatorMessageRequest.setEffectiveDate(new Date());

        if (campaignMessages.getSmstext() != null && campaignMessages.getSmstext().trim().length() > 0) {
            aggregatorMessageRequest.setMessageRequest(decorator.getRequestXML());
            if (threadType.equalsIgnoreCase("pa_pending")
                    || threadType.equalsIgnoreCase("pa_pending_close")
                    || threadType.equalsIgnoreCase("data_rec")
                    || threadType.equalsIgnoreCase("res_handler")
                    || threadType.equalsIgnoreCase("res_handler1")) {
                aggregatorMessageRequest.setMessageText(decorator.getMessageText());
            }
            aggregatorMessageRequest.setMessageText(campaignMessages.getSmstext());
            if (redemptionDAO != null) {
                redemptionDAO.save(aggregatorMessageRequest);
            } else {
                try {
                    refillReminderDAO.save(aggregatorMessageRequest);
                } catch (Exception ex) {
                    Logger.getLogger(TextIntervalReminderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            AggregatorMessageResponse messageResponse = new AggregatorMessageResponse();
            if (decorator.getErrorCode() == null) {
                decorator.setErrorCode("201");
            }
            messageResponse.setErrorCode(decorator.getErrorCode());
            messageResponse.setErrorDescription(decorator.getErrorDescription());
            messageResponse.setMessageReqNo(aggregatorMessageRequest.getMessageReqNo());
            messageResponse.setMessageResponse(decorator.getResponseXML());
            messageResponse.setTicketId(decorator.getTicketId());
            messageResponse.setMtsId(decorator.getMtsId());
            messageResponse.setEffectiveDate(new Date());

            if (redemptionDAO != null) {
                redemptionDAO.save(messageResponse);
            } else {
                try {
                    refillReminderDAO.save(messageResponse);
                } catch (Exception ex) {
                    Logger.getLogger(TextIntervalReminderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (threadType.equalsIgnoreCase("redemption")) {
            RedemptionMessageThread messageThread = new RedemptionMessageThread();
            messageThread.setCampaign(campaign);
            messageThread.setFolderId(folderId);
            messageThread.setGatewayInfo(gatewayInfo);
            messageThread.setInputReferenceNumber(0);
            messageThread.setInstantRedemption(instantRedemption);
            messageThread.setMessageContext(aggregatorMessageRequest.getMessageContext());
            messageThread.setMessageTypeId(messageTypeId);
            messageThread.setRedemptionDAO(redemptionDAO);
            messageThread.setCommunicationPath(communicationType);
            messageThread.setShortCodes(campaign.getShortCodes());

            Thread thread = new Thread(messageThread);
            thread.start();
        }

        if (threadType.equalsIgnoreCase("refill") || threadType.equalsIgnoreCase("res_handler")) {
            RefillReminderMessageThread messageThread = new RefillReminderMessageThread();

            messageThread.setFolderId(folderId);
            messageThread.setGatewayInfo(gatewayInfo);
            messageThread.setMessageTypeId(messageTypeId);
            messageThread.setRefillReminderDAO(refillReminderDAO);
            messageThread.setCampaign(campaign);
            messageThread.setMessageContext(aggregatorMessageRequest.getMessageContext());
            messageThread.setInstantRedemption(instantRedemption);
            messageThread.setCommunicationPath(communicationType);

            Thread thread = new Thread(messageThread);
            thread.start();
        }

        if (threadType.equalsIgnoreCase("repeat_refill")) {
            RepeatRefillReminderMessageThread messageThread = new RepeatRefillReminderMessageThread();

            messageThread.setFolderId(folderId);
            messageThread.setGatewayInfo(gatewayInfo);
            messageThread.setMessageTypeId(messageTypeId);
            messageThread.setRefillReminderDAO(refillReminderDAO);
            messageThread.setCampaign(campaign);
            messageThread.setMessageContext(aggregatorMessageRequest.getMessageContext());
            messageThread.setInstantRedemption(instantRedemption);
            messageThread.setCommunicationPath(communicationType);
            messageThread.setIntervalId(intervalId);

            Thread thread = new Thread(messageThread);
            thread.start();
        }

    }

    public GatewayInfo getGatewayInfo() {
        return gatewayInfo;
    }

    public void setGatewayInfo(GatewayInfo gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }

    public CampaignMessages getCampaignMessages() {
        return campaignMessages;
    }

    public void setCampaignMessages(CampaignMessages campaignMessages) {
        this.campaignMessages = campaignMessages;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getInputReferenceNumber() {
        return inputReferenceNumber;
    }

    public void setInputReferenceNumber(long inputReferenceNumber) {
        this.inputReferenceNumber = inputReferenceNumber;
    }

    public long getSecondsDelay() {
        return secondsDelay;
    }

    public void setSecondsDelay(long secondsDelay) {
        this.secondsDelay = secondsDelay;
    }

    public MTDecorator getDecorator() {
        return decorator;
    }

    public void setDecorator(MTDecorator decorator) {
        this.decorator = decorator;
    }

    public RedemptionService getRedemptionDAO() {
        return redemptionDAO;
    }

    public void setRedemptionDAO(RedemptionService redemptionDAO) {
        this.redemptionDAO = redemptionDAO;
    }

    public Campaigns getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaigns campaign) {
        this.campaign = campaign;
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

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public int getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(int intervalId) {
        this.intervalId = intervalId;
    }

    public String getIntervalDesc() {
        return intervalDesc;
    }

    public void setIntervalDesc(String intervalDesc) {
        this.intervalDesc = intervalDesc;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public InstantRedemption getInstantRedemption() {
        return instantRedemption;
    }

    public void setInstantRedemption(InstantRedemption instantRedemption) {
        this.instantRedemption = instantRedemption;
    }

    public int getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(int messageSent) {
        this.messageSent = messageSent;
    }

    public String getThreadType() {
        return threadType;
    }

    public void setThreadType(String threadType) {
        this.threadType = threadType;
    }

    public RefillReminderService getRefillReminderDAO() {
        return refillReminderDAO;
    }

    public void setRefillReminderDAO(RefillReminderService refillReminderDAO) {
        this.refillReminderDAO = refillReminderDAO;
    }

    public DailyRedemption getDailyRedemption() {
        return dailyRedemption;
    }

    public void setDailyRedemption(DailyRedemption dailyRedemption) {
        this.dailyRedemption = dailyRedemption;
    }

    public AggregatorMessageRequest getAggregatorMessageRequest() {
        return aggregatorMessageRequest;
    }

    public void setAggregatorMessageRequest(AggregatorMessageRequest aggregatorMessageRequest) {
        this.aggregatorMessageRequest = aggregatorMessageRequest;
    }

    public int getDhdReferenceNumber() {
        return dhdReferenceNumber;
    }

    public void setDhdReferenceNumber(int dhdReferenceNumber) {
        this.dhdReferenceNumber = dhdReferenceNumber;
    }
}
