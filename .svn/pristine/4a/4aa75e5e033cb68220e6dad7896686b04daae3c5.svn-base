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
@Table(name = "patient_appointments")
public class PatientAppointment implements Serializable {
    private Integer id ;
    private PatientProfile profile;
    private AppointmentRequest request;
    private Practices physician;
    private String reasonToVist;
    private String appointmentDateTime;

    private Date cratedAt;
    private Date updatedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rquest_id")
    public AppointmentRequest getRequest() {
        return request;
    }

    public void setRequest(AppointmentRequest request) {
        this.request = request;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id")
    public Practices getPhysician() {
        return physician;
    }

    public void setPhysician(Practices physician) {
        this.physician = physician;
    }
   @Column(name = "reason_to_visit")
    public String getReasonToVist() {
        return reasonToVist;
    }

    public void setReasonToVist(String reasonToVist) {
        this.reasonToVist = reasonToVist;
    }
 @Column(name = "appointment_date_time")
    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "created_at")
    public Date getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(Date cratedAt) {
        this.cratedAt = cratedAt;
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
