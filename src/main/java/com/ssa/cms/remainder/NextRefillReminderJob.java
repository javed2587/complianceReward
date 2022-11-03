/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javed Iqbal
 */
@Component
public class NextRefillReminderJob {

    @Autowired
    private RefillReminderService refillReminderService;

    private final Logger log = Logger.getLogger("NextRefillReminderJob");

    private final Logger success = Logger.getLogger("successRefillReminder");
    private final Logger failed = Logger.getLogger("failedRefillReminder");
    //This cron job Run on Every day at noon - 12pm
//    @Scheduled(cron = "0 0 12 * * ?")
    //This cron job Run on every mid night 0 second, 0 minute, 0 hour, day of month 1 to 31, every month, every day(s) of week
//    @Scheduled(cron = "0 0 0 * * *")
    //This cron job run on every ten seconds
//    @Scheduled(cron = "*/30 * * * * *")
//    @Transactional
//    This cron job run on 5 minutes
    @Scheduled(cron = "0 */5 * ? * *")
    @Async
    public void sendNextRefillReminderJob() {
        try {
            log.info("...................Start Next Refill Reminder Job..............");
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
            String date = format.format(new Date());
            boolean refillFlag = this.refillReminderService.sendNextRefillReminder(DateUtil.stringToDate(date, Constants.DATE_FORMATE_SHORT), log, success, failed);
            if (refillFlag) {
                log.info("Refill Messages send " + refillFlag);
            } else {
                log.info("No Refill Messages send " + refillFlag);
            }
        } catch (ParseException e) {
            log.error("Exception# sendNextRefillReminderJob# ", e);
        }
        log.info("...................End Next Refill Reminder Job..............");
    }

    public void sendNextRefillReminderJob(RefillReminderService refillReminderDAO) {
        this.refillReminderService = refillReminderDAO;
        sendNextRefillReminderJob();
    }

}
