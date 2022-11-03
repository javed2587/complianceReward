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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author msheraz
 */
//@Entity
//@Table(name = "PharmacyType")
public class PharmacyType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String title;

    private BigInteger createdBy;

    private Date createdOn;

    private BigInteger lastUpdatedBy;

    private Date lastUpdatedOn;

    private List<PharmacyLookup> pharmacylookupList;
    
    public PharmacyType() {
    }

    public PharmacyType(Integer id) {
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

    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacyType")
    @OrderBy(clause = "id asc")
    public List<PharmacyLookup> getPharmacylookupList() {
        return pharmacylookupList;
    }

    public void setPharmacylookupList(List<PharmacyLookup> pharmacylookupList) {
        this.pharmacylookupList = pharmacylookupList;
    }

}
