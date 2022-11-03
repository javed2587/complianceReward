package com.ssa.cms.dto;

/**
 *
 * @author mzubair
 */
public class PatientProfileDTO {

    private Integer patientProfileSeqNo;
    private Integer id;
    private Integer profileId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Integer verificationCode;
    private String securityToken;
    private Integer points;
    private Long availableRewardPoints;
    private Long availedRewardPoints;
    private Long lifetimeRewardPoints;
    private String emailAddress;

    private Object user;
    private Object saveSearches;
    private Object insurancecards;
    private Object insuranceCardData;
    private Object dependents;
    private Object creditcards;
    private Object address;
    private Object totalSavings;
    private String registrationDate;
    private String allergies;
    private String gender;
    private String patientName;
    private String patientDOB;
    private String hasRxCard;
    private String zip;
    private String hasPaymentCard;
    private String deviceToken;
    private Long insuranceCardCount;
    private Long allergisCount;
    private Object patientActivityCount;
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getSaveSearches() {
        return saveSearches;
    }

    public void setSaveSearches(Object saveSearches) {
        this.saveSearches = saveSearches;
    }

    public Object getInsurancecards() {
        return insurancecards;
    }

    public void setInsurancecards(Object insurancecards) {
        this.insurancecards = insurancecards;
    }

    public Object getInsuranceCardData() {
        return insuranceCardData;
    }

    public void setInsuranceCardData(Object insuranceCardData) {
        this.insuranceCardData = insuranceCardData;
    }

    public Object getDependents() {
        return dependents;
    }

    public void setDependents(Object dependents) {
        this.dependents = dependents;
    }

    public Object getCreditcards() {
        return creditcards;
    }

    public void setCreditcards(Object creditcards) {
        this.creditcards = creditcards;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Long getAvailableRewardPoints() {
        return availableRewardPoints;
    }

    public void setAvailableRewardPoints(Long availableRewardPoints) {
        this.availableRewardPoints = availableRewardPoints;
    }

    public Long getAvailedRewardPoints() {
        return availedRewardPoints;
    }

    public void setAvailedRewardPoints(Long availedRewardPoints) {
        this.availedRewardPoints = availedRewardPoints;
    }

    public Long getLifetimeRewardPoints() {
        return lifetimeRewardPoints;
    }

    public void setLifetimeRewardPoints(Long lifetimeRewardPoints) {
        this.lifetimeRewardPoints = lifetimeRewardPoints;
    }

    public Object getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(Object totalSavings) {
        this.totalSavings = totalSavings;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getHasRxCard() {
        return hasRxCard;
    }

    public void setHasRxCard(String hasRxCard) {
        this.hasRxCard = hasRxCard;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getHasPaymentCard() {
        return hasPaymentCard;
    }

    public void setHasPaymentCard(String hasPaymentCard) {
        this.hasPaymentCard = hasPaymentCard;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getPatientProfileSeqNo() {
        return patientProfileSeqNo;
    }

    public void setPatientProfileSeqNo(Integer patientProfileSeqNo) {
        this.patientProfileSeqNo = patientProfileSeqNo;
    }
     public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
    public Long getInsuranceCardCount() {
        return insuranceCardCount;
    }

    public void setInsuranceCardCount(Long insuranceCardCount) {
        this.insuranceCardCount = insuranceCardCount;
    }
    public Long getAllergisCount() {
        return allergisCount;
    }

    public void setAllergisCount(Long allergisCount) {
        this.allergisCount = allergisCount;
    }
       public Object getPatientActivityCount() {
        return patientActivityCount;
    }

    public void setPatientActivityCount(Object patientActivityCount) {
        this.patientActivityCount = patientActivityCount;
    }
}
