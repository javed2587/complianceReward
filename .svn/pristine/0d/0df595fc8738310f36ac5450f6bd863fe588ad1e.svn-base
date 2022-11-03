/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="payment_recived")
public class Payment implements Serializable {
    private Long id;
    private Order order;
    private PatientInsuranceDetails insurance;
    private Double rewardMoney;
    private Double insuranceMoney;
    private Double selfPayMoney;
    //private WegaWalletInfo wegawallet;
    private Double tax;
    private Double homeDeliveryFee;
    private Double totalBill;
    private Double payeedAmount;
    private String signature;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

//     @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "insurance_card_id", nullable = false, insertable = true, updatable = true)
     @Transient
    public PatientInsuranceDetails getInsurance() {
        return insurance;
    }

    public void setInsurance(PatientInsuranceDetails insurance) {
        this.insurance = insurance;
    }

    @Column(name="patient_copay_paid")
    public Double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

     @Column(name="rx_third_party_paid")
    public Double getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Double insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

     @Column(name="other_payment_received")
    public Double getSelfPayMoney() {
        return selfPayMoney;
    }

    public void setSelfPayMoney(Double selfPayMoney) {
        this.selfPayMoney = selfPayMoney;
    }

    @Column(name="tax_paid")
    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Column(name="home_delivery_fee_paid")
    public Double getHomeDeliveryFee() {
        return homeDeliveryFee;
    }

    public void setHomeDeliveryFee(Double homeDeliveryFee) {
        this.homeDeliveryFee = homeDeliveryFee;
    }
    

    @Column(name="total_bill_paid")
    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    //@Column(name="paid")
    @Transient
    public Double getPayeedAmount() {
        return payeedAmount;
    }

    public void setPayeedAmount(Double payeedAmount) {
        this.payeedAmount = payeedAmount;
    }

//    @Column(name="signature")
     @Transient
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Column(name="created_on")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="updated_on")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
}
