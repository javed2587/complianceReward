package com.ssa.cms.model;
// Generated Apr 16, 2013 7:26:01 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CampaignMessagesResponse")
public class CampaignMessagesResponse extends AuditFields implements java.io.Serializable {

    private Integer campaignMessagesResponseId;
    private int intervalId;
    private int intervalsTypeId;
    private Intervals intervals;
    private Response response;
    private CampaignMessages campaignMessages;
    private Integer nextMessage;
    private Integer repeatIntervalId;
    private Integer repeatIntervalTypeId;
    private Integer repeatMessageId;
    private Integer campaignId;
    private Integer messageTypeId;
    private Integer folderId;
    private String paired;
    private String responseTitle;
    private String comunicationPath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "IntervalId", referencedColumnName = "IntervalId", nullable = false, insertable = false, updatable = false)})
    public Intervals getIntervals() {
        return this.intervals;
    }

    public void setIntervals(Intervals intervals) {
        this.intervals = intervals;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResponseId")
    public Response getResponse() {
        return this.response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MessageId", nullable = false, insertable = true, updatable = true)
    public CampaignMessages getCampaignMessages() {
        return this.campaignMessages;
    }

    public void setCampaignMessages(CampaignMessages campaignMessages) {
        this.campaignMessages = campaignMessages;
    }

    @Column(name = "CampaignId", nullable = false, length = 11)
    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "MessageTypeId", nullable = false, length = 11)
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Column(name = "FolderId", nullable = false, length = 11)
    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    @Column(name = "NextMessage")
    public Integer getNextMessage() {
        return this.nextMessage;
    }

    public void setNextMessage(Integer nextMessage) {
        this.nextMessage = nextMessage;
    }

    @Column(name = "RepeatIntervalId")
    public Integer getRepeatIntervalId() {
        return this.repeatIntervalId;
    }

    public void setRepeatIntervalId(Integer repeatIntervalId) {
        this.repeatIntervalId = repeatIntervalId;
    }

    @Column(name = "RepeatIntervalTypeId")
    public Integer getRepeatIntervalTypeId() {
        return this.repeatIntervalTypeId;
    }

    public void setRepeatIntervalTypeId(Integer repeatIntervalTypeId) {
        this.repeatIntervalTypeId = repeatIntervalTypeId;
    }

    @Column(name = "RepeatMessageId")
    public Integer getRepeatMessageId() {
        return this.repeatMessageId;
    }

    public void setRepeatMessageId(Integer repeatMessageId) {
        this.repeatMessageId = repeatMessageId;
    }

    @Column(name = "Paired", length = 4)
    public String getPaired() {
        return this.paired;
    }

    public void setPaired(String paired) {
        this.paired = paired;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CampaignMessagesResponseId", unique = true, nullable = false)
    public Integer getCampaignMessagesResponseId() {
        return this.campaignMessagesResponseId;
    }

    public void setCampaignMessagesResponseId(Integer campaignMessagesResponseId) {
        this.campaignMessagesResponseId = campaignMessagesResponseId;
    }

    @Column(name = "IntervalId", nullable = false)
    public int getIntervalId() {
        return this.intervalId;
    }

    public void setIntervalId(int intervalId) {
        this.intervalId = intervalId;
    }

    @Column(name = "IntervalsTypeId", nullable = false)
    public int getIntervalsTypeId() {
        return this.intervalsTypeId;
    }

    public void setIntervalsTypeId(int intervalsTypeId) {
        this.intervalsTypeId = intervalsTypeId;
    }

    @Transient
    public String getResponseTitle() {
        return responseTitle;
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }
 @Column(name= "Communication_Path")
    public String getComunicationPath() {
        return comunicationPath;
    }

    public void setComunicationPath(String comunicationPath) {
        this.comunicationPath = comunicationPath;
    }
}
