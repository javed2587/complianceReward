package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.AssignedSurveyLogsDTO;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.surveyQuestionAnswerDTO;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.ActivitesHistory;
import com.ssa.cms.model.AppointmentRequest;
import com.ssa.cms.model.AssignedSurvey;
import com.ssa.cms.model.BloodPressure;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.ComplianceRewardPoint;
import com.ssa.cms.model.DeliveryPreferences;
import com.ssa.cms.model.DiseaseDetail;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBrand;
//import com.ssa.cms.model.DrugCategory;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.DrugDetail2;
import com.ssa.cms.model.DrugGenericTypes;
import com.ssa.cms.model.DrugSearches;
//import com.ssa.cms.model.DrugTherapyClass;
import com.ssa.cms.model.EnrollementIpad;
import com.ssa.cms.model.Faq;
import com.ssa.cms.model.MessageThreads;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderCustomDocumments;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.PatientAllergies;
import com.ssa.cms.model.PatientBodyMassResult;
import com.ssa.cms.model.PatientDeliveryAddress;
import com.ssa.cms.model.PatientGlucoseResults;
import com.ssa.cms.model.PatientHeartPulseResult;
import com.ssa.cms.model.PatientInsuranceDetails;
import com.ssa.cms.model.PatientPoints;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.PatientProfileNotes;
import com.ssa.cms.model.PatientProfilePreferences;
import com.ssa.cms.model.PharmacyPatientMessage;
import com.ssa.cms.model.PharmacyZipCodes;
import com.ssa.cms.model.Practices;
import com.ssa.cms.model.PreferencesSetting;
import com.ssa.cms.model.QuestionAnswer;
import com.ssa.cms.model.ReporterChatSession;
import com.ssa.cms.model.ReporterMessages;
import com.ssa.cms.model.ReporterProfile;
import com.ssa.cms.model.RewardActivity;
import com.ssa.cms.model.RewardHistory;
import com.ssa.cms.model.RewardPoints;
import com.ssa.cms.model.RxRenewal;
import com.ssa.cms.model.RxReporterUsers;
//import com.ssa.cms.model.RxRepQusDetail;
//import com.ssa.cms.model.RxReporterOption;
//import com.ssa.cms.model.RxReporterQuestion;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.Survey;
import com.ssa.cms.model.Survey2;
import com.ssa.cms.model.SurveyAnswer;
import com.ssa.cms.model.SurveyBridge;
//import com.ssa.cms.model.SurveyLogs;
//import com.ssa.cms.model.SurveyLogs;
import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.model.SurveyResponse;
import com.ssa.cms.model.SurveyResponseAnswerDetial;
import com.ssa.cms.model.SurveyTheme;
import com.ssa.cms.model.TransferDetail;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.model.Url;
import com.ssa.cms.model.VegaWalletRewardPoint;
import com.ssa.cms.model.ZipCodeCalculation;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.sun.xml.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.eclipse.persistence.exceptions.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Repository
@Transactional
public class PatientProfileDAO extends BaseDAO {

    public Object getObjectById(Object clz, int id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }

    public Object getObjectById(Class clz, int id) {
        return this.getCurrentSession().get(clz, id);
    }

    public List getAllRecords(Object type) throws Exception {
        return getCurrentSession().createQuery("from " + type.getClass().getName()).list();
    }

    public List getAllRecords(Class entity) throws Exception {
        return getCurrentSession().createQuery("from " + entity.getName()).list();
    }

    public List<PatientProfile> getPatientProfilesList() throws Exception {
        return getCurrentSession().createQuery("From PatientProfile patientProfile "
                + " order by patientProfile.createdOn desc").list();
    }

    public PatientProfile getPatientProfile(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile "
                + " where patientProfile.patientProfileSeqNo=:id");
        query.setParameter("id", id);
        return (PatientProfile) query.uniqueResult();
    }

    public List<Object[]> getPatientProfilesListWithDefaultAddress() throws Exception {
//        return getCurrentSession().createQuery("From PatientProfile p,PatientDeliveryAddress addr "
//                + "  where p.id=addr.patientProfile.id and addr.defaultAddress='Yes' order by p.firstName  ").list();

        return getCurrentSession().createQuery("From PatientProfile p left join p.patientDeliveryAddresses addr "
                + "  where p.id=addr.patientProfile.id and (addr=null or  addr.defaultAddress='Yes' )order by p.firstName  ").list();
    }

    public String getURL(String urlCode) throws Exception {
        Url url = null;
        String urlString = "";

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

        return urlString;
    }

    public boolean updatePatientInfo(PatientProfile patientProfile) throws Exception {
        Query query = getCurrentSession().createQuery("Update PatientProfile set updatedOn=:currentDate,status=:patientStatus,comments=:comments,insuranceProvider=:insuranceProvider,planId=:planId,groupNumber=:groupNumber,memberId=:memberId,insuranceExpiryDate=:expiryDate,providerPhoneNumber=:providerPhoneNo,providerAddress=:providerAddress where id=:id");
        query.setParameter("currentDate", new Date());
        query.setParameter("patientStatus", patientProfile.getStatus());
        query.setParameter("comments", patientProfile.getComments());
        query.setParameter("insuranceProvider", patientProfile.getInsuranceProvider());
        query.setParameter("planId", patientProfile.getPlanId());
        query.setParameter("groupNumber", patientProfile.getGroupNumber());
        query.setParameter("memberId", patientProfile.getMemberId());
        query.setParameter("expiryDate", patientProfile.getInsuranceExpiryDate());
        query.setParameter("providerPhoneNo", patientProfile.getProviderPhoneNumber());
        query.setParameter("providerAddress", patientProfile.getProviderAddress());
        query.setParameter("id", patientProfile.getPatientProfileSeqNo());
        query.executeUpdate();
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public String isCommunicationIdExist(String communicationId) throws Exception {
        String statusCode = "";
        SQLQuery sQLQuery = null;
        if (communicationId.contains("@")) {
            sQLQuery = getCurrentSession().createSQLQuery("SELECT DISTINCT EmailCustomerRequest.`Status_Code` "
                    + "                FROM "
                    + "                `PtProfile_Info` "
                    + "                INNER JOIN `EmailOptInOut` "
                    + "                    ON EmailOptInOut.`Email` = PtProfile_Info.`CommunicationID` "
                    + "                  INNER JOIN `EmailCustomerRequest` "
                    + "                    ON EmailCustomerRequest.`Email` = EmailOptInOut.`Email` "
                    + "                 WHERE PtProfile_Info.`CommunicationID` = ? AND PtProfile_Info.`Status`='Pending'"
                    + "                 ORDER BY EmailCustomerRequest.`Effective_Date` DESC");
        } else {
            sQLQuery = getCurrentSession().createSQLQuery("SELECT "
                    + "  DISTINCT CustomerRequest.`Status_Code` "
                    + "FROM "
                    + "  `PtProfile_Info` "
                    + "  INNER JOIN `OptInOut` "
                    + "    ON OptInOut.`Phone_Number` = PtProfile_Info.`CommunicationID` "
                    + "  INNER JOIN `CustomerRequest` "
                    + "    ON CustomerRequest.`Phone_Number` = OptInOut.`Phone_Number` "
                    + " WHERE PtProfile_Info.`CommunicationID` = ? AND PtProfile_Info.`Status`='Pending'"
                    + " ORDER BY CustomerRequest.`Effective_Date` DESC");
        }

        sQLQuery.setParameter(0, communicationId);
        List<Object[]> list = sQLQuery.list();
        if (list != null && !list.isEmpty()) {
            Object object = list.get(0);
            if (object.toString().equalsIgnoreCase(StatusEnum.COMPLETED.getValue()) || object.toString().equalsIgnoreCase(StatusEnum.IN_PROGRESS.getValue()) || object.toString().equalsIgnoreCase(StatusEnum.STOPPED.getValue())) {
                statusCode = object.toString();
            }
        }
        return statusCode;
    }

    public SmtpServerInfo getSmtpServerInfo(String campaignId) {
        Query query = getCurrentSession().createQuery("From SmtpServerInfo smtpServerInfo left join fetch smtpServerInfo.campaignses campaign where campaign.campaignId=:campaignId");
        query.setParameter("campaignId", Integer.parseInt(campaignId));
        return (SmtpServerInfo) query.list().get(0);
    }

    public boolean isVerificationCodeExist(String phoneNumber, Integer code) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile  where mobileNumber=:phoneNumber and verificationCode=:code");
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("code", code);
        if (query.list().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List retrieveatientByPhoneAndCode(String phoneNumber, Integer code) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where mobileNumber=:phoneNumber and verificationCode=:code");
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("code", code);
        return query.list();
    }

    public boolean isPatientPhoneNumberExist(String phoneNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where mobileNumber=:phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        //query.setParameter("status", Constants.COMPLETED);
        if (query.list().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public RewardPoints getRewardPoints(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From RewardPoints rewardPoints where rewardPoints.id=:id");
        query.setParameter("id", id);
        return (RewardPoints) query.uniqueResult();
    }

    public boolean updateVerificationCode(Integer verificationCode, String mobileNumber) throws Exception {
        Query query = getCurrentSession().createQuery("Update PatientProfile set updatedOn=:currentDate,verificationCode=:verificationCode where mobileNumber=:mobileNumber");
        query.setParameter("currentDate", new Date());
        query.setParameter("verificationCode", verificationCode);
        query.setParameter("mobileNumber", mobileNumber);
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public PatientProfile getPatientProfileByMobileNumber(String mobileNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.mobileNumber=:mobileNumber and status=:status");
        query.setParameter("mobileNumber", mobileNumber);
        query.setParameter("status", Constants.COMPLETED);
        return (PatientProfile) query.uniqueResult();
    }

    public PatientProfile getPatientProfileByPhone(String mobileNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.mobileNumber=:mobileNumber ");
        query.setParameter("mobileNumber", mobileNumber);
        return (PatientProfile) query.uniqueResult();
    }

    public List populateInsCardList(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery(" From PatientInsuranceDetails pm where pm.patientProfile.patientProfileSeqNo=:patientId and isArchived=0");
        query.setParameter("patientId", patientId);
        return query.list();
    }

    public Long populateInsCardCount(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientInsuranceDetails pm where pm.patientProfile.patientProfileSeqNo=:patientId  and isArchived=0");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }
    //////////////////////////////////////////////////////////////////////////////////

    public PatientProfile getPatientProfileByToken(String securityToken) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile "
                + " where patientProfile.securityToken=:securityToken and status=:status");
        query.setParameter("securityToken", securityToken);
        query.setParameter("status", Constants.COMPLETED);
        return (PatientProfile) query.uniqueResult();
    }
    public PatientDeliveryAddress getPatientDeliveryAddressById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress where patientDeliveryAddress.id=:id");
        query.setParameter("id", id);
        return (PatientDeliveryAddress) query.uniqueResult();
    }

    public DeliveryPreferences getDeliveryPreferenceById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From DeliveryPreferences deliveryPreferences where deliveryPreferences.id=:id");
        query.setParameter("id", id);
        return (DeliveryPreferences) query.uniqueResult();
    }

    public PatientProfile getPatientProfileByPhoneNumber(String mobileNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.mobileNumber=:mobileNumber and status=:status");
        query.setParameter("mobileNumber", mobileNumber);
        query.setParameter("status", Constants.PENDING);
        return (PatientProfile) query.uniqueResult();
    }

    public Long getTotalRewardHistoryPoints(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("SELECT SUM(point) FROM RewardHistory WHERE patientId=:patientId AND type='PLUS'");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public List<DrugBrand> getDrugBrandsList(String name) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugBrand drugBrand where drugBrand.drugBrandName like :name or drugBrand.drugGenericTypes.drugGenericName like :name");
        query.setParameter("name", name + "%");
        return query.list();
    }

    public List<Drug> getAllDrug(Integer drugBrandId) throws Exception {
        Query query = getCurrentSession().createQuery("from Drug drug left join fetch drug.drugUnits drugUnits where drug.drugBrand.id=:drugBrandId");
        query.setParameter("drugBrandId", drugBrandId);
        return query.list();
    }
////not exist class and db
//    public List<DrugAdditionalMarginPrices> getDrugAdditionalMarginPrices(Integer id) throws Exception {
//        Query query = getCurrentSession().createQuery("From DrugAdditionalMarginPrices drugAdditionalMarginPrices left join fetch drugAdditionalMarginPrices.drugAdditionalMargin drugAdditionalMargin where drugAdditionalMargin.drugCategory.id=:id");
//        query.setParameter("id", id);
//        return query.list();
//    }

    public Drug getDrugById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug left join fetch drug.drugBrand drugBrand where drug.drugId=:drugId");
        query.setParameter("drugId", id);
        return (Drug) query.uniqueResult();
    }

    public List<NotificationMessages> getNonArchivedNotificationMessagesByProfileId(Integer profileId) throws Exception {
        String hql = "From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.patientProfile patientProfile "
                + "left join fetch notificationMessages.messageType messageType "
                + "where patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.status=:status "
                + "and notificationMessages.isDelete =:isDeleted "
                 + "and messageType.id.messageTypeId != 41 and messageType.id.messageTypeId != 57 "
                + "and notificationMessages.isArchive=:isArchived "
                + "order by notificationMessages.createdOn desc";
          Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        query.setParameter("isArchived", Boolean.FALSE);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
    public List<NotificationMessages> getNotificationMessagesByPatientId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.patientProfile patientProfile and patientProfile.patientProfileSeqNo=:id "
                + "left join fetch notificationMessages.messageType messageType "
                + "where notificationMessages.patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.status=:status and notificationMessages.isDelete =:isDeleted "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
    public List<NotificationMessages> getNotificationMessagesListById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages left join fetch notificationMessages.patientProfile patientProfile left join fetch notificationMessages.messageType messageType where notificationMessages.id=:id and notificationMessages.status=:status order by notificationMessages.createdOn desc");
        query.setParameter("id", id);
        query.setParameter("status", Constants.SUCCESS);
        return query.list();
    }

    ////////////////////////////////////////////////////////////////
    public List<NotificationMessages> getNotificationMessagesListForWaitingResponses(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages left join fetch "
                + " notificationMessages.patientProfile patientProfile "
                + " left join fetch notificationMessages.messageType messageType "
                + " left join fetch notificationMessages.orders ord "
                + " where notificationMessages.isEventFire=0 and messageType.responseRequired=1 "
                + " and patientProfile.patientProfileSeqNo=:patientId and notificationMessages.status=:status and "
                + " ord is not null and ord.orderStatus=16  "
                + " order by notificationMessages.id asc");
        query.setParameter("patientId", patientId);
        query.setParameter("status", Constants.SUCCESS);
        return query.list();
    }

    ///////////////////////////////////////////////////////////////
    public Long getTotalReadNotificationMessages(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From NotificationMessages notificationMessages where notificationMessages.isRead=:isRead and notificationMessages.isDelete =:isDeleted and patientProfile.patientProfileSeqNo=:id");
        query.setParameter("isRead", Boolean.TRUE);
        query.setParameter("id", profileId);
        query.setParameter("isDeleted", "No");
        return (Long) query.uniqueResult();
    }

    public Double getTotalLifeTimeRewardPoints(Integer profileId) throws Exception {
        String hql = "SELECT SUM(currentEarnReward) FROM ComplianceRewardPoint complianceRewardPoint "
                + "WHERE complianceRewardPoint.profile.patientProfileSeqNo=:id  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", profileId);
        return (Double) query.uniqueResult();
    }
      public Double getTotalAvailableRewardPoints(Integer profileId) throws Exception {
        String hql = "SELECT SUM(orders.complianceRewardPoint.currentRemainBalance) FROM Order orders "
                + "WHERE orders.patientProfile.patientProfileSeqNo=:id "
//                + "and orders.orderStatus.id in :ids "
                + "and orders.orderStatus.id in :ids";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", profileId);
        query.setParameterList("ids", new Integer[]{2,23});
        return (Double) query.uniqueResult();
    }
