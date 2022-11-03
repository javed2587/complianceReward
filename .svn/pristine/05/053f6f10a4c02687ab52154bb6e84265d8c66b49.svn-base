package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.service.PharmacyService;
import com.ssa.cms.validator.PharmacyValidator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zubair
 */
@Controller
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PharmacyValidator pharmacyValidator;
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

    @RequestMapping(value = "/list")
    public ModelAndView pharmaciesList() {
        ModelAndView modelAndView = new ModelAndView("managepharmacieslist");
        modelAndView.addObject("pharmacieslist", pharmacyService.getPharmaciesList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("addpharmacy");
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setStatus("Active");
        modelAndView.addObject("stateList", pharmacyService.getStatesList());
        modelAndView.addObject("pharmacy", pharmacy);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView savePharmacy(@ModelAttribute @Valid Pharmacy pharmacy, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addpharmacy");
        pharmacyValidator.validate(pharmacy, result);
        if (result.hasErrors()) {
            modelAndView.addObject("stateList", pharmacyService.getStatesList());
            modelAndView.addObject("pharmacy", pharmacy);
            return modelAndView;
        }
        boolean isTitleDuplicate = pharmacyService.isPharmacyTitleDuplicate(pharmacy);
        if (isTitleDuplicate) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.pharmacyTitle.duplicate", null, null));
            modelAndView.addObject("stateList", pharmacyService.getStatesList());
            modelAndView.addObject("pharmacy", pharmacy);
            return modelAndView;
        }
        boolean isNPIDuplicate = pharmacyService.isNPINoDuplicate(pharmacy);
        if (isNPIDuplicate) {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.pharmacyNPI.duplicate", null, null));
            modelAndView.addObject("stateList", pharmacyService.getStatesList());
            modelAndView.addObject("pharmacy", pharmacy);
            return modelAndView;
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean isSaved = pharmacyService.savePharmacy(pharmacy, sessionBean.getUserId());
        if (isSaved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("stateList", pharmacyService.getStatesList());
            modelAndView.addObject("pharmacy", pharmacy);
            return modelAndView;
        }
        return new ModelAndView("redirect:list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPharmacy(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("addpharmacy");
        modelAndView.addObject("pharmacyUserList", consumerRegistrationService.getPharmacyUserList(id));
        modelAndView.addObject("pharmacyFacilityOperations", consumerRegistrationService.getPharmacyFacilityOperations(id));
        modelAndView.addObject("pharmacy", pharmacyService.getPharmacyById(id));
        return modelAndView;
    }
}
