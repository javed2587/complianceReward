package com.ssa.cms.model;

import com.ssa.cms.modelinterfaces.CustomerRequestFunctionalityI;
import com.ssa.cms.modellisteners.CustomerRequestListener;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mzubair
 *
 *
 */
@Entity
@Table(name = "OptInOut")
@EntityListeners(CustomerRequestListener.class)
public class OptInOut implements CustomerRequestFunctionalityI, java.io.Serializable {

    private static final long serialVersionUID = 4345689704443604613L;
    private Long crSeqNo;
    private String phoneNumber;
    private Integer campaignId;
    private String campaignName;
    private String statusCode;
    private String optInOut;
    private Date effectiveDate;
    private Integer shortCode;

    // Constructors
    /**
     * default constructor
     */
    public OptInOut() {
    }

    /**
     * minimal constructor
     */
    public OptInOut(Long crSeqNo, String phoneNumber, String optInOut,
            Date effectiveDate) {
        this.crSeqNo = crSeqNo;
        this.phoneNumber = phoneNumber;
        this.optInOut = optInOut;
        this.effectiveDate = effectiveDate;
    }

    /**
     * full constructor
     */
    public OptInOut(Long crSeqNo, String phoneNumber, Integer campaignId,
            String campaignName, String statusCode, String optInOut,
            Date effectiveDate, Integer shortCode) {
        this.crSeqNo = crSeqNo;
        this.phoneNumber = phoneNumber;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.statusCode = statusCode;
        this.optInOut = optInOut;
        this.effectiveDate = effectiveDate;
        this.shortCode = shortCode;
    }

    // Property accessors
    @Id
    @Column(name = "CR_Seq_No", unique = true, nullable = false)
    public Long getCrSeqNo() {
        return this.crSeqNo;
    }

    public void setCrSeqNo(Long crSeqNo) {
        this.crSeqNo = crSeqNo;
    }

    @Column(name = "Phone_Number", nullable = false, length = 10)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "Campaign_Id")
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name = "Campaign_Name")
    public String getCampaignName() {
        return this.campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Column(name = "Status_Code", length = 2)
    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Column(name = "Opt_In_Out", nullable = false, length = 1)
    public String getOptInOut() {
        return this.optInOut;
    }

    public void setOptInOut(String optInOut) {
        this.optInOut = optInOut;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date", nullable = false, length = 19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "Short_Code")
    public Integer getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }
}
