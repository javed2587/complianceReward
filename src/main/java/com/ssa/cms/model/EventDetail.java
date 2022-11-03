package com.ssa.cms.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="EventDetail")
public class EventDetail  implements java.io.Serializable {


     private Integer eventDetailId;
     private Event event;
     private String bracketsStart;
     private String dataSet;
     private String fieldSelection;
     private String operation;
     private String specificValue;
     private String condition;
     private String bracketsEnd;
     private Boolean deleted;
     private String drfValue;
     private String tempValue;
     
    public EventDetail() {
    }

	
    public EventDetail(Event event, String dataSet, String fieldSelection, String operation, String condition) {
        this.event = event;
        this.dataSet = dataSet;
        this.fieldSelection = fieldSelection;
        this.operation = operation;
        this.condition = condition;
    }
    public EventDetail(Event event, String bracketsStart, String dataSet, String fieldSelection, String operation, String specificValue, String condition, String bracketsEnd) {
       this.event = event;
       this.bracketsStart = bracketsStart;
       this.dataSet = dataSet;
       this.fieldSelection = fieldSelection;
       this.operation = operation;
       this.specificValue = specificValue;
       this.condition = condition;
       this.bracketsEnd = bracketsEnd;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="EventDetailId", unique=true, nullable=false)
    public Integer getEventDetailId() {
        return this.eventDetailId;
    }
    
    public void setEventDetailId(Integer eventDetailId) {
        this.eventDetailId = eventDetailId;
    }
    @ManyToOne(fetch= FetchType.LAZY) 
    @JoinColumn(name="EventId", nullable=false, insertable=true, updatable=true)
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    @Column(name="BracketsStart", length=10)
    public String getBracketsStart() {
        return this.bracketsStart;
    }
    
    public void setBracketsStart(String bracketsStart) {
        this.bracketsStart = bracketsStart;
    }
    
    @Column(name="DataSet", nullable=false, length=50)
    public String getDataSet() {
        return this.dataSet;
    }
    
    public void setDataSet(String dataSet) {
        this.dataSet = dataSet;
    }
    
    @Column(name="FieldSelection", nullable=false, length=50)
    public String getFieldSelection() {
        return this.fieldSelection;
    }
    
    public void setFieldSelection(String fieldSelection) {
        this.fieldSelection = fieldSelection;
    }
    
    @Column(name="Operation", nullable=false, length=2)
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    @Column(name="SpecificValue", length=50)
    public String getSpecificValue() {
        return this.specificValue;
    }
    
    public void setSpecificValue(String specificValue) {
        this.specificValue = specificValue;
    }
    
    @Column(name="SQL_Condition", nullable=false, length=4)
    public String getCondition() {
        return this.condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    @Column(name="BracketsEnd", length=10)
    public String getBracketsEnd() {
        return this.bracketsEnd;
    }
    
    public void setBracketsEnd(String bracketsEnd) {
        this.bracketsEnd = bracketsEnd;
    }
    
    @Transient
    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    @Transient
    public String getDrfValue() {
        return drfValue;
    }

    public void setDrfValue(String drfValue) {
        this.drfValue = drfValue;
    }

    @Transient
    public String getTempValue() {
        return tempValue;
    }

    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    


}


