package com.ssa.cms.validator;

import com.ssa.cms.model.DeliveryDistanceFee;
import com.ssa.cms.model.PharmacyZipCodes;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author mzubair
 */
@Component
public class ShippingMilesValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return PharmacyZipCodes.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PharmacyZipCodes pharmacyZipCodes = (PharmacyZipCodes) o;
        if (pharmacyZipCodes.getId() == 0) {
            errors.rejectValue("id", "error.field.required");
        }
        int count = 0;
        for (DeliveryDistanceFee deliveryDistanceFee : pharmacyZipCodes.getDeliveryDistanceFeesList()) {
            if (deliveryDistanceFee.getFee() == null) {
                errors.rejectValue("deliveryDistanceFeesList[" + count + "].fee", "error.field.required");
            }
            if (deliveryDistanceFee.getDescription() != null && deliveryDistanceFee.getDescription().isEmpty()) {
                errors.rejectValue("deliveryDistanceFeesList[" + count + "].description", "error.field.required");
            }
            count++;
        }
    }

}
