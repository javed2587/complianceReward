package com.ssa.cms.model;
// Generated May 7, 2013 3:38:35 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
/**
 * 
 * @author mzubair
 * 
 * InstantRedemption table not in used and not exist in db
 */
//@Entity
//@Table(name = "InstantRedemption")
public class InstantRedemption implements java.io.Serializable {

    private InstantRedemptionId id;
    private Integer campaignId;
    private String submittedId;
    private String communicationMethod;
    private String communicationId;
    private String isValidPhone;
    private String memberId;
    private Date fillDate;
    private Date rxWrittenDate;
    private String ndcNumber;
    private Long fdbGcn;
    private String submittedCardholderFirstName;
    private String submittedCardholderLastName;
    private Date submittedCardholderDob;
    private String submittedCardholderGender;
    private String cardholderId;
    private String cardholderFirstName;
    private String cardholderLastName;
    private Date cardholderDob;
    private Character cardholderGender;
    private String pharmacyNpi;
    private String pharmacyName;
    private String pharmacyCity;
    private String pharmacyState;
    private String pharmacyZipCode;
    private String pharmacyAddress1;
    private String pharmacyPhone;
    private String prescriptionNumber;
    private String rxGroupNumber;
    private double quantity;
    private int daysSupply;
    private int refillsUsed;
    private int refillAllowed;
    private BigDecimal patientBenefitAmount;
    private BigDecimal ingredientCostPaid;
    private BigDecimal ptOutOfPocket;
    private BigDecimal totDrugCostPaidToPharmacy;
    private Date reversalDate;
    private Date postingDate;
    private String deaNumber;
    private String prescriberNpi;
    private String prescriberFirstName;
    private String prescriberLastName;
    private String prescriberPhone;
    private String prescriberFax;
    private String priceSource;
    private String otherPayerRejectionCode;
    private String otherPayerId1;
    private String otherPayerId2;
    private String otherPayerId3;
    private String otherPayerId4;
    private String otherPayerId5;
    private String otherPayerId6;
    private String otherPayerId7;
    private String otherPayerId8;
    private String otherPayerId9;
    private String programCode;
    private String fileTypeCode;
    private String fileName;
    private Date effectiveDate;
    private Date timeStamp;
    private Long inputReferenceNumber;
    private Integer enrollmentId;
    private String communicationSourceCode;
    private Date enrollmentDate;
    private String enrollmentPath;
    private int redemptionId;
    private int redemptionChannelId;
    private String redemptionChannelTitle;
    private Integer refillAuthorized;
    private List<RedemptionIngredient> ingredientList;
    private Double copay;

    public InstantRedemption() {
    }

    public InstantRedemption(InstantRedemptionId id, Date fillDate, String ndcNumber, String cardholderFirstName, String cardholderLastName, Date cardholderDob, String prescriptionNumber, String rxGroupNumber, double quantity, int daysSupply, int refillsUsed, BigDecimal ptOutOfPocket, BigDecimal totDrugCostPaidToPharmacy, Date postingDate, Date effectiveDate, Date timeStamp, int redemptionId) {
        this.id = id;
        this.fillDate = fillDate;
        this.ndcNumber = ndcNumber;
        this.cardholderFirstName = cardholderFirstName;
        this.cardholderLastName = cardholderLastName;
        this.cardholderDob = cardholderDob;
        this.prescriptionNumber = prescriptionNumber;
        this.rxGroupNumber = rxGroupNumber;
        this.quantity = quantity;
        this.daysSupply = daysSupply;
        this.refillsUsed = refillsUsed;
        this.ptOutOfPocket = ptOutOfPocket;
        this.totDrugCostPaidToPharmacy = totDrugCostPaidToPharmacy;
        this.postingDate = postingDate;
        this.effectiveDate = effectiveDate;
        this.timeStamp = timeStamp;
        this.redemptionId = redemptionId;
    }

