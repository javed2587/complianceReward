package com.ssa.cms.remainder;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.service.ConsumerRegistrationService;
import com.ssa.cms.util.EmailSenderUtil;
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
public class ResetPasswordReminder {

    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ConsumerRegistrationService consumerRegistrationService;

//    @Scheduled(cron = "0 */5 * * * *")
    protected void executeInternal() {
        List<PharmacyUser> list = consumerRegistrationService.getPharmacyUsersList();
        if (list.size() > 0) {
            for (PharmacyUser pharmacyUser : list) {
                if (!"".equals(pharmacyUser.getEmail())) {
                    CMSEmailContent cMSEmailContent = consumerRegistrationService.getCMSEmailContent(Constants.PASSWORD_REMINDER);
                    if (cMSEmailContent != null) {
                        consumerRegistrationService.updatePharmacyUsersPasswordDate(pharmacyUser.getEmail());
                        EmailSenderUtil.send(pharmacyUser.getEmail(), cMSEmailContent.getSubject(), cMSEmailContent.getEmailBody());
                    } else {
                        logger.info("Email Content is null: " + cMSEmailContent);
                    }
                }
            }
        } else {
            logger.info("No Pharmacy User found to send Password Reminder !");
        }
    }
}
