///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ssa.cms.service;
//
//import com.ssa.cms.common.Constants;
//import com.ssa.cms.dao.PatientProfileDAO;
//import com.ssa.cms.model.CampaignMessages;
//import com.ssa.cms.model.PatientProfile;
//import com.ssa.cms.util.AppUtil;
//import com.ssa.cms.util.CommonUtil;
//import java.util.Date;
//import java.util.List;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Osman.Khan
// */
//@Service
//@Transactional
//public class MessageService {
//
//    @Autowired
//    private PatientProfileDAO patientProfileDAO;
//
//    @Autowired
//    private PatientProfileService patientProfileService;
//    private final Logger log = Logger.getLogger("MessageService");
//
//    public boolean sendLetsGetStartedMessage(Date date) {
//        boolean isMsgSend = false;
//        try {
//            String eventName = Constants.EVENTNAME;
//            String message = Constants.MSG_WE_MISSED_YOU;
//
//            List<PatientProfile> patientProfileList = patientProfileDAO.getPatientProfileWithNoOrder(date);
//            if (CommonUtil.isNullOrEmpty(patientProfileList)) {
//                log.info("No patient found " + patientProfileList.size()+" (System will return)");
//                return isMsgSend;
//            }
//
//            CampaignMessages campaignMessages = this.patientProfileService.getNotificationMsgs(message, eventName);
//            if (campaignMessages == null || CommonUtil.isNullOrEmpty(campaignMessages.getMessageId())) {
//                log.info("There is no msg found against this event name " + eventName + " and message is " + message+" (System will return)");
//                return isMsgSend;
//            }
//            
//            for (PatientProfile patientProfile : patientProfileList) {
//                campaignMessages.setSmstext(AppUtil.getSafeStr(campaignMessages.getSmstext(), ""));
//                patientProfileService.saveNotificationMessages(campaignMessages, Constants.NO, patientProfile.getPatientProfileSeqNo());
//                isMsgSend = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Exception# sendLetsGetStartedMessage# ", e);
//            isMsgSend = false;
//        }
//        return isMsgSend;
//    }
//}
