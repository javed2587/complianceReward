package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.model.RewardPoints;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mzubair
 */
@Controller
@RequestMapping(value = "/rewardPoints")
public class RewardPointsSetupController {

    @Autowired
    private SetupService setupService;
    @Autowired
    private MessageSource messageSource;
    SessionBean sessionBean;

    @InitBinder
    public void init(HttpServletRequest request) {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView loadPage() {
        ModelAndView modelAndView = new ModelAndView("rewardsetup");
        modelAndView.addObject("rewardPoints", setupService.getRewardPoints());
        return modelAndView;
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute RewardPoints rewardPoints, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("rewardsetup");
        boolean isSaved = setupService.saveRewardPoints(rewardPoints, sessionBean.getUserId());
        if (isSaved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
        return new ModelAndView("redirect:/rewardPoints/load");
    }
}
