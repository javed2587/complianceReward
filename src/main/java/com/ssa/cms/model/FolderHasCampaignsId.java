package com.ssa.cms.model;
// Generated Apr 16, 2013 7:26:01 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class FolderHasCampaignsId  implements java.io.Serializable {


     private int fhCid;
     private int folderId;
     private int campaignId;

    public FolderHasCampaignsId() {
    }

    public FolderHasCampaignsId(int fhCid, int folderId, int campaignId) {
       this.fhCid = fhCid;
       this.folderId = folderId;
       this.campaignId = campaignId;
    }
   

    @Column(name="FhCId", nullable=false)
    public int getFhCid() {
        return this.fhCid;
    }
    
    public void setFhCid(int fhCid) {
        this.fhCid = fhCid;
    }

    @Column(name="FolderId", nullable=false)
    public int getFolderId() {
        return this.folderId;
    }
    
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Column(name="CampaignId", nullable=false)
    public int getCampaignId() {
        return this.campaignId;
    }
    
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FolderHasCampaignsId) ) return false;
		 FolderHasCampaignsId castOther = ( FolderHasCampaignsId ) other; 
         
		 return (this.getFhCid()==castOther.getFhCid())
 && (this.getFolderId()==castOther.getFolderId())
 && (this.getCampaignId()==castOther.getCampaignId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getFhCid();
         result = 37 * result + this.getFolderId();
         result = 37 * result + this.getCampaignId();
         return result;
   }   


}


