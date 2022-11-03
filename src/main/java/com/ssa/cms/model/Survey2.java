/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name = "surveys")
public class Survey2 implements Serializable {
  private Long id;
  private String title;
  private String details;
  private String language;
  private String status;
  private SurveyType2 surveyType;
  private Users user;
  private Date created_At;
  private Date updated_At;
  private List<SurveyQues> questionList;
  private SurveyTheme theme;

  public Survey2()
  {}
  
  public Survey2(Long id)
  {
      this.id=id;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  @Column(name="title")  
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Column(name="survey_language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, insertable = true, updatable = true)
    public SurveyType2 getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(SurveyType2 surveyType) {
        this.surveyType = surveyType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = true)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) { 
        this.user = user;
    }

    @Column(name="created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }
    
    @Column(name="updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "survey")
    @OrderBy(clause="survey asc")
    public List<SurveyQues> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<SurveyQues> questionList) {
        this.questionList = questionList;
    }

    
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "survey")
    public SurveyTheme getTheme() {
        return theme;
    }

    public void setTheme(SurveyTheme theme) {
        this.theme = theme;
    }
  
  
}
