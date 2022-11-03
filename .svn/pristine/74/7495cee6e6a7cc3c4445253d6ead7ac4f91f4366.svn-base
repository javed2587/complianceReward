package com.ssa.cms.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RxMMSRedemptionResponse")
public class RxMMSRedemptionResponse {

    private Integer mmsReqNo;
    private Date effectiveDate;
    private Date endDate;
    private String cmrspdStatusCode;
    private String cmrspdStatusText;
    private String mmsResponse;
    private String mtID;
    private String cmrspdMessageID;
    
    
    public RxMMSRedemptionResponse(){
        
    }
    
    public RxMMSRedemptionResponse(Integer mmsReqNo, Date effectiveDate, Date endDate, String cmrspdStatusCode, String cmrspdStatusText, String mmsResponse, String mtID, String cmrspdMessageID){
        this.cmrspdMessageID = cmrspdMessageID;
        this.cmrspdStatusCode = cmrspdStatusCode;
        this.cmrspdStatusText = cmrspdStatusText;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.mmsReqNo = mmsReqNo;
        this.mmsResponse = mmsResponse;
        this.mtID = mtID;
    }

    @Id
    @Column(name = "MMS_Req_No", unique = true, nullable = false)
    public Integer getMmsReqNo() {
        return mmsReqNo;
    }

    public void setMmsReqNo(Integer mmsReqNo) {
        this.mmsReqNo = mmsReqNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Effective_Date", nullable = false, length = 19)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "End_Date", length = 19)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "CMRSPD_StatusCode", length = 6)
    public String getCmrspdStatusCode() {
        return cmrspdStatusCode;
    }

    public void setCmrspdStatusCode(String cmrspdStatusCode) {
        this.cmrspdStatusCode = cmrspdStatusCode;
    }

    @Column(name = "CMRSPD_StatusText", length = 200)
    public String getCmrspdStatusText() {
        return cmrspdStatusText;
    }

    public void setCmrspdStatusText(String cmrspdStatusText) {
        this.cmrspdStatusText = cmrspdStatusText;
    }

    @Column(name = "MMS_Response", length = 2000)
    public String getMmsResponse() {
        return mmsResponse;
    }

    public void setMmsResponse(String mmsResponse) {
        this.mmsResponse = mmsResponse;
    }

    @Column(name = "MT_ID", length = 20)
    public String getMtID() {
        return mtID;
    }

    public void setMtID(String mtID) {
        this.mtID = mtID;
    }

    @Column(name = "CMRSPD_MessageID", length = 50)
    public String getCmrspdMessageID() {
        return cmrspdMessageID;
    }

    public void setCmrspdMessageID(String cmrspdMessageID) {
        this.cmrspdMessageID = cmrspdMessageID;
    }
}
