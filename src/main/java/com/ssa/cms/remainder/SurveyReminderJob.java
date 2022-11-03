/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.service.RefillReminderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javed Iqbal
 */
@Component
public class SurveyReminderJob {

    @Autowired
    private RefillReminderService refillReminderService;
    private static final Logger logger = Logger.getLogger("SurveyReminder");
        //This cron job Run on Every day at noon - 12pm
//    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "0 */5 * ? * *")
    public void sendPushNotificationRemider() throws Exception {
        logger.info("..Start Survey Reminder..");
        try {
            boolean isSurveyMsgSend = refillReminderService.sendSurveyReminders(logger);
            if (isSurveyMsgSend) {
                logger.info("Survey Reminders msg send: " + isSurveyMsgSend);
            } else {
                logger.info("Survey Reminders msg send: " + isSurveyMsgSend);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SurveyReminderJob# sendPushNotificationRemider: " + e);
        }
        logger.info("..End Survey Reminder..");
    }

}