//        public Long getTotalAvailableRewardPoints(Integer profileId) throws Exception {
//        String sql = "SELECT SUM(currentRemainBalance) FROM ComplianceRewardPoint complianceRewardPoint "
//                + "WHERE complianceRewardPoint.profile.patientProfileSeqNo=:id  and complianceRewardPoint.orders.orderStatus.id in :ids";
//        Query query = getCurrentSession().createSQLQuery(sql);
//        query.setParameter("id", profileId);
//        query.setParameterList("ids", new Integer[]{10,23});
//        return (Long) query.uniqueResult();
//    }
        public Long getTotalUnReadNotificationMessages(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From NotificationMessages notificationMessages "
                + "where notificationMessages.isRead=:isRead "
                + "and notificationMessages.isDelete =:isDeleted "
                + "and notificationMessages.messageType.id.messageTypeId in (41,57,2,6)"
                + "and patientProfile.patientProfileSeqNo=:id");
        query.setParameter("isRead", Boolean.FALSE);
        query.setParameter("id", profileId);
        query.setParameter("isDeleted", "No");
        return (Long) query.uniqueResult();
    }

    /////////////////////////////////////////////////////////////////////////
    public Long getInsuranceCardsCount(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From PatientInsuranceDetails details "
                + "where details.isArchived=0 "
                + "and details.patientProfile.patientProfileSeqNo=:id ");
        query.setParameter("id", profileId);
        return (Long) query.uniqueResult();
    }

    ////////////////////////////////////////////////////////////////////////
    public Object getRecordByType(Object type, String title) throws Exception {
        Criteria searchCriteria = getCurrentSession().createCriteria(type.getClass());
        return searchCriteria.add(Restrictions.and(Restrictions.eq("type", title))).uniqueResult();
    }

    public Long getTotalRewardHistoryPointByType(String type, Integer profileId) throws Exception {
        try {
            Query query = getCurrentSession().createQuery("Select sum(rewardHistory.point) From RewardHistory rewardHistory where rewardHistory.type=:type and rewardHistory.patientId=:profileId");
            query.setParameter("type", type);
            query.setParameter("profileId", profileId);
            return (Long) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public List getAllRecords(Class entity, Integer offset, Integer maxResults) throws Exception {
        Query query = getCurrentSession().createQuery("from " + entity.getName());
        query.setMaxResults(maxResults);
        query.setFirstResult(offset);
        return query.list();
    }

    public Long getTotalRecords(Class aClass) throws Exception {
        String queryCount = "Select count(*) from " + aClass.getName();
        Query query = getCurrentSession().createQuery(queryCount);
        return (Long) query.uniqueResult();
    }

    public List getDrugSearch(Class entity, String searchParameter) throws Exception {
        //String str_query = "from DrugCategory drugCategory left join fetch drugCategory.drugTherapyClass drugTherapyClass left join drugTherapyClass.drugGenericTypes drugGenericTypes left join drugGenericTypes.drugBrand";
        String str_queryDrugCategory = "from DrugCategory drugCategory inner join fetch drugCategory.drugTherapyClass drugTherapyClass inner join fetch drugTherapyClass.drugGenericTypes drugGenericTypes"
                + " inner join fetch drugGenericTypes.drugBrand drugBrand inner join fetch drugBrand.drugs drugs where drugCategory.drugCategoryName like '%" + searchParameter + "%' and drugs.drugMacPrice=:macPrice";
        String str_queryDrugTherapyClass = "from DrugTherapyClass drugTherapyClass inner join fetch drugTherapyClass.drugGenericTypes drugGenericTypes "
                + "inner join fetch drugGenericTypes.drugBrand drugBrand inner join fetch drugBrand.drugs drugs where drugTherapyClass.drugTherapyClassName like '%" + searchParameter + "%' and drugs.drugMacPrice=:macPrice";
        String str_queryDrugGenericTypes = "from DrugGenericTypes drugGenericTypes inner join fetch drugGenericTypes.drugBrand drugBrand inner join fetch drugBrand.drugs drugs where drugGenericTypes.drugGenericName like '%" + searchParameter + "%' and drugs.drugMacPrice=:macPrice";
        String str_queryDrugBrand = "from DrugBrand drugBrand inner join fetch drugBrand.drugs drugs where drugBrand.drugBrandName like '%" + searchParameter + "%' and drugs.drugMacPrice=:macPrice";

        List lst_DrugCategory = new ArrayList();

        /**
         * records exist in DrugCategory
         */
        Query query = getCurrentSession().createQuery(str_queryDrugCategory);
        query.setParameter("macPrice", 0d);
        lst_DrugCategory = query.list();
        if (lst_DrugCategory.size() > 0) {
            return lst_DrugCategory;
        }

        /**
         * record exist in Drug Therapy Class
         */
        query = getCurrentSession().createQuery(str_queryDrugTherapyClass);
        lst_DrugCategory = query.list();
        if (lst_DrugCategory.size() > 0) {

            return lst_DrugCategory;
        }

        /**
         * search in Generic types
         */
        query = getCurrentSession().createQuery(str_queryDrugGenericTypes);
        lst_DrugCategory = query.list();
        if (lst_DrugCategory.size() > 0) {

            return lst_DrugCategory;
        }

        /**
         * search in drug brand
         */
        query = getCurrentSession().createQuery(str_queryDrugBrand);
        lst_DrugCategory = query.list();
        if (lst_DrugCategory.size() > 0) {

            return lst_DrugCategory;
        }

        return lst_DrugCategory;
    }

    public CampaignMessagesResponse getCampaignMessagesResponseByCampaignMessageId(Integer messageId) throws Exception {
        Query query = getCurrentSession().createQuery("From CampaignMessagesResponse campaignMessagesResponse left join fetch campaignMessagesResponse.campaignMessages campaignMessages where campaignMessages.messageType.id.messageTypeId=:messageId");
        query.setParameter("messageId", messageId);
        return (CampaignMessagesResponse) query.uniqueResult();
    }

    public List getDrugCategoryList(Integer offset, Integer maxResults) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugCategory drugCategory inner join fetch drugCategory.drugTherapyClass drugTherapyClass inner join fetch drugTherapyClass.drugGenericTypes drugGenericTypes "
                + "inner join fetch drugGenericTypes.drugBrand drugBrand inner join fetch drugBrand.drugs drugs where drugs.drugMacPrice=:macPrice");
        query.setParameter("macPrice", 0d);
        query.setMaxResults(maxResults);
        query.setFirstResult(offset);
        return query.list();
    }

    public Long getTotalRxOrderStatusRecordByPatientId(Integer patientId, Integer orderStatusId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From Order ord join ord.orderStatus orderStatus join ord.patientProfile patientProfile where patientProfile.id=:patientId and orderStatus=:orderStatusId");
        query.setParameter("patientId", patientId);
        query.setParameter("orderStatusId", orderStatusId);
        return (Long) query.uniqueResult();
    }

    public Double getTotalRxOrderSaving(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select Sum(ord.redeemPointsCost) From Order ord join ord.patientProfile patientProfile where patientProfile.id=:patientId");
        query.setParameter("patientId", patientId);
        return (Double) query.uniqueResult();
    }

    public List<Order> getOrdersListByProfileId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus where patientProfile.id=:patientId and orderStatus not in(11,18) order by ord.createdOn desc");
        query.setParameter("patientId", patientId);
        return query.list();
    }

    //////////////////////////////////////////////////////////
    public List<Order> getActiveOrdersListByProfileId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord "
                + "left join fetch ord.patientProfile patientProfile "
                + " left join fetch ord.orderStatus orderStatus "
                + "where patientProfile.patientProfileSeqNo=:patientId "
                //                + "and orderStatus not in(11,16,18) "
                //                + " order by orderStatus.sortBy asc ");
                + " order by ord.updatedAt desc ");
        query.setParameter("patientId", patientId);
        return query.list();
    }

    //////////////////////////////////////////////////////////
    public List<Order> getRefillableOrdersListByProfileId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch "
                + "  ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus where "
                + " patientProfile.patientProfileSeqNo=:patientId and orderStatus  in(5,6,15,8) "
                + " and ord.nextRefillDate<=:nextRefillDate and ord.refillsRemaining>0 "
                + " and ord.rxExpiredDate>:nextRefillDate "
                + " and (ord.refillDone is null or ord.refillDone=0) "
                + " order by ord.createdOn desc ");
        query.setParameter("patientId", patientId);
        query.setDate("nextRefillDate", new Date());
        return query.list();
    }
    /////////////////////////////////////////////////////////

    public List<RewardHistory> getRewardHistoryByProfileId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From RewardHistory reward where reward.patientId=:patientId order by reward.id asc");
        query.setParameter("patientId", patientId);
        return query.list();
    }

    public List<Order> viewOrderReceiptList(Integer patientId, String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus left join fetch ord.drugDetail drugDetail where patientProfile.patientProfileSeqNo=:patientId and ord.id=:orderId order by ord.createdAt desc");
        query.setParameter("patientId", patientId);
        query.setParameter("orderId", orderId);
        return query.list();
    }

    public Long getTotalPlaceOrdersRecordsByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From Order ord join ord.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:profileId and ord.orderStatus in (1,2,3,5,6,8,15,16,17,19)");
        query.setParameter("profileId", profileId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalActiveOrdersRecordsByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From Order ord join ord.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:profileId and ord.orderStatus in (1,2,3,5,6,8,15,17,19)");
        query.setParameter("profileId", profileId);
        return (Long) query.uniqueResult();
    }

    public List<PatientDeliveryAddress> getPatientDeliveryAddressesByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress "
                + "left join fetch patientDeliveryAddress.patientProfile patientProfile "
                + "left join fetch patientDeliveryAddress.state state "
                + "where patientProfile.patientProfileSeqNo=:profileId "
                + "order by patientDeliveryAddress.createdOn desc ");
        query.setParameter("profileId", profileId);
        return query.list();
    }

    public List<PatientDeliveryAddress> getPatientDeliveryAddressesByProfileIdAndAddressType(Integer profileId, String addressType) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress "
                + "left join fetch patientDeliveryAddress.patientProfile patientProfile "
                + "left join fetch patientDeliveryAddress.state state "
                + "where patientProfile.patientProfileSeqNo=:profileId "
                + "AND patientDeliveryAddress.addressType =:addressType "
                + "order by patientDeliveryAddress.createdOn desc ");
        query.setParameter("profileId", profileId);
        query.setParameter("addressType", addressType);
        return query.list();
    }

    public PatientDeliveryAddress getPatientDeliveryAddressByProfileIdAndAddressType(Integer profileId, String addressType) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress "
                + "left join fetch patientDeliveryAddress.patientProfile patientProfile "
                + "left join fetch patientDeliveryAddress.state state "
                + "where patientProfile.patientProfileSeqNo=:profileId "
                + "AND patientDeliveryAddress.addressType =:addressType "
                + "order by patientDeliveryAddress.createdOn desc ");
        query.setParameter("profileId", profileId);
        query.setParameter("addressType", addressType);
        return (PatientDeliveryAddress) query.uniqueResult();
    }

    public PatientDeliveryAddress getPatientDeliveryAddressById(Integer profileId, Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress left join fetch patientDeliveryAddress.patientProfile patientProfile left join fetch patientDeliveryAddress.state state where patientProfile.patientProfileSeqNo=:profileId and patientDeliveryAddress.id=:id");
        query.setParameter("profileId", profileId);
        query.setParameter("id", id);
        return (PatientDeliveryAddress) query.uniqueResult();
    }

    public PatientDeliveryAddress getDefaultPatientDeliveryAddress(Integer profileId) throws Exception {
        PatientDeliveryAddress patientDeliveryAddress = new PatientDeliveryAddress();
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress left join fetch patientDeliveryAddress.patientProfile patientProfile left join fetch patientDeliveryAddress.state state where patientProfile.patientProfileSeqNo=:profileId and patientDeliveryAddress.defaultAddress='Yes' Order by patientDeliveryAddress.createdOn desc");
        query.setParameter("profileId", profileId);
        if (!CommonUtil.isNullOrEmpty(query.list())) {
            patientDeliveryAddress = (PatientDeliveryAddress) query.list().get(0);
        }
        return patientDeliveryAddress;
    }

    public List<PatientDeliveryAddress> getPatientDeliveryAddressListById(Integer addresId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress left join fetch patientDeliveryAddress.patientProfile patientProfile left join fetch patientDeliveryAddress.state state where patientDeliveryAddress.id !=:id and patientDeliveryAddress.defaultAddress='Yes'");
        query.setParameter("id", addresId);
        return query.list();
    }

    public List<PharmacyZipCodes> getPharmacyZipCodesList() throws Exception {
        return getCurrentSession().createQuery("select distinct pharmacyZipCodes From PharmacyZipCodes pharmacyZipCodes left join fetch pharmacyZipCodes.deliveryDistanceFeesList deliveryDistanceFeesList left join fetch deliveryDistanceFeesList.deliveryDistances deliveryDistances left join fetch deliveryDistanceFeesList.deliveryPreferenceses deliveryPreferenceses ").list();
    }

    public void updateDeliveryPreferencesByProfileId(Integer profileId, Integer dprefId, String status, String deliveryFee, String distance) throws Exception {
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("Update PatientProfileInfo SET DeliveryPreferenceId=:dprefId,Status=:status,DeliveryFee=:deliveryFee,Distance=:distance where Id=:profileId");
        sQLQuery.setParameter("dprefId", dprefId);
        sQLQuery.setParameter("status", status);
        sQLQuery.setParameter("deliveryFee", deliveryFee);
        sQLQuery.setParameter("distance", distance);
        sQLQuery.setParameter("profileId", profileId);
        sQLQuery.executeUpdate();
    }

    public Long getTotalDeliveryAddressByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientDeliveryAddress patientDeliveryAddress join patientDeliveryAddress.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:profileId");
        query.setParameter("profileId", profileId);
        return (Long) query.uniqueResult();
    }

    public boolean updateTransferRequest(Integer profileId, Integer transferRxId, Integer devliveryAddressId, Integer paymentId, Integer dprefId, String zip, String miles, String deliveryFee) throws Exception {
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("Update TransferRequest SET PatientDeliveryAddressId=:devliveryAddressId,PatientPaymentInfoId=:paymentId,DeliveryPreferencesId=:dprefId,Zip=:zip,Miles=:miles,DeliveryFee=:deliveryFee where Id=:transferRxId and PatientId=:profileId");
        sQLQuery.setParameter("devliveryAddressId", devliveryAddressId);
        sQLQuery.setParameter("paymentId", paymentId);
        sQLQuery.setParameter("dprefId", dprefId);
        sQLQuery.setParameter("zip", zip);
        sQLQuery.setParameter("miles", miles);
        sQLQuery.setParameter("deliveryFee", deliveryFee);
        sQLQuery.setParameter("transferRxId", transferRxId);
        sQLQuery.setParameter("profileId", profileId);

        if (sQLQuery.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List getDrugCategoryListAll(Integer offset, Integer maxResults) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugCategory drugCategory inner join fetch drugCategory.drugTherapyClass drugTherapyClass inner join fetch drugTherapyClass.drugGenericTypes drugGenericTypes "
                + "inner join fetch drugGenericTypes.drugBrand drugBrand inner join fetch drugBrand.drugs drugs inner join fetch drugs.drugUnits drugUnits");
        query.setMaxResults(maxResults);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * Update Allergies
     *
     * @param patientProfile
     * @return
     * @throws Exception
     */
    public boolean updatePatientInfoAllergies(PatientProfile patientProfile) throws Exception {
        Query query = getCurrentSession().createQuery("Update PatientProfile set updatedOn=:currentDate,allergies=:allergies where id=:id");
        query.setParameter("currentDate", new Date());
        query.setParameter("allergies", patientProfile.getAllergies());
        query.setParameter("id", patientProfile.getPatientProfileSeqNo());
        query.executeUpdate();
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<RewardHistory> getRewardHistorysByPatientId(Integer patientId) {
        Query query = getCurrentSession().createQuery("From RewardHistory rewardHistory where rewardHistory.patientId=:patientId order by rewardHistory.id desc");
        query.setParameter("patientId", patientId);
        return query.list();
    }
////////////////////////////not exist class and db//////////////////////////////
//    public List<DrugCategory> searchDrugCategoryListByParameter(String name) throws Exception {
//        Query query = getCurrentSession().createQuery("From DrugCategory drugCategory where drugCategory.drugCategoryName like :name");
//        query.setParameter("name", name + "%");
//        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
//        return query.list();
//    }

    //    public List<DrugTherapyClass> searchDrugTherapyClassListByParameter(Integer drugCatId, String drugTherapyClassname) throws Exception {
//        Query query = getCurrentSession().createQuery("From DrugTherapyClass drugTherapyClass where drugTherapyClass.drugTherapyClassName like :name and drugTherapyClass.drugCategory.id = :catId");
//        query.setParameter("name", drugTherapyClassname + "%");
//        query.setParameter("catId", drugCatId);
//        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
//        return query.list();
//    }
    public List<DrugGenericTypes> searchDrugGenericTypesListByParameter(Integer therapyClassId, String drugGenericType) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugGenericTypes drugGenericTypes where drugGenericTypes.drugGenericName like :name and drugGenericTypes.drugTherapyClass.id = :catId");
        query.setParameter("name", drugGenericType + "%");
        query.setParameter("catId", therapyClassId);
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<DrugBrand> searchDrugBrandNameListByParameter(Integer genericTypeId, String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugBrand drugBrand where drugBrand.drugBrandName like :name and drugBrand.drugGenericTypes.id = :catId");
        query.setParameter("name", drugBrandName + "%");
        query.setParameter("catId", genericTypeId);
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<DrugSearches> getDrugSearchesList(Integer profileId) throws Exception {
        //Query query = getCurrentSession().createQuery("From DrugSearches drugSearches left join fetch drugSearches.patientProfile patientProfile left join fetch drugSearches.drug drug where patientProfile.id=:profileId order by drugSearches.createdOn desc");
        Query query = getCurrentSession().createQuery("From DrugSearches drugSearches left join fetch drugSearches.patientProfile patientProfile left join fetch drugSearches.drugDetail drug where patientProfile.id=:profileId order by drugSearches.createdOn desc");
        query.setParameter("profileId", profileId);
        return query.list();
    }

    public boolean deleteDrugSearchesRecordById(Integer id) throws Exception {
        SQLQuery query = getCurrentSession().createSQLQuery("DELETE FROM DrugSearches WHERE Id=:id");
        query.setParameter("id", id);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean deleteDrugImages(String orderId) throws Exception {
        String sql = "DELETE FROM OrderTransferImages WHERE OrderId = :orderId";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setString("orderId", orderId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean deleteAllDrugSearchesRecordByProfileId(Integer profileId) throws Exception {
        SQLQuery query = getCurrentSession().createSQLQuery("Delete from DrugSearches where PatientId=:profileId");
        query.setParameter("profileId", profileId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean isCardNumberExist(String cardNumber, Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("from PatientPaymentInfo patientPaymentInfo left join fetch patientPaymentInfo.patientProfile patientProfile where patientPaymentInfo.cardNumber=:cardNumber and patientProfile.id=:profileId");
        query.setParameter("cardNumber", cardNumber);
        query.setParameter("profileId", profileId);
        if (!CommonUtil.isNullOrEmpty(query.list())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public PatientInsuranceDetails getPatientInsuranceDetailByPatientIdAndCardNo(Long cardNumber, Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("from PatientInsuranceDetails patientInsuranceDetails left join fetch patientInsuranceDetails.patientProfile patientProfile where patientInsuranceDetails.id=:cardNumber and patientProfile.patientProfileSeqNo=:profileId ");
        query.setParameter("cardNumber", cardNumber);
        query.setParameter("profileId", profileId);
        return (PatientInsuranceDetails) query.uniqueResult();
    }

    public PatientInsuranceDetails getPatientInsuranceDetailByPatientId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientInsuranceDetails patientInsuranceDetails where patientInsuranceDetails.patientProfile.id=:id");
        query.setParameter("id", id);
        return (PatientInsuranceDetails) query.uniqueResult();
    }

    public boolean isDefaultPatientDeliveryAddress(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress left join fetch patientDeliveryAddress.patientProfile patientProfile where patientProfile.id=:id and patientDeliveryAddress.defaultAddress=:defaultAddress");
        query.setParameter("id", profileId);
        query.setParameter("defaultAddress", "Yes");
        if (!CommonUtil.isNullOrEmpty(query.list())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public ZipCodeCalculation getZipCodeCalculationByProfileId(Integer profileId, Integer deliveryPreferencesId, String zipCode) throws Exception {
        ZipCodeCalculation zipCodeCalculation = new ZipCodeCalculation();
        Query query = getCurrentSession().createQuery("From ZipCodeCalculation zipCodeCalculation where zipCodeCalculation.patientId=:patientId and zipCodeCalculation.deliveryPreferencesId=:deliveryPreferencesId and zipCodeCalculation.zip=:zipCode order by zipCodeCalculation.createdOn desc");
        query.setParameter("patientId", profileId);
        query.setParameter("deliveryPreferencesId", deliveryPreferencesId);
        query.setParameter("zipCode", zipCode);
        if (!CommonUtil.isNullOrEmpty(query.list())) {
            zipCodeCalculation = (ZipCodeCalculation) query.list().get(0);
        }
        return zipCodeCalculation;
    }

    public List<ZipCodeCalculation> getZipCodeCalculationsList(String zip, Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From ZipCodeCalculation zipCodeCalculation where zipCodeCalculation.zip=:zip and zipCodeCalculation.patientId=:profileId");
        query.setParameter("zip", zip);
        query.setParameter("profileId", profileId);
        return query.list();
    }

    public ZipCodeCalculation getZipCodeCalculations(String zip, Integer profileId, Integer prefId) throws Exception {
        Query query = getCurrentSession().createQuery("From ZipCodeCalculation zipCodeCalculation where zipCodeCalculation.zip=:zip and zipCodeCalculation.patientId=:profileId and zipCodeCalculation.deliveryPreferencesId=:prefId");
        query.setParameter("zip", zip);
        query.setParameter("profileId", profileId);
        query.setParameter("prefId", prefId);
        List lst = query.list();
        if (lst != null && lst.size() > 0) {
            return (ZipCodeCalculation) lst.get(0);
        }
        return null;

    }

    public List<TransferRequest> geTransferRequestsList(Integer profileId, Integer transferId) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(TransferRequest.class);
        criteria.createCriteria("patientDeliveryAddress", "patientDeliveryAddress", JoinType.LEFT_OUTER_JOIN);
        criteria.createCriteria("patientPaymentInfo", "patientPaymentInfo", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("patientId", profileId));
        criteria.add(Restrictions.eq("id", transferId));
        return criteria.list();
    }

    public boolean updatePreviousDefaultAddress(Integer profileId, String defaultAddress) throws Exception {
        Query query = getCurrentSession().createQuery("Update PatientDeliveryAddress set updatedOn=:currentDate,defaultAddress=:defaultAddress where patientProfile.patientProfileSeqNo=:profileId and defaultAddress='Yes' and addressType='CurrentAddress'");
        query.setParameter("currentDate", new Date());
        query.setParameter("defaultAddress", defaultAddress);
        query.setParameter("profileId", profileId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public PatientProfile getPatientProfileBySecurityToken(String securityToken) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.securityToken=:securityToken and patientProfile.status=:status ");
        query.setParameter("securityToken", securityToken);
        query.setParameter("status", Constants.COMPLETED);
        return (PatientProfile) query.uniqueResult();
    }

    public List<PatientProfileNotes> getPatientProfileNotesListByPtProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfileNotes c left join fetch c.patientProfile patientProfile WHERE patientProfile.id =:profileId ORDER BY c.createdOn desc");
        query.setParameter("profileId", profileId);
        return query.list();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public List getPatientProfilesHistory(int patientId, String status) throws Exception {
        String sql = " select distinct o From Order o  "
                + " left join fetch o.patientProfile   "
                + " left join fetch o.rewardHistorySet r  "
                + " where o.patientProfile.id=:patientId and (r.type='PLUS' OR r.type=NULL)";
        if (AppUtil.getSafeStr(status, "").length() > 0) {
            sql += " and o.orderStatus.name=:status";
        }
        sql += " order by o.createdOn desc";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientId", patientId);
        if (AppUtil.getSafeStr(status, "").length() > 0) {
            query.setParameter("status", status);
        }
        return query.list();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public List getPatientProfilesHistoryOtherThanPending(int patientId) throws Exception {
        String sql = " select distinct o From Order o  "
                + " left join fetch o.patientProfile "
                + " left join fetch o.rewardHistorySet r  "
                + " where o.patientProfile.id=:patientId and (r.type='PLUS' OR r.type=NULL)"
                + " and o.orderStatus.name!='Pending'"
                + " and o.orderStatus.name!='Cancelled'"
                + " order by o.createdOn desc ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientId", patientId);

        return query.list();
    }
    ///////////////////////////////////////////////////////////////////////////////////

    public boolean isDefaultDeliveryAddress(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientDeliveryAddress patientDeliveryAddress left join fetch patientDeliveryAddress.patientProfile patientProfile where patientDeliveryAddress.defaultAddress='Yes' and patientProfile.id=:profileId");
        query.setParameter("profileId", profileId);
        if (query.list() != null && !query.list().isEmpty() && query.list().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean isDefaultPaymentInfo(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientPaymentInfo patientPaymentInfo left join fetch patientPaymentInfo.patientProfile patientProfile where patientPaymentInfo.defaultCard='Yes' and patientProfile.id=:profileId");
        query.setParameter("profileId", profileId);
        if (query.list() != null && !query.list().isEmpty() && query.list().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public TransferDetail getTransferDetailByTranferRequestId(int requestId) {
        TransferDetail transferdetail = null;
        Query query = getCurrentSession().createQuery(
                "FROM TransferDetail td  where  td.requsetId=:requestId");
        query.setParameter("requestId", requestId);

        List<TransferDetail> transferDetailList = query.list();

        if (!CommonUtil.isNullOrEmpty(transferDetailList)) {
            transferdetail = (TransferDetail) transferDetailList.get(0);
        }
        return transferdetail;

    }

    public boolean deleteRxTransferRecord(Integer transferId, Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Delete From TransferRequest transferRequest where transferRequest.id=:transferId and transferRequest.patientId=:profileId");
        query.setParameter("transferId", transferId);
        query.setParameter("profileId", profileId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public DrugDetail getDrugDetailById(long id) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugForm drugForm left join fetch drugDetail.drugBasic drugBasic where drugDetail.drugDetailId=:drugDetailId");
        query.setParameter("drugDetailId", id);
        return (DrugDetail) query.uniqueResult();
    }

    public List retrieveDrugWithoutGeneric(String drugName) {
//        return getCurrentSession().createQuery("From PatientProfile p,PatientDeliveryAddress addr "
//                + "  where p.id=addr.patientProfile.id and addr.defaultAddress='Yes' order by p.firstName  ").list();

        return getCurrentSession().createQuery("From DrugBasic b  "
                + "  where   b.brandName like :drugName and b.drugGeneric.brandNameOnly=1"
                ////////////////////////////////////////////////////
                + " and b.drugBasicId in(select d.drugBasic.drugBasicId from DrugDetail d) "
                ///////////////////////////////////////////////////
                + " order by b.brandName  ").setParameter("drugName", drugName + "%").list();
    }

    public List retrieveDrugWithGeneric(String drugName) {
//        return getCurrentSession().createQuery("From PatientProfile p,PatientDeliveryAddress addr "
//                + "  where p.id=addr.patientProfile.id and addr.defaultAddress='Yes' order by p.firstName  ").list();

        return getCurrentSession().createQuery("From DrugBasic b  "
                + "  where  ( b.drugGeneric.genericName like :drugName or (b.brandName like :drugName and b.drugGeneric.genericName!='* BRAND NAME ONLY *')) "
                ////////////////////////////////////////////////////
                + " and b.drugBasicId in(select d.drugBasic.drugBasicId from DrugDetail d) "
                ///////////////////////////////////////////////////
                + " order by  b.brandName,b.drugGeneric.genericName ").setParameter("drugName", drugName + "%").list();
    }

    public Long getTotalRewardHistoryPointByOrderId(String type, String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("Select sum(rewardHistory.point) From RewardHistory rewardHistory "
                + "where rewardHistory.type=:type and rewardHistory.order.id=:orderId");
        query.setParameter("type", type);
        query.setParameter("orderId", orderId);
        return (Long) query.uniqueResult();
    }

    public List<PatientProfile> getPatientProfileWithNoOrder(Date date) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile left join fetch patientProfile.orders ord where ord.patientProfile.id is null and patientProfile.createdOn<=:queryDate ");
        query.setParameter("queryDate", date);
        return query.list();
    }

    public List<PatientProfile> getPatientProfilesList(String mobileNumber, Integer verificationCode) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile patientProfile where patientProfile.mobileNumber=:mobileNumber and patientProfile.verificationCode=:verificationCode and status=:status");
        query.setParameter("mobileNumber", mobileNumber);
        query.setParameter("verificationCode", verificationCode);
        query.setParameter("status", Constants.COMPLETED);
        return query.list();
    }

    public List getYearEndStatment(Integer patientId, Date startDate, Date endDate) throws Exception {

        Query query = getCurrentSession().createQuery("FROM Order WHERE patientProfile.patientProfileSeqNo = :patientId "
                + " AND createdAt >= :startDate AND createdAt <= :endDate "
                + " AND orderStatus  in(5,8,6,15) ")
                .setParameter("patientId", patientId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List list = query.list();
        return list;
    }

    public Long getTotalAllergiesCount(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) from PatientAllergies pa join pa.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientId");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalAllergiesCont(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) from PatientAllergies patientAllergies where patientAllergies.patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public Long getAllPatientAllergiesCount(Integer patientId) {
        Query query = getCurrentSession().createQuery("Select count(*) from PatientAllergies patientAllergies where patientAllergies.patientProfile.patientProfileSeqNo=:patientId and patientAllergies.allergies is not null and patientAllergies.allergies<>''");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    //    public List<Order> getRefillReminderOrdersList(Date date, List<String> orderStatusList) throws Exception {
//        Query query = getCurrentSession().createQuery("From Order ord "
//                + "left join fetch ord.drugDetail drugDetail "
//                + "left join fetch drugDetail.drugBasic drugBasic "
//                + "left join fetch drugBasic.drugGeneric drugGeneric "
//                + "left join fetch ord.orderStatus orderStatus "
//                + "where ord.nextRefillFlag=:nextRefillFlag "
//                + "and (ord.optOutRefillReminder=:optOutRefillReminder or ord.optOutRefillReminder is null)"
//                + "and ord.nextRefillDate<=:nextRefillDate "
//                + "and orderStatus.name in (:orderStatusList)");
//        query.setParameter("nextRefillFlag", "0");
//        query.setParameter("optOutRefillReminder", 0);
//        query.setParameter("nextRefillDate", date);
//        query.setParameterList("orderStatusList", orderStatusList);
//        return query.list();
//    }
    public List<Order> getRefillReminderOrdersList(Date date, List<String> orderStatusList) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord "
                + "left join fetch ord.patientProfile patientProfile "
                + "left join fetch ord.drugDetail2 drugNew "
                + "left join fetch ord.orderStatus orderStatus "
                + "where ord.lastReminderDate is null and ord.nextRefillFlag=:nextRefillFlag "
                + "and (ord.optOutRefillReminder=:optOutRefillReminder or ord.optOutRefillReminder is null)"
                + "and ord.nextRefillDate<=:nextRefillDate "
                + "AND ord.refillsRemaining >:Zero AND ord.viewStatus is null "
                + "and orderStatus.name in (:orderStatusList)");
        query.setParameter("nextRefillFlag", "0");
        query.setParameter("optOutRefillReminder", 0);
        query.setParameter("nextRefillDate", date);
         query.setParameter("Zero", 0);
        query.setParameterList("orderStatusList", orderStatusList);
        return query.list();
    }

    public List<Order> getOrdersForAnnualStatement(Integer currentYear, List<String> orderStatusList) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord where Year(ord.createdOn)=:createdOn and ord.orderStatus.name in (:orderStatusList)");
        query.setParameter("createdOn", currentYear);
        query.setParameterList("orderStatusList", orderStatusList);
        return query.list();
    }

    public Long getTotalOrders(List<String> orderStatusList, Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From Order ord where ord.orderStatus.name in (:orderStatusList) and ord.patientProfile.id=:patientId");
        query.setParameterList("orderStatusList", orderStatusList);
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public List<NotificationMessages> getInAppNotificationReport(BaseDTO baseDTO) throws Exception {
        String hql = "From NotificationMessages notificationMessages left join fetch notificationMessages.patientProfile patientProfile ";
        if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            hql += "where notificationMessages.createdOn>=:fromDate and notificationMessages.createdOn<=:toDate and patientProfile.mobileNumber=:phoneNumber order by notificationMessages.createdOn desc";
        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null) {
            hql += "where notificationMessages.createdOn>=:fromDate and notificationMessages.createdOn<=:toDate order by notificationMessages.createdOn desc";
        } else if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            hql += "where patientProfile.mobileNumber=:phoneNumber order by notificationMessages.createdOn desc";
        }
        Query query = getCurrentSession().createQuery(hql);
        if (baseDTO.getFromDate() != null) {
            query.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            query.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            query.setParameter("phoneNumber", baseDTO.getPhoneNumber());
        }
        return query.list();
    }

    public void savePatientPreference(PatientProfilePreferences preferences) {
        super.saveOrUpdate(preferences);
    }

    public List<PharmacyZipCodes> getPharmacyZipCodesList(boolean pickedFromPharmacy) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct pharmacyZipCodes From PharmacyZipCodes pharmacyZipCodes "
                + "left join fetch pharmacyZipCodes.deliveryDistanceFeesList deliveryDistanceFeesList "
                + "left join fetch deliveryDistanceFeesList.deliveryDistances deliveryDistances "
                + "left join fetch deliveryDistanceFeesList.deliveryPreferenceses deliveryPreferenceses "
                + " order by deliveryPreferenceses.seqNo");
//                + "where deliveryPreferenceses.pickedFromPharmacy=:pickedFromPharmacy");
//        query.setBoolean("pickedFromPharmacy", pickedFromPharmacy);
        return query.list();
    }

    public List<CampaignMessages> getCampaignMessagesList() throws Exception {
        Query query = getCurrentSession().createQuery("From CampaignMessages campaignMessages left join fetch campaignMessages.messageType messageType");
        return query.list();
    }

    public List<Order> getOrdersListByStatus(String mobileNumber, List<Integer> orderStatusList) throws Exception {
        Query query = getCurrentSession().createQuery("From Order o left join fetch o.patientProfile patientProfile left join fetch o.orderStatus orderStatus where patientProfile.mobileNumber=:mobileNumber and orderStatus in (:orderStatusList)");
        query.setParameter("mobileNumber", mobileNumber);
        query.setParameterList("orderStatusList", orderStatusList);
        return query.list();
    }

    public Object getTotalMsgCountByPatientId(Integer patientId, String testMsg) throws Exception {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT Count(*) FROM NotificationMessages WHERE ProfileId=:patientId AND IsTestMsg=:testMsg");
        query.setParameter("patientId", patientId);
        query.setParameter("testMsg", testMsg);
        return query.uniqueResult();
    }

    public Object getTotalMsgCountByPatientId(Integer patientId) throws Exception {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT Count(*) FROM NotificationMessages WHERE ProfileId=:patientId ");
        query.setParameter("patientId", patientId);
        return query.uniqueResult();
    }

    public List<PreferencesSetting> getPreferenceSettingId() throws Exception {
        Query query = getCurrentSession().createQuery("From PreferencesSetting ");
        return query.list();
    }

     public List<Order> getRefillableOrdersListByProfileId(Integer patientId, Integer dependentId, List<Integer> listOfOrderStatusIds) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("From Order ord left join fetch ");
        hql.append(" ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus where ");
        hql.append(" patientProfile.patientProfileSeqNo=:patientId and orderStatus.id in (:listOfOrderStatusIds)");
        hql.append(" and ord.refillsRemaining>0 ");
//        hql.append(" and (ord.nextRefillDate is not null and ord.nextRefillDate<=:nextRefillDate) and ord.refillsRemaining>0 ");    
//        hql.append(" and ord.rxExpiredDate>:nextRefillDate ");
//        hql.append(" and (ord.refillDone is null or ord.refillDone=0) ");
        hql.append(" order by ord.createdAt desc ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("patientId", patientId);
//        query.setDate("nextRefillDate", new Date());
        query.setParameterList("listOfOrderStatusIds", listOfOrderStatusIds);
        return query.list();
    }

    public PatientProfile getPatientProfileByPhoneAndStatus(String mobileNumber, String status) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.mobileNumber=:mobileNumber and status=:status");
        query.setParameter("mobileNumber", mobileNumber);
        query.setParameter("status", status);
        return (PatientProfile) query.uniqueResult();
    }

    public void removePatientAllergiesByPatientIdAndDependentExist(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From PatientAllergies Where PatientId In (:patientId) and DependentId is not null");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public void removePatientInsuranceDetailsByPatientIdAndDependentExist(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From PatientInsuranceDetails Where PatientId In (:patientId) and DependentID is not null");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    //    public void removeOrdersByPatientIdAndDependentExist(Integer patientId) throws Exception {
//        Query query = getCurrentSession().createSQLQuery("Delete From Orders Where PatientId In (:patientId) and DependentId is not null");
//        query.setParameter("patientId", patientId);
//        query.executeUpdate();
//    }
    public void removeOrdersByPatientIdAndDependentExist(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From Orders Where PatientId In (:patientId) ");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public void removeNotificationMessagesByPatientIdAndDependentExist(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From NotificationMessages Where ProfileId In (:patientId) and DependentId is not null");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public List<Order> generatePatientBasicReport(BaseDTO baseDTO) throws Exception {
        StringBuilder hqlSb = new StringBuilder();
        hqlSb.append("From Order ord ");
        hqlSb.append("inner join fetch ord.patientProfile patientProfile ");
        hqlSb.append("where ");
        if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getPhoneNumber()) && CommonUtil.isNotEmpty(baseDTO.getPatientName()) && CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            hqlSb.append(" ord.createdOn>=:fromDate and ord.createdOn<=:toDate and patientProfile.mobileNumber=:phoneNumber");
            hqlSb.append(" and ((patientProfile.firstName=:orgnPtName or patientProfile.lastName=:orgnPtName) or (patientProfile.firstName=:captilizePtName or patientProfile.lastName=:captilizePtName) or (patientProfile.firstName=:fullCaptilizePtName or patientProfile.lastName=:fullCaptilizePtName))");

            hqlSb.append(" and ((patientProfile.emailAddress=:orgnemailAddress) or (patientProfile.emailAddress=:captilizeEmailAddress) or (patientProfile.emailAddress=:fullCaptilizeEmailAddress))");
        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            hqlSb.append(" ord.createdOn>=:fromDate and ord.createdOn<=:toDate and patientProfile.mobileNumber=:phoneNumber");
        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getPatientName())) {
            hqlSb.append(" ord.createdOn>=:fromDate and ord.createdOn<=:toDate");
            hqlSb.append(" and ((patientProfile.firstName=:orgnPtName or patientProfile.lastName=:orgnPtName) or (patientProfile.firstName=:captilizePtName or patientProfile.lastName=:captilizePtName) or (patientProfile.firstName=:fullCaptilizePtName or patientProfile.lastName=:fullCaptilizePtName))");

        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            hqlSb.append(" ord.createdOn>=:fromDate and ord.createdOn<=:toDate");
            hqlSb.append(" and ((patientProfile.emailAddress=:orgnemailAddress) or (patientProfile.emailAddress=:captilizeEmailAddress) or (patientProfile.emailAddress=:fullCaptilizeEmailAddress)");

        } else if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber()) && CommonUtil.isNotEmpty(baseDTO.getPatientName())) {
            hqlSb.append(" patientProfile.mobileNumber=:phoneNumber");
            hqlSb.append(" and ((patientProfile.firstName=:orgnPtName or patientProfile.lastName=:orgnPtName) or (patientProfile.firstName=:captilizePtName or patientProfile.lastName=:captilizePtName) or (patientProfile.firstName=:fullCaptilizePtName or patientProfile.lastName=:fullCaptilizePtName))");

        } else if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber()) && CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            hqlSb.append(" patientProfile.mobileNumber=:phoneNumber");
            hqlSb.append(" and ((patientProfile.emailAddress=:orgnemailAddress) or (patientProfile.emailAddress=:captilizeEmailAddress) or (patientProfile.emailAddress=:fullCaptilizeEmailAddress)");

        } else if (CommonUtil.isNotEmpty(baseDTO.getPatientName()) && CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            hqlSb.append(" ((patientProfile.firstName=:orgnPtName or patientProfile.lastName=:orgnPtName) or (patientProfile.firstName=:captilizePtName or patientProfile.lastName=:captilizePtName) or (patientProfile.firstName=:fullCaptilizePtName or patientProfile.lastName=:fullCaptilizePtName))");

            hqlSb.append(" and ((patientProfile.emailAddress=:orgnemailAddress) or (patientProfile.emailAddress=:captilizeEmailAddress) or (patientProfile.emailAddress=:fullCaptilizeEmailAddress))");

        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null) {
            hqlSb.append(" ord.createdOn>=:fromDate and ord.createdOn<=:toDate");
        } else if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            hqlSb.append(" patientProfile.mobileNumber=:phoneNumber");
        } else if (CommonUtil.isNotEmpty(baseDTO.getPatientName())) {
            hqlSb.append(" (patientProfile.firstName=:orgnPtName or patientProfile.lastName=:orgnPtName) or (patientProfile.firstName=:captilizePtName or patientProfile.lastName=:captilizePtName) or (patientProfile.firstName=:fullCaptilizePtName or patientProfile.lastName=:fullCaptilizePtName)");

        } else if (CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            hqlSb.append(" (patientProfile.emailAddress=:orgnemailAddress) or (patientProfile.emailAddress=:captilizeEmailAddress) or (patientProfile.emailAddress=:fullCaptilizeEmailAddress)");
        }
        hqlSb.append(" order by ord.createdOn desc ");
        Query query = getCurrentSession().createQuery(hqlSb.toString());
        if (baseDTO.getFromDate() != null) {
            query.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            query.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (CommonUtil.isNotEmpty(baseDTO.getPhoneNumber())) {
            query.setParameter("phoneNumber", baseDTO.getPhoneNumber());
        }
        if (CommonUtil.isNotEmpty(baseDTO.getPatientName())) {
            query.setParameter("orgnPtName", EncryptionHandlerUtil.getEncryptedString(baseDTO.getPatientName()));
            query.setParameter("captilizePtName", EncryptionHandlerUtil.getEncryptedString(StringUtils.capitalize(baseDTO.getPatientName())));
            query.setParameter("fullCaptilizePtName", EncryptionHandlerUtil.getEncryptedString(baseDTO.getPatientName().toUpperCase()));
        }
        if (CommonUtil.isNotEmpty(baseDTO.getEmail())) {
            query.setParameter("orgnemailAddress", EncryptionHandlerUtil.getEncryptedString(baseDTO.getEmail()));
            query.setParameter("captilizeEmailAddress", EncryptionHandlerUtil.getEncryptedString(StringUtils.capitalize(baseDTO.getEmail())));
            query.setParameter("fullCaptilizeEmailAddress", EncryptionHandlerUtil.getEncryptedString(baseDTO.getEmail().toUpperCase()));
        }
        return query.list();
    }

    public boolean isPatientEmailExist(String email, Integer patientId) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(PatientProfile.class);
        criteria.add(Restrictions.eq("emailAddress", email));
        if (!CommonUtil.isNullOrEmpty(patientId)) {
            criteria.add(Restrictions.ne("id", patientId));
        }
        return (!CommonUtil.isNullOrEmpty(criteria.list()));
    }

    public boolean isPatientMobileExist(String mobileNumber, Integer patientId) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(PatientProfile.class);
        criteria.add(Restrictions.eq("mobileNumber", mobileNumber));
        if (!CommonUtil.isNullOrEmpty(patientId)) {
            criteria.add(Restrictions.ne("id", patientId));
        }
        return (!CommonUtil.isNullOrEmpty(criteria.list()));
    }

    public Long getActiveOrderCount(Integer patientProfileSeqNo) throws Exception {
        Query query = getCurrentSession().createQuery(""
                + "From Order ord "
                + "left join fetch ord.patientProfile patientProfile "
                + " left join fetch ord.orderStatus orderStatus "
                + "where patientProfile.id=:patientProfileSeqNo "
                + "and orderStatus not in(11,16,18)");
        query.setParameter("patientProfileSeqNo", patientProfileSeqNo);
        return Long.parseLong(String.valueOf(query.list().size()));
    }

    public PatientGlucoseResults getGlucoseById(Integer patientProfileSeqNo, Integer glucosId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientGlucoseResults patientGlucoseResults ");
        sb.append("left join fetch patientGlucoseResults.patientProfile patientProfile ");
        sb.append("where patientProfile.patientProfileSeqNo =:patientProfileSeqNo ");
        sb.append("and patientGlucoseResults.id =:glucosId ");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setInteger("glucosId", glucosId);
        return (PatientGlucoseResults) query.uniqueResult();
    }

    public BloodPressure getBloodPresureById(Integer patientProfileSeqNo, Integer bloodPressureSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From BloodPressure bloodPressure ");
        sb.append("left join fetch bloodPressure.patientProfile patientProfile ");
        sb.append("where patientProfile.patientProfileSeqNo =:patientProfileSeqNo ");
        sb.append("and bloodPressure.bloodPresureSeqNo =:bloodPressureSeqNo ");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setInteger("bloodPressureSeqNo", bloodPressureSeqNo);
        return (BloodPressure) query.uniqueResult();
    }

    public List<BloodPressure> getBloodPressureResultListByPatientProfileId(Integer patientProfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From BloodPressure bloodPressure ");
        sb.append("left join fetch bloodPressure.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo ");
        sb.append("order by bloodPressure.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientProfileSeqNo);
        return qury.list();
    }

    public PatientProfile getPatientInfoByEmailOrMobileNo(String mobileNumber, String email) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(PatientProfile.class);
        criteria.createCriteria("patientDeliveryAddresses", "patientDeliveryAddresses", JoinType.LEFT_OUTER_JOIN);
        criteria.createCriteria("patientDeliveryAddresses.state", "state", JoinType.LEFT_OUTER_JOIN);
        if (CommonUtil.isNotEmpty(mobileNumber)) {
            criteria.add(Restrictions.eq("mobileNumber", EncryptionHandlerUtil.getEncryptedString(mobileNumber)));
        } else if (CommonUtil.isNotEmpty(email)) {
            criteria.add(Restrictions.eq("emailAddress", EncryptionHandlerUtil.getEncryptedString(email)));
        }
        return (PatientProfile) criteria.uniqueResult();
    }

    //    public List<BloodPressureDTO> getbloodPressureListById(Integer bloodPressureSeqNo, Integer patientProfileSeqNo) throws Exception{
