/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Javed.Iqbal
 */
@Entity
@Table(name = "QuestionAnswer")
public class QuestionAnswer implements Serializable, Comparable<QuestionAnswer> {

    private Long id;
    private Order order;
    private String question;
    private String answer;
    private Date questionTime;
    private Date answerTime;
    private PatientProfile patientProfile;
    private NotificationMessages notificationMessages;
    private String questionImge;
    private String questionType;
   private Boolean isRead;
    private Date updatedAt;
    private Date createdAt;
    private Long prescriberId;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "Question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "Answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "QuestionTime")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId")
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientprofile) {
        this.patientProfile = patientprofile;
    }

    @Column(name = "AnswerTime")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NotificationMsgId")
    public NotificationMessages getNotificationMessages() {
        return notificationMessages;
    }

    public void setNotificationMessages(NotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }
    @Column(name = "QuestionImage")
    public String getQuestionImge() {
        return questionImge;
    }

    public void setQuestionImge(String questionImge) {
        this.questionImge = questionImge;
    }

    @Column(name = "QuestionType")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
     @Column(name = "IsRead")
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdateAt")
       public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
      public int compareTo(QuestionAnswer u) {
    if (getQuestionTime() == null || u.getQuestionTime() == null) {
      return 0;
    }
    return getQuestionTime().compareTo(u.getQuestionTime());
  }
    @Column(name = "PrescriberId")
    public Long getPrescriberId() {
        return prescriberId;
    }

    public void setPrescriberId(Long prescriberId) {
        this.prescriberId = prescriberId;
    }
}
