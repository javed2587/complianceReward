package com.ssa.cms.validator;

import com.ssa.cms.model.Drug;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class DrugIngredientValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Drug.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Drug drug = (Drug) o;
        if (drug.getDrugBrand().getId() == 0) {
            errors.rejectValue("drugBrand.id", "error.field.required");
        }
        if (drug.getSelectedDrugId() == 0) {
            errors.rejectValue("selectedDrugId", "error.field.required");
        }
        Integer counter = 0;
        Integer totalPercentage = 0;
//        for (DrugIngredient drugIngredient : drug.getDrugIngredients()) {
//            if (drugIngredient.getIngredients().getId() == 0) {
//                errors.rejectValue("drugIngredients[" + counter + "].ingredients.id", "error.field.required");
//            }
//            if (drugIngredient.getNdcNo() == null) {
//                errors.rejectValue("drugIngredients[" + counter + "].ndcNo", "error.field.required");
//            }
//            if (drugIngredient.getNdcNo() != null) {
//                String ndcNumber = drugIngredient.getNdcNo().toString();
//                if (ndcNumber.length() < 11) {
//                    errors.rejectValue("drugIngredients[" + counter + "].ndcNo", "Pattern.drugBrand.drugs.ndcnumber");
//                }
//            }
//            if (drugIngredient.getPercentage() == null) {
//                errors.rejectValue("drugIngredients[" + counter + "].percentage", "error.field.required");
//            }
//            if (drugIngredient.getPercentage() != null) {
//                totalPercentage = totalPercentage + drugIngredient.getPercentage();
//                if (totalPercentage <= 0 || totalPercentage > 100) {
//                    errors.rejectValue("drugIngredients[" + counter + "].percentage", "field.less.percentage");
//                }
//            }
//            if (drugIngredient.getCost() == null) {
//                errors.rejectValue("drugIngredients[" + counter + "].cost", "error.field.required");
//            }
//            if (drugIngredient.getMinOffer() == null) {
//                errors.rejectValue("drugIngredients[" + counter + "].minOffer", "error.field.required");
//            }
//            if (drugIngredient.getMaxOffer() == null) {
//                errors.rejectValue("drugIngredients[" + counter + "].maxOffer", "error.field.required");
//            }
//            if (drugIngredient.getMinOffer() != null && drugIngredient.getMaxOffer() != null) {
//                if (drugIngredient.getMinOffer() <= 0 || drugIngredient.getMinOffer() > drugIngredient.getMaxOffer()) {
//                    errors.rejectValue("drugIngredients[" + counter + "].minOffer", "field.less.MinOffer");
//                }
//            }
//            if (drugIngredient.getMaxOffer() != null && drugIngredient.getMinOffer() != null && drugIngredient.getMaxOffer() < drugIngredient.getMinOffer()) {
//                errors.rejectValue("drugIngredients[" + counter + "].maxOffer", "field.less.MaxOffer");
//            }
//            counter++;
//        }
    }

}
