package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.FinancialReportDTO;
import com.ssa.cms.dto.SearchDTO;
import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.dto.ViewDTO;
import com.ssa.cms.model.AppResource;
import com.ssa.cms.model.AppResourceObjectPermissions;
import com.ssa.cms.model.DrugBrand;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.NotifyAdmin;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderHistory;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyLookup;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.service.ConsumerPortalService;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.PermissionUtil;
import com.ssa.cms.util.PharmacyQueueUtil;
import com.ssa.cms.util.PropertiesUtil;
import com.ssa.cms.util.RedemptionUtil;
import com.ssa.cms.validator.ConsumerChangePasswordValidator;
import com.ssa.cms.validator.ConsumerRegistrationValidator;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author msheraz
 */
@Controller
@RequestMapping(value = {"/PharmacyPortal", "/Pharmacyqueue", "/pharmacyqueue"})
public class ConsumerRegistrationController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;
    @Autowired
    private ConsumerRegistrationValidator consumerRegistrationValidator;
    @Autowired
    private ConsumerChangePasswordValidator consumerChangePasswordValidator;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ConsumerPortalService consumerPortalService;
    SessionBean sessionBean;
    private final Log logger = LogFactory.getLog(ConsumerRegistrationController.class);

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBeanPortal");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loadPharmacyLoginPageHandler(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/pharmacylogin2");
        pageContents(modelAndView);
        try {
            String recordOpened = (String) request.getSession().getAttribute("recordOpened");
            if (recordOpened != null && recordOpened.trim().equalsIgnoreCase("true")) {

                String id = (String) request.getSession().getAttribute("orderId");
                //TODO
//                if (id != null && !id.trim().equalsIgnoreCase("0") && !id.trim().equalsIgnoreCase("")) {
//                    consumerRegistrationService.setOrderStatusWithId("Closed", id);
//                }

            }
            SessionBean sb = (SessionBean) request.getSession().getAttribute("sessionBean");
            if (sb != null) {
                modelAndView.addObject("sessionBean", sb);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("#Exception" + e);
        }
        modelAndView.addObject("pharmacy", new Pharmacy());
        modelAndView.addObject("pharmacyLookup", new PharmacyLookup());
       // modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String pharmacyLogin(@ModelAttribute Pharmacy pharmacy, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        //ModelAndView modelAndView = new ModelAndView("portal/pharmacylogin");
        String str_response = "";

        try {
            String userNameUI = pharmacy.getEmail();
            String passwordUI = pharmacy.getPassword();

            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(userNameUI);

            //pageContents(modelAndView);
            if (pharmacyUser != null) {
                String userName = pharmacyUser.getEmail();
                String encryptedPassword = pharmacyUser.getPassword();
                String password = passwordUI;
                if (!(userNameUI.equals(userName) && CommonUtil.isPswExist(password, encryptedPassword))) {   //encryptedPassword.equals(password)

                    //invalid user
                    //modelAndView.addObject("message", messageSource.getMessage("login.invalid.credentials", null, null));
                    String errorMessage = messageSource.getMessage("login.invalid.credentials", null, null);
                    str_response = JsonResponseComposer.composeFailureResponse(errorMessage);
                    return str_response;
                } else if ("InActive".equalsIgnoreCase(pharmacyUser.getStatus())) {
                    //inactive
                    //modelAndView.addObject("message", messageSource.getMessage("login.inactive.userPharmacy", null, null));
                    String errorMessage = messageSource.getMessage("login.inactive.userPharmacy", null, null);
                    str_response = JsonResponseComposer.composeFailureResponse(errorMessage);
                    return str_response;
                }
                request.getSession().setAttribute("pharmacyUser", pharmacyUser);
                //Save last logged on date
                consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());

                SessionBean newSessionBean = populateSessionBean(pharmacyUser, new Date(), permissionService);
                newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
                newSessionBean.setUserNameDB("PharmacyPortal");
                newSessionBean.setUserPassword(encryptedPassword);
                newSessionBean.setUserName(pharmacyUser.getEmail());
                newSessionBean.setUserId(pharmacyUser.getId());
                newSessionBean.setRole(pharmacyUser.getRole());
                newSessionBean.setCurrentDate(new Date());
                Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
                //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());
                newSessionBean.setPharmacyAbbr(AppUtil.getSafeStr(par.getAbbr(), ""));
                request.getSession().setAttribute("PharmacyTitle", par.getTitle());
                try {
                    request.getSession().setAttribute("Address", par.getAddress1() + System.lineSeparator());
                    request.getSession().setAttribute("Address2", ((par.getAddress2() == null || par.getAddress2().equals("")) ? "" : par.getAddress2())
                            + System.lineSeparator() + par.getCity() + " " + (par.getState() != null ? par.getState().getName() : "") + " " + par.getZip());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                request.getSession().setAttribute("Phone", par.getPhone());
                request.getSession().setAttribute("Fax", CommonUtil.isNullOrEmpty(par.getFax()) ? "" : par.getFax());
                request.getSession().setAttribute("userName", userName);
                request.getSession().setAttribute("Email", CommonUtil.isNullOrEmpty(par.getEmail()) ? "" : par.getEmail());
                request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
                request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
                request.getSession().setAttribute("Count", consumerRegistrationService.getCountTransferDetails());
                String currentUserName = "";
                currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

                newSessionBean.setCurrentUserName(currentUserName);
                newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
                newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
                request.getSession().setAttribute("sessionBeanPortal", newSessionBean);
                request.getSession().setAttribute("Title", "Pharmacy Queue");
                //modelAndView.setViewName("transferRxQueue");
                //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
                request.getSession().setMaxInactiveInterval(900000);
                str_response = JsonResponseComposer.composeSuccessResponse();
            } else {
                String errorMessage = messageSource.getMessage("login.invalid.credentials", null, null);
                str_response = JsonResponseComposer.composeFailureResponse(errorMessage);
                //modelAndView.addObject("message", messageSource.getMessage("login.invalid.credentials", null, null));
                //return modelAndView;
                return str_response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationController -> pharmacyLogin", e);
        }
        //return modelAndView;
        return str_response;
    }

    @RequestMapping(value = "/Cancel", method = RequestMethod.POST)
    public ModelAndView loadPharmacyLogin() {
        ModelAndView modelAndView = new ModelAndView("portal/pharmacylogin2");
        pageContents(modelAndView);
        modelAndView.addObject("pharmacy", new Pharmacy());
        modelAndView.addObject("pharmacyLookup", new PharmacyLookup());
       // modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/successLogin", method = RequestMethod.GET)
    public ModelAndView sucessfullLogin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("transferRxQueue");
        try {
            modelAndView.setViewName("transferRxQueue");
            String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
            int userId = 0;
            PharmacyUser user = null;
            if (request.getSession().getAttribute("pharmacyUser") != null) {
                user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
                if (user != null) {
                    userId = user.getId();
                }
            }
            //List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId);
            List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Pending", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listProcessing = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "other", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listProcessed = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Shipped", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listFilled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Filled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listCancelled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Cancelled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listHold = new ArrayList<>();// consumerRegistrationService.
            //getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "On Hold");

            modelAndView.addObject("listRxTransfer", list);//consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails());
            modelAndView.addObject("Count", list != null ? "" + list.size() : "0");
            modelAndView.addObject("listRxProcessing", listProcessing);
            modelAndView.addObject("ProcessingCount", listProcessing != null ? "" + listProcessing.size() : "0");
            modelAndView.addObject("listRxProcessed", listProcessed);
            modelAndView.addObject("ProcessedCount", listProcessed != null ? "" + listProcessed.size() : "0");
            modelAndView.addObject("listRxFilled", listFilled);
            modelAndView.addObject("filledCount", listFilled != null ? "" + listFilled.size() : "0");
            modelAndView.addObject("listHold", listHold);
            modelAndView.addObject("holdCount", listHold != null ? "" + listHold.size() : "0");
            modelAndView.addObject("listCancelled", listCancelled);
            modelAndView.addObject("CancelledCount", listCancelled != null ? "" + listCancelled.size() : "0");
            modelAndView.addObject("tab1", tab1);
            modelAndView.addObject("tab2", tab2);
            modelAndView.addObject("tab3", tab3);
            modelAndView.addObject("tab4", tab4);
            modelAndView.addObject("recordDiv1", recordDiv1);
            modelAndView.addObject("recordDiv2", recordDiv2);
            modelAndView.addObject("recordDiv3", recordDiv3);
            modelAndView.addObject("recordDiv4", recordDiv4);
            populateFinancialReportValuesInModel(modelAndView);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationController -> sucessfullLogn", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/successLogin/{orderId}")
    public ModelAndView sucessfullLogin(@PathVariable("orderId") String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("transferRxQueue");
        try {
            modelAndView.setViewName("transferRxQueue");
            String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
            String status = AppUtil.getSafeStr(request.getParameter("status"), "");
            if (status.length() > 0) {

                if (status.equalsIgnoreCase("shipped")) {
                    tab1 = "";
                    tab2 = "";
                    tab3 = "class=\"active\"";
                    tab4 = "";
                    recordDiv1 = "";
                    recordDiv2 = "";
                    recordDiv3 = "active";
                    recordDiv4 = "";
                }

                if (status.equalsIgnoreCase("On Hold")) {
                    tab1 = "";
                    tab2 = "";
                    tab3 = "";
                    tab4 = "class=\"active\"";
                    recordDiv1 = "";
                    recordDiv2 = "";
                    recordDiv3 = "";
                    recordDiv4 = "active";
                } else if (!status.equalsIgnoreCase("pending")) {
                    tab1 = "";
                    tab2 = "class=\"active\"";
                    tab3 = "";
                    tab4 = "";
                    recordDiv1 = "";
                    recordDiv2 = "active";
                    recordDiv3 = "";
                    recordDiv4 = "";
                }
            }
            if (!CommonUtil.isNullOrEmpty(id) && !id.trim().equals("0")) {

                consumerRegistrationService.updateOrderStatusWithIdAndUser("Pending", id, 0);//setOrderStatusWithId("Pending", id);
                request.getSession().setAttribute("recordOpened", "false");
                request.getSession().removeAttribute("orderId");
            }
            request.getSession().setAttribute("Title", "Pharmacy Queue");
            int userId = 0;
            PharmacyUser user = null;
            if (request.getSession().getAttribute("pharmacyUser") != null) {
                user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
                if (user != null) {
                    userId = user.getId();
                }
            }
            //List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId);
            List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Pending", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listProcessing = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "other", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listProcessed = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Shipped", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listFilled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Filled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listCancelled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Cancelled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listHold = new ArrayList<>();// consumerRegistrationService.
            //getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "On Hold");

            modelAndView.addObject("listRxTransfer", list);//consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails());
            modelAndView.addObject("Count", list != null ? "" + list.size() : "0");
            modelAndView.addObject("listRxProcessing", listProcessing);
            modelAndView.addObject("ProcessingCount", listProcessing != null ? "" + listProcessing.size() : "0");
            modelAndView.addObject("listRxProcessed", listProcessed);
            modelAndView.addObject("ProcessedCount", listProcessed != null ? "" + listProcessed.size() : "0");
            modelAndView.addObject("listRxFilled", listFilled);
            modelAndView.addObject("filledCount", listFilled != null ? "" + listFilled.size() : "0");
            modelAndView.addObject("listCancelled", listCancelled);
            modelAndView.addObject("CancelledCount", listCancelled != null ? "" + listCancelled.size() : "0");
            modelAndView.addObject("listHold", listHold);
            modelAndView.addObject("holdCount", listHold != null ? "" + listHold.size() : "0");
            modelAndView.addObject("tab1", tab1);
            modelAndView.addObject("tab2", tab2);
            modelAndView.addObject("tab3", tab3);
            modelAndView.addObject("tab4", tab4);
            modelAndView.addObject("recordDiv1", recordDiv1);
            modelAndView.addObject("recordDiv2", recordDiv2);
            modelAndView.addObject("recordDiv3", recordDiv3);
            modelAndView.addObject("recordDiv4", recordDiv4);
            populateFinancialReportValuesInModel(modelAndView);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> sucessfullLogn", e);
        }
        return modelAndView;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/successSignin", method = RequestMethod.GET)
    public ModelAndView sucessfullSignin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1Old");
        System.out.println("Entered in SIGNIN FUNCTION");
        try {
            modelAndView.setViewName("pharmacyQueuePage1Old");
            populatePage1Data(request, modelAndView);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: ConsumerRegistrationController -> sucessfullLogn", e);
        }
        return modelAndView;
    }

    ////////////////////////////////////////////////////////////////////////////
    private void populatePage1Data(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
        int userId = getSessionUserId(request);
        //List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId);
        System.out.println("GOING TO POPULATE PENDING ORDERS");
        List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueForPage1(userId, "Pending");
        System.out.println("GOING TO POPULATE PENDING REVIEW ORDERS");
        List<TransferRxQueueDTO> listInterpreted = consumerRegistrationService.
                getTranferRxQueueForPage1(userId, "Pending Review");
        System.out.println("GOING TO POPULATE WAITING RESPONSE ORDERS");
        List<TransferRxQueueDTO> listWaitingPtResponse = consumerRegistrationService.
                getTranferRxQueueForPage1(userId, "Waiting Pt Response");
        System.out.println("GOING TO POPULATE SHIPPED ORDERS");
        List<TransferRxQueueDTO> listProcessed = consumerRegistrationService.
                getTranferRxQueueForPage1(userId, "Shipped");
        System.out.println("GOING TO POPULATE FILLED ORDERS");
        List<TransferRxQueueDTO> listFilled = consumerRegistrationService.
                getTranferRxQueueForPage1(userId, "Filled");
        System.out.println("GOING TO POPULATE CANCELLED ORDERS");
        List<TransferRxQueueDTO> listCancelled = consumerRegistrationService.
                getTranferRxQueueForPage1(userId, "Cancelled");
        List<TransferRxQueueDTO> listHold = new ArrayList<>();// consumerRegistrationService.
        //getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "On Hold");
        modelAndView.addObject("listRxTransfer", list);//consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails());
        modelAndView.addObject("Count", list != null ? "" + list.size() : "0");
        modelAndView.addObject("listInterpreted", listInterpreted);
        modelAndView.addObject("ProcessingCount", listInterpreted != null ? "" + listInterpreted.size() : "0");
        modelAndView.addObject("listWaitingPtResponse", listWaitingPtResponse);
        modelAndView.addObject("WaitingPtResponseCount", listWaitingPtResponse != null ? "" + listWaitingPtResponse.size() : "0");
        modelAndView.addObject("listRxProcessed", listProcessed);
        modelAndView.addObject("ProcessedCount", listProcessed != null ? "" + listProcessed.size() : "0");
        modelAndView.addObject("listRxFilled", listFilled);
        modelAndView.addObject("filledCount", listFilled != null ? "" + listFilled.size() : "0");
        modelAndView.addObject("listHold", listHold);
        modelAndView.addObject("holdCount", listHold != null ? "" + listHold.size() : "0");
        modelAndView.addObject("listCancelled", listCancelled);
        modelAndView.addObject("CancelledCount", listCancelled != null ? "" + listCancelled.size() : "0");
        modelAndView.addObject("tab1", tab1);
        modelAndView.addObject("tab2", tab2);
        modelAndView.addObject("tab3", tab3);
        modelAndView.addObject("tab4", tab4);
        modelAndView.addObject("recordDiv1", recordDiv1);
        modelAndView.addObject("recordDiv2", recordDiv2);
        modelAndView.addObject("recordDiv3", recordDiv3);
        modelAndView.addObject("recordDiv4", recordDiv4);
        modelAndView.addObject("loadingTime", PropertiesUtil.getProperty("NEXT_ORDER_LOADING"));
        System.out.println("GOING TO POPULATE FINANCIAL REPORT");
        populateFinancialReportValuesInModel(modelAndView);
        System.out.println("POPULATING VIEW");
    }

    ////////////////////////////////////////////////////////////////////////////
    private Map<String, Integer> populateStatusWiseCount(HttpServletRequest request) {
        PharmacyUser user = getPharmacyUser(request);
        Map<String, Integer> map = this.consumerRegistrationService.populateCountForPage1(CommonUtil.getPharmacyId(user));
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    private PharmacyUser getPharmacyUser(HttpServletRequest request) {
        PharmacyUser user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        return user;
    }

    ////////////////////////////////////////////////////////////////////////////
    private void populatePage1DataNew(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
        int userId = getSessionUserId(request);
        PharmacyUser user = getPharmacyUser(request);
        Map<String, Integer> map = this.consumerRegistrationService.populateCountForPage1(CommonUtil.getPharmacyId(user));
        if (map == null) {
            map = new HashMap<>();
        }
        //List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId);
//        System.out.println("GOING TO POPULATE PENDING ORDERS");
//        List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueForPage1(userId, "Pending");
//        System.out.println("GOING TO POPULATE PENDING REVIEW ORDERS");
//        List<TransferRxQueueDTO> listInterpreted = consumerRegistrationService.
//                getTranferRxQueueForPage1(userId, "Pending Review");
//        System.out.println("GOING TO POPULATE WAITING RESPONSE ORDERS");
//        List<TransferRxQueueDTO> listWaitingPtResponse = consumerRegistrationService.
//                getTranferRxQueueForPage1(userId, "Waiting Pt Response");
//        System.out.println("GOING TO POPULATE SHIPPED ORDERS");
//        List<TransferRxQueueDTO> listProcessed = consumerRegistrationService.
//                getTranferRxQueueForPage1(userId, "Shipped");
//        System.out.println("GOING TO POPULATE FILLED ORDERS");
//        List<TransferRxQueueDTO> listFilled = consumerRegistrationService.
//                getTranferRxQueueForPage1(userId, "Filled");
//        System.out.println("GOING TO POPULATE CANCELLED ORDERS");
//        List<TransferRxQueueDTO> listCancelled = consumerRegistrationService.
//                getTranferRxQueueForPage1(userId, "Cancelled");
//        List<TransferRxQueueDTO> listHold = new ArrayList<>();// consumerRegistrationService.
        //getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "On Hold");
//        modelAndView.addObject("listRxTransfer", list);//consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails());
//        Map<String, Integer> pendingCount = this.consumerRegistrationService.populateCountForPage1(null);
//        if (pendingCount == null) {
//            pendingCount = new HashMap<>();
//        }
        modelAndView.addObject("Count", map.get("1") != null ? map.get("1") : 0);
//        modelAndView.addObject("listInterpreted", listInterpreted);
        modelAndView.addObject("ProcessingCount", map.get("17") != null ? map.get("17") : 0);
//        modelAndView.addObject("listWaitingPtResponse", listWaitingPtResponse);
        modelAndView.addObject("WaitingPtResponseCount", map.get("16") != null ? map.get("16") : 0);
        modelAndView.addObject("PtResponseCount", map.get("19") != null ? map.get("19") : 0);
//        modelAndView.addObject("listRxProcessed", listProcessed);
        int shipped = map.get("6") != null ? map.get("6") : 0;
        int delivered = map.get("5") != null ? map.get("5") : 0;
        int pickPharmacy = map.get("15") != null ? map.get("15") : 0;
        BigInteger shippedAndReadyToDeliveryCount = this.consumerRegistrationService.populateShippedAndReadyToDeliveryCountForPage1(CommonUtil.getPharmacyId(user));
        //modelAndView.addObject("ProcessedCount", shipped + delivered + pickPharmacy);
        modelAndView.addObject("ProcessedCount", shippedAndReadyToDeliveryCount);
//        modelAndView.addObject("listRxFilled", listFilled);
        modelAndView.addObject("filledCount", map.get("8") != null ? map.get("8") : 0);
//        modelAndView.addObject("listHold", listHold);
        modelAndView.addObject("holdCount", 0);
//        modelAndView.addObject("listCancelled", listCancelled);
        modelAndView.addObject("CancelledCount", map.get("11") != null ? map.get("11") : 0);
        modelAndView.addObject("tab1", tab1);
        modelAndView.addObject("tab2", tab2);
        modelAndView.addObject("tab3", tab3);
        modelAndView.addObject("tab4", tab4);
        modelAndView.addObject("recordDiv1", recordDiv1);
        modelAndView.addObject("recordDiv2", recordDiv2);
        modelAndView.addObject("recordDiv3", recordDiv3);
        modelAndView.addObject("recordDiv4", recordDiv4);
        modelAndView.addObject("loadingTime", PropertiesUtil.getProperty("NEXT_ORDER_LOADING"));
        System.out.println("GOING TO POPULATE FINANCIAL REPORT");
//        populateFinancialReportValuesInModel(modelAndView);
        System.out.println("POPULATING VIEW");
    }

    private int getSessionUserId(HttpServletRequest request) {
        int userId = 0;
        if (request.getSession().getAttribute("pharmacyUser") != null) {
            PharmacyUser user = getPharmacyUser(request);
            if (user != null) {
                userId = user.getId();
            }
            System.out.println("FETCHED USER INFO");
        }
        return userId;
    }

    @RequestMapping(value = "/successSignin/{orderId}")
    public ModelAndView sucessfullSignin(@PathVariable("orderId") String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("transferRxQueue");
        try {
            modelAndView.setViewName("pharmacyQueuePage1Old");
            String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
            String status = AppUtil.getSafeStr(request.getParameter("status"), "");
            if (status.length() > 0) {

                if (status.equalsIgnoreCase("shipped")) {
                    tab1 = "";
                    tab2 = "";
                    tab3 = "class=\"active\"";
                    tab4 = "";
                    recordDiv1 = "";
                    recordDiv2 = "";
                    recordDiv3 = "active";
                    recordDiv4 = "";
                }

                if (status.equalsIgnoreCase("On Hold")) {
                    tab1 = "";
                    tab2 = "";
                    tab3 = "";
                    tab4 = "class=\"active\"";
                    recordDiv1 = "";
                    recordDiv2 = "";
                    recordDiv3 = "";
                    recordDiv4 = "active";
                } else if (!status.equalsIgnoreCase("pending")) {
                    tab1 = "";
                    tab2 = "class=\"active\"";
                    tab3 = "";
                    tab4 = "";
                    recordDiv1 = "";
                    recordDiv2 = "active";
                    recordDiv3 = "";
                    recordDiv4 = "";
                }
            }
            if (!CommonUtil.isNullOrEmpty(id) && !id.trim().equals("0")) {

                consumerRegistrationService.updateOrderStatusWithIdAndUser("Pending", id, 0);//setOrderStatusWithId("Pending", id);
                request.getSession().setAttribute("recordOpened", "false");
                request.getSession().removeAttribute("orderId");
            }
            request.getSession().setAttribute("Title", "Pharmacy Queue");
            int userId = 0;
            PharmacyUser user = null;
            if (request.getSession().getAttribute("pharmacyUser") != null) {
                user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
                if (user != null) {
                    userId = user.getId();
                }
            }
            //List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId);
            List<TransferRxQueueDTO> list = consumerRegistrationService.getTranferRxQueueForPage1(userId, "Pending");
            List<TransferRxQueueDTO> listInterpreted = consumerRegistrationService.
                    getTranferRxQueueForPage1(userId, "Pending Review");
            List<TransferRxQueueDTO> listProcessing = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "other", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listProcessed = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Shipped", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listFilled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Filled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listCancelled = consumerRegistrationService.
                    getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "Cancelled", CommonUtil.getPharmacyId(user));
            List<TransferRxQueueDTO> listHold = new ArrayList<>();// consumerRegistrationService.
            //getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(userId, "On Hold");

            modelAndView.addObject("listRxTransfer", list);//consumerRegistrationService.getTranferRxQueueByPatientProfileTransferRequestTransferDetails());
            modelAndView.addObject("Count", list != null ? "" + list.size() : "0");
            modelAndView.addObject("listRxProcessing", listProcessing);
            modelAndView.addObject("ProcessingCount", listProcessing != null ? "" + listProcessing.size() : "0");
            modelAndView.addObject("listRxProcessed", listProcessed);
            modelAndView.addObject("ProcessedCount", listProcessed != null ? "" + listProcessed.size() : "0");
            modelAndView.addObject("listRxFilled", listFilled);
            modelAndView.addObject("filledCount", listFilled != null ? "" + listFilled.size() : "0");
            modelAndView.addObject("listCancelled", listCancelled);
            modelAndView.addObject("CancelledCount", listCancelled != null ? "" + listCancelled.size() : "0");
            modelAndView.addObject("listHold", listHold);
            modelAndView.addObject("holdCount", listHold != null ? "" + listHold.size() : "0");
            modelAndView.addObject("tab1", tab1);
            modelAndView.addObject("tab2", tab2);
            modelAndView.addObject("tab3", tab3);
            modelAndView.addObject("tab4", tab4);
            modelAndView.addObject("recordDiv1", recordDiv1);
            modelAndView.addObject("recordDiv2", recordDiv2);
            modelAndView.addObject("recordDiv3", recordDiv3);
            modelAndView.addObject("recordDiv4", recordDiv4);
            modelAndView.addObject("loadingTime", PropertiesUtil.getProperty("NEXT_ORDER_LOADING"));
            populateFinancialReportValuesInModel(modelAndView);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> sucessfullLogn", e);
        }
        return modelAndView;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    private void populateFinancialReportValuesInModel(ModelAndView modelAndView) throws Exception {
        int weekOfyear = DateUtil.getWeekOfTheYear(new Date());
        int cycle = weekOfyear / 2;
        Date frmDate = new Date();
        Date toDate = new Date();
        if (weekOfyear % 2 == 0) {
            int dayOfWeek = DateUtil.getDayOfWeek(new Date());
            int daysToAdd = 7 - dayOfWeek;
            frmDate = DateUtil.addDaysToDate(frmDate, (dayOfWeek - 1 + 7) * -1);
            toDate = DateUtil.addDaysToDate(toDate, daysToAdd);
        }
        modelAndView.addObject("frmDate", DateUtil.dateToString(frmDate, Constants.USA_DATE_FORMATE));
        modelAndView.addObject("toDate", DateUtil.dateToString(toDate, Constants.USA_DATE_FORMATE));
        modelAndView.addObject("currCycle", cycle);
        modelAndView.addObject("currYear", DateUtil.getCurrYear(new Date()));

    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/loadFinancialReportParams", produces = "application/json")
    public @ResponseBody
    String loadFinancialReportParams() throws ServletException, IOException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            int weekOfyear = DateUtil.getWeekOfTheYear(new Date());
            int cycle = weekOfyear / 2;
            Date frmDate = new Date();
            Date toDate = new Date();
            if (weekOfyear % 2 == 0) {
                int dayOfWeek = DateUtil.getDayOfWeek(new Date());
                int daysToAdd = 7 - dayOfWeek;
                frmDate = DateUtil.addDaysToDate(frmDate, (dayOfWeek - 1 + 7) * -1);
                toDate = DateUtil.addDaysToDate(toDate, daysToAdd);
                FinancialReportDTO dto = new FinancialReportDTO();
                dto.setFrmDate(DateUtil.dateToString(frmDate, Constants.USA_DATE_FORMATE));
                dto.setToDate(DateUtil.dateToString(toDate, Constants.USA_DATE_FORMATE));
                dto.setCycleNo(cycle + "");
                dto.setYear(DateUtil.getCurrYear(new Date()) + "");
                String str_response = objectMapper.writeValueAsString(dto);
                return str_response;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/loadFinancialCycleInfo", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String loadFinancialCycleInfo(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for loading financial cycle info @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode yearNode = rootNode.path("year");
            String yearStr = yearNode.asText();

            JsonNode cycleNode = rootNode.path("cycle");
            String cycleStr = cycleNode.asText();

            int year = AppUtil.getSafeInt(yearStr, 0);
            int cycle = AppUtil.getSafeInt(cycleStr, 0);

            if (year > 0 && cycle > 0) {
                //DateUtil.stringToDate("01/01"+year, "Mm/dd/yyyy");
                int startWeek = (cycle * 2) - 1;
                //int endWeek=cycle*2;
                Date frmDate = DateUtil.getDateFromASpecificWeek(startWeek, year);
                Date toDate = DateUtil.addDaysToDate(frmDate, 13);
                FinancialReportDTO dto = new FinancialReportDTO();
                dto.setFrmDate(DateUtil.dateToString(frmDate, Constants.USA_DATE_FORMATE));
                dto.setToDate(DateUtil.dateToString(toDate, Constants.USA_DATE_FORMATE));
                dto.setCycleNo(cycle + "");
                dto.setYear(year + "");
                String str_response = objectMapper.writeValueAsString(dto);
                return str_response;
            }

//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
//            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugName(i_drugName);
//            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
//            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public ModelAndView loadChangePasswordPageHandler() {
        ModelAndView modelAndView = new ModelAndView("portal/changepassword");
        pageContents(modelAndView);
        modelAndView.addObject("pharmacy", new Pharmacy());
        return modelAndView;
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public ModelAndView changePasswordHandler(@ModelAttribute Pharmacy pharmacy, HttpServletRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("portal/changepassword");
        try {
            consumerChangePasswordValidator.validate(pharmacy, result);
            if (result.hasErrors()) {
                return modelAndView;
            }
            String email = sessionBean.getUserName();
            Pharmacy pharmacy1 = consumerRegistrationService.getPharmacyByEmail(email);
            String currentPassword;
            if (pharmacy1 != null) { // in case of admin user
                currentPassword = pharmacy1.getPassword();
            } else { // in case secondary user
                PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
                currentPassword = pharmacyUser.getPassword();
            }
            if (!currentPassword.equals(pharmacy.getPassword())) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.password.invalid", null, null));
                return modelAndView;
            } else if (!pharmacy.getNewPassword().equals(pharmacy.getRepeatNewPassword())) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.newpassword.invalid", null, null));
                return modelAndView;
            }

            consumerRegistrationService.changePharmacyPassword(email, pharmacy.getNewPassword());

            request.getSession().removeAttribute("sessionBeanPortal");
            //request.getSession().invalidate();
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("consumer.password.updated", null, null));
            return new ModelAndView("redirect:/PharmacyPortal/login");
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> forgotUserNameHandler", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView loadConsumerProfilePageHandler(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/profile");
        try {
            String email = sessionBean.getUserName();
            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
            Pharmacy pharmacyDetail = pharmacyUser.getPharmacy();
            modelAndView.addObject("pharmacy", pharmacyDetail);
            pageContents(modelAndView);
            modelAndView.addObject("pharmacyUserList", consumerRegistrationService.getPharmacyUserList(pharmacyDetail.getId()));
            modelAndView.addObject("pharmacyFacilityOperations", consumerRegistrationService.getPharmacyFacilityOperations(pharmacyDetail.getId()));
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> loadConsumerProfilePageHandler", e);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile_edit/{email}", method = RequestMethod.GET)
    public ModelAndView loadEditProfilePageHandler(@PathVariable("email") String email, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/profile_edit");
        try {
//            String email = sessionBean.getUserName();
            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
            Pharmacy pharmacyDetail = pharmacyUser.getPharmacy();
            pharmacyDetail.setPharmacyUserList(new ArrayList(consumerRegistrationService.getPharmacyUserList(pharmacyDetail.getId())));
            pharmacyDetail.setPharmacyFacilityOperationList(new ArrayList(consumerRegistrationService.getPharmacyFacilityOperations(pharmacyDetail.getId())));
            pageContents(modelAndView);
            List<String> facilityHoursList = new ArrayList<>();
            for (int count = 0; count < 25; count++) {
                if (count < 10) {
                    facilityHoursList.add("0" + count);
                } else {
                    facilityHoursList.add(String.valueOf(count));
                }
            }
            modelAndView.addObject("facilityHoursList", facilityHoursList);
            modelAndView.addObject("pharmacy", pharmacyDetail);
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> loadConsumerProfilePageHandler", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/accountsummary", method = RequestMethod.GET)
    public ModelAndView accountSummary(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/accountsummary");
        String email = sessionBean.getUserName();
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
        pageContents(modelAndView);
        modelAndView.addObject("orderList", orderService.getAllOrderByPharmacyId(pharmacyUser.getPharmacy().getId()));
        modelAndView.addObject("order", new Order());
        modelAndView.addObject("path", "accountsummary");
        return modelAndView;
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
    public ModelAndView searchOrderHandler(@ModelAttribute Order order, BindingResult result, RedirectAttributes redirectAttributes) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("portal/accountsummary");
        String email = sessionBean.getUserName();
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
        Integer pharmacyId = pharmacyUser.getPharmacy().getId();
        pageContents(modelAndView);
        modelAndView.addObject("orderList", orderService.searchOrders(order, pharmacyId));
        modelAndView.addObject("orderStatusList", orderService.getOrderStatusList());
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping(value = "/prescription", method = RequestMethod.GET)
    public ModelAndView prescription(HttpServletRequest request, String orderNo) {
        ModelAndView modelAndView = new ModelAndView("portal/prescription");
        managePrescription(request, modelAndView, orderNo);
        return modelAndView;
    }

    private void managePrescription(HttpServletRequest request, ModelAndView modelAndView, String orderNo) {
        String email = sessionBean.getUserName();
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
        Order orderDetail = setOrderValue(pharmacyUser.getPharmacy().getId());
        pageContents(modelAndView);
        modelAndView.addObject("orderList", orderService.getAllOrderByPharmacyId(pharmacyUser.getPharmacy().getId()));
        dropDownOrderList(modelAndView, orderDetail);
        modelAndView.addObject("order", orderDetail);
        modelAndView.addObject("path", "prescription");
        if (orderNo == null && orderDetail != null) {
            orderNo = orderDetail.getId();
        }
        modelAndView.addObject("selectedOrderNo", orderNo);
    }

    private void dropDownOrderList(ModelAndView modelAndView, Order orderDetail) {
        modelAndView.addObject("orderStatusList", orderService.getOrderStatusList());
        modelAndView.addObject("filterOrderStatus", filteredStatusList(orderDetail));
        modelAndView.addObject("orderCarriersList", orderService.getOrderCarriersList());
    }

    @RequestMapping(value = "/prescription/{orderNo}", method = RequestMethod.GET)
    public ModelAndView prescriptionView(HttpServletRequest request, @PathVariable String orderNo) {
        return getOrderDetail("prescription", orderNo, request);
    }

    private Order setOrderValue(Integer pharmacyId) {
        List<Order> list = orderService.getAllOrderByPharmacyId(pharmacyId);
        Order orderDetail = new Order();
        if (list.size() > 0) {
            Order order = list.get(0);
            if (order.getId() != null) {
                orderDetail = orderService.getOrderDetailById(order.getId());
            }
        }
        return orderDetail;
    }

    @RequestMapping(value = "/getOrderDetail/{path}/{id}", method = RequestMethod.GET)
    public ModelAndView getOrderDetail(@PathVariable("path") String path, @PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("selectedOrder", id);
        Order order = orderService.getOrderDetailById(id);
        String email = sessionBean.getUserName();
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
        if (path.equalsIgnoreCase("accountsummary")) {
            modelAndView.setViewName("portal/accountsummary");
            modelAndView.addObject("path", "accountsummary");
            Order account = consumerRegistrationService.getAccountOverView(pharmacyUser.getPharmacy().getId());
        } else {
            modelAndView.addObject("path", "prescription");
            modelAndView.setViewName("portal/prescription");
        }
        pageContents(modelAndView);
        modelAndView.addObject("orderList", orderService.getAllOrderByPharmacyId(pharmacyUser.getPharmacy().getId()));
        dropDownOrderList(modelAndView, order);
        modelAndView.addObject("order", order);
        modelAndView.addObject("selectedOrderNo", id);
        return modelAndView;
    }

    private List<OrderStatus> filteredStatusList(Order order) {
        List<OrderStatus> statusList = orderService.getOrderStatusList();
        if (order.getId() != null) {
            for (Iterator<OrderStatus> iterator = statusList.iterator(); iterator.hasNext();) {
                OrderStatus status = iterator.next();
                if ((order.getOrderStatus().getId() == 2 && (status.getId() == 1 || status.getId() == 2 || status.getId() == 4 || status.getId() == 6 || status.getId() == 7))
                        || (order.getOrderStatus().getId() == 3 && (status.getId() == 1 || status.getId() == 2 || status.getId() == 3 || status.getId() == 5 || status.getId() == 6 || status.getId() == 7))) {
                    iterator.remove();
                }
            }
        }
        return statusList;
    }

    @RequestMapping(value = "/acceptall", method = RequestMethod.POST)
    public ModelAndView acceptOrderHandler(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String data = request.getParameter("orderToBeAccepted");
        orderService.acceptOrderList(data, sessionBean.getPharmacy().getId(), sessionBean.getUserId());
        return new ModelAndView("redirect:/PharmacyPortal/home");
    }

    @RequestMapping(value = "/ordercareer/{orderId}", produces = "application/json")
    public @ResponseBody
    String getOrderCarrierHandler(@PathVariable String orderId) throws Exception {
        return orderService.getOrderCareerByOrderId(orderId);
    }

    @RequestMapping(value = "/update/{orderId}/{status}", method = RequestMethod.GET)
    public ModelAndView updateOrderStatusHandler(@PathVariable String orderId, @PathVariable String status, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(sessionBean.getUserName());
        orderService.updateOrderStatus(orderId, Integer.parseInt(status), pharmacyUser.getPharmacy().getId(), pharmacyUser.getId());
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("order.status.successfully", null, null));
        return new ModelAndView("redirect:/PharmacyPortal/prescription");
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public ModelAndView ingredients() {
        ModelAndView modelAndView = new ModelAndView("portal/ingredients");
        modelAndView.addObject("drugBrand", new DrugBrand());
        pageContents(modelAndView);
        modelAndView.addObject("emptyList", "No data available.");
        modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(10));
        modelAndView.addObject("totalRecords", orderService.getRedemptionIngredients());
        return modelAndView;
    }

    @RequestMapping(value = "/ingredients/{index}", method = RequestMethod.GET)
    public ModelAndView viewMoreIngredients(@PathVariable("index") Integer index) {
        ModelAndView modelAndView = new ModelAndView("portal/ingredients");
        modelAndView.addObject("drugBrand", new DrugBrand());
        pageContents(modelAndView);
        if (index < 10) {
            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(10));
        } else {
            index = index + 10;
            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(index));
        }
        modelAndView.addObject("totalRecords", orderService.getRedemptionIngredients());
        return modelAndView;
    }

    @RequestMapping(value = "/ingredientSearch", method = RequestMethod.POST)
    public ModelAndView searchIngredients(@ModelAttribute DrugBrand drugBrand) {
        ModelAndView modelAndView = new ModelAndView("portal/ingredients");
        modelAndView.addObject("drugBrand", drugBrand);
        pageContents(modelAndView);
        modelAndView.addObject("totalRecords", orderService.getRedemptionIngredients());
//        if (drugBrand.getSearch().isEmpty()) {
//            modelAndView.addObject("redemptionIngredient", orderService.getRedemptionIngredients(10));
//            return modelAndView;
//        }
        modelAndView.addObject("emptyList", "No ingredient found.");
        //modelAndView.addObject("redemptionIngredient", orderService.getIngredientsList(drugBrand.getSearch()));
        return modelAndView;
    }

    @RequestMapping(value = {"/home", "/pharmacyhome"}, method = RequestMethod.GET)
    public ModelAndView listUser(@ModelAttribute Order order, HttpServletRequest request) throws ParseException, Exception {
        ModelAndView modelAndView = new ModelAndView("portal/pharmacyhome");
        String email = sessionBean.getUserName();
        PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
        modelAndView.addObject("recentOrderlist", orderService.getRecentOrder(pharmacyUser.getPharmacy().getId()));
        modelAndView.addObject("trackingOrderlist", orderService.getPrescriptionTrackingOrder(pharmacyUser.getPharmacy().getId()));
        modelAndView.addObject("order", order);
        modelAndView.addObject("path", "prescription");
        pageContents(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    public ModelAndView editProfileHandler(@ModelAttribute Pharmacy pharmacy, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/PharmacyPortal/profile");
        try {
            consumerRegistrationValidator.validate(pharmacy, result);
            if (result.hasErrors()) {
                modelAndView.setViewName("/portal/profile_edit");
                String email = sessionBean.getUserName();
                PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(email);
                Pharmacy pharmacyDetail = pharmacyUser.getPharmacy();
                pharmacyDetail.setPharmacyUserList(new ArrayList(consumerRegistrationService.getPharmacyUserList(pharmacyDetail.getId())));
                pharmacyDetail.setPharmacyFacilityOperationList(new ArrayList(consumerRegistrationService.getPharmacyFacilityOperations(pharmacyDetail.getId())));
                modelAndView.addObject("pharmacy", pharmacyDetail);
                List<String> facilityHoursList = new ArrayList<>();
                for (int count = 0; count < 25; count++) {
                    if (count < 10) {
                        facilityHoursList.add("0" + count);
                    } else {
                        facilityHoursList.add(String.valueOf(count));
                    }
                }
                modelAndView.addObject("facilityHoursList", facilityHoursList);
                modelAndView.addObject("pharmacy", pharmacy);
                pageContents(modelAndView);
                return modelAndView;
            }

            //Remove extra users
            if (pharmacy.getPharmacyUserList().size() == 2) {
                PharmacyUser pharmacyUser = pharmacy.getPharmacyUserList().get(1);
                if ((pharmacyUser.getFullName() == null || pharmacyUser.getFullName().isEmpty())
                        && (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty())
                        && (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty())) {

                    pharmacy.getPharmacyUserList().remove(pharmacyUser);
                }
            }

            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("consumer.updated.success", null, null));
            consumerRegistrationService.updateConsumerDetail(pharmacy);

        } catch (Exception e) {
            logger.error("ConsumerRegistrationController -> editProfileHandler", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        request.getSession().removeAttribute("sessionBeanPortal");
        request.getSession().invalidate();
        redirectAttributes.addFlashAttribute("message", "You have logged out successfully.");
        return new ModelAndView("redirect:/PharmacyPortal/login");
    }

    private void pageContents(ModelAndView modelAndView) {
        modelAndView.addObject("pageContents", consumerPortalService.getCMSPageContents());
    }

    @RequestMapping(value = "/prescription/{id}", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView saveOrder(@ModelAttribute Order order, @PathVariable String id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/prescription");
        managePrescription(request, modelAndView, id);
        if (order.getOrderStatus().getId() == 0) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("order.status.error", null, null));
            return modelAndView;
        }
        if (order.getOrderTrackingNo() != null && order.getOrderTrackingNo().trim().isEmpty()) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("order.orderTrackNo.required", null, null));
            modelAndView.addObject("selectedOrderstatus", order.getOrderStatus().getId());
            return modelAndView;
        }
        manageOrderHistory(order);
        if (order.getOrderStatus().getId() == 4) {
            if (order.getOrderCarriers() != null && order.getOrderCarriers().getId() == 0) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("order.orderscarriers.error", null, null));
                modelAndView.addObject("selectedOrderstatus", order.getOrderStatus().getId());
                modelAndView.addObject("orderTrackingNo", order.getOrderTrackingNo());
                return modelAndView;
            }
            boolean isDuplicate = orderService.isTrackingNoDuplicate(order.getOrderTrackingNo(), order.getId());
            if (isDuplicate) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("field.OrderTrackNo.duplicate", null, null));
                modelAndView.addObject("selectedOrderstatus", order.getOrderStatus().getId());
                modelAndView.addObject("orderTrackingNo", order.getOrderTrackingNo());
                return modelAndView;
            }
        }
        boolean isSaved = orderService.saveOrderDetail(order, sessionBean.getUserId());
        if (isSaved) {
            if (order.getOrderStatus().getId() == 3 || order.getOrderStatus().getId() == 4 || order.getOrderStatus().getId() == 5) {
                return new ModelAndView("redirect:/order/prescription/" + id);
            }
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("order.field.save", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
        }
        return new ModelAndView("redirect:/PharmacyPortal/prescription");
    }

    private List<OrderHistory> manageOrderHistory(Order order) {
        List<OrderHistory> orderHistoryremove = new ArrayList<>();
        if (order.getOrderHistory() != null) {
            for (Iterator<OrderHistory> i = order.getOrderHistory().iterator(); i.hasNext();) {
                OrderHistory orderHistory = i.next();
                if (orderHistory.getComments() == null || orderHistory.getComments().isEmpty()) {
                    orderHistoryremove.add(orderHistory);
                    i.remove();
                } else {
                    orderHistory.setOrder(order);
                }
            }
        }
        return orderHistoryremove;
    }

    @RequestMapping(value = "/orderingredients/{transactionNo}", produces = "application/json")
    public @ResponseBody
    String getIngredientsHandler(@PathVariable String transactionNo) throws Exception {
        return consumerRegistrationService.getIntgredients(transactionNo);
    }

    @RequestMapping(value = "/orderDetail/{id}", produces = "application/json")
    public @ResponseBody
    String getOrderDetail(@PathVariable String id) throws Exception {
        Order dbOrder = orderService.getOrderDetailById(id);
        Order order = new Order();
        order.setFirstName(dbOrder.getFirstName());
        order.setOrderHistory(dbOrder.getOrderHistory());
        List<Order> list = new ArrayList<>();
        list.add(dbOrder);
        String result = new ObjectMapper().writeValueAsString(list);
        return result;
    }

    @RequestMapping(value = "/comments/{orderId}", produces = "application/json")
    public @ResponseBody
    String getOrderCommentsHandler(@PathVariable String orderId) throws Exception {
        return orderService.getOrderCommentsHandler(orderId);
    }

    //Added by GShah Afridi
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView viewPatientProfileInfo(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("selectedRxTransfer");
        List<TransferRequest> list = consumerRegistrationService.getSelectedTransferRx(id);
        modelAndView.addObject("selectedTransferRxList", consumerRegistrationService.getSelectedTransferRx(id));

        return modelAndView;
    }

    @RequestMapping(value = "/pharmacyNotifiction", method = RequestMethod.POST)
    public @ResponseBody
    String sendPharmacyNotification(
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile,
            @RequestParam(value = "patientId", required = false) Integer patientId,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "isCriticalPT", required = false) Integer isCriticalPT,
            HttpServletRequest request) {

        String str_response = "";

        try {
//            Order order = new Order();
//            order.setId(orderId);
            PatientProfile patient = new PatientProfile();
            patient.setPatientProfileSeqNo(patientId);

            
           
            str_response = JsonResponseComposer.composeSuccessResponse();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PortalController -> sendPharmacyNotification", e);
            str_response = JsonResponseComposer.composeFailureResponse("Msg not save");
            return str_response;
        }
        logger.info("json request receive for registeration page END ::: " + str_response);

        return str_response;
    }

    @RequestMapping(value = "/sendNotifyAdmin", method = RequestMethod.POST)
    public @ResponseBody
    String sendNotifyAdmin(@RequestBody String jsonRequest, HttpServletRequest request) {
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode nameNode = rootNode.path("notifyAdmin");
            String notifyAdminMsg = nameNode.asText();
            NotifyAdmin notifyAdmin = new NotifyAdmin();
            notifyAdmin.setMessageText(notifyAdminMsg);
            notifyAdmin.setCreatedBy(sessionBean.getUserId());
            notifyAdmin.setCreatedOn(new Date());

            if (patientProfileService.save(notifyAdmin)) {
                str_response = JsonResponseComposer.composeResponse("Msg has been send successfully.", Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            } else {
                str_response = JsonResponseComposer.composeFailureResponse("Msg not send");
            }
        } catch (Exception e) {
            logger.error("PortalController:: sendNotifyAdmin", e);
        }
        return str_response;
    }

    @RequestMapping(value = "/uploadDrugDetail", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String uploadDrugDetail(@RequestParam("drugDetailId") Long drugDetailId, @RequestParam("drugGCN") Long drugGCN, @RequestParam("drugGPI") Long drugGPI,
            @RequestParam("demandBrand") String demandBrand, @RequestParam("inStock") String inStock, @RequestParam("brandName") String brandName,
            @RequestParam("genericName") String genericName, @RequestParam("strength") String strength, @RequestParam("formDesc") String formDesc,
            @RequestParam("packingDesc") String packingDesc, @RequestParam("defQty") Integer defQty, @RequestParam("packageSizeValues") String packageSizeValues, @RequestParam("therapeuticCategory") String therapeuticCategory, @RequestParam(value = "gnn", required = false) String gnn,
            @RequestParam("rxExpire") String rxExpires, @RequestParam("requiresHandDelivery") String reqHandDelivery,
            @RequestParam("marginPercent") Float marginPercent, @RequestParam("basePrice") Float basePrice,
            @RequestParam("additionalFee") Float additionalFee, @RequestParam("mktSurcharge") Float mktSurcharge,
            @RequestParam(value = "pdfDocFile", required = false) MultipartFile pdfDocFile, @RequestParam(value = "drugDocFile", required = false) MultipartFile drugDocFile, @RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
            HttpServletRequest httpRequest) {
        String response = null;
        try {
            DrugDetailDTO drugDetailDTO = populateDrugDetail(drugDetailId, drugGCN, drugGPI, demandBrand, inStock, brandName, genericName, strength, formDesc, packingDesc, defQty, packageSizeValues, therapeuticCategory, gnn, rxExpires, reqHandDelivery, marginPercent, basePrice, additionalFee, mktSurcharge, pdfDocFile, drugDocFile, imgFile);
            boolean isUpdate = consumerRegistrationService.updateDrugDetail(drugDetailDTO, sessionBean.getUserId());
            logger.info("isUpdate:: " + isUpdate);
            if (isUpdate) {
                response = JsonResponseComposer.composeResponse("Record has been updated successfully.", Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            } else {
                response = JsonResponseComposer.composeResponse("Problem to update changes.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }
        } catch (Exception e) {
            logger.error("Exception:: uploadDrugDetail", e);
            response = JsonResponseComposer.composeResponse("Problem to update changes.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }

    public DrugDetailDTO populateDrugDetail(Long drugDetailId, Long drugGCN, Long drugGPI, String demandBrand, String inStock, String brandName, String genericName, String strength, String formDesc, String packingDesc, Integer defQty, String packageSizeValues, String therapeuticCategory, String gnn, String rxExpires, String reqHandDelivery, Float marginPercent, Float basePrice, Float additionalFee, Float mktSurcharge, MultipartFile pdfDocFile, MultipartFile drugDocFile, MultipartFile imgFile) throws IOException {
        DrugDetailDTO drugDetailDTO = new DrugDetailDTO();
        drugDetailDTO.setDrugDetailId(drugDetailId);
        drugDetailDTO.setDrugGCN(drugGCN);
        drugDetailDTO.setDrugGPI(drugGPI);
        drugDetailDTO.setDemandBrand(demandBrand);
        drugDetailDTO.setInStock(inStock);
        drugDetailDTO.setBrandName(brandName);
        drugDetailDTO.setGenericName(genericName);
        drugDetailDTO.setStrength(strength);
        drugDetailDTO.setFormDesc(formDesc);
        drugDetailDTO.setPackingDesc(packingDesc);
        drugDetailDTO.setDefQty(defQty);
        drugDetailDTO.setPackageSizeValues(packageSizeValues);
        drugDetailDTO.setTherapy(therapeuticCategory);
        drugDetailDTO.setGnn(gnn);
        drugDetailDTO.setRxExpire(rxExpires);
        drugDetailDTO.setRequiresHandDelivery(reqHandDelivery);
        drugDetailDTO.setMarginPercent(marginPercent);
        drugDetailDTO.setBasePrice(basePrice);
        drugDetailDTO.setAdditionalFee(additionalFee);
        drugDetailDTO.setMktSurcharge(mktSurcharge);
        String path = "", destPath = "", fileName = "";
        if (pdfDocFile != null && (!pdfDocFile.isEmpty())) {
            path = CommonUtil.getDocumentPath("1");
            destPath = CommonUtil.getDocumentDestinationPath("1");
            fileName = Constants.INSURANCE_CARD_URL + path + drugGCN + ".pdf";
            FileCopyUtils.copy(pdfDocFile.getBytes(), new File(destPath + drugGCN + ".pdf"));
            CommonUtil.executeCommand(Constants.COMMAND + path);
            drugDetailDTO.setPdfDocUrl(fileName);
        }
        if (drugDocFile != null && (!drugDocFile.isEmpty())) {
            path = CommonUtil.getDocumentPath("2");
            destPath = CommonUtil.getDocumentDestinationPath("2");
            fileName = Constants.INSURANCE_CARD_URL + path + drugGCN + ".pdf";
            FileCopyUtils.copy(drugDocFile.getBytes(), new File(destPath + drugGCN + ".pdf"));
            CommonUtil.executeCommand(Constants.COMMAND + path);
            drugDetailDTO.setDrugDocUrl(fileName);
        }
        if (imgFile != null && (!imgFile.isEmpty())) {
            path = CommonUtil.getDocumentPath("3");
            destPath = CommonUtil.getDocumentDestinationPath("3");
            fileName = Constants.INSURANCE_CARD_URL + path + drugGCN + ".png";
            FileCopyUtils.copy(imgFile.getBytes(), new File(destPath + drugGCN + ".png"));
            CommonUtil.executeCommand(Constants.COMMAND + path);
            drugDetailDTO.setImage(fileName);
        }
        return drugDetailDTO;
    }

    @RequestMapping(value = "/addNewDrugDetail", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String addDrugDetail(@RequestParam("drugDetailId") Long drugDetailId, @RequestParam("drugGCN") Long drugGCN, @RequestParam("drugGPI") Long drugGPI,
            @RequestParam("demandBrand") String demandBrand, @RequestParam("inStock") String inStock, @RequestParam("brandName") String brandName,
            @RequestParam("genericName") String genericName, @RequestParam("strength") String strength, @RequestParam("formDesc") String formDesc,
            @RequestParam("packingDesc") String packingDesc, @RequestParam("defQty") Integer defQty, @RequestParam("packageSizeValues") String packageSizeValues,
            @RequestParam("therapeuticCategory") String therapeuticCategory, @RequestParam(value = "gnn", required = false) String gnn,
            @RequestParam("rxExpire") String rxExpires, @RequestParam("requiresHandDelivery") String reqHandDelivery,
            @RequestParam("marginPercent") Float marginPercent, @RequestParam("basePrice") Float basePrice,
            @RequestParam("additionalFee") Float additionalFee, @RequestParam("mktSurcharge") Float mktSurcharge,
            @RequestParam(value = "pdfDocFile", required = false) MultipartFile pdfDocFile, @RequestParam(value = "drugDocFile", required = false) MultipartFile drugDocFile, @RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
            HttpServletRequest httpRequest) {
        String response = null;
        try {
            DrugDetailDTO drugDetailDTO = populateDrugDetail(drugDetailId, drugGCN, drugGPI, demandBrand, inStock, brandName, genericName, strength, formDesc, packingDesc, defQty, packageSizeValues, therapeuticCategory, gnn, rxExpires, reqHandDelivery, marginPercent, basePrice, additionalFee, mktSurcharge, pdfDocFile, drugDocFile, imgFile);
            if (consumerRegistrationService.isDrugDetailExist(drugDetailDTO)) {
                return JsonResponseComposer.composeResponse("Record already exists.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }
            boolean isUpdate = consumerRegistrationService.addDrugDetail(drugDetailDTO, sessionBean.getUserId());
            logger.info("isUpdate:: " + isUpdate);
            if (isUpdate) {
                response = JsonResponseComposer.composeResponse("Record has been saved successfully.", Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            } else {
                response = JsonResponseComposer.composeResponse("Problem to save changes.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }
        } catch (Exception e) {
            logger.error("Exception:: addDrugDetail", e);
            response = JsonResponseComposer.composeResponse("Problem to save changes.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }

    @RequestMapping(value = "/calculateDrugDetailPrice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String calculateDrugDetailPrice(@RequestParam("defQty") Integer defQty,
            @RequestParam("marginPercent") Float marginPercent, @RequestParam("basePrice") Float basePrice,
            @RequestParam("additionalFee") Float additionalFee, @RequestParam("mktSurcharge") Float mktSurcharge,
            HttpServletRequest httpRequest) {
        String response = null;
        try {
            DrugDetail drugDetail = new DrugDetail();
            drugDetail.setDefQty(defQty);
            drugDetail.setMarginPercent(marginPercent);
            drugDetail.setBasePrice(basePrice);
            drugDetail.setAdditionalFee(additionalFee);
            drugDetail.setMktSurcharge(mktSurcharge);
            DrugDetailDTO drugDetailDTO = new DrugDetailDTO();
            consumerPortalService.calculateDrugBrandPrice(drugDetail, drugDetailDTO, defQty);
            response = new ObjectMapper().writeValueAsString(drugDetailDTO);
        } catch (NumberFormatException | IOException e) {
            logger.error("Exception:: calculateDrugDetailPrice", e);
            response = JsonResponseComposer.composeResponse("Server error.", Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }

    @RequestMapping(value = "/careGiverList", method = RequestMethod.GET)
    public ModelAndView getCareGiverList(HttpServletRequest request)  {
        ModelAndView modelAndView = new ModelAndView("portal/caregiverlist");
        request.getSession().setAttribute("Title", "Care Giver List");
        return modelAndView;
    }
       @RequestMapping(value = "/patientActivities", method = RequestMethod.GET)
     public ModelAndView getpatientActivitiesList(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("portal/patientActivities");
        request.getSession().setAttribute("Title", "Patient Activities");
//        String id = request.getSession().getId();
//       int profileId = 1;
        modelAndView.addObject("patientActivitiesList", patientProfileService.getPatientActivitiesList());
        return modelAndView;
    }
     
    @RequestMapping(value = "/bloodPressureDetails", method = RequestMethod.GET)
    public ModelAndView getBloodPressureDetails(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("portal/patientActivities");
        request.getSession().setAttribute("Title", "Patient Activities");
//        String id = request.getSession().getId();
//       int profileId = 1;
        modelAndView.addObject("patientBloodPressureList", patientProfileService.getBloodPressureList(35));
        return modelAndView;
    }

    public static SessionBean populateSessionBean(PharmacyUser userDetail, Date currentDate, PermissionService permissionService) throws SQLException {
        SessionBean sessionBean = new SessionBean();
        String userName = userDetail.getUserName();
        String encryptedPassword = RedemptionUtil.MD5(userDetail.getPassword());
        String firstName = userDetail.getFirstName();
        String lastName = userDetail.getLastName();
        sessionBean.setUserNameDB(userName);
        sessionBean.setUserName(firstName + " " + lastName);
        sessionBean.setUserPassword(encryptedPassword);
        sessionBean.setUserId(userDetail.getId());
        sessionBean.setCurrentDate(currentDate);
        sessionBean.setIsAdmin(userDetail.getRole().equalsIgnoreCase("admin"));
//        sessionBean.setParentId(userDetail.getId());
        sessionBean.setRoleId(userDetail.getRoleTypes() != null ? userDetail.getRoleTypes().getRoleId() : Constants.ROLE_TYPES.PHARMACY_TECHNICIAN);
        sessionBean.setRole(userDetail.getRoleTypes() != null ? userDetail.getRoleTypes().getRoleTitle() : "Pharmacy Technician");
//        sessionBean.setTransactionNumber(permissionService.getDailyRedemption());
//        sessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");

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

        //get permission by Pharmacy User id
        List<AppResourceObjectPermissions> listPermission = permissionService.getAppResourcesByPharmacyUserId(userDetail.getId());
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/newMemberRequest1")
    public ModelAndView listOfNewMemberRequest1(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
//        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
//        modelAndView.addObject("type", "PharmacyPortal");
//        modelAndView.addObject("requestUrl", "newMemberRequest-listing");
//        modelAndView.addObject("col", PharmacyQueueUtil.newMemberRequestTableData());
//        populatePage1DataNew(request, modelAndView);
//        //modelAndView.addObject("tab", "tab1");
//        if (AppUtil.getSafeStr(search, "").length() == 0) {
//            search = AppUtil.getSafeStr(request.getParameter("searchTitle_1"), "");
//        }
//        populateSearchValue(modelAndView, request, search,"tab1");
//        modelAndView.addObject("filter", "Pending");
        ModelAndView modelAndView = new ModelAndView("pharmacyQueueIstPageNew");//"pharmacyQueuePage1");
        this.populateModelForPage1(request, search, "Pending", "tab1", modelAndView);
        return modelAndView;

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/newMemberRequest")
    public ModelAndView listOfNewMemberRequest(@RequestParam(value = "search", required = false) String search,
            HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
//        modelAndView.addObject("type", "PharmacyPortal");
//        modelAndView.addObject("requestUrl", "newMemberRequest-listing");
//        modelAndView.addObject("col", PharmacyQueueUtil.newMemberRequestTableData());
        Map<String, Integer> map = this.populateStatusWiseCount(request);
        populatePage1DataNew(request, modelAndView);
        if (map != null && map.get("19") != null && map.get("19") > 0) {
            return new ModelAndView("redirect:/PharmacyPortal/responseReceived");
        } else {
            return new ModelAndView("redirect:/PharmacyPortal/newMemberRequest1");
        }
    }

    private void populateSearchValue(ModelAndView modelAndView, HttpServletRequest request, String search, String selectedTab) {
        if (CommonUtil.isNullOrEmpty(search)) {
            search = request.getParameter("sSearch_1");
        }
        modelAndView.addObject("search", search);
        modelAndView.addObject("tab", selectedTab);
        sessionBean.setSelectedTab(selectedTab);
        request.getSession().setAttribute("sessionBeanPortal", sessionBean);
    }

    @RequestMapping(value = "/interpretedImages")
    public ModelAndView listOfInterpretedImages(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("col", PharmacyQueueUtil.interpretedImagesTableData());
        populatePage1DataNew(request, modelAndView);
        //modelAndView.addObject("tab", "tab2");
        populateSearchValue(modelAndView, request, search, "tab2");
        modelAndView.addObject("filter", "Pending Review");
        return modelAndView;
    }

    @RequestMapping(value = "/waitingPtResponse")
    public ModelAndView listOfWaitingPtResponse(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("col", PharmacyQueueUtil.waitingPtResponseTableData());
        populatePage1DataNew(request, modelAndView);
        //modelAndView.addObject("tab", "tab3");
        populateSearchValue(modelAndView, request, search, "tab3");
        modelAndView.addObject("filter", "Waiting Pt Response");
        return modelAndView;
    }

    @RequestMapping(value = "/responseReceived")
    public ModelAndView listOfResponseReceived(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueueIstPageNew");//"pharmacyQueuePage1");
        this.populateModelForPage1(request, search, "Response Received", "tab7", modelAndView);
        return modelAndView;
    }

    private Map populateColumnsMap() {
        Map map = new HashMap();
        map.put("tab1", PharmacyQueueUtil.newMemberRequestTableData());
        map.put("tab2", PharmacyQueueUtil.interpretedImagesTableData());
        map.put("tab3", PharmacyQueueUtil.waitingPtResponseTableData());
        map.put("tab4", PharmacyQueueUtil.processRequestTableData());
        map.put("tab5", PharmacyQueueUtil.shippingDeliveryTableData());
        map.put("tab6", PharmacyQueueUtil.cancelledRequestTableData());
        map.put("tab7", PharmacyQueueUtil.responseReceivedTableData());

        return map;
    }

    private void populateModelForPage1(HttpServletRequest request,
            @RequestParam(value = "search", required = false) String search, String filter, String tab,
            ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("colMap", this.populateColumnsMap());
        modelAndView.addObject("filter", filter);
        populatePage1Info(request, modelAndView);
        populateSearchValue(modelAndView, request, search, tab);
    }

    ////////////////////////////////////////////////////////////////////////////////
    private void populatePage1Info(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        String tab1 = "class=\"active\"", tab2 = "", tab3 = "", tab4 = "", recordDiv1 = "active", recordDiv2 = "", recordDiv3 = "", recordDiv4 = "";//for assigning class to activated tab on home page
        int userId = getSessionUserId(request);
        PharmacyUser user = getPharmacyUser(request);

        modelAndView.addObject("tab1", tab1);
        modelAndView.addObject("tab2", tab2);
        modelAndView.addObject("tab3", tab3);
        modelAndView.addObject("tab4", tab4);
        modelAndView.addObject("recordDiv1", recordDiv1);
        modelAndView.addObject("recordDiv2", recordDiv2);
        modelAndView.addObject("recordDiv3", recordDiv3);
        modelAndView.addObject("recordDiv4", recordDiv4);
        modelAndView.addObject("loadingTime", PropertiesUtil.getProperty("NEXT_ORDER_LOADING"));
        System.out.println("GOING TO POPULATE FINANCIAL REPORT");
//        populateFinancialReportValuesInModel(modelAndView);
        System.out.println("POPULATING VIEW");
    }

    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/firstQueueInfo", produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String populateFirstQueueInformation(@RequestParam(value = "iDisplayStart", required = false) Integer iDisplayStart,
            @RequestParam(value = "iDisplayLength", required = false) Integer iDisplayLength,
            @RequestParam(value = "sEcho", required = false) Integer sEcho,
            HttpServletRequest request,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sourceName", required = false) String sourceName,
            @RequestParam(value = "sourceId", required = false) Integer sourceId,
            @RequestParam(value = "relating", required = false) String relating,
            @RequestParam(value = "search", required = false) String search) {

        int userId = getSessionUserId(request);
        String filteredUserResponse = "";
        try {
            //Search parameters
            String sSearch_1 = CommonUtil.isNotEmpty(request.getParameter("sSearch_1")) ? request.getParameter("sSearch_1") : search;
            //Sort parameters
            String sCol = request.getParameter("iSortCol_0");
            String sdir = request.getParameter("sSortDir_0");

            String colName;
            //Set Search criteria
            if (CommonUtil.isNotEmpty(sSearch_1)) {
                colName = request.getParameter("mDataProp_1");
                search = sSearch_1;
            } else {
                //Set sort criteria
                colName = request.getParameter("mDataProp_" + sCol);
                search = "";
            }
            PharmacyUser user = getPharmacyUser(request);
            //Get data from data source according to criteria
            //        DataTablesTO<TransferRxQueueDTO> dataTableTo=consumerRegistrationService.getFilteredRequestForQueue1(iDisplayStart, iDisplayLength, search, colName, sdir, filter, sEcho, userId, CommonUtil.getPharmacyId(user));        
            iDisplayStart = iDisplayStart != null ? iDisplayStart : 0;
            iDisplayLength = iDisplayLength != null ? iDisplayLength : 10;
            sEcho = sEcho != null ? sEcho : 1;
//            String tableData = consumerRegistrationService.getFilteredNewMemberRequest(iDisplayStart,iDisplayLength, search, colName, sdir, filter, sEcho, userId, CommonUtil.getPharmacyId(user));
//            List lst = consumerRegistrationService.getFilteredRequestForQueue1(iDisplayStart,iDisplayLength, search, colName, sdir, filter, sEcho, userId, CommonUtil.getPharmacyId(user));
            Map<String, Integer> map = this.consumerRegistrationService.populateCountForPage1(CommonUtil.getPharmacyId(user));
            int handDeliveryCount = 0;
            int pharmacyPickupCount = 0;
            int shippedCount = 0;
            if (map.containsKey("5")) {
                handDeliveryCount = map.get("5");
                map.remove("5");
            }
            if (map.containsKey("6")) {
                shippedCount = map.get("6");
            }
            if (map.containsKey("15")) {
                pharmacyPickupCount = map.get("15");
                map.remove(15);
            }
            // map.put("6", handDeliveryCount + shippedCount + pharmacyPickupCount);
            BigInteger shippedAndReadyToDeliveryCount = this.consumerRegistrationService.populateShippedAndReadyToDeliveryCountForPage1(CommonUtil.getPharmacyId(user));
            map.put("6", shippedAndReadyToDeliveryCount.intValue());
            map.remove(null);
            ViewDTO dto = new ViewDTO();
            dto.setFilter(filter);
            dto.setMapStatusCount(map);
            filteredUserResponse = new ObjectMapper().writeValueAsString(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredUserResponse;
    }

    ////////////////////////////////////////////////////////////////////////////////
    private String populateTableHeaders(String filter) {
        filter = AppUtil.getSafeStr(filter, "").toLowerCase();
        switch (filter) {
            case "response received":
                return PharmacyQueueUtil.responseReceivedTableData();

            case "filled":
                return PharmacyQueueUtil.processRequestTableData();

            case "shipped":
                return PharmacyQueueUtil.shippingDeliveryTableData();

            case "cancelled":
                return PharmacyQueueUtil.cancelledRequestTableData();

            case "waiting pt response":
                return PharmacyQueueUtil.waitingPtResponseTableData();

            case "pending review":
                return PharmacyQueueUtil.interpretedImagesTableData();
            case "pending":
                return PharmacyQueueUtil.newMemberRequestTableData();
        }
        return PharmacyQueueUtil.responseReceivedTableData();
    }
    ////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/processRequest")
    public ModelAndView listOfProcessRequest(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("col", PharmacyQueueUtil.processRequestTableData());
        populatePage1DataNew(request, modelAndView);
        //modelAndView.addObject("tab", "tab4");
        populateSearchValue(modelAndView, request, search, "tab4");
        modelAndView.addObject("filter", "Filled");
        return modelAndView;
    }

    @RequestMapping(value = "/shippingDelivery")
    public ModelAndView listOfShippingDelivery(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("col", PharmacyQueueUtil.shippingDeliveryTableData());
        populatePage1DataNew(request, modelAndView);
        //modelAndView.addObject("tab", "tab5");
        populateSearchValue(modelAndView, request, search, "tab5");
        modelAndView.addObject("filter", "Shipped");
        return modelAndView;
    }

    @RequestMapping(value = "/cancelledRequest")
    public ModelAndView listOfCancelledRequest(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage1");
        modelAndView.addObject("type", "PharmacyPortal");
        modelAndView.addObject("requestUrl", "listing");
        modelAndView.addObject("col", PharmacyQueueUtil.cancelledRequestTableData());
        populatePage1DataNew(request, modelAndView);
        // modelAndView.addObject("tab", "tab6");
        populateSearchValue(modelAndView, request, search, "tab6");
        modelAndView.addObject("filter", "Cancelled");
        return modelAndView;
    }

    @RequestMapping(value = "/listing", produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String filterRecordListing(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho,
            HttpServletRequest request,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sourceName", required = false) String sourceName,
            @RequestParam(value = "sourceId", required = false) Integer sourceId,
            @RequestParam(value = "relating", required = false) String relating,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "mbrReq", required = false) String mbrReq,
            @RequestParam(value = "deliveryPref", required = false) String deliveryPref,
            @RequestParam(value = "rxStatus", required = false) String rxStatus,
            @RequestParam(value = "searchField", required = false) String searchField,
            @RequestParam(value = "searchValue", required = false) String searchValue) throws Exception {
        int userId = getSessionUserId(request);
        String filteredUserResponse;

        //Search parameters
        String sSearch_1 = CommonUtil.isNotEmpty(request.getParameter("sSearch_1")) ? request.getParameter("sSearch_1") : search;
        //Sort parameters
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");

        String colName;
        //Set Search criteria
        if (CommonUtil.isNotEmpty(sSearch_1)) {
            colName = request.getParameter("mDataProp_1");
            search = sSearch_1;
        } else {
            //Set sort criteria
            colName = request.getParameter("mDataProp_" + sCol);
            search = "";
        }
        PharmacyUser user = getPharmacyUser(request);
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setMbrReqType(mbrReq);
        searchDTO.setDeliveryPref(deliveryPref);
        searchDTO.setOrderStatus(rxStatus);
        searchDTO.setSearchField(searchField);
        searchDTO.setSearchValue(searchValue);
        //Get data from data source according to criteria
        if (AppUtil.getSafeStr(filter, "").equalsIgnoreCase("Ready to Deliver Rx") || AppUtil.getSafeStr(filter, "").equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED)) {
            filteredUserResponse = consumerRegistrationService.getFilteredReadyToShipRequest(iDisplayStart, iDisplayLength, search, colName, sdir, filter, sEcho, userId, CommonUtil.getPharmacyId(user), searchDTO, sessionBean);
        } else {
            filteredUserResponse = consumerRegistrationService.getFilteredNewMemberRequest(iDisplayStart, iDisplayLength, search, colName, sdir, filter, sEcho, userId, CommonUtil.getPharmacyId(user), searchDTO, sessionBean);
        }
        request.getSession().setAttribute("sessionBeanPortal", sessionBean);
        return filteredUserResponse;
    }

    @RequestMapping(value = "/postOrderToIOMNI", method = RequestMethod.POST)
    public @ResponseBody
    String postOrderToIOMNI(@RequestBody String jsonRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode totalPaymentNode = rootNode.path("totalPayment");
            String totalPayment = totalPaymentNode.asText();
            consumerRegistrationService.postOrderToIOMNI(orderId, totalPayment);
        } catch (Exception e) {
        }

        return "";
    }

    @RequestMapping(value = "/getQuestionAnswer", method = RequestMethod.POST)
    public @ResponseBody
    String getQuestionAnswer(@RequestBody String jsonRequest) {
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("questionId");
            Integer questionId = orderIdNode.asInt();

            json = patientProfileService.getQuestionAnswerById(questionId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    @RequestMapping(value = "/processSameDayShippingRxOrders", method = RequestMethod.POST)
    public @ResponseBody
    String processSameDayShippingRxOrders(@RequestBody String jsonRequest) {
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderIds");
            String orderIds = orderIdNode.asText();

            JsonNode vieworders = rootNode.path("vieworders");
            int vieworder = vieworders.asInt();

            if (CommonUtil.isNullOrEmpty(orderIds)) {
                return json;
            }

            if (CommonUtil.isNotEmpty(orderIds)) {
                if (vieworder == 0) {
                    json = patientProfileService.processSameDayShippingRxOrders(orderIds);
                } else {
                    json = consumerRegistrationService.populateSameDayShippingRxOrders(orderIds);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception# processSameDayShippingRxOrders# ", e);
        }
        return json;
    }
}
