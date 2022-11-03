package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.model.Survey;
import com.ssa.cms.model.SurveyQuestionAssociation;
import com.ssa.cms.model.SurveyResponseDetail;
import com.ssa.cms.service.PMSEmailFlowService;
import com.ssa.cms.service.SurveyService;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.validator.SurveyValidator;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static com.rosaloves.bitlyj.Bitly.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author msheraz
 */
@Controller
@RequestMapping(value = "/survey")
public class SurveyController {

    private static final Log logger = LogFactory.getLog(SurveyController.class.getName());

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addSurvey() {
        ModelAndView modelAndView = new ModelAndView("addsurvey");
        populateDropDownList(modelAndView);
        Survey survey = new Survey();
        survey.setStatus("Active");
        modelAndView.addObject("survey", survey);
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public ModelAndView getResponseList() {
        ModelAndView modelAndView = new ModelAndView("surveylist");
        modelAndView.addObject("surveyList", surveyService.getSurveyList());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveSurvey(@ModelAttribute @Valid Survey survey, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("addsurvey");
            populateDropDownList(modelAndView);
            return modelAndView;
        }
        Boolean isDuplicate = surveyService.checkDuplicateSurvey(survey);
        if (isDuplicate) {
            ModelAndView modelAndView = new ModelAndView("addsurvey");
            populateDropDownList(modelAndView);
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.survey.duplicate", null, null));
            return modelAndView;
        }
        // Check survey is associated with campaign
        if (survey.getAssociated() != null && survey.getAssociated() && survey.getStatus().equalsIgnoreCase("InActive")) {
            ModelAndView modelAndView = new ModelAndView("addsurvey");
            populateDropDownList(modelAndView);
            modelAndView.addObject("errorMessage", messageSource.getMessage("error.associated.record", null, null));
            return modelAndView;
        }
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean isSave = surveyService.saveSurvey(survey, sessionBean.getUserId());
        if (isSave) {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            return new ModelAndView("redirect:list");
        } else {
            ModelAndView modelAndView = new ModelAndView("/addsurvey");
            modelAndView.addObject("errorMessage", messageSource.getMessage("field.saved.error", null, null));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editSurvey(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("addsurvey");
        populateDropDownList(modelAndView);
        modelAndView.addObject("survey", surveyService.getSurveyById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/addQuestions")
    public ModelAndView openSurveyQuestionDialog(@ModelAttribute Survey survey, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        boolean isSave = surveyService.addSurveyQuestions(survey, sessionBean.getUserId());
        if (isSave) {
            ModelAndView modelAndView = new ModelAndView("surveylist");
            modelAndView.addObject("surveyList", surveyService.getSurveyList());
            modelAndView.addObject("message", messageSource.getMessage("field.value.successfully", null, null));
            return modelAndView;
        }
        return null;
    }

    @RequestMapping(value = "/associate/{id}")
    public ModelAndView associateSurveyQuestions(@PathVariable Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("associatesurveyquestion");
        modelAndView.addObject("survey", surveyService.getSurveyWithAssociatedQuestions(id));
        modelAndView.addObject("surveyQuestionList", surveyService.getSurveyQuestionList());
        return modelAndView;
    }

    @RequestMapping(value = "/testSms")
    public ModelAndView sendTestSms(@ModelAttribute @Valid Survey survey, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("addsurvey");
        modelAndView.addObject("survey", survey);
        populateDropDownList(modelAndView);

        if (survey.getId() > 0 && survey.getCommunicationId() != null && !survey.getCommunicationId().isEmpty()) {
            String phone = survey.getCommunicationId().replace("-", "");
            String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
            uri = uri.replace("testSms", "takeSurvey/" + survey.getId() + "/" + phone);
            uri = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(uri)).getShortUrl();
            logger.info("Shortened Uri: " + uri);
            SMSUtil.sendSmsMessage(phone, uri);
            modelAndView.addObject("message", messageSource.getMessage("message.sent.successfully", null, null));
        }
        return modelAndView;
    }

    private void populateDropDownList(ModelAndView modelAndView) {
        modelAndView.addObject("surveyQuestionList", surveyService.getSurveyQuestionList());
        modelAndView.addObject("surveyTypeList", surveyService.getSurveyTypeList());
        modelAndView.addObject("shortCodeList", surveyService.getShortCodeList());
        modelAndView.addObject("smtpServerList", surveyService.getAllSmtpServers());
    }

    @RequestMapping(value = "/takeSurvey/{surveyId}/{communicationId}")
    public ModelAndView takeSurvey(@PathVariable Integer surveyId, @PathVariable String communicationId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("takesurvey");
        Survey alreadyTakenSurvey = surveyService.getSurveyUserResponseList(surveyId, communicationId);
        if (alreadyTakenSurvey != null && alreadyTakenSurvey.getSurveyUserResponseList() != null && !alreadyTakenSurvey.getSurveyUserResponseList().isEmpty()) {
            modelAndView.addObject("message", "This survey has already been taken by you.");
            modelAndView.setViewName("/displaymessage");
            return modelAndView;
        }
        Survey survey = surveyService.getSurveyWithAssociatedQuestions(surveyId);
        modelAndView.addObject("survey", survey);
        Set<SurveyQuestionAssociation> surveyQuestionList = surveyService.getSurveyQuestionList(surveyId);
        for (SurveyQuestionAssociation surveyQuestionAssociation : surveyQuestionList) {
            Set<SurveyResponseDetail> surveyResponseDetailList = new LinkedHashSet<>(surveyQuestionAssociation.getSurveyQuestion().getSurveyResponseType().getSurveyResponseDetails());
            surveyQuestionAssociation.getSurveyQuestion().getSurveyResponseType().setSurveyResponseDetails(null);
            surveyQuestionAssociation.getSurveyQuestion().getSurveyResponseType().setSurveyResponseDetails(new ArrayList<>(surveyResponseDetailList));
        }
        modelAndView.addObject("surveyQuestionList", surveyQuestionList);
        modelAndView.addObject("communicationId", communicationId);
        return modelAndView;
    }

    @RequestMapping(value = "/submitSurvey")
    public ModelAndView submitSurveyResponse(@ModelAttribute Survey survey, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView modelAndView = new ModelAndView("submitsurvey");
        if (!validateUserResponse(survey, redirectAttributes)) {
            return new ModelAndView("redirect:/survey/takeSurvey/" + survey.getId() + "/" + survey.getCommunicationId());
        }
        surveyService.saveSurveyUserResponse(survey);
        modelAndView.addObject("survey", surveyService.getSurveyWithAssociatedQuestions(survey.getId()));
        return modelAndView;
    }

    private boolean validateUserResponse(Survey survey, RedirectAttributes redirectAttributes) throws NoSuchMessageException {
        boolean flag = true;
        if (survey.getUserResponse().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("error.userresponse", null, null));
            flag = false;
        }
        return flag;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletetSurvey(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/list");
        Survey survey = surveyService.getSurveyById(id);
        if (survey.getAssociated()) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("field.deleted.error", null, null));
        } else {
            surveyService.deleteSurvey(id);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.deleted.successfully", null, null));
        }
        return modelAndView;
    }
}
