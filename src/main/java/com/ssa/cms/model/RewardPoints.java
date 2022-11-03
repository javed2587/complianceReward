package com.ssa.cms.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "RewardPoints")
public class RewardPoints extends AuditFields implements java.io.Serializable {

    private Integer id;
    private String type;
    private BigDecimal point;
    private List<RewardPoints> rewardPointlist;
    private List<PatientRewardLevel> patientRewardLevel;
    private String insuranceBackCardPath;
    private String insuranceFrontCardPath;
    private String securityToken;
    private Integer points;
    private BigDecimal cost;
    private Object bonusPoints;
    private Long availablePoints;
    private Long lifeTimePoints;
    private Integer transferId;
    private Integer sharePoints;
    private Long orderChainId;
    private Long availedRewardPoints;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "Points")
    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    @Transient
    public List<RewardPoints> getRewardPointlist() {
        return rewardPointlist;
    }

    public void setRewardPointlist(List<RewardPoints> rewardPointlist) {
        this.rewardPointlist = rewardPointlist;
    }

    @Transient
    public List<PatientRewardLevel> getPatientRewardLevel() {
        return patientRewardLevel;
    }

    public void setPatientRewardLevel(List<PatientRewardLevel> patientRewardLevel) {
        this.patientRewardLevel = patientRewardLevel;
    }

    @Transient
    public String getInsuranceBackCardPath() {
        return insuranceBackCardPath;
    }

    public void setInsuranceBackCardPath(String insuranceBackCardPath) {
        this.insuranceBackCardPath = insuranceBackCardPath;
    }

    @Transient
    public String getInsuranceFrontCardPath() {
        return insuranceFrontCardPath;
    }

    public void setInsuranceFrontCardPath(String insuranceFrontCardPath) {
        this.insuranceFrontCardPath = insuranceFrontCardPath;
    }

    @Transient
    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    @Transient
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Transient
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Transient
    public Object getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Object bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Transient
    public Long getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(Long availablePoints) {
        this.availablePoints = availablePoints;
    }

    @Transient
    public Long getLifeTimePoints() {
        return lifeTimePoints;
    }

    public void setLifeTimePoints(Long lifeTimePoints) {
        this.lifeTimePoints = lifeTimePoints;
    }

    @Transient
    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    @Transient
    public Integer getSharePoints() {
        return sharePoints;
    }

    public void setSharePoints(Integer sharePoints) {
        this.sharePoints = sharePoints;
    }

    @Transient
    public Long getOrderChainId() {
        return orderChainId;
    }

    public void setOrderChainId(Long orderChainId) {
        this.orderChainId = orderChainId;
    }

    @Transient
    public Long getAvailedRewardPoints() {
        return availedRewardPoints;
    }

    public void setAvailedRewardPoints(Long availedRewardPoints) {
        this.availedRewardPoints = availedRewardPoints;
    }

}
