/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Haider Ali
 */
public class BaseDTO implements Serializable {

    private Date fromDate;
    private Date toDate;
    private String phoneNumber;
    private Integer patientId;
    private String status;
    private String selectedTab;
    private Integer dependentId;
    private Date expiringSoonDate;
    private String patientName;
    private String email;
    private String orderId;
    private Integer practiceId;
    private String question;
    private Integer notificationMsgId;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public Integer getDependentId() {
        return dependentId;
    }

    public void setDependentId(Integer dependentId) {
        this.dependentId = dependentId;
    }

    public Date getExpiringSoonDate() {
        return expiringSoonDate;
    }

    public void setExpiringSoonDate(Date expiringSoonDate) {
        this.expiringSoonDate = expiringSoonDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Integer practiceId) {
        this.practiceId = practiceId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getNotificationMsgId() {
        return notificationMsgId;
    }

    public void setNotificationMsgId(Integer notificationMsgId) {
        this.notificationMsgId = notificationMsgId;
    }

}
