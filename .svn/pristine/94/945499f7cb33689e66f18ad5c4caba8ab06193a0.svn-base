/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.sun.javafx.geom.transform.Identity;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
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
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "survey_resend_logs")
public class SurveyResendLogs implements Serializable {
    
    private int id;
    private PatientProfile patientProfile;
    private AssignedSurvey surveyLogs;
    private int sentBy;
    private Date createdAt;
    private Date updtedAt;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = true, updatable = true, nullable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_log_id", insertable = true, updatable = true, nullable = true)
    public AssignedSurvey getSurveyLogs() {
        return surveyLogs;
    }

    public void setSurveyLogs(AssignedSurvey surveyLogs) {
        this.surveyLogs = surveyLogs;
    }
    @Column(name = "sent_by")
    public int getSentBy() {
        return sentBy;
    }

    public void setSentBy(int sentBy) {
        this.sentBy = sentBy;
    }
    @Column(name = "created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Column(name = "updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdtedAt() {
        return updtedAt;
    }

    public void setUpdtedAt(Date updtedAt) {
        this.updtedAt = updtedAt;
    }
    
}
