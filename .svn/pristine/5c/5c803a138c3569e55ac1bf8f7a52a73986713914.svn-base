package com.ssa.cms.bean;

import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.util.PermissionUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class SessionBean implements Serializable {

    private String userName;
    private String userNameDB;
    private int userId;
    private String userPassword;
    private Integer parentId;
    private Date currentDate;
    private String datePattern;
    private Map<Integer, PermissionUtil> pmap;
    private Pharmacy pharmacy;
    private String transactionNumber;
    private String role;
    private String currentUserName;
    private String lastLoggedOn;
    private int roleId;
    private String selectedTab;
    private String pharmacyAbbr;
    private Boolean isAdmin;
    private Map<String, String> rxNumberMap;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the datePattern
     */
    public String getDatePattern() {
        return datePattern;
    }

    /**
     * @param datePattern the datePattern to set
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    /**
     *
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return Map<Integer, PermissionUtil>
     */
    public Map<Integer, PermissionUtil> getPmap() {
        return pmap;
    }

    /**
     *
     * @param pmap of type (setPmap(Map<Integer, PermissionUtil>)
     */
    public void setPmap(Map<Integer, PermissionUtil> pmap) {
        this.pmap = pmap;
    }

    /**
     *
     * @param id
     * @return boolean
     */
    public boolean getViewPermission(Integer id) {
        //super user has every rights
        if (this.getUserNameDB().equalsIgnoreCase("admin")) {
            return true;
        }
        if (!this.pmap.containsKey(id)) {
            return false;
        }
        PermissionUtil pu = this.pmap.get(id);

        return pu.isView();
    }

    /**
     *
     * @param id
     * @return boolean
     */
    public boolean getManagePermission(Integer id) {
        //super user has every rights
        if (this.getUserNameDB().equalsIgnoreCase("admin")) {
            return true;
        }
        if (!this.pmap.containsKey(id)) {
            return false;
        }
        PermissionUtil pu = this.pmap.get(id);

        return pu.isManage();
    }

    /**
     *
     * @return userNameDB
     */
    public String getUserNameDB() {
        return userNameDB;
    }

    /**
     *
     * @param userNameDB
     */
    public void setUserNameDB(String userNameDB) {
        this.userNameDB = userNameDB;
    }

    /**
     *
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     *
     * @return pharmacy
     */
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    /**
     *
     * @param pharmacy
     */
    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    /**
     *
     * @return transactionNumber
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     *
     * @param transactionNumber
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    /**
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return currentUserName
     */
    public String getCurrentUserName() {
        return currentUserName;
    }

    /**
     *
     * @param currentUserName
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    /**
     *
     * @return lastLoggedOn
     */
    public String getLastLoggedOn() {
        return lastLoggedOn;
    }

    /**
     *
     * @param lastLoggedOn
     */
    public void setLastLoggedOn(String lastLoggedOn) {
        this.lastLoggedOn = lastLoggedOn;
    }

    /**
     *
     * @return roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getPharmacyAbbr() {
        return pharmacyAbbr;
    }

    public void setPharmacyAbbr(String pharmacyAbbr) {
        this.pharmacyAbbr = pharmacyAbbr;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Map<String, String> getRxNumberMap() {
        return rxNumberMap;
    }

    public void setRxNumberMap(Map<String, String> rxNumberMap) {
        this.rxNumberMap = rxNumberMap;
    }

}
