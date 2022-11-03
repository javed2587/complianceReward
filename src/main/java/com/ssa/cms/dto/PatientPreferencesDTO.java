/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.io.Serializable;

/**
 *
 * @author arsalan.ahmad
 */
public class PatientPreferencesDTO implements Serializable {

    private Integer id;
    private String settingName;
    private boolean preferenceValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public boolean isPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(boolean preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

}
