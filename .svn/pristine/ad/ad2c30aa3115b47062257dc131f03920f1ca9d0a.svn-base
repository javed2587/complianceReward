package com.ssa.cms.model;
// Generated Apr 16, 2013 7:26:01 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Campaigns", uniqueConstraints = @UniqueConstraint(columnNames = "CampaignName"))

public class Campaigns extends AuditFields implements java.io.Serializable {

    private Integer campaignId;

    private ShortCodes shortCodes;
    private Industry industry;
    private SmtpServerInfo smtpServerInfo;

    @NotBlank(message = "Required")
    private String campaignName;

    private String campaignType;
    private int refillProcessTiming;
    private Integer pADeniedRequestTime;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lanchDateTime;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date terminationDateTime;
    private byte[] campaignLogo;
    private String campaignLogoName;
    private String campaignLogoType;
    private String isPa;
    private String isRefill;
    private String isRepeatRefill;
    private String isSeePhysician;
    private String isMaxBenefit;
    private String isMemberId;
    private String isValidateMemberId;
    private String isRefillFailure;
    private String isRefillOrderReminder;
    private Integer redemptionLimit;
    private Integer pendingInterval;
    private Integer pendingCount;
    private BigDecimal maxBenefitAmount;
    private String isClinicalMsgs;
    private Integer clinicalMsgsCount;
    private Integer clinicalMsgsTime;
    private String isActive;
    private String isDelete;
    private int brandId;
    private List<DrugBrand> drugBrands;

    private Integer[] selectedDrugBrands;
    @Valid
    @NotNull
    @Size(min = 1)
    private List<CampaignTrigger> triggers;

    //private RedemptionChannel redemptionChannel;

    private List<FolderHasCampaigns> folderHasCampaignses;
    private List<CampaignMessages> campaignMessageses;

    //private Set<InstantRequest> instantRequests = new HashSet<InstantRequest>(0);

    private List<String> selectedOptions;
    private String showFolder;
    private Survey survey;
    private Integer campId;
    private String totalResponse;
    private String questionTitle;
    private String responseTitle;
    private Integer srno;
    private String surveyId;
    private String grandTotal;

    public Campaigns() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CampaignId", unique = true, nullable = false)
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShortCode", nullable = false)
    public ShortCodes getShortCodes() {
        return this.shortCodes;
    }

