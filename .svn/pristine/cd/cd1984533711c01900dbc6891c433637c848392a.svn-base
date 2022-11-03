/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PMSEmailFlowDAO extends BaseDAO implements Serializable {

    private final Log logger = LogFactory.getLog(getClass());
   

    

    public void save(Object bean) throws Exception {
        this.getCurrentSession().save(bean);
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public void update(Object bean) throws Exception {
        this.getCurrentSession().update(bean);
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public CampaignTrigger getTriggerByKeyword(String keyword) {

        CampaignTrigger trigger = null;

        try {

            String hql = "select trigger from CampaignTrigger trigger "
                    + "inner join fetch trigger.campaigns campaign "
                    + "where "
                    + "trigger.id.keyword = :keyword "
                    + "and trigger.isActive = 'Yes' "
                    + "and trigger.isDelete = 'No' ";

            List<CampaignTrigger> list = (List<CampaignTrigger>) this.getCurrentSession().createQuery(hql)
                    .setParameter("keyword", keyword)
                    .list();

            if (list != null && (!list.isEmpty())) {
                trigger = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getTriggerByKeyword", e);
        }

        return trigger;
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public SmtpServerInfo getSmtpServerInfo(int smtpId) {

        SmtpServerInfo smtpServerInfo = null;

        try {

            String hql = "select smtpServerInfo from SmtpServerInfo smtpServerInfo "
                    + "where "
                    + "smtpServerInfo.smtpId = :smtpId ";

            List<SmtpServerInfo> list = (List<SmtpServerInfo>) this.getCurrentSession().createQuery(hql)
                    .setParameter("smtpId", smtpId)
                    .list();

            if (list != null && list.size() > 0) {
                smtpServerInfo = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getSmtpServerInfo", e);
        }

        return smtpServerInfo;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public Event getEventByStaticValue(String staticValue) {

        Event event = null;

        try {

            String hql = "select event from Event event "
                    + "where "
                    + "event.eventCriteria = 'Static' "
                    + "and event.staticValue = :staticValue";

            List<Event> list = (List<Event>) this.getCurrentSession().createQuery(hql)
                    .setParameter("staticValue", staticValue)
                    .list();

            if (list != null && list.size() > 0) {
                event = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEventByStaticValue", e);
        }

        return event;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId) {

        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;

        try {

            String hql = "select event from EventHasFolderHasCampaigns event "
                    + "where  "
                    + "event.campaignId = :campaignId "
                    + "and event.eventId = :eventId ";

            List<EventHasFolderHasCampaigns> list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("eventId", eventId)
                    .list();

            if (list != null && list.size() > 0) {
                eventHasFolderHasCampaigns = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEventHasFolderHasCampaign", e);
        }

        return eventHasFolderHasCampaigns;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId, String communicationPath) {

        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;

        try {

            String hql = "select event from EventHasFolderHasCampaigns event "
                    + "where  "
                    + "event.campaignId = :campaignId "
                    + "and event.eventId = :eventId "
                    + "and event.communicationPath=:communicationPath";

            List<EventHasFolderHasCampaigns> list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("eventId", eventId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                eventHasFolderHasCampaigns = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEventHasFolderHasCampaign", e);
        }

        return eventHasFolderHasCampaigns;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessages", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessagesByCommunicationType(int campaignId, int folderId, String communicationType) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.communicationPath = :communicationPath "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("communicationPath", communicationType)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessagesByCommunicationType", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessagesByResponse(int campaignId, int folderId, String communicationType) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.communicationPath = :communicationPath "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("communicationPath", communicationType)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessagesByCommunicationType", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public ValidResponse getValidResponse(String validWord) {

        ValidResponse validResponse = null;

        try {

            String hql = "select validResponse from ValidResponse validResponse "
                    + "where "
                    + "validResponse.validWord = :validWord ";

            List<ValidResponse> list = (List<ValidResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("validWord", validWord)
                    .list();

            if (list != null && list.size() > 0) {
                validResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getValidResponse", e);
        }

        return validResponse;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public MessagePriority getEmailPriority(String email, String campaignFrom) {

        MessagePriority messagePriority = null;

        try {

            String sql = "SELECT  "
                    + "drive_table.Seq_No, "
                    + "drive_table.Email, "
                    + "drive_table.Message_Context, "
                    + "drive_table.Message_Time_Stamp, "
                    + "drive_table.Campaign_Id "
                    + "FROM  "
                    + "( "
                    + "SELECT  "
                    + "request.ECR_Seq_No AS Seq_No, "
                    + "request.Email AS Email, "
                    + "'EmailFlow' AS Message_Context, "
                    + "request.Last_Updated_On AS Message_Time_Stamp, "
                    + "request.Campaign_Id AS Campaign_Id "
                    + "FROM EmailCustomerRequest request "
                    + "INNER JOIN CommunicationSource comSource "
                    + "ON request.Communication_Source_Code = comSource.Communication_Source_Code "
                    + "INNER JOIN SmtpServerInfo smtpServerInfo "
                    + "ON smtpServerInfo.SmtpId = request.Smtp_Id "
                    + "WHERE "
                    + "    MD5(request.Email) = '" + email + "' "
                    + "AND request.Status_Code IN ('IP') "
                    + "AND MD5(smtpServerInfo.FromEmail) = '" + campaignFrom + "' "
                    + "AND comSource.Source_Type = 'External' "
                    + "UNION ALL  "
                    + "SELECT  "
                    + "request.Email_Req_No AS Seq_No, "
                    + "request.Email AS Email, "
                    + "request.Message_Context AS Message_Context, "
                    + "request.Effective_Date AS Message_Time_Stamp, "
                    + "request.Campaign_Id AS Campaign_Id "
                    + "FROM EmailRequest request "
                    + "INNER JOIN EmailResponse response "
                    + "ON request.Email_Req_No = response.Email_Req_No "
                    + "INNER JOIN SmtpServerInfo smtpServerInfo "
                    + "ON smtpServerInfo.SmtpId = request.Smtp_Id "
                    + "WHERE "
                    + "MD5(request.Email) = '" + email + "' "
                    + "AND MD5(smtpServerInfo.FromEmail) = '" + campaignFrom + "' "
                    + "AND request.Message_Context IN ('" + Constants.MSG_CONTEXT_REFILL + "','" + Constants.MSG_CONTEXT_REPEAT_REFILL + "','" + Constants.REDEMPTION + "','" + Constants.MSG_CONTEXT_REFILL_CONFIRMATION + "') "
                    + "AND request.End_Date IS NULL  "
                    + ")drive_table "
                    + "ORDER BY drive_table.Message_Time_Stamp DESC  "
                    + "LIMIT 0,1";

            logger.error("Priority SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object[] result = queryResult.get(0);

                if (result != null) {
                    messagePriority = new MessagePriority();

                    long messageSeqNo = 0;
                    String messageContext = "";
                    Date messageDate = null;
                    int campaignId = 0;

                    Object value = null;

                    value = result[0];

                    if (value != null) {
                        messageSeqNo = Long.valueOf(value.toString());
                        messagePriority.setMessageSeqNo(messageSeqNo);
                    }

                    messagePriority.setEmail(email);

                    value = result[2];

                    if (value != null) {
                        messageContext = value.toString();
                        messagePriority.setMessageContext(messageContext);
                    }

                    value = result[3];

                    if (value != null) {
                        messageDate = (Date) value;
                        messagePriority.setMessageTimeStamp(messageDate);
                    }

                    value = result[4];

                    if (value != null) {
                        campaignId = Integer.parseInt(String.valueOf(value));
                        messagePriority.setCampaignId(campaignId);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEmailPriority", e);
        }
        return messagePriority;
    }


    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, int messageTypeId) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.id.messageTypeId = :messageTypeId ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessagesResponse", e);
        }

        return campaignMessagesResponse;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public CampaignMessagesResponse getCampaignMessagesResponsebyCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.id.messageTypeId = :messageTypeId "
                    + "and campaignMessagesResponse.communicationPath = :communicationPath ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessagesResponsebyCommunicationPath", e);
        }

        return campaignMessagesResponse;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public CampaignMessagesResponse getCampaignMessagesResponseByResComm(int campaignId, int folderId, String responseTitle, String communicationPath) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch campaignMessagesResponse.response response "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId=:campaignId "
                    + "and campaignMessagesResponse.response.responseTitle=:responseTitle "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId=:folderId "
                    + "and campaignMessagesResponse.communicationPath=:communicationPath ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("responseTitle", responseTitle)
                    .setParameter("folderId", folderId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "  campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.messageType.id.messageTypeId = :messageTypeId "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessages", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessagesByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.messageType.id.messageTypeId = :messageTypeId "
                    + "and campaignMessages.communicationPath = :communicationPath "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCampaignMessagesByCommunicationPath", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public int sentEmailCountCampaign(String email, int campaignId, int folderId, int messageTypeid) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Message_Count FROM EmailCustomerRequest customerRequest "
                    + "INNER JOIN CampaignEmailRequest campaignEmailRequest "
                    + "ON customerRequest.ECR_Seq_No = campaignEmailRequest.ECR_Seq_No "
                    + "INNER JOIN CampaignEmailResponse campaignEmailResponse "
                    + "ON campaignEmailRequest.Email_Req_No = campaignEmailResponse.Email_Req_No "
                    + "WHERE "
                    + "    customerRequest.Email = ? "
                    + "AND campaignEmailRequest.Campaign_Id = ? "
                    + "AND campaignEmailRequest.Folder_Id = ? "
                    + "AND campaignEmailRequest.Message_Type_Id = ? "
                    + "AND campaignEmailResponse.Email_Status = 'Success' "
                    + "AND TIMESTAMPDIFF(MINUTE,campaignEmailRequest.Effective_Date, NOW()) <= (24 * 60) + 30 ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, email);
            query.setInteger(1, campaignId);
            query.setInteger(2, folderId);
            query.setInteger(3, messageTypeid);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("Sent message count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }

            logger.error("Phone Number : " + email);
            logger.error("Campaign Id : " + campaignId);
            logger.error("Folder Id : " + folderId);
            logger.error("Message Type Id : " + messageTypeid);
            logger.error("Sent Message Count : " + count);

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> sentEmailCountCampaign", e);
        }

        return count;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public Campaigns getCampaignsById(Integer campaignsId) {
        Campaigns campaigns = null;
        Object o = this.getCurrentSession().get(Campaigns.class, campaignsId);
        if (o != null) {
            campaigns = (Campaigns) o;
        }
        return campaigns;
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public List<ReminderPOJO> getCouponRemindeRecords() {

        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();

        try {

            String sql = "SELECT "
                    + "campaignEmailRequest.Email_Req_No, "
                    + "campaignEmailRequest.ECR_Seq_No, "
                    + "campaignEmailRequest.Campaign_Name, "
                    + "campaignEmailRequest.Campaign_Id, "
                    + "campaignEmailRequest.Folder_Id, "
                    + "campaignEmailRequest.Message_Type_Id, "
                    + "campaignEmailRequest.SmtpId, "
                    + "customerRequest.Email, "
                    + "couponOffered.Card_Number, "
                    + "(ilsType.UnitInSecond * ils.IntervalValue) AS Seconds_Passed, "
                    + "ils.IntervalId, "
                    + "ils.IntervalValue, "
                    + "ilsType.IntervalsTypeTitle "
                    + "FROM EmailCustomerRequest customerRequest "
                    + "INNER JOIN CampaignEmailRequest campaignEmailRequest "
                    + "ON customerRequest.ECR_Seq_No = campaignEmailRequest.ECR_Seq_No "
                    + "INNER JOIN EmailCouponOffered couponOffered "
                    + "ON customerRequest.ECR_Seq_No = couponOffered.ECR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON campaign.CampaignId = customerRequest.Campaign_Id "
                    + "INNER JOIN  CampaignMessagesResponse cmsRes "
                    + "ON "
                    + "( "
                    + "    campaignEmailRequest.Campaign_Id = cmsRes.CampaignId "
                    + "AND campaignEmailRequest.Folder_Id = cmsRes.FolderId "
                    + "AND campaignEmailRequest.Message_Type_Id = cmsRes.MessageTypeId "
                    + ") "
                    + "INNER JOIN Intervals ils  "
                    + "ON "
                    + "( "
                    + "cmsRes.RepeatIntervalId = ils.IntervalId "
                    + "AND cmsRes.RepeatIntervalTypeId = ils.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType ilsType "
                    + "ON cmsRes.RepeatIntervalTypeId = ilsType.IntervalsTypeId "
                    + "INNER JOIN ( "
                    + "SELECT "
                    + "                      MAX(cmr.Effective_Date)                       Effective_Date, "
                    + "                      cr.Email, cmr.Campaign_Id "
                    + "                    FROM EmailCustomerRequest cr "
                    + "                      INNER JOIN CampaignEmailRequest cmr "
                    + "                        ON cr.ECR_Seq_No = cmr.ECR_Seq_No "
                    + "                    GROUP BY cr.Email,cmr.Campaign_Id) drive_distinct "
                    + "          ON (drive_distinct.Email = customerRequest.Email "
                    + "              AND drive_distinct.Effective_Date = campaignEmailRequest.Effective_Date "
                    + "              AND drive_distinct.Campaign_Id = campaignEmailRequest.Campaign_Id) "
                    + "WHERE "
                    + "    customerRequest.Status_Code = 'CM' "
                    + "AND cmsRes.RepeatIntervalId > 0 "
                    + "AND cmsRes.RepeatIntervalTypeId > 0 "
                    + "AND cmsRes.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,campaignEmailRequest.Effective_Date,NOW()) >= ilsType.UnitInSecond * ils.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,campaignEmailRequest.Effective_Date,NOW()) < (ilsType.UnitInSecond * ils.IntervalValue) + (30 * 60 ) ";

            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            logger.error("Result List Size : " + queryResult.size());

            ReminderPOJO reminderPOJO = null;

            int ecrSeqNO = 0;
            long emailReqNo = 0;
            String campaignName = null;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            int smtpId = 0;
            String email = "";
            String cardNumber = "";
            long seconds = 0;
            int intervalId = 0;

            Object value = null;

            for (Object[] record : queryResult) {
                reminderPOJO = new ReminderPOJO();

                value = record[0];
                if (value != null) {
                    emailReqNo = Long.valueOf(value.toString());
                    reminderPOJO.setEmailSeqNo(emailReqNo);
                }

                value = record[1];
                if (value != null) {
                    ecrSeqNO = Integer.parseInt(value.toString());
                    reminderPOJO.setEcrSeqNo(ecrSeqNO);
                }

                value = record[2];
                if (value != null) {
                    campaignName = value.toString();
                    reminderPOJO.setCampaignName(campaignName);
                }

                value = record[3];
                if (value != null) {
                    campaignId = Integer.valueOf(value.toString());
                    reminderPOJO.setCampaignId(campaignId);
                }

                value = record[4];
                if (value != null) {
                    folderId = Integer.valueOf(value.toString());
                    reminderPOJO.setFolderId(folderId);
                }

                value = record[5];
                if (value != null) {
                    messageTypeId = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageTypeId(messageTypeId);
                }

                value = record[6];
                if (value != null) {
                    smtpId = Integer.valueOf(value.toString());
                    reminderPOJO.setSmtpId(smtpId);
                }

                value = record[7];
                if (value != null) {
                    email = value.toString();
                    reminderPOJO.setEmail(email);
                }

                value = record[8];
                if (value != null) {
                    cardNumber = value.toString();
                    reminderPOJO.setCardNumber(cardNumber);
                }

                value = record[9];

                if (value != null) {
                    seconds = Long.valueOf(value.toString().trim());
                    reminderPOJO.setSeconds(seconds);
                }

                value = record[10];
                if (value != null) {
                    intervalId = Integer.parseInt(value.toString());
                    reminderPOJO.setIntervalId(intervalId);
                }
                list.add(reminderPOJO);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getCouponRemindeRecords", e);
        }

        return list;
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public int getIRFRedemptionCountByEmail(String email, int campaignId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Instant_Record_Count FROM InstantRedemption irf "
                    + "WHERE "
                    + "    irf.CampaignId = ? "
                    + "AND irf.Communication_Id = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setString(1, email);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("IRF redemption count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }

            logger.error("Campaign Id : " + campaignId);
            logger.error("Email : " + email);
            logger.error("Redemption Count : " + count);

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getIRFRedemptionCountByEmail", e);
        }

        return count;
    }

    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public long getIRFLastRedemptionSecondsByEmail(String email, int campaignId) {

        long seconds = 0;

        try {

            String sql = "SELECT TIMESTAMPDIFF(SECOND,drive_irf.Effective_Date,NOW()) AS Last_Redemption_Minutes FROM "
                    + "( "
                    + "SELECT irf.Effective_Date,irf.Claim_Status FROM InstantRedemption irf "
                    + "WHERE "
                    + "irf.Communication_Id = ? "
                    + "AND irf.CampaignId = ? "
                    + "GROUP BY irf.Fill_Date, irf.Communication_Id, irf.Rx_Group_Number, irf.Cardholder_DOB, irf.NDC_Number, irf.Transaction_Number "
                    + "HAVING COUNT(*) = 1 "
                    + ") "
                    + "drive_irf "
                    + "WHERE drive_irf.Claim_Status = 0 "
                    + "ORDER BY drive_irf.Effective_Date DESC "
                    + "LIMIT 0,1 ";

            logger.error("IRF last redemption seconds query : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, email);
            query.setInteger(1, campaignId);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    seconds = Long.valueOf(object.toString());
                }
            }

            logger.error("Campaign Id : " + campaignId);
            logger.error("Email : " + email);
            logger.error("Seconds Passed : " + seconds);

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getIRFLastRedemptionSecondsByEmail", e);
        }

        return seconds;
    }


    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     */
    public SmtpServerInfo getSmtpServerInfoByMD5FromEmail(String emailFrom) {

        SmtpServerInfo smtpServerInfo = null;

        try {

            String hql = "select smtpServerInfo from SmtpServerInfo smtpServerInfo "
                    + "where "
                    + "MD5(smtpServerInfo.fromEmail) = :emailFrom ";

            List<SmtpServerInfo> list = (List<SmtpServerInfo>) this.getCurrentSession().createQuery(hql)
                    .setParameter("emailFrom", emailFrom)
                    .list();

            if (list != null && list.size() > 0) {
                smtpServerInfo = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getSmtpServerInfoByMD5FromEmail", e);
        }
        return smtpServerInfo;

    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public GroupHasFolderHasCampaign getEmailGroupbyFolderId(int folderId, int campaignId, int campaignMessagesId) {

        GroupHasFolderHasCampaign groupHasFolderHasCampaign = null;

        try {

            String hql = "SELECT groupHasFolderHasCampaign FROM GroupHasFolderHasCampaign groupHasFolderHasCampaign "
                    + "WHERE "
                    + "    groupHasFolderHasCampaign.id.folderId = :folderId "
                    + " and groupHasFolderHasCampaign.id.campaignId = :campaignId "
                    + " and groupHasFolderHasCampaign.sourceType = 'email' "
                    + "and groupHasFolderHasCampaign.id.campaignMessagesId = :campaignMessagesId";

            List<GroupHasFolderHasCampaign> list = (List<GroupHasFolderHasCampaign>) this.getCurrentSession().createQuery(hql)
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("campaignMessagesId", campaignMessagesId)
                    .list();

            if (list != null && (!list.isEmpty())) {
                groupHasFolderHasCampaign = new GroupHasFolderHasCampaign();
                groupHasFolderHasCampaign = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEmailGroupbyFolderId", e);
        }

        return groupHasFolderHasCampaign;
    }

    public String getEventsDescription(int campaignId, int folderId) {

        String events = "";

        try {

            String sql = "SELECT "
                    + "GROUP_CONCAT(event.EventTitle) AS eventTitle "
                    + "FROM EventHasFolderHasCampaigns ehfhc "
                    + "INNER JOIN Event event "
                    + "ON event.EventId = ehfhc.EventId "
                    + "WHERE "
                    + " ehfhc.CampaignId = ? "
                    + "AND ehfhc.FolderId = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setInteger(1, folderId);

            List<Object[]> list = query.list();

            Object value = null;

            if (list != null && !list.isEmpty()) {
                value = list.get(0);
                events = value.toString();
            }

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getEventsDescription", e);
        }

        return events;
    }

    /**
     * *******************************************************************************************
     */
    public List<Campaigns> getAllOptedOutCampaigns() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'NO' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) < CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception:PMSEmailFlowDAO -> getAllOptedOutCampaigns", e);
        }
        return list;
    }
}
