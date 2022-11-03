package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
//@Entity
//@Table(name = "SurveyUserResponse")
public class SurveyUserResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SurveyId", nullable = false, insertable = true, updatable = true)
    private Survey survey;
    @Basic(optional = false)
    @Column(name = "UserId")
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QuestionId", nullable = false, insertable = true, updatable = true)
    private SurveyQuestion surveyQuestion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResponseId", nullable = false, insertable = true, updatable = true)
    private SurveyResponseDetail surveyResponseDetail;
    @Column(name = "CreatedOn", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedOn;
    @Column(name = "CreatedBy", nullable = false)
    private Integer CreatedBy;
    @Column(name = "LastModifiedBy", nullable = false)
    private Integer lastModifiedBy;
    @Column(name = "LastModifiedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public SurveyUserResponse() {
        
    }
    
    public SurveyUserResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    public SurveyResponseDetail getSurveyResponseDetail() {
        return surveyResponseDetail;
    }

    public void setSurveyResponseDetail(SurveyResponseDetail surveyResponseDetail) {
        this.surveyResponseDetail = surveyResponseDetail;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public Integer getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Integer CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    } 
}