//     StringBuilder sb = new StringBuilder();
//     sb.append("From BloodPressure bloodPressure ");
//     sb.append("left join fetch bloodPressure.patientProfile patientProfile ");
//     sb.append("where patientProfile.patientProfileSeqNo =:patientProfileSeqNo ");
//     sb.append("and bloodPressure.bloodPressureSeqNo =:bloodPressureSeqNo ");
//     Query query = getCurrentSession().createQuery(sb.toString());
//     query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
//     query.setInteger("bloodPressureSeqNo", bloodPressureSeqNo);
//     return 
//    }
    public PatientBodyMassResult getBodyMassResutById(Integer bodyMassResultSeqNo, Integer patientPrfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientBodyMassResult patientBodyMassResult ");
        sb.append("left join fetch patientBodyMassResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo ");
        sb.append("and patientBodyMassResult.bodyMassResultSeqNo =:bodyMassResultSeqNo ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientPrfileSeqNo);
        qury.setInteger("bodyMassResultSeqNo", bodyMassResultSeqNo);
        return (PatientBodyMassResult) qury.uniqueResult();
    }

    public List<PatientBodyMassResult> getBodyMassResultListByPatientProfileId(Integer patientProfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientBodyMassResult patientBodyMassResult ");
        sb.append("left join fetch patientBodyMassResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo ");
        sb.append("order by patientBodyMassResult.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientProfileSeqNo);
        return qury.list();
    }

    public PatientProfile getPatientProfileByEmailIdOrPhoneNumber(String identity) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientProfile patientProfile ");
        sb.append("left join fetch patientProfile.patientDeliveryAddresses patientDeliveryAddresses ");
        sb.append("left join fetch patientDeliveryAddresses.state ptDeliveryAddressState ");
        sb.append("left join fetch patientProfile.statee statee ");
        sb.append("where patientProfile.mobileNumber =:identity ");
//        sb.append("Or patientProfile.emailAddress =:identity order by patientProfile.createdAt desc");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setString("identity", EncryptionHandlerUtil.getEncryptedString(identity));
//        query.setMaxResults(1);
        return (PatientProfile) query.uniqueResult();
    }

    public PatientHeartPulseResult getHeartPulseById(Integer heartPulseSeqNo, Integer patinetProfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientHeartPulseResult patientHeartPulseResult ");
        sb.append("left join fetch patientHeartPulseResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patinetProfileSeqNo ");
        sb.append("and patientHeartPulseResult.heartPulseSeqNo =:heartPulseSeqNo ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patinetProfileSeqNo", patinetProfileSeqNo);
        qury.setInteger("heartPulseSeqNo", heartPulseSeqNo);
        return (PatientHeartPulseResult) qury.uniqueResult();
    }

    public List<PatientHeartPulseResult> getHeartPulseListByPatientProfileId(Integer patinetProfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientHeartPulseResult patientHeartPulseResult ");
        sb.append("left join fetch patientHeartPulseResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patinetProfileSeqNo ");
        sb.append("order by patientHeartPulseResult.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patinetProfileSeqNo", patinetProfileSeqNo);
        return qury.list();
    }

    public List<PatientProfile> getPatientActivitesDetial() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Select php.heartPulse as heartPulse, pBm.height as height, pBm.weight as weight, bp.systolicBloodPressure as systolicBloodPressure, bp.distolicBloodPressure as distolicBloodPressure, pGlucose.glucoseLevel as glucoseLevel ");
        sb.append("From PatientProfile patientProfile ");
        sb.append("left join fetch PatientHeartPulseResult.patientProfile php ");
        sb.append("left join fetch PatientBodyMassResult.patientProfile pBm ");
        sb.append("left join fetch BloodPressure.patientProfile bp ");
        sb.append("left join fetch PatientGlucoseResults.patientProfile pGlucose ");
        Query query = getCurrentSession().createQuery(sb.toString());
        return query.list();
    }

    public List<Object[]> getPatientActivitesDetial2() throws Exception {

        String sql = "SELECT patientProfile.`Id` AS patientId, patientProfile.`FirstName` as firstName, patientProfile.`LastName` as lastName, patientProfile.`BirthDate` as birth,\n"
                + " php.heartPulse as heartPulse, php.`HeartPulseSeqNo`, pBm.height AS height, pBm.weight AS weight,\n"
                + " bp.`Systolic_BloodPressure` as systolicBloodPressure, bp.`Distolic_BloodPressure` as distolicBloodPressure, pG.`GlucoseLevel` as glucoseLevel,\n"
                + " patientProfile.`CreatedOn` AS creationDate \n"
                + "FROM `PatientProfileInfo` patientProfile\n"
                + "left JOIN `PatientHeartPulse` php on php.`HeartPulseSeqNo` = ( select ph.`HeartPulseSeqNo` from `PatientHeartPulse` ph where patientProfile.`Id`= ph.`Fk_PatientProfileSeqNo` ORDER by ph.`HeartPulseSeqNo` desc LIMIT 1 )\n"
                + "left JOIN `PatientBodyMassResult` pBm on pBm.`BodyMassResult_seqNo` = ( select pB.`BodyMassResult_seqNo` from `PatientBodyMassResult` pB where patientProfile.`Id`= pB.`Fk_PatientProfileSeqNo` ORDER by pB.`BodyMassResult_seqNo` desc LIMIT 1)\n"
                + "left join PatientBloodPresuureResult bp on bp.`BloodPresureSeqNo` =( Select p.BloodPresureSeqNo from PatientBloodPresuureResult p where patientProfile.`Id` = p.Fk_PatientProfileSeqNo order by p.BloodPresureSeqNo desc limit 1)\n"
                + "left join `PatientGlucoseResults` pG on pG.`Id` = ( select g.`Id`  from PatientGlucoseResults g where patientProfile.`Id` = g.`PatientId` order by g.`Id` desc limit 1)";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        //query.addEntity(PatientActivityDTO.class);
        List<Object[]> results = query.list();

        return results;

    }

    public List<BloodPressure> getBloodPressureList(int patientId) {
        String sql = "from BloodPressure pbpr left join fetch pbpr.patientProfile ppi "
                + "where ppi.patientProfileSeqNo = :patientId and pbpr.createdOn = :createdOn "
                + "order by pbpr.readingTime desc ";
        Query query = getCurrentSession().createQuery(sql);
        query.setInteger("patientId", patientId);
        query.setDate("createdOn", new Date());
        List<BloodPressure> results = query.list();

        return results;
    }

    public List<Object[]> getAllWeekResultPatientBodyMass(Integer patientProfileSeqNo, String startDate, String endDate) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("from PatientBodyMassResult patientBodyMassResult ");
        sb.append("left join fetch patientBodyMassResult.patientProfile patientProfile ");
        sb.append("where patientProfile.patientProfileSeqNo=:patientProfileSeqNo ");
        sb.append("and patientBodyMassResult.resultDate ");
        sb.append("BETWEEN :startDate AND :endDate ");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setString("startDate", startDate);
        query.setString("endDate", endDate);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getAllWeekAverageResultPatientBodyMass(Integer patientProfileSeqNo, String startDate, String endDate) throws Exception {

        String sql = "SELECT pbmr.bodyMassResultSeqNo, pbmr.patientProfile.patientProfileSeqNo, AVG(pbmr.height) AS height, AVG(pbmr.weight) AS weight, "
                + "pbmr.resultDate, AVG(pbmr.pulse) AS pulse, pbmr.createdOn, pbmr.updatedOn, pbmr.ethnicity "
                + "FROM PatientBodyMassResult pbmr "
                + "WHERE pbmr.patientProfile.patientProfileSeqNo = :patientProfileSeqNo AND pbmr.resultDate BETWEEN :startDate AND :endDate "
                + "GROUP BY DAY(pbmr.resultDate) "
                + "ORDER BY pbmr.resultDate ASC";

        Query query = getCurrentSession().createQuery(sql);
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setString("startDate", startDate);
        query.setString("endDate", endDate);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getgetBMIYearFilterWs(int patientId, String startDate, String endDate) throws Exception {

        String sql = "SELECT pbmr.bodyMassResultSeqNo, pbmr.patientProfile.patientProfileSeqNo, AVG(pbmr.height) AS height, AVG(pbmr.weight) AS weight, "
                + "pbmr.resultDate, AVG(pbmr.pulse) AS pulse, pbmr.createdOn, pbmr.updatedOn, pbmr.ethnicity "
                + "FROM PatientBodyMassResult pbmr "
                + "WHERE pbmr.patientProfile.patientProfileSeqNo = :patientId AND pbmr.resultDate BETWEEN :startDate AND :endDate "
                + "GROUP BY MONTH(pbmr.resultDate) "
                + "ORDER BY pbmr.resultDate ASC";

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientId", patientId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Object[]> results = query.list();

        return results;
    }

    public List<Object[]> getgetBMIMonthFilterWs(int patientId, String startDate, String endDate) throws Exception {

        String sql = "SELECT AVG(pbmr.height) AS height, AVG(pbmr.weight) AS weight, "
                + "WEEK(pbmr.resultDate), AVG(pbmr.pulse) AS pulse "
                + "FROM PatientBodyMassResult pbmr "
                + "WHERE pbmr.patientProfile.patientProfileSeqNo = :patientId AND pbmr.resultDate BETWEEN :startDate AND :endDate "
                + "GROUP BY WEEK(pbmr.resultDate) "
                + "ORDER BY pbmr.resultDate ASC";

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientId", patientId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Object[]> results = query.list();

        return results;
    }

    public List<Object[]> getAllWeekDetailResultPatientBodyMass(Integer patientProfileSeqNo, String startDate, String endDate) throws Exception {

        String sql = "SELECT pbmr.bodyMassResultSeqNo, pbmr.patientProfile.patientProfileSeqNo, pbmr.height AS height, pbmr.weight AS weight, "
                + "pbmr.resultDate, AVG(pbmr.pulse) AS pulse, pbmr.createdOn, pbmr.updatedOn, pbmr.ethnicity "
                + "FROM PatientBodyMassResult pbmr "
                + "WHERE pbmr.patientProfile.patientProfileSeqNo = :patientProfileSeqNo AND pbmr.resultDate BETWEEN :startDate AND :endDate "
                + "GROUP BY DAY(pbmr.resultDate) "
                + "ORDER BY pbmr.resultDate ASC";

        Query query = getCurrentSession().createQuery(sql);
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setString("startDate", startDate);
        query.setString("endDate", endDate);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getQuestionList(String surveyId, int practiceId, String patientPhoneNumber) throws Exception {
        String sql = "SELECT q.* FROM Question q JOIN "
                + "( "
                + "SELECT pqa.* FROM PracticeQuestionAns pqa JOIN SurveyBridge sb "
                + "ON pqa.Bridge_Key = sb.Unique_Key "
                + "WHERE "
                + "pqa.Survey_Status_Code != 'CM' "
                + "AND "
                + "pqa.Bridge_Key = :surveyId "
                + "AND "
                + "pqa.Practice_ID = :practiceId "
                + "AND "
                + "pqa.Patient_Phone_Number = :patientPhoneNumber "
                + ") temp ON q.Question_ID = temp.Question_ID";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("surveyId", surveyId);
        query.setParameter("practiceId", practiceId);
        query.setParameter("patientPhoneNumber", patientPhoneNumber);
        List<Object[]> result = query.list();
        return result;
    }

    public List<SurveyBridge> getSurveyListByMobileNumber(String mobileNumber) throws Exception {
        String sql = "from SurveyBridge sb WHERE sb.patientPhonNumber = :mobileNumber ";
        Query query = getCurrentSession().createQuery(sql);
        query.setString("mobileNumber", EncryptionHandlerUtil.getDecryptedString(mobileNumber));
        List<SurveyBridge> result = query.list();
        return result;

    }

    public List<SurveyBridge> getSurveyBridgerecordOldOneDay() throws Exception {
        String sql = "Select sb.* "
                + "FROM SurveyBridge sb "
                + "WHERE sb.Survey_Status_Code != 'CM' "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())>=24 "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())<48 ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<SurveyBridge> result = query.list();
        return result;

    }

    public List<SurveyBridge> getSurveyBridgeRecordOldTwoDays() throws Exception {
        String sql = "Select sb.* "
                + "from SurveyBridge sb "
                + "WHERE sb.Survey_Status_Code != 'CM' "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())>=48 "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())<72 ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<SurveyBridge> result = query.list();
        return result;

    }

    public List<SurveyBridge> getSurveyBridgeRecordOld3rdDays() throws Exception {
        String sql = "Select sb.* "
                + "from SurveyBridge sb "
                + "WHERE sb.Survey_Status_Code != 'CM' "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())>=72 "
                + "AND TIMESTAMPDIFF(HOUR, sb.Effective_Date, NOW())<96 ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<SurveyBridge> result = query.list();
        return result;

    }

    public List<PatientAllergies> getAllergyList(int patientId) throws Exception {
        String sql = "from PatientAllergies pa WHERE pa.patientProfile.patientProfileSeqNo = :patientId ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientId", patientId);
        List<PatientAllergies> result = query.list();
        return result;

    }

    public PatientAllergies getAllergy(int patientId, String allergyName) throws Exception {
        String hql = "from PatientAllergies patientAllergies "
                + "left join fetch patientAllergies.patientProfile patientProfile "
                + "WHERE patientAllergies.allergies = :allergyName AND  patientProfile.patientProfileSeqNo= :patientId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("allergyName", allergyName);
        return (PatientAllergies) query.uniqueResult();

    }
    public PatientAllergies getAllergyById(int patientId, int allergyId) throws Exception {
        String hql = "from PatientAllergies patientAllergies "
                + "left join fetch patientAllergies.patientProfile patientProfile "
                + "WHERE patientAllergies.id = :allergyId AND  patientProfile.patientProfileSeqNo= :patientId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("allergyId", allergyId);
        return (PatientAllergies) query.uniqueResult();

    }
    public PatientAllergies AllergyeExist(int patientId, int allergyid) throws Exception {
        String hql = "from PatientAllergies patientAllergies "
                + "left join fetch patientAllergies.patientProfile patientProfile "
                + "WHERE patientAllergies.id = :allergyid AND  patientProfile.patientProfileSeqNo= :patientId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("allergyid", allergyid);
        return (PatientAllergies) query.uniqueResult();

    }

    public List<Object[]> getQuestionListByPhone(String phoneNumber) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From Question q ");
        sb.append("left join fetch PractiseQuestionAns pqa ");
        sb.append("left join fetch SurveyBridge sb ");
        sb.append("left join fetch PatientProfile patientProfile ");
        sb.append("Where q.questionId = pqa.questionId ");
        sb.append("AND pqa.bridgeKey = sb.uniqueKey ");
        sb.append("AND sb.patientPhonNumber =:phoneNumber ");

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setParameter("phoneNumber", EncryptionHandlerUtil.getEncryptedString(phoneNumber));
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getQuestionListByPhneNumber(String phoneNumber, String surveyId) throws Exception {
        String sql = "Select q.* "
                + "from Question q "
                + "left join  PracticeQuestionAns pqa "
                + "ON pqa.Question_ID = q.Question_ID "
                + "left join SurveyBridge sb "
                + "ON sb.Unique_Key = pqa.Bridge_Key  "
                + "where sb.Patient_Phone_Number =:phoneNumber AND sb.Unique_Key =:surveyId ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("phoneNumber", EncryptionHandlerUtil.getDecryptedString(phoneNumber));
        query.setParameter("surveyId", surveyId);
        List<Object[]> result = query.list();
        return result;
    }

    public boolean updatePractiseQuestionAnswerReply(String phone, String surveyId, List<surveyQuestionAnswerDTO> qRDTO) throws Exception {
        for (surveyQuestionAnswerDTO list : qRDTO) {
            String sql = "UPDATE PracticeQuestionAns "
                    + " SET `PQA_Ans` =:answerId "
                    + "WHERE `Patient_Phone_Number` = :phoneNumber "
                    + "AND `Bridge_Key`=:surveyId "
                    + "AND Question_ID = :questionId ";
            Query query = getCurrentSession().createSQLQuery(sql);
            query.setParameter("surveyId", surveyId);
            query.setParameter("phoneNumber", EncryptionHandlerUtil.getDecryptedString(phone));
            query.setParameter("questionId", list.getQuestionId());
            query.setParameter("answerId", list.getAnswerId());
            query.executeUpdate();
        }
        return Boolean.TRUE;
    }

    public List<Order> getZeroRefillOrdersListByProfileId(Integer patientId, Integer dependentId, List<Integer> listOfOrderStatusIds) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("From Order ord ");
        hql.append(" left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus ");
        hql.append(" where patientProfile.patientProfileSeqNo=:patientId and orderStatus.id in (:listOfOrderStatusIds) ");
        hql.append(" and ord.refillsRemaining=0 ");
        hql.append(" and ord.rxExpiredDate>=:currentDate ");
        hql.append(" order by ord.createdAt desc ");

        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("patientId", patientId);
        query.setDate("currentDate", new Date());
        query.setParameterList("listOfOrderStatusIds", listOfOrderStatusIds);
        return query.list();
    }

    public List<Object[]> getRxExpireSoonListByProfileId(Integer patientId, Integer dependentId, List<Integer> listOfOrderStatusIds) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("Select orders.* From Orders orders ");
        sql.append(" inner join PatientProfileInfo patientProfile on orders.PatientId=patientProfile.Id ");
        sql.append("inner join OrderStatus orderStatus on orders.OrderStatus=orderStatus.id ");
        sql.append("inner join drugdetail on orders.DrugDetailId=drugdetail.drugdetailid ");
        sql.append("where orders.PatientId=:patientId and orderStatus.id in (:listOfOrderStatusIds) ");
        sql.append(" and orders.RefillsRemaining>0 ");
        sql.append(" and (TIMESTAMPDIFF(DAY,NOW(),orders.RxExpiryDate)>0 AND TIMESTAMPDIFF(DAY,NOW(),orders.RxExpiryDate)<=45) ");
        sql.append(" and (orders.refilldone is null or orders.refilldone=0) ");
        sql.append(" order by orders.created_at desc ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("patientId", patientId);
        query.setParameterList("listOfOrderStatusIds", listOfOrderStatusIds);
        return query.list();
    }

    public List<Order> getRxExpireSoonListByPatientId(Integer patientId, Integer dependentId, List<Integer> listOfOrderStatusIds) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("From Order ord ");
        hql.append(" left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus ");
        hql.append(" where patientProfile.patientProfileSeqNo=:patientId and orderStatus.id in (:listOfOrderStatusIds) ");
        hql.append(" and ord.refillsRemaining>0 ");
        hql.append(" and ord.rxExpiredDate>=:rxExpiredDate ");
        hql.append(" and (ord.refillDone is null or ord.refillDone=0) ");
        hql.append(" order by ord.createdAt desc ");

        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("patientId", patientId);
        query.setDate("rxExpiredDate", new Date());
        query.setParameterList("listOfOrderStatusIds", listOfOrderStatusIds);
        return query.list();
    }

    public Practices getPractiseNameById(int practiseId) throws Exception {
        String hql = "FROM Practices practices WHERE practices.id = :practiseId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("practiseId", practiseId);
        return (Practices) query.uniqueResult();

    }

    public boolean deleteAllergies(Integer profileId, Integer allergyId) throws Exception {
        String sql = "DELETE FROM `PatientAllergies` WHERE `PatientId`=:profileId AND `Id`=:allergyId ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("profileId", profileId);
        query.setParameter("allergyId", allergyId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean UpdateAllergies(Integer profileId, String allergy, int allergyId) throws Exception {
        String sql = "UPDATE `PatientAllergies` SET `Allergies`=:allergy WHERE `PatientId`=:profileId AND `Id`=:allergyId ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("profileId", profileId);
        query.setParameter("allergyId", allergyId);
        query.setParameter("allergy", allergy);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Order> getOrderListByStatus(int profileId, int orderStatusId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Order o ");
        sb.append("LEFT JOIN FETCH o.patientProfile patientProfile ");
        sb.append("LEFT JOIN FETCH o.orderStatus orderStatus ");
        sb.append("LEFT JOIN FETCH o.complianceRewardPoint complianceRewardPoint ");
        sb.append("WHERE patientProfile.patientProfileSeqNo= :profileId ");
        sb.append("AND orderStatus.id = :orderStatusId");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setParameter("profileId", profileId);
        query.setParameter("orderStatusId", orderStatusId);
        return query.list();
    }

    public EnrollementIpad getEnrollemtRecord(int profileId) throws Exception {
        String hql = "FROM EnrollementIpad enrollementIpad "
                + "LEFT JOIN FETCH  enrollementIpad.profile profile "
                + "WHERE profile.patientProfileSeqNo =:profileId ";

        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("profileId", profileId);
        return (EnrollementIpad) query.uniqueResult();
    }

    public boolean DeletePatientProfileRecordFromAllOrphens(String phone) throws Exception {
        String sql = "CALL `rxdev_ComplianceReward`.`Delete_RECORD`(:phoneNumber) ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("phoneNumber", EncryptionHandlerUtil.getEncryptedString(phone));
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<ActivitesHistory> getActivityHistory() throws Exception {
        String hql = "FROM ActivitesHistory ";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    public boolean updateOrderStatusById(int profileId, String orderId) throws Exception {
        String sql = "UPDATE `Orders` SET `OrderStatus`=10 WHERE `Id` =:orderId AND `PatientId`=:profileId ";
//        String hql = "UPDATE Order order SET orderStatus =10 WHERE order.id IN (:orderList) AND order.patientProfile.patientProfileSeqNo =:profileId ";

        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("orderId", orderId);
        query.setParameter("profileId", profileId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean deleteCurrentAddress(int addressId, int patientId) throws Exception {
        String sql = "DELETE FROM `PatientDeliveryAddress` WHERE `Id`= :addressId AND `PatientId` = :patientId ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("patientId", patientId);
        query.setParameter("addressId", addressId);
        if (query.executeUpdate() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public PatientProfile getOrderDetailByOrderId(String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order order "
                + "LEFT JOIN FETCH order.patientProfile patientProfile "
                + "where order.id =:orderId "
                + "and status=:status");
        query.setParameter("mobileNumber", orderId);
        return (PatientProfile) query.uniqueResult();
    }

    public Order getOrderDetailById(String orderId) throws Exception {
        String hql = "From Order ord "
                + "LEFT JOIN FETCH ord.orderStatus orderStatus "
                + "LEFT JOIN FETCH ord.drugDetail2 drugNew "
                + "LEFT JOIN FETCH ord.complianceRewardPoint complianceRewardPoint "
                + "WHERE ord.id =:orderId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId", orderId);
        return (Order) query.uniqueResult();
    }

    public PatientProfile getPatientProfileByPatientIdr(int patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientId and status=:status ");
        query.setParameter("patientId", patientId);
        query.setParameter("status", Constants.COMPLETED);
        return (PatientProfile) query.uniqueResult();
    }

    public PatientProfile getPatientProfileByPatientIdwithOutStatusBoundery(int patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (PatientProfile) query.uniqueResult();
    }

    ////////////////////////////// Changes by jandal
    public List<Faq> getFAQuestions() throws Exception {
        Query query = getCurrentSession().createQuery("FROM Faq");
        return (List<Faq>) query.list();
     
    }

    public List<Object[]> getPatientQuestionByDate(Integer id, Date startdate, Date enddate) throws Exception {
        String hql = "select qa.id, qa.order.id, qa.question,qa.questionTime, qa.isRead from QuestionAnswer qa where qa.patientProfile.patientProfileSeqNo=:id AND (qa.questionTime>=:startdate AND qa.questionTime<=:enddate)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return query.list();
    }

    public List<Object[]> getPatientQuestionById(Integer id) throws Exception {
        String hql = "select qa.id, qa.order.id, qa.question,qa.questionTime, qa.isRead "
                + "from QuestionAnswer qa "
                + "where qa.patientProfile.patientProfileSeqNo=:id AND qa.prescriberId is null order by qa.createdAt desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }
        public List<Object[]> getPatientHcpGenralQuestionById(Integer id) throws Exception {
        String hql = "select qa.id, qa.order.id, qa.question,qa.questionTime, qa.isRead, qa.prescriberId "
                + "from QuestionAnswer qa "
                + "where qa.patientProfile.patientProfileSeqNo=:id AND qa.prescriberId is not null order by qa.createdAt desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }

    public List<QuestionAnswer> getQuestionAnswerById(Integer id) throws Exception {
        String hql = "from QuestionAnswer qa where qa.patientProfile.patientProfileSeqNo=:id order by qa.answerTime desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
//        query.setParameter("QuestionID", questionid);
        return query.list();
    }

    public boolean isNotEnrollforPoints(int patientid, String orderid) {
        String sql = "select * from patientpoints where PatientId=:patientid and OrderId=:orderid";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("patientid", patientid);
        query.setParameter("orderid", orderid);
        if (query.uniqueResult() == null) {
            return true;
        }
        return false;
    }

    public PatientPoints updatePatientPointsObject(int patientid, String orderid) {
        String hql = "From PatientPoints pp "
                + "LEFT JOIN FETCH pp.patientProfile profile "
                + "LEFT JOIN FETCH pp.orders ord "
                + "WHERE profile.patientProfileSeqNo =:patientid "
                + "AND ord.id =:orderid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientid", patientid);
        query.setParameter("orderid", orderid);
        return (PatientPoints) query.uniqueResult();
    }

    public Long TotalActivityCountbyPatientId(int patientid) {
        Long total;

        String sql = "Select count(*) from BloodPressure bloodPressure join bloodPressure.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientid";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patientid", patientid);
        total = (Long) query.uniqueResult();

        sql = "Select count(*) from PatientGlucoseResults pgr join pgr.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientid";
        query = getCurrentSession().createQuery(sql);
        query.setParameter("patientid", patientid);
        total = total + (Long) query.uniqueResult();

        sql = "Select count(*) from PatientBodyMassResult pbmr join pbmr.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientid";
        query = getCurrentSession().createQuery(sql);
        query.setParameter("patientid", patientid);
        total = total + (Long) query.uniqueResult();

        sql = "Select count(*) from PatientHeartPulseResult phpr join phpr.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientid";
        query = getCurrentSession().createQuery(sql);
        query.setParameter("patientid", patientid);
        total = total + (Long) query.uniqueResult();
        
        sql = "Select count(*) from AssignedSurvey assignedSurvey join assignedSurvey.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientid and assignedSurvey.status IN :StatusList AND assignedSurvey.deletedAt is null ";
        query = getCurrentSession().createQuery(sql);
        query.setParameter("patientid", patientid);
//        query.setParameter("Pending", "Pending");
        query.setParameterList("StatusList", new String[] {Constants.PENDING,Constants.DUPLICATE});
           
        total = total + (Long) query.uniqueResult();

        return total;

    }

    public List<PatientGlucoseResults> getGlucoseResultHistoryByDate(Integer patientProfileSeqNo, Date starting, Date ending) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientGlucoseResults patientGlucoseResults ");
        sb.append("left join fetch patientGlucoseResults.patientProfile patientProfile ");
        sb.append("where patientProfile.patientProfileSeqNo =:patientProfileSeqNo "
                + "and (patientGlucoseResults.createdOn>=:starting and patientGlucoseResults.createdOn<=:ending) "
                + "order by patientGlucoseResults.createdOn desc");

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        query.setParameter("starting", starting);
        query.setParameter("ending", ending);
        return query.list();
    }

    public List<BloodPressure> getBloodPressureResultListByDate(Integer patientProfileSeqNo, Date starting, Date ending) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From BloodPressure bloodPressure ");
        sb.append("left join fetch bloodPressure.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo and "
                + "(bloodPressure.createdOn>=:starting and bloodPressure.createdOn<=:ending) ");
        sb.append("order by bloodPressure.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientProfileSeqNo);
        qury.setParameter("starting", starting);
        qury.setParameter("ending", ending);
        return qury.list();
    }

    public List<PatientBodyMassResult> getBodyMassResultListByDate(Integer patientProfileSeqNo, Date starting, Date ending) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientBodyMassResult patientBodyMassResult ");
        sb.append("left join fetch patientBodyMassResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo and "
                + "(patientBodyMassResult.createdOn>=:starting and patientBodyMassResult.createdOn<=:ending) ");
        sb.append("order by patientBodyMassResult.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientProfileSeqNo);
        qury.setParameter("starting", starting);
        qury.setParameter("ending", ending);
        return qury.list();
    }

    public List<PatientHeartPulseResult> getHeartPulseListByDate(Integer patinetProfileSeqNo, Date starting, Date ending) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientHeartPulseResult patientHeartPulseResult ");
        sb.append("left join fetch patientHeartPulseResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientProfileSeqNo "
                + "and (patientHeartPulseResult.createdOn>=:starting and patientHeartPulseResult.createdOn<=:ending) ");
        sb.append("order by patientHeartPulseResult.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientProfileSeqNo", patinetProfileSeqNo);
        qury.setParameter("starting", starting);
        qury.setParameter("ending", ending);
        return qury.list();
    }

    public List<Order> getMedicationListByProfileId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus left join fetch ord.drugDetail2 where patientProfile.patientProfileSeqNo=:patientId order by ord.createdAt desc");
        query.setParameter("patientId", patientId);
        return query.list();
    }

    public List<Order> getOrdersListByRxNo(Integer patientId, String RxNo) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus left join fetch ord.drugDetail2 where patientProfile.patientProfileSeqNo=:patientId and ord.rxNumber=:RxNo order by ord.createdAt desc");
        query.setParameter("patientId", patientId);
        query.setParameter("RxNo", RxNo);
        return query.list();
    }

    public List<Order> getOrdersListBy_RxNo_DrugId(Integer patientId, String RxNo, int DrugId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.patientProfile patientProfile left join fetch ord.orderStatus orderStatus left join fetch ord.drugDetail2 drugnew where patientProfile.patientProfileSeqNo=:patientId and ord.rxNumber=:RxNo and drugnew.id=:drugId order by ord.createdAt desc");
        query.setParameter("patientId", patientId);
        query.setParameter("RxNo", RxNo);
        query.setParameter("drugId", DrugId);
        return query.list();
    }

    public Long TotalWatingPtResponseCount(int patientId, Date date) {
        Long total;
        String hql = "select count(*) from Order ord join ord.patientProfile patientProfile join ord.orderStatus orderstatus where patientProfile.patientProfileSeqNo=:patientId and orderstatus.id=:statusid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("statusid", 2);
        total = (Long) query.uniqueResult();
        query = getCurrentSession().createQuery(" select count(*) From Order ord "
                + "join ord.drugDetail2 drugNew "
                + "join ord.orderStatus orderStatus "
                + "join ord.patientProfile patientProfile "
                + "where patientProfile.patientProfileSeqNo=:patientId "
                + "and ord.nextRefillFlag=:nextRefillFlag "
                + "and (ord.optOutRefillReminder=:optOutRefillReminder or ord.optOutRefillReminder is null)"
                + "and ord.nextRefillDate<=:nextRefillDate "
                + "and orderStatus.name in (:orderStatusList)");
        query.setParameter("nextRefillFlag", "0");
        query.setParameter("patientId", patientId);
        query.setParameter("optOutRefillReminder", 0);
        query.setParameter("nextRefillDate", date);
        query.setParameter("orderStatusList", "Processing");
        total = total + (Long) query.uniqueResult();
        return total;

    }

    public QuestionAnswer getQuestionAnswerByPatientId(Integer id) throws Exception {
        String hql = "from QuestionAnswer questionAnswer "
                + "left join fetch questionAnswer.patientProfile patientProfile "
                + "where patientProfile.id=:id "
                + "AND questionAnswer.answer is not null ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (QuestionAnswer) query.uniqueResult();
    }
        public QuestionAnswer getQuestionAnswerByNotificationId(Integer id) throws Exception {
        String hql = "from QuestionAnswer questionAnswer "
                + "left join fetch questionAnswer.patientProfile patientProfile "
                + "left join fetch questionAnswer.notificationMessages notificationMessages "
                + "where notificationMessages.id=:id ";
                      
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
               return (QuestionAnswer) query.uniqueResult();  
            }
    public QuestionAnswer getQuestionAnswerByQuestionId(Integer patientId, Long questionId) throws Exception {
        String hql = "from QuestionAnswer questionAnswer "
                + "left join fetch questionAnswer.patientProfile patientProfile "
                + "where questionAnswer.id=:id "
                + "AND patientProfile.patientProfileSeqNo=:patientId "
                + "AND questionAnswer.question is not null ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", questionId);
          query.setParameter("patientId", patientId);
        return (QuestionAnswer) query.uniqueResult();
    }
    public Long getTotalRefillCountByPatientId(int patientId) {
        Query query = getCurrentSession().createQuery("select sum(ord.refillsRemaining) from Order ord join ord.patientProfile patientProfile where patientProfile.patientProfileSeqNo=:patientId");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public List<ActivitesHistory> getPatientActivityHistory(Integer patientProfileSeqNo, Date starting, Date ending) {
        String hql = "From ActivitesHistory activityHistory Where activityHistory.patientProfile.patientProfileSeqNo =:patientProfileSeqNo "
                + "and (activityHistory.createdOn>=:starting and activityHistory.createdOn<=:ending) "
                + "order by activityHistory.createdOn desc";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setInteger("patientProfileSeqNo", patientProfileSeqNo);
        qury.setParameter("starting", starting);
        qury.setParameter("ending", ending);
        return qury.list();
    }

    public List<SurveyAnswer> getSurveyAnswerListByQuestionId(int questionId) throws Exception {
        String hql = "From SurveyAnswer surveyAnswer left join fetch surveyAnswer.surveyQuestion surveyQuestion where surveyQuestion.id=:questionId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("questionId", questionId);
        return query.list();
    }

    public Survey2 getSurveyByIdd(int surveyId) throws Exception {
        String hql = "From Survey2 survey WHERE survey.id =:surveyId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyId", surveyId);
        return (Survey2) query.uniqueResult();
    }
    public Survey getSurveyById(int surveyId) throws Exception {
        String hql = "From Survey survey WHERE survey.id =:surveyId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyId", surveyId);
        return (Survey) query.uniqueResult();
    }
    public List<SurveyQuestion> getSurveyQuestionBySurveyId(int surveyId) throws Exception {
        String hql = "From SurveyQuestion surveyQuestion "
                + "left join fetch  surveyQuestion.survey survey"
                + " WHERE survey.id =:surveyId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyId", surveyId);
        return query.list();
    }

    public Practices getPracticesByIdentity(String Identity) throws Exception {
        String hql = "from Practices practice where practice.practicephonenumber=:identity Or practice.practicelicensenumber =:identity ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("identity", Identity);
        return (Practices) qury.uniqueResult();
    }

    public Practices getPracticesByPhone(String Phone) throws Exception {
        String hql = "from Practices practice where practice.practicephonenumber=:Phone ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("Phone", Phone);
        return (Practices) qury.uniqueResult();
    }

    public Practices getPracticesByLicense(String License) throws Exception {
        String hql = "from Practices practice where practice.practicelicensenumber =:License ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("License", License);
        return (Practices) qury.uniqueResult();
    }

    public List<PatientProfile> getPatientProfileByPracticeId(int PracticeId) {
        String hql = "from PatientProfile patientProfile where patientProfile.practiceId=:practiceId";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("practiceId", PracticeId);
        return qury.list();
    }

    public Order getOrdersByRxNumberAndStatus(String rxNumber, int patientId) {
        String hql = "select orders from Order orders left join fetch  orders.complianceRewardPoint complianceRewardPoint "
                + "where orders.rxNumber =:rxNumber AND (orders.orderStatus.id=2 OR orders.orderStatus.id=23)"
                + " AND orders.patientProfile.patientProfileSeqNo=:patientId AND complianceRewardPoint.activityCount<4  "
                + "order by orders.createdAt desc ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("rxNumber", rxNumber);
        qury.setParameter("patientId", patientId);
        qury.setMaxResults(1);
        return (Order) qury.uniqueResult();
    }

    /*public List<Order> getOrdersByRxNumberAndStatus(String rxNumber, int patientId) {
        String hql="select orders from Order orders join ComplianceRewardPoint complianceRewardPoint ON (oders.id=complianceRewardPoint.orders.id) "
                + "where orders.rxNumber =:rxNumber AND (orders.orderStatus.id=2 OR orders.orderStatus.id=8) AND orders.patientProfile.patientProfileSeqNo=:patientId AND complianceRewardPoint.ActivityCount<4  "
                + "order by orders.createdAt desc "; 
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("rxNumber", rxNumber);
         qury.setParameter("patientId", patientId);
        return qury.list();
    }*/
    public Order getOrdersByStatus(int patientId) {
        String hql = "select orders from Order orders left join fetch  orders.complianceRewardPoint complianceRewardPoint "
                + "where (orders.orderStatus.id=2 OR orders.orderStatus.id=23)"
                + " AND orders.patientProfile.patientProfileSeqNo=:patientId AND complianceRewardPoint.activityCount<4 AND orders.rxPatientOutOfPocket >:assistAuth "
                + "order by orders.createdAt desc ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("patientId", patientId);
         qury.setParameter("assistAuth", 0.0);
        qury.setMaxResults(1);
        return (Order) qury.uniqueResult();
    }

    public Order getOrdersByRxNumberAndStatusWithOutActivityCount(String rxNumber, int patientId) {
        String hql = "select orders from Order orders left join fetch  orders.complianceRewardPoint complianceRewardPoint "
                + "where orders.rxNumber =:rxNumber AND (orders.orderStatus.id=2 OR orders.orderStatus.id=23)"
                + " AND orders.patientProfile.patientProfileSeqNo=:patientId "
                + "order by orders.createdAt desc ";

        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("rxNumber", rxNumber);
        qury.setParameter("patientId", patientId);
        qury.setMaxResults(1);
        return (Order) qury.uniqueResult();
    }

    public Order getOrdersByStatusWithOutActivityCount(int patientId) {
        String hql = "select orders from Order orders left join fetch  orders.complianceRewardPoint complianceRewardPoint "
                + "where (orders.orderStatus.id=2 OR orders.orderStatus.id=23) AND orders.patientProfile.patientProfileSeqNo=:patientId AND orders.rxPatientOutOfPocket >:assistAuth "
                + "order by orders.createdAt desc ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("patientId", patientId);
         qury.setParameter("assistAuth", 0.0);
        qury.setMaxResults(1);
        return (Order) qury.uniqueResult();
    }

    public ComplianceRewardPoint getComplianceRewardPoint(int patientId, String orderId) throws Exception {
        String hql = "from ComplianceRewardPoint complianceRewardPoint "
                + "where complianceRewardPoint.profile.patientProfileSeqNo=:patientId AND complianceRewardPoint.orders.id=:orderId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId", orderId);
        query.setParameter("patientId", patientId);
        return (ComplianceRewardPoint) query.uniqueResult();
    }

    public Long getTotalOrderPaymentPending(int profileId, int orderStatusId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Select count(*) ");
        sb.append("FROM Order o ");
        sb.append("LEFT JOIN FETCH o.patientProfile patientProfile ");
        sb.append("LEFT JOIN FETCH o.orderStatus orderStatus ");
        sb.append("WHERE patientProfile.patientProfileSeqNo= :profileId ");
        sb.append("AND orderStatus.id = :orderStatusId");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setParameter("profileId", profileId);
        query.setParameter("orderStatusId", orderStatusId);
        return (Long) query.uniqueResult();
    }

    public String getAllergyNameByAllergyId(int patientId, int allergyId) {
        Query query = getCurrentSession().createSQLQuery("select Allergies from PatientAllergies where PatientId=:patientId and Id=:allergyId");
        query.setParameter("patientId", patientId);
        query.setParameter("allergyId", allergyId);
        return (String) query.uniqueResult();
    }

    public List<RewardHistory> getRewardHistoryListbyPatientId(int patientId, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from RewardHistory rewardHistory left join fetch rewardHistory.activityId rewardActivity  where rewardHistory.patientId=:profileId AND rewardHistory.createdDate >=:startDate AND rewardHistory.createdDate <=:endDate order by rewardHistory.createdDate desc");
        query.setParameter("profileId", patientId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public RewardActivity getRewardActivityById(int id) {
        Query query = getCurrentSession().createQuery("from RewardActivity rewardActivity where rewardActivity.activityId=:id ");
        query.setParameter("id", id);
        return (RewardActivity) query.uniqueResult();
    }

    public List<Order> getOrderListForRefillOnTime(int patientId) throws Exception {

        String hql = "from Order order "
                + "left join fetch order.patientProfile patientProfile "
                + "left join fetch order.orderStatus orderStatus "
                + "WHERE  order.patientProfile=:patientId "
                + "AND orderStatus.id=:orderStatusId "
                + "AND order.nextRefillFlag=:nextRefillFlag ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("orderStatusId", 2);
        query.setParameter("nextRefillFlag", 1);
//        query.setParameter("(patientId", 2);
//        query.setParameter("CurrntDate", new Date());  
        return query.list();
    }

    public PatientBodyMassResult getBodyMassResutByPatientId(Integer patientPrfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientBodyMassResult patientBodyMassResult ");
        sb.append("left join fetch patientBodyMassResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo order by patientBodyMassResult.bodyMassResultSeqNo desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientPrfileSeqNo);
        qury.setMaxResults(1);
        return (PatientBodyMassResult) qury.uniqueResult();
    }

    public BloodPressure getBbloodPressureByPatientId(Integer patientPrfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From BloodPressure bloodPressure ");
        sb.append("left join fetch bloodPressure.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo order by bloodPressure.bloodPresureSeqNo desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientPrfileSeqNo);
        qury.setMaxResults(1);
        return (BloodPressure) qury.uniqueResult();
    }

    public PatientGlucoseResults getPatientGlucoseResultsByPatientId(Integer patientPrfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientGlucoseResults patientGlucoseResults ");
        sb.append("left join fetch patientGlucoseResults.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo order by patientGlucoseResults.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientPrfileSeqNo);
        qury.setMaxResults(1);
        return (PatientGlucoseResults) qury.uniqueResult();
    }

    public PatientHeartPulseResult getPatientHeartPulseResultByPatientId(Integer patientPrfileSeqNo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From PatientHeartPulseResult patientHeartPulseResult ");
        sb.append("left join fetch patientHeartPulseResult.patientProfile patientProfile ");
        sb.append("Where patientProfile.patientProfileSeqNo =:patientPrfileSeqNo order by patientHeartPulseResult.createdOn desc ");
        Query qury = getCurrentSession().createQuery(sb.toString());
        qury.setInteger("patientPrfileSeqNo", patientPrfileSeqNo);
        qury.setMaxResults(1);
        return (PatientHeartPulseResult) qury.uniqueResult();
    }

    public Long getTotalCountPatientHeartPulseResult(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientHeartPulseResult patientHeartPulseResult where patientHeartPulseResult.patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalCountPatientPatientGlucoseResults(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientGlucoseResults patientGlucoseResults where patientGlucoseResults.patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalCountPatientBloodPressureResult(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From BloodPressure bloodPressure where bloodPressure.patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalCountPatientPatientBodyMassResultResult(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientBodyMassResult patientBodyMassResult where patientBodyMassResult.patientProfile.patientProfileSeqNo=:patientId ");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public NotificationMessages getNotificationMessagesByMessageId(int messageId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.patientProfile patientProfile "
                + "left join fetch notificationMessages.messageType messageType "
                + "where notificationMessages.id=:id "
                + "and notificationMessages.status=:status "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", messageId);
        query.setParameter("status", Constants.SUCCESS);
        return (NotificationMessages) query.uniqueResult();
    }

    public NotificationMessages getNotificationMessageByOrdrId(String orderId) throws Exception {

        String hql = "FROM NotificationMessages notificationMessages "
                + "WHERE notificationMessages.orders.id =:orderId "
                + "AND notificationMessages.isArchive =:isArchive "
                + "AND notificationMessages.messageType.id.messageTypeId = :messageTypeid "
                + "ORDER BY notificationMessages.id DESC ";
        Query qry = getCurrentSession().createQuery(hql);
        qry.setParameter("orderId", orderId);
        qry.setParameter("messageTypeid", 4);
        qry.setParameter("isArchive", Boolean.FALSE);
        qry.setMaxResults(1);
        return (NotificationMessages) qry.uniqueResult();

    }

    public NotificationMessages getNotificationMessageByOrdrIdforRefill(String orderId) throws Exception {

        String hql = "FROM NotificationMessages notificationMessages "
                + "WHERE notificationMessages.orders.id =:orderId "
                + "AND notificationMessages.isArchive =:isArchive "
                + "AND notificationMessages.messageType.id.messageTypeId =:messageTypeId "
                + "ORDER BY notificationMessages.id DESC ";
        Query qry = getCurrentSession().createQuery(hql);
        qry.setParameter("orderId", orderId);
        qry.setParameter("messageTypeId", 28);
        qry.setParameter("isArchive", Boolean.FALSE);
        qry.setMaxResults(1);
        return (NotificationMessages) qry.uniqueResult();

    }

    public PatientDeliveryAddress getPatientDeliveryAddressByPatientId(Integer profileId) throws Exception {
        PatientDeliveryAddress patientDeliveryAddress = new PatientDeliveryAddress();
        Query query = getCurrentSession().createQuery("From PatientDeliveryAddress patientDeliveryAddress "
                + "left join fetch patientDeliveryAddress.patientProfile patientProfile "
                + "left join fetch patientDeliveryAddress.state state "
                + "where patientProfile.patientProfileSeqNo=:profileId ");
        query.setParameter("profileId", profileId);
        if (!CommonUtil.isNullOrEmpty(query.list())) {
            patientDeliveryAddress = (PatientDeliveryAddress) query.list().get(0);
        }
        return patientDeliveryAddress;
    }

    public PatientProfile getProfileBySecurityToken(String securityToken) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.securityToken=:securityToken");
        query.setParameter("securityToken", securityToken);
        // query.setParameter("status", Constants.COMPLETED);
        return (PatientProfile) query.uniqueResult();
    }

    public AssignedSurvey getAssignedSurveysByIdd(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyList "
                + "join fetch surveyList.survey srvy "
                + "join fetch srvy.surveyType "
                + "where surveyList.patientProfile.patientProfileSeqNo=:PatientId "
                + "And surveyList.id=:Id AND surveyList.status !=:status AND surveyList.deletedAt IS NOT NULL ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
        query.setParameter("status", Constants.COMPLETED);
        return (AssignedSurvey) query.uniqueResult();
    }
        public AssignedSurvey getAssignedSurveysById(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyList "
                + "join fetch surveyList.survey srvy "
                + "join fetch srvy.surveyType "
                + "where surveyList.patientProfile.patientProfileSeqNo=:PatientId "
                + "And surveyList.id=:Id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
//        query.setParameter("status", Constants.COMPLETED);
        return (AssignedSurvey) query.uniqueResult();
    }
//    public AssignedSurvey getAssignedSurveysStatusById(int patientId, Long id) {
//        String hql = "From AssignedSurvey surveyList "
////                + "join fetch surveyList.survey srvy "
////                + "join fetch srvy.surveyType "
//                + "where surveyList.patientProfile.patientProfileSeqNo=:PatientId "
//                + "And surveyList.id=:Id";
//        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("PatientId", patientId);
//        query.setParameter("Id", id);
//        return (AssignedSurvey) query.uniqueResult();
//    }
    public List<AssignedSurvey> getAssignedSurveysBypatientId(int patientId) {
        String hql = "From AssignedSurvey surveyList "
                + "join fetch surveyList.survey srvy "
                + "join fetch srvy.surveyType "
                + "where surveyList.patientProfile.patientProfileSeqNo=:PatientId "
                + "AND surveyList.status IN :status "
                + "AND surveyList.deletedAt is Null ";
//                + "AND surveyList.survey.id != 48 ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
//        query.setParameter("status", Constants.PENDING);
         query.setParameterList("status", new String[]{Constants.PENDING,Constants.DUPLICATE});
        return query.list();
    }

    public Long getTotalCountSurvey(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From AssignedSurvey assignedSurvey "
                + "where assignedSurvey.patientProfile.patientProfileSeqNo=:patientId "
                + "AND assignedSurvey.status in :status AND assignedSurvey.deletedAt is null ");
//                + "AND assignedSurvey.survey.id != 48 ");
        query.setParameter("patientId", patientId);
//        query.setParameter("Completed", Constants.COMPLETED);
        query.setParameterList("status", new String[]{Constants.PENDING,Constants.DUPLICATE});
        return (Long) query.uniqueResult();
    }
        public Long getTotalCountSurveylogs(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From SurveyLogs assignedSurvey where assignedSurvey.patientProfile.patientProfileSeqNo=:patientId AND assignedSurvey.status <>:Completed ");
        query.setParameter("patientId", patientId);
        query.setParameter("Completed", Constants.COMPLETED);
        return (Long) query.uniqueResult();
    }

    public Long getTotalPresentSurvey() throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From Survey2 Survey2 ");
        return (Long) query.uniqueResult();
    }

//    public List<Order> getRefillAbleOrderListForReminder(Date repeatDate) throws Exception {
//        String hql = "FROM Order ord "
//                + "WHERE ord.refillReinderCount < 4 "
//                + "AND ord.orderStatus = 23 "
//                + "AND ord.nextRefillFlag =:refillFlag "
//                + "AND ord.lastReminderDate <=:repeatDate ";
//        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("repeatDate", repeatDate);
////        query.setParameter("ordStatus", 23);
//        query.setParameter("refillFlag", "1");
//        return query.list();
//    }
      public List<Object[]> getRefillAbleOrderListForSecondReminder() throws Exception {

        String sql = "SELECT *  FROM Orders WHERE `OrderStatus`= 8 AND `RefillReminderCount`< 4 AND `nextRefillFlag`=1 \n"
                + "AND `OptOutRefillReminder`= 0 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())>=48 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())<72";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getRefillAbleOrderListForThirdReminder() throws Exception {
   
        String sql = "SELECT *  FROM Orders WHERE `OrderStatus`= 8 AND `RefillReminderCount`< 4 AND `nextRefillFlag`=1 \n"
                + "AND `OptOutRefillReminder`= 0 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())>=120 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())<144";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getRefillAbleOrderListForFourtReminder() throws Exception {

        String sql = "SELECT *  FROM Orders WHERE `OrderStatus`= 8 AND `RefillReminderCount`< 4 AND `nextRefillFlag`=1 \n"
                + "AND `OptOutRefillReminder`= 0 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())>=240 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())<264";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> result = query.list();
        return result;
    }
    public PharmacyPatientMessage getPharmacyPatientMessageByOrderId(String orderId) throws Exception {
        String hql = "From PharmacyPatientMessage pharmacyPatientMessage "
                + "where (pharmacyPatientMessage.id) in (SELECT MAX(pharmacyPatientMessage.id) FROM PharmacyPatientMessage pharmacyPatientMessage WHERE pharmacyPatientMessage.order.id =:orderId) ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId", orderId);
        return (PharmacyPatientMessage) query.uniqueResult();
    }

    public OrderStatus getOrderStausById(int sttausId) throws Exception {
        String hql = "from OrderStatus orderStatus where orderStatus.id =:sttausId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sttausId", sttausId);
        return (OrderStatus) query.uniqueResult();
    }

    public OrderCustomDocumments getOrderCustomDocumments(String orderId) throws Exception {
        String hql = "FROM OrderCustomDocumments orderCustomDocumments "
                + "WHERE (orderCustomDocumments.id) in (SELECT MAX(orderCustomDocumments.id) "
                + "FROM OrderCustomDocumments orderCustomDocumments "
                + "WHERE orderCustomDocumments.order.id =:orderId) ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId", orderId);
        return (OrderCustomDocumments) query.uniqueResult();
    }
    public PharmacyPatientMessage getPharmacyPatientMessageById(int id) throws Exception {
        String hql = "From PharmacyPatientMessage pharmacyPatientMessage "
                + "where  pharmacyPatientMessage.id =:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (PharmacyPatientMessage) query.uniqueResult();
    }
     public List<Object[]> getMBROrderReminderList(int orderStatusId,int fromHour,int toHour,int mbrFolderId,int mbrMsgTypeId) throws Exception {

        String sql = "SELECT orders.*  FROM Orders orders"
                + " INNER JOIN `NotificationMessages` notificationMessages ON notificationMessages.`OrderID`=orders.`Id`"
                + " AND notificationMessages.`FolderId`=:mbrFolderId AND notificationMessages.`MessageTypeId`=:mbrMsgTypeId"
                + " WHERE orders.`OrderStatus`=:orderStatusId AND orders.`MbrReminderCount`< 4 AND orders.`MBRFlag`=1 "
                + "AND TIMESTAMPDIFF(HOUR,notificationMessages.`CreatedOn`, NOW())>=:fromHour "
                + "AND TIMESTAMPDIFF(HOUR,notificationMessages.`CreatedOn`, NOW())<:toHour";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("orderStatusId", orderStatusId);
        query.setParameter("fromHour", fromHour);
        query.setParameter("toHour", toHour);
        query.setParameter("mbrFolderId", mbrFolderId);
        query.setParameter("mbrMsgTypeId", mbrMsgTypeId);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getMBROrderListForThirdReminder() throws Exception {

        String sql = "SELECT *  FROM Orders WHERE `OrderStatus`= 2 AND `MbrReminderCount`< 4 AND `MBRFlag`=1 \n"
                + "AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())>=48 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())<72";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> result = query.list();
        return result;
    }

    public List<Object[]> getMBROrderListForFourtReminder() throws Exception {

        String sql = "SELECT *  FROM Orders WHERE `OrderStatus`= 2 AND `MbrReminderCount`< 4 AND `MBRFlag`=1 \n"
                + "AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())>=72 AND TIMESTAMPDIFF(HOUR, `LastReminderDate`, NOW())<96";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> result = query.list();
        return result;
    }
        public Survey2 getSurvyById(Long surveyId) throws Exception {
        String hql = "From Survey2 survey WHERE survey.id =:surveyId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyId", surveyId);
        return (Survey2) query.uniqueResult();
    }
        public SurveyTheme getSurveyThemeBySurveyId(long surveyId) throws Exception {
        String hql = "From SurveyTheme surveyTheme "
                + "left join fetch surveyTheme.survey survey "
                + "where survey.id =:surveyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyId", surveyId);
        return (SurveyTheme) query.uniqueResult();
        
        }
            public AssignedSurvey getSurveyLogsById(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyLogs "
                + "join fetch surveyLogs.survey survey "
                + "join fetch survey.surveyType "
                + "where surveyLogs.patientProfile.patientProfileSeqNo=:PatientId "
                + "And surveyLogs.id=:Id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
        return (AssignedSurvey) query.uniqueResult();
    }
          public AssignedSurvey getSurveyLogsBySurveyId(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyLogs "
                + "join fetch surveyLogs.survey survey "
                + "join fetch survey.surveyType "
                + "where surveyLogs.patientProfile.patientProfileSeqNo=:PatientId "
                + "And survey.id=:Id order by surveyLogs.updatedAt desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
        query.setMaxResults(1);
        return (AssignedSurvey) query.uniqueResult();
    }
       public AssignedSurvey getSurveyLogsBySurveyIdd(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyLogs "
                + "join fetch surveyLogs.survey survey "
                + "join fetch survey.surveyType "
                + "where surveyLogs.patientProfile.patientProfileSeqNo=:PatientId "
                + "And survey.id=:Id order by surveyLogs.createdAt desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
        query.setMaxResults(1);
        return (AssignedSurvey) query.uniqueResult();
    }
              public AssignedSurvey getSurveyLogsBySurveyLogId(int patientId, Long id) {
        String hql = "From AssignedSurvey surveyLogs "
                + "join fetch surveyLogs.survey survey "
                + "join fetch survey.surveyType "
                + "where surveyLogs.patientProfile.patientProfileSeqNo=:PatientId "
                + "And surveyLogs.id=:Id order by surveyLogs.createdAt desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("PatientId", patientId);
        query.setParameter("Id", id);
//        query.setMaxResults(1);
        return (AssignedSurvey) query.uniqueResult();
    }
        public List<MessageThreads> getMessageThreadByOrderId(Integer id, String messgeType) throws Exception {
        String hql = "from MessageThreads messageThreads "
                + "join fetch messageThreads.order ord "
                + "join fetch ord.orderStatus orderStatus "
                + "join fetch ord.drugDetail2 drugDetail2 "
                + "where messageThreads.profile.patientProfileSeqNo=:id and messageThreads.messsgeType=:type "
                + "Order by messageThreads.createdAt desc";                
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("type", messgeType);
        return query.list();
    }
        public AppointmentRequest getAppointmentRequestById(int id, int patientId){
        String hql = "from AppointmentRequest appointmentRequest where appointmentRequest.profile.id=:patientId AND appointmentRequest.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
         query.setParameter("patientId", patientId);
        return (AppointmentRequest) query.uniqueResult();    
        }
      public MessageThreads getMessageThreadByOrderId(String orderId) throws Exception {
        String hql = "from MessageThreads messageThreads where messageThreads.order.id=:orderId order by messageThreads.createdAt desc";
        Query query = getCurrentSession().createQuery(hql);     
        query.setParameter("orderId", orderId);
        query.setMaxResults(1);
        return (MessageThreads) query.uniqueResult();
      }
       public List<DiseaseDetail> getDiseaseList() throws Exception {
        String hql = "from DiseaseDetail messageThreads ";
        Query query = getCurrentSession().createQuery(hql);     
        return query.list();
      }
//       public List<Practices> getPracticesList() throws Exception{
//       String hql = "from Practices practices where practices.practicetype=:Physician ";
//        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("Physician", "Physician");
//        return query.list();
//       }
       public List<RxReporterUsers> getPracticesList() throws Exception {
       String hql = "from RxReporterUsers rxReporterUsers "
               + "LEFT JOIN FETCH rxReporterUsers.practiceId practices "
               + "where practices.practicetype=:Physician ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("Physician", "Physician");
        return query.list();
       }
        public Practices getPracticesById(int id) throws Exception {
        String hql = "from Practices practice where practice.id=:id ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("id", id);
        return (Practices) qury.uniqueResult();
    }
        public RxReporterUsers getRxReporterUsersById(int doctorId) throws Exception {
        String hql = "from RxReporterUsers rxReporterUsers "
//                + "LEFT JOIN FETCH rxReporterUsers.practiceId practice "
                + "where rxReporterUsers.practiceId.id=:id ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("id", doctorId);
        return (RxReporterUsers) qury.uniqueResult();
    }
        public RxReporterUsers getRxReporterUsersByUserId(Long userId) throws Exception {
        String hql = "from RxReporterUsers rxReporterUsers "
//                + "LEFT JOIN FETCH rxReporterUsers.practiceId practice "
                + "where rxReporterUsers.id=:id ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("id", userId);
        return (RxReporterUsers) qury.uniqueResult();
    }
      public Order getOrderDetailById(String orderId, int patientId) throws Exception {
        String hql = "From Order ord "
                + "LEFT JOIN FETCH ord.drugDetail2 drugDetail2 "
                + "WHERE ord.id =:orderId "
                + "AND ord.patientProfile.patientProfileSeqNo=:patientId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId", orderId);
        query.setParameter("patientId", patientId);
        return (Order) query.uniqueResult();
    }
        public Order getOrderDetailByPatientId(int patientId) throws Exception {
        String hql = "From Order ord WHERE ord.patientProfile.patientProfileSeqNo=:patientId order by ord.createdAt desc";             
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setMaxResults(1);
        return (Order) query.uniqueResult();
    }
        public List<ReporterMessages> getReportMsgsList(int userFrom, int userTo){
          String hql = "From ReporterMessages reporterMessages "
                  + "Where reporterMessages.userFrom =:userFrom "
                  + "AND reporterMessages.userTo=:userTo "
                  + "order by reporterMessages.crateAt desc ";
          Query query = getCurrentSession().createQuery(hql);
          query.setParameter("userFrom", userFrom);
          query.setParameter("userTo", userTo);
          return  query.list();
        }
        
    public NotificationMessages getNotificationMessagesByMessageIdAndPatientId(int patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.patientProfile patientProfile  "
                + "left join fetch notificationMessages.orders orders "
                + "left join fetch notificationMessages.messageType messageType "
//                + "left join fetch messageType.id messageTypeId "
//                + "where messageTypeId.messageTypeId=:id "
//                + "where notificationMessages.status=:status "
                + "where patientProfile.patientProfileSeqNo =:patientId "
                + "and orders.id is null "
                + "order by notificationMessages.createdOn desc");
//        query.setParameter("id", messageId);
//        query.setParameter("status", Constants.SUCCESS);
        query.setParameter("patientId", patientId);
        return (NotificationMessages) query.uniqueResult();
    }
        public List<Order> getOrderDetailListByPatientId(int patientId) throws Exception {
        String hql = "Select ord.prescriberId,ord.practiceId From Order ord WHERE ord.patientProfile.patientProfileSeqNo=:patientId order by ord.createdAt desc";             
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        return query.list();
    }
//        public List<Integer> getOrderDetailListByPatientId(int patientId) throws Exception {
//        String hql = "Select ord.prescriberId,ord.practiceId From Order ord WHERE ord.patientProfile.patientProfileSeqNo=:patientId order by ord.createdAt desc";             
//        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("patientId", patientId);
//        return query.list();
//    }
       public List<Order> getOrderDetailListByPatientIdd(int patientId) throws Exception {
        String hql = "From Order ord WHERE ord.patientProfile.patientProfileSeqNo=:patientId order by ord.createdAt desc";             
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        return query.list();
    }
      public Long getCountSentMessageForOrder(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From MessageThreads messageThreads "
                + "where messageThreads.messgeStaus=:status "
                + "and messageThreads.profile.patientProfileSeqNo=:id ");
        query.setParameter("id", profileId);
         query.setParameter("status", Constants.SENT);
        return (Long) query.uniqueResult();
      }
    public ReporterChatSession getsessionStatus(int patientId, int reporterId) throws Exception {
       String hql = "from ReporterChatSession reporterChatSession "
               + "where reporterChatSession.patientId=:patientId "
               + "and reporterChatSession.reporterId=:reporterId order by reporterChatSession.createdAt desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setParameter("reporterId", reporterId);
        query.setMaxResults(1);
        return (ReporterChatSession) query.uniqueResult();
       }
        public MessageThreads getMessageThreadByPatientId(Integer profileId, String orderId) throws Exception {
        String hql = "from MessageThreads messageThreads where messageThreads.profile.patientProfileSeqNo=:profileId AND messageThreads.order.id =:orderId Order by messageThreads.createdAt desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("profileId", profileId);
        query.setParameter("orderId", orderId);
        query.setMaxResults(1);
        return (MessageThreads) query.uniqueResult();
    }
       public List<MessageThreads> getPatientMessagesByDate(Integer id, Date startdate, Date enddate) throws Exception {
        String hql = "from MessageThreads messageThreads where messageThreads.profile.patientProfileSeqNo=:id AND (messageThreads.createdAt>=:startdate AND messageThreads.createdAt<=:enddate)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return query.list();
    }
      public MessageThreads getMessageThreadByOrderIdAndMsgId(String orderId, int messgeId) throws Exception {
        String hql = "from MessageThreads messageThreads "
                + "where messageThreads.order.id=:orderId "
                + "AND messageThreads.id=:messgeId  "
                + "order by messageThreads.createdAt desc";
        Query query = getCurrentSession().createQuery(hql);     
        query.setParameter("orderId", orderId);
        query.setParameter("messgeId", messgeId);
        query.setMaxResults(1);
        return (MessageThreads) query.uniqueResult();
      }
        public Long getCountReadMessageForOrder(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From MessageThreads messageThreads "
                + "where messageThreads.markAsRead=:isRead AND messageThreads.messge is not null "
                + "and messageThreads.profile.patientProfileSeqNo=:id AND messageThreads.messgeStaus=:status ");
        query.setParameter("id", profileId);
        //query.setParameter("status", Constants.RECEIVED);
        query.setParameter("status", Constants.SENT);
         query.setParameter("isRead", Boolean.FALSE);
        return (Long) query.uniqueResult(); 
      }
        public Long getGenralQuestionReleventChatCount(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From MessageThreads messageThreads "
                + "where messageThreads.questionId is not null "
                + "AND messageThreads.markAsRead=:isRead "
                + "AND messageThreads.messge is not null and messageThreads.messgeStaus=:status "
                + "and messageThreads.profile.patientProfileSeqNo=:id");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SENT);
         query.setParameter("isRead", Boolean.FALSE);
        return (Long) query.uniqueResult(); 
      }

          public List<MessageThreads> getMessageThreadByOrderId(Integer id, String orderId, String msgType) throws Exception {
        String hql = "from MessageThreads messageThreads "
                + "where messageThreads.profile.id=:id "
                + "AND messageThreads.order.id=:orderId AND messageThreads.messsgeType=:msgType ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("orderId", orderId);
          query.setParameter("msgType", msgType);
        return query.list();
    }
         public List<MessageThreads> getMessageThreadByQuestionId(Long questionId) throws Exception {
        String hql = "from MessageThreads messageThreads where messageThreads.questionId=:questionId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("questionId", questionId);
        return query.list();
    }
       public List<QuestionAnswer> getQuestionAnswerById(Integer id, Long questionid) throws Exception {
        String hql = "from QuestionAnswer qa where qa.patientProfile.patientProfileSeqNo=:id AND qa.id=:QuestionID";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("QuestionID", questionid);
        return query.list();
    }
       public Long getCountReadGenralQuestion(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From QuestionAnswer qa "
                + "where qa.isRead=:isRead AND qa.answer is not null "
                + "and qa.patientProfile.patientProfileSeqNo=:id ");
        query.setParameter("id", profileId);
         query.setParameter("isRead", Boolean.FALSE);
        return (Long) query.uniqueResult(); 
      }
             public List<ReporterMessages> getReportMsgsListBySessionId(int sessionId){
          String hql = "From ReporterMessages reporterMessages "
                  + "Where reporterMessages.sessionId =:sessionId "
                  + "order by reporterMessages.crateAt desc ";
          Query query = getCurrentSession().createQuery(hql);
          query.setParameter("sessionId", sessionId);
          return  query.list();
        }
        public List<ReporterMessages> getReportMsgsByPatient(int userFrom, int userTo){
          String hql = "From ReporterMessages reporterMessages "
                  + "Where reporterMessages.userFrom =:userFrom "
                  + "AND reporterMessages.userTo=:userTo "
                  + "order by reporterMessages.crateAt desc ";
          Query query = getCurrentSession().createQuery(hql);
          query.setParameter("userFrom", userFrom);
          query.setParameter("userTo", userTo);
          return  query.list();
        }
         public List<ReporterMessages> getReportMsgsByReporter(int userFrom){
          String hql = "From ReporterMessages reporterMessages "
                  + "Where reporterMessages.userFrom =:userFrom "
//                  + "Where reporterMessages.userTo=:userTo "
                  + "order by reporterMessages.crateAt desc ";
          Query query = getCurrentSession().createQuery(hql);
          query.setParameter("userFrom", userFrom);
//          query.setParameter("userTo", userTo);
          return  query.list();
        }
      public Long getTotalUserNotificationCouunt(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From NotificationMessages notificationMessages "
                + "where notificationMessages.isRead=:isRead "
                + "and notificationMessages.isDelete =:isDeleted "
                + "and notificationMessages.messageType.id.messageTypeId not in (41,57,2,6)"
                + "and patientProfile.patientProfileSeqNo=:id");
        query.setParameter("isRead", Boolean.FALSE);
        query.setParameter("id", profileId);
        query.setParameter("isDeleted", "No");
        return (Long) query.uniqueResult();
    }
          public List<Order> getOrderListByRxnumber(int profileId, String rxNumber)throws Exception {
         String hql = "from Order ord "
                 + "where ord.rxNumber =:rxNumber "
                 + "and ord.patientProfile.patientProfileSeqNo=:profileId ";
         Query qury = getCurrentSession().createQuery(hql);
         qury.setParameter("profileId", profileId);
         qury.setParameter("rxNumber", rxNumber);
         return qury.list();
    }
     public NotificationMessages getNotificationMessageByOrdrIdAndMsgTypeId(String orderId) throws Exception {

        String hql = "FROM NotificationMessages notificationMessages "
                + "WHERE notificationMessages.orders.id =:orderId "
                + "AND notificationMessages.isArchive =:isArchive "
                + "AND notificationMessages.messageType.id.messageTypeId = :messageTypeid "
                + "ORDER BY notificationMessages.id DESC ";
        Query qry = getCurrentSession().createQuery(hql);
        qry.setParameter("orderId", orderId);
        qry.setParameter("messageTypeid", 75);
        qry.setParameter("isArchive", Boolean.FALSE);
        qry.setMaxResults(1);
        return (NotificationMessages) qry.uniqueResult();

    }
      public Order getRefillReminderOrders(Date date, List<String> orderStatusList, String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord "
                + "left join fetch ord.patientProfile patientProfile "
                + "left join fetch ord.drugDetail2 drugNew "
                + "left join fetch ord.orderStatus orderStatus "
                + "where ord.lastReminderDate is null "
                + "and ord.nextRefillFlag=:nextRefillFlag "
                + "and (ord.optOutRefillReminder=:optOutRefillReminder or ord.optOutRefillReminder is null)"
                + "and ord.nextRefillDate<=:nextRefillDate and ord.id=:OrderId "
                + "and orderStatus.name in (:orderStatusList)");
        query.setParameter("nextRefillFlag", "0");
        query.setParameter("optOutRefillReminder", 0);
        query.setParameter("nextRefillDate", date);
        query.setParameter("OrderId", orderId);
        query.setParameterList("orderStatusList", orderStatusList);
        return (Order) query.uniqueResult();
    }
     public Long getGenralQuestionReleventChatCount(Long questionId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From MessageThreads messageThreads "
                + "where messageThreads.markAsRead=:isRead "
                + "AND messageThreads.messge is not null "
                + "and messageThreads.questionId=:questionId "
                + "and messageThreads.messgeStaus=:status "
                + "and messageThreads.messsgeType=:Pharmacy ");
        query.setParameter("questionId", questionId);
         query.setParameter("isRead", Boolean.FALSE);
         query.setParameter("status", Constants.SENT);
           query.setParameter("Pharmacy", Constants.PHARMACY);
          return (Long) query.uniqueResult(); 
       
      }
          public Long getGenralQuestionReleventHCPChatCount(Long questionId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) "
                + "From MessageThreads messageThreads "
                + "where messageThreads.markAsRead=:isRead "
                + "AND messageThreads.messge is not null "
                + "and messageThreads.questionId=:questionId "
                + "and messageThreads.messgeStaus=:status "
                + "and messageThreads.messsgeType=:HCP ");
        query.setParameter("questionId", questionId);
         query.setParameter("isRead", Boolean.FALSE);
         query.setParameter("status", Constants.SENT);
         query.setParameter("HCP", Constants.HCP);
          return (Long) query.uniqueResult(); 
       
      }
     public Date getMessageThreadDateOfLastMessage(Long questionId) throws Exception {
            String hql = "select qa.createdAt "
                + "from MessageThreads qa "
                    + "where qa.questionId=:questionId order by qa.createdAt desc";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("questionId", questionId);
            query.setMaxResults(1);
            return (Date) query.uniqueResult();
     }
    public QuestionAnswer getQuestionAnswerByQuestionId(Long questionId) throws Exception {
        String hql = "from QuestionAnswer questionAnswer "
                + "where questionAnswer.id=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", questionId);
