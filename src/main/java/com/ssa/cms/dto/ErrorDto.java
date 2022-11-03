/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

/**
 *
 * @author Javed Iqbal
 */
public class ErrorDto {
    private String update;
    private String save;
    private Boolean isSaved;
    private int value;
    private int dbAllergyid;

    public int getDbAllergyid() {
        return dbAllergyid;
    }

    public void setDbAllergyid(int dbAllergyid) {
        this.dbAllergyid = dbAllergyid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }
}
