/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CommonMessageFunctionalityI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author adeel.usmani
 */
public class MessageListener {

    @PrePersist
    public void setPersistInfo(CommonMessageFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CommonMessageFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }

    private void encryptData(CommonMessageFunctionalityI commonFunctionality) {
        commonFunctionality.setMessageText(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getMessageText()));
        commonFunctionality.setMessage(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getMessage()));
        commonFunctionality.setSubject(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getSubject()));
        commonFunctionality.setPushSubject(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getPushSubject()));
        commonFunctionality.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(
                commonFunctionality.getPhoneNumber()));
    }

    @PostLoad
    public void loadPersistInfo(CommonMessageFunctionalityI commonFunctionality) {
        commonFunctionality.setMessageText(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getMessageText()));
        commonFunctionality.setMessage(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getMessage()));
        commonFunctionality.setSubject(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getSubject()));
        commonFunctionality.setPushSubject(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getPushSubject()));
        commonFunctionality.setPhoneNumber(EncryptionHandlerUtil.getDecryptedString(
                commonFunctionality.getPhoneNumber()));
    }
}
