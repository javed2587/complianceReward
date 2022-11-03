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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "VegaWalletRewardPoint")
public class VegaWalletRewardPoint  implements Serializable {
    private int id;
    private float vegaWalletPoint;
    private int activityCount;
    private int patientId;
    private Date createdAt;
    private Date updatedAt;
    private String wegaWalletOverDue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "VegaWallet_Point")

    public float getVegaWalletPoint() {
        return vegaWalletPoint;
    }

    public void setVegaWalletPoint(float vegaWalletPoint) {
        this.vegaWalletPoint = vegaWalletPoint;
    }
    @Column(name = "ActivityCount")
    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }
     @Column(name = "PatientId")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "CreatedAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
        @Transient
        public String getWegaWalletOverDue() {
        return wegaWalletOverDue;
    }

    public void setWegaWalletOverDue(String wegaWalletOverDue) {
        this.wegaWalletOverDue = wegaWalletOverDue;
    }
}
