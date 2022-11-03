package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.model.SurveyResponseType;
import com.ssa.cms.service.SurveyService;
import com.ssa.cms.validator.SurveyResponseTypeValidator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/surveyResponseType")
public class SurveyResponseTypeController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SurveyResponseTypeValidator surveyResponseTypeValidator;

    @RequestMapping(value = "/list")
    public ModelAndView getResponseTypeList() {
        ModelAndView modelAndView = new ModelAndView("surveyresponsetypelist");
        modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addSurveyResponseType() {
        ModelAndView modelAndView = new ModelAndView("addsurveyresponsetype");
        modelAndView.addObject("surveyResponseType", new SurveyResponseType());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveSurveyResponseType(@ModelAttribute @Valid SurveyResponseType responseType, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        surveyResponseTypeValidator.validate(responseType, result);
        if (result.hasErrors()) {
            return new ModelAndView("/addsurveyresponsetype");
        }
        boolean checkDuplicate = surveyService.checkSurveyResponseTypeTitle(responseType);
        if (checkDuplicate) {
            model.addAttribute("errorMessage", messageSource.getMessage("field.surveyResponseType.duplicate", null, null));
            return new ModelAndView("/addsurveyresponsetype");
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean isSave = surveyService.saveSurveyResponseType(responseType, sessionBean.getUserId());
        if (isSave) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return new ModelAndView("/addsurveyresponsetype");
        }
        return new ModelAndView("redirect:list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSurveyResponseType(@PathVariable("id") Integer id) {
        SurveyResponseType surveyResponseType = surveyService.getSurveyResponseTypeById(id);
        ModelAndView modelAndView = new ModelAndView("addsurveyresponsetype");
        modelAndView.addObject("surveyResponseType", surveyResponseType);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletetSurveyResponseType(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/surveyResponseType/list");
        SurveyResponseType surveyResponseType = surveyService.getSurveyResponseTypeById(id);
        if (surveyResponseType.getAssociated()) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        } else {
            surveyService.deleteSurveyResponseType(id);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        }
        return modelAndView;
    }
}
