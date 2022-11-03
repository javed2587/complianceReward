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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="physician_appointment")
public class AppointmentDetail implements Serializable {
    private Long id;
    private Physician physician;
    private PatientProfile patient;
    private ChildInfo appointmentFor;
    private PatientInsuranceDetails insuranceDetail;
    private String detail;
    private Date appointmentDate;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id", insertable = true, updatable = true) 
    public Physician getDoctor() {
        return physician;
    }

    public void setDoctor(Physician physician) {
        this.physician = physician;
    }

    @Column(name="appointment_date_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatient() {
        return patient;
    }

    public void setPatient(PatientProfile patient) {
        this.patient = patient;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_for", nullable = false, insertable = true, updatable = true)
    public ChildInfo getAppointmentFor() {
        return appointmentFor;
    }

    public void setAppointmentFor(ChildInfo appointmentFor) {
        this.appointmentFor = appointmentFor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_card_id", nullable = false, insertable = true, updatable = true)
    public PatientInsuranceDetails getInsuranceDetail() {
        return insuranceDetail;
    }

    public void setInsuranceDetail(PatientInsuranceDetails insuranceDetail) {
        this.insuranceDetail = insuranceDetail;
    }

    @Column(name="detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name="created_on")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="updated_on")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    
}
