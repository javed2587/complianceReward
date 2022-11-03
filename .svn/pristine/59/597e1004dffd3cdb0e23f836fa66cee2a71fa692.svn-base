/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "survey_response_detail")
public class SurveyResponseAnswerDetial implements Serializable {

    private Long id;
    private Long questionId;
    private Long surveyResponseId;

    private Long questionOptionId;

    private String answer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "question_id")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "question_option_id")
    public Long getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(Long questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
        @Column(name = "survey_response_id")
    public Long getSurveyResponseId() {
        return surveyResponseId;
    }

    public void setSurveyResponseId(Long surveyResponseId) {
        this.surveyResponseId = surveyResponseId;
    }
}
