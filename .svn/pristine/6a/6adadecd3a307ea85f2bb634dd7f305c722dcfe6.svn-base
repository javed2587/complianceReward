package com.ssa.cms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;

//@Entity
//@Table(name = "WidgetUser")
public class WidgetUser implements java.io.Serializable {

    private Integer widgetUserId;
    @NotBlank(message = "Required")
    private String userName;
    @NotBlank(message = "Required")
    private String password;
    @NotNull
    private Integer campaignId;
    private String isActive;
    private String validateIp;
    private String createdBy;
    private Date createdOn;
    private String lastModifiedBy;
    private Date lastModifiedOn;
    private List<WidgetUserIpaddresses> widgetUserIpaddresseses = new ArrayList<WidgetUserIpaddresses>();
    String campaignName;

    public WidgetUser() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "WidgetUserId", unique = true, nullable = false)
    public Integer getWidgetUserId() {
        return this.widgetUserId;
    }

    public void setWidgetUserId(Integer widgetUserId) {
        this.widgetUserId = widgetUserId;
    }

    @Column(name = "UserName", nullable = false, length = 20)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "Password", nullable = false, length = 20)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "CampaignId", nullable = false)
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "ValidateIp", nullable = false, length = 4)
    public String getValidateIp() {
        return this.validateIp;
    }

    public void setValidateIp(String validateIp) {
        this.validateIp = validateIp;
    }

    @Column(name = "CreatedBy", nullable = false, length = 20)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn", nullable = false, length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "widgetUser")
    @OrderBy(clause = "widgetUserIpaddressId ASC")
    public List<WidgetUserIpaddresses> getWidgetUserIpaddresseses() {
        return widgetUserIpaddresseses;
    }

    public void setWidgetUserIpaddresseses(List<WidgetUserIpaddresses> widgetUserIpaddresseses) {
        this.widgetUserIpaddresseses = widgetUserIpaddresseses;
    }

    @Transient
    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

}