//          query.setParameter("patientId", patientId);
        return (QuestionAnswer) query.uniqueResult();
    }
      public ComplianceRewardPoint getComplianceRewardPoint(int patientId) throws Exception {
        String hql = "from ComplianceRewardPoint complianceRewardPoint "
                + "where complianceRewardPoint.profile.patientProfileSeqNo=:patientId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("patientId", patientId);
        query.setMaxResults(1);
        return (ComplianceRewardPoint) query.uniqueResult();
    }
    public Order getOrderDetialByRxNumber(String rxNumber) throws Exception {
      Query query = getCurrentSession().createQuery("from Order order where order.rxNumber=:rxNumber ");
      query.setParameter("rxNumber", rxNumber);
      query.setMaxResults(1);
      return (Order) query.uniqueResult();
    
    }
     public List<Order> getOrderListByRxnumber(String rxNumber, int patientId)throws Exception {
         String hql = "from Order ord "
                 + "where ord.rxNumber =:rxNumber and ord.refillDone=:refillDone and ord.patientProfile.patientProfileSeqNo=:patientId ";
         Query qury = getCurrentSession().createQuery(hql);
         qury.setParameter("rxNumber", rxNumber);
         qury.setParameter("patientId", patientId);
         qury.setParameter("refillDone", 1);
         return qury.list();
    }
    public List<Order> getRefillAbleOrderListByPatientId(int patientId) throws Exception {
        String hql = "from Order ord "
                + "where ord.refillDone=:refillDone "
                + "and ord.patientProfile.patientProfileSeqNo=:patientId "
                + "and ord.nextRefillDate<=:Date and ord.refillsRemaining >:refillRemaining "
                + "and orderStatus.name =:orderStatus and ord.viewStatus is null ";
        Query qury = getCurrentSession().createQuery(hql);
        qury.setParameter("patientId", patientId);
        qury.setParameter("Date", new Date());
        qury.setParameter("refillDone", 0);
           qury.setParameter("refillRemaining", 0);
        qury.setParameter("orderStatus", Constants.ORDER_STATUS.FILLED);
        return qury.list();
    }
        public List<NotificationMessages> getArchivedNotificationMessagesByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.patientProfile patientProfile "
                + "left join fetch notificationMessages.messageType messageType "
                + "where patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.status=:status "
                + "and notificationMessages.isDelete =:isDeleted "
