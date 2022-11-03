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
@Table(name="survey_response_detail")
public class SurveyRespDetail implements Serializable {
    private Long id;
    private String questionAnswer;
    private Date createdAt;
    private Date updatedAt;
    private SurveyQues surveyQuestion;
    private Long surveyQustionId;
    private SurveyResponse surveyResponse;
    private QuestionOption questionOption;
    private Integer patientId;

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="answer")
    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "question_id", insertable = true, updatable = true, nullable = true)
    @Transient
    public SurveyQues getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQues surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_response_id", insertable = true, updatable = true, nullable = true)
    public SurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_option_id", insertable = true, updatable = true, nullable = true)
    public QuestionOption getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }
    @Column(name = "question_id")
    public Long getSurveyQustionId() {
        return surveyQustionId;
    }

    public void setSurveyQustionId(Long surveyQustionId) {
        this.surveyQustionId = surveyQustionId;
    }
     @Column(name = "patient_Id")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

}
