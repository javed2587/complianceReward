package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.AggregatorMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CouponOffered;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.Drfprocessed;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.Enrollment;
import com.ssa.cms.model.EventDetail;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.GatewayInfo;
import com.ssa.cms.model.InstantRequest;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.model.InstantRedemptionId;
import com.ssa.cms.model.NotificationStatus;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.RedemptionIngredient;
import com.ssa.cms.model.Url;
import com.ssa.cms.util.DBUtil;
import com.ssa.cms.util.RefillUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
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

public class RedemptionDAO extends BaseDAO implements Serializable {

    private final Log logger = LogFactory.getLog(getClass());

    public Object getById(Class clazz, Serializable id) {
        return this.getCurrentSession().get(clazz, id);
    }

    public InstantRedemption getIRFById(InstantRedemptionId id) throws Exception {
        InstantRedemption irf = null;
        Object o = this.getCurrentSession().get(InstantRedemption.class, id);
        if (o != null) {
            irf = (InstantRedemption) o;
        }
        return irf;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public int getIRFCountById(InstantRedemptionId id) throws Exception {
        InstantRedemption irf = null;
        int count = 0;
        irf = getIRFById(id);
        if (irf != null) {
            count = 1;
        }
        return count;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public DailyRedemption getDRFById(DailyRedemptionId id) throws Exception {
        DailyRedemption drf = null;
        Object o = this.getCurrentSession().get(DailyRedemption.class, id);
        if (o != null) {
            drf = (DailyRedemption) o;
        }
        return drf;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public int getDRFCountById(DailyRedemptionId id) throws Exception {
        int count = 0;
        DailyRedemption drf = getDRFById(id);
        if (drf != null) {
            count = 1;
        }
        return count;
    }

    public Campaigns getCampaignByNDCNumber(String ndcNumber) throws Exception {
        Campaigns campaign = null;
        String hql = "select campaign from Campaigns campaign "
                + "inner join campaign.drugBrands drugBrand "
                + "inner join fetch campaign.redemptionChannel "
                + "inner join drugBrand.drugs drug "
                + "inner join fetch campaign.smtpServerInfo "
                + "where "
                + "drug.ndcnumber = :ndcNumber ";

        List<Campaigns> list = (List<Campaigns>) this.getCurrentSession().createQuery(hql)
                .setParameter("ndcNumber", ndcNumber)
                .list();

        if (list != null && (!list.isEmpty())) {
            campaign = list.get(0);
        }

        return campaign;
    }

    public List<RedemptionIngredient> getRedemptionIngredientByTransactionNumber(String transactionNumber) throws Exception {

        String hql = "select redemptionIngredient from RedemptionIngredient redemptionIngredient where "
                + " redemptionIngredient.ndc in (select d.ndcnumber from Drug d)"
                + " and redemptionIngredient.transactionNumber = :transactionNumber";

        List<RedemptionIngredient> list = (List<RedemptionIngredient>) this.getCurrentSession().createQuery(hql)
                .setParameter("transactionNumber", transactionNumber)
                .list();

        return list;
    }

    public Campaigns getCampaignById(String campaignId) throws Exception {

        Campaigns campaign = null;

        String hql = "select campaign from Campaigns campaign "
                + "inner join campaign.drugBrands drugBrand "
                + "inner join fetch campaign.redemptionChannel "
                + "inner join drugBrand.drugs drug "
                + "inner join fetch campaign.smtpServerInfo "
                + "where "
                + "campaign.campaignId = :campaignId ";

        List<Campaigns> list = (List<Campaigns>) this.getCurrentSession().createQuery(hql)
                .setParameter("campaignId", Integer.parseInt(campaignId))
                .list();

        if (list != null && (!list.isEmpty())) {
            campaign = list.get(0);
        }

        return campaign;
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
            logger.error("Exception:RedemptionDAO -> sentMMSCountCampaign", e);
        }

        return count;
    }

    /**
     * *****************************************************************************
     * *****************************************************************************
     * *****************************************************************************
     */
    public InstantRequest getInstantRequestByMemberId(String phoneNo, int campaignId) {

        InstantRequest instantRequest = null;

        try {
            String hql = "select ins from InstantRequest ins where "
                    + "(ins.text = :phoneNo or ins.email = :phoneNo) "
                    + "and ins.campaigns.campaignId = :campaignId "
                    + "order by ins.id";

            List<InstantRequest> list = (List<InstantRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNo", phoneNo)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && list.size() > 0) {
                instantRequest = new InstantRequest();
                instantRequest = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getInstantRequestByMemberId", e);
        }

        return instantRequest;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<Integer> getEventByRedemptionDynamicValue() {

        List<Integer> list = null;
        try {

            String hql = "select event.eventId from Event event "
                    + "where "
                    + "event.eventCriteria = 'Dynamic' "
                    + "and event.dynamicValue = 'Redemption'";

            list = (List<Integer>) this.getCurrentSession().createQuery(hql)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getEventByRedemptionDynamicValue", e);
        }
        return list;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<Integer> getFolderByEventIds(List<Integer> eventList, int campaignId) {
        List<Integer> folders = null;
        String events = "";
        try {
            for (Integer value : eventList) {
                events = events + "," + value;
            }
            events = events.substring(1);
            String hql = "select ehfhc.folderId from EventHasFolderHasCampaigns ehfhc "
                    + "where "
                    + "ehfhc.eventId in (" + events + ") "
                    + "and ehfhc.campaignId = :campaignId";

            folders = (List<Integer>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getFolderByEventIds", e);
        }

        return folders;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
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
            logger.error("Exception:RedemptionDAO -> getEventHasFolderHasCampaign", e);
        }

        return eventHasFolderHasCampaigns;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<EventHasFolderHasCampaigns> getEventHasFolderHasCampaign(int campaignId, String dynamicValue, String communicationPath) {

        List<EventHasFolderHasCampaigns> list = null;
        try {
            String hql = "SELECT eventHasFolderHasCampaigns from EventHasFolderHasCampaigns eventHasFolderHasCampaigns,Event event "
                    + "WHERE  "
                    + "eventHasFolderHasCampaigns.campaignId = :campaignId "
                    + "and eventHasFolderHasCampaigns.communicationPath=:communicationPath "
                    //                    + "and eventHasFolderHasCampaigns.eventId != 1 "
                    + "and (event.eventId=eventHasFolderHasCampaigns.eventId) "
                    + "and  (event.dynamicValue = :dynamicValue) "
                    + "ORDER BY eventHasFolderHasCampaigns.folderId desc ";

            list = (List<EventHasFolderHasCampaigns>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("communicationPath", communicationPath)
                    .setParameter("dynamicValue", dynamicValue)
                    .list();
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getEventHasFolderHasCampaign", e);
        }
        return list;
    }

    /*
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessages(int campaignId, List<Integer> folderIds) {

        List<CampaignMessages> list = null;
        String folderid = "";

        try {
            for (Integer value : folderIds) {
                folderid = folderid + "," + value;
            }
            folderid = folderid.substring(1);
            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId IN (" + folderid + ") "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCampaignMessages", e);
        }

        return list;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public List<CampaignMessages> getCampaignMessages(int campaignId, int folderId) {

        List<CampaignMessages> list = null;
        try {
            String hql = "select campaignMessages from CampaignMessages campaignMessages "
                    + "where "
                    + "    campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessages.messageType.id.folderId IN (" + folderId + ") "
                    + "order by campaignMessages.messageType.id.messageTypeId ASC ";

            list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCampaignMessages", e);
        }

        return list;

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
                    + "left join fetch eventd.event event "
                    + "where "
                    + "eventd.event.eventId IN (" + eventsIds + ") "
                    + "and eventd.dataSet = :dataSet order by eventd.event.eventId,eventd.id.eventDetailId ASC";

            list = (List<EventDetail>) this.getCurrentSession().createQuery(hql)
                    .setParameter("dataSet", dataSet)
                    .list();

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getEventDetail", e);
        }
        return list;

    }

    /**
     * *****************************************************************************
     * *****************************************************************************
     * *****************************************************************************
     */
    public List<Object> get(Class objClass, Object object) {
        List<Object> list = new ArrayList<Object>();
        return list;
    }

    /**
     * ******************************************************************************************
     * ******************************************************************************************
     * ******************************************************************************************
     */
    public Enrollment getTextEnrollment(String phoneNumber, int campaignId) {

        Enrollment enrollment = null;

        try {

            String hql = "select customerRequest from CustomerRequest customerRequest,CommunicationSource commSource,CouponOffered couponOffered "
                    + "where "
                    + " customerRequest.communicationSourceCode = commSource.communicationSourceCode "
                    + "and couponOffered.crSeqNo = customerRequest.crSeqNo "
                    + "and customerRequest.statusCode = :statusCode "
                    + "and customerRequest.phoneNumber = :phoneNumber "
                    + "and customerRequest.campaignId = :campaignId "
                    + "and commSource.type = 'External' "
                    + "order by customerRequest.effectiveDate desc ";

            List<CustomerRequest> list = (List<CustomerRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("statusCode", StatusEnum.COMPLETED.getValue())
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && (!list.isEmpty())) {
                CustomerRequest customerRequest = list.get(0);

                if (customerRequest != null) {
                    enrollment = new Enrollment();
                    enrollment.setEnrollmentId(customerRequest.getCrSeqNo());
                    enrollment.setEnrollmentPath("T");
                    enrollment.setCommunicationSourceCode(customerRequest.getCommunicationSourceCode());
//                    enrollment.setEffectiveDate(customerRequest.getLastUpdatedOn());

                    enrollment.setCommunicationMethod("T");
                    enrollment.setCommunicationId(customerRequest.getPhoneNumber());
                }
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getTextEnrollment", e);
        }

        return enrollment;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public CouponOffered getCouponOfferedByCrno(long crNo, int campaignId) {

        CouponOffered couponOffered = null;

        try {

            String hql = "select couponOffered from CouponOffered couponOffered "
                    + "where couponOffered.crSeqNo = :crNo "
                    + "and couponOffered.campaignId = :campaignId "
                    + "order by couponOffered.effectiveDate desc ";

            List<CouponOffered> list = (List<CouponOffered>) this.getCurrentSession().createQuery(hql)
                    .setParameter("crNo", crNo)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && list.size() > 0) {
                couponOffered = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCouponOfferedByCrno", e);
        }

        return couponOffered;

    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public InstantRequest getInstantRequestByPhone(String phone, int campaignId) {

        InstantRequest instantRequest = null;

        try {

            String hql = "select instantRequest from InstantRequest instantRequest "
                    + "where instantRequest.text = :text "
                    + "and instantRequest.campaigns.campaignId = :campaignId "
                    + "order by instantRequest.effectiveDate desc ";

            List<InstantRequest> list = (List<InstantRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("text", phone)
                    .setParameter("campaignId", campaignId)
                    .list();

            if (list != null && list.size() > 0) {
                instantRequest = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getInstantRequestByPhone", e);
        }

        return instantRequest;

    }

    public int retrieveMessageCountByTypeForRedemption(String phoneNumber, String rxGroupNumber, Date cardholderDob, Date fillDate, String ndcNumber,
            int claimStatus, int folderId, int messageTypeId) {
        int count = 0;
        try {
            String hql = "SELECT request FROM AggregatorMessageRequest request "
                    + "WHERE "
                    + "request.phoneNumber = :phoneNumber "
                    + "AND request.rxGroupNumber = :rxGroupNumber "
                    + "AND request.cardholderDob = :cardholderDob "
                    + "AND request.fillDate = :fillDate "
                    + "AND request.ndcNumber = :ndcNumber "
                    + "AND request.claimStatus = :claimStatus "
                    + "AND request.folderId = :folderId "
                    + "AND request.messageTypeId = :messageTypeId "
                    + "AND TIMESTAMPDIFF(HOUR,request.effectiveDate,NOW()) <= 24 ";

            List<AggregatorMessageRequest> list = (List<AggregatorMessageRequest>) this.getCurrentSession().createQuery(hql)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("rxGroupNumber", rxGroupNumber)
                    .setParameter("cardholderDob", cardholderDob)
                    .setParameter("fillDate", fillDate)
                    .setParameter("ndcNumber", ndcNumber)
                    .setParameter("claimStatus", claimStatus)
                    .setParameter("folderId", new Integer(folderId))
                    .setParameter("messageTypeId", new Integer(messageTypeId))
                    .list();
            if (list != null && list.size() > 0) {
                count = list.size();
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> retrieveMessageCountByTypeForRedemption", e);
        }
        return count;
    }

    /**
     * *****************************************************************************
     * *****************************************************************************
     * *****************************************************************************
     */
    @SuppressWarnings("unchecked")
    public int getInstantRedemptionCount() {

        int count = 0;
        List<Object> list = new ArrayList<Object>();
        String hql = "select count(*) from InstantRedemption irf";

        list = (List<Object>) this.getCurrentSession().createQuery(hql)
                .list();
        if (list != null && list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public boolean isEventDetailVerifiedForDailyRedemption(List<EventDetail> details, DailyRedemptionId drfId, int redemptionId) {
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
            if (eventDetail.getSpecificValue().equalsIgnoreCase("Null")) {
                sql += eventDetail.getFieldSelection() + " IS NULL ";
            } else {
                sql += eventDetail.getFieldSelection() + eventDetail.getOperation() + "'" + eventDetail.getSpecificValue() + "'";
            }
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
            sql += " Claim_Status = '" + drfId.getClaimStatus() + "' ";
        }
        if (!sql.contains("Transaction_Number")) {
            sql += " AND Transaction_Number = '" + drfId.getTransactionNumber() + "' ";
        }

        if (!sql.contains("Redemption_Id")) {
            sql += " AND Redemption_Id = '" + redemptionId + "' ";
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

    public boolean isEventDetailVerifiedForInstantRedemption(List<EventDetail> details, InstantRedemptionId irfId) {
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

            if (eventDetail.getSpecificValue().equalsIgnoreCase("Null")) {
                sql += eventDetail.getFieldSelection() + " IS NULL ";
            } else {
                sql += eventDetail.getFieldSelection() + eventDetail.getOperation() + "'" + eventDetail.getSpecificValue() + "'";
            }

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
            sql += " AND Claim_Status = '" + irfId.getClaimStatus() + "' ";
        }
        if (!sql.contains("Transaction_Number")) {
            sql += " AND Transaction_Number = '" + irfId.getTransactionNumber() + "' ";
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

    public boolean isEventDetailVerifiedForInstantRedemption(List<EventDetail> details, InstantRedemptionId irfId, int redemptionId) {
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

            if (eventDetail.getSpecificValue().equalsIgnoreCase("Null")) {
                sql += eventDetail.getFieldSelection() + " IS NULL ";
            } else {
                sql += eventDetail.getFieldSelection() + eventDetail.getOperation() + "'" + eventDetail.getSpecificValue() + "'";
            }

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
            sql += " AND Claim_Status = '" + irfId.getClaimStatus() + "' ";
        }
        if (!sql.contains("Transaction_Number")) {
            sql += " AND Transaction_Number = '" + irfId.getTransactionNumber() + "' ";
        }
        if (!sql.contains("Redemption_Id")) {
            sql += " AND Redemption_Id = '" + redemptionId + "' ";
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

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.EFFECTIVE_DATE_FORMAT);
        return simpleDateFomrat.format(date);
    }

    public static String formatDateShort(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
        return simpleDateFomrat.format(date);
    }

    public Object findById(Class clazz, Integer id) throws Exception {
        Object o = this.getCurrentSession().get(clazz, id);
        return o;
    }

    public Campaigns getCampaignsById(Integer campaignsId) throws Exception {
        Campaigns campaigns = null;
        Object o = this.getCurrentSession().get(Campaigns.class, campaignsId);
        if (o != null) {
            campaigns = (Campaigns) o;
        }
        return campaigns;
    }

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
                    .setParameter("campaignId", campaignId)
                    .setParameter("folderId", folderId)
                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesResponse", e);
        }

        return campaignMessagesResponse;

    }

    public CampaignMessagesResponse getCampaignMessagesResponseBycommunicationPath(int campaignId, int folderId, int messageTypeId, String communicationPath) {

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
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesResponseBycommunicationPath", e);
        }

        return campaignMessagesResponse;

    }

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
            logger.error("Exception:RedemptionDAO -> getCampaignMessages", e);
        }

        return list;

    }

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
            logger.error("Exception:RedemptionDAO -> getGatewayInfo", e);
        }

        return gatewayInfo;
    }

    public void populateNpiValues(InstantRedemption irf) {
        Connection connection = null;
        try {
            PrescriberJdbcDAO.populatePrescriberValues(irf, connection);
//            PharmacyJdbcDAO.populatePharmacyValues(irf, connection);

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> populateNpiValues", e);
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
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
            logger.error("Exception:RedemptionDAO -> getTriggerByCampaignId", e);
        }

        return trigger;
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
            logger.error("Exception:RedemptionDAO -> getTextOptInOut", e);
        }

        return optInOut;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
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
            logger.error("Exception:RedemptionDAO -> getURL", e);
        }

        return urlString;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public BigDecimal getTotalBenefitAmountIRF(InstantRedemption instantRedemption) {

        BigDecimal amount = null;

        try {

            String sql = "SELECT SUM(drive_irf.Tot_Drug_Cost_Paid_To_Pharmacy) AS Total_Benefit_Amount FROM "
                    + "( "
                    + "SELECT "
                    + "irf.Cardholder_First_Name AS Cardholder_First_Name,"
                    + "irf.Cardholder_Last_Name AS Cardholder_Last_Name, "
                    + "irf.Cardholder_DOB, "
                    + "irf.Cardholder_Gender, "
                    + "irf.NDC_Number, "
                    + "irf.Claim_Status, "
                    + "irf.Fill_Date,"
                    + "irf.Tot_Drug_Cost_Paid_To_Pharmacy,"
                    + "irf.Prescription_Number,"
                    + "irf.CampaignId AS CampaignId "
                    + "FROM InstantRedemption irf "
                    + "GROUP BY irf.Communication_Id, irf.Rx_Group_Number, irf.Cardholder_DOB, irf.Fill_Date,irf.NDC_Number, irf.Transaction_Number,irf.CampaignId "
                    + "HAVING COUNT(*) = 1 "
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Cardholder_First_Name = ? "
                    + "AND drive_irf.Cardholder_Last_Name = ? "
                    + "AND drive_irf.Cardholder_DOB = ? "
                    + "AND drive_irf.Cardholder_Gender = ? "
                    + "AND drive_irf.CampaignId = ? "
                    + "AND YEAR(drive_irf.Fill_Date) = YEAR(?)";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, instantRedemption.getCardholderFirstName());
            query.setString(1, instantRedemption.getCardholderLastName());
            query.setString(2, RefillUtil.formatDateShort(instantRedemption.getCardholderDob()));
            query.setString(3, instantRedemption.getCardholderGender().toString());
            query.setInteger(4, instantRedemption.getCampaignId());
            query.setString(5, RefillUtil.formatDateShort(instantRedemption.getFillDate()));

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    amount = BigDecimal.valueOf(Double.valueOf(object.toString()));
                }
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getTotalBenefitAmountIRF", e);
        }

        return amount;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public BigDecimal getTotalBenefitAmountDRF(DailyRedemption dailyRedemption) {

        BigDecimal amount = null;

        try {

            String sql = "SELECT SUM(drive_irf.Tot_Drug_Cost_Paid_To_Pharmacy) AS Total_Benefit_Amount FROM "
                    + "( "
                    + "SELECT "
                    + "drf.Cardholder_First_Name AS Cardholder_First_Name,"
                    + "drf.Cardholder_Last_Name AS Cardholder_Last_Name, "
                    + "drf.Cardholder_DOB, "
                    + "drf.Cardholder_Gender, "
                    + "drf.NDC_Number, "
                    + "drf.Claim_Status, "
                    + "drf.Fill_Date,"
                    + "drf.Tot_Drug_Cost_Paid_To_Pharmacy,"
                    + "drf.Prescription_Number,"
                    + "drf.CampaignId AS CampaignId "
                    + "FROM DailyRedemption drf "
                    + "GROUP BY drf.Communication_Id, drf.Rx_Group_Number, drf.Cardholder_DOB, drf.Fill_Date,drf.NDC_Number, drf.Transaction_Number,drf.CampaignId "
                    + "HAVING COUNT(*) = 1 "
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Cardholder_First_Name = ? "
                    + "AND drive_irf.Cardholder_Last_Name = ? "
                    + "AND drive_irf.Cardholder_DOB = ? "
                    + "AND drive_irf.Cardholder_Gender = ? "
                    + "AND drive_irf.CampaignId = ? "
                    + "AND YEAR(drive_irf.Fill_Date) = YEAR(?)";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, dailyRedemption.getCardholderFirstName());
            query.setString(1, dailyRedemption.getCardholderLastName());
            query.setString(2, RefillUtil.formatDateShort(dailyRedemption.getCardholderDob()));
            query.setString(3, dailyRedemption.getCardholderGender().toString());
            query.setInteger(4, dailyRedemption.getCampaignId());
            query.setString(5, RefillUtil.formatDateShort(dailyRedemption.getFillDate()));

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    amount = BigDecimal.valueOf(Double.valueOf(object.toString()));
                }
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getTotalBenefitAmountDRF", e);
        }

        return amount;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
     * *******************************************************************************************
     */
    public int getTotalRedemptionCountIRF(InstantRedemption instantRedemption) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Redemption_Count FROM "
                    + "( "
                    + "SELECT "
                    + "irf.Cardholder_First_Name, "
                    + "irf.Cardholder_Last_Name, "
                    + "irf.Cardholder_DOB, "
                    + "irf.Cardholder_Gender, "
                    + "irf.NDC_Number, "
                    + "irf.Claim_Status, "
                    + "irf.Fill_Date, "
                    + "irf.CampaignId AS CampaignId "
                    + "FROM InstantRedemption irf "
                    + "GROUP BY irf.Communication_Id, irf.Rx_Group_Number, irf.Cardholder_DOB, irf.Fill_Date,irf.NDC_Number, irf.Transaction_Number,irf.CampaignId "
                    + "HAVING COUNT(*) = 1 "
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Cardholder_First_Name = ? "
                    + "AND drive_irf.Cardholder_Last_Name = ? "
                    + "AND drive_irf.Cardholder_Gender = ? "
                    + "AND drive_irf.Cardholder_DOB = ? "
                    + "AND YEAR(drive_irf.Fill_Date) = YEAR(NOW()) "
                    + "AND drive_irf.CampaignId = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, instantRedemption.getCardholderFirstName());
            query.setString(1, instantRedemption.getCardholderLastName());
            query.setString(2, instantRedemption.getCardholderGender().toString());
            query.setString(3, RefillUtil.formatDateShort(instantRedemption.getCardholderDob()));
            query.setInteger(4, instantRedemption.getCampaignId());

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getTotalRedemptionCountIRF", e);
        }

        return count;
    }

    /**
     *********************************************************************************************
     *********************************************************************************************
     *********************************************************************************************
     */
    public int getTotalRedemptionCountDRF(DailyRedemption dailyRedemption) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) AS Redemption_Count FROM "
                    + "( "
                    + "SELECT "
                    + "drf.Cardholder_First_Name, "
                    + "drf.Cardholder_Last_Name, "
                    + "drf.Cardholder_DOB, "
                    + "drf.Cardholder_Gender, "
                    + "drf.NDC_Number, "
                    + "drf.Claim_Status, "
                    + "drf.Fill_Date, "
                    + "drf.CampaignId AS CampaignId "
                    + "FROM DailyRedemption drf "
                    + "GROUP BY drf.Communication_Id, drf.Rx_Group_Number, drf.Cardholder_DOB, drf.Fill_Date,drf.NDC_Number, drf.Transaction_Number,drf.CampaignId "
                    + "HAVING COUNT(*) = 1 "
                    + ") drive_irf "
                    + "WHERE "
                    + "drive_irf.Claim_Status = 0 "
                    + "AND drive_irf.Cardholder_First_Name = ? "
                    + "AND drive_irf.Cardholder_Last_Name = ? "
                    + "AND drive_irf.Cardholder_Gender = ? "
                    + "AND drive_irf.Cardholder_DOB = ? "
                    + "AND YEAR(drive_irf.Fill_Date) = YEAR(NOW()) "
                    + "AND drive_irf.CampaignId = ? ";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setString(0, dailyRedemption.getCardholderFirstName());
            query.setString(1, dailyRedemption.getCardholderLastName());
            query.setString(2, dailyRedemption.getCardholderGender().toString());
            query.setString(3, RefillUtil.formatDateShort(dailyRedemption.getCardholderDob()));
            query.setInteger(4, dailyRedemption.getCampaignId());

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {
                Object object = queryResult.get(0);

                if (object != null) {
                    count = Integer.valueOf(object.toString());
                }
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getTotalRedemptionCountDRF", e);
        }

        return count;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
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
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesResponseByResponseName", e);
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
                    + "inner join fetch campaignMessagesResponse.campaignMessages campaignResponse "
                    + "where "
                    + " campaignMessagesResponse.campaignMessages.campaigns.campaignId = :campaignId "
                    + "and campaignMessagesResponse.response.responseTitle = :responseTitle "
                    + "and campaignMessagesResponse.campaignMessages.messageType.folder.folderId = :folderId ";
//                    + "and campaignMessagesResponse.communicationPath = :communicationPath ";

            logger.error("HQL : " + hql);

            List<CampaignMessagesResponse> list = (List<CampaignMessagesResponse>) this.getCurrentSession().createQuery(hql)
                    .setParameter("campaignId", campaignId)
                    .setParameter("responseTitle", responseTitle)
                    .setParameter("folderId", folderId)
//                    .setParameter("communicationPath", communicationPath)
                    .list();

            if (list != null && list.size() > 0) {
                campaignMessagesResponse = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesResponseByResComm", e);
        }
        return campaignMessagesResponse;
    }

    /**
     * *******************************************************************************************
     * *******************************************************************************************
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
            logger.error("Exception:RedemptionDAO -> getCampaignMessagesByCommunicationPath", e);
        }

        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public int getPerPrescriptionCountDRF(DailyRedemption dailyRedemption) {

        int amount = 0;

        try {

            String sql = "SELECT COUNT(*) AS Redemption_Count FROM "
                    + "( "
                    + "SELECT "
                    + "drf.Cardholder_First_Name, "
                    + "drf.Cardholder_Last_Name, "
                    + "drf.Cardholder_DOB, "
                    + "drf.Cardholder_Gender, "
                    + "drf.NDC_Number, "
                    + "drf.Claim_Status, "
                    + "drf.Fill_Date,"
                    + "drf.Prescription_Number,"
                    + "drf.CampaignId "
                    + "FROM DailyRedemption drf "
                    + "GROUP BY drf.Communication_Id, drf.Rx_Group_Number, drf.Cardholder_DOB, drf.Fill_Date,drf.NDC_Number, drf.Transaction_Number,drf.CampaignId "
                    + "HAVING COUNT(*) = 1 "
                    + ") drive_drf "
                    + "WHERE "
                    + "drive_drf.Claim_Status = 0 "
                    + "AND drive_drf.Cardholder_First_Name = '" + dailyRedemption.getCardholderFirstName() + "' "
                    + "AND drive_drf.Cardholder_Last_Name = '" + dailyRedemption.getCardholderLastName() + "' "
                    + "AND drive_drf.Cardholder_DOB = '" + RefillUtil.formatDateShort(dailyRedemption.getCardholderDob()) + "' "
                    + "AND drive_drf.Cardholder_Gender = '" + dailyRedemption.getCardholderGender() + "' "
                    + "AND drive_drf.Prescription_Number = '" + dailyRedemption.getPrescriptionNumber() + "' "
                    + "AND YEAR(drive_drf.Fill_Date) = YEAR(NOW()) "
                    + "AND drive_drf.CampaignId = '" + dailyRedemption.getCampaignId() + "'";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            List<Object[]> queryResult = query.list();

            if (queryResult != null && (!queryResult.isEmpty())) {

                Object object = queryResult.get(0);

                if (object != null) {
                    amount = Integer.valueOf(object.toString());
                }
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getPerPrescriptionCountDRF", e);
        }

        return amount;
    }

    public List<Object[]> getMessageCode(String ndc) {

        List<Object[]> messageList = null;

        try {
            String sql = "SELECT msg.*, `CampaignId`"
                    + " FROM  ClinicalMessage msg "
                    + "	INNER JOIN ClinicalMessageSetup setup ON msg.MessageCategoryId = setup.MessageCategoryId "
                    + " WHERE ndc = '" + ndc + "'";

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

            messageList = query.list();

        } catch (HibernateException e) {
            logger.error("Exception:RedemptionDAO -> getMessageCode", e);
        }

        return messageList;
    }
    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public List<DailyRedemption> getPaidOnlyDRFRecords(int daysBack) {

        List<DailyRedemption> dailyRedemptionList = new ArrayList<DailyRedemption>();
        DailyRedemption dailyRedemption = null;
        DailyRedemptionId id = null;

        try {
            String sql = "SELECT "
                    + "drive_drf.Transaction_Number, "
                    + "drive_drf.Claim_Status "
                    + "FROM "
                    + "( "
                    + "SELECT  "
                    + "drf.Transaction_Number AS Transaction_Number, "
                    + "drf.Claim_Status AS Claim_Status, "
                    + "drf.Effective_Date AS Effective_Date, "
                    + "drf.Fill_Date AS Fill_Date "
                    + "FROM DailyRedemption drf "
                    + "GROUP BY drf.Transaction_Number "
                    + "HAVING COUNT(*) = 1 "
                    + ")drive_drf "
                    + "WHERE "
                    + "drive_drf.Claim_Status = 0 "
                    + "AND drive_drf.Effective_Date >= DATE_SUB(NOW(),INTERVAL ? DAY) "
                    + "AND drive_drf.Effective_Date <= NOW() "
                    + "ORDER BY drive_drf.Fill_Date ASC";
            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setLong(0, daysBack);

            List<Object[]> queryResult = query.list();

            String tranId = "";
            int claimStatus = 0;

            Object value = null;

            for (Object[] record : queryResult) {

                dailyRedemption = new DailyRedemption();
                id = new DailyRedemptionId();

                value = record[0];
                if (value != null) {
                    tranId = value.toString();
                    id.setTransactionNumber(tranId);
                }

                value = record[1];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    id.setClaimStatus(claimStatus);
                }

                dailyRedemption.setId(id);
                dailyRedemptionList.add(dailyRedemption);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getPaidOnlyDRFRecords", e);
        }
        return dailyRedemptionList;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public int getDRFProcessedRedmeptionCount(DailyRedemption dailyRedemption) {

        int count = 0;

        try {

            String hql = "select count(*) from Drfprocessed drfprocessed "
                    + "where "
                    + "  drfprocessed.cardholderFirstName = :cardholderFirstName "
                    + "and drfprocessed.cardholderLastName = :cardholderLastName "
                    + "and drfprocessed.cardholderDob = :cardholderDob "
                    + "and drfprocessed.cardholderGender = :cardholderGender "
                    + "and drfprocessed.campaignId = :campaignId ";

            List<Drfprocessed> list = (List<Drfprocessed>) this.getCurrentSession().createQuery(hql)
                    .setParameter("cardholderFirstName", dailyRedemption.getCardholderFirstName())
                    .setParameter("cardholderLastName", dailyRedemption.getCardholderLastName())
                    .setParameter("cardholderDob", dailyRedemption.getCardholderDob())
                    .setParameter("cardholderGender", dailyRedemption.getCardholderGender())
                    .setParameter("campaignId", dailyRedemption.getCampaignId())
                    .list();

            if (list != null && !list.isEmpty()) {
                count = 1;
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getDRFProcessedRedmeptionCount", e);
        }

        return count;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public int getDRFProcessedCountById(int claimStatus, String transactionNumber) {

        int count = 0;

        try {

            String hql = "select drfprocessed from Drfprocessed drfprocessed "
                    + "where "
                    + "  drfprocessed.id.claimStatus = :claimStatus "
                    + "and drfprocessed.id.transactionNumber = :transactionNumber";

            List<Drfprocessed> list = (List<Drfprocessed>) this.getCurrentSession().createQuery(hql)
                    .setParameter("claimStatus", claimStatus)
                    .setParameter("transactionNumber", transactionNumber)
                    .list();

            if (list != null && !list.isEmpty()) {
                count = 1;
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getDRFProcessedCountById", e);
        }

        return count;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public List<DailyRedemption> getCancelledDRFRecords(int daysBack) {

        List<DailyRedemption> dailyRedemptionList = new ArrayList<DailyRedemption>();
        DailyRedemption dailyRedemption = null;
        DailyRedemptionId id = null;

        try {
            String sql = "SELECT "
                    + "drive_drf.Transaction_Number, "
                    + "drive_drf.Claim_Status "
                    + "FROM "
                    + "( "
                    + "SELECT  "
                    + "drf.Transaction_Number AS Transaction_Number, "
                    + "MIN(drf.Claim_Status) AS Claim_Status, "
                    + "drf.Effective_Date AS Effective_Date, "
                    + "drf.Fill_Date AS Fill_Date "
                    + "FROM DailyRedemption drf "
                    + "GROUP BY drf.Transaction_Number "
                    + "HAVING COUNT(*) = 2 "
                    + ")drive_drf "
                    + "WHERE "
                    + "drive_drf.Claim_Status = 0 "
                    + "AND drive_drf.Effective_Date >= DATE_SUB(NOW(),INTERVAL ? DAY) "
                    + "AND drive_drf.Effective_Date <= NOW() "
                    + "ORDER BY drive_drf.Fill_Date ASC";
            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setLong(0, daysBack);

            List<Object[]> queryResult = query.list();

            String tranId = "";
            int claimStatus = 0;

            Object value = null;

            for (Object[] record : queryResult) {

                dailyRedemption = new DailyRedemption();
                id = new DailyRedemptionId();

                value = record[0];
                if (value != null) {
                    tranId = value.toString();
                    id.setTransactionNumber(tranId);
                }

                value = record[1];
                if (value != null) {
                    claimStatus = Integer.valueOf(value.toString());
                    id.setClaimStatus(claimStatus);
                }

                dailyRedemption.setId(id);
                dailyRedemptionList.add(dailyRedemption);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getCancelledDRFRecords", e);
        }
        return dailyRedemptionList;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public boolean deleteDRFProcessed(Drfprocessed drfprocessed) {
        boolean flag = false;
        try {

            this.getCurrentSession().delete(drfprocessed);
            flag = true;

            logger.error("DRFProcessed Deleted.");

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> deleteDRFProcessed", e);
            flag = false;
        }
        return flag;
    }

    public List<Drfprocessed> getDRFProcessedRecordForPatient(Drfprocessed drfprocessed) {
        List<Drfprocessed> list = null;
        try {
            String hql = "select * from Drfprocessed drfprocessed "
                    + "where "
                    + "  drfprocessed.cardholderFirstName = :cardholderFirstName "
                    + "and drfprocessed.cardholderLastName = :cardholderLastName "
                    + "and drfprocessed.cardholderDob = :cardholderDob "
                    + "and drfprocessed.cardholderGender = :cardholderGender "
                    + "and drfprocessed.campaignId = :campaignId "
                    + "order by drfprocessed.fillDate ASC";

            list = (List<Drfprocessed>) this.getCurrentSession().createQuery(hql)
                    .setParameter("cardholderFirstName", drfprocessed.getCardholderFirstName())
                    .setParameter("cardholderLastName", drfprocessed.getCardholderLastName())
                    .setParameter("cardholderDob", drfprocessed.getCardholderDob())
                    .setParameter("cardholderGender", drfprocessed.getCardholderGender().toString())
                    .setParameter("campaignId", drfprocessed.getCampaignId())
                    .list();

        } catch (Exception e) {
        }
        return list;
    }

    /**
     * ***********************************************************************************************
     * ***********************************************************************************************
     * ***********************************************************************************************
     */
    public Drfprocessed getDRFProcessedById(int claimStatus, String transactionNumber) {

        Drfprocessed drfprocessed = null;

        try {

            String hql = "select drfprocessed from Drfprocessed drfprocessed "
                    + "where "
                    + "  drfprocessed.id.claimStatus = :claimStatus "
                    + "and drfprocessed.id.transactionNumber = :transactionNumber";

            List<Drfprocessed> list = (List<Drfprocessed>) this.getCurrentSession().createQuery(hql)
                    .setParameter("claimStatus", claimStatus)
                    .setParameter("transactionNumber", transactionNumber)
                    .list();

            if (list != null && !list.isEmpty()) {
                drfprocessed = new Drfprocessed();
                drfprocessed = list.get(0);
            }

        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getDRFProcessedById", e);
        }

        return drfprocessed;
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
            logger.error("Exception:RedemptionDAO -> getEventsDescription", e);
        }

        return events;
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
            logger.error("Exception:RedemptionDAO -> getInstantRedemptionDetailByRedemptionId", e);
        }
        return irf;
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
            logger.error("Exception:RedemptionDAO -> isMmsExists", e);
        }

