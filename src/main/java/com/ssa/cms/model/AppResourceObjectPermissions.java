package com.ssa.cms.model;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AppResourceObjectPermissions")
public class AppResourceObjectPermissions extends AuditFields implements java.io.Serializable {

    private AppResourceObjectPermissionsId id;
    private Boolean allowView;
    private Boolean allowManage;
    private List<AppResource> resources;

    public AppResourceObjectPermissions() {
    }

    public AppResourceObjectPermissions(AppResourceObjectPermissionsId id) {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "resourcePermission", column = @Column(name = "ResourcePermission", nullable = false, length = 20)),
        @AttributeOverride(name = "roleId", column = @Column(name = "RoleID", nullable = false)),
        @AttributeOverride(name = "resourceId", column = @Column(name = "ResourceID", nullable = false))})
    public AppResourceObjectPermissionsId getId() {
        return this.id;
    }

    public void setId(AppResourceObjectPermissionsId id) {
        this.id = id;
    }

    @Column(name = "AllowView")
    public Boolean isAllowView() {
        return allowView;
    }

    public void setAllowView(Boolean allowView) {
        this.allowView = allowView;
    }

    @Column(name = "AllowManage")
    public Boolean isAllowManage() {
        return allowManage;
    }

    public void setAllowManage(Boolean allowManage) {
        this.allowManage = allowManage;
    }

    @Transient
    public List<AppResource> getResources() {
        return resources;
    }

    public void setResources(List<AppResource> resources) {
        this.resources = resources;
    }
}
