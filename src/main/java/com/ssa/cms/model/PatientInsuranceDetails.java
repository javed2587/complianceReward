/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.ssa.cms.modelinterfaces.CommonPatientInsuranceDetailFunctionlityI;
import com.ssa.cms.modellisteners.CommonPatientInsuranceDetailListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Haider Ali
 */
@Entity
@Table(name = "PatientInsuranceDetails")
@EntityListeners(CommonPatientInsuranceDetailListener.class)
public class PatientInsuranceDetails implements Serializable, CommonPatientInsuranceDetailFunctionlityI {

    private Long id;
    private PatientProfile patientProfile;
    private String memberID;
    private String insuranceProvider;
    private String groupNumber;
    private String planId;
    private String providerPhone;
    private String providerAddress;
    private Date expiryDate;
    private Date createdOn;
    private Date updatedOn;

    private String cardHolderRelationship;
    private String insuranceFrontCardPath;
    private String insuranceBackCardPath;
    private Integer isPrimary;
    private Integer isArchived;
    private Date effectiveDate;

    public PatientInsuranceDetails() {
    }

    public PatientInsuranceDetails(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Column(name = "MemberID")
    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    @Column(name = "InsuranceProvider")
    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    @Column(name = "GroupNumber")
    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Column(name = "PlanId")
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Column(name = "ProviderPhone")
    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    @Column(name = "ProviderAddress")
    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    @Column(name = "ExpiryDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "UpdatedOn")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "CardHolderRelationship")
    public String getCardHolderRelationship() {
        return cardHolderRelationship;
    }

    public void setCardHolderRelationship(String cardHolderRelationship) {
        this.cardHolderRelationship = cardHolderRelationship;
    }

    @Column(name = "InsuranceFrontCardPath")
    public String getInsuranceFrontCardPath() {
        return insuranceFrontCardPath;
    }

    public void setInsuranceFrontCardPath(String insuranceFrontCardPath) {
        this.insuranceFrontCardPath = insuranceFrontCardPath;
    }

    @Column(name = "InsuranceBackCardPath")
    public String getInsuranceBackCardPath() {
        return insuranceBackCardPath;
    }

    public void setInsuranceBackCardPath(String insuranceBackCardPath) {
        this.insuranceBackCardPath = insuranceBackCardPath;
    }

    @Column(name = "IS_PRIMARY")
    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Column(name = "IS_ARCHIVED")
    public Integer getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }

    @Column(name = "EffectiveDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Transient
    @Override
    public String getCopayFrontCardPath() {
        return "";
    }

    @Override
    public void setCopayFrontCardPath(String copayFrontCardPath) {

    }

    @Transient
    @Override
    public String getCopayBackCardPath() {
        return "";
    }

    @Override
    public void setCopayBackCardPath(String copayBackCardPath) {

    }

}
