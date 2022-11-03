package com.ssa.cms.validator;

import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class IntervalValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return IntervalsType.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        IntervalsType intervalsType = (IntervalsType) o;
        Integer counter = 0;
        if (intervalsType.getIntervalsTypeTitle() != null && intervalsType.getIntervalsTypeTitle().trim().isEmpty()) {
            errors.rejectValue("intervalsTypeTitle", "error.field.required");
        }
        if (intervalsType.getUnitInSecond() == null) {
            errors.rejectValue("unitInSecond", "error.field.required");
        }
        for (Intervals intervals : intervalsType.getIntervals()) {
            if (intervals.getIntervalValue() == null) {
                errors.rejectValue("intervals[" + counter + "].intervalValue", "error.field.required");
            }
            counter++;
        }
    }

}
