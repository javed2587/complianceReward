/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.io.Serializable;

/**
 *
 * @author Haider Ali
 */
public class DrugCategoryListDTO implements Serializable {

    private String categoryId;
    private String categoryName;
    private String therapyClassId;
    private String therapyClassName;
    private String genericNameId;
    private String genericName;
    private String brandNameId;
    private String brandName;
    private String strength;
    private String type;
    private String gpi;
    private String routDesc;
    private String macUnitPrice;
    private String awpUnitPrice;
    private String drugUnitName;
    private String strengthWithUnit;
    private String ndc;
    private int defaultQty;
    private float basePrice;
    private Integer drugBasicId;
    private float acqCost;
    private Long drugDetailId;

    public DrugCategoryListDTO() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTherapyClassId() {
        return therapyClassId;
    }

    public void setTherapyClassId(String therapyClassId) {
        this.therapyClassId = therapyClassId;
    }

    public String getTherapyClassName() {
        return therapyClassName;
    }

    public void setTherapyClassName(String therapyClassName) {
        this.therapyClassName = therapyClassName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGpi() {
        return gpi;
    }

    public void setGpi(String gpi) {
        this.gpi = gpi;
    }

    public String getRoutDesc() {
        return routDesc;
    }

    public void setRoutDesc(String routDesc) {
        this.routDesc = routDesc;
    }

    public String getMacUnitPrice() {
        return macUnitPrice;
    }

    public void setMacUnitPrice(String macUnitPrice) {
        this.macUnitPrice = macUnitPrice;
    }

    public String getAwpUnitPrice() {
        return awpUnitPrice;
    }

    public void setAwpUnitPrice(String awpUnitPrice) {
        this.awpUnitPrice = awpUnitPrice;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDrugUnitName() {
        return drugUnitName;
    }

    public void setDrugUnitName(String drugUnitName) {
        this.drugUnitName = drugUnitName;
    }

    public String getGenericNameId() {
        return genericNameId;
    }

    public void setGenericNameId(String genericNameId) {
        this.genericNameId = genericNameId;
    }

    public String getBrandNameId() {
        return brandNameId;
    }

    public void setBrandNameId(String brandNameId) {
        this.brandNameId = brandNameId;
    }

    public String getStrengthWithUnit() {
        return strengthWithUnit;
    }

    public void setStrengthWithUnit(String strengthWithUnit) {
        this.strengthWithUnit = strengthWithUnit;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public int getDefaultQty() {
        return defaultQty;
    }

    public void setDefaultQty(int defaultQty) {
        this.defaultQty = defaultQty;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getDrugBasicId() {
        return drugBasicId;
    }

    public void setDrugBasicId(Integer drugBasicId) {
        this.drugBasicId = drugBasicId;
    }

    public float getAcqCost() {
        return acqCost;
    }

    public void setAcqCost(float acqCost) {
        this.acqCost = acqCost;
    }

    public Long getDrugDetailId() {
        return drugDetailId;
    }

    public void setDrugDetailId(Long drugDetailId) {
        this.drugDetailId = drugDetailId;
    }

}
