package com.ssa.cms.servlet;

import com.ssa.cms.common.Constants;
import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.enums.PlaceholderEnum;
import com.ssa.cms.model.CampaignMessageRequest;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.CustomerRequest;
import com.ssa.cms.model.OptInOut;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Event;
import com.ssa.cms.model.EventHasFolderHasCampaigns;
import com.ssa.cms.model.Intervals;
import com.ssa.cms.model.IntervalsType;
import com.ssa.cms.model.MessagePriority;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Practices;
import com.ssa.cms.model.Response;
import com.ssa.cms.model.ValidResponse;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.service.RedemptionService;
import com.ssa.cms.thread.TextIntervalMessageRequestThread;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.SMSUtil;
import com.ssa.cms.util.TextFlowUtil;
import static com.ssa.cms.validation.DailyValidationUtil.log;
import com.ssa.decorator.MTDecorator;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class PMSGenericTextFlowServlet {

    private static final Logger logger = Logger.getLogger(PMSGenericTextFlowServlet.class);

    @Autowired
    PMSTextFlowService textFlowService;
    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private RedemptionService redemptionService;

    @RequestMapping(value = "/PMSGenericTextFlow", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/PMSGenericTextFlow", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String phoneNumber;
        String message;
        String qualifyAmount = "";
        //response from user
        if (request.getParameter("from") == null || "".equals(request.getParameter("from")) || request.getParameter("message") == null || "".equals(request.getParameter("message"))) {
            phoneNumber = request.getParameter("PhoneNumber") == null ? "" : request.getParameter("PhoneNumber").trim();
            message = request.getParameter("Message") == null ? "" : request.getParameter("Message").trim();//message Save,Yes,Initials,N 
            qualifyAmount = request.getParameter("QualifyAmount") == null ? "" : request.getParameter("QualifyAmount");

            if ("".equals(phoneNumber)) {
                phoneNumber = request.getAttribute("from") == null ? "" : request.getAttribute("from").toString();
            }
            if ("".equals(message)) {
                message = request.getAttribute("message") == null ? "" : request.getAttribute("message").toString();//message Save,Yes,Initials,N      
            }
            if ("".equals(qualifyAmount)) {
                qualifyAmount = request.getAttribute("QualifyAmount") == null ? "" : request.getAttribute("QualifyAmount").toString();
            }

        } else { //from system
            phoneNumber = request.getParameter("from") == null ? "" : request.getParameter("from").trim();
            message = request.getParameter("message") == null ? "" : request.getParameter("message").trim();//message Save,Yes,Initials,N      
        }

        if (phoneNumber.length() == 0) {
            response.getOutputStream().print("Phone number empty");
            return;
        }
        if (message.length() == 0) {
            response.getOutputStream().print("Message is empty");
            return;
        }
        String source = request.getParameter("source") == null ? "" : request.getParameter("source").trim();
        String widgetName = request.getParameter("widgetName") == null ? "" : request.getParameter("widgetName").trim();
        String inputRefNumber = request.getParameter("inputReferenceNumber") == null ? "" : request.getParameter("inputReferenceNumber").trim();
        String eventName = request.getParameter("eventName") == null ? "" : request.getParameter("eventName").trim();

        try {
            if (source.length() == 0) {
                source = "0002"; //phone
            }

            long inputReferenceNumber = validateInputReference(inputRefNumber);

            int sCode = 88202;
            if (phoneNumber.length() == 11) {
                phoneNumber = phoneNumber.substring(1);
            }
            logger.info("Trigger/Response= " + message+" phoneNumber= "+phoneNumber);
            CampaignTrigger trigger = textFlowService.getTriggerByKeyword(message);
            if (trigger != null) {
                boolean isProcessTrigger = processTrigger(trigger, phoneNumber, sCode, message, source, widgetName, inputReferenceNumber, eventName);
//                response.getWriter().write(isProcessTrigger+"");
                return;
            }

            //cancel request
            textFlowService.cancelRequest(message, phoneNumber, sCode);

            //stop, help and invalid response handling
            if (standardMsgsRequest(message, phoneNumber, sCode, Constants.STANDARD, inputReferenceNumber)) {
                return;
            }

            //proccessing Y,YES,YEP ect.
            ValidResponse validResponse = textFlowService.getValidResponse(message);
            String msgResponse = "";
            CustomerRequest customerRequest = textFlowService.getCustomerRequestIP(phoneNumber, sCode);
            if (validResponse == null) {
                logger.info("No valid response found. (System will return)");
                //SMSUtil.sendSmsMessage(phoneNumber, Constants.INVALIDRESPONSE);
                if (standardMsgsRequest(Constants.INVALIDRESPONSE, phoneNumber, sCode, Constants.STANDARD, inputReferenceNumber)) {
                    return;
                }
                return;
            }

            int responseIdWithWord = 0;
            if (msgResponse.equalsIgnoreCase("")) {
                responseIdWithWord = validResponse.getResponse().getResponseId();
            }

            MessagePriority messagePriority = textFlowService.getMessagePriority(phoneNumber, sCode);
            if (messagePriority == null) {
                logger.info("No message priority found. (System will return)");
                return;
            }

            String messageContext = messagePriority.getMessageContext();
            if (messageContext == null) {
                logger.info("No message context found. (System will return)");
                return;
            }

            logger.info("Servlet context value : " + messageContext);

            if (!messageContext.equalsIgnoreCase("TextFlow")) {
                logger.info("Inside for Refill");
                if (messageContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REFILL) || messageContext.equalsIgnoreCase(Constants.MSG_CONTEXT_REPEAT_REFILL) || messageContext.equalsIgnoreCase(Constants.REDEMPTION)) {
                    logger.info("Inside for Refill");
                    request.setAttribute("messageReqNo", messagePriority.getMessageSeqNo());
                    request.setAttribute("path", messagePriority.getPath());
                    request.getRequestDispatcher("/PMSGenericAnonymousResponseHandler").forward(request, response);
                    return;
                }
            }

            if (customerRequest == null) {
                logger.info("No in progress record found. (System will return)");
                return;
            }

            long crSeqNo = customerRequest.getCrSeqNo();
            logger.info("crSeqNo= " + crSeqNo);
            int yCount = customerRequest.getYcount();
            int campaignId = customerRequest.getCampaignId();
            int shortCodeValue = customerRequest.getShortCode();
            String campaignName = customerRequest.getCampaignName();

            Campaigns campaign = textFlowService.getCampaigns(campaignId);

            if (campaign == null) {
                logger.info("Campaign is null");
                return;
            }

            int messageTypeId = 0;
            int folderId = 0;
            String communicationPath = customerRequest.getCommunicationPath();
            CampaignMessageRequest campaignMessageRequest;
            boolean sendSMS = false;

            if (communicationPath.equalsIgnoreCase(Constants.SMS)) {

                campaignMessageRequest = textFlowService.getLastCampaignMessageRequestByCRSeqNo(crSeqNo);

                if (campaignMessageRequest != null) {
                    messageTypeId = campaignMessageRequest.getMessageTypeId();
                    folderId = campaignMessageRequest.getFolderId();
                    sendSMS = true;
                }
            }

            logger.info("messageTypeId= " + messageTypeId);
            logger.info("folderId= " + folderId);
            logger.info("sendSMS= " + sendSMS);

            if (!sendSMS && communicationPath.equalsIgnoreCase(Constants.SMS)) {
                logger.info("Phone number :" + phoneNumber + " has no record in SMS");
                return;
            }

            logger.info("Previous request MessageTypeId : " + messageTypeId);
            logger.info("Previous request folderId : " + folderId);

            if (messageTypeId == 0 && folderId == 0) {
                logger.info("Both folderId and messageTypeId are 0 for previous request");
                return;
            }

            List<CampaignMessagesResponse> list = textFlowService.getResponseByCommunicationPath(campaignId, folderId, messageTypeId, communicationPath);
            if (list == null || list.isEmpty()) {
                logger.info("No CampaignMessageResponses object found (System will return)");
                return;
            }
            int count = 0;
            
            for (CampaignMessagesResponse campaignMessageResponses : list) {
                logger.info("CampaignMessagesResponse Id : " + campaignMessageResponses.getCampaignMessagesResponseId());
                int responseId = 0;
                String cmrResponse = "";
                 Response responsee = campaignMessageResponses.getResponse();
             if(responsee!= null){
                responseId = responsee.getResponseId();
           if(responseId == 0){
               logger.info("response Id : " + responseId+ " (System will return)");
               continue;
           }
             }
                             
//                if (msgResponse.equalsIgnoreCase("")) {
//                    responseId = campaignMessageResponses.getResponse().getResponseId();
//                } else {
//                    cmrResponse = campaignMessageResponses.getResponse().getResponseTitle();
//                }

                logger.info("Response id with word : " + responseIdWithWord);
                logger.info("response Id : " + responseId);

                if (msgResponse.equalsIgnoreCase("")) {
                    if (responseId != responseIdWithWord) {
                        logger.info("Invalid response has been provided. (System will return)");
                        count++;
                        if (count == list.size()) {
                            if (standardMsgsRequest(Constants.INVALIDRESPONSE, phoneNumber, sCode, Constants.STANDARD, inputReferenceNumber)) {
                                return;
                            }
                        }
                        continue;
                    }

                } else if (!cmrResponse.equalsIgnoreCase(msgResponse)) {
                    logger.info("Invalid response has been provided. (System will return)");
                    count++;
                    if (count == list.size()) {
                        if (standardMsgsRequest(Constants.INVALIDRESPONSE, phoneNumber, sCode, Constants.STANDARD, inputReferenceNumber)) {
                            return;
                        }
                    }
                    continue;
                }
                logger.info("yCount= " + yCount);
                int nextMessageTypeId = campaignMessageResponses.getNextMessage();
                if (yCount > 0 && "CM".equalsIgnoreCase(customerRequest.getStatusCode())) {
                    nextMessageTypeId = Constants.PT_ALREADY_ENROLLED_MSG_ID;
                } else {
                    yCount = yCount + 1;
                    customerRequest.setYcount(yCount);
                }
                List<CampaignMessages> campaignMessagesList = textFlowService.getCampaignMessagesByCommunicationPath(campaignId, folderId, nextMessageTypeId, communicationPath);

                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will return)");
                    continue;
                }

                CampaignMessages campaignMessages = campaignMessagesList.get(0);

                if (campaignMessages == null) {
                    logger.info("CampaignMessages object not found. (System will return)");
                    continue;
                }

                customerRequest.setLastUpdatedOn(new Date());

                textFlowService.update(customerRequest);

                int messageId = campaignMessages.getMessageId();

                String messageText = campaignMessages.getSmstext();

                logger.info("SMS Text : " + messageText);

                CampaignMessagesResponse campaignMessagesResponse = textFlowService.getCampaignMessagesResponse(campaignId, folderId, nextMessageTypeId);

                if (campaignMessagesResponse == null) {
                    logger.info("campaignMessagesResponse for current message is null.");
                    continue;
                }

                Intervals interval = campaignMessagesResponse.getIntervals();
                IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();

                int intervalId = interval.getIntervalId();
                String intervalDesc = interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                String eventDesc = textFlowService.getEventsDescription(campaignId, folderId, communicationPath);

                int intervalValue = interval.getIntervalValue();
                int intervalUnitInSecond = intervalsType.getUnitInSecond();
                long secondsDelay = intervalValue * intervalUnitInSecond;

                logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");

                String groupNumber = "";

                if (messageText != null && messageText.trim().length() > 0) {
                    if (!"".equals(qualifyAmount)) {
                        logger.info("Replace Qualify Amount: " + qualifyAmount);
                        messageText = TextFlowUtil.parseMessage(messageText, Double.parseDouble(qualifyAmount));
                    }
//                    messageText = messageText.replace("PT_INFO_URL", RedemptionUtil.preparePatientProfileURL(phoneNumber));
                    campaignMessages.setSmstext(messageText);
                    campaignMessageRequest = textFlowService.setCampaignMessageRequest(crSeqNo, campaignId, campaignName, shortCodeValue,
                            messageId, nextMessageTypeId, folderId, inputReferenceNumber, intervalId, intervalDesc, eventDesc, communicationPath, message);

                    setAndStartMessageThread(campaignMessages, customerRequest, phoneNumber, inputReferenceNumber,
                            secondsDelay, campaign, folderId, nextMessageTypeId, communicationPath, intervalId, intervalDesc,
                            eventDesc, groupNumber, campaignMessageRequest, false);

                    if (nextMessageTypeId != Constants.PT_ALREADY_ENROLLED_MSG_ID) {
                        textFlowService.updateEnrollmentIpad(phoneNumber);
                        textFlowService.updateOrderStatusInResponseYes(phoneNumber);                     
                    }
                }
            }
        } catch (NumberFormatException e) {
            logger.error("Exception: PMSGenericTextFlowServlet:NumberFormatException -> doPost", e);
        } catch (ServletException e) {
            logger.error("Exception: PMSGenericTextFlowServlet:ServletException -> doPost", e);
        } catch (IOException e) {
            logger.error("Exception: PMSGenericTextFlowServlet:IOException -> doPost", e);
        } catch (Exception e) {
            logger.error("Exception: PMSGenericTextFlowServlet:Exception -> doPost", e);
            e.printStackTrace();
        } finally {
        }
    }

    private boolean processTrigger(CampaignTrigger trigger, String phoneNumber, int sCode, String message, String source,
            String widgetName, long inputReferenceNumber, String eventName) {
        try {
            Campaigns campaign = trigger.getCampaigns();
            if (validateCampaign(campaign, trigger)) {
                return false;
            }

            int campaignId = campaign.getCampaignId();
            String campaignName = campaign.getCampaignName();
            int shortCodeValue = campaign.getShortCodes().getShortCode();
            ShortCodes shortCode = textFlowService.getShortCodeByCode(shortCodeValue);
            logger.info("Campaign Name Decided : " + campaignName);
            if (shortCode == null) {
                logger.info("Short code not defined for canpaign : " + campaignName);
                return false;
            }

            //cancel in progress request if any
            CustomerRequest customerRequest = textFlowService.getCustomerRequestIP(phoneNumber, sCode);
            textFlowService.cancelRequest(customerRequest);

            customerRequest = textFlowService.setCustomerRequest(phoneNumber, campaignId, campaignName, message,
                    shortCodeValue, source, widgetName, inputReferenceNumber, 0, null, null, StatusEnum.IN_PROGRESS.getValue());

            OptInOut optInOut = textFlowService.setOptinOut(phoneNumber, campaignId, campaignName, sCode, StatusEnum.IN_PROGRESS.getValue());
            textFlowService.save(customerRequest);

            optInOut.setCrSeqNo(customerRequest.getCrSeqNo());
            optInOut.setStatusCode(StatusEnum.IN_PROGRESS.getValue());
            textFlowService.save(optInOut);
            if (eventName == null || eventName.trim().length() == 0) {
                eventName = message;
            }

            //mark campaign end in case of IVR & return
//        if ("0003".equals(source)) {
//            TextFlowUtil.markCampaignEnd(customerRequest, campaign, "I", textFlowService);
//            return true;
//        }
            Event event = textFlowService.getEventByStaticValue(eventName.trim());
            if (event == null) {
                logger.info("No such event defined (System will return)");
                return false;
            }

            EventHasFolderHasCampaigns eventHasFolderHasCampaigns = textFlowService.getEventHasFolderHasCampaign(campaignId, event.getEventId(), Constants.SMS);
            if (eventHasFolderHasCampaigns == null) {
                logger.info("No folder associated to this campaign (System will return)");
                return false;
            }

            int folderId = eventHasFolderHasCampaigns.getFolderId();
            logger.info("Folder Id : " + folderId);
            String communicationType;
            communicationType = Constants.SMS;

            customerRequest.setCommunicationPath(communicationType);
            textFlowService.update(customerRequest);
            List<CampaignMessages> campaignMessagesList = textFlowService.getCampaignMessagesByCommunicationType(campaignId, folderId, communicationType);
            if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                logger.info("No messages found for (System will return)");
                return false;
            }

            if (campaignMessagesList.size() == 1) {
                logger.info("Perform campaign end activities");
            }

            CampaignMessages campaignMessages = campaignMessagesList.get(0);
            int messageId = campaignMessages.getMessageId();
            int messageTypeId = campaignMessages.getMessageType().getId().getMessageTypeId();
            String welcomeMessage = campaignMessages.getSmstext();
            logger.info("Message Id : " + messageId + "Message Type Id : " + messageTypeId + "Welcome Message : " + welcomeMessage);
            if (CommonUtil.isNullOrEmpty(welcomeMessage)) {
                logger.info("welcomeMessage is null.");
                return false;
            }
            List<CampaignMessagesResponse> list = textFlowService.getResponseByCommunicationPath(campaignId, folderId, messageTypeId, communicationType);
            if (list == null || list.isEmpty()) {
                logger.info("campaignMessagesResponse for current message is null.");
                return false;
            }

            for (CampaignMessagesResponse campaignMessagesResponse : list) {
                Intervals interval = campaignMessagesResponse.getIntervals();
                IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
                int intervalId = interval.getIntervalId();
                String intervalDesc = interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                String eventDesc = textFlowService.getEventsDescription(campaignId, folderId, communicationType);
                int intervalValue = interval.getIntervalValue();
                int intervalUnitInSecond = intervalsType.getUnitInSecond();
                long secondsDelay = intervalValue * intervalUnitInSecond;
                String groupNumber = "",practiceName="";
//                PatientProfile profile = patientProfileService.getPatientProfileByMobileNumber(phoneNumber);
                PatientProfile profile = patientProfileService.getPatientProfileByPhone(phoneNumber);
                if (profile != null && !CommonUtil.isNullOrEmpty(profile.getPracticeId())) {
                    logger.info("PracticeId= "+profile.getPracticeId());
                    Practices practices = (Practices) patientProfileService.findRecordById(new Practices(), profile.getPracticeId());
                    if(practices!=null){
                        practiceName=AppUtil.getSafeStr(practices.getPracticename(),"");
                    }
                }
                logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");
                if (welcomeMessage != null && welcomeMessage.trim().length() > 0) {
                    CampaignMessageRequest campaignMessageRequest = textFlowService.setCampaignMessageRequest(customerRequest.getCrSeqNo(), campaignId, campaignName,
                            shortCodeValue, messageId, messageTypeId, folderId, inputReferenceNumber, intervalId, intervalDesc, eventDesc, communicationType, message);
                    campaignMessages.setSmstext(welcomeMessage.replace(PlaceholderEnum.PHARMACY_NAME.getValue(),practiceName));

                    setAndStartMessageThread(campaignMessages, customerRequest, phoneNumber, inputReferenceNumber,
                            secondsDelay, campaign, folderId, messageTypeId, communicationType, intervalId, intervalDesc,
                            eventDesc, groupNumber, campaignMessageRequest, false);
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PMSGenericTextFlowSerlvlet-> processTrigger", e);
        }

        return true;
    }

    private long validateInputReference(String inputRefNumber) throws NumberFormatException {
        long inputReferenceNumber = 0;
        if (inputRefNumber != null && inputRefNumber.trim().length() > 0) {

            inputRefNumber = inputRefNumber.trim();
            Pattern pattern = Pattern.compile("^\\d{1,20}$");

            Matcher matcher = pattern.matcher(inputRefNumber);

            if (matcher.matches()) {
                inputReferenceNumber = Long.valueOf(inputRefNumber);
            }

        }
        return inputReferenceNumber;
    }

    private boolean validateCampaign(Campaigns campaign, CampaignTrigger trigger) {
        if (campaign == null) {
            logger.info("No active campaign for trigger : " + trigger);
            return true;
        }
        if (!campaign.getIsActive().equalsIgnoreCase("YES")) {
            logger.info("campaign is not Actice. (System will return)");
            return true;
        }
        Date today = new Date();
        if (today.compareTo(campaign.getTerminationDateTime()) > 0) {
            logger.info("campaign has been expired. (System will return)");
            return true;
        }
        if (!campaign.getIsDelete().equalsIgnoreCase("NO")) {
            logger.info("campaign is deleted. (System will return)");
            return true;
        }
        return false;
    }

    private boolean standardMsgsRequest(String message, String phoneNumber, int sCode, String eventName, long inputReferenceNumber) throws Exception {
        if (message.equalsIgnoreCase("STOP") || message.equalsIgnoreCase("Help") || message.equalsIgnoreCase(Constants.INVALIDRESPONSE)) {
            CustomerRequest customerRequest = textFlowService.getCustomerRequestIPorCM(phoneNumber, sCode);
            if (customerRequest != null) {
                if (message.equalsIgnoreCase("STOP")) {
                    message = Constants.STOP;
                    customerRequest.setStatusCode(StatusEnum.STOPPED.getValue());
                    customerRequest.setLastUpdatedOn(new Date());
                    textFlowService.update(customerRequest);

                    OptInOut optInOut = textFlowService.getOptInOut(customerRequest.getCrSeqNo());
                    if (optInOut != null) {
                        optInOut.setStatusCode(StatusEnum.STOPPED.getValue());
                        optInOut.setOptInOut("O");
                        textFlowService.update(optInOut);
                    }
//                    textFlowService.OptOutPatientProfile(phoneNumber);
                } else {
                    message = message.equalsIgnoreCase("Help") ? Constants.HELP : Constants.INVALIDRESPONSE;
                    logger.info("Request Message is: " + message);
                }

                ValidResponse validResponse = textFlowService.getValidResponse(message);
                if (validResponse == null) {
                    logger.info("STOP response not found for the program. (System will return)");
                    return false;
                }

                CampaignMessageRequest campaignMessageRequest;
                if (customerRequest.getCrSeqNo() != null) {
                    campaignMessageRequest = textFlowService.getLastCampaignMessageRequestByCRSeqNo(customerRequest.getCrSeqNo());
                } else {
                    log.info("customerRequest found with invalid crSeqNo. (System will return)");
                    return false;
                }
                Event event = textFlowService.getEventByStaticValue(eventName.trim());
                if (event == null) {
                    logger.info("No such event defined (System will return)");
                    return true;
                }

                EventHasFolderHasCampaigns eventHasFolderHasCampaigns = textFlowService.getEventHasFolderHasCampaign(customerRequest.getCampaignId(), event.getEventId(), Constants.SMS);
                if (eventHasFolderHasCampaigns == null) {
                    logger.info("No folder associated to this campaign (System will return)");
                    return true;
                }

                int folderId = eventHasFolderHasCampaigns.getFolderId();
                logger.info("Folder Id : " + folderId);
                List<CampaignMessages> campaignMessagesList = textFlowService.getCampaignMessagesByCommunicationType(customerRequest.getCampaignId(), folderId, Constants.SMS);
                if (campaignMessagesList == null || campaignMessagesList.isEmpty()) {
                    logger.info("No messages found for (System will return)");
                    return true;
                }

                if (campaignMessagesList.size() == 1) {
                    logger.info("Perform campaign end activities");
                }
                String messageTitle;
                int messageId;
                int messageTypeId;
                CampaignMessages campaignMessages;
                CampaignMessagesResponse campaignMessagesResponse;
                if (campaignMessageRequest == null) {
                    log.info("campaignMessageRequest not found for the given crSeqNo. (System will return)");
                    return false;
                }
                campaignMessagesResponse = redemptionService.getCampaignMessagesResponseByResComm(customerRequest.getCampaignId(), folderId, validResponse.getResponse().getResponseTitle(), Constants.SMS);
                campaignMessages = campaignMessagesResponse.getCampaignMessages();
                messageId = campaignMessages.getMessageId();
                messageTypeId = campaignMessagesResponse.getMessageTypeId();
                messageTitle = campaignMessagesResponse.getCampaignMessages().getSmstext();
                logger.info("Message Id : " + messageId + "Message Type Id : " + messageTypeId + "Welcome Message : " + messageTitle);

                Intervals interval = campaignMessagesResponse.getIntervals();
                IntervalsType intervalsType = campaignMessagesResponse.getIntervals().getIntervalsType();
                int intervalId = interval.getIntervalId();
                String intervalDesc = interval.getIntervalValue() + " " + intervalsType.getIntervalsTypeTitle();
                String eventDesc = textFlowService.getEventsDescription(customerRequest.getCampaignId(), folderId, Constants.SMS);
                int intervalValue = interval.getIntervalValue();
                int intervalUnitInSecond = intervalsType.getUnitInSecond();
                long secondsDelay = intervalValue * intervalUnitInSecond;

                logger.info("Thread going to sleep for " + secondsDelay + " Seconds.");
                if (messageTitle != null && messageTitle.trim().length() > 0) {
                    logger.info("Send STOP Message");
                    campaignMessageRequest = textFlowService.setCampaignMessageRequest(customerRequest.getCrSeqNo(), customerRequest.getCampaignId(), customerRequest.getCampaignName(),
                            sCode, messageId, messageTypeId, folderId, inputReferenceNumber, intervalId, intervalDesc, eventDesc, Constants.SMS, message);
                    MTDecorator decorator = SMSUtil.sendSmsText(phoneNumber, messageTitle);
                    campaignMessageRequest.setEffectiveDate(new Date());
                    textFlowService.save(campaignMessageRequest);

                    textFlowService.saveCampaignMessageReqRes(decorator, campaignMessageRequest.getCmrSeqNo(), sCode, customerRequest.getCampaignId(),
                            customerRequest.getCampaignName(), messageId, messageTypeId, folderId, inputReferenceNumber);

                    return true;
                } else {
                    logger.info("Message Title is null or messageTitle length is zero: " + messageTitle);
                    return false;
                }

            } else {
                logger.info("Customer Request is null: " + customerRequest);
                return false;
            }
        }
        return false;
    }

    private void setAndStartMessageThread(CampaignMessages campaignMessages, CustomerRequest customerRequest, String phoneNumber,
            long inputReferenceNumber, long secondsDelay, Campaigns campaign, int folderId, int messageTypeId,
            String communicationType, int intervalId, String intervalDesc, String eventDesc, String groupNumber,
            CampaignMessageRequest campaignMessageRequest, boolean isWebEnabled) {

        TextIntervalMessageRequestThread intervalMessageThread = new TextIntervalMessageRequestThread();
        intervalMessageThread.setGatewayInfo(null);
        intervalMessageThread.setCampaignMessages(campaignMessages);
        intervalMessageThread.setAppName("PMS text flow thread");
        intervalMessageThread.setCustomerRequest(customerRequest);
        intervalMessageThread.setPhoneNumber(phoneNumber);
        intervalMessageThread.setInputReferenceNumber(inputReferenceNumber);
        intervalMessageThread.setSecondsDelay(secondsDelay);
        intervalMessageThread.setCampaign(campaign);
        intervalMessageThread.setTextFlowDAO(textFlowService);
        intervalMessageThread.setFolderId(folderId);
        intervalMessageThread.setMessageTypeId(messageTypeId);
        intervalMessageThread.setCommunicationType(communicationType);
        intervalMessageThread.setIsWebEnabled(isWebEnabled);
        intervalMessageThread.setIntervalId(intervalId);
        intervalMessageThread.setIntervalDesc(intervalDesc);
        intervalMessageThread.setEventDesc(eventDesc);
        intervalMessageThread.setGroupNumber(groupNumber);
        intervalMessageThread.setThreadType("text_flow");
        intervalMessageThread.setCampaignMessageRequest(campaignMessageRequest);
        Thread smsIntervalMessageThread = new Thread(intervalMessageThread);
        smsIntervalMessageThread.start();
    }

}
