/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.List;

/**
 *
 * @author Jandal
 */
public class SurveyQuesDTO {
    private Long surveyQuestionId;
    private String questionCode;
    private String questionTitle;
    private String description;
    private Boolean isrequired;
    private Boolean displayInline;
    private String inputType;
    private Integer questionOrder;
    private SurveyQuestionTypeDTO questionType;
    private List<QuestionOptionDTO> optionList;
    private SurveyResponseAnswerDTO surveyResponseList;



    
    public Long getsurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setsurveyQuestionId(Long surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public Boolean getDisplayInline() {
        return displayInline;
    }

    public void setDisplayInline(Boolean displayInline) {
        this.displayInline = displayInline;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public SurveyQuestionTypeDTO getQuestionType() {
        return questionType;
    }

    public void setQuestionType(SurveyQuestionTypeDTO questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOptionDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<QuestionOptionDTO> optionList) {
        this.optionList = optionList;
    }

    public SurveyResponseAnswerDTO getSurveyResponseList() {
        return surveyResponseList;
    }

    public void setSurveyResponseList(SurveyResponseAnswerDTO surveyResponseList) {
        this.surveyResponseList = surveyResponseList;
    }


}
