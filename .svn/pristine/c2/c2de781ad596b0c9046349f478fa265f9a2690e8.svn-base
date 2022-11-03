/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.DrugAdditionalMargin;
import com.ssa.cms.model.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
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
@RequestMapping(value = "/drugAdditionalMargin")
public class DrugAdditionalMarginController {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;

    SessionBean sessionBean;

    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView drugAdditionalMarginList() {
        ModelAndView modelandView = new ModelAndView("drugadditionalmarginlist");
        modelandView.addObject("list", setupsDelegate.getDrugAdditionalMarginList());
        return modelandView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelandView = new ModelAndView("adddrugadditionalmargin");
//        getDrugList(modelandView);
        modelandView.addObject("drugAdditionalMargin", new DrugAdditionalMargin());
        return modelandView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addRecord(@ModelAttribute DrugAdditionalMargin drugAdditionalMargin, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("adddrugadditionalmargin");
//        getDrugList(modelAndView);
        modelAndView.addObject("drugAdditionalMargin", drugAdditionalMargin);
        if (setupsDelegate.saveDrugAdditionalMargin(drugAdditionalMargin, sessionBean.getUserId())) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
        return new ModelAndView("redirect:/drugAdditionalMargin/list");
    }

//    private void getDrugList(ModelAndView modelAndView) {
//        modelAndView.addObject("drugCategorylist", setupsDelegate.getDrugCategorysList());
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDrugAdditionalMargin(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("adddrugadditionalmargin");
//        getDrugList(modelAndView);
        modelAndView.addObject("drugAdditionalMargin", setupsDelegate.getDrugAdditionalMarginById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDrugAdditionalMargin(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (setupsDelegate.deleteDrugAdditionalMargin(id)) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        return new ModelAndView("redirect:/drugAdditionalMargin/list");
    }

    @RequestMapping(value = "/load/{id}", produces = "application/json")
    public @ResponseBody
    String getDrugAdditionalMarginPricesByDrugBrandId(@PathVariable("id") Integer drugBrandId) {
        return setupsDelegate.getDrugAdditionalMarginPricesByDrugBrandId(drugBrandId);
    }
}
