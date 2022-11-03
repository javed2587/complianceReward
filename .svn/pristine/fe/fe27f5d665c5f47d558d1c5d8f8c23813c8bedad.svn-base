package com.ssa.cms.model;
// Generated Jul 26, 2013 1:25:38 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotEmpty;


@Embeddable
public class CampaignTriggerId  implements java.io.Serializable {


     private int triggerId;
     private int campaignId;
     @NotEmpty
     private String keyword;

    public CampaignTriggerId() {
    }

    public CampaignTriggerId(int triggerId, int campaignId, String keyword) {
       this.triggerId = triggerId;
       this.campaignId = campaignId;
       this.keyword = keyword;
    }
   

    @Column(name="TriggerId", nullable=false)
    public int getTriggerId() {
        return this.triggerId;
    }
    
    public void setTriggerId(int triggerId) {
        this.triggerId = triggerId;
    }

    @Column(name="CampaignId", nullable=false)
    public int getCampaignId() {
        return this.campaignId;
    }
    
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    @Column(name="Keyword", unique=true, nullable=false, length=10)
    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CampaignTriggerId) ) return false;
		 CampaignTriggerId castOther = ( CampaignTriggerId ) other; 
         
		 return (this.getTriggerId()==castOther.getTriggerId())
 && (this.getCampaignId()==castOther.getCampaignId())
 && ( (this.getKeyword()==castOther.getKeyword()) || ( this.getKeyword()!=null && castOther.getKeyword()!=null && this.getKeyword().equals(castOther.getKeyword()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getTriggerId();
         result = 37 * result + this.getCampaignId();
         result = 37 * result + ( getKeyword() == null ? 0 : this.getKeyword().hashCode() );
         return result;
   }   


}


