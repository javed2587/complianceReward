package com.ssa.decorator;

public class MTDecorator {

    private boolean sent;
    private String requestXML;
    private String responseXML;
    private String statusCode;
    private String statusDescription;
    private String errorCode;
    private String errorDescription;
    private String ticketId;
    private String messageText;
    private String mtsId;

    @Override
    public String toString() {
        String toStr = errorCode;

        toStr = toStr + "\n";
        toStr = toStr + errorDescription;
        toStr = toStr + "\n";
        toStr = toStr + requestXML;
        toStr = toStr + "\n";
        toStr = toStr + responseXML;

        toStr = toStr + "\n";
        toStr = toStr + mtsId;

        return toStr;
    }

    /**
     * @return the mtsId
     */
    public String getMtsId() {
        return mtsId;
    }

    /**
     * @param mtsId the mtsId to set
     */
    public void setMtsId(String mtsId) {
        this.mtsId = mtsId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getRequestXML() {
        return requestXML;
    }

    public void setRequestXML(String requestXML) {
        this.requestXML = requestXML;
    }

    public String getResponseXML() {
        return responseXML;
    }

    public void setResponseXML(String responseXML) {
        this.responseXML = responseXML;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * @return the messageText
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

}
