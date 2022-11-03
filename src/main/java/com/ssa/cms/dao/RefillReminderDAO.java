package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.EventDetail;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.RefillReminderPOJO;
import com.ssa.cms.model.RxMMSRedemptionReqRes;
//import com.ssa.cms.model.SurveyLogs;
import com.ssa.cms.model.Url;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.util.DBUtil;
import com.ssa.cms.util.RedemptionUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RefillReminderDAO extends BaseDAO implements Serializable {

    private final Log logger = LogFactory.getLog(getClass());
   

    public void save(Object bean) throws Exception {
        this.getCurrentSession().save(bean);
    }

    public void update(Object bean) throws Exception {
        this.getCurrentSession().update(bean);
    }

    public void delete(Object bean) throws Exception {
        this.getCurrentSession().delete(bean);
    }

    public List<Campaigns> getAllRefillCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND campaigns.isRefill='Yes' "
                    + "AND (campaigns.refillProcessTiming is not NULL "
                    + "AND campaigns.refillProcessTiming > 0) "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAllRefillCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllRepeatRefillCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND campaigns.isRepeatRefill='Yes' "
                    + "AND (campaigns.refillProcessTiming is not NULL "
                    + "AND campaigns.refillProcessTiming > 0) "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAllRepeatRefillCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<Campaigns> getAllRefillFailureCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND campaigns.isRefillFailure='Yes' "
                    + "AND (campaigns.refillProcessTiming is not NULL "
                    + "AND campaigns.refillProcessTiming > 0) "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAllRefillFailureCandidateActiveCampaign", e);
        }
        return list;
    }
    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */

    public List<Campaigns> getAllPACandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "LEFT OUTER JOIN FETCH campaigns.smtpServerInfo smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND campaigns.isPa='Yes' "
                    + "AND (campaigns.pADeniedRequestTime is not NULL "
                    + "AND campaigns.pADeniedRequestTime > 0) "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId ASC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAllPACandidateActiveCampaign", e);
        }
        return list;
    }

    public List<DailyRedemption> getDRFRefillReminderRecordsByCampaignId(int campaignId, int intervalValue) {

        List<DailyRedemption> list = new ArrayList<>();

        String sql = "SELECT "
                + "drive_drf.Fill_Date,  "
                + "drive_drf.Submitted_ID, "
                + "drive_drf.Rx_Group_Number, "
                + "drive_drf.Cardholder_DOB, "
                + "drive_drf.Cardholder_First_Name, "
                + "drive_drf.Cardholder_Last_Name, "
                + "drive_drf.NDC_Number,  "
                + "drive_drf.Claim_Status,  "
                + "drive_drf.File_Name,  "
                + "drive_drf.Days_Supply, "
                + "drive_drf.isValidPhone, "
                + "drive_drf.Communication_Id, "
                + "drive_drf.Communication_Method, "
                + "drive_drf.Pharmacy_Phone, "
                + "drive_drf.Pharmacy_Name, "
                + "drive_drf.Cardholder_Id, "
                + "drive_drf.Cardholder_Gender, "
                + "drive_drf.Redemption_Id, "
                + "drive_drf.File_Type_Code, "
                + "drive_drf.Transaction_Number, "
                + "drive_drf.Pt_Out_Of_Pocket "
                + "FROM   "
                + "(  "
                + "SELECT   "
                + "drf.Fill_Date       AS Fill_Date,  "
                + "drf.Submitted_ID    AS Submitted_ID,  "
                + "drf.Rx_Group_Number AS Rx_Group_Number,  "
                + "drf.Cardholder_DOB  AS Cardholder_DOB, "
                + "drf.Cardholder_First_Name AS Cardholder_First_Name, "
                + "drf.Cardholder_Last_Name AS Cardholder_Last_Name, "
                + "drf.Cardholder_Gender AS Cardholder_Gender,"
                + "drf.NDC_Number      AS NDC_Number,  "
                + "drf.Claim_Status    AS Claim_Status,  "
                + "drf.File_Name       AS File_Name,  "
                + "drf.File_Type_Code AS File_Type_Code, "
                + "drf.Days_Supply AS Days_Supply, "
                + "drf.isValidPhone AS isValidPhone, "
                + "drf.Communication_Id AS Communication_Id, "
                + "drf.Communication_Method AS Communication_Method, "
                + "drf.Pharmacy_Phone AS Pharmacy_Phone, "
                + "drf.Pharmacy_Name AS Pharmacy_Name, "
                + "drf.Cardholder_Id AS Cardholder_Id, "
                + "drf.Redemption_Id AS Redemption_Id, "
                + "drf.Transaction_Number AS Transaction_Number, "
                + "drf.Pt_Out_Of_Pocket AS Pt_Out_Of_Pocket "
                + "FROM DailyRedemption drf  "
                + "WHERE "
                + "drf.CampaignId = ? "
                + "GROUP BY  "
                + "drf.Transaction_Number  "
                + "HAVING COUNT(*) = 1  "
                + ") drive_drf  "
                + "WHERE  "
                + "drive_drf.Claim_Status = 0  "
                + "AND drive_drf.Communication_Id IS NOT NULL "
                + "AND TIMESTAMPDIFF(DAY, drive_drf.Fill_Date, NOW()) >= (drive_drf.Days_Supply * .?)  "
                + "AND TIMESTAMPDIFF(DAY, drive_drf.Fill_Date, NOW()) < (drive_drf.Days_Supply * .?) + 1";

        try {

            logger.error("Reminder SQL : " + sql);
            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setInteger(1, intervalValue);
            query.setInteger(2, intervalValue);

            List<Object[]> queryResult = query.list();

            Object value = null;
            Date fillDate = null;
            String submittedId = null;
            String rxGroupNumber = null;
            Date cardholderDOB = null;
            String cardholderFirstName = null;
            String cardholderLastName = null;
            char cardholderGender = 'F';
            String ndcNumber = null;
            int claimStatus = 0;
            String fileName = null;
            int daysSupply = 0;
            String isValidPhone = null;
            String communicationId = null;
            String communicationMethod = null;
            String pharmacyPhone = null;
            String pharmacyName = null;
            String cardholderId = null;
            int redemptionId = 0;
            String transactionNumber;
            String fileTypeCode = "";

            DailyRedemptionId drfId = null;
            DailyRedemption drf = null;

            for (Object[] result : queryResult) {

                drfId = new DailyRedemptionId();
                drf = new DailyRedemption();

                drf.setId(drfId);

                value = result[0];
                if (value != null) {
                    fillDate = (Date) value;
                    drf.setFillDate(fillDate);
                }

                value = result[1];
                if (value != null) {
                    submittedId = (String) value;
                    drf.setSubmittedId(submittedId);
                }

                value = result[2];
                if (value != null) {
                    rxGroupNumber = (String) value;
                    drf.setRxGroupNumber(rxGroupNumber);
                }

                value = result[3];
                if (value != null) {
                    cardholderDOB = (Date) value;
                    drf.setCardholderDob(cardholderDOB);
                }

                value = result[4];
                if (value != null) {
                    cardholderFirstName = (String) value;
                    drf.setCardholderFirstName(cardholderFirstName);
                }

                value = result[5];
                if (value != null) {
                    cardholderLastName = (String) value;
                    drf.setCardholderLastName(cardholderLastName);
                }

                value = result[6];
                if (value != null) {
                    ndcNumber = (String) value;
                    drf.setNdcNumber(ndcNumber);
                }

                value = result[7];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    drf.getId().setClaimStatus(claimStatus);
                }

                value = result[8];
                if (value != null) {
                    fileName = (String) value;
                    drf.setFileName(fileName);
                }

                value = result[9];
                if (value != null) {
                    daysSupply = Integer.valueOf(value.toString());
                    drf.setDaysSupply(daysSupply);
                }

                value = result[10];
                if (value != null) {
                    isValidPhone = (String) value;
                    drf.setIsValidPhone(isValidPhone);
                }

                value = result[11];
                if (value != null) {
                    communicationId = (String) value;
                    drf.setCommunicationId(communicationId);
                }

                value = result[12];
                if (value != null) {
                    communicationMethod = (String) value;
                    drf.setCommunicationMethod(communicationMethod);
                }

                value = result[13];
                if (value != null) {
                    pharmacyPhone = (String) value;
                    drf.setPharmacyPhone(pharmacyPhone);
                }

                value = result[14];
                if (value != null) {
                    pharmacyName = (String) value;
                    drf.setPharmacyName(pharmacyName);
                }

                value = result[15];
                if (value != null) {
                    cardholderId = (String) value;
                    drf.setCardholderId(cardholderId);
                }

                value = result[16];
                if (value != null) {
                    cardholderGender = (Character) value;
                    drf.setCardholderGender(cardholderGender);
                }

                value = result[17];
                if (value != null) {
                    redemptionId = Integer.valueOf(value.toString());
                    drf.setRedemptionId(redemptionId);
                }

                value = result[18];
                if (value != null) {
                    fileTypeCode = value.toString();
                    drf.setFileTypeCode(fileTypeCode);
                }

                value = result[19];
                if (value != null) {
                    transactionNumber = value.toString();
                    drf.getId().setTransactionNumber(transactionNumber);
                }
                value = result[20];
                if (value != null) {
                    drf.setPtOutOfPocket(new BigDecimal(value.toString()));
                }
                list.add(drf);
            } // for(Object[] result: queryResult)

        } catch (HibernateException | NumberFormatException e) {
            logger.error("Exception: RefillReminderDAO -> getDRFRefillReminderRecordsByCampaignId", e);
        }

        return list;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId) {

        List<EventHasFolderHasCampaigns> list = new ArrayList<>();

        String hql = "select eventHasFolderHasCampaigns from EventHasFolderHasCampaigns eventHasFolderHasCampaigns,Event event "
                + " where  "
                + " eventHasFolderHasCampaigns.campaignId = :campaignId "
                + " and eventHasFolderHasCampaigns.folderId != 1 "
                + " and (event.eventId=eventHasFolderHasCampaigns.eventId) "
                + " and  event.dynamicValue = 'Refill' "
                + "ORDER BY eventHasFolderHasCampaigns.folderId desc";

        try {
            list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getEventHasFolderHasCampaign", e);
        }
        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId, String dynamicValue) {

        List<EventHasFolderHasCampaigns> list = new ArrayList<>();

        String hql = "select eventHasFolderHasCampaigns from EventHasFolderHasCampaigns eventHasFolderHasCampaigns,Event event "
                + " where  "
                + " eventHasFolderHasCampaigns.campaignId = :campaignId "
                + " and eventHasFolderHasCampaigns.folderId != 1 "
                + " and (event.eventId=eventHasFolderHasCampaigns.eventId) "
                + " and  event.dynamicValue =:dynamicValue "
                + "ORDER BY eventHasFolderHasCampaigns.folderId desc";

        try {
            list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("dynamicValue", dynamicValue)
                    .list();
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getEventHasFolderHasCampaign", e);
        }
        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
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
            logger.error("Exception: RefillReminderDAO -> getCampaignMessages", e);
        }

        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessagesByResponseTitle(int campaignId, int folderId) {

        List<CampaignMessages> list = null;

        try {

            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId = :folderId "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", folderId)
                    .setParameter("folderId", folderId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesByResponseTitle", e);
        }

        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
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
                    .setParameter("campaignId", folderId)
                    .setParameter("folderId", folderId)
                    .setParameter("messageTypeId", messageTypeId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessages", e);
        }

        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public CustomerRequest getCustomerRequestIPorCM(String phoneNumber) {

        CustomerRequest customerRequest = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest "
                    + "where "
                    + "customerRequest.phoneNumber = :phoneNumber "
                    + "order by customerRequest.effectiveDate desc ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNumber", phoneNumber)
                    .list();

            if (list != null && list.size() > 0) {
                customerRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCustomerRequestIPorCM", e);
        }

        return customerRequest;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public OptInOut checkOptInOut(String phoneNumber, int campaignId, int shortCode) {

        OptInOut optInOut = null;
        try {
            String hql = "SELECT optio FROM OptInOut optio "
                    + "WHERE "
                    + "    optio.phoneNumber = :phoneNumber "
                    + "AND optio.campaignId = :campaignId "
                    + "AND optio.shortCode = :shortCode "
                    + "ORDER BY optio.effectiveDate DESC ";

            List<OptInOut> list = (List<OptInOut>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("campaignId", campaignId)
                    .setParameter("shortCode", shortCode)
                    .list();

            if (list != null && list.size() > 0) {
                optInOut = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> checkOptInOut", e);
        }

        return optInOut;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
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
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("messageTypeId", messageTypeId)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesResponse", e);
        }

        return campaignMessagesResponse;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public CampaignMessagesResponse getCampaignMessagesResponse(int campaignId, int folderId, String communicationPath) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.communicationPath = :communicationPath";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesResponse", e);
        }

        return campaignMessagesResponse;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
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
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("messageTypeId", messageTypeId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesResponsebyCommunicationPath", e);
        }

        return campaignMessagesResponse;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
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
            logger.error("Exception: RefillReminderDAO -> getOptInOut", e);
        }

        return optInOut;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public boolean redemptionExistsInIRF(DailyRedemption drf, int daysBack, int campaignId) {
        boolean redemptionExists = false;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS Redemption_Count FROM "
                    + "( "
                    + "SELECT irf.Claim_Status,irf.Fill_Date FROM InstantRedemption irf "
                    + "WHERE "
                    + "irf.Cardholder_DOB = ? "
                    + "AND irf.Cardholder_First_Name = ? "
                    + "AND irf.Cardholder_Last_Name = ? "
                    + "AND irf.Cardholder_Gender = ? "
                    + "AND irf.CampaignId = ? "
                    + "GROUP BY irf.Transaction_Number "
                    + "HAVING COUNT(*) = 1 "
                    + "ORDER BY irf.Posting_Date DESC "
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Fill_Date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL ? DAY),'%Y-%m-%d') "
                    + "LIMIT 0,1";

            logger.error("SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setDate(0, drf.getCardholderDob());
            query.setString(1, drf.getCardholderFirstName());
            query.setString(2, drf.getCardholderLastName());
            query.setCharacter(3, drf.getCardholderGender());
            query.setInteger(4, campaignId);
            query.setInteger(5, daysBack);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("Sent message count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                    if (count > 0) {
                        redemptionExists = true;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> redemptionExistsInIRF", e);
        }
        return redemptionExists;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<RefillReminderPOJO> getTextRepeatRefillRecords(int campId) {

        List<RefillReminderPOJO> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ( SELECT  "
                    + "aggregatorMessageRequest.Message_Req_No, "
                    + "aggregatorMessageRequest.Campaign_Id, "
                    + "aggregatorMessageRequest.FolderId, "
                    + "aggregatorMessageRequest.MessageTypeId, "
                    + "aggregatorMessageRequest.Cardholder_DOB, "
                    + "aggregatorMessageRequest.Claim_Status, "
                    + "aggregatorMessageRequest.File_Name, "
                    + "aggregatorMessageRequest.File_Type_Code, "
                    + "aggregatorMessageRequest.Fill_Date, "
                    + "aggregatorMessageRequest.NDC_Number, "
                    + "aggregatorMessageRequest.Phone_Number, "
                    + "aggregatorMessageRequest.Redemption_Id, "
                    + "aggregatorMessageRequest.Rx_Group_Number, "
                    + "(intervals.IntervalValue * intervalsType.UnitInSecond) / (60 * 60 *24) AS Reminder_Dats, "
                    + "dailyRedemption.Cardholder_First_Name, "
                    + "dailyRedemption.Cardholder_Last_Name, "
                    + "dailyRedemption.Cardholder_Gender, "
                    + "dailyRedemption.Pharmacy_Name, "
                    + "dailyRedemption.Pharmacy_Phone, "
                    + "dailyRedemption.Redemption_Channel_Id, "
                    + " aggregatorMessageRequest.Communication_Path, "
                    + " dailyRedemption.isValidPhone, "
                    + "intervals.IntervalId "
                    + "FROM AggregatorMessageRequest aggregatorMessageRequest "
                    + "INNER JOIN AggregatorMessageResponse aggregatorMessageResponse "
                    + "ON aggregatorMessageRequest.Message_Req_No = aggregatorMessageResponse.Message_Req_No "
                    + "INNER JOIN GatewayCode gatewayCode "
                    + "ON aggregatorMessageResponse.Error_Code = gatewayCode.Error_Code "
                    + "INNER JOIN DailyRedemption dailyRedemption "
                    + "ON "
                    + "aggregatorMessageRequest.Redemption_Id = dailyRedemption.Redemption_Id "
                    + "INNER JOIN CampaignMessagesResponse campaignMessagesResponse "
                    + "ON "
                    + "( "
                    + "    aggregatorMessageRequest.Campaign_Id = campaignMessagesResponse.CampaignId "
                    + "AND aggregatorMessageRequest.FolderId = campaignMessagesResponse.FolderId "
                    + "AND aggregatorMessageRequest.MessageTypeId = campaignMessagesResponse.MessageTypeId "
                    + "AND aggregatorMessageRequest.Communication_Path = campaignMessagesResponse.Communication_Path "
                    + ") "
                    + "INNER JOIN Intervals intervals  "
                    + "ON  "
                    + "( "
                    + "campaignMessagesResponse.RepeatIntervalId = intervals.IntervalId "
                    + "AND campaignMessagesResponse.RepeatIntervalTypeId = intervals.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType intervalsType "
                    + "ON  "
                    + "intervals.IntervalsTypeId = intervalsType.IntervalsTypeId "
                    + "WHERE "
                    + "aggregatorMessageRequest.End_Date IS NULL "
                    + "AND gatewayCode.Code_Type = 'Success' "
                    + "AND aggregatorMessageRequest.Campaign_Id = '" + campId + "' "
                    + "AND aggregatorMessageRequest.Message_Context = '" + Constants.MSG_CONTEXT_REFILL + "' "
                    + "AND campaignMessagesResponse.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,aggregatorMessageRequest.Effective_Date,NOW()) >= intervalsType.UnitInSecond * intervals.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,aggregatorMessageRequest.Effective_Date,NOW()) < ((intervalsType.UnitInSecond * intervals.IntervalValue) + 84600 ) "
                    + " ";

            sql += " UNION ALL SELECT  "
                    + "                   ivrRequestResponse.IVR_Req_No AS Message_Req_No, "
                    + "                   ivrRequestResponse.Campaign_Id, "
                    + "                   ivrRequestResponse.FolderId, "
                    + "                   ivrRequestResponse.MessageTypeId, "
                    + "                   ivrRequestResponse.Cardholder_DOB, "
                    + "                   ivrRequestResponse.Claim_Status, "
                    + "                   ivrRequestResponse.File_Name, "
                    + "                   ivrRequestResponse.File_Type_Code, "
                    + "                   ivrRequestResponse.Fill_Date, "
                    + "                   ivrRequestResponse.NDC_Number, "
                    + "                   ivrRequestResponse.Phone_Number, "
                    + "                   ivrRequestResponse.Redemption_Id, "
                    + "                   ivrRequestResponse.Rx_Group_Number, "
                    + "                   (intervals.IntervalValue * intervalsType.UnitInSecond) / (60 * 60 *24) AS Reminder_Dats, "
                    + "                   dailyRedemption.Cardholder_First_Name, "
                    + "                   dailyRedemption.Cardholder_Last_Name, "
                    + "                   dailyRedemption.Cardholder_Gender, "
                    + "                   dailyRedemption.Pharmacy_Name, "
                    + "                   dailyRedemption.Pharmacy_Phone, "
                    + "                   dailyRedemption.Redemption_Channel_Id, "
                    + "                   '" + Constants.OIVR + "'  AS Communication_Path, "
                    + "                    dailyRedemption.isValidPhone, "
                    + "intervals.IntervalId "
                    + "                   FROM IVRRequestResponse ivrRequestResponse "
                    + "                  "
                    + "                   INNER JOIN GatewayCode gatewayCode "
                    + "                   ON ivrRequestResponse.Error_Code = gatewayCode.Error_Code "
                    + "                   INNER JOIN DailyRedemption dailyRedemption "
                    + "                   ON "
                    + "                   ivrRequestResponse.Redemption_Id = dailyRedemption.Redemption_Id "
                    + "                   INNER JOIN CampaignMessagesResponse campaignMessagesResponse "
                    + "                   ON "
                    + "                   ( "
                    + "                       ivrRequestResponse.Campaign_Id = campaignMessagesResponse.CampaignId "
                    + "                   AND ivrRequestResponse.FolderId = campaignMessagesResponse.FolderId "
                    + "                   AND ivrRequestResponse.MessageTypeId = campaignMessagesResponse.MessageTypeId "
                    + "                   ) "
                    + "                   INNER JOIN Intervals intervals  "
                    + "                   ON  "
                    + "                   ( "
                    + "                   campaignMessagesResponse.RepeatIntervalId = intervals.IntervalId "
                    + "                   AND campaignMessagesResponse.RepeatIntervalTypeId = intervals.IntervalsTypeId "
                    + "                   ) "
                    + "                   INNER JOIN IntervalsType intervalsType "
                    + "                   ON  "
                    + "                   intervals.IntervalsTypeId = intervalsType.IntervalsTypeId "
                    + "                   WHERE "
                    + "                   ivrRequestResponse.End_Date IS NULL "
                    + "                   AND gatewayCode.Code_Type = 'Success' "
                    + "                   AND ivrRequestResponse.Campaign_Id = '" + campId + "' "
                    + "                   AND ivrRequestResponse.Message_Context = '" + Constants.MSG_CONTEXT_REFILL + "' "
                    + "                   AND campaignMessagesResponse.RepeatMessageId > 0 "
                    + "                   AND TIMESTAMPDIFF(SECOND,ivrRequestResponse.Effective_Date,NOW()) >= intervalsType.UnitInSecond * intervals.IntervalValue "
                    + "                   AND TIMESTAMPDIFF(SECOND,ivrRequestResponse.Effective_Date,NOW()) < ((intervalsType.UnitInSecond * intervals.IntervalValue) + 84600 ) "
                    + "                   ) drive_table ORDER BY drive_table.Message_Req_No";

            logger.error("Repeat refill query : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            List<Object[]> queryResult = query.list();

            int messageSeqNo = 0;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            Date cardholderDOB = null;
            int claimStatus = 0;
            String fileName = null;
            String fileTypeCode = null;
            Date fillDate = null;
            String ndcNumber = null;
            String phoneNumber = null;
            int redemptionId = 0;
            String rxGroupNumber = null;
            int reminderDays = 0;
            String cardholderFirstName = null;
            String cardholderLastName = null;
            char cardholderGender = 'U';
            String pharmacyName = null;
            String pharmacyPhone = null;
            int redemptionChannelId = 0;
            String communicationPath = "";
            String isValidPhone = "";
            Object value = null;
            RefillReminderPOJO reminderPOJO;
            int intervalId = 0;

            for (Object[] record : queryResult) {
                reminderPOJO = new RefillReminderPOJO();

                value = record[0];
                if (value != null) {
                    messageSeqNo = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageReqNo(messageSeqNo);
                }

                value = record[1];
                if (value != null) {
                    campaignId = Integer.valueOf(value.toString());
                    reminderPOJO.setCampaignId(campaignId);
                }

                value = record[2];
                if (value != null) {
                    folderId = Integer.valueOf(value.toString());
                    reminderPOJO.setFolderId(folderId);
                }

                value = record[3];
                if (value != null) {
                    messageTypeId = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageTypeId(messageTypeId);
                }

                value = record[4];
                if (value != null) {
                    cardholderDOB = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setCardholderDob(cardholderDOB);
                }

                value = record[5];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    reminderPOJO.setClaimStatus(claimStatus);
                }

                value = record[6];
                if (value != null) {
                    fileName = value.toString();
                    reminderPOJO.setFileName(fileName);
                }

                value = record[7];
                if (value != null) {
                    fileTypeCode = value.toString();
                    reminderPOJO.setFileTypeCode(fileTypeCode);
                }

                value = record[8];
                if (value != null) {
                    fillDate = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setFillDate(fillDate);
                }

                value = record[9];
                if (value != null) {
                    ndcNumber = value.toString();
                    reminderPOJO.setNdcNumber(ndcNumber);
                }

                value = record[10];
                if (value != null) {
                    phoneNumber = value.toString();
                    reminderPOJO.setPhoneNumber(phoneNumber);
                }

                value = record[11];
                if (value != null) {
                    redemptionId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionId(redemptionId);
                }

                value = record[12];
                if (value != null) {
                    rxGroupNumber = value.toString();
                    reminderPOJO.setRxGroupNumber(rxGroupNumber);
                }

                value = record[13];
                if (value != null) {
                    reminderDays = Double.valueOf(value.toString()).intValue();
                    reminderPOJO.setReminderDays(reminderDays);
                }

                value = record[14];
                if (value != null) {
                    cardholderFirstName = value.toString();
                    reminderPOJO.setCardholderFirstName(cardholderFirstName);
                }

                value = record[15];
                if (value != null) {
                    cardholderLastName = value.toString();
                    reminderPOJO.setCardholderLastName(cardholderLastName);
                }

                value = record[16];
                if (value != null) {
                    cardholderGender = (Character) value;
                    reminderPOJO.setCardholderGender(cardholderGender);
                }

                value = record[17];
                if (value != null) {
                    pharmacyName = value.toString();
                    reminderPOJO.setPharmacyName(pharmacyName);
                }

                value = record[18];
                if (value != null) {
                    pharmacyPhone = value.toString();
                    reminderPOJO.setPharmacyPhone(pharmacyPhone);
                }
                value = record[19];
                if (value != null) {
                    redemptionChannelId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionChannelId(redemptionChannelId);
                }
                value = record[20];
                if (value != null) {
                    communicationPath = value.toString();
                    reminderPOJO.setCommunicationPath(communicationPath);
                }
                value = record[21];
                if (value != null) {
                    isValidPhone = value.toString();
                    reminderPOJO.setIsValidPhone(isValidPhone);
                }

                value = record[22];
                if (value != null) {
                    intervalId = Integer.parseInt(value.toString());
                    reminderPOJO.setIntervalId(intervalId);
                }

                list.add(reminderPOJO);

            } // for (Object[] record : queryResult)

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getTextRepeatRefillRecords", e);
        }

        return list;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<RefillReminderPOJO> getTextRefill2Records(int campId) {

        List<RefillReminderPOJO> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ( SELECT  "
                    + "aggregatorMessageRequest.Message_Req_No, "
                    + "aggregatorMessageRequest.Campaign_Id, "
                    + "aggregatorMessageRequest.FolderId, "
                    + "aggregatorMessageRequest.MessageTypeId, "
                    + "aggregatorMessageRequest.Cardholder_DOB, "
                    + "aggregatorMessageRequest.Claim_Status, "
                    + "aggregatorMessageRequest.File_Name, "
                    + "aggregatorMessageRequest.File_Type_Code, "
                    + "aggregatorMessageRequest.Fill_Date, "
                    + "aggregatorMessageRequest.NDC_Number, "
                    + "aggregatorMessageRequest.Phone_Number, "
                    + "aggregatorMessageRequest.Redemption_Id, "
                    + "aggregatorMessageRequest.Rx_Group_Number, "
                    + "dailyRedemption.Cardholder_First_Name, "
                    + "dailyRedemption.Cardholder_Last_Name, "
                    + "dailyRedemption.Cardholder_Gender, "
                    + "dailyRedemption.Pharmacy_Name, "
                    + "dailyRedemption.Pharmacy_Phone, "
                    + "dailyRedemption.Redemption_Channel_Id, "
                    + " aggregatorMessageRequest.Communication_Path, "
                    + " dailyRedemption.isValidPhone, "
                    + "dailyRedemption.Communication_Method "
                    + "FROM AggregatorMessageRequest aggregatorMessageRequest "
                    + "INNER JOIN AggregatorMessageResponse aggregatorMessageResponse "
                    + "ON aggregatorMessageRequest.Message_Req_No = aggregatorMessageResponse.Message_Req_No "
                    + "INNER JOIN GatewayCode gatewayCode "
                    + "ON aggregatorMessageResponse.Error_Code = gatewayCode.Error_Code "
                    + "INNER JOIN DailyRedemption dailyRedemption "
                    + "ON "
                    + "aggregatorMessageRequest.Redemption_Id = dailyRedemption.Redemption_Id "
                    + "INNER JOIN CampaignMessagesResponse campaignMessagesResponse "
                    + "ON "
                    + "( "
                    + "    aggregatorMessageRequest.Campaign_Id = campaignMessagesResponse.CampaignId "
                    + "AND aggregatorMessageRequest.FolderId = campaignMessagesResponse.FolderId "
                    + "AND aggregatorMessageRequest.MessageTypeId = campaignMessagesResponse.MessageTypeId "
                    + "AND aggregatorMessageRequest.Communication_Path = campaignMessagesResponse.Communication_Path "
                    + ") "
                    + "WHERE "
                    + "aggregatorMessageRequest.End_Date IS NULL "
                    + "AND gatewayCode.Code_Type = 'Success' "
                    + "AND aggregatorMessageRequest.Campaign_Id = '" + campId + "' "
                    + "AND aggregatorMessageRequest.Message_Context = '" + Constants.MSG_CONTEXT_REFILL_CONFIRMATION + "' "
                    + "AND TIMESTAMPDIFF(SECOND,aggregatorMessageRequest.Effective_Date,NOW()) >= 10800 "
                    + "AND TIMESTAMPDIFF(SECOND,aggregatorMessageRequest.Effective_Date,NOW()) < 10800 + 1800  "
                    + " ) drive_table ORDER BY drive_table.Message_Req_No";

            logger.error("Repeat refill query : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            List<Object[]> queryResult = query.list();

            int messageSeqNo = 0;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            Date cardholderDOB = null;
            int claimStatus = 0;
            String fileName = null;
            String fileTypeCode = null;
            Date fillDate = null;
            String ndcNumber = null;
            String phoneNumber = null;
            int redemptionId = 0;
            String rxGroupNumber = null;
            int reminderDays = 0;
            String cardholderFirstName = null;
            String cardholderLastName = null;
            char cardholderGender = 'U';
            String pharmacyName = null;
            String pharmacyPhone = null;
            int redemptionChannelId = 0;
            String communicationPath = "";
            String isValidPhone = "";
            String communicationMethod = "";
            Object value = null;
            RefillReminderPOJO reminderPOJO;

            for (Object[] record : queryResult) {
                reminderPOJO = new RefillReminderPOJO();

                value = record[0];
                if (value != null) {
                    messageSeqNo = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageReqNo(messageSeqNo);
                }

                value = record[1];
                if (value != null) {
                    campaignId = Integer.valueOf(value.toString());
                    reminderPOJO.setCampaignId(campaignId);
                }

                value = record[2];
                if (value != null) {
                    folderId = Integer.valueOf(value.toString());
                    reminderPOJO.setFolderId(folderId);
                }

                value = record[3];
                if (value != null) {
                    messageTypeId = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageTypeId(messageTypeId);
                }

                value = record[4];
                if (value != null) {
                    cardholderDOB = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setCardholderDob(cardholderDOB);
                }

                value = record[5];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    reminderPOJO.setClaimStatus(claimStatus);
                }

                value = record[6];
                if (value != null) {
                    fileName = value.toString();
                    reminderPOJO.setFileName(fileName);
                }

                value = record[7];
                if (value != null) {
                    fileTypeCode = value.toString();
                    reminderPOJO.setFileTypeCode(fileTypeCode);
                }

                value = record[8];
                if (value != null) {
                    fillDate = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setFillDate(fillDate);
                }

                value = record[9];
                if (value != null) {
                    ndcNumber = value.toString();
                    reminderPOJO.setNdcNumber(ndcNumber);
                }

                value = record[10];
                if (value != null) {
                    phoneNumber = value.toString();
                    reminderPOJO.setPhoneNumber(phoneNumber);
                }

                value = record[11];
                if (value != null) {
                    redemptionId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionId(redemptionId);
                }

                value = record[12];
                if (value != null) {
                    rxGroupNumber = value.toString();
                    reminderPOJO.setRxGroupNumber(rxGroupNumber);
                }

                value = record[13];
                if (value != null) {
                    cardholderFirstName = value.toString();
                    reminderPOJO.setCardholderFirstName(cardholderFirstName);
                }

                value = record[14];
                if (value != null) {
                    cardholderLastName = value.toString();
                    reminderPOJO.setCardholderLastName(cardholderLastName);
                }

                value = record[15];
                if (value != null) {
                    cardholderGender = (Character) value;
                    reminderPOJO.setCardholderGender(cardholderGender);
                }

                value = record[16];
                if (value != null) {
                    pharmacyName = value.toString();
                    reminderPOJO.setPharmacyName(pharmacyName);
                }

                value = record[17];
                if (value != null) {
                    pharmacyPhone = value.toString();
                    reminderPOJO.setPharmacyPhone(pharmacyPhone);
                }
                value = record[18];
                if (value != null) {
                    redemptionChannelId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionChannelId(redemptionChannelId);
                }
                value = record[19];
                if (value != null) {
                    communicationPath = value.toString();
                    reminderPOJO.setCommunicationPath(communicationPath);
                }
                value = record[20];
                if (value != null) {
                    isValidPhone = value.toString();
                    reminderPOJO.setIsValidPhone(isValidPhone);
                }
                value = record[21];
                if (value != null) {
                    communicationMethod = value.toString();
                    reminderPOJO.setCommunicationMethod(communicationMethod);
                }

                list.add(reminderPOJO);

            } // for (Object[] record : queryResult)

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getTextRefill2Records", e);
        }

        return list;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<RefillReminderPOJO> getEmailRepeatRefillRecords(int campId) {

        List<RefillReminderPOJO> list = new ArrayList<>();

        try {
            String sql = "SELECT  "
                    + "emailRequest.Email_Req_No, "
                    + "emailRequest.Campaign_Id, "
                    + "emailRequest.FolderId, "
                    + "emailRequest.MessageTypeId, "
                    + "emailRequest.Cardholder_DOB, "
                    + "emailRequest.Claim_Status, "
                    + "emailRequest.File_Name, "
                    + "emailRequest.File_Type_Code, "
                    + "emailRequest.Fill_Date, "
                    + "emailRequest.NDC_Number, "
                    + "emailRequest.Email, "
                    + "emailRequest.Redemption_Id, "
                    + "emailRequest.Rx_Group_Number, "
                    + "(intervals.IntervalValue * intervalsType.UnitInSecond) / (60 * 60 *24) AS Reminder_Dats, "
                    + "dailyRedemption.Cardholder_First_Name, "
                    + "dailyRedemption.Cardholder_Last_Name, "
                    + "dailyRedemption.Cardholder_Gender, "
                    + "dailyRedemption.Pharmacy_Name, "
                    + "dailyRedemption.Pharmacy_Phone, "
                    + "dailyRedemption.Redemption_Channel_Id "
                    + "FROM EmailRequest emailRequest "
                    + "INNER JOIN EmailResponse emailResponse "
                    + "ON emailRequest.Email_Req_No = emailResponse.Email_Req_No "
                    + "INNER JOIN DailyRedemption dailyRedemption "
                    + "ON "
                    + "emailRequest.Redemption_Id = dailyRedemption.Redemption_Id "
                    + "INNER JOIN CampaignMessagesResponse campaignMessagesResponse "
                    + "ON "
                    + "( "
                    + "    emailRequest.Campaign_Id = campaignMessagesResponse.CampaignId "
                    + "AND emailRequest.FolderId = campaignMessagesResponse.FolderId "
                    + "AND emailRequest.MessageTypeId = campaignMessagesResponse.MessageTypeId "
                    + ") "
                    + "INNER JOIN Intervals intervals  "
                    + "ON  "
                    + "( "
                    + "campaignMessagesResponse.RepeatIntervalId = intervals.IntervalId "
                    + "AND campaignMessagesResponse.RepeatIntervalTypeId = intervals.IntervalsTypeId "
                    + ") "
                    + "INNER JOIN IntervalsType intervalsType "
                    + "ON  "
                    + "intervals.IntervalsTypeId = intervalsType.IntervalsTypeId "
                    + "WHERE "
                    + "emailRequest.End_Date IS NULL "
                    + "AND emailRequest.Campaign_Id = '" + campId + "' "
                    + "AND emailRequest.Message_Context = '" + Constants.MSG_CONTEXT_REFILL + "' "
                    + "AND campaignMessagesResponse.Communication_Path = 'Email' "
                    + "AND campaignMessagesResponse.RepeatMessageId > 0 "
                    + "AND TIMESTAMPDIFF(SECOND,emailRequest.Effective_Date,NOW()) >= intervalsType.UnitInSecond * intervals.IntervalValue "
                    + "AND TIMESTAMPDIFF(SECOND,emailRequest.Effective_Date,NOW()) < ((intervalsType.UnitInSecond * intervals.IntervalValue) + 84600 ) "
                    + "ORDER BY emailRequest.Email_Req_No ASC";

            logger.error("Repeat refill query : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            List<Object[]> queryResult = query.list();

            int emailReqNo = 0;
            int campaignId = 0;
            int folderId = 0;
            int messageTypeId = 0;
            Date cardholderDOB = null;
            int claimStatus = 0;
            String fileName = null;
            String fileTypeCode = null;
            Date fillDate = null;
            String ndcNumber = null;
            String email = null;
            int redemptionId = 0;
            String rxGroupNumber = null;
            int reminderDays = 0;
            String cardholderFirstName = null;
            String cardholderLastName = null;
            char cardholderGender = 'U';
            String pharmacyName = null;
            String pharmacyPhone = null;
            int redemptionChannelId = 0;
            Object value = null;
            RefillReminderPOJO reminderPOJO;

            for (Object[] record : queryResult) {
                reminderPOJO = new RefillReminderPOJO();

                value = record[0];
                if (value != null) {
                    emailReqNo = Integer.valueOf(value.toString());
                    reminderPOJO.setEmailReqNo(emailReqNo);
                }

                value = record[1];
                if (value != null) {
                    campaignId = Integer.valueOf(value.toString());
                    reminderPOJO.setCampaignId(campaignId);
                }

                value = record[2];
                if (value != null) {
                    folderId = Integer.valueOf(value.toString());
                    reminderPOJO.setFolderId(folderId);
                }

                value = record[3];
                if (value != null) {
                    messageTypeId = Integer.valueOf(value.toString());
                    reminderPOJO.setMessageTypeId(messageTypeId);
                }

                value = record[4];
                if (value != null) {
                    cardholderDOB = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setCardholderDob(cardholderDOB);
                }

                value = record[5];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    reminderPOJO.setClaimStatus(claimStatus);
                }

                value = record[6];
                if (value != null) {
                    fileName = value.toString();
                    reminderPOJO.setFileName(fileName);
                }

                value = record[7];
                if (value != null) {
                    fileTypeCode = value.toString();
                    reminderPOJO.setFileTypeCode(fileTypeCode);
                }

                value = record[8];
                if (value != null) {
                    fillDate = RedemptionUtil.parseDateShort(value.toString());
                    reminderPOJO.setFillDate(fillDate);
                }

                value = record[9];
                if (value != null) {
                    ndcNumber = value.toString();
                    reminderPOJO.setNdcNumber(ndcNumber);
                }

                value = record[10];
                if (value != null) {
                    email = value.toString();
                    reminderPOJO.setEmail(email);
                }

                value = record[11];
                if (value != null) {
                    redemptionId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionId(redemptionId);
                }

                value = record[12];
                if (value != null) {
                    rxGroupNumber = value.toString();
                    reminderPOJO.setRxGroupNumber(rxGroupNumber);
                }

                value = record[13];
                if (value != null) {
                    reminderDays = Double.valueOf(value.toString()).intValue();
                    reminderPOJO.setReminderDays(reminderDays);
                }

                value = record[14];
                if (value != null) {
                    cardholderFirstName = value.toString();
                    reminderPOJO.setCardholderFirstName(cardholderFirstName);
                }

                value = record[15];
                if (value != null) {
                    cardholderLastName = value.toString();
                    reminderPOJO.setCardholderLastName(cardholderLastName);
                }

                value = record[16];
                if (value != null) {
                    cardholderGender = (Character) value;
                    reminderPOJO.setCardholderGender(cardholderGender);
                }

                value = record[17];
                if (value != null) {
                    pharmacyName = value.toString();
                    reminderPOJO.setPharmacyName(pharmacyName);
                }

                value = record[18];
                if (value != null) {
                    pharmacyPhone = value.toString();
                    reminderPOJO.setPharmacyPhone(pharmacyPhone);
                }
                value = record[19];
                if (value != null) {
                    redemptionChannelId = Integer.valueOf(value.toString());
                    reminderPOJO.setRedemptionChannelId(redemptionChannelId);
                }

                list.add(reminderPOJO);

            } // for (Object[] record : queryResult)

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getEmailRepeatRefillRecords", e);
        }

        return list;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public DailyRedemption getDailyRedemptionDetailByRedemptionId(int redemptionId) {
        DailyRedemption drf = null;
        try {
            String queryString = "from DailyRedemption where redemptionId = :redemptionId";
            Query query = this.getCurrentSession().createQuery(queryString);
            query.setInteger("redemptionId", redemptionId);
            Object queryResult = query.uniqueResult();
            drf = (DailyRedemption) queryResult;
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getDailyRedemptionDetailByRedemptionId", e);
        }
        return drf;
    }

    public InstantRedemption getInstantRedemptionDetailByRedemptionId(int redemptionId) {
        InstantRedemption irf = null;
        try {
            String queryString = "from InstantRedemption where redemptionId = :redemptionId";
            Query query = this.getCurrentSession().createQuery(queryString);
            query.setInteger("redemptionId", redemptionId);
            Object queryResult = query.uniqueResult();
            irf = (InstantRedemption) queryResult;
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getDailyRedemptionDetailByRedemptionId", e);
        }
        return irf;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public Campaigns getCampaignsById(Integer campaignsId) {
        Campaigns campaigns = null;
        try {
            String hql = "select campaigns from Campaigns campaigns "
                    + "inner join fetch campaigns.shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "where "
                    + "campaigns.campaignId = :campaignId";

            List<Campaigns> list = (List<Campaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignsId)
                    .list();

            if (list != null && !list.isEmpty()) {
                campaigns = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCampaignsById", e);
        }
        return campaigns;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public boolean isPatientEligibleForReminder(int messageRequestId, long secondsDelay) {
        boolean isPatientEligibleForReminder = false;
        int count = 0;
        long secondDealyEnd = secondsDelay + (30 * 60);
        try {
            String sql = "SELECT COUNT(*) AS Message_Count  "
                    + "FROM AggregatorMessageRequest aggregatorMessageRequest "
                    + "WHERE "
                    + "aggregatorMessageRequest.Message_Req_No = :messageRequestId "
                    + "AND TIMESTAMPDIFF(SECOND,aggregatorMessageRequest.Effective_Date,NOW()) >= :secondsDelay "
                    + "AND TIMESTAMPDIFF(MINUTE,aggregatorMessageRequest.Effective_Date,NOW())  < :secondDealyEnd "
                    + "AND aggregatorMessageRequest.End_Date IS NULL";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger("messageRequestId", messageRequestId);
            query.setLong("secondsDelay", secondsDelay);

            query.setLong("secondDealyEnd", secondDealyEnd);

            List<Object[]> queryResult = query.list();
            if (queryResult != null && (!queryResult.isEmpty())) {

                logger.error("Sent message count list size : " + queryResult.size());

                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                    if (count > 0) {
                        isPatientEligibleForReminder = true;
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> isPatientEligibleForReminder", e);
        }
        return isPatientEligibleForReminder;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
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
            logger.error("Exception: RefillReminderDAO -> getGatewayInfo", e);
        }

        return gatewayInfo;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public CustomerRequest getCustomerRequestIP(String phoneNumber) {

        CustomerRequest customerRequest = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest "
                    + "where "
                    + "customerRequest.statusCode = :statusCode "
                    + "and customerRequest.phoneNumber = :phoneNumber "
                    + "order by customerRequest.effectiveDate desc ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("statusCode", StatusEnum.IN_PROGRESS.getValue())
                    .setParameter("phoneNumber", phoneNumber)
                    .list();

            if (list != null && list.size() > 0) {
                customerRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getCustomerRequestIP", e);
        }

        return customerRequest;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public ValidResponse getValidResponse(String validWord) {

        ValidResponse validResponse = null;

        try {

            String hql = "select validResponse from ValidResponse validResponse "
                    + "where "
                    + "validResponse.validWord = :validWord "
                    + "AND validResponse.isActive = 'YES' "
                    + "AND validResponse.isDelete = 'NO' ";

            List<ValidResponse> list = (List<ValidResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("validWord", validWord)
                    .list();

            if (list != null && list.size() > 0) {
                validResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getValidResponse", e);
        }

        return validResponse;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public AggregatorMessageRequest getAggregatorMessageRequestById(int messageReqNo) {

        AggregatorMessageRequest aggregatorMessageRequest = null;

        try {
            String hql = "select aggregatorMessageRequest from AggregatorMessageRequest aggregatorMessageRequest "
                    + "where "
                    + "aggregatorMessageRequest.messageReqNo = :messageReqNo "
                    + "and aggregatorMessageRequest.endDate IS NULL ";

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("messageReqNo", messageReqNo)
                    .list();

            if (list != null && (!list.isEmpty())) {
                aggregatorMessageRequest = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAggregatorMessageRequestById", e);
        }

        return aggregatorMessageRequest;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public AggregatorMessageRequest getRefillReminderAggregatorMessageRequestByRedemptionId(int redemptionId) {

        AggregatorMessageRequest aggregatorMessageRequest = null;

        try {
            String hql = "select aggregatorMessageRequest from AggregatorMessageRequest aggregatorMessageRequest "
                    + "where "
                    + "aggregatorMessageRequest.redemptionId = :redemptionId "
                    + "and aggregatorMessageRequest.messageContext IN (:messageContext1,:messageContext2) ";

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("redemptionId", redemptionId)
                    .setParameter("messageContext1", Constants.MSG_CONTEXT_REFILL)
                    .setParameter("messageContext2", Constants.MSG_CONTEXT_REPEAT_REFILL)
                    .list();

            if (list != null && (!list.isEmpty())) {
                aggregatorMessageRequest = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getRefillReminderAggregatorMessageRequestByRedemptionId", e);
        }

        return aggregatorMessageRequest;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public RxMMSRedemptionReqRes getRxMMSRedemptionReqResById(int messageReqNo) {

        RxMMSRedemptionReqRes rxMMSRedemptionReqRes = null;

        try {
            String hql = "select rxMMSRedemptionReqRes from RxMMSRedemptionReqRes rxMMSRedemptionReqRes "
                    + "where "
                    + "rxMMSRedemptionReqRes.mmsReqNo = :messageReqNo";

            List<RxMMSRedemptionReqRes> list = (List<RxMMSRedemptionReqRes>) this.getCurrentSession().createQuery(hql)
                    .setParameter("messageReqNo", messageReqNo)
                    .list();

            if (list != null && (!list.isEmpty())) {
                rxMMSRedemptionReqRes = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getRxMMSRedemptionReqResById", e);
        }

        return rxMMSRedemptionReqRes;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public void populateNpiValues(InstantRedemption irf) {
        Connection connection = null;
        try {
            PrescriberJdbcDAO.populatePrescriberValues(irf, connection);
            PharmacyJdbcDAO.populatePharmacyValues(irf, connection);

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> populateNpiValues", e);
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    public Drug getProductByNdc(String ndcNo) {
        Drug drug = null;
        try {
            String hql = "select drug from Drug drug "
                    + "inner join fetch drug.drugBrand "
                    + "where "
                    + " drug.ndcnumber = :ndcnumber ";

            List<Drug> list = (List<Drug>) this.getCurrentSession().createQuery(hql)
                    .setParameter("ndcnumber", ndcNo)
                    .list();

            if (list != null && list.size() > 0) {

                drug = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> populateNpiValues", e);
        }
        return drug;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public int retrieveMessageCountByTypeForRedemption(AggregatorMessageRequest aggregatorMessageRequest) {
        int count = 0;

        try {
            String hql = "SELECT request FROM AggregatorMessageRequest request "
                    + "WHERE "
                    + "    request.phoneNumber = :phoneNumber "
                    + "AND request.rxGroupNumber = :rxGroupNumber "
                    + "AND request.cardholderDob = :cardholderDOB "
                    + "AND request.fillDate = :fillDate "
                    + "AND request.ndcNumber = :ndcNumber "
                    + "AND request.claimStatus = :claimStatus "
                    + "AND request.folderId = :folderId "
                    + "AND request.campaignId = :campaignId "
                    + "AND request.messageTypeId = :messageTypeId "
                    + "AND request.isRepeat = :isRepeat ";

            String phoneNumber = aggregatorMessageRequest.getPhoneNumber();
            String rxGroupNumber = aggregatorMessageRequest.getRxGroupNumber();
            Date cardholderDOB = aggregatorMessageRequest.getCardholderDob();
            Date fillDate = aggregatorMessageRequest.getFillDate();
            String ndcNumber = aggregatorMessageRequest.getNdcNumber();
            int claimStatus = aggregatorMessageRequest.getClaimStatus();
            int folderId = aggregatorMessageRequest.getFolderId();
            int campaignId = aggregatorMessageRequest.getCampaignId();
            int redemptionId = aggregatorMessageRequest.getRedemptionId();
            int messageTypeId = aggregatorMessageRequest.getMessageTypeId();

            logger.error("HQL to get sent message count : " + hql);

            logger.error("Phone Number : " + phoneNumber);
            logger.error("Rx Group Number : " + rxGroupNumber);
            logger.error("Cardhodler DOB : " + RedemptionUtil.formatDateShort(cardholderDOB));
            logger.error("Fill Date : " + RedemptionUtil.formatDateShort(fillDate));
            logger.error("NDC Number : " + ndcNumber);
            logger.error("Claim Status : " + claimStatus);
            logger.error("Folder Id : " + folderId);
            logger.error("Campaign Id : " + campaignId);
            logger.error("Message Type Id : " + messageTypeId);

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("rxGroupNumber", rxGroupNumber)
                    .setParameter("cardholderDOB", cardholderDOB)
                    .setParameter("fillDate", fillDate)
                    .setParameter("ndcNumber", ndcNumber)
                    .setParameter("claimStatus", claimStatus)
                    .setParameter("folderId", folderId)
                    .setParameter("campaignId", campaignId)
                    .setParameter("messageTypeId", messageTypeId)
                    .setParameter("isRepeat", aggregatorMessageRequest.getIsRepeat())
                    .list();

            if (list != null && list.size() > 0) {
                count = list.size();
            }

            logger.error("Sent Message Count : " + count);

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> retrieveMessageCountByTypeForRedemption", e);
        }
        return count;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public String getURL(String urlCode) {
        Url url = null;
        String urlString = "";
        try {

            String hql = "select url from Url url "
                    + "where url.code = :urlCode "
                    + "and url.endDate is null";

            List<Url> list = (List<Url>) this.getCurrentSession().createQuery(hql)
                    .setParameter("urlCode", urlCode)
                    .list();

            if (list != null && (!list.isEmpty())) {
                url = new Url();
                url = list.get(0);
                urlString = url.getUrl();
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getURL", e);
        }

        return urlString;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByResponseName(int campaignId, int folderId, String responseTitle) {

        CampaignMessagesResponse campaignMessagesResponse = null;

        try {

            String hql = "select campaignMessagesResponse from  CampaignMessagesResponse campaignMessagesResponse "
                    + "inner join fetch campaignMessagesResponse.intervals interval "
                    + "inner join fetch campaignMessagesResponse.response response "
                    + "inner join fetch interval.intervalsType intervalType "
                    + "where "
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
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
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesResponseByResponseName", e);
        }

        return campaignMessagesResponse;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public CampaignTrigger getTriggerByCampaignId(int campaignId) {
        CampaignTrigger trigger = null;

        try {

            String hql = "select trigger from CampaignTrigger trigger "
                    + "WHERE "
                    + "    trigger.id.campaignId = :campaignId "
                    + "AND trigger.isActive = 'Yes' "
                    + "AND trigger.isDelete = 'No' "
                    + "ORDER BY trigger.id.triggerId ASC ";

            List<CampaignTrigger> list = (List<CampaignTrigger>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && (!list.isEmpty())) {
                trigger = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getTriggerByCampaignId", e);
        }

        return trigger;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public Intervals getIntervalById(int intervalId) {
        Intervals interval = null;

        try {

            String hql = "SELECT intervals FROM Intervals intervals "
                    + "INNER JOIN FETCH intervals.intervalsType intervalType "
                    + "WHERE "
                    + "intervals.intervalId = :intervalId "
                    + "AND intervals.isActive = 'YES' "
                    + "AND intervals.isDelete = 'NO'";

            List<Intervals> list = (List<Intervals>) this.getCurrentSession().createQuery(hql)
                    .setParameter("intervalId", intervalId)
                    .list();

            if (list != null && (!list.isEmpty())) {
                interval = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getIntervalById", e);
        }

        return interval;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public int markAggregatorMessageRequestEndDate(int messageReqNo) {

        int count = 0;

        try {

            String hql = "update AggregatorMessageRequest request "
                    + "set "
                    + "request.endDate = ? "
                    + "where "
                    + "request.messageReqNo = ? ";

            Query query = this.getCurrentSession().createSQLQuery(hql);

            query.setDate(0, new Date());
            query.setInteger(1, messageReqNo);

            count = query.executeUpdate();

            logger.error("Mark end date update count : " + count);

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> markAggregatorMessageRequestEndDate", e);
        }

        return count;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public int markRARYESReceivedEndDate(int redmeptionId) {

        int count = 0;

        try {

            String hql = "update Raryesreceived request "
                    + "set "
                    + "request.endDate = ? "
                    + "where "
                    + "request.redemptionId = ? ";

            Query query = this.getCurrentSession().createSQLQuery(hql);

            query.setDate(0, new Date());
            query.setInteger(1, redmeptionId);

            count = query.executeUpdate();

            logger.error("Mark end date update count : " + count);

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> markRARYESReceivedEndDate", e);
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
    public int sentEmailCountRefill(String email, int campaignId, int folderId, int messageTypeid) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Message_Count FROM EmailRequest emailRequest "
                    + "INNER JOIN EmailResponse emailResponse "
                    + "ON emailRequest.Email_Req_No = emailResponse.Email_Req_No "
                    + "WHERE "
                    + "    emailRequest.Email = ? "
                    + "AND emailRequest.Campaign_Id = ? "
                    + "AND emailRequest.FolderId = ? "
                    + "AND emailRequest.MessageTypeId = ? "
                    + "AND emailResponse.Email_Status = 'Success' "
                    + "AND TIMESTAMPDIFF(MINUTE,emailRequest.Effective_Date, NOW()) <= (24 * 60) ";

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
            logger.error("Exception: RefillReminderDAO -> sentEmailCountRefill", e);
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
    public int sentEmailCountPAPending(String email, int campaignId, int folderId, int messageTypeid) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Message_Count FROM EmailRequest emailRequest "
                    + "INNER JOIN EmailResponse emailResponse "
                    + "ON emailRequest.Email_Req_No = emailResponse.Email_Req_No "
                    + "WHERE "
                    + "    emailRequest.Email = ? "
                    + "AND emailRequest.Campaign_Id = ? "
                    + "AND emailRequest.FolderId = ? "
                    + "AND emailRequest.MessageTypeId = ? "
                    + "AND emailResponse.Email_Status = 'Success' "
                    + "AND TIMESTAMPDIFF(MINUTE,emailRequest.Effective_Date, NOW()) <= (24 * 60) ";

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
            logger.error("Exception: RefillReminderDAO -> sentEmailCountPAPending", e);
        }

        return count;
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
                    + "    campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.response.responseTitle = :responseTitle "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId "
                    + "and campaignMessagesResponse.communicationPath = :communicationPath ";

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
            logger.error("Exception: RefillReminderDAO -> getCampaignMessagesByCommunicationPath", e);
        }

        return list;

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
            logger.error("Exception: RefillReminderDAO -> getEventsDescription", e);
        }

        return events;
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
            logger.error("Exception: RefillReminderDAO -> sentMMSCountCampaign", e);
        }

        return count;
    }

    public Object findById(Class clazz, Integer id) throws Exception {
        Object o = this.getCurrentSession().get(clazz, id);
        return o;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<EventDetail> getEventDetail(String eventsIds, String dataSet) {

        List<EventDetail> list = null;
        try {
            String hql = "Select eventd from EventDetail eventd "
                    + "where "
                    + "eventd.event.eventId IN (" + eventsIds + ") "
                    + "and eventd.dataSet = :dataSet order by eventd.event.eventId,eventd.id.eventDetailId ASC";

            list = (List<EventDetail>) this.getCurrentSession().createQuery(hql)
                    .setParameter("dataSet", dataSet)
                    .list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getEventDetail", e);
        }
        return list;

    }

    public List<Order> getOrderByTransactionNumber(String transactionNo) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.pharmacy pharmacy where ord.transactionNo=:transactionNo order by ord.id desc").setMaxResults(1);
        query.setParameter("transactionNo", transactionNo);
        return query.list();
    }

    public boolean isEventDetailVerifiedForDailyRedemption(List<EventDetail> details, DailyRedemptionId drfId) {
        boolean flag = false;
        int count = 0;
        Session session = this.getCurrentSession();
        String sql = "select COUNT(*) from  ";
        if (details.size() > 0) {
            sql += details.get(0).getDataSet() + " where ";
        }
        int eventId = 0;
        for (EventDetail eventDetail : details) {
            if (eventId > 0 && eventId != eventDetail.getEvent().getEventId()) {

                sql += " AND ";
            }
            eventId = eventDetail.getEvent().getEventId();
            if (eventDetail.getBracketsStart() != null && eventDetail.getBracketsStart().length() > 0) {
                sql += eventDetail.getBracketsStart();
            }
            sql += eventDetail.getFieldSelection() + eventDetail.getOperation() + "'" + eventDetail.getSpecificValue() + "'";
            if (eventDetail.getBracketsEnd() != null && eventDetail.getBracketsEnd().length() > 0) {
                sql += eventDetail.getBracketsEnd();
            }
            if (!eventDetail.getCondition().equalsIgnoreCase("END")) {
                sql += " " + eventDetail.getCondition() + " ";
            }

        }
        if (sql.endsWith("AND")) {
            sql = sql.substring(0, sql.lastIndexOf("AND"));
        }
        if (!sql.contains("Claim_Status")) {
            sql += " AND Claim_Status = '" + drfId.getClaimStatus() + "' ";
        }
        if (!sql.contains("Transaction_Number")) {
            sql += " AND Transaction_Number = '" + drfId.getTransactionNumber() + "' ";
        }

        Query query = session.createSQLQuery(sql);
        List<Object[]> queryResult = query.list();

        if (queryResult != null && (!queryResult.isEmpty())) {
            Object object = queryResult.get(0);

            if (object != null) {
                count = Integer.parseInt(object.toString());
            }
        }

        if (count > 0) {
            flag = true;
        }
        return flag;
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
            logger.error("Exception: RefillReminderDAO -> isMmsExists", e);
        }

        return flag;
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
                    + "request.Message_Req_No AS Seq_No, "
                    + "request.Phone_Number AS Phone_Number, "
                    + "request.Message_Context AS Message_Context, "
                    + "request.Effective_Date AS Message_Time_Stamp, "
                    + "'Text' AS Path "
                    + "FROM AggregatorMessageRequest request "
                    + "INNER JOIN AggregatorMessageResponse response "
                    + "ON request.Message_Req_No = response.Message_Req_No "
                    + "INNER JOIN GatewayCode gatewayCode "
                    + "ON gatewayCode.Error_Code = response.Error_Code "
                    + "INNER JOIN Campaigns campaign "
                    + "ON request.Campaign_Id = campaign.CampaignId "
                    + "WHERE "
                    + "request.Phone_Number = '" + phoneNumber + "' "
                    + "AND campaign.ShortCode = " + shortCode + " "
                    + "AND gatewayCode.Code_Type = 'Success' "
                    + "AND request.End_Date IS NULL  "
                    + "UNION ALL "
                    + "SELECT "
                    + "request.MMS_CMR_Seq_No AS Seq_No, "
                    + "request.Phone_Number AS Phone_Number, "
                    + "request.MMS_Context AS Message_Context, "
                    + "request.Effective_Date AS Message_Time_Stamp, "
                    + "'MMS' AS Path "
                    + "from MMSCampaignMessageReqRes request "
                    + "INNER JOIN MMSCampaignMessageResponseParsedData response "
                    + "ON request.MMS_CMR_Seq_No = response.MMS_CMR_Seq_No "
                    + "INNER JOIN Campaigns campaign "
                    + "ON request.Campaign_Id = campaign.CampaignId "
                    + "WHERE "
                    + "  request.Phone_Number = '" + phoneNumber + "' "
                    + "AND campaign.ShortCode = '" + shortCode + "' "
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
            logger.error("Exception: RefillReminderDAO -> getMessagePriority", e);
        }
        return messagePriority;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public OptInOut getTextOptInOut(int campaignId, String phoneNumber) {

        OptInOut optInOut = null;

        try {

            String hql = "select optInOut from OptInOut optInOut "
                    + "where "
                    + "    optInOut.phoneNumber = :phoneNumber "
                    + "and optInOut.campaignId = :campaignId "
                    + "order by optInOut.effectiveDate desc ";

            List<OptInOut> list = (List<OptInOut>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && (!list.isEmpty())) {
                optInOut = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getTextOptInOut", e);
        }

        return optInOut;
    }

    public List<Campaigns> getAllRefillOrderReminderCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND campaigns.isRefillOrderReminder='Yes' "
                    + "AND (campaigns.refillProcessTiming is not NULL "
                    + "AND campaigns.refillProcessTiming > 0) "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql);

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getAllRefillOrderReminderCandidateActiveCampaign", e);
        }
        return list;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<InstantRedemption> getDRFInLast30Mints(int campaignId) {

        List<InstantRedemption> list = null;

        try {

            String hql = "select instantRedemption from InstantRedemption instantRedemption "
                    + "where "
                    + " instantRedemption.campaignId = :campaignId "
                    + " and TIMESTAMPDIFF(MINUTE,instantRedemption.effectiveDate,NOW()) < 30 "
                    + "order by instantRedemption.effectiveDate desc ";

            list = (List<InstantRedemption>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getDRFInLast30Mints", e);
        }

        return list;
    }

    public int getPreviousRedemptionId(InstantRedemption instantRedemption) {

        int redemptionId = 0;

        try {

            String sql = "SELECT "
                    + "drive_irf.Redemption_Id,"
                    + "drive_irf.Cardholder_First_Name "
                    + "FROM  "
                    + "(  "
                    + "SELECT "
                    + "irf.Redemption_Id,"
                    + "            irf.Claim_Status ,"
                    + "            irf.Cardholder_DOB,"
                    + "            irf.Cardholder_First_Name,"
                    + "            irf.Cardholder_Last_Name,"
                    + "            irf.Cardholder_Gender,"
                    + "            irf.Transaction_Number,"
                    + "            irf.Fill_Date "
                    + "FROM InstantRedemption irf "
                    + "GROUP BY irf.Transaction_Number "
                    + "HAVING COUNT(*) = 1"
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Cardholder_First_Name = '" + instantRedemption.getCardholderFirstName() + "' "
                    + "AND drive_irf.Cardholder_Last_Name = '" + instantRedemption.getCardholderLastName() + "' "
                    + "AND drive_irf.Cardholder_DOB = '" + instantRedemption.getCardholderDob() + "' "
                    + "AND drive_irf.Cardholder_Gender = '" + instantRedemption.getCardholderGender() + "' "
                    + "AND drive_irf.Transaction_Number != '" + instantRedemption.getId().getTransactionNumber() + "' "
                    + "ORDER BY drive_irf.Fill_Date DESC "
                    + "LIMIT 0,1 ";

            logger.error("Priority SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object[] result = queryResult.get(0);

                if (result != null) {
                    Object value = null;

                    value = result[0];

                    if (value != null) {
                        redemptionId = Integer.valueOf(value.toString());
                    }

                }
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getPreviousRedemptionId", e);
        }
        return redemptionId;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public AggregatorMessageRequest getLastestAggregatorMessageRequest(int redemptionId) {

        AggregatorMessageRequest aggregatorMessageRequest = null;

        try {

            String hql = "select aggregatorMessageRequest from AggregatorMessageRequest aggregatorMessageRequest "
                    + "where "
                    + " aggregatorMessageRequest.redemptionId = :redemptionId "
                    + "and aggregatorMessageRequest.messageContext = :messageContext "
                    + "order by aggregatorMessageRequest.effectiveDate desc ";

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("redemptionId", redemptionId)
                    .setParameter("messageContext", Constants.REDEMPTION)
                    .list();

            if (list != null && (!list.isEmpty())) {
                aggregatorMessageRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getLastestAggregatorMessageRequest", e);
        }

        return aggregatorMessageRequest;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     * @param redemptionId
     * @return
     */
    public AggregatorMessageRequest getRefillSuccessfullMessage(int redemptionId) {

        AggregatorMessageRequest aggregatorMessageRequest = null;

        try {

            String hql = "select aggregatorMessageRequest from AggregatorMessageRequest aggregatorMessageRequest "
                    + "where "
                    + " aggregatorMessageRequest.redemptionId = :redemptionId "
                    + "and aggregatorMessageRequest.messageContext = :messageContext "
                    + "order by aggregatorMessageRequest.effectiveDate desc ";

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("redemptionId", redemptionId)
                    .setParameter("messageContext", Constants.MSG_CONTEXT_REFILL_SUCCESS)
                    .list();

            if (list != null && (!list.isEmpty())) {
                aggregatorMessageRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getRefillSuccessfullMessage", e);
        }

        return aggregatorMessageRequest;
    }

    public String getDrugName(String ndc) {
        String drugName = null;
        try {
            String sql = "select * from ClinicalMessageSetup where ndc = " + ndc;

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object[] object = queryResult.get(0);
                drugName = object[6].toString();
            }

        } catch (Exception e) {
            logger.error("Exception: RefillReminderDAO -> getDrugName", e);
        }
        return drugName;
    }

    public String getDailyRedemptions(String firstName, String lastName, Integer campaignId, Date cardHolderDOB) throws Exception {
        String redemptionId = null;
        String sql = "SELECT"
                + "  Transaction_Number "
                + "FROM"
                + "  DailyRedemption"
                + "  INNER JOIN Orders"
                + "    ON Orders.TransactionNumber = DailyRedemption.Transaction_Number "
                + "WHERE DailyRedemption.Cardholder_First_Name = '" + firstName + "' "
                + "  AND DailyRedemption.Cardholder_Last_Name ='" + lastName + "' "
                + "  AND DailyRedemption.Cardholder_DOB = '" + cardHolderDOB + "' "
                + "  AND DailyRedemption.CampaignId = '" + campaignId + "' ";
        logger.info("getDailyRedemptions: " + sql);

        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> queryResult = query.list();
        if (queryResult != null && (!queryResult.isEmpty())) {
            Object object = queryResult.get(0);
            redemptionId = object.toString();
        }
        return redemptionId;
    }

    public List<Object[]> getSurveyRemindersList(int fromHour, int toHour) throws Exception {
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT slogs.* FROM `survey_logs` slogs ");
        sql.append("WHERE slogs.`status` <> 'Completed' ");
        sql.append("AND TIMESTAMPDIFF(HOUR, slogs.`created_at`, NOW()) >=:fromHour ");
        sql.append("AND TIMESTAMPDIFF(HOUR, slogs.`created_at`, NOW()) <:toHour ");
        
        Query query=getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("fromHour", fromHour);
        query.setParameter("toHour", toHour);
        return query.list();
    }
}
