/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.ssa.cms.modelinterfaces.CommonMessageFunctionalityI;
import com.ssa.cms.modellisteners.MessageListener;
import com.ssa.cms.util.JsonDateTimeSerializer;
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
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Haider Ali
 */
//@Entity
//@Table(name = "PatientNotification")
//@EntityListeners(MessageListener.class)
public class PatientNotification implements Serializable, CommonMessageFunctionalityI {

    private Integer id;
    private String message;
    private Date dateSent;
    private String phoneNumber;
    private PatientProfile patientProfile;
    private Boolean isRead;
    private Integer createdBy;
    private Date createdOn;
    private String sentBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @Column(name = "DateSent")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientProfileInfoId", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "IsRead")
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Column(name = "CreatedBy")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Transient
    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    @Transient
    @Override
    public String getMessageText() {
        return "";
    }

    @Override
    public void setMessageText(String s) {

    }

    @Override
    @Transient
    public String getSubject() {
        return "";
    }

    @Override
    public void setSubject(String subject) {

    }

    @Override
    @Transient
    public String getPushSubject() {
        return "";
    }

    @Override
    public void setPushSubject(String PushSubject) {

    }

}
