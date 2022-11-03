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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name = "ComplianceRewardPoint")
public class ComplianceRewardPoint implements Serializable {
    private int rewardId;
    private PatientProfile profile;
    private String RxNo;
    private Order orders;
    private RewardActivity rewardactivities;
    private int activityCount;
    private Double rxPatientOutOfPocket;
    private Double currentEarnReward;
    private Double currentRemainBalance;
    private Date createdOn;
    private Date updateOn;
    private float wegaWalletPoint;





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RewardId", unique = true, nullable = false)    
    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int RewardId) {
        this.rewardId = RewardId;
    }
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "PatientId", insertable = true, updatable = true)
    public PatientProfile getProfile() {
        return profile;
    }

    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }
    @Column(name = "RxId")
    public String getRxNo() {
        return RxNo;
    }

    public void setRxNo(String RxNo) {
        this.RxNo = RxNo;
    }
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "OrderId", insertable = true, updatable = true)
   
// @Transient
       public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "ActivityTypeId", insertable = true, updatable = true)
    public RewardActivity getRewardactivities() {
        return rewardactivities;
    }

    public void setRewardactivities(RewardActivity rewardactivities) {
        this.rewardactivities = rewardactivities;
    }
@Column(name = "ActivityCount")
    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int ActivityCount) {
        this.activityCount = ActivityCount;
    }
@Column(name = "RxPatientOutOfPocket")
    public Double getRxPatientOutOfPocket() {
        return rxPatientOutOfPocket;
    }

    public void setRxPatientOutOfPocket(Double OrignalPointAuth) {
        this.rxPatientOutOfPocket = OrignalPointAuth;
    }
@Column(name = "CurrentEarnReward")
    public Double getCurrentEarnReward() {
        return currentEarnReward;
    }

    public void setCurrentEarnReward(Double CurrentEarnReward) {
        this.currentEarnReward = CurrentEarnReward;
    }
@Column(name = "CurrentRemainBalance")
    public Double getCurrentRemainBalance() {
        return currentRemainBalance;
    }

    public void setCurrentRemainBalance(Double CurrentRemainBalance) {
        this.currentRemainBalance = CurrentRemainBalance;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.createdOn = CreatedOn;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn")
    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.updateOn = UpdateOn;
    }
     @Column(name = "WegaWallet_Point")
    public float getWegaWalletPoint() {
        return wegaWalletPoint;
    }

    public void setWegaWalletPoint(float wegaWalletPoint) {
        this.wegaWalletPoint = wegaWalletPoint;
    }

}
