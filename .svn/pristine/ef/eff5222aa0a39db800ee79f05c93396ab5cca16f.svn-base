package com.ssa.cms.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppResourceObjectPermissionsId implements java.io.Serializable {

    private int roleId;
    private int resourceId;

    public AppResourceObjectPermissionsId() {
    }

    @Column(name = "RoleID", nullable = false)
    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "ResourceID", nullable = false)
    public int getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof AppResourceObjectPermissionsId)) {
            return false;
        }
        AppResourceObjectPermissionsId castOther = (AppResourceObjectPermissionsId) other;

        return (this.getRoleId() == castOther.getRoleId()
                && this.getResourceId() == castOther.getResourceId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getRoleId();
        result = 37 * result + this.getResourceId();
        return result;
    }
}
