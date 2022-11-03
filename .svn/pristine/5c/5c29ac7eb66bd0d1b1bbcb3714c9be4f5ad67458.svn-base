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
import javax.persistence.TemporalType;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="survey_logs")
public class AssignedSurvey implements Serializable {
    private Long id;
    private PatientProfile patientProfile;
    private String surveyCode;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Survey2 survey;
    private int version;
    private Date deletedAt;


    public AssignedSurvey() {
    }
    
    
     public AssignedSurvey(Long id) {
         this.id=id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "patient_id", insertable = true, updatable = true, nullable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Column(name="survey_code")
    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    @Column(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name="updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "survey_id", insertable = true, updatable = true, nullable = false)
    public Survey2 getSurvey() {
        return survey;
    }

    public void setSurvey(Survey2 survey) {
        this.survey = survey;
    }
   @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
       @Column(name = "deleted_at")
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    
}
