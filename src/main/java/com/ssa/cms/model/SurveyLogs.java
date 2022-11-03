///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ssa.cms.model;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//
///**
// *
// * @author Javed Iqbal
// */
//@Entity
//@Table(name = "survey_logs")
//public class SurveyLogs implements Serializable {
//
//    private Long id;
//    private PatientProfile patientProfile;
//    private Survey2 survey;
//    private String surveyCode;
//    private String status;
//    private Date createdAt;
//    private Date updatedAt;
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, unique = true)
//    @Id
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "patient_id")
//    public PatientProfile getPatientProfile() {
//        return patientProfile;
//    }
//
//    public void setPatientProfile(PatientProfile patientProfile) {
//        this.patientProfile = patientProfile;
//    }
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "survey_id")
//    public Survey2 getSurvey() {
//        return survey;
//    }
//
//    public void setSurvey(Survey2 survey) {
//        this.survey = survey;
//    }
//
//    @Column(name="survey_code")
//    public String getSurveyCode() {
//        return surveyCode;
//    }
//
//    public void setSurveyCode(String surveyCode) {
//        this.surveyCode = surveyCode;
//    }
//
//    @Column(name = "status")
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Column(name = "created_at")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Column(name = "updated_at")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//}
