/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author arsalan.ahmad
 */
//@Entity
//@Table(name = "PreferencesSetting")
public class PreferencesSetting implements Serializable {

    private Integer id;
    private String heading;
    private String settingName;
    private List<PatientProfilePreferences> patientProfilePreferenceses;

    public PreferencesSetting() {
    }

    public PreferencesSetting(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Heading")
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Column(name = "SettingName")
    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    @OneToMany(mappedBy = "preferenceSetting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<PatientProfilePreferences> getPatientProfilePreferenceses() {
        return patientProfilePreferenceses;
    }

    public void setPatientProfilePreferenceses(List<PatientProfilePreferences> patientProfilePreferenceses) {
        this.patientProfilePreferenceses = patientProfilePreferenceses;
    }

}
