package com.ssa.cms.validator;

import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBrand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class DrugBrandValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return DrugBrand.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DrugBrand drugBrand = (DrugBrand) o;
        Integer counter = 0;
        if (drugBrand.getDrugBrandName() != null && drugBrand.getDrugBrandName().trim().isEmpty()) {
            errors.rejectValue("genericName", "error.field.required");
        }
        for (Drug drug : drugBrand.getDrugs()) {
            if (drug.getDrugGpi().trim().isEmpty()) {
                errors.rejectValue("drugs[" + counter + "].drugGpi", "error.field.required");
            }
            if (drug.getStrength().trim().isEmpty()) {
                errors.rejectValue("drugs[" + counter + "].strength", "error.field.required");
            }
//            if (drug.getDrugUnits().getId() == 0) {
//                errors.rejectValue("drugs[" + counter + "].drugUnits.id", "error.field.required");
//            }
            if (drug.getDrugMacPrice() == null) {
                errors.rejectValue("drugs[" + counter + "].drugMacPrice", "error.field.required");
            }
            if (drug.getDrugAwpPrice() == null) {
                errors.rejectValue("drugs[" + counter + "].drugAwpPrice", "error.field.required");
            }
            counter++;
        }

    }

}
