package com.ssa.cms.model;
// Generated Mar 28, 2013 7:45:08 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NotificationStatus")
public class NotificationStatus implements java.io.Serializable {

    private Integer id;
    private String statusCode;
    private Date effectiveDate;
    private Date endDate;
    private String narrative;
    private String phoneNumber;

    public NotificationStatus() {
    }

    public NotificationStatus(String statusCode, Date effectiveDate, String narrative) {
        this.statusCode = statusCode;
        this.effectiveDate = effectiveDate;
        this.narrative = narrative;
    }

    public NotificationStatus(String statusCode, Date effectiveDate, Date endDate, String narrative) {
        this.statusCode = statusCode;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.narrative = narrative;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date", nullable = false)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "End_Date")
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Narrative")
    public String getNarrative() {
        return this.narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "Status_Code")
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
