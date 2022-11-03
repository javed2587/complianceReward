package com.ssa.cms.model;

/**
 *
 * @author mzubair
 */
public class JsonResponse {

    private Object data;
    private int errorCode;
    private String errorMessage;
    private String successMessage;
    private long totalRecords;
    private int offset;
    private int errorCodeType;
    private String yearEndStatementPdfFile;
    private String yearEndStatementLink;
    private Integer patientId;
    private String surveyId;
    private int successCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getErrorCodeType() {
        return errorCodeType;
    }

    public void setErrorCodeType(int errorCodeType) {
        this.errorCodeType = errorCodeType;
    }

    public String getYearEndStatementPdfFile() {
        return yearEndStatementPdfFile;
    }

    public void setYearEndStatementPdfFile(String yearEndStatementPdfFile) {
        this.yearEndStatementPdfFile = yearEndStatementPdfFile;
    }

    public String getYearEndStatementLink() {
        return yearEndStatementLink;
    }

    public void setYearEndStatementLink(String yearEndStatementLink) {
        this.yearEndStatementLink = yearEndStatementLink;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
        public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
        public int getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(int successCode) {
        this.successCode = successCode;
    }

}
