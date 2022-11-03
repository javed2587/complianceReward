package com.ssa.cms.model;
// Generated May 6, 2013 7:07:31 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@Entity
//@Table(name="CommunicationSource")
public class CommunicationSource  implements java.io.Serializable {


     private String communicationSourceCode;
     private String narrative;
     private Date effectiveDate;
     private Date endDate;
     private String type;

    public CommunicationSource() {
    }

	
    public CommunicationSource(String communicationSourceCode, String narrative, Date effectiveDate, String type) {
        this.communicationSourceCode = communicationSourceCode;
        this.narrative = narrative;
        this.effectiveDate = effectiveDate;
        this.type = type;
    }
    public CommunicationSource(String communicationSourceCode, String narrative, Date effectiveDate, Date endDate, String type) {
       this.communicationSourceCode = communicationSourceCode;
       this.narrative = narrative;
       this.effectiveDate = effectiveDate;
       this.endDate = endDate;
       this.type = type;
    }
   
     @Id 
    
    @Column(name="Communication_Source_Code", unique=true, nullable=false, length=6)
    public String getCommunicationSourceCode() {
        return this.communicationSourceCode;
    }
    
    public void setCommunicationSourceCode(String communicationSourceCode) {
        this.communicationSourceCode = communicationSourceCode;
    }
    
    @Column(name="Narrative", nullable=false, length=500)
    public String getNarrative() {
        return this.narrative;
    }
    
    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Effective_Date", nullable=false, length=19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="End_Date", length=19)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Column(name="Source_Type", nullable=false, length=20)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }




}


