package com.ssa.cms.model;
// Generated Apr 16, 2013 7:26:01 PM by Hibernate Tools 3.2.1.GA


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


/**
 * 
 * @author mzubair
 * RedemptionChannel table not exist in db
 */
//@Entity
//@Table(name="RedemptionChannel"
//    , uniqueConstraints = @UniqueConstraint(columnNames="RChannelTitle") 
//)
public class RedemptionChannel  implements java.io.Serializable {


     private int rchannelId;
     private String rchannelTitle;
     private String isActive;
     private String isDelete;
     private String createdBy;
     private Date createOn;
     private String lastModifiedBy;
     private Date lastModifiedOn;
     private Set<Campaigns> campaigns = new HashSet<Campaigns>(0);

    public RedemptionChannel() {
    }

	
    public RedemptionChannel(int rchannelId, String rchannelTitle, String isActive, String isDelete, String createdBy, Date createOn, String lastModifiedBy, Date lastModifiedOn) {
        this.rchannelId = rchannelId;
        this.rchannelTitle = rchannelTitle;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.createdBy = createdBy;
        this.createOn = createOn;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedOn = lastModifiedOn;
    }
    public RedemptionChannel(int rchannelId, String rchannelTitle, String isActive, String isDelete, String createdBy, Date createOn, String lastModifiedBy, Date lastModifiedOn, Set<Campaigns> campaigns) {
       this.rchannelId = rchannelId;
       this.rchannelTitle = rchannelTitle;
       this.isActive = isActive;
       this.isDelete = isDelete;
       this.createdBy = createdBy;
       this.createOn = createOn;
       this.lastModifiedBy = lastModifiedBy;
       this.lastModifiedOn = lastModifiedOn;
       this.campaigns = campaigns;
    }
   
//     @Id 
//    
//    @Column(name="RChannelId", unique=true, nullable=false)
    public int getRchannelId() {
        return this.rchannelId;
    }
    
    public void setRchannelId(int rchannelId) {
        this.rchannelId = rchannelId;
    }
    
    //@Column(name="RChannelTitle", unique=true, nullable=false, length=50)
    public String getRchannelTitle() {
        return this.rchannelTitle;
    }
    
    public void setRchannelTitle(String rchannelTitle) {
        this.rchannelTitle = rchannelTitle;
    }
    
//    @Column(name="IsActive", nullable=false, length=4)
    public String getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
 //   @Column(name="IsDelete", nullable=false, length=4)
    public String getIsDelete() {
        return this.isDelete;
    }
    
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    
 //   @Column(name="CreatedBy", nullable=false, length=20)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="CreateOn", nullable=false, length=19)
    public Date getCreateOn() {
        return this.createOn;
    }
    
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    
//    @Column(name="LastModifiedBy", nullable=false, length=20)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }
    
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="LastModifiedOn", nullable=false, length=19)
    public Date getLastModifiedOn() {
        return this.lastModifiedOn;
    }
    
    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="redemptionChannel")
    public Set<Campaigns> getCampaigns() {
        return this.campaigns;
    }
    
    public void setCampaigns(Set<Campaigns> campaigns) {
        this.campaigns = campaigns;
    }




}


