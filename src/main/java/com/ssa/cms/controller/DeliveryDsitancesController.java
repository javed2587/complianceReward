package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.DeliveryDistances;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
 * @author mzubair
 */
@Controller
@RequestMapping(value = "/deliveryDsitances")
public class DeliveryDsitancesController {

    @Autowired
    private SetupService setupService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView("adddeliverydsitances");
        modelAndView.addObject("deliveryDsitances", setupService.getDeliveryDsitances());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveRecords(@ModelAttribute @Valid DeliveryDistances deliveryDsitances, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("adddeliverydsitances");
        modelAndView.addObject("deliveryDsitances", deliveryDsitances);
        if (result.hasErrors()) {
            return modelAndView;
        }
        if (setupService.saveDeliveryDsitances(deliveryDsitances, sessionBean.getUserId())) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
        return new ModelAndView("redirect:/deliveryDsitances/add");
    }

    @RequestMapping(value = "/delete/{id}", produces = "application/json")
    public @ResponseBody
    boolean getDeleteRecordHandler(@PathVariable Integer id,
            HttpServletRequest request) throws Exception {
        return setupService.deleteDeliveryDistances(id);
    }
}
