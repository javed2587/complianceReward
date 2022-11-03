package com.ssa.cms.model;

import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "RoleTypes")
public class RoleTypes extends AuditFields implements java.io.Serializable {

    private Integer roleId;
    private Integer userId;
    private Integer brandId;
    @NotBlank(message = "Required")
    private String roleTitle;
    private String roleDescription;
    private String isActive;
    private String isDeleted;
    private List<Users> usersList;

    public RoleTypes() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "RoleId", unique = true, nullable = false)
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "UserId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "RoleTitle", length = 100)
    public String getRoleTitle() {
        return this.roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Column(name = "RoleDescription", length = 500)
    public String getRoleDescription() {
        return this.roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Column(name = "IsActive", length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDeleted", length = 4)
    public String getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "BrandId")
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleTypes")
    @Transient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
