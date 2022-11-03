/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.ssa.cms.util.JsonDateSerializer;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author adeel.usmani
 */
@Entity
@Table(name = "OrderBatch")
public class OrderBatch implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date createdOn;
    private Date lastRefillDate;
    private PatientProfile patientProfile;
    private List<Order> orders;

    public OrderBatch() {
    }

    
    
    public OrderBatch(Integer id) {
        this.id = id;
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

    
    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    
    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastRefillDate", length = 19)
    public Date getLastRefillDate() {
        return lastRefillDate;
    }

    public void setLastRefillDate(Date lastRefillDate) {
        this.lastRefillDate = lastRefillDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = false, insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
    
    @OneToMany(mappedBy = "orderBatch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
