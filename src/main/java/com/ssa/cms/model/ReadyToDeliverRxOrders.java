/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "ReadyToDeliverRxOrders")
public class ReadyToDeliverRxOrders implements java.io.Serializable {

    private Integer id;
    private PatientProfile patientProfile;
    private DeliveryPreferences deliveryPreferences;
    private PatientDeliveryAddress patientDeliveryAddress;
    private Boolean isShipped;
    private Date postedDate;
    private Date shippedDate;
    private List<Order> listOfOrders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "DeliveryPreferenceId", insertable = true, updatable = true)
    @Transient
    public DeliveryPreferences getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(DeliveryPreferences deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DeliveryAddressId", insertable = true, updatable = true)
    public PatientDeliveryAddress getPatientDeliveryAddress() {
        return patientDeliveryAddress;
    }

    public void setPatientDeliveryAddress(PatientDeliveryAddress patientDeliveryAddress) {
        this.patientDeliveryAddress = patientDeliveryAddress;
    }

    @Column(name = "IsShipped")
    public Boolean getIsShipped() {
        return isShipped;
    }

    public void setIsShipped(Boolean isShipped) {
        this.isShipped = isShipped;
    }

    @Column(name = "PostedDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Column(name = "ShippedDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "readyToDeliverRxOrders")
    @Transient
    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

}
