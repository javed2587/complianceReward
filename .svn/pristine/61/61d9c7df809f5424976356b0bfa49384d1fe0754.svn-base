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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Javed.Iqbal
 */
@Component
public class EndOfTheYearJob {
    
    @Autowired
    
    private RefillReminderService refillReminderService;

    //private final Logger log = Logger.getLogger("EndOfTheYearJob");
    private final Log logger = LogFactory.getLog(getClass());

    //This cron job Run on every mid night 0 second, 0 minute, 0 hour, day of month 1 to 31, every month, every day(s) of week
//    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    @Async
    public void processEndOfTheYearJob() throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
//        String date = format.format(new Date());
        boolean endOfYearFlag = this.refillReminderService.sendEndOfTheYearJob();
        if (endOfYearFlag) {
            logger.info("End of The Year Messages send" + endOfYearFlag);
        } else {
            logger.info("End of The Year Messages not sent" + endOfYearFlag);
        }
    }
    
    public void processEndOfTheYearJob(RefillReminderService refillReminderService) {
        try {
            this.refillReminderService = refillReminderService;
            processEndOfTheYearJob();
        } catch (Exception e) {
            logger.error("Exception# processEndOfTheYearJob# ", e);
        }
        
    }
    
}
