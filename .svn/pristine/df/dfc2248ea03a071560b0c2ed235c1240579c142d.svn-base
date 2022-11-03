package com.ssa.cms.validator;

import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyFacilityOperation;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.service.ConsumerRegistrationService;
import java.util.ArrayList;
import java.util.Iterator;

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
public class ConsumerRegistrationValidator implements Validator {

    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

    @Override
    public boolean supports(Class<?> type) {
        return Pharmacy.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pharmacy pharmacy = (Pharmacy) o;

        int counter = 0;
        List<PharmacyUser> pharmacyUserList = new ArrayList();//pharmacy.getPharmacyUserList();
        if (pharmacy.getPharmacyUserList().size() > 0) {
            Iterator<PharmacyUser> iter = pharmacy.getPharmacyUserList().iterator();
            while (iter.hasNext()) {
                PharmacyUser pharmacyUser = iter.next();
                if ((pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) && (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty())
                        && (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty())
                        && (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty())) {
                    pharmacyUserList.add(pharmacyUser);
                    iter.remove();
                } else {
                    pharmacyUserList.add(pharmacyUser);
                }
            }

//        	for (int i = 1; i < pharmacy.getPharmacyUserList().size(); i++) {
//        		  PharmacyUser pharmacyUser = pharmacy.getPharmacyUserList().get(i);
//                  if ((pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) && (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty())
//                          && (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty())
//                          && (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty())) {
//
//
//                      pharmacy.getPharmacyUserList().remove(pharmacyUser);
//                  }
//			}
        }
        Iterator<PharmacyUser> iter = pharmacyUserList.iterator();
        while (iter.hasNext()) {
            PharmacyUser pharmacyUser = iter.next();
            if (counter == 0) {
                if (pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) {
                    errors.rejectValue("pharmacyUserList[" + counter + "].firstName", "error.field.required");
                }

                if (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty()) {
                    errors.rejectValue("pharmacyUserList[" + counter + "].lastName", "error.field.required");
                }

                if (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty()) {
                    errors.rejectValue("pharmacyUserList[" + counter + "].phone", "error.field.required");
                }

                if (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty()) {
                    errors.rejectValue("pharmacyUserList[" + counter + "].email", "error.field.required");
                }
                /*if (pharmacyUser.getEmailNotify() == null && pharmacyUser.getSmsNotify() == null) {
                    errors.rejectValue("pharmacyUserList[" + counter + "].smsNotify", "error.field.required");
                }*/
            } else if (counter == 1) {

                if (pharmacy.getAddSecondaryContacts()) {
                    if (pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].firstName", "error.field.required");
                    }

                    if (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].lastName", "error.field.required");
                    }

                    if (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].phone", "error.field.required");
                    }

                    if (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].email", "error.field.required");
                    }
                } else {
                    pharmacy.getPharmacyUserList().remove(pharmacyUser);
                }

            } else if (counter == 2) {

                if (pharmacy.getAddThirdContacts()) {
                    if (pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].firstName", "error.field.required");
                    }

                    if (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].lastName", "error.field.required");
                    }

                    if (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].phone", "error.field.required");
                    }

                    if (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty()) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].email", "error.field.required");
                    }
                } else {
                    pharmacy.getPharmacyUserList().remove(pharmacyUser);
                }
            }

            if (pharmacyUser.getEmail() != null && !pharmacyUser.getEmail().isEmpty()) {
                if (pharmacy.getId() == null) {
                    boolean emailExist = consumerRegistrationService.isEmailAddressUnique(pharmacyUser.getEmail());
                    if (emailExist) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].email", "field.email.duplicate");
                    } else if (counter > 0 && pharmacyUser.getEmail() != null) {
                        if (pharmacy.getPharmacyUserList().get(0).getEmail().equalsIgnoreCase(pharmacy.getPharmacyUserList().get(counter).getEmail())) {
                            errors.rejectValue("pharmacyUserList[" + counter + "].email", "field.email.duplicate");
                        }
                    }
                } else if (pharmacyUser.getId() == null) {
                    boolean emailExist = consumerRegistrationService.isEmailAddressUnique(pharmacyUser.getEmail());
                    if (emailExist) {
                        errors.rejectValue("pharmacyUserList[" + counter + "].email", "field.email.duplicate");
                    } else if (counter > 0 && pharmacyUser.getEmail() != null) {
                        if (pharmacy.getPharmacyUserList().get(0).getEmail().equalsIgnoreCase(pharmacy.getPharmacyUserList().get(counter).getEmail())) {
                            errors.rejectValue("pharmacyUserList[" + counter + "].email", "field.email.duplicate");
                        }
                    }
                }
            }
            if (pharmacyUserList.size() != counter) {
                counter++;
            }
        }
        if (pharmacy.getAcceptedTerms() == null) {
            errors.rejectValue("acceptedTerms", "error.field.required");
        }
        if (pharmacy.getAcceptedPolicy() == null) {
            errors.rejectValue("acceptedPolicy", "error.field.required");
        }

        boolean facilityOperationFlag = false;
        Iterator iter2 = null;
        if (pharmacy.getPharmacyFacilityOperationList() != null && pharmacy.getPharmacyFacilityOperationList().size() > 0) {
            iter2 = pharmacy.getPharmacyFacilityOperationList().iterator();
        }
        //for (PharmacyFacilityOperation pharmacyFacilityOperation : pharmacy.getPharmacyFacilityOperationList()) {
        while (iter2 != null && iter2.hasNext()) {
            PharmacyFacilityOperation pharmacyFacilityOperation = (PharmacyFacilityOperation) iter2.next();
            if (pharmacyFacilityOperation.getIsSelected()) {
                facilityOperationFlag = true;
                if ((pharmacyFacilityOperation.getPhoneHoursFrom() == null || pharmacyFacilityOperation.getPhoneHoursFrom().trim().equals(""))
                        || (pharmacyFacilityOperation.getPhoneHoursTo() == null || pharmacyFacilityOperation.getPhoneHoursTo().trim().equals(""))) {
                    errors.rejectValue("facilityOperationSelected", "facilityoperation.schedule.required");
                }
//                 else if (DateUtil.getTimeWithDate(pharmacyFacilityOperation.getPhoneHoursFrom().toLowerCase(),"H:mm a").equals(
//                            DateUtil.getTimeWithDate(pharmacyFacilityOperation.getPhoneHoursTo().toLowerCase(),"H:mm a"))) {
//                    errors.rejectValue("facilityOperationSelected", "facilityoperation.schedule.error");
//                }else if (DateUtil.getTimeWithDate(pharmacyFacilityOperation.getPhoneHoursFrom().toLowerCase(),"H:mm a").after(
//                            DateUtil.getTimeWithDate(pharmacyFacilityOperation.getPhoneHoursTo().toLowerCase(),"H:mm a"))) {
//                    errors.rejectValue("facilityOperationSelected", "facilityoperation.schedule.error");
//                }
            } else {
                pharmacyFacilityOperation.setPhoneHoursFrom(null);
                pharmacyFacilityOperation.setPhoneHoursTo(null);
            }
        }

        if (facilityOperationFlag == false) {
            errors.rejectValue("facilityOperationSelected", "error.field.required");
        }
    }
}
