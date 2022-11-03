package com.ssa.cms.delegate;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import java.util.List;
import com.ssa.cms.dao.PermissionDAO;
import com.ssa.cms.dto.ValueDTO;
import com.ssa.cms.model.AppResource;
import com.ssa.cms.model.AppResourceObjectPermissions;
import com.ssa.cms.model.AppResourceObjectPermissionsId;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.RoleTypes;
import com.ssa.cms.model.UserBrand;
import java.util.ArrayList;
import com.ssa.cms.model.Users;
import com.ssa.cms.thread.UserLoginEmailThread;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EmailSenderUtil;
import com.ssa.cms.util.RandomString;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;
    AppResourceObjectPermissions appResourcePermission;
    private final Log logger = LogFactory.getLog(getClass());

    public Users getUserByName(String userName) {
        return permissionDAO.getUserByName(userName);
    }

    public void userLastLogin(Integer userId) {
        try {
            permissionDAO.userLastLogin(userId);
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> userLastLogin", e);
        }
    }

    public boolean isUserExists(String userName, String emailAddress, Integer userId) {
        boolean exists = false;
        try {
            exists = permissionDAO.isUserExists(userName, emailAddress, userId);
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> isUserExists", e);
        }
        return exists;
    }

    
      public boolean isPharmacyUserExists(String userNameOrEmail, boolean isEmail) {
        boolean exists = false;
        try {
            exists = permissionDAO.isPharmacyUserExists(userNameOrEmail, isEmail);
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> isUserExists", e);
        }
        return exists;
    }
    public List<Users> getUserList(int userId) {
        List<Users> usersList = new ArrayList<>();
        try {
            usersList = permissionDAO.getUsersList();
           // List<PharmacyUser> pharmacyUsers = permissionDAO.getPharmacyUsersList();
            //populateUsersFromPharmacyUsers(usersList, pharmacyUsers);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PermisssionService -> getUserList", e);
        }
        return usersList;
    }

    private List<Users> populateUsersFromPharmacyUsers(List<Users> users, List<PharmacyUser> pharmacyUsers) {
        if (users == null) {
            users = new ArrayList<>();
        }
        for (PharmacyUser p : pharmacyUsers) {
            users.add(populateUserFromPharmacyUser(p));
        }
        return users;
    }

    public List<Campaigns> getCampaignsByBrandId(List<Integer> listbrandIds, int userId) {
        List<Campaigns> list = new ArrayList<>();
        try {
            List<UserBrand> userBrands = permissionDAO.getCampaignsByBrandId(listbrandIds, userId);

            for (UserBrand userBrand : userBrands) {
                if (userBrand.getCampaignIds() != null) {
                    for (String campaignId : userBrand.getCampaignIds().split(",")) {
                        Campaigns campaign = permissionDAO.getCampaignById(Integer.parseInt(campaignId));
                        if (campaign != null) {
                            list.add(campaign);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            logger.error("NumberFormatException: PermisssionService -> getCampaignsByBrandId", e);
        }
        return list;
    }

    public List<RoleTypes> getUserRoles() {
        List<RoleTypes> roleList = new ArrayList<>();
        try {
            roleList = permissionDAO.fetchAllRolesTypes();
        } catch (Exception e) {
            logger.error("NumberFormatException: PermisssionService -> getUserRoles", e);
        }
        return roleList;
    }

    public List<RoleTypes> getUserActiveRoles(SessionBean sessionBean) {
        List<RoleTypes> roleList = new ArrayList<>();
        try {
            roleList = permissionDAO.getActiveRolesByUserId(sessionBean.getUserId());
        } catch (NumberFormatException e) {
            logger.error("NumberFormatException: PermisssionService -> getUserRoles", e);
        }
        return roleList;
    }

    public List<RoleTypes> getUserRolesById(SessionBean sessionBean) {
        List<RoleTypes> roleList = new ArrayList<>();
        try {
            roleList = permissionDAO.getRoleByUserId(sessionBean.getUserId());

        } catch (NumberFormatException e) {
            logger.error("NumberFormatException: PermisssionService -> getUserRolesById", e);
        }
        return roleList;
    }

    public boolean roleExists(String roleTitle, Integer roleId, int userId) {
        boolean exists = false;
        try {
            List<RoleTypes> roleList = permissionDAO.isRoleExists(roleTitle, roleId, userId);
            if (roleList != null && roleList.size() > 0) {
                exists = true;
            }
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> roleExists", e);
        }
        return exists;
    }

    public List<UserBrand> getUserBrandByUserId(int userId) {
        List<UserBrand> userBrands = new ArrayList<>();
        try {
            userBrands = permissionDAO.getUserBrandByUserId(userId);
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getUserBrandByUserId", e);
        }

        return userBrands;
    }

    public RoleTypes getRoleById(Integer id) {
        RoleTypes roleType = new RoleTypes();
        try {
            //Object o = permissionDAO.findRecordById(RoleTypes.class, id);
            Object o=permissionDAO.findByPropertyUnique(new RoleTypes(),"roleId",id,Constants.HIBERNATE_EQ_OPERATOR,"", 0);
            if (o != null) {
                roleType = (RoleTypes) o;
            }
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> getRoleById", e);
        }
        return roleType;
    }

    public boolean saveRole(RoleTypes roleType, Integer userId) {
        boolean saved = false;
        try {
            roleType.setUserId(userId);
            if (roleType.getRoleId() == null || roleType.getRoleId() == 0) {
                roleType.setCreatedBy(userId);
                roleType.setCreatedOn(new Date());
            } else {
                roleType.setUpdatedBy(userId);
                roleType.setUpdatedOn(new Date());
                roleType.setUserId(userId);
            }
            permissionDAO.saveOrUpdate(roleType);
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> saveRole", e);
        }
        return saved;
    }

    public boolean deleteRole(Integer roleId) {
        boolean deleted = false;
        try {
            RoleTypes roleTypes = new RoleTypes();
            roleTypes.setRoleId(roleId);
            List<Integer> list = permissionDAO.getPermissions(roleId);
            List<Users> users = permissionDAO.getUsersByRoleId(roleId);
            if ((list != null && list.size() > 0) || (users != null && users.size() > 0)) {
                deleted = false;
            } else {
                //permissionDAO.delete(roleTypes);
                permissionDAO.delete("RoleTypes","RoleId",roleId);
                deleted = true;
            }

        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> deleteRole", e);
        }
        return deleted;
    }

    public Users getUserById(Integer id) {
        Users user = new Users();
        try {
            Object o = permissionDAO.getUserById(id);
            user = (Users) o;
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> getUserById", e);
        }
        return user;
    }

    public boolean deleteUser(Integer userId) {
        boolean deleted = false;
        try {
            Users o = (Users)permissionDAO.findRecordById(new Users(), userId);
            Hibernate.initialize(o.getRoleTypes());
            o.setIsActive("No");
            permissionDAO.update(o);
            deleted = true;
        } catch (Exception e) {
            logger.error("Exception: PermisssionService -> deleteUser", e);
        }
        return deleted;
    }
    
    public boolean deletePharmacyUser(Integer userId) {
        boolean deleted = false;
        try {
            PharmacyUser o = (PharmacyUser) permissionDAO.findRecordById(new PharmacyUser(), userId);
            Hibernate.initialize(o.getRoleTypes());
            o.setActive(Boolean.FALSE);
            permissionDAO.update(o);
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: PermisssionService -> deletePharmacyUser", e);
        }
        return deleted;
    }

    @Transactional(readOnly = false)
    public boolean saveUser(Users user, SessionBean sessionBean) throws Exception {

        try {
            if (user.getCellNo() != null && !"".equals(user.getCellNo())) {
                user.setCellNo(user.getCellNo().replaceAll("[\\s\\-()]", ""));
            }
            if (user.getTelNo() != null && !"".equals(user.getTelNo())) {
                user.setTelNo(user.getTelNo().replaceAll("[\\s\\-()]", ""));
            }
            if (user.getIsActive() != null && user.getIsActive().contains("Y")) {
                user.setIsActive("Yes");
            } else {
                user.setIsActive("No");
            }
            String userPassword = RandomString.generatePassword();
            if (user.getisAdmin()) {
                if (user.getUserId() == null || user.getUserId() == 0) {
                    user.setUserPassword(this.md5(userPassword));
                    user.setPasswordSource("E");
                    user.setCreatedBy(sessionBean.getUserId());
                    user.setCreatedOn(new Date());
                    user.setParentId(sessionBean.getUserId());

                    //sending mail
                    //this.sendLoginInformation(user, userPassword);
                    UserLoginEmailThread eThread=new UserLoginEmailThread(user, userPassword, "", false);
                    Thread thread=new Thread(eThread);
                    thread.start();
                } else {
                    user.setUpdatedBy(sessionBean.getUserId());
                    user.setUpdatedOn(new Date());
                }
                permissionDAO.persist(user);
            } else {
                createPharmacyUser(user, userPassword, sessionBean.getUserId());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("Exception: PermissionService -> saveUser", e);
            return false;
        } catch (Exception ee) {
            ee.printStackTrace();
            logger.error("Exception: PermissionService -> saveUser", ee);
            return false;
        }
        return true;
    }

    public void createPharmacyUser(Users user, String userPassword, int createdUserId) throws Exception {
        PharmacyUser pu = new PharmacyUser();
        populatePharmacyUser(pu, user, userPassword);
        pu.setCreatedBy(new BigInteger("" + createdUserId));
//        if (pu.getId() == null) {
            userInfoSendByEmail(pu);
//        }
        permissionDAO.persist(pu);
    }

    public void userInfoSendByEmail(PharmacyUser pu) throws Exception {
        // Send username email
        CMSEmailContent emailContent = permissionDAO.getCMSEmailContent(Constants.ACCOUNT_CREATED_USERNAME);
        String emailBody = CommonUtil.relpacePlaceHolderForCredentials(
                emailContent.getEmailBody(), pu.getEmail(), pu.getPassword(), null, null);
        //EmailSender.send(pu.getEmail(), emailContent.getSubject(), emailBody);
        UserLoginEmailThread eThread=new UserLoginEmailThread(pu.getEmail(), emailContent.getSubject(), emailBody,"", true);
        Thread thread=new Thread(eThread);
        thread.start();
    }

    public void populatePharmacyUser(PharmacyUser pu, Users user, String userPassword) {
        pu.setId(user.getUserId());
        pu.setFirstName(user.getFirstName());
        pu.setLastName(user.getLastName());
        pu.setUserName(user.getUserName().toLowerCase());
        pu.setEmail(user.getEmailAddress().toLowerCase());
//        if (user.getUserId() == null) {
            pu.setPassword(userPassword);
//        }
        pu.setActive(Boolean.TRUE);
        pu.setPhone(user.getTelNo());
        pu.setCellNo(user.getCellNo());
        pu.setStatus("Active");
        pu.setCreatedOn(new Date());
        pu.setLastUpdatedOn(new Date());
        pu.setRole("User");//will be depricated soon! TODO
        pu.setRoleTypes(user.getRoleTypes());
        pu.setUserStartDate(user.getUserStartDate());
        Pharmacy p = new Pharmacy();
        p.setId(user.getPharmacyId());
        pu.setPharmacy(p);
    }

    private Users populateUserFromPharmacyUser(PharmacyUser p) {
        Users u = new Users();
        u.setUserId(p.getId());
        u.setFirstName(p.getFirstName());
        u.setLastName(p.getLastName());
        u.setisAdmin(Boolean.FALSE);
        u.setIsActive(p.getActive() ? "Yes" : "No");
        u.setEmailAddress(p.getEmail());
        u.setRoleTypes(p.getRoleTypes());
        u.setTelNo(p.getPhone());
        u.setCellNo(p.getCellNo());
        u.setUserName(p.getUserName());
        u.setisAdmin(Boolean.FALSE);
        u.setUserStartDate(p.getUserStartDate());
        u.setPharmacyId(p.getPharmacy().getId());
        return u;
    }

    private void sendLoginInformation(Users users, String password) throws Exception {
        String emailBody = "<table width=\"75%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "<tbody>\n"
                + "<tr valign=\"top\">\n"
                + "<td width=\"100%\" style=\"padding-top:10px;color: #000;  font-family:Verdana, Geneva, sans-serif;font-size:12px;\">"
                + "<b>Dear User,</b><br>\n"
                + "<br>Welcome to the API Rx Savings & Support Program!.\n"
                + "<br>\n"
                + "<br>Your account has been created and will be effective on " + DateUtil.dateToString(users.getCreatedOn(), "EEE, MMM dd, yyyy 'at' hh:mm") + " EDT. <br>\n"
                + "<br>Your account details are below.<br>"
                + "<br>&nbsp; &nbsp;Username:&nbsp;<b>" + users.getUserName() + "</b><br>"
                + "&nbsp; &nbsp;Password:&nbsp;<b>" + password + "</b><br>\n"
                + "<br>\n"
                + "<b>Thank you,</b><br>\n"
                + "<br>\n"
                + "The API Direct Team<br>\n"
                + "<br><i>This email was created automatically. Please do not reply to this message.</i>\n"
                + "\n</td></tr></tbody></table>";
        EmailSenderUtil.send(users.getEmailAddress(), "API Direct Savings & Support Program | User Account Created", emailBody);

    }

    private void sendPasswordInformation(Users users, String password) {
        String emailBody = "<table width=\"75%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "<tbody>\n"
                + "<tr valign=\"top\">\n"
                + "<td width=\"100%\" style=\"padding-top:10px;color: #000;  font-family:Verdana, Geneva, sans-serif;font-size:12px;\">"
                + "<b>Dear User,</b><br>\n"
                + "<br>\n"
                + "Your account password for API Rx Savings & Support Program has been changed.<br>\n"
                + "<br>\n"
                + "Your new password is:  "
                + "<b>" + password + "</b><br>\n"
                + "<br>\n"
                + "<b>Thank you,</b><br>\n"
                + "<br>\n"
                + "The API Direct Team<br>\n"
                + "<br><i>This email was created automatically. Please do not reply to this message.</i>\n"
                + "\n</td></tr></tbody></table>";
        EmailSenderUtil.send(users.getEmailAddress(), "API Direct Savings & Support Program | Password Updated", emailBody);

    }

    private String md5(String password) throws NoSuchAlgorithmException {
        String result = password;
        if (password != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
            md.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { //40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }

    public boolean changeUserPassword(Users users) {
        boolean updated = false;
        try {
            String password = users.getUserPassword();
            Integer userId = users.getUserId();
            if(users.getisAdmin()){ 
            users.setUserPassword(this.md5(password));
            users.setUpdatedBy(users.getUpdatedBy());
            users.setUpdatedOn(new Date());
            updated = permissionDAO.changePassword(userId, users.getUserPassword());
            }else {
                updated = permissionDAO.changePharmacyUserPassword(userId, password);
            }
            this.sendPasswordInformation(users, password);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e, e);
        }
        return updated;
    }

    public List<AppResource> getAppResources() {
        List<AppResource> resources = new ArrayList<>();
        try {
            resources = permissionDAO.getAppResources();
        } catch (Exception exception) {
            logger.error("Exception: PermissionService -> getAppResources", exception);
        }
        return resources;
    }

    public boolean saveAppResourcePermission(List<AppResource> appResources, int roleId, Integer currentUserId) {

        boolean saved = false;
        try {

            for (AppResource appResource : appResources) {
                AppResourceObjectPermissions permission = new AppResourceObjectPermissions();
                AppResourceObjectPermissionsId appResourceObjectPermissionsId = new AppResourceObjectPermissionsId();
                appResourceObjectPermissionsId.setResourceId(appResource.getResourceId());
                appResourceObjectPermissionsId.setRoleId(roleId);
                permission.setId(appResourceObjectPermissionsId);
                permission.setAllowManage(appResource.getManageAllow());
                permission.setAllowView(appResource.getViewAllow());
                permission.setCreatedBy(currentUserId);
                permission.setCreatedOn(new Date());
                permission.setUpdatedBy(currentUserId);
                permission.setUpdatedOn(new Date());
                permissionDAO.persist(permission);
            }
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getAppResourcesByUserId", e);
        }
        return saved;
    }

    public List<AppResourceObjectPermissions> getAppResourcePermissionByRoleId(Integer roleId) {
        List<AppResourceObjectPermissions> appResourceList = permissionDAO.getAppResourcePermissionByRoleId(roleId);
        return appResourceList;
    }

    public List<AppResourceObjectPermissions> getAppResourcesByUserId(int userId) {
        List<AppResourceObjectPermissions> resources = new ArrayList<>();
        try {
            resources = permissionDAO.getAppResourcesByUserId(userId);
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getAppResourcesByUserId", e);
        }
        return resources;
    }

    public AppResourceObjectPermissions getAppResourcePermission() {
        return appResourcePermission;
    }

    public void setAppResourcePermission(AppResourceObjectPermissions appResourcePermission) {
        this.appResourcePermission = appResourcePermission;
    }

    public Pharmacy getPharmacyByEmail(String email, String password) {
        Pharmacy pharmacy = new Pharmacy();
        try {
            pharmacy = permissionDAO.getPharmacy(email, password);
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getPharmacyByEmail", e);
        }
        return pharmacy;
    }

//    public String getDailyRedemption() {
//        String transactionNo = null;
//        try {
//            List<DailyRedemption> list = permissionDAO.getDailyRedemptionsList();
//            if (list.size() > 0) {
//                for (DailyRedemption dr : list) {
//                    transactionNo = dr.getId().getTransactionNumber();
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Exception: PermissionService -> getDailyRedemption", e);
//        }
//        return transactionNo;
//    }

    public List<RoleTypes> getRoleTypeList() {
        List<RoleTypes> list = new ArrayList<>();
        try {
            list = permissionDAO.getAllRoleTypes();
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getRoleTypeList", e);
        }
        return list;
    }

    public List<Pharmacy> getPharmaciesList() {
        List<Pharmacy> list = new ArrayList<>();
        try {
            list = permissionDAO.findByProperty(new Pharmacy(), "status", "Active", Constants.HIBERNATE_EQ_OPERATOR, "title", Constants.HIBERNATE_ASC_ORDER);
        } catch (Exception e) {
            logger.error("Exception: PermissionService:: getPharmaciesList", e);
        }
        return list;
    }
    
    public List<ValueDTO> getPharmaciesDTOList() 
    {
        List<ValueDTO> lstValueDTO=new ArrayList();
        try 
        {
            List<Pharmacy> lstPharmacies = permissionDAO.findByProperty(new Pharmacy(), "status", "Active", Constants.HIBERNATE_EQ_OPERATOR, "title", Constants.HIBERNATE_ASC_ORDER);
            for(Pharmacy pharmacy :lstPharmacies )
            {
                ValueDTO value=new ValueDTO();
                value.setName(pharmacy.getTitle());
                value.setValue(pharmacy.getId()+"");
                lstValueDTO.add(value);
            }
        } 
        catch (Exception e) 
        {
            logger.error("Exception: PermissionService:: getPharmaciesList", e);
        }
        return lstValueDTO;
    }

    public PharmacyUser getPharmacyUserByUserId(Integer userId) {
        PharmacyUser pharmacyUser = new PharmacyUser();
        try {
            pharmacyUser = (PharmacyUser) permissionDAO.findByPropertyUnique(new PharmacyUser(), "users.userId", userId, Constants.HIBERNATE_EQ_OPERATOR, "", 0);
        } catch (Exception e) {
            logger.error("Exception: PermissionService:: getPharmacyUserByUserId", e);
        }
        return pharmacyUser;
    }

    public Users getPharmacyUserByPharmacyUserID(int id) {

        return populateUserFromPharmacyUser((PharmacyUser) permissionDAO.findByPropertyUnique(new PharmacyUser(), "id", id, Constants.HIBERNATE_EQ_OPERATOR, "", 0));
    }
    
    public List<AppResourceObjectPermissions> getAppResourcesByPharmacyUserId(int pharmacyUserId) {
        List<AppResourceObjectPermissions> resources = new ArrayList<>();
        try {
            resources = permissionDAO.getAppResourcesByPharmacyUserId(pharmacyUserId);
        } catch (Exception e) {
            logger.error("Exception: PermissionService -> getAppResourcesByUserId", e);
        }
        return resources;
    }
}
