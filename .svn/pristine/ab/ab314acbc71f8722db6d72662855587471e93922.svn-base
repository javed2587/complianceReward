package com.ssa.cms.model;
// Generated Mar 28, 2013 7:45:08 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ShortCodes", uniqueConstraints
        = @UniqueConstraint(columnNames = "ShortCode"))
public class ShortCodes implements java.io.Serializable {

    private int shortCode;
    private Date expirationDate;
    private String vanityShortCode;
    private String isActive;
    private String isDelete;
    private String createdBy;
    private Date createOn;
    private String lastModifiedBy;
    private Date lastModifiedOn;
    private Set<Campaigns> campaignses = new HashSet<Campaigns>(0);

    public ShortCodes() {
    }

    @Id
    @Column(name = "ShortCode", unique = true, nullable = false)
    public int getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(int shortCode) {
        this.shortCode = shortCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ExpirationDate", nullable = false, length = 10)
    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Column(name = "VanityShortCode", nullable = false, length = 4)
    public String getVanityShortCode() {
        return this.vanityShortCode;
    }

    public void setVanityShortCode(String vanityShortCode) {
        this.vanityShortCode = vanityShortCode;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDelete", nullable = false, length = 4)
    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "CreatedBy", nullable = false, length = 20)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreateOn", nullable = false, length = 19)
    public Date getCreateOn() {
        return this.createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "LastModifiedBy", nullable = false, length = 20)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastModifiedOn", nullable = false, length = 19)
    public Date getLastModifiedOn() {
        return this.lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shortCodes")
    public Set<Campaigns> getCampaignses() {
        return this.campaignses;
    }

    public void setCampaignses(Set<Campaigns> campaignses) {
        this.campaignses = campaignses;
    }

}
