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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "payment_recived")
public class PaymentsRecived implements Serializable {

    private Long id;
    private Order order;
    private Double patientCopyCard;
    private Double rxThirdPartyPaid;
    private Double otherPaymentRecived;
//    private WegaWalletInfo wegawallet;
    private Double taxPaid;
    private Double homeDeliveryFeePaid;
    private Double totalBillPaid;
    private Long authNumber;
    private Date createdAt;
    private Date updatedAt;





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

    @Column(name = "patient_copay_paid")
    public Double getPatientCopyCard() {
        return patientCopyCard;
    }

    public void setPatientCopyCard(Double patientCopyCard) {
        this.patientCopyCard = patientCopyCard;
    }

    @Column(name = "rx_third_party_paid")
    public Double getRxThirdPartyPaid() {
        return rxThirdPartyPaid;
    }

    public void setRxThirdPartyPaid(Double rxThirdPartyPaid) {
        this.rxThirdPartyPaid = rxThirdPartyPaid;
    }

    @Column(name = "other_payment_received")
    public Double getOtherPaymentRecived() {
        return otherPaymentRecived;
    }

    public void setOtherPaymentRecived(Double otherPaymentRecived) {
        this.otherPaymentRecived = otherPaymentRecived;
    }

    @Column(name = "tax_paid")
    public Double getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(Double taxPaid) {
        this.taxPaid = taxPaid;
    }

    @Column(name = "home_delivery_fee_paid")
    public Double getHomeDeliveryFeePaid() {
        return homeDeliveryFeePaid;
    }

    public void setHomeDeliveryFeePaid(Double homeDeliveryFeePaid) {
        this.homeDeliveryFeePaid = homeDeliveryFeePaid;
    }

    @Column(name = "total_bill_paid")
    public Double getTotalBillPaid() {
        return totalBillPaid;
    }

    public void setTotalBillPaid(Double totalBillPaid) {
        this.totalBillPaid = totalBillPaid;
    }

    @Column(name = "auth_number")
    public Long getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(Long authNumber) {
        this.authNumber = authNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
