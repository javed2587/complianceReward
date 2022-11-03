package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "ZipCodeCalculation")
public class ZipCodeCalculation implements Serializable {

    private Integer id;
    private Integer patientId;
    private Long miles;
    private String zip;
    private BigDecimal deliveryFee;
    private Integer deliveryPreferencesId;
    private Date createdOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PatientId")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Column(name = "Miles")
    public Long getMiles() {
        return miles;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }

    @Column(name = "DeliveryFee")
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Column(name = "DeliveryPreferencesId")
    public Integer getDeliveryPreferencesId() {
        return deliveryPreferencesId;
    }

    public void setDeliveryPreferencesId(Integer deliveryPreferencesId) {
        this.deliveryPreferencesId = deliveryPreferencesId;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "ZipCode")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
