package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "TransferDetail")
public class TransferDetail extends AuditFields implements Serializable {

    private Integer id;
    private Date olversion;
    private Integer requestId;
    private String firstName;
    private String lastName;
    private String rxNumber;
    private String drugName;
    private String strength;
    private String drugType;
    private Integer quantity;
    private Integer daysSupply;
    private Integer refillAllowed;
    private Integer refillUsed;
    private Date lastFillDate;
    private Date expiryDate;
    private String remarks;
    private String status;
    private String npi;
    private String pharmacyStatus;
    private Date pharmacyStatusCreatedOn;
    private Integer defaultQty;
    private String ndc;
    private String pharmacyName;
    private String pharmacyPhone;
    private String tempDrugName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
//    @Version
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "OlVersion")
    @Transient
    public Date getOlversion() {
        return olversion;
    }

    public void setOlversion(Date olversion) {
        this.olversion = olversion;
    }

    @Column(name = "RequestId")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @NotBlank(message = "Required")
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "Required")
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank(message = "Required")
    @Column(name = "RxNumber")
    public String getRxNumber() {
        return rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    @NotBlank(message = "Required")
    @Column(name = "DrugName")
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @NotBlank(message = "Required")
    @Column(name = "Strength")
    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @NotBlank(message = "Required")
    @Column(name = "Type")
    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    @NotNull(message = "Required")
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @NotNull(message = "Required")
    @Column(name = "DaysSupply")
    public Integer getDaysSupply() {
        return daysSupply;
    }

    public void setDaysSupply(Integer daysSupply) {
        this.daysSupply = daysSupply;
    }

    @NotNull(message = "Required")
    @Column(name = "RefillAllowed")
    public Integer getRefillAllowed() {
        return refillAllowed;
    }

    public void setRefillAllowed(Integer refillAllowed) {
        this.refillAllowed = refillAllowed;
    }

    @NotNull(message = "Required")
    @Column(name = "RefillUsed")
    public Integer getRefillUsed() {
        return refillUsed;
    }

    public void setRefillUsed(Integer refillUsed) {
        this.refillUsed = refillUsed;
    }

    @NotNull(message = "Required")
    @Column(name = "LastFillDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getLastFillDate() {
        return lastFillDate;
    }

    public void setLastFillDate(Date lastFillDate) {
        this.lastFillDate = lastFillDate;
    }

    @NotNull(message = "Required")
    @Column(name = "ExpiryDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "Remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotBlank(message = "Required")
    @Column(name = "PrescriberNpi")
    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    @Column(name = "PharmacyStatus")
    public String getPharmacyStatus() {
        return pharmacyStatus;
    }

    public void setPharmacyStatus(String pharmacyStatus) {
        this.pharmacyStatus = pharmacyStatus;
    }

    @Column(name = "CreatedPharmacyStatus")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getPharmacyStatusCreatedOn() {
        return pharmacyStatusCreatedOn;
    }

    public void setPharmacyStatusCreatedOn(Date pharmacyStatusCreatedOn) {
        this.pharmacyStatusCreatedOn = pharmacyStatusCreatedOn;
    }

    /**
     * @return the pharmacyName
     */
    @NotBlank(message = "Required")
    @Column(name = "PharmacyName")
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
    @NotBlank(message = "Required")
    @Column(name = "PharmacyPhone")
    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    /**
     * @param pharmacyPhone the pharmacyPhone to set
     */
    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

    @Transient
    public Integer getDefaultQty() {
        return defaultQty;
    }

    public void setDefaultQty(Integer defaultQty) {
        this.defaultQty = defaultQty;
    }

    @Column(name = "Ndc")
    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    @Transient
    public String getTempDrugName() {
        return tempDrugName;
    }

    public void setTempDrugName(String tempDrugName) {
        this.tempDrugName = tempDrugName;
    }

}
