/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.sql.Date;

/**
 *
 * @author Javed
 */
public class SurveyBridgeDTO {
       
    private String orgUnitId;
    private String practiceId;
    private String patientPhonNumber;
    private Integer sessionId;
    private String source;
    private String surveyStatusCode;
    private Date effectiveDate;
    private Date endDate;
    private String uniqueKey;

    public String getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    public String getPatientPhonNumber() {
        return patientPhonNumber;
    }

    public void setPatientPhonNumber(String patientPhonNumber) {
        this.patientPhonNumber = patientPhonNumber;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSurveyStatusCode() {
        return surveyStatusCode;
    }

    public void setSurveyStatusCode(String surveyStatusCode) {
        this.surveyStatusCode = surveyStatusCode;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
