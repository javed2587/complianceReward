/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.common.Constants;
import com.ssa.cms.modelinterfaces.CommonPatientProfileMembersFunctionalityI;
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
 * @author mzubair
 */
public class PatientProfileMembersListener {

    @PrePersist
    public void setPersistInfo(CommonPatientProfileMembersFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonPatientProfileMembersFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CommonPatientProfileMembersFunctionalityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CommonPatientProfileMembersFunctionalityI commonFunctionality) {
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setEmail(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getEmail()));
        commonFunctionality.setBackPOAImage(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getBackPOAImage()));
        commonFunctionality.setFrontPOAImage(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getFrontPOAImage()));
        try {
            if (commonFunctionality.getDob() != null) {
                commonFunctionality.setBirthDate(EncryptionHandlerUtil.getEncryptedString(DateUtil.dateToString(commonFunctionality.getDob(), Constants.USA_DATE_FORMATE)));
            }
        } catch (Exception ex) {
            Logger.getLogger(PatientListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void decryptData(CommonPatientProfileMembersFunctionalityI commonFunctionality) {
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setEmail(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getEmail()));
        commonFunctionality.setBackPOAImage(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getBackPOAImage()));
        commonFunctionality.setFrontPOAImage(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getFrontPOAImage()));
        commonFunctionality.setBirthDate(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getBirthDate()));
        try {
            if (CommonUtil.isNotEmpty(commonFunctionality.getBirthDate())) {
                commonFunctionality.setDob(DateUtil.stringToDate(commonFunctionality.getBirthDate(), Constants.USA_DATE_FORMATE));
            }

        } catch (ParseException ex) {
            Logger.getLogger(PatientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
