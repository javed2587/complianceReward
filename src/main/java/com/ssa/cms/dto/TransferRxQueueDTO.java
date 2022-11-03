/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import com.ssa.cms.model.OrderTransferImages;
import com.ssa.cms.model.TransferDetail;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ghirat.shah
 */
@SuppressWarnings("serial")
public class TransferRxQueueDTO implements Serializable {

    Date receivedDate;
    private Date updatedDate;
    private String updatedBy;
    String patientName;
    Date patientDOB;
    String hasRxCard;
    String rxSearched;
    Integer quantity;
    Double cashPriceQuated;
    String status;

    private String statusLabel;
    int statusId;
    Integer patientId;
    Integer rxId;
    String disabled;
    String drug;
    private String drugType;
    private String strength;
    Date requestDate;
    String pharmacyName;
    String pharmacyPhone;
    String rxNumber;
    String qouted;
    String ptVideo;
    Integer transferDetailId;
    String orderId;
    String orderType;
    private TransferDetail transferDetail;
    private String orderHistoryMessage;
    private String drugNameWithoutStrength;
    private boolean sameDayDelivery;
    private String allergies;
    private String gender;
    private String zip;
    private boolean payCash;
    private boolean multiRx;

    private int refillsRemaining;
    private int refillsAllowed;
    private int daysSupply;
    private int pointsEarned;
    private Double rxAcqCost;
    private String rxAcqCostStr;
    private Double rxReimbCost;
    private Double originalPtCopay;
    private String originalPtCopayStr;
    private Double additionalMargin;
    private Double profitMargin;
    private Double amountWithoutMargin;
    private Double handlingFee;
    private String handlingFeeStr;
    private Double finalPayment;
    private String finalPaymentStr;
    private double paymentExcludingDelivery;
    private String paymentExcludingDeliveryStr;
    private Date nextRefillDate;
    private String filledDate;

    private String paymentMode;
    private String prescriberName;
    private String prescriberPhone;
    private String prescriberNPI;
    private String redeemPoints;
    private Double redeemPointsCost;
    private String awardedPoints;
    private Date patientEnrollmentDate;
    private boolean isOldPatient;
    private String transferImage;
    private String comments;
    private String carrier;
    private String tracking;
    private String clerk;
    private String paymentType;
    private String paymentTypeDisabled;
    private String selfPayCheck;
    private String insuranceCheck;
    private Double totalDrugPrice;
    private Double mfrCost;
    private Double profitShareCost;
    private String deliveryService;
    private int deliveryId;
    private String publicInsCheck;
    private String insuranceType;
    private Date rxExpiredDate;
    private String rxExpiredDateStr;
    private Date rxDate;
    private String deliveryFeeWaived;
    private String finalPaymentMode;
    private int profitSharePoint;
    private float actualProfitShare;
    private String actualrofitShareStr;
    private Date olversion;
    private int multiRxCount;
    private String multiRxLabel;
    private boolean userInput;
    private String oldRxNo;
    private String cashPayLabel;
    private String requestControlNumber;
    private String requestType;
    private boolean deliveryUrgent;
    private String userInputStr;
    private String patientResponse;
    private String medicationSpecMsg;
    private Double underQuotedPrice;
    private String underQuotedPriceStr;
    private String deliverycarrier;
    private Date deliveryTo;
    private Date deliveryFrom;
    private String requiredResponse;
    private String daysToRefill;
    private String daysToExpire;
    private String miles;
    private Double sellingPrice;
    private String sellingPriceStr;
    private String deliveryDate;
    private String deliveryAddress;
    private String insuranceCheckCommercial;
    private boolean drugIncluded;
    private String processedRxNo;
    private Integer careGiverRequest;
    private int caregiverRequestApproved;
    private int caregiverRequestDisapproved;
    private int disabledFld;
    private String RequestControlNumber1;
    private String RequestControlNumber2;
    private boolean requiresDeletion;
    private int handDelivery;
    private int handDeliveryAccepted;
    private int orderPharmacyId;
    private double finalCopay;
    private String finalCopayStr;
    private String requestTypeBgColor;
    private String textColor;
    private boolean rxScan;
    private boolean labelScan;
    private String prescriberLastName;
    private String systemGeneratedRxNumber;
    private boolean ptOverridePrice;
    private double estimatedPrice;
    private List<OrderTransferImages> orderTransferImages;
    private boolean multipleImages;
    private boolean nextImage;
    private boolean prevImage;
    private int imageId;
    private String assignedOrderId;
    private String nextRefillDateStr;
    private boolean imageVerificationRequired;
    private int dependentId;
    private String pharmacyExtension;
    private String prescriberExtension;
    private boolean dependentFlag;
    private boolean adultFlag;
    private boolean brandOnly;
    private String reqPosted;
    private String rxDateStr;
    private String subInterpretedIImageTable;
    private String pickUpPaperRx;
    private String memberDependant;
    private String pharmacyResponse;
    private String quantityStr;
    private String orderStatus;
    private String paymentAuthorization;
    private Date shippedOn;
    private boolean preauthorized;
    private int pharmacyId;

