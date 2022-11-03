package com.ssa.cms.validation;

import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author msheraz
 */
@Component
public class SurveyQuestionValidator implements Validator {
    
    @Autowired
    private SurveyService surveyService;

    @Override
    public boolean supports(Class<?> type) {
        return SurveyQuestion.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SurveyQuestion surveyQuestion = (SurveyQuestion) o;
        if(surveyQuestion.getSurveyResponseType() != null && surveyQuestion.getSurveyResponseType().getId() == 0){
            errors.rejectValue("surveyResponseType.id", "error.field.required");
        }
        if(surveyQuestion.getTitle() == null || surveyQuestion.getTitle().trim().equals("")){
            errors.rejectValue("title", "error.field.required");
        } else {
            Boolean duplicate = surveyService.checkDuplicateSurveyQuestion(surveyQuestion.getTitle(), surveyQuestion.getSurveyResponseType().getId());
            if(duplicate){
                if(surveyQuestion.getId() == null || surveyQuestion.getId() == 0){
                    errors.rejectValue("title", "field.surveyQuestionTitle.duplicate");
                }
            }
        }
    }
}
