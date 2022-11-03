package com.ssa.cms.dao;

import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.Survey;
import com.ssa.cms.model.SurveyResponseDetail;
import com.ssa.cms.model.SurveyResponseType;
import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.model.SurveyQuestionAssociation;
import com.ssa.cms.model.SurveyUserResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Repository
@Transactional
public class SurveyDAO extends BaseDAO implements Serializable {

    
    private static final Log logger = LogFactory.getLog(SurveyDAO.class.getName());

    public void save(Object bean) throws Exception {
        this.getCurrentSession().save(bean);
    }

    public List getAllRecords(Object object) throws Exception {
        return getCurrentSession().createQuery("from " + object.getClass().getName()).list();
    }

    public void merge(Object bean) throws Exception {
        this.getCurrentSession().merge(bean);
    }

    public Object getRecord(Object type, Integer id) throws Exception {
        Criteria searchCriteria = getCurrentSession().createCriteria(type.getClass());
        Object result = searchCriteria.add(Restrictions.eq("id", id)).uniqueResult();
        getCurrentSession().evict(result);
        return result;
    }

    public void saveSurveyResponse(List<SurveyResponseDetail> surveyResponseDetails, Integer currentUserId, Integer surveyResponseId) throws Exception {
        Integer counter = 0;
        for (SurveyResponseDetail responseDetail : surveyResponseDetails) {
            if (responseDetail.getId() == null) {
                responseDetail.setCreatedBy(currentUserId);
                responseDetail.setCreatedOn(new Date());
            }
            SurveyResponseType surveyResponseType = new SurveyResponseType(surveyResponseId);

            responseDetail.getSurveyResponseType().setId(surveyResponseType.getId());
            responseDetail.setStatus("Active");
            responseDetail.setLastModifiedBy(currentUserId);
            responseDetail.setLastModifiedOn(new Date());
            getCurrentSession().saveOrUpdate(responseDetail);

        }
    }

    public SurveyResponseType getSurveyResponse(Integer id) throws Exception {
        SurveyResponseType surveyResponseDetail = new SurveyResponseType();
        Query query = getCurrentSession().createQuery("from SurveyResponseType surveyResponseType left join fetch surveyResponseType.surveyResponseDetails responseDetails "
                + "where surveyResponseType.id=:id");
        query.setParameter("id", id);
        Object object = query.uniqueResult();
        if (object != null) {
            surveyResponseDetail = (SurveyResponseType) object;
        }
        return surveyResponseDetail;
    }

    public SurveyResponseType getSurveyResponseType(Integer id) throws Exception {
        SurveyResponseType surveyResponseType = new SurveyResponseType();
        Query query = getCurrentSession().createQuery("from SurveyResponseType surveyResponseType left join fetch surveyResponseType.surveyQuestionList surveyQuestionList "
                + "where surveyResponseType.id=:id");
        query.setParameter("id", id);
        Object object = query.uniqueResult();
        if (object != null) {
            surveyResponseType = (SurveyResponseType) object;
        }
        return surveyResponseType;
    }

    public List<SurveyResponseType> getSurveyResponseDetails() throws Exception {
        return getCurrentSession().createQuery("select distinct surveyResponseType from SurveyResponseType surveyResponseType inner join fetch surveyResponseType.surveyResponseDetails").list();
    }

    public List getAllSurveyQuestions() throws Exception {
        return getCurrentSession().createQuery("from SurveyQuestion surveyQuestion inner join fetch surveyQuestion.surveyResponseType").list();
    }

