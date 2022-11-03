/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author arsalan.ahmad
 */
//@Entity
//@Table(name = "MultiRx")
public class MultiRx implements Serializable {

    private Long id;

    private Order mainOrder;
    
    private Order order;
    
    private Date shippedDate;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MainOrderID", insertable = true, updatable = true, nullable = true)
    public Order getMainOrder() {
        return mainOrder;
    }

    public void setMainOrder(Order mainOrder) {
        this.mainOrder = mainOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", insertable = true, updatable = true, nullable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ShippedDate", length = 19)
    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    
    
}
