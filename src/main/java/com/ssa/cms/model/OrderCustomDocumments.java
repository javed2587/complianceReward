/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "OrderCustomDocuments")
public class OrderCustomDocumments implements Serializable {
    private int id;
    private Order order;
    private PatientProfile patientProfile;
    private String customDocuments;
    private String message;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
   @Column(name = "CustomDocument")
    public String getCustomDocuments() {
        return customDocuments;
    }

    public void setCustomDocuments(String customDocuments) {
        this.customDocuments = customDocuments;
    }
    @Column(name = "Message")
      public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
