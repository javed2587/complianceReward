package com.ssa.cms.remainder.refill;

import com.ssa.cms.service.RefillReminderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class AutoRefillFailureJob {

    @Autowired
    RefillReminderService refillReminderService;

    private final Log logger = LogFactory.getLog(getClass());
    
//    @Scheduled(cron = "${AUTO_REFILL_FAILURE_CRON}")
    protected void executeInternal() {
        try {
            
            PMSAutoRefillFailure autoRefillFailure = new PMSAutoRefillFailure();
            autoRefillFailure.sendRefillFailure(refillReminderService);

        } catch (Exception e) {
            logger.error("Exception: AutoRefillFailureJob -> executeInternal", e);
            
        }
    }
}
