package com.ssa.cms.model;
// Generated Apr 23, 2013 6:26:58 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EventHasFolderHasCampaigns")
public class EventHasFolderHasCampaigns implements java.io.Serializable {

    private Integer fhCid;
    private int folderId;
    private int campaignId;
    private int eventId;
    private String communicationPath;

    public EventHasFolderHasCampaigns() {
    }

    public EventHasFolderHasCampaigns(int folderId, int campaignId, int eventId) {
        this.folderId = folderId;
        this.campaignId = campaignId;
        this.eventId = eventId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "FhCId", unique = true, nullable = false)
    public Integer getFhCid() {
        return this.fhCid;
    }

    public void setFhCid(Integer fhCid) {
        this.fhCid = fhCid;
    }

    @Column(name = "FolderId", nullable = false)
    public int getFolderId() {
        return this.folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Column(name = "CampaignId", nullable = false)
    public int getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "EventId", nullable = false)
    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name = "communicationPath", nullable = false)
    public String getCommunicationPath() {
        return communicationPath;
    }

    public void setCommunicationPath(String communicationPath) {
        this.communicationPath = communicationPath;
    }

}
