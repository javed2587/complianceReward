/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Javed
 */
@Entity
@Table(name = "SurveyBridge")
public class SurveyBridge implements Serializable {
  
    private String orgUnitId;
    private String practiceId;
    private String patientPhonNumber;
    private Integer sessionId;
    private String source;
    private String surveyStatusCode;
    private Date effectiveDate;
    private Date endDate;
    private String uniqueKey;

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "Unique_Key", unique = true, nullable = false)
    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    @Column(name = "Org_Unit_ID")
    public String getOrgUnitId() {
        return orgUnitId;
    }
    
    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }
    @Column(name = "Practice_ID")
    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }
    @Column(name = "Patient_Phone_Number")
    public String getPatientPhonNumber() {
        return patientPhonNumber;
    }

    public void setPatientPhonNumber(String patientPhonNumber) {
        this.patientPhonNumber = patientPhonNumber;
    }
    @Column(name = "Session_ID")
    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
    @Column(name = "Source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    @Column(name = "Survey_Status_Code")
    public String getSurveyStatusCode() {
        return surveyStatusCode;
    }

    public void setSurveyStatusCode(String surveyStatusCode) {
        this.surveyStatusCode = surveyStatusCode;
    }
    @Column(name = "Effective_Date")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    @Column(name = "End_Date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }   
}

