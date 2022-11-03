package com.ssa.cms.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssa.cms.bean.OrderSearch;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.CMSService;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.DrugBrandDTO;
import com.ssa.cms.dto.NotificationPharmacyDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.dto.PharmacyDTO;
import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.model.CMSDocuments;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.DeliveryPreferences;
import com.ssa.cms.common.JsonResponse;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.PatientDependantDTO;
import com.ssa.cms.model.MultiRx;
import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderStatus;
import com.ssa.cms.model.PatientDeliveryAddress;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyLookup;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.SupportModel;
import com.ssa.cms.model.WidgetLog;
import com.ssa.cms.service.ConsumerPortalService;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.service.NotificationPharmacyService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EmailSenderUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.ssa.cms.util.PropertiesUtil;
import com.ssa.cms.validator.ConsumerRegistrationValidator;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
 * @author shussain
 */
@Controller
@RequestMapping(value = "/ConsumerPortal")
public class PortalController {

    OrderSearch orderSearch;
    private Pattern pattern;
    private Matcher matcher;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ConsumerPortalService consumerPortalService;
    @Autowired
    private CMSService cMSService;
    SessionBean sessionBean;
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;
    @Autowired
    private ConsumerRegistrationValidator consumerRegistrationValidator;
    @Autowired
    NotificationPharmacyService notificationPharmacyService;
    @Autowired
    private PermissionService permissionService;

