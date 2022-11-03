package com.ssa.cms.model;
// Generated Apr 9, 2013 4:16:04 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Intervals")
public class Intervals extends AuditFields implements java.io.Serializable {

    private int intervalId;
    private IntervalsType intervalsType;
    private Integer intervalValue;
    private String isActive;
    private String isDelete;
    private Set<CampaignMessagesResponse> campaignMessagesResponses = new HashSet<>(0);
    int remove;
    private String interval;

    public Intervals() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IntervalId", unique = true, nullable = false)
    public int getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(int intervalId) {
        this.intervalId = intervalId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IntervalsTypeId", nullable = false, insertable = true, updatable = true)
    public IntervalsType getIntervalsType() {
        return this.intervalsType;
    }

    public void setIntervalsType(IntervalsType intervalsType) {
        this.intervalsType = intervalsType;
    }

    @Column(name = "IntervalValue", nullable = false)
    public Integer getIntervalValue() {
        return this.intervalValue;
    }

    public void setIntervalValue(Integer intervalValue) {
        this.intervalValue = intervalValue;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDelete", nullable = false, length = 4)
    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "intervals")
    public Set<CampaignMessagesResponse> getCampaignMessagesResponses() {
        return this.campaignMessagesResponses;
    }

    public void setCampaignMessagesResponses(Set<CampaignMessagesResponse> campaignMessagesResponses) {
        this.campaignMessagesResponses = campaignMessagesResponses;
    }

    @Transient
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    @Transient
    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

}
