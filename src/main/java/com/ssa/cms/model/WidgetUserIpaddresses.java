package com.ssa.cms.model;
// Generated Jun 25, 2013 5:28:02 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.OrderBy;

//@Entity
//@Table(name = "WidgetUserIPAddresses")
public class WidgetUserIpaddresses implements java.io.Serializable {

    private Integer widgetUserIpaddressId;
    private WidgetUser widgetUser;
    private String ipAddress;
    int remove;

    public WidgetUserIpaddresses() {
    }

    public WidgetUserIpaddresses(WidgetUser widgetUser, String ipAddress) {
        this.widgetUser = widgetUser;
        this.ipAddress = ipAddress;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "WidgetUserIPAddressId", unique = true, nullable = false)

    public Integer getWidgetUserIpaddressId() {
        return this.widgetUserIpaddressId;
    }

    public void setWidgetUserIpaddressId(Integer widgetUserIpaddressId) {
        this.widgetUserIpaddressId = widgetUserIpaddressId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WidgetUserId", nullable = false)
    @OrderBy(clause = "WidgetUserIPAddressId asc")
    public WidgetUser getWidgetUser() {
        return this.widgetUser;
    }

    public void setWidgetUser(WidgetUser widgetUser) {
        this.widgetUser = widgetUser;
    }

    @Column(name = "IpAddress", nullable = false, length = 15)
    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Transient
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

}
