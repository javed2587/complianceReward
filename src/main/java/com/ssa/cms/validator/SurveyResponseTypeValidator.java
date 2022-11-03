package com.ssa.cms.validator;

import com.ssa.cms.model.SurveyResponseType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class SurveyResponseTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return SurveyResponseType.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SurveyResponseType surveyResponseType = (SurveyResponseType) o;
        if (surveyResponseType.getTitle() != null && "".equals(surveyResponseType.getTitle().trim())) {
            errors.rejectValue("title", "error.field.required");
        }
    }

}
