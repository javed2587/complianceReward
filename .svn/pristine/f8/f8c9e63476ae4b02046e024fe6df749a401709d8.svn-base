package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.PermissionService;
import com.ssa.cms.dto.ValueDTO;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.RoleTypes;
import com.ssa.cms.model.Users;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.service.PhoneValidationService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    PMSTextFlowService textFlowDAO;

    private SessionBean sessionBean;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid Users users, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        List<RoleTypes> roles = permissionService.getRoleTypeList();
        model.addAttribute("roles", roles);
//        model.addAttribute("pharmaciesList", permissionService.getPharmaciesList());
        if (!validateUser(result, users, model)) {
            return "/adduser";
        }
        boolean saved = permissionService.saveUser(users, sessionBean);
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
        }
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/exchangejson", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, ? extends Object> onBrandChange(@RequestBody String newInput) {
        Map map = new HashMap<>();
        List<Campaigns> campaignList = permissionService.getCampaignsByBrandId(convertStringToList(newInput.substring(0, newInput.length() - 1)), sessionBean.getUserId());
        map.put("campaignList", campaignList);
        return map;
    }

    @RequestMapping(value = "/edit/{id}/{isAdmin}", method = RequestMethod.GET)
    public ModelAndView editUser(@ModelAttribute Users users, @PathVariable Integer id, @PathVariable boolean isAdmin, Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("adduser");
        Users user = null;
        if (isAdmin) {
            user = permissionService.getUserById(id);
//            PharmacyUser pharmacyUser = permissionService.getPharmacyUserByUserId(id);
//            if (pharmacyUser != null && pharmacyUser.getId() != null) {
//                user.setPharmacyId(pharmacyUser.getPharmacy().getId());
//            }
        } else {
            user = permissionService.getPharmacyUserByPharmacyUserID(id);
            user.setisAdmin(Boolean.FALSE);
        }
        modelAndView.addObject("users", user);
        populatePageData(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}/{isAdmin}", method = RequestMethod.GET)
    public ModelAndView deleteRole(@PathVariable Integer id, @PathVariable boolean isAdmin, RedirectAttributes redirectAttributes) {
        boolean deleted = false;
        if (isAdmin) {
            deleted = permissionService.deleteUser(id);
        } else {
            deleted = permissionService.deletePharmacyUser(id);
        }
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        return modelAndView;
    }

    private List<Integer> convertStringToList(String str) {
        List<Integer> selectedBrands = new ArrayList<>();
        if (str != null && !"".equals(str)) {
            for (String id : str.split(",")) {
                selectedBrands.add(new Integer(id));
            }
        }
        return selectedBrands;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEditUser(@ModelAttribute Users users, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("adduser");
        users.setIsActive("Yes");
//        users.setisAdmin(Boolean.FALSE);
        modelAndView.addObject("users", users);
        populatePageData(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/loadPharmacies", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String populatePharmacies(ModelAndView modelAndView) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Pharmacy> lstPharmacies = permissionService.getPharmaciesList();
        List<ValueDTO> lstValueDTO = new ArrayList();
        for (Pharmacy pharmacy : lstPharmacies) {
            ValueDTO value = new ValueDTO();
            value.setName(pharmacy.getTitle());
            value.setValue(pharmacy.getId() + "");
            lstValueDTO.add(value);
        }
        String str_response = objectMapper.writeValueAsString(lstValueDTO);
        return str_response;

    }

    public void populatePageData(ModelAndView modelAndView) {
        List<RoleTypes> roles = permissionService.getRoleTypeList();
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("pharmaciesList", permissionService.getPharmaciesList());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listUser(@ModelAttribute Users users, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("userlist");
        List<Users> userList = permissionService.getUserList(sessionBean.getUserId());
        modelAndView.addObject("users", userList);
        return modelAndView;
    }

    private boolean validateUser(BindingResult result, Users users, Model model) throws Exception {
        boolean valid = true;
        if (result.hasErrors()) {
            valid = false;
        }
        if (users.getRoleTypes().getRoleId() == 0) {
            model.addAttribute("message1", "Please select a role");
            valid = false;
        }
        if (users.getUserName() != null && !"".equals(users.getUserName().trim()) && users.getUserName().trim().length() < 5) {
            model.addAttribute("message2", "Minimum length 5 characters");
            valid = false;
        }

        String PVURL = textFlowDAO.getURL(Constants.PVURL);
        PhoneValidationService phoneValidationService = new PhoneValidationService(PVURL);

        try {
            if (users.getisAdmin()) {
                if (permissionService.isUserExists(users.getUserName(), null, users.getUserId()) && !"".equals(users.getUserName().trim())) {
                    model.addAttribute("errorMessage", "Username already exists. Please correct and try again.");
                    valid = false;
                }
                if (permissionService.isUserExists(null, users.getEmailAddress(), users.getUserId()) && !"".equals(users.getEmailAddress().trim())) {
                    model.addAttribute("errorMessage", messageSource.getMessage("field.email.duplicate", null, null));
                    valid = false;
                }
                if (users.getisAdmin() == null) {
                    model.addAttribute("pharmacyErrorMessage", "Please select User Type");
                    valid = false;
                }
            } else {
                if (!"".equals(users.getUserName().trim()) && permissionService.isPharmacyUserExists(users.getUserName().trim(), false)) {
                    model.addAttribute("errorMessage", "Username already exists. Please correct and try again.");
                    valid = false;
                }
                if (!"".equals(users.getEmailAddress().trim()) && permissionService.isPharmacyUserExists(users.getEmailAddress().trim(), true)) {
                    model.addAttribute("errorMessage", messageSource.getMessage("field.email.duplicate", null, null));
                    valid = false;
                }
            }

            return valid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;

    }

    @RequestMapping(value = "/changepassword/{id}/{isAdmin}", method = RequestMethod.GET)
    public ModelAndView changePassword(@ModelAttribute Users users, @PathVariable Integer id, @PathVariable boolean isAdmin, Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("changepassword");
        Users user = null;
        if (isAdmin) {
            user = permissionService.getUserById(id);
        } else {
            user = permissionService.getPharmacyUserByPharmacyUserID(id);
        }

        modelAndView.addObject("users", user);
        return modelAndView;
    }

    @RequestMapping(value = "/savepassword", method = RequestMethod.POST)
    public String savePassword(@ModelAttribute Users users, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Users user = null;
        if (users.getisAdmin()) {
            user = permissionService.getUserById(users.getUserId());
        } else {
            user = permissionService.getPharmacyUserByPharmacyUserID(users.getUserId());
        }
        model.addAttribute("users", user);
        if (!validatePassword(users, model)) {
            return "/changepassword";
        }
        boolean saved = permissionService.changeUserPassword(users);
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
        }
        return "redirect:/user/list";
    }

    private boolean validatePassword(Users users, Model model) {
        boolean valid = true;
        if (users.getUserPassword() == null || "".equals(users.getUserPassword())) {
            model.addAttribute("message1", "Required");
            valid = false;
        }
        if (users.getConfirmPassword() == null || "".equals(users.getConfirmPassword())) {
            model.addAttribute("message2", "Required");
            valid = false;
        }
        if (!users.getConfirmPassword().equals(users.getUserPassword())) {
            model.addAttribute("errorMessage", "Password does not match.");
            valid = false;
        }
        return valid;
    }
}
