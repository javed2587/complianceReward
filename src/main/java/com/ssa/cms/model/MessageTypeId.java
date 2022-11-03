package com.ssa.cms.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotEmpty;


@Embeddable
public class MessageTypeId  implements java.io.Serializable {


     private int messageTypeId;
     @NotEmpty
     private int folderId;

    public MessageTypeId() {
    }

    public MessageTypeId(int messageTypeId, int folderId) {
       this.messageTypeId = messageTypeId;
       this.folderId = folderId;
    }
   

    @Column(name="MessageTypeId", nullable=false)
    public int getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Column(name="FolderId", nullable=false)
    public int getFolderId() {
        return this.folderId;
    }
    
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MessageTypeId) ) return false;
		 MessageTypeId castOther = ( MessageTypeId ) other; 
         
		 return (this.getMessageTypeId()==castOther.getMessageTypeId())
 && (this.getFolderId()==castOther.getFolderId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getMessageTypeId();
         result = 37 * result + this.getFolderId();
         return result;
   }   


}


