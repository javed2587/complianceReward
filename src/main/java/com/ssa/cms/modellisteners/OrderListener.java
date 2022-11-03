/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CommonOrderFunctionalityI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author adeel.usmani
 */
public class OrderListener {

    @PrePersist
    public void setPersistInfo(CommonOrderFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonOrderFunctionalityI commonFunctionality) {
        this.encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CommonOrderFunctionalityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CommonOrderFunctionalityI commonFunctionality) {
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setCardHolderName(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardHolderName()));
        commonFunctionality.setCardNumber(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardNumber()));
        commonFunctionality.setCardCvv(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardCvv()));
        commonFunctionality.setCardType(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCardType()));
        //commonFunctionality.setDrugName(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getDrugName()));
        commonFunctionality.setImagePath(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getImagePath()));
        commonFunctionality.setVideo(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getVideo()));
        commonFunctionality.setDrugImg(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getDrugImg()));
        commonFunctionality.setCustomDocument(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getCustomDocument()));
    }

    private void decryptData(CommonOrderFunctionalityI commonFunctionality) {
        commonFunctionality.setFirstName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getFirstName()));
        commonFunctionality.setLastName(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getLastName()));
        commonFunctionality.setCardHolderName(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardHolderName()));
        commonFunctionality.setCardNumber(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardNumber()));
        commonFunctionality.setCardCvv(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardCvv()));
        commonFunctionality.setCardType(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCardType()));
        //commonFunctionality.setDrugName(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getDrugName()));
        commonFunctionality.setImagePath(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getImagePath()));
        commonFunctionality.setVideo(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getVideo()));
        commonFunctionality.setDrugImg(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getDrugImg()));
        commonFunctionality.setCustomDocument(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getCustomDocument()));
    }
}
