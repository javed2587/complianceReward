package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.model.SurveyResponseType;
import com.ssa.cms.service.SurveyService;
import com.ssa.cms.validator.SurveyResponseValidator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zubair
 */
@Controller
@RequestMapping(value = "/surveyResponse")
public class SurveyResponseSetupController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SurveyResponseValidator surveyResponseValidator;

    @RequestMapping(value = "/list")
    public ModelAndView viewSurveyResponseList() {
        ModelAndView modelAndView = new ModelAndView("surveyresponselist");
        modelAndView.addObject("surveyResponselist", surveyService.getSurveyResponseList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView viewSurveyResponse() {
        ModelAndView modelAndView = new ModelAndView("addsurveyresponse");
        modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
        modelAndView.addObject("surveyResponseType", new SurveyResponseType());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveSurveyResponse(@ModelAttribute @Valid SurveyResponseType surveyResponseType, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        surveyResponseValidator.validate(surveyResponseType, bindingResult);
        if (bindingResult.hasErrors()) {
            // Should not re-init the AutoPopulatingList
            ModelAndView modelAndView = new ModelAndView("addsurveyresponse");
            modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
            modelAndView.addObject("surveyResponseType", surveyResponseType);
            return modelAndView;
        }
        Boolean duplicate = surveyService.checkDuplicateSurveyResponseDetail(surveyResponseType);
        if (duplicate) {
            ModelAndView modelAndView = new ModelAndView("addsurveyresponse");
            modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
            modelAndView.addObject("surveyResponseType", surveyResponseType);
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.surveyResponseType.duplicate", null, null));
            return modelAndView;
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean saved = surveyService.saveSurveyResponse(surveyResponseType, sessionBean.getUserId());
        if (saved) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            return new ModelAndView("redirect:/surveyResponse/list");
        } else {
            ModelAndView modelAndView = new ModelAndView("addsurveyresponse");
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
            modelAndView.addObject("surveyResponseType", surveyResponseType);
            return modelAndView;
        }

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSurveyResponse(@PathVariable("id") Integer id) {
        SurveyResponseType surveyResponseType = surveyService.getSurveyResponseDetail(id);
        ModelAndView modelAndView = new ModelAndView("addsurveyresponse");
        modelAndView.addObject("surveyResponseTypeList", surveyService.getSurveyResponseTypeList());
        modelAndView.addObject("surveyResponseType", surveyResponseType);
        return modelAndView;
    }

    @RequestMapping(value = "/associatedResponses/{responseTypeId}", produces = "application/json")
    public @ResponseBody
    String getResponsesByResponseType(@PathVariable Integer responseTypeId) {
        return surveyService.getResponsesByResponseType(responseTypeId);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletetSurveyResponse(@PathVariable("id") Integer responseTypeId, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/surveyResponse/list");
        SurveyResponseType surveyResponseType = surveyService.getSurveyResponseDetail(responseTypeId);
        if (surveyResponseType.getAssociated()) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        } else {
            surveyService.deleteSurveyResponse(responseTypeId);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/isdelete/{id}", produces = "application/json")
    public @ResponseBody
    boolean getDeleteResponseHandler(@PathVariable String id,
            HttpServletRequest request) throws Exception {
        return surveyService.isResponseAssociated(Integer.parseInt(id));
    }
}
