package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "DrugSearches")
public class DrugSearches implements Serializable {

    private Integer id;
    private PatientProfile patientProfile;
    private Drug drug;
    private String genericName;
    private String drugBrandName;
    private String drugType;
    private String strength;
    private String qty;
    private Double drugPrice;
    private Date createdOn;
    private DrugDetail drugDetail;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Column(name = "DrugType")
    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    @Column(name = "Quantity")
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Column(name = "DrugPrice")
    public Double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Double drugPrice) {
        this.drugPrice = drugPrice;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "Strength")
    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "DrugId", nullable = false, insertable = true, updatable = true)
    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Column(name = "DrugGenericName")
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Column(name = "DrugBrandName")
    public String getDrugBrandName() {
        return drugBrandName;
    }

    public void setDrugBrandName(String drugBrandName) {
        this.drugBrandName = drugBrandName;
    }
    
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="DrugNdc")
    public DrugDetail getDrugDetail() {
        return drugDetail;
    }

    public void setDrugDetail(DrugDetail drugDetail) {
        this.drugDetail = drugDetail;
    }

}
