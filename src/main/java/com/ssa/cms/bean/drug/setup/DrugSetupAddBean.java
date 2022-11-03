/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.bean.drug.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISPIN
 */
public class DrugSetupAddBean {
    
    private     String      drugCategoryId;
    private     String      drugCategoryName;
    private     String      therapyClassId;
    private     String      therapyClassName;
    private     String      genericNameId;
    private     String      genericNameName;
    private     String      brandNameId;
    private     String      brandNameName;
    
    private     boolean     saveAction = false;
    private     boolean     updateAction = false;
    private     boolean     deleteAction = false;

    
    private     List<DrugSetupDrugBean> drugSetupDrugBean = new ArrayList<DrugSetupDrugBean>();

    public DrugSetupAddBean() {
    }

    public String getDrugCategoryId() {
        return drugCategoryId;
    }

    public void setDrugCategoryId(String drugCategoryId) {
        this.drugCategoryId = drugCategoryId;
    }

    public String getDrugCategoryName() {
        return drugCategoryName;
    }

    public void setDrugCategoryName(String drugCategoryName) {
        this.drugCategoryName = drugCategoryName;
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

    public String getGenericNameId() {
        return genericNameId;
    }

    public void setGenericNameId(String genericNameId) {
        this.genericNameId = genericNameId;
    }

    public String getGenericNameName() {
        return genericNameName;
    }

    public void setGenericNameName(String genericNameName) {
        this.genericNameName = genericNameName;
    }

    public String getBrandNameId() {
        return brandNameId;
    }

    public void setBrandNameId(String brandNameId) {
        this.brandNameId = brandNameId;
    }

    public String getBrandNameName() {
        return brandNameName;
    }

    public void setBrandNameName(String brandNameName) {
        this.brandNameName = brandNameName;
    }

    public List<DrugSetupDrugBean> getDrugSetupDrugBean() {
        return drugSetupDrugBean;
    }

    public void setDrugSetupDrugBean(List<DrugSetupDrugBean> drugSetupDrugBean) {
        this.drugSetupDrugBean = drugSetupDrugBean;
    }

    public boolean isUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(boolean updateAction) {
        this.updateAction = updateAction;
    }

    public boolean isDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(boolean deleteAction) {
        this.deleteAction = deleteAction;
    }

    public boolean isSaveAction() {
        return saveAction;
    }

    public void setSaveAction(boolean saveAction) {
        this.saveAction = saveAction;
    }
    
}
