/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Javed
 */
@Entity
@Table(name = "PatientBloodPresuureResult")
public class BloodPressure implements Serializable {

    private Integer bloodPresureSeqNo;
    private PatientProfile patientProfile;
    private Integer systolicBloodPressure;
    private Integer distolicBloodPressure;
    private Integer pulse;
    private String readingTime;
    private Date createdOn;
    private Date updatedOn;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "BloodPresureSeqNo")
    public Integer getBloodPresureSeqNo() {
        return bloodPresureSeqNo;
    }

    public void setBloodPresureSeqNo(Integer bloodPresureSeqNo) {
        this.bloodPresureSeqNo = bloodPresureSeqNo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_PatientProfileSeqNo")
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
    @Column(name = "Systolic_BloodPressure")
    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }
    @Column(name = "Distolic_BloodPressure")
    public Integer getDistolicBloodPressure() {
        return distolicBloodPressure;
    }

    public void setDistolicBloodPressure(Integer distolicBloodPressure) {
        this.distolicBloodPressure = distolicBloodPressure;
    }
    @Column(name = "Pulse")
    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }
    @Column(name = "ReadingTime")
    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
     public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    @Column(name = "UpdatedOn")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
