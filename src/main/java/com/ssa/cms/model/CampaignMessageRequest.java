package com.ssa.cms.model;

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
 * @author mzubair
 * 
 * CampaignMessageRequest table not exist
 */

@Entity
@Table(name = "CampaignMessageRequest")
public class CampaignMessageRequest implements java.io.Serializable {

    private static final long serialVersionUID = 4270771992826213251L;
    private Long cmrSeqNo;
    private Long crSeqNo;
    private Integer campaignId;
    private String campaignName;
    private Integer messageId;
    private int messageTypeId;
    private int folderId;
    private Integer shortCode;
    private String messageTypeCode;
    private String cmsEvent;
    private String cmsInterval;
    private Date effectiveDate;
    private long inputReferenceNumber;
    private long intervalId;
    private String intervalDescription;
    private String eventDetail;
    private String communicationPath;
    private String isRepeat = "No";

	// Constructors
    /**
     * default constructor
     */
    public CampaignMessageRequest() {
    }

    /**
     * minimal constructor
     */
    public CampaignMessageRequest(Integer shortCode, Date effectiveDate) {
        this.shortCode = shortCode;
        this.effectiveDate = effectiveDate;
    }

    /**
     * full constructor
     */
    public CampaignMessageRequest(Long crSeqNo, Integer campaignId,
            String campaignName, Integer messageId, Integer shortCode,
            String messageTypeCode, String cmsEvent, String cmsInterval,
            Date effectiveDate, long inputReferenceNumber, long intervalId, String intervalDescription,
            String eventDetail, String communicationPath) {
        this.crSeqNo = crSeqNo;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.messageId = messageId;
        this.shortCode = shortCode;
        this.messageTypeCode = messageTypeCode;
        this.cmsEvent = cmsEvent;
        this.cmsInterval = cmsInterval;
        this.effectiveDate = effectiveDate;
        this.inputReferenceNumber = inputReferenceNumber;
        this.intervalId = intervalId;
        this.intervalDescription = intervalDescription;
        this.eventDetail = eventDetail;
        this.communicationPath = communicationPath;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CMR_Seq_No", unique = true, nullable = false)
    public Long getCmrSeqNo() {
        return this.cmrSeqNo;
    }

    public void setCmrSeqNo(Long cmrSeqNo) {
        this.cmrSeqNo = cmrSeqNo;
    }

    @Column(name = "CR_Seq_No")
    public Long getCrSeqNo() {
        return this.crSeqNo;
    }

    public void setCrSeqNo(Long crSeqNo) {
        this.crSeqNo = crSeqNo;
    }

    @Column(name = "Campaign_Id")
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "Campaign_Name")
    public String getCampaignName() {
        return this.campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Column(name = "Message_Id")
    public Integer getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Column(name = "Short_Code", nullable = false)
    public Integer getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }

    @Column(name = "Message_Type_Code", length = 40)
    public String getMessageTypeCode() {
        return this.messageTypeCode;
    }

    public void setMessageTypeCode(String messageTypeCode) {
        this.messageTypeCode = messageTypeCode;
    }

    @Column(name = "CMS_Event", length = 50)
    public String getCmsEvent() {
        return this.cmsEvent;
    }

    public void setCmsEvent(String cmsEvent) {
        this.cmsEvent = cmsEvent;
    }

    @Column(name = "CMS_Interval", length = 10)
    public String getCmsInterval() {
        return this.cmsInterval;
    }

    public void setCmsInterval(String cmsInterval) {
        this.cmsInterval = cmsInterval;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date", nullable = false, length = 19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "Message_Type_Id", nullable = false)
    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Column(name = "Folder_Id", nullable = false)
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Column(name = "Input_Reference_No", length = 20)
    public long getInputReferenceNumber() {
        return inputReferenceNumber;
    }

    public void setInputReferenceNumber(long inputReferenceNumber) {
        this.inputReferenceNumber = inputReferenceNumber;
    }

    @Column(name = "Interval_Id")
    public long getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(long intervalId) {
        this.intervalId = intervalId;
    }

    @Column(name = "Interval_Description", length = 50)
    public String getIntervalDescription() {
        return intervalDescription;
    }

    public void setIntervalDescription(String intervalDescription) {
        this.intervalDescription = intervalDescription;
    }

    @Column(name = "Event_Detail", length = 225)
    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    @Column(name = "Communication_Path", length = 10)
    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }

    @Column(name = "isRepeat")
    public String getIsRepeat() {
        return isRepeat;
    }

    /**
     * @param isRepeat the isRepeat to set
     */
    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }

}
