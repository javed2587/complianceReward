package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.GroupHasFolderHasCampaign;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.ReminderPOJO;
import com.ssa.cms.model.Response;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.Url;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.model.WidgetUser;
import com.ssa.cms.model.WidgetUserIpaddresses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PMSTextFlowDAO extends BaseDAO implements Serializable {

    private final Log logger = LogFactory.getLog(getClass());
    
    public void save(Object bean) throws Exception {
        this.getCurrentSession().save(bean);
    }

    public void update(Object bean) throws Exception {
        this.getCurrentSession().update(bean);
    }

    public CampaignTrigger getTriggerByKeyword(String keyword) throws Exception {

        String hql = "select trigger from CampaignTrigger trigger "
                + "inner join  trigger.campaigns campaign "
                + "INNER JOIN  trigger.campaigns.shortCodes shortCodes "
                + "where "
                + "trigger.id.keyword = :keyword "
                + "and trigger.isActive = 'Yes' "
                + "and trigger.isDelete = 'No' ";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("keyword",keyword);
          return (CampaignTrigger) query.uniqueResult();
//        return (CampaignTrigger) this.getCurrentSession().createQuery(hql).setParameter("keyword", keyword).uniqueResult();
    }

    public Campaigns getCampaigns(Integer campaignId) {

        Campaigns campaigns = null;

        try {

            String hql = "select campaign from Campaigns campaign "
                    + "where "
                    + "    campaign.campaignId = :campaignId "
                    + "and campaign.isActive = 'Yes' "
                    + "and campaign.isDelete = 'No' ";

            List<Campaigns> list = (List<Campaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && list.size() > 0) {
                campaigns = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCampaigns", e);
        }
        return campaigns;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public ShortCodes getShortCodeByCode(Integer code) {

        ShortCodes shortCode = null;

        try {

            String hql = "select shortCode from ShortCodes shortCode "
                    + "where "
                    + "shortCode.shortCode = :code ";

            List<ShortCodes> list = (List<ShortCodes>) this.getCurrentSession().createQuery(hql)
                    .setParameter("code", code)
                    .list();

            if (list != null && list.size() > 0) {
                shortCode = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getShortCodeByCode", e);
        }

        return shortCode;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     */
    /**
     * *******************************************************************************************
     * @param staticValue
     * @return
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
            logger.error("Exception:PMSTextFlowDAO -> getEventByStaticValue", e);
        }

        return event;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     * @param campaignId
     * @param eventId
     * @param communicationPath
     * @return
     */
    public EventHasFolderHasCampaigns getEventHasFolderHasCampaign(int campaignId, int eventId, String communicationPath) {

        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = null;

        try {

            String hql = "select event from EventHasFolderHasCampaigns event "
                    + "where  "
                    + "event.campaignId = :campaignId "
                    + "and event.eventId = :eventId "
                    + "and event.communicationPath=:communicationPath ";

            List<EventHasFolderHasCampaigns> list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("eventId", eventId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                eventHasFolderHasCampaigns = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getEventHasFolderHasCampaign", e);
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
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessages", e);
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
     * @param campaignId
     * @param folderId
     * @return
     */
    public List<CampaignMessages> getCampaignMessagesByCommunicationType(int campaignId, int folderId) {

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
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesByCommunicationType", e);
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
    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId, int messageTypeId) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.messageType.id.messageTypeId = :messageTypeId "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessages", e);
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
//                    + "and campaignMessages.communicationPath = :communicationPath "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
//                    .setParameter("communicationPath", communicationPath)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesByCommunicationPath", e);
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
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesResponse", e);
        }

        return campaignMessagesResponse;

    }

    public List<CampaignMessagesResponse> getResponseByCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {

        List<CampaignMessagesResponse> list = null;
        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "left join fetch campaignMessagesResponse.response "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.id.messageTypeId = :messageTypeId ";
//                    + "and campaignMessagesResponse.comunicationPath = :communicationPath ";

            logger.error("HQL : " + hql);

            list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
//                    .setParameter("communicationPath", communicationPath)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesResponsebyCommunicationPath", e);
        }

        return list;

    }

    public CampaignMessagesResponse getCampaignMessagesResponsebyCommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.id.messageTypeId = :messageTypeId ";
//                    + "and campaignMessagesResponse.communicationPath = :communicationPath ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
//                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesResponsebyCommunicationPath", e);
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
    public CampaignMessagesResponse getCampaignMessagesResponseWithResponseTitle(int campaignId, int folderId, int messageTypeId, String responseTitle) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.id.messageTypeId = :messageTypeId "
                    + "and campaignMessagesResponse.response.responseTitle = :responseTitle ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .setParameter("responseTitle", responseTitle)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getCampaignMessagesResponseWithResponseTitle", e);
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
     * @param validWord
     * @return
     */
    public ValidResponse getValidResponse(String validWord) {

        ValidResponse validResponse = null;

        try {

            String hql = "select validResponse from ValidResponse validResponse "
                    + "left join fetch validResponse.response response "
                    + "where "
                    + "validResponse.validWord = :validWord "
                    + "and validResponse.isActive = 'YES' "
                    + "and validResponse.isDelete = 'NO' "
                    + "order by validResponse.id.vresponseId asc ";

            List<ValidResponse> list = (List<ValidResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("validWord", validWord)
                    .list();

            if (list != null && list.size() > 0) {
                validResponse = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getValidResponse", e);
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
    public Response getResponseByTitle(String responseTitle) {

        Response response = null;

        try {

            String hql = "select response from Response response "
                    + "where "
                    + "response.responseTitle = :responseTitle ";

            List<Response> list = (List<Response>) this.getCurrentSession().createQuery(hql)
                    .setParameter("responseTitle", responseTitle)
                    .list();

            if (list != null && list.size() > 0) {
                response = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getResponseByTitle", e);
        }
        return response;
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
    public CustomerRequest getCustomerRequestIP(String phoneNumber, int shortCode) {

        CustomerRequest customerRequest = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest "
                    + "where "
                    + "customerRequest.statusCode = :statusCode "
                    + "and customerRequest.phoneNumber = :phoneNumber "
                    + "and customerRequest.shortCode = :shortCode "
                    + "order by customerRequest.effectiveDate desc ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("statusCode", StatusEnum.IN_PROGRESS.getValue())
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("shortCode", shortCode)
                    .list();

            if (list != null && list.size() > 0) {
                customerRequest = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCustomerRequestIP", e);
        }

        return customerRequest;

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
    public CustomerRequest getCustomerRequestById(long crSeqNo) {

        CustomerRequest customerRequest = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest "
                    + "where "
                    + "customerRequest.crSeqNo = :crSeqNo ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("crSeqNo", crSeqNo)
                    .list();

            if (list != null && (!list.isEmpty())) {
                customerRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getCustomerRequestById", e);
        }

        return customerRequest;

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
    public CustomerRequest getCustomerRequestIPorCM(String phoneNumber, int shortCode) {

        CustomerRequest customerRequest = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest "
                    + "where "
//                    + "customerRequest.statusCode IN ( :statusCodeIP, :statusCodeCM) "
                    + " customerRequest.phoneNumber = :phoneNumber "
                    + "and customerRequest.shortCode = :shortCode "
                    + "order by customerRequest.effectiveDate desc ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
//                    .setParameter("statusCodeIP", StatusEnum.IN_PROGRESS.getValue())
//                    .setParameter("statusCodeCM", StatusEnum.COMPLETED.getValue())
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("shortCode", shortCode)
                    .list();

            if (list != null && list.size() > 0) {
                customerRequest = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getCustomerRequestIPorCM", e);
        }

        return customerRequest;

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
    public OptInOut getOptInOut(long crSeqNo) {

        OptInOut optInOut = null;

        try {

            String hql = "select optInOut from OptInOut optInOut "
                    + "where "
                    + "optInOut.crSeqNo = :crSeqNo";

            List<OptInOut> list = (List<OptInOut>) this.getCurrentSession().createQuery(hql)
                    .setParameter("crSeqNo", crSeqNo)
                    .list();

            if (list != null && list.size() > 0) {
                optInOut = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getOptInOut", e);
        }

        return optInOut;

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
    public CampaignMessageRequest getLastCampaignMessageRequestByCRSeqNo(long crSeqNo) {

        CampaignMessageRequest campaignMessageRequest = null;

        try {

            String hql = "select campaignMessageRequest from CampaignMessageRequest campaignMessageRequest "
                    + "where "
                    + "campaignMessageRequest.crSeqNo = :crSeqNo "
                    + "order by campaignMessageRequest.effectiveDate desc ";

            List<CampaignMessageRequest> list = (List<CampaignMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("crSeqNo", crSeqNo)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessageRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getLastCampaignMessageRequestByCRSeqNo", e);
        }

        return campaignMessageRequest;

    }

    public List<ReminderPOJO> getAutoTextFlowRecord() {

        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();

        try {

            String sql = "SELECT \n"
                    + "	CMR_Seq_No,\n"
                    + "	CustomerRequest.CR_Seq_No,\n"
                    + "	CustomerRequest.Campaign_Name,\n"
                    + "	CustomerRequest.Campaign_Id,\n"
                    + "	Folder_Id,\n"
                    + "	Message_Type_Id,\n"
                    + "	CampaignMessageRequest.Short_Code,\n"
                    + "	Phone_Number,\n"
                    + "	CampaignMessageRequest.Communication_Path,\n"
                    + "	CampaignMessageRequest.Effective_Date\n"
                    + "FROM CampaignMessageRequest CampaignMessageRequest\n"
                    + "	INNER JOIN CustomerRequest CustomerRequest ON CustomerRequest.CR_Seq_No = CampaignMessageRequest.CR_Seq_No\n"
                    + "WHERE CMR_Seq_No IN (\n"
                    + "	SELECT MAX(`CMR_Seq_No`) FROM CampaignMessageRequest \n"
                    + "		WHERE Campaign_id = 12 \n"
                    + "		AND Folder_Id = 1\n"
                    + "		GROUP BY CR_Seq_No\n"
                    + "	) \n"
                    + "	AND Message_TYPE_Id = 1 \n"
                    + "	AND Status_Code = 'IP'\n"
                    + "	AND TIMESTAMPDIFF(MINUTE, CampaignMessageRequest.Effective_Date, NOW()) >=1\n"
                    + "	AND TIMESTAMPDIFF(MINUTE, CampaignMessageRequest.Effective_Date, NOW()) <=5";

            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            logger.error("Result List Size : " + queryResult.size());

            Object value = null;

            for (Object[] record : queryResult) {
                ReminderPOJO reminderPOJO = new ReminderPOJO();

                value = record[0];
                if (value != null) {
                    long cmrSeqNo = Long.valueOf(value.toString());
                    reminderPOJO.setCmrSeqNo(cmrSeqNo);
                }

                value = record[1];
                if (value != null) {
                    long crSeqNO = Long.valueOf(value.toString());
                    reminderPOJO.setCrSeqNo(crSeqNO);
                }

                value = record[2];
                if (value != null) {
                    String campaignName = value.toString();
                    reminderPOJO.setCampaignName(campaignName);
                }

                value = record[3];
                if (value != null) {
                    int campaignId = Integer.valueOf(value.toString());
                    reminderPOJO.setCampaignId(campaignId);
                }

                value = record[4];
                if (value != null) {
                    int folderId = Integer.valueOf(value.toString());
                    reminderPOJO.setFolderId(folderId);
                }

                value = record[5];
                if (value != null) {
                    int messageTypeId = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageTypeId(messageTypeId);
                }

                value = record[6];
                if (value != null) {
                    int shortCode = Integer.valueOf(value.toString());
                    reminderPOJO.setShortCode(shortCode);
                }

                value = record[7];
                if (value != null) {
                    String phoneNumber = value.toString();
                    reminderPOJO.setPhoneNumber(phoneNumber);
                }

                value = record[8];
                if (value != null) {
                    String communicationPath = value.toString();
                    reminderPOJO.setCommunicationPath(communicationPath);
                }

                list.add(reminderPOJO);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getTextFlowReminderRecords", e);
        }

        return list;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<ReminderPOJO> getTextFlowReminderRecords(int yCount) {

        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();

        try {

            String sql = "SELECT "
                    + "drive_table.seqNo, "
                    + "drive_table.CR_Seq_No, "
                    + "drive_table.Campaign_Name, "
                    + "drive_table.Campaign_Id, "
                    + "drive_table.Folder_Id, "
                    + "drive_table.Message_Type_Id, "
                    + "drive_table.Short_Code, "
                    + "drive_table.Input_Reference_No, "
                    + "drive_table.Phone_Number, "
                    + "drive_table.Keyword_Code, "
                    + "drive_table.Communication_Path, "
                    + "drive_table.Effective_Date, "
                    + "drive_table.IntervalId, "
                    + "drive_table.IntervalValue, "
                    + "drive_table.IntervalsTypeTitle "
                    + "FROM "
                    + "( "
                    + "SELECT "
                    + "campaignMessageRequest.CMR_Seq_No AS seqNo, "
                    + "campaignMessageRequest.CR_Seq_No, "
                    + "campaignMessageRequest.Campaign_Name, "
                    + "campaignMessageRequest.Campaign_Id, "
                    + "campaignMessageRequest.Folder_Id, "
                    + "campaignMessageRequest.Message_Type_Id, "
                    + "campaignMessageRequest.Short_Code, "
                    + "campaignMessageRequest.Input_Reference_No, "
                    + "customerRequest.Phone_Number, "
                    + "customerRequest.Keyword_Code, "
                    + "customerRequest.Communication_Path, "
                    + "campaignMessageRequest.Effective_Date AS  Effective_Date, "
                    + "ils.IntervalId, "
                    + "ils.IntervalValue, "
                    + "ilsType.IntervalsTypeTitle "
                    + "FROM CustomerRequest customerRequest "
                    + "INNER JOIN CampaignMessageRequest campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON campaign.CampaignId = customerRequest.Campaign_Id "
                    + "INNER JOIN CampaignMessagesResponse cmsRes "
                    + "ON "
                    + "( "
                    + "    campaignMessageRequest.Campaign_Id = cmsRes.CampaignId "
                    + "AND campaignMessageRequest.Folder_Id = cmsRes.FolderId "
                    + "AND campaignMessageRequest.Message_Type_Id = cmsRes.MessageTypeId "
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
                    + "                      cr.Phone_Number, cmr.Campaign_Id "
                    + "                    FROM CustomerRequest cr "
                    + "                      INNER JOIN CampaignMessageRequest cmr "
                    + "                        ON cr.CR_Seq_No = cmr.CR_Seq_No "
                    + "                    GROUP BY cr.Phone_Number,cmr.Campaign_Id) drive_distinct "
                    + "          ON (drive_distinct.Phone_Number = customerRequest.Phone_Number "
                    + "              AND (drive_distinct.Effective_Date = campaignMessageRequest.Effective_Date) AND drive_distinct.Campaign_Id = campaignMessageRequest.Campaign_Id) "
                    + " WHERE "
                    + "    customerRequest.yCount = ? "
                    + "AND customerRequest.Status_Code = 'IP' "
                    + "AND cmsRes.RepeatIntervalId > 0 "
                    + "AND cmsRes.RepeatIntervalTypeId > 0 "
                    + "AND cmsRes.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) >= ilsType.UnitInSecond * ils.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) < (ilsType.UnitInSecond * ils.IntervalValue) + (30 * 60 ) "
                    + "UNION ALL "
                    + "SELECT "
                    + "campaignMessageRequest.MMS_CMR_Seq_No, "
                    + "campaignMessageRequest.CR_Seq_No, "
                    + "campaignMessageRequest.Campaign_Name, "
                    + "campaignMessageRequest.Campaign_Id, "
                    + "campaignMessageRequest.Folder_Id, "
                    + "campaignMessageRequest.Message_Type_Id, "
                    + "campaignMessageRequest.Short_Code, "
                    + "campaignMessageRequest.Input_Reference_No, "
                    + "customerRequest.Phone_Number, "
                    + "customerRequest.Keyword_Code, "
                    + "customerRequest.Communication_Path, "
                    + "campaignMessageRequest.Effective_Date, "
                    + "ils.IntervalId, "
                    + "ils.IntervalValue, "
                    + "ilsType.IntervalsTypeTitle "
                    + " FROM CustomerRequest customerRequest "
                    + " INNER JOIN MMSCampaignMessageReqRes campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON campaign.CampaignId = customerRequest.Campaign_Id "
                    + "INNER JOIN CampaignMessagesResponse cmsRes "
                    + "ON "
                    + "( "
                    + "   campaignMessageRequest.Campaign_Id = cmsRes.CampaignId "
                    + "AND campaignMessageRequest.Folder_Id = cmsRes.FolderId "
                    + "AND campaignMessageRequest.Message_Type_Id = cmsRes.MessageTypeId "
                    + ") "
                    + "INNER JOIN Intervals ils  "
                    + "ON "
                    + "( "
                    + "cmsRes.RepeatIntervalId = ils.IntervalId "
                    + "AND cmsRes.RepeatIntervalTypeId = ils.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType ilsType "
                    + "ON cmsRes.RepeatIntervalTypeId = ilsType.IntervalsTypeId "
                    + "INNER JOIN ( SELECT "
                    + "                      MAX(cmr.Effective_Date)                       Effective_Date, "
                    + "                      cr.Phone_Number, cmr.Campaign_Id "
                    + "                    FROM CustomerRequest cr "
                    + "                      INNER JOIN MMSCampaignMessageReqRes cmr "
                    + "                        ON cr.CR_Seq_No = cmr.CR_Seq_No "
                    + "                    GROUP BY cr.Phone_Number,cmr.Campaign_Id) drive_distinct "
                    + "          ON (drive_distinct.Phone_Number = customerRequest.Phone_Number "
                    + "              AND (drive_distinct.Effective_Date = campaignMessageRequest.Effective_Date) AND drive_distinct.Campaign_Id = campaignMessageRequest.Campaign_Id ) "
                    + "WHERE "
                    + "    customerRequest.yCount = ? "
                    + "AND customerRequest.Status_Code = 'IP'  "
                    + "AND cmsRes.RepeatIntervalId > 0 "
                    + "AND cmsRes.RepeatIntervalTypeId > 0 "
                    + "AND cmsRes.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) >= ilsType.UnitInSecond * ils.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) < (ilsType.UnitInSecond * ils.IntervalValue) + (30 * 60 ) "
                    + ") drive_table "
                    + "ORDER BY drive_table.Effective_Date ASC";

            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, yCount);
            query.setInteger(1, yCount);

            List<Object[]> queryResult = query.list();

            logger.error("Result List Size : " + queryResult.size());

            ReminderPOJO reminderPOJO = null;

            long crSeqNO = 0;
            long cmrSeqNo = 0;
            String campaignName = null;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            int shortCode = 0;
            long inputReferenceNumber = 0;
            String phoneNumber = "";
            String keywordCode = "";
            String communicationPath = "";
            int IntervalId = 0;
            String intervalvalue = "";
            String intervalTypeTitle = "";

            Object value = null;

            for (Object[] record : queryResult) {
                reminderPOJO = new ReminderPOJO();

                value = record[0];
                if (value != null) {
                    cmrSeqNo = Long.valueOf(value.toString());
                    reminderPOJO.setCmrSeqNo(cmrSeqNo);
                }

                value = record[1];
                if (value != null) {
                    crSeqNO = Long.valueOf(value.toString());
                    reminderPOJO.setCrSeqNo(crSeqNO);
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
                    shortCode = Integer.valueOf(value.toString());
                    reminderPOJO.setShortCode(shortCode);
                }

                value = record[7];
                if (value != null) {
                    inputReferenceNumber = Long.valueOf(value.toString());
                    reminderPOJO.setInputReferenceNumber(inputReferenceNumber);
                }

                value = record[8];
                if (value != null) {
                    phoneNumber = value.toString();
                    reminderPOJO.setPhoneNumber(phoneNumber);
                }

                value = record[9];
                if (value != null) {
                    keywordCode = value.toString();
                    reminderPOJO.setKeywordCode(keywordCode);
                }

                value = record[10];
                if (value != null) {
                    communicationPath = value.toString();
                    reminderPOJO.setCommunicationPath(communicationPath);
                }

                value = record[12];
                if (value != null) {
                    IntervalId = Integer.parseInt(value.toString());
                    reminderPOJO.setIntervalId(IntervalId);
                }

                value = record[13];
                if (value != null) {
                    intervalvalue = value.toString();
                }

                value = record[14];
                if (value != null) {
                    intervalTypeTitle = value.toString();
                }

                reminderPOJO.setIntervalValue(intervalvalue + " " + intervalTypeTitle);
                list.add(reminderPOJO);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getTextFlowReminderRecords", e);
        }

        return list;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<ReminderPOJO> getCouponRemindeRecords() {

        List<ReminderPOJO> list = new ArrayList<ReminderPOJO>();

        try {

            String sql = "SELECT "
                    + "campaignMessageRequest.CMR_Seq_No, "
                    + "campaignMessageRequest.CR_Seq_No, "
                    + "campaignMessageRequest.Campaign_Name, "
                    + "campaignMessageRequest.Campaign_Id, "
                    + "campaignMessageRequest.Folder_Id, "
                    + "campaignMessageRequest.Message_Type_Id, "
                    + "campaignMessageRequest.Short_Code, "
                    + "campaignMessageRequest.Input_Reference_No, "
                    + "customerRequest.Phone_Number, "
                    + "couponOffered.Card_Number, "
                    + "(ilsType.UnitInSecond * ils.IntervalValue) AS Seconds_Passed, "
                    + "campaignMessageRequest.Communication_Path, "
                    + "ils.IntervalId, "
                    + "ils.IntervalValue, "
                    + "ilsType.IntervalsTypeTitle "
                    + "FROM CustomerRequest customerRequest "
                    + "INNER JOIN CampaignMessageRequest campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN CouponOffered couponOffered "
                    + "ON customerRequest.CR_Seq_No = couponOffered.CR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON campaign.CampaignId = customerRequest.Campaign_Id "
                    + "INNER JOIN CampaignMessagesResponse cmsRes "
                    + "ON "
                    + "( "
                    + "    campaignMessageRequest.Campaign_Id = cmsRes.CampaignId "
                    + "AND campaignMessageRequest.Folder_Id = cmsRes.FolderId "
                    + "AND campaignMessageRequest.Message_Type_Id = cmsRes.MessageTypeId "
                    + ") "
                    + "INNER JOIN Intervals ils  "
                    + "ON "
                    + "( "
                    + "cmsRes.RepeatIntervalId = ils.IntervalId "
                    + "AND cmsRes.RepeatIntervalTypeId = ils.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType ilsType "
                    + "ON cmsRes.RepeatIntervalTypeId = ilsType.IntervalsTypeId "
                    + "INNER JOIN (SELECT "
                    + "MAX(cmr.Effective_Date)                       Effective_Date, "
                    + "cr.Phone_Number, "
                    + "cmr.Campaign_Id "
                    + "FROM CustomerRequest cr "
                    + " INNER JOIN CampaignMessageRequest cmr "
                    + "   ON cr.CR_Seq_No = cmr.CR_Seq_No "
                    + "GROUP BY cr.Phone_Number,cmr.Campaign_Id) drive_distinct "
                    + "ON (drive_distinct.Phone_Number = customerRequest.Phone_Number "
                    + "  AND (drive_distinct.Effective_Date = campaignMessageRequest.Effective_Date) "
                    + "AND drive_distinct.Campaign_Id = campaignMessageRequest.Campaign_Id) "
                    + "WHERE "
                    + "    customerRequest.Status_Code = 'CM' "
                    + "AND cmsRes.RepeatIntervalId > 0 "
                    + "AND cmsRes.RepeatIntervalTypeId > 0 "
                    + "AND cmsRes.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) >= ilsType.UnitInSecond * ils.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) < (ilsType.UnitInSecond * ils.IntervalValue) + (30 * 60 ) "
                    + "UNION ALL "
                    + "SELECT "
                    + "campaignMessageRequest.MMS_CMR_Seq_No, "
                    + "campaignMessageRequest.CR_Seq_No, "
                    + "campaignMessageRequest.Campaign_Name, "
                    + "campaignMessageRequest.Campaign_Id, "
                    + "campaignMessageRequest.Folder_Id, "
                    + "campaignMessageRequest.Message_Type_Id, "
                    + "campaignMessageRequest.Short_Code, "
                    + "campaignMessageRequest.Input_Reference_No, "
                    + "customerRequest.Phone_Number, "
                    + "customerRequest.Card_Number, "
                    + "(ilsType.UnitInSecond * ils.IntervalValue) AS Seconds_Passed, "
                    + "customerRequest.Communication_Path, "
                    + "ils.IntervalId, "
                    + "ils.IntervalValue, "
                    + "ilsType.IntervalsTypeTitle "
                    + "FROM CustomerRequest customerRequest "
                    + "INNER JOIN MMSCampaignMessageReqRes campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON campaign.CampaignId = customerRequest.Campaign_Id "
                    + "INNER JOIN CampaignMessagesResponse cmsRes "
                    + "ON "
                    + "( "
                    + "   campaignMessageRequest.Campaign_Id = cmsRes.CampaignId "
                    + "AND campaignMessageRequest.Folder_Id = cmsRes.FolderId "
                    + "AND campaignMessageRequest.Message_Type_Id = cmsRes.MessageTypeId "
                    + ") "
                    + "INNER JOIN Intervals ils  "
                    + "ON "
                    + "( "
                    + "cmsRes.RepeatIntervalId = ils.IntervalId "
                    + "AND cmsRes.RepeatIntervalTypeId = ils.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType ilsType "
                    + "ON cmsRes.RepeatIntervalTypeId = ilsType.IntervalsTypeId "
                    + "INNER JOIN (SELECT "
                    + "MAX(cmr.Effective_Date)                       Effective_Date, "
                    + "cr.Phone_Number, "
                    + "cmr.Campaign_Id "
                    + "FROM CustomerRequest cr "
                    + " INNER JOIN CampaignMessageRequest cmr "
                    + "   ON cr.CR_Seq_No = cmr.CR_Seq_No "
                    + "GROUP BY cr.Phone_Number,cmr.Campaign_Id) drive_distinct "
                    + "ON (drive_distinct.Phone_Number = customerRequest.Phone_Number "
                    + "  AND (drive_distinct.Effective_Date = campaignMessageRequest.Effective_Date) "
                    + "AND drive_distinct.Campaign_Id = campaignMessageRequest.Campaign_Id) "
                    + "WHERE "
                    + " customerRequest.Status_Code = 'CM' "
                    + "AND cmsRes.RepeatIntervalId > 0 "
                    + "AND cmsRes.RepeatIntervalTypeId > 0 "
                    + "AND cmsRes.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) >= ilsType.UnitInSecond * ils.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,campaignMessageRequest.Effective_Date,NOW()) < (ilsType.UnitInSecond * ils.IntervalValue) + (30 * 60 )";

            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            logger.error("Result List Size : " + queryResult.size());

            ReminderPOJO reminderPOJO = null;

            long crSeqNO = 0;
            long cmrSeqNo = 0;
            String campaignName = null;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            int shortCode = 0;
            long inputReferenceNumber = 0;
            String phoneNumber = "";
            String cardNumber = "";
            long seconds = 0;
            String communicationPath = "";
            int intervalId = 0;
            String intervalValue = "";
            String intervalValueTitle = "";

            Object value = null;

            for (Object[] record : queryResult) {
                reminderPOJO = new ReminderPOJO();

                value = record[0];
                if (value != null) {
                    cmrSeqNo = Long.valueOf(value.toString());
                    reminderPOJO.setCmrSeqNo(cmrSeqNo);
                }

                value = record[1];
                if (value != null) {
                    crSeqNO = Long.valueOf(value.toString());
                    reminderPOJO.setCrSeqNo(crSeqNO);
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
                    shortCode = Integer.valueOf(value.toString());
                    reminderPOJO.setShortCode(shortCode);
                }

                value = record[7];
                if (value != null) {
                    inputReferenceNumber = Long.valueOf(value.toString());
                    reminderPOJO.setInputReferenceNumber(inputReferenceNumber);
                }

                value = record[8];
                if (value != null) {
                    phoneNumber = value.toString();
                    reminderPOJO.setPhoneNumber(phoneNumber);
                }

                value = record[9];
                if (value != null) {
                    cardNumber = value.toString();
                    reminderPOJO.setCardNumber(cardNumber);
                }

                value = record[10];

                if (value != null) {
                    seconds = Long.valueOf(value.toString().trim());
                    reminderPOJO.setSeconds(seconds);
                }

                value = record[11];

                if (value != null) {
                    communicationPath = value.toString();
                    reminderPOJO.setCommunicationPath(communicationPath);
                }

                value = record[12];
                if (value != null) {
                    intervalId = Integer.parseInt(value.toString());
                    reminderPOJO.setIntervalId(intervalId);
                }

                value = record[13];
                if (value != null) {
                    intervalValue = value.toString();
                }

                value = record[14];
                if (value != null) {
                    intervalValueTitle = value.toString();
                }

                reminderPOJO.setIntervalValue(intervalValue + " " + intervalValueTitle);

                list.add(reminderPOJO);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getCouponRemindeRecords", e);
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
    public int sentMessageCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid, String isRepeat) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Message_Count FROM CustomerRequest customerRequest "
                    + "INNER JOIN CampaignMessageRequest campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN CampaignMessageReqRes campaignMessageReqRes "
                    + "ON campaignMessageRequest.CMR_Seq_No = campaignMessageReqRes.CMR_Seq_No "
                    + "INNER JOIN GatewayCode gatewayCode "
                    + "ON campaignMessageReqRes.Error_Code = gatewayCode.Error_Code "
                    + "WHERE "
                    + "    customerRequest.Phone_Number = ? "
                    + "AND campaignMessageRequest.Campaign_Id = ? "
                    + "AND campaignMessageRequest.Folder_Id = ? "
                    + "AND campaignMessageRequest.Message_Type_Id = ? "
                    + "AND gatewayCode.Code_Type = 'Success' "
                    + "AND campaignMessageRequest.isRepeat = ?"
                    + "AND TIMESTAMPDIFF(MINUTE,campaignMessageRequest.Effective_Date, NOW()) <= (24 * 60) + 30 ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, phoneNumber);
            query.setInteger(1, campaignId);
            query.setInteger(2, folderId);
            query.setInteger(3, messageTypeid);
            query.setString(4, isRepeat);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("Sent message count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }

            logger.error("Phone Number : " + phoneNumber);
            logger.error("Campaign Id : " + campaignId);
            logger.error("Folder Id : " + folderId);
            logger.error("Message Type Id : " + messageTypeid);
            logger.error("Sent Message Count : " + count);

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> sentMessageCountCampaign", e);
        }

        return count;
    }

    public int sentMMSCountCampaign(String phoneNumber, int campaignId, int folderId, int messageTypeid) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Message_Count FROM CustomerRequest customerRequest "
                    + "INNER JOIN MMSCampaignMessageReqRes campaignMessageRequest "
                    + "ON customerRequest.CR_Seq_No = campaignMessageRequest.CR_Seq_No "
                    + "INNER JOIN MMSCampaignMessageResponseParsedData campaignMessageReqRes "
                    + "ON campaignMessageRequest.MMS_CMR_Seq_No = campaignMessageReqRes.MMS_CMR_Seq_No "
                    + "WHERE "
                    + "    customerRequest.Phone_Number = ? "
                    + "AND campaignMessageRequest.Campaign_Id = ? "
                    + "AND campaignMessageRequest.Folder_Id = ? "
                    + "AND campaignMessageRequest.Message_Type_Id = ? "
                    + "AND campaignMessageReqRes.CMRSPD_StatusCode = '1000' "
                    + "AND TIMESTAMPDIFF(MINUTE,campaignMessageRequest.Effective_Date, NOW()) <= (24 * 60) ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, phoneNumber);
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

            logger.error("Phone Number : " + phoneNumber);
            logger.error("Campaign Id : " + campaignId);
            logger.error("Folder Id : " + folderId);
            logger.error("Message Type Id : " + messageTypeid);
            logger.error("Sent Message Count : " + count);

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> sentMMSCountCampaign", e);
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
    public GatewayInfo getGatewayInfo(int shortCode) {

        GatewayInfo gatewayInfo = null;

        try {
            String hql = "select gatewayInfo from GatewayInfo gatewayInfo "
                    + "where "
                    + "gatewayInfo.shortCode = :shortCode";

            List<GatewayInfo> list = (List<GatewayInfo>) this.getCurrentSession().createQuery(hql)
                    .setParameter("shortCode", shortCode)
                    .list();

            if (list != null && (!list.isEmpty())) {
                gatewayInfo = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getGatewayInfo", e);
        }

        return gatewayInfo;
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
    @SuppressWarnings("CallToThreadDumpStack")
    public int getIRFRedemptionCountByPhone(String phoneNumber, int campaignId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Instant_Record_Count FROM InstantRedemption irf "
                    + "WHERE "
                    + "    irf.CampaignId = ? "
                    + "AND irf.Communication_Id = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setString(1, phoneNumber);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("IRF redemption count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }

            logger.error("Campaign Id : " + campaignId);
            logger.error("Phone Number : " + phoneNumber);
            logger.error("Redemption Count : " + count);

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getIRFRedemptionCountByPhone", e);
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
    @SuppressWarnings("CallToThreadDumpStack")
    public long getIRFLastRedemptionSecondsByPhone(String phoneNumber, int campaignId) {

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
            query.setString(0, phoneNumber);
            query.setInteger(1, campaignId);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    seconds = Long.valueOf(object.toString());
                }
            }

            logger.error("Campaign Id : " + campaignId);
            logger.error("Phone Number : " + phoneNumber);
            logger.error("Seconds Passed : " + seconds);

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getIRFLastRedemptionSecondsByPhone", e);
        }

        return seconds;
    }

    public MessagePriority getMessagePriority(String phoneNumber, int shortCode) {

        MessagePriority messagePriority = null;

        try {

            String sql = "SELECT  "
                    + "drive_table.Seq_No, "
                    + "drive_table.Phone_Number, "
                    + "drive_table.Message_Context, "
                    + "drive_table.Message_Time_Stamp, "
                    + "drive_table.Path "
                    + "FROM  "
                    + "( "
                    + "SELECT  "
                    + "request.CR_Seq_No AS Seq_No, "
                    + "request.Phone_Number AS Phone_Number, "
                    + "'TextFlow' AS Message_Context, "
                    + "request.Last_Updated_On AS Message_Time_Stamp, "
                    + "'CM' AS Path "
                    + "FROM CustomerRequest request "
//                    + "INNER JOIN CommunicationSource comSource "
//                    + "ON request.Communication_Source_Code = comSource.Communication_Source_Code "
                    + "WHERE "
                    + "    request.Phone_Number = '" + phoneNumber + "' "
                    + "AND request.Status_Code = 'IP' "
                    + "AND request.Short_Code = " + shortCode + " "
//                    + "AND comSource.Source_Type = 'External' "
//                    + "UNION ALL  "
//                    + "SELECT  "
//                    + "request.Message_Req_No AS Seq_No, "
//                    + "request.Phone_Number AS Phone_Number, "
//                    + "request.Message_Context AS Message_Context, "
//                    + "request.Effective_Date AS Message_Time_Stamp, "
//                    + "'Text' AS Path "
//                    + "FROM AggregatorMessageRequest request "
//                    + "INNER JOIN AggregatorMessageResponse response "
//                    + "ON request.Message_Req_No = response.Message_Req_No "
//                    + "INNER JOIN GatewayCode gatewayCode "
//                    + "ON gatewayCode.Error_Code = response.Error_Code "
//                    + "INNER JOIN Campaigns campaign "
//                    + "ON request.Campaign_Id = campaign.CampaignId "
//                    + "WHERE "
//                    + "request.Phone_Number = '" + phoneNumber + "' "
//                    + "AND campaign.ShortCode = " + shortCode + " "
//                    + "AND gatewayCode.Code_Type = 'Success' "
//                    + "AND request.End_Date IS NULL  "
//                    + "UNION ALL "
//                    + "SELECT "
//                    + "request.MMS_CMR_Seq_No AS Seq_No, "
//                    + "request.Phone_Number AS Phone_Number, "
//                    + "request.MMS_Context AS Message_Context, "
//                    + "request.Effective_Date AS Message_Time_Stamp, "
//                    + "'MMS' AS Path "
//                    + "from MMSCampaignMessageReqRes request "
//                    + "INNER JOIN MMSCampaignMessageResponseParsedData response "
//                    + "ON request.MMS_CMR_Seq_No = response.MMS_CMR_Seq_No "
//                    + "INNER JOIN Campaigns campaign "
//                    + "ON request.Campaign_Id = campaign.CampaignId "
//                    + "WHERE "
//                    + "  request.Phone_Number = '" + phoneNumber + "' "
//                    + "AND campaign.ShortCode = '" + shortCode + "' "
                    + ")drive_table "
                    + "ORDER BY drive_table.Message_Time_Stamp DESC  "
                    + "LIMIT 0,1";

            logger.error("Priority SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();
            logger.info("Query Result messge periority"+queryResult.size());
            if (queryResult != null && (!queryResult.isEmpty())) {
                Object[] result = queryResult.get(0);

                if (result != null) {
                    messagePriority = new MessagePriority();

                    long messageSeqNo = 0;
                    String messageContext = "";
                    Date messageDate = null;

                    Object value = null;

                    value = result[0];

                    if (value != null) {
                        messageSeqNo = Long.valueOf(value.toString());
                        messagePriority.setMessageSeqNo(messageSeqNo);
                    }

                    messagePriority.setPhoneNumber(phoneNumber);

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
                        messagePriority.setPath(value.toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:PMSTextFlowDAO -> getMessagePriority", e);
        }
        return messagePriority;
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public String getURL(String urlCode) {
        Url url = null;
        String urlString = "";
        try {

            String hql = "SELECT url FROM Url url "
                    + "WHERE "
                    + "    url.code = :urlCode "
                    + "AND url.endDate IS NULL ";

            List<Url> list = (List<Url>) this.getCurrentSession().createQuery(hql)
                    .setParameter("urlCode", urlCode)
                    .list();

            if (list != null && (!list.isEmpty())) {
                url = new Url();
                url = list.get(0);
                urlString = url.getUrl();
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getURL", e);
        }

        return urlString;
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public GroupHasFolderHasCampaign getTextGroupbyFolderId(int folderId, int campaignId, String communicationPath) {

        GroupHasFolderHasCampaign groupHasFolderHasCampaign = null;

        try {

            String hql = "SELECT groupHasFolderHasCampaign FROM GroupHasFolderHasCampaign groupHasFolderHasCampaign "
                    + "WHERE "
                    + "    groupHasFolderHasCampaign.id.folderId = :folderId "
                    + " and groupHasFolderHasCampaign.id.campaignId = :campaignId "
                    + " and groupHasFolderHasCampaign.sourceType = :communicationPath ";

            List<GroupHasFolderHasCampaign> list = (List<GroupHasFolderHasCampaign>) this.getCurrentSession().createQuery(hql)
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && (!list.isEmpty())) {
                groupHasFolderHasCampaign = new GroupHasFolderHasCampaign();
                groupHasFolderHasCampaign = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getTextGroupbyFolderId", e);
        }

        return groupHasFolderHasCampaign;
    }

    public boolean isMmsExists(int campaignId, int folderId) {
        boolean flag = false;
        try {
            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "and campaignMessages.communicationPath = 'MMS'"
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            List<CampaignMessages> list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .list();

            if (list != null && !list.isEmpty()) {
                flag = true;
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> isMmsExists", e);
        }

        return flag;
    }

    public String getEventsDescription(int campaignId, int folderId, String communicationPath) {

        String events = "";

        try {

            String sql = "SELECT "
                    + "GROUP_CONCAT(event.EventTitle) AS eventTitle "
                    + "FROM EventHasFolderHasCampaigns ehfhc "
                    + "INNER JOIN Event event "
                    + "ON event.EventId = ehfhc.EventId "
                    + "WHERE "
                    + " ehfhc.CampaignId = ? "
                    + "AND ehfhc.FolderId = ? "
                    + "AND ehfhc.communicationPath = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setInteger(1, folderId);
            query.setString(2, communicationPath);

            List<Object[]> list = query.list();

            Object value = null;

            if (list != null && !list.isEmpty()) {
                value = list.get(0);
                events = value.toString();
            }

        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getEventsDescription", e);
        }

        return events;
    }

    public WidgetUser getWidgetUser(String userName, String userPassword) {
        WidgetUser widgetUser = null;
        try {
            String hql = "select widgetUser from WidgetUser widgetUser "
                    + "where "
                    + "    widgetUser.userName = :widgetUser "
                    + "and widgetUser.password = :userPassword "
                    + "and widgetUser.isActive = 'Yes'";

            List<WidgetUser> list = (List<WidgetUser>) this.getCurrentSession().createQuery(hql)
                    .setParameter("widgetUser", userName)
                    .setParameter("userPassword", userPassword)
                    .list();

            if (list != null && !list.isEmpty()) {
                widgetUser = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception:PMSTextFlowDAO -> getWidgetUser", e);
        }
        return widgetUser;
    }

    public boolean isIPAddressValid(Integer widgetUserId, String ipAddress) {
        boolean isIpValid = false;
        try {
            String hql = "select widgetUserIpaddresses from WidgetUserIpaddresses widgetUserIpaddresses "
                    + "where "
                    + "    widgetUserIpaddresses.widgetUser.widgetUserId = :widgetUserId "
                    + "and widgetUserIpaddresses.ipAddress = :ipAddress "
                    + "and widgetUserIpaddresses.widgetUser.isActive = 'Yes'";

            List<WidgetUserIpaddresses> list = (List<WidgetUserIpaddresses>) this.getCurrentSession().createQuery(hql)
                    .setParameter("widgetUserId", widgetUserId)
                    .setParameter("ipAddress", ipAddress)
                    .list();

            if (list != null && !list.isEmpty()) {
                isIpValid = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception:PMSTextFlowDAO -> isIPAddressValid", e);
        }
        return isIpValid;
    }

    public boolean isCommunicationIdRegistredWithDifferentMemeberId(String memberId, String phoneNumber) {

        logger.info("Start : PMSTextFlowDAO -> memeberExits");
        boolean flag = false;
        String hql = "select count(*) from CustomerRequest customerRequest where customerRequest.cardNumber != :memberId and customerRequest.phoneNumber = :phoneNumber";
        try {
            List<Long> list = this.getCurrentSession().createQuery(hql).setParameter("memberId", memberId).setParameter("phoneNumber", phoneNumber).list();

            if (list != null && !list.isEmpty() && list.get(0) > 0) {
                flag = true;
            }

        } catch (HibernateException e) {
            logger.error("Exception:memeberExits -> isCommunicationIdRegistredWithDifferentMemeberId", e);
        }

        logger.info("End : PMSTextFlowDAO -> isCommunicationIdRegistredWithDifferentMemeberId :: Flag:" + flag);

        return flag;
    }

    public boolean isMemberExists(String memberId, String firstName, String lastName, String dateOfBirth) {

        logger.info("Start : PMSTextFlowDAO -> memeberExits");
        boolean flag = false;
        String hql = "SELECT \n"
                + "  COUNT(*) as count \n"
                + "FROM\n"
                + "  CustomerRequest customerRequest \n"
                + "  INNER JOIN WidgetLog \n"
                + "    ON WidgetLog.Member_Id = customerRequest.card_Number \n"
                + "WHERE customerRequest.card_Number = '" + memberId + "' \n"
                + "  AND first_name = '" + firstName + "' \n"
                + "  AND last_name = '" + lastName + "' \n"
                + "  AND Date_Of_Birth = '" + dateOfBirth + "' "
                + "   AND Status_Code <> 'CL'";
        try {
            List<Long> list = this.getCurrentSession().createSQLQuery(hql).addScalar("count", LongType.INSTANCE).list();

            if (list != null && !list.isEmpty() && list.get(0) > 0) {
                flag = true;
            }

        } catch (HibernateException e) {
            logger.error("Exception:memeberExits -> memeberExits", e);
        }

        logger.info("End : PMSTextFlowDAO -> memeberExits :: Flag:" + flag);

        return flag;
    }

    public List<OptInOut> getOptedOutCampaignMemberRecords(Integer campaignId) throws Exception {
        Query query = getCurrentSession().createQuery("From OptInOut optInOut "
                + "where optInOut.campaignId=:campaignId "
                + "AND optInOut.optInOut='I' "
                + "AND optInOut.statusCode='CM'");
        query.setParameter("campaignId", campaignId);
        return query.list();
    }

    public List<CustomerRequest> getAllCustomerRequestIPOrCMByCampaignId(int campaignId) throws Exception {
        String hql = "select customerRequest from CustomerRequest customerRequest "
                + "where "
                + " (customerRequest.statusCode=:statusCodeIP OR customerRequest.statusCode=:statusCodeCM) "
                + "and customerRequest.campaignId=:campaignId "
                + "order by customerRequest.effectiveDate desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("statusCodeIP", StatusEnum.IN_PROGRESS.getValue());
        query.setParameter("statusCodeCM", StatusEnum.COMPLETED.getValue());
        query.setParameter("campaignId", campaignId);
        return query.list();
    }

    public boolean updatePatientInfo(String communicationId) throws Exception {
        Query query = getCurrentSession().createQuery("Update PatientProfile set lastUpdatedOn=:currentDate,status=:patientStatus where communicationID=:communicationId and status='Pending'");
        query.setParameter("currentDate", new Date());
        query.setParameter("patientStatus", Constants.OPTED_OUT);
        query.setParameter("communicationId", communicationId);

        query.executeUpdate();
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResComm(int campaignId, int folderId, String responseTitle) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch campaignMessagesResponse.response response "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "inner join fetch campaignMessagesResponse.campaignMessages campaignResponse "
                    + "where "
                    + " campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.response.responseTitle = :responseTitle "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("responseTitle", responseTitle)
                    .setParameter("folderId", folderId)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    public CampaignMessages getCampaignMessagesByMessageTypeId(Integer messageTypeId, Integer folderId) throws Exception {
        Query query = getCurrentSession().createQuery("From CampaignMessages campaignMessages where campaignMessages.messageType.id.messageTypeId=:messageTypeId and campaignMessages.messageType.id.folderId=:folderId ");
        query.setParameter("messageTypeId", messageTypeId);
        query.setParameter("folderId", folderId);
        return (CampaignMessages) query.uniqueResult();
    }
    
    public boolean updateEnrollmentIpadStatus(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Update enrollments set updated_at=:updated_at,enrollment_status=:enrollment_status where patient_id=:patientId");
        query.setParameter("updated_at", new Date());
        query.setParameter("enrollment_status","Enrolled");
        query.setParameter("patientId", patientId);

        query.executeUpdate();
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
