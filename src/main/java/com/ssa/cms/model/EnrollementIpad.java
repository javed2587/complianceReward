/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "enrollments")
public class EnrollementIpad implements Serializable {

    private Integer id;
    private PatientProfile profile;
    private String emialBody;
    private String uniqueKey;
    private String enrollemtStatus;
    private String preferMethodContact;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = true, updatable = true)
    public PatientProfile getProfile() {
        return profile;
    }

    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }

    @Column(name = "email_body")
    public String getEmialBody() {
        return emialBody;
    }

    public void setEmialBody(String emialBody) {
        this.emialBody = emialBody;
    }

    @Column(name = "unique_key")
    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Column(name = "enrollment_status")
    public String getEnrollemtStatus() {
        return enrollemtStatus;
    }

    public void setEnrollemtStatus(String enrollemtStatus) {
        this.enrollemtStatus = enrollemtStatus;
    }

    @Column(name = "preffered_method_contact")
    public String getPreferMethodContact() {
        return preferMethodContact;
    }

    public void setPreferMethodContact(String preferMethodContact) {
        this.preferMethodContact = preferMethodContact;
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
    @Column(name = "deleted_at")
        public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
