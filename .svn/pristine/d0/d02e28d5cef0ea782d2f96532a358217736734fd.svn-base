package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Users")
public class Users extends AuditFields implements Serializable {

    private Integer userId;
    @NotBlank(message = "Required")
    private String userName;
    private String userPassword;
    @NotBlank(message = "Required")
    private String firstName;
    @NotBlank(message = "Required")
    private String lastName;
    private String telNo;
    private String cellNo;
    @NotBlank(message = "Required")
    private String emailAddress;
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date userStartDate;
    private String passwordSource;
    private Date userEndDate;
    private Date lastUsageDate;
    private String remarks;
    private Date userActiveDate;
    private String isActive;
    private int parentId;
    private RoleTypes roleTypes;
    private Integer[] selectedBrands;
    private Integer[] selectedCampaigns;
    private String confirmPassword;
    private String chkValue;
    private Boolean isAdmin;
    private Integer pharmacyId;
    private List<PharmacyUser> pharmacyUsersList;

    public Users(String isActive) {
        this.isActive = isActive;
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "UserId", unique = true, nullable = false)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "UserName", length = 20)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "UserPassword", length = 128)
    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "FirstName", length = 20)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LastName", length = 15)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "TelNo", length = 11)
    public String getTelNo() {
        return this.telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Column(name = "CellNo", length = 11)
    public String getCellNo() {
        return this.cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    @Column(name = "EmailAddress", length = 50)
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UserStartDate", length = 19)
    public Date getUserStartDate() {
        return this.userStartDate;
    }

    public void setUserStartDate(Date userStartDate) {
        this.userStartDate = userStartDate;
    }

    @Column(name = "PasswordSource", length = 2)
    public String getPasswordSource() {
        return this.passwordSource;
    }

    public void setPasswordSource(String passwordSource) {
        this.passwordSource = passwordSource;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UserEndDate", length = 19)
    public Date getUserEndDate() {
        return this.userEndDate;
    }

    public void setUserEndDate(Date userEndDate) {
        this.userEndDate = userEndDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastUsageDate", length = 19)
    public Date getLastUsageDate() {
        return this.lastUsageDate;
    }

    public void setLastUsageDate(Date lastUsageDate) {
        this.lastUsageDate = lastUsageDate;
    }

    @Column(name = "Remarks", length = 500)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UserActiveDate", length = 19)
    public Date getUserActiveDate() {
        return this.userActiveDate;
    }

    public void setUserActiveDate(Date userActiveDate) {
        this.userActiveDate = userActiveDate;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "ParentId", nullable = false)
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Transient
    public Integer[] getSelectedBrands() {
        return selectedBrands;
    }

    public void setSelectedBrands(Integer[] selectedBrands) {
        this.selectedBrands = selectedBrands;
    }

    @Transient
    public Integer[] getSelectedCampaigns() {
        return selectedCampaigns;
    }

    public void setSelectedCampaigns(Integer[] selectedCampaigns) {
        this.selectedCampaigns = selectedCampaigns;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Transient
    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", insertable = true, updatable = true)
    public RoleTypes getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(RoleTypes roleTypes) {
        this.roleTypes = roleTypes;
    }

    @Column(name = "AccessToPharmacy", nullable = false)
    public Boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Transient
    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch = FetchType.LAZY)
    @Transient
    public List<PharmacyUser> getPharmacyUsersList() {
        return pharmacyUsersList;
    }

    public void setPharmacyUsersList(List<PharmacyUser> pharmacyUsersList) {
        this.pharmacyUsersList = pharmacyUsersList;
    }

}
