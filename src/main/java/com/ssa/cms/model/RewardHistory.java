package com.ssa.cms.model;

//import java.beans.Transient;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "RewardHistory")
public class RewardHistory implements Serializable {

    private Integer id;
    private Integer patientId;
    private RewardActivity activityId;
    private Integer activityNumber;
    private String RxNumber;
    private Integer RewardPointId;
    private String description;
    private String type;
    private Integer point;
    private Date createdDate;
    private Order order;
    private Double earnedReward;
    private String activityDetail;
    private String readingTime;
    private float veagaWalletPoint;
    private int vegaWalletActivityCount;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PatientId")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActivityId", nullable = false, insertable = true, updatable = true)
    public RewardActivity getActivityId() {
        return activityId;
    }

    public void setActivityId(RewardActivity activityId) {
        this.activityId = activityId;
    }
@Column(name = "ActivityNumber")
    public Integer getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(Integer activityNumber) {
        this.activityNumber = activityNumber;
    }
@Column(name = "RxNumber")
    public String getRxNumber() {
        return RxNumber;
    }

    public void setRxNumber(String RxNumber) {
        this.RxNumber = RxNumber;
    }
@Column(name = "RewardPointId")
    public Integer getRewardPointId() {
        return RewardPointId;
    }

    public void setRewardPointId(Integer RewardPointId) {
        this.RewardPointId = RewardPointId;
    }
@Transient
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
@Transient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
@Transient
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", length = 19)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderId", nullable = false, insertable = true, updatable = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
@Column(name="EarnedReward")
    public Double getEarnedReward() {
        return earnedReward;
    }

    public void setEarnedReward(Double earnedReward) {
        this.earnedReward = earnedReward;
    }
@Column(name="ActivityDetail")
    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }
    @Column(name = "readingTime")
    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
    @Column(name = "vegaWalletPoint")
    public float getVeagaWalletPoint() {
        return veagaWalletPoint;
    }

    public void setVeagaWalletPoint(float veagaWalletPoint) {
        this.veagaWalletPoint = veagaWalletPoint;
    }  
        @Column(name = "vegaWalletCount")
    public int getVegaWalletActivityCount() {
        return vegaWalletActivityCount;
    }

    public void setVegaWalletActivityCount(int vegaWalletActivityCount) {
        this.vegaWalletActivityCount = vegaWalletActivityCount;
    }
}
