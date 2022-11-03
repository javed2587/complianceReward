package com.ssa.cms.model;

import java.math.BigDecimal;
import java.math.BigInteger;
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
 * DailyRedemption table not exist
 */
//@Entity
//@Table(name = "DailyRedemption")
public class DailyRedemption implements java.io.Serializable {

    private DailyRedemptionId id;
    private String submittedId;
    private Integer campaignId;
    private int redemptionChannelId;
    private String redemptionChannelTitle;
    private String programCode;
    private String rxGroupNumber;
    private Date fillDate;
    private Date cardholderDob;
    private String memberId;
    private String communicationMethod;
    private String communicationId;
    private String gpi;
    private String submittedCardholderFirstName;
    private String submittedCardholderLastName;
    private Date submittedCardholderDob;
    private String submittedCardholderGender;
    private String claimAuthorizationNumber;
    private String orignalClaimAuthorizationNumber;
    private Date postingDate;
    private Date reversalDate;
    private long pharmacyNcpdp;
    private String pharmacyName;
    private String cardholderId;
    private String rxWrittenDate;
    private String prescriptionNumber;
    private int refillsUsed;
    private Integer refillsAllowed;
    private String ndcNumber;
    private String productName;
    private String drugStrength;
    private String drugDosageForm;
    private double quantity;
    private int daysSupply;
    private BigDecimal ingredientCostPaid;
    private Double dispenseFeePaid;
    private Double flatSalesTaxAmountPaid;
    private Double professionalServiceFee;
    private BigDecimal ptOutOfPocket;
    private String priceSource;
    private String pharmacyNpi;
    private BigDecimal totDrugCostPaidToPharmacy;
    private Integer ciPaperClaimFlag;
    private Integer ciDirectReimbursementFlag;
    private Integer ciTestClaimFlag;
    private Integer ciBatchProcessedFlag;
    private Integer ciOtherProcessorFlag;
    private String claimSource;
    private String cardholderLastName;
    private String cardholderFirstName;
    private Character cardholderGender;
    private String submittedPatientFname;
    private String submittedPatientLname;
    private Long priorAuthCode;
    private Long fdbGcn;
    private String therapeuticClassDesc;
    private String pharmacyAddress1;
    private String pharmacyAddress2;
    private String pharmacyCity;
    private String pharmacyState;
    private String pharmacyZipCode;
    private String pharmacyPhone;
    private String prescriberFirstName;
    private String prescriberLastName;
    private String prescriberAddress1;
    private String prescriberAddress2;
    private String prescriberPhone;
    private String prescriberCity;
    private String prescriberState;
    private String prescriberZipCode;
    private String deaNumber;
    private BigDecimal usualCustomaryAmount;
    private String prescriberNpi;
    private String prescriberSpecialty;
    private String isValidPhone;
    private Date effectiveDate;
    private String fileTypeCode;
    private String fileName;
    private Long inputReferenceNumber;
    private Integer enrollmentId;
    private String communicationSourceCode;
    private Date enrollmentDate;
    private String enrollmentPath;
    private String otherCoverageCode;
    private String otherPayerRejectionCode;
    private String otherPayerIdQualifier;
    private String otherPayerId;
    private int redemptionId;
    private Date postingpDateTimeStamp;
    private Date reversalDateTimeStamp;
    private Double pharmacyGrossAmount;
    private Double planPharmacyAmount;

    private List<RedemptionIngredient> ingredientList;
    private BigInteger totalRxTransactions;
    private BigInteger totalPatientOptins;
    private String sumOfAWP;

    public DailyRedemption() {
    }

//    @EmbeddedId
//    @AttributeOverrides({
//        @AttributeOverride(name = "transactionNumber", column
//                = @Column(name = "Transaction_Number", nullable = false, length = 20)),
//        @AttributeOverride(name = "claimStatus", column
//                = @Column(name = "Claim_Status", nullable = false))})
    public DailyRedemptionId getId() {
        return this.id;
    }

