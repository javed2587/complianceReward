package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author msheraz
 */
//@Entity
//@Table(name = "PharmacyFacilityOperation")
public class PharmacyFacilityOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String day;

    private String phoneHoursFrom;

    private String phoneHoursTo;

    private BigInteger createdBy;

    private Date createdOn;

    private BigInteger lastUpdatedBy;

    private Date lastUpdatedOn;

    private Pharmacy pharmacy;
    
    private boolean isSelected;
    
    
   

    public PharmacyFacilityOperation() {
    }

    public PharmacyFacilityOperation(Integer id) {
        this.id = id;
    }

    public PharmacyFacilityOperation(Integer id, String day) {
        this.id = id;
        this.day = day;
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

    @Basic(optional = false)
    @Column(name = "Day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Column(name = "PhoneHoursFrom")
    public String getPhoneHoursFrom() {
        return phoneHoursFrom;
    }

    public void setPhoneHoursFrom(String phoneHoursFrom) {
        this.phoneHoursFrom = phoneHoursFrom;
    }

    @Column(name = "PhoneHoursTo")
    public String getPhoneHoursTo() {
        return phoneHoursTo;
    }

    public void setPhoneHoursTo(String phoneHoursTo) {
        this.phoneHoursTo = phoneHoursTo;
    }

    @Column(name = "CreatedBy")
    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "LastUpdatedBy")
    public BigInteger getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PharmacyId")
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Column(name = "IsSelected")
    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
  

	 
 

}
