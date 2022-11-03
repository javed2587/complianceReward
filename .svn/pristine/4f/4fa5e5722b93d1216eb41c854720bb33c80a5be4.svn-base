package com.ssa.cms.controller;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.dto.DrugCategoryListDTO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.FinancialReportDTO;
import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.dto.ValueDTO;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.OrderTransferImages;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.TransferDetail;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.service.DrugService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mzubair
 */
@Controller
@RequestMapping(value = "/rxTransfer")
public class RxTransferController implements Serializable {

    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private SetupService setupService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    @Autowired
    DrugService drugService;

    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

    @Autowired
    private PermissionService permissionService;
    
    private static final Log logger = LogFactory.getLog(RxTransferController.class.getName());

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView transferDetailList() {
        ModelAndView modelAndView = new ModelAndView("rxtransferrequestlist");
        modelAndView.addObject("transferRequestList", setupService.getPendingTransferRequestList());//getTransferRequestList());
        return modelAndView;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView viewRxTransferDetail(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("rxtransferrequest");
        getPopulateData(modelAndView, id);
        TransferDetail transferDetail = setupService.getTransferDetailById(id);
        if (transferDetail == null) {
            modelAndView.addObject("transferDetail", new TransferDetail());
        } else {
            getPopulateTransferRequestData(modelAndView, transferDetail);
            //modelAndView.addObject("drugStrengthList", setupService.getDrugsByDrugName(transferDetail.getDrugName()));
            modelAndView.addObject("transferDetail", transferDetail);
        }
        return modelAndView;
    }

    private void getPopulateData(ModelAndView modelAndView, Integer id) {
        modelAndView.addObject("drugNameList", setupService.getDrugList());
        modelAndView.addObject("transferRequest", setupService.getTransferRequestById(id));
    }

    /**
     * -HA 21-06-201
     *
     * @param modelAndView
     * @param transferDetail
     */
    private void getPopulateTransferRequestData(ModelAndView modelAndView, TransferDetail transferDetail) {

        List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugName(transferDetail.getDrugName());
        DrugCategoryListDTO drugCategoryListDTO = lst_drugCategoryListDTO != null && lst_drugCategoryListDTO.size() > 0
                ? lst_drugCategoryListDTO.get(0) : new DrugCategoryListDTO();
        Integer i_drugBrandNameId = AppUtil.getSafeInt(drugCategoryListDTO.getBrandNameId(), 0);
        List<DrugCategoryListDTO> lst_drugStrength = drugService.searchDrugByDrugType(transferDetail.getDrugName(), transferDetail.getDrugType());

        modelAndView.addObject("brandNameDrug", drugCategoryListDTO);
        modelAndView.addObject("lst_drugTypes", lst_drugCategoryListDTO);
        modelAndView.addObject("lst_drugStrength", lst_drugStrength);
        List<DrugDetail> details = new ArrayList<>();
        DrugDetail drugDetail = null;
        if (CommonUtil.isNotEmpty(transferDetail.getNdc())) {
            details = drugService.getDrugDetailsByNDC(Long.parseLong(transferDetail.getNdc()));
            drugDetail = CommonUtil.isNullOrEmpty(details) ? new DrugDetail() : details.get(0);
        }
        modelAndView.addObject("lst_drugDefaultQty", details);
        modelAndView.addObject("drugDetail", drugDetail);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView saveRxTransferDetail(@ModelAttribute @Valid TransferDetail transferDetail, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("rxtransferrequest");
        try {
            if (bindingResult.hasErrors()) {
                return modelAndView;
            }
            boolean isDuplicate = setupService.isRxNumberExist(transferDetail.getRxNumber(), transferDetail.getId());
            if (isDuplicate) {
                modelAndView.addObject("errorMessage", messageSource.getMessage("field.rx.exist", null, null));
                return modelAndView;
            }
            boolean isSaved = setupService.saveRxTransfer(transferDetail, sessionBean.getUserId());
            if (isSaved) {
                redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            } else {
                modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
                return modelAndView;
            }
            return new ModelAndView("redirect:/rxTransfer/list");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;

        }
    }

    @RequestMapping(value = "/populateDrugStrength/{drugName}", produces = "application/json")
    public @ResponseBody
    String populatedDrugStrengthValue(@PathVariable("drugName") String drugName, HttpServletRequest request) {
        return setupService.getDrugsByDrugBrandName(drugName);
    }

    /**
     * -HA page data
     */
    @RequestMapping(value = "/searchDrugBrandByName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugBrandByName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugBrandByName(str_name);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    /**
     *
     * @param jsonRequest
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/searchDrugByDrugBrandId", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugByDrugBrandId(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Brand Id @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("id");
            Integer i_drugBrand = drugBrandIdNode.asInt();

//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugBrandId(i_drugBrand);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugByNameSearch", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugByNameSearch(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            List<String> lst = drugService.populateDrugByNameSearch(str_name);
            String str_response = objectMapper.writeValueAsString(lst);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/searchDrugByDrugBrandName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugByDrugBrandName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Brand Id @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("name");
            String i_drugName = drugBrandIdNode.asText();

//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugName(i_drugName);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/searchDrugByDrugTypeAndName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugByDrugTypeAndName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("name");
            String drugName = drugBrandIdNode.asText();

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();
            
            String brandName = "", genericName = "";
            if (drugName.contains("{"))
            {
                String[] output = drugName.split("\\{");
                brandName = output[0];
                genericName = output[1].replace("}", "");

            }
            else 
            {
                String[] output = drugName.split("\\*");
                brandName = output[0];
                genericName =Constants.BRAND_NAME_ONLY_WITH_STARIC; //"* BRAND NAME ONLY *";
            }
            
//            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugType(drugName, type);
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.populateQtyByTypeAndDrugName(brandName,genericName, type);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    /////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/searchDrugDetailByDrugTypeAndName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugDetailByDrugTypeAndName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("name");
            String drugName=rootNode.path("name").asText();
            String brandName = drugBrandIdNode.asText();
            String[] drugArr=AppUtil.getBrandAndGenericFromDrugName(drugName);

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();

            String drugStrength = rootNode.path("drugStrength").asText();
            int qty = rootNode.path("qty").asInt();
            Integer patientProfileId = rootNode.path("patientProfileId").asInt();
            String orderId = rootNode.path("orderId").asText();
            DrugCategoryListDTO dto = drugService.searchDrugDetailByDrugNameTypeStrength(drugArr, type,
                    drugStrength, qty);
            PatientProfile patientProfile = patientProfileService.getPatientProfileById(patientProfileId);
            DrugDetail drugDetail = new DrugDetail();
            if (dto.getDrugDetailId() != null && dto.getDrugDetailId() > 0) {
                drugDetail = patientProfileService.getGenericBasedDrugDetailInfo(dto.getDrugDetailId(), qty, patientProfile, orderId);
            }
            String str_response = objectMapper.writeValueAsString(drugDetail);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }
    /////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/calculatePointsFromProfit", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String calculatePointsFromProfit(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode profitNode = rootNode.path("profit");
            String profit = profitNode.asText();

            DrugDetail drugDetail = new DrugDetail();
            drugDetail = patientProfileService.getGenericBasedDrugDetailInfo(AppUtil.getSafeFloat(profit, 0f));
            String str_response = objectMapper.writeValueAsString(drugDetail);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }
    ////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/updateDrugInfoInOrder", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String updateDrugInfoInOrder(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("name");
            String brandName = drugBrandIdNode.asText();

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();

            JsonNode strengthNode = rootNode.path("drugStrength");
            String strength = strengthNode.asText();

            JsonNode qtyNode = rootNode.path("drugQty");
            String qty = qtyNode.asText();

            JsonNode orderNode = rootNode.path("oid");
            String orderId = orderNode.asText();

            int i = drugService.updateDrugInfoInOrder(null, brandName, type, strength, qty, orderId, "", false);
            //String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return objectMapper.writeValueAsString(i > 0 ? "Drug info updated successfully" : "No such drug exists");
        } catch (Exception ex) {

            ex.printStackTrace();
            return new ObjectMapper().writeValueAsString("no such drug exists");
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        //return "";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param jsonRequest
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/searchDrugByDrugType", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugByDrugType(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("id");
            Integer i_drugBrand = drugBrandIdNode.asInt();

            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugByDrugType(i_drugBrand, str_name);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/saveInstantNotification", method = RequestMethod.POST)
    public @ResponseBody
    boolean saveInstantNotification(@RequestParam("patientId") Integer patientId, @RequestParam("messageText") String messageText) {
        boolean isSaved = false;
        try {
            if (drugService.saveInstantNotification(patientId, messageText)) {
                isSaved = Boolean.TRUE;
            } else {
                isSaved = Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return isSaved;
    }

    @RequestMapping(value = "/searchDrugDefaultQtyByStrength", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugDefaultQtyByStrength(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("drugBasicId");
            Integer drugBasicId = drugBrandIdNode.asInt();

            JsonNode nameNode = rootNode.path("strength");
            String strength = nameNode.asText();
            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugDefaultQtyByStrength(drugBasicId, strength);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/searchDrugBasicPrice", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugBasicPrice(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug by Drug Type @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode drugBrandIdNode = rootNode.path("name");
            String brandName = drugBrandIdNode.asText();

            JsonNode typeNode = rootNode.path("drugType");
            String type = typeNode.asText();

            JsonNode drugStrengthNode = rootNode.path("strength");
            String strength = drugStrengthNode.asText();

            List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugBasicPrice(brandName, type, strength);
            String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugByDrugGenericName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugByDrugGenericName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            List<String> lst = drugService.populateDrugByDrugGenericName(str_name);
            String str_response = objectMapper.writeValueAsString(lst);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugDetail", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugDetail(@RequestBody String jsonRequest, HttpServletRequest request) {
        logger.info("json request for search populateDrugDetail by brand Name or generic name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode nameNode = rootNode.path("name");

            String str_name = nameNode.asText();
            JsonNode drugGcnNode = rootNode.path("drugGcn");
            if (CommonUtil.isNotEmpty(str_name)) {
                String brandName = "", genericName = "";
                if (str_name.contains("{")) {
                    String[] output = str_name.split("\\{");
                    brandName = output[0];
                    genericName = output[1].replace("}", "");

                } else {
                    String[] output = str_name.split("\\*");
                    brandName = output[0];
                    genericName = "* BRAND NAME ONLY *";
                }
                str_response = patientProfileService.getDrugDetailByBrandName(brandName, genericName, drugGcnNode.asLong());
            }
        } catch (Exception e) {
            logger.error("Exception::populateDrugDetail:: ", e);
        }
        return str_response;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateQtyByStrengthAndDrugName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateQtyByStrength(@RequestBody String jsonRequest, HttpServletRequest request) {
        logger.info("json request for search Drug Detail by GCN @@@@@@@@@@@@@@@@@  " + jsonRequest);
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode nameNode = rootNode.path("drugStrength");
            String drugStrength = nameNode.asText();
            JsonNode typeNode = rootNode.path("drugType");
            String drugType = typeNode.asText();
            JsonNode drugNameNode = rootNode.path("drugName");
            String drugName = drugNameNode.asText();

            String brandName = "", genericName = "";
            if (drugName.contains("{")) {
                String[] output = drugName.split("\\{");
                brandName = output[0];
                genericName = output[1].replace("}", "");

            } else {
                String[] output = drugName.split("\\*");
                brandName = output[0];
                genericName =Constants.BRAND_NAME_ONLY_WITH_STARIC; //"* BRAND NAME ONLY *";
            }
            str_response = objectMapper.writeValueAsString(drugService.populateQtyByStrengthTypeAndDrugName(brandName, genericName, drugStrength,drugType));
        } catch (Exception e) {
            logger.error("Exception:: populateQtyByStrength:: ", e);
        }
        return str_response;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugDetailByGCN", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugDetailByGCN(@RequestBody String jsonRequest, HttpServletRequest request) {
        logger.info("json request for search Drug Detail by GCN @@@@@@@@@@@@@@@@@  " + jsonRequest);
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode drugGCNNode = rootNode.path("drugGCN");
            long drugGCN = drugGCNNode.asLong();

            List<DrugDetail> lst = this.drugService.getDrugDetailsListByDrugGCN(drugGCN, Constants.HIBERNATE_LIKE_START_OPERATOR);
            List<String> list = new ArrayList<>();
            for (DrugDetail drugDetail : lst) {
                list.add(drugDetail.getDrugGCN() + "");
            }
            str_response = objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception:: populateDrugDetailByGCN:: ", e);
        }
        return str_response;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugDetailByMultiGCN", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugDetailByMultiGCN(@RequestBody String jsonRequest, HttpServletRequest request) {
        logger.info("json request for search Drug Detail by GCN @@@@@@@@@@@@@@@@@  " + jsonRequest);
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode drugGCNNode = rootNode.path("drugGCN");
            String commaSeparatedGcn=drugGCNNode.asText();
            long drugGCN =0L;
            String[] gcnArr=commaSeparatedGcn.split(",");
            if(gcnArr!=null && gcnArr.length>0)
            {
                drugGCN = AppUtil.getSafeLong(gcnArr[gcnArr.length-1], 0L);
            }
            
            String concatenatedValues="";//commaSeparatedGcn+",";
            if(AppUtil.getSafeStr(commaSeparatedGcn, "").indexOf(",")>=0)
            {
                concatenatedValues=commaSeparatedGcn.substring(0,commaSeparatedGcn.lastIndexOf(","))+",";
            }
            
            List<DrugDetail> lst = this.drugService.getDrugDetailsListByDrugGCN(drugGCN, Constants.HIBERNATE_LIKE_START_OPERATOR);
            List<String> list = new ArrayList<>();
            for (DrugDetail drugDetail : lst) {
                list.add(concatenatedValues+drugDetail.getDrugGCN() + "");
            }
            str_response = objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception:: populateDrugDetailByGCN:: ", e);
        }
        return str_response;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/populateDrugQtyByGCN", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String populateDrugQtyByGCN(@RequestBody String jsonRequest, HttpServletRequest request) {
        String str_response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);
            JsonNode drugGCNNode = rootNode.path("drugGCN");
            long drugGCN = drugGCNNode.asLong();
            DrugDetail detail = drugService.getDrugDetailByGCN(drugGCN);
            if (detail != null && detail.getDrugDetailId() != null) {
                DrugDetailDTO detailDTO = new DrugDetailDTO();
                detailDTO.setDrugDetailId(detail.getDrugDetailId());
                detailDTO.setDefQty(detail.getDefQty());

                if (AppUtil.getSafeStr(detail.getDrugBasic().getDrugGeneric().getGenericName(), "").equalsIgnoreCase("* BRAND NAME ONLY *")) {
                    detailDTO.setBrandName(AppUtil.getSafeStr(detail.getDrugBasic().getBrandName(), "") + " "
                            + AppUtil.getSafeStr(detail.getDrugBasic().getDrugGeneric().getGenericName(), ""));
                } else {
                    detailDTO.setBrandName(AppUtil.getSafeStr(detail.getDrugBasic().getBrandName(), "") + "{"
                            + AppUtil.getSafeStr(detail.getDrugBasic().getDrugGeneric().getGenericName(), "") + "}");

                }
                detailDTO.setDosageForm(detail.getDrugForm().getFormDescr());
                detailDTO.setStrength(detail.getStrength());
                detailDTO.setGenericName(AppUtil.getSafeStr(detail.getDrugBasic().getBrandName(), ""));
                str_response = objectMapper.writeValueAsString(detailDTO);
            }

        } catch (Exception e) {
            logger.error("Exception:: populateDrugQtyByGCN:: ", e);
        }
        return str_response;
    }

