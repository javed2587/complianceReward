/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

/**
 *
 * @author Javed
 */
public class PatientActivity {
    private String patientId;
    private String firstName;
    private String lastName;
    private String birth;
    private String dateTime;
    private int systolicBloodPressure;
    private int distolicBloodPressure;
    private String glucoseLevel;
    private String height;
    private String weight;
    private int heartPulse;
    private int HeartPulseSeqNo;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(int systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public int getDistolicBloodPressure() {
        return distolicBloodPressure;
    }

    public void setDistolicBloodPressure(int distolicBloodPressure) {
        this.distolicBloodPressure = distolicBloodPressure;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHeartPulse() {
        return heartPulse;
    }

    public void setHeartPulse(int heartPulse) {
        this.heartPulse = heartPulse;
    }

    public int getHeartPulseSeqNo() {
        return HeartPulseSeqNo;
    }

    public void setHeartPulseSeqNo(int HeartPulseSeqNo) {
        this.HeartPulseSeqNo = HeartPulseSeqNo;
    }

}
