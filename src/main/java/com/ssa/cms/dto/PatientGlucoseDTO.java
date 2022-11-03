/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.dto;

import java.util.Date;

/**
 *
 * @author Javed
 */
public class PatientGlucoseDTO {
     private Integer id;
    private Integer patientProfileSeqNo;
    private String glucoseLevel;
    private String readingTime;
    private String isFasting;
    private Date createdOn;
    private String rxNumber;
    private ComplianceRewardPointDTO rewardDTO;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPatientProfileSeqNo() {
        return patientProfileSeqNo;
    }

    public void setPatientProfileSeqNo(Integer patientProfileSeqNo) {
        this.patientProfileSeqNo = patientProfileSeqNo;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

 public String getIsFasting() {
        return isFasting;
    }

    public void setIsFasting(String isFasting) {
        this.isFasting = isFasting;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public ComplianceRewardPointDTO getRewardDTO() {
        return rewardDTO;
    }

    public void setRewardDTO(ComplianceRewardPointDTO rewardDTO) {
        this.rewardDTO = rewardDTO;
    }
    
}
