package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
@Entity
@Table(name = "SurveyQuestion")
public class SurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Column(name = "Status")
    private String status;
//    @OrderBy(clause = "id asc")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ResponseTypeId", insertable = true, updatable = true)
    @Transient
    private SurveyResponseType surveyResponseType;
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="surveyQuestion")
//    @OrderBy(clause = "id asc")
    @Transient
    private List<SurveyQuestionAssociation> surveyQuestionAssociationList;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyQuestion")
//    @OrderBy(clause = "id asc")
    @Transient
    private List<SurveyUserResponse> surveyUserResponseList;
    @Column(name = "CreatedOn", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedOn;
    @Column(name = "CreatedBy", nullable = false)
    private Integer CreatedBy;
    @Column(name = "LastModifiedBy", nullable = false)
    private Integer lastModifiedBy;
    @Column(name = "LastModifiedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;
    @Transient
    private String associationFlag;
    @Transient
    private Integer selectedResponse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Survey_Id")
    private Survey survey;

   

    public SurveyQuestion() {
    }

    public SurveyQuestion(Integer id) {
        this.id = id;
    }

    public SurveyQuestion(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SurveyResponseType getSurveyResponseType() {
        return surveyResponseType;
    }

    public void setSurveyResponseType(SurveyResponseType surveyResponseType) {
        this.surveyResponseType = surveyResponseType;
    }

    public List<SurveyQuestionAssociation> getSurveyQuestionAssociationList() {
        return surveyQuestionAssociationList;
    }

    public void setSurveyQuestionAssociationList(List<SurveyQuestionAssociation> surveyQuestionAssociationList) {
        this.surveyQuestionAssociationList = surveyQuestionAssociationList;
    }

    public List<SurveyUserResponse> getSurveyUserResponseList() {
        return surveyUserResponseList;
    }

    public void setSurveyUserResponseList(List<SurveyUserResponse> surveyUserResponseList) {
        this.surveyUserResponseList = surveyUserResponseList;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public Integer getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Integer CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public String getAssociationFlag() {
        return associationFlag;
    }

    public void setAssociationFlag(String associationFlag) {
        this.associationFlag = associationFlag;
    }

    public Integer getSelectedResponse() {
        return selectedResponse;
    }

    public void setSelectedResponse(Integer selectedResponse) {
        this.selectedResponse = selectedResponse;
    }

     public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

}
