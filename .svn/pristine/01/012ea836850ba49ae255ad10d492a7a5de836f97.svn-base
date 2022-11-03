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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "reporter_chat_sessions")
public class ReporterChatSession implements Serializable {
    private Integer id;
    private Integer reporterId;
     private Integer patientId;
     private Integer sessionStatus;
     private Date createdAt;
     private Date updateAt;

   

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
@Column(name = "reporter_id")
    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }
@Column(name = "patient_id")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
@Column(name = "session_status")
    public Integer getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(Integer sessionStatus) {
        this.sessionStatus = sessionStatus;
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
}
