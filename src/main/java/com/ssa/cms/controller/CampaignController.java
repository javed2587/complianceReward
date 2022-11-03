package com.ssa.cms.controller;

import com.google.gson.Gson;
import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponse;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.CampaignService;
import com.ssa.cms.delegate.SetupService;
import com.ssa.cms.dto.PatientProfileDTO;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.DrugBrand;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.Folder;
import com.ssa.cms.model.FolderHasCampaigns;
import com.ssa.cms.model.Industry;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.MessageType;
import com.ssa.cms.model.Response;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.Survey;
import com.ssa.cms.util.EmailSender;
import com.ssa.cms.util.GraphVizUtil;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignDelegate;

    private String type = "demo";
    private SessionBean sessionBean;

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SetupService setupService;
    private final Log logger = LogFactory.getLog(getClass());

    List<DrugBrand> drugBrands;
    List<Folder> folderList;
    private Pattern pattern;
    private Matcher matcher;

    public CampaignController() {
    }

    @InitBinder
    void initBinder(WebDataBinder binder, HttpServletRequest request) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
    }

    @RequestMapping(value = "/surveyList/{id}", produces = "application/json")
    public @ResponseBody
    String getSurveyHandler(@PathVariable String id,
            HttpServletRequest request) throws Exception {
        return setupService.getSurveyByCampaignId(Integer.parseInt(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid Campaigns campaigns, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        try {
            populateDropDown(null, model, campaigns.getCampaignId());
            //validation
            model.addAttribute("type", campaigns.getCampaignType());
            manageTriggers(campaigns);
            if (!validateCampaign(result, campaigns, model)) {
                return create(campaigns, model, false);
            }

            manageDrugBrands(campaigns.getSelectedDrugBrands(), campaigns);

            boolean saved = campaignDelegate.saveCampignBasic(campaigns, sessionBean.getUserId());
            if (!saved) {
                model.addAttribute("message", messageSource.getMessage("field.saved.error", null, null));
                return create(campaigns, model, false);
            } else {
                redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            }
        } catch (NoSuchMessageException e) {
            logger.error("CampaignController -> save", e);
            model.addAttribute("message", "There is some issue with saving campaign please try again.");
            return create(campaigns, model, false);
        }

        type = campaigns.getCampaignType();
        return "redirect:list/" + type;
    }

    private boolean validateCampaign(BindingResult result, Campaigns campaigns, Model model) {
        boolean valid = true;
        if (result.hasErrors()) {
            valid = false;
        }
        boolean flag = campaignDelegate.isCampaignExists(campaigns.getCampaignName(), campaigns.getCampaignId());
        if (flag) {
            model.addAttribute("message", messageSource.getMessage("field.campaign.duplicate", null, null));
            valid = false;
        }
        if (campaigns.getSelectedDrugBrands() == null || campaigns.getSelectedDrugBrands().length == 0) {
            model.addAttribute("message2", "Required");
            valid = false;
        }
        if (campaigns.getTriggers() == null || campaigns.getTriggers().isEmpty()) {
            model.addAttribute("message3", "Required");
            valid = false;
        }
        if (campaigns.getTriggers() != null && campaigns.getTriggers().size() > 0) {
            Set<String> checkDuplicateTrigger = findTriggersDuplicates(campaigns.getTriggers());
            if (checkDuplicateTrigger.size() > 0) {
                model.addAttribute("message", "Trigger already exists. Please correct and try again.");
                valid = false;
            }
        }
        for (CampaignTrigger trigger : campaigns.getTriggers()) {
            if (!campaignDelegate.isTriggerValid(trigger.getId().getKeyword(), campaigns.getCampaignId())) {
                model.addAttribute("message", "Trigger (" + trigger.getId().getKeyword() + ") already in use. Please correct and try again.");
                valid = false;
            }
        }

        if (campaigns.getTerminationDateTime() != null && campaigns.getLanchDateTime() != null && campaigns.getTerminationDateTime().before(campaigns.getLanchDateTime())) {
            model.addAttribute("message", "Termination date must be greater than launch date.");
            valid = false;
        }

        if (("Yes".equals(campaigns.getIsRefill()) || "Yes".equals(campaigns.getIsRepeatRefill())) && campaigns.getRefillProcessTiming() <= 0) {
            model.addAttribute("message8", "Required");
            valid = false;
        }

        if (campaigns.getShortCodes().getShortCode() == 0) {
            model.addAttribute("message4", "Required");
            valid = false;
        }
        if (campaigns.getIndustry().getIndustryId() == 0) {
            model.addAttribute("message5", "Required");
            valid = false;
        }
        if (campaigns.getSmtpServerInfo().getSmtpId() == 0) {
            model.addAttribute("message6", "Required");
            valid = false;
        }
        if (campaigns.getSurvey().getId() == 0) {
            model.addAttribute("surveyMessage", "Required");
            valid = false;
        }
        if ("Yes".equals(campaigns.getIsMaxBenefit()) || (campaigns.getSelectedOptions() != null && campaigns.getSelectedOptions().contains("max_benefit"))) {
            if ((campaigns.getMaxBenefitAmount() == null || campaigns.getMaxBenefitAmount().intValue() == 0)
                    && (campaigns.getRedemptionLimit() == null || campaigns.getRedemptionLimit() == 0)) {
                model.addAttribute("message7", "Required");
                campaigns.setMaxBenefitAmount(null);
                campaigns.setRedemptionLimit(null);
                valid = false;
            } else if (campaigns.getMaxBenefitAmount() == null || campaigns.getMaxBenefitAmount().intValue() == 0) {
                campaigns.setMaxBenefitAmount(null);
            } else if (campaigns.getRedemptionLimit() == null || campaigns.getRedemptionLimit() == 0) {
                campaigns.setRedemptionLimit(null);
            }
        } else {
            campaigns.setMaxBenefitAmount(null);
            campaigns.setRedemptionLimit(null);
        }

        if ("Yes".equals(campaigns.getIsClinicalMsgs()) || (campaigns.getSelectedOptions() != null && campaigns.getSelectedOptions().contains("clinical_messages"))) {
            if ((campaigns.getClinicalMsgsCount() == null || campaigns.getClinicalMsgsCount() == 0)) {
                model.addAttribute("clinicalMessage", "Required");
                campaigns.setClinicalMsgsCount(null);

                valid = false;
            } else if (campaigns.getClinicalMsgsCount() == null || campaigns.getClinicalMsgsCount() == 0) {
                campaigns.setClinicalMsgsCount(null);
            }
        } else {
            campaigns.setClinicalMsgsCount(null);

        }

        return valid;
    }

    private void manageDrugBrands(Integer[] brands, Campaigns campigns) {
        List<DrugBrand> dbSet = new ArrayList<>();
        for (Integer dbid : brands) {
            DrugBrand db = new DrugBrand();
            db.setId(dbid);
            dbSet.add(db);
        }
        campigns.setDrugBrands(dbSet);
    }

    @RequestMapping(value = "/{communicationPath}", method = RequestMethod.POST)
    public String advanceSave(@ModelAttribute Campaigns campaigns, @PathVariable String communicationPath, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String urlpath = "";
        try {
            populateAdvanceDropDown(null, model, campaigns, communicationPath);
            model.addAttribute("type", campaigns.getCampaignType());
            model.addAttribute("communicationPath", communicationPath);

            if (null != communicationPath) {
                switch (communicationPath) {
                    case "SMS":
                        urlpath = "/campaignadvance";
                        //validation
                        if (!validateAdvanceCampaign(campaigns, model)) {
                            return urlpath;
                        }
                        break;
                    case "Email":
                        urlpath = "/campaignemail";
                        //validation
                        if (!validateAdvanceCampaignEmail(campaigns, model)) {
                            return urlpath;
                        }
                        break;
                    case "OIVR":
                        urlpath = "/campaignivr";
                        if (!validateCampaignIVR(campaigns, model)) {
                            return urlpath;
                        }
                        break;
                }
            }
            boolean saved = campaignDelegate.saveCampignAdvance(campaigns, communicationPath, sessionBean.getUserId());
            if (!saved) {
                model.addAttribute("errorMessage", "There is some issue with saving campaign please try again.");
                return urlpath;
            } else {
                model.addAttribute("message", messageSource.getMessage("field.value.successfully", null, null));
            }
        } catch (NoSuchMessageException e) {
            logger.error("CampaignController -> advanceSave", e);
            model.addAttribute("errorMessage", "There is some issue with saving campaign please try again.");
            if (null != communicationPath) {
                switch (communicationPath) {
                    case "SMS":
                        return "/campaignadvance";
                    case "Email":
                        return "/campaignemail";
                    case "OIVR":
                        return "/campaignivr";
                }
            }
        }

        type = campaigns.getCampaignType();
        return urlpath;
    }

    private boolean validateAdvanceCampaign(Campaigns campaigns, Model model) {

        boolean flag = true;

        //vlidate events
        boolean isTextFlowDefined = false;
        if (campaigns.getFolderHasCampaignses() != null && !campaigns.getFolderHasCampaignses().isEmpty()) {

            for (FolderHasCampaigns message : campaigns.getFolderHasCampaignses()) {
                if (message.getFolder().getFolderId() != null) { //selected
                    isTextFlowDefined = false;
                    if (message.getSelectedEvents() == null || message.getSelectedEvents().length <= 0) {
                        model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > Please select one or more events.");
                        return false;
                    }

                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
                        if (messages.getMessageTypeId() != null && messages.getMessageType() != null && messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId()) { //selected
                            isTextFlowDefined = true;
                            for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {
                                if (response.getPaired() != null && response.getPaired().trim().length() > 0) {
                                    if (response.getNextMessage() == null || response.getNextMessage() == 0) {
                                        model.addAttribute("errorMessage", message.getFolder().getFolderName() + ">" + messages.getMessageType().getMessageTypeTitle() + " > Please select the next paired message.");
                                        return false;
                                    }
                                }
                                if (messages.getSubject() == null || messages.getSubject().trim().length() == 0) {
                                    model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Subject is required.");
                                    return false;
                                }
                                if (messages.getSmstext() == null || messages.getSmstext().trim().length() == 0) {
                                    model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Message text is required.");
                                    return false;
                                }
                                if (messages.getSmstext() != null && response.getIntervals().getIntervalId() <= 0) {
                                    model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Interval is required.");
                                    return false;
                                }

                            }
                        } else if (messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId() && messages.getSmstext() != null && !"".equals(messages.getSmstext())) {
                            model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > Please select the message, " + messages.getMessageType().getMessageTypeTitle());
                            flag = false;
                        }
                    }
                    if (!isTextFlowDefined) {
                        model.addAttribute("errorMessage", message.getFolder().getFolderName() + " > Please select at least one message.");
                        isTextFlowDefined = true;
                        flag = false;
                    }
                } else {
//                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
//                        if (messages.getSmstext() != null && messages.getSmstext().trim().length() > 1) {
//                            model.addAttribute("message", "Please select folder." + message.getFolder().getFolderName());
//                        }
//                    }
                }
            }
        }
        if (!isTextFlowDefined) {
            model.addAttribute("errorMessage", "Please define at least one text flow.");
            flag = false;
        }
        //end validate events
        return flag;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute Campaigns campaigns, Model model, HttpServletRequest request) {
        populateDropDown(null, model, null);
        return create(campaigns, model, true);
    }

    private String create(Campaigns campaigns, Model model, boolean init) {
        if (init) {
            campaigns.setIsActive("No");
            campaigns.setIsRefill("Yes");
            campaigns.setIsRepeatRefill("Yes");
            campaigns.setIsRefillOrderReminder("Yes");
            campaigns.setTriggers(new AutoPopulatingList<>(CampaignTrigger.class));
        }
        //model.addAttribute("type", "create1");
        return "campaignadd";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute Campaigns campaigns, @PathVariable Integer id, HttpServletRequest request) {
        Campaigns campaign = campaignDelegate.getCampaignById(id);
        Integer[] selectedBrand = null;
        if (campaign.getDrugBrands() != null && !campaign.getDrugBrands().isEmpty()) {
            selectedBrand = new Integer[campaign.getDrugBrands().size()];
            int counter = 0;
            for (DrugBrand drug : campaign.getDrugBrands()) {
                selectedBrand[counter++] = drug.getId();
            }
        }

        List<String> selectedOptions = new ArrayList<>();

        if ("Yes".equalsIgnoreCase(campaign.getIsRefill())) {
            selectedOptions.add("refill");
        }
        if ("Yes".equalsIgnoreCase(campaign.getIsRepeatRefill())) {
            selectedOptions.add("repeat_refill");
        }
        if ("Yes".equalsIgnoreCase(campaign.getIsRefillOrderReminder())) {
            selectedOptions.add("refill_order_reminder");
        }
        if ("Yes".equalsIgnoreCase(campaign.getIsMemberId())) {
            selectedOptions.add("validate_member_id");
        }
        if ("Yes".equalsIgnoreCase(campaign.getIsMaxBenefit())) {
            selectedOptions.add("max_benefit");
        }
        if ("Yes".equalsIgnoreCase(campaign.getIsClinicalMsgs())) {
            selectedOptions.add("clinical_messages");
        }

        campaign.setSelectedOptions(selectedOptions);
        campaign.setSelectedDrugBrands(selectedBrand);
        ModelAndView modelAndView = new ModelAndView("campaignadd");
        populateDropDown(modelAndView, null, id);
        modelAndView.addObject("campaigns", campaign);
        modelAndView.addObject("ctype", campaign.getCampaignType());
        modelAndView.addObject("type", campaign.getCampaignType());
        return modelAndView;
    }

    @RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET)
    public ModelAndView advanceEdit(@ModelAttribute Campaigns campaigns, @PathVariable Integer id, @PathVariable String type, HttpServletRequest request) {
        Campaigns campaign = campaignDelegate.getCampaignById(id);
        //drugBrands = campaign.getDrugBrands();
        ModelAndView modelAndView = new ModelAndView();
        folderList = campaignDelegate.getFolderList(sessionBean.getUserId() + "", type);

        populateAdvanceDropDown(modelAndView, null, campaign, type);

        //set selected events 
        setSelectedEvents(folderList, campaign, type);

        //filter messages
        setCampaignMessages(folderList, campaign, type);
//        if (drugBrands.isEmpty()) {
//            modelAndView.addObject("message", "No message has been defined yet.");
//        }
        modelAndView.addObject("campaigns", campaign);
        modelAndView.addObject("communicationPath", type);
        modelAndView.addObject("ctype", campaign.getCampaignType());
        switch (type) {
            case "SMS":
                //modelAndView.setViewName("campaignadvance");
                modelAndView.setViewName("campaignmsg");
                break;
            case "Email":
                modelAndView.setViewName("campaignemail");
                break;
            case "OIVR":
                modelAndView.setViewName("campaignivr");
                break;
            default:
                break;
        }
        return modelAndView;
    }

    private void setCampaignMessages(List<Folder> folderList, Campaigns campaign, String type1) {
        List<CampaignMessages> list = new ArrayList<>();
        for (Folder folder : folderList) {
            Integer folderId = folder.getFolderId();
            if (folder.getMessageTypes() != null && !folder.getMessageTypes().isEmpty()) {
                List<MessageType> messageList = filterMessageType(folder, type1);
                for (MessageType messageType : messageList) {
                    CampaignMessages msg = new CampaignMessages();
                    for (CampaignMessages campaignMessage : campaign.getCampaignMessageses()) {
                        msg = new CampaignMessages();
                        if (campaignMessage.getMessageType().getFolder().getFolderId().equals(folderId) && messageType.getId().getMessageTypeId() == campaignMessage.getMessageTypeId()) {
                            msg = campaignMessage;
                            break;
                        }
                    }
                    list.add(msg);
                }
            }
        }
        campaign.setCampaignMessageses(list);
    }

    private List<MessageType> filterMessageType(Folder folder, String communicationPath) {
        List<MessageType> listToReturn = new ArrayList<>();
        for (MessageType messageType : folder.getMessageTypes()) {
            if (messageType.getType().equals(communicationPath)) {
                listToReturn.add(messageType);
//                if (campaignDelegate.brandFound2_(messageType.getBrandIds(), drugBrands)) {
//                    listToReturn.add(messageType);
//                }
            }
        }
        return listToReturn;
    }

    private void setSelectedEvents(List<Folder> folderList, Campaigns campaign, String communicationPath) {
        List<FolderHasCampaigns> campaignFolderList = new ArrayList<>();
        for (Folder folder : folderList) {
            if (folder.getMessageTypes() != null && !folder.getMessageTypes().isEmpty()) {
                FolderHasCampaigns campaignFolder = new FolderHasCampaigns();
                for (FolderHasCampaigns folderHasCampaigns : campaign.getFolderHasCampaignses()) {
                    Integer[] selectedEvents;

                    if (folderHasCampaigns.getFolder().getFolderId().equals(folder.getFolderId()) && folderHasCampaigns.getCommunicationPath().equalsIgnoreCase(communicationPath)) {
                        List<EventHasFolderHasCampaigns> eventsList = campaignDelegate.getFolderEvents(folder.getFolderId(), campaign.getCampaignId());
                        if (eventsList != null && !eventsList.isEmpty()) {
                            selectedEvents = new Integer[eventsList.size()];
                            int counter = 0;
                            for (EventHasFolderHasCampaigns event : eventsList) {
                                selectedEvents[counter++] = event.getEventId();
                            }
                            folderHasCampaigns.setSelectedEvents(selectedEvents);
                            campaignFolder = folderHasCampaigns;
                        } else {
                            campaignFolder = folderHasCampaigns;
                        }
                    }
                }
                campaignFolderList.add(campaignFolder);
            }
        }
        campaign.setFolderHasCampaignses(campaignFolderList);
    }

    private void populateAdvanceDropDown(ModelAndView modelAndView, Model model, Campaigns campaign, String communicationPath) {
        List<Response> responseList = campaignDelegate.getResponseList();
        List<Intervals> intervalList = campaignDelegate.getIntervalList();

        //filter event
        List<Event> eventList = campaignDelegate.getEventList(campaign.getCampaignId());
        //filter message;
        for (Folder folder : folderList) {
            List<MessageType> messageList = filterMessageType(folder, communicationPath);
            folder.setMessageTypes(messageList);
        }

        if (model == null && modelAndView != null) {
            modelAndView.addObject("responseList", responseList);
            modelAndView.addObject("intervalList", intervalList);
            modelAndView.addObject("folderList", folderList);
            modelAndView.addObject("intervalList", intervalList);
            modelAndView.addObject("eventList", eventList);
        } else if (model != null) {
            model.addAttribute("responseList", responseList);
            model.addAttribute("intervalList", intervalList);
            model.addAttribute("folderList", folderList);
            model.addAttribute("intervalList", intervalList);
            model.addAttribute("eventList", eventList);
        }
    }

    private void populateDropDown(ModelAndView modelAndView, Model model, Integer campiagnId) {

        List<DrugBrand> brands = campaignDelegate.getAvailableDrugBrands(sessionBean.getUserId() + "", campiagnId);
        List<Industry> industryList = campaignDelegate.getIndustryList();
        List<ShortCodes> shortCodeList = campaignDelegate.getShortCodeList();
        List<SmtpServerInfo> smtpList = campaignDelegate.getSmtpList();
        List<Intervals> intervalList = campaignDelegate.getIntervalList();
        List<Survey> surveyList = campaignDelegate.getSurveyList();
        // String[] advanceOption = {"Refill","Repeat Refill","Refill Order Reminder","Validate Member Id","Max Benefit"};

        if (model == null) {
            modelAndView.addObject("brands", brands);
            modelAndView.addObject("industryList", industryList);
            modelAndView.addObject("shortCodeList", shortCodeList);
            modelAndView.addObject("smtpList", smtpList);
            modelAndView.addObject("intervalList", intervalList);
            modelAndView.addObject("surveyList", surveyList);
        } else {
            model.addAttribute("brands", brands);
            model.addAttribute("industryList", industryList);
            model.addAttribute("shortCodeList", shortCodeList);
            model.addAttribute("smtpList", smtpList);
            model.addAttribute("intervalList", intervalList);
            model.addAttribute("surveyList", surveyList);
        }
    }

    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
    public ModelAndView listCampaign(@ModelAttribute Campaigns campaigns, @PathVariable String type, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("campaignlist");
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        List<Campaigns> list = campaignDelegate.getCampaignListByType(type, sessionBean);
        modelAndView.addObject("list", list);
        this.type = type;
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listCampaigns(@ModelAttribute Campaigns campaigns, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("campaignlist");
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        if (type == null || type.equals("")) {
            type = "demo";
        }
        List<Campaigns> list = campaignDelegate.getCampaignListByType(type, sessionBean);
        modelAndView.addObject("list", list);

        modelAndView.addObject("type", type);
        return modelAndView;
    }

    public boolean brandFound(String brandIds, List<String> userBrandList) {
        boolean brandFound = false;
        if (brandIds != null && userBrandList != null) {
            for (String brand : brandIds.split(",")) {
                if (userBrandList.contains(brand)) {
                    brandFound = true;
                    break;
                }
            }
        }
        return brandFound;
    }

    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public @ResponseBody
    String sendTestSMS(Model model, HttpServletRequest request) {
        String phone = request.getParameter("phone_");
        String messages = request.getParameter("message_");
        String subject = request.getParameter("subject_");
        if (campaignDelegate.sendTestSMS(phone, messages)) {
            return "0";
        } else {
            return "1";
        }
    }

    @RequestMapping(value = "/graph", method = RequestMethod.POST)
    public @ResponseBody
    String showFolderGraph(HttpServletRequest request) {

        String campaignId = request.getParameter("campaignId");
        String folderId = request.getParameter("folderId");
        Integer folder = Integer.parseInt(folderId);
        String communicationPath = request.getParameter("communicationPath");
        String imgId = request.getParameter("imgId");
        Integer imageId = Integer.parseInt(imgId);

        int imageNo = imageId;
        int count = imageNo - 1;

        if (count == 0) {
            count = 1;
        }

        Campaigns campaign = campaignDelegate.getCampaignById(Integer.parseInt(campaignId));
        String img = Constants.GRAPHIC_IMG_PATH + "/graphimage" + count + ".png";
        File checkFile = new File(img);

        if (count == 1) {
            File directory = new File(Constants.GRAPHIC_IMG_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        } else if (checkFile.exists()) {
            checkFile.delete();
        }

        String folderName = null;
        boolean iteration = false;
        String commPath;
        GraphVizUtil gv = new GraphVizUtil();
        gv.addln(gv.start_graph());
        String imageType = "png";
        File out = new File("out." + imageType);
        commPath = communicationPath.replace(" ", "");

        for (CampaignMessages messages : campaign.getCampaignMessageses()) {
            if (folder.equals(messages.getMessageType().getFolder().getFolderId())) {
                folderName = messages.getMessageType().getFolder().getFolderName().replace("-", "").replace(" ", "");
                break;
            }
        }
        String fName = folderName;
        gv.addln(commPath + "->" + folderName + ";");
        for (CampaignMessages messages : campaign.getCampaignMessageses()) {
            int messageCount = campaign.getCampaignMessageses().size();
            int responseCount = messages.getCampaignMessagesResponses().size();
            int messageId = messages.getMessageTypeId();
            if (folder.equals(messages.getMessageType().getFolder().getFolderId())) {
                if (messages.getMessageTypeId() != null) {
                    for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {
                        String repeatMessage;
                        String nextMessage;
                        String messageName;

                        String messageResponse = response.getResponseTitle();
                        int nextMessageId = response.getNextMessage();
                        int repeatMsgId = response.getRepeatMessageId();

                        if (nextMessageId == 0 && repeatMsgId == 0) {
                            messageName = messages.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");

                            if (folderName != null && !folderName.equalsIgnoreCase(messageName)) {
                                gv.addln(fName + "->" + messageName + ";");
                            }
                        } else {
                            for (CampaignMessages msgs : campaign.getCampaignMessageses()) {
                                if (nextMessageId == msgs.getMessageType().getId().getMessageTypeId()) {
                                    messageName = messages.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");
                                    if (!messageName.contains("Repeat")) {
                                        if (!iteration) {
                                            if (!messageName.equalsIgnoreCase(folderName)) {
                                                gv.addln(folderName + "->" + messageName + ";");
                                            }
                                        }
                                        nextMessage = msgs.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");
                                        gv.addln(messageName + "->" + nextMessage + "[label=\"" + messageResponse + "\"]" + ";");

                                        if (responseCount == 1) {
                                            folderName = nextMessage;
                                            iteration = false;
                                        } else {
                                            //responseCount = responseCount - 1;
                                            iteration = true;
                                        }
                                    } else if (messageId != nextMessageId) {
                                        if (!iteration) {
                                            if (!messageName.equalsIgnoreCase(folderName)) {
                                                gv.addln(folderName + "->" + messageName + ";");
                                            }
                                        }
                                        nextMessage = msgs.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");
                                        gv.addln(messageName + "->" + nextMessage + "[label=\"" + messageResponse + "\"]" + ";");

                                        if (responseCount == 1) {
                                            folderName = nextMessage;
                                            iteration = false;
                                        } else {
                                            responseCount = responseCount - 1;
                                            iteration = true;
                                        }
                                    }
                                    if (response.getRepeatIntervalId() != 0) {
                                        for (CampaignMessages repeatMsgs : campaign.getCampaignMessageses()) {
                                            if (repeatMsgId == repeatMsgs.getMessageType().getId().getMessageTypeId()) {
                                                int repeatIntervalId = response.getRepeatIntervalId();
                                                int repeatIntervalTypeId = response.getRepeatIntervalTypeId();
                                                String repeatInterval = null;
                                                List<Intervals> intervalList = campaignDelegate.getIntervalList();
                                                for (Intervals inteveralResponse : intervalList) {
                                                    if (repeatIntervalId == inteveralResponse.getIntervalId() && repeatIntervalTypeId == inteveralResponse.getIntervalsType().getIntervalsTypeId()) {
                                                        repeatInterval = inteveralResponse.getIntervalValue() + " " + inteveralResponse.getIntervalsType().getIntervalsTypeTitle();
                                                        break;
                                                    }
                                                }
                                                repeatMessage = messages.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");
                                                gv.addln(messageName + "->" + repeatMessage + "[label=\"" + repeatInterval + "\"]" + ";");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                } else if (messageCount == 1) {
                                    messageName = messages.getMessageType().getMessageTypeTitle().replace(" ", "").replace("-", "").replace("&", "");
                                    gv.addln(folderName + "->" + messageName + "[label=\"" + messageResponse + "\"]" + ";");
                                }
                            }
                        }
                    }
                }
            }
        }

        gv.addln(gv.end_graph());
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), imageType, imageNo), out);

        return "graphimage.png";
    }

    private List<CampaignTrigger> manageTriggers(Campaigns campaigns) {
        List<CampaignTrigger> trigger2remove = new ArrayList<>();
        if (campaigns.getTriggers() != null) {
            for (Iterator<CampaignTrigger> i = campaigns.getTriggers().iterator(); i.hasNext();) {
                CampaignTrigger trigger = i.next();
                // If the remove flag is true, remove the employee from the list
                if (trigger.getId() == null) {
                    trigger2remove.add(trigger);
                    i.remove();
                    // Otherwise, perform the links
                } else {
                    trigger.setCampaigns(campaigns);
                    trigger.setIsActive("Yes");
                    trigger.setIsDelete("No");
                    trigger.setCreateOn(new Date());
                    trigger.setCreatedBy(sessionBean.getUserId() + "");
                    trigger.setLastModifiedBy(sessionBean.getUserId() + "");
                    trigger.setLastModifiedOn(new Date());
                }
            }
        }
        return trigger2remove;
    }

    @RequestMapping(value = "/clone/{campaignId}", method = RequestMethod.GET)
    public String createClone(@PathVariable Integer campaignId) {

        String campaignName = "";
        Campaigns campaigns = campaignDelegate.getCampaignById(campaignId);

        campaigns.setCampaignName(campaignName + "(CLONE)");

        return "campaignadd";
    }

    public static Set<String> findTriggersDuplicates(List<CampaignTrigger> list) {

        final Set<String> setToReturn = new HashSet<>();
        final Set<String> set1 = new HashSet<>();

        for (CampaignTrigger campaignTrigger : list) {
            if (!set1.add(campaignTrigger.getId().getKeyword()) && !"".equals(campaignTrigger.getId().getKeyword())) {
                setToReturn.add(campaignTrigger.getId().getKeyword());
            }
        }
        return setToReturn;
    }

    private boolean validateAdvanceCampaignEmail(Campaigns campaigns, Model model) {

        boolean flag = true;

        //vlidate events
        boolean isTextFlowDefined = false;
        if (campaigns.getFolderHasCampaignses() != null && !campaigns.getFolderHasCampaignses().isEmpty()) {

            for (FolderHasCampaigns message : campaigns.getFolderHasCampaignses()) {
                if (message.getFolder().getFolderId() != null) { //selected
                    isTextFlowDefined = false;
                    if (message.getSelectedEvents() == null || message.getSelectedEvents().length <= 0) {
                        model.addAttribute("message", message.getFolder().getFolderName() + " > Please select one or more events.");
                        return false;
                    }

                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
                        if (messages.getMessageTypeId() != null && messages.getMessageType() != null && messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId()) { //selected
                            isTextFlowDefined = true;
                            for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {
                                if (response.getPaired() != null && response.getPaired().trim().length() > 0) {
                                    if (response.getNextMessage() == null || response.getNextMessage() == 0) {
                                        model.addAttribute("message", message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Please select the next paired message.");
                                        return false;
                                    }
                                }

                                if (response.getIntervals().getIntervalId() <= 0) {
                                    model.addAttribute("message", message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Interval is required.");
                                    return false;
                                }

                            }
                        } else if (messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId()) {
                            model.addAttribute("message", message.getFolder().getFolderName() + " > Please select the message, " + messages.getMessageType().getMessageTypeTitle());
                            return false;
                        }
                    }
                    if (!isTextFlowDefined) {
                        model.addAttribute("message", message.getFolder().getFolderName() + " > Please select at least one message ");
                        isTextFlowDefined = true;
                        return false;
                    }
                } else {
//                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
//                        if (messages.getSmstext() != null && messages.getSmstext().trim().length() > 1) {
//                            model.addAttribute("message", "Please select folder." + message.getFolder().getFolderName());
//                        }
//                    }
                }
            }
        }
        if (!isTextFlowDefined) {
            model.addAttribute("message", "Please define at least one Email flow.");
            flag = false;
        }
        //end validate events
        return flag;
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public @ResponseBody
    String sendTestEmail(Model model, HttpServletRequest request) throws IOException {
        SmtpServerInfo smtpServerInfo = new SmtpServerInfo();
        String toEmail = request.getParameter("email_");
        String emailSubject = request.getParameter("subject_");
        String emailBody = request.getParameter("emailbody_");
        String campaignId = request.getParameter("campaignId");
        if (toEmail != null && !toEmail.isEmpty()) {
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(toEmail);
            if (!matcher.matches()) {
                return "Enter valid email.";
            }
        }
        if (campaignId != null) {
            Campaigns campaigns = campaignDelegate.getCampaignById(Integer.parseInt(campaignId));
            smtpServerInfo = setupService.getSmtpById(campaigns.getSmtpServerInfo().getSmtpId());
        }
        emailBody = EmailSender.makeEmailBody(emailBody);
        boolean isEmailSend = EmailSender.sendEmail(toEmail, emailSubject, emailBody, smtpServerInfo, null);
        if (isEmailSend) {
            return "Email has been sent successfully.";
        } else {
            return "Email sending failed.";
        }
    }

    @RequestMapping(value = "/surveyReports", method = RequestMethod.GET)
    public ModelAndView surveyReports(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("surveyreports");
        surveyReportsDropDownList(request, modelAndView);
        modelAndView.addObject("campaigns", new Campaigns());
        modelAndView.addObject("surveyList", setupService.getSurveyListByCampaignId(13));
        return modelAndView;
    }

    @RequestMapping(value = "/patientNoOrderReport", method = RequestMethod.GET)
    public ModelAndView patientNoOrderReport(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("patientNoOrderReport");
        try {
            surveyReportsDropDownList(request, modelAndView);
            modelAndView.addObject("campaigns", new Campaigns());
            modelAndView.addObject("patientlist", setupService.getPatientWithoutOrder());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    private void surveyReportsDropDownList(HttpServletRequest request, ModelAndView modelAndView) {
        if (type == null || type.equals("")) {
            type = "demo";
        }
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        modelAndView.addObject("campaignslist", campaignDelegate.getCampaignListByType(type, sessionBean));
    }

    @RequestMapping(value = "/surveyReports", method = RequestMethod.POST)
    public ModelAndView generateSurveyReports(@ModelAttribute Campaigns campaigns, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("surveyreports");
        surveyReportsDropDownList(request, modelAndView);
        if (campaigns.getCampId() > 0) {
            modelAndView.addObject("surveyList", setupService.getSurveyListByCampaignId(campaigns.getCampId()));
        }
        if (!validateSurveyReports(campaigns, modelAndView)) {

            return modelAndView;
        }
        List<Campaigns> list = campaignDelegate.getSurveyReports(campaigns.getCampId(), campaigns.getSurvey().getId());
        modelAndView.addObject("list", list);
        modelAndView.addObject("totalRecords", list.size());
        modelAndView.addObject("campaigns", campaigns);
        return modelAndView;
    }

    private boolean validateSurveyReports(Campaigns campaigns, ModelAndView modelAndView) {
        boolean empty = true;
        if (campaigns.getCampId() == 0) {
            modelAndView.addObject("emptyCampaign", "Required");
            modelAndView.addObject("campaigns", campaigns);
            empty = false;
        }
        if (campaigns.getSurvey().getId() == 0) {
            modelAndView.addObject("emptySurvey", "Required");
            modelAndView.addObject("campaigns", campaigns);
            empty = false;
        }
        return empty;
    }

    @RequestMapping(value = "/pdfDownload.pdf", method = RequestMethod.GET)
    public ModelAndView exportPDF(@RequestParam(value = "campaign", required = false) int campaignId,
            @RequestParam(value = "survey", required = false) int surveyId) throws ParseException {
        List<Campaigns> list = campaignDelegate.getSurveyReports(campaignId, surveyId);
        ModelAndView modelAndView = new ModelAndView("pdfView", "list", list);
        modelAndView.addObject("key", "SurveyReports");
        return modelAndView;
    }

    @RequestMapping(value = "/excelDownload.xls", method = RequestMethod.GET)
    public ModelAndView exportExcel(@RequestParam(value = "campaign", required = false) int campaignId,
            @RequestParam(value = "survey", required = false) int surveyId) throws ParseException {
        List<Campaigns> list = campaignDelegate.getSurveyReports(campaignId, surveyId);
        ModelAndView modelAndView = new ModelAndView("excelView", "list", list);
        modelAndView.addObject("key", "SurveyReports");
        return modelAndView;
    }

    @RequestMapping(value = "/moveDown/{messageTypeId}/{type}/{id}", method = RequestMethod.GET)
    public ModelAndView updateMoveDownSortOrder(@PathVariable Integer messageTypeId, @PathVariable String type, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        MessageType messageType = setupService.getMessageTypeById(messageTypeId);
        Integer sortOrder = messageType.getSortOrder();
        if (sortOrder == null) {
            messageType.setSortOrder(0);
        } else {
            messageType.setSortOrder(sortOrder + 1);
        }
        boolean saved = setupService.updateMessageType(messageType);
        if (saved) {
            return new ModelAndView("redirect:/campaign/" + type + "/" + id);
        } else {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.saved.error", null, null));
            return new ModelAndView("redirect:/campaign/" + type + "/" + id);
        }
    }

    @RequestMapping(value = "/moveUp/{messageTypeId}/{type}/{id}", method = RequestMethod.GET)
    public ModelAndView updateMoveUpSortOrder(@PathVariable Integer messageTypeId, @PathVariable String type, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        MessageType messageType = setupService.getMessageTypeById(messageTypeId);
        Integer sortOrder = messageType.getSortOrder();
        if (sortOrder == null) {
            messageType.setSortOrder(0);
        } else {
            messageType.setSortOrder(sortOrder - 1);
        }
        boolean saved = setupService.updateMessageType(messageType);
        if (saved) {
            return new ModelAndView("redirect:/campaign/" + type + "/" + id);
        } else {
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("field.saved.error", null, null));
            return new ModelAndView("redirect:/campaign/" + type + "/" + id);
        }
    }

    private boolean validateCampaignIVR(Campaigns campaigns, Model model) {
        boolean flag = true;

        //vlidate events
        boolean isTextFlowDefined = false;
        if (campaigns.getFolderHasCampaignses() != null && !campaigns.getFolderHasCampaignses().isEmpty()) {

            for (FolderHasCampaigns message : campaigns.getFolderHasCampaignses()) {
                if (message.getFolder().getFolderId() != null) { //selected
                    isTextFlowDefined = false;
                    if (message.getSelectedEvents() == null || message.getSelectedEvents().length <= 0) {
                        model.addAttribute("message", message.getFolder().getFolderName() + " > Please select one or more events.");
                        return false;
                    }

                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
                        if (messages.getMessageTypeId() != null && messages.getMessageType() != null && messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId()) { //selected
                            isTextFlowDefined = true;
                            for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {

                            }
                        } else if (messages.getMessageType().getFolder().getFolderId() == message.getFolder().getFolderId()) {
                            model.addAttribute("message", message.getFolder().getFolderName() + " > Please select the message, " + messages.getMessageType().getMessageTypeTitle());
                            flag = false;
                        }
                    }
                    if (!isTextFlowDefined) {
                        model.addAttribute("message", message.getFolder().getFolderName() + " > Please select at least one message ");
                        isTextFlowDefined = true;
                        flag = false;
                    }
                }
            }
        }
        if (!isTextFlowDefined) {
            model.addAttribute("message", "Please define at least one text flow.");
            flag = false;
        }
        //end validate events
        return flag;
    }

    @RequestMapping(value = "/exportInActivePatientReports", method = RequestMethod.GET)
    public ModelAndView exportInActivePatientReport(@RequestParam(value = "type", required = false) String type) {
        List<PatientProfileDTO> list = new ArrayList<>();
        try {
            list = setupService.getPatientWithoutOrder();
        } catch (Exception ex) {
            Logger.getLogger(CampaignController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView modelAndView = new ModelAndView(type, "list", list);
        modelAndView.addObject("key", "InActivePatientReports");
        return modelAndView;
    }

    private String validateAdvanceCampaign(Campaigns campaigns) {
        String responseStr = JsonResponseComposer.composeSuccessResponse();

        //vlidate events
        boolean isTextFlowDefined = false;
        if (campaigns.getFolderHasCampaignses() != null && !campaigns.getFolderHasCampaignses().isEmpty()) {
            for (FolderHasCampaigns message : campaigns.getFolderHasCampaignses()) {
                if (message.getFolder().getFolderId() != null) {
                    isTextFlowDefined = false;
                    if (message.getSelectedEvents() == null || message.getSelectedEvents().length <= 0) {
                        responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > Please select one or more events.");
                        return responseStr;
                    }

                    for (CampaignMessages messages : campaigns.getCampaignMessageses()) {
                        if (messages.getMessageTypeId() != null && messages.getMessageType() != null && Objects.equals(messages.getMessageType().getFolder().getFolderId(), message.getFolder().getFolderId())) { //selected
                            isTextFlowDefined = true;
                            for (CampaignMessagesResponse response : messages.getCampaignMessagesResponses()) {
                                if (response.getPaired() != null && response.getPaired().trim().length() > 0) {
                                    if (response.getNextMessage() == null || response.getNextMessage() == 0) {
                                        responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Please select the next paired message.");
                                        return responseStr;
                                    }
                                }
                                if (messages.getSubject() == null || messages.getSubject().trim().length() == 0) {
                                    responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Subject is required.");
                                    return responseStr;
                                }
                                if (messages.getSmstext() == null || messages.getSmstext().trim().length() == 0) {
                                    responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Message text is required.");
                                    return responseStr;
                                }
                                if (messages.getSmstext() != null && response.getIntervals().getIntervalId() <= 0) {
                                    responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Interval is required.");
                                    return responseStr;
                                }
                                if (response.getIntervals().getIntervalId() <= 0) {
                                    responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > " + messages.getMessageType().getMessageTypeTitle() + " > Interval is required.");
                                    return responseStr;
                                }

                            }
                        } else if (Objects.equals(messages.getMessageType().getFolder().getFolderId(), message.getFolder().getFolderId())) {
                            responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > Please select the message, " + messages.getMessageType().getMessageTypeTitle());
                            return responseStr;
                        }
                    }
                    if (!isTextFlowDefined) {
                        responseStr = JsonResponseComposer.composeFailureResponse(message.getFolder().getFolderName() + " > Please select at least one message ");
                        isTextFlowDefined = true;
                        return responseStr;
                    }
                }
            }
        }
        if (!isTextFlowDefined) {
            responseStr = JsonResponseComposer.composeFailureResponse("Please define at least one SMS flow.");
        }
        return responseStr;
    }

    @RequestMapping(value = "/saveCampaignMsgs", method = RequestMethod.POST)
    public @ResponseBody
    String saveCampaign(@RequestParam("campaignId") Integer campaignId, @RequestParam("folderId") Integer folderId, @RequestParam("selectedEvents") Integer[] selectedEvents,
            @RequestParam("messageTypeId") Integer messageTypeId, @RequestParam("isCritical") Integer isCritical,
            @RequestParam("responseId") Integer responseId, @RequestParam("intervalId") Integer intervalId,
            @RequestParam("subject") String subject, @RequestParam("msgBody") String msgBody,
            @RequestParam("nextMessage") Integer nextMessage, @RequestParam("repeatIntervalId") Integer repeatIntervalId,
            @RequestParam("repeatMessageId") Integer repeatMessageId, @RequestParam("fhCid") Integer fhCid,
            @RequestParam("messageId") Integer messageId, @RequestParam("campaignMessagesResponseId") Integer campaignMessagesResponseId,
            @RequestParam(value = "pushNotification",required = false) String pushNotification) {
        String responseStr = null;
        Campaigns campaigns = new Campaigns();
        campaigns.setCampaignId(campaignId);

        Folder folder = populateFolderHasCampaignsData(selectedEvents, folderId, campaigns);

        this.populateCampaignMsgsData(folder, messageTypeId, isCritical, subject, msgBody, repeatIntervalId,
                nextMessage, repeatMessageId, responseId, intervalId, campaigns, messageId, campaignMessagesResponseId,pushNotification);
        responseStr = this.validateAdvanceCampaign(campaigns);
        JsonResponse jsonResponse = new Gson().fromJson(responseStr, JsonResponse.class);
        if (Objects.equals(jsonResponse.getStatuscode(), Constants.JSON_STATUS.CODE_FAILLIURE) || jsonResponse.getStatus().equalsIgnoreCase(Constants.JSON_STATUS.FAIL)) {
            return responseStr;
        }

        boolean saved = campaignDelegate.saveOrUpdateCampignAdvance(campaigns, "SMS", sessionBean.getUserId(), fhCid);
        if (!saved) {
            responseStr = JsonResponseComposer.composeFailureResponse("There is some issue with saving campaign please try again.");
        } else {
            responseStr = JsonResponseComposer.composeResponse(messageSource.getMessage("field.value.successfully", null, null), Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
        }
        return responseStr;
    }

    private void populateCampaignMsgsData(Folder folder, Integer messageTypeId, Integer isCritical, String subject, String msgBody,
            Integer repeatIntervalId, Integer nextMessage, Integer repeatMessageId, Integer responseId,
            Integer intervalId, Campaigns campaigns, Integer messageId, Integer campaignMessagesResponseId,String pushNotification) {
        List<CampaignMessages> campaignMessageses = new ArrayList<>();
        CampaignMessages campaignMessages = new CampaignMessages();
        MessageType messageType = new MessageType();
        messageType.setFolder(folder);
        campaignMessages.setMessageId(messageId);
        campaignMessages.setMessageType(messageType);
        campaignMessages.setMessageTypeId(messageTypeId);
        campaignMessages.setIsCritical(isCritical);
        campaignMessages.setSubject(subject);
        campaignMessages.setSmstext(msgBody);
        campaignMessages.setPushNotification(pushNotification);
        List<CampaignMessagesResponse> campaignMessagesResponses = this.populateCampaignMessagesResponseData(campaignMessagesResponseId, repeatIntervalId, nextMessage, repeatMessageId, responseId, intervalId);
        campaignMessages.setCampaignMessagesResponses(campaignMessagesResponses);
        campaignMessageses.add(campaignMessages);
        campaigns.setCampaignMessageses(campaignMessageses);
    }

    private List<CampaignMessagesResponse> populateCampaignMessagesResponseData(Integer campaignMessagesResponseId, Integer repeatIntervalId, Integer nextMessage, Integer repeatMessageId, Integer responseId, Integer intervalId) {
        CampaignMessagesResponse campaignMessagesResponse = new CampaignMessagesResponse();
        campaignMessagesResponse.setCampaignMessagesResponseId(campaignMessagesResponseId);
        campaignMessagesResponse.setRepeatIntervalId(repeatIntervalId);
        campaignMessagesResponse.setNextMessage(nextMessage);
        campaignMessagesResponse.setRepeatMessageId(repeatMessageId);
        Response response1 = new Response();
        response1.setResponseId(responseId);
        campaignMessagesResponse.setResponse(response1);
        Intervals intervals = new Intervals();
        intervals.setIntervalId(intervalId);
        campaignMessagesResponse.setIntervals(intervals);
        List<CampaignMessagesResponse> campaignMessagesResponses = new ArrayList<>();
        campaignMessagesResponses.add(campaignMessagesResponse);
        return campaignMessagesResponses;
    }

    private Folder populateFolderHasCampaignsData(Integer[] selectedEvents, Integer folderId, Campaigns campaigns) {
        FolderHasCampaigns folderHasCampaigns = new FolderHasCampaigns();
        Folder folder = new Folder();
        folderHasCampaigns.setSelectedEvents(selectedEvents);
        folder.setFolderId(folderId);
        folderHasCampaigns.setFolder(folder);
        List<FolderHasCampaigns> listOfFolderHasCampaigns = new ArrayList<>();
        listOfFolderHasCampaigns.add(folderHasCampaigns);
        campaigns.setFolderHasCampaignses(listOfFolderHasCampaigns);
        return folder;
    }
}
