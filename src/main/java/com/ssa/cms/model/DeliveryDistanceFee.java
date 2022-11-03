package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "DeliveryDistanceFee")
public class DeliveryDistanceFee extends AuditFields implements Serializable {

    private Integer id;
    private PharmacyZipCodes pharmacyZipCodes;
    private DeliveryDistances deliveryDistances;
    private DeliveryPreferences deliveryPreferenceses;
    private BigDecimal deliveryFee;
    private String description;
    private BigDecimal fee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "PharmacyId", insertable = true, updatable = true)
    @Transient
    public PharmacyZipCodes getPharmacyZipCodes() {
        return pharmacyZipCodes;
    }

    public void setPharmacyZipCodes(PharmacyZipCodes pharmacyZipCodes) {
        this.pharmacyZipCodes = pharmacyZipCodes;
    }

    @Column(name = "DeliveryFee")
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "DeliveryPreferencesId", insertable = true, updatable = true)
    @Transient
    public DeliveryPreferences getDeliveryPreferenceses() {
        return deliveryPreferenceses;
    }

    public void setDeliveryPreferenceses(DeliveryPreferences deliveryPreferenceses) {
        this.deliveryPreferenceses = deliveryPreferenceses;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "DeliveryDistanceId", insertable = true, updatable = true)
    @Transient
    public DeliveryDistances getDeliveryDistances() {
        return deliveryDistances;
    }

    public void setDeliveryDistances(DeliveryDistances deliveryDistances) {
        this.deliveryDistances = deliveryDistances;
    }

    @Transient
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

}
