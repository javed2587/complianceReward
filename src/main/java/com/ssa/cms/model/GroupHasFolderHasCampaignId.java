package com.ssa.cms.model;
// Generated May 7, 2013 2:13:48 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GroupHasFolderHasCampaignId generated by hbm2java
 */
@Embeddable
public class GroupHasFolderHasCampaignId implements java.io.Serializable {

    private int fhCid;
    private int folderId;
    private int campaignId;
    private int groupId;
    private int campaignMessagesId;

    public GroupHasFolderHasCampaignId() {
    }

    public GroupHasFolderHasCampaignId(int fhCid, int folderId, int campaignId, int groupId, int campaignMessagesId) {
        this.fhCid = fhCid;
        this.folderId = folderId;
        this.campaignId = campaignId;
        this.groupId = groupId;
        this.campaignMessagesId = campaignMessagesId;
    }

    @Column(name = "FhCId", nullable = false)
    public int getFhCid() {
        return this.fhCid;
    }

    public void setFhCid(int fhCid) {
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

    @Column(name = "GroupId", nullable = false)
    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "CampaignMessagesId", nullable = false)
    public int getCampaignMessagesId() {
        return campaignMessagesId;
    }

    public void setCampaignMessagesId(int campaignMessagesId) {
        this.campaignMessagesId = campaignMessagesId;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof GroupHasFolderHasCampaignId)) {
            return false;
        }
        GroupHasFolderHasCampaignId castOther = (GroupHasFolderHasCampaignId) other;

        return (this.getFhCid() == castOther.getFhCid())
                && (this.getFolderId() == castOther.getFolderId())
                && (this.getCampaignId() == castOther.getCampaignId())
                && (this.getGroupId() == castOther.getGroupId())
                && (this.getCampaignMessagesId() == castOther.getCampaignMessagesId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getFhCid();
        result = 37 * result + this.getFolderId();
        result = 37 * result + this.getCampaignId();
        result = 37 * result + this.getGroupId();
        return result;
    }
}