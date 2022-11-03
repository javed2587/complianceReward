package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.SupportService;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.LoginDTO;
import com.ssa.cms.dto.PatientDeliveryAddressDTO;
import com.ssa.cms.dto.PatientDependantDTO;
import com.ssa.cms.dto.PatientInsuranceDetailsDTO;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.dto.PatientProfileUpdateRequestResponseDTO;
import com.ssa.cms.dto.RewardHistoryDTO;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.DeleteRecord;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.PatientNotification;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.PatientProfileNotes;
import com.ssa.cms.model.Practices;
import com.ssa.cms.model.RewardHistory;
import com.ssa.cms.service.DoDirectPayment;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.servlet.PMSGenericTextFlowServlet;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.PropertiesUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author msheraz
 */
@Controller
@RequestMapping("/patient")
public class PatientProfileController implements Serializable {
  
    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private MessageSource messageSource;
    private String message;
    private static final Logger logger = Logger.getLogger(PatientProfileController.class);
    @Autowired
    PMSGenericTextFlowServlet genericTextFlowServlet;
    SessionBean sessionBean;
    @Autowired
    private SupportService supportDelegate;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.USA_DATE_FORMATE);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView viewRegistrationPageHandler(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("patientprofile/patientinfo");
        String communicationId = request.getParameter("from");
        String statusCode = patientProfileService.isCommunicationIdExist(communicationId);
        if (statusCode.equalsIgnoreCase(StatusEnum.COMPLETED.getValue()) || statusCode.equalsIgnoreCase(StatusEnum.IN_PROGRESS.getValue())) {
            modelAndView.addObject("message", "Patient insurance profile information already submitted against this communication id.");
            modelAndView.setViewName("/displaymessage");
            return modelAndView;
        } else if (statusCode.equalsIgnoreCase(StatusEnum.STOPPED.getValue())) {
            modelAndView.addObject("message", "You are not authorize to access this page, because you have Opted Out from this Program.");
            modelAndView.setViewName("/displaymessage");
            return modelAndView;
        }
        PatientProfile patientProfile = new PatientProfile();
        Object obj = request.getSession().getAttribute("patientProfile");
        if (obj != null) {
            patientProfile = (PatientProfile) obj;
        } else {
            patientProfile.setGender("M");
        }
        patientProfile.setMobileNumber(communicationId);
        modelAndView.addObject("patientProfile", patientProfile);
        populateDropDownlist(modelAndView);
        return modelAndView;
    }
    
    private void populateDropDownlist(ModelAndView modelAndView) {
        modelAndView.addObject("states", patientProfileService.getStates());
    }
    
    @RequestMapping(value = "/creditcardinfo", method = RequestMethod.POST)
    public ModelAndView viewCreditCardPage(@ModelAttribute @Valid PatientProfile patientProfile, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("patientprofile/creditcardinfo");
        request.getSession().setAttribute("patientProfile", patientProfile);
        populateDropDownlist(modelAndView);
        modelAndView.addObject("patientProfile", patientProfile);
        return modelAndView;
    }
    
    @RequestMapping(value = "/creditcardinfo", method = RequestMethod.GET)
    public ModelAndView viewCreditCardInfoPageHandler(@ModelAttribute PatientProfile patientProfile, HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("patientProfile");
        if (obj != null) {
            patientProfile = (PatientProfile) obj;
        }
        return viewCreditCardPage(patientProfile, request);
    }
    
    @RequestMapping(value = "/uploadcard", method = RequestMethod.POST)
    public ModelAndView uploadCardPageLoader(@ModelAttribute @Valid PatientProfile patientProfile, BindingResult result, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("patientprofile/uploadcreditcard");
        // get patient basic info of page 1
        Object obj = request.getSession().getAttribute("patientProfile");
        if (obj != null) {
            PatientProfile patientProfile1 = (PatientProfile) obj;
            
            patientProfile = patientProfile1;
        }
        modelAndView.addObject("patientProfile", patientProfile);
        patientProfile.setCardHolderRelation("Cardholder");
        patientProfile.setAllergyStatus("None");
        request.getSession().setAttribute("patientProfile", patientProfile);

        //validate credit card info
        

        //validating...
        DoDirectPayment payment = new DoDirectPayment();
//        DoDirectPaymentResponseType response = payment.authorizationRequest(paymentInfo.getCardType(),
//                paymentInfo.getCardNumber(), paymentInfo.getExpiryDate(),
//                paymentInfo.getCardHolderName(), paymentInfo.getCvvNumber());
//        
//        logger.info("DoDirectPaymentResponseType: " + response);
//        if (!(response.getAck().getValue().equalsIgnoreCase("success") || response.getAck().getValue().equalsIgnoreCase("SuccessWithWarning"))) {
//            List<ErrorType> errorList = response.getErrors();
//            modelAndView.addObject("errorMessage", errorList.get(0).getLongMessage());
//            modelAndView.setViewName("patientprofile/creditcardinfo");
//        }
        //validation ends...

        return modelAndView;
    }
    
    @RequestMapping(value = "/uploadimage/{target}", method = RequestMethod.POST)
    public boolean uploadInsuranceCardImage(HttpServletRequest httpRequest, @PathVariable String target) {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
        Set set = request.getFileMap().entrySet();
        Iterator i = set.iterator();
        
        MultipartFile multipartFile = null;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            multipartFile = (MultipartFile) me.getValue();
            break;
        }
        
        Object obj = request.getSession().getAttribute("patientProfile");
        if (obj != null) {
            PatientProfile patientProfile = (PatientProfile) obj;
            request.getSession().setAttribute("patientProfile", patientProfile);
        }
        return true;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView savePatientInfoHandler(@ModelAttribute @Valid PatientProfile patientProfile, BindingResult result, HttpServletRequest request, HttpServletResponse response, @RequestParam("insuranceCardFront") MultipartFile file, @RequestParam("insuranceCardBack") MultipartFile backFile) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("patientprofile/success");
        // get patient basic info of page 1 & 2
        Object obj = request.getSession().getAttribute("patientProfile");
        if (obj != null) {
            PatientProfile patientProfile1 = (PatientProfile) obj;
            
            patientProfile1.setCardHolderRelation(patientProfile.getCardHolderRelation());
            patientProfile1.setAllergyStatus(patientProfile.getAllergyStatus());
            patientProfile1.setAllergies(patientProfile.getAllergies());
            patientProfile = patientProfile1;
        }
        boolean isSaved = patientProfileService.savePatientInfo(patientProfile);
        if (isSaved) {
            logger.info("After Save Patient info send message and Phone Number: " + patientProfile.getMobileNumber());
            request.setAttribute("message", Constants.Pt_Info_Submit);
            request.setAttribute("from", patientProfile.getMobileNumber());
            genericTextFlowServlet.doGet(request, response);
            //remove patientprofile after successfully save
            if (obj != null) {
                request.getSession().removeAttribute("patientProfile");
            }
            return modelAndView;
        } else {
            modelAndView.addObject("patientProfile", patientProfile);
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.setViewName("patientprofile/uploadcreditcard");
            return modelAndView;
        }
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView patientProfileList() {
        ModelAndView modelAndView = new ModelAndView("patientprofilelist");
        modelAndView.addObject("patientlist", patientProfileService.getPatientProfileList());
        return modelAndView;
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView viewPatientInfo(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("viewpatientinfo");
        modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(id));
        return modelAndView;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updatePatientInfoHandler(@ModelAttribute PatientProfile patientProfile, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("viewpatientinfo");
        boolean isUpdate = patientProfileService.updatePatientProfile(patientProfile);
        if (isUpdate) {
            logger.info("Patient Status: " + patientProfile.getStatus() + "Communication id is: " + patientProfile.getMobileNumber());
            if (patientProfile.getStatus().equalsIgnoreCase("Approved") && patientProfile.getMobileNumber() != null) {
                logger.info("After Save Patient info send message and Phone Number: " + patientProfile.getMobileNumber() + " Qualify Amount is: ");
                request.setAttribute("message", Constants.PTInfo_Approved);
                request.setAttribute("from", patientProfile.getMobileNumber());
                genericTextFlowServlet.doGet(request, response);
            }
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            return new ModelAndView("redirect:/patient/list");
        } else {
            modelAndView.addObject("patientProfile", patientProfileService.getPatientProfileById(patientProfile.getPatientProfileSeqNo()));
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
    }
    
    @RequestMapping(value = "/frontSideCard/{id}", method = RequestMethod.GET)
    public void getFrontCard(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        PatientProfile patientProfile = patientProfileService.getPatientProfileById(id);
        response.setContentType("image/*");
        InputStream in1 = null;
//        if (patientProfile.getInsuranceCardFront() != null) {
//            in1 = new ByteArrayInputStream(patientProfile.getInsuranceCardFront());
//        }
        IOUtils.copy(in1, response.getOutputStream());
    }
    
    @RequestMapping(value = "/backSideCard/{id}", method = RequestMethod.GET)
    public void getBackSideCard(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        PatientProfile patientProfile = patientProfileService.getPatientProfileById(id);
        response.setContentType("image/*");
        InputStream in1 = null;
//        if (patientProfile.getInsuranceCardBack() != null) {
//            in1 = new ByteArrayInputStream(patientProfile.getInsuranceCardBack());
//        }
        IOUtils.copy(in1, response.getOutputStream());
    }
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView patientProfileList1() {
        ModelAndView modelAndView = new ModelAndView("patientprofilelist_1");
        modelAndView.addObject("patientlist", patientProfileService.getPatientProfileList());
        return modelAndView;
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView viewPatientProfileInfo(@PathVariable("id") Integer id) {
        
        ModelAndView modelAndView = new ModelAndView("patientdetails");
        try {
            modelAndView.addObject("rewardLevelList", patientProfileService.getPatientRewardLevelList());
            
            LoginDTO patientProfile = patientProfileService.getPatientProfileDataById(id);
            
            List<RewardHistory> lst = this.patientProfileService.getPatientRewardHistory(id);
            List<RewardHistoryDTO> dtoLst = new ArrayList();
            Long balance = getPointBalanceAndList(dtoLst, lst);
            
            List<Order> listofOrders = patientProfile.getOrders();
            if (listofOrders.size() > 0) {
                listofOrders = new ArrayList<>();
                for (Order order : patientProfile.getOrders()) {
                    CommonUtil.populateDecryptedOrderData(null, order);
                    listofOrders.add(order);
                }
            }
            patientProfile.setOrders(listofOrders);
            
            formatePatientProfile(patientProfile);
            modelAndView.addObject("patientProfile", patientProfile);
            modelAndView.addObject("reward", dtoLst);
            modelAndView.addObject("balance", balance);
            List<Order> listOfOrder = patientProfileService.getProcessedOrdersByPatientId(id);
            modelAndView.addObject("totalRxProgram", CommonUtil.isNullOrEmpty(listOfOrder) ? 0 : listOfOrder.size());
            modelAndView.addObject("listOfPtInsurances", patientProfileService.getPatientInsuranceCards(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //modelAndView.addObject("patientProfileNotesList", patientProfileService.getPatientProfileNotesList(id));
        return modelAndView;
    }
    
    private Long getPointBalanceAndList(List<RewardHistoryDTO> dtoLst, List<RewardHistory> rewardDBList) throws Exception {
        Long balance = 0L;
        if (dtoLst == null) {
            dtoLst = new ArrayList<>();
        }
        for (RewardHistory rewardHistory : rewardDBList) {
            RewardHistoryDTO dto = new RewardHistoryDTO();
            dto.setId(rewardHistory.getId());
            dto.setPatientId(rewardHistory.getPatientId());
            dto.setDescription(rewardHistory.getDescription());
            dto.setType(rewardHistory.getType());
            if (rewardHistory.getPoint() != null && rewardHistory.getType() != null) {
                dto.setRedeemedPoints(AppUtil.getSafeStr(rewardHistory.getType(), "").equalsIgnoreCase("MINUS") ? rewardHistory.getPoint() : 0);
                dto.setAwardedPoints(AppUtil.getSafeStr(rewardHistory.getType(), "").equalsIgnoreCase("PLUS") ? rewardHistory.getPoint() : 0);
            }
            dto.setCreatedDate(rewardHistory.getCreatedDate() != null
                    ? DateUtil.dateToString(rewardHistory.getCreatedDate(), "MM/dd/yyyy hh:mm a") : "-");
            dtoLst.add(dto);
            if (AppUtil.getSafeStr(rewardHistory.getType(), "").equalsIgnoreCase("PLUS")) {
                balance = balance + rewardHistory.getPoint();
            } else {
                balance = balance - (rewardHistory.getPoint() == null ? 0 : rewardHistory.getPoint());
            }
        }
        return balance;
    }
    
    private void formatePatientProfile(LoginDTO patientProfile) {
        
        for (int i = 0; i < patientProfile.getOrders().size(); i++) {
            
            String str_drugTypeDb = AppUtil.getSafeStr(patientProfile.getOrders().get(i).getDrugType(), "");
            String str_drugType = "";
            if (str_drugTypeDb.equalsIgnoreCase(Constants.DRUG_TYPE.CAPSULE)) {
                str_drugType = "(CAP)";
            } else if (str_drugTypeDb.equalsIgnoreCase(Constants.DRUG_TYPE.TABLET)) {
                str_drugType = "(TAB)";
            }
            
            String str_formatDrugName = patientProfile.getOrders().get(i).getDrugName() + " " + patientProfile.getOrders().get(i).getStrength() + " " + str_drugType;
            patientProfile.getOrders().get(i).setDrugName(str_formatDrugName);
        }
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRecord(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (patientProfileService.deleteRecord(id)) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        return new ModelAndView("redirect:/patient/detail");
    }
    
    @RequestMapping(value = "/savePatientProfileHealth", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String savePatientProfileHealth(@RequestBody String patientProfileHealthJson) {
        
        logger.info("!!!!!  Request Received for upate health pat ::::  " + patientProfileHealthJson);
        
        String patientProfileUpdateJson = "";
        try {
            
            ObjectMapper om = new ObjectMapper();
            PatientProfileUpdateRequestResponseDTO patientProfileUpdateRequestResponseDTO = om.readValue(patientProfileHealthJson, PatientProfileUpdateRequestResponseDTO.class);
            
            patientProfileUpdateRequestResponseDTO.setStatus(Constants.JSON_STATUS.SUCCESS);
            patientProfileUpdateRequestResponseDTO.setStatuscode(Constants.JSON_STATUS.CODE_SUCCESS);
            
            patientProfileUpdateJson = om.writeValueAsString(patientProfileUpdateRequestResponseDTO);
            
        } catch (Exception ex) {
            logger.error("Exception: PatientProfileService -> updatePatientProfileHealth  ", ex);
        }
        
        logger.info("!!!!!  Send response back to client ::::  " + patientProfileUpdateJson);
        return patientProfileUpdateJson;
    }
    
    @RequestMapping(value = "/getPatientShippingAddress", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String getPatientShippingAddress(@RequestBody String json) {
        //return patientProfileService.getPatientShippingAddress(id);
        logger.info("!!!!!!!!!!!!!**********************  request received json ::: " + json);
        Integer i_shippingAddress = 0;
        try {
            ObjectMapper om = new ObjectMapper();
            PatientDeliveryAddressDTO patientDeliveryAddressDTO = om.readValue(json, PatientDeliveryAddressDTO.class);
            i_shippingAddress = patientDeliveryAddressDTO.getAddressId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return patientProfileService.getPatientDeliveryAddressById(i_shippingAddress);
    }

//    @RequestMapping(value = "/savePatientShippingAddress", method = RequestMethod.POST)
//    public @ResponseBody
//    boolean savePatientShippingAddress(@RequestBody PatientAddress patientAddress, HttpServletRequest request) {
//        return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
//    }
    @RequestMapping(value = "/savePatientShippingAddress", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String savePatientShippingAddress(@RequestBody String jsonRequest, HttpServletRequest request) {
        logger.info("json request @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper om = new ObjectMapper();
            PatientDeliveryAddressDTO patientDeliveryAddressDTO = om.readValue(jsonRequest, PatientDeliveryAddressDTO.class);
            return patientProfileService.updatePatientDeliveryAddress(patientDeliveryAddressDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }
    
    @RequestMapping(value = "/updatePatientAllergies", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String updatePatientInfoAllergies(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper om = new ObjectMapper();
            PatientProfileUpdateRequestResponseDTO patientProfileUpdateRequestResponseDTO = om.readValue(jsonRequest, PatientProfileUpdateRequestResponseDTO.class);
            return patientProfileService.updatePatientProfileAllergies(patientProfileUpdateRequestResponseDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }
    
    @RequestMapping(value = "/savePatientInsurance", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String savePatientInsurance(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request @@@@@@@@@@@@@@@@@  " + jsonRequest);
        String str_response = "";
        try {
            ObjectMapper om = new ObjectMapper();
            PatientInsuranceDetailsDTO patientInsuranceDetailsDTO = om.readValue(jsonRequest, PatientInsuranceDetailsDTO.class);
            boolean bSaveed = patientProfileService.saveUpdatePatientInsuranceDetails(patientInsuranceDetailsDTO);
            if (bSaveed) {
                str_response = JsonResponseComposer.composeSuccessResponse(patientInsuranceDetailsDTO);
                return str_response;
            } else {
                str_response = JsonResponseComposer.composeFailureResponse("Faile to save Patient Insurance Details");
            }
        } catch (Exception ex) {
            str_response = JsonResponseComposer.composeFailureResponse("Internal Server Error");
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return str_response;
    }
    
    @RequestMapping(value = "/getPatientInsurance/{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String getPatientInsurance(@PathVariable Long id) throws ServletException, IOException {
        logger.info("Get patient insurance by patient profile id @@@@@@@@@@@@@@@@@  " + id);
        String str_response = "";
        try {
            PatientInsuranceDetailsDTO patientInsuranceDetailsDTO = patientProfileService.getPatientInsuranceDetailsById(id);
            str_response = JsonResponseComposer.composeSuccessResponse(patientInsuranceDetailsDTO);
        } catch (Exception ex) {
            str_response = JsonResponseComposer.composeFailureResponse("Internal Server Error");
        }
        return str_response;
    }
    
    @RequestMapping(value = "/savePatientNotes", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Boolean savePatientNotes(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request @@@@@@@@@@@@@@@@@  " + jsonRequest);
        System.out.println("Recieved a Hit!");
        try {
            ObjectMapper om = new ObjectMapper();
            PatientProfileNotes patientProfileNotes = om.readValue(jsonRequest, PatientProfileNotes.class);
            if (patientProfileNotes.getPtProfileId() == null) {
                logger.error("Patient Profile id is null");
                return false;
            }
            patientProfileNotes.setCreatedBy(sessionBean.getUserId());
            patientProfileNotes.setPatientProfile(new PatientProfile(patientProfileNotes.getPtProfileId()));
            boolean bSaveed = patientProfileService.savePatientProfileNotes(patientProfileNotes);
            return bSaveed;
        } catch (Exception ex) {
            logger.error("Exception::Save Patient Notes:: ", ex);
            ex.printStackTrace();
        }
        return null;
    }

    /*
        @RequestMapping(value = "/getPatientShippingAddress", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String getPatientShippingAddress(@RequestBody String json) {
        //return patientProfileService.getPatientShippingAddress(id);
        logger.info("!!!!!!!!!!!!!**********************  request received json ::: " + json);
        Integer i_shippingAddress = 0;
        try {
            ObjectMapper om = new ObjectMapper();
            PatientDeliveryAddressDTO patientDeliveryAddressDTO = om.readValue(json, PatientDeliveryAddressDTO.class);
            i_shippingAddress = patientDeliveryAddressDTO.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return patientProfileService.getPatientDeliveryAddressById(i_shippingAddress);
    }

     */
    @RequestMapping(value = "/checkPatientIdExists", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    Boolean checkPatientIdExists(@RequestBody String json) {
        
        logger.info("!!!!!!!!!!!!!**********************  request received json ::: " + json);
        boolean exists = false;
        Integer patientId = 0;
        try {
            ObjectMapper om = new ObjectMapper();
            PatientProfile p;
            p = om.readValue(json, PatientProfile.class);
            PatientProfile patientProfile = patientProfileService.getPatientProfileById(p.getPatientProfileSeqNo());
            if (patientProfile != null && patientProfile.getPatientProfileSeqNo() != null) {
                exists = true;
            }
            System.out.println("Checking ID: " + exists);
        } catch (Exception e) {
            logger.info("Exception::", e);
        }
        return exists;
    }
    
    @RequestMapping(value = "/getPatientDependant/{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String getPatientDependant(@PathVariable Integer id) throws ServletException, IOException {
        logger.info("Get patient insurance by patient profile id @@@@@@@@@@@@@@@@@  " + id);
        String str_response = "";
        try {
            
            PatientProfile patientProfile = patientProfileService.getPatientProfileById(id);
            List<PatientDependantDTO> lst_patientDependantDTO = patientDependants(patientProfile);
            str_response = JsonResponseComposer.composeSuccessResponse(lst_patientDependantDTO);
        } catch (Exception ex) {
            str_response = JsonResponseComposer.composeFailureResponse("Internal Server Error");
        }
        return str_response;
    }
    
    private List<PatientDependantDTO> patientDependants(PatientProfile patientProfile) {
        
        List<PatientDependantDTO> lst_patientDependantDTO = new ArrayList<>();
       
        return lst_patientDependantDTO;
    }
    
    @RequestMapping(value = "/patientNotifiction", method = RequestMethod.POST)
    public @ResponseBody
    boolean savePatientNotifiction(@RequestBody String jsonRequest, HttpServletRequest request) {
        boolean str_response = false;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            
            JsonNode patientIdNode = rootNode.path("patientProfileId");
            Integer i_patientId = patientIdNode.asInt();
            
            JsonNode phoneNumberNode = rootNode.path("phoneNumber");
            String phoneNumber = phoneNumberNode.asText();
            
            JsonNode messageNode = rootNode.path("message");
            String str_message = messageNode.asText();
            PatientNotification notification = new PatientNotification();
            notification.setPatientProfile(new PatientProfile(i_patientId));
            notification.setPhoneNumber(phoneNumber);
            notification.setMessage(str_message);
            notification.setDateSent(new Date());
            notification.setIsRead(Boolean.FALSE);
            notification.setCreatedBy(sessionBean.getUserId());
            notification.setCreatedOn(new Date());
            str_response = patientProfileService.save(notification);
        } catch (Exception e) {
            logger.info("Exception:: " + e);
        }
        return str_response;
    }
    
    @RequestMapping(value = "/populateProcessedOrdersDetail/{patientId}", method = RequestMethod.POST)
    public @ResponseBody
    String populateProcessedOrdersDetail(@PathVariable("patientId") Integer patientId,
            HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List lst = this.patientProfileService.getProcessedOrdersByPatientId(patientId);
            response = objectMapper.writeValueAsString(lst);
        } catch (Exception e) {
            logger.error("Exception:: getPatientDetailByMobileNumber", e);
            e.printStackTrace();
        }
        return response;
    }
    
    @RequestMapping(value = "/patientBasicReport", method = RequestMethod.GET)
    public ModelAndView patientBasicReport() {
        ModelAndView modelAndView = new ModelAndView("patientBasicReport");
        modelAndView.addObject("baseDTO", new BaseDTO());
        modelAndView.addObject("btnSearchDisable", "disabled");
        return modelAndView;
    }
    
    @RequestMapping(value = "/patientBasicReport", method = RequestMethod.POST)
    public ModelAndView viewPatientBasicReport(@ModelAttribute BaseDTO baseDTO, HttpServletRequest request) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("patientBasicReport");
        if (baseDTO.getFromDate() != null) {
            baseDTO.setFromDate(DateUtil.formatDate(baseDTO.getFromDate(), Constants.USA_DATE_FORMATE));
        }
        if (baseDTO.getToDate() != null) {
            baseDTO.setToDate(DateUtil.formatDate(baseDTO.getToDate(), Constants.USA_DATE_FORMATE));
        }
        modelAndView.addObject("list", patientProfileService.generatePatientBasicReport(baseDTO));
        modelAndView.addObject("btnSearchDisable", "");
        return modelAndView;
    }
    
    @RequestMapping(value = "/patientEnhancedReport", method = RequestMethod.GET)
    public ModelAndView patientEnhancedReport() {
        ModelAndView modelAndView = new ModelAndView("patientEnhancedReport");
        modelAndView.addObject("baseDTO", new BaseDTO());
        modelAndView.addObject("btnSearchDisable", "disabled");
        return modelAndView;
    }
    
    @RequestMapping(value = "/patientEnhancedReport", method = RequestMethod.POST)
    public ModelAndView viewPatientEnhancedReport(@ModelAttribute BaseDTO baseDTO, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("patientEnhancedReport");
        modelAndView.addObject("list", patientProfileService.generatePatientBasicReport(baseDTO));
        modelAndView.addObject("btnSearchDisable", "");
        return modelAndView;
    }
    
    @RequestMapping(value = "/exportPdf", method = RequestMethod.GET)
    public ModelAndView exportPdf(@RequestParam(value = "reportName", required = false) String reportName, @RequestParam(value = "fromDate", required = false) Date fromDate,
            @RequestParam(value = "toDate", required = false) Date toDate, @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "ptName", required = false) String ptName, @RequestParam(value = "email", required = false) String email) {
        BaseDTO baseDTO = new BaseDTO();
        if (fromDate != null) {
            baseDTO.setFromDate(DateUtil.endDateFormat(fromDate));
        }
        if (toDate != null) {
            baseDTO.setToDate(DateUtil.endDateFormat(toDate));
        }
        baseDTO.setPhoneNumber(phoneNumber);
        baseDTO.setPatientName(ptName);
        baseDTO.setEmail(email);
        ModelAndView modelAndView = new ModelAndView("pdfView", "list", patientProfileService.generatePatientBasicReport(baseDTO));
        modelAndView.addObject("key", reportName);
        modelAndView.addObject("btnSearchDisable", "");
        return modelAndView;
    }
    
      ///////////////////Test Controller///////
    @RequestMapping(value = "/deletePatientrecorByPhone", method = RequestMethod.GET)
    public ModelAndView DeleteRecordByPhone(HttpServletRequest request) {
       if(request.getSession().getAttribute("sessionBean") !=null) {
//        if(request.getAttribute("sessionBean")!= null){
        ModelAndView modelAndView = new ModelAndView("deletePatientrecorByPhone");
        DeleteRecord obj=new DeleteRecord();
        modelAndView.addObject("deleteRecord", obj);
        return modelAndView;
        } 
        return new ModelAndView("redirect:".concat(PropertiesUtil.getProperty("APP_PATH_CODE").trim()).concat("login"));
//        return new ModelAndView("redirect:/CRQA/login");
    }
        @RequestMapping(value = "/deletePatientByEmail", method = RequestMethod.GET)
    public ModelAndView DeleteRecordByEmail(HttpServletRequest request) {
        if(request.getSession().getAttribute("sessionBean") !=null) {
        ModelAndView modelAndView = new ModelAndView("deletePatientByEmail");
        DeleteRecord obj=new DeleteRecord();
        modelAndView.addObject("deleteRecord", obj);
        return modelAndView;
        }
        return new ModelAndView("redirect:/login");
//           return new ModelAndView("redirect:/CRQA/login");
    }
     @RequestMapping(value = "/deletePracticerecorByPhone", method = RequestMethod.GET)
    public ModelAndView DeletePracticeRecordByPhone(HttpServletRequest request) {
       if(request.getSession().getAttribute("sessionBean") !=null) {
//        if(request.getAttribute("sessionBean")!= null){
        ModelAndView modelAndView = new ModelAndView("deletePracticerecorByPhone");
        DeleteRecord obj=new DeleteRecord();
        modelAndView.addObject("deleteRecord", obj);
        return modelAndView;
        } 
        return new ModelAndView("redirect:/login");
//        return new ModelAndView("redirect:/CRQA/login");
    }
        @RequestMapping(value = "/deletePracticeRecordByLicenseNo", method = RequestMethod.GET)
    public ModelAndView DeletePracticeRecordByLicenseNo(HttpServletRequest request) {
        if(request.getSession().getAttribute("sessionBean") !=null) {
        ModelAndView modelAndView = new ModelAndView("deletePracticeRecordByLicenseNo");
        DeleteRecord obj=new DeleteRecord();
        modelAndView.addObject("deleteRecord", obj);
        return modelAndView;
        }
        return new ModelAndView("redirect:/login");
//           return new ModelAndView("redirect:/CRQA/login");
    }
    
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
    public ModelAndView deleteRecord(@Valid DeleteRecord infObj,
            BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:deletePatientrecorByPhone");
                if (infObj.getType().equals("patient")) {
                    try {
                        if (!infObj.getValue().matches(".*[a-zA-Z]+.*")) {
                            infObj.setValue(infObj.getValue().replaceAll("[^0-9]", ""));
                            ModelAndView modelPhone = new ModelAndView("redirect:deletePatientrecorByPhone");
                            PatientProfile patientProfile = patientProfileService.getPatientProfileByEmailOrPhone(infObj.getValue());
                            if (patientProfile == null) {
                                message = "Patient record not found";
                                redirectAttributes.addFlashAttribute("message", message);
                                redirectAttributes.addFlashAttribute("deleteInfo", infObj);
                                return modelPhone;
                                }
                            if (patientProfileService.delRecord(patientProfile)) {
                                message = "Record sucessfully deleted";
                                redirectAttributes.addFlashAttribute("message", message);
                                return modelPhone;
                            } else {
                                    message = "Encounter error";
                                    redirectAttributes.addFlashAttribute("message", message);
                                    return modelPhone;
                                    }
                            }
                    
                        } catch (Exception ex) {
                                message = "Encountered Error";
                                redirectAttributes.addFlashAttribute("message", message);
                                java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                            return model;}
/////====================FOR email.====================//////
                ModelAndView modelEmail = new ModelAndView("redirect:deletePatientByEmail");
               try{
                PatientProfile patientProfile = patientProfileService.getPatientProfileByEmailOrPhone(infObj.getValue());
                if (patientProfile == null) {
                    message = "Patient record not found";
                    redirectAttributes.addFlashAttribute("message", message);
                    redirectAttributes.addFlashAttribute("deleteInfo", infObj);
                    return modelEmail;
                }
                if (patientProfileService.delRecord(patientProfile)) {
                    message = "Record sucessfully deleted";
                    redirectAttributes.addFlashAttribute("message", message);
//                    redirectAttributes.addFlashAttribute("deleteInfo", infObj);
                    return modelEmail;
                    } else {
                            message = "Encounter error";
                            redirectAttributes.addFlashAttribute("message", message);
                            redirectAttributes.addFlashAttribute("deleteInfo", infObj);
                            return modelEmail;}
               }catch (Exception ex) {
                                message = "Encountered Error";
                                redirectAttributes.addFlashAttribute("message", message);
                                java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                            return modelEmail;}
            } 
////////////////////////////////////for practice./////////////////////////////////////////
            else if (infObj.getType().equals("practice")) {
                
                    Practices practice=new Practices(Integer.parseInt(infObj.getRecordId()));
                    if(infObj.getJspName().equalsIgnoreCase("DeleteByPhone"))
                        {
                            ModelAndView modelphone=new ModelAndView("redirect:deletePracticerecorByPhone");
                        try{
                            patientProfileService.deleteUsersbyPracticeId(practice);
                            patientProfileService.deletePatientProfilebyPracticeId(practice); 
                            message = "Record sucessfully deleted";
                            redirectAttributes.addFlashAttribute("message", message);
                            return modelphone;
                        
                            }catch (Exception ex) {
                                    message = "Encountered Error";
                                    redirectAttributes.addFlashAttribute("message", message);
                                    java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                                    return modelphone;}
                        }
                        else if(infObj.getJspName().equalsIgnoreCase("DeleteByLicenseNo"))
                            {  
                               ModelAndView modelLicense=new ModelAndView("redirect:deletePracticeRecordByLicenseNo");
                            try{
                                patientProfileService.deleteUsersbyPracticeId(practice);
                                patientProfileService.deletePatientProfilebyPracticeId(practice);
                                message = "Record sucessfully deleted";
                                redirectAttributes.addFlashAttribute("message", message);
                                return modelLicense;
                                }catch (Exception ex) {
                                    message = "Encountered Error";
                                    redirectAttributes.addFlashAttribute("message", message);
                                    java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                                    return modelLicense;}
                            }
                    }

        return model;
    }
    
    @RequestMapping(value = "/searchPatient", method = RequestMethod.POST)
    public ModelAndView searchhPatient(@ModelAttribute("deleteRecord") DeleteRecord Info,
            BindingResult result, RedirectAttributes redirectAttributes) {
        
        ModelAndView model = new ModelAndView("redirect:deletePatientrecorByPhone");  
        
////////////////////////////////Patient Record check/////////////////////////////////////////////// 
            if (Info.getType().equals("patient")) {
           //////////////////////////here check is email or phone//////////////////////////////// 
                     try {
                                if (!Info.getValue().matches(".*[a-zA-Z]+.*")) {
                                        Info.setValue(Info.getValue().replaceAll("[^0-9]", ""));
           ////==============in case of phone.==========================////
                                        ModelAndView modelphone = new ModelAndView("redirect:deletePatientrecorByPhone");  
                                        PatientProfileDTO patient = patientProfileService.getPatientProfileDTOByEmailOrPhone(Info.getValue());
                                            if (patient == null) {
                                                logger.info("getPatientProfileDTOByEmailOrPhone->  "+ patient+ "( Record not found.)");
                                                message = "Record not found.";
                                                if (!Info.getValue().matches(".*[a-zA-Z]+.*")) {
                                                    String mobileNumber = Info.getValue();
                                                    //////////Change Phone Number Format///////
                                                    mobileNumber = "("+mobileNumber.substring(0, 3)+") "+mobileNumber.substring(3, 6)+"-"+ mobileNumber.substring(6, 10);
                                                    Info.setValue(mobileNumber);}
                                                redirectAttributes.addFlashAttribute("message", message);
                                                redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                                return modelphone;
                                                }
                                                ///===========if found record==========///
                                    ////////setting and returning value with default format As already set in DTO//////////////                 
                                    if (!Info.getValue().matches(".*[a-zA-Z]+.*")) 
                                        {Info.setValue(patient.getMobileNumber());}
                                    redirectAttributes.addFlashAttribute("patient", patient);
                                    redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                    return modelphone;
                                    }    
                        } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                                message = "Duplicate record found.";
                                redirectAttributes.addFlashAttribute("message", message);
                                redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                return model;}
                     
//////////////////////////////////IN CASE of Email. ////////////////////////////////////////
                        ModelAndView modeEmail = new ModelAndView("redirect:deletePatientByEmail");  
                    try {
                            PatientProfileDTO patient = patientProfileService.getPatientProfileDTOByEmailOrPhone(Info.getValue());
                            if (patient == null) {
                                message = "Record not found.";
                                if (!Info.getValue().matches(".*[a-zA-Z]+.*")) {
                                    String mobileNumber = Info.getValue();
                                    //////////Change Phone Number Format///////
                                    mobileNumber = "("+mobileNumber.substring(0, 3)+") "+mobileNumber.substring(3, 6)+"-"+ mobileNumber.substring(6, 10);
                                    Info.setValue(mobileNumber);}
                                redirectAttributes.addFlashAttribute("message", message);
                                redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                return modeEmail;
                                }///==========if found record=======///////
                            if (!Info.getValue().matches(".*[a-zA-Z]+.*")) {
                                Info.setValue(patient.getMobileNumber());}
                            redirectAttributes.addFlashAttribute("patient", patient);
                            redirectAttributes.addFlashAttribute("deleteInfo", Info);
                            return modeEmail;
                        } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                                message = "Duplicate record found.";
                                redirectAttributes.addFlashAttribute("message", message);
                                redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                return modeEmail;}
            }
//////////////////////////////In Case of practise./////////////////////////////////////////////////////
            else if (Info.getType().equals("practice")) {
                if(Info.getJspName().equalsIgnoreCase("DeleteByPhone"))
                        {   
                ModelAndView modelphone=new ModelAndView("redirect:deletePracticerecorByPhone");
                    try{    ///===============Practice by Phone Number===================///
                        if (!Info.getValue().matches(".*[a-zA-Z]+.*")) {
                                    ///========Search Practice by default phone format (123) 456-7890=======/// 
                            Practices practice = patientProfileService.getPracticebyPhone(Info.getValue());
                            if (practice == null) {
                                String digits = Info.getValue().replaceAll("[^0-9]", "");
                                    ///=======Reformatting and search Phone Number to 123-456-7890=====////
                                StringBuilder str = new StringBuilder(digits); // declare a StringBuffer
                                str.insert(3, '-');       // add char '-' at third index 
                                str.insert(7, '-');       // add char '-' at position 7 
                                practice = patientProfileService.getPracticebyPhone(str.toString());
                                if (practice == null) {
                                    message = "Record not found.";
                                    redirectAttributes.addFlashAttribute("message", message);
                                    redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                    return modelphone;}
                                ///========= Found record by new Format=======///
                                practice.setPracticephonenumber(Info.getValue());
                                //=========reformatting Phone Number to default format====//////
                                String digi = Info.getValue().replaceAll("[^0-9]", "");
                                if(digi.length()==10)
                                {digi="("+digi.substring(0, 3)+") "+digi.substring(3, 6)+"-"+ digi.substring(6, 10);            
                                 practice.setPracticephonenumber(digi);}
                    
                                redirectAttributes.addFlashAttribute("practice", practice);
                                redirectAttributes.addFlashAttribute("deleteInfo", Info);
                                return modelphone;}
                            ///=======Record found by default Phone Format=======/
                            redirectAttributes.addFlashAttribute("practice", practice);
                            redirectAttributes.addFlashAttribute("deleteInfo", Info);
                            return modelphone;
                            }
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                            message = "Duplicate record found.";
                            redirectAttributes.addFlashAttribute("message", message);
                            redirectAttributes.addFlashAttribute("deleteInfo", Info);
                            return modelphone; }
                        }
                    //////////////Search Practice by License Number ////////////
               else if(Info.getJspName().equalsIgnoreCase("DeleteByLicenseNo"))
                            { ModelAndView modelLicense=new ModelAndView("redirect:deletePracticeRecordByLicenseNo");
                try{
                    Practices practice = patientProfileService.getPracticebyLicense(Info.getValue());
                    if (practice == null) {
                        message = "Record not found.";
                        redirectAttributes.addFlashAttribute("message", message);
                        redirectAttributes.addFlashAttribute("deleteInfo", Info);
                        return modelLicense;}
                    ///======IN CASE record found===////
                    practice.setPracticephonenumber(practice.getPracticephonenumber());
                    String digits = practice.getPracticephonenumber().replaceAll("[^0-9]", "");
                    if(digits.length()==10)
                    {digits="("+digits.substring(0, 3)+") "+digits.substring(3, 6)+"-"+ digits.substring(6, 10);            
                    practice.setPracticephonenumber(digits);}
                    
                    redirectAttributes.addFlashAttribute("practice", practice);
                    redirectAttributes.addFlashAttribute("deleteInfo", Info);
                    return modelLicense;
                    }catch (Exception ex) {
                        java.util.logging.Logger.getLogger(PatientProfileController.class.getName()).log(Level.SEVERE, null, ex);
                        message = "Duplicate record found.";
                        redirectAttributes.addFlashAttribute("message", message);
                        redirectAttributes.addFlashAttribute("deleteInfo", Info);
                        return modelLicense;}
                }
            }
        return model;
        }
}
