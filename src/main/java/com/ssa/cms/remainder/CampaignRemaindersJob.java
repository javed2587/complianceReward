package com.ssa.cms.remainder;

import com.ssa.cms.service.PMSEmailFlowService;
import com.ssa.cms.service.PMSTextFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class CampaignRemaindersJob {

    @Autowired
    PMSTextFlowService textFlowDAO;

    @Autowired
    PMSEmailFlowService emailFlowDAO;

//    @Scheduled(cron = "${REMINDERS_CRON}")
    protected void executeInternal() {

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("PMS Campaigns reminders started");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        try {

            PMSGenericTextFlowReminder pmsGenericTextFlowReminder = new PMSGenericTextFlowReminder();

            for (int i = 0; i < 5; i++) {
                pmsGenericTextFlowReminder.processTextFlowReminder(i, textFlowDAO);
            }

            System.out.println("******************************************************************************");
            System.out.println("Text flow WM and TCM etc completed");
            System.out.println("******************************************************************************");

            PMSGenericCouponReminder couponReminder = new PMSGenericCouponReminder();
            couponReminder.processCouponReminder(textFlowDAO);

            System.out.println("******************************************************************************");
            System.out.println("Text flow coupon reminder completed");
            System.out.println("******************************************************************************");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("PMS Campaigns reminders completed");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

}
