package com.ssa.cms.validator;

import com.ssa.cms.model.SurveyResponseDetail;
import com.ssa.cms.model.SurveyResponseType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class SurveyResponseValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return SurveyResponseType.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SurveyResponseType surveyResponseType = (SurveyResponseType) o;
        if (surveyResponseType.getSelectedId() != null && surveyResponseType.getSelectedId() == 0) {
            errors.rejectValue("selectedId", "error.field.required");
        }
        Integer counter = 0;
        List<SurveyResponseDetail> surveyResponseDetailList = new ArrayList<SurveyResponseDetail>();
        for (SurveyResponseDetail surveyResponseDetail : surveyResponseType.getSurveyResponseDetails()) {
            if (surveyResponseDetail.getRemove() == 0) {
                if (surveyResponseDetail.getTitle().trim().isEmpty()) {
                    errors.rejectValue("surveyResponseDetails[" + counter + "].title", "error.field.required");
                }
                surveyResponseDetailList.add(surveyResponseDetail);
            }
            counter++;
        }
        surveyResponseType.setSurveyResponseDetails(null);
        surveyResponseType.setSurveyResponseDetails(surveyResponseDetailList);
    }
}
