package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name = "RefillOrders")
public class RefillOrders implements Serializable {

    private int refillOrderId;
    private int messageReqNo;
    private int messageReqNoReminder;
    private int campaignId;
    private int folderId;
    private int messageTypeId;
    private String submittedID;
    private String comments;
    private String actionBySystem;
    private String actionByUserID;
    private String refillFrom;
    private String refillStatus;
    private Date effectiveDate;
    private Date endDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public int getRefillOrderId() {
        return refillOrderId;
    }

    public void setRefillOrderId(int refillOrderId) {
        this.refillOrderId = refillOrderId;
    }

    @Column(name = "Message_Req_No")
    public int getMessageReqNo() {
        return messageReqNo;
    }

    public void setMessageReqNo(int messageReqNo) {
        this.messageReqNo = messageReqNo;
    }

    @Column(name = "Message_Req_No_Reminder")
    public int getMessageReqNoReminder() {
        return messageReqNoReminder;
    }

    public void setMessageReqNoReminder(int messageReqNoReminder) {
        this.messageReqNoReminder = messageReqNoReminder;
    }

    @Column(name = "Campaign_Id")
    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "FolderId")
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Column(name = "MessageTypeId")
    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Column(name = "Submitted_ID")
    public String getSubmittedID() {
        return submittedID;
    }

    public void setSubmittedID(String submittedID) {
        this.submittedID = submittedID;
    }

    @Column(name = "Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "ActionBySystem")
    public String getActionBySystem() {
        return actionBySystem;
    }

    public void setActionBySystem(String actionBySystem) {
        this.actionBySystem = actionBySystem;
    }

    @Column(name = "ActionByUserID")
    public String getActionByUserID() {
        return actionByUserID;
    }

    public void setActionByUserID(String actionByUserID) {
        this.actionByUserID = actionByUserID;
    }

    @Column(name = "Refill_From")
    public String getRefillFrom() {
        return refillFrom;
    }

    public void setRefillFrom(String refillFrom) {
        this.refillFrom = refillFrom;
    }

    @Column(name = "Refill_Status")
    public String getRefillStatus() {
        return refillStatus;
    }

    public void setRefillStatus(String refillStatus) {
        this.refillStatus = refillStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "End_Date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
