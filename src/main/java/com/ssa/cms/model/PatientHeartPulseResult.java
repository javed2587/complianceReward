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
@Table(name = "PatientHeartPulse")
public class PatientHeartPulseResult implements Serializable {
    private Integer heartPulseSeqNo;
    private PatientProfile patientProfile;
    private String heartPulse;
    private String heartPulseDate;
    private Date createdOn;
    private Date updatedOn;

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "HeartPulseSeqNo")
    public Integer getHeartPulseSeqNo() {
        return heartPulseSeqNo;
    }
  
    public void setHeartPulseSeqNo(Integer heartPulseSeqNo) {
        this.heartPulseSeqNo = heartPulseSeqNo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_PatientProfileSeqNo")
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
    @Column(name = "HeartPulse")
    public String getHeartPulse() {
        return heartPulse;
    }

    public void setHeartPulse(String heartPulse) {
        this.heartPulse = heartPulse;
    }
    @Column(name = "HeartPulseDate")
    public String getHeartPulseDate() {
        return heartPulseDate;
    }

    public void setHeartPulseDate(String heartPulseDate) {
        this.heartPulseDate = heartPulseDate;
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
