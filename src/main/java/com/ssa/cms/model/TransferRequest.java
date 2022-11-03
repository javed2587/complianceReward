package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "TransferRequest")
public class TransferRequest implements Serializable {

    private Integer id;
    private Integer patientId;
    private String patientName;
    private String rxNumber;
    private String pharmacyName;
    private String pharmacyPhone;
    private String drug;
    private Integer quantity;
    private String video;
    private String drugImg;
    private Date requestedOn;
    private TransferDetail transferDetail;
    //private PatientProfile patientProfile;
    private Object patientProfile;
    private PatientDeliveryAddress patientDeliveryAddress;
    private DeliveryPreferences deliveryPreferences;
    private String zip;
    private String miles;
    private BigDecimal deliveryFee;
    private Integer transferId;
    private String prescriberName;
    private String prescribeerPhone;
    private String isOrder;
    private String orderId;
    private String paymentType;
    private String deliveryDay;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PatientId")
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Column(name = "PatientName")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "RxNumber")
    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    @Column(name = "PharmacyName")
    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    @Column(name = "PharmacyPhone")
    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

    @Column(name = "Drug")
    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "Video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Column(name = "RequestedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    @Transient
    public TransferDetail getTransferDetail() {
        return transferDetail;
    }

    public void setTransferDetail(TransferDetail transferDetail) {
        this.transferDetail = transferDetail;
    }

//    @Transient
//    public PatientProfile getPatientProfile() {
//        return patientProfile;
//    }
//
//    public void setPatientProfile(PatientProfile patientProfile) {
//        this.patientProfile = patientProfile;
//    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientDeliveryAddressId", insertable = true, updatable = true)
    public PatientDeliveryAddress getPatientDeliveryAddress() {
        return patientDeliveryAddress;
    }

    public void setPatientDeliveryAddress(PatientDeliveryAddress patientDeliveryAddress) {
        this.patientDeliveryAddress = patientDeliveryAddress;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "DeliveryPreferencesId", insertable = true, updatable = true)
    @Transient
    public DeliveryPreferences getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(DeliveryPreferences deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }

    @Column(name = "Zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "Miles")
    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    @Column(name = "DeliveryFee")
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Transient
    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    @Column(name = "DrugImg")
    public String getDrugImg() {
        return drugImg;
    }

    public void setDrugImg(String drugImg) {
        this.drugImg = drugImg;
    }

    @Column(name = "PrescriberName")
    public String getPrescriberName() {
        return prescriberName;
    }

    public void setPrescriberName(String prescriberName) {
        this.prescriberName = prescriberName;
    }

    @Column(name = "PrescriberPhone")
    public String getPrescribeerPhone() {
        return prescribeerPhone;
    }

    public void setPrescribeerPhone(String prescribeerPhone) {
        this.prescribeerPhone = prescribeerPhone;
    }

    @Column(name = "IsOrder")
    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    @Transient
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Transient
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Transient
    public String getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(String deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    @Transient
    public Object getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(Object patientProfile) {
        this.patientProfile = patientProfile;
    }

}
