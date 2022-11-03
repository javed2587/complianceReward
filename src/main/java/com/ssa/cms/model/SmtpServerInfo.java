package com.ssa.cms.model;

import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "SmtpServerInfo", uniqueConstraints = @UniqueConstraint(columnNames = "FromEmail"))
public class SmtpServerInfo extends AuditFields implements java.io.Serializable {

    private Integer smtpId;
    @NotBlank(message = "Required")
    private String fromEmail;
    @NotBlank(message = "Required")
    private String fromName;
    @NotBlank(message = "Required")
    private String outGoingSmtp;
    @NotBlank(message = "Required")
    private String smtpPort;
    @NotBlank(message = "Required")
    private String smtpUserName;
    @NotBlank(message = "Required")
    private String smtpPassword;
    private String isActive;
    private String isDelete;
    private Set<Campaigns> campaignses = new HashSet<>(0);
    private String brandIds;
    private List<String> brandName;
    private Integer[] selectedBrands;

    public SmtpServerInfo() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "SmtpId", unique = true, nullable = false)
    public Integer getSmtpId() {
        return this.smtpId;
    }

    public void setSmtpId(Integer smtpId) {
        this.smtpId = smtpId;
    }

    @Column(name = "FromEmail", unique = true, nullable = false, length = 100)
    public String getFromEmail() {
        return this.fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Column(name = "OutGoingSmtp", nullable = false, length = 50)
    public String getOutGoingSmtp() {
        return this.outGoingSmtp;
    }

    public void setOutGoingSmtp(String outGoingSmtp) {
        this.outGoingSmtp = outGoingSmtp;
    }

    @Column(name = "SmtpPort", nullable = false, length = 5)
    public String getSmtpPort() {
        return this.smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    @Column(name = "SmtpUserName", nullable = false, length = 20)
    public String getSmtpUserName() {
        return this.smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    @Column(name = "SmtpPassword", nullable = false, length = 20)
    public String getSmtpPassword() {
        return this.smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smtpServerInfo")
    public Set<Campaigns> getCampaignses() {
        return this.campaignses;
    }

    public void setCampaignses(Set<Campaigns> campaignses) {
        this.campaignses = campaignses;
    }

    @Column(name = "FromName", nullable = false, length = 250)
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    @Column(name = "brandIds", length = 250)
    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    @Transient
    public Integer[] getSelectedBrands() {
        return selectedBrands;
    }

    public void setSelectedBrands(Integer[] selectedBrands) {
        this.selectedBrands = selectedBrands;
    }

    @Transient
    public List<String> getBrandName() {
        return brandName;
    }

    public void setBrandName(List<String> brandName) {
        this.brandName = brandName;
    }
}