//                + "and messageType.id.messageTypeId <> in  (41,57) "
                 + "and messageType.id.messageTypeId != 41 and messageType.id.messageTypeId != 57 "
                + "and notificationMessages.isArchive=:isArchived "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        query.setParameter("isArchived", Boolean.TRUE);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
            public List<NotificationMessages> getNotificationMessagesByProfileId2(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
                + "left join fetch notificationMessages.messageType messageType "
                + "where notificationMessages.status=:status "
                + "and notificationMessages.patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.isDelete =:isDeleted "
//                + "and messageType.id.messageTypeId <> in  (41,57) "
                 + "and messageType.id.messageTypeId != 41 and messageType.id.messageTypeId != 57 "
                + "and notificationMessages.isArchive=:isArchived "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        query.setParameter("isArchived", Boolean.FALSE);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
            /**
             * Replica API Queries
     * @param profileId
             */
        public List<NotificationMessages> getNotificationMessagesByProfileId(Integer profileId) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
//                + "left join fetch notificationMessages.patientProfile patientProfile "
                + "left join fetch notificationMessages.messageType messageType "
                + "left join fetch notificationMessages.orders orders "
                + "join fetch orders.orderStatus orderStatus "
                + "join fetch orders.drugDetail2  drugDetail2 "
                 + "where notificationMessages.patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.status=:status and notificationMessages.isDelete =:isDeleted "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        query.setReadOnly(true);
//        query.setParameter("status", Constants.SUCCESS);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
                public List<NotificationMessages> getNotificationMessagesByProfileIdforPagination(Integer profileId, int skip, int limit) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessages "
//                + "left join fetch notificationMessages.patientProfile patientProfile "
                + "left join fetch notificationMessages.messageType messageType "
                + "left join fetch notificationMessages.orders orders "
                + "join fetch orders.orderStatus orderStatus "
                + "join fetch orders.drugDetail2  drugDetail2 "
                + "where notificationMessages.patientProfile.patientProfileSeqNo=:id "
                + "and notificationMessages.status=:status and notificationMessages.isDelete =:isDeleted "
                + "order by notificationMessages.createdOn desc");
        query.setParameter("id", profileId);
        query.setParameter("status", Constants.SUCCESS);
        query.setReadOnly(true);
        query.setFirstResult(skip);
        query.setMaxResults(limit);
