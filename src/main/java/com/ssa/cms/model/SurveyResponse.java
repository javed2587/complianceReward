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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="survey_response")
public class SurveyResponse implements Serializable {
    
    private Long id;
    /// those two field might also be fetch from serveyQustion Object////
    private String questionTitle;
    private String questionType;
    private String inputType;
    private Date createdAt;
    private Date updatedAt;
    private SurveyQues surveyQuestion;
    private Survey2 survey;
    private Long surveyId;


    private AssignedSurvey assignSurvey;

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="question_title")
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Column(name="question_type")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
     @Column(name="input_type")
    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    @Column(name="created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", insertable = true, updatable = true, nullable = true)
    public SurveyQues getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQues surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }
    
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "survey_id", insertable = true, updatable = true, nullable = false)
    @Transient
    public Survey2 getSurvey() {
        return survey;
    }

    public void setSurvey(Survey2 survey) {
        this.survey = survey;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_log_id", insertable = true, updatable = true, nullable = false)
    public AssignedSurvey getAssignSurvey() {
        return assignSurvey;
    }

    public void setAssignSurvey(AssignedSurvey assignSurvey) {
        this.assignSurvey = assignSurvey;
    }
    @Column(name = "survey_id")
        public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
    
}