    public InstantRedemption(InstantRedemptionId id, Integer campaignId, Integer redemptionChannelId, String redemptionChannelTitle, String submittedId, String communicationMethod, String communicationId, String isValidPhone, String memberId, Date fillDate, Date rxWrittenDate, String ndcNumber, Long fdbGcn, String submittedCardholderFirstName, String submittedCardholderLastName, Date submittedCardholderDob, String submittedCardholderGender, String cardholderId, String cardholderFirstName, String cardholderLastName, Date cardholderDob, Character cardholderGender, String pharmacyNpi, String pharmacyName, String pharmacyCity, String pharmacyState, String pharmacyZipCode, String pharmacyAddress1, String pharmacyPhone, String prescriptionNumber, String rxGroupNumber, double quantity, int daysSupply, int refillsUsed, BigDecimal ingredientCostPaid, BigDecimal ptOutOfPocket, BigDecimal totDrugCostPaidToPharmacy, Date reversalDate, Date postingDate, String deaNumber, String prescriberNpi, String prescriberFirstName, String prescriberLastName, String prescriberPhone, String prescriberFax, String priceSource, String otherPayerRejectionCode, String otherPayerId1, String otherPayerId2, String otherPayerId3, String otherPayerId4, String otherPayerId5, String otherPayerId6, String otherPayerId7, String otherPayerId8, String otherPayerId9, String programCode, String fileTypeCode, String fileName, Date effectiveDate, Date timeStamp, Long inputReferenceNumber, Integer enrollmentId, String communicationSourceCode, Date enrollmentDate, String enrollmentPath, int redemptionId) {
        this.id = id;
        this.campaignId = campaignId;
        this.redemptionChannelId = redemptionChannelId;
        this.redemptionChannelTitle = redemptionChannelTitle;
        this.submittedId = submittedId;
        this.communicationMethod = communicationMethod;
        this.communicationId = communicationId;
        this.isValidPhone = isValidPhone;
        this.memberId = memberId;
        this.fillDate = fillDate;
        this.rxWrittenDate = rxWrittenDate;
        this.ndcNumber = ndcNumber;
        this.fdbGcn = fdbGcn;
        this.submittedCardholderFirstName = submittedCardholderFirstName;
        this.submittedCardholderLastName = submittedCardholderLastName;
        this.submittedCardholderDob = submittedCardholderDob;
        this.submittedCardholderGender = submittedCardholderGender;
        this.cardholderId = cardholderId;
        this.cardholderFirstName = cardholderFirstName;
        this.cardholderLastName = cardholderLastName;
        this.cardholderDob = cardholderDob;
        this.cardholderGender = cardholderGender;
        this.pharmacyNpi = pharmacyNpi;
        this.pharmacyName = pharmacyName;
        this.pharmacyCity = pharmacyCity;
        this.pharmacyState = pharmacyState;
        this.pharmacyZipCode = pharmacyZipCode;
        this.pharmacyAddress1 = pharmacyAddress1;
        this.pharmacyPhone = pharmacyPhone;
        this.prescriptionNumber = prescriptionNumber;
        this.rxGroupNumber = rxGroupNumber;
        this.quantity = quantity;
        this.daysSupply = daysSupply;
        this.refillsUsed = refillsUsed;
        this.ingredientCostPaid = ingredientCostPaid;
        this.ptOutOfPocket = ptOutOfPocket;
        this.totDrugCostPaidToPharmacy = totDrugCostPaidToPharmacy;
        this.reversalDate = reversalDate;
        this.postingDate = postingDate;
        this.deaNumber = deaNumber;
        this.prescriberNpi = prescriberNpi;
        this.prescriberFirstName = prescriberFirstName;
        this.prescriberLastName = prescriberLastName;
        this.prescriberPhone = prescriberPhone;
        this.prescriberFax = prescriberFax;
        this.priceSource = priceSource;
        this.otherPayerRejectionCode = otherPayerRejectionCode;
        this.otherPayerId1 = otherPayerId1;
        this.otherPayerId2 = otherPayerId2;
        this.otherPayerId3 = otherPayerId3;
        this.otherPayerId4 = otherPayerId4;
        this.otherPayerId5 = otherPayerId5;
        this.otherPayerId6 = otherPayerId6;
        this.otherPayerId7 = otherPayerId7;
        this.otherPayerId8 = otherPayerId8;
        this.otherPayerId9 = otherPayerId9;
        this.programCode = programCode;
        this.fileTypeCode = fileTypeCode;
        this.fileName = fileName;
        this.effectiveDate = effectiveDate;
        this.timeStamp = timeStamp;
        this.inputReferenceNumber = inputReferenceNumber;
        this.enrollmentId = enrollmentId;
        this.communicationSourceCode = communicationSourceCode;
        this.enrollmentDate = enrollmentDate;
        this.enrollmentPath = enrollmentPath;
        this.redemptionId = redemptionId;
    }

//    @EmbeddedId
//
//    @AttributeOverrides({
//        @AttributeOverride(name = "transactionNumber", column = @Column(name = "Transaction_Number", nullable = false, length = 20)),
//        @AttributeOverride(name = "claimStatus", column = @Column(name = "Claim_Status", nullable = false))})
    public InstantRedemptionId getId() {
        return this.id;
    }

