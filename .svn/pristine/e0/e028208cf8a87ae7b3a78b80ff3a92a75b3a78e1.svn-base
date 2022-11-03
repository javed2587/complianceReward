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
//@Entity
//@Table(name = "survey_response_detail")
public class SurveyResponseDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Transient
    private String title;

    @Transient
    private String status;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedOn;

    @Transient
    private Integer CreatedBy;

    @Transient
    private Integer lastModifiedBy;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;
    @OrderBy(clause = "title asc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SurveyResponseTypeId", nullable = false, insertable = true, updatable = true)
    private SurveyResponseType surveyResponseType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyResponseDetail")
    @OrderBy(clause = "id asc")
    private List<SurveyUserResponse> surveyUserResponseList;

    @Transient
    int remove;
    @Transient
    private Boolean associated;

    public SurveyResponseDetail() {
    }

    public SurveyResponseDetail(Integer id) {
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

    public SurveyResponseType getSurveyResponseType() {
        return surveyResponseType;
    }

    public void setSurveyResponseType(SurveyResponseType surveyResponseType) {
        this.surveyResponseType = surveyResponseType;
    }

    public List<SurveyUserResponse> getSurveyUserResponseList() {
        return surveyUserResponseList;
    }

    public void setSurveyUserResponseList(List<SurveyUserResponse> surveyUserResponseList) {
        this.surveyUserResponseList = surveyUserResponseList;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public Boolean getAssociated() {
        return associated;
    }

    public void setAssociated(Boolean associated) {
        this.associated = associated;
    }
}
