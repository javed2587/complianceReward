/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Saber
 */
//@Entity
//@Table(name = "DeliveryPreferences")
public class DeliveryPreferences extends AuditFields implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;

    private List<PatientProfile> patientProfileInfoList;
    private List<DeliveryPreferences> deliveryPreferencesList;
    private List<DeliveryDistanceFee> deliveryDistanceFees;
    private List<TransferRequest> transferRequestList;
    private List<PatientDeliveryAddress> patientDeliveryAddresList;
    private BigDecimal cost;
    private boolean pickedFromPharmacy;
    private Integer seqNo;
    
    public DeliveryPreferences() {
    }

    public DeliveryPreferences(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO @OneToMany(mappedBy = "deliveryPreferenceId")
    @Transient
    public List<PatientProfile> getPatientProfileInfoList() {
        return patientProfileInfoList;
    }

    public void setPatientProfileInfoList(List<PatientProfile> patientProfileInfoList) {
        this.patientProfileInfoList = patientProfileInfoList;
    }

    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryPreferences)) {
            return false;
        }
        DeliveryPreferences other = (DeliveryPreferences) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "DeliveryPreferences[ id=" + id + " ]";
    }

    @Transient
    public List<DeliveryPreferences> getDeliveryPreferencesList() {
        return deliveryPreferencesList;
    }

    public void setDeliveryPreferencesList(List<DeliveryPreferences> deliveryPreferencesList) {
        this.deliveryPreferencesList = deliveryPreferencesList;
    }

    //TODO @OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryPreferenceses")
    @Transient
    public List<DeliveryDistanceFee> getDeliveryDistanceFees() {
        return deliveryDistanceFees;
    }

    public void setDeliveryDistanceFees(List<DeliveryDistanceFee> deliveryDistanceFees) {
        this.deliveryDistanceFees = deliveryDistanceFees;
    }

    //TODO @OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryPreferences")
    @Transient
    public List<TransferRequest> getTransferRequestList() {
        return transferRequestList;
    }

    public void setTransferRequestList(List<TransferRequest> transferRequestList) {
        this.transferRequestList = transferRequestList;
    }

    @Transient
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    //TODO @OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryPreferences")
    @Transient
    public List<PatientDeliveryAddress> getPatientDeliveryAddresList() {
        return patientDeliveryAddresList;
    }

    public void setPatientDeliveryAddresList(List<PatientDeliveryAddress> patientDeliveryAddresList) {
        this.patientDeliveryAddresList = patientDeliveryAddresList;
    }

    @Column(name = "PickedFromPharmacy")
    public boolean isPickedFromPharmacy() {
        return pickedFromPharmacy;
    }

    public void setPickedFromPharmacy(boolean pickedFromPharmacy) {
        this.pickedFromPharmacy = pickedFromPharmacy;
    }

    @Column(name = "seqNo")
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    
    

    
}
