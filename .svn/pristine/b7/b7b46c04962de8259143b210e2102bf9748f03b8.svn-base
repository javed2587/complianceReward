package com.ssa.cms.model;

import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.modelinterfaces.CommonMessageFunctionalityI;
import com.ssa.cms.modellisteners.MessageListener;
import com.ssa.cms.util.JsonDateSerializer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "NotificationMessages")
@EntityListeners(MessageListener.class)
public class NotificationMessages implements Serializable, CommonMessageFunctionalityI {

    private Integer id;
    private MessageType messageType;
    private PatientProfile patientProfile;
    private String subject;
    private String messageText;
    private String status;
    private Date createdOn;
    private String createdonStringFormat;
    private Integer messageTypeId;
    private Integer profileId;
    private Boolean isRead;
    private Long readMesages;
    private Long unReadMessages;
    private String messageCategory;
    private Order orders;
    private TransferDetail transferDetail;
    private String orderId = "";
    private String orderStatus = "";
    private String attachmentPath;
    private String attachmentName;
    private String contentType;
    private String timeAgo;
    private Integer isCritical;
    private Integer pointsAwarded;
    private String mobileNumber;
    private List<MessageResponses> messageResponses;
    private String isTestMsg;
    private String orderPrefix;
    private String PushSubject;
    private String rxNo;
    private Integer dependentId;
    private Boolean isEventFire;
    private String drugName;
    private Long questionId;
    private Integer notificationMsgId;
    private Double rxOutOfPocket;
    private Double earnedRewardPoint;
    private Boolean isArchive;
    private String drugType;
    private String qty;
    private Double patientOutOfPocket;
    private String brandRefrance;
    private String genericOrBrand;
    private String strength;
    private String isDelete;
    private Date updatedOn;
    private Integer daysSupply;
    private Date rxExpiredDate;
    private Integer refillsRemaining;
    private Integer patientOrdMsgId;
    private String patientOrdMessge;
    private String patientOrdMsgSubject;
    private Integer refillRemainingDaysCount;
    private String lastFilledDate;
    private String orderPdfDocument;
    private String orderDocumentMessgeSub;
    private String questionAnserImg;
    private String questionText;
    private String quesAnswerText;
    private Long assignSurveyId;
    private String assignsurvyId;
    private String assignStatus;
    private Long surveyId;
    private Long surveyLogsId;
    private String viewStatus;
    private Double assistanceAuth;
    private String createdBy;
    private Double orgPatientOutOfPocket;
    private OrderDetailDTO orderDto;

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "MessageTypeId", referencedColumnName = "MessageTypeId", nullable = false),
        @JoinColumn(name = "FolderId", referencedColumnName = "FolderId", nullable = false)})
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProfileId", insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", insertable = true, updatable = true, nullable = true)
    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    @Column(name = "Subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
//     @Transient
    @Column(name = "MessageText")
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Transient
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Transient
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Column(name = "IsRead")
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Transient
    public Long getReadMesages() {
        return readMesages;
    }

    public void setReadMesages(Long readMesages) {
        this.readMesages = readMesages;
    }

    @Transient
    public Long getUnReadMessages() {
        return unReadMessages;
    }

    public void setUnReadMessages(Long unReadMessages) {
        this.unReadMessages = unReadMessages;
    }

    @Transient
    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "TransferDetailID", nullable = true, insertable = true, updatable = true)
    @Transient
    public TransferDetail getTransferDetail() {
        return transferDetail;
    }

    /**
     * @param transferDetail the transferDetail to set
     */
    public void setTransferDetail(TransferDetail transferDetail) {
        this.transferDetail = transferDetail;
    }

    @Transient
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Transient
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Transient
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Transient
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Transient
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Transient
    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    @Column(name = "IsCritical")
    public Integer getIsCritical() {
        return isCritical;
    }

    public void setIsCritical(Integer isCritical) {
        this.isCritical = isCritical;
    }

    @Column(name = "pointsAwarded")
    public Integer getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(Integer pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    @Transient
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @OneToMany(mappedBy = "notificationMessages", fetch = FetchType.LAZY)
    public List<MessageResponses> getMessageResponses() {
        return messageResponses;
    }

    public void setMessageResponses(List<MessageResponses> messageResponses) {
        this.messageResponses = messageResponses;
    }

    @Column(name = "IsTestMsg")
    public String getIsTestMsg() {
        return isTestMsg;
    }

    public void setIsTestMsg(String isTestMsg) {
        this.isTestMsg = isTestMsg;
    }

    @Transient
    public String getOrderPrefix() {
        return orderPrefix;
    }

    public void setOrderPrefix(String orderPrefix) {
        this.orderPrefix = orderPrefix;
    }

    @Column(name = "PushSubject")
    public String getPushSubject() {
        return PushSubject;
    }

    public void setPushSubject(String PushSubject) {
        this.PushSubject = PushSubject;
    }

    @Transient
    public String getRxNo() {
        return rxNo;
    }

    public void setRxNo(String rxNo) {
        this.rxNo = rxNo;
    }

    @Transient
    public Integer getDependentId() {
        return dependentId;
    }

    public void setDependentId(Integer dependentId) {
        this.dependentId = dependentId;
    }

    @Column(name = "IsEventFire")
    public Boolean getIsEventFire() {
        return isEventFire;
    }

    public void setIsEventFire(Boolean isEventFire) {
        this.isEventFire = isEventFire;
    }

    @Override
    @Transient
    public String getMessage() {
        return "";
    }

    @Override
    public void setMessage(String s) {

    }

    @Override
    @Transient
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Transient
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @Transient
    public String getCreatedonStringFormat() {
        return createdonStringFormat;
    }

    public void setCreatedonStringFormat(String createdonStringFormat) {
        this.createdonStringFormat = createdonStringFormat;
    }

//    @Transient
    @Column(name = "questionId")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Transient
    public Integer getNotificationMsgId() {
        return notificationMsgId;
    }

    public void setNotificationMsgId(Integer notificationMsgId) {
        this.notificationMsgId = notificationMsgId;
    }

    @Transient
    public Double getRxOutOfPocket() {
        return rxOutOfPocket;
    }

    public void setRxOutOfPocket(Double rxOutOfPocket) {
        this.rxOutOfPocket = rxOutOfPocket;
    }

    @Transient
    public Double getEarnedRewardPoint() {
        return earnedRewardPoint;
    }

    public void setEarnedRewardPoint(Double earnedRewardPoint) {
        this.earnedRewardPoint = earnedRewardPoint;
    }

    @Column(name = "IsArchive")
    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    @Transient
    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    @Transient
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Transient
    public Double getPatientOutOfPocket() {
        return patientOutOfPocket;
    }

    public void setPatientOutOfPocket(Double patientOutOfPocket) {
        this.patientOutOfPocket = patientOutOfPocket;
    }

    @Transient
    public String getBrandRefrance() {
        return brandRefrance;
    }

    public void setBrandRefrance(String brandRefrance) {
        this.brandRefrance = brandRefrance;
    }

    @Transient
    public String getGenericOrBrand() {
        return genericOrBrand;
    }

    public void setGenericOrBrand(String genericOrBrand) {
        this.genericOrBrand = genericOrBrand;
    }

    @Transient
    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Column(name = "IsDelete")
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "UpdatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "SurveyID")
    public Long getSurveyLogsId() {
        return surveyLogsId;
    }

    public void setSurveyLogsId(Long surveyLogsId) {
        this.surveyLogsId = surveyLogsId;
    }
        @Transient
    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
    
    @Transient
    public Integer getDaysSupply() {
        return daysSupply;
    }

    public void setDaysSupply(Integer daysSupply) {
        this.daysSupply = daysSupply;
    }

    @Transient
    public Date getRxExpiredDate() {
        return rxExpiredDate;
    }

    public void setRxExpiredDate(Date rxExpiredDate) {
        this.rxExpiredDate = rxExpiredDate;
    }

    @Transient
    public Integer getRefillsRemaining() {
        return refillsRemaining;
    }

    public void setRefillsRemaining(Integer refillsRemaining) {
        this.refillsRemaining = refillsRemaining;
    }

    @Transient
    public String getPatientOrdMessge() {
        return patientOrdMessge;
    }

    public void setPatientOrdMessge(String patientOrdMessge) {
        this.patientOrdMessge = patientOrdMessge;
    }

    @Transient
    public String getPatientOrdMsgSubject() {
        return patientOrdMsgSubject;
    }

    public void setPatientOrdMsgSubject(String patientOrdMsgSubject) {
        this.patientOrdMsgSubject = patientOrdMsgSubject;
    }

    @Transient
    public Integer getRefillRemainingDaysCount() {
        return refillRemainingDaysCount;
    }

    public void setRefillRemainingDaysCount(Integer refillRemainingDaysCount) {
        this.refillRemainingDaysCount = refillRemainingDaysCount;
    }

    @Transient
    public String getLastFilledDate() {
        return lastFilledDate;
    }

    public void setLastFilledDate(String lastFilledDate) {
        this.lastFilledDate = lastFilledDate;
    }

    @Transient
    public Integer getPatientOrdMsgId() {
        return patientOrdMsgId;
    }

    public void setPatientOrdMsgId(Integer patientOrdMsgId) {
        this.patientOrdMsgId = patientOrdMsgId;
    }

    @Transient
    public String getOrderPdfDocument() {
        return orderPdfDocument;
    }

    public void setOrderPdfDocument(String orderPdfDocument) {
        this.orderPdfDocument = orderPdfDocument;
    }

    @Transient
    public String getOrderDocumentMessgeSub() {
        return orderDocumentMessgeSub;
    }

    public void setOrderDocumentMessgeSub(String orderDocumentMessgeSub) {
        this.orderDocumentMessgeSub = orderDocumentMessgeSub;
    }

    @Transient
    public String getQuestionAnserImg() {
        return questionAnserImg;
    }

    public void setQuestionAnserImg(String questionAnserImg) {
        this.questionAnserImg = questionAnserImg;
    }

    @Transient
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Transient
    public String getQuesAnswerText() {
        return quesAnswerText;
    }

    public void setQuesAnswerText(String quesAnswerText) {
        this.quesAnswerText = quesAnswerText;
    }

    @Transient
    public Long getAssignSurveyId() {
        return assignSurveyId;
    }

    public void setAssignSurveyId(Long assignSurveyId) {
        this.assignSurveyId = assignSurveyId;
    }

    @Transient
    public String getAssignsurvyId() {
        return assignsurvyId;
    }

    public void setAssignsurvyId(String assignsurvyId) {
        this.assignsurvyId = assignsurvyId;
    }

    @Transient
    public String getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(String assignStatus) {
        this.assignStatus = assignStatus;
    }
        @Transient
    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }
     @Transient
        public Double getAssistanceAuth() {
        return assistanceAuth;
    }

    public void setAssistanceAuth(Double assistanceAuth) {
        this.assistanceAuth = assistanceAuth;
    }
    @Column(name = "CreatedBy")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
     @Transient
    public Double getOrgPatientOutOfPocket() {
        return orgPatientOutOfPocket;
    }

    public void setOrgPatientOutOfPocket(Double orgPatientOutOfPocket) {
        this.orgPatientOutOfPocket = orgPatientOutOfPocket;
    }
    @Transient
    public OrderDetailDTO getOrderDto() {
        return orderDto;
    }
    public void setOrderDto(OrderDetailDTO orderDto) {
        this.orderDto = orderDto;
    }

}
