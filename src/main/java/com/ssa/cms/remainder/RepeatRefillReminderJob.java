/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.service.RefillReminderService;
import com.ssa.cms.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mzubair
 * @author (CR) Javed Iqbal
 */
@Component
public class RepeatRefillReminderJob {

    @Autowired
    private RefillReminderService refillReminderService;

    private final Logger log = Logger.getLogger("RepeatRefillReminderJob");
    private final Logger success = Logger.getLogger("successRefillReminder");
    private final Logger failed = Logger.getLogger("failedRefillReminder");

    //@Scheduled(cron = "0 0 * * * *")
    @Transactional
    @Async
    public void sendRefillReminder() {
        try {
            log.info("...................Start Next Refill Reminder Job..............");
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
            String date = format.format(new Date());
            boolean refillFlag = this.refillReminderService.sendNextRefillReminder(DateUtil.stringToDate(date, Constants.DATE_FORMATE_SHORT), log, success, failed);
            if (refillFlag) {
                log.info("Refill Messages send" + refillFlag);
            } else {
                log.info("No Refill Messages send" + refillFlag);
            }
        } catch (Exception e) {
            log.error("sendRefillReminder", e);
        }
    }

}
