package com.ssa.cms.model;
// Generated Mar 27, 2013 2:07:39 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;


//@Embeddable
public class DrfprocessedId  implements java.io.Serializable {


     private int claimStatus;
     private String transactionNumber;

    public DrfprocessedId() {
    }

    public DrfprocessedId(int claimStatus, String transactionNumber) {
       this.claimStatus = claimStatus;
       this.transactionNumber = transactionNumber;
    }
   

  //  @Column(name="Claim_Status", nullable=false)
    public int getClaimStatus() {
        return this.claimStatus;
    }
    
    public void setClaimStatus(int claimStatus) {
        this.claimStatus = claimStatus;
    }

   // @Column(name="Transaction_Number", nullable=false, length=15)
    public String getTransactionNumber() {
        return this.transactionNumber;
    }
    
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DrfprocessedId) ) return false;
		 DrfprocessedId castOther = ( DrfprocessedId ) other; 
         
		 return (this.getClaimStatus()==castOther.getClaimStatus())
 && ( (this.getTransactionNumber()==castOther.getTransactionNumber()) || ( this.getTransactionNumber()!=null && castOther.getTransactionNumber()!=null && this.getTransactionNumber().equals(castOther.getTransactionNumber()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getClaimStatus();
         result = 37 * result + ( getTransactionNumber() == null ? 0 : this.getTransactionNumber().hashCode() );
         return result;
   }   


}


