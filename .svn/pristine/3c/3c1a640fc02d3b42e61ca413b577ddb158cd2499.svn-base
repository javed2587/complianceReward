/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modellisteners;

import com.ssa.cms.modelinterfaces.CampaignMessageReqResFunctionalityI;
import com.ssa.cms.util.EncryptionHandlerUtil;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Zubair
 */
public class CampaignMessageReqResListener {

    @PrePersist
    public void setPersistInfo(CampaignMessageReqResFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }

    @PreUpdate
    public void setUpdateInfo(CampaignMessageReqResFunctionalityI commonFunctionality) {
        encryptData(commonFunctionality);
    }

    @PostLoad
    public void loadPersistInfo(CampaignMessageReqResFunctionalityI commonFunctionality) {
        this.decryptData(commonFunctionality);
    }

    private void encryptData(CampaignMessageReqResFunctionalityI commonFunctionality) {
        commonFunctionality.setSmsRequest(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getSmsRequest()));
        commonFunctionality.setSmsResponse(EncryptionHandlerUtil.getEncryptedString(commonFunctionality.getSmsResponse()));
    }

    private void decryptData(CampaignMessageReqResFunctionalityI commonFunctionality) {
        commonFunctionality.setSmsRequest(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getSmsRequest()));
        commonFunctionality.setSmsResponse(EncryptionHandlerUtil.getDecryptedString(commonFunctionality.getSmsResponse()));
    }
}
