package com.ssa.cms.model;

import com.ssa.cms.modelinterfaces.CampaignMessageReqResFunctionalityI;
import com.ssa.cms.modellisteners.CampaignMessageReqResListener;
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
 */
@Entity
@Table(name = "CampaignMessageReqRes")
@EntityListeners(CampaignMessageReqResListener.class)
public class CampaignMessageReqRes implements CampaignMessageReqResFunctionalityI, java.io.Serializable {

	private static final long serialVersionUID = -1479300522611145281L;
	private Long cmrSeqNo;
	private Integer campaignId;
	private String campaignName;
	private Integer messageId;
        private int messageTypeId;
        private int folderId;
	private Integer shortCode;
	private String errorCode;
	private String errorDescription;
	private String messageText;
	private String smsRequest;
	private String smsResponse;
	private Date effectiveDate;
	private Date timeStamp;
	//private String reminderCategoryCode;
	private String mtsId;
	private String ticketId;
	private Long inputReferenceNumber;
        

	// Constructors

	/** default constructor */
	public CampaignMessageReqRes() {
	}

	/** minimal constructor */
	public CampaignMessageReqRes(Long cmrSeqNo, Integer shortCode,Date effectiveDate) {
		this.cmrSeqNo = cmrSeqNo;
		this.shortCode = shortCode;
		this.effectiveDate = effectiveDate;
	}

	/** full constructor */
	public CampaignMessageReqRes(Long cmrSeqNo, Integer campaignId,
			String campaignName, Integer messageId, Integer shortCode,
			String errorCode, String errorDescription, String messageText,
			String smsRequest, String smsResponse, Date effectiveDate,
			Date timeStamp, String mtsId,
			String ticketId, Long inputReferenceNumber) {
		this.cmrSeqNo = cmrSeqNo;
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.messageId = messageId;
		this.shortCode = shortCode;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.messageText = messageText;
		this.smsRequest = smsRequest;
		this.smsResponse = smsResponse;
		this.effectiveDate = effectiveDate;
		this.timeStamp = timeStamp;
		this.mtsId = mtsId;
		this.ticketId = ticketId;
		this.inputReferenceNumber = inputReferenceNumber;
	}

	// Property accessors
	@Id
	@Column(name = "CMR_Seq_No", unique = true, nullable = false)
	public Long getCmrSeqNo() {
		return this.cmrSeqNo;
	}

	public void setCmrSeqNo(Long cmrSeqNo) {
		this.cmrSeqNo = cmrSeqNo;
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

	@Column(name = "Message_Id")
	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@Column(name = "Short_Code", nullable = false)
	public Integer getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(Integer shortCode) {
		this.shortCode = shortCode;
	}

	@Column(name = "Error_Code", length = 5)
	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(name = "Error_Description", length = 200)
	public String getErrorDescription() {
		return this.errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Column(name = "Message_Text", length = 200)
	public String getMessageText() {
		return this.messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Column(name = "SMS_Request")
	public String getSmsRequest() {
		return this.smsRequest;
	}

	public void setSmsRequest(String smsRequest) {
		this.smsRequest = smsRequest;
	}

	@Column(name = "SMS_Response")
	public String getSmsResponse() {
		return this.smsResponse;
	}

	public void setSmsResponse(String smsResponse) {
		this.smsResponse = smsResponse;
	}

        
        @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Effective_Date", nullable = false, length = 19)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

        @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Time_Stamp", length = 19)
	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	

	@Column(name = "MTS_ID", length = 20)
	public String getMtsId() {
		return this.mtsId;
	}

	public void setMtsId(String mtsId) {
		this.mtsId = mtsId;
	}

	@Column(name = "Ticket_ID", length = 50)
	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	
	
        @Column(name = "Message_Type_Id", nullable = false)
        public int getMessageTypeId() {
            return messageTypeId;
        }

        public void setMessageTypeId(int messageTypeId) {
            this.messageTypeId = messageTypeId;
        }

        @Column(name = "Folder_Id")
        public int getFolderId() {
            return folderId;
        }
        public void setFolderId(int folderId) {
            this.folderId = folderId;
        }

        @Column(name = "Input_Reference_No")
        public Long getInputReferenceNumber() {
            return inputReferenceNumber;
        }

        public void setInputReferenceNumber(Long inputReferenceNumber) {
            this.inputReferenceNumber = inputReferenceNumber;
        }

}