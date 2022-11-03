package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "PatientDeliveryAddress")
public class PatientDeliveryAddress implements Serializable {

    private Integer id;
    private PatientProfile patientProfile;
    private DeliveryPreferences deliveryPreferences;
    private String address;
    private String apartment;
    private String city;
    private State state;
    private String zip;
    private String description;
    private String addressType;
    private String defaultAddress;
    private Date createdOn;
    private Date updatedOn;
    private List<TransferRequest> transferRequestList;
    private String commenceDate;
    private String ceaseDate;
    private Date createdAt;
    private Date updatedAt;

   

    public PatientDeliveryAddress() {
    }

    public PatientDeliveryAddress(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", insertable = true, updatable = true)
    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "Apartment")
    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StateId", insertable = true, updatable = true)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "Zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "AddressDescription")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "AddressType")
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn")
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "DefaultAddress")
    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    //TODO @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patientDeliveryAddress")
    @Transient
    public List<TransferRequest> getTransferRequestList() {
        return transferRequestList;
    }

    public void setTransferRequestList(List<TransferRequest> transferRequestList) {
        this.transferRequestList = transferRequestList;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "DeliveryPreferenceId", insertable = true, updatable = true)
    @Transient
    public DeliveryPreferences getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(DeliveryPreferences deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }

    @Column(name = "CommenceDate")
    public String getCommenceDate() {
        return commenceDate;
    }

    public void setCommenceDate(String commenceDate) {
        this.commenceDate = commenceDate;
    }

    @Column(name = "CeaseDate")
    public String getCeaseDate() {
        return ceaseDate;
    }

    public void setCeaseDate(String ceaseDate) {
        this.ceaseDate = ceaseDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
