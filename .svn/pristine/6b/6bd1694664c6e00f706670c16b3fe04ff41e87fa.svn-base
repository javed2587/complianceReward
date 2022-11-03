package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "Pharmacy")
public class Pharmacy implements Serializable {

    private Integer id;
    private String title;
    private Long npi;
    private String phone;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private Long zip;
    private String accountNo;
    private String userName;
    private String password;
    private String status;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastUpdatedBy;
    private Date lastUpdatedOn;
    private State state;
//    private List<Order> orderList;
    private String territory;
    private String contactPerson;
    private String personEmail;
    private String personMobileNo;
    private String personOfficePhoneNo;
    private PharmacyLookup pharmacyLookup;
    private List<PharmacyUser> pharmacyUserList;
    private List<PharmacyFacilityOperation> pharmacyFacilityOperationList;
    private String newPassword;
    private String repeatNewPassword;
    private String acceptedTerms;
    private String acceptedPolicy;
    private Boolean facilityOperationSelected;
    private Boolean addSecondaryContacts = false;
    private Boolean addThirdContacts = false;
    private String salesRep;

    private Integer fillCount;
    private Integer shipCount;
    private Integer deninedCount;
    private BigDecimal awp;
    private Double apiToNonApiRatio;
    private String fax;
    private String abbr;
    
    public Pharmacy() {
    }

    public Pharmacy(Integer id) {
        this.id = id;
    }

    /**
     * @return the fax
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "NPI", nullable = false)
    public Long getNpi() {
        return npi;
    }

    public void setNpi(Long npi) {
        this.npi = npi;
    }

    @Column(name = "Phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "Email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Address_1", nullable = false)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name = "Address_2", nullable = false)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "City", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "Zip", nullable = false)
    public Long getZip() {
        return zip;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    @Column(name = "UserName", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CreatedBy", nullable = false)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn", nullable = false)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "LastUpdatedBy", nullable = false)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastUpdatedOn", nullable = false)
    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StateId", nullable = false)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacy")
//    @OrderBy(clause = "id asc")
//    public List<Order> getOrderList() {
//        return orderList;
//    }
//
//    public void setOrderList(List<Order> orderList) {
//        this.orderList = orderList;
//    }

    @Column(name = "AccountNo", nullable = false)
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Column(name = "Territory", nullable = false)
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Column(name = "Contact_Person", nullable = false)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "Person_Email", nullable = false)
    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    @Column(name = "Person_MobileNo", nullable = false)
    public String getPersonMobileNo() {
        return personMobileNo;
    }

    public void setPersonMobileNo(String personMobileNo) {
        this.personMobileNo = personMobileNo;
    }

    @Column(name = "Person_Office_PhoneNO", nullable = false)
    public String getPersonOfficePhoneNo() {
        return personOfficePhoneNo;
    }

    public void setPersonOfficePhoneNo(String personOfficePhoneNo) {
        this.personOfficePhoneNo = personOfficePhoneNo;
    }

    //TODO @ManyToOne(fetch = FetchType.LAZY)
    //TODO @JoinColumn(name = "PharmacyLookupId")
    @Transient
    public PharmacyLookup getPharmacyLookup() {
        return pharmacyLookup;
    }

    public void setPharmacyLookup(PharmacyLookup pharmacyLookup) {
        this.pharmacyLookup = pharmacyLookup;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacy")
    @OrderBy(clause = "id asc")
    public List<PharmacyUser> getPharmacyUserList() {
        return pharmacyUserList;
    }

    public void setPharmacyUserList(List<PharmacyUser> pharmacyUserList) {
        this.pharmacyUserList = pharmacyUserList;
    }

    //TODO @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacy")
    //TODO @OrderBy(clause = "id asc")
    @Transient
    public List<PharmacyFacilityOperation> getPharmacyFacilityOperationList() {
        return pharmacyFacilityOperationList;
    }

    public void setPharmacyFacilityOperationList(List<PharmacyFacilityOperation> pharmacyFacilityOperationList) {
        this.pharmacyFacilityOperationList = pharmacyFacilityOperationList;
    }

    @Column(name = "SalesRep")
    public String getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    @Transient
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Transient
    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }

    @Column(name = "AcceptTerms")
    public String getAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(String acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }

    @Column(name = "AcceptPolicy")
    public String getAcceptedPolicy() {
        return acceptedPolicy;
    }

    public void setAcceptedPolicy(String acceptedPolicy) {
        this.acceptedPolicy = acceptedPolicy;
    }

    @Transient
    public Boolean getFacilityOperationSelected() {
        return facilityOperationSelected;
    }

    public void setFacilityOperationSelected(Boolean facilityOperationSelected) {
        this.facilityOperationSelected = facilityOperationSelected;
    }

    @Transient
    public Boolean getAddSecondaryContacts() {
        return addSecondaryContacts;
    }

    public void setAddSecondaryContacts(Boolean addSecondaryContacts) {
        this.addSecondaryContacts = addSecondaryContacts;
    }

    @Transient
    public BigDecimal getAwp() {
        return awp;
    }

    public void setAwp(BigDecimal awp) {
        this.awp = awp;
    }

    @Transient
    public Double getApiToNonApiRatio() {
        return apiToNonApiRatio;
    }

    public void setApiToNonApiRatio(Double apiToNonApiRatio) {
        this.apiToNonApiRatio = apiToNonApiRatio;
    }

    @Transient
    public Integer getFillCount() {
        return fillCount;
    }

    public void setFillCount(Integer fillCount) {
        this.fillCount = fillCount;
    }

    @Transient
    public Integer getShipCount() {
        return shipCount;
    }

    public void setShipCount(Integer shipCount) {
        this.shipCount = shipCount;
    }

    @Transient
    public Integer getDeninedCount() {
        return deninedCount;
    }

    public void setDeninedCount(Integer deninedCount) {
        this.deninedCount = deninedCount;
    }

    @Transient
    public Boolean getAddThirdContacts() {
        return addThirdContacts;
    }

    public void setAddThirdContacts(Boolean addThirdContacts) {
        this.addThirdContacts = addThirdContacts;
    }

    @Column(name = "Fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "Abbr")
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    
    
}
