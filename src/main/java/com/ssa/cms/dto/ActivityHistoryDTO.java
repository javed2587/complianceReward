/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.Date;

/**
 *
 * @author Javed Iqbal
 */
public class ActivityHistoryDTO {

    private Integer activityNumber;
    private String activityName;
    private Integer rxNumber;
    private String rxNoSTR;
    private Date dateTime;
    private String dateTimeSTR;
    private String activityDetails;
    private String readingTime;

  

    public Integer getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(Integer activityNumber) {
        this.activityNumber = activityNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(Integer rxNumber) {
        this.rxNumber = rxNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getRxNoSTR() {
        return rxNoSTR;
    }

    public void setRxNoSTR(String rxNoSTR) {
        this.rxNoSTR = rxNoSTR;
    }

    public String getDateTimeSTR() {
        return dateTimeSTR;
    }

    public void setDateTimeSTR(String dateTimeSTR) {
        this.dateTimeSTR = dateTimeSTR;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }
     public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
}