    public void setId(InstantRedemptionId id) {
        this.id = id;
    }

//    @Column(name = "CampaignId")
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

//    @Column(name = "Submitted_ID", length = 11)
    public String getSubmittedId() {
        return this.submittedId;
    }

    public void setSubmittedId(String submittedId) {
        this.submittedId = submittedId;
    }

//    @Column(name = "Communication_Method", length = 1)
    public String getCommunicationMethod() {
        return this.communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

//    @Column(name = "Communication_Id", length = 50)
    public String getCommunicationId() {
        return this.communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }

//    @Column(name = "isValidPhone", length = 3)
    public String getIsValidPhone() {
        return this.isValidPhone;
    }

    public void setIsValidPhone(String isValidPhone) {
        this.isValidPhone = isValidPhone;
    }

//    @Column(name = "Member_ID", length = 25)
    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Fill_Date", length = 10)
    public Date getFillDate() {
        return this.fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Rx_Written_Date", length = 10)
    public Date getRxWrittenDate() {
        return this.rxWrittenDate;
    }

    public void setRxWrittenDate(Date rxWrittenDate) {
        this.rxWrittenDate = rxWrittenDate;
    }

//    @Column(name = "NDC_Number", nullable = false, length = 12)
    public String getNdcNumber() {
        return this.ndcNumber;
    }

    public void setNdcNumber(String ndcNumber) {
        this.ndcNumber = ndcNumber;
    }

//    @Column(name = "FDB_GCN", precision = 6, scale = 0)
    public Long getFdbGcn() {
        return this.fdbGcn;
    }

    public void setFdbGcn(Long fdbGcn) {
        this.fdbGcn = fdbGcn;
    }

//    @Column(name = "Submitted_Cardholder_First_Name", length = 50)
    public String getSubmittedCardholderFirstName() {
        return this.submittedCardholderFirstName;
    }

    public void setSubmittedCardholderFirstName(String submittedCardholderFirstName) {
        this.submittedCardholderFirstName = submittedCardholderFirstName;
    }

//    @Column(name = "Submitted_Cardholder_Last_Name", length = 50)
    public String getSubmittedCardholderLastName() {
        return this.submittedCardholderLastName;
    }

    public void setSubmittedCardholderLastName(String submittedCardholderLastName) {
        this.submittedCardholderLastName = submittedCardholderLastName;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Submitted_Cardholder_DOB", length = 10)
    public Date getSubmittedCardholderDob() {
        return this.submittedCardholderDob;
    }

    public void setSubmittedCardholderDob(Date submittedCardholderDob) {
        this.submittedCardholderDob = submittedCardholderDob;
    }

//    @Column(name = "Submitted_Cardholder_Gender", length = 1)
    public String getSubmittedCardholderGender() {
        return this.submittedCardholderGender;
    }

    public void setSubmittedCardholderGender(String submittedCardholderGender) {
        this.submittedCardholderGender = submittedCardholderGender;
    }

//    @Column(name = "Cardholder_ID", length = 20)
    public String getCardholderId() {
        return this.cardholderId;
    }

    public void setCardholderId(String cardholderId) {
        this.cardholderId = cardholderId;
    }

//    @Column(name = "Cardholder_First_Name", nullable = true, length = 30)
    public String getCardholderFirstName() {
        return this.cardholderFirstName;
    }

    public void setCardholderFirstName(String cardholderFirstName) {
        this.cardholderFirstName = cardholderFirstName;
    }

//    @Column(name = "Cardholder_Last_Name", nullable = true, length = 30)
    public String getCardholderLastName() {
        return this.cardholderLastName;
    }

    public void setCardholderLastName(String cardholderLastName) {
        this.cardholderLastName = cardholderLastName;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Cardholder_DOB", nullable = true, length = 10)
    public Date getCardholderDob() {
        return this.cardholderDob;
    }

    public void setCardholderDob(Date cardholderDob) {
        this.cardholderDob = cardholderDob;
    }

//    @Column(name = "Cardholder_Gender", length = 1)
    public Character getCardholderGender() {
        return this.cardholderGender;
    }

    public void setCardholderGender(Character cardholderGender) {
        this.cardholderGender = cardholderGender;
    }

//    @Column(name = "Pharmacy_NPI", length = 10)
    public String getPharmacyNpi() {
        return this.pharmacyNpi;
    }

    public void setPharmacyNpi(String pharmacyNpi) {
        this.pharmacyNpi = pharmacyNpi;
    }

//    @Column(name = "Pharmacy_Name", length = 50)
    public String getPharmacyName() {
        return this.pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

//    @Column(name = "Pharmacy_City", length = 18)
    public String getPharmacyCity() {
        return this.pharmacyCity;
    }

    public void setPharmacyCity(String pharmacyCity) {
        this.pharmacyCity = pharmacyCity;
    }

//    @Column(name = "Pharmacy_State", length = 2)
    public String getPharmacyState() {
        return this.pharmacyState;
    }

    public void setPharmacyState(String pharmacyState) {
        this.pharmacyState = pharmacyState;
    }

//    @Column(name = "Pharmacy_Zip_Code", length = 10)
    public String getPharmacyZipCode() {
        return this.pharmacyZipCode;
    }

    public void setPharmacyZipCode(String pharmacyZipCode) {
        this.pharmacyZipCode = pharmacyZipCode;
    }

//    @Column(name = "Pharmacy_Address_1", length = 250)
    public String getPharmacyAddress1() {
        return this.pharmacyAddress1;
    }

    public void setPharmacyAddress1(String pharmacyAddress1) {
        this.pharmacyAddress1 = pharmacyAddress1;
    }

//    @Column(name = "Pharmacy_Phone", length = 10)
    public String getPharmacyPhone() {
        return this.pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

//    @Column(name = "Prescription_Number", nullable = true, length = 12)
    public String getPrescriptionNumber() {
        return this.prescriptionNumber;
    }

    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

//    @Column(name = "Rx_Group_Number", nullable = true, length = 15)
    public String getRxGroupNumber() {
        return this.rxGroupNumber;
    }

    public void setRxGroupNumber(String rxGroupNumber) {
        this.rxGroupNumber = rxGroupNumber;
    }

//    @Column(name = "Quantity", nullable = true, precision = 22, scale = 0)
    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

//    @Column(name = "Days_Supply", nullable = true)
    public int getDaysSupply() {
        return this.daysSupply;
    }

    public void setDaysSupply(int daysSupply) {
        this.daysSupply = daysSupply;
    }

//    @Column(name = "Refills_Used", nullable = true)
    public int getRefillsUsed() {
        return this.refillsUsed;
    }

    public void setRefillsUsed(int refillsUsed) {
        this.refillsUsed = refillsUsed;
    }

//    @Column(name = "Refill_Allowed", nullable = true)
    public int getRefillAllowed() {
        return this.refillAllowed;
    }

    public void setRefillAllowed(int refillAllowed) {
        this.refillAllowed = refillAllowed;
    }

//    @Column(name = "Patient_Benefit_Amount", precision = 11, scale = 3)
    public BigDecimal getPatientBenefitAmount() {
        return this.patientBenefitAmount;
    }

    public void setPatientBenefitAmount(BigDecimal patientBenefitAmount) {
        this.patientBenefitAmount = patientBenefitAmount;
    }

//    @Column(name = "Ingredient_Cost_Paid", precision = 11, scale = 3)
    public BigDecimal getIngredientCostPaid() {
        return this.ingredientCostPaid;
    }

    public void setIngredientCostPaid(BigDecimal ingredientCostPaid) {
        this.ingredientCostPaid = ingredientCostPaid;
    }

//    @Column(name = "Pt_Out_Of_Pocket", nullable = true, precision = 11, scale = 3)
    public BigDecimal getPtOutOfPocket() {
        return this.ptOutOfPocket;
    }

    public void setPtOutOfPocket(BigDecimal ptOutOfPocket) {
        this.ptOutOfPocket = ptOutOfPocket;
    }

//    @Column(name = "Tot_Drug_Cost_Paid_To_Pharmacy", nullable = true, precision = 11, scale = 3)
    public BigDecimal getTotDrugCostPaidToPharmacy() {
        return this.totDrugCostPaidToPharmacy;
    }

    public void setTotDrugCostPaidToPharmacy(BigDecimal totDrugCostPaidToPharmacy) {
        this.totDrugCostPaidToPharmacy = totDrugCostPaidToPharmacy;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Reversal_Date", length = 19)
    public Date getReversalDate() {
        return this.reversalDate;
    }

    public void setReversalDate(Date reversalDate) {
        this.reversalDate = reversalDate;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Posting_Date", nullable = true, length = 19)
    public Date getPostingDate() {
        return this.postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

//    @Column(name = "DEA_Number", length = 9)
    public String getDeaNumber() {
        return this.deaNumber;
    }

    public void setDeaNumber(String deaNumber) {
        this.deaNumber = deaNumber;
    }

//    @Column(name = "Prescriber_NPI", length = 12)
    public String getPrescriberNpi() {
        return this.prescriberNpi;
    }

    public void setPrescriberNpi(String prescriberNpi) {
        this.prescriberNpi = prescriberNpi;
    }

//    @Column(name = "Prescriber_First_Name", length = 50)
    public String getPrescriberFirstName() {
        return this.prescriberFirstName;
    }

    public void setPrescriberFirstName(String prescriberFirstName) {
        this.prescriberFirstName = prescriberFirstName;
    }

//    @Column(name = "Prescriber_Last_Name", length = 50)
    public String getPrescriberLastName() {
        return this.prescriberLastName;
    }

    public void setPrescriberLastName(String prescriberLastName) {
        this.prescriberLastName = prescriberLastName;
    }

//    @Column(name = "Prescriber_Phone", length = 20)
    public String getPrescriberPhone() {
        return this.prescriberPhone;
    }

    public void setPrescriberPhone(String prescriberPhone) {
        this.prescriberPhone = prescriberPhone;
    }

//    @Column(name = "Prescriber_Fax", length = 20)
    public String getPrescriberFax() {
        return this.prescriberFax;
    }

    public void setPrescriberFax(String prescriberFax) {
        this.prescriberFax = prescriberFax;
    }

//    @Column(name = "Price_Source", length = 20)
    public String getPriceSource() {
        return this.priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }

//    @Column(name = "Other_Payer_Rejection_Code", length = 4)
    public String getOtherPayerRejectionCode() {
        return this.otherPayerRejectionCode;
    }

    public void setOtherPayerRejectionCode(String otherPayerRejectionCode) {
        this.otherPayerRejectionCode = otherPayerRejectionCode;
    }

//    @Column(name = "Other_Payer_Id1", length = 25)
    public String getOtherPayerId1() {
        return this.otherPayerId1;
    }

    public void setOtherPayerId1(String otherPayerId1) {
        this.otherPayerId1 = otherPayerId1;
    }

//    @Column(name = "Other_Payer_Id2", length = 25)
    public String getOtherPayerId2() {
        return this.otherPayerId2;
    }

    public void setOtherPayerId2(String otherPayerId2) {
        this.otherPayerId2 = otherPayerId2;
    }

//    @Column(name = "Other_Payer_Id3", length = 25)
    public String getOtherPayerId3() {
        return this.otherPayerId3;
    }

    public void setOtherPayerId3(String otherPayerId3) {
        this.otherPayerId3 = otherPayerId3;
    }

//    @Column(name = "Other_Payer_Id4", length = 25)
    public String getOtherPayerId4() {
        return this.otherPayerId4;
    }

    public void setOtherPayerId4(String otherPayerId4) {
        this.otherPayerId4 = otherPayerId4;
    }

//    @Column(name = "Other_Payer_Id5", length = 25)
    public String getOtherPayerId5() {
        return this.otherPayerId5;
    }

    public void setOtherPayerId5(String otherPayerId5) {
        this.otherPayerId5 = otherPayerId5;
    }

//    @Column(name = "Other_Payer_Id6", length = 25)
    public String getOtherPayerId6() {
        return this.otherPayerId6;
    }

    public void setOtherPayerId6(String otherPayerId6) {
        this.otherPayerId6 = otherPayerId6;
    }

//    @Column(name = "Other_Payer_Id7", length = 25)
    public String getOtherPayerId7() {
        return this.otherPayerId7;
    }

    public void setOtherPayerId7(String otherPayerId7) {
        this.otherPayerId7 = otherPayerId7;
    }

//    @Column(name = "Other_Payer_Id8", length = 25)
    public String getOtherPayerId8() {
        return this.otherPayerId8;
    }

    public void setOtherPayerId8(String otherPayerId8) {
        this.otherPayerId8 = otherPayerId8;
    }

//    @Column(name = "Other_Payer_Id9", length = 25)
    public String getOtherPayerId9() {
        return this.otherPayerId9;
    }

    public void setOtherPayerId9(String otherPayerId9) {
        this.otherPayerId9 = otherPayerId9;
    }

   // @Column(name = "Program_Code", length = 25)
    public String getProgramCode() {
        return this.programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

//    @Column(name = "File_Type_Code", length = 3)
    public String getFileTypeCode() {
        return this.fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

//    @Column(name = "File_Name", length = 100)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Effective_Date", nullable = true, length = 19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Time_Stamp", nullable = true, length = 19)
    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

//    @Column(name = "Input_Reference_Number")
    public Long getInputReferenceNumber() {
        return this.inputReferenceNumber;
    }

    public void setInputReferenceNumber(Long inputReferenceNumber) {
        this.inputReferenceNumber = inputReferenceNumber;
    }

//    @Column(name = "Enrollment_Id")
    public Integer getEnrollmentId() {
        return this.enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

//    @Column(name = "Communication_Source_Code", length = 4)
    public String getCommunicationSourceCode() {
        return this.communicationSourceCode;
    }

    public void setCommunicationSourceCode(String communicationSourceCode) {
        this.communicationSourceCode = communicationSourceCode;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Enrollment_Date", length = 19)
    public Date getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

//    @Column(name = "Enrollment_Path", length = 1)
    public String getEnrollmentPath() {
        return this.enrollmentPath;
    }

    public void setEnrollmentPath(String enrollmentPath) {
        this.enrollmentPath = enrollmentPath;
    }

//    @Column(name = "Redemption_Id", nullable = true)
    public int getRedemptionId() {
        return this.redemptionId;
    }

    public void setRedemptionId(int redemptionId) {
        this.redemptionId = redemptionId;
    }

//    @Column(name = "Redemption_Channel_Id", nullable = true)
    public int getRedemptionChannelId() {
        return redemptionChannelId;
    }

    public void setRedemptionChannelId(int redemptionChannelId) {
        this.redemptionChannelId = redemptionChannelId;
    }

//    @Column(name = "Redemption_Channel_Title", nullable = true, length = 50)
    public String getRedemptionChannelTitle() {
        return redemptionChannelTitle;
    }

    public void setRedemptionChannelTitle(String redemptionChannelTitle) {
        this.redemptionChannelTitle = redemptionChannelTitle;
    }

//    @Column(name = "refill_Authorized", length = 2)
    public Integer getRefillAuthorized() {
        return refillAuthorized;
    }

    public void setRefillAuthorized(Integer refillAuthorized) {
        this.refillAuthorized = refillAuthorized;
    }

//    @Transient
    public List<RedemptionIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RedemptionIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

//    @Transient
    public Double getCopay() {
        return copay;
    }

    public void setCopay(Double copay) {
        this.copay = copay;
    }

}
