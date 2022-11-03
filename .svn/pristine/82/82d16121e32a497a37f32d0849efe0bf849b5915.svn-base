package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
//@Entity
//@Table(name = "SurveyQuestionAssociation")
public class SurveyQuestionAssociation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Survey survey;
    private SurveyQuestion surveyQuestion;
    private Date CreatedOn;
    private Integer CreatedBy;
    private Integer lastModifiedBy;
    private Date lastModifiedOn;
    private Boolean questionAssociate;

    public SurveyQuestionAssociation() {
    }

    public SurveyQuestionAssociation(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SurveyId", nullable = false, insertable = true, updatable = true)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SurveyQuestionId", nullable = false)
    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    @Column(name = "CreatedOn", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    @Column(name = "CreatedBy", nullable = false)
    public Integer getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Integer CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    @Column(name = "LastModifiedBy", nullable = false)
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Column(name = "LastModifiedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    @Transient
    public Boolean getQuestionAssociate() {
        return questionAssociate;
    }

    public void setQuestionAssociate(Boolean questionAssociate) {
        this.questionAssociate = questionAssociate;
    }

}
