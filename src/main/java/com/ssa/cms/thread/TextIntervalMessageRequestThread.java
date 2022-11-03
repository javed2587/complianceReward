package com.ssa.cms.thread;

import com.ssa.cms.model.CampaignMessageReqRes;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.decorator.MTDecorator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Mohsin
 */
public class TextIntervalMessageRequestThread implements Runnable {

    private GatewayInfo gatewayInfo;
    private CampaignMessages campaignMessages;
    private PMSTextFlowService textFlowDAO;
    private Campaigns campaign;
    private int folderId;
    private int messageTypeId;
    private CustomerRequest customerRequest;
    private MTDecorator decorator;
    private String appName;
    private String phoneNumber;
    private long inputReferenceNumber;
    private long secondsDelay;
    private Long cmrSeqNo;
    private boolean isWebEnabled;
    private String communicationType;
    private int intervalId;
    private String intervalDesc;
    private String eventDesc;
    private String groupNumber;
    private String threadType;
    private CampaignMessageRequest campaignMessageRequest;

    private static final Log logger = LogFactory.getLog(TextIntervalMessageRequestThread.class);

    @Override
    public void run() {
        logger.info("TextIntervalMessageRequestThread >> Thread going to sleep for " + secondsDelay + " Seconds.");
        try {
            
            //send first message
            Thread.sleep(1000 * secondsDelay);
            decorator = SMSUtil.sendSmsText(phoneNumber, campaignMessages.getSmstext());
            
            //log message and send paired message
            process();
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            logger.error(Level.SEVERE, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(Level.SEVERE, ex);
        }
    }

    private void process() {

        campaignMessageRequest.setEffectiveDate(new Date());
        textFlowDAO.save(campaignMessageRequest);

        if (campaignMessages.getSmstext() != null && campaignMessages.getSmstext().trim().length() > 0) {
            try {
                CampaignMessageReqRes campaignMessageReqRes = new CampaignMessageReqRes();
                campaignMessageReqRes.setCmrSeqNo(campaignMessageRequest.getCmrSeqNo());
                campaignMessageReqRes.setErrorCode(decorator.getErrorCode());
                campaignMessageReqRes.setErrorDescription(decorator.getErrorDescription());
                campaignMessageReqRes.setSmsRequest(decorator.getRequestXML());
                campaignMessageReqRes.setSmsResponse(decorator.getResponseXML());
                campaignMessageReqRes.setMtsId(decorator.getMtsId());
                campaignMessageReqRes.setMessageText(decorator.getMessageText());
                campaignMessageReqRes.setTicketId(decorator.getTicketId());
                campaignMessageReqRes.setShortCode(campaign.getShortCodes().getShortCode());
                campaignMessageReqRes.setEffectiveDate(new Date());
                campaignMessageReqRes.setCampaignId(campaign.getCampaignId());
                campaignMessageReqRes.setCampaignName(campaign.getCampaignName());
                campaignMessageReqRes.setMessageId(campaignMessages.getMessageId());
                campaignMessageReqRes.setFolderId(folderId);
                campaignMessageReqRes.setMessageTypeId(messageTypeId);
                campaignMessageReqRes.setInputReferenceNumber(inputReferenceNumber);
                textFlowDAO.save(campaignMessageReqRes);
            } catch (Exception ex) {
                Logger.getLogger(TextIntervalMessageRequestThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (threadType.equalsIgnoreCase("text_flow")) {
            TextFlowMessageThread messageThread = new TextFlowMessageThread();
            messageThread.setFolderId(folderId);
            messageThread.setMessageTypeId(messageTypeId);
            messageThread.setCustomerRequest(customerRequest);
            messageThread.setTextFlowDAO(textFlowDAO);
            messageThread.setCampaign(campaign);
            messageThread.setShortCode(campaign.getShortCodes());
            messageThread.setInputReferenceNumber(inputReferenceNumber);
            messageThread.setGroupNumber(groupNumber);
            messageThread.setCommunicationPath(communicationType);
            Thread thread = new Thread(messageThread);
            thread.start();
        }

        if (threadType.equalsIgnoreCase("text_reminder")) {
            TextFlowRepeatMessageThread messageThread = new TextFlowRepeatMessageThread();
            messageThread.setFolderId(folderId);
            messageThread.setMessageTypeId(messageTypeId);
            messageThread.setCustomerRequest(customerRequest);
            messageThread.setTextFlowDAO(textFlowDAO);
            messageThread.setCampaign(campaign);
            messageThread.setIntervalId(intervalId);
            messageThread.setShortCode(campaign.getShortCodes());
            messageThread.setInputReferenceNumber(inputReferenceNumber);
            messageThread.setCommunicationPath(communicationType);
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

    public Long getCmrSeqNo() {
        return cmrSeqNo;
    }

    public void setCmrSeqNo(Long cmrSeqNo) {
        this.cmrSeqNo = cmrSeqNo;
    }

    public PMSTextFlowService getTextFlowDAO() {
        return textFlowDAO;
    }

    public void setTextFlowDAO(PMSTextFlowService textFlowDAO) {
        this.textFlowDAO = textFlowDAO;
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

    public boolean isIsWebEnabled() {
        return isWebEnabled;
    }

    public void setIsWebEnabled(boolean isWebEnabled) {
        this.isWebEnabled = isWebEnabled;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public CustomerRequest getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
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

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getThreadType() {
        return threadType;
    }

    public void setThreadType(String threadType) {
        this.threadType = threadType;
    }

    public CampaignMessageRequest getCampaignMessageRequest() {
        return campaignMessageRequest;
    }

    public void setCampaignMessageRequest(CampaignMessageRequest campaignMessageRequest) {
        this.campaignMessageRequest = campaignMessageRequest;
    }
}
