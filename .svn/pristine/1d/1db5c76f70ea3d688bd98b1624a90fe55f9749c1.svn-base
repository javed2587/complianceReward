package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.model.SurveyQuestion;
import com.ssa.cms.service.SurveyService;
import com.ssa.cms.validation.SurveyQuestionValidator;
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
 * @author msheraz
 */
@Controller
@RequestMapping(value = "/surveyQuestion")
public class SurveyQuestionController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SurveyQuestionValidator surveyQuestionValidator;

    @RequestMapping(value = "/list")
    public ModelAndView viewSurveyQuestionList() {
        ModelAndView modelAndView = new ModelAndView("surveyquestionlist");
        modelAndView.addObject("surveyQuestionList", surveyService.getSurveyQuestionList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addSurveyQuestion() {
        ModelAndView modelAndView = new ModelAndView("addsurveyquestion");
        modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
        modelAndView.addObject("surveyQuestion", new SurveyQuestion());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editSurveyQuestion(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("addsurveyquestion");
        modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
        modelAndView.addObject("surveyQuestion", surveyService.getSurveyQuestionById(id));
        return modelAndView;
    }

    //Are 30 tablets an ample month supply?
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveSurveyQuestion(@ModelAttribute @Valid SurveyQuestion surveyQuestion, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        surveyQuestionValidator.validate(surveyQuestion, result);
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/addsurveyquestion");
            modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
            return modelAndView;
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean isSave = surveyService.saveSurveyQuestion(surveyQuestion, sessionBean.getUserId());
        if (isSave) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            return new ModelAndView("redirect:list");
        } else {
            ModelAndView modelAndView = new ModelAndView("/addsurveyquestion");
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSurveyQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/surveyQuestion/list");
        boolean isAssociated = surveyService.isSurveyQuestionAssociated(id);
        if (isAssociated) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        } else {
            boolean deleted = surveyService.deleteSurveyQuestion(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.db.error", null, null));
            }
        }
        return modelAndView;
    }
}
