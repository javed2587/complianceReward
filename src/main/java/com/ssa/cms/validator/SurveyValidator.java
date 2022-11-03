package com.ssa.cms.validator;

import com.ssa.cms.model.Survey;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class SurveyValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Survey.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Survey survey = (Survey) o;
        if (survey.getTitle().trim().isEmpty()) {
            errors.rejectValue("title", "error.field.required");
        }
    }

}
