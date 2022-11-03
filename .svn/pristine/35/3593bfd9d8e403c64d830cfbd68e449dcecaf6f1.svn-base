/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.thread;

import com.ssa.cms.model.OrderPlaceEmail;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.EmailSenderUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import java.util.List;
import org.apache.commons.logging.Log;

/**
 *
 * @author mzubair
 */
public class SMSAndEmailSendThread implements Runnable {

    private String phoneNumber;
    private String email;
    private String message;
    private String subject;
    private String emailBody;
    private final Log logger;
    private PatientProfileService patientProfileService;
    private List<OrderPlaceEmail> orderPlaceEmails;

    public SMSAndEmailSendThread(Log logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.info("Start SMS and email sender Thread");
        try {
            //patientProfileService.sendVerificationCode(phoneNumber, message);
            logger.info("Subject# " + subject);
            logger.info("EmailBody# " + emailBody);
            orderPlaceEmails.stream().forEach((orderPlaceEmail) -> {
                EmailSenderUtil.send(EncryptionHandlerUtil.getDecryptedString(orderPlaceEmail.getEmail()), subject, emailBody);
            });
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Exception# SMSAndEmailSendThread# ", e);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PatientProfileService getPatientProfileService() {
        return patientProfileService;
    }

    public void setPatientProfileService(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public List<OrderPlaceEmail> getOrderPlaceEmails() {
        return orderPlaceEmails;
    }

    public void setOrderPlaceEmails(List<OrderPlaceEmail> orderPlaceEmails) {
        this.orderPlaceEmails = orderPlaceEmails;
    }

}
