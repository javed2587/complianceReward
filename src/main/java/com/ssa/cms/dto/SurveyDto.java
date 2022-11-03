/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javed Iqbal
 */
public class SurveyDto {
    
    private String surveyBridgeId;
    private int surveyId;
    private String surveyLabel;
    private String surveyDetail;
    private List<surveyQuestionAnswerDTO> surveyQuestion;

     public String getSurveyBridgeId() {
        return surveyBridgeId;
    }

    public void setSurveyBridgeId(String surveyBridgeId) {
        this.surveyBridgeId = surveyBridgeId;
    }
    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyLabel() {
        return surveyLabel;
    }

    public void setSurveyLabel(String surveyLabel) {
        this.surveyLabel = surveyLabel;
    }

    public String getSurveyDetail() {
        return surveyDetail;
    }

    public void setSurveyDetail(String surveyDetail) {
        this.surveyDetail = surveyDetail;
    }

    public List<surveyQuestionAnswerDTO> getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(List<surveyQuestionAnswerDTO> surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }


}
