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
@Table(name = "messages")
public class MessageThreads implements Serializable {

    private Integer id;
    private Practices practice;
    private PatientProfile profile;
    private Long questionId;
    private Order order;
    private String messgeStaus;
    private Boolean markAsRead;
    private String attachment;
    private String messge;
    private Date createdAt;
    private Date updateAt;
    private String messsgeType;
    private String physicianName;
    private int physicianId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PracticeId", nullable = true, insertable = true, updatable = true)
    public Practices getPractice() {
        return practice;
    }

    public void setPractice(Practices practice) {
        this.practice = practice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = true, insertable = true, updatable = true)
    public PatientProfile getProfile() {
        return profile;
    }

    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId", nullable = true, insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "message_status")
    public String getMessgeStaus() {
        return messgeStaus;
    }

    public void setMessgeStaus(String messgeStaus) {
        this.messgeStaus = messgeStaus;
    }
//@Transient

    @Column(name = "mark_as_read")
    public Boolean getMarkAsRead() {
        return markAsRead;
    }

    public void setMarkAsRead(Boolean markAsRead) {
        this.markAsRead = markAsRead;
    }

    @Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Column(name = "Message")
    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
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
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Column(name = "questionId")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "message_type")
    public String getMesssgeType() {
        return messsgeType;
    }

    public void setMesssgeType(String messsgeType) {
        this.messsgeType = messsgeType;
    }

    @Column(name = "physician_name")
    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    @Column(name = "physicianId")
    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }
}
