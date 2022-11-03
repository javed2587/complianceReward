package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
@Entity
@Table(name = "PharmacyUser")
public class PharmacyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String firstName;

    private String lastName;
    
    private String userName;

    private String email;

    private String password;

    private String phone;
    
    private String cellNo;

    private String role;

    private BigInteger createdBy;

    private Date createdOn;

    private BigInteger lastUpdatedBy;

    private Date lastUpdatedOn;

    private Pharmacy pharmacy;

    private String fullName;

    private String status;

    private Date LastLoggedOn;
    private String emailNotify;
    private String smsNotify;
    private Date passwordUpdatedOn;
    private Boolean active;
    private Users users;

    private RoleTypes roleTypes;
    
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date userStartDate;

    public PharmacyUser() {
    }

    public PharmacyUser(Integer id) {
        this.id = id;
    }

    public PharmacyUser(Integer id, String firstName, String lastName, String email, String phone, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
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

    @Basic(optional = false)
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic(optional = false)
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic(optional = false)
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic(optional = false)
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Deprecated
    @Basic(optional = false)
    @Column(name = "Role", nullable = true)
    public String getRole() {
        return role;
    }

    @Deprecated
    public void setRole(String role) {
        this.role = role;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PharmacyId")
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Transient
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "Status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "LastLoggedOn")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLoggedOn() {
        return LastLoggedOn;
    }

    public void setLastLoggedOn(Date LastLoggedOn) {
        this.LastLoggedOn = LastLoggedOn;
    }

    @Column(name = "EmailNotify")
    public String getEmailNotify() {
        return emailNotify;
    }

    public void setEmailNotify(String emailNotify) {
        this.emailNotify = emailNotify;
    }

    @Column(name = "SmsNotify")
    public String getSmsNotify() {
        return smsNotify;
    }

    public void setSmsNotify(String smsNotify) {
        this.smsNotify = smsNotify;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PasswordUpdatedOn")
    public Date getPasswordUpdatedOn() {
        return passwordUpdatedOn;
    }

    public void setPasswordUpdatedOn(Date passwordUpdatedOn) {
        this.passwordUpdatedOn = passwordUpdatedOn;
    }

    @Column(name = "IsActive")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Deprecated
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "UserId")
    public Users getUsers() {
        return users;
    }

    @Deprecated
    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", insertable = true, updatable = true)
    public RoleTypes getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(RoleTypes roleTypes) {
        this.roleTypes = roleTypes;
    }
    
    @Column(name = "CellNo", length = 11)
    public String getCellNo() {
        return this.cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }
    
    @Column(name = "UserName", length = 20)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UserStartDate", length = 19)
    public Date getUserStartDate() {
        return this.userStartDate;
    }

    public void setUserStartDate(Date userStartDate) {
        this.userStartDate = userStartDate;
    }

}