    private long processedOrderCount;

    private boolean questionAuthorization;

    private int questionId;
    private String question;
    private String shippingStatus;
    private int readyToDeliverId;
    private int sortByMbrReq;
    private int sortByStatus;
    private int sortByDeliveryPref;
    private String rxNo;
    private String careGiverDetailUrl;
    private String rxDetailUrl;
    private String firstName;
    private String lastName;
    private String rxExpiry;
    private int rxCount;
    private String rxControlNumber;

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the drug
     */
    public String getDrug() {
        return drug;
    }

    /**
     * @param drug the drug to set
     */
    public void setDrug(String drug) {
        this.drug = drug;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the pharmacyName
     */
    public String getPharmacyName() {
        return pharmacyName;
    }

    /**
     * @param pharmacyName the pharmacyName to set
     */
    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    /**
     * @return the pharmacyPhone
     */
    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    /**
     * @param pharmacyPhone the pharmacyPhone to set
     */
    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

    /**
     * @return the rxNumber
     */
    public String getRxNumber() {
        return rxNumber;
    }

    /**
     * @param rxNumber the rxNumber to set
     */
    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    /**
     * @return the qouted
     */
    public String getQouted() {
        return qouted;
    }

    /**
     * @param qouted the qouted to set
     */
    public void setQouted(String qouted) {
        this.qouted = qouted;
    }

    /**
     * @return the ptVideo
     */
    public String getPtVideo() {
        return ptVideo;
    }

    /**
     * @param ptVideo the ptVideo to set
     */
    public void setPtVideo(String ptVideo) {
        this.ptVideo = ptVideo;
    }

    /**
     * @return the disabled
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientDOB(Date patientDOB) {
        this.patientDOB = patientDOB;
    }

    public void setHasRxCard(String hasRxCard) {
        this.hasRxCard = hasRxCard;
    }

    public void setRxSearched(String rxSearched) {
        this.rxSearched = rxSearched;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCashPriceQuated(Double cashPriceQuated) {
        this.cashPriceQuated = cashPriceQuated;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public Date getPatientDOB() {
        return patientDOB;
    }

    public String getHasRxCard() {
        return hasRxCard;
    }

    public String getRxSearched() {
        return rxSearched;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getCashPriceQuated() {
        return cashPriceQuated;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getRxId() {
        return rxId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setRxId(Integer rxId) {
        this.rxId = rxId;
    }

    /**
     * @return the transferDetailId
     */
    public Integer getTransferDetailId() {
        return transferDetailId;
    }

    /**
     * @param transferDetailId the transferDetailId to set
     */
    public void setTransferDetailId(Integer transferDetailId) {
        this.transferDetailId = transferDetailId;
    }

    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getOrderHistoryMessage() {
        return orderHistoryMessage;
    }

    public void setOrderHistoryMessage(String orderHistoryMessage) {
        this.orderHistoryMessage = orderHistoryMessage;
    }

    public TransferDetail getTransferDetail() {
        return transferDetail;
    }

    public void setTransferDetail(TransferDetail transferDetail) {
        this.transferDetail = transferDetail;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDrugNameWithoutStrength() {
        return drugNameWithoutStrength;
    }

    public void setDrugNameWithoutStrength(String drugNameWithoutStrength) {
        this.drugNameWithoutStrength = drugNameWithoutStrength;
    }

    public boolean isSameDayDelivery() {
        return sameDayDelivery;
    }

    public void setSameDayDelivery(Boolean sameDayDelivery) {
        this.sameDayDelivery = sameDayDelivery;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isPayCash() {
        return payCash;
    }

    public void setPayCash(boolean payCash) {
        this.payCash = payCash;
    }

    public boolean getMultiRx() {
        return multiRx;
    }

    public void setMultiRx(boolean multiRx) {
        this.multiRx = multiRx;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getDaysSupply() {
        return daysSupply;
    }

    public void setDaysSupply(int daysSupply) {
        this.daysSupply = daysSupply;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Double getRxAcqCost() {
        return rxAcqCost;
    }

    public void setRxAcqCost(Double rxAcqCost) {
        this.rxAcqCost = rxAcqCost;
    }

    public String getRxAcqCostStr() {
        return rxAcqCostStr;
    }

    public void setRxAcqCostStr(String rxAcqCostStr) {
        this.rxAcqCostStr = rxAcqCostStr;
    }

    public Double getRxReimbCost() {
        return rxReimbCost;
    }

    public void setRxReimbCost(Double rxReimbCost) {
        this.rxReimbCost = rxReimbCost;
    }

    public Double getOriginalPtCopay() {
        return originalPtCopay;
    }

    public void setOriginalPtCopay(Double originalPtCopay) {
        this.originalPtCopay = originalPtCopay;
    }

    public String getOriginalPtCopayStr() {
        return originalPtCopayStr;
    }

    public void setOriginalPtCopayStr(String originalPtCopayStr) {
        this.originalPtCopayStr = originalPtCopayStr;
    }

    public Double getAdditionalMargin() {
        return additionalMargin;
    }

    public void setAdditionalMargin(Double additionalMargin) {
        this.additionalMargin = additionalMargin;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public Double getAmountWithoutMargin() {
        return amountWithoutMargin;
    }

    public void setAmountWithoutMargin(Double amountWithoutMargin) {
        this.amountWithoutMargin = amountWithoutMargin;
    }

    public Date getNextRefillDate() {
        return nextRefillDate;
    }

    public Double getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(Double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public String getHandlingFeeStr() {
        return handlingFeeStr;
    }

    public void setHandlingFeeStr(String handlingFeeStr) {
        this.handlingFeeStr = handlingFeeStr;
    }

    public void setNextRefillDate(Date nextRefillDate) {
        this.nextRefillDate = nextRefillDate;
    }

    public int getRefillsRemaining() {
        return refillsRemaining;
    }

    public void setRefillsRemaining(int refillsRemaining) {
        this.refillsRemaining = refillsRemaining;
    }

    public int getRefillsAllowed() {
        return refillsAllowed;
    }

    public void setRefillsAllowed(int refillsAllowed) {
        this.refillsAllowed = refillsAllowed;
    }

    public String getFilledDate() {
        return filledDate;
    }

    public void setFilledDate(String filledDate) {
        this.filledDate = filledDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPrescriberName() {
        return prescriberName;
    }

    public void setPrescriberName(String prescriberName) {
        this.prescriberName = prescriberName;
    }

    public String getPrescriberPhone() {
        return prescriberPhone;
    }

    public void setPrescriberPhone(String prescriberPhone) {
        this.prescriberPhone = prescriberPhone;
    }

    public String getPrescriberNPI() {
        return prescriberNPI;
    }

    public void setPrescriberNPI(String prescriberNPI) {
        this.prescriberNPI = prescriberNPI;
    }

    public Double getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(Double finalPayment) {
        this.finalPayment = finalPayment;
    }

    public String getFinalPaymentStr() {
        return finalPaymentStr;
    }

    public void setFinalPaymentStr(String finalPaymentStr) {
        this.finalPaymentStr = finalPaymentStr;
    }

    public double getPaymentExcludingDelivery() {
        return paymentExcludingDelivery;
    }

    public void setPaymentExcludingDelivery(double paymentExcludingDelivery) {
        this.paymentExcludingDelivery = paymentExcludingDelivery;
    }

    public String getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(String redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public Double getRedeemPointsCost() {
        return redeemPointsCost;
    }

    public void setRedeemPointsCost(Double redeemPointsCost) {
        this.redeemPointsCost = redeemPointsCost;
    }

    public String getAwardedPoints() {
        return awardedPoints;
    }

    public void setAwardedPoints(String awardedPoints) {
        this.awardedPoints = awardedPoints;
    }

    public Date getPatientEnrollmentDate() {
        return patientEnrollmentDate;
    }

    public void setPatientEnrollmentDate(Date patientEnrollmentDate) {
        this.patientEnrollmentDate = patientEnrollmentDate;
    }

    public boolean isIsOldPatient() {
        return isOldPatient;
    }

    public void setIsOldPatient(boolean isOldPatient) {
        this.isOldPatient = isOldPatient;
    }

    public String getTransferImage() {
        return transferImage;
    }

    public void setTransferImage(String transferImage) {
        this.transferImage = transferImage;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDisabled() {
        return paymentTypeDisabled;
    }

    public void setPaymentTypeDisabled(String paymentTypeDisabled) {
        this.paymentTypeDisabled = paymentTypeDisabled;
    }

    public String getSelfPayCheck() {
        return selfPayCheck;
    }

    public void setSelfPayCheck(String selfPayCheck) {
        this.selfPayCheck = selfPayCheck;
    }

    public String getInsuranceCheck() {
        return insuranceCheck;
    }

    public void setInsuranceCheck(String insuranceCheck) {
        this.insuranceCheck = insuranceCheck;
    }

    public Double getTotalDrugPrice() {
        return totalDrugPrice;
    }

    public void setTotalDrugPrice(Double totalDrugPrice) {
        this.totalDrugPrice = totalDrugPrice;
    }

    public Double getMfrCost() {
        return mfrCost;
    }

    public void setMfrCost(Double mfrCost) {
        this.mfrCost = mfrCost;
    }

    public Double getProfitShareCost() {
        return profitShareCost;
    }

    public void setProfitShareCost(Double profitShareCost) {
        this.profitShareCost = profitShareCost;
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(String deliveryService) {
        this.deliveryService = deliveryService;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getPublicInsCheck() {
        return publicInsCheck;
    }

    public void setPublicInsCheck(String publicInsCheck) {
        this.publicInsCheck = publicInsCheck;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Date getRxExpiredDate() {
        return rxExpiredDate;
    }

    public void setRxExpiredDate(Date rxExpiredDate) {
        this.rxExpiredDate = rxExpiredDate;
    }

    public String getRxExpiredDateStr() {
        return rxExpiredDateStr;
    }

    public void setRxExpiredDateStr(String rxExpiredDateStr) {
        this.rxExpiredDateStr = rxExpiredDateStr;
    }

    public String getDeliveryFeeWaived() {
        return deliveryFeeWaived;
    }

    public void setDeliveryFeeWaived(String deliveryFeeWaived) {
        this.deliveryFeeWaived = deliveryFeeWaived;
    }

    public Date getRxDate() {
        return rxDate;
    }

    public void setRxDate(Date rxDate) {
        this.rxDate = rxDate;
    }

    public String getFinalPaymentMode() {
        return finalPaymentMode;
    }

    public void setFinalPaymentMode(String finalPaymentMode) {
        this.finalPaymentMode = finalPaymentMode;
    }

    public int getProfitSharePoint() {
        return profitSharePoint;
    }

    public void setProfitSharePoint(int profitSharePoint) {
        this.profitSharePoint = profitSharePoint;
    }

    public float getActualProfitShare() {
        return actualProfitShare;
    }

    public void setActualProfitShare(float actualProfitShare) {
        this.actualProfitShare = actualProfitShare;
    }

    public String getActualrofitShareStr() {
        return actualrofitShareStr;
    }

    public void setActualrofitShareStr(String actualrofitShareStr) {
        this.actualrofitShareStr = actualrofitShareStr;
    }

    public Date getOlversion() {
        return olversion;
    }

    public void setOlversion(Date olversion) {
        this.olversion = olversion;
    }

    public int getMultiRxCount() {
        return multiRxCount;
    }

    public void setMultiRxCount(int multiRxCount) {
        this.multiRxCount = multiRxCount;
    }

    public String getMultiRxLabel() {
        return multiRxLabel;
    }

    public void setMultiRxLabel(String multiRxLabel) {
        this.multiRxLabel = multiRxLabel;
    }

    public boolean isUserInput() {
        return userInput;
    }

    public void setUserInput(boolean userInput) {
        this.userInput = userInput;
    }

    public String getOldRxNo() {
        return oldRxNo;
    }

    public void setOldRxNo(String oldRxNo) {
        this.oldRxNo = oldRxNo;
    }

    public String getCashPayLabel() {
        return cashPayLabel;
    }

    public void setCashPayLabel(String cashPayLabel) {
        this.cashPayLabel = cashPayLabel;
    }

    public String getRequestControlNumber() {
        return requestControlNumber;
    }

    public void setRequestControlNumber(String requestControlNumber) {
        this.requestControlNumber = requestControlNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public boolean isDeliveryUrgent() {
        return deliveryUrgent;
    }

    public void setDeliveryUrgent(boolean deliveryUrgent) {
        this.deliveryUrgent = deliveryUrgent;
    }

    public String getUserInputStr() {
        return userInputStr;
    }

    public void setUserInputStr(String userInputStr) {
        this.userInputStr = userInputStr;
    }

    public String getPatientResponse() {
        return patientResponse;
    }

    public void setPatientResponse(String patientResponse) {
        this.patientResponse = patientResponse;
    }

    public String getMedicationSpecMsg() {
        return medicationSpecMsg;
    }

    public void setMedicationSpecMsg(String medicationSpecMsg) {
        this.medicationSpecMsg = medicationSpecMsg;
    }

    public Double getUnderQuotedPrice() {
        return underQuotedPrice;
    }

    public void setUnderQuotedPrice(Double underQuotedPrice) {
        this.underQuotedPrice = underQuotedPrice;
    }

    public String getUnderQuotedPriceStr() {
        return underQuotedPriceStr;
    }

    public void setUnderQuotedPriceStr(String underQuotedPriceStr) {
        this.underQuotedPriceStr = underQuotedPriceStr;
    }

    public String getDeliverycarrier() {
        return deliverycarrier;
    }

    public void setDeliverycarrier(String deliverycarrier) {
        this.deliverycarrier = deliverycarrier;
    }

    public Date getDeliveryTo() {
        return deliveryTo;
    }

    public void setDeliveryTo(Date deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    public Date getDeliveryFrom() {
        return deliveryFrom;
    }

    public void setDeliveryFrom(Date deliveryFrom) {
        this.deliveryFrom = deliveryFrom;
    }

    public String getRequiredResponse() {
        return requiredResponse;
    }

    public void setRequiredResponse(String requiredResponse) {
        this.requiredResponse = requiredResponse;
    }

    public String getDaysToRefill() {
        return daysToRefill;
    }

    public void setDaysToRefill(String daysToRefill) {
        this.daysToRefill = daysToRefill;
    }

    public String getDaysToExpire() {
        return daysToExpire;
    }

    public void setDaysToExpire(String daysToExpire) {
        this.daysToExpire = daysToExpire;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSellingPriceStr() {
        return sellingPriceStr;
    }

    public void setSellingPriceStr(String sellingPriceStr) {
        this.sellingPriceStr = sellingPriceStr;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getInsuranceCheckCommercial() {
        return insuranceCheckCommercial;
    }

    public void setInsuranceCheckCommercial(String insuranceCheckCommercial) {
        this.insuranceCheckCommercial = insuranceCheckCommercial;
    }

    public boolean isDrugIncluded() {
        return drugIncluded;
    }

    public void setDrugIncluded(boolean drugIncluded) {
        this.drugIncluded = drugIncluded;
    }

    public String getProcessedRxNo() {
        return processedRxNo;
    }

    public void setProcessedRxNo(String processedRxNo) {
        this.processedRxNo = processedRxNo;
    }

    public Integer getCareGiverRequest() {
        return careGiverRequest;
    }

    public void setCareGiverRequest(Integer careGiverRequest) {
        this.careGiverRequest = careGiverRequest;
    }

    public int getCaregiverRequestApproved() {
        return caregiverRequestApproved;
    }

    public void setCaregiverRequestApproved(int caregiverRequestApproved) {
        this.caregiverRequestApproved = caregiverRequestApproved;
    }

    public int getCaregiverRequestDisapproved() {
        return caregiverRequestDisapproved;
    }

    public void setCaregiverRequestDisapproved(int caregiverRequestDisapproved) {
        this.caregiverRequestDisapproved = caregiverRequestDisapproved;
    }

    public int getDisabledFld() {
        return disabledFld;
    }

    public void setDisabledFld(int disabledFld) {
        this.disabledFld = disabledFld;
    }

    public String getRequestControlNumber1() {
        return RequestControlNumber1;
    }

    public void setRequestControlNumber1(String RequestControlNumber1) {
        this.RequestControlNumber1 = RequestControlNumber1;
    }

    public String getRequestControlNumber2() {
        return RequestControlNumber2;
    }

    public void setRequestControlNumber2(String RequestControlNumber2) {
        this.RequestControlNumber2 = RequestControlNumber2;
    }

    public boolean isRequiresDeletion() {
        return requiresDeletion;
    }

    public void setRequiresDeletion(boolean requiresDeletion) {
        this.requiresDeletion = requiresDeletion;
    }

    public int getHandDelivery() {
        return handDelivery;
    }

    public void setHandDelivery(int handDelivery) {
        this.handDelivery = handDelivery;
    }

    public int getHandDeliveryAccepted() {
        return handDeliveryAccepted;
    }

    public void setHandDeliveryAccepted(int handDeliveryAccepted) {
        this.handDeliveryAccepted = handDeliveryAccepted;
    }

    public int getOrderPharmacyId() {
        return orderPharmacyId;
    }

    public void setOrderPharmacyId(int orderPharmacyId) {
        this.orderPharmacyId = orderPharmacyId;
    }

    public double getFinalCopay() {
        return finalCopay;
    }

    public void setFinalCopay(double finalCopay) {
        this.finalCopay = finalCopay;
    }

    public String getFinalCopayStr() {
        return finalCopayStr;
    }

    public void setFinalCopayStr(String finalCopayStr) {
        this.finalCopayStr = finalCopayStr;
    }

    public String getRequestTypeBgColor() {
        return requestTypeBgColor;
    }

    public void setRequestTypeBgColor(String requestTypeBgColor) {
        this.requestTypeBgColor = requestTypeBgColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public boolean isRxScan() {
        return rxScan;
    }

    public void setRxScan(boolean rxScan) {
        this.rxScan = rxScan;
    }

    public boolean isLabelScan() {
        return labelScan;
    }

    public void setLabelScan(boolean labelScan) {
        this.labelScan = labelScan;
    }

    public String getPrescriberLastName() {
        return prescriberLastName;
    }

    public void setPrescriberLastName(String prescriberLastName) {
        this.prescriberLastName = prescriberLastName;
    }

    public String getSystemGeneratedRxNumber() {
        return systemGeneratedRxNumber;
    }

    public void setSystemGeneratedRxNumber(String systemGeneratedRxNumber) {
        this.systemGeneratedRxNumber = systemGeneratedRxNumber;
    }

    public boolean isPtOverridePrice() {
        return ptOverridePrice;
    }

    public void setPtOverridePrice(boolean ptOverridePrice) {
        this.ptOverridePrice = ptOverridePrice;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public List<OrderTransferImages> getOrderTransferImages() {
        return orderTransferImages;
    }

    public void setOrderTransferImages(List<OrderTransferImages> orderTransferImages) {
        this.orderTransferImages = orderTransferImages;
    }

    public boolean isMultipleImages() {
        return multipleImages;
    }

    public void setMultipleImages(boolean multipleImages) {
        this.multipleImages = multipleImages;
    }

    public boolean isNextImage() {
        return nextImage;
    }

    public void setNextImage(boolean nextImage) {
        this.nextImage = nextImage;
    }

    public boolean isPrevImage() {
        return prevImage;
    }

    public void setPrevImage(boolean prevImage) {
        this.prevImage = prevImage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAssignedOrderId() {
        return assignedOrderId;
    }

    public void setAssignedOrderId(String assignedOrderId) {
        this.assignedOrderId = assignedOrderId;
    }

    public String getNextRefillDateStr() {
        return nextRefillDateStr;
    }

    public void setNextRefillDateStr(String nextRefillDateStr) {
        this.nextRefillDateStr = nextRefillDateStr;
    }

    public boolean isImageVerificationRequired() {
        return imageVerificationRequired;
    }

    public void setImageVerificationRequired(boolean imageVerificationRequired) {
        this.imageVerificationRequired = imageVerificationRequired;
    }

    public int getDependentId() {
        return dependentId;
    }

    public void setDependentId(int dependentId) {
        this.dependentId = dependentId;
    }

    public String getPharmacyExtension() {
        return pharmacyExtension;
    }

    public void setPharmacyExtension(String pharmacyExtension) {
        this.pharmacyExtension = pharmacyExtension;
    }

    public String getPrescriberExtension() {
        return prescriberExtension;
    }

    public void setPrescriberExtension(String prescriberExtension) {
        this.prescriberExtension = prescriberExtension;
    }

    public boolean isDependentFlag() {
        return dependentFlag;
    }

    public void setDependentFlag(boolean dependentFlag) {
        this.dependentFlag = dependentFlag;
    }

    public boolean isAdultFlag() {
        return adultFlag;
    }

    public void setAdultFlag(boolean adultFlag) {
        this.adultFlag = adultFlag;
    }

    public boolean isBrandOnly() {
        return brandOnly;
    }

    public void setBrandOnly(boolean brandOnly) {
        this.brandOnly = brandOnly;
    }

    public String getReqPosted() {
        return reqPosted;
    }

    public void setReqPosted(String reqPosted) {
        this.reqPosted = reqPosted;
    }

    public String getRxDateStr() {
        return rxDateStr;
    }

    public void setRxDateStr(String rxDateStr) {
        this.rxDateStr = rxDateStr;
    }

    public String getSubInterpretedIImageTable() {
        return subInterpretedIImageTable;
    }

    public void setSubInterpretedIImageTable(String subInterpretedIImageTable) {
        this.subInterpretedIImageTable = subInterpretedIImageTable;
    }

    public String getPickUpPaperRx() {
        return pickUpPaperRx;
    }

    public void setPickUpPaperRx(String pickUpPaperRx) {
        this.pickUpPaperRx = pickUpPaperRx;
    }

    public String getMemberDependant() {
        return memberDependant;
    }

    public void setMemberDependant(String memberDependant) {
        this.memberDependant = memberDependant;
    }

    public String getPharmacyResponse() {
        return pharmacyResponse;
    }

    public void setPharmacyResponse(String pharmacyResponse) {
        this.pharmacyResponse = pharmacyResponse;
    }

    public String getQuantityStr() {
        return quantityStr;
    }

    public void setQuantityStr(String quantityStr) {
        this.quantityStr = quantityStr;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentExcludingDeliveryStr() {
        return paymentExcludingDeliveryStr;
    }

    public void setPaymentExcludingDeliveryStr(String paymentExcludingDeliveryStr) {
        this.paymentExcludingDeliveryStr = paymentExcludingDeliveryStr;
    }

    public String getPaymentAuthorization() {
        return paymentAuthorization;
    }

    public void setPaymentAuthorization(String paymentAuthorization) {
        this.paymentAuthorization = paymentAuthorization;
    }

    public Date getShippedOn() {
        return shippedOn;
    }

    public void setShippedOn(Date shippedOn) {
        this.shippedOn = shippedOn;
    }

    public boolean isPreauthorized() {
        return preauthorized;
    }

    public void setPreauthorized(boolean preauthorized) {
        this.preauthorized = preauthorized;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public long getProcessedOrderCount() {
        return processedOrderCount;
    }

    public boolean isQuestionAuthorization() {
        return questionAuthorization;
    }

    public void setProcessedOrderCount(long processedOrderCount) {
        this.processedOrderCount = processedOrderCount;
    }

    public void setQuestionAuthorization(boolean questionAuthorization) {
        this.questionAuthorization = questionAuthorization;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public int getReadyToDeliverId() {
        return readyToDeliverId;
    }

    public void setReadyToDeliverId(int readyToDeliverId) {
        this.readyToDeliverId = readyToDeliverId;
    }

    public int getSortByMbrReq() {
        return sortByMbrReq;
    }

    public void setSortByMbrReq(int sortByMbrReq) {
        this.sortByMbrReq = sortByMbrReq;
    }

    public int getSortByStatus() {
        return sortByStatus;
    }

    public void setSortByStatus(int sortByStatus) {
        this.sortByStatus = sortByStatus;
    }

    public int getSortByDeliveryPref() {
        return sortByDeliveryPref;
    }

    public void setSortByDeliveryPref(int sortByDeliveryPref) {
        this.sortByDeliveryPref = sortByDeliveryPref;
    }

    public String getRxNo() {
        return rxNo;
    }

    public void setRxNo(String rxNo) {
        this.rxNo = rxNo;
    }

    public String getCareGiverDetailUrl() {
        return careGiverDetailUrl;
    }

    public void setCareGiverDetailUrl(String careGiverDetailUrl) {
        this.careGiverDetailUrl = careGiverDetailUrl;
    }

    public String getRxDetailUrl() {
        return rxDetailUrl;
    }

    public void setRxDetailUrl(String rxDetailUrl) {
        this.rxDetailUrl = rxDetailUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRxExpiry() {
        return rxExpiry;
    }

    public void setRxExpiry(String rxExpiry) {
        this.rxExpiry = rxExpiry;
    }

    public int getRxCount() {
        return rxCount;
    }

    public void setRxCount(int rxCount) {
        this.rxCount = rxCount;
    }

    public String getRxControlNumber() {
        return rxControlNumber;
    }

    public void setRxControlNumber(String rxControlNumber) {
        this.rxControlNumber = rxControlNumber;
    }

}