    public void setId(DailyRedemptionId id) {
        this.id = id;
    }

//    @Column(name = "Submitted_Id", nullable = false, length = 11)
    public String getSubmittedId() {
        return this.submittedId;
    }

    public void setSubmittedId(String submittedId) {
        this.submittedId = submittedId;
    }

//    @Column(name = "CampaignId")
    public Integer getCampaignId() {
        return this.campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

//    @Column(name = "Redemption_Channel_Id")
    public int getRedemptionChannelId() {
        return this.redemptionChannelId;
    }

    public void setRedemptionChannelId(int redemptionChannelId) {
        this.redemptionChannelId = redemptionChannelId;
    }

//    @Column(name = "Redemption_Channel_Title", length = 50)
    public String getRedemptionChannelTitle() {
        return this.redemptionChannelTitle;
    }

    public void setRedemptionChannelTitle(String redemptionChannelTitle) {
        this.redemptionChannelTitle = redemptionChannelTitle;
    }

//    @Column(name = "Program_Code", length = 25)
    public String getProgramCode() {
        return this.programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

//    @Column(name = "Rx_Group_Number", nullable = true, length = 15)
    public String getRxGroupNumber() {
        return this.rxGroupNumber;
    }

    public void setRxGroupNumber(String rxGroupNumber) {
        this.rxGroupNumber = rxGroupNumber;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Fill_Date", nullable = true, length = 10)
    public Date getFillDate() {
        return this.fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Cardholder_DOB", nullable = true, length = 10)
    public Date getCardholderDob() {
        return this.cardholderDob;
    }

    public void setCardholderDob(Date cardholderDob) {
        this.cardholderDob = cardholderDob;
    }

//    @Column(name = "Member_Id", length = 25)
    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

//    @Column(name = "GPI")
    public String getGpi() {
        return this.gpi;
    }

    public void setGpi(String gpi) {
        this.gpi = gpi;
    }

   // @Column(name = "Submitted_Cardholder_First_Name", length = 50)
    public String getSubmittedCardholderFirstName() {
        return this.submittedCardholderFirstName;
    }

    public void setSubmittedCardholderFirstName(String submittedCardholderFirstName) {
        this.submittedCardholderFirstName = submittedCardholderFirstName;
    }

 //   @Column(name = "Submitted_Cardholder_Last_Name", length = 50)
    public String getSubmittedCardholderLastName() {
        return this.submittedCardholderLastName;
    }

    public void setSubmittedCardholderLastName(String submittedCardholderLastName) {
        this.submittedCardholderLastName = submittedCardholderLastName;
    }
//
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

//    @Column(name = "Claim_Authorization_Number", length = 20)
    public String getClaimAuthorizationNumber() {
        return this.claimAuthorizationNumber;
    }

    public void setClaimAuthorizationNumber(String claimAuthorizationNumber) {
        this.claimAuthorizationNumber = claimAuthorizationNumber;
    }

//    @Column(name = "Orignal_Claim_Authorization_Number", length = 20)
    public String getOrignalClaimAuthorizationNumber() {
        return this.orignalClaimAuthorizationNumber;
    }

    public void setOrignalClaimAuthorizationNumber(String orignalClaimAuthorizationNumber) {
        this.orignalClaimAuthorizationNumber = orignalClaimAuthorizationNumber;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Posting_Date", nullable = true, length = 19)
    public Date getPostingDate() {
        return this.postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Reversal_Date", length = 19)
    public Date getReversalDate() {
        return this.reversalDate;
    }

    public void setReversalDate(Date reversalDate) {
        this.reversalDate = reversalDate;
    }

//    @Column(name = "Pharmacy_NCPDP", nullable = true)
    public long getPharmacyNcpdp() {
        return this.pharmacyNcpdp;
    }

    public void setPharmacyNcpdp(long pharmacyNcpdp) {
        this.pharmacyNcpdp = pharmacyNcpdp;
    }

//    @Column(name = "Pharmacy_Name", length = 50)
    public String getPharmacyName() {
        return this.pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

//    @Column(name = "Cardholder_ID", nullable = true, length = 20)
    public String getCardholderId() {
        return this.cardholderId;
    }

    public void setCardholderId(String cardholderId) {
        this.cardholderId = cardholderId;
    }

//    @Column(name = "Rx_Written_Date", nullable = true, length = 10)
    public String getRxWrittenDate() {
        return this.rxWrittenDate;
    }

    public void setRxWrittenDate(String rxWrittenDate) {
        this.rxWrittenDate = rxWrittenDate;
    }

//    @Column(name = "Prescription_Number", nullable = true, length = 12)
    public String getPrescriptionNumber() {
        return this.prescriptionNumber;
    }

    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

//    @Column(name = "Refills_Used", nullable = true)
    public int getRefillsUsed() {
        return this.refillsUsed;
    }

    public void setRefillsUsed(int refillsUsed) {
        this.refillsUsed = refillsUsed;
    }

//    @Column(name = "Refills_Allowed")
    public Integer getRefillsAllowed() {
        return this.refillsAllowed;
    }

    public void setRefillsAllowed(Integer refillsAllowed) {
        this.refillsAllowed = refillsAllowed;
    }

    //@Column(name = "NDC_Number", nullable = true, length = 12)
    public String getNdcNumber() {
        return this.ndcNumber;
    }

    public void setNdcNumber(String ndcNumber) {
        this.ndcNumber = ndcNumber;
    }

   // @Column(name = "Product_Name", nullable = true, length = 35)
    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

   // @Column(name = "Drug_Strength", length = 10)
    public String getDrugStrength() {
        return this.drugStrength;
    }

    public void setDrugStrength(String drugStrength) {
        this.drugStrength = drugStrength;
    }

    //@Column(name = "Drug_Dosage_Form", length = 10)
    public String getDrugDosageForm() {
        return this.drugDosageForm;
    }

    public void setDrugDosageForm(String drugDosageForm) {
        this.drugDosageForm = drugDosageForm;
    }

    //@Column(name = "Quantity", nullable = true, precision = 22, scale = 0)
    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    //@Column(name = "Days_Supply", nullable = true)
    public int getDaysSupply() {
        return this.daysSupply;
    }

    public void setDaysSupply(int daysSupply) {
        this.daysSupply = daysSupply;
    }

    //@Column(name = "Ingredient_Cost_Paid", precision = 11, scale = 3)
    public BigDecimal getIngredientCostPaid() {
        return this.ingredientCostPaid;
    }

    public void setIngredientCostPaid(BigDecimal ingredientCostPaid) {
        this.ingredientCostPaid = ingredientCostPaid;
    }

  //  @Column(name = "Dispense_Fee_Paid", precision = 22, scale = 0)
    public Double getDispenseFeePaid() {
        return this.dispenseFeePaid;
    }

    public void setDispenseFeePaid(Double dispenseFeePaid) {
        this.dispenseFeePaid = dispenseFeePaid;
    }

    //@Column(name = "Flat_Sales_Tax_Amount_Paid", precision = 22, scale = 0)
    public Double getFlatSalesTaxAmountPaid() {
        return this.flatSalesTaxAmountPaid;
    }

    public void setFlatSalesTaxAmountPaid(Double flatSalesTaxAmountPaid) {
        this.flatSalesTaxAmountPaid = flatSalesTaxAmountPaid;
    }

   // @Column(name = "Professional_Service_Fee", precision = 22, scale = 0)
    public Double getProfessionalServiceFee() {
        return this.professionalServiceFee;
    }

    public void setProfessionalServiceFee(Double professionalServiceFee) {
        this.professionalServiceFee = professionalServiceFee;
    }

  //  @Column(name = "Pt_Out_Of_Pocket", nullable = true, precision = 11, scale = 3)
    public BigDecimal getPtOutOfPocket() {
        return this.ptOutOfPocket;
    }

    public void setPtOutOfPocket(BigDecimal ptOutOfPocket) {
        this.ptOutOfPocket = ptOutOfPocket;
    }

   // @Column(name = "Price_Source", length = 20)
    public String getPriceSource() {
        return this.priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }

   // @Column(name = "Pharmacy_NPI", length = 10)
    public String getPharmacyNpi() {
        return this.pharmacyNpi;
    }

    public void setPharmacyNpi(String pharmacyNpi) {
        this.pharmacyNpi = pharmacyNpi;
    }

  //  @Column(name = "Tot_Drug_Cost_Paid_To_Pharmacy", precision = 11, scale = 3)
    public BigDecimal getTotDrugCostPaidToPharmacy() {
        return this.totDrugCostPaidToPharmacy;
    }

    public void setTotDrugCostPaidToPharmacy(BigDecimal totDrugCostPaidToPharmacy) {
        this.totDrugCostPaidToPharmacy = totDrugCostPaidToPharmacy;
    }

    //@Column(name = "CI_Paper_Claim_Flag")
    public Integer getCiPaperClaimFlag() {
        return this.ciPaperClaimFlag;
    }

    public void setCiPaperClaimFlag(Integer ciPaperClaimFlag) {
        this.ciPaperClaimFlag = ciPaperClaimFlag;
    }

   // @Column(name = "CI_Direct_Reimbursement_Flag")
    public Integer getCiDirectReimbursementFlag() {
        return this.ciDirectReimbursementFlag;
    }

    public void setCiDirectReimbursementFlag(Integer ciDirectReimbursementFlag) {
        this.ciDirectReimbursementFlag = ciDirectReimbursementFlag;
    }

   // @Column(name = "CI_Test_Claim_Flag")
    public Integer getCiTestClaimFlag() {
        return this.ciTestClaimFlag;
    }

    public void setCiTestClaimFlag(Integer ciTestClaimFlag) {
        this.ciTestClaimFlag = ciTestClaimFlag;
    }

 //   @Column(name = "CI_Batch_Processed_Flag")
    public Integer getCiBatchProcessedFlag() {
        return this.ciBatchProcessedFlag;
    }

    public void setCiBatchProcessedFlag(Integer ciBatchProcessedFlag) {
        this.ciBatchProcessedFlag = ciBatchProcessedFlag;
    }

//    @Column(name = "CI_Other_Processor_Flag")
    public Integer getCiOtherProcessorFlag() {
        return this.ciOtherProcessorFlag;
    }

    public void setCiOtherProcessorFlag(Integer ciOtherProcessorFlag) {
        this.ciOtherProcessorFlag = ciOtherProcessorFlag;
    }

 //   @Column(name = "Claim_Source", length = 20)
    public String getClaimSource() {
        return this.claimSource;
    }

    public void setClaimSource(String claimSource) {
        this.claimSource = claimSource;
    }

  //  @Column(name = "Cardholder_Last_Name", length = 30)
    public String getCardholderLastName() {
        return this.cardholderLastName;
    }

    public void setCardholderLastName(String cardholderLastName) {
        this.cardholderLastName = cardholderLastName;
    }

  //  @Column(name = "Cardholder_First_Name", length = 30)
    public String getCardholderFirstName() {
        return this.cardholderFirstName;
    }

    public void setCardholderFirstName(String cardholderFirstName) {
        this.cardholderFirstName = cardholderFirstName;
    }

   // @Column(name = "Cardholder_Gender", length = 1)
    public Character getCardholderGender() {
        return this.cardholderGender;
    }

    public void setCardholderGender(Character cardholderGender) {
        this.cardholderGender = cardholderGender;
    }

    //@Column(name = "Submitted_Patient_Fname", length = 12)
    public String getSubmittedPatientFname() {
        return this.submittedPatientFname;
    }

    public void setSubmittedPatientFname(String submittedPatientFname) {
        this.submittedPatientFname = submittedPatientFname;
    }

  //  @Column(name = "Submitted_Patient_LName", length = 15)
    public String getSubmittedPatientLname() {
        return this.submittedPatientLname;
    }

    public void setSubmittedPatientLname(String submittedPatientLname) {
        this.submittedPatientLname = submittedPatientLname;
    }

   // @Column(name = "Prior_Auth_Code")
    public Long getPriorAuthCode() {
        return this.priorAuthCode;
    }

    public void setPriorAuthCode(Long priorAuthCode) {
        this.priorAuthCode = priorAuthCode;
    }

//    @Column(name = "FDB_GCN")
    public Long getFdbGcn() {
        return this.fdbGcn;
    }

    public void setFdbGcn(Long fdbGcn) {
        this.fdbGcn = fdbGcn;
    }

//    @Column(name = "Therapeutic_Class_Desc", length = 50)
    public String getTherapeuticClassDesc() {
        return this.therapeuticClassDesc;
    }

    public void setTherapeuticClassDesc(String therapeuticClassDesc) {
        this.therapeuticClassDesc = therapeuticClassDesc;
    }

//    @Column(name = "Pharmacy_Address_1", length = 250)
    public String getPharmacyAddress1() {
        return this.pharmacyAddress1;
    }

    public void setPharmacyAddress1(String pharmacyAddress1) {
        this.pharmacyAddress1 = pharmacyAddress1;
    }

//    @Column(name = "Pharmacy_Address_2", length = 35)
    public String getPharmacyAddress2() {
        return this.pharmacyAddress2;
    }

    public void setPharmacyAddress2(String pharmacyAddress2) {
        this.pharmacyAddress2 = pharmacyAddress2;
    }

    //@Column(name = "Pharmacy_City", length = 18)
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

//    @Column(name = "Pharmacy_Phone", length = 10)
    public String getPharmacyPhone() {
        return this.pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
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

//    @Column(name = "Prescriber_Address_1", length = 40)
    public String getPrescriberAddress1() {
        return this.prescriberAddress1;
    }

    public void setPrescriberAddress1(String prescriberAddress1) {
        this.prescriberAddress1 = prescriberAddress1;
    }

//    @Column(name = "Prescriber_Address_2", length = 40)
    public String getPrescriberAddress2() {
        return this.prescriberAddress2;
    }

    public void setPrescriberAddress2(String prescriberAddress2) {
        this.prescriberAddress2 = prescriberAddress2;
    }

//    @Column(name = "Prescriber_City", length = 24)
    public String getPrescriberCity() {
        return this.prescriberCity;
    }

    public void setPrescriberCity(String prescriberCity) {
        this.prescriberCity = prescriberCity;
    }

    //@Column(name = "Prescriber_State", length = 2)
    public String getPrescriberState() {
        return this.prescriberState;
    }

    public void setPrescriberState(String prescriberState) {
        this.prescriberState = prescriberState;
    }

//    @Column(name = "Prescriber_Zip_Code", length = 10)
    public String getPrescriberZipCode() {
        return this.prescriberZipCode;
    }

    public void setPrescriberZipCode(String prescriberZipCode) {
        this.prescriberZipCode = prescriberZipCode;
    }

//    @Column(name = "DEA_Number", length = 9)
    public String getDeaNumber() {
        return this.deaNumber;
    }

    public void setDeaNumber(String deaNumber) {
        this.deaNumber = deaNumber;
    }

//    @Column(name = "Usual_Customary_Amount", precision = 8)
    public BigDecimal getUsualCustomaryAmount() {
        return this.usualCustomaryAmount;
    }

    public void setUsualCustomaryAmount(BigDecimal usualCustomaryAmount) {
        this.usualCustomaryAmount = usualCustomaryAmount;
    }

//    @Column(name = "Prescriber_NPI", length = 12)
    public String getPrescriberNpi() {
        return this.prescriberNpi;
    }

    public void setPrescriberNpi(String prescriberNpi) {
        this.prescriberNpi = prescriberNpi;
    }

//    @Column(name = "Prescriber_Specialty", nullable = true, length = 40)
    public String getPrescriberSpecialty() {
        return this.prescriberSpecialty;
    }

    public void setPrescriberSpecialty(String prescriberSpecialty) {
        this.prescriberSpecialty = prescriberSpecialty;
    }

//    @Column(name = "isValidPhone", nullable = false, length = 3)
    public String getIsValidPhone() {
        return this.isValidPhone;
    }

    public void setIsValidPhone(String isValidPhone) {
        this.isValidPhone = isValidPhone;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Effective_Date", length = 19)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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

//    @Column(name = "Other_Coverage_Code", length = 10)
    public String getOtherCoverageCode() {
        return this.otherCoverageCode;
    }

    public void setOtherCoverageCode(String otherCoverageCode) {
        this.otherCoverageCode = otherCoverageCode;
    }

//    @Column(name = "Other_Payer_Rejection_Code", length = 25)
    public String getOtherPayerRejectionCode() {
        return this.otherPayerRejectionCode;
    }

    public void setOtherPayerRejectionCode(String otherPayerRejectionCode) {
        this.otherPayerRejectionCode = otherPayerRejectionCode;
    }

//    @Column(name = "Other_Payer_Id_Qualifier", length = 10)
    public String getOtherPayerIdQualifier() {
        return this.otherPayerIdQualifier;
    }

    public void setOtherPayerIdQualifier(String otherPayerIdQualifier) {
        this.otherPayerIdQualifier = otherPayerIdQualifier;
    }

//    @Column(name = "Other_Payer_Id", length = 20)
    public String getOtherPayerId() {
        return this.otherPayerId;
    }

    public void setOtherPayerId(String otherPayerId) {
        this.otherPayerId = otherPayerId;
    }

//    @Column(name = "Redemption_Id")
    public int getRedemptionId() {
        return this.redemptionId;
    }

    public void setRedemptionId(int redemptionId) {
        this.redemptionId = redemptionId;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Posting_Date_TimeStamp", length = 19)
    public Date getPostingpDateTimeStamp() {
        return postingpDateTimeStamp;
    }

    public void setPostingpDateTimeStamp(Date postingpDateTimeStamp) {
        this.postingpDateTimeStamp = postingpDateTimeStamp;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Reversal_Date_TimeStamp", length = 19)
    public Date getReversalDateTimeStamp() {
        return reversalDateTimeStamp;
    }

    public void setReversalDateTimeStamp(Date reversalDateTimeStamp) {
        this.reversalDateTimeStamp = reversalDateTimeStamp;
    }

//    @Transient

    public List<RedemptionIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RedemptionIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

//    @Column(name = "Pharmacy_Gross_Amount", precision = 22, scale = 0)
    public Double getPharmacyGrossAmount() {
        return pharmacyGrossAmount;
    }

    public void setPharmacyGrossAmount(Double pharmacyGrossAmount) {
        this.pharmacyGrossAmount = pharmacyGrossAmount;
    }

   // @Column(name = "Plan_Pharmacy_Amount", precision = 22, scale = 0)
    public Double getPlanPharmacyAmount() {
        return planPharmacyAmount;
    }

    public void setPlanPharmacyAmount(Double planPharmacyAmount) {
        this.planPharmacyAmount = planPharmacyAmount;
    }

   // @Transient
    public BigInteger getTotalRxTransactions() {
        return totalRxTransactions;
    }

    public void setTotalRxTransactions(BigInteger totalRxTransactions) {
        this.totalRxTransactions = totalRxTransactions;
    }

   // @Transient
    public BigInteger getTotalPatientOptins() {
        return totalPatientOptins;
    }

    public void setTotalPatientOptins(BigInteger totalPatientOptins) {
        this.totalPatientOptins = totalPatientOptins;
    }

   // @Transient
    public String getSumOfAWP() {
        return sumOfAWP;
    }

    public void setSumOfAWP(String sumOfAWP) {
        this.sumOfAWP = sumOfAWP;
    }
    
  //  @Column(name = "Prescriber_Phone", length = 20)
    public String getPrescriberPhone() {
        return this.prescriberPhone;
    }

    public void setPrescriberPhone(String prescriberPhone) {
        this.prescriberPhone = prescriberPhone;
    }
}
