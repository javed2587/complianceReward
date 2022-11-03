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

/**
 *
 * @author Jandal
 */
@Entity
@Table(name = "RewardActivities")
public class RewardActivity implements Serializable {
    private int activityId;
    private String activityName;
    private String activityDetail;
    private Date CreatedOn;
    private Date UpdateOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActivityId", unique = true, nullable = false)
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int ActivityId) {
        this.activityId = ActivityId;
    }
    @Column(name = "ActivityName")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String ActivityName) {
        this.activityName = ActivityName;
    }
    @Column(name = "ActivityDetail")
    public String getActivity() {
        return activityDetail;
    }

    public void setActivity(String Activity) {
        this.activityDetail = Activity;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn")
    public Date getUpdateOn() {
        return UpdateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.UpdateOn = UpdateOn;
    }
    
}
