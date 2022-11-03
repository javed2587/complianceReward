/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.ssa.cms.modelinterfaces.CommonOrderFunctionalityI;
import com.ssa.cms.modellisteners.OrderListener;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "OrderTransferImages")
//@EntityListeners(OrderListener.class)
public class OrderTransferImages implements java.io.Serializable, CommonOrderFunctionalityI {

    private Integer id;
    private Order order;
    private String drugImg;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "DrugImg")
    public String getDrugImg() {
        return drugImg;
    }

    public void setDrugImg(String drugImg) {
        this.drugImg = drugImg;
    }

    @Column(name = "CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "UpdatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Transient
    @Override
    public String getFirstName() {
        return "";
    }

    @Override
    public void setFirstName(String s) {

    }

    @Override
    @Transient
    public String getLastName() {
        return "";
    }

    @Override
    public void setLastName(String s) {

    }

    @Override
    @Transient
    public String getCardCvv() {
        return "";
    }

    @Override
    public void setCardCvv(String cardCvv) {

    }

    @Override
    @Transient
    public String getImagePath() {
        return "";
    }

    @Override
    public void setImagePath(String imagePath) {

    }

    @Override
    @Transient
    public String getVideo() {
        return "";
    }

    @Override
    public void setVideo(String video) {

    }

    @Override
    @Transient
    public String getDrugName() {
        return "";
    }

    @Override
    public void setDrugName(String drugName) {

    }

    @Transient
    @Override
    public String getCardHolderName() {
        return "";
    }

    @Override
    public void setCardHolderName(String cardHolderName) {

    }

    @Transient
    @Override
    public String getCardType() {
        return "";
    }

    @Override
    public void setCardType(String cardType) {

    }

    @Transient
    @Override
    public String getCardNumber() {
        return "";
    }

    @Override
    public void setCardNumber(String cardNumber) {

    }

    @Transient
    @Override
    public String getCvvNumber() {
        return "";
    }

    @Override
    public void setCvvNumber(String cvvNumber) {

    }

    @Transient
    @Override
    public String getCustomDocument() {
        return "";
    }

    @Override
    public void setCustomDocument(String customDocument) {

    }

}
