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

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "appointment_requests")
public class AppointmentRequest implements Serializable {
    private int id;
    private PatientProfile profile;
    private String insuranceUsageFlage;
    private String reasonForAppointment;
    private String statusOfAppointment;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    public PatientProfile getProfile() {
        return profile;
    }
    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }
@Column(name = "ins_usage_flage")
    public String getInsuranceUsageFlage() {
        return insuranceUsageFlage;
    }

    public void setInsuranceUsageFlage(String insuranceUsageFlage) {
        this.insuranceUsageFlage = insuranceUsageFlage;
    }
@Column(name = "reason_for_appointment")
    public String getReasonForAppointment() {
        return reasonForAppointment;
    }

    public void setReasonForAppointment(String reasonForAppointment) {
        this.reasonForAppointment = reasonForAppointment;
    }
@Column(name = "status_of_appointment")
    public String getStatusOfAppointment() {
        return statusOfAppointment;
    }

    public void setStatusOfAppointment(String statusOfAppointment) {
        this.statusOfAppointment = statusOfAppointment;
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
}
