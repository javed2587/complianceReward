/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.Date;

/**
 *
 * @author Umar
 */
public class PatientDependantDTO {

    private String memberId;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String allergies;
    private String dermatologist;
    private Integer id;
    private String createdOn;
    private Integer patientId;
    private Boolean isAdult;
    private Boolean isApproved;
    private String frontPOAImage;
    private String backPOAImage;
    private String state;
    private String expiryDate;
    private String email;
    private String fullName;
    private String comments;
    private Date birthDate;
    private Date createdDate;
    private Long rxCount;
    private String fullPatientName;
    private String approvalStr;
    private String disabledStr;
    private Date poaApprovalDate;
    private Date poaExpirationDate;
    private int pharmacyQueueParam;
    private Integer disapprove;
    private Integer archived;

    public PatientDependantDTO() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(String dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getFrontPOAImage() {
        return frontPOAImage;
    }

    public void setFrontPOAImage(String frontPOAImage) {
        this.frontPOAImage = frontPOAImage;
    }

    public String getBackPOAImage() {
        return backPOAImage;
    }

    public void setBackPOAImage(String backPOAImage) {
        this.backPOAImage = backPOAImage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getRxCount() {
        return rxCount;
    }

    public void setRxCount(Long rxCount) {
        this.rxCount = rxCount;
    }

    public String getFullPatientName() {
        return fullPatientName;
    }

    public void setFullPatientName(String fullPatientName) {
        this.fullPatientName = fullPatientName;
    }

    public String getApprovalStr() {
        return approvalStr;
    }

    public void setApprovalStr(String approvalStr) {
        this.approvalStr = approvalStr;
    }

    public String getDisabledStr() {
        return disabledStr;
    }

    public void setDisabledStr(String disabledStr) {
        this.disabledStr = disabledStr;
    }

    public Date getPoaApprovalDate() {
        return poaApprovalDate;
    }

    public void setPoaApprovalDate(Date poaApprovalDate) {
        this.poaApprovalDate = poaApprovalDate;
    }

    public Date getPoaExpirationDate() {
        return poaExpirationDate;
    }

    public void setPoaExpirationDate(Date poaExpirationDate) {
        this.poaExpirationDate = poaExpirationDate;
    }

    public int getPharmacyQueueParam() {
        return pharmacyQueueParam;
    }

    public void setPharmacyQueueParam(int pharmacyQueueParam) {
        this.pharmacyQueueParam = pharmacyQueueParam;
    }

    public Integer getDisapprove() {
        return disapprove;
    }

    public void setDisapprove(Integer disapprove) {
        this.disapprove = disapprove;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }
    
}
