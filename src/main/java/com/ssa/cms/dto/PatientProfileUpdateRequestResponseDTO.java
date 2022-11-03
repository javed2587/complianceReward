/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.math.BigDecimal;

/**
 *
 * @author ISPIN
 */
public class PatientProfileUpdateRequestResponseDTO {
    
    /**
     * Request Attributes
     */
    private Integer     patientProfileId;
    
    /**
     * Allergies
     */
    private String      allergies;
    
    /**
     * health
     */
    private Integer     patientHealthId;
    private Integer     height;
    private Integer     inch;
    private Integer     weigth;
    private Double      bmi ;
    
    /**
     * response Attributes
     */
    
    private String status;
    private Integer statuscode;
    
    private String errorMessage;
    
    private String phoneNumber;
    
    private String message;

    public PatientProfileUpdateRequestResponseDTO() {
    }

    public Integer getPatientProfileId() {
        return patientProfileId;
    }

    public void setPatientProfileId(Integer patientProfileId) {
        this.patientProfileId = patientProfileId;
    }
    
    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getInch() {
        return inch;
    }

    public void setInch(Integer inch) {
        this.inch = inch;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Integer getPatientHealthId() {
        return patientHealthId;
    }

    public void setPatientHealthId(Integer patientHealthId) {
        this.patientHealthId = patientHealthId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}


