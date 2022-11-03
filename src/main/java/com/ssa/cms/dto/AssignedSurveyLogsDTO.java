/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.ArrayList;

/**
 *
 * @author Javed Iqbal
 */
public class AssignedSurveyLogsDTO {
       private Long id;
       private int profileId;
       private Long surveyId;
       private String readingTime;
  ArrayList<SurveyResponseDTO> surveyResponseList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public ArrayList<SurveyResponseDTO> getSurveyResponseList() {
        return surveyResponseList;
    }

    public void setSurveyResponseList(ArrayList<SurveyResponseDTO> surveyResponseList) {
        this.surveyResponseList = surveyResponseList;
    }
        public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
}
