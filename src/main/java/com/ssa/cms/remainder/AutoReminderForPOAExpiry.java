/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author javed.iqbal
 */
@Component
public class AutoReminderForPOAExpiry {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PatientProfileService patientProfileService;

    private final Logger successed = Logger.getLogger("successPOAExpiry");
    private final Logger failed = Logger.getLogger("failedPOAExpiry");

    @Scheduled(cron = "0 0 02 * * *")  //Fire at 2am every day
    @Async
    private void autoReminder() {
        successed.info(".....AutoReminderForPOAExpiry Start.....");
//        orderService.reminderPOAExpiry(successed, failed);
        CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs("Update Caretaker POA", Constants.PHARMACY_NOTIFICATION);
        if (campaignMessages == null || CommonUtil.isNullOrEmpty(campaignMessages.getMessageId())) 
        {
            failed.info("There are no caretaker msg found.");
            return;
        }
        patientProfileService.reminderPOAExpiry(successed, failed,campaignMessages);
    }

    public void autoReminder(OrderService orderService) {
        this.orderService = orderService;
        this.autoReminder();
    }
    
    public void autoReminder(PatientProfileService service) 
    {
        this.patientProfileService = service;
        this.autoReminder();
    }
}
