package com.ssa.cms.dto;

/**
 *
 * @author mzubair
 */
public class DrugSearchesDTO {

    private Integer id;
    private Integer drugSearchId;
    private String drugName;
    private String drugType;
    private String drugQty;
    private Double drugPrice;
    private String genericName;
    private String strength;
    private Double payment;
    private String createdOn;
    private Integer totalRecord;
    private Integer drugId;
    private String drugNDC;
    private Integer patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDrugSearchId() {
        return drugSearchId;
    }

    public void setDrugSearchId(Integer drugSearchId) {
        this.drugSearchId = drugSearchId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public Double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Double drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getDrugQty() {
        return drugQty;
    }

    public void setDrugQty(String drugQty) {
        this.drugQty = drugQty;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDrugNDC() {
        return drugNDC;
    }

    public void setDrugNDC(String drugNDC) {
        this.drugNDC = drugNDC;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

}
