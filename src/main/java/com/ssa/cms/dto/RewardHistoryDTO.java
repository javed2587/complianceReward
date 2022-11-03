/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.dto;

import com.ssa.cms.model.Order;

/**
 *
 * @author adeel.usmani
 */
public class RewardHistoryDTO 
{
    private Integer id;
    private Integer patientId;
    private String activityName;
    private Integer activityNumber;
    private String RxNumber;
    private Integer RewardPointId;
    private String createdDate;
    private Order order;
    private Double EarnedReward;
    private String activityTitle;
    private String activityDetail;
///////////////////////////////////
    private Integer redeemedPoints;
    private Integer awardedPoints;
    private String description;
    private String type;
    private Integer point;   
    private String readingTime;
    private float veagaWalletPoint;
    private int vegaWalletActivityCount;



///////////////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(Integer redeemedPoints) {
        this.redeemedPoints = redeemedPoints;
    }

    public Integer getAwardedPoints() {
        return awardedPoints;
    }

    public void setAwardedPoints(Integer awardedPoints) {
        this.awardedPoints = awardedPoints;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(Integer activityNumber) {
        this.activityNumber = activityNumber;
    }

    public String getRxNumber() {
        return RxNumber;
    }

    public void setRxNumber(String RxNumber) {
        this.RxNumber = RxNumber;
    }

    public Integer getRewardPointId() {
        return RewardPointId;
    }

    public void setRewardPointId(Integer RewardPointId) {
        this.RewardPointId = RewardPointId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getEarnedReward() {
        return EarnedReward;
    }

    public void setEarnedReward(Double EarnedReward) {
        this.EarnedReward = EarnedReward;
    }

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
        public float getVeagaWalletPoint() {
        return veagaWalletPoint;
    }

    public void setVeagaWalletPoint(float veagaWalletPoint) {
        this.veagaWalletPoint = veagaWalletPoint;
    }

    public int getVegaWalletActivityCount() {
        return vegaWalletActivityCount;
    }

    public void setVegaWalletActivityCount(int vegaWalletActivityCount) {
        this.vegaWalletActivityCount = vegaWalletActivityCount;
    }
}
