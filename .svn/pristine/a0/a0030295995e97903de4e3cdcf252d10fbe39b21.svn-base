package com.ssa.cms.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author mzubair
 * CouponOffered table no exist in db
 */
//@Entity
//@Table(name = "CouponOffered")
public class CouponOffered implements java.io.Serializable {

	
	private static final long serialVersionUID = 455973430484567681L;
	private Long crSeqNo;
	private String phoneNumber;
	private String keywordCode;
	private Integer campaignId;
	private String campaignName;
	private Date effectiveDate;
	private String cardNumber;

	// Constructors

	/** default constructor */
	public CouponOffered() {
	}

	/** minimal constructor */
	public CouponOffered(Long crSeqNo, String phoneNumber,
			Timestamp effectiveDate) {
		this.crSeqNo = crSeqNo;
		this.phoneNumber = phoneNumber;
		this.effectiveDate = effectiveDate;
	}

	/** full constructor */
	public CouponOffered(Long crSeqNo, String phoneNumber, String keywordCode,
			Integer campaignId, String campaignName, Timestamp effectiveDate,
			String cardNumber) {
		this.crSeqNo = crSeqNo;
		this.phoneNumber = phoneNumber;
		this.keywordCode = keywordCode;
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.effectiveDate = effectiveDate;
		this.cardNumber = cardNumber;
	}

	// Property accessors
//	@Id
//	@Column(name = "CR_Seq_No", unique = true, nullable = false)
	public Long getCrSeqNo() {
		return this.crSeqNo;
	}

	public void setCrSeqNo(Long crSeqNo) {
		this.crSeqNo = crSeqNo;
	}

//	@Column(name = "Phone_Number", nullable = false, length = 10)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	@Column(name = "Keyword_Code", length = 10)
	public String getKeywordCode() {
		return this.keywordCode;
	}

	public void setKeywordCode(String keywordCode) {
		this.keywordCode = keywordCode;
	}

//	@Column(name = "Campaign_Id")
	public Integer getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

//	@Column(name = "Campaign_Name")
	public String getCampaignName() {
		return this.campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

//        @Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "Effective_Date", nullable = false, length = 19)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

//	@Column(name = "Card_Number", length = 20)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}