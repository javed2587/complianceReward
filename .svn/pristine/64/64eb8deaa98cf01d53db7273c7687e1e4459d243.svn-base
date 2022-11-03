/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CustomerRequestFunctionalityI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Zubair
 */
public class CustomerRequestListener {

    @PrePersist
    public void setPersistInfo(CustomerRequestFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CustomerRequestFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }
    
    @PostLoad
    public void loadPersistInfo(CustomerRequestFunctionalityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CustomerRequestFunctionalityI commonFunctionality) {
        commonFunctionality.setPhoneNumber(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getPhoneNumber()));
    }

    private void decryptData(CustomerRequestFunctionalityI commonFunctionality) {
        commonFunctionality.setPhoneNumber(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getPhoneNumber()));
    }
}
