package com.ssa.cms.service;

import com.ssa.cms.dao.SurveyDAO;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.Survey;
import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.model.SurveyQuestionAssociation;
import com.ssa.cms.model.SurveyResponseDetail;
import com.ssa.cms.model.SurveyResponseType;
import com.ssa.cms.model.SurveyToSurveyTypeAssociation;
import com.ssa.cms.model.SurveyType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Service
@Transactional
public class SurveyService {

    @Autowired
    private SurveyDAO surveyDAO;
    private static final Log logger = LogFactory.getLog(SetupService.class.getName());

    public boolean saveSurveyResponseType(SurveyResponseType responseType, Integer currentUserId) {
        boolean save = false;
        try {
            if (responseType.getId() == null) {
                responseType.setCreatedBy(currentUserId);
                responseType.setCreatedOn(new Date());
            }
            responseType.setLastModifiedBy(currentUserId);
            responseType.setLastModifiedOn(new Date());
            responseType.setStatus("Active");
            surveyDAO.saveOrUpdate(responseType);
            save = true;
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> saveResponseType", e);
        }
        return save;
    }

    public List<SurveyResponseType> getSurveyResponseTypeList() {
        List<SurveyResponseType> list = new ArrayList<>();
        try {
            list = surveyDAO.getResponseTypes();
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getResponseTypeList", e);
        }
        return list;
    }

