package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.CampaignMessages;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EmailSenderUtil;
import com.ssa.cms.util.SMSUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zubair
 */
@Component
public class NotificationRemindersJob {

    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

//    @Scheduled(cron = "0 0 * * * MON-FRI")
    protected void executeInternal() throws Exception {

        List<PharmacyUser> list = consumerRegistrationService.getPharmacyUsersList(DateUtil.getHour(new Date()), DateUtil.dateToString(new Date(), "EEEE"));
        if (list.size() > 0) {
            if (consumerRegistrationService.getOrdersList().size() > 0) {
                for (PharmacyUser pharmacyUser : list) {
                    if (pharmacyUser.getSmsNotify().equalsIgnoreCase("Yes")) {
                        if (pharmacyUser.getPhone() != null) {
                            boolean validatePhoneNo = consumerRegistrationService.validatePhoneNo(pharmacyUser.getPhone());
                            if (validatePhoneNo) {
                                continue;
                            }
                            CampaignMessages campaignMessages = consumerRegistrationService.getMessageType(Constants.Orders_NOTIFICATION_SMS);
                            if (campaignMessages != null) {
                                logger.info("Send SMS.....!");
                                SMSUtil.sendSmsMessage(pharmacyUser.getPhone(), campaignMessages.getSmstext());
                            } else {
                                logger.info("Message is null: " + campaignMessages);
                            }

                        }
                    }
                    if (pharmacyUser.getEmailNotify().equalsIgnoreCase("Yes")) {
                        if (pharmacyUser.getEmail() != null) {
                            CMSEmailContent cMSEmailContent = consumerRegistrationService.getCMSEmailContent(Constants.Orders_NOTIFICATION_EMAIL);
                            if (cMSEmailContent != null) {
                                logger.info("Send Email.....!");
                                EmailSenderUtil.send(pharmacyUser.getEmail(), cMSEmailContent.getSubject(), cMSEmailContent.getEmailBody());
                            } else {
                                logger.info("Email Content is null: " + cMSEmailContent);
                            }
                        }
                    }
                }
            } else {
                logger.info("There is no Un-Assigned Order available in the list.");
            }
        } else {
            logger.info("There is no record found in the Pharmacy User list.");
        }
    }
}