    @RequestMapping(value = "/loadNextImage/{orderId}/{imageId}", method = RequestMethod.GET)
    public @ResponseBody
    String loadNextDrugImage(HttpServletRequest request,
            @PathVariable("orderId") String orderId, @PathVariable("imageId") int imageId)
            throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TransferRxQueueDTO images = this.consumerRegistrationService.getNextOrderImages(orderId, imageId);
            if (images != null) {
                String str_response = objectMapper.writeValueAsString(images);
                return str_response;
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();;
            return e.getMessage();
        }
    }
    
    @RequestMapping(value = "/loadPrevImage/{orderId}/{imageId}", method = RequestMethod.GET)
    public @ResponseBody
    String loadPrevDrugImage(HttpServletRequest request,
            @PathVariable("orderId") String orderId, @PathVariable("imageId") int imageId)
            throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TransferRxQueueDTO images = this.consumerRegistrationService.getPrevOrderImages(orderId, imageId);
            if (images != null) {
                String str_response = objectMapper.writeValueAsString(images);
                return str_response;
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();;
            return e.getMessage();
        }
    }


    @RequestMapping(value = "/searchDrugFormByDrugBrandName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugTypeByDrugBrandName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Form by Drug Brand Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode rootNode = objectMapper.readTree(jsonRequest);

                JsonNode drugBrandIdNode = rootNode.path("name");
                String i_drugName = drugBrandIdNode.asText();

                ///////////////////////////////////////////////////////
                String brandName = "", genericName = "";
                if (CommonUtil.isNotEmpty(i_drugName))
                {

                    String[] drugArr=AppUtil.getBrandAndGenericFromDrugName(i_drugName);
                    //////////////////////////////////////////////////////
        //            JsonNode nameNode = rootNode.path("name");
        //            String str_name = nameNode.asText();
                    List<DrugCategoryListDTO> lst_drugCategoryListDTO = drugService.searchDrugFormByDrugBrandName(drugArr);
                    String str_response = objectMapper.writeValueAsString(lst_drugCategoryListDTO);
                    return str_response;
                }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/loadFinancialReportParams", produces = "application/json")
    public @ResponseBody
    String loadFinancialReportParams(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            int weekOfyear = DateUtil.getWeekOfTheYear(new Date());
            int cycle = weekOfyear / 2;
            Date frmDate = new Date();
            Date toDate = new Date();
            List<ValueDTO> lstValueDTO=permissionService.getPharmaciesDTOList();
            FinancialReportDTO dto = new FinancialReportDTO();
            if (weekOfyear % 2 == 0) 
            {
                int dayOfWeek = DateUtil.getDayOfWeek(new Date());
                int daysToAdd = 7 - dayOfWeek;
                frmDate = DateUtil.addDaysToDate(frmDate, (dayOfWeek - 1 + 7) * -1);
                toDate = DateUtil.addDaysToDate(toDate, daysToAdd);
                dto.setFrmDate(DateUtil.dateToString(frmDate, Constants.USA_DATE_FORMATE));
                dto.setToDate(DateUtil.dateToString(toDate, Constants.USA_DATE_FORMATE));
                dto.setCycleNo(cycle + "");
                dto.setYear(DateUtil.getCurrYear(new Date()) + "");
               
            }
            dto.setLstItems(lstValueDTO);
            String str_response = objectMapper.writeValueAsString(dto);
            return str_response;

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

    @RequestMapping(value = "/exportPdfFinancial", method = RequestMethod.GET)
    public ModelAndView exportPdf(@RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "pharmacyId", required = false) String pharmacyId,
            @RequestParam(value = "pharmacy", required = false) String pharmacy,
            @RequestParam(value = "cycle", required = false) String cycle,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "format", required = false) String format,
            HttpServletRequest req) {
        try {
            Map map = this.consumerRegistrationService.populateOrderListForFinancialReport(fromDate, toDate, AppUtil.getSafeInt(pharmacyId, 0));
            map.put("frmDate", fromDate);
            map.put("toDate", toDate);
            map.put("cycle", cycle);
            map.put("pharmacy", pharmacy);
//            map.put("cycle", cycle);
//            String format=AppUtil.getSafeStr(req.getParameter("formatBtn"), "").length()>0?
//                            AppUtil.getSafeStr(req.getParameter("formatBtn"),""):"pdfView";
            //ModelAndView modelAndView = new ModelAndView("pdfView", "finDataMap", map);
            ModelAndView modelAndView = new ModelAndView(format, "finDataMap", map);
            modelAndView.addObject("key", "biWeeklyFinancialReport");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @RequestMapping(value = "/exportPdfTransactional", method = RequestMethod.GET)
    public ModelAndView exportPdf(@RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "pharmacyId", required = false) String pharmacyId,
            @RequestParam(value = "pharmacy", required = false) String pharmacy,
             @RequestParam(value = "format", required = false) String format,
            HttpServletRequest req) {
        try {
            Map map = this.consumerRegistrationService.populateOrderListForFinancialReport(fromDate, toDate, AppUtil.getSafeInt(pharmacyId, 0));
            map.put("frmDate", fromDate);
            map.put("toDate", toDate);
            map.put("pharmacy", pharmacy);
            
//            map.put("cycle", cycle);
//            String format=AppUtil.getSafeStr(req.getParameter("formatBtn"), "").length()>0?
//                            AppUtil.getSafeStr(req.getParameter("formatBtn"),""):"pdfView";
            //ModelAndView modelAndView = new ModelAndView("pdfView", "finDataMap", map);
            ModelAndView modelAndView = new ModelAndView(format, "pharmacyTransactionalDataMap", map);
            modelAndView.addObject("key", "pharmacyTransactionalReport");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
