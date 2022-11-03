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
 * @author Jandal
 */
@Entity
@Table(name="patient_symptoms")
public class PatientSymptoms implements Serializable {
   private Long id;
   private AppointmentDetail appointmentDetail;
   private Symptoms symptom;
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
    @JoinColumn(name = "physician_appointment_id", nullable = false, insertable = true, updatable = true)
    public AppointmentDetail getAppointmentDetail() {
        return appointmentDetail;
    }

    public void setAppointmentDetail(AppointmentDetail appointmentDetail) {
        this.appointmentDetail = appointmentDetail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symptom_id", nullable = false, insertable = true, updatable = true)
    public Symptoms getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptoms symptom) {
        this.symptom = symptom;
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
