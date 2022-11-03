/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CommonPatientInsuranceDetailFunctionlityI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author jiqbal
 */
public class CommonPatientInsuranceDetailListener {

    @PrePersist
    public void setPersistInfo(CommonPatientInsuranceDetailFunctionlityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonPatientInsuranceDetailFunctionlityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CommonPatientInsuranceDetailFunctionlityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CommonPatientInsuranceDetailFunctionlityI commonFunctionality) {

        commonFunctionality.setProviderPhone(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getProviderPhone()));
        commonFunctionality.setInsuranceFrontCardPath(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getInsuranceFrontCardPath()));
        commonFunctionality.setInsuranceBackCardPath(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getInsuranceBackCardPath()));
        commonFunctionality.setCopayFrontCardPath(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCopayFrontCardPath()));
        commonFunctionality.setCopayBackCardPath(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCopayBackCardPath()));

    }

    private void decryptData(CommonPatientInsuranceDetailFunctionlityI commonFunctionality) {

        commonFunctionality.setProviderPhone(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getProviderPhone()));
        commonFunctionality.setInsuranceFrontCardPath(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getInsuranceFrontCardPath()));
        commonFunctionality.setInsuranceBackCardPath(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getInsuranceBackCardPath()));
        commonFunctionality.setCopayFrontCardPath(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCopayFrontCardPath()));
        commonFunctionality.setCopayBackCardPath(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCopayBackCardPath()));
    }

}
