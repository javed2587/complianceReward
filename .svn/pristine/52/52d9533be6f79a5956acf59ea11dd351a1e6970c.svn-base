package com.ssa.cms.model;
// Generated Jul 26, 2013 1:25:38 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

@Entity
@Table(name = "CampaignTrigger", uniqueConstraints = @UniqueConstraint(columnNames = "Keyword")
)
public class CampaignTrigger implements java.io.Serializable {
    @Valid
    private CampaignTriggerId id;
    private Campaigns campaigns;
    private String isActive;
    private String isDelete;
    private String createdBy;
    private Date createOn;
    private String lastModifiedBy;
    private Date lastModifiedOn;
    int remove;

    public CampaignTrigger() {
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "triggerId", column = @Column(name = "TriggerId", nullable = false)),
        @AttributeOverride(name = "campaignId", column = @Column(name = "CampaignId", nullable = false)),
        @AttributeOverride(name = "keyword", column = @Column(name = "Keyword", unique = true, nullable = false, length = 10))})
    public CampaignTriggerId getId() {
        if(this.id != null && this.id.getCampaignId() == 0) {
            if(campaigns != null && campaigns.getCampaignId() != null) {
                this.id.setCampaignId(campaigns.getCampaignId());
            }
        }
        return this.id;
    }

    public void setId(CampaignTriggerId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CampaignId", nullable = false, insertable = false, updatable = false)
    public Campaigns getCampaigns() {
        return this.campaigns;
    }

    public void setCampaigns(Campaigns campaigns) {
        this.campaigns = campaigns;
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

    @Transient
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }
}
