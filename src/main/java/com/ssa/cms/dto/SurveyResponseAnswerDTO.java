/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;


import java.util.List;

/**
 *
 * @author Javed Iqbal
 */
public class SurveyResponseAnswerDTO {
    private Long questionId;
    private String inputType;
    private String questionType;
    private List<SruveyResponseDetialDTO>  surveyRespponseDetialList;



    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
      public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
        public List<SruveyResponseDetialDTO> getSurveyRespponseDetialList() {
        return surveyRespponseDetialList;
    }

    public void setSurveyRespponseDetialList(List<SruveyResponseDetialDTO> surveyRespponseDetialList) {
        this.surveyRespponseDetialList = surveyRespponseDetialList;
    }

  
   
}
