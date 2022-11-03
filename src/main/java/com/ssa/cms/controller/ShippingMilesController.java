/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.PharmacyZipCodes;
import com.ssa.cms.validator.ShippingMilesValidator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tahir Saeed
 */
@Controller
@RequestMapping(value = "/shippingMiles")
public class ShippingMilesController {
    
    @Autowired
    private SetupService setupService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;
    @Autowired
    private ShippingMilesValidator milesValidator;
    
    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView shippingMilesList() {
        ModelAndView modelandView = new ModelAndView("shippingmileslist");
        getPharmacyZipCodes(modelandView);
        return modelandView;
    }
    
    private void getPharmacyZipCodes(ModelAndView modelandView) {
        modelandView.addObject("deliveryDistanceslist", setupService.getDeliveryDistancesList());
        modelandView.addObject("deliveryPreferenceslist", setupService.getDeliveryPreferencesList());
        modelandView.addObject("list", setupService.getPharmacyZipCodesList());
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelandView = new ModelAndView("addshippingmiles");
        getPharmacyZipCodes(modelandView);
        modelandView.addObject("pharmacyZipCodes", new PharmacyZipCodes());
        return modelandView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addRecord(@ModelAttribute @Valid PharmacyZipCodes pharmacyZipCodes, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("addshippingmiles");
        milesValidator.validate(pharmacyZipCodes, result);
        getPharmacyZipCodes(modelAndView);
        modelAndView.addObject("pharmacyZipCodes", pharmacyZipCodes);
        if (result.hasErrors()) {
            return modelAndView;
        }
        if (setupService.savePharmacyZipCodes(pharmacyZipCodes, sessionBean.getUserId())) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
        return new ModelAndView("redirect:/shippingMiles/list");
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editRecord(@PathVariable("id") Integer id) {
        ModelAndView modelandView = new ModelAndView("addshippingmiles");
        getPharmacyZipCodes(modelandView);
        modelandView.addObject("pharmacyZipCodes", setupService.getPharmacyZipCodesById(id));
        return modelandView;
    }
    
    @RequestMapping(value = "/load/{id}", produces = "application/json")
    public @ResponseBody
    String getDeliveryPreferencesDistanceById(@PathVariable("id") Integer id) {
        return setupService.getDeliveryPreferencesDistanceById(id);
    }
    
    @RequestMapping(value = "/getDeliveryDistances/{id}/{dprefId}", produces = "application/json")
    public @ResponseBody
    String getDeliveryDistances(@PathVariable("id") Integer id, @PathVariable("dprefId") Integer dprefId) {
        return setupService.getDeliveryDistances(id, dprefId);
    }
}
