package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author msheraz
 */
//@Entity
//@Table(name = "PharmacyLookup")
public class PharmacyLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String npi;

    private String passNumber;

    private String title;

    private String address;

    private String city;

    private State state;

    private BigInteger zip;

    private String territory;
    
    private String salesRep;

    private BigInteger createdBy;

    private Date createdOn;

    private BigInteger lastUpdatedBy;

    private Date lastUpdatedOn;

    private PharmacyType pharmacyType;

    private List<Pharmacy> pharmacyList;

    private boolean pharmacyExist;

    public PharmacyLookup() {
    }

    public PharmacyLookup(Integer id) {
        this.id = id;
    }

    public PharmacyLookup(Integer id, String npi, String passNumber, String title) {
        this.id = id;
        this.npi = npi;
        this.passNumber = passNumber;
        this.title = title;
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

    @Column(name = "NPI")
    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    @Column(name = "PassNumber")
    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "Zip")
    public BigInteger getZip() {
        return zip;
    }

    public void setZip(BigInteger zip) {
        this.zip = zip;
    }

    @Column(name = "Territory")
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Column(name = "SalesRep")
    public String getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
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

    //@JoinColumn(name = "PharmacyTypeId", referencedColumnName = "Id")
    //@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Transient
    public PharmacyType getPharmacyType() {
        return pharmacyType;
    }

    public void setPharmacyType(PharmacyType pharmacyType) {
        this.pharmacyType = pharmacyType;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacyLookup")
    @OrderBy(clause = "id asc")
    public List<Pharmacy> getPharmacyList() {
        return pharmacyList;
    }

    public void setPharmacyList(List<Pharmacy> pharmacyList) {
        this.pharmacyList = pharmacyList;
    }

    @Transient
    public boolean getPharmacyExist() {
        return pharmacyExist;
    }

    public void setPharmacyExist(boolean pharmacyExist) {
        this.pharmacyExist = pharmacyExist;
    }

}