    public SurveyResponseType getSurveyResponseTypeById(Integer id) {
        SurveyResponseType surveyResponseType = new SurveyResponseType();
        try {
            surveyResponseType = surveyDAO.getSurveyResponseType(id);
            surveyResponseType.setAssociated(Boolean.FALSE);
            if (surveyResponseType.getSurveyQuestionList().size() > 0) {
                surveyResponseType.setAssociated(Boolean.TRUE);
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getResponseTypeById", e);
        }
        return surveyResponseType;
    }

    public boolean saveSurveyResponse(SurveyResponseType surveyResponseType, Integer currentUserId) {
        boolean saved = false;
        try {
            List<Integer> responseIdList = new ArrayList<>();
            List<SurveyResponseDetail> surveyResponseDetails = new ArrayList<>();
            if (surveyResponseType.getSelectedId() != null && surveyResponseType.getSelectedId() > 0) {
                for (SurveyResponseDetail surveyResponseDetail : surveyResponseType.getSurveyResponseDetails()) {
                    if (surveyResponseDetail.getRemove() == 0) {
                        if (surveyResponseDetail.getId() == null) {
                            surveyResponseDetail.setCreatedBy(currentUserId);
                            surveyResponseDetail.setCreatedOn(new Date());
                        } else {
                            responseIdList.add(surveyResponseDetail.getId());
                        }
                        surveyResponseDetail.setStatus("Active");
                        surveyResponseDetail.setLastModifiedBy(currentUserId);
                        surveyResponseDetail.setLastModifiedOn(new Date());
                        surveyResponseDetail.setSurveyResponseType(new SurveyResponseType(surveyResponseType.getSelectedId()));
                        surveyResponseDetails.add(surveyResponseDetail);
                    }
                }
                if (!responseIdList.isEmpty() && surveyResponseType.getSelectedId() != null && surveyResponseType.getSelectedId() > 0) {
                    // Remove user deleted entries
                    surveyDAO.deleteResponse(responseIdList, surveyResponseType.getSelectedId());
                }
                // Save/Update
                surveyDAO.batchUpdateSurveyResponseDetail(surveyResponseDetails);
                saved = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> saveSurveyResponse", e);
        }
        return saved;
    }

    public SurveyResponseType getSurveyResponseDetail(Integer id) {
        SurveyResponseType surveyResponseType = new SurveyResponseType();
        try {
            surveyResponseType = surveyDAO.getSurveyResponse(id);
            SurveyResponseType surveyResponseTypeWithQuestions = surveyDAO.getSurveyResponseType(id);
            surveyResponseType.setSelectedId(id);
            surveyResponseType.setAssociated(Boolean.FALSE);
            if (surveyResponseTypeWithQuestions.getSurveyQuestionList().size() > 0) {
                surveyResponseType.setAssociated(Boolean.TRUE);
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyResponseDetail", e);
        }
        return surveyResponseType;
    }

    public List<SurveyResponseType> getSurveyResponseList() {
        List<SurveyResponseType> surveyResponseTypeList = new ArrayList<>();
        try {
            surveyResponseTypeList = surveyDAO.getSurveyResponseDetails();
//            for (SurveyResponseType surveyResponseType : surveyResponseTypeList) {
//                surveyResponseType.setAssociated(Boolean.FALSE);
//                for (SurveyQuestion surveyQuestion : surveyResponseType.getSurveyQuestionList()) {
//                    if (surveyQuestion.getSurveyQuestionAssociationList().size() > 0) {
//                        surveyResponseType.setAssociated(Boolean.TRUE);
//                        break;
//                    }
//                }
//            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyResponseList", e);
        }
        return surveyResponseTypeList;
    }

    public boolean saveSurveyQuestion(SurveyQuestion surveyQuestion, Integer currentUserId) {
        boolean save = false;
        try {
            if (surveyQuestion.getId() == null) {
                surveyQuestion.setCreatedBy(currentUserId);
                surveyQuestion.setCreatedOn(new Date());
            }
            surveyQuestion.setLastModifiedBy(currentUserId);
            surveyQuestion.setLastModifiedOn(new Date());
            surveyQuestion.setStatus("Active");
            surveyDAO.saveOrUpdate(surveyQuestion);
            save = true;
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> saveSurveyQuestion", e);
        }
        return save;
    }

    public List<SurveyQuestion> getSurveyQuestionList() {
        List<SurveyQuestion> list = new ArrayList<>();
        try {
            list = surveyDAO.getAllSurveyQuestions();
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyQuestionList", e);
        }
        return list;
    }

    public SurveyQuestion getSurveyQuestionById(Integer id) {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        try {
            surveyQuestion = (SurveyQuestion) surveyDAO.getSurveyQuestionById(id);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyQuestionTypeById", e);
        }
        return surveyQuestion;
    }

    public Boolean checkDuplicateSurveyQuestion(String questionTitle, Integer responseTypeId) {
        Boolean duplicateFlag = Boolean.FALSE;
        try {
            duplicateFlag = surveyDAO.checkDuplicateSurveyQuestion(questionTitle, responseTypeId);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> checkDuplicateSurveyQuestion", e);
        }
        return duplicateFlag;
    }

    public List<Survey> getSurveyList() {
        List<Survey> surveyList = new ArrayList<>();
        try {
            List<Survey> list = surveyDAO.getAllSurveys();
            for (Survey survey : list) {
                survey.setNoOfQuestions(surveyDAO.getNoofQuestionsBySurveyId(survey.getId()));
                StringBuilder result = new StringBuilder();
                for (SurveyToSurveyTypeAssociation surveyToSurveyTypeAssociation : survey.getSurveyToSurveyTypeAssociation()) {
                    result.append(surveyToSurveyTypeAssociation.getSurveyType().getType());
                    result.append(",");
                }
                String ids = result.length() > 0 ? result.substring(0, result.length() - 1) : "";
                survey.setCommaSeparatedSurveyTypes(ids);
                surveyList.add(survey);
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyList", e);
        }
        return surveyList;
    }

    public boolean checkSurveyResponseTypeTitle(SurveyResponseType surveyResponseType) {
        boolean checkDuplicate = false;
        try {
            checkDuplicate = surveyDAO.getSurveyResponseTypeByTitle(surveyResponseType.getTitle(), surveyResponseType.getId());
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> checkSurveyResponseTypeTitle", e);
        }
        return checkDuplicate;
    }

    public boolean checkDuplicateSurveyResponseDetail(SurveyResponseType surveyResponseType) {
        boolean checkDuplicate = false;
        try {
            List<String> surveyResponseDetailTitleList = new ArrayList<>();
            for (SurveyResponseDetail surveyResponseDetail : surveyResponseType.getSurveyResponseDetails()) {
                if (surveyResponseDetail.getId() == null) {
                    surveyResponseDetailTitleList.add(surveyResponseDetail.getTitle());
                }
            }
            if (surveyResponseDetailTitleList.size() > 0) {
                Integer id = surveyResponseType.getId();
                if (id == null) {
                    id = surveyResponseType.getSelectedId();
                }
                checkDuplicate = surveyDAO.checkDuplicateSurveyResponses(id, surveyResponseDetailTitleList);
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> checkSurveyResponseDetail", e);
        }
        return checkDuplicate;
    }

    public List<SurveyType> getSurveyTypeList() {
        List<SurveyType> list = new ArrayList<>();
        try {
            list = surveyDAO.getAllRecords(new SurveyType());
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyTypeList", e);
        }
        return list;
    }

    public List<ShortCodes> getShortCodeList() {
        List<ShortCodes> list = new ArrayList<>();
        try {
            list = surveyDAO.getAllRecords(new ShortCodes());
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getShortCodeList", e);
        }
        return list;
    }

    public boolean saveSurvey(Survey survey, Integer currentUserId) {
        boolean save = false;
        try {
            if (survey.getId() == null) {
                survey.setCreatedBy(currentUserId);
                survey.setCreatedOn(new Date());
            } else {
                surveyDAO.deleteSurveyQuestionAssociation(survey.getId());
            }
            survey.setUpdatedBy(currentUserId);
            survey.setUpdatedOn(new Date());
            if (!"Active".equals(survey.getStatus())) {
                survey.setStatus("InActive");
            }
            survey.setSurveyToSurveyTypeAssociation(null);
            if (survey.getSurveyQuestionList().size() > 0) {
                List<SurveyQuestionAssociation> questionAssociations = new ArrayList<>();
                for (SurveyQuestionAssociation surveyQuestion : survey.getSurveyQuestionList()) {
                    if (surveyQuestion.getQuestionAssociate() && surveyQuestion.getSurveyQuestion().getId() != null) {
                        SurveyQuestionAssociation newSurveyQuestionAssociation = new SurveyQuestionAssociation();
                        newSurveyQuestionAssociation.setSurvey(survey);
                        newSurveyQuestionAssociation.setSurveyQuestion(new SurveyQuestion(surveyQuestion.getSurveyQuestion().getId()));
                        newSurveyQuestionAssociation.setCreatedBy(currentUserId);
                        newSurveyQuestionAssociation.setCreatedOn(new Date());
                        newSurveyQuestionAssociation.setLastModifiedBy(currentUserId);
                        newSurveyQuestionAssociation.setLastModifiedOn(new Date());
                        questionAssociations.add(newSurveyQuestionAssociation);
                    }
                }
                survey.setSurveyQuestionList(questionAssociations);
            }
            surveyDAO.saveOrUpdate(survey);
            save = true;
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> saveSurvey", e);
        }
        return save;
    }

    public boolean checkDuplicateEmailTemplateTitle(String title, Integer id) {
        boolean duplicate = false;
        try {
            duplicate = surveyDAO.checkDuplicateEmailTemplateTitle(title, id);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> bcheckDuplicateEmailTemplateTitle", e);
        }
        return duplicate;
    }

    public Survey getSurveyById(Integer id) {
        Survey survey = new Survey();
        try {
            survey = surveyDAO.getSurveyById(id);
            if (survey.getCampaignses() != null && survey.getCampaignses().size() > 0) {
                survey.setAssociated(Boolean.TRUE);
            } else {
                survey.setAssociated(Boolean.FALSE);
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyById", e);
        }
        return survey;
    }

    public List<SmtpServerInfo> getAllSmtpServers() {
        List<SmtpServerInfo> smtpServerList = new ArrayList<>();
        try {
            smtpServerList = surveyDAO.getAllRecords(new SmtpServerInfo());
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getAllSmtpServers", e);
        }
        return smtpServerList;
    }

    public Set<SurveyQuestionAssociation> getSurveyQuestionList(Integer surveyId) {
        Set<SurveyQuestionAssociation> surveyQuestionList = new LinkedHashSet<>();
        try {
            surveyQuestionList = new LinkedHashSet<>(surveyDAO.getSurveyQuestionList(surveyId));
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyQuestionList", e);
        }
        return surveyQuestionList;
    }

    public Survey getSurveyWithAssociatedQuestions(Integer surveyId) {
        Survey survey = new Survey();
        try {
            survey = surveyDAO.getSurveyWithAssociatedQuestions(surveyId);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyWithAssociatedQuestions", e);
        }
        return survey;
    }

    public Survey getSurveyUserResponseList(Integer surveyId, String communicationId) {
        Survey survey = new Survey();
        try {
            survey = surveyDAO.getSurveyUserResponseList(surveyId, communicationId);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getSurveyUserResponseList", e);
        }
        return survey;
    }

    public boolean addSurveyQuestions(Survey survey, Integer currentUserId) {
        boolean save = false;
        try {
            surveyDAO.addSurveyQuestions(survey, currentUserId);
            save = true;
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> addSurveyQuestions", e);
        }
        return save;
    }

    public void saveSurveyUserResponse(Survey survey) {
        try {
            surveyDAO.saveSurveyUserResponse(survey);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> saveSurveyUserResponse", e);
        }
    }

    public boolean deleteSurveyQuestion(Integer id) {
        boolean deleted = false;
        try {
            deleted = surveyDAO.deleteSurveyQuestion(id);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> deleteSurveyQuestion", e);
        }
        return deleted;
    }

    public boolean isSurveyQuestionAssociated(Integer id) {
        boolean recordAssociatedFlag = false;
        try {
            SurveyQuestion surveyQuestion = surveyDAO.getSurveyQuestionById(id);
            if (surveyQuestion.getSurveyQuestionAssociationList().size() > 0) {
                recordAssociatedFlag = true;
            }
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> isSurveyQuestionAssociated", e);
        }
        return recordAssociatedFlag;
    }

    public boolean deleteSurveyEmailTemplate(Integer id) {
        boolean deleted = false;
        try {
            deleted = surveyDAO.deleteSurveyEmailTemplate(id);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> deleteSurveyEmailTemplate", e);
        }
        return deleted;
    }

    public String getResponsesByResponseType(Integer responseTypeId) {
        List<SurveyResponseDetail> surveyResponseDetailList = new ArrayList<>();
        String json = "";
        try {
            SurveyResponseType surveyResponseType = surveyDAO.getSurveyResponse(responseTypeId);
            for (SurveyResponseDetail surveyResponseDetail : surveyResponseType.getSurveyResponseDetails()) {
                SurveyResponseDetail newSurveyResponseDetail = new SurveyResponseDetail();
                newSurveyResponseDetail.setId(surveyResponseDetail.getId());
                newSurveyResponseDetail.setTitle(surveyResponseDetail.getTitle());
                surveyResponseDetailList.add(newSurveyResponseDetail);
            }
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(surveyResponseDetailList);

        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getResponsesByResponseType", e);
        }
        return json;
    }

    public void deleteSurveyResponseType(Integer responseTypeId) {
        try {
            SurveyResponseType surveyResponseType = (SurveyResponseType) surveyDAO.getRecord(new SurveyResponseType(), responseTypeId);
            surveyDAO.deleteSurveyResponseType(surveyResponseType);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> deleteSurveyResponseType", e);
        }
    }

    public void deleteSurveyResponse(Integer responseTypeId) {
        try {
            surveyDAO.deleteSurveyResponse(responseTypeId);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> deleteSurveyResponse", e);
        }
    }

    public void deleteSurvey(Integer responseId) {
        try {
            Survey survey = (Survey) surveyDAO.getRecord(new Survey(), responseId);
            surveyDAO.deleteSurvey(survey);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> deleteSurvey", e);
        }
    }

    public boolean checkDuplicateSurvey(Survey survey) {
        boolean checkDuplicate = false;
        try {
            checkDuplicate = surveyDAO.getSurveyByTitle(survey);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> checkDuplicateSurvey", e);
        }
        return checkDuplicate;
    }

    public boolean isResponseAssociated(Integer id) {
        boolean result = false;
        try {
            result = surveyDAO.deleteSurveyResponseDetail(id);
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> isResponseAssociated", e);
        }
        return result;
    }

    public List<Campaigns> getAllSurveyCandidateActiveCampaign() {
        List<Campaigns> list = null;
        try {
            list = surveyDAO.getAllSurveyCandidateActiveCampaign();
        } catch (Exception e) {
            logger.error("Exception: SurveyService -> getAllRefillCandidateActiveCampaign", e);
        }
        return list;
    }

    public List<DailyRedemption> getDRFSurveyReminderRecordsByCampaignId(int campaignId, int interval) {
        List<DailyRedemption> list = new ArrayList<>();
        list = surveyDAO.getDRFSurveyReminderRecordsByCampaignId(campaignId, interval);
        return list;
    }
}
