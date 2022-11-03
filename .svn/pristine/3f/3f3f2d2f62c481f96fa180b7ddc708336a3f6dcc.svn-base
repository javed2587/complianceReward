package com.ssa.cms.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;


//@Embeddable
public class InstantRedemptionId  implements java.io.Serializable {


     private String transactionNumber;
     private int claimStatus;

    public InstantRedemptionId() {
    }

    public InstantRedemptionId(String transactionNumber, int claimStatus) {
       this.transactionNumber = transactionNumber;
       this.claimStatus = claimStatus;
    }
   

   // @Column(name="Transaction_Number", nullable=false, length=20)
    public String getTransactionNumber() {
        return this.transactionNumber;
    }
    
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

   // @Column(name="Claim_Status", nullable=false)
    public int getClaimStatus() {
        return this.claimStatus;
    }
    
    public void setClaimStatus(int claimStatus) {
        this.claimStatus = claimStatus;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InstantRedemptionId) ) return false;
		 InstantRedemptionId castOther = ( InstantRedemptionId ) other; 
         
		 return ( (this.getTransactionNumber()==castOther.getTransactionNumber()) || ( this.getTransactionNumber()!=null && castOther.getTransactionNumber()!=null && this.getTransactionNumber().equals(castOther.getTransactionNumber()) ) )
 && (this.getClaimStatus()==castOther.getClaimStatus());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTransactionNumber() == null ? 0 : this.getTransactionNumber().hashCode() );
         result = 37 * result + this.getClaimStatus();
         return result;
   }   


}


