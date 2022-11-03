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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author msheraz
 */
@Entity
@Table(name = "Survey")
public class Survey extends AuditFields implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private PatientProfile PatientProfile;
    private String title;
    private String surveyDetail;
    private String surveyImg;
    private String status;
    private Date sentOn;
    private List<SurveyQuestionAssociation> surveyQuestionList;
    private List<SurveyUser> surveyUserList;
    private List<SurveyUserResponse> surveyUserResponseList;
    private List<SurveyToSurveyTypeAssociation> surveyToSurveyTypeAssociation;
    private List<Integer> selectedSurveyType;
    private String commaSeparatedSurveyTypes;
    private Long noOfQuestions;
    private String testMail;
    private List<Integer> selectedQuestionIds;
    private String communicationId;
    private List<Campaigns> campaignses;
    private Boolean isAssociated;
    private String userResponse;
    private Date deletedAt;


    public Survey() {
    }

    public Survey(Integer id) {
        this.id = id;
    }

    public Survey(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "Fk_PatientId")
     public PatientProfile getPatientProfile() {
        return PatientProfile;
    }

    public void setPatientProfile(PatientProfile PatientProfile) {
        this.PatientProfile = PatientProfile;
    }
 @Column(name = "Survey_Detail")
    public String getSurveyDetail() {
        return surveyDetail;
    }

    public void setSurveyDetail(String surveyDetail) {
        this.surveyDetail = surveyDetail;
    }
 @Column(name = "Survey_Img")
    public String getSurveyImg() {
        return surveyImg;
    }

    public void setSurveyImg(String surveyImg) {
        this.surveyImg = surveyImg;
    }

    @Column(name = "Title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "SentOn")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OrderBy(clause = "id asc")
    @Transient
    public List<SurveyQuestionAssociation> getSurveyQuestionList() {
        return surveyQuestionList;
    }

    public void setSurveyQuestionList(List<SurveyQuestionAssociation> surveyQuestionList) {
        this.surveyQuestionList = surveyQuestionList;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
//    @OrderBy(clause = "id asc")
    @Transient
    public List<SurveyUser> getSurveyUserList() {
        return surveyUserList;
    }

    public void setSurveyUserList(List<SurveyUser> surveyUserList) {
        this.surveyUserList = surveyUserList;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
//    @OrderBy(clause = "id asc")
    @Transient
    public List<SurveyUserResponse> getSurveyUserResponseList() {
        return surveyUserResponseList;
    }

    public void setSurveyUserResponseList(List<SurveyUserResponse> surveyUserResponseList) {
        this.surveyUserResponseList = surveyUserResponseList;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
//    @OrderBy(clause = "id asc")
    @Transient
    public List<SurveyToSurveyTypeAssociation> getSurveyToSurveyTypeAssociation() {
        return surveyToSurveyTypeAssociation;
    }

    public void setSurveyToSurveyTypeAssociation(List<SurveyToSurveyTypeAssociation> surveyToSurveyTypeAssociation) {
        this.surveyToSurveyTypeAssociation = surveyToSurveyTypeAssociation;
    }

    @Transient
    public List<Integer> getSelectedSurveyType() {
        return selectedSurveyType;
    }

    public void setSelectedSurveyType(List<Integer> selectedSurveyType) {
        this.selectedSurveyType = selectedSurveyType;
    }

    @Transient
    public String getCommaSeparatedSurveyTypes() {
        return commaSeparatedSurveyTypes;
    }

    public void setCommaSeparatedSurveyTypes(String commaSeparatedSurveyTypes) {
        this.commaSeparatedSurveyTypes = commaSeparatedSurveyTypes;
    }

    @Transient
    public Long getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(Long noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    @Transient
    public String getTestMail() {
        return testMail;
    }

    public void setTestMail(String testMail) {
        this.testMail = testMail;
    }

    @Transient
    public List<Integer> getSelectedQuestionIds() {
        return selectedQuestionIds;
    }

    public void setSelectedQuestionIds(List<Integer> selectedQuestionIds) {
        this.selectedQuestionIds = selectedQuestionIds;
    }

    @Transient
    public String getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
//    @OrderBy(clause = "id asc")
    @Transient
    public List<Campaigns> getCampaignses() {
        return campaignses;
    }

    public void setCampaignses(List<Campaigns> campaignses) {
        this.campaignses = campaignses;
    }

    @Transient
    public Boolean getAssociated() {
        return isAssociated;
    }

    public void setAssociated(Boolean isAssociated) {
        this.isAssociated = isAssociated;
    }

    @Transient
    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }
     @Column(name = "deleted_at")
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
