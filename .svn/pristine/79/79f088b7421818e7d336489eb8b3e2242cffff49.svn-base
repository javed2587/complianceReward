package com.ssa.cms.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "renewal_prescription")
public class RxRenewal implements java.io.Serializable {

    private int id;
    private String ordrId;
    private String viewStatus;
    private String renewalOrderId;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "OrderId")
    public String getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(String ordrId) {
        this.ordrId = ordrId;
    }

    @Column(name = "viewStatus")
    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }
    @Column(name = "renw_orderId")
    public String getRenewalOrderId() {
        return renewalOrderId;
    }

    public void setRenewalOrderId(String renewalOrderId) {
        this.renewalOrderId = renewalOrderId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
