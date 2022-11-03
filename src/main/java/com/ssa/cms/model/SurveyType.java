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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
//@Entity
//@Table(name = "SurveyType")
public class SurveyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Short id;
    @Column(name = "Type")
    private String type;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="survey")
    @OrderBy(clause = "id asc")
    private List<SurveyToSurveyTypeAssociation> surveyToSurveyTypeAssociation;
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

    public SurveyType() {
    }

    public SurveyType(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SurveyToSurveyTypeAssociation> getSurveyToSurveyTypeAssociation() {
        return surveyToSurveyTypeAssociation;
    }

    public void setSurveyToSurveyTypeAssociation(List<SurveyToSurveyTypeAssociation> surveyToSurveyTypeAssociation) {
        this.surveyToSurveyTypeAssociation = surveyToSurveyTypeAssociation;
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
}
