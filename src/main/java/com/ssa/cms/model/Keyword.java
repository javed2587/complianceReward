package com.ssa.cms.model;

import java.util.Date;

public class Keyword {
	
	
	private String keyCode;
	private String programCode;
	private int userId;
	private String narrative;
	private String forwardURL;
	private String sendWM;
	private String sendTCM;
	private String sendEnrollment;
	private int yesResponseCount;
	private Date effectiveDate;
	private Date endDate;
	
	private String sendAnonymousResponse;
	
	
	/**
	 * @return the keyCode
	 */
	public String getKeyCode() {
		return keyCode;
	}
	/**
	 * @param keyCode the keyCode to set
	 */
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}
	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the narrative
	 */
	public String getNarrative() {
		return narrative;
	}
	/**
	 * @param narrative the narrative to set
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	/**
	 * @return the forwardURL
	 */
	public String getForwardURL() {
		return forwardURL;
	}
	/**
	 * @param forwardURL the forwardURL to set
	 */
	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}
	/**
	 * @return the sendWM
	 */
	public String getSendWM() {
		return sendWM;
	}
	/**
	 * @param sendWM the sendWM to set
	 */
	public void setSendWM(String sendWM) {
		this.sendWM = sendWM;
	}
	/**
	 * @return the sendTCM
	 */
	public String getSendTCM() {
		return sendTCM;
	}
	/**
	 * @param sendTCM the sendTCM to set
	 */
	public void setSendTCM(String sendTCM) {
		this.sendTCM = sendTCM;
	}
	/**
	 * @return the sendEnrollment
	 */
	public String getSendEnrollment() {
		return sendEnrollment;
	}
	/**
	 * @param sendEnrollment the sendEnrollment to set
	 */
	public void setSendEnrollment(String sendEnrollment) {
		this.sendEnrollment = sendEnrollment;
	}
	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the yesResponseCount
	 */
	public int getYesResponseCount() {
		return yesResponseCount;
	}
	/**
	 * @param yesResponseCount the yesResponseCount to set
	 */
	public void setYesResponseCount(int yesResponseCount) {
		this.yesResponseCount = yesResponseCount;
	}
	/**
	 * @return the sendAnonymousResponse
	 */
	public String getSendAnonymousResponse() {
		return sendAnonymousResponse;
	}
	/**
	 * @param sendAnonymousResponse the sendAnonymousResponse to set
	 */
	public void setSendAnonymousResponse(String sendAnonymousResponse) {
		this.sendAnonymousResponse = sendAnonymousResponse;
	}
	
	
	
	
}
