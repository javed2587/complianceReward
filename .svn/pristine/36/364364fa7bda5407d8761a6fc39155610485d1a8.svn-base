/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CommonPatientPaymentInfoI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author mzubair
 */
public class PatientPaymentInfoListener {

    @PrePersist
    public void setPersistInfo(CommonPatientPaymentInfoI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonPatientPaymentInfoI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CommonPatientPaymentInfoI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CommonPatientPaymentInfoI commonFunctionality) {
        commonFunctionality.setCardHolderName(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardHolderName()));
        commonFunctionality.setCardNumber(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardNumber()));
        commonFunctionality.setCardType(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardType()));
        commonFunctionality.setCvvNumber(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCvvNumber()));
    }

    private void decryptData(CommonPatientPaymentInfoI commonFunctionality) {
        commonFunctionality.setCardHolderName(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardHolderName()));
        commonFunctionality.setCardNumber(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardNumber()));
        commonFunctionality.setCardType(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardType()));
        commonFunctionality.setCvvNumber(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCvvNumber()));
    }
}
