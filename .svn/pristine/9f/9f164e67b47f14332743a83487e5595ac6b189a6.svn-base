/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.common.Constants;
import com.ssa.cms.modelinterfaces.CommonPatientFunctionalityI;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author adeel.usmani
 */
public class PatientListener {

    @PrePersist
    public void setPersistInfo(CommonPatientFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonPatientFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CommonPatientFunctionalityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CommonPatientFunctionalityI commonFunctionality) {

        commonFunctionality.setAlternatePhoneNumber(
                EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getAlternatePhoneNumber()));
        commonFunctionality.setEmailAddress(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getEmailAddress()));
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setInsuranceBackCardPath(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getInsuranceBackCardPath()));
        commonFunctionality.setInsuranceFrontCardPath(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getInsuranceFrontCardPath()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setMobileNumber(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getMobileNumber()));
        commonFunctionality.setSignature(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getSignature()));
        commonFunctionality.setRefillRequestSign(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getRefillRequestSign()));
        try {
            if (commonFunctionality.getDob() != null) {
                commonFunctionality.setBirthDate(EncryptionHandlerUtil.getEncryptedString(DateUtil.dateToString(commonFunctionality.getDob(), Constants.USA_DATE_FORMATE)));
            }
        } catch (Exception ex) {
            Logger.getLogger(PatientListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void decryptData(CommonPatientFunctionalityI commonFunctionality) {

        commonFunctionality.setAlternatePhoneNumber(
                EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getAlternatePhoneNumber()));
        commonFunctionality.setEmailAddress(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getEmailAddress()));
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setInsuranceBackCardPath(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getInsuranceBackCardPath()));
        commonFunctionality.setInsuranceFrontCardPath(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getInsuranceFrontCardPath()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setMobileNumber(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getMobileNumber()));
        //commonFunctionality.setBirthDate(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getBirthDate()));
        commonFunctionality.setSignature(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getSignature()));
        commonFunctionality.setRefillRequestSign(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getRefillRequestSign()));
        try {
            if (CommonUtil.isNotEmpty(commonFunctionality.getBirthDate())) {
                commonFunctionality.setDob(DateUtil.stringToDate(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getBirthDate()), Constants.USA_DATE_FORMATE));
            }

        } catch (ParseException ex) {
            Logger.getLogger(PatientListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
