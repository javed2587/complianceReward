package com.ssa.cms.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Folder")
public class Folder extends AuditFields implements java.io.Serializable {

    private Integer folderId;
    @NotBlank(message = "Required")
    private String folderName;
    private String isActive;
    private String isDelete;
    private Set<FolderHasCampaigns> folderHasCampaignses = new HashSet<>(0);
    private List<MessageType> messageTypes;

    public Folder() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FolderId", unique = true, nullable = false)
    public Integer getFolderId() {
        return this.folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    @Column(name = "FolderName", nullable = false, length = 50)
    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "folder")
    public Set<FolderHasCampaigns> getFolderHasCampaignses() {
        return this.folderHasCampaignses;
    }

    public void setFolderHasCampaignses(Set<FolderHasCampaigns> folderHasCampaignses) {
        this.folderHasCampaignses = folderHasCampaignses;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "folder")
    @OrderBy(clause = "folderId asc,sortOrder asc")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<MessageType> getMessageTypes() {
        return this.messageTypes;
    }

    public void setMessageTypes(List<MessageType> messageTypes) {
        this.messageTypes = messageTypes;
    }
}
