/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Javed Iqbal
 */
@Table(name = "PracticeQuestionAns")
public class PractiseQuestionAns {

    private Long practiceId;
    private Long orgUnitId;
    private Long qCId;
    private Long questionId;
    private Long seqNo;
    private String phoneNumber;
    private int pqaAnser;
    private Date endDate;
    private Long sessionId;
    private String bridgeKey;
    private String surveyStatusCode;
    private String source;
    private Date effectiveDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    @Column(name = "Practice_ID")
    public Long getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Long practiceId) {
        this.practiceId = practiceId;
    }

    @Column(name = "Org_Unit_ID")
    public Long getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Long orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    @Column(name = "QC_ID")
    public Long getqCId() {
        return qCId;
    }

    public void setqCId(Long qCId) {
        this.qCId = qCId;
    }
 @Column(name = "Question_ID")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "Patient_Phone_Number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "PQA_Ans")
    public int getPqaAnser() {
        return pqaAnser;
    }

    public void setPqaAnser(int pqaAnser) {
        this.pqaAnser = pqaAnser;
    }

    @Column(name = "End_Date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Session_ID")
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "Bridge_Key")
    public String getBridgeKey() {
        return bridgeKey;
    }

    public void setBridgeKey(String bridgeKey) {
        this.bridgeKey = bridgeKey;
    }

    @Column(name = "Survey_Status_Code")
    public String getSurveyStatusCode() {
        return surveyStatusCode;
    }

    public void setSurveyStatusCode(String surveyStatusCode) {
        this.surveyStatusCode = surveyStatusCode;
    }

    @Column(name = "Soruce")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "Effective_Date")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
