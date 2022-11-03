package com.ssa.cms.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Lob;
import org.hibernate.validator.constraints.NotEmpty;

public class SupportModel {

    public String id;
    private List<String> ndcNumber;
    @NotEmpty
    private String communicationMethod;
    @NotEmpty
    private String shortCode;
    @NotEmpty
    private String eventName;
    @NotEmpty
    private String ctrigger;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String communicationId;
    @NotEmpty
    private String cardNumber = "";
    private String EmTransactionNum = "SSA2219S7EB2810";
    private String firstName = "test";
    private String lastName = "test";
    private Date postingDate = new Date();
    private Date fillDate = new Date();
    private String fileType;
    private String submittedId;
    private String claimStatus;
    private String cardHolderId;
    int remove;
    private String phoneNo;
    private String ivr;
    private String tc;
    private String email;
    private String options;
    private String outofpocket;
    private String memberId;
    private byte[] imgUpload;
    private Integer messageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCtrigger() {
        return ctrigger;
    }

    public void setCtrigger(String ctrigger) {
        this.ctrigger = ctrigger;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmTransactionNum() {
        return EmTransactionNum;
    }

    public void setEmTransactionNum(String EmTransactionNum) {
        this.EmTransactionNum = EmTransactionNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getFillDate() {
        return fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSubmittedId() {
        return submittedId;
    }

    public void setSubmittedId(String submittedId) {
        this.submittedId = submittedId;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public List<String> getNdcNumber() {
        return ndcNumber;
    }

    public void setNdcNumber(List<String> ndcNumber) {
        this.ndcNumber = ndcNumber;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getIvr() {
        return ivr;
    }

    public void setIvr(String ivr) {
        this.ivr = ivr;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOutofpocket() {
        return outofpocket;
    }

    public void setOutofpocket(String outofpocket) {
        this.outofpocket = outofpocket;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Lob
    public byte[] getImgUpload() {
        return imgUpload;
    }

    public void setImgUpload(byte[] imgUpload) {
        this.imgUpload = imgUpload;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

}
