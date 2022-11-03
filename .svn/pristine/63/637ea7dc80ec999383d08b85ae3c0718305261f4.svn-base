package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.model.AppResource;
import com.ssa.cms.model.AppResourceObjectPermissions;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.Users;
import com.ssa.cms.util.PermissionUtil;
import com.ssa.cms.util.PropertiesUtil;
import com.ssa.cms.util.RedemptionUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController implements Serializable {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MessageSource messageSource;

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = {"/login", "/admin", "/Admin"}, method = RequestMethod.GET)
    public ModelAndView login() {
        // logger.info("Begin: Login controller  @@@@@@@@@@@@@@@@@@@@@@ ##############");
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }

    @RequestMapping(value = "/notauthorized", method = RequestMethod.GET)
    public ModelAndView notauthorized() {
        ModelAndView modelAndView = new ModelAndView("notauthorized");
        return modelAndView;
    }

    @RequestMapping(value = "/notfound", method = RequestMethod.GET)
    public ModelAndView notfound() {
        ModelAndView modelAndView = new ModelAndView("notfound");
        return modelAndView;
    }

    @RequestMapping(value = {"/login", "/admin", "/Admin"}, method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Users users, Model model, HttpServletRequest request, HttpServletResponse response,
            @CookieValue(value = "user", defaultValue = "") String cUserName,
            @CookieValue(value = "password", defaultValue = "") String cPassword
    ) {
        // logger.info("Begin: LoginController -> addCustomer 1111111111111111111111111 ");
        try {
            response.getHeader("Cookie");
            String userNameUI = users.getUserName();
            String passwordUI = users.getUserPassword();
            if ("".equals(userNameUI) || "".equals(passwordUI)) {
                model.addAttribute("message", messageSource.getMessage("login.user.empty", null, null));
                return "/login";
            }

            Users userDetail = permissionService.getUserByName(userNameUI);

            if (userDetail != null) {
                String userName = userDetail.getUserName();
                String password = userDetail.getUserPassword();
                String encryptedPassword = RedemptionUtil.MD5(passwordUI);
                if (!validate(userName, userNameUI, password, encryptedPassword, model, userDetail, null)) {
                    return "/login";
                }
                permissionService.userLastLogin(userDetail.getUserId()); //update last login
                SessionBean sessionBean = populateSessionBean(userDetail, getCurrentDate());
                sessionBean.setUserPassword(passwordUI);
                request.getSession().setAttribute("sessionBean", sessionBean);
                myServiceMethodSettingCookie(users, request, response, passwordUI);
            } else {
                Pharmacy pharmacyDetail = permissionService.getPharmacyByEmail(userNameUI, passwordUI);
                if (pharmacyDetail != null) {
                    String userName = pharmacyDetail.getEmail();
                    String password = pharmacyDetail.getPassword();
                    String encryptedPassword = passwordUI;
                    if (!validate(userName, userNameUI, password, encryptedPassword, model, null, pharmacyDetail)) {
                        return "/login";
                    } else {
                        SessionBean sessionBean = new SessionBean();
                        sessionBean.setUserNameDB("viewOrder");
                        sessionBean.setUserPassword(encryptedPassword);
                        sessionBean.setUserName(pharmacyDetail.getTitle());
                        sessionBean.setUserId(pharmacyDetail.getId());
                        sessionBean.setCurrentDate(getCurrentDate());
                        sessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
                        sessionBean.setPharmacy(pharmacyDetail);
                        request.getSession().setAttribute("sessionBean", sessionBean);
                        return "redirect:/index";

                    }

                } else {
                    model.addAttribute("message", messageSource.getMessage("login.user.not.found", null, null));
                    return "/login";
                }
            }
        } catch (SQLException e) {
            logger.error("Exception: LoginController -> addCustomer", e);
        } catch (NoSuchMessageException e) {
            logger.error("Exception: LoginController -> addCustomer", e);
        }

        //  logger.info("End: LoginController -> addCustomer");
        //return "redirect:/dashboard";
        return "redirect:".concat(PropertiesUtil.getProperty("APP_PATH_CODE").trim()).concat("patient/deletePatientrecorByPhone");
        }

    private boolean validate(String userName, String userNameUI, String password, String encryptedPassword, Model model, Users userDetail, Pharmacy pharmacyDetail) throws NoSuchMessageException {

//        logger.info(" ##############################  Testing ########################### ");
//        
//        logger.info("userDetail "+userDetail);
//        logger.info("pharmacyDetail "+pharmacyDetail);
//        logger.info("userName "+userName);
//        logger.info("userNameUI "+userNameUI);
//        logger.info("password "+password);
//        logger.info("encryptedPassword "+encryptedPassword);
        // logger.info(" ######################################################################## ");
        if (userDetail != null && pharmacyDetail == null) {
            if (!(userName.equals(userNameUI) && password.equals(encryptedPassword))) {
                //invalid user
                model.addAttribute("message", messageSource.getMessage("login.invalid.credentials", null, null));
                return false;
            } else if ("No".equalsIgnoreCase(userDetail.getIsActive())) {
                //inactive
                model.addAttribute("message", messageSource.getMessage("login.inactive.user", null, null));
                return false;
            }
            Date userStartDate = userDetail.getUserStartDate();
            if (getCurrentDate().before(userStartDate)) {
                model.addAttribute("message", messageSource.getMessage("login.startdate.future", null, null));
                return false;
            }

        } else if (pharmacyDetail != null && userDetail == null) {
            if (!(userNameUI.equals(userName) && encryptedPassword.equals(password))) {
                //invalid user
                model.addAttribute("message", messageSource.getMessage("login.invalid.credentials", null, null));
                return false;
            } else if ("No".equalsIgnoreCase(pharmacyDetail.getStatus())) {
                //inactive
                model.addAttribute("message", messageSource.getMessage("login.inactive.userPharmacy", null, null));
                return false;
            }
        }
        return true;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionBean");
        //request.getSession().invalidate();
        return login();
    }

    private Date getCurrentDate() {
        return new Date();
    }

    private SessionBean populateSessionBean(Users userDetail, Date currentDate) throws SQLException {
        SessionBean sessionBean = new SessionBean();
        String userName = userDetail.getUserName();
        String encryptedPassword = RedemptionUtil.MD5(userDetail.getUserPassword());
        String firstName = userDetail.getFirstName();
        String lastName = userDetail.getLastName();
        sessionBean.setUserNameDB(userName);
        sessionBean.setUserName(firstName + " " + lastName);
        sessionBean.setUserPassword(encryptedPassword);
        sessionBean.setUserId(userDetail.getUserId());
        sessionBean.setCurrentDate(currentDate);
        sessionBean.setParentId(userDetail.getParentId());
        sessionBean.setRoleId(userDetail.getRoleTypes().getRoleId());
        sessionBean.setRole(userDetail.getRoleTypes().getRoleTitle());
        //sessionBean.setTransactionNumber(permissionService.getDailyRedemption());
        sessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");

        //get all resources and fill map
        List<AppResource> resources = permissionService.getAppResources();
        Map<Integer, PermissionUtil> pmap = new LinkedHashMap<>();
        for (AppResource appResource : resources) {
            PermissionUtil permissionUtil = new PermissionUtil();
            permissionUtil.setView(false);
            permissionUtil.setManage(false);
            permissionUtil.setServletName(appResource.getResourceUrl());
            pmap.put(appResource.getResourceId(), permissionUtil);
        }

        //get permission by user id
        List<AppResourceObjectPermissions> listPermission = permissionService.getAppResourcesByUserId(userDetail.getUserId());
        for (AppResourceObjectPermissions appResourceObjectPermissions : listPermission) {
            Integer resourceId = appResourceObjectPermissions.getId().getResourceId();
            PermissionUtil permissionUtil = pmap.get(resourceId);
            if (permissionUtil == null) {
                continue;
            }
            if (appResourceObjectPermissions.isAllowManage()) {
                permissionUtil.setManage(true);
            }
            if (appResourceObjectPermissions.isAllowView()) {
                permissionUtil.setView(true);
            }
            pmap.put(resourceId, permissionUtil);
        }
        sessionBean.setPmap(pmap);

        return sessionBean;
    }

    private void myServiceMethodSettingCookie(Users users, HttpServletRequest request, HttpServletResponse response, String password) {

        if (users.getChkValue() != null && !"".equals(users.getChkValue())) {
            final int expiryTime = 60 * 60 * 24 * 30; // 30days
            Cookie myCookie = new Cookie("user", users.getUserName());
            myCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
            response.addCookie(myCookie);

            myCookie = new Cookie("password", password);
            myCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
            response.addCookie(myCookie);
        } else {
            final int expiryTime = 0;
            Cookie myCookie = new Cookie("user", null);
            myCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
            response.addCookie(myCookie);

            myCookie = new Cookie("password", null);
            myCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
            response.addCookie(myCookie);
        }
    }

}
