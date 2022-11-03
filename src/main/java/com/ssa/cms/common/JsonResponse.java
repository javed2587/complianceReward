/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.common;

import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.model.AuditFields;
import com.ssa.cms.model.BaseModel;
import java.io.Serializable;

/**
 *
 * @author Haider Ali
 */
public class JsonResponse implements Serializable{
    
    /**
     * response Attributes
     */
    
    private     String      status;
    private     Integer     statuscode;
    private     String      errorMessage;
    private     AuditFields auditFields;
    private     BaseDTO     baseDTO;
    private     BaseModel   baseModel;
    private     Object      dependants;

    public JsonResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AuditFields getAuditFields() {
        return auditFields;
    }

    public void setAuditFields(AuditFields auditFields) {
        this.auditFields = auditFields;
    }

    public BaseDTO getBaseDTO() {
        return baseDTO;
    }

    public void setBaseDTO(BaseDTO baseDTO) {
        this.baseDTO = baseDTO;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public Object getDependants() {
        return dependants;
    }

    public void setDependants(Object dependants) {
        this.dependants = dependants;
    }
    
}
