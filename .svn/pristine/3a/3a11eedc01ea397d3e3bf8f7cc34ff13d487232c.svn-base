/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author arsalan.ahmad
 */
@Entity
@Table(name = "MessageResponses")
public class MessageResponses implements Serializable {

    private Integer id;
    private String responseDescription;
    private PatientProfile patientProfile;
    private NotificationMessages notificationMessages;
    private Order order;
    private Date createdOn;

    public MessageResponses() {
    }

    public MessageResponses(String responseDescription, PatientProfile patientProfile, NotificationMessages notificationMessages, Order order) {
        this.responseDescription = responseDescription;
        this.patientProfile = patientProfile;
        this.notificationMessages = notificationMessages;
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ResDescription")
    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MessageId", insertable = true, updatable = true)
    public NotificationMessages getNotificationMessages() {
        return notificationMessages;
    }

    public void setNotificationMessages(NotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId", insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