    public void setShortCodes(ShortCodes shortCodes) {
        this.shortCodes = shortCodes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IndustryId", nullable = false)
    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    @ManyToOne
    @JoinColumn(name = "SmtpId", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    public SmtpServerInfo getSmtpServerInfo() {
        return this.smtpServerInfo;
    }

    public void setSmtpServerInfo(SmtpServerInfo smtpServerInfo) {
        this.smtpServerInfo = smtpServerInfo;
    }

//    @ManyToOne
//    @JoinColumn(name = "RedemptionChannelId", nullable = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    public RedemptionChannel getRedemptionChannel() {
//        return this.redemptionChannel;
//    }
//
//    public void setRedemptionChannel(RedemptionChannel redemptionChannel) {
//        this.redemptionChannel = redemptionChannel;
//    }

    @Column(name = "CampaignName", unique = true, nullable = false)
    public String getCampaignName() {
        return this.campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Column(name = "CampaignType", nullable = false, length = 11)
    public String getCampaignType() {
        return this.campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    @Column(name = "RefillProcessTiming")
    public int getRefillProcessTiming() {
        return this.refillProcessTiming;
    }

    public void setRefillProcessTiming(int refillProcessTiming) {
        this.refillProcessTiming = refillProcessTiming;
    }

    @Column(name = "PADeniedRequestTime")
    public Integer getpADeniedRequestTime() {
        return pADeniedRequestTime;
    }

    public void setpADeniedRequestTime(Integer pADeniedRequestTime) {
        this.pADeniedRequestTime = pADeniedRequestTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LanchDateTime", nullable = false, length = 19)
    public Date getLanchDateTime() {
        return this.lanchDateTime;
    }

    public void setLanchDateTime(Date lanchDateTime) {
        this.lanchDateTime = lanchDateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TerminationDateTime", nullable = false, length = 19)
    public Date getTerminationDateTime() {
        return this.terminationDateTime;
    }

    public void setTerminationDateTime(Date terminationDateTime) {
        this.terminationDateTime = terminationDateTime;
    }

    @Column(name = "CampaignLogo")
    public byte[] getCampaignLogo() {
        return this.campaignLogo;
    }

    public void setCampaignLogo(byte[] campaignLogo) {
        this.campaignLogo = campaignLogo;
    }

    @Column(name = "CampaignLogoName", length = 500)
    public String getCampaignLogoName() {
        return this.campaignLogoName;
    }

    public void setCampaignLogoName(String campaignLogoName) {
        this.campaignLogoName = campaignLogoName;
    }

    @Column(name = "CampaignLogoType", length = 20)
    public String getCampaignLogoType() {
        return this.campaignLogoType;
    }

    public void setCampaignLogoType(String campaignLogoType) {
        this.campaignLogoType = campaignLogoType;
    }

    @Column(name = "IsPA", length = 4)
    public String getIsPa() {
        return this.isPa;
    }

    public void setIsPa(String isPa) {
        this.isPa = isPa;
    }

    @Column(name = "IsRefill", length = 4)
    public String getIsRefill() {
        return this.isRefill;
    }

    public void setIsRefill(String isRefill) {
        this.isRefill = isRefill;
    }

    @Column(name = "IsRepeatRefill", length = 4)
    public String getIsRepeatRefill() {
        return this.isRepeatRefill;
    }

    public void setIsRepeatRefill(String isRepeatRefill) {
        this.isRepeatRefill = isRepeatRefill;
    }

    @Column(name = "RedemptionLimit")
    public Integer getRedemptionLimit() {
        return this.redemptionLimit;
    }

    public void setRedemptionLimit(Integer redemptionLimit) {
        this.redemptionLimit = redemptionLimit;
    }

    @Column(name = "IsSeePhysician")
    public String getIsSeePhysician() {
        return isSeePhysician;
    }

    public void setIsSeePhysician(String isSeePhysician) {
        this.isSeePhysician = isSeePhysician;
    }

    @Column(name = "IsMaxBenefit")
    public String getIsMaxBenefit() {
        return isMaxBenefit;
    }

    public void setIsMaxBenefit(String isMaxBenefit) {
        this.isMaxBenefit = isMaxBenefit;
    }

    @Column(name = "MaxBenefitAmount", precision = 10)
    public BigDecimal getMaxBenefitAmount() {
        return this.maxBenefitAmount;
    }

    public void setMaxBenefitAmount(BigDecimal maxBenefitAmount) {
        this.maxBenefitAmount = maxBenefitAmount;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDelete", nullable = false, length = 4)
    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaigns")
    @OrderBy(clause = "FolderId asc")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<FolderHasCampaigns> getFolderHasCampaignses() {
        return this.folderHasCampaignses;
    }

    public void setFolderHasCampaignses(List<FolderHasCampaigns> folderHasCampaignses) {
        this.folderHasCampaignses = folderHasCampaignses;
    }

//    @ManyToMany(cascade = CascadeType.REFRESH)
//    @JoinTable(name = "CampaignsHasDrugBrand", joinColumns = {
//        @JoinColumn(name = "CampaignId", nullable = false, updatable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "DrugBrandId", nullable = false, updatable = false)})
//    @LazyCollection(LazyCollectionOption.FALSE)
//
    @Transient
    public List<DrugBrand> getDrugBrands() {
        return this.drugBrands;
    }

    public void setDrugBrands(List<DrugBrand> drugBrands) {
        this.drugBrands = drugBrands;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaigns")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy(clause = "messageId asc")
    public List<CampaignMessages> getCampaignMessageses() {
        return this.campaignMessageses;
    }

    public void setCampaignMessageses(List<CampaignMessages> campaignMessageses) {
        this.campaignMessageses = campaignMessageses;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaigns")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CampaignTrigger> getTriggers() {
        return this.triggers;
    }

    public void setTriggers(List<CampaignTrigger> triggers) {
        this.triggers = triggers;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "campaigns")
//    public Set<InstantRequest> getInstantRequests() {
//        return this.instantRequests;
//    }
//
//    public void setInstantRequests(Set<InstantRequest> instantRequests) {
//        this.instantRequests = instantRequests;
//    }

    @Column(name = "PendingInterval")
    public Integer getPendingInterval() {
        return pendingInterval;
    }

    public void setPendingInterval(Integer pendingInterval) {
        this.pendingInterval = pendingInterval;
    }

    @Column(name = "PendingCount")
    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

    @Column(name = "IsMemberId")
    public String getIsMemberId() {
        return isMemberId;
    }

    public void setIsMemberId(String isMemberId) {
        this.isMemberId = isMemberId;
    }

    @Column(name = "IsValidateMemberId")
    public String getIsValidateMemberId() {
        return isValidateMemberId;
    }

    public void setIsValidateMemberId(String isValidateMemberId) {
        this.isValidateMemberId = isValidateMemberId;
    }

    @Column(name = "isRefillFailure")
    public String getIsRefillFailure() {
        return isRefillFailure;
    }

    /**
     * @param isRefillFailure the isRefillFailure to set
     */
    public void setIsRefillFailure(String isRefillFailure) {
        this.isRefillFailure = isRefillFailure;
    }

    @Column(name = "isRefillOrderReminder")
    public String getIsRefillOrderReminder() {
        return isRefillOrderReminder;
    }

    public void setIsRefillOrderReminder(String isRefillOrderReminder) {
        this.isRefillOrderReminder = isRefillOrderReminder;
    }

    @Column(name = "BrandId", nullable = true)
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Transient
    public Integer[] getSelectedDrugBrands() {
        return selectedDrugBrands;
    }

    public void setSelectedDrugBrands(Integer[] selectedDrugBrands) {
        this.selectedDrugBrands = selectedDrugBrands;
    }

    @Transient
    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    @Column(name = "IsClinicalMsgs")
    public String getIsClinicalMsgs() {
        return isClinicalMsgs;
    }

    public void setIsClinicalMsgs(String isClinicalMsgs) {
        this.isClinicalMsgs = isClinicalMsgs;
    }

    @Column(name = "ClinicalMsgsCount")
    public Integer getClinicalMsgsCount() {
        return clinicalMsgsCount;
    }

    public void setClinicalMsgsCount(Integer clinicalMsgsCount) {
        this.clinicalMsgsCount = clinicalMsgsCount;
    }

    @Column(name = "clinicalMsgsTime")
    public Integer getClinicalMsgsTime() {
        return clinicalMsgsTime;
    }

    public void setClinicalMsgsTime(Integer clinicalMsgsTime) {
        this.clinicalMsgsTime = clinicalMsgsTime;
    }

    @Transient
    public String getShowFolder() {
        return showFolder;
    }

    public void setShowFolder(String showFolder) {
        this.showFolder = showFolder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SurveyId", nullable = false, insertable = true, updatable = true)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Transient
    public Integer getCampId() {
        return campId;
    }

    public void setCampId(Integer campId) {
        this.campId = campId;
    }

    @Transient
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Transient
    public String getResponseTitle() {
        return responseTitle;
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }

    @Transient
    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    @Transient
    public String getTotalResponse() {
        return totalResponse;
    }

    public void setTotalResponse(String totalResponse) {
        this.totalResponse = totalResponse;
    }

    @Transient
    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Transient
    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

}
