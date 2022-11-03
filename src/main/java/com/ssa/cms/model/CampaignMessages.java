package com.ssa.cms.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "CampaignMessages")
public class CampaignMessages extends AuditFields implements java.io.Serializable {

    private Integer messageId;
    private MessageType messageType;
    private Campaigns campaigns;
    private String smstext;
    private String smsWebText;
    private String subject;
    private String isActive;
    private String isDelete;
    private String isGroup;

    private Integer messageTypeId;
    private boolean messageTypeIdSelected;
    private Integer isCritical;
    private List<CampaignMessagesResponse> campaignMessagesResponses;
    private String requiredPatientResponse;
    private String pushNotification;

    public CampaignMessages() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MessageId", unique = true, nullable = false)
    public Integer getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "MessageTypeId", referencedColumnName = "MessageTypeId", nullable = false),
        @JoinColumn(name = "FolderId", referencedColumnName = "FolderId", nullable = false)})
    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampaignId", nullable = false)
    public Campaigns getCampaigns() {
        return this.campaigns;
    }

    public void setCampaigns(Campaigns campaigns) {
        this.campaigns = campaigns;
    }

    @Column(name = "SMSText")
    public String getSmstext() {
        return this.smstext;
    }

    public void setSmstext(String smstext) {
        this.smstext = smstext;
    }

    @Column(name = "SmsWebText")
    public String getSmsWebText() {
        return smsWebText;
    }

    public void setSmsWebText(String smsWebText) {
        this.smsWebText = smsWebText;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignMessages")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CampaignMessagesResponse> getCampaignMessagesResponses() {
        return this.campaignMessagesResponses;
    }

    public void setCampaignMessagesResponses(List<CampaignMessagesResponse> campaignMessagesResponses) {
        this.campaignMessagesResponses = campaignMessagesResponses;
    }

    @Column(name = "IsGroup", length = 4)
    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    @Column(name = "MessageTypeId", nullable = false, insertable = false, updatable = false)
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Transient
    public boolean isMessageTypeIdSelected() {
        if (this.messageTypeId != null) {
            messageTypeIdSelected = true;
        }
        return messageTypeIdSelected;
    }

    public void setMessageTypeIdSelected(boolean messageTypeIdSelected) {
        this.messageTypeIdSelected = messageTypeIdSelected;
    }

    @Column(name = "Subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "IsCritical")
    public Integer getIsCritical() {
        return isCritical;
    }

    public void setIsCritical(Integer isCritical) {
        this.isCritical = isCritical;
    }

    @Column(name = "RequiredPatientResponse")
    public String getRequiredPatientResponse() {
        return requiredPatientResponse;
    }

    public void setRequiredPatientResponse(String requiredPatientResponse) {
        this.requiredPatientResponse = requiredPatientResponse;
    }

    @Column(name = "PushNotification")
    public String getPushNotification() {
        return pushNotification;
    }

    public void setPushNotification(String pushNotification) {
        this.pushNotification = pushNotification;
    }
    
    

}
