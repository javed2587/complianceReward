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
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "Pharmacy_PtNotifications")
public class PharmacyPatientMessage implements Serializable {

    private Integer id;
    private Order order;
    private PatientProfile patientProfile;
    private String subject;
    private String message;
    private String reply;
    private String attachmentName;
    private String contentType;
    private String attachmentPath;
    private Boolean isRead;
    private Integer createdBy;
    private Date createdAt;
    private Date updatedAt;
    private Integer isCritical;
    private Integer pointsAwarded;
    private String sort;
    private String sentBy;
    private String sentOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId", nullable = false, insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
    @Column(name = "Subject", length = 255)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Column(name = "Message", length = 1000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     @Column(name = "Reply", length = 500)
    public String getReply() {
        return reply;
    }  
    public void setReply(String reply) {
        this.reply = reply;
    }
    @Column(name = "AttachmentName")
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
 @Transient
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
 @Transient
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
 @Transient
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
 @Transient
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
 @Transient
    public Integer getIsCritical() {
        return isCritical;
    }

    public void setIsCritical(Integer isCritical) {
        this.isCritical = isCritical;
    }
 @Transient
    public Integer getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(Integer pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }
// @Transactional
//    public String getSort() {
//        return sort;
//    }
//
//    public void setSort(String sort) {
//        this.sort = sort;
//    }
// @Transactional
//    public String getSentBy() {
//        return sentBy;
//    }
//
//    public void setSentBy(String sentBy) {
//        this.sentBy = sentBy;
//    }
// @Transactional
//    public String getSentOn() {
//        return sentOn;
//    }
//
//    public void setSentOn(String sentOn) {
//        this.sentOn = sentOn;
//    }
}
