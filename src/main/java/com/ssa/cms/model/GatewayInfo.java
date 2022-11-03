package com.ssa.cms.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GatewayInfo")
public class GatewayInfo implements java.io.Serializable {

    // Fields
    private Integer shortCode;
    private String gkurl;
    private String mtsurl;
    private String userId;
    private String secKey;
    private String appCode;
    private Date effectiveDate;
    private Date endDate;
    private String statusServiceURL;
    private String webQueryURL;
    private String mmsurl;

    @Column(name = "Web_Query_URL", length = 250)
    public String getWebQueryURL() {
        return webQueryURL;
    }

    public void setWebQueryURL(String webQueryURL) {
        this.webQueryURL = webQueryURL;
    }

    
    public GatewayInfo() {
    }

    
    public GatewayInfo(Integer shortCode, String gkurl, String mtsurl,
            String secKey, Date effectiveDate) {
        this.shortCode = shortCode;
        this.gkurl = gkurl;
        this.mtsurl = mtsurl;
        this.secKey = secKey;
        this.effectiveDate = effectiveDate;
    }

   
    public GatewayInfo(Integer shortCode, String gkurl, String mtsurl,
            String userId, String secKey, Date effectiveDate,
            Date endDate) {
        this.shortCode = shortCode;
        this.gkurl = gkurl;
        this.mtsurl = mtsurl;
        this.userId = userId;
        this.secKey = secKey;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
    }

    // Property accessors
    @Id
    @Column(name = "Short_Code", unique = true, nullable = false)
    public Integer getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }

    @Column(name = "GKURL", nullable = false, length = 250)
    public String getGkurl() {
        return this.gkurl;
    }

    public void setGkurl(String gkurl) {
        this.gkurl = gkurl;
    }

    @Column(name = "MTSURL", nullable = false, length = 250)
    public String getMtsurl() {
        return this.mtsurl;
    }

    public void setMtsurl(String mtsurl) {
        this.mtsurl = mtsurl;
    }

    @Column(name = "User_Id", length = 50)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "Sec_Key", nullable = false, length = 100)
    public String getSecKey() {
        return this.secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
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
    @Column(name = "End_Date", length = 19)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    
    @Column(name = "App_Code", nullable = true, length = 25)
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    
    @Column(name = "Gateway_Status_Service_URL", nullable = false, length = 250)
    public String getStatusServiceURL() {
        return statusServiceURL;
    }

    public void setStatusServiceURL(String statusServiceURL) {
        this.statusServiceURL = statusServiceURL;
    }

    
    @Column(name = "MMS_URL", length = 250)
    public String getMmsurl() {
        return mmsurl;
    }

    public void setMmsurl(String mmsurl) {
        this.mmsurl = mmsurl;
    }
}