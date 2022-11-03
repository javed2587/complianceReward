package com.ssa.cms.model;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FolderHasCampaigns")
public class FolderHasCampaigns implements java.io.Serializable {

    private FolderHasCampaignsId id;
    private Campaigns campaigns;
    private Folder folder;
    private Integer[] selectedEvents;
    private Integer groupId;
    private List<Event> events;
    private String communicationPath;

    public FolderHasCampaigns() {
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "fhCid", column = @Column(name = "FhCId", nullable = false)),
        @AttributeOverride(name = "folderId", column = @Column(name = "FolderId", nullable = false)),
        @AttributeOverride(name = "campaignId", column = @Column(name = "CampaignId", nullable = false))})
    public FolderHasCampaignsId getId() {
        return this.id;
    }

    public void setId(FolderHasCampaignsId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampaignId", nullable = false, insertable = false, updatable = false)
    public Campaigns getCampaigns() {
        return this.campaigns;
    }

    public void setCampaigns(Campaigns campaigns) {
        this.campaigns = campaigns;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FolderId", nullable = false, insertable = false, updatable = false)
    public Folder getFolder() {
        return this.folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EventHasFolderHasCampaigns", joinColumns = {
        @JoinColumn(name = "FhCId", nullable = false, updatable = false),
        @JoinColumn(name = "FolderId", nullable = false, updatable = false),
        @JoinColumn(name = "CampaignId", nullable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "EventId", nullable = false, updatable = false)})
    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Transient
    public Integer[] getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(Integer[] selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    @Transient
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Column(name = "communicationPath")
    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }

}
