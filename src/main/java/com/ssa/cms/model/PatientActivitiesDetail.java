/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Javed
 */
@Entity
@Table(name = "PatientActivitiesDetail")
public class PatientActivitiesDetail implements Serializable {

    private Integer patientActivitySeqNo;
    private PatientProfile patientProfile;
    private PatientGlucoseResults patientGlucoseResults;
    private PatientBodyMassResult patientBodyMassResult;
    private BloodPressure bloodPressure;
    private PatientHeartPulseResult patientHeartPulseResult;
    private String patientName;
    private String patientAge;
    private String readingDateAndTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "patientActivitySeqNo")
    public Integer getPatientActivitySeqNo() {
        return patientActivitySeqNo;
    }

    public void setPatientActivitySeqNo(Integer patientActivitySeqNo) {
        this.patientActivitySeqNo = patientActivitySeqNo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_patinetProfileSeqNo", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_BloodGlucoseSeqNo")
    public PatientGlucoseResults getPatientGlucoseResults() {
        return patientGlucoseResults;
    }

    public void setPatientGlucoseResults(PatientGlucoseResults patientGlucoseResults) {
        this.patientGlucoseResults = patientGlucoseResults;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_BodyMassSeqNo")
    public PatientBodyMassResult getPatientBodyMassResult() {
        return patientBodyMassResult;
    }

    public void setPatientBodyMassResult(PatientBodyMassResult patientBodyMassResult) {
        this.patientBodyMassResult = patientBodyMassResult;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_BloodPressureSeqNo")
    public BloodPressure getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(BloodPressure bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_HeartPulseSeqNo")
    public PatientHeartPulseResult getPatientHeartPulseResult() {
        return patientHeartPulseResult;
    }

    public void setPatientHeartPulseResult(PatientHeartPulseResult patientHeartPulseResult) {
        this.patientHeartPulseResult = patientHeartPulseResult;
    }

    @Column(name = "PatientName")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "PatientAge")
    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    @Column(name = "ReadingDateTime")
    public String getReadingDateAndTime() {
        return readingDateAndTime;
    }

    public void setReadingDateAndTime(String readingDateAndTime) {
        this.readingDateAndTime = readingDateAndTime;
    }
}
