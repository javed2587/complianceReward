package com.ssa.cms.remainder.refill;

import com.ssa.cms.service.RefillReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class RefillOrderReminderJob {

    @Autowired
    RefillReminderService refillReminderDAO;

//    @Scheduled(cron = "${REFILL_ORDER_REMINDER}")
    protected void executeInternal() {
        try {

            PMSRefillOrderReminder mSRefillOrderReminder = new PMSRefillOrderReminder();
            mSRefillOrderReminder.sendRefillOrderReminder(refillReminderDAO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
