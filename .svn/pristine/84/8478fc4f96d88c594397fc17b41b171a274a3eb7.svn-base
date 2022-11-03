package com.ssa.cms.model;
// Generated Apr 3, 2013 8:57:51 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AppResource")
public class AppResource extends AuditFields implements java.io.Serializable {

    private Integer resourceId;
    private String resourceTitle;
    private String description;
    private String resourceUrl;
    private String resourceType;
    private Boolean isActive;
    private String isDeleted;
    private boolean viewAllow;
    private boolean manageAllow;

    public AppResource() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ResourceID", unique = true, nullable = false)
    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Column(name = "ResourceTitle", length = 150)
    public String getResourceTitle() {
        return this.resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    @Column(name = "Description", length = 250)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ResourceURL", length = 250)
    public String getResourceUrl() {
        return this.resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Column(name = "ResourceType", length = 20)
    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "IsActive")
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDeleted", length = 4)
    public String getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Transient
    public boolean getViewAllow() {
        return viewAllow;
    }

    public void setViewAllow(boolean viewAllow) {
        this.viewAllow = viewAllow;
    }

    @Transient
    public boolean getManageAllow() {
        return manageAllow;
    }

    public void setManageAllow(boolean manageAllow) {
        this.manageAllow = manageAllow;
    }

}
