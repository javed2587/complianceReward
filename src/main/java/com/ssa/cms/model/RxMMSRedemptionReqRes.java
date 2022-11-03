package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RxMMSRedemptionReqRes")
public class RxMMSRedemptionReqRes implements Serializable {

    private Integer mmsReqNo;
    private String phoneNumber;
    private Integer folderId;
    private Integer messageTypeId;
    private Integer campaignId;
    private String messageText;
    private String fileTypeCode;
    private String rxGroupNumber;
    private String mmsContext;
    private Date cardholderDob;
    private String fileName;
    private Date fillDate;
    private String ndcNumber;
    private Integer claimStatus;
    private Date effectiveDate;
    private Date endDate;
    private int redemptionId;
    private long inputReferenceNumber;
    private long intervalId;
    private String intervalDescription;
    private String eventDetail;
    private String communicationPath;
    private String mmsRequest;

    public RxMMSRedemptionReqRes() {
    }

    public RxMMSRedemptionReqRes(String phoneNumber, Integer folderId, Integer messageTypeId, Integer campaignId, String fileTypeCode,  String rxGroupNumber, Date cardholderDob, Date fillDate, String ndcNumber, Date effectiveDate, int redemptionId) {
        this.phoneNumber = phoneNumber;
        this.folderId = folderId;
        this.messageTypeId = messageTypeId;
        this.campaignId = campaignId;
        this.fileTypeCode = fileTypeCode;
        this.rxGroupNumber = rxGroupNumber;
        this.cardholderDob = cardholderDob;
        this.fillDate = fillDate;
        this.ndcNumber = ndcNumber;
        this.effectiveDate = effectiveDate;
        this.redemptionId = redemptionId;
    }

    public RxMMSRedemptionReqRes(String phoneNumber, Integer folderId, Integer messageTypeId, Integer campaignId, String messageText, String fileTypeCode,  String rxGroupNumber, Date cardholderDob, String fileName, Date fillDate, String ndcNumber, Integer claimStatus, Date effectiveDate, Date endDate, int redemptionId, long intervalId, String intervalDescription,
            String eventDetail, String communicationPath,String mmsRequest) {
        this.phoneNumber = phoneNumber;
        this.folderId = folderId;
        this.messageTypeId = messageTypeId;
        this.campaignId = campaignId;
        this.messageText = messageText;
        this.fileTypeCode = fileTypeCode;
        this.rxGroupNumber = rxGroupNumber;
        this.cardholderDob = cardholderDob;
        this.fileName = fileName;
        this.fillDate = fillDate;
        this.ndcNumber = ndcNumber;
        this.claimStatus = claimStatus;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.redemptionId = redemptionId;
        this.intervalId = intervalId;
        this.intervalDescription = intervalDescription;
        this.eventDetail = eventDetail;
        this.communicationPath = communicationPath;
        this.mmsRequest = mmsRequest;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MMS_Req_No", unique = true, nullable = false)
    public Integer getMmsReqNo() {
        return this.mmsReqNo;
    }

    public void setMmsReqNo(Integer mmsReqNo) {
        this.mmsReqNo = mmsReqNo;
    }

    @Column(name = "Phone_Number", nullable = false, length = 11)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "FolderId", nullable = false, length = 11)
    public Integer getFolderId() {
        return this.folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    @Column(name = "MessageTypeId", nullable = false, length = 11)
    public Integer getMessageTypeId() {
        return this.messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Column(name = "Campaign_Id", nullable = false, length = 11)
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "Message_Text", length = 200)
    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Column(name = "File_Type_Code", nullable = false, length = 6)
    public String getFileTypeCode() {
        return this.fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    @Column(name = "Rx_Group_Number", nullable = false, length = 10)
    public String getRxGroupNumber() {
        return this.rxGroupNumber;
    }

    public void setRxGroupNumber(String rxGroupNumber) {
        this.rxGroupNumber = rxGroupNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Cardholder_DOB", nullable = false, length = 10)
    public Date getCardholderDob() {
        return this.cardholderDob;
    }

    public void setCardholderDob(Date cardholderDob) {
        this.cardholderDob = cardholderDob;
    }

    @Column(name = "File_Name", length = 125)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Fill_Date", nullable = false, length = 10)
    public Date getFillDate() {
        return this.fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

    @Column(name = "NDC_Number", nullable = false, length = 12)
    public String getNdcNumber() {
        return this.ndcNumber;
    }

    public void setNdcNumber(String ndcNumber) {
        this.ndcNumber = ndcNumber;
    }

    @Column(name = "Claim_Status")
    public Integer getClaimStatus() {
        return this.claimStatus;
    }

    public void setClaimStatus(Integer claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date", nullable = false, length = 19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "End_Date", length = 19)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Redemption_Id", nullable = false)
    public int getRedemptionId() {
        return this.redemptionId;
    }

    public void setRedemptionId(int redemptionId) {
        this.redemptionId = redemptionId;
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

    @Column(name = "Event_Detail", length = 50)
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

    @Column(name = "MMS_Request", length = 2000)
    public String getMmsRequest() {
        return mmsRequest;
    }

    public void setMmsRequest(String mmsRequest) {
        this.mmsRequest = mmsRequest;
    }

    @Column(name = "MMS_Context", length = 2000)
    public String getMmsContext() {
        return mmsContext;
    }

    public void setMmsContext(String mmsContext) {
        this.mmsContext = mmsContext;
    }
}
