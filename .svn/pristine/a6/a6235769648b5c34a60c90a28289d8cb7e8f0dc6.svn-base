package com.ssa.cms.validator;

import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.service.ConsumerRegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author msheraz
 */
@Component
public class ConsumerChangePasswordValidator implements Validator {

    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

    @Override
    public boolean supports(Class<?> type) {
        return Pharmacy.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pharmacy pharmacy = (Pharmacy) o;
        
        if(pharmacy.getPassword() != null && pharmacy.getPassword().isEmpty()) {
                errors.rejectValue("password", "error.field.required");
        }
       
        if(pharmacy.getNewPassword() != null && pharmacy.getNewPassword().isEmpty()) {
            errors.rejectValue("newPassword", "error.field.required");
        }
        
        if(pharmacy.getRepeatNewPassword() != null && pharmacy.getRepeatNewPassword().isEmpty()) {
            errors.rejectValue("repeatNewPassword", "error.field.required");
        }
               
    }

}
