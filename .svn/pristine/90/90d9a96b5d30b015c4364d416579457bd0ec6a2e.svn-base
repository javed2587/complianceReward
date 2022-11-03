package com.ssa.cms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Zubair
 */
//@Entity
//@Table(name = "OrderHistory")
public class OrderHistory extends AuditFields implements java.io.Serializable {

    private Integer id;
    private OrderStatus orderStatus;
    private Order order;
    private String comments;
    private String status;
    private String responseRequiredLog;
    private String patientResponseLog;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderStatusId", insertable = true, updatable = true)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonManagedReference
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId", nullable = false, insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Transient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "ResponseRequiredLog")
    public String getResponseRequiredLog() {
        return responseRequiredLog;
    }

    public void setResponseRequiredLog(String responseRequiredLog) {
        this.responseRequiredLog = responseRequiredLog;
    }

    @Column(name = "PatientResponseLog")
    public String getPatientResponseLog() {
        return patientResponseLog;
    }

    public void setPatientResponseLog(String patientResponseLog) {
        this.patientResponseLog = patientResponseLog;
    }
    
    

}
