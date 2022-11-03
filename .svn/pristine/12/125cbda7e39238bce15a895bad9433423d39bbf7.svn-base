///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ssa.cms.remainder;
//
//import com.ssa.cms.common.Constants;
////import com.ssa.cms.service.MessageService;
//import com.ssa.cms.util.DateUtil;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Osman.Khan
// */
//@Component
//public class LetsGetStartedJob {
//
//    @Autowired
//    private MessageService messgaeService;
//    private final Logger log = Logger.getLogger("LetsGetStartedJob");
//
//    //Run on every year, every month, every week, every day, every hour on 0 mintutes and 0 second
//    @Scheduled(cron = "0 0 0 * * ?")
//    @Transactional
//    @Async
//    public void processLetsGetStartedMessage() {
//        try {
//            boolean refillFlag = this.messgaeService.sendLetsGetStartedMessage(DateUtil.getOneWeekBeforeDate());
//            if (refillFlag) {
//                log.info(Constants.MSG_WE_MISSED_YOU + " messages send " + refillFlag);
//            } else {
//                log.info(Constants.MSG_WE_MISSED_YOU + " messages send fail " + refillFlag);
//            }
//        } catch (Exception e) {
//            log.error("Exception# processLetsGetStartedMessage# ", e);
//        }
//
//    }
//
//    public void processLetsGetStartedMessage(MessageService messgaeService) {
//        this.messgaeService = messgaeService;
//        processLetsGetStartedMessage();
//    }
//
//}
