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
import static javax.persistence.GenerationType.IDENTITY;
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
@Table(name="survey_questions")
public class SurveyQues implements Serializable {
    private Long id;
    private String questionCode;
    private String questionTitle;
    private String description;
    private Boolean isrequired;
    private Boolean displayInline;
    private String inputType;
    private Integer questionOrder;
    private QuestionType questionType;
    private Date createdAt;
    private Date updatedAt;
//    private Date deletedAt;
    private Survey2 survey;
    private List<QuestionOption> optionList;
     private List<SurveyResponseAnswerDetial> answerList;



    public SurveyQues() {
    }
    
    public SurveyQues(Long id)
    {
     this.id=id;   
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="question_code")
    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    @Column(name="question_title")
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="is_required")
    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    @Column(name="display_inline")
    public Boolean getDisplayInline() {
        return displayInline;
    }

    public void setDisplayInline(Boolean displayInline) {
        this.displayInline = displayInline;
    }

    @Column(name="input_type")
    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    @Column(name="question_order")
    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

     @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id", insertable = true, updatable = true, nullable = true)
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Column(name="created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name="updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @Column(name="deleted_at")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
//    public Date getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Date deletedAt) {
//        this.deletedAt = deletedAt;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = true, insertable = true, updatable = true)
    public Survey2 getSurvey() {
        return survey;
    }

    public void setSurvey(Survey2 survey) {
        this.survey = survey;
    }

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "surveyQuestion")
   // @OrderBy(clause="surveyQuestion asc")
    public List<QuestionOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<QuestionOption> optionList) {
        this.optionList = optionList;
    }
//    @OneToMany(cascade=CascadeType.ALL,mappedBy = "SurveyResponseAnswerDetial")
//    public List<SurveyResponseAnswerDetial> getAnswerList() {
//        return answerList;
//    }
//
//    public void setAnswerList(List<SurveyResponseAnswerDetial> answerList) {
//        this.answerList = answerList;
//    }
//    
}
