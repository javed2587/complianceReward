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
 * @author Jandal
 */
@Entity
@Table(name = "patientpoints")
public class PatientPoints implements Serializable {
    private int id;
    private PatientProfile patientProfile;
    private Order orders;
    private int enrollmentPoints;
    private int refillPoints;
    private int questionSubmittionPoints;
    private Date CreatedOn;
    private Date UpdateOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @JoinColumn(name = "OrderId", insertable = true, updatable = true)
    
    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
    
@Column(name = "EnrollmentPoint")
    public int getEnrollmentPoints() {
        return enrollmentPoints;
    }

    public void setEnrollmentPoints(int enrollmentPoints) {
        this.enrollmentPoints = enrollmentPoints;
    }
@Column(name = "RefillPoint")
    public int getRefillPoints() {
        return refillPoints;
    }

    public void setRefillPoints(int refillPoints) {
        this.refillPoints = refillPoints;
    }
@Column(name = "QuestionSubmittion")
    public int getQuestionSubmittionPoints() {
        return questionSubmittionPoints;
    }

    public void setQuestionSubmittionPoints(int questionSubmittionPoints) {
        this.questionSubmittionPoints = questionSubmittionPoints;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }
    
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "UpdateOn")
    public Date getUpdateOn() {
        return UpdateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.UpdateOn = UpdateOn;
    }
 public Boolean EnrollmentCriteria(Order order)
 {
     System.out.print(order.getOrderStatus());
     return 10==order.getOrderStatus().getId();
 }
 public int AddPoint(int pointtype)
 {
    return pointtype=pointtype+1;
 }

}