/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "reporter_messages")
public class ReporterMessages implements Serializable,Comparable<ReporterMessages>{

    private Integer id;
    private Integer userFrom;
    private Integer userTo;
    private Boolean isRead;
    private String userText;
    private String fromType;
    private Date bookingTime;
    private Date crateAt;
    private Date updateAt;
    private Integer sessionId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_from")
    public Integer getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    @Column(name = "user_to")
    public Integer getUserTo() {
        return userTo;
    }

    public void setUserTo(Integer userTo) {
        this.userTo = userTo;
    }

    @Column(name = "is_read")
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Column(name = "user_text")
    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    @Column(name = "from_type")
    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    @Column(name = "booking_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Column(name = "created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(Date crateAt) {
        this.crateAt = crateAt;
    }

    @Column(name = "updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
      @Column(name = "chat_session_id")
        public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
  @Override
  public int compareTo(ReporterMessages u) {
    if (getCrateAt() == null || u.getCrateAt() == null) {
      return 0;
    }
    return getCrateAt().compareTo(u.getCrateAt());
  }
}
