/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.service;

import com.ssa.cms.dao.PMSEmailFlowDAO;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GroupHasFolderHasCampaign;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.ReminderPOJO;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.ValidResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PMSEmailFlowService {

    @Autowired
    private PMSEmailFlowDAO emailFlowDAO;

    private final Log logger = LogFactory.getLog(getClass());

    public PMSEmailFlowDAO getEmailFlowDAO() {
        return emailFlowDAO;
    }

    public void setEmailFlowDAO(PMSEmailFlowDAO emailFlowDAO) {
        this.emailFlowDAO = emailFlowDAO;
    }

    public void save(Object bean) {
        try {
            emailFlowDAO.save(bean);
        } catch (Exception ex) {
            logger.error("Exception: PMSEmailFlowService -> save", ex);
            //Logger.getLogger(PMSEmailFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Object bean) {
        try {
            emailFlowDAO.update(bean);
        } catch (Exception ex) {
            logger.error("Exception: PMSEmailFlowService -> update", ex);
            //Logger.getLogger(PMSEmailFlowService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CampaignTrigger getTriggerByKeyword(String keyword) {
        CampaignTrigger trigger = null;
        try {
            trigger = emailFlowDAO.getTriggerByKeyword(keyword);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getTriggerByKeyword", e);
        }
        return trigger;
    }

    public SmtpServerInfo getSmtpServerInfo(int smtpId) {
        SmtpServerInfo smtpServerInfo = null;
        try {
            smtpServerInfo = emailFlowDAO.getSmtpServerInfo(smtpId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getSmtpServerInfo", e);
        }
        return smtpServerInfo;
    }

    public Event getEventByStaticValue(String staticValue) {
        Event event = null;
        try {
            event = emailFlowDAO.getEventByStaticValue(staticValue);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEventByStaticValue", e);
        }
        return event;
    }

    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;
        try {
            eventHasFolderHasCampaigns = emailFlowDAO.getEventHasFolderHasCampaign(campaignId, eventId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEventHasFolderHasCampaign", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId, String communicationPath) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;
        try {
            eventHasFolderHasCampaigns = emailFlowDAO.getEventHasFolderHasCampaign(campaignId, eventId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEventHasFolderHasCampaign", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {
        List<CampaignMessages> list = null;
        try {
            list = emailFlowDAO.getCampaignMessages(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationType(int campaignId, int folderId, String communicationType) {
        List<CampaignMessages> list = null;
        try {
            list = emailFlowDAO.getCampaignMessagesByCommunicationType(campaignId, folderId, communicationType);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessagesByCommunicationType", e);
        }
        return list;
    }

    public ValidResponse getValidResponse(String validWord) {
        ValidResponse validResponse = null;
        try {
            validResponse = emailFlowDAO.getValidResponse(validWord);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getValidResponse", e);
        }
        return validResponse;
    }

    public MessagePriority getEmailPriority(String email, String campaignFrom) {
        MessagePriority messagePriority = null;
        try {
            messagePriority = emailFlowDAO.getEmailPriority(email, campaignFrom);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEmailPriority", e);
        }
        return messagePriority;
    }

    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, int messageTypeId) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = emailFlowDAO.getCampaignMessagesResponse(campaignId, folderId, messageTypeId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessagesResponse", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponsebyCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = emailFlowDAO.getCampaignMessagesResponsebyCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessagesResponsebyCommunicationPath", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResComm(int campaignId, int folderId, String responseTitle, String communicationPath) {
        CampaignMessagesResponse campaignMessagesResponse = null;
        try {
            campaignMessagesResponse = emailFlowDAO.getCampaignMessagesResponseByResComm(campaignId, folderId, responseTitle, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {
        List<CampaignMessages> list = null;
        try {
            list = emailFlowDAO.getCampaignMessages(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessages", e);
        }
        return list;
    }

    public List<CampaignMessages> getCampaignMessagesByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {
        List<CampaignMessages> list = null;
        try {
            list = emailFlowDAO.getCampaignMessagesByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCampaignMessagesByCommunicationPath", e);
        }
        return list;
    }

    public int sentEmailCountCampaign(String email, int campaignId, int folderId, int messageTypeid) {
        int count = 0;
        try {
            count = emailFlowDAO.sentEmailCountCampaign(email, campaignId, folderId, messageTypeid);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> sentEmailCountCampaign", e);
        }
        return count;
    }

    public Campaigns getCampaignsById(Integer campaignsId) {
        Campaigns campaigns = null;
        campaigns = emailFlowDAO.getCampaignsById(campaignsId);
        return campaigns;
    }

    public List<ReminderPOJO> getCouponRemindeRecords() {
        List<ReminderPOJO> list = new ArrayList<>();
        try {
            list = emailFlowDAO.getCouponRemindeRecords();
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getCouponRemindeRecords", e);
        }
        return list;
    }

    public int getIRFRedemptionCountByEmail(String email, int campaignId) {
        int count = 0;
        try {
            count = emailFlowDAO.getIRFRedemptionCountByEmail(email, campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getIRFRedemptionCountByEmail", e);
        }
        return count;
    }

    public long getIRFLastRedemptionSecondsByEmail(String email, int campaignId) {
        long seconds = 0;
        try {
            seconds = emailFlowDAO.getIRFLastRedemptionSecondsByEmail(email, campaignId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getIRFLastRedemptionSecondsByEmail", e);
        }
        return seconds;
    }

    public SmtpServerInfo getSmtpServerInfoByMD5FromEmail(String emailFrom) {
        SmtpServerInfo smtpServerInfo = null;
        try {
            smtpServerInfo = emailFlowDAO.getSmtpServerInfoByMD5FromEmail(emailFrom);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getSmtpServerInfoByMD5FromEmail", e);
        }
        return smtpServerInfo;
    }

    public GroupHasFolderHasCampaign getEmailGroupbyFolderId(int folderId, int campaignId, int campaignMessagesId) {
        GroupHasFolderHasCampaign groupHasFolderHasCampaign = null;
        try {
            groupHasFolderHasCampaign = emailFlowDAO.getEmailGroupbyFolderId(folderId, campaignId, campaignMessagesId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEmailGroupbyFolderId", e);
        }
        return groupHasFolderHasCampaign;
    }

    public String getEventsDescription(int campaignId, int folderId) {
        String events = "";
        try {
            events = emailFlowDAO.getEventsDescription(campaignId, folderId);
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getEventsDescription", e);
        }
        return events;
    }

    public List<Campaigns> getAllOptedOutCampaigns() {
        List<Campaigns> list = null;
        try {
            list = emailFlowDAO.getAllOptedOutCampaigns();
        } catch (Exception e) {
            logger.error("Exception: PMSEmailFlowService -> getAllOptedOutCampaigns", e);
        }
        return list;
    }
}