    private final Log logger = LogFactory.getLog(PortalController.class);

    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private OrderService orderService;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.USA_DATE_FORMATE);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public ModelAndView viewPage() {
        ModelAndView modelAndView = new ModelAndView("portal/aboutUs");
        populateLoginPageData(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
    public ModelAndView privacyPolicy() {
        ModelAndView modelAndView = new ModelAndView("portal/privacypolicey");
        pageContents(modelAndView);
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("portal/index");
        pageContents(modelAndView);
        modelAndView.addObject("supportModel", new SupportModel());
        return modelAndView;
    }

    private void pageContents(ModelAndView modelAndView) {
        modelAndView.addObject("pageContents", consumerPortalService.getCMSPageContents());
    }

    /*@RequestMapping(value = "/transferRxList/{transferDetailId}/{requestId}/{orderId}/{patientId}")*/
    @RequestMapping(value = "/transferRxList/{orderId}/{patientId}")
    public ModelAndView link(@PathVariable("orderId") String oid, @PathVariable("patientId") int patientId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        request.getSession().setAttribute("Title", "PHARMACY QUEUE"); //"Pending Transfer RX Queue");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        //Save last logged on date
        if (pharmacyUser != null && pharmacyUser.getId() > 0) {
            consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());

            SessionBean newSessionBean = new SessionBean();
            newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
            newSessionBean.setUserNameDB("PharmacyPortal");

            newSessionBean.setUserName(pharmacyUser.getEmail());
            newSessionBean.setUserId(pharmacyUser.getId());
            newSessionBean.setRole(pharmacyUser.getRole());
            newSessionBean.setCurrentDate(new Date());
            Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
            //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());

            request.getSession().setAttribute("PharmacyTitle", par.getTitle());
            request.getSession().setAttribute("Address", par.getAddress1());
            request.getSession().setAttribute("Phone", par.getPhone());
            request.getSession().setAttribute("Fax", par.getPhone());
            request.getSession().setAttribute("Email", par.getEmail());

            request.getSession().setAttribute("orderId", oid + "");
            request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
            request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
            String currentUserName = "";
            currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

            newSessionBean.setCurrentUserName(currentUserName);
            try {
                newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
            request.getSession().setAttribute("sessionBeanPortal", newSessionBean);

        }
        //modelAndView.setViewName("transferRxQueue");
        //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
        request.getSession().setMaxInactiveInterval(900000);
        pageContents(modelAndView);
        try {
            //consumerRegistrationService.setOrderStatusWithId("Opened",oid);
            int userId = 0;
            userId = getPharmacyUserId(request, userId);
            consumerRegistrationService.updateOrderStatusWithIdAndUser("Opened", oid, userId);
        } catch (Exception e1) {
            logger.info("transferRxList setOrderStatusWithId Message is: " + e1);
            e1.printStackTrace();
        }
        request.getSession().setAttribute("recordOpened", "true");

        List<TransferRxQueueDTO> transferDList = new ArrayList<>();

        try {
            if (patientId > 0 && (!CommonUtil.isNullOrEmpty(oid) && !oid.trim().equals("0"))) {
                //transferDList = consumerRegistrationService.getTranferRxQueueByPatientProfileOrder(patientId, oid);
                String status = AppUtil.getSafeStr(request.getParameter("status"), "");
                transferDList = consumerRegistrationService.getTranferRxQueueByPatientProfileOrder(patientId, oid,
                        status.equalsIgnoreCase("Processing") || status.equalsIgnoreCase("Pending Pharmacy Reply")
                        || status.equalsIgnoreCase("Delayed / Rx On Order")
                        || status.equalsIgnoreCase("Courier Delivery") ? "Other"
                        : AppUtil.getSafeStr(request.getParameter("status"), ""));
                request.getSession().setAttribute("count", transferDList != null ? transferDList.size() : 0);//consumerRegistrationService.getTransferRListCount(patientId, oid));
            }

            request.getSession().setAttribute("orderId", oid + "");
        } catch (Exception e) {
            logger.info("transferRxList Message is: " + e);
        }

        modelAndView.addObject("TransferRxQueueDTO", transferDList);
        /**
         * patient info
         */
        //  modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(patientId));
        CommonUtil.loadPageData(modelAndView, patientId, oid, patientProfileService);
        return modelAndView;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/patientHistory/{patientId}")
    public ModelAndView linkPatientHistory(@PathVariable("patientId") int patientId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/patientProfileHistory2");
        request.getSession().setAttribute("Title", "PHARMACY QUEUE"); //"Pending Transfer RX Queue");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        //Save last logged on date
        if (pharmacyUser != null && pharmacyUser.getId() > 0) {
            consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());

            SessionBean newSessionBean = new SessionBean();
            newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
            newSessionBean.setUserNameDB("PharmacyPortal");

            newSessionBean.setUserName(pharmacyUser.getEmail());
            newSessionBean.setUserId(pharmacyUser.getId());
            newSessionBean.setRole(pharmacyUser.getRole());
            newSessionBean.setCurrentDate(new Date());
            Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
            //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());

            request.getSession().setAttribute("PharmacyTitle", par.getTitle());
            request.getSession().setAttribute("Address", par.getAddress1());
            request.getSession().setAttribute("Phone", par.getPhone());
            request.getSession().setAttribute("Fax", par.getPhone());
            request.getSession().setAttribute("Email", par.getEmail());

            request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
            request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
            String currentUserName = "";
            currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

            newSessionBean.setCurrentUserName(currentUserName);
            try {
                newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
            request.getSession().setAttribute("sessionBeanPortal", newSessionBean);

        }
        //modelAndView.setViewName("transferRxQueue");
        //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
        request.getSession().setMaxInactiveInterval(900000);
        pageContents(modelAndView);
        try {
            //consumerRegistrationService.setOrderStatusWithId("Opened",oid);
            int userId = 0;
            userId = getPharmacyUserId(request, userId);

        } catch (Exception e1) {
            logger.info("transferRxList setOrderStatusWithId Message is: " + e1);
            e1.printStackTrace();
        }
        request.getSession().setAttribute("recordOpened", "true");

        try {
            List<OrderDetailDTO> orderLst =new ArrayList<>(); //this.patientProfileService.getPatientProfilesHistory(patientId, "Pending");//Shipped");
            List<OrderDetailDTO> orderLst2 = this.patientProfileService.getPatientProfilesHistoryOtherThanPending(patientId);
            List<OrderDetailDTO> orderLst3 =new ArrayList<>();// this.patientProfileService.getPatientProfilesHistory(patientId, "Cancelled");
            modelAndView.addObject("patientOrderQueue", orderLst);
            modelAndView.addObject("count", orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0);
            modelAndView.addObject("patientActiveOrderQueue", orderLst2);
            modelAndView.addObject("activeCount", orderLst2 != null && orderLst2.size() > 0 ? orderLst2.size() : 0);
            modelAndView.addObject("patientCancelOrderQueue", orderLst3);
            modelAndView.addObject("cancelActiveCount", orderLst3 != null && orderLst3.size() > 0 ? orderLst3.size() : 0);
            int count1 = orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0;
            int count2 = orderLst2 != null && orderLst2.size() > 0 ? orderLst2.size() : 0;
            int total = count1 + count2;
            modelAndView.addObject("total", total);
            CommonUtil.loadPageData(modelAndView, patientId, "0", patientProfileService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * patient info
         */
        //  modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(patientId));
        //loadPageData(modelAndView, patientId, oid);
        return modelAndView;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////4
    @RequestMapping(value = "/rxDetailRefillHandler/{patientId}")
    public ModelAndView rxDetailRefillHandler(@PathVariable("patientId") int pid,
            @RequestParam(value = "refillOrder", required = false) String refillOrder,
            RedirectAttributes redirectAttributes) {
        Order order = this.patientProfileService.findOrderById(refillOrder);

        return new ModelAndView("redirect:/ConsumerPortal/rxDetail/" + refillOrder + "/" + pid);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/rxDetailHandler")
    public ModelAndView rxDetailHandler(@RequestParam(value = "itemIds", required = false) String itemIds,
            @RequestParam(value = "orderId", required = false) String orderId,
            RedirectAttributes redirectAttributes) {
        Order order = this.patientProfileService.findOrderById(orderId);
        if (itemIds.indexOf(",") > 0 && itemIds.indexOf(",") < itemIds.length() - 1) {
            itemIds = itemIds.substring(itemIds.indexOf(",") + 1);
        }
        redirectAttributes.addFlashAttribute("itemIds", itemIds);
        return new ModelAndView("redirect:/ConsumerPortal/rxDetail/" + orderId + "/" + order.getPatientProfile().getPatientProfileSeqNo());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/rxDetail/{orderId}/{patientId}")
    public ModelAndView rxDetailLink(@PathVariable("orderId") String id, @PathVariable("patientId") int pid,
            @ModelAttribute Pharmacy pharmacy, RedirectAttributes redirectAttributes,
            @RequestParam(value = "multiProcessingOrders", required = false) String multiParam,
            @RequestParam(value = "questionId", required = false) Integer questionId,
            @ModelAttribute("itemIds") String itemIds, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/RxDetail3");
        this.populateRxdetail3(modelAndView, request, id, pid, itemIds, multiParam);
        if (!CommonUtil.isNullOrEmpty(questionId)) {
            modelAndView.addObject("incommingPtResponseQuestion", patientProfileService.populateQuestionAnswerDetail(questionId));
            modelAndView.addObject("showPtRespQDiv", "block");
        } else {
            modelAndView.addObject("showPtRespQDiv", "none");
        }
        return modelAndView;

    }

    private void populateRxdetail3(ModelAndView modelAndView, HttpServletRequest request, String id, int pid, String itemIds, String multiParam) {
        pageContents(modelAndView);
        request.getSession().setAttribute("Title", "RX ORDER DETAIL");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        String imageDivStyle = "tab-pane fade video_div_one in active";
        String shippingDivStyle = "tab-pane fade video_div_one";
        //Save last logged on date
        try {
            sessionBean = (SessionBean) request.getSession().getAttribute("sessionBeanPortal");
            if (pharmacyUser != null && pharmacyUser.getId() > 0) {
                consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());

                SessionBean newSessionBean = ConsumerRegistrationController.populateSessionBean(pharmacyUser, new Date(), permissionService);
                newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
                newSessionBean.setUserNameDB("PharmacyPortal");

                newSessionBean.setUserName(pharmacyUser.getEmail());
                newSessionBean.setUserId(pharmacyUser.getId());
                newSessionBean.setRole(pharmacyUser.getRole());
                newSessionBean.setCurrentDate(new Date());
                Pharmacy par = pharmacyUser.getPharmacy();//consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
                //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());

                request.getSession().setAttribute("PharmacyTitle", par.getTitle());
                request.getSession().setAttribute("Address", par.getAddress1());
                request.getSession().setAttribute("Phone", par.getPhone());
                request.getSession().setAttribute("Fax", par.getPhone());

                request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
                request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
                String currentUserName = "";
                currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

                newSessionBean.setCurrentUserName(currentUserName);
                try {
                    newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
                if (sessionBean != null) {
                    newSessionBean.setSelectedTab(sessionBean.getSelectedTab());
                }
                newSessionBean.setPharmacyAbbr(par.getAbbr());
                request.getSession().setAttribute("sessionBeanPortal", newSessionBean);

            }
            //modelAndView.setViewName("transferRxQueue");
            //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
            request.getSession().setMaxInactiveInterval(900000);

            if (!CommonUtil.isNullOrEmpty(id) && !id.trim().equals("0")) {

                //request.getSession().setAttribute("TransferRxQueue", consumerRegistrationService.getOrderDetailListById(id));
                TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(id,
                        CommonUtil.getPharmacyId((PharmacyUser) request.getSession().getAttribute("pharmacyUser")));
                if (sessionBean.getRxNumberMap() != null && !sessionBean.getRxNumberMap().isEmpty()) {
                    String rxNumber = sessionBean.getRxNumberMap().get(id);
                    dto.setRxControlNumber(id + "-" + rxNumber);
                } else {
                    dto.setRxControlNumber(id + "-001");
                }
                if (AppUtil.getSafeStr(dto.getStatus(), "").equalsIgnoreCase("Filled")
                        || AppUtil.getSafeStr(dto.getStatus(), "").equalsIgnoreCase("Shipped")
                        || AppUtil.getSafeStr(dto.getStatus(), "").equalsIgnoreCase("DELIVERY")
                        || AppUtil.getSafeStr(dto.getStatus(), "").equalsIgnoreCase("Pickup At Pharmacy")
                        || AppUtil.getSafeStr(dto.getStatus(), "").equalsIgnoreCase(Constants.ORDER_STATUS.READY_TO_DELIVER_RX)) {
                    shippingDivStyle = "tab-pane fade video_div_one"; //"tab-pane fade video_div_one in active";
                    imageDivStyle = "tab-pane fade video_div_one in active";
                    BaseDTO baseDTO = new BaseDTO();
                    baseDTO.setStatus("Filled");
                    baseDTO.setPatientId(pid);
                    List lst = consumerRegistrationService.getOrdersListByDate(pharmacyUser.getId(), baseDTO);
                    if (lst != null && lst.size() > 0) {
                        int index = this.actualIndexOfOrder(dto.getOrderId(), lst);
                        modelAndView.addObject("filledIndex", index + 1);
                        modelAndView.addObject("filledSize", lst.size());
                    } else {
                        modelAndView.addObject("filledIndex", 1);
                        modelAndView.addObject("filledSize", 1);
                    }
                    modelAndView.addObject("waitingPatientMsg", "Waiting patient approval for order shipment.");
                }
//                if(dto.getOrderPharmacyId()>0)
//                {
//                    if(dto.getOrderPharmacyId()!=pharmacyUser.getPharmacy().getId())
//                    {
//                        throw new Exception("This order belongs to some other pharmacy so you can't process it.");
//                    }
//                }
                String status = dto.getStatus();
                String nextOrderId = "";
                System.out.println("=======================>HERE==============================> " + new Date());
                if (AppUtil.getSafeStr(itemIds, "").length() > 0) {
                    String[] orderArray = itemIds.split(",");
                    nextOrderId = orderArray[0];
                    modelAndView.addObject("multiProcessingOrders", itemIds);
                    Order nextOrder = this.patientProfileService.findOrderById(nextOrderId);
                    modelAndView.addObject("nextPatientId", nextOrder != null ? nextOrder.getPatientProfile().getPatientProfileSeqNo() : 0);

                } else if (AppUtil.getSafeStr(multiParam, "").length() > 0) {
                    String multiProcessingOrder = AppUtil.getSafeStr(multiParam, "");
                    if (AppUtil.getSafeStr(request.getParameter("multiProcessingOrders"), "").equals("0")) {
                        nextOrderId = "0";
                        modelAndView.addObject("nextPatientLnk", 0);
                    } else {

                        String[] orderArray = multiProcessingOrder.split(",");
                        nextOrderId = orderArray[0];
                        Order nextOrder = this.patientProfileService.findOrderById(nextOrderId);
                        modelAndView.addObject("nextPatientId", nextOrder != null ? nextOrder.getPatientProfile().getPatientProfileSeqNo() : 0);
                        if (orderArray.length > 1) {
                            if (multiProcessingOrder.indexOf(",") > 0 && multiProcessingOrder.indexOf(",") < multiProcessingOrder.length() - 1) {
                                multiProcessingOrder = multiProcessingOrder.substring(multiProcessingOrder.indexOf(",") + 1);
//                                modelAndView.addObject("multiProcessingOrders", multiProcessingOrder);
                            } else {

                                multiProcessingOrder = "0";
//                                modelAndView.addObject("multiProcessingOrders", "");
                            }
//                            modelAndView.addObject("multiProcessingOrders", multiProcessingOrder);
                        } else if (orderArray.length == 1) {
                            multiProcessingOrder = "0";
                        } else {
                            multiProcessingOrder = "0";
                            nextOrderId = "0";
                        }
                        modelAndView.addObject("multiProcessingOrders", multiProcessingOrder);
                    }
                } else {
                    nextOrderId = consumerRegistrationService.geNextOrderByPatientProfileOrder(pid, id,
                            dto.getReceivedDate(), "Pending",
                            //                        AppUtil.getSafeStr(request.getParameter("status"), "").length() > 0
                            //                        ? AppUtil.getSafeStr(request.getParameter("status"), "") : dto.getStatus(),
                            AppUtil.getSafeInt(request.getParameter("index"), 0),
                            pharmacyUser != null && pharmacyUser.getPharmacy() != null ? pharmacyUser.getPharmacy().getId() : 0);
                    String nextPatientId = "0";
                    if (nextOrderId.equals("0")) {
                        OrderDetailDTO nextDTO = consumerRegistrationService.geNextPatientProfile(pid, id,
                                dto.getReceivedDate(), status,
                                AppUtil.getSafeInt(request.getParameter("index"), 0),
                                pharmacyUser != null && pharmacyUser.getPharmacy() != null ? pharmacyUser.getPharmacy().getId() : 0);
                        nextPatientId = nextDTO.getPatientId();
                        nextOrderId = nextDTO.getId();
                    }
                    modelAndView.addObject("nextPatientId", nextPatientId);
                }
                String prevOrderId = "";

                if (AppUtil.getSafeStr(nextOrderId, "").length() > 0
                        && !AppUtil.getSafeStr(nextOrderId, "").equals("0")) {
                    //modelAndView.addObject("prevOrder", "0");
                    modelAndView.addObject("nextOrder", "1");
                    modelAndView.addObject("index", AppUtil.getSafeInt(request.getParameter("index"), 0) + 1);
                } else {

                    modelAndView.addObject("nextOrder", "0");
                }
                if (AppUtil.getSafeStr(prevOrderId, "").length() > 0
                        && !AppUtil.getSafeStr(prevOrderId, "").equals("0")) {
                    //modelAndView.addObject("nextOrder", "0");
                    modelAndView.addObject("prevOrder", "1");
                    //modelAndView.addObject("index",AppUtil.getSafeInt(request.getParameter("index"),0)>0? AppUtil.getSafeInt(request.getParameter("index"),0)-1:0);
                } else {
                    modelAndView.addObject("prevOrder", "0");

                }
                modelAndView.addObject("adminRole", AppUtil.getSafeStr(pharmacyUser.getRole(), "").equalsIgnoreCase("admin"));
                modelAndView.addObject("shippingDivStyle", shippingDivStyle);
                modelAndView.addObject("imageDivStyle", imageDivStyle);
                modelAndView.addObject("order", dto);
                int imageCount = dto.getOrderTransferImages() != null ? dto.getOrderTransferImages().size() : 0;
                if (imageCount == 0) {
                    imageCount = AppUtil.getSafeStr(dto.getPtVideo(), "").length() > 0
                            || AppUtil.getSafeStr(dto.getTransferImage(), "").length() > 0 ? 1 : 0;
                }
                modelAndView.addObject("imageCount", imageCount);
                modelAndView.addObject("nextOrderId", AppUtil.getSafeStr(nextOrderId, ""));
                modelAndView.addObject("prevOrderId", AppUtil.getSafeStr(prevOrderId, ""));
                modelAndView.addObject("status", AppUtil.getSafeStr(request.getParameter("status"), "pending"));
///////////////////////////////////////////////////////////////////////////////////////////////////
                List<OrderDetailDTO> orderLst =new ArrayList<>(); //this.patientProfileService.getPatientProfilesHistory(pid, "");//Shipped");
                List<OrderDetailDTO> filledOrdersLst = new ArrayList<>();
                if (!dto.isDependentFlag()) {
                    filledOrdersLst = consumerRegistrationService.getMultiRxOrdersByPatientId(AppUtil.getSafeInt(id, 0), pid, 0, CommonUtil.getPharmacyId(pharmacyUser));
                } else {
                    filledOrdersLst = consumerRegistrationService.getMultiRxOrdersByDependentId(AppUtil.getSafeInt(id, 0), dto.getDependentId(), 0, CommonUtil.getPharmacyId(pharmacyUser));
                }
                if (filledOrdersLst == null) {
                    filledOrdersLst = new ArrayList<>();
                }
                modelAndView.addObject("filledOrdersLst", filledOrdersLst);
                int count1 = orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0;
                modelAndView.addObject("total", dto.getMultiRxCount());//count1);
                modelAndView.addObject("processedCount", dto.getProcessedOrderCount());
                modelAndView.addObject("loadingTime", PropertiesUtil.getProperty("NEXT_ORDER_LOADING"));
                modelAndView.addObject("phmcyPrefix", PropertiesUtil.getProperty("PHARMACY_ABR_PREFIX"));
///////////////////////////////////////////////////////////////////////////////////////////////////

                if (dto.getStatus().equalsIgnoreCase(Constants.ORDER_STATUS.READY_TO_DELIVER_RX) || dto.getStatus().equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED)) {
                    OrderDetailDTO orderDetailDTO = consumerRegistrationService.getReadyToDeliverOrdersByPatientId(pid, dto.getReadyToDeliverId(), dto.getDeliveryId());
//                    List<OrderDetailDTO> listOfReadyToDeliverOrders = (List<OrderDetailDTO>) orderDetailDTO.getReadyToDeliverOrders();
//                    if (!CommonUtil.isNullOrEmpty(listOfReadyToDeliverOrders)) {
//                        modelAndView.setViewName("portal/multiRxDelivery");
//                        modelAndView.addObject("listOfReadyToDeliverOrders", listOfReadyToDeliverOrders);
//                        modelAndView.addObject("readyToDeliverOrders", orderDetailDTO);
//                    }
                }

            }
            CommonUtil.loadPageData(modelAndView, pid, id, patientProfileService);
            if (AppUtil.getSafeStr(request.getParameter("update"), "").equals("1")) {
                modelAndView.addObject("update", "1");
            } else {
                modelAndView.addObject("update", "0");
            }
            List questionList = this.patientProfileService.getQuestionAnswerList(id);
            modelAndView.addObject("sameDayOredrsList", consumerRegistrationService.getSameDayAndFilledStatusOrdersByPatientId(pid));
            modelAndView.addObject("questions", questionList);
            BaseDTO baseDTO = new BaseDTO();
            String status = request.getParameter("status");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");

            if (status != null && !status.isEmpty()) {
                baseDTO.setStatus(status);
            }
            if (fromDate != null && !fromDate.isEmpty()) {
                baseDTO.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
            }

            if (toDate != null && !toDate.isEmpty()) {
                baseDTO.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
            }

            modelAndView.addObject("baseDTO", baseDTO);
        } catch (Exception e) {
            logger.error("Exception#", e);
            e.printStackTrace();
            modelAndView.addObject("err", 1);
            modelAndView.addObject("errMsg", e.getMessage());
        }
        modelAndView.addObject("err", 0);
    }

    //////////////////////////////////////////////////////////////
    private int actualIndexOfOrder(String orderId, List<TransferRxQueueDTO> lst) {
        int index = 0;
        for (TransferRxQueueDTO transferRxQueueDTO : lst) {

            if (transferRxQueueDTO.getOrderId().equals(orderId)) {
                return index;
            }
            index++;
        }
        return index;
    }

    @RequestMapping(value = "/optIn", produces = "text/plain")
    public @ResponseBody
    String consumerPortal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("portal/index");
        pageContents(modelAndView);
        SupportModel supportModel = new SupportModel();
        String shortCode = request.getParameter("shortCode");
        String eventName = request.getParameter("eventName");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String ctrigger = request.getParameter("ctrigger");
        String communicationMethod = request.getParameter("communicationMethod");
        String communicationId = request.getParameter("communicationId");
        String memberId = request.getParameter("memberId");
        supportModel.setShortCode(shortCode);
        supportModel.setEventName(eventName);
        supportModel.setUserId(userId);
        supportModel.setPassword(password);
        supportModel.setCtrigger(ctrigger);
        supportModel.setCommunicationMethod(communicationMethod);

        if (communicationMethod.equalsIgnoreCase("text")) {
            supportModel.setCommunicationId(communicationId.replaceAll("[\\s\\-()]", ""));

        } else if (communicationMethod.equalsIgnoreCase("ivr")) {
            supportModel.setCommunicationId(communicationId);

        } else if (communicationMethod.equalsIgnoreCase("email")) {
            supportModel.setCommunicationId(communicationId);
            supportModel.setMemberId(memberId.replaceAll("[\\s\\-()]", ""));
        }
        makeWidgetUrl(supportModel);
        String message = "";
        WidgetLog widgetLog = consumerPortalService.getWidgetLog(communicationMethod);
        if (widgetLog.getId() != null) {
            if (widgetLog.getNotificationType().equalsIgnoreCase(Constants.FAILURE)) {
                message = widgetLog.getNotificationMessage();
            } else {
                message = widgetLog.getNotificationType();
            }
        }
        logger.info("Notification Message is: " + message);
        return message;
    }

    private void makeWidgetUrl(SupportModel supportModel) throws IOException, MalformedURLException, UnsupportedEncodingException {
        String dataPost;
        if (supportModel.getMemberId() != null) {
            dataPost = URLEncoder.encode("communicationMethod", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationMethod(), "UTF-8") + "&"
                    + URLEncoder.encode("shortCode", "UTF-8") + "=" + URLEncoder.encode(supportModel.getShortCode(), "UTF-8") + "&"
                    + URLEncoder.encode("eventName", "UTF-8") + "=" + URLEncoder.encode(supportModel.getEventName(), "UTF-8") + "&"
                    + URLEncoder.encode("trigger", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCtrigger(), "UTF-8") + "&"
                    + URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getUserId(), "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(supportModel.getPassword(), "UTF-8") + "&"
                    + URLEncoder.encode("communicationId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationId(), "UTF-8") + "&"
                    + URLEncoder.encode("phoneNo", "UTF-8") + "=" + URLEncoder.encode(supportModel.getMemberId(), "UTF-8");
        } else {
            dataPost = URLEncoder.encode("communicationMethod", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationMethod(), "UTF-8") + "&"
                    + URLEncoder.encode("shortCode", "UTF-8") + "=" + URLEncoder.encode(supportModel.getShortCode(), "UTF-8") + "&"
                    + URLEncoder.encode("eventName", "UTF-8") + "=" + URLEncoder.encode(supportModel.getEventName(), "UTF-8") + "&"
                    + URLEncoder.encode("trigger", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCtrigger(), "UTF-8") + "&"
                    + URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getUserId(), "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(supportModel.getPassword(), "UTF-8") + "&"
                    + URLEncoder.encode("communicationId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationId(), "UTF-8");
        }
        String urlString = Constants.APP_PATH + "/widget";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Method", "GET");
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(dataPost);
        wr.flush();
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        wr.close();
        rd.close();
    }

    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public ModelAndView documents() {
        ModelAndView modelAndView = new ModelAndView("portal/documents");
        modelAndView.addObject("documents", new CMSDocuments());
        modelAndView.addObject("documentlist", consumerPortalService.getCMSDocumentsList(10));
        modelAndView.addObject("list", consumerPortalService.getCMSDocumentsList());
        pageContents(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/documents/{index}", method = RequestMethod.GET)
    public ModelAndView viewMoreDocuments(@PathVariable("index") Integer index) {
        ModelAndView modelAndView = new ModelAndView("portal/documents");
        int ind = index + 10;
        modelAndView.addObject("documents", new CMSDocuments());
        modelAndView.addObject("documentlist", consumerPortalService.getCMSDocumentsList(ind));
        modelAndView.addObject("list", consumerPortalService.getCMSDocumentsList());
        pageContents(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/termscondition", method = RequestMethod.GET)
    public ModelAndView termsCondition() {
        ModelAndView modelAndView = new ModelAndView("portal/termscondition");
        pageContents(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.GET)
    public ModelAndView createAccount() {
        ModelAndView modelAndView = new ModelAndView("portal/registration1");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchDocument(@ModelAttribute CMSDocuments documents) {
        ModelAndView modelAndView = new ModelAndView("portal/documents");
        modelAndView.addObject("documents", documents);
        modelAndView.addObject("list", consumerPortalService.getCMSDocumentsList());
        if (documents.getTitle().trim().isEmpty()) {
            pageContents(modelAndView);
            modelAndView.addObject("documentlist", consumerPortalService.getCMSDocumentsList(10));
            return modelAndView;
        }
        pageContents(modelAndView);
        modelAndView.addObject("documentlist", consumerPortalService.getCMSDocumentsByTitle(documents.getTitle()));
        return modelAndView;
    }

    @RequestMapping("/download/{id}")
    public void downloadDocuments(@PathVariable("id") Integer id, HttpServletResponse response) {
        CMSDocuments cmsd = cMSService.getCMSDocuments(id);
        try {
            try (OutputStream out = response.getOutputStream()) {
                response.setContentType(cmsd.getContentType());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + cmsd.getAttachmentName() + "\"");
                IOUtils.copyLarge(new ByteArrayInputStream(cmsd.getAttachment()), out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView loadRegistration1PageHandler() {
        ModelAndView modelAndView = new ModelAndView("/portal/registration1");
        try {
            modelAndView.addObject("pharmacyLookup", new PharmacyLookup());
            pageContents(modelAndView);
          //  modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
        } catch (Exception e) {
            logger.error("ConsumerPortalController -> loadRegistration1PageHandler", e);
        }

        return modelAndView;
    }

//    @RequestMapping(value = "/lookup", method = RequestMethod.POST)
//    public ModelAndView lookupPharmacy(@ModelAttribute PharmacyLookup pharmacyLookup) {
//        ModelAndView modelAndView = new ModelAndView("/portal/registration1");
//        try {
//            String npi = pharmacyLookup.getNpi();
//            String sitePassNumber = pharmacyLookup.getPassNumber();
//            int typeId = pharmacyLookup.getPharmacyType().getId();
//            PharmacyLookup pharmacyLookup1 = consumerRegistrationService.lookupPharmacy(npi, sitePassNumber, typeId);
//            if (pharmacyLookup1 == null) {
//                pageContents(modelAndView);
//                modelAndView.addObject("pharmacyLookup", new PharmacyLookup());
//                modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
//                modelAndView.addObject("message", messageSource.getMessage("consumer.lookup.notfound", null, null));
//                return modelAndView;
//            }
//
//            List<Pharmacy> pharmacyList = consumerRegistrationService.getPharmacyByNpi(pharmacyLookup.getNpi(), pharmacyLookup1.getId());
//            if (pharmacyList.size() > 0) {
//                pharmacyLookup1.setPharmacyExist(Boolean.TRUE);
//            }
//            pageContents(modelAndView);
//            modelAndView.addObject("pharmacyLookup", pharmacyLookup1);
//            modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
//        } catch (NoSuchMessageException e) {
//            logger.error("ConsumerRegistrationController -> lookupPharmacy", e);
//        }
//
//        return modelAndView;
//    }
    @RequestMapping(value = "/lookup", method = RequestMethod.POST)
    public @ResponseBody
    String lookupPharmacy(@ModelAttribute PharmacyLookup pharmacyLookup) {
        //ModelAndView modelAndView = new ModelAndView("/portal/registration1");
        String str_response = "";
        try {
            String npi = pharmacyLookup.getNpi();
            String sitePassNumber = pharmacyLookup.getPassNumber();
            int typeId = pharmacyLookup.getPharmacyType().getId();
            PharmacyLookup pharmacyLookup1 = consumerRegistrationService.lookupPharmacy(npi, sitePassNumber, typeId);
            if (pharmacyLookup1 == null) {
                str_response = JsonResponseComposer.composeFailureResponse("No pharmacy found. Correct your information & try again.");
                return str_response;
            }

            PharmacyDTO pharmacyDTO = new PharmacyDTO();
            pharmacyDTO.setAddress(pharmacyLookup1.getAddress());
            pharmacyDTO.setCity(pharmacyLookup1.getCity());
            pharmacyDTO.setId(pharmacyLookup1.getId());
            pharmacyDTO.setNpi(pharmacyLookup1.getNpi());
            pharmacyDTO.setSitePass(pharmacyLookup1.getPassNumber());
            pharmacyDTO.setState(pharmacyLookup1.getState().getName());
            pharmacyDTO.setTitle(pharmacyLookup1.getTitle());
            pharmacyDTO.setZip("" + pharmacyLookup1.getZip());
            pharmacyDTO.setTypeId(pharmacyLookup.getPharmacyType().getId());
            pharmacyDTO.setTypeName(pharmacyLookup.getPharmacyType().getTitle());

            List<Pharmacy> pharmacyList = consumerRegistrationService.getPharmacyByNpi(pharmacyLookup.getNpi(), pharmacyLookup1.getId());
            /**
             * Pharmacy exist and registered
             */
            if (pharmacyList.size() > 0) {
                str_response = JsonResponseComposer.composeFailureResponse("Account is already created for this pharmacy. Please login with provided credentials.", pharmacyDTO);
                return str_response;
            }
            /**
             * Pharmacy exist and not registered
             */
            str_response = JsonResponseComposer.composeSuccessResponse(pharmacyDTO);
        } catch (NoSuchMessageException e) {
            logger.error("ConsumerRegistrationController -> lookupPharmacy", e);
            e.printStackTrace();
        }
        return str_response;
    }

//    @RequestMapping(value = "/continueRegistration", method = RequestMethod.POST)
//    public ModelAndView registration2PageLoadHandler(@ModelAttribute PharmacyLookup pharmacyLookup) {
//        ModelAndView modelAndView = new ModelAndView("/portal/registration2");
//        try {
//            Pharmacy pharmacy = new Pharmacy();
//            pharmacy.setPharmacyLookup(pharmacyLookup);
//            pageContents(modelAndView);
//            List<String> facilityHoursList = new ArrayList<>();
//            for (int count = 0; count < 25; count++) {
//                if (count < 10) {
//                    facilityHoursList.add("0" + count);
//                } else {
//                    facilityHoursList.add(String.valueOf(count));
//                }
//            }
//            modelAndView.addObject("facilityHoursList", facilityHoursList);
//            modelAndView.addObject("pharmacy", pharmacy);
//        } catch (Exception e) {
//            logger.error("ConsumerRegistrationController -> registration2PageLoadHandler", e);
//        }
//
//        return modelAndView;
//    }
    @RequestMapping(value = "/pharmacyRegistration", method = RequestMethod.GET)
    public ModelAndView registration2PageLoadHandlerGet(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/portal/registration2");
        try {

            System.out.println("json request receive for registeration page ::: " + request.getParameter("npi"));

            String str_npiId = request.getParameter("npi");
            String str_sitePass = request.getParameter("sitePass");
            String str_typeId = request.getParameter("typeId");
            Integer i_typeId = Integer.parseInt(str_typeId);

            PharmacyLookup pharmacyLookup = consumerRegistrationService.lookupPharmacy(str_npiId, str_sitePass, i_typeId);

            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setPharmacyLookup(pharmacyLookup);
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
            modelAndView.addObject("pharmacy", pharmacy);
        } catch (Exception e) {
            logger.error("ConsumerRegistrationController -> registration2PageLoadHandler", e);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/pharmacyRegistration", method = RequestMethod.POST)
    public ModelAndView registration2PageLoadHandler(@RequestBody String jsonRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/portal/registration2");
        try {

            System.out.println("json request receive for registeration page ::: " + jsonRequest);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode npiIdNode = rootNode.path("npi");
            String str_npiId = npiIdNode.asText();

            JsonNode sitePassNode = rootNode.path("sitePass");
            String str_sitePass = sitePassNode.asText();

            //typeId
            JsonNode typeIdNode = rootNode.path("typeId");
            Integer i_typeId = typeIdNode.asInt();

            PharmacyLookup pharmacyLookup = consumerRegistrationService.lookupPharmacy(str_npiId, str_sitePass, i_typeId);

            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setPharmacyLookup(pharmacyLookup);
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
            modelAndView.addObject("pharmacy", pharmacy);
        } catch (Exception e) {
            logger.error("ConsumerRegistrationController -> registration2PageLoadHandler", e);
        }

        System.out.println("json request receive for registeration page END ::: " + jsonRequest);

        return modelAndView;
    }

    @RequestMapping(value = "/addConsumer")
    public ModelAndView addUserHandler(@ModelAttribute Pharmacy pharmacy, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/portal/registration2");
        try {

            if (pharmacy == null) {
                return modelAndView;
            }

            consumerRegistrationValidator.validate(pharmacy, result);

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

            if (result.hasErrors()) {
                modelAndView.addObject("pharmacy", pharmacy);

                return modelAndView;
            }
            //Remove extra users
            if (pharmacy.getPharmacyUserList().size() > 0) {
                List<PharmacyUser> pharmacyUsersList = new ArrayList<>();
                Iterator<PharmacyUser> iter = pharmacy.getPharmacyUserList().iterator();
                while (iter.hasNext()) {
                    PharmacyUser pharmacyUser = iter.next();
                    if ((pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) && (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty())
                            && (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty())
                            && (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty())) {
                        //pharmacyUsersList.add(pharmacyUser);
                        iter.remove();
                    } else {
                        pharmacyUser.setUserStartDate(new Date());
                        pharmacyUsersList.add(pharmacyUser);
                    }
                }
//                for (int i = 1; i < pharmacy.getPharmacyUserList().size(); i++) {
//                    PharmacyUser pharmacyUser = pharmacy.getPharmacyUserList().get(i);
//                    if ((pharmacyUser.getFirstName() == null || pharmacyUser.getFirstName().isEmpty()) && (pharmacyUser.getLastName() == null || pharmacyUser.getLastName().isEmpty())
//                            && (pharmacyUser.getEmail() == null || pharmacyUser.getEmail().isEmpty())
//                            && (pharmacyUser.getPhone() == null || pharmacyUser.getPhone().isEmpty())) {
//
//                        pharmacy.getPharmacyUserList().remove(pharmacyUser);
//                    }
//                }
                pharmacy.setPharmacyUserList(pharmacyUsersList);
            }
            consumerRegistrationService.saveConsumerDetail(pharmacy);
            Pharmacy newpharmacy = new Pharmacy();
            newpharmacy.setPharmacyLookup(pharmacy.getPharmacyLookup());
            modelAndView.addObject("pharmacy", newpharmacy);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ConsumerRegistrationController -> registration2PageLoadHandler", e);
            redirectAttributes.addFlashAttribute("message", "Some error occurred " + e.getMessage());
            return new ModelAndView("redirect:/PharmacyPortal/login");

        }

        //
        redirectAttributes.addFlashAttribute("message", "Pharmacy registered successfully and access details are sent at given email(s) respectively.");
        return new ModelAndView("redirect:/PharmacyPortal/login");
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public ModelAndView loadForgotPasswrdPageHandler() {
        ModelAndView modelAndView = new ModelAndView("portal/forgotpassword");
        pageContents(modelAndView);
        modelAndView.addObject("pharmacy", new Pharmacy());
        return modelAndView;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ModelAndView forgotPasswordHandler(@ModelAttribute Pharmacy pharmacy) {
        ModelAndView modelAndView = new ModelAndView("portal/forgotpassword");
        try {
            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(pharmacy.getEmail());
            pageContents(modelAndView);
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(pharmacy.getEmail());
            if (!matcher.matches()) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.forgotusername.invalid.email", null, null));
                return modelAndView;
            }

            if (pharmacyUser == null) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.email.not.found", null, null));
                return modelAndView;
            }

            // Update password and send email
            consumerRegistrationService.forgotPassword(pharmacyUser);
            modelAndView.addObject("message", messageSource.getMessage("consumer.forgotpassword.success", null, null));

        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> forgotUserNameHandler", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/forgotusername", method = RequestMethod.GET)
    public ModelAndView loadForgotUserNamePageHandler() {
        ModelAndView modelAndView = new ModelAndView("portal/forgotusername");
        pageContents(modelAndView);
        modelAndView.addObject("pharmacy", new Pharmacy());
        return modelAndView;
    }

    @RequestMapping(value = "/forgotusername", method = RequestMethod.POST)
    public ModelAndView forgotUserNameHandler(@ModelAttribute Pharmacy pharmacy) {
        ModelAndView modelAndView = new ModelAndView("portal/forgotusername");
        try {
            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(pharmacy.getEmail());
            pageContents(modelAndView);
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(pharmacy.getEmail());
            if (!matcher.matches()) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.forgotusername.invalid.email", null, null));
                return modelAndView;
            }

            if (pharmacyUser == null) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.email.not.found", null, null));
                return modelAndView;
            }

            // Send email
            CMSEmailContent emailContent = consumerRegistrationService.getCMSEmailContent(Constants.FORGOT_USERNAME);
            String emailBody = consumerRegistrationService.relpacePlaceHolder(emailContent.getEmailBody(), pharmacyUser.getEmail(), null, null, null);
            EmailSenderUtil.send(pharmacyUser.getEmail(), emailContent.getSubject(), emailBody);
            modelAndView.addObject("message", messageSource.getMessage("consumer.forgotusername.success", null, null));

        } catch (NoSuchMessageException e) {
            logger.error("Exception: ConsumerRegistrationController -> forgotUserNameHandler", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/forgotUserNamePassword", method = RequestMethod.POST)
    public @ResponseBody
    String forgotUserNamePasswordHandler(@ModelAttribute Pharmacy pharmacy) {
        //ModelAndView modelAndView = new ModelAndView("portal/forgotpassword");
        String str_response = "";
        try {
            PharmacyUser pharmacyUser = consumerRegistrationService.getPharmacyUserByEmail(pharmacy.getEmail());
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(pharmacy.getEmail());
            if (!matcher.matches()) {

                //modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.forgotusername.invalid.email", null, null));
                str_response = JsonResponseComposer.composeFailureResponse(messageSource.getMessage("consumer.forgotusername.invalid.email", null, null));
                return str_response;
            }

            if (pharmacyUser == null) {
                //modelAndView.addObject("errorMessage", messageSource.getMessage("consumer.email.not.found", null, null));
                //return modelAndView;
                str_response = JsonResponseComposer.composeFailureResponse(messageSource.getMessage("consumer.email.not.found", null, null));
                return str_response;
            }

            // Update password and send email
            consumerRegistrationService.forgotUserNamePassword(pharmacyUser);
            //modelAndView.addObject("message", messageSource.getMessage("consumer.forgotpassword.success", null, null));
            str_response = JsonResponseComposer.composeSuccessResponse();
        } catch (Exception e) {
            logger.error("Exception: ConsumerRegistrationController -> forgotUserNamePasswordHandler", e);
            str_response = JsonResponseComposer.composeFailureResponse(e.getMessage());
            return str_response;
        }
        return str_response;
    }

    /**
     * send notification
     *
     * @param jsonRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "/pharmacyNotifiction", method = RequestMethod.POST)
    public @ResponseBody
    String sendPharmacyNotification(@RequestBody String jsonRequest, HttpServletRequest request) {

        String str_response = "";

        try {

            System.out.println("json request receive for registeration page ::: " + jsonRequest);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode patientIdNode = rootNode.path("patientId");
            Integer i_patientId = patientIdNode.asInt();

            JsonNode orderIdNode = rootNode.path("orderId");
            Integer i_orderId = orderIdNode.asInt();

            //message
            JsonNode messageNode = rootNode.path("message");
            String str_message = messageNode.asText();

            JsonNode isCriticalPTNode = rootNode.path("isCriticalPT");
            String strIsCriticalPT = isCriticalPTNode.asText();

            int isCriticalPT = AppUtil.getSafeInt(strIsCriticalPT, 0);

            /**
             * Haider Ali sent notification
             */
//         NotificationPharmacy notificationPharmacy = new NotificationPharmacy();
//         
//         PatientProfile patientProfile = new PatientProfile();
//         patientProfile.setId(i_patientId);
            Order order = new Order();
            order.setId("" + i_orderId);

//         notificationPharmacy.setPatientProfile(patientProfile);
//         notificationPharmacy.setOrder(order);
//         notificationPharmacy.setMessage(str_message);
//         notificationPharmacy = notificationPharmacyService.saveNotificationPharmacy(notificationPharmacy);
//         if(notificationPharmacy == null){
//             str_response = JsonResponseComposer.composeFailureResponse("Notification Message not save");
//             return str_response; 
//         }
            str_response = JsonResponseComposer.composeSuccessResponse();

        } catch (Exception e) {
            logger.error("PortalController -> sendPharmacyNotification", e);
            str_response = JsonResponseComposer.composeFailureResponse("Notification Message not save");
            return str_response;
        }

        System.out.println("json request receive for registeration page END ::: " + jsonRequest);

        return str_response;
    }

    /////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/pharmacyNotifictionHistory", method = RequestMethod.POST)
    public @ResponseBody
    String getOrderwisePharmacyNotifications(@RequestBody String jsonRequest, HttpServletRequest request) {

        String str_response = "JSON Ret";

        try {

            System.out.println("json request receive for registeration page ::: " + jsonRequest);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            Integer i_orderId = orderIdNode.asInt();

        } catch (Exception e) {
            logger.error("PortalController -> sendPharmacyNotification", e);
            str_response = JsonResponseComposer.composeFailureResponse("Notification Message not save");
            e.printStackTrace();
            return str_response;
        }

        System.out.println("json request receive for registeration page END ::: " + jsonRequest);

        return str_response;
    }

    ////////////////////////////////////////////////////////////////////
    /**
     * Haider Ali
     *
     * @param jsonRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "/getpharmacyNotifiction", method = RequestMethod.POST)
    public @ResponseBody
    String getPharmacyNotification(@RequestBody String jsonRequest, HttpServletRequest request) {

        String str_response = "";

        try {

            System.out.println("json request receive for registeration page ::: " + jsonRequest);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode patientIdNode = rootNode.path("patientId");
            Integer i_patientId = patientIdNode.asInt();

            JsonNode orderIdNode = rootNode.path("orderId");
            Integer i_orderId = orderIdNode.asInt();

            /**
             * Haider Ali get notification
             */
            PatientProfile patientProfile = new PatientProfile();
            patientProfile.setPatientProfileSeqNo(i_patientId);

            Order order = new Order();
            order.setId("" + i_orderId);

            //NotificationPharmacy notificationPharmacy = notificationPharmacyService.getNotificationPharmacy(i_patientId, "" + i_orderId);
//            NotificationPharmacy notificationPharmacy = null;
//            if (notificationPharmacy == null) {
//                str_response = JsonResponseComposer.composeFailureResponse("Notification Message not exist");
//                return str_response;
//            }
//            NotificationPharmacyDTO notificationPharmacyDTO = new NotificationPharmacyDTO();
//            notificationPharmacyDTO.setId(notificationPharmacy.getId());
//            notificationPharmacyDTO.setPatientId(notificationPharmacy.getPatientProfile().getId());
//            notificationPharmacyDTO.setOrderId(notificationPharmacy.getOrder().getId());
//            notificationPharmacyDTO.setMessage(notificationPharmacy.getMessage());
//            notificationPharmacyDTO.setSendDate("" + notificationPharmacy.getDateSent());
        } catch (Exception e) {
            logger.error("PortalController -> getpharmacyNotifiction", e);
            str_response = JsonResponseComposer.composeFailureResponse("Notification Message not exist");
            return str_response;
        }

        System.out.println("json response sent to client ::: " + str_response);

        return str_response;
    }

    @RequestMapping(value = "/updateorderstatus/{id}/{patientId}", method = RequestMethod.GET)
    public ModelAndView updateOrderStatus(HttpServletRequest request, @PathVariable("id") Integer id, @PathVariable("patientId") int patientId) {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        try {

            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            if (pharmacyUser != null) {
                this.consumerRegistrationService.updateOrderStatus(id, CommonUtil.getPharmacyId(pharmacyUser), pharmacyUser.getId(), 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnToLevelTwoQueue(request, patientId, "" + id);
    }

    @RequestMapping(value = "/updatePatientAllergies/{patientId}", method = RequestMethod.POST)
    public @ResponseBody
    String updatePatientAllergies(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp,
            @PathVariable("patientId") int patientId) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode allergyNode = rootNode.path("allergies");
            String allergies = allergyNode.asText();
            String response = this.patientProfileService.updatePatientAllergies(patientId, allergies);

            return new ObjectMapper().writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
    }

    @RequestMapping(value = "/popUpWind/{patientId}", method = RequestMethod.POST)
    public @ResponseBody
    String popUpPatientAllergies(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp,
            @PathVariable("patientId") int patientId) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode allergyNode = rootNode.path("statusId");
            int statusUId = allergyNode.asInt();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            long response = this.patientProfileService.getOrdersCount(statusUId, CommonUtil.getPharmacyId(pharmacyUser));
//            String response = "Hello POP_UP";
            return new ObjectMapper().writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
    }
    /////////////////////////////////////////////////////////

    @RequestMapping(value = "/updateorderstatus/{id}/{patientId}", method = RequestMethod.POST)
    public @ResponseBody
    String updateOrderStatus(
            @RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp, @PathVariable("id") Integer id,
            @PathVariable("patientId") int patientId) throws IOException {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode changeStatusNode = rootNode.path("changeStatus");
            Integer changeStatus = changeStatusNode.asInt();

            JsonNode statusValNode = rootNode.path("statusBit");
            Integer statusVal = statusValNode.asInt();

            //message
            JsonNode messageNode = rootNode.path("message");
            String message = messageNode.asText();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");

            /////////////////////////////////////////////////////////
            JsonNode rxNumberNode = rootNode.path("rxNumberFld");
            String rxNumber = rxNumberNode.asText();

            JsonNode drugBrandIdNode = rootNode.path("name");
            String brandName = drugBrandIdNode.asText();

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();

            JsonNode strengthNode = rootNode.path("drugStrength");
            String strength = strengthNode.asText();

            JsonNode qtyNode = rootNode.path("drugQty");
            String qty = qtyNode.asText();

            JsonNode daysSupplyNode = rootNode.path("daysSupplyFld");
            String daysSupply = daysSupplyNode.asText();

            JsonNode refillsAllowedNode = rootNode.path("refillAllowedFld");
            String refillsAllowed = refillsAllowedNode.asText();

            JsonNode refillUsedNode = rootNode.path("refillUsedFld");
            String refillUsed = refillUsedNode.asText();

            JsonNode paymentModeNode = rootNode.path("paymentFld");
            String paymentMode = paymentModeNode.asText();

            JsonNode patientNameNode = rootNode.path("patientNameFld");
            String patientName = patientNameNode.asText();

            JsonNode pharmacyRxNoNode = rootNode.path("pharmacyRxNoFld");
            String pharmacyRxNo = pharmacyRxNoNode.asText();

            JsonNode pharmacyNameNode = rootNode.path("pharmacyNameFld");
            String pharmacyName = pharmacyNameNode.asText();

            JsonNode pharmacyPhoneNode = rootNode.path("pharmacyPhoneFld");
            String pharmacyPhone = pharmacyPhoneNode.asText();

            JsonNode prescriberNameNode = rootNode.path("prescriberNameFld");
            String prescriberName = prescriberNameNode.asText();

            JsonNode prescriberPhoneNode = rootNode.path("prescriberPhoneFld");
            String prescriberPhone = prescriberPhoneNode.asText();

            JsonNode prescriberNPINode = rootNode.path("prescriberNPIFld");
            String prescriberNPI = prescriberNPINode.asText();

            JsonNode acqCostNode = rootNode.path("acqCostFld");
            String acqCost = acqCostNode.asText();

            JsonNode reimbNode = rootNode.path("reimbFld");
            String reimb = reimbNode.asText();

            JsonNode ptCopayNode = rootNode.path("ptCopayFld");
            String ptCopay = ptCopayNode.asText();

            JsonNode finalPaymentNode = rootNode.path("finalPaymentFld");
            String finalPayment = finalPaymentNode.asText();

            JsonNode deliverycarrierNode = rootNode.path("deliverycarrier");
            String deliverycarrier = deliverycarrierNode.asText();

            JsonNode shipDateNode = rootNode.path("shipDate");
            String shipDate = shipDateNode.asText();

            JsonNode traclingnoNode = rootNode.path("traclingno");
            String traclingno = traclingnoNode.asText();

            JsonNode clerkNode = rootNode.path("clerk");
            String clerk = clerkNode.asText();

            JsonNode commentsNode = rootNode.path("orderComments");
            String comments = commentsNode.asText();

            JsonNode redeemPointsNode = rootNode.path("redeemPoints");
            String redeemPoints = redeemPointsNode.asText();

            JsonNode redeemPointsCostNode = rootNode.path("redeemPointsCost");
            String redeemPointsCost = redeemPointsCostNode.asText();

            JsonNode mfrCostNode = rootNode.path("mfrCost");
            String mfrCost = mfrCostNode.asText();

//            JsonNode profitShareNode = rootNode.path("profitShare");
//            String profitShare = profitShareNode.asText();
            JsonNode insuranceTypeNode = rootNode.path("insuranceType");
            String insuranceType = insuranceTypeNode.asText();

//            JsonNode priceIncludingMarginsNode = rootNode.path("priceIncludingMargins");
//            String priceIncludingMargins = priceIncludingMarginsNode.asText();
            JsonNode rxDateNode = rootNode.path("rxDate");
            String rxDateStr = rxDateNode.asText();

            JsonNode nextRefillDateStrNode = rootNode.path("nextRefillDateStr");
            String nextRefillDateStr = nextRefillDateStrNode.asText();

            JsonNode expireDateNode = rootNode.path("rxExpireDate");
            String expireDateStr = expireDateNode.asText();

            JsonNode profitMarginNode = rootNode.path("profitMargin");
            String profitMargin = profitMarginNode.asText();

            JsonNode actualProfitShareNode = rootNode.path("actualProfitShare");
            String actualProfitShare = actualProfitShareNode.asText();

            JsonNode profitSharePointNode = rootNode.path("profitSharePoint");
            String profitSharePoint = profitSharePointNode.asText();

            JsonNode sellingPriceNode = rootNode.path("sellingPrice");

//            JsonNode olVersionNode = rootNode.path("olversion");
//            LocalDateTime ldt = LocalDateTime.parse(olVersionNode.asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Date olversion = new Date(); //Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

            JsonNode handlingFeeNode = rootNode.path("handlingFee");
            String handlingFee = handlingFeeNode.asText();

            JsonNode systemGeneratedRxNumberNode = rootNode.path("systemGeneratedRxNumber");
            String systemGeneratedRxNumber = systemGeneratedRxNumberNode.asText();

            JsonNode prescriberLastNameNode = rootNode.path("prescriberLastName");
            String prescriberLastName = prescriberLastNameNode.asText();

            JsonNode ptOverridePriceNode = rootNode.path("ptOverridePrice");
            int ptOverridePrice = ptOverridePriceNode.asInt();

            JsonNode prescriberExtNode = rootNode.path("prescriberExt");
            String prescriberExt = prescriberExtNode.asText();

            JsonNode pharmacyExtNode = rootNode.path("pharmacyExt");
            String pharmacyExt = pharmacyExtNode.asText();

            JsonNode estPriceFldNode = rootNode.path("estPriceFld");
            double estPriceFld = AppUtil.getSafeDouble(estPriceFldNode.asText(), 0d);

            JsonNode paymentAuthorizationNode = rootNode.path("paymentAuthorization");
            String paymentAuthorization = paymentAuthorizationNode.asText();

            JsonNode deliveryIdNode = rootNode.path("prefId");
            int deliveryId = deliveryIdNode.asInt();
            ////////////////////////////////////////////////////////

            JsonNode multiRxNode = rootNode.path("multiRx");
            boolean multiRx = multiRxNode.asBoolean();
//            ArrayNode multiRxIdsNode = (ArrayNode) rootNode.get("multiRxIds");
//            List<String> multiRxIds = new ObjectMapper().convertValue(multiRxIdsNode, ArrayList.class);
            Integer numberOfRecord = 1, totalMultiRx = 1;
            JsonNode multiRxIdsNode = rootNode.path("multiRxIds");
            List<String> multiRxIds = null;
            if (CommonUtil.isNotEmpty(multiRxIdsNode.asText())) {
                multiRxIds = Arrays.asList(multiRxIdsNode.asText().split(","));
                totalMultiRx = multiRxIds.size();
                numberOfRecord = multiRxIds.size();
            }
            Order order = orderService.getOrderById("" + id);//, olversion);
            //   Order order = orderService.getOrderById("" + id, olversion);
            if (order == null) {
                throw new Exception("No Order found against this " + id);
            }
            //check poa expire or not
            response = patientProfileService.isPOAExpire(order.getId());
            JsonResponse jsonResponse = new Gson().fromJson(response, JsonResponse.class);
            if (jsonResponse != null && jsonResponse.getStatus().equalsIgnoreCase(Constants.JSON_STATUS.FAIL)) {
                return response;
            }
            if (order != null) {
                
                Integer pharmacyId = CommonUtil.getPharmacyId(pharmacyUser);

                if (AppUtil.getSafeDouble(AppUtil.getSafeStr(handlingFee, "").replace("$", ""), 0d) > 0d) {
                    order.setHandLingFee(AppUtil.getSafeDouble(AppUtil.getSafeStr(handlingFee, "").replace("$", ""), 0d));
                }
                //save multi rx shipping rx orders
                saveMultiRxShippingOrders(multiRx, multiRxIds, order, pharmacyId, pharmacyUser, id, request, message, estPriceFldNode, estPriceFld, patientId);
                if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
                    order.setPharmacy(new Pharmacy(pharmacyId));
                    if (request.getSession().getAttribute("sessionBeanPortal") != null) {
                        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBeanPortal");
                        order.setRxPrefix(AppUtil.getSafeStr(sessionBean.getPharmacyAbbr(), "") + "-");
                    }
                }
                //DrugDetail detail = orderService.getDrugDetailByOrder("" + id);
                if (AppUtil.getSafeStr(order.getCardType(), "").length() == 0) {
                   
                }
                if (AppUtil.getSafeStr(order.getShippingAddress(), "").length() == 0) {
                    PatientDeliveryAddress patientDeliveryAddress = patientProfileService.getPatientDeliveryDefaultAddress(patientId);
                    if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
                        logger.info("Set shipping address info. ");
                        order.setStreetAddress(patientDeliveryAddress.getAddress());
                        order.setCity(patientDeliveryAddress.getCity());
                        order.setZip(patientDeliveryAddress.getZip());
                        order.setAddressLine2(patientDeliveryAddress.getDescription());
                        order.setApartment(patientDeliveryAddress.getApartment());
                        if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                            order.setState(patientDeliveryAddress.getState().getName());
                        }
                    }
                }
                order.setPatientComments(comments);
                order.setRedeemPoints(redeemPoints);
                order.setRedeemPointsCost(AppUtil.getSafeDouble(redeemPointsCost, 0d));
                order.setPriceIncludingMargins(sellingPriceNode.asDouble());
                if (CommonUtil.isNotEmpty(rxDateStr)) {
                    Date rxDate = DateUtil.stringToDate(rxDateStr, "MM/dd/yyyy");
                    if (rxDate != null) {
                        order.setRxDate(rxDate);
                    }
                }
                if (CommonUtil.isNotEmpty(nextRefillDateStr)) {
                    Date nextRefillDate = DateUtil.stringToDate(nextRefillDateStr, "MM-dd-yyyy");
                    if (nextRefillDate != null) {
                        order.setNextRefill(nextRefillDate);
                        order.setNextRefillDate(nextRefillDate);
                    }
                }

                order.setOldRxNumber(AppUtil.getSafeStr(pharmacyRxNo, ""));
//                if (AppUtil.getSafeDouble(AppUtil.getSafeStr(handlingFee, "").replace("$", ""), 0d) > 0d) {
//                    order.setHandLingFee(AppUtil.getSafeDouble(AppUtil.getSafeStr(handlingFee, "").replace("$", ""), 0d));
//                }
                order.setSystemGeneratedRxNumber(systemGeneratedRxNumber);
                order.setPrescriberLastName(prescriberLastName);
                order.setPtOverridePrice(ptOverridePrice);
                order.setPrescriberExtension(prescriberExt);
                order.setPharmacyExtension(pharmacyExt);
                if (statusVal == 8)//For Filled status
                {
                    if (estPriceFld > 0 && estPriceFld < AppUtil.getSafeDouble(finalPayment, 0d)
                            && AppUtil.getSafeStr(paymentAuthorization, "0").equals("0")) {
                        statusVal = 9;//set waiting patient response for payment authorization
                    }
                }

                int statusValCopy = statusVal;
                CampaignMessages campaignMessages = null;
                switch (statusVal) {
                    case 0:
                    case 2:
                        //Processing

                        //Order order = orderService.getOrderById("" + id);
                        //CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(Constants.MSG_ORDER_RECEIPT, Constants.RX_ORDER);
                        campaignMessages = patientProfileService.getNotificationMsgs("Order Processed", Constants.RX_ORDER);
                        break;
                    case 3:
                        campaignMessages = patientProfileService.getNotificationMsgs("Pending Phmcy Reply", Constants.RX_ORDER);
                        break;
                    case 6:
                    case 5:
                    case 15:
                        //Shipping Or Delivery
                        //Order order = orderService.getOrderById("" + id);
                        campaignMessages = patientProfileService.getNotificationMsgs(
                                "Rx Delivered", Constants.PHARMACY_NOTIFICATION);
                        break;
//                    case 5:
//                        campaignMessages = patientProfileService.getNotificationMsgs(
//                                "Change Delivery Options", Constants.RX_ORDER);
//                        order.setResponseRequired(AppUtil.getSafeStr(campaignMessages.getRequiredPatientResponse(), "Hand delivery options"));
//                        order.setDisabledFlds(1);
//                        //statusValCopy=Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID;
//                        break;
                    case 13:
                        campaignMessages = patientProfileService.getNotificationMsgs(
                                "Ready To Deliver", Constants.RX_ORDER);
                        order.setResponseRequired(AppUtil.getSafeStr(campaignMessages.getRequiredPatientResponse(), "Waiting to accept"));
                        order.setDisabledFlds(1);
                        statusValCopy = Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID;
                        break;
                    case 8://Filled
                        //Payment Authorization
                        //Order order = orderService.getOrderById("" + id);
                        campaignMessages = patientProfileService.getNotificationMsgs(
                                "Change Delivery Options", Constants.RX_ORDER);
                        order.setResponseRequired(campaignMessages.getRequiredPatientResponse());
                        order.setDisabledFlds(1);
//                        statusValCopy=Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID;
                        break;

                    case 9:
                        //Payment Authorization
                        //Order order = orderService.getOrderById("" + id);
                        campaignMessages = patientProfileService.getNotificationMsgs(
                                Constants.PAYMENT_AUTHORIZATION, Constants.PHARMACY_NOTIFICATION);
                        order.setResponseRequired(campaignMessages.getRequiredPatientResponse());
                        order.setDisabledFlds(1);
                        statusValCopy = Constants.ORDER_STATUS.WAITING_PT_RESPONSE_ID;
                        break;
                    default:
                        break;
                }
                order.setDeliveryId(deliveryId);
                order = this.consumerRegistrationService.updateOrder(id, pharmacyUser, statusValCopy, message, rxNumber,
                        brandName, type, strength, qty, daysSupply, refillsAllowed, refillUsed, paymentMode,
                        patientName, pharmacyName, pharmacyPhone, prescriberName, prescriberPhone, prescriberNPI,
                        acqCost, reimb, ptCopay, finalPayment, deliverycarrier, shipDate, traclingno, clerk, order,
                        mfrCost, insuranceType, sellingPriceNode.asText(), expireDateStr,
                        AppUtil.getSafeDouble(profitMargin, 0d),
                        AppUtil.getSafeInt(profitSharePoint, 0), AppUtil.getSafeFloat(actualProfitShare, 0f)); //changeStatus == 1 ? 2 : 1, message);
                int userId = 0;
//                if (request.getSession().getAttribute("pharmacyUser") != null) {
//                    PharmacyUser user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
//                    if (user != null) {
//                        userId = user.getId();
//                        consumerRegistrationService.updateOrderStatus(id, userId, pharmacyUser.getId(), 2);
//                    }
//                }
                message = sendPharmacyQueueMsgs(order, campaignMessages, id, request, message, estPriceFldNode, brandName, type, strength, qty, estPriceFld, patientId, numberOfRecord, totalMultiRx);
                NotificationPharmacyDTO notificationPharmacyDTO = populateNotificationPharmacyDTO(id, patientId, message, order);
                //notificationPharmacyDTO.setOlVersion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOlversion()));
                response = JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
                //////////////////////////////////////////
                if (statusVal == 6 || statusVal == 5) {
                    String msg = "Your order has been shipped successfully.";
                    String json = "{\"patientId\": \"" + patientId + "\",\"msg\": \"" + msg + "\"}";//,\"body\": \"Hi\"}";
                    //CommonUtil.postDataToPhone(msg, json, patientId, "http://rxdirectdev.ssasoft.com/rxdirectapi/users/pushNot", false);
                }

            } else {
                //@TODO send message
                throw new Exception("You have an old version of record, Please refresh the page to get the updated record.");
            }
            ////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            logger.error("Exception# ", e);
            //response = JsonResponseComposer.composeFailureResponse(e.getMessage());
            e.printStackTrace();
            resp.setStatus(500);
            if (e instanceof java.net.ConnectException) {
                return new ObjectMapper().writeValueAsString("Order shipped successfully but due to some connection problem to messaging server, shipment message couldn't be sent to patient this time.");
            }
            return new ObjectMapper().writeValueAsString(e.getMessage());

        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }

    private NotificationPharmacyDTO populateNotificationPharmacyDTO(Integer id, int patientId, String message, Order order) {
        ///////////////////////////////////////////
        NotificationPharmacyDTO notificationPharmacyDTO = new NotificationPharmacyDTO();
        notificationPharmacyDTO.setId(id);
        notificationPharmacyDTO.setPatientId(patientId);
        /**
         * if(statusVal==8){ String
         * nextOrderId=orderService.nextOrderId(patientId, id.toString());
         * notificationPharmacyDTO.setNextOrderId(nextOrderId); }*
         */
        notificationPharmacyDTO.setOrderId("" + id);
        notificationPharmacyDTO.setMessage(message);
        notificationPharmacyDTO.setSendDate("" + new Date());
        notificationPharmacyDTO.setStatus("" + order.getOrderStatus().getId());
        return notificationPharmacyDTO;
    }

    private void saveMultiRxShippingOrders(boolean multiRx, List<String> multiRxIds, Order order, Integer pharmacyId, PharmacyUser pharmacyUser, Integer id, HttpServletRequest request, String message, JsonNode estPriceFldNode, double estPriceFld, int patientId) throws Exception {
        if (multiRx && !CommonUtil.isNullOrEmpty(multiRxIds)) {
            int pickedFromPharmacyDFId = 4;
            boolean containsAnyOrderPFP = false;
            for (String oi : multiRxIds) {
                Order o = orderService.getOrderById(oi);
                if (o != null) {
                    if (o.getDeliveryPreference() != null && o.getDeliveryPreference().getId() == pickedFromPharmacyDFId) {
                        containsAnyOrderPFP = true;
                    }
                }
            }

            if (!containsAnyOrderPFP) {
//                List<OrderStatus> osl = orderService.getOrderStatusList();
//                OrderStatus os = null;
//                for (OrderStatus oss : osl) {
//                    if (oss.getId() == 6) {
//                        os = oss;
//                        break;
//                    }
//                }
                Integer numberOfRecord = 1, totalMultiRx = multiRxIds.size();
                for (String oi : multiRxIds) {
                    MultiRx m = new MultiRx();
                    m.setMainOrder(order);
                    Order o = orderService.getOrderById(oi);
                    m.setOrder(o);
                    m.setShippedDate(new Date());
                    orderService.save(m);
                    //Set OrderStatus
                    OrderStatus os = new OrderStatus();
                    os.setId(Constants.ORDER_STATUS.SHIPPED_ID);
                    o.setOrderStatus(os);
//                    if (os != null) {
//                        o.setOrderStatus(os);
//                    }

                    if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
                        o.setPharmacy(new Pharmacy(pharmacyId));
                    }
                    o.setHandLingFee(order.getHandLingFee() != null ? order.getHandLingFee() : 0d);
                    o.setUpdatedBy(pharmacyUser.getId());
                    o.setUpdatedOn(new Date());
                    orderService.update(o);
                    if (o.getId() == null ? id.toString() == null : o.getId().equals(id.toString())) {
                        //totalMultiRx--;
                        continue;
                    }

                    //First shipping message
                    CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(
                            "Rx Delivered", Constants.PHARMACY_NOTIFICATION);
                    CommonUtil.populateDecryptedOrderData(null, o);
                    this.sendPharmacyQueueMsgs(o, campaignMessages, Integer.parseInt(o.getId()), request, message, estPriceFldNode, o.getDrugName(), o.getDrugType(), o.getStrength(), o.getQty(), estPriceFld, patientId, numberOfRecord, totalMultiRx);
                    numberOfRecord++;
                }
                order.setShippedOn(new Date());
                order.setShippedBy(pharmacyUser);
                order.setMultiRx(true);
                orderService.update(order);
            } else {
                throw new Exception("Order cannot be shipped because it also contains the order that will be picked from pharmacy.");
            }
        }
    }

    private String sendPharmacyQueueMsgs(Order order, CampaignMessages campaignMessages, Integer id, HttpServletRequest request, String message, JsonNode estPriceFldNode, String brandName, String type,
            String strength, String qty, double estPriceFld, int patientId, Integer numberOfRecord, Integer totalMultiRx) throws Exception {
        ////////////////////////////////////////////
        if (order != null && campaignMessages != null && campaignMessages.getMessageTypeId() != null) {
            String duration = "";
            String pharmacyComments = "";
            String genericName = "";
            String dateStr = "", expirationDate = "", drugCost = "Not Specified", estimatedDrugCost = "Not Specified";
            String cardNumber = "";
           
//                    genericName = this.patientProfileService.loadDrugGeneric(brandName);
            double drugCostNumeric = 0d;
            double estPriceNumeric = 0d;
            double priceDiff = 0d;
            try {
                TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(id.toString(),
                        CommonUtil.getPharmacyId((PharmacyUser) request.getSession().getAttribute("pharmacyUser")));
                if (campaignMessages.getSmstext().indexOf("[date_time]") >= 0
                        || campaignMessages.getSmstext().indexOf("[duration]") >= 0) {

                    //Date date=DateUtil.formatDate(dto.getReceivedDate(), "MM/dd/YYYY hh:mm a");
                    dateStr = DateUtil.dateToString(dto.getReceivedDate(), "MM/dd/YYYY hh:mm a");
                    duration = DateUtil.getDateDiffFromCurrentDate(dto.getReceivedDate());

                }
                if (order.getFinalPayment() != null) {
                    drugCost = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getFinalPayment());
                    estimatedDrugCost = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getFinalPayment());
                }
                if (order.getEstimatedPrice() != null && order.getEstimatedPrice() > 0d) {
                    estimatedDrugCost = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getEstimatedPrice());
                }
                expirationDate = dto.getRxExpiredDate() != null ? DateUtil.dateToString(
                        dto.getRxExpiredDate(), "MM/dd/yyyy") : "";
                cardNumber = AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(order.getCardNumber()), "");
                if (cardNumber.length() > 4) {
                    cardNumber = cardNumber.substring(cardNumber.length() - 4);
                }
            } catch (Exception e) {
                logger.error("Exception# " + e);
            }
            message = AppUtil.getSafeStr(campaignMessages.getSmstext(), "");
//message = CommonUtil.replaceOrdersReceiptPlaceHolder(message, id, order, brandName, strength, type, qty, ptCopay);
///////////////////////////////////////////////////////////
            /**
             * String[] arr = AppUtil.getBrandAndGenericFromDrugName(brandName);
             * if (arr != null) { if (arr.length == 2) { brandName = arr[0];
             * genericName = arr[1]; } else { brandName = arr[0] + "(" +
             * Constants.BRAND_NAME_ONLY + ")"; } }*
             */
            drugCostNumeric = AppUtil.getSafeDouble(drugCost, 0d);
            if (estPriceFldNode != null) {
                estPriceNumeric = AppUtil.getSafeDouble(estPriceFldNode.asText(), 0d);
            }
            priceDiff = drugCostNumeric - estPriceNumeric;
            String drugName = AppUtil.getProperDrugName(brandName, type, strength);
            double finalAmount = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;
            finalAmount = order.getHandLingFee() != null ? finalAmount + order.getHandLingFee() : finalAmount;
            order.setImagePath(EncryptionHandlerUtil.getDecryptedString(order.getImagePath()));
            order.setCardType(EncryptionHandlerUtil.getDecryptedString(order.getCardType()));
            message = AppUtil.getSafeStr(message, "").replace("[ORDER_TYPE]", AppUtil.getSafeStr(order.getOrderType(), "")).replace("[ORDER_DATE]", dateStr)
                    .replace("[DRUG_NAME]", drugName).replace("[DRUG_STRENGTH]", strength)
                    .replace("[DRUG_TYPE]", type).replace("[DRUG_QTY]", qty)
                    .replace("[date_time]", DateUtil.dateToString(new Date(), "MM/dd/yyyy"))
                    .replace("[time]", DateUtil.dateToString(new Date(), "HH:mm a"))
                    .replace("[request_no]", id.toString())
                    .replace("[duration]", duration)
                    .replace("[DR_NAME]", AppUtil.getSafeStr(order.getPrescriberName(), "") + " " + AppUtil.getSafeStr(order.getPrescriberLastName(), ""))
                    .replace("[DR_PHONE]", AppUtil.getSafeStr(order.getPharmacyPhone(), ""))
                    .replace("[RX_NUMBER]", AppUtil.getSafeStr(order.getRxNumber(), ""))
                    .replace("[DAYS_SUPPLY]", order.getDaysSupply() != null ? order.getDaysSupply().toString() : "Not mentioned")
                    .replace("[REFILLS_REMAIN]", order.getRefillsRemaining() != null ? order.getRefillsRemaining().toString() : "Not mentioned")
                    //.replace("[pharmacy_comments]", pharmacyComments)
                    .replaceAll("(255 characters) [pharmacy_comments]", CommonUtil.isNotEmpty(pharmacyComments) ? pharmacyComments : "")
                    .replaceAll("[pharmacy_comments] ( 255 characters)", CommonUtil.isNotEmpty(pharmacyComments) ? pharmacyComments : "")
                    .replace("[generic_name]", genericName)
                    .replace("[RX_EXPIRATION]", AppUtil.getSafeStr(expirationDate, ""))
                    .replace("[DRUG_COST]", AppUtil.roundOffNumberToCurrencyFormat(AppUtil.getSafeDouble(drugCost, 0d), "en", "US").length() > 0 ? "$" + drugCost : "$0")
                    .replace("[ESTIMATED_DRUG_COST]", AppUtil.roundOffNumberToCurrencyFormat(estPriceFld, "en", "US"))
                    .replace("[PRICE_CHANGE]", AppUtil.roundOffNumberToCurrencyFormat(priceDiff, "en", "US"))
                    .replace("[DRUG_COST_WO_DOLLAR]", AppUtil.getSafeStr(drugCost, "").length() > 0
                            ? AppUtil.roundOffNumberToCurrencyFormat(AppUtil.getSafeDouble(drugCost, 0d), "en", "US") : "$0.00")
                    .replace("[ins_price]", order.getRxReimbCost() != null && order.getRxReimbCost() > 0d ? AppUtil.roundOffNumberToCurrencyFormat(order.getRxReimbCost(), "en", "US") : "N/A")
                    .replace("[INS_TYPE]", AppUtil.getSafeStr(order.getPaymentType(), "").equalsIgnoreCase("INSURANCE") ? "Y" : "N")
                    .replace("[points_cost]", order.getActualProfitShare() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getActualProfitShare(), "en", "US") : "$0.00")
                    .replace("[card_number]", "****" + cardNumber)
                    .replace("[delivery_carrier]", AppUtil.getSafeStr(order.getDeliverycarrier(), ""))
                    .replace("[clerk]", AppUtil.getSafeStr(order.getClerk(), ""))
                    .replace("[tracking_no]", AppUtil.getSafeStr(order.getTraclingno(), ""))
                    .replace("[card_type]", AppUtil.getSafeStr(order.getCardType(), ""))
                    .replace("[shipped_date]", order.getShippedOn() != null
                            ? DateUtil.dateToString(order.getShippedOn(), "MM/dd/yyyy") : "N/A")
                    .replace("[DRUG_IMAGE]", (AppUtil.getSafeStr(order.getImagePath(), "").length() > 0 && CommonUtil.urlAuthorization(AppUtil.getSafeStr(order.getImagePath(), "")) ? "<img id='drugImg' src=\'" + AppUtil.getSafeStr(order.getImagePath(), "") + "\' width='30' height='30'/>" : ""))
                    .replace("[final_payment]", AppUtil.roundOffNumberToCurrencyFormat(finalAmount, "en", "US"))
                    .replace("[NUMBER_OF_RECORD]", numberOfRecord.toString())
                    .replace("[TOTAL_RECORD]", totalMultiRx.toString())
                    .replace("[OUT_OF_POCKET]", AppUtil.roundOffNumberToCurrencyFormat(order.getOriginalPtCopay(), "en", "US"))
                    .replace("[REDEEM_POINT_COST]", AppUtil.roundOffNumberToCurrencyFormat(order.getRedeemPointsCost(), "en", "US"))
                    .replace("[DRUGCOST_ESTIMATE]", AppUtil.roundOffNumberToCurrencyFormat(order.getRxAcqCost(), "en", "US"))
                    .replace("[SUB_TOTAL]", AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment(), "en", "US"))
                    .replace("[RX_NUM]", AppUtil.getSafeStr(order.getRxPrefix(), "").length() > 0 ? AppUtil.getSafeStr(order.getRxPrefix(), "") + " " + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));

            if (AppUtil.getSafeStr(message, "").indexOf("[delivery_method]") >= 0) {
//                        String pref = this.consumerRegistrationService.populateDeliveryPreferenceAgainstSpecificOrder(id.toString());
                String[] pref = this.consumerRegistrationService.populateDeliveryPreferenceArrAgainstSpecificOrder(id.toString());
                if (pref != null && pref.length > 0) {
//                            message = campaignMessages.getSmstext();
                    //message = message.replace("[delivery_method]", pref[0]);
                    if (pref.length >= 2) {
                        logger.info("Pref is at index 0# " + pref[0] + "Pref is at index 1# " + pref[1]);
                        message = message.replace("[delivery_method_used]", pref[1]);
                        if (CommonUtil.isNotEmpty(AppUtil.getSafeStr(pref[1], "")) && AppUtil.getSafeStr(pref[1], "").contains("2nd Day*")) {
                            message = message.replace("[delivery_method]", "2<sup>nd</sup>DAY");
                            message = message.replace("[handling_fee]", "included");
                        } else {
                            message = message.replace("[handling_fee]", order.getHandLingFee() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getHandLingFee(), "en", "US") : "$0.00");
                        }
                    } else {
                        message = message.replace("[delivery_method_used]", pref[0]);
                        if (CommonUtil.isNotEmpty(AppUtil.getSafeStr(pref[1], "")) && AppUtil.getSafeStr(pref[0], "").contains("2nd Day*")) {
                            message = message.replace("[delivery_method]", "2<sup>nd</sup>DAY");
                            message = message.replace("[handling_fee]", "included");
                        } else {
                            message = message.replace("[handling_fee]", order.getHandLingFee() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getHandLingFee(), "en", "US") : "$0.00");
                        }
                    }
                    message = message.replace("[delivery_method]", pref[0]);
//                            if (AppUtil.getSafeStr(pref[0], "").indexOf("2nd Day") >= 0) {
//                                message = message.replace("[handling_fee]", "included");
//                            } else {
//                                message = message.replace("[handling_fee]", order.getHandLingFee() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getHandLingFee(), "en", "US") : "$0.00");
//                            }

                }
            }
            campaignMessages.setSmstext(message);
/////////////////////////////////////////////////////////////////////////////
//                    if (AppUtil.getSafeStr(genericName, "").length() == 0
//                            && campaignMessages != null
//                            && AppUtil.getSafeStr(campaignMessages.getSmstext(), "").length() > 0) {
//                        campaignMessages.setSmstext(campaignMessages.getSmstext().replace("Generic For", ""));
//                    }
//////////////////////////////////////////////////////////
//campaignMessages.setSmstext(message);
//                    if (statusVal == 6 || statusVal == 5) {
//                        if (detail != null) {
//                            message = campaignMessages.getSmstext();
//                            if (AppUtil.getSafeStr(detail.getPdfDocUrl(), "").length() > 0) {
//                                message += "<p>Patient Document <a href=\"" + AppUtil.getSafeStr(detail.getPdfDocUrl(), "") + "\">View Patient Doc</a></p>";
//                            } else {
//                                message += "<p>No Patient document associated.</p>";
//                            }
//
//                            if (AppUtil.getSafeStr(detail.getDrugDocUrl(), "").length() > 0) {
//                                message += "<p>Drug Document  <a href=\"" + AppUtil.getSafeStr(detail.getDrugDocUrl(), "") + "\">View Drug Doc</a></p>";
//                            } else {
//                                message += "<p>No drug document associated.</p>";
//                            }
//                            campaignMessages.setSmstext(message);
//                        }
//
//                    }
            patientProfileService.saveNotificationMessages(campaignMessages, patientId, order.getId());
        }
        return message;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/postAnswer", method = RequestMethod.POST)
    public @ResponseBody
    String updateOrderStatus(
            @RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");

            /////////////////////////////////////////////////////////
            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode questionIdNode = rootNode.path("questionId");
            int questionId = questionIdNode.asInt();

            JsonNode answerNode = rootNode.path("answer");
            String answer = answerNode.asText();

            boolean flag = this.patientProfileService.saveAnswer(orderId, questionId, answer, pharmacyUser != null ? pharmacyUser.getId() : 0);
            response = "Answer sent successfully.";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: posting question ", e);
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
        return new ObjectMapper().writeValueAsString(response);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public ModelAndView returnToLevelTwoQueue(HttpServletRequest request, int patientId, String oid) {
        ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        request.getSession().setAttribute("Title", "Transfer RX Queue");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        //Save last logged on date
        if (pharmacyUser != null && pharmacyUser.getId() > 0) {
            consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());

            SessionBean newSessionBean = new SessionBean();
            newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
            newSessionBean.setUserNameDB("PharmacyPortal");

            newSessionBean.setUserName(pharmacyUser.getEmail());
            newSessionBean.setUserId(pharmacyUser.getId());
            newSessionBean.setRole(pharmacyUser.getRole());
            newSessionBean.setCurrentDate(new Date());
            Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
            //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());

            request.getSession().setAttribute("PharmacyTitle", par.getTitle());
            request.getSession().setAttribute("Address", par.getAddress1());
            request.getSession().setAttribute("Phone", par.getPhone());
            request.getSession().setAttribute("Fax", par.getPhone());
            request.getSession().setAttribute("Email", par.getEmail());

            request.getSession().setAttribute("orderId", oid + "");
            request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
            request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
            String currentUserName = "";
            currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

            newSessionBean.setCurrentUserName(currentUserName);
            try {
                newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
            request.getSession().setAttribute("sessionBeanPortal", newSessionBean);

        }
        //modelAndView.setViewName("transferRxQueue");
        //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
        request.getSession().setMaxInactiveInterval(900000);
        pageContents(modelAndView);
        try {
            //consumerRegistrationService.setOrderStatusWithId("Opened",oid);
            int userId = 0;
            userId = getPharmacyUserId(request, userId);
        } catch (Exception e1) {
            logger.info("transferRxList setOrderStatusWithId Message is: " + e1);
            e1.printStackTrace();
        }
        request.getSession().setAttribute("recordOpened", "true");

        List<TransferRxQueueDTO> transferDList = new ArrayList<>();

        try {
            if (patientId > 0 && (!CommonUtil.isNullOrEmpty(oid) && !oid.trim().equals("0"))) {
                transferDList = consumerRegistrationService.getTranferRxQueueByPatientProfileOrder(patientId, oid);
                request.getSession().setAttribute("count", consumerRegistrationService.getTransferRListCount(patientId, oid));
            }

            request.getSession().setAttribute("orderId", oid + "");
        } catch (Exception e) {
            logger.info("transferRxList Message is: " + e);
        }

        modelAndView.addObject("TransferRxQueueDTO", transferDList);
        /**
         * patient info
         */
        modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(patientId));
        return modelAndView;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/loadDeliveryPrefFee/{id}", method = RequestMethod.GET)
    public ModelAndView loadDeliveryPrefFee(@PathVariable Integer id) {
        try {

        } catch (Exception e) {

        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/sendinappnotification/{orderId}/{patientId}", method = RequestMethod.POST)
    public @ResponseBody
    String sendInAppMessage(
            @RequestBody String jsonRequest, HttpServletRequest request,
            @PathVariable("orderId") String orderId, @PathVariable("patientId") int patientId) {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");

        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode eventNode = rootNode.path("eventName");
            String eventName = eventNode.asText();

            //message
            JsonNode customMsgNode = rootNode.path("customMsg");
            String customMsg = customMsgNode.asText();

            JsonNode customSubNode = rootNode.path("customSubject");
            String customSub = AppUtil.getSafeStr(customSubNode.asText(), "");

            JsonNode messageNode = rootNode.path("message");
            String message = messageNode.asText();

            JsonNode orderTyeNode = rootNode.path("ORDER_TYPE");
            String orderType = orderTyeNode != null ? orderTyeNode.asText() : "";

            JsonNode orderDateNode = rootNode.path("ORDER_DATE");
            String orderDate = orderDateNode != null ? orderDateNode.asText() : "";

            JsonNode drugNode = rootNode.path("drug");
            String drug = drugNode != null ? drugNode.asText() : "";

            JsonNode drugStrengthNode = rootNode.path("DRUG_STRENGTH");
            String drugStrength = drugStrengthNode != null ? drugStrengthNode.asText() : "";

            JsonNode drugTypeNode = rootNode.path("DRUG_TYPE");
            String drugType = drugTypeNode != null ? drugTypeNode.asText() : "";

            String genericName = "";
            ///////////////////////////////////////////
            String drugName = AppUtil.getProperDrugName(drug, drugType, drugStrength);
            ///////////////////////////////////////////

//            byte[] fileNode = rootNode.path("pdfDocFile").getBinaryValue();
            JsonNode drugQtyNode = rootNode.path("qty");
            String drugQty = drugQtyNode != null ? drugQtyNode.asText() : "";

            JsonNode daysSupplyNode = rootNode.path("daysSupply");
            Integer daysSupply = daysSupplyNode != null ? daysSupplyNode.asInt() : 0;

            JsonNode refillAllowedFldNode = rootNode.path("refillAllowedFld");
            String refillAllowedFld = refillAllowedFldNode.asText();

            JsonNode acqCostFldNode = rootNode.path("acqCostFld");
            String acqCostFld = acqCostFldNode.asText();

            JsonNode ptCopayFldNode = rootNode.path("ptCopayFld");
            String ptCopayFld = ptCopayFldNode.asText();

            JsonNode redeemPointsCostFldNode = rootNode.path("redeemPointsCostFld");
            String redeemPointsCostFld = redeemPointsCostFldNode.asText();

            JsonNode insTypeNode = rootNode.path("insType");
            String insType = insTypeNode.asText();
            insType = CommonUtil.isNotEmpty(insType) && (insType.equalsIgnoreCase("Public") || insType.equalsIgnoreCase("Commercial"))
                    ? "Y -ON FILE" : "N";

            JsonNode finalPaymentNode = rootNode.path("finalPayment");
            String finalPayment = finalPaymentNode.asText();

            String duration = "";
            String pharmacyComments = "";
//            String genericName = "";
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(message, eventName);
            int statusVal = 0;
            if (campign != null && campign.getMessageId() != null) {
                if (CommonUtil.isNotEmpty(campign.getRequiredPatientResponse())) {
                    customSub = campign.getRequiredPatientResponse();
                    statusVal = 16;
                }
            }
            if (pharmacyUser != null) {
                this.consumerRegistrationService.updateOrderStatusAndHisotry(orderId, pharmacyUser.getId(), statusVal, customSub, "", CommonUtil.getPharmacyId(pharmacyUser));
//                this.consumerRegistrationService.updateOrderStatus(id, pharmacyUser.getId(), 2);
//                  this.consumerRegistrationService.updateOrderStatusAndHisotry(orderId, pharmacyUser.getId(), 16, customSub);
            }

            Order order = (Order) this.patientProfileService.getOrderById(new Order(), orderId);//.getObjectById(new Order(), orderId);
            Integer isCritical = 0;
            order.setDaysSupply(daysSupply);
            order.setFinalPayment(AppUtil.getSafeDouble(AppUtil.getSafeStr(finalPayment, "").replace("$", ""), 0d));

            if (campign != null) {
                if (AppUtil.getSafeStr(customMsg, "").length() > 0) {
                    campign.setSmstext(AppUtil.getSafeStr(customMsg, ""));
                }
//                if (AppUtil.getSafeStr(customMsg, "").length() == 0) {
                String dateStr = "", expirationDate = "", drugCost = "Not Specified";
              
//                    genericName = this.patientProfileService.loadDrugGeneric(drug);
                try {
                    TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(orderId,
                            CommonUtil.getPharmacyId((PharmacyUser) request.getSession().getAttribute("pharmacyUser")));
                    if (campign.getSmstext().indexOf("[date_time]") >= 0
                            || campign.getSmstext().indexOf("[duration]") >= 0) {

                        //Date date=DateUtil.formatDate(dto.getReceivedDate(), "MM/dd/YYYY hh:mm a");
                        dateStr = DateUtil.dateToString(new Date(), "MM/dd/YYYY hh:mm a");
                        duration = DateUtil.getDateDiffFromCurrentDate(dto.getReceivedDate());

                    }
                    if (order.getPriceIncludingMargins() != null) {
                        drugCost = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getPriceIncludingMargins());
                    }
                    expirationDate = order.getRxExpiredDateStr() != null ? order.getRxExpiredDateStr() : "Not Specified";
                } catch (Exception e) {

                }
                //TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(orderDate);
                String messageSubject = campign.getSubject();
                campign.setSubject(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(messageSubject, order != null ? order.getId() : "0", null));
                campign.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campign.getPushNotification(), ""), order != null ? order.getId() : "0", null));

                CommonUtil.populateDecryptedOrderData(null, order);

                campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                campign.setSmstext(campign.getSmstext().replace("[ORDER_TYPE]", orderType).replace("[ORDER_DATE]", orderDate)
                        .replace("[DRUG_NAME]", drugName).replace("[DRUG_STRENGTH]", drugStrength)
                        .replace("[DRUG_TYPE]", drugType).replace("[DRUG_QTY]", drugQty)
                        .replace("[date_time]", dateStr)
                        .replace("[request_no]", orderId)
                        .replace("[duration]", duration)
                        .replace("[DR_NAME]", AppUtil.getSafeStr(order.getPrescriberName(), ""))
                        .replace("[DR_PHONE]", AppUtil.getSafeStr(order.getPharmacyPhone(), ""))
                        .replace("[RX_NUMBER]", AppUtil.getSafeStr(order.getRxNumber(), ""))
                        .replace("[SYS_RX_NUMBER]", AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "").length() > 0
                                ? AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), "") : "N/A")
                        .replace("[DAYS_SUPPLY]", order.getDaysSupply() != null ? order.getDaysSupply().toString() : "Not mentioned")
                        .replace("[REFILLS_REMAIN]", order.getRefillsRemaining() != null ? order.getRefillsRemaining().toString() : "Not mentioned")
                        .replace("[pharmacy_comments]", AppUtil.getSafeStr(pharmacyComments, ""))
                        .replace("( 255 characters)", "")
                        .replace("(255 characters)", "")
                        .replace("[generic_name]", genericName)
                        .replace("[RX_EXPIRATION]", order.getRxExpiredDateStr())
                        .replace("[LAST_FILLED_DATE]", AppUtil.getSafeStr(order.getLastFilledDate(), ""))
                        .replace("[DRUG_COST]", AppUtil.getSafeStr(drugCost, ""))
                        .replace("[DRUG_IMAGE]", (AppUtil.getSafeStr(order.getImagePath(), "").length() > 0
                                && CommonUtil.urlAuthorization(AppUtil.getSafeStr(order.getImagePath(), ""))
                                ? "<img id='drugImg' src=\'" + AppUtil.getSafeStr(order.getImagePath(), "")
                                + "\' width='30' height='30'/>" : ""))
                        .replace("[IMAGE_LNK]", (AppUtil.getSafeStr(order.getImagePath(), "").length() > 0
                                && CommonUtil.urlAuthorization(AppUtil.getSafeStr(order.getImagePath(), ""))
                                ? AppUtil.getSafeStr(order.getImagePath(), "#") : "#"))
                        .replace("[display_style]", order.getDrugDetail() != null
                                && AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "").length() > 0 ? "" : "display:none")
                        .replace("[points_cost]", order.getActualProfitShare() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getActualProfitShare(), "en", "US") : "$0.00")
                        .replace("[card_number]", "****" + CommonUtil.subStrCardNumber(AppUtil.getSafeStr(order.getCardNumber(), "")))
                        .replace("[final_payment]", AppUtil.roundOffNumberToCurrencyFormat(order.getFinalPayment() != null ? order.getFinalPayment() : 0d, "en", "US"))
                        //.replace("[INS_TYPE]", AppUtil.getSafeStr(order.getPaymentType(), "").equalsIgnoreCase("INSURANCE") ? "Y" : "N")
                        .replace("[INS_TYPE]", insType)
                        .replace("[guide_lnk]", order.getDrugDetail() != null
                                ? AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "#") : "#")
                        .replace("[SALES_TAX]", "N/A")
                        .replace("[COMPLIEANCE_REWARD]", AppUtil.getSafeStr(redeemPointsCostFld, "0.00"))
                        .replace("[OUT_OF_POCKET]", AppUtil.getSafeStr(ptCopayFld, "0.00"))
                        .replace("[Self_Pay_Price]", AppUtil.getSafeStr(acqCostFld, "0.00"))
                        .replace("[REF_ALLOWED]", refillAllowedFld));

//                if (AppUtil.getSafeStr(genericName, "").length() == 0
//                        && campign != null
//                        && AppUtil.getSafeStr(campign.getSmstext(), "").length() > 0) {
//                    campign.setSmstext(campign.getSmstext().replaceAll("(?i)Generic For", ""));
//                }
                ///////////////////////////////////////////////////////
                if (campign != null && AppUtil.getSafeStr(
                        campign.getSmstext(), "").indexOf("[delivery_method]") >= 0) {
                    String pref = this.consumerRegistrationService.populateDeliveryPreferenceAgainstSpecificOrder(orderId);
                    if (pref != null) {
                        message = campign.getSmstext();
                        message = message.replace("[delivery_method]", pref);
                        if (AppUtil.getSafeStr(pref, "").indexOf("2nd Day") >= 0) {
                            message = message.replace("[handling_fee]", "included");
                        } else {
                            message = message.replace("[handling_fee]", order.getHandLingFee() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getHandLingFee(), "en", "US") : "$0.00");
                        }
                        campign.setSmstext(message);
                    }
                }
                ///////////////////////////////////////////////////////

                patientProfileService.saveNotificationMessages(campign, patientId, orderId, campign.getIsCritical());
                if (AppUtil.getSafeStr(rootNode.path("lowerCostOption").asText(), "").equals("1")) {
                    consumerRegistrationService.updateOrderStatusNew(AppUtil.getSafeInt(orderId, 0), 16);
                }
                if (AppUtil.getSafeStr(rootNode.path("delOption").asText(), "").equals("1")) {
                    consumerRegistrationService.updateOrderStatus(AppUtil.getSafeInt(orderId, 0), pharmacyUser.getId(), 11);
                }
                if (AppUtil.getSafeStr(rootNode.path("autodelOrderOption").asText(), "").equals("1")) {

                    consumerRegistrationService.updateOrderDeletionAttributes(AppUtil.getSafeInt(orderId, 0), pharmacyUser.getId(), 16);

                }
            }

            ///////////////////////////////////////////
            NotificationPharmacyDTO notificationPharmacyDTO = populateNotificationPharmacyDTO(AppUtil.getSafeInt(orderId, 0), patientId, message, order);
            //notificationPharmacyDTO.setOlVersion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOlversion()));
            response = JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
            //////////////////////////////////////////

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: sendInAppMessage::  ", e);
        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }

    /////////////////////////////////////////////////////
    @RequestMapping(value = "/sendInAppMessageWithFile/{orderId}/{patientId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String sendInAppMessageWithFile(
            @PathVariable("orderId") String orderId, @PathVariable("patientId") int patientId,
            @RequestParam(value = "eventName", required = false) String eventName, @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "drug", required = false) String drug,
            @RequestParam(value = "qty", required = false) String drugQty, @RequestParam(value = "DRUG_STRENGTH", required = false) String drugStrength,
            @RequestParam(value = "DRUG_TYPE", required = false) String drugType,
            @RequestParam(value = "ORDER_TYPE", required = false) String orderType, @RequestParam(value = "isCritical", required = false) int critical,
            @RequestParam(value = "ORDER_DATE", required = false) String orderDate, @RequestParam(value = "PAYMENT", required = false) String payment,
            @RequestParam(value = "DELIVER_PREFERENCE", required = false) String preference,
            @RequestParam(value = "customMsg", required = false) String customMsg, @RequestParam(value = "customSubject", required = false) String customSub,
            @RequestParam(value = "pdfDocFile", required = false) MultipartFile pdfDocFile,
            HttpServletRequest request) {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            String duration = "";
            String pharmacyComments = "";
            String genericName = "";
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(message, eventName);
            int statusVal = 0;
            String fileName = "";
            String attachmentURL = "";
            //////////////////////;//////////////////////////////
            if (pdfDocFile != null && (!pdfDocFile.isEmpty())) {
                String path = CommonUtil.getDocumentPath("1");
                String destPath = CommonUtil.getDocumentDestinationPath("1");
                Date d = new Date();
                String datePart = DateUtil.dateToString(d, "yy-MM-dd hh:mm:ss");
                fileName = Constants.INSURANCE_CARD_URL + path + "custom_" + orderId + "_" + datePart + ".pdf";
                FileCopyUtils.copy(pdfDocFile.getBytes(), new File(destPath + "custom_" + orderId + "_" + datePart + ".pdf"));
                CommonUtil.executeCommand(Constants.COMMAND + path);
                attachmentURL = "<div><a href=\"" + fileName + "\"><img src=\"" + PropertiesUtil.getProperty("APP_PATH")
                        + "/resources/images/attachment.png" + "\"></a></div>";
                System.out.println("=============>ATTACHMENT URL " + attachmentURL + "<===============================");
                this.consumerRegistrationService.createOrderCustomDocument(fileName, 0, orderId);

                //drugDetailDTO.setPdfDocUrl(fileName);
            }
            ////////////////////////////////////////////////////
            if (campign != null && campign.getMessageId() != null) {
                if (CommonUtil.isNotEmpty(campign.getRequiredPatientResponse())) {
                    customSub = campign.getRequiredPatientResponse();
                    statusVal = 16;
                }
            }
            if (pharmacyUser != null) {
                this.consumerRegistrationService.updateOrderStatusAndHisotry(orderId, pharmacyUser.getId(), statusVal, customSub, "", CommonUtil.getPharmacyId(pharmacyUser));
//                this.consumerRegistrationService.updateOrderStatus(id, pharmacyUser.getId(), 2);
//                  this.consumerRegistrationService.updateOrderStatusAndHisotry(orderId, pharmacyUser.getId(), 16, customSub);
            }

            Order order = (Order) this.patientProfileService.getOrderById(new Order(), orderId);//.getObjectById(new Order(), orderId);
            CommonUtil.populateDecryptedOrderData(null, order);
            Integer isCritical = 0;

            if (campign != null) {
                if (AppUtil.getSafeStr(customMsg, "").length() > 0) {
                    campign.setSmstext(AppUtil.getSafeStr(customMsg, ""));
                }
//                if (AppUtil.getSafeStr(customMsg, "").length() == 0) {
                String dateStr = "", expirationDate = "", drugCost = "Not Specified";
               
                genericName = this.patientProfileService.loadDrugGeneric(drug);
                try {
                    TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(orderId,
                            CommonUtil.getPharmacyId((PharmacyUser) request.getSession().getAttribute("pharmacyUser")));
                    if (campign.getSmstext().indexOf("[date_time]") >= 0
                            || campign.getSmstext().indexOf("[duration]") >= 0) {

                        //Date date=DateUtil.formatDate(dto.getReceivedDate(), "MM/dd/YYYY hh:mm a");
                        dateStr = DateUtil.dateToString(dto.getReceivedDate(), "MM/dd/YYYY hh:mm a");
                        duration = DateUtil.getDateDiffFromCurrentDate(dto.getReceivedDate());

                    }
                    if (order.getPriceIncludingMargins() != null) {
                        drugCost = AppUtil.roundOffNumberToTwoDecimalPlaces(order.getPriceIncludingMargins());
                    }
                    //order.getRxExpiredDate() != null ? DateUtil.dateToString(order.getRxExpiredDate(), "MM-dd-yyyy") : "Not Specified";
                } catch (Exception e) {

                }
                //TransferRxQueueDTO dto = consumerRegistrationService.getOrderDetailListById(orderDate);
                String drugName = AppUtil.getProperDrugName(drug, drugType, drugStrength);
                campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
                campign.setSmstext(campign.getSmstext().replace("[ORDER_TYPE]", orderType).replace("[ORDER_DATE]", AppUtil.getSafeStr(orderDate, ""))
                        .replace("[DRUG_NAME]", drugName).replace("[DRUG_STRENGTH]", drugStrength)
                        .replace("[DRUG_TYPE]", drugType).replace("[DRUG_QTY]", drugQty)
                        .replace("[date_time]", dateStr)
                        .replace("[request_no]", orderId)
                        .replace("[duration]", duration)
                        .replace("[DR_NAME]", AppUtil.getSafeStr(order.getPrescriberName(), ""))
                        .replace("[DR_PHONE]", AppUtil.getSafeStr(order.getPharmacyPhone(), ""))
                        .replace("[RX_NUMBER]", AppUtil.getSafeStr(order.getRxNumber(), ""))
                        .replace("[DAYS_SUPPLY]", order.getDaysSupply() != null ? order.getDaysSupply().toString() : "Not mentioned")
                        .replace("[REFILLS_REMAIN]", order.getRefillsRemaining() != null ? order.getRefillsRemaining().toString() : "Not mentioned")
                        .replace("[pharmacy_comments]", pharmacyComments)
                        .replace("( 255 characters)", "")
                        .replace("(255 characters)", "")
                        .replace("[generic_name]", genericName)
                        .replace("[RX_EXPIRATION]", AppUtil.getSafeStr(expirationDate, ""))
                        .replace("[DRUG_COST]", AppUtil.getSafeStr(drugCost, ""))
                        .replace("[display_style]", order.getDrugDetail() != null
                                && AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "").length() > 0 ? "" : "display:none")
                        .replace("[guide_lnk]", order.getDrugDetail() != null
                                ? AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "#") : "#"));
                if (AppUtil.getSafeStr(fileName, "").length() > 0) {
                    campign.setSmstext(campign.getSmstext() + "<div>Associated File:</div>" + attachmentURL);

                }

                String messageSubject = campign.getSubject();
                campign.setSubject(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(messageSubject, order != null ? order.getId() : "0", null));
                campign.setPushNotification(patientProfileService.getMessageSubjectWithprocessedPlaceHolders(
                        AppUtil.getSafeStr(campign.getPushNotification(), ""), order != null ? order.getId() : "0", null));
