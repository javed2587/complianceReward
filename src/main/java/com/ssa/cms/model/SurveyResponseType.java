package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
//@Entity
//@Table(name = "SurveyResponseType")
public class SurveyResponseType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyResponseType")
    @OrderBy(clause = "id asc")
    private List<SurveyResponseDetail> surveyResponseDetails;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyResponseType")
//    @OrderBy(clause = "title asc")
    @Transient
    private List<SurveyQuestion> surveyQuestionList;
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
    private Integer selectedId;
    @Transient
    private Boolean associated;

    public SurveyResponseType() {
    }

    public SurveyResponseType(Integer id) {
        this.id = id;
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

    public List<SurveyResponseDetail> getSurveyResponseDetails() {
        return surveyResponseDetails;
    }

    public void setSurveyResponseDetails(List<SurveyResponseDetail> surveyResponseDetails) {
        this.surveyResponseDetails = surveyResponseDetails;
    }

    public Integer getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Integer selectedId) {
        this.selectedId = selectedId;
    }

    public List<SurveyQuestion> getSurveyQuestionList() {
        return surveyQuestionList;
    }

    public void setSurveyQuestionList(List<SurveyQuestion> surveyQuestionList) {
        this.surveyQuestionList = surveyQuestionList;
    }

    public Boolean getAssociated() {
        return associated;
    }

    public void setAssociated(Boolean associated) {
        this.associated = associated;
    }
}
