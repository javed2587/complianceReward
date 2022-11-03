/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.delegate.OrderService;
import com.ssa.cms.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;
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
public class AutoDeletionOrderJob {
    
    @Autowired
    private OrderService orderService;

//    private final Logger log = Logger.getLogger("AutoDeletionOrderJob");
    private final Logger successed = Logger.getLogger("successfullyDeleteOrder");
    private final Logger failed = Logger.getLogger("failedDeleteOrder");

    //This cron job Run on every morning at 6, 0 second, 0 minute, 0 hour, on every day(s) 
    //@Scheduled(cron = "02 06 * * * *")
    @Transactional
    @Async
    public void autoDeleteOrder() throws Exception {
        
        Date crruntdate = new Date();
        String startProcessTime = DateUtil.dateToString(crruntdate, Constants.DATE_TIME_FORMAT);
        successed.info("Starting time of AutoDeletionOrderJob is :" + startProcessTime);
        orderService.autoDeletOrder(successed, failed);
        Date crruntdateTime = new Date();
        String endProcessTime = DateUtil.dateToString(crruntdateTime, Constants.DATE_TIME_FORMAT);
        successed.info("Ending time of AutoDeletionOrderJob is :" + endProcessTime);
        
    }
//    public static void main(String[] args) {
//        AutoDeletionOrderJob abc = new AutoDeletionOrderJob();
//        try {
//            abc.autoDeleteOrder();
//        } catch (Exception ex) {
//            java.util.logging.Logger.getLogger(AutoDeletionOrderJob.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void autoDeleteOrder(OrderService orderService) {
        try {
            this.orderService = orderService;
            autoDeleteOrder();
        } catch (Exception e) {
            failed.error("Exception# autoDeleteOrder# ", e);
        }
        
    }
}
