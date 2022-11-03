package com.ssa.cms.model;

import com.ssa.cms.util.JsonDateSerializer;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@MappedSuperclass
public abstract class AuditFields {

    private Integer createdBy;
    private Date createdOn;
    private Integer updatedBy;
    private Date updatedOn;

    @Column(name = "CreatedBy", length = 20)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "UpdatedBy", length = 20)
    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