//                if (customSub.length() > 0) {
//                    campign.setSubject(AppUtil.getSafeStr(customSub, ""));
//                }
                //patientProfileService.saveNotificationMessages(campign, patientId, orderId);
                patientProfileService.saveNotificationMessages(campign, patientId, orderId, campign.getIsCritical());
            }

            ///////////////////////////////////////////
            NotificationPharmacyDTO notificationPharmacyDTO = populateNotificationPharmacyDTO(Integer.parseInt(orderId), patientId, message, order);
            //notificationPharmacyDTO.setOlVersion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOlversion()));
            response = JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
            //////////////////////////////////////////

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: sendInAppMessage::  ", e);
        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }

    /////////////////////////////////////////////////////
    @RequestMapping(value = "/getInAppMsg", method = RequestMethod.POST)
    public @ResponseBody
    String getMsgTextService(
            @RequestBody String jsonRequest, HttpServletRequest request) {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode eventNode = rootNode.path("eventName");
            String eventName = eventNode.asText();

            //message
            JsonNode messageNode = rootNode.path("message");
            String message = messageNode.asText();

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(message, eventName);

            response = campign != null ? AppUtil.getSafeStr(campign.getSmstext(), "") : "";//JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
            //////////////////////////////////////////

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: ", e);
        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }
    ////////////////////////////////////////////////////

    @RequestMapping(value = "/orderReceipt/{id}", method = RequestMethod.GET)
    public ModelAndView getOrderReceipt(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("orderReceipt");
        List<NotificationMessages> messagesesList = patientProfileService.getNotificationMessagesListById(id);
        if (!CommonUtil.isNullOrEmpty(messagesesList)) {
            NotificationMessages notificationMessages = messagesesList.get(0);
            modelAndView.addObject("notificationMessages", notificationMessages);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/calculateFinalPtPay", method = RequestMethod.POST)
    public @ResponseBody
    String calculateFinalPtPay(@RequestBody String jsonRequest, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        String result = "";
        try {
            rootNode = objectMapper.readTree(jsonRequest);
            JsonNode eventNode = rootNode.path("redeemPoints");
            String redeemPoints = eventNode.asText();

            JsonNode patientIdNode = rootNode.path("patientId");
            Integer patientId = patientIdNode.asInt();

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode copayNode = rootNode.path("copay");
            String copay = copayNode.asText();
            result = patientProfileService.getCalculateFinalPtPay(redeemPoints, patientId, orderId, copay);
        } catch (IOException ex) {
            Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @RequestMapping(value = "/calculateDrugPrice", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String calculateDrugPrice(@RequestBody String jsonRequest, HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        rootNode = objectMapper.readTree(jsonRequest);

        JsonNode drugDetailIdNode = rootNode.path("drugDetailId");
        Long drugDetailId = drugDetailIdNode.asLong();

        JsonNode qtyNode = rootNode.path("qty");
        Integer qty = qtyNode.asInt();
        return consumerPortalService.calculateDrugPrice(drugDetailId, qty);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/retrievecampaign/", method = RequestMethod.POST)
    public @ResponseBody
    String retrieveCampaign(
            @RequestBody String jsonRequest, HttpServletRequest request) {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode eventNode = rootNode.path("eventName");
            String eventName = eventNode.asText();

            JsonNode messageNode = rootNode.path("message");
            String message = messageNode.asText();

            JsonNode orderNode = rootNode.path("orderId");
            String orderId = orderNode.asText();

            JsonNode redeemPointsCostNode = rootNode.path("actualProfitShare");
            String rewardPointCost = redeemPointsCostNode.asText();

            JsonNode ptOutOfPocketNode = rootNode.path("ptCopayFld");
            String ptOutOfPocket = ptOutOfPocketNode.asText();

            JsonNode selfPayPriceNode = rootNode.path("acqCostFld");
            String selfPayPrice = selfPayPriceNode.asText();
            CampaignMessages campaignMessages = new CampaignMessages();

            CampaignMessages campign = this.patientProfileService.getNotificationMsgs(message, eventName);
            if (campign == null && CommonUtil.isNullOrEmpty(campign.getMessageId())) {
                return objectMapper.writeValueAsString(campaignMessages);
            }

            campaignMessages.setIsCritical(campign.getIsCritical());
            campaignMessages.setSmstext(AppUtil.getSafeStr(campign.getSmsWebText(), "").length() > 0
                    ? campign.getSmsWebText() : campign.getSmstext());
            //////////////////////////////////////////////////////
            String drug = "", genericName = "";
            drug = rootNode.path("name").asText();
            String drugName = AppUtil.getProperDrugName(drug, rootNode.path("drugType").asText(), rootNode.path("drugStrength").asText());
            Order order = this.patientProfileService.getOrderById(new Order(), orderId);
            String duration = DateUtil.getDateDiffFromCurrentDate(DateUtil.stringToDate(rootNode.path("recievedDateFld").asText(), "MM-dd-YYYY"));
            String costStr = AppUtil.getSafeStr(rootNode.path("finalPaymentFld").asText(), "0.00");
            double drugCost = AppUtil.getSafeDouble(costStr, 0d);
            String smsText = "";
            campign.setSmstext(AppUtil.getSafeStr(campign.getSmstext(), ""));
            smsText = (campaignMessages.getSmstext().replace("[ORDER_DATE]", rootNode.path("recievedDateFld").asText())
                    .replace("[DRUG_NAME]", drugName)
                    .replace("[DRUG_STRENGTH]", rootNode.path("drugStrength").asText())
                    .replace("[DRUG_TYPE]", rootNode.path("drugType").asText())
                    .replace("[DRUG_QTY]", rootNode.path("drugQty").asText())
                    .replace("[date_time]", DateUtil.dateToString(new Date(), "MM/dd/yyyy hh:mm"))
                    .replace("[request_no]", rootNode.path("orderId").asText())
                    .replace("[duration]", duration)
                    .replace("[DR_NAME]", AppUtil.getSafeStr(rootNode.path("prescriberNameFld").asText(), "") + " " + AppUtil.getSafeStr(rootNode.path("prescriberLastName").asText(), ""))
                    .replace("[DR_PHONE]", AppUtil.getSafeStr(rootNode.path("prescriberPhoneFld").asText(), ""))
                    .replace("[RX_NUMBER]", AppUtil.getSafeStr(rootNode.path("systemGeneratedRxNumber").asText(), ""))
                    .replace("[DAYS_SUPPLY]", rootNode.path("daysSupplyFld").asText() != null ? rootNode.path("daysSupplyFld").asText() : "Not mentioned")
                    .replace("[generic_name]", genericName)
                    // .replace("[pharmacy_comments]", "")
                    .replace("[REFILLS_REMAIN]", AppUtil.getSafeStr(rootNode.path("refillRemain").asText(), "0"))
                    .replace("[RX_EXPIRATION]", AppUtil.getSafeStr(order.getRxExpiredDateStr(), "N/A"))
                    .replace("[year]", DateUtil.dateToString(new Date(), "YYYY"))
                    .replace("[date_number]", DateUtil.dateToString(new Date(), "dd"))
                    .replace("[month_name]", DateUtil.getMonthForInt(DateUtil.getMonthValue(new Date())))
                    .replace("[day_name]", DateUtil.getDayForInt(DateUtil.getDayOfWeek(new Date())))
                    .replace("[DRUG_COST]", AppUtil.roundOffNumberToCurrencyFormat(drugCost, "en", "US"))
                    .replace("[guide_lnk]", order != null && order.getDrugDetail() != null
                            ? AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "#") : "#")
                    .replace("[SALES_TAX]", "N/A")
                    .replace("[COMPLIEANCE_REWARD]", AppUtil.getSafeStr(rewardPointCost, "0.00"))
                    .replace("[OUT_OF_POCKET]", AppUtil.getSafeStr(ptOutOfPocket, "0.00"))
                    .replace("[Self_Pay_Price]", AppUtil.getSafeStr(selfPayPrice, "0.00"))
                    .replace("[final_payment]", costStr));

//         if(AppUtil.getSafeStr(genericName,"").length()==0 && 
//                   campaignMessages!=null && AppUtil.getSafeStr(smsText, "").length()>0)
//         {
//            smsText=smsText.replace("Generic For","");
//         }
            campaignMessages.setSmstext(smsText);
            if (AppUtil.getSafeStr(message, "").indexOf("[delivery_method]") >= 0) {
//                Order order = this.patientProfileService.findOrderById(orderId);
                String pref = this.consumerRegistrationService.populateDeliveryPreferenceAgainstSpecificOrder(orderId);
                if (pref != null) {
                    message = campign.getSmstext();
                    message = message.replace("[delivery_method]", pref);
                    if (AppUtil.getSafeStr(pref, "").indexOf("2nd Day") >= 0) {
                        message = message.replace("[handling_fee]", "included");
                    } else {
                        message = message.replace("[handling_fee]", order.getHandLingFee() != null ? AppUtil.roundOffNumberToCurrencyFormat(order.getHandLingFee(), "en", "US") : "$0.00");
                    }
                    campign.setSmstext(message);
                }
            }
            //.replace("[display_style]", order.getDrugDetail() != null
            //      && AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "").length() > 0 ? "" : "display:none")
            //.replace("[guide_lnk]", order.getDrugDetail() != null
            //      ? AppUtil.getSafeStr(order.getDrugDetail().getDrugDocUrl(), "#") : "#"));

            //////////////////////////////////////////////////////
            campaignMessages.setSubject(campign.getSubject());
            response = objectMapper.writeValueAsString(campaignMessages);//JsonResponseComposer.composeSuccessResponse(campaignMessages);
            //////////////////////////////////////////

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: ", e);
        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/estimateDrugPriceListUsingId", produces = "application/json",
            method = RequestMethod.POST)
    public @ResponseBody
    String estimateDrugPriceListUsingId(@RequestBody String jsonRequest, HttpServletRequest request,
            HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonNode rootNode;
            rootNode = objectMapper.readTree(jsonRequest);

            JsonNode idNode = rootNode.path("idParam");
            Long id = idNode.asLong();

            JsonNode qtyNode = rootNode.path("qtyParam");
            int qty = qtyNode.asInt();

            JsonNode basePriceNode = rootNode.path("basePriceParam");
            String basePriceStr = basePriceNode.asText().replace("$", "").replace(",", "");
            float basePrice = AppUtil.getSafeFloat(basePriceStr, 0f);

            JsonNode marginPercentNode = rootNode.path("marginPercentParam");
            String marginPercentStr = marginPercentNode.asText().replaceAll(",", "");
            float marginPercent = AppUtil.getSafeFloat(marginPercentStr, 0f);

            JsonNode pkgCostNode = rootNode.path("pkgCostParam");
            String pkgCostStr = pkgCostNode.asText().replace("$", "").replaceAll(",", "");
            float pkgCost = AppUtil.getSafeFloat(pkgCostStr, 0f);

            JsonNode unitMarginNode = rootNode.path("unitMarginParam");
            String unitMarginStr = unitMarginNode.asText().replace("$", "").replaceAll(",", "");
            float unitMargin = AppUtil.getSafeFloat(unitMarginStr, 0f);

            String json = "";
            DrugDetailDTO detailDTO = new DrugDetailDTO();
            detailDTO.setBasePrice(basePrice);
            detailDTO.setMarginPercent(marginPercent);
            detailDTO.setDefQty(qty);
            detailDTO.setAcqPrice(pkgCost);
            detailDTO.setUnitMarkupAmt(unitMargin);

            DrugDetailDTO dto = consumerPortalService.estimateDrugPriceListbyDrugId(id, detailDTO);
            if (dto != null) {
                json = new ObjectMapper().writeValueAsString(dto);
            }

            return json;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/udateMultiDrugPrices", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String
            udateMultiDrugPrices(@RequestBody String jsonRequest, HttpServletRequest request,
                    HttpServletResponse resp) throws IOException {

        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONObject jsonObj = new JSONObject(jsonRequest);
            JSONArray jsonArray = jsonObj.getJSONArray("jsonArr");

            //then get the type for list and parse using gson as
            Type listType = new TypeToken<List<DrugDetailDTO>>() {
            }.getType();
            List<DrugDetailDTO> lstDetail = new Gson().fromJson(jsonArray.toString(), listType);
            this.consumerPortalService.updateMultiRxRecords(lstDetail);
            return new ObjectMapper().writeValueAsString("Udated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/estimateDrugPriceListUsingGCN", produces = "application/json",
            method = RequestMethod.POST)
    public @ResponseBody
    String estimateDrugPriceListUsingGCN(@RequestBody String jsonRequest, HttpServletRequest request,
            HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            JsonNode rootNode;
            rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugGCNNode = rootNode.path("searchParam");
            String drugGCN = drugGCNNode.asText();//request.getParameter("searchParam");
            String json = "";

            List<DrugDetailDTO> list = consumerPortalService.estimateDrugPriceListbyGCN(drugGCN);
            if (list != null) {
                json = new ObjectMapper().writeValueAsString(list);
            }

            return json;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/estimateDrugPriceListUsingDrugName", method = RequestMethod.POST)
    public @ResponseBody
    String estimateDrugPriceListUsingDrugName(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode;
//        rootNode = objectMapper.readTree(jsonRequest);

//        JsonNode drugGCNNode = rootNode.path("searchParam");
        try {
            JsonNode rootNode;
            rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugGCNNode = rootNode.path("searchParam");
            String drug = drugGCNNode.asText();
            String json = "";
            List<DrugDetailDTO> list = consumerPortalService.estimateDrugPriceListbyDrugName(drug);
            if (list != null) {
                json = new ObjectMapper().writeValueAsString(list);
            }

            return json;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/estimateDrugPriceList", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String estimateDrugPriceList(@RequestBody String jsonRequest, HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        rootNode = objectMapper.readTree(jsonRequest);

        JsonNode drugGCNNode = rootNode.path("drugGCN");
        Long drugGCN = drugGCNNode.asLong();
        JsonNode dosageFormNode = rootNode.path("dosageForm");
        String dosageForm = dosageFormNode.asText();
        JsonNode brandnameNode = rootNode.path("brandname");
        String brandname = brandnameNode.asText();

        JsonNode qtyNode = rootNode.path("qty");
        Integer qty = qtyNode.asInt();

        JsonNode drugStrengthNode = rootNode.path("drugStrength");
        String drugStrength = drugStrengthNode.asText();
        return consumerPortalService.estimateDrugPriceList(drugGCN, qty, dosageForm, brandname, drugStrength);
    }

    @RequestMapping(value = "/populateDrugDetailByBrandOrGenericName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugDetailByBrandOrGenericName(@RequestBody String jsonRequest, HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonRequest);
        JsonNode nameNode = rootNode.path("name");
        String drugName = nameNode.asText();
        Set<DrugBrandDTO> list = patientProfileService.getDrugBrandsListByBrandOrGenericName(drugName);
        List<String> drugBrandList = new ArrayList<>();
        for (DrugBrandDTO dTO : list) {
            drugBrandList.add(dTO.getDrugBrandName());
        }
        return objectMapper.writeValueAsString(drugBrandList);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/queuePatientDetailPage/{patientId}/{dependentId}/{selectedTab}")
    public ModelAndView linkPatientHistory2(@PathVariable("patientId") int patientId, @PathVariable("dependentId") int dependentId, @PathVariable("selectedTab") String selectedTab, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage2");
        populateSessionData(request, modelAndView, selectedTab);
        int userId = 0;
        try {
            BaseDTO baseDTO = new BaseDTO();
            String status = request.getParameter("status");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            boolean search = false;
            if (status != null && !status.isEmpty()) {
                baseDTO.setStatus(status);
                search = true;
            }
            if (fromDate != null && !fromDate.isEmpty()) {
                baseDTO.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
                search = true;
            }

            if (toDate != null && !toDate.isEmpty()) {
                baseDTO.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
                search = true;
            }
            baseDTO.setDependentId(dependentId);
            userId = getPharmacyUserId(request, userId);
            if (search) {
                CommonUtil.loadPageData(modelAndView, patientId, "0", patientProfileService);
                baseDTO.setPatientId(patientId);
                modelAndView.addObject("patientOrderQueue", consumerRegistrationService.getOrdersListByDate(userId, baseDTO));
            } else {
                populatePatientHistory2Data(userId, patientId, dependentId, modelAndView);
            }
            modelAndView.addObject("baseDTO", baseDTO);
            String filterLabel = baseDTO != null ? AppUtil.getSafeStr(baseDTO.getStatus(), "All Requests") : "All Requests";
            modelAndView.addObject("filterLabel", filterLabel);
            modelAndView.addObject("showPtRespQDiv", "none");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: linkPatientHistory2:: ", e);
        }
        /**
         * patient info
         */
        //  modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(patientId));
        //loadPageData(modelAndView, patientId, oid);
        return modelAndView;

    }

    public int getPharmacyUserId(HttpServletRequest request, int userId) {
        //consumerRegistrationService.setOrderStatusWithId("Opened",oid);
        if (request.getSession().getAttribute("pharmacyUser") != null) {
            PharmacyUser user = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            if (user != null) {
                userId = user.getId();
            }
        }
        return userId;
    }

    public void populatePatientHistory2Data(int userId, int patientId, ModelAndView modelAndView) {
        List<TransferRxQueueDTO> orderLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, 0, "");//Shipped");
//            List<TransferRxQueueDTO> interpretedLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, Constants.ORDER_STATUS.INTERPRETED_IMAGE);
//            List<TransferRxQueueDTO> cancelledLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, Constants.ORDER_STATUS.CANCELLED);
//            List<TransferRxQueueDTO> waitingPtResponseLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, Constants.ORDER_STATUS.WAITING_PT_RESPONSE);
//            List<TransferRxQueueDTO> processedRequestsLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, Constants.ORDER_STATUS.FILLED);
//            List<String> orderStatusList = new ArrayList<>();
//            orderStatusList.add(Constants.ORDER_STATUS.SHIPPED);
//            orderStatusList.add(Constants.ORDER_STATUS.DELIVERY);
//            orderStatusList.add(Constants.ORDER_STATUS.PICKUP_AT_PHARMACY);
//            List<TransferRxQueueDTO> shipdeliveryLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, orderStatusList);

        modelAndView.addObject("patientOrderQueue", orderLst);
//            modelAndView.addObject("count", orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0);
//            modelAndView.addObject("interpretedLst", interpretedLst);
//            modelAndView.addObject("interpretedCount", interpretedLst != null && interpretedLst.size() > 0 ? interpretedLst.size() : 0);
//            modelAndView.addObject("patientCancelOrderQueue", cancelledLst);
//            modelAndView.addObject("cancelActiveCount", cancelledLst != null && cancelledLst.size() > 0 ? cancelledLst.size() : 0);
//            modelAndView.addObject("waitingPtResponseLst", waitingPtResponseLst);
//            modelAndView.addObject("waitingPtResponseCount", waitingPtResponseLst != null && waitingPtResponseLst.size() > 0 ? waitingPtResponseLst.size() : 0);
//            modelAndView.addObject("processedRequestsLst", processedRequestsLst);
//            modelAndView.addObject("processedRequestsCount", processedRequestsLst != null && processedRequestsLst.size() > 0 ? processedRequestsLst.size() : 0);
//            modelAndView.addObject("shipdeliveryLst", shipdeliveryLst);
//            modelAndView.addObject("shipdeliveryCount", shipdeliveryLst != null && shipdeliveryLst.size() > 0 ? shipdeliveryLst.size() : 0);
//            
//            int count1 = orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0;
//            int count2 = interpretedLst != null && interpretedLst.size() > 0 ? interpretedLst.size() : 0;
//            int total = count1 + count2;
//            modelAndView.addObject("total", total);
        CommonUtil.loadPageData(modelAndView, patientId, "0", patientProfileService);
    }

    public void populateSessionData(HttpServletRequest request, ModelAndView modelAndView, String selectedTab) {
        request.getSession().setAttribute("Title", "PHARMACY QUEUE"); //"Pending Transfer RX Queue");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        //Save last logged on date
        if (pharmacyUser != null && pharmacyUser.getId() > 0) {
            consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());
            SessionBean newSessionBean = null;
            try {
                newSessionBean = ConsumerRegistrationController.populateSessionBean(pharmacyUser, new Date(), permissionService);
                newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
                newSessionBean.setUserNameDB("PharmacyPortal");

                newSessionBean.setUserName(pharmacyUser.getEmail());
                newSessionBean.setUserId(pharmacyUser.getId());
                newSessionBean.setRole(pharmacyUser.getRole());
                newSessionBean.setCurrentDate(new Date());
                Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
                //PharmacyLookup pharmacylookup = consumerRegistrationService.PharmacylookupbyId(par.getPharmacyLookup().getId());

                request.getSession().setAttribute("PharmacyTitle", par.getTitle());
                request.getSession().setAttribute("Address", par.getAddress1());
                request.getSession().setAttribute("Phone", par.getPhone());
                request.getSession().setAttribute("Fax", par.getPhone());
                request.getSession().setAttribute("Email", par.getEmail());

                request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
                request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
                String currentUserName = "";
                currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();

                newSessionBean.setCurrentUserName(currentUserName);
                newSessionBean.setSelectedTab(selectedTab);
                newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.error("Exception:: populateSessionData", e);
            }
            newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
            request.getSession().setAttribute("sessionBeanPortal", newSessionBean);
            request.getSession().setAttribute("recordOpened", "true");
        }
        //modelAndView.setViewName("transferRxQueue");
        //modelAndView.addObject("listRxTransfer", consumerRegistrationService.getTranferRxQueue());
        request.getSession().setMaxInactiveInterval(900000);
        pageContents(modelAndView);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/queuePatientDetailPage", method = RequestMethod.POST)
    public ModelAndView getQueuePatientDetailPage(@ModelAttribute BaseDTO baseDTO, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("pharmacyQueuePage2");
        int userId = 0;
        try {
            populateSessionData(request, modelAndView, baseDTO.getSelectedTab());
            userId = getPharmacyUserId(request, userId);
            CommonUtil.loadPageData(modelAndView, baseDTO.getPatientId(), "0", patientProfileService);
            modelAndView.addObject("patientOrderQueue", consumerRegistrationService.getOrdersListByDate(userId, baseDTO));
            modelAndView.addObject("baseDTO", baseDTO);
            String filterLabel = baseDTO != null ? AppUtil.getSafeStr(baseDTO.getStatus(), "All Requests") : "All Requests";
            if (filterLabel.equalsIgnoreCase("Pending Review")) {
                filterLabel = "Interpreted Images";
            } else if (filterLabel.equalsIgnoreCase("Filled")) {
                filterLabel = "Processed Requests";
            } else if (filterLabel.equalsIgnoreCase("Shipped")) {
                filterLabel = "Shipping Delivery";
            } else if (filterLabel.equalsIgnoreCase("Response Received")) {
                filterLabel = "Patient Response";
            } else if (filterLabel.equalsIgnoreCase("Pending")) {
                filterLabel = "Unverified Images/Pending";
            }
            modelAndView.addObject("baseDTO", baseDTO);
            modelAndView.addObject("filterLabel", filterLabel);
        } catch (Exception e) {
            logger.error("Exception:: getQueuePatientDetailPage", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/cancelOrder/{orderId}", method = RequestMethod.POST)
    public @ResponseBody
    String cancelOrderById(@PathVariable("orderId") String orderId, HttpServletRequest request) {
        String response = "";
        try {

            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            if (pharmacyUser != null) {
                consumerRegistrationService.updateOrderStatus(AppUtil.getSafeInt(orderId, 0), pharmacyUser.getId(), 11);
            }

        } catch (Exception e) {
            logger.error("Exception:: getQueuePatientDetailPage", e);
        }
        return response;
    }

    @RequestMapping(value = "/refillOrders/", method = RequestMethod.POST)
    public @ResponseBody
    String refillOrders(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String response = "";
        try {
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode actionTypeNode = rootNode.path("actionType");
            int actionType = actionTypeNode.asInt();
            if (actionType == 1) {
                JsonNode commaSeparatedOrderNode = rootNode.path("commaSeparatedOrderNode");
                String commaSeparatedOrderIds = commaSeparatedOrderNode.asText();

                if (pharmacyUser != null) {
                    response = patientProfileService.saveRefillModuleFromWeb(commaSeparatedOrderIds, pharmacyUser.getId());
                }
            } else {
                JsonNode commaSeparatedOrderNode = rootNode.path("orderId");
                String orderId = commaSeparatedOrderNode.asText();
                JsonNode orderStatusNode = rootNode.path("orderStatusId");
                int orderStatusId = orderStatusNode.asInt();
                JsonNode acqCostFldNode = rootNode.path("acqCostFld");
                Double rxAcqCost = acqCostFldNode.asDouble();
                JsonNode ptCopayFldNode = rootNode.path("ptCopayFld");
                Double originalPtCopay = ptCopayFldNode.asDouble();
                JsonNode reimbFldNode = rootNode.path("reimbFld");
                Double rxReimbCost = reimbFldNode.asDouble();
                JsonNode refillOverridenNode = rootNode.path("refillOverriden");
                Integer refillOverriden = refillOverridenNode.asInt();
                JsonNode totalRedeemPointsIdNode = rootNode.path("totalRedeemPointsId");
                Integer profitSharePoint = totalRedeemPointsIdNode.asInt();
                JsonNode redeemPointsCostFldNode = rootNode.path("redeemPointsCostFld");
                Double actualProfitShare = redeemPointsCostFldNode.asDouble();
                JsonNode finalPaymentFeeFldNode = rootNode.path("finalPaymentFeeFld");
                Double paymentExcludingDelivery = finalPaymentFeeFldNode.asDouble();

                if (pharmacyUser != null) {
                    response = patientProfileService.saveRefill(orderId, rxAcqCost, originalPtCopay, rxReimbCost, profitSharePoint, actualProfitShare, paymentExcludingDelivery, pharmacyUser.getId(), orderStatusId, refillOverriden);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:: refillOrders", e);
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());
        }
        return new ObjectMapper().writeValueAsString(response);
    }

    @RequestMapping(value = "/multirxOrder/{patientId}/{orderId}/{waiveDeliveryFee}", method = RequestMethod.POST)
    public @ResponseBody
    String getMultiRxOrder(@PathVariable("patientId") int patientId, @PathVariable("orderId") int orderId,
            @PathVariable("waiveDeliveryFee") int waiveDeliveryFee, HttpServletRequest request) {
        String response = "";
        try {

            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            if (pharmacyUser != null) {
                List<OrderDetailDTO> orders = consumerRegistrationService.getMultiRxOrdersByPatientId(orderId, patientId, waiveDeliveryFee, CommonUtil.getPharmacyId(pharmacyUser));
                if (orders != null && orders.size() > 0) {
                    response = JsonResponseComposer.composeSuccessResponse(orders);
                }
            }

        } catch (Exception e) {
            logger.error("Exception:: getMultiRxOrder", e);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/newOnlineRxDetail/{patientId}/{dependentId}")
    public ModelAndView newOnlineRxDetail(@PathVariable("patientId") int pid, @PathVariable("dependentId") int dependentId, @ModelAttribute Pharmacy pharmacy, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/onlineRxDetail");
        pageContents(modelAndView);
        request.getSession().setAttribute("Title", "RX ORDER DETAIL");
        PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
        try {
            sessionBean = (SessionBean) request.getSession().getAttribute("sessionBeanPortal");
            if (pharmacyUser != null && pharmacyUser.getId() > 0) {
                consumerRegistrationService.setPharmacyUserLastLoggedInTime(pharmacyUser.getId());
                SessionBean newSessionBean = ConsumerRegistrationController.populateSessionBean(pharmacyUser, new Date(), permissionService);
                newSessionBean.setPharmacy(pharmacyUser.getPharmacy());
                newSessionBean.setUserNameDB("PharmacyPortal");
                newSessionBean.setUserName(pharmacyUser.getEmail());
                newSessionBean.setUserId(pharmacyUser.getId());
                newSessionBean.setRole(pharmacyUser.getRole());
                newSessionBean.setCurrentDate(new Date());
                Pharmacy par = consumerRegistrationService.getPharmacyById(pharmacyUser.getPharmacy().getId());
                request.getSession().setAttribute("PharmacyTitle", par.getTitle());
                request.getSession().setAttribute("Address", par.getAddress1());
                request.getSession().setAttribute("Phone", par.getPhone());
                request.getSession().setAttribute("Fax", par.getPhone());
                request.getSession().setAttribute("Admin", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "admin"));
                request.getSession().setAttribute("Staff", consumerRegistrationService.getUserCountByPharmacyIdAndRole(par.getId(), "user"));
                String currentUserName = "";
                currentUserName = pharmacyUser.getFirstName() + " " + pharmacyUser.getLastName();
                newSessionBean.setCurrentUserName(currentUserName);
                try {
                    newSessionBean.setLastLoggedOn(DateUtil.dateToString(pharmacyUser.getLastLoggedOn(), "MM-dd-yyyy HH:mm"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                newSessionBean.setDatePattern("EEEEE, MMMMM, dd, yyyy");
                if (sessionBean != null) {
                    newSessionBean.setSelectedTab(sessionBean.getSelectedTab());
                }
                request.getSession().setAttribute("sessionBeanPortal", newSessionBean);
            }
            request.getSession().setMaxInactiveInterval(900000);
            if (!CommonUtil.isNullOrEmpty(pid) && pid != 0) {
                TransferRxQueueDTO dto = new TransferRxQueueDTO();//consumerRegistrationService.getOrderDetailListById(id);
                modelAndView.addObject("order", dto);
                modelAndView.addObject("dependentId", dependentId);
                // PatientProfileMembers dependents = new PatientProfileMembers();
                PatientDependantDTO dependents = new PatientDependantDTO();
                if (dependentId > 0) {
//                    dependents = (PatientProfileMembers) this.patientProfileService.getObjectById(
//                            new PatientProfileMembers(), dependentId);
                   

                }
                modelAndView.addObject("dependentFlag", dependents != null && dependents.getId() != null && dependents.getId() > 0);
                
                modelAndView.addObject("adultFlag", dependents != null && dependents.getId() != null && dependents.getId() > 0
                        && dependents.getIsAdult() != null && dependents.getIsAdult());
                modelAndView.addObject("status", AppUtil.getSafeStr(request.getParameter("status"), "online"));
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                List<OrderDetailDTO> orderLst =new ArrayList<>();// this.patientProfileService.getPatientProfilesHistory(pid, "");//Shipped");
                int count1 = orderLst != null && orderLst.size() > 0 ? orderLst.size() : 0;
                modelAndView.addObject("total", count1);
                ///////////////////////////////////////////////////////////////////////////////////////////////////

            }
            CommonUtil.loadPageData(modelAndView, pid, "0", patientProfileService);
            List questionList = this.patientProfileService.getQuestionAnswerList("0");
            modelAndView.addObject("questions", questionList);
            BaseDTO baseDTO = new BaseDTO();
            String status = request.getParameter("status");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            if (status != null && !status.isEmpty()) {
                baseDTO.setStatus(status);
            }
            if (fromDate != null && !fromDate.isEmpty()) {
                baseDTO.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
            }
            if (toDate != null && !toDate.isEmpty()) {
                baseDTO.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse(toDate));
            }
            modelAndView.addObject("baseDTO", baseDTO);
            modelAndView.addObject("showPtRespQDiv", "none");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveOnlineOrder/", method = RequestMethod.POST)
    public @ResponseBody
    String saveOnlineOrder(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode changeStatusNode = rootNode.path("changeStatus");
            Integer changeStatus = changeStatusNode.asInt();

            JsonNode statusValNode = rootNode.path("statusBit");
            Integer statusVal = statusValNode.asInt();

            //message
            JsonNode messageNode = rootNode.path("message");
            String message = messageNode.asText();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");

            /////////////////////////////////////////////////////////
            JsonNode rxNumberNode = rootNode.path("rxNumberFld");
            String rxNumber = rxNumberNode.asText();

            JsonNode drugBrandIdNode = rootNode.path("name");
            String brandName = drugBrandIdNode.asText();

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();

            JsonNode strengthNode = rootNode.path("drugStrength");
            String strength = strengthNode.asText();

            JsonNode qtyNode = rootNode.path("drugQty");
            String qty = qtyNode.asText();

            JsonNode daysSupplyNode = rootNode.path("daysSupplyFld");
            String daysSupply = daysSupplyNode.asText();

            JsonNode refillsAllowedNode = rootNode.path("refillAllowedFld");
            String refillsAllowed = refillsAllowedNode.asText();

            JsonNode refillUsedNode = rootNode.path("refillUsedFld");
            String refillUsed = refillUsedNode.asText();

            JsonNode paymentModeNode = rootNode.path("paymentFld");
            String paymentMode = paymentModeNode.asText();

            JsonNode patientNameNode = rootNode.path("patientNameFld");
            String patientName = patientNameNode.asText();

            JsonNode pharmacyRxNoNode = rootNode.path("pharmacyRxNoFld");
            String pharmacyRxNo = pharmacyRxNoNode.asText();

            JsonNode pharmacyNameNode = rootNode.path("pharmacyNameFld");
            String pharmacyName = pharmacyNameNode.asText();

            JsonNode pharmacyPhoneNode = rootNode.path("pharmacyPhoneFld");
            String pharmacyPhone = pharmacyPhoneNode.asText();

            JsonNode prescriberNameNode = rootNode.path("prescriberNameFld");
            String prescriberName = prescriberNameNode.asText();

            JsonNode prescriberPhoneNode = rootNode.path("prescriberPhoneFld");
            String prescriberPhone = prescriberPhoneNode.asText();

            JsonNode prescriberNPINode = rootNode.path("prescriberNPIFld");
            String prescriberNPI = prescriberNPINode.asText();

            JsonNode acqCostNode = rootNode.path("acqCostFld");
            String acqCost = acqCostNode.asText();

            JsonNode reimbNode = rootNode.path("reimbFld");
            String reimb = reimbNode.asText();

            JsonNode ptCopayNode = rootNode.path("ptCopayFld");
            String ptCopay = ptCopayNode.asText();

            JsonNode finalPaymentNode = rootNode.path("finalPaymentFld");
            String finalPayment = finalPaymentNode.asText();

            JsonNode deliverycarrierNode = rootNode.path("deliverycarrier");
            String deliverycarrier = deliverycarrierNode.asText();

            JsonNode traclingnoNode = rootNode.path("traclingno");
            String traclingno = traclingnoNode.asText();

            JsonNode clerkNode = rootNode.path("clerk");
            String clerk = clerkNode.asText();

            JsonNode commentsNode = rootNode.path("orderComments");
            String comments = commentsNode.asText();

            JsonNode redeemPointsNode = rootNode.path("redeemPoints");
            String redeemPoints = redeemPointsNode.asText();

            JsonNode redeemPointsCostNode = rootNode.path("redeemPointsCost");
            String redeemPointsCost = redeemPointsCostNode.asText();

            JsonNode mfrCostNode = rootNode.path("mfrCost");
            String mfrCost = mfrCostNode.asText();

//            JsonNode profitShareNode = rootNode.path("profitShare");
//            String profitShare = profitShareNode.asText();
            JsonNode insuranceTypeNode = rootNode.path("insuranceType");
            String insuranceType = insuranceTypeNode.asText();

//            JsonNode priceIncludingMarginsNode = rootNode.path("priceIncludingMargins");
//            String priceIncludingMargins = priceIncludingMarginsNode.asText();
            JsonNode rxDateNode = rootNode.path("rxDate");
            String rxDateStr = rxDateNode.asText();

            JsonNode expireDateNode = rootNode.path("rxExpireDate");
            String expireDateStr = expireDateNode.asText();

            JsonNode profitMarginNode = rootNode.path("profitMargin");
            String profitMargin = profitMarginNode.asText();

            JsonNode actualProfitShareNode = rootNode.path("actualProfitShare");
            String actualProfitShare = actualProfitShareNode.asText();

            JsonNode profitSharePointNode = rootNode.path("profitSharePoint");
            String profitSharePoint = profitSharePointNode.asText();

            JsonNode sellingPriceNode = rootNode.path("sellingPrice");

//            JsonNode olVersionNode = rootNode.path("olversion");
//            LocalDateTime ldt = LocalDateTime.parse(olVersionNode.asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            Date olversion = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            JsonNode handlingFeeNode = rootNode.path("handlingFee");
            String handlingFee = handlingFeeNode.asText();

            JsonNode patientIdNode = rootNode.path("patientId");
            int patientId = patientIdNode.asInt();

            JsonNode dependentIdNode = rootNode.path("dependentId");
            int dependentId = dependentIdNode.asInt();

            ////////////////////////////////////////////////////////
//            JsonNode multiRxNode = rootNode.path("multiRx");
//            boolean multiRx = multiRxNode.asBoolean();
//            ArrayNode multiRxIdsNode = (ArrayNode) rootNode.get("multiRxIds");
//            List<String> multiRxIds = new ObjectMapper().convertValue(multiRxIdsNode, ArrayList.class);
//          Order order = orderService.getOrderById("" + id);//, olversion);
            Order order = new Order();//orderService.getOrderById("" + id, olversion);
            PatientProfile profile = patientProfileService.getPatientProfileById(patientId);
            
//            orderChain.set
            if (order != null) {
                order.setOnlineOrder(true);
                order.setDrugName(brandName);
                order.setDrugType(type);
                order.setStrength(strength);
                order.setQty(qty);
                order.setCreatedBy(0);
                order.setCreatedOn(new Date());
                order.setRefillsAllowed(Integer.parseInt(refillsAllowed));
                order.setRefillsRemaining(Integer.parseInt(refillUsed));
                order.setPaymentMode(paymentMode);
                if (profile != null) {
                    order.setFirstName(profile.getFirstName());
                    order.setLastName(profile.getLastName());
                }
                order.setPatientName(patientName);
                order.setPharmacyName(pharmacyName);
                order.setPharmacyPhone(pharmacyPhone);
                order.setPrescriberName(prescriberName);
                order.setPrescriberPhone(prescriberPhone);
                order.setPrescriberNPI(prescriberNPI);

                order.setRxAcqCost(AppUtil.getSafeDouble(acqCost.replace("$", ""), 0d));
                order.setRxReimbCost(AppUtil.getSafeDouble(reimb.replace("$", ""), 0d));
                order.setProfitMargin(AppUtil.getSafeDouble(profitMargin.replace("$", ""), 0d));
                order.setProfitSharePoint(AppUtil.getSafeInt(profitSharePoint.replace("$", ""), 0));
                order.setActualProfitShare(AppUtil.getSafeFloat(actualProfitShare.replace("$", ""), 0f));
                order.setOriginalPtCopay(AppUtil.getSafeDouble(ptCopay.replace("$", ""), 0d));
                order.setFinalPayment(AppUtil.getSafeDouble(finalPayment.replace("$", ""), 0d));

               

                OrderStatus orderStatus = (OrderStatus) patientProfileService.getObjectById(new OrderStatus(), Constants.ORDER_STATUS_UNASSIGNED_ID);
                order.setNextRefillFlag("0");
                if (orderStatus != null && orderStatus.getId() != null) {
                    order.setOrderStatus(orderStatus);
                }

                
                if (AppUtil.getSafeStr(order.getShippingAddress(), "").length() == 0) {
                    PatientDeliveryAddress patientDeliveryAddress = patientProfileService.getPatientDeliveryDefaultAddress(patientId);
                    if (patientDeliveryAddress != null && patientDeliveryAddress.getId() != null) {
                        logger.info("Set shipping address info. ");
                        order.setStreetAddress(patientDeliveryAddress.getAddress());
                        order.setCity(patientDeliveryAddress.getCity());
                        order.setZip(patientDeliveryAddress.getZip());
                        order.setAddressLine2(patientDeliveryAddress.getDescription());
                        order.setApartment(patientDeliveryAddress.getApartment());
                        if (patientDeliveryAddress.getState() != null && patientDeliveryAddress.getState().getId() != null) {
                            order.setState(patientDeliveryAddress.getState().getName());
                        }
                    }
                }
                order.setPatientComments(comments);
                order.setRedeemPoints(redeemPoints);
                order.setRedeemPointsCost(AppUtil.getSafeDouble(redeemPointsCost, 0d));
                order.setPriceIncludingMargins(sellingPriceNode.asDouble());
                if (CommonUtil.isNotEmpty(rxDateStr)) {
                    Date rxDate = DateUtil.stringToDate(rxDateStr, "MM/dd/yyyy");
                    if (rxDate != null) {
                        order.setRxDate(rxDate);
                    }
                }
                order.setOldRxNumber(AppUtil.getSafeStr(pharmacyRxNo, ""));
                order.setHandLingFee(AppUtil.getSafeDouble(handlingFee, 0d));
                
//                order.setPaymentMode(insuranceType);
                order.setPaymentMode(AppUtil.getSafeStr(paymentMode, "").equalsIgnoreCase("SELF PAY") ? "Cash" : "INS");
                int statusValCopy = statusVal;
                CampaignMessages campaignMessages = null;
                consumerRegistrationService.getDrugDetail(brandName, type, strength, order, qty);
               
                DeliveryPreferences pref = (DeliveryPreferences) this.patientProfileService.getObjectById(new DeliveryPreferences(),
                        rootNode.path("pref").asInt());
                order.setDeliveryPreference(pref);
               
//                orderService.save(orderChain);
//                orderService.save(order);

                ///////////////////////////////////////////
                NotificationPharmacyDTO notificationPharmacyDTO = new NotificationPharmacyDTO();
                notificationPharmacyDTO.setMessage(message);
                notificationPharmacyDTO.setSendDate("" + new Date());
                notificationPharmacyDTO.setOrderId(order.getId());
                notificationPharmacyDTO.setStatus("" + order.getOrderStatus().getId());
                ///     notificationPharmacyDTO.setOlVersion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOlversion()));
                response = JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
                //////////////////////////////////////////
            } else {
                //@TODO send message
                throw new Exception("You have an old version of record, Please refresh the page to get the updated record.");
            }
            ////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            //response = JsonResponseComposer.composeFailureResponse(e.getMessage());
            e.printStackTrace();
            resp.setStatus(500);
            return new ObjectMapper().writeValueAsString(e.getMessage());

        }
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }

    @RequestMapping(value = "/patientDetailByMobileNumber/{mobileNumber}", method = RequestMethod.POST)
    public @ResponseBody
    String getPatientDetailByMobileNumber(@PathVariable("mobileNumber") String mobileNumber, HttpServletRequest request) {
        String response = "";
        try {
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            if (pharmacyUser != null) {
                response = JsonResponseComposer.composeSuccessResponse(consumerRegistrationService.getPatientProfile(mobileNumber));
            }
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/populateMultiRxOrdersDetail/{orderId}", method = RequestMethod.POST)
    public @ResponseBody
    String populateMultiRxOrdersDetail(@PathVariable("orderId") String orderId,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            List lst = this.consumerRegistrationService.getMultiRxOrdersStatusWiseByPatientId(orderId, CommonUtil.getPharmacyId(pharmacyUser));
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: populateMultiRxOrdersDetail", e);
            e.printStackTrace();
        }
        return response;
    }

    ///////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateProcessedOrdersDetail/{orderId}", method = RequestMethod.POST)
    public @ResponseBody
    String populateProcessedOrdersDetail(@PathVariable("orderId") String orderId,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            List lst = this.consumerRegistrationService.getProcessedOrdersByPatientId(orderId, CommonUtil.getPharmacyId(pharmacyUser));
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateOrdersWithSameDeliverPref/{orderId}", method = RequestMethod.POST)
    public @ResponseBody
    String populateOrdersWithSameDeliverPref(@PathVariable("orderId") String orderId,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            List lst = this.consumerRegistrationService.getOrdersWithSamePref(orderId,
                    CommonUtil.getPharmacyId(pharmacyUser), Constants.orderIdsOtherThanShipped);
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }

    //////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateShippingQueue/{orderId}", method = RequestMethod.POST)
    public @ResponseBody
    String populateShippingQueueDetail(@PathVariable("orderId") String orderId,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            List lst = this.consumerRegistrationService.getShippingQueueByPatientId(orderId, CommonUtil.getPharmacyId(pharmacyUser));
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }

    //////////////////////////////////////////////////////////////
    private void populatePatientHistory2Data(int userId, int patientId, int dependentId, ModelAndView modelAndView) {
        List<TransferRxQueueDTO> orderLst = this.consumerRegistrationService.getTranferRxQueueForPage2(userId, patientId, dependentId, "");
        modelAndView.addObject("patientOrderQueue", orderLst);
        CommonUtil.loadPageData(modelAndView, patientId, "0", patientProfileService);
    }

    @RequestMapping(value = "/calculateShippingFee", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String calculateShippingFee(@RequestBody String jsonRequest, HttpServletRequest request) {
        String response = "";
        TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode zipCodeNode = rootNode.path("zipCode");
            String zipCode = zipCodeNode.asText();

            JsonNode deliveryPreferencesNode = rootNode.path("deliveryPreferencesId");
            Integer deliveryPreferencesId = deliveryPreferencesNode.asInt();

            JsonNode outOfPocketNode = rootNode.path("outOfPocket");
            String outOfPocket = outOfPocketNode.asText();
            if (outOfPocket.contains(",")) {
                outOfPocket = outOfPocket.replace(",", "");
            }
            Order order = patientProfileService.getOrderById(orderId);
            if (order == null || CommonUtil.isNullOrEmpty(order.getId())) {
                logger.info("No order found against this id " + orderId);
                response = "No order found against this id " + orderId;
                return JsonResponseComposer.composeFailureResponse(response);
            }
            if (order.getPatientProfile() == null || CommonUtil.isNullOrEmpty(order.getPatientProfile().getPatientProfileSeqNo())) {
                logger.info("No patient associate against this order " + orderId);
                response = "No patient associate against this order " + orderId;
                return JsonResponseComposer.composeFailureResponse(response);
            }
            if (deliveryPreferencesId == 3) {
                response = "0.00";
            } else if (order.getDeliveryPreference() != null && order.getDeliveryPreference().getId() != null) {
                if (Objects.equals(order.getDeliveryPreference().getId(), deliveryPreferencesId)) {
                    response = CommonUtil.getDecimalFormat(order.getHandLingFee());
                } else {
                    response = patientProfileService.getZipCodeCalculations(zipCode, order.getPatientProfile().getPatientProfileSeqNo(), deliveryPreferencesId);
                }
            } else {
                response = patientProfileService.getZipCodeCalculations(zipCode, order.getPatientProfile().getPatientProfileSeqNo(), deliveryPreferencesId);
            }
            Float totalPayment = AppUtil.getSafeFloat(response, 0f) + AppUtil.getSafeFloat(outOfPocket, 0f);
            transferRxQueueDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(totalPayment, "en", "US"));
            transferRxQueueDTO.setHandlingFeeStr(response);
        } catch (Exception e) {
            logger.error("Exception# calculateShippingFee# ", e);
        }
        return JsonResponseComposer.composeSuccessResponse(transferRxQueueDTO);
    }

    ////////////////////////////////////////////
    @RequestMapping(value = "/validateZip/{zip}/{state}",
            method = RequestMethod.POST)
    public @ResponseBody
    String populateStateByZip(@PathVariable("zip") String zip,
            @PathVariable("state") String state,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");
            List lst = this.consumerRegistrationService.getStatesByZip(
                    zip, state);
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public ModelAndView FAQ() {
        ModelAndView modelAndView = new ModelAndView("portal/faq");
        populateLoginPageData(modelAndView);
        return modelAndView;
    }

    private void populateLoginPageData(ModelAndView modelAndView) {
        modelAndView.addObject("pharmacy", new Pharmacy());
        modelAndView.addObject("pharmacyLookup", new PharmacyLookup());
       // modelAndView.addObject("pharmacyTypeList", consumerRegistrationService.getAllPharmacyTypes());
    }

    @RequestMapping(value = "/multiRxDelivery/{orderId}/{patientId}", method = RequestMethod.GET)
    public ModelAndView multiRxDelivery(@PathVariable("orderId") String id, @PathVariable("patientId") int pid,
            @ModelAttribute Pharmacy pharmacy, RedirectAttributes redirectAttributes,
            @RequestParam(value = "multiProcessingOrders", required = false) String multiParam,
            @ModelAttribute("itemIds") String itemIds, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("portal/multiRxDelivery");
        populateRxdetail3(modelAndView, request, id, pid, itemIds, multiParam);
        return modelAndView;
    }

    @RequestMapping(value = "/processMultiRxOrders", method = RequestMethod.POST)
    public @ResponseBody
    String processMultiRxOrders(@RequestBody String jsonRequest, HttpServletRequest request, HttpServletResponse resp) throws Exception {
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode patientIdNode = rootNode.path("patientId");
            Integer patientId = patientIdNode.asInt();

            JsonNode deliverycarrierNode = rootNode.path("deliverycarrier");
            String deliverycarrier = deliverycarrierNode.asText();

            JsonNode traclingnoNode = rootNode.path("traclingno");
            String traclingno = traclingnoNode.asText();

            JsonNode clerkNode = rootNode.path("clerk");
            String clerk = clerkNode.asText();

            JsonNode shipDateNode = rootNode.path("shipDate");
            String shipDate = shipDateNode.asText();

            JsonNode finalPaymentFldNode = rootNode.path("finalPaymentFld");
            String finalPaymentFld = finalPaymentFldNode.asText();

            JsonNode outOfPocketFIdNode = rootNode.path("outOfPocketFId");
            String outOfPocketFId = outOfPocketFIdNode.asText();

            JsonNode handlingFeeFldNode = rootNode.path("handlingFeeFld");
            String handlingFeeFld = handlingFeeFldNode.asText();

            JsonNode statusValNode = rootNode.path("statusVal");
            Integer statusVal = statusValNode.asInt();

            JsonNode numberOfRecordNode = rootNode.path("numberOfRecord");
            Integer numberOfRecord = numberOfRecordNode.asInt();

            PharmacyUser pharmacyUser = (PharmacyUser) request.getSession().getAttribute("pharmacyUser");

            Order order = orderService.getOrderById(orderId);
            if (order == null) {
                throw new Exception("No Order found against this " + orderId);
            }
            //check poa expire or not
            response = patientProfileService.isPOAExpire(order.getId());
            JsonResponse jsonResponse = new Gson().fromJson(response, JsonResponse.class);
            if (jsonResponse != null && jsonResponse.getStatus().equalsIgnoreCase(Constants.JSON_STATUS.FAIL)) {
                return response;
            }
            if (order != null) {
               
                Integer pharmacyId = CommonUtil.getPharmacyId(pharmacyUser);

                if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
                    order.setPharmacy(new Pharmacy(pharmacyId));
                    if (request.getSession().getAttribute("sessionBeanPortal") != null) {
                        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBeanPortal");
                        order.setRxPrefix(AppUtil.getSafeStr(sessionBean.getPharmacyAbbr(), "") + "-");
                    }
                }

                order = consumerRegistrationService.updateOrderStatus(order, patientId, deliverycarrier, traclingno, clerk, shipDate,
                        finalPaymentFld, outOfPocketFId, handlingFeeFld, statusVal, pharmacyUser);
                order.setFinalPayment(AppUtil.getSafeDouble(finalPaymentFld, 0d));
                order.setPaymentExcludingDelivery(AppUtil.getSafeDouble(outOfPocketFId, 0d));
//                CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs(
//                        "Rx Delivered", Constants.PHARMACY_NOTIFICATION);
//                CommonUtil.populateDecryptedOrderData(null, order);
                String message = "";
                // message = this.sendPharmacyQueueMsgs(order, campaignMessages, AppUtil.getSafeInt(orderId, 0), request, message, null, order.getDrugName(), order.getDrugType(), order.getStrength(), order.getQty(), 0d, patientId, numberOfRecord, numberOfRecord);
                NotificationPharmacyDTO notificationPharmacyDTO = populateNotificationPharmacyDTO(AppUtil.getSafeInt(orderId, 0), patientId, message, order);
                response = JsonResponseComposer.composeSuccessResponse(notificationPharmacyDTO);
            }
        } catch (IOException e) {
            logger.error("Exception# ", e);
            //response = JsonResponseComposer.composeFailureResponse(e.getMessage());
            e.printStackTrace();
            resp.setStatus(500);
            if (e instanceof java.net.ConnectException) {
                return new ObjectMapper().writeValueAsString("Order shipped successfully but due to some connection problem to messaging server, shipment message couldn't be sent to patient this time.");
            }
            return new ObjectMapper().writeValueAsString(e.getMessage());

        }
        return response;
    }
}
