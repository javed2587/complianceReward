package com.ssa.cms.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "MessageType")
public class MessageType extends AuditFields implements java.io.Serializable {

    private Folder folder;
    private MessageTypeId id;
    @NotBlank(message = "Required")
    private String messageTypeTitle;
    private String isActive;
    private String isDelete;
    private Set<CampaignMessages> campaignMessageses = new HashSet<>(0);
    String folderNames;
    @NotEmpty(message = "Required")
    private String type;
    private Boolean associatedType = false;
    @NotNull(message = "Required")
    private Integer sortOrder;
    private List<NotificationMessages> notificationMessagesList;
    private Integer responseRequired;

    public MessageType() {
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "messageTypeId", column
                = @Column(name = "MessageTypeId", nullable = false)),
        @AttributeOverride(name = "folderId", column
                = @Column(name = "FolderId", nullable = false))})
    public MessageTypeId getId() {
        return this.id;
    }

    public void setId(MessageTypeId id) {
        this.id = id;
    }

    @Column(name = "MessageTypeTitle", nullable = false, length = 50)
    public String getMessageTypeTitle() {
        return this.messageTypeTitle;
    }

    public void setMessageTypeTitle(String messageTypeTitle) {
        this.messageTypeTitle = messageTypeTitle;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "messageType")
    public Set<CampaignMessages> getCampaignMessageses() {
        return this.campaignMessageses;
    }

    public void setCampaignMessageses(Set<CampaignMessages> campaignMessageses) {
        this.campaignMessageses = campaignMessageses;
    }

    @Transient
    public String getFolderNames() {
        return folderNames;
    }

    public void setFolderNames(String folderNames) {
        this.folderNames = folderNames;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FolderId", nullable = false, insertable = false, updatable = false)
    public Folder getFolder() {
        return this.folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Column(name = "MessageType", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public Boolean getAssociatedType() {
        return associatedType;
    }

    public void setAssociatedType(Boolean associatedType) {
        this.associatedType = associatedType;
    }

    @Column(name = "SortOrder", nullable = false)
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    @Column(name="ResponseRequired")
    public Integer getResponseRequired() {
        return responseRequired;
    }

    public void setResponseRequired(Integer responseRequired) {
        this.responseRequired = responseRequired;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "messageType")
    public List<NotificationMessages> getNotificationMessagesList() {
        return notificationMessagesList;
    }

    public void setNotificationMessagesList(List<NotificationMessages> notificationMessagesList) {
        this.notificationMessagesList = notificationMessagesList;
    }

}
