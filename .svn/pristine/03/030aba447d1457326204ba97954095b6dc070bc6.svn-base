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
@Table(name = "PatientBodyMassResult")
public class PatientBodyMassResult implements Serializable {
    
    private Integer bodyMassResultSeqNo;
    private PatientProfile patientProfile;
    private String height;
    private String weight;
    private String resultDate;
    private String pulse;
    private String bmi;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "BodyMassResult_seqNo", unique = true, nullable = false)
    public Integer getBodyMassResultSeqNo() {
        return bodyMassResultSeqNo;
    }
   
    public void setBodyMassResultSeqNo(Integer bodyMassResultSeqNo) {
        this.bodyMassResultSeqNo = bodyMassResultSeqNo;
    }
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_patientProfileSeqNo", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
   @Column(name = "Height")
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    @Column(name = "Weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    @Column(name = "ResultDate")
    public String getresultDate() {
        return resultDate;
    }

    public void setresultDate(String resultDate) {
        this.resultDate = resultDate;
    }
    @Column(name = "Pulse")
    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
    
    @Column(name = "BMI")
    public String getBMI() {
        return bmi;
    }

    public void setBMI(String ethnicity) {
        this.bmi = ethnicity;
    }
    @Column(name = "CreatedDate")
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
