package com.ssa.cms.dao;

import com.ssa.cms.model.AppResource;
import com.ssa.cms.model.AppResourceObjectPermissions;
import com.ssa.cms.model.AppResourceObjectPermissionsId;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.RoleTypes;
import com.ssa.cms.model.UserBrand;
import com.ssa.cms.model.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PermissionDAO extends BaseDAO implements Serializable {

    private final Log permissionLogger = LogFactory.getLog(getClass());

    public boolean isUserExists(String userName, String email, Integer userId) throws Exception {
        String hql = "select user from Users user where 1 = 1 ";
        if (userName != null && userName.trim().length() > 0) {
            hql = hql + " and user.userName = '" + userName + "' ";
        }
        if (email != null && email.trim().length() > 0) {
            hql = hql + " and user.emailAddress = '" + email + "' ";
        }
        if (userId != null && userId > 0) {
            hql = hql + " and user.userId != '" + userId + "' ";
        }
        List<Users> list = (List<Users>) getCurrentSession().createQuery(hql).list();

        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<RoleTypes> isRoleExists(String roleTitle, Integer roleId, int userId) throws Exception {

        String hql = "select role from RoleTypes role where 1=1 and role.userId = '" + userId + "'";
        if (roleTitle != null && roleTitle.trim().length() > 0) {
            hql = hql + " and role.roleTitle = '" + roleTitle + "' ";
        }

        if (roleId != null && roleId > 0) {
            hql = hql + " and role.roleId != '" + roleId + "' ";
        }

        List<RoleTypes> list = (List<RoleTypes>) getCurrentSession().createQuery(hql).list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Users> getUserList(int parentId) {

        int currentUserParentId = getParentIdbyUserId(parentId);
        String hql;
        if (currentUserParentId == 0) {
            hql = "select user from Users user  where 1=1 ";
        } else {
            hql = "select user from Users user  where 1=1 and user.parentId = '" + parentId + "' ";
        }
        return (List<Users>) this.getCurrentSession().createQuery(hql).list();
    }

    public List<RoleTypes> getAllRolesByUserId(int userId) {
        String hql = "select role from RoleTypes role ,Users users"
                + " where role.userId = users.userId and role.userId in (select usr.userId from Users usr where usr.parentId='" + userId + "')"
                + " order by role.roleId ASC";

        return (List<RoleTypes>) this.getCurrentSession().createQuery(hql).list();
    }

    public List<RoleTypes> getActiveRolesByUserId(int userId) {
        String hql = "select role from RoleTypes role ,Users users"
                + " where role.userId = users.userId and role.isActive='Yes' and role.userId in (select usr.userId from Users usr where usr.parentId='" + userId + "' or usr.userId='" + userId + "')"
                + " order by role.roleTitle ASC";

        return (List<RoleTypes>) this.getCurrentSession().createQuery(hql).list();
    }

    public List<RoleTypes> getRoleByUserId(int userId) {

        String sql = "SELECT role.*"
                + "FROM RoleTypes role,"
                + "  Users users"
                + "  WHERE role.userId = users.userId"
                + "    AND role.isActive = 'Yes'"
                + "    AND role.userId IN(SELECT"
                + "                         usr.userId"
                + "                       FROM Users usr"
                + "                       WHERE usr.parentId = '" + userId + "'"
                + "                            OR usr.userId = '" + userId + "')"
                + "    AND role.roleid IN (SELECT roleid FROM AppResourceObjectPermissions)"
                + "ORDER BY role.roleTitle ASC";

        List<Object[]> queryResult = this.getCurrentSession().createSQLQuery(sql).list();
        List<RoleTypes> list = new ArrayList<RoleTypes>();
        for (Object[] result : queryResult) {
            RoleTypes roleTypes = new RoleTypes();
            roleTypes.setRoleId(Integer.parseInt(result[0].toString()));
            roleTypes.setRoleTitle(result[1].toString());

            list.add(roleTypes);
        }

        return list;
    }

    public List<UserBrand> getUserBrandByUserId(int userId) {
        List<UserBrand> list = null;

        String hql = "select userBrand from UserBrand userBrand where userBrand.userId = :userId";
        try {
            list = (List<UserBrand>) getCurrentSession().createQuery(hql).setParameter("userId", userId).list();
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> getUserByName", e);
        }

        return list;
    }

    public List<UserBrand> getCampaignsByBrandId(List<Integer> brandIds, int userId) {
        List<UserBrand> list = null;

        String hql = "select userBrand from UserBrand userBrand where userBrand.brandId in (:brandIds) and userBrand.userId = :userId";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setParameterList("brandIds", brandIds);
            query.setInteger("userId", userId);
            list = (List<UserBrand>) query.list();
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> getCampaignsByBrandId", e);
        }

        return list;
    }

    public List<Campaigns> getCampaignsByBrandIds(List<Integer> brandIds) {
        List<Campaigns> list = null;

        String hql = "select userBrand from Campaigns userBrand where userBrand.brandId in (:brandIds)";
        try {
            //Query query = currentSession().createQuery(hql);
            //query.setParameterList("brandIds", brandIds);
            // list = (List<Campaigns>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Campaigns getCampaignById(int campaignId) {
        List<Campaigns> list = null;
        Campaigns campaigns = null;
        String hql = "select campaigns from Campaigns campaigns where campaigns.campaignId = :campaignId ";
        try {
            String[] names = {"campaignId"};
            Object[] values = {campaignId};

            list = (List<Campaigns>) getCurrentSession().createQuery(hql).setParameter("campaignId", campaignId).list();

            if (!list.isEmpty()) {
                campaigns = new Campaigns();
                campaigns = list.get(0);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return campaigns;
    }

    public List<UserBrand> fetchAllactiveUserBrands() {
        List<UserBrand> list = null;

        String hql = "select userBrand from UserBrand userBrand";
        try {
            // list = (List<UserBrand>) getCurrentSession().createQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<RoleTypes> fetchAllUsersRoles(Integer userId) {

        List<RoleTypes> list;

        String hql = "select role from RoleTypes role";

        //list = (List<RoleTypes>) this.getCurrentSession().createQuery(hql);
        return new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public List<RoleTypes> searchRoles(String title, String status) {

        List<RoleTypes> list;

        String hql = "select role from RoleTypes role where 1=1 ";
        if (title != null && title.trim().length() > 0) {
            title = title.trim();
            hql = hql + " and role.roleTitle like '%" + title + "%' ";
        }
        if (status != null && status.trim().length() > 0 && !status.equalsIgnoreCase("all")) {
            if (status.equalsIgnoreCase("yes")) {
                hql = hql + " and role.isActive = 'Yes' ";
            } else {
                hql = hql + " and role.isActive = 'No' ";
            }

        }

        //list = (List<RoleTypes>) this.getCurrentSession().createQuery(hql);
        return new ArrayList<>();
    }

    public boolean saveOrUpdateUser(Object object) {
        boolean flag = true;
        this.getCurrentSession().saveOrUpdate(object);
        return flag;
    }

    public List<AppResource> getAppResources() {
        String hql = "select resource from AppResource resource where resource.isActive = true order by resource.resourceTitle ASC";
        return (List<AppResource>) this.getCurrentSession().createQuery(hql).list();
    }

    @SuppressWarnings("unchecked")
    public List<AppResource> searchAppResources(Integer id, String title, String type, String status) {

        List<AppResource> list = new ArrayList<>();

        String hql = "select resource from AppResource resource where 1=1 ";

        if (id != null && id > 0) {
            hql = hql + " and resource.resourceId = '" + id + "' ";

        }

        if (title != null && title.trim().length() > 0) {

            title = title.trim();

            hql = hql + " and resource.resourceTitle like '%" + title + "%' ";
        }
        if (status != null && status.trim().length() > 0 && !status.equalsIgnoreCase("all")) {
            if (status.equalsIgnoreCase("yes")) {
                hql = hql + " and  resource.isActive = '1' ";
            } else {
                hql = hql + " and  resource.isActive = '0' ";
            }

        }
        if (type != null && type.trim().length() > 0) {

            type = type.trim();

            hql = hql + " and  resource.resourceType = '" + type + "' ";
        }

        //list = (List<AppResource>) this.getCurrentSession().createQuery(hql);
        return list;
    }

    public List<AppResource> getAppResourceByTitle(String resourceTitle, Integer resourceId) throws Exception {
        String hql = "select appResource from AppResource appResource where 1=1 ";
        if (resourceTitle != null && resourceTitle.trim().length() > 0) {
            hql += " and appResource.resourceTitle = :resourceTitle ";
        }
        if (resourceId != null && resourceId > 0) {
            hql += " and appResource.resourceId != '" + resourceId + "' ";
        }
        //Query query = currentSession().createQuery(hql);
        // query.setString("resourceTitle", resourceTitle);
        // Object queryResult = query.list();
        //List<AppResource> list = (List<AppResource>) queryResult;
        return new ArrayList();
    }

    public List<AppResource> getAdminPermissions() {
        List<AppResource> list = null;
        String hql = "from AppResource appResource where isActive=1";
        try {
            //Query query = currentSession().createQuery(hql);
            //list = (List<AppResource>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AppResource> getAdminPermissions(List<Integer> resourceIds, Integer parentId) {
        List<AppResource> list = null;
        String hql = "from AppResource appResource where isActive=1 and (appResource.resourceId in (:resourceIds) or (" + parentId + "=0))";
        try {
            //Query query = currentSession().createQuery(hql);
            // query.setParameterList("resourceIds", resourceIds);
            //  list = (List<AppResource>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getPermissions(Integer roleId) throws Exception {
        return (List<Integer>) getCurrentSession().createQuery("select app.id.resourceId from AppResourceObjectPermissions app where app.id.roleId = '" + roleId + "'").list();
    }

    public List<Users> getUsersByRoleId(Integer roleId) throws Exception {
        return (List<Users>) getCurrentSession().createQuery("from Users usr where roleId = '" + roleId + "'").list();
    }

    public List<AppResource> getAllAppResources() {
        List<AppResource> list;
        //list = (List<AppResource>) getCurrentSession().createQuery("from AppResource appResource where isActive=1");
        return new ArrayList();
    }

    public List<Integer> getCurrentUsersPermissions(int roleId) {
        List<Integer> list;
        //list = (List<Integer>) getCurrentSession().createQuery("SELECT distinct arop.id.resourceId FROM AppResourceObjectPermissions arop WHERE arop.id.roleId = '" + roleId + "'");
        return new ArrayList<Integer>();
    }

    public AppResourceObjectPermissions getAllAppResourcesPermissions(AppResourceObjectPermissionsId roleId) {

        AppResourceObjectPermissions db = null;
        //Object o = this.getHibernateTemplate().get(AppResourceObjectPermissions.class, roleId);
        //if (o != null) {
        //     db = (AppResourceObjectPermissions) o;
        // }
        return db;

    }

    public int getParentIdbyUserId(Integer userId) {
        int id = 0;
        List<Integer> uId = (List<Integer>) getCurrentSession().createQuery("SELECT users.parentId FROM Users users WHERE users.userId ='" + userId + "'").list();

        if (!uId.isEmpty()) {
            id = uId.get(0);
        }

        return id;
    }

    public void removeRecordsById(Integer rId) {
        String queryString = "delete from AppResourceObjectPermissions where roleID= :roleID";
        Query q = getCurrentSession().createQuery(queryString);
        q.setInteger("roleID", rId);
        q.executeUpdate();
    }

    public List<AppResourceObjectPermissions> getAppResourcePermissionByRoleId(Integer roleId) {
        return (List<AppResourceObjectPermissions>) getCurrentSession().createQuery("from AppResourceObjectPermissions appResourceObjectPermissions where appResourceObjectPermissions.id.roleId =" + roleId).list();
    }

    public List<AppResourceObjectPermissions> getAppResourcesByUserId(int userId) {
        String sql = "SELECT "
                + "   AppResourceObjectPermissions.AllowView, "
                + "  AppResourceObjectPermissions.RoleID,"
                + "  AppResourceObjectPermissions.ResourceID, "
                + "   AppResourceObjectPermissions.AllowManage "
                + "FROM AppResourceObjectPermissions AppResourceObjectPermissions "
                + "  INNER JOIN Users Users"
                + "    ON Users.RoleId = AppResourceObjectPermissions.RoleID "
                + "WHERE Users.UserId = ?";
        List<AppResourceObjectPermissions> list = new ArrayList<>();
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setInteger(0, userId);
        List<Object[]> queryResult = query.list();
        Object value;
        AppResourceObjectPermissions appResourceObjectPermissions;
        AppResourceObjectPermissionsId id;
        for (Object[] result : queryResult) {
            appResourceObjectPermissions = new AppResourceObjectPermissions();
            id = new AppResourceObjectPermissionsId();
            value = result[0];

            if (!(Boolean) value) {
                appResourceObjectPermissions.setAllowView(false);
            } else {
                appResourceObjectPermissions.setAllowView(true);
            }

            value = result[1];
            if (value != null) {
                id.setRoleId(Integer.parseInt(value.toString()));
            }
            value = result[2];
            if (value != null) {
                id.setResourceId(Integer.parseInt(value.toString()));
            }

            value = result[3];
            if (!(Boolean) value) {
                appResourceObjectPermissions.setAllowManage(false);
            } else {
                appResourceObjectPermissions.setAllowManage(true);
            }
            appResourceObjectPermissions.setId(id);
            list.add(appResourceObjectPermissions);
        }

        return list;
    }

    public List<RoleTypes> getAllRoleTypes() throws Exception {
        return (List<RoleTypes>) getCurrentSession().createQuery("from RoleTypes roleTypes where isActive='Yes'").list();
    }

    public List<RoleTypes> fetchAllRolesTypes() throws Exception {
        return getCurrentSession().createQuery("From RoleTypes roleTypes order by roleTypes.roleId ASC").list();
    }

    public Users getUserByName(String userName) {
        Users user = null;
        permissionLogger.info("Begin: PermissionDAO -> getUserByName");
        try {
            String hql = "select usr from Users usr left join fetch usr.roleTypes roleTypes where usr.userName =  '" + userName + "'";
            List<Users> users = (List<Users>) getCurrentSession().createQuery(hql).list();
            if (users != null && !users.isEmpty()) {
                user = users.get(0);
            }
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> getUserByName", e);
        }

        permissionLogger.info("Begin: PermissionDAO -> getUserByName");
        return user;
    }

    public int userLastLogin(Integer userId) {
        permissionLogger.info("Begin: PermissionDAO -> userLastLogin");
        int count = 0;
        try {
            String hql = "update Users usr set usr.lastUsageDate = :lastUsageDate where usr.userId = :userId";
            Query query = this.getCurrentSession().createQuery(hql);
            query.setParameter("lastUsageDate", new Date());
            query.setParameter("userId", userId);
            count = query.executeUpdate();
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> getUserByName", e);
        }
        permissionLogger.info("End: PermissionDAO -> userLastLogin -> count :" + count);
        return count;
    }

    public void persist(Object o) {
        getCurrentSession().saveOrUpdate(o);
    }

    public Users getUserById(Integer userId) throws Exception {
        Users user = null;
        String queryString = "select distinct users from Users users where users.userId = :userId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("userId", userId);
        Object o = query.uniqueResult();
        if (o != null) {
            user = (Users) o;
        }
        return user;
    }

    public Campaigns getCampaignByBrandId(int campaignId, int brandId) {
        List<Campaigns> list = null;
        Campaigns campaigns = null;
        String hql = "select campaigns from Campaigns campaigns where campaigns.campaignId = :campaignId and campaigns.brandId = :brandId ";
        try {
            String[] names = {"campaignId", "brandId"};
            Object[] values = {campaignId, brandId};
            //list = (List<Campaigns>) getCurrentSession().createQueryByNamedParam(hql, names, values);

            if (!list.isEmpty()) {
                campaigns = new Campaigns();
                campaigns = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaigns;
    }

    public List<Campaigns> getCampaignByBrandId(int brandId) {
        List<Campaigns> list = null;
        String hql = "select campaigns from Campaigns campaigns where campaigns.brandId = :brandId and campaigns.isActive = 'Yes'";
        try {
            String[] names = {"brandId"};
            Object[] values = {brandId};
            // list = (List<Campaigns>) getCurrentSession().createQueryByNamedParam(hql, names, values);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteAll(List o) {
        // getHibernateTemplate().deleteAll(o);
    }

    public boolean changePassword(Integer userId, String userPassword) {
        permissionLogger.info("Begin: PermissionDAO -> changePassword");
        boolean result = false;
        try {
            String hql = "update Users usr set usr.userPassword = :userPassword where usr.userId = :userId";
            Query query = this.getCurrentSession().createQuery(hql);
            query.setParameter("userPassword", userPassword);
            query.setParameter("userId", userId);
            query.executeUpdate();
            result = true;
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> changePassword", e);
        }
        return result;
    }
    
    public boolean changePharmacyUserPassword(Integer userId, String userPassword) {
        permissionLogger.info("Begin: PermissionDAO -> changePharmacyUserPassword");
        boolean result = false;
        try {
            String hql = "update PharmacyUser usr set usr.password = :userPassword where usr.id = :userId";
            Query query = this.getCurrentSession().createQuery(hql);
            query.setParameter("userPassword", userPassword);
            query.setParameter("userId", userId);
            query.executeUpdate();
            result = true;
        } catch (Exception e) {
            permissionLogger.error("Exception: PermissionDAO -> changePharmacyUserPassword", e);
        }
        return result;
    }

    public Pharmacy getPharmacy(String email, String password) throws Exception {
        Pharmacy pharmacy = new Pharmacy();
        Query query = getCurrentSession().createQuery("From Pharmacy pharmacy left join fetch pharmacy.state st where pharmacy.email=:email and pharmacy.password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Object object = query.uniqueResult();
        if (object != null) {
            pharmacy = (Pharmacy) object;
        }
        return pharmacy;
    }
    /**
     * 
     * @return
     * @throws Exception 
     * DailyRedemption table not in used
     */

//    public List<DailyRedemption> getDailyRedemptionsList() throws Exception {
//        return getCurrentSession().createQuery("From DailyRedemption dailyRedemption").list();
//    }

    public List<Users> getUsersList() throws Exception {
        return getCurrentSession().createQuery("From Users user left join fetch user.roleTypes roleTypes where user.isActive='Yes'").list();
    }
    
    public List<PharmacyUser> getPharmacyUsersList() throws Exception {
        return getCurrentSession().createQuery("From PharmacyUser user inner join fetch user.roleTypes roleTypes where user.active=true").list();
    }
    
    public List<AppResourceObjectPermissions> getAppResourcesByPharmacyUserId(int pharmacyUserId) {
        String sql = "SELECT "
                + "   AppResourceObjectPermissions.AllowView, "
                + "  AppResourceObjectPermissions.RoleID,"
                + "  AppResourceObjectPermissions.ResourceID, "
                + "   AppResourceObjectPermissions.AllowManage "
                + "FROM AppResourceObjectPermissions AppResourceObjectPermissions "
                + "  INNER JOIN PharmacyUser Users"
                + "    ON Users.RoleId = AppResourceObjectPermissions.RoleID "
                + "WHERE Users.id = ?";
        List<AppResourceObjectPermissions> list = new ArrayList<>();
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setInteger(0, pharmacyUserId);
        List<Object[]> queryResult = query.list();
        Object value;
        AppResourceObjectPermissions appResourceObjectPermissions;
        AppResourceObjectPermissionsId id;
        for (Object[] result : queryResult) {
            appResourceObjectPermissions = new AppResourceObjectPermissions();
            id = new AppResourceObjectPermissionsId();
            value = result[0];

            if (!(Boolean) value) {
                appResourceObjectPermissions.setAllowView(false);
            } else {
                appResourceObjectPermissions.setAllowView(true);
            }

            value = result[1];
            if (value != null) {
                id.setRoleId(Integer.parseInt(value.toString()));
            }
            value = result[2];
            if (value != null) {
                id.setResourceId(Integer.parseInt(value.toString()));
            }

            value = result[3];
            if (!(Boolean) value) {
                appResourceObjectPermissions.setAllowManage(false);
            } else {
                appResourceObjectPermissions.setAllowManage(true);
            }
            appResourceObjectPermissions.setId(id);
            list.add(appResourceObjectPermissions);
        }

        return list;
    }
    /**
     * @author javed iqbal
     * @param userNameorEmail
     * @param isEmail
     * @return 
     * @throws Exception 
     */
    public boolean isPharmacyUserExists(String userNameorEmail, boolean isEmail) throws Exception {
        
         String queryStr = "select pu from PharmacyUser pu where ";
        
        if (isEmail) {
            queryStr = queryStr + " pu.email = :email";
        }else {
            queryStr = queryStr + " pu.userName = :userName";
        }
        
        Query query = getCurrentSession().createQuery(queryStr);
        if (isEmail) {
            query.setString("email", userNameorEmail.toLowerCase());
        }else {
           query.setString("userName", userNameorEmail.toLowerCase());
        }
        
        List<PharmacyUser> list = (List<PharmacyUser>) query.list();

        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
