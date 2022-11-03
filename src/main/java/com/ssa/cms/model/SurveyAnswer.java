/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "SurveyAnswer")
public class SurveyAnswer implements Serializable {
    
  
    private int surveyId;
    private String answerLabel;
    private SurveyQuestion surveyQuestion;
    private Date effectiveDate;
    private Date modifiedDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
    @Column(name = "Answer")
    public String getAnswerLabel() {
        return answerLabel;
    }

    public void setAnswerLabel(String answerLabel) {
        this.answerLabel = answerLabel;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_SurvQuestion_ID")
   public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }
    @Column(name = "Effective_Date")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
 @Column(name = "Date_Modified")
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
  
    
}