        return flag;
    }

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
            logger.error("Exception:RedemptionDAO -> getIRFLastRedemptionSecondsByPhone", e);
        }

        return seconds;
    }

    public String getCommunictionIdByMemberId(String messageId, String dob) throws Exception {
        String sql = "SELECT Phone_Number FROM CustomerRequest WHERE Card_Number = '" + messageId + "' and YearOfBirth = '" + dob + "' ORDER BY Effective_Date DESC LIMIT 1";
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        List<String> queryResult = query.list();
        String communictionId = "";
        if (queryResult != null && (!queryResult.isEmpty())) {
            communictionId = queryResult.get(0);
        }
        return communictionId;
    }

    public List<CampaignMessages> getCampaignMessagesByMsgId(int messageId) throws Exception {

        String hql = "select campaignMessages from CampaignMessages campaignMessages "
                + "where "
                + "    campaignMessages.messageId = :messageId ";

        List<CampaignMessages> list = (List<CampaignMessages>) this.getCurrentSession().createQuery(hql)
                .setParameter("messageId", messageId)
                .list();

        return list;
    }

    public int getCurrentMonthMessageCount(int campaignId, String communicationId) {
        int count = 0;
        String hql = "select count(*) from ClinicalMessageLog "
                + " where campaignId = " + campaignId + " and communicationId = '" + communicationId + "'"
                + " AND EffectiveDate >= LAST_DAY(CURDATE()) + INTERVAL 1 DAY - INTERVAL 1 MONTH "
                + " AND EffectiveDate < LAST_DAY(CURDATE()) + INTERVAL 1 DAY  ";

        List<Object> list = (List<Object>) this.getCurrentSession().createSQLQuery(hql).list();
        if (list != null && list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public List<Object[]> getCurrentMonthMessageQueueList() {
        String hql = "SELECT * FROM ClinicalMessageQueue WHERE ScheduleMonth =  MONTH(CURDATE())";
        return (List<Object[]>) this.getCurrentSession().createSQLQuery(hql).list();
    }

    public boolean deleteMessageFromQueue(String queueId) {
        boolean flag = false;
        try {
            String hql = "Delete FROM ClinicalMessageQueue WHERE queueId =  " + queueId;
            Query query = getCurrentSession().createQuery(hql);
            query.executeUpdate();
            flag = true;
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> deleteMessageFromQueue", e);
            flag = false;
        }
        return flag;
    }

    public int getNdcMessageQueueCount(int campaignId, String communicationId, String ndc) {
        int count = 0;
        String hql = "select count(*) from ClinicalMessageQueue "
                + " where campaignId = " + campaignId + " and communicationId = '" + communicationId + "'"
                + " AND ndc = '" + ndc + "' ";

        List<Object> list = (List<Object>) this.getCurrentSession().createQuery(hql).list();
        if (list != null && list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public int getMessageQueueCount(int campaignId, String communicationId) {
        int count = 0;
        String hql = "select count(*) from ClinicalMessageQueue "
                + " where campaignId = " + campaignId + " and communicationId = '" + communicationId + "'";

        List<Object> list = (List<Object>) this.getCurrentSession().createQuery(hql).list();
        if (list != null && list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public String getNotificationDescription(int statusCode) throws Exception {

        String description = "";
        NotificationStatus notificationStatus;

        notificationStatus = getNotificationById(statusCode);

        if (notificationStatus != null) {
            description = notificationStatus.getNarrative();
        }

        return description;
    }

    public NotificationStatus getNotificationById(int statusCode) throws Exception {

        NotificationStatus notificationStatus = null;

        Object o = this.getCurrentSession().get(NotificationStatus.class, statusCode);

        if (o != null) {
            notificationStatus = (NotificationStatus) o;
        }

        return notificationStatus;
    }

    public Boolean getPharmacyByNpiNo(Long npi) throws Exception {
        Query query = getCurrentSession().createQuery("From Pharmacy pharmacy where pharmacy.npi=:npi");
        query.setParameter("npi", npi);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Drug getMaxOfferByNdcNo(String ndc) throws Exception {
        Drug drug = new Drug();
        Query query = getCurrentSession().createQuery("from Drug drug where drug.ndcnumber=:ndc");
        query.setParameter("ndc", ndc);
        Object result = query.uniqueResult();
        if (result != null) {
            drug = (Drug) result;
        }
        return drug;
    }

    public EventHasFolderHasCampaigns getEhfhCampaigns(int campaignId, String dynamicValue, String communicationPath) {
        EventHasFolderHasCampaigns eventHasFolderHasCampaigns = new EventHasFolderHasCampaigns();
        try {
            String hql = "SELECT eventHasFolderHasCampaigns from EventHasFolderHasCampaigns eventHasFolderHasCampaigns,Event event "
                    + "WHERE  "
                    + "eventHasFolderHasCampaigns.campaignId = :campaignId "
                    + "and eventHasFolderHasCampaigns.communicationPath=:communicationPath "
                    //                    + "and eventHasFolderHasCampaigns.eventId != 1 "
                    + "and (event.eventId=eventHasFolderHasCampaigns.eventId) "
                    + "and  (event.dynamicValue = :dynamicValue) "
                    + "ORDER BY eventHasFolderHasCampaigns.folderId desc ";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("campaignId", campaignId);
            query.setParameter("communicationPath", communicationPath);
            query.setParameter("dynamicValue", dynamicValue);
            Object result = query.uniqueResult();
            if (result != null) {
                eventHasFolderHasCampaigns = (EventHasFolderHasCampaigns) result;
            }
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> getEHFHCampaigns", e);
        }
        return eventHasFolderHasCampaigns;
    }

    public List<Order> getOrdersByTransactionNo(String transactionNo) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord where ord.transactionNo=:transactionNo");
        query.setParameter("transactionNo", transactionNo);
        return query.setMaxResults(1).list();
    }

    public void populateNpiValues(DailyRedemption dailyRedemption) {
        Connection connection = null;
        try {
            PrescriberJdbcDAO.populateDRFPrescriberValues(dailyRedemption, connection);
        } catch (Exception e) {
            logger.error("Exception:RedemptionDAO -> populateNpiValues", e);
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    public String getCampaignByNDCS(List<String> ndcNumber) throws Exception {
        String result = "";
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("SELECT "
                + " drug.NDCNumber "
                + "FROM "
                + "  Drug  drug "
                + "  LEFT OUTER JOIN DrugBrand brand "
                + "    ON drug.DrugBrandId = brand.DrugBrandId "
                + "  LEFT OUTER JOIN CampaignsHasDrugBrand hasBrand "
                + "    ON brand.DrugBrandId = hasBrand.DrugBrandId "
                + "  LEFT OUTER JOIN Campaigns c "
                + "    ON hasBrand.CampaignId = c.CampaignId "
                + " WHERE drug.NDCNumber IN (:ndcNumber)");
        List<Object[]> queryResult = sQLQuery.setParameterList("ndcNumber", ndcNumber).list();
        if (queryResult != null && (!queryResult.isEmpty())) {
            Object object = queryResult.get(0);
            if (object != null) {
                result = object.toString();
            }
        }
        return result;
    }

    public void deletePatientProfileAddress(Integer patientProfileId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("DELETE FROM PatientProfileAddress WHERE PatientProfileAddress.Id IN (SELECT BillingAddressId FROM PatientProfileInfo WHERE PatientProfileInfo.Id IN (:patientProfileId))");
        query.setParameter("patientProfileId", patientProfileId);
        query.executeUpdate();
    }
}
