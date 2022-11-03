package com.ssa.cms.model;
import java.util.Date;

public class Enrollment {

	private long enrollmentId;
        private String communicationId;
        private String communicationMethod;
	private String communicationSourceCode;
	private Date effectiveDate;
	private String enrollmentPath;
	
	
	/**
	 * @return the enrollmentId
	 */
	public long getEnrollmentId() {
		return enrollmentId;
	}
	/**
	 * @param enrollmentId the enrollmentId to set
	 */
	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	/**
	 * @return the communicationSourceCode
	 */
	public String getCommunicationSourceCode() {
		return communicationSourceCode;
	}
	/**
	 * @param communicationSourceCode the communicationSourceCode to set
	 */
	public void setCommunicationSourceCode(String communicationSourceCode) {
		this.communicationSourceCode = communicationSourceCode;
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
	 * @return the enrollmentPath
	 */
	public String getEnrollmentPath() {
		return enrollmentPath;
	}
	/**
	 * @param enrollmentPath the enrollmentPath to set
	 */
	public void setEnrollmentPath(String enrollmentPath) {
		this.enrollmentPath = enrollmentPath;
	}

    public String getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }

    public String getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }
	
	
}
