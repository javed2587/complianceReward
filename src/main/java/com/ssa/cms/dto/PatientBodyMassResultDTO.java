/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.dto;

/**
 *
 * @author Javed
 */
public class PatientBodyMassResultDTO {
    
    private Integer bodyMassResultSeqNo;
    private Integer patientProfileSeqNo;
    private String height;
    private String weight;
    private String resultDate;
    private String pulse;
    private String bmi;
    private String startDate;
    private String endDate;
    private String rxNumber;
    private ComplianceRewardPointDTO rewardPointDTO;
    
//    public Date getDateCreatedOn() {
//        return dateCreatedOn;
//    }
//
//    public void setDateCreatedOn(Date dateCreatedOn) {
//        this.dateCreatedOn = dateCreatedOn;
//    }

//    public List<PatientBodyMassResult> getRecords() {
//        return records;
//    }
//
//    public void setRecords(List<PatientBodyMassResult> records) {
//        this.records = records;
//    }
    public Integer getBodyMassResultSeqNo() {
        return bodyMassResultSeqNo;
    }

    public void setBodyMassResultSeqNo(Integer bodyMassResultSeqNo) {
        this.bodyMassResultSeqNo = bodyMassResultSeqNo;
    }

    public Integer getPatientProfileSeqNo() {
        return patientProfileSeqNo;
    }

    public void setPatientProfileSeqNo(Integer patientProfile) {
        this.patientProfileSeqNo = patientProfile;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
    
    public String getBMI() {
        return bmi;
    }

    public void setBMI(String ethnicity) {
        this.bmi = ethnicity;
    }
   
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public ComplianceRewardPointDTO getRewardPointDTO() {
        return rewardPointDTO;
    }

    public void setRewardPointDTO(ComplianceRewardPointDTO rewardPointDTO) {
        this.rewardPointDTO = rewardPointDTO;
    }
    
}
