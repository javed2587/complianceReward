/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author arsalan.ahmad
 */
@Entity
@Table(name = "PatientProfilePreferences")
public class PatientProfilePreferences implements Serializable{
   
    private Integer id;
    private PatientProfile patient;
    private PreferencesSetting preferenceSetting;
    private boolean preferenceValue;
    
    public PatientProfilePreferences(){}
    public PatientProfilePreferences(PatientProfile patient, PreferencesSetting preferenceSetting){
        this.patient = patient;
        this.preferenceSetting = preferenceSetting;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", insertable = true, updatable = true)
    public PatientProfile getPatient() {
        return patient;
    }

    public void setPatient(PatientProfile patient) {
        this.patient = patient;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "preferenceSettingId", insertable = true, updatable = true)
    @Transient
    public PreferencesSetting getPreferenceSetting() {
        return preferenceSetting;
    }

    public void setPreferenceSetting(PreferencesSetting preferenceSetting) {
        this.preferenceSetting = preferenceSetting;
    }

    @Column(name = "preferenceValue")
    public boolean isPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(boolean preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
    
}
