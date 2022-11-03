package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.CampaignTrigger;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.ShortCodes;
import com.ssa.cms.model.WidgetLog;
import com.ssa.cms.model.WidgetUser;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.service.PhoneValidationService;
import java.io.StringWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class WidgetUtil {

    private static final Log logger = LogFactory.getLog(WidgetUtil.class.getClass());

    private String createXML(WidgetLog log, String paramName, String paramValue, String suggession) {
        String xml = "";

        try {

            Element rootElement = new Element("PMSWidgetNotification");
            Document document = new Document(rootElement);

            Element element = new Element("NotificationType");
            element.setText(log.getNotificationType());
            rootElement.addContent(element);

            element = new Element("NotificationDate");
            element.setText(RedemptionUtil.formatDate(new Date()));
            rootElement.addContent(element);

            element = new Element("TicketId");
            if (log.getId() != null) {
                element.setText(Long.toString(log.getId()));
            } else {
                element.setText("");
            }

            rootElement.addContent(element);

            element = new Element("Description");
            element.setText(log.getNotificationMessage());
            rootElement.addContent(element);

            element = new Element("ParamName");
            if (paramName != null) {
                element.setText(paramName);
            }
            rootElement.addContent(element);

            element = new Element("ParamValue");
            if (paramValue != null) {
                element.setText(paramValue);
            }
            rootElement.addContent(element);

            element = new Element("Suggession");
            if (suggession != null) {
                element.setText(suggession);
            }
            rootElement.addContent(element);

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            StringWriter stringWriter = new StringWriter();
            outputter.output(document, stringWriter);

            xml = stringWriter.toString();

            logger.info("Notificaiton XML  : " + xml);
        } catch (Exception e) {
            logger.error("Exception: WidgetUtil -> createXML", e);
        }
        return xml;
    }

    public void validateWidget(WidgetLog log, PMSTextFlowService textFlowDAO) {

        try {

            log.setNotificationType(Constants.SUCCESS);
            log.setNotificationMessage("Data received successfully");
            String notification = this.createXML(log, null, null, null);
            log.setNotificationXml(notification);

            // Validation of email or phone number
            String communicationId = log.getCommunicationId();
            if (communicationId.length() == 0) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Communicaiton Id value required");
                notification = this.createXML(log, "CommunicationId", "Value missing", "Communication Id is required field with value 10 digit mobile number");
                log.setNotificationXml(notification);
                return;
            } else if (log.getCommunicationMethod().equalsIgnoreCase("T")) {
                if (validatePhoneNo(communicationId, log, textFlowDAO)) {
                    return;
                }
            } else if (log.getCommunicationMethod().equalsIgnoreCase("E")) {
                Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(log.getEmail());
                if (!matcher.matches()) {
                    log.setNotificationType(Constants.FAILURE);
                    log.setNotificationMessage("Communicaiton Id should be valid email required");

                    notification = this.createXML(log, "CommunicationId", "Invalid email", "Communication Id must be email number when communication method is E");
                    log.setNotificationXml(notification);
                    return;
                }
                if (validatePhoneNo(communicationId, log, textFlowDAO)) {
                    return;
                }
            }

            String userId = log.getUserId();
            if (userId == null || userId.trim().length() == 0) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("User Id value required");
                notification = this.createXML(log, "userId", "Value missing", "User id value required");
                log.setNotificationXml(notification);
                return;

            }

            String password = log.getPassword();
            if (password == null || password.trim().length() == 0) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Password value required");
                notification = this.createXML(log, "password", "Value missing", "Password value required");
                log.setNotificationXml(notification);
                return;

            }

            //all right validate that user name exists in database
            WidgetUser widgetUser = textFlowDAO.getWidgetUser(userId, password);
            if (widgetUser == null) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("User authentication failed");
                notification = this.createXML(log, "userId", "Invalid value", "User authentication failed");
                log.setNotificationXml(notification);
                return;
            }

            //validate Ip address if required
            if (widgetUser.getValidateIp().equalsIgnoreCase("yes")) {
                Integer widgetUserId = widgetUser.getWidgetUserId();
                boolean isIpAddressValid = textFlowDAO.isIPAddressValid(widgetUserId, log.getRemoteIpAddress());
                if (!isIpAddressValid) {
                    log.setNotificationType(Constants.FAILURE);
                    log.setNotificationMessage("User IP authentication failed");
                    notification = this.createXML(log, "userId", "Invalid value", "User IP authentication failed");
                    log.setNotificationXml(notification);
                    return;
                }
            }

            String trigger = log.getTriggerValue();
            if (trigger == null || trigger.trim().length() == 0) {

                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Trigger value required");

                notification = this.createXML(log, "trigger", "Value missing", "Trigger value required");
                log.setNotificationXml(notification);
                return;

            }

            trigger = trigger.trim();
            if (trigger.length() > 10) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Trigger value length exceeds");

                notification = this.createXML(log, "trigger", "Invalid Value", "Trigger value exceeds the max length");
                log.setNotificationXml(notification);
                return;
            }

            CampaignTrigger campaignTrigger = textFlowDAO.getTriggerByKeyword(trigger);
            if (campaignTrigger == null) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Trigger invalid value");
                notification = this.createXML(log, "trigger", "Invalid Value", "Trigger : " + trigger + " is not listed in system. Please provide valid value");
                log.setNotificationXml(notification);
                return;
            }

            Campaigns campaign = campaignTrigger.getCampaigns();
            if (campaign == null) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Trigger invalid value");
                notification = this.createXML(log, "trigger", "No active campaign", "Trigger : " + trigger + " is not listed in system. Please provide valid value");
                log.setNotificationXml(notification);
                return;
            }

            ShortCodes shortCodes = campaign.getShortCodes();
            if (shortCodes == null) {
                log.setNotificationType(Constants.FAILURE);
                log.setNotificationMessage("Short code value not found.");
                notification = this.createXML(log, "trigger", "Invalid value", "Short code value not found against trigger : " + trigger + "");
                log.setNotificationXml(notification);
                return;
            }
            log.setShortCode(Integer.toString(shortCodes.getShortCode()));

        } catch (Exception e) {
            logger.error("Exception: WidgetUtil -> validateWidget", e);
        }
    }

    private boolean validatePhoneNo(String communicationId, WidgetLog log, PMSTextFlowService textFlowDAO) throws Exception {
        String notification;
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(communicationId);
        if (!matcher.matches()) {
            log.setNotificationType(Constants.FAILURE);
            log.setNotificationMessage("Communicaiton Id 10 digit phone number required");
            notification = this.createXML(log, "CommunicationId", "Invalid value", "Communication Id must be 10 digit phone number when communication method is  T");
            log.setNotificationXml(notification);
            return true;
        }
        // Phone Validation
        String PVURL = textFlowDAO.getURL(Constants.PVURL);
        PhoneValidationService phoneValidationService = new PhoneValidationService(PVURL);
        boolean phoneValidity = phoneValidationService.checkPhoneValidity(communicationId, "0000", "PMS");
        if (!phoneValidity) {
            log.setNotificationType(Constants.FAILURE);
            log.setNotificationMessage("Communicaiton Id should be valid 10 digit mobile number");
            notification = this.createXML(log, "CommunicationId", "Invalid value", "Communication Id must be 10 digit valid mobile number when communication method is T");
            log.setNotificationXml(notification);
            return true;
        }
        return false;
    }
}
