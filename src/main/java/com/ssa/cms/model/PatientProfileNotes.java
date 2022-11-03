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
import javax.persistence.Transient;

/**
 *
 * @author Abdullah
 */
//@Entity
//@Table(name = "PatientProfileNotes")
public class PatientProfileNotes implements Serializable {

    private Integer id;
    private PatientProfile patientProfile;
    private String notes;
    private Integer createdBy;
    private Date createdOn;
    private Integer ptProfileId;
    private String sentBy;
    private String sentOn;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CreatedBy")
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "Notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "PtProfileId", insertable = true, updatable = true)
    @Transient
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Transient
    public Integer getPtProfileId() {
        return ptProfileId;
    }

    public void setPtProfileId(Integer ptProfileId) {
        this.ptProfileId = ptProfileId;
    }

    @Transient
    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    @Transient
    public String getSentOn() {
        return sentOn;
    }

    public void setSentOn(String sentOn) {
        this.sentOn = sentOn;
    }

}
