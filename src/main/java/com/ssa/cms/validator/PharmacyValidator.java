package com.ssa.cms.validator;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.Pharmacy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Zubair
 */
@Component
public class PharmacyValidator implements Validator {

    private final Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> type) {
        return Pharmacy.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pharmacy pharmacy = (Pharmacy) o;
        if (pharmacy.getTitle() != null && pharmacy.getTitle().trim().isEmpty()) {
            errors.rejectValue("title", "error.field.required");
        }
        if (pharmacy.getNpi() == null) {
            errors.rejectValue("npi", "error.field.required");
        }
        if (pharmacy.getAccountNo().isEmpty()) {
            errors.rejectValue("accountNo", "error.field.required");
        }
        if (pharmacy.getPhone() != null && pharmacy.getPhone().trim().isEmpty()) {
            errors.rejectValue("phone", "error.field.required");
        }
        if (pharmacy.getEmail() != null && pharmacy.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "error.field.required");
        }
        if (pharmacy.getAddress1() != null && pharmacy.getAddress1().trim().isEmpty()) {
            errors.rejectValue("address1", "error.field.required");
        }
        if (pharmacy.getCity() != null && pharmacy.getCity().trim().isEmpty()) {
            errors.rejectValue("city", "error.field.required");
        }
        if (pharmacy.getState().getId() == 0) {
            errors.rejectValue("state.id", "error.field.required");
        }
        if (pharmacy.getZip() == null) {
            errors.rejectValue("zip", "error.field.required");
        }
        if (pharmacy.getUserName() != null && pharmacy.getUserName().trim().isEmpty()) {
            errors.rejectValue("userName", "error.field.required");
        }
        if (pharmacy.getPassword() != null && pharmacy.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "error.field.required");
        }
        if (pharmacy.getEmail() != null && !pharmacy.getEmail().trim().isEmpty()) {
            matcher = pattern.matcher(pharmacy.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "error.invalid.email");
            }
        }
        if (pharmacy.getTerritory() != null && pharmacy.getTerritory().trim().isEmpty()) {
            errors.rejectValue("territory", "error.field.required");
        }
        if (pharmacy.getContactPerson() != null && pharmacy.getContactPerson().trim().isEmpty()) {
            errors.rejectValue("contactPerson", "error.field.required");
        }
        if (pharmacy.getPersonEmail() != null && pharmacy.getPersonEmail().trim().isEmpty()) {
            errors.rejectValue("personEmail", "error.field.required");
        }
        if (pharmacy.getPersonMobileNo() != null && pharmacy.getPersonMobileNo().trim().isEmpty()) {
            errors.rejectValue("personMobileNo", "error.field.required");
        }
        if (pharmacy.getPersonOfficePhoneNo() != null && pharmacy.getPersonOfficePhoneNo().trim().isEmpty()) {
            errors.rejectValue("personOfficePhoneNo", "error.field.required");
        }
        if (pharmacy.getPersonEmail() != null && !pharmacy.getPersonEmail().trim().isEmpty()) {
            matcher = pattern.matcher(pharmacy.getPersonEmail());
            if (!matcher.matches()) {
                errors.rejectValue("personEmail", "error.invalid.email");
            }
        }
    }

}
