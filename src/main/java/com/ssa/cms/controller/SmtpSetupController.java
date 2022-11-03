package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.SmtpServerInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/smtp")
public class SmtpSetupController {

    @Autowired
    private SetupService setupsDelegate;
    @Autowired
    private MessageSource messageSource;

    SessionBean sessionBean;

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
    public ModelAndView smtpList(@ModelAttribute SmtpServerInfo smtpServerInfo) {
        List<SmtpServerInfo> list = setupsDelegate.getSmtpList();
        ModelAndView modelAndView = new ModelAndView("smtplist");
        modelAndView.addObject("smtplist", list);
        return modelAndView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEdit(@ModelAttribute SmtpServerInfo smtpServerInfo, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("addsmtp");
        modelAndView.addObject("smtpServerInfo", new SmtpServerInfo());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveSmtp(@ModelAttribute @Valid SmtpServerInfo smtpServerInfo, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/addsmtp";
        }
        boolean duplicate = setupsDelegate.getSMTPByEmail(smtpServerInfo.getFromEmail(), smtpServerInfo.getSmtpId());
        if (duplicate) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.email.duplicate", null, null));
            return "/addsmtp";
        }
        boolean saved = setupsDelegate.saveSmtpSetup(smtpServerInfo, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return "/addsmtp";
        }
        return "redirect:/smtp/list";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSmtp(@PathVariable("id") Integer id) {
        SmtpServerInfo smtpServerInfo = setupsDelegate.getSmtpById(id);
        ModelAndView modelAndView = new ModelAndView("addsmtp");
        modelAndView.addObject("smtpServerInfo", smtpServerInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSmtp(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean delete = setupsDelegate.deleteSmtp(id);
        if (delete) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/smtp/list");
        return modelAndView;
    }
}
