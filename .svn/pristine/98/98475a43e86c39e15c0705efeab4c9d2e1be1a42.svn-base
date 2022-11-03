package com.ssa.cms.remainder;

/**
 *
 * @author Javed Iqbal
 */
import com.ssa.cms.service.RefillReminderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NextRefillReminderRepeatJob {

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
    public void sendNextRefillAfterTwoFifthAndTenthDaysReminder() {
 
        try {
             boolean isSend = refillReminderService.sendRepeatRefillReminder(log, success, failed);
            if(isSend){
                log.info("Refill Messages send " + isSend);
            } else {
                log.info("No Refill Messages send " + isSend);
            }
        } catch (Exception e) {
                log.error("Exception# sendNextRefillReminderJob# ", e);
        }
        log.info("...................End Next Refill Reminder Job..............");
         
          
    }

}
