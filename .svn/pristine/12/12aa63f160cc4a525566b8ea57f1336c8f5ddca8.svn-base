/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.thread;

import com.ssa.cms.common.Constants;
import com.ssa.cms.service.ConsumerRegistrationService;
import org.apache.commons.logging.Log;

/**
 *
 * @author Rizwan.Munir
 */
public class EmialThread implements Runnable{

   public String firstName; 
   public String emailAdress; 
   public String password; 
   public ConsumerRegistrationService consumerRegistrationService; 
   public Log logger;

    public EmialThread(String firstName, String emailAdress, String password, ConsumerRegistrationService consumerRegistrationService, Log logger) {
        this.firstName = firstName;
        this.emailAdress = emailAdress;
        this.password = password;
        this.consumerRegistrationService = consumerRegistrationService;
        this.logger = logger;
    }
    

    
   
   
    @Override
    public void run() {

        try {
            consumerRegistrationService.sendUsernameEmail(firstName, emailAdress, Constants.APP_ACCOUNT_CREATED_USERNAME);
            consumerRegistrationService.sendPsw(firstName, emailAdress, password, Constants.APP_ACCOUNT_CREATED_PSW);
        } catch (Exception e) {
            logger.info("Exception-->EmialThread"+e.getMessage());
            e.getStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConsumerRegistrationService getConsumerRegistrationService() {
        return consumerRegistrationService;
    }

    public void setConsumerRegistrationService(ConsumerRegistrationService consumerRegistrationService) {
        this.consumerRegistrationService = consumerRegistrationService;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }
    
}
