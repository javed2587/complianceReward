package com.ssa.cms.controller;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.delegate.SupportService;
import com.ssa.cms.model.DailyRedemptionId;
import com.ssa.cms.model.SupportModel;
import com.ssa.cms.service.DrugService;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.util.CommonUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SupportController implements Serializable {

    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    RedemptionService redemptionDAO;

    @Autowired
    private SupportService supportDelegate;

    @Autowired
    private MessageSource messageSource;
    private Pattern pattern;
    private Matcher matcher;
    @Autowired
    ServletContext servletContext;
    @Autowired
    private DrugService drugService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @RequestMapping(value = {"/support", "/run", "/orderDetailList"}, method = RequestMethod.GET)
    public ModelAndView showPage(@ModelAttribute SupportModel supportModel) {

        ModelAndView modelAndView = new ModelAndView("support");
        modelAndView.addObject("supportModel", new SupportModel());
        populateCampaignMsgsList(modelAndView);
        return modelAndView;
    }

    private void populateCampaignMsgsList(ModelAndView modelAndView) {
        modelAndView.addObject("campaignMessagesList", supportDelegate.getCampaignMessagesList());
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runProcess(@ModelAttribute SupportModel supportModel, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        logger.info("ID is : " + supportModel.getId());

        int id = Integer.parseInt(supportModel.getId());

        if (id == 0) {
            logger.info("Please select the value.");
            model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
            return "/support";
        }

        boolean processExecute = supportDelegate.executeProcess(id);
        if (processExecute) {
            model.addAttribute("message", messageSource.getMessage("support.success", null, null));
        } else {
            logger.info("Please select the process.");
            model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
            return "/support";
        }
        return "/support";

    }

    @RequestMapping(value = "/runWidget", method = RequestMethod.POST)
    public String runWidget(@ModelAttribute @Valid SupportModel supportModel, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
                return "/support";
            }
            makeWidgetUrl(supportModel);

            model.addAttribute("message", messageSource.getMessage("support.success", null, null));
        } catch (NoSuchMessageException e) {
            model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
            logger.error("Exception", e);
            return "/support";
        } catch (IOException e) {
            model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
            logger.error("Exception", e);
            return "/support";
        }
        return "/support";
    }

    private void makeWidgetUrl(SupportModel supportModel) throws IOException, MalformedURLException, UnsupportedEncodingException {
        String dataPost = URLEncoder.encode("communicationMethod", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationMethod(), "UTF-8") + "&"
                + URLEncoder.encode("shortCode", "UTF-8") + "=" + URLEncoder.encode(supportModel.getShortCode(), "UTF-8") + "&"
                + URLEncoder.encode("eventName", "UTF-8") + "=" + URLEncoder.encode(supportModel.getEventName(), "UTF-8") + "&"
                + URLEncoder.encode("trigger", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCtrigger(), "UTF-8") + "&"
                + URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getUserId(), "UTF-8") + "&"
                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(supportModel.getPassword(), "UTF-8") + "&"
                + URLEncoder.encode("communicationId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCommunicationId(), "UTF-8");

        String urlString = Constants.APP_PATH + "/widget";

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Method", "GET");

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(dataPost);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        wr.close();
        rd.close();
    }

    @RequestMapping(value = "/executeRedempiton", method = RequestMethod.POST)
    public String executeRedempitonEmdeon(@ModelAttribute @Valid SupportModel supportModel, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            String filetype = supportModel.getFileType();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat filldateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (filetype == null || filetype.trim().length() == 0) {
                model.addAttribute("errorMessage", messageSource.getMessage("process.name.required", null, null));
                return "/support";
            }

            SAXBuilder builder = new SAXBuilder();
            File drfXml = null;
            Document doc = null;
            String servlet = "";

            if (filetype.equalsIgnoreCase("DRF")) {
                if (supportModel.getNdcNumber().isEmpty() || supportModel.getCardHolderId().isEmpty() || supportModel.getClaimStatus().isEmpty()
                        || supportModel.getEmTransactionNum().isEmpty() || supportModel.getFirstName().isEmpty() || supportModel.getLastName().isEmpty() || supportModel.getOutofpocket().isEmpty()) {
                    model.addAttribute("errorMessage", messageSource.getMessage("support.err.missing", null, null));
                    return "/support";
                }
                DailyRedemptionId id = new DailyRedemptionId();
                id.setTransactionNumber(supportModel.getEmTransactionNum());
                id.setClaimStatus(Integer.parseInt(supportModel.getClaimStatus()));
                if (redemptionDAO.getDRFCountById(id) > 0) {
                    model.addAttribute("errorMessage", messageSource.getMessage("dailyRedemption.duplicate", null, null));
                    return "/support";
                }
                boolean isSaved = supportDelegate.saveRedemptionIngredient(supportModel);
                if (isSaved) {
                    servlet = "/dailyRedemption";
                    logger.info("Url path " + servlet);
                } else {
                    model.addAttribute("errorMessage", messageSource.getMessage("field.saved.error", null, null));
                    return "/support";
                }
            } else if (filetype.equalsIgnoreCase("IRF")) {

                String file = Constants.EM_IRF_TESTFILE;
                servlet = "/InstantRedemption";
                drfXml = new File(file);

                if (drfXml == null) {
                    model.addAttribute("errorMessage", messageSource.getMessage("file.missing", null, null));
                    return "/support";
                }

                doc = (Document) builder.build(drfXml);
                Element rootNode = doc.getRootElement();

                rootNode.getChild("CardholderId").setText(supportModel.getCardHolderId());
                //rootNode.getChild("NDCNumber").setText(supportModel.getNdcNumber());
                rootNode.getChild("TransactionNumber").setText(supportModel.getEmTransactionNum());
                rootNode.getChild("PatientFirstName").setText(supportModel.getFirstName());
                rootNode.getChild("PatientLastName").setText(supportModel.getLastName());
                rootNode.getChild("PostingDate").setText(dateFormat.format(supportModel.getPostingDate()));
                rootNode.getChild("FillDate").setText(filldateFormat.format(supportModel.getFillDate()));
                rootNode.getChild("ClaimStatus").setText(supportModel.getClaimStatus());
            }
            String ndcNumber = supportDelegate.getCampaignByNDCNumber(supportModel.getNdcNumber());
            if (ndcNumber.isEmpty()) {
                ndcNumber = supportModel.getNdcNumber().get(supportModel.getNdcNumber().size() - 1);
            }
            String dataPost = URLEncoder.encode("ndcNumber", "UTF-8") + "=" + URLEncoder.encode(ndcNumber, "UTF-8") + "&"
                    + URLEncoder.encode("cardHolderId", "UTF-8") + "=" + URLEncoder.encode(supportModel.getCardHolderId(), "UTF-8") + "&"
                    + URLEncoder.encode("transactionNum", "UTF-8") + "=" + URLEncoder.encode(supportModel.getEmTransactionNum(), "UTF-8") + "&"
                    + URLEncoder.encode("claimStatus", "UTF-8") + "=" + URLEncoder.encode(supportModel.getClaimStatus(), "UTF-8") + "&"
                    + URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(supportModel.getFirstName(), "UTF-8") + "&"
                    + URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(supportModel.getLastName(), "UTF-8") + "&"
                    + URLEncoder.encode("fillDate", "UTF-8") + "=" + URLEncoder.encode(filldateFormat.format(supportModel.getFillDate()), "UTF-8") + "&"
                    + URLEncoder.encode("postingDate", "UTF-8") + "=" + URLEncoder.encode(filldateFormat.format(supportModel.getPostingDate()), "UTF-8") + "&"
                    + URLEncoder.encode("fillType", "UTF-8") + "=" + URLEncoder.encode(supportModel.getFileType(), "UTF-8") + "&" + URLEncoder.encode("outofpocket", "UTF-8") + "=" + URLEncoder.encode(supportModel.getOutofpocket(), "UTF-8");
            String urlString = Constants.APP_PATH + servlet;
            logger.info(urlString);
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Method", "GET");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            wr.close();
            rd.close();

            model.addAttribute("message", messageSource.getMessage("support.success", null, null));

        } catch (NoSuchMessageException e) {
            logger.error("Exception", e);
            model.addAttribute("errorMessage", messageSource.getMessage("process.unsuccessful", null, null));
            return "/support";
        } catch (JDOMException e) {
            logger.error("Exception", e);
            model.addAttribute("errorMessage", messageSource.getMessage("process.unsuccessful", null, null));
            return "/support";
        } catch (IOException e) {
            logger.error("Exception", e);
            model.addAttribute("errorMessage", messageSource.getMessage("process.unsuccessful", null, null));
            return "/support";
        }
        return "/support";
    }

    @RequestMapping(value = "/QRCode", method = RequestMethod.GET)
    public ModelAndView getQRWidget(HttpServletRequest request, HttpServletResponse response) {
        SupportModel supportModel = new SupportModel();
        String trigger = request.getParameter("trigger") == null ? "" : request.getParameter("trigger").trim();
        if (trigger == null || trigger.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView("qrwebportal");
            logger.info("Trigger value is null go to QR Web Portal.! ");
            modelAndView.addObject("appPath", Constants.APP_PATH);
            return modelAndView;
        } else {
            logger.info("Trigger value is not null: " + trigger);
            supportModel.setCtrigger(trigger);
            ModelAndView modelAndView = new ModelAndView("widgetportal");
            modelAndView.addObject("supportModel", supportModel);
            modelAndView.addObject("appPath", Constants.APP_PATH);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/QRCode", method = RequestMethod.POST)
    public String WidgetPortal(@ModelAttribute SupportModel supportModel, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            if (supportModel.getOptions().equalsIgnoreCase("T") && supportModel.getPhoneNo().trim().isEmpty()) {
                supportModel.setCommunicationMethod("T");
                model.addAttribute("errorMessage", messageSource.getMessage("webportal.empty.field", null, null));
                return "/widgetportal";
            }
            if (supportModel.getOptions().equalsIgnoreCase("E") && supportModel.getEmail().trim().isEmpty()) {
                supportModel.setCommunicationMethod("E");
                model.addAttribute("errorMessage", messageSource.getMessage("webportal.empty.field", null, null));
                return "/widgetportal";
            }
            if (supportModel.getOptions().equalsIgnoreCase("I") && supportModel.getIvr().trim().isEmpty()) {
                supportModel.setCommunicationMethod("I");
                model.addAttribute("errorMessage", messageSource.getMessage("webportal.empty.field", null, null));
                return "/widgetportal";
            }

            if (supportModel.getOptions().equalsIgnoreCase("E") && !"".equals(supportModel.getEmail())) {
                pattern = Pattern.compile(Constants.EMAIL_PATTERN);
                matcher = pattern.matcher(supportModel.getEmail());
                if (!matcher.matches()) {
                    supportModel.setCommunicationMethod("E");
                    model.addAttribute("errorMessage", messageSource.getMessage("error.invalid.Email", null, null));
                    return "/widgetportal";
                }
                supportModel.setCommunicationMethod("email");
                supportModel.setCommunicationId(supportModel.getEmail());
                logger.info("Communication Method is: " + supportModel.getCommunicationMethod());
            } else if (!"".equals(supportModel.getPhoneNo()) && supportModel.getOptions().equalsIgnoreCase("T")) {
                if (supportModel.getPhoneNo().length() < 10) {
                    supportModel.setCommunicationMethod("T");
                    model.addAttribute("errorMessage", messageSource.getMessage("invalid.phoneNo", null, null));
                    return "/widgetportal";
                }
                supportModel.setCommunicationMethod("text");
                supportModel.setCommunicationId(supportModel.getPhoneNo());
                logger.info("Communication Method is: " + supportModel.getCommunicationMethod());
            } else if (!"".equals(supportModel.getIvr()) && supportModel.getOptions().equalsIgnoreCase("I")) {
                supportModel.setCommunicationMethod("I");
                if (supportModel.getIvr().length() < 10) {
                    model.addAttribute("errorMessage", messageSource.getMessage("invalid.phoneNo", null, null));
                    return "/widgetportal";
                }
                supportModel.setCommunicationId(supportModel.getIvr());
                logger.info("Communication Method is: " + supportModel.getCommunicationMethod());
            }
            if (supportModel.getTc() == null) {
                model.addAttribute("errorMessage", messageSource.getMessage("error.empty.tc", null, null));
                return "/widgetportal";
            }
            if (supportModel.getOptions().equals("I")) {
                supportModel.setCommunicationMethod("text");
            }
            makeWidgetUrl(supportModel);
            supportModel.setCommunicationId(null);
            supportModel.setPhoneNo("");
            supportModel.setEmail("");
            supportModel.setIvr("");
            supportModel.setTc(null);
            model.addAttribute("message", messageSource.getMessage("webportal.process", null, null));
        } catch (IOException | NoSuchMessageException e) {
            logger.error("Exception", e);
            model.addAttribute("errorMessage", messageSource.getMessage("process.unsuccessful", null, null));
            return "/widgetportal";
        }
        return "/widgetportal";
    }

    @RequestMapping(value = "/displayMessage", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse hsr1) throws Exception {
        String showMessage = request.getAttribute("message").toString();
        ModelAndView modelAndView = new ModelAndView("displaymessage");
        modelAndView.addObject("message", showMessage);
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView uploadImage(@ModelAttribute SupportModel supportModel, HttpServletRequest request, @RequestParam("imgUpload") MultipartFile file) throws IOException {
        String webappRoot = servletContext.getRealPath("/");
        String relativeFolder = File.separator + "resources" + File.separator
                + "images" + File.separator;
        String filename = webappRoot + relativeFolder
                + file.getOriginalFilename();

        if (!"".equalsIgnoreCase(filename)) {
            file.transferTo(new File(filename));   //Here I Added
        }
        return showPage(supportModel);
    }

    @RequestMapping(value = "/importFile", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView importFile(@ModelAttribute SupportModel supportModel, @RequestParam("files[]") List<MultipartFile> files, HttpServletRequest request) throws FileNotFoundException, IOException {
        ModelAndView modelAndView = new ModelAndView("support");
        logger.info(" Inside the upload receipts method " + files.size());
        modelAndView.addObject("supportModel", supportModel);
        if (CommonUtil.isNullOrEmpty(files)) {
            modelAndView.addObject("errorMessage", "Please Select files.");
            return modelAndView;
        }

        String destPath = Constants.DRUG_ATTACHMENT_PATH + Constants.ATTACHMENT;
        for (MultipartFile f : files) {
            logger.info("File name " + f.getName());
            FileCopyUtils.copy(f.getBytes(), new File(destPath + f.getOriginalFilename()));
            CommonUtil.executeCommand(Constants.COMMAND + Constants.ATTACHMENT);
            String fileNameWithOutExt = FilenameUtils.removeExtension(f.getOriginalFilename());
            logger.info("File Name WithOut Ext:: " + fileNameWithOutExt);
            if (CommonUtil.isNotEmpty(fileNameWithOutExt)) {
                drugService.saveDrugPdfPath(Constants.INSURANCE_CARD_URL + Constants.ATTACHMENT + f.getOriginalFilename(), Long.parseLong(fileNameWithOutExt), "pdf");
            }
        }
        modelAndView.addObject("message", messageSource.getMessage("support.success", null, null));
        return modelAndView;
    }

    @RequestMapping(value = "/delPatientProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String delPatientProfile(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, @RequestParam(value = "tblName", required = false) String tblName) {
        return supportDelegate.delPatientProfileByPhoneNumber(phoneNumber, tblName);
    }

    @RequestMapping(value = "/sendMsgs", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView appMsgs(@ModelAttribute SupportModel supportModel) {
        ModelAndView modelAndView = new ModelAndView("support");
        populateCampaignMsgsList(modelAndView);
        int response = drugService.isAPPMsgSend(supportModel);
        if (response == 1) {
            modelAndView.addObject("errorMessage", "Phone number is not exist.");
            return modelAndView;
        }
        if (response == 0) {
            modelAndView.addObject("message", "Msg send successfully.");
        } else {
            modelAndView.addObject("errorMessage", messageSource.getMessage("process.unsuccessful", null, null));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/orderDetailList", method = RequestMethod.POST)
    public ModelAndView orderDetailList(@ModelAttribute SupportModel supportModel) {
        ModelAndView modelAndView = new ModelAndView("support");
        populateCampaignMsgsList(modelAndView);
        modelAndView.addObject("orderDetailList", supportDelegate.getOrdersListByStatus(supportModel.getPhoneNo()));
        return modelAndView;
    }

    @RequestMapping(value = "/updateOrderFillDate", method = RequestMethod.POST)
    public @ResponseBody
    String updateOrderFillDate(@RequestBody String jsonRequest, HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            JsonNode nextRefillDateNode = rootNode.path("fillDate");
            String nextRefillDate = nextRefillDateNode.asText();
            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
            boolean isUpdate = supportDelegate.isUpdateOrderFillDate(orderId, nextRefillDate, sessionBean.getUserId());
            if (isUpdate) {
                response = JsonResponseComposer.composeResponse(messageSource.getMessage("field.value.successfully", null, null), Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            } else {
                response = JsonResponseComposer.composeResponse(messageSource.getMessage("server.error", null, null), Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }

        } catch (IOException | NoSuchMessageException e) {
            logger.error("Exception# updateOrderFillDate# ", e);
        }
        return response;
    }

    @RequestMapping(value = "/sendRefillReminderMsg", method = RequestMethod.POST)
    public @ResponseBody
    String sendRefillReminderMsg(@RequestBody String jsonRequest, HttpServletRequest request) {
        String response = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonRequest);

            JsonNode orderIdNode = rootNode.path("orderId");
            String orderId = orderIdNode.asText();

            boolean isMsgSend = supportDelegate.isRefillReminderMsgSend(orderId);
            if (isMsgSend) {
                response = JsonResponseComposer.composeResponse("Msg send successfully.", Constants.JSON_STATUS.SUCCESS, Constants.JSON_STATUS.CODE_SUCCESS);
            } else {
                response = JsonResponseComposer.composeResponse(messageSource.getMessage("process.unsuccessful", null, null), Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
            }

        } catch (IOException | NoSuchMessageException e) {
            logger.error("Exception# sendRefillMsg# ", e);
            response = JsonResponseComposer.composeResponse(e.getMessage(), Constants.JSON_STATUS.FAIL, Constants.JSON_STATUS.CODE_FAILLIURE);
        }
        return response;
    }

    @RequestMapping(value = "/searchPatient", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String searchhPatient(@RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        return supportDelegate.searchPatientProfileByPhoneNumber(phoneNumber);
    }
}
