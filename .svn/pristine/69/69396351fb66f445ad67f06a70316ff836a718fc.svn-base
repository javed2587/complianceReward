package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.CMSService;
import com.ssa.cms.model.CMSEmailContent;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Zubair
 */
@Controller
@RequestMapping(value = "/email")
public class CMSEmailSetupController {

    @Autowired
    private CMSService cMSService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("cmsemailsetup");
        modelAndView.addObject("cmseTypelist", cMSService.getCMSEmailTypeList());
        modelAndView.addObject("cMSEmailContent", new CMSEmailContent());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute @Valid CMSEmailContent cMSEmailContent, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("cmsemailsetup");
        modelAndView.addObject("cmseTypelist", cMSService.getCMSEmailTypeList());
        if (!validateField(result, modelAndView, cMSEmailContent)) {
            return modelAndView;
        }
        boolean isSave = cMSService.saveCMSEContent(cMSEmailContent, sessionBean.getUserId());
        if (isSave) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("cMSEmailContent", cMSEmailContent);
            return modelAndView;
        }
        return new ModelAndView("redirect:/email/add");
    }

    private boolean validateField(BindingResult result, ModelAndView modelAndView, CMSEmailContent cMSEmailContent) {
        boolean valid = true;
        if (result.hasErrors()) {
            modelAndView.addObject("cMSEmailContent", cMSEmailContent);
            valid = false;
        }
        if (cMSEmailContent.getcMSEmailType().getId() == 0) {
            modelAndView.addObject("cMSEmailContent", cMSEmailContent);
            modelAndView.addObject("message1", "Required");
            valid = false;
        }
        if (cMSEmailContent.getSubject() != null && cMSEmailContent.getSubject().isEmpty()) {
            modelAndView.addObject("cMSEmailContent", cMSEmailContent);
            modelAndView.addObject("message2", "Required");
            valid = false;
        }
        return valid;
    }

    @RequestMapping(value = "/getRecord/{id}", produces = "application/json")
    public @ResponseBody
    String getRecord(@PathVariable("id") Integer id) throws Exception {
        return cMSService.getCMSEContentById(id);
    }
}
