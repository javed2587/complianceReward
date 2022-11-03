package com.ssa.cms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Industry", uniqueConstraints
        = @UniqueConstraint(columnNames = "IndustryTitle"))
public class Industry extends AuditFields implements java.io.Serializable {

    private Integer industryId;
    @NotBlank(message = "Required")
    private String industryTitle;
    private String isActive;
    private String isDelete;
    private Set<Campaigns> campaignses = new HashSet<Campaigns>(0);

    public Industry() {
    }

    public Industry(String industryTitle, String isActive, String isDelete, String createdBy, Date createOn, String lastModifiedBy, Date lastModifiedOn) {
        this.industryTitle = industryTitle;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

    public Industry(String industryTitle, String isActive, String isDelete, String createdBy, Date createOn, String lastModifiedBy, Date lastModifiedOn, Set<Campaigns> campaignses) {
        this.industryTitle = industryTitle;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.campaignses = campaignses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IndustryId", unique = true, nullable = false)
    public Integer getIndustryId() {
        return this.industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    @Column(name = "IndustryTitle", unique = true, nullable = false, length = 100)
    public String getIndustryTitle() {
        return this.industryTitle;
    }

    public void setIndustryTitle(String industryTitle) {
        this.industryTitle = industryTitle;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "industry")
    public Set<Campaigns> getCampaignses() {
        return this.campaignses;
    }

    public void setCampaignses(Set<Campaigns> campaignses) {
        this.campaignses = campaignses;
    }
}
