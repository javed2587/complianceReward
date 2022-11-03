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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mzubair
 */
@Component
public class AnnualStatementJob {

    private static final Logger logger = Logger.getLogger(AnnualStatementJob.class);
    @Autowired
    private RefillReminderService refillReminderService;

    //This cron job Run on every mid night 0 second, 0 minute, 0 hour, day of month 1 to 31, every month, every day(s) of week
    //@Scheduled(cron = "*/10 * * * * *")
    @Transactional
    @Async
    public void processAnnualStatements() {
        if (refillReminderService.sendAnnualStatements()) {
            logger.info("Send Annual Statements.");
        } else {
            logger.info("Problem to send Annual Statements");
        }
    }

    public void processAnnualStatements(RefillReminderService refillReminderService) {
        this.refillReminderService = refillReminderService;
        processAnnualStatements();
    }
}
