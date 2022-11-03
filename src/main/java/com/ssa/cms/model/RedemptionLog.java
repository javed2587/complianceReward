package com.ssa.cms.model;
// Generated Apr 16, 2013 7:26:01 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * RedemptionLog not used
 * @author mzubair
 */

//@Entity
//@Table(name="RedemptionLog")
public class RedemptionLog  implements java.io.Serializable {


     private Long seqNo;
     private String clientIp;
     private String description;
     private Date effectiveDate;
     private Date endDate;
     private String fileTypeCode;
     private Integer messageSent;
     private String notification;
     private String requestXml;
     private Integer statusCode;
     private long campaignId;

    public RedemptionLog() {
    }

    public RedemptionLog(Date effectiveDate,long campaignId) {
        this.effectiveDate = effectiveDate;
        this.campaignId = campaignId;
    }
    public RedemptionLog(String clientIp, String description, Date effectiveDate, Date endDate, String fileTypeCode, Integer messageSent, String notification, String requestXml, Integer statusCode, long campaignId) {
       this.clientIp = clientIp;
       this.description = description;
       this.effectiveDate = effectiveDate;
       this.endDate = endDate;
       this.fileTypeCode = fileTypeCode;
       this.messageSent = messageSent;
       this.notification = notification;
       this.requestXml = requestXml;
       this.statusCode = statusCode;
       this.campaignId = campaignId;
    }
   
//     @Id @GeneratedValue(strategy=IDENTITY)
//    
//    @Column(name="Seq_No", unique=true, nullable=false)
    public Long getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }
    
//    @Column(name="Client_IP", length=20)
    public String getClientIp() {
        return this.clientIp;
    }
    
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
    
//    @Column(name="Description", length=200)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="Effective_Date", nullable=false, length=19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="End_Date", length=19)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
   // @Column(name="File_Type_Code", length=3)
    public String getFileTypeCode() {
        return this.fileTypeCode;
    }
    
    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }
    
   // @Column(name="Message_Sent")
    public Integer getMessageSent() {
        return this.messageSent;
    }
    
    public void setMessageSent(Integer messageSent) {
        this.messageSent = messageSent;
    }
    
   // @Column(name="Notification", length=65535)
    public String getNotification() {
        return this.notification;
    }
    
    public void setNotification(String notification) {
        this.notification = notification;
    }
    
   // @Column(name="Request_Xml", length=65535)
    public String getRequestXml() {
        return this.requestXml;
    }
    
    public void setRequestXml(String requestXml) {
        this.requestXml = requestXml;
    }
    
   // @Column(name="Status_Code")
    public Integer getStatusCode() {
        return this.statusCode;
    }
    
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    //@Column(name="Campaign_Id", nullable=false)
    public long getCampaignId() {
        return campaignId;
    }
    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }




}


