/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

/**
 *
 * @author Osman.Khan
 */
public class CoPayCardDetailsDTO extends BaseDTO {

    private Long id;
    private String manufacturerName;
    private String copayFrontCardPath;
    private String copayBackCardPath;
    private String effectiveDate;
    private String expiryDate;
    private String orderId;
    private String isArchive;
    private Integer patientId;
    private Integer drugBasicId;
    private String drugBrand;
    private Long mfrId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCopayFrontCardPath() {
        return copayFrontCardPath;
    }

    public void setCopayFrontCardPath(String copayFrontCardPath) {
        this.copayFrontCardPath = copayFrontCardPath;
    }

    public String getCopayBackCardPath() {
        return copayBackCardPath;
    }

    public void setCopayBackCardPath(String copayBackCardPath) {
        this.copayBackCardPath = copayBackCardPath;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(String isArchive) {
        this.isArchive = isArchive;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDrugBasicId() {
        return drugBasicId;
    }

    public void setDrugBasicId(Integer drugBasicId) {
        this.drugBasicId = drugBasicId;
    }

    public String getDrugBrand() {
        return drugBrand;
    }

    public void setDrugBrand(String drugBrand) {
        this.drugBrand = drugBrand;
    }

    public Long getMfrId() {
        return mfrId;
    }

    public void setMfrId(Long mfrId) {
        this.mfrId = mfrId;
    }

}
