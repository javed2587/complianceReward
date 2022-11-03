/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import com.ssa.cms.model.Order;
import com.ssa.cms.model.PatientProfile;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jandal
 */
public class PatientPointsDTO implements Serializable{
    private int id;
    private int orderid;
    private int patientid;
    private int enrollmentPoints;
    private PatientProfile patientprofile;
    private Order order;
    private int refillPoints;
    private int questionSubmittionPoints;
    private Date CreatedOn;
    private Date UpdateOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getEnrollmentPoints() {
        return enrollmentPoints;
    }

    public void setEnrollmentPoints(int enrollmentPoints) {
        this.enrollmentPoints = enrollmentPoints;
    }

    public int getRefillPoints() {
        return refillPoints;
    }

    public void setRefillPoints(int refillPoints) {
        this.refillPoints = refillPoints;
    }

    public int getQuestionSubmittionPoints() {
        return questionSubmittionPoints;
    }

    public void setQuestionSubmittionPoints(int questionSubmittionPoints) {
        this.questionSubmittionPoints = questionSubmittionPoints;
    }

    public PatientProfile getPatientprofile() {
        return patientprofile;
    }

    public void setPatientprofile(PatientProfile patientprofile) {
        this.patientprofile = patientprofile;
    }
    
 public Boolean EnrollmentCriteria(Order order)
 {
     System.out.print(order.getOrderStatus());
     return 10==order.getOrderStatus().getId();
 }
 public void AddPoint(int pointtype)
 {
     pointtype=pointtype+1;
 }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public Date getUpdateOn() {
        return UpdateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.UpdateOn = UpdateOn;
    }
    

}
