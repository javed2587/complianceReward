package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.Industry;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
@RequestMapping(value = "/industry")
public class IndustrySetupController implements Serializable {

    @Autowired
    private SetupService setupsDelegate;

    @Autowired
    private MessageSource messageSource;

    public String message;
    public String errorMessage;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/list")
    public ModelAndView getIndustryList() {
        List<Industry> list = setupsDelegate.getIndustries();
        ModelAndView modelAndView = new ModelAndView("industrylist");
        modelAndView.addObject("industrylist", list);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEdit(@ModelAttribute Industry industry, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("addindustry");
        modelAndView.addObject("industry", new Industry());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveIndustry(@ModelAttribute @Valid Industry industry, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String industryTitle = industry.getIndustryTitle();
        Integer industryId = industry.getIndustryId();
        if (!validateIndustry(result, industryTitle, industryId, model)) {
            return new ModelAndView("/addindustry");
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean saved = setupsDelegate.saveIndustry(industry, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return new ModelAndView("/addindustry");
        }
        return new ModelAndView("redirect:list");
    }

    private boolean validateIndustry(BindingResult result, String industryTitle, Integer industryId, Model model) throws NoSuchMessageException {
        if (result.hasErrors()) {
            return false;
        }
        if (industryTitle != null && !"".equals(industryTitle.trim()) && industryTitle.length() < 4) {
            model.addAttribute("message1", "Minimum length 4 character");
            return false;
        }
        boolean duplicate = setupsDelegate.getIndustryByName(industryTitle, industryId);
        if (duplicate) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.industryTitle.duplicate", null, null));
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editIndustry(@PathVariable("id") Integer id) {
        Industry industry = setupsDelegate.getIndustryById(id);
        ModelAndView modelAndView = new ModelAndView("addindustry");
        modelAndView.addObject("industry", industry);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteIndustry(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = setupsDelegate.deleteIndustry(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/industry/list");
        return modelAndView;
    }
}