    public SurveyQuestion getSurveyQuestionById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("from SurveyQuestion surveyQuestion left join fetch surveyQuestion.surveyResponseType where surveyQuestion.id =:id");
        query.setParameter("id", id);
        return (SurveyQuestion) query.uniqueResult();
    }

    public boolean getSurveyResponseTypeByTitle(String title, Integer id) throws Exception {
        boolean found = false;
        String hql = "From SurveyResponseType surveyResponseType where surveyResponseType.title= '" + title + "'";
        if (id != null && id != 0) {
            hql += " and surveyResponseType.id != " + id;
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.uniqueResult() != null) {
            found = true;
        }
        return found;
    }

    public Boolean checkDuplicateSurveyQuestion(String questionTitle, Integer responseTypeId) throws Exception {
        Query query = getCurrentSession().createQuery("from SurveyQuestion surveyQuestion left join fetch surveyQuestion.surveyResponseType responseType where surveyQuestion.title =:questionTitle and responseType.id=:responseTypeId");
        query.setParameter("questionTitle", questionTitle);
        query.setParameter("responseTypeId", responseTypeId);
        Object result = query.uniqueResult();
        if (result != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List getAllSurveys() throws Exception {
        return getCurrentSession().createQuery("select distinct survey from Survey survey left join fetch survey.surveyToSurveyTypeAssociation ").list();
    }

    public boolean checkDuplicateEmailTemplateTitle(String title, Integer id) throws Exception {
        String hql = "From SurveyEmailTemplate surveyEmailTemplate where surveyEmailTemplate.title = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and surveyEmailTemplate.id != " + id;
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public SurveyResponseType getSurveyResponseTypeById(Integer id) {
        Query query = getCurrentSession().createQuery("from SurveyResponseType surveyResponseType left join fetch surveyResponseType.surveyQuestionList where surveyResponseType.id=:id");
        query.setParameter("id", id);
        SurveyResponseType surveyResponseType = (SurveyResponseType) query.uniqueResult();
        return surveyResponseType;
    }

    public void deleteResponse(List<Integer> responseIdList, Integer responseTypeId) {
        Query query = getCurrentSession().createQuery("delete from SurveyResponseDetail surveyResponseDetail where surveyResponseDetail.surveyResponseType.id=:responseTypeId and surveyResponseDetail.id NOT IN (:responseIdList)");
        query.setParameter("responseTypeId", responseTypeId);
        query.setParameterList("responseIdList", responseIdList);
        query.executeUpdate();
    }

    public void batchUpdateSurveyResponseDetail(List<SurveyResponseDetail> surveyResponseDetailList) {
        Integer counter = 0;
        for (SurveyResponseDetail surveyResponseDetail : surveyResponseDetailList) {
            getCurrentSession().saveOrUpdate(surveyResponseDetail);
            counter++;
            if (counter % 100 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    public Boolean checkDuplicateSurveyResponses(Integer surveyResponseTypeId, List<String> surveyResponseTitleList) {
        Query query = getCurrentSession().createQuery("from SurveyResponseType surveyResponseType inner join fetch surveyResponseType.surveyResponseDetails surveyResponseDetail where surveyResponseType.id=:id and surveyResponseDetail.title in (:surveyResponseTitleList)");
        query.setParameter("id", surveyResponseTypeId);
        query.setParameterList("surveyResponseTitleList", surveyResponseTitleList);
        if (query.list().size() > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Long getNoofQuestionsBySurveyId(Integer surveyId) {
        return (Long) getCurrentSession().createQuery("select count(*) from SurveyQuestionAssociation surveyQuestionAssociation where surveyQuestionAssociation.survey.id =:surveyId").setParameter("surveyId", surveyId).uniqueResult();
    }

    public Survey getSurveyById(Integer id) {
        Query query = getCurrentSession().createQuery("from Survey survey left join fetch survey.surveyQuestionList surveyQuestionList left join fetch surveyQuestionList.surveyQuestion where survey.id=:id");
        query.setParameter("id", id);
        Survey survey = (Survey) query.uniqueResult();
        return survey;
    }

    public void deleteSurveyToSurveyTypeAssociation(Integer surveyId) {
        Query query = getCurrentSession().createQuery("delete from SurveyToSurveyTypeAssociation surveyToSurveyTypeAssociation where surveyToSurveyTypeAssociation.survey.id=:surveyId");
        query.setParameter("surveyId", surveyId);
        query.executeUpdate();
    }

    public List<SurveyQuestionAssociation> getSurveyQuestionList(Integer surveyId) {
        Query query = getCurrentSession().createQuery("select distinct surveyQuestionAssociation from SurveyQuestionAssociation surveyQuestionAssociation left join fetch surveyQuestionAssociation.surveyQuestion surveyQuestion left join fetch surveyQuestion.surveyResponseType surveyResponseType left join fetch surveyResponseType.surveyResponseDetails where surveyQuestionAssociation.survey.id=:surveyId");
        query.setParameter("surveyId", surveyId);
        return query.list();
    }

    public Survey getSurveyWithAssociatedQuestions(Integer surveyId) {
        Query query = getCurrentSession().createQuery("from Survey survey left join fetch survey.surveyQuestionList surveyQuestionList left join fetch surveyQuestionList.surveyQuestion surveyQuestion left join fetch surveyQuestion.surveyResponseType where survey.id=:surveyId");
        query.setParameter("surveyId", surveyId);
        Survey survey = (Survey) query.uniqueResult();
        return survey;
    }

    public Survey getSurveyUserResponseList(Integer surveyId, String communicationId) {
        Query query = getCurrentSession().createQuery("from Survey survey left join fetch survey.surveyUserResponseList list where list.survey.id=:surveyId and list.userId=:userId");
        query.setParameter("surveyId", surveyId);
        query.setParameter("userId", communicationId);
        Survey survey = (Survey) query.uniqueResult();
        return survey;
    }

    public void addSurveyQuestions(Survey survey, Integer currentUserId) {
        Query deleteQuery = getCurrentSession().createQuery("delete from SurveyQuestionAssociation surveyQuestionAssociation where surveyQuestionAssociation.survey.id=:surveyId");
        deleteQuery.setParameter("surveyId", survey.getId());
        deleteQuery.executeUpdate();

        Integer counter = 0;
        if (survey.getSurveyQuestionList() != null) {
            for (SurveyQuestionAssociation surveyQuestionAssociation : survey.getSurveyQuestionList()) {
                if (surveyQuestionAssociation.getSurveyQuestion() != null && surveyQuestionAssociation.getSurveyQuestion().getId() != null) {
                    SurveyQuestionAssociation objectToSave = new SurveyQuestionAssociation();
                    objectToSave.setSurvey(new Survey(survey.getId()));
                    objectToSave.setSurveyQuestion(new SurveyQuestion(surveyQuestionAssociation.getSurveyQuestion().getId()));
                    objectToSave.setCreatedBy(currentUserId);
                    objectToSave.setCreatedOn(new Date());
                    objectToSave.setLastModifiedBy(currentUserId);
                    objectToSave.setLastModifiedOn(new Date());
                    getCurrentSession().saveOrUpdate(objectToSave);
                    counter++;
                    if (counter % 100 == 0) {
                        getCurrentSession().flush();
                        getCurrentSession().clear();
                    }
                }
            }
        }
    }

    public void saveSurveyUserResponse(Survey survey) {
        Integer counter = 0;
        for (SurveyQuestionAssociation surveyQuestionAssociation : survey.getSurveyQuestionList()) {
            if (surveyQuestionAssociation.getSurveyQuestion() != null && surveyQuestionAssociation.getSurveyQuestion().getId() != null && surveyQuestionAssociation.getSurveyQuestion().getSelectedResponse() != null) {
                SurveyUserResponse surveyUserResponse = new SurveyUserResponse();
                surveyUserResponse.setSurvey(new Survey(survey.getId()));
                surveyUserResponse.setUserId(survey.getCommunicationId());
                surveyUserResponse.setSurveyQuestion(new SurveyQuestion(surveyQuestionAssociation.getSurveyQuestion().getId()));
                surveyUserResponse.setSurveyResponseDetail(new SurveyResponseDetail(surveyQuestionAssociation.getSurveyQuestion().getSelectedResponse()));
                getCurrentSession().saveOrUpdate(surveyUserResponse);
                counter++;
                if (counter % 100 == 0) {
                    getCurrentSession().flush();
                    getCurrentSession().clear();
                }
            }
        }
    }

    public boolean deleteSurveyQuestion(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("Delete from SurveyQuestion surveyQuestion where surveyQuestion.id =:id");
        query.setParameter("id", id);
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean deleteSurveyEmailTemplate(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("Delete from SurveyEmailTemplate surveyEmailTemplate where surveyEmailTemplate.id =:id");
        query.setParameter("id", id);
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void deleteSurveyResponseType(SurveyResponseType surveyResponseType) {
        getCurrentSession().delete(surveyResponseType);
    }

    public void deleteSurveyResponse(Integer responseTypeId) {
        Query query = getCurrentSession().createQuery("delete from SurveyResponseDetail surveyResponseDetail where surveyResponseDetail.surveyResponseType.id=:responseTypeId");
        query.setParameter("responseTypeId", responseTypeId);
        query.executeUpdate();
    }

    public void deleteSurvey(Survey survey) {
        getCurrentSession().delete(survey);
    }

    public Boolean getSurveyByTitle(Survey survey) {
        boolean found = false;
        Query query = getCurrentSession().createQuery("from Survey survey where survey.title=:surveyTitle");
        query.setParameter("surveyTitle", survey.getTitle());
        Survey persistentSurvey = (Survey) query.uniqueResult();
        if (persistentSurvey != null && persistentSurvey.getId() != null) {
            if (survey.getId() == null) {
                found = true;
            } else if (!persistentSurvey.getId().equals(survey.getId())) {
                found = true;
            }
        }
        return found;
    }

    public boolean deleteSurveyResponseDetail(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("Delete from SurveyResponseDetail surveyResponseDetail where surveyResponseDetail.id=:id");
        query.setParameter("id", id);
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<SurveyResponseType> getResponseTypes() throws Exception {
        return getCurrentSession().createQuery("From SurveyResponseType surveyResponseType order by surveyResponseType.title asc").list();
    }

    public List<Campaigns> getAllSurveyCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            String hql
                    = "SELECT campaigns FROM Campaigns campaigns "
                    + "INNER JOIN FETCH campaigns.shortCodes shortCodes "
                    + "inner join fetch campaigns.smtpServerInfo "
                    + "inner join fetch campaigns.survey "
                    + "WHERE "
                    + "campaigns.campaignType IN('Production','Demo','Development') "
                    + "AND campaigns.isActive = 'Yes' "
                    + "AND campaigns.isDelete = 'No' "
                    + "AND Date(campaigns.lanchDateTime) <= CURDATE() "
                    + "AND Date(campaigns.terminationDateTime) >= CURDATE() "
                    + "ORDER BY campaigns.campaignId DESC ";

            list = (List<Campaigns>) this.getCurrentSession().createQuery(hql).list();

        } catch (Exception e) {
            logger.error("Exception: SurveyDAO -> getAllSurveyCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<DailyRedemption> getDRFSurveyReminderRecordsByCampaignId(int campaignId, int interval) {
        List<DailyRedemption> list = new ArrayList<>();
        try {

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
                    + "drive_drf.Prescription_Number "
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
                    + "drf.Prescription_Number AS Prescription_Number "
                    + "FROM DailyRedemption drf  "
                    + "WHERE "
                    + "drf.CampaignId = ? "
                    + "AND drf.Refills_Used = 0 "
                    + "GROUP BY  "
                    + "drf.Transaction_Number  "
                    + "HAVING COUNT(*) = 1  "
                    + ") drive_drf  "
                    + "WHERE  "
                    + "drive_drf.Claim_Status = 0  "
                    + "AND drive_drf.Communication_Id IS NOT NULL "
                    + "AND TIMESTAMPDIFF(DAY, drive_drf.Fill_Date, NOW()) >= ? "
                    + "AND TIMESTAMPDIFF(DAY, drive_drf.Fill_Date, NOW()) < (?+1)";

            logger.error("Reminder SQL : " + sql);
            logger.error("Reminder SQL : " + sql);

            SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
            query.setInteger(0, campaignId);
            query.setInteger(1, interval);
            query.setInteger(2, interval);

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
                    drf.setPrescriptionNumber(value.toString());
                }
                list.add(drf);
            } // for(Object[] result: queryResult)

        } catch (Exception e) {
            logger.error("Exception: SurveyDAO -> getDRFSurveyReminderRecordsByCampaignId", e);
        }
        return list;
    }

    public void deleteSurveyQuestionAssociation(Integer id) throws Exception {
        Query deleteQuery = getCurrentSession().createQuery("delete from SurveyQuestionAssociation surveyQuestionAssociation where surveyQuestionAssociation.survey.id=:surveyId");
        deleteQuery.setParameter("surveyId", id);
        deleteQuery.executeUpdate();
    }
}
