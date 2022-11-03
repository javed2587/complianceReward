/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.service.RefillReminderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zubair
 */
@Component
public class MBRReminderJob {

    private final Logger log = Logger.getLogger("MbrReminders");

    @Autowired
    private RefillReminderService refillReminderService;
        //This cron job Run on Every day at noon - 12pm
//    @Scheduled(cron = "0 0 12 * * ?")
  //This cron job Run on every mid night 0 second, 0 minute, 0 hour, day of month 1 to 31, every month, every day(s) of week
//    @Scheduled(cron = "0 0 0 * * *")
  @Scheduled(cron = "0 */5 * ? * *")
    @Async
    public void sendMbrRemindersJob() {
        try {
            log.info("...................Start Mbr Reminder Job..............");
            boolean refillFlag = this.refillReminderService.sendRepeatRefillReminderForMBR(log);
            if (refillFlag) {
                log.info("Mbr Messages send " + refillFlag);
            } else {
                log.info("No Mbr Messages send " + refillFlag);
            }
        } catch (Exception e) {
            log.error("Exception# sendMbrRemindersJob# ", e);
        }
        log.info("...................End Mbr Reminder Job..............");
    }

    public void sendMbrRemindersJob(RefillReminderService refillReminderDAO) {
        this.refillReminderService = refillReminderDAO;
        sendMbrRemindersJob();
    }
}
