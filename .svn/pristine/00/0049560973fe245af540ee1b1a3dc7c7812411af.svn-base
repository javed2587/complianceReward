package com.ssa.cms.model;
// Generated Apr 9, 2013 10:00:02 PM by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Event", uniqueConstraints
        = @UniqueConstraint(columnNames = "EventTitle"))
public class Event extends AuditFields implements java.io.Serializable {

    private Integer eventId;
    @NotBlank(message = "Required")
    private String eventTitle;
    private String eventCriteria;
    private String staticValue;
    private String dynamicValue;
    private String brandIds;

    private Set<FolderHasCampaigns> folderHasCampaignses = new HashSet<>(0);
    private List<EventDetail> eventDetails = new ArrayList<>();

    private List<String> brandName;

    public Event() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "EventId", unique = true, nullable = false)
    public Integer getEventId() {
        return this.eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Column(name = "EventTitle", unique = true, nullable = false, length = 100)
    public String getEventTitle() {
        return this.eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Column(name = "EventCriteria", nullable = false, length = 7)
    public String getEventCriteria() {
        return this.eventCriteria;
    }

    public void setEventCriteria(String eventCriteria) {
        this.eventCriteria = eventCriteria;
    }

    @Column(name = "StaticValue", length = 50)
    public String getStaticValue() {
        return this.staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }

    @Column(name = "DynamicValue", length = 11)
    public String getDynamicValue() {
        return this.dynamicValue;
    }

    public void setDynamicValue(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "events")
    public Set<FolderHasCampaigns> getFolderHasCampaignses() {
        return this.folderHasCampaignses;
    }

    public void setFolderHasCampaignses(Set<FolderHasCampaigns> folderHasCampaignses) {
        this.folderHasCampaignses = folderHasCampaignses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    @OrderBy(clause = "EventDetailId asc")
    public List<EventDetail> getEventDetails() {
        return this.eventDetails;
    }

    public void setEventDetails(List<EventDetail> eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Column(name = "BrandIds", length = 100)
    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    @Transient
    public List<String> getBrandName() {
        return brandName;
    }

    public void setBrandName(List<String> brandName) {
        this.brandName = brandName;
    }

}
