package com.ssa.cms.controller;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.bean.drug.setup.DrugSetupAddBean;
import com.ssa.cms.bean.drug.setup.DrugSetupDrugBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonRequestParser;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.dto.DrugCategoryListDTO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBrand;
//import com.ssa.cms.model.DrugCategory;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.DrugDetailDocuments;
import com.ssa.cms.model.DrugForm;
import com.ssa.cms.model.DrugGeneric;
import com.ssa.cms.model.DrugGenericTypes;
import com.ssa.cms.model.DrugPacking;
//import com.ssa.cms.model.DrugTherapyClass;
import com.ssa.cms.model.JsonResponse;
import com.ssa.cms.model.SupportModel;
import com.ssa.cms.service.DrugService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping(value = "/drugBrand")
public class DrugSetupController implements Serializable {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    @Autowired
    private PatientProfileService patientProfileService;

    @Autowired
    private DrugService drugService;

    private static final Log logger = LogFactory.getLog(DrugSetupController.class.getName());

    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list")
    public ModelAndView drugList() {
        ModelAndView modelAndView = new ModelAndView("druglist");
        modelAndView.addObject("druglist", setupsDelegate.getDrugList());
        return modelAndView;
    }

    @RequestMapping(value = "/retrieveDrug/{id}")
    public String retrieveDrugDetail(@PathVariable("id") Long id) {
        try {
            DrugDetailDTO dto = drugService.retrieveDrugDetail(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String str_response = objectMapper.writeValueAsString(dto);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";

    }

    /**
     * old commit by Haider ali
     */
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String addEdit(@ModelAttribute DrugBrand drugBrand, Model model) {
//        return create(drugBrand, model, true);
//    }
    /**
     *
     * @param drugBrand
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEdit(Model model) {
        DrugSetupAddBean drugSetupAddBean = new DrugSetupAddBean();
        drugSetupAddBean.setSaveAction(true);
        drugSetupAddBean.setUpdateAction(false);
        drugSetupAddBean.setDeleteAction(false);
        model.addAttribute("drugSetupAddBean", drugSetupAddBean);
//        model.addAttribute("drugUnitList", setupsDelegate.getDrugUnitsList());
        return "adddrug";
    }

//    private String create(DrugBrand drugBrand, Model model, boolean init) {
//        if (init) {
//            //drugBrand.setDrugs(new AutoPopulatingList<>(Drug.class));
//        }
////        model.addAttribute("drugUnitList", setupsDelegate.getDrugUnitsList());
////        model.addAttribute("drugCategoryList", setupsDelegate.getDrugCategoryList());
////        model.addAttribute("drugTherapyClassList", setupsDelegate.getDrugTherapyClassList());
//        model.addAttribute("drugGenericNameList", setupsDelegate.getDrugGenericNameList());
//        return "adddrug";
//    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String saveDrug(@ModelAttribute @Valid DrugBrand drugBrand, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//        drugBrandValidator.validate(drugBrand, result);
//        if (!validateDrug(result, drugBrand, model)) {
//            return create(drugBrand, model, false);
//        }
//        boolean saved = setupsDelegate.saveDrug(drugBrand, sessionBean.getUserId());
//        if (saved) {
//            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
//        } else {
//            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
//            return "/adddrug";
//        }
//        return "redirect:/drugBrand/list";
//    }
//    private boolean validateDrug(BindingResult result, DrugBrand drugBrand, Model model) {
//        if (result.hasErrors()) {
//            return false;
//        }
//        if (drugBrand.getDrugBrandName() != null && !"".equals(drugBrand.getDrugBrandName().trim()) && drugBrand.getDrugBrandName().trim().length() < 4) {
//            model.addAttribute("message1", "Minimum length 4 character");
//            return false;
//        }
//        boolean checkDuplicateGenericName = setupsDelegate.getGenericName(drugBrand.getDrugBrandName(), drugBrand.getId());
//        if (checkDuplicateGenericName) {
//            model.addAttribute("errorMessage", messageSource.getMessage("field.genericName.duplicate", null, null));
//            return false;
//        }
//        if (drugBrand.getDrugs() != null && !drugBrand.getDrugs().isEmpty()) {
//            List<String> ndcs = new ArrayList<>();
//            for (Drug drug : drugBrand.getDrugs()) {
//                if (drug.getDrugGpi() != null && !"".equals(drug.getDrugGpi())) {
//                    ndcs.add(drug.getDrugGpi());
//                }
//            }
//            Set<String> isNdcDuplicate = findDuplicates(drugBrand.getDrugs());
//            if (isNdcDuplicate.size() > 0) {
//                model.addAttribute("errorMessage", messageSource.getMessage("field.ndcNumber.duplicate", null, null));
//                return false;
//            }
//            if (ndcs.size() > 0) {
//                boolean checkDuplicateNdc = setupsDelegate.getNdcNumber(ndcs, drugBrand.getId());
//                if (checkDuplicateNdc) {
//                    model.addAttribute("errorMessage", messageSource.getMessage("field.ndcNumber.duplicate", null, null));
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    /**
     * Committed by Haider Ali below revised method written
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public ModelAndView editDrug(@PathVariable("id") Integer id, @ModelAttribute DrugBrand drugBrand) {
//        drugBrand = setupsDelegate.getDrugById(id);
//        ModelAndView modelAndView = new ModelAndView("adddrug");
//        modelAndView.addObject("drugBrand", drugBrand);
//        modelAndView.addObject("drugUnitList", setupsDelegate.getDrugUnitsList());
//        return modelAndView;
//    }
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteDrug(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
//        boolean delete = false;
//
//        try {
//            delete = setupsDelegate.deleteDrug(id);
//        } catch (Exception exception) {
//
//        }
//        if (delete) {
//            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
//        }
//        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list");
//        return modelAndView;
//    }
    private List<Drug> manageDrug(DrugBrand drugBrand) {
        List<Drug> drug2remove = new ArrayList<>();
//        if (drugBrand.getDrugs() != null) {
//            for (Iterator<Drug> i = drugBrand.getDrugs().iterator(); i.hasNext();) {
//                Drug drug = i.next();
//                // If the remove flag is true, remove the employee from the list
//                if (drug.getRemove() == 1) {
//                    drug2remove.add(drug);
//                    i.remove();
//                    // Otherwise, perform the links
//                } else if (drug.getDrugGpi() == null) {
//                    drug2remove.add(drug);
//                    i.remove();
//                } else {
//                    drug.setDrugBrand(drugBrand);
//                }
//            }
//        }
        return drug2remove;
    }

    @RequestMapping(value = "/deleteDrug/{id}", produces = "application/json")
    public @ResponseBody
    boolean getCampaignHandler(@PathVariable String id,
            HttpServletRequest request) throws Exception {
        return setupsDelegate.deleteDrugById(Integer.parseInt(id));
    }

    public static Set<String> findDuplicates(List<Drug> list) {
        final Set<String> setToReturn = new HashSet<>();
        final Set<String> set1 = new HashSet<>();
        for (Drug drug : list) {
            if (!set1.add(drug.getDrugGpi())) {
                setToReturn.add(drug.getDrugGpi());
            }
        }
        return setToReturn;
    }

    /**
     * @Authoer Haider Ali
     */
//    @RequestMapping(value = "/list/category")
//    public ModelAndView drugCategoryList(@ModelAttribute("message") String message, @ModelAttribute("errorMessage") String errorMessage) {
//
//        logger.debug("!!!!!!!!!!!!!!!!!!  calling list category @@@@@@@@@@@@@@@@@@@@");
//
//        List drugCategories = patientProfileService.getDrugCategory(0);
//        logger.debug("!!!!!!!!!!!!!!!!!!  getting records for list category @@@@@@@@@@@@@@@@@@@@");
//        for (Object obj : drugCategories) {
//            DrugCategory dc = (DrugCategory) obj;
//            logger.debug(" Drug Category Name ::: " + dc.getDrugCategoryName());
//        }
//
//        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
//        try {
//            modelToDTO(drugCategories, lst_drugCategoryListDTO);
//            groupingDrugCategories(lst_drugCategoryListDTO);
//            groupingDrugTherapyClass(lst_drugCategoryListDTO);
//            //groupingGenericBrand(lst_drugCategoryListDTO);                    
//
//        } catch (Exception ex) {
//            logger.debug(ex);
//        }
//        ModelAndView modelAndView = new ModelAndView("drugCategorylist");
//        modelAndView.addObject("drugCategoriesList", lst_drugCategoryListDTO);
//        if (AppUtil.getSafeStr(message, "").length() > 0) {
//            modelAndView.addObject("message", message);
//        } else if (AppUtil.getSafeStr(errorMessage, "").length() > 0) {
//            modelAndView.addObject("errorMessage", errorMessage);
//        }
//        //modelAndView.addObject("message","fdfddfdf");
//        return modelAndView;
//    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/list/drugdata")
    public ModelAndView retrieveDrugList(@ModelAttribute("message") String message, @ModelAttribute("errorMessage") String errorMessage) {

        logger.debug("!!!!!!!!!!!!!!!!!!  calling list category @@@@@@@@@@@@@@@@@@@@");

        ModelAndView modelAndView = new ModelAndView("drugImportList");//("drugCategorylist");
        try {
            // drugService.loadPicturesFromExcel(null,null);
            modelAndView.addObject("drugCategoriesList", this.drugService.retrieveActiveDrugListData());// lst_drugCategoryListDTO);
            if (AppUtil.getSafeStr(message, "").length() > 0) {
                modelAndView.addObject("message", message);
            } else if (AppUtil.getSafeStr(errorMessage, "").length() > 0) {
                modelAndView.addObject("errorMessage", errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //modelAndView.addObject("message","fdfddfdf");
        return modelAndView;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/list/searchdrugdata")
    public ModelAndView searchDrugList(@RequestParam(value = "drugName", required = false) String drugName) {

        logger.debug("!!!!!!!!!!!!!!!!!!  calling list category @@@@@@@@@@@@@@@@@@@@");

        ModelAndView modelAndView = new ModelAndView("drugImportList");//("drugCategorylist");
        try {
            // drugService.loadPicturesFromExcel(null,null);
            modelAndView.addObject("drugCategoriesList", this.drugService.retrieveActiveDrugListDataByDrugName(drugName));// lst_drugCategoryListDTO);
            modelAndView.addObject("searchDrug", AppUtil.getSafeStr(drugName, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //modelAndView.addObject("message","fdfddfdf");
        return modelAndView;
    }
    ////////////////////////////////////////////////////////////////////////////////////////

//    private void modelToDTO(List drugCategories, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        List<DrugCategory> lst_drugCategory = objectToDrugCategory(drugCategories);
//        for (DrugCategory drugCategory : lst_drugCategory) {
//            modelToDtoDrugCategory(drugCategory, a_drugCategoryListDTO);
//        }
//
//    }

//    private void modelToDtoDrugCategory(DrugCategory drugCategory, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        Set<DrugTherapyClass> set_DrugTherapyClass = drugCategory.getDrugTherapyClass();
//        for (DrugTherapyClass drugTherapyClass : set_DrugTherapyClass) {
//            modelToDtoDrugCategory(drugCategory, drugTherapyClass, a_drugCategoryListDTO);
//        }
//
//    }

//    private void modelToDtoDrugCategory(DrugCategory drugCategory, DrugTherapyClass drugTherapyClass, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        Set<DrugGenericTypes> set_drugGenericTypes = drugTherapyClass.getDrugGenericTypes();
//        for (DrugGenericTypes drugGenericTypes : set_drugGenericTypes) {
//            modelToDtoDrugCategory(drugCategory, drugTherapyClass, drugGenericTypes, a_drugCategoryListDTO);
//        }
//    }

//    private void modelToDtoDrugCategory(DrugCategory drugCategory, DrugTherapyClass drugTherapyClass, DrugGenericTypes drugGenericTypes, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        Set<DrugBrand> set_drugBrand = drugGenericTypes.getDrugBrand();
//        for (DrugBrand drugBrand : set_drugBrand) {
//            modelToDtoDrugCategory(drugCategory, drugTherapyClass, drugGenericTypes, drugBrand, a_drugCategoryListDTO);
//        }
//    }

//    private void modelToDtoDrugCategory(DrugCategory drugCategory, DrugTherapyClass drugTherapyClass, DrugGenericTypes drugGenericTypes, DrugBrand drugBrand, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        List<Drug> lst_drug = drugBrand.getDrugs();
//        String str_strengthCommaSeperated = modelToDtoDrugCommaSeperated(lst_drug);
//
//        DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
//
//        drugCategoryListDTO.setCategoryName(drugCategory.getDrugCategoryName());
//        drugCategoryListDTO.setTherapyClassName(drugTherapyClass.getDrugTherapyClassName());
//        drugCategoryListDTO.setGenericName(drugGenericTypes.getDrugGenericName());
//        drugCategoryListDTO.setBrandNameId("" + drugBrand.getId());
//        drugCategoryListDTO.setBrandName(drugBrand.getDrugBrandName());
//        drugCategoryListDTO.setStrength(str_strengthCommaSeperated);
//
//        a_drugCategoryListDTO.add(drugCategoryListDTO);
////        for (Drug drug : lst_drug) {
////            modelToDtoDrugCategory(drugCategory, drugTherapyClass,drugGenericTypes,drugBrand,drug,a_drugCategoryListDTO);
////        }
//    }

//    private void modelToDtoDrugCategory(DrugCategory drugCategory, DrugTherapyClass drugTherapyClass, DrugGenericTypes drugGenericTypes, DrugBrand drugBrand, Drug drug, List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {
//
//        DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
//
//        drugCategoryListDTO.setCategoryName(drugCategory.getDrugCategoryName());
//        drugCategoryListDTO.setTherapyClassName(drugTherapyClass.getDrugTherapyClassName());
//        drugCategoryListDTO.setGenericName(drugGenericTypes.getDrugGenericName());
//        drugCategoryListDTO.setBrandNameId("" + drugBrand.getId());
//        drugCategoryListDTO.setBrandName(drugBrand.getDrugBrandName());
//        drugCategoryListDTO.setStrength(drug.getStrength());
//        drugCategoryListDTO.setDrugUnitName(drug.getDrugUnits().getName());
//
//        a_drugCategoryListDTO.add(drugCategoryListDTO);
//
//    }
//
//    private String modelToDtoDrugCommaSeperated(List<Drug> lst_drug) throws Exception {
//
//        String str_strength = "";
//        for (int i = 0; i < lst_drug.size(); i++) {
//
//            Drug drug = lst_drug.get(i);
////            str_strength += drug.getStrength() + " " + drug.getDrugUnits().getName();
//            if (i < lst_drug.size() - 1) {
//                str_strength += ",";
//            }
//        }
//        return str_strength;
//    }

//    private List<DrugCategory> objectToDrugCategory(List drugCategories) {
//
//        List<DrugCategory> lst_drugCategory = new ArrayList<>();
//
//        for (Object obj : drugCategories) {
//            DrugCategory dc = (DrugCategory) obj;
//            lst_drugCategory.add(dc);
//            logger.info(" Drug Category Name ::: " + dc.getDrugCategoryName());
//        }
//        return lst_drugCategory;
//    }

    /**
     * replace empty string duplicate values e.f. Grouping
     *
     * @param a_drugCategoryListDTO
     * @return
     * @throws Exception
     */
    private void groupingDrugCategories(List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {

        DrugCategoryListDTO drugCategoryListDTOPrevoius = a_drugCategoryListDTO.get(0);
        String str_categoryNamePrevious = drugCategoryListDTOPrevoius.getCategoryName();

        logger.info("!!!!!!!!!!!!!!  %%%%%%%%%%%%%%%%%%%%%%% &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        for (int i = 1; i < a_drugCategoryListDTO.size(); i++) {

            DrugCategoryListDTO drugCategoryListDTOCurrent = a_drugCategoryListDTO.get(i);
            String str_categoryNameNext = drugCategoryListDTOCurrent.getCategoryName();
            //logger.info(" Previous Category Name ::::::::: "+str_categoryNamePrevious);
            //logger.info(" Next Category Name ::::::::: "+str_categoryNameNext);
            //logger.info(" Strength ::::::::: "+drugCategoryListDTOCurrent.getStrength());
            //logger.info(" previous Strength ::::::::: "+drugCategoryListDTOPrevoius.getStrength());

            //category
            if (str_categoryNamePrevious.equalsIgnoreCase(str_categoryNameNext)) {
                a_drugCategoryListDTO.get(i).setCategoryName("");
            }
            str_categoryNamePrevious = str_categoryNameNext;
        }
    }

    private void groupingDrugTherapyClass(List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {

        DrugCategoryListDTO drugCategoryListDTOPrevoius = a_drugCategoryListDTO.get(0);
        String str_categoryNamePrevious = drugCategoryListDTOPrevoius.getCategoryName();
        String str_therapyClassPrevious = drugCategoryListDTOPrevoius.getTherapyClassName();

        logger.info("!!!!!!!!!!!!!!  %%%%%%%%%%%%%%%%%%%%%%% &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        for (int i = 1; i < a_drugCategoryListDTO.size(); i++) {

            DrugCategoryListDTO drugCategoryListDTOCurrent = a_drugCategoryListDTO.get(i);
            String str_categoryNameNext = drugCategoryListDTOCurrent.getCategoryName();
            String str_therapyClassNext = drugCategoryListDTOCurrent.getTherapyClassName();
            //logger.info(" Previous Category Name ::::::::: "+str_categoryNamePrevious);
            //logger.info(" Next Category Name ::::::::: "+str_categoryNameNext);
            //logger.info(" Strength ::::::::: "+drugCategoryListDTOCurrent.getStrength());
            //logger.info(" previous Strength ::::::::: "+drugCategoryListDTOPrevoius.getStrength());

            //category
            if (str_categoryNameNext.isEmpty() && str_therapyClassPrevious.equalsIgnoreCase(str_therapyClassNext)) {
                a_drugCategoryListDTO.get(i).setTherapyClassName("");
            }
            str_categoryNamePrevious = str_categoryNameNext;
            str_therapyClassPrevious = str_therapyClassNext;
        }
    }

    private void groupingGenericBrand(List<DrugCategoryListDTO> a_drugCategoryListDTO) throws Exception {

        DrugCategoryListDTO drugCategoryListDTOPrevoius = a_drugCategoryListDTO.get(0);
        String str_categoryNamePrevious = drugCategoryListDTOPrevoius.getCategoryName();
        String str_therapyClassPrevious = drugCategoryListDTOPrevoius.getTherapyClassName();
        String str_genericBrandNamePrevious = drugCategoryListDTOPrevoius.getGenericName() + drugCategoryListDTOPrevoius.getBrandName();

        logger.info("!!!!!!!!!!!!!!  %%%%%%%%%%%%%%%%%%%%%%% &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        for (int i = 1; i < a_drugCategoryListDTO.size(); i++) {

            DrugCategoryListDTO drugCategoryListDTOCurrent = a_drugCategoryListDTO.get(i);
            String str_categoryNameNext = drugCategoryListDTOCurrent.getCategoryName();
            String str_therapyClassNext = drugCategoryListDTOCurrent.getTherapyClassName();
            String str_genericBrandNameNext = drugCategoryListDTOCurrent.getGenericName() + drugCategoryListDTOCurrent.getBrandName();
            //logger.info(" Previous str_genericBrandNamePrevious ::::::::: "+str_genericBrandNamePrevious);
            //logger.info(" Next str_genericBrandNameNext ::::::::: "+str_genericBrandNameNext);

            //category
            if (str_therapyClassNext.isEmpty() && str_genericBrandNamePrevious.equalsIgnoreCase(str_genericBrandNameNext)) {
                a_drugCategoryListDTO.get(i).setGenericName("");
                a_drugCategoryListDTO.get(i).setBrandName("");
            }
            str_categoryNamePrevious = str_categoryNameNext;
            str_therapyClassPrevious = str_therapyClassNext;
            str_genericBrandNamePrevious = str_genericBrandNameNext;

        }
    }

//    @RequestMapping(value = "/searchDrugCategory", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//    String searchDrugCategory(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
//        logger.info("json request for search Drug Category by parameter @@@@@@@@@@@@@@@@@  " + jsonRequest);
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            JsonNode rootNode = objectMapper.readTree(jsonRequest);
//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
//
//            return patientProfileService.searchDrugCategoryByParameter(str_name);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
//        return "";
//    }

//    @RequestMapping(value = "/searchTherapyClass", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//    String searchTherapyClass(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
//        logger.info("json request for search Drug Category by parameter @@@@@@@@@@@@@@@@@  " + jsonRequest);
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            JsonNode rootNode = objectMapper.readTree(jsonRequest);
//
//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
//
//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
//            return patientProfileService.searchTherapyClassesByParameter(i_catId, str_name);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
//        return "";
//    }

    @RequestMapping(value = "/searchGenericName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchGenericName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Category by parameter @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode catIdNode = rootNode.path("catId");
            Integer i_catId = catIdNode.asInt();

            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            return patientProfileService.searchGenericNameByParameter(i_catId, str_name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    @RequestMapping(value = "/searchBrandName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchBrandName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Category by parameter @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode catIdNode = rootNode.path("catId");
            Integer i_catId = catIdNode.asInt();

            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            return patientProfileService.searchBrandNameByParameter(i_catId, str_name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    /**
     * save drug category
     */
//    @RequestMapping(value = "/saveDrugCategory", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//    String saveDrugCategory(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
//
//        DrugCategory drugCategory = JsonRequestParser.parseDrugCategoryRequest(jsonRequest);
//        if (drugCategory == null) {
//            return JsonResponseComposer.composeFailureResponse("Invalid Request <- Unable to parse");
//        }
//        drugCategory = drugService.saveDrugCategory(drugCategory);
//        if (drugCategory != null) {
//            return JsonResponseComposer.composeSuccessResponse(drugCategory);
//        } else {
//            return JsonResponseComposer.composeFailureResponse("Category not save.");
//        }
//    }

    /**
     * save therapy class
     */
//    @RequestMapping(value = "/saveTherapyClass", produces = "application/json", method = RequestMethod.POST)
//    public @ResponseBody
//    String saveTherapyClass(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
//
//        logger.info("json request for save Therapy Class @@@@@@@@@@@@@@@@@  " + jsonRequest);
//
////        DrugTherapyClass drugTherapyClass = JsonRequestParser.parseDrugTherapyClassRequest(jsonRequest);
//        if (drugTherapyClass == null) {
//            return JsonResponseComposer.composeFailureResponse("Invalid Request <- Unable to parse");
//        }
//        drugTherapyClass = drugService.saveDrugTherapyClass(drugTherapyClass);
//        if (drugTherapyClass != null) {
//            return JsonResponseComposer.composeSuccessResponse(drugTherapyClass);
//        } else {
//            return JsonResponseComposer.composeFailureResponse("Drug Therapy Class not save.");
//        }
//    }

    @RequestMapping(value = "/saveGenericName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String saveGenericName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {

        logger.info("json request for save Therapy Class @@@@@@@@@@@@@@@@@  " + jsonRequest);

        DrugGenericTypes drugGenericTypes = JsonRequestParser.parseDrugGenericTypesRequest(jsonRequest);
        if (drugGenericTypes == null) {
            return JsonResponseComposer.composeFailureResponse("Invalid Request <- Unable to parse");
        }
        drugGenericTypes = drugService.saveDrugGenericTypes(drugGenericTypes);
        if (drugGenericTypes != null) {
            return JsonResponseComposer.composeSuccessResponse(drugGenericTypes);
        } else {
            return JsonResponseComposer.composeFailureResponse("Generic Type not save.");
        }
    }

    @RequestMapping(value = "/saveBrandName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String saveBrandName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {

        logger.info("json request for save Brand Name @@@@@@@@@@@@@@@@@  " + jsonRequest);

        DrugBrand drugBrand = JsonRequestParser.parseDrugBrandRequest(jsonRequest);
        if (drugBrand == null) {
            return JsonResponseComposer.composeFailureResponse("Invalid Request <- Unable to parse");
        }
        drugBrand = drugService.saveDrugBrand(drugBrand);
        if (drugBrand != null) {
            return JsonResponseComposer.composeSuccessResponse(drugBrand);
        } else {
            return JsonResponseComposer.composeFailureResponse("Drug Brand Name not save.");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveDrug(@ModelAttribute DrugSetupAddBean drugSetupAddBean, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//        drugBrandValidator.validate(drugBrand, result);
//        if (!validateDrug(result, drugBrand, model)) {
//            return create(drugBrand, model, false);
//        }
        //boolean saved = setupsDelegate.saveDrug(drugBrand, sessionBean.getUserId());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@  request receive for drug saving &&&&&&&&&&&&&&&&&&&&&&&");
        refreshDrugList(drugSetupAddBean);
        boolean saved = drugService.saveDrug(drugSetupAddBean);
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/adddrug";
        }
        return "redirect:/drugBrand/list/category";
    }

    /**
     * User can delete drug from Add Drug page. so while deleting the index not
     * maintained. This method use to make correct index.
     *
     *
     */
    private void refreshDrugList(DrugSetupAddBean drugSetupAddBean) {

        List<DrugSetupDrugBean> lst_drugSetupDrugBeanPage = drugSetupAddBean.getDrugSetupDrugBean();

        Iterator<DrugSetupDrugBean> it_drugSetupDrugBeanPage = lst_drugSetupDrugBeanPage.iterator();
        while (it_drugSetupDrugBeanPage.hasNext()) {

            DrugSetupDrugBean drugSetupDrugBean = it_drugSetupDrugBeanPage.next();
            if (drugSetupDrugBean == null || drugSetupDrugBean.getStrength() == null) {
                it_drugSetupDrugBeanPage.remove();
            }
        }
        drugSetupAddBean.setDrugSetupDrugBean(lst_drugSetupDrugBeanPage);
        logger.info("Total drugs for saving :::  " + lst_drugSetupDrugBeanPage.size());
        int i = 0;
        for (DrugSetupDrugBean drugSetupDrugBean : lst_drugSetupDrugBeanPage) {

            //String str_strength = drugSetupDrugBean.getStrength();
//            if(str_strength != null){
//                lst_drugSetupDrugBeanRefresh.add(drugSetupDrugBean);
//            }
            logger.info("Strength  :::  " + drugSetupDrugBean.getStrength() + "at i " + i);
            i++;
        }
    }

    /**
     *
     * @param drugBrand
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{brandNameId}", method = RequestMethod.GET)
    public String editDrug(Model model, @PathVariable("brandNameId") Integer brandNameId) {

        DrugSetupAddBean drugSetupAddBeanDb = drugService.getDrugsByBrandNameById(brandNameId);
        drugSetupAddBeanDb.setSaveAction(false);
        drugSetupAddBeanDb.setUpdateAction(true);
        drugSetupAddBeanDb.setDeleteAction(false);

        model.addAttribute("drugSetupAddBean", drugSetupAddBeanDb);
//        model.addAttribute("drugUnitList", setupsDelegate.getDrugUnitsList());
        return "adddrug";
    }

    @RequestMapping(value = "/delete/{brandNameId}", method = RequestMethod.GET)
    public String deleteDrug(Model model, @PathVariable("brandNameId") Integer brandNameId) {

        DrugSetupAddBean drugSetupAddBeanDb = drugService.getDrugsByBrandNameById(brandNameId);
        drugSetupAddBeanDb.setSaveAction(false);
        drugSetupAddBeanDb.setUpdateAction(false);
        drugSetupAddBeanDb.setDeleteAction(true);

        model.addAttribute("drugSetupAddBean", drugSetupAddBeanDb);
//        model.addAttribute("drugUnitList", setupsDelegate.getDrugUnitsList());
        return "adddrug";
    }

    @RequestMapping(value = "/importlist", method = RequestMethod.POST)
    public ModelAndView importDrugList(@RequestParam(value = "fileData", required = false) MultipartFile file,
            RedirectAttributes redirectAttrs, HttpServletRequest request) throws Exception{

        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list/drugdata");
        try {
            int userId = 0;
            SessionBean sessionBean = request.getSession() != null && request.getSession().getAttribute("sessionBean") != null
                    ? (SessionBean) request.getSession().getAttribute("sessionBean") : null;
            if (sessionBean != null) {
                userId = sessionBean.getUserId();
            }
            drugService.saveDrugData(file, userId);
            redirectAttrs.addFlashAttribute("message", "Drug list has been imported successfully.");
            modelAndView.addObject("message", "Drug list has been imported successfully.");
            //smodelAndView.addObject("drugCategoriesList", lst_drugCategoryListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            //modelAndView.addObject("errorMessage",e.getMessage());
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            
        }

        return modelAndView;//"redirect:/drugBrand/list/category";
    }
    
    @RequestMapping(value = "/uploadDrug", method = RequestMethod.POST)
    @ResponseBody JsonResponse upload(@RequestParam(value = "fileData", required = true) String file) {

        JsonResponse modelAndView = new JsonResponse();
        try {
            int userId = 0;
            drugService.saveDrugData(null, userId);
            //smodelAndView.addObject("drugCategoriesList", lst_drugCategoryListDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;//"redirect:/drugBrand/list/category";
    }

    @RequestMapping(value = "/deletedrugdetail/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String deletedrugDetail(
            @RequestBody String jsonRequest, HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            DrugDetail drug = new DrugDetail();

            JsonNode ndc = rootNode.path("ndc");

            // drug.setDrugNDC(new Long(ndc.asLong()).Value());
            //drug.setArchived("ndc");
            drug.setArchived("N");
            this.drugService.archiveDrugDetail(ndc.asLong());
            //response = JsonResponseComposer.composeSuccessResponse("Drug detail has been saved successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;//returnToLevelTwoQueue(request,pati
    }

    @RequestMapping(value = "/updatedrugdetail/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String updatedrugDetail(
            @RequestBody String jsonRequest, HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
        //ModelAndView modelAndView = new ModelAndView("portal/transferRList");
        String response = "";
        //try {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonRequest);

        DrugDetail drug = new DrugDetail();

        JsonNode ndc = rootNode.path("ndc");
        JsonNode gcn = rootNode.path("gcn");
        JsonNode gpi = rootNode.path("gpi");
        JsonNode thereupatic = rootNode.path("thereupatic");
        JsonNode inStock = rootNode.path("inStock");
        ////////////////////////////////////////////////////////////////////

        JsonNode generic = rootNode.path("generic");
        JsonNode brand = rootNode.path("brand");
        JsonNode strength = rootNode.path("strength");
        JsonNode formDesc = rootNode.path("formDesc");
        JsonNode packingDesc = rootNode.path("packingDesc");
        JsonNode defQty = rootNode.path("defQty");
        JsonNode packageSizeValues = rootNode.path("packageSizeValues");
        JsonNode basePrice = rootNode.path("basePrice");
        JsonNode marginPercent = rootNode.path("marginPercent");
        JsonNode additionalFee = rootNode.path("additionalFee");

        //////////////////////////////////////////
        drug.setDrugNDC(ndc.asLong());
        drug.setDrugGCN(gcn.asLong());
        drug.setDrugGPI(gpi.asLong());
        drug.setTherapy(thereupatic.asText());
        drug.setGenericName(generic.asText());
        drug.setInStock(inStock.asText());
        drug.setBrandName(brand.asText());
        drug.setStrength(strength.asText());
        drug.setFormDesc(formDesc.asText());
        drug.setPackingDesc(packingDesc.asText());
        drug.setDefQty(defQty.asInt());
        drug.setPackageSizeValues(packageSizeValues.asText());
        drug.setBasePrice(new Double(basePrice.asDouble()).floatValue());
        drug.setMarginPercent(new Double(marginPercent.asDouble()).floatValue());
        drug.setAdditionalFee(new Double(additionalFee.asDouble()).floatValue());
        drug.setArchived("N");
        int userId = 0;
        SessionBean sessionBean = request.getSession() != null && request.getSession().getAttribute("sessionBean") != null
                ? (SessionBean) request.getSession().getAttribute("sessionBean") : null;
        if (sessionBean != null) {
            userId = sessionBean.getUserId();
        }
        this.drugService.updateDrugDetail(drug, id, userId);
        //response = JsonResponseComposer.composeSuccessResponse("Drug detail has been saved successfully.");

        /* } catch (Exception e) {
            e.printStackTrace();
        }*/
        return response;//returnToLevelTwoQueue(request,patientId,""+id);
    }
    
    //////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/importDrugFile", method = RequestMethod.GET)
    public ModelAndView importDrugFile() {
        return new ModelAndView("redirect:/support");
    }

    //////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/importDrugFile", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView importDrugFile(@ModelAttribute SupportModel supportModel,
            @RequestParam("files[]") List<MultipartFile> files, @RequestParam("docTypeOpt") String docTypeOpt,
            HttpServletRequest request) throws FileNotFoundException, IOException {

        ModelAndView modelAndView = new ModelAndView("support");
        try {
            logger.info(" Inside the upload receipts method " + files.size());
            modelAndView.addObject("supportModel", supportModel);
            if (AppUtil.getSafeStr(docTypeOpt, "").equals("-1")) {
                modelAndView.addObject("errorMessage", "Please Select valid document type.");
                return modelAndView;
            }
            if (CommonUtil.isNullOrEmpty(files)) {
                modelAndView.addObject("errorMessage", "Please Select files.");
                return modelAndView;
            }
            if (files != null && files.size() == 1) {
                String istFileName = files.get(0).getOriginalFilename();//.getName();
                if (AppUtil.getSafeStr(istFileName, "").length() == 0) {
                    modelAndView.addObject("errorMessage", "Please Select files.");
                    return modelAndView;
                }
            }

            String path = CommonUtil.getDocumentPath(docTypeOpt);
            String destPath = CommonUtil.getDocumentDestinationPath(docTypeOpt);
            for (MultipartFile f : files) {
                logger.info("File name " + f.getName());
                CommonUtil.saveDrugDocPath(f, path, destPath, docTypeOpt, drugService);
            }
            modelAndView.addObject("message", messageSource.getMessage("support.success", null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /////////////////////////////////////////////////////////////////////////    
    @RequestMapping(value = "/uploaddrugdocument", method = RequestMethod.POST)
    public ModelAndView uploadDrugDocument(@RequestParam(value = "fileUpload", required = false) MultipartFile file,
            @RequestParam(value = "drugDetailId", required = false) Long drugDetailId,
            @RequestParam(value = "docTypeOpt", required = false) String docTypeOpt, 
            RedirectAttributes redirectAttrs, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list/drugdata");
        try {
            int userId = 0;
            SessionBean sessionBean = request.getSession() != null && request.getSession().getAttribute("sessionBean") != null
                    ? (SessionBean) request.getSession().getAttribute("sessionBean") : null;
            if (sessionBean != null) {
                userId = sessionBean.getUserId();
            }
            ///////////////////////////////////////////////
//            if (AppUtil.getSafeStr(docTypeOpt, "").equalsIgnoreCase("pdf")) {
//                String destPath = Constants.DRUG_ATTACHMENT_PATH + Constants.PATIENT_GUIDE_ATTACHMENT;
//                FileCopyUtils.copy(file.getBytes(), new File(destPath + file.getOriginalFilename()));
//                CommonUtil.executeCommand(Constants.COMMAND + Constants.PATIENT_GUIDE_ATTACHMENT);
//                String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
//                logger.info("File Name WithOut Ext:: " + fileNameWithOutExt);
//                if (CommonUtil.isNotEmpty(fileNameWithOutExt)) {
//                    drugService.saveDrugPdfPath(Constants.INSURANCE_CARD_URL + Constants.PATIENT_GUIDE_ATTACHMENT + file.getOriginalFilename(),
//                            Long.parseLong(fileNameWithOutExt),docTypeOpt);
//                }
//            } else if (AppUtil.getSafeStr(docTypeOpt, "").equalsIgnoreCase("DrugDoc")) {
//                String destPath = Constants.DRUG_ATTACHMENT_PATH + Constants.MEDICATION_GUIDE_ATTACHMENT;
//                FileCopyUtils.copy(file.getBytes(), new File(destPath + file.getOriginalFilename()));
//                CommonUtil.executeCommand(Constants.COMMAND + Constants.MEDICATION_GUIDE_ATTACHMENT);
//                String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
//                logger.info("File Name WithOut Ext:: " + fileNameWithOutExt);
//                if (CommonUtil.isNotEmpty(fileNameWithOutExt)) {
//                    drugService.saveDrugPdfPath(Constants.INSURANCE_CARD_URL + Constants.MEDICATION_GUIDE_ATTACHMENT + file.getOriginalFilename(),
//                            Long.parseLong(fileNameWithOutExt),docTypeOpt);
//                }
//            } //////////////////////////////////////////////
//            else {
//                drugService.uploadDrugDocument(file, userId, drugDetailId, !AppUtil.getSafeStr(docTypeOpt, "").equalsIgnoreCase("image"));
//            }
            String path = CommonUtil.getDocumentPath(docTypeOpt);
            String destPath = CommonUtil.getDocumentDestinationPath(docTypeOpt);
            CommonUtil.saveDrugDocPath(file, path, destPath, docTypeOpt, drugService);
            redirectAttrs.addFlashAttribute("message", "Document has been uploaded successfully for drug with NDC " + drugDetailId);
            //modelAndView.addObject("message","Drug list has been imported successfully.");
            //smodelAndView.addObject("drugCategoriesList", lst_drugCategoryListDTO);
        } catch (Exception e) {
            //modelAndView.addObject("errorMessage",e.getMessage());
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
        }

        return modelAndView;//"redirect:/drugBrand/list/category";
    }

    @RequestMapping(value = {"/downloaddocument"}, method = RequestMethod.POST)
    public ModelAndView downloadDocument(@RequestParam(value = "drugDetailIdDocument", required = false) Long drugDetailIdDocument,
            @RequestParam(value = "dpfdoc", required = false) int dpfdoc, RedirectAttributes redirectAttrs,
            HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list/drugdata");
        try {

            DrugDetailDocuments document = drugService.retireveDrugDocument(drugDetailIdDocument, true);
            response.setContentType(document.getContentType());
            response.setContentLength(document.getPdfDoc().length);
            response.setHeader("Content-Disposition", "inline; filename=\"pdf_" + drugDetailIdDocument + ".pdf" + "\"");

            FileCopyUtils.copy(document.getPdfDoc(), response.getOutputStream());
        } catch (Exception e) {
            //modelAndView.addObject("errorMessage",e.getMessage());
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/downloaddocument"}, method = RequestMethod.GET)
    public void downloadDocument(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list/drugdata");
        try {

            DrugDetailDocuments document = drugService.retireveDrugDocument(AppUtil.getSafeLong(
                    request.getParameter("docid"), 0L), true);
            response.setContentType(document.getContentType());
            response.setContentLength(document.getPdfDoc().length);
            response.setHeader("Content-Disposition", "inline; filename=\"pdf_" + AppUtil.getSafeStr(
                    request.getParameter("docid"), "DrugDoc") + ".pdf" + "\"");

            FileCopyUtils.copy(document.getPdfDoc(), response.getOutputStream());
        } catch (Exception e) {
            //modelAndView.addObject("errorMessage",e.getMessage());
            //redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
        }
        //return modelAndView;
    }

    @RequestMapping(value = {"/downloadimage"}, method = RequestMethod.POST)
    public ModelAndView downloadImage(@RequestParam(value = "drugDetailIdImage", required = false) Long drugDetailIdImage,
            RedirectAttributes redirectAttrs,
            HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("redirect:/drugBrand/list/drugdata");
        try {

            DrugDetailDocuments document = drugService.retireveDrugDocument(drugDetailIdImage, false);
            String arr[] = document.getContentTypeImage().split("/");
            String ext = "png";
            if (arr != null && arr.length > 0) {
                ext = arr[arr.length - 1];
            }
            response.setContentType(document.getContentTypeImage());
            response.setContentLength(document.getImage().length);
            response.setHeader("Content-Disposition", "attachment; filename=\"image_" + drugDetailIdImage + "." + ext + "\"");

            FileCopyUtils.copy(document.getImage(), response.getOutputStream());
        } catch (Exception e) {
            //modelAndView.addObject("errorMessage",e.getMessage());
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/downloadimage"}, method = RequestMethod.GET)
    public void downloadImage(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try {

            DrugDetailDocuments document = drugService.retireveDrugDocument(AppUtil.getSafeLong(
                    request.getParameter("docid"), 0L), false);
            String arr[] = document.getContentTypeImage().split("/");
            String ext = "png";
            if (arr != null && arr.length > 0) {
                ext = arr[arr.length - 1];
            }
            response.setContentType(document.getContentTypeImage());
            response.setContentLength(document.getImage().length);
            response.setHeader("Content-Disposition", "inline; filename=\"image_" + AppUtil.getSafeStr(
                    request.getParameter("docid"), "DrugImage") + "." + ext + "\"");

            FileCopyUtils.copy(document.getImage(), response.getOutputStream());
        } catch (Exception e) {
            //modelAndView.addObject("errorMessage",e.getMessage());

            e.printStackTrace();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/searchDrugGenericName", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugGenericByName(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String name = nameNode.asText();
            List<BusinessObject> lstObj = new ArrayList();

            lstObj.add(new BusinessObject("genericName", name, Constants.HIBERNATE_LIKE_START_OPERATOR));

            DrugGeneric drugGeneric = new DrugGeneric();

            List<DrugGeneric> lst = this.drugService.populateRecordList(drugGeneric, lstObj, "genericName", Constants.HIBERNATE_ASC_ORDER);

            List nameList = new ArrayList();

            for (DrugGeneric obj : lst) {
                String genericName = obj.getGenericName();
                nameList.add(genericName);
            }

            String str_response = objectMapper.writeValueAsString(nameList);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/searchDrugForm", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugForm(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String name = nameNode.asText();
            List<BusinessObject> lstObj = new ArrayList();

            lstObj.add(new BusinessObject("formDescr", name, Constants.HIBERNATE_LIKE_START_OPERATOR));

            DrugForm drugGeneric = new DrugForm();

            List<DrugForm> lst = this.drugService.populateRecordList(drugGeneric, lstObj, "formDescr", Constants.HIBERNATE_ASC_ORDER);

            List nameList = new ArrayList();

            for (DrugForm obj : lst) {
                String genericName = obj.getFormDescr();
                nameList.add(genericName);
            }

            String str_response = objectMapper.writeValueAsString(nameList);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/searchDrugPacking", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String searchDrugPacking(@RequestBody String jsonRequest, HttpServletRequest request) throws ServletException, IOException {
        logger.info("json request for search Drug Brand Name by Name @@@@@@@@@@@@@@@@@  " + jsonRequest);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
            JsonNode nameNode = rootNode.path("name");
            String name = nameNode.asText();
            List<BusinessObject> lstObj = new ArrayList();

            lstObj.add(new BusinessObject("packingDescr", name, Constants.HIBERNATE_LIKE_START_OPERATOR));

            DrugPacking drugGeneric = new DrugPacking();

            List<DrugPacking> lst = this.drugService.populateRecordList(drugGeneric, lstObj, "packingDescr", Constants.HIBERNATE_ASC_ORDER);

            List nameList = new ArrayList();

            for (DrugPacking obj : lst) {
                String genericName = obj.getPackingDescr();
                nameList.add(genericName);
            }

            String str_response = objectMapper.writeValueAsString(nameList);
            return str_response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return patientProfileService.updatePatientAddress(patientAddress, sessionBean.getUserId());
        return "";
    }
}