//        query.setParameter("status", Constants.SUCCESS);
        String no = "No";
        query.setParameter("isDeleted", no);
             return query.list();
        }
       public Practices getRegisterdPracticeNames(int practiseId) throws Exception {
       String hql ="FROM Practices practices "
                + "WHERE practices.id =:practiseId "
                + "AND practices.practicetype=:Physician "
                + "AND practices.addfromPharmacy is null ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("practiseId", practiseId);
        query.setParameter("Physician", "Physician");
        return (Practices) query.uniqueResult();
    }
       public List<Practices> getRegisterdPractice() throws Exception {
       String hql = "FROM Practices practices "
                + "WHERE practices.practicetype=:Physician "
                + "AND practices.addfromPharmacy is null ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("Physician", "Physician");
        return query.list();
    }
       public Practices getRegisterdPracticeById(int id) throws Exception {
       String hql = "FROM Practices practices "
                + "WHERE practices.practicetype=:Physician " 
                + "AND practices.addfromPharmacy is null AND practices.id=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("Physician", "Physician");
        query.setParameter("id", id);
        return (Practices) query.uniqueResult();
    }
       public ReporterProfile getPhysicianProfileDetialById(int id) throws Exception {
         String hql = "From ReporterProfile reporterProfile where reporterProfile.physicianId=:prescriberId";
         Query query = getCurrentSession().createQuery(hql);
         query.setParameter("prescriberId", id);
         query.setMaxResults(1);
          return (ReporterProfile) query.uniqueResult();
       } 
       public VegaWalletRewardPoint getVegaWalletRewardPoint(int patientId) {
          String hql = "From VegaWalletRewardPoint vegaWalletRewardPoint WHERE vegaWalletRewardPoint.patientId=:patientId";
                Query query = getCurrentSession().createQuery(hql);
         query.setParameter("patientId", patientId);
          return (VegaWalletRewardPoint) query.uniqueResult();
       }
       public List<Order> getOrdersDetialForRenewal(Date newDate) {
         String hql = "from Order ord "
                + "where ord.nextRefillFlag=:nextRefillFlag "
//                + "and ord.nextRefillDate <=:todayDate "
                +"and ord.lastReminderDate is null "
                + "and ord.orderStatus.name =:orderStatus AND ord.viewStatus !=:viewStatus "
                 + "AND ord.refillsRemaining =:Zeroo ";
         Query query = getCurrentSession().createQuery(hql);
        query.setParameter("nextRefillFlag", "0");
        query.setParameter("orderStatus", Constants.ORDER_STATUS.FILLED);
        query.setParameter("viewStatus", new String[] {"HcpReq_Created","Rx_Renewal"});
         query.setParameter("Zeroo", 0);
         return query.list();
       }
       public List<SurveyResponseAnswerDetial> getSurveyResponseAnswer(long responseId) throws Exception {
           String hql = "from SurveyResponseAnswerDetial srda where srda.surveyResponseId=:responseId ";
             Query query = getCurrentSession().createQuery(hql);
             query.setParameter("responseId",responseId);
             return query.list(); 
       }
           public SurveyResponse getSurveyResponseById(long surveyResponseId) throws Exception {
        String hql = "From SurveyResponse survey WHERE survey.id =:surveyResponseId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyResponseId", surveyResponseId);
        return (SurveyResponse) query.uniqueResult();
    }
        public SurveyResponse getSurveyResponseByQuestionId(long surveyLogId, long questionId) throws Exception {
        String hql = "From SurveyResponse survey WHERE survey.assignSurvey.id =:surveyLogId AND survey.surveyQuestion.id=:questionId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("surveyLogId", surveyLogId);
          query.setParameter("questionId", questionId);
          query.setMaxResults(1);
        return (SurveyResponse) query.uniqueResult();
    }
         public RxRenewal getRxRenewalByOrderId(String orderId) throws Exception {
           String hql = "From RxRenewal rxRenewal where rxRenewal.ordrId=:orderId and rxRenewal.viewStatus !=:status";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("orderId", orderId);
           query.setParameter("status", "RxRenewal_Canceled");
           return (RxRenewal) query.uniqueResult();
        }
                public AssignedSurvey getduplicateAssignId(int patientId, long surveyId) throws Exception {
          String hql = "from AssignedSurvey assignedSurvey "
                  + "where assignedSurvey.patientProfile.patientProfileSeqNo =:patientId "
                  + "AND assignedSurvey.survey.id =:surveyId "
                  + "AND assignedSurvey.status =:status order by assignedSurvey.id desc";
          Query query = getCurrentSession().createQuery(hql);
           query.setParameter("patientId", patientId);
           query.setParameter("surveyId", surveyId);
           query.setParameter("status", Constants.DUPLICATE);
           query.setMaxResults(1);
           return (AssignedSurvey) query.uniqueResult();        
       }
         public RxRenewal getRxRenewalRequestByOrderId(String orderId) throws Exception {
           String hql = "From RxRenewal rxRenewal where rxRenewal.ordrId=:orderId and rxRenewal.viewStatus=:status";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("orderId", orderId);
           query.setParameter("status", "HcpReq_Created");
           return (RxRenewal) query.uniqueResult();
        }
          public List<RxRenewal> getRxRenewalRequestByOrderIdd(String orderId) throws Exception {
           String hql = "From RxRenewal rxRenewal where rxRenewal.ordrId=:orderId";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("orderId", orderId);
           return query.list();
        }
          public DrugDetail2 fetchLowCostDrug(String gcnSeq,String rxLabelName,String sponsoredProduct) throws Exception {
              String hql = "from DrugDetail2 drugDetail2 "
                      + "where drugDetail2.gcnSeq=:gcnSeq "
                      + "and drugDetail2.rxLabelName =:rxLabelName "
                      + "and drugDetail2.sponsoredProduct =:sponsoredProduct order by drugDetail2.unitPrice asc ";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("gcnSeq", gcnSeq);
           query.setParameter("rxLabelName", rxLabelName);
           query.setMaxResults(1);
           query.setParameter("sponsoredProduct", sponsoredProduct);
           return (DrugDetail2) query.uniqueResult();
          }
        public DrugDetail2 fetchLowCostDrug(String gcnSeq,String rxLabelName,String sponsoredProduct, int drugId) throws Exception {
              String hql = "from DrugDetail2 drugDetail2 "
                      + "where drugDetail2.gcnSeq=:gcnSeq "
                      + "and drugDetail2.rxLabelName =:rxLabelName and drugDetail2.drugId =:drugId "
                      + "and drugDetail2.sponsoredProduct =:sponsoredProduct";
           Query query = getCurrentSession().createQuery(hql);
           query.setParameter("gcnSeq", gcnSeq);
           query.setParameter("drugId", drugId);
           query.setParameter("rxLabelName", rxLabelName);
           query.setMaxResults(1);
           query.setParameter("sponsoredProduct", sponsoredProduct);
           return (DrugDetail2) query.uniqueResult();
          }
        public List<Practices> getPhysicianListByPracticeId(int practiceId) {
            String hql = "from Practices practices left join fetch practices. ";
            Query query = getCurrentSession().createQuery(hql);
            query.setParameter("practiceId", practiceId);
            return query.list();
        }
    public List<Object[]> getPhysicianList(int practiceId)  {
        try {
            String sql = "SELECT p.`id`,p.`practice_name`,p.practice_logo,u.`name`,rpd.`profile_image`,rpd.`lang_1`,rpd.`lang_2`,rpd.`lang_3`,rpd.`major_ss_1`,rpd.`major_ss_2`,rpd.`sub_ss_1`,rpd.`sub_ss_2`,u.`id`as physicianId "
                    + "FROM `practices` p "
                    + "INNER JOIN `practice_user` pu ON p.id = pu.`practice_id` "
                    + "INNER JOIN  `users` u ON pu.`user_id`= u.`id` "
                    + "INNER JOIN  `model_has_roles` mhr ON u.`id`= mhr.`model_id` "
                    + "LEFT JOIN `reporter_profile_detail` rpd ON u.`id`= rpd.reporter_id "
                    + "WHERE p.id =:practiceId "
                    + "AND mhr.`role_id` = 6 ";
            Query query = getCurrentSession().createSQLQuery(sql);
            query.setParameter("practiceId", practiceId);
            List<Object[]> lists = query.list();
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object getPhysicianNameById(int physicianId) throws Exception {
    String sql ="SELECT users.`name` FROM users WHERE id =:physicianId";
    Query  query = getCurrentSession().createSQLQuery(sql);
    query.setParameter("physicianId", physicianId);
    return query.uniqueResult();
    }
}
