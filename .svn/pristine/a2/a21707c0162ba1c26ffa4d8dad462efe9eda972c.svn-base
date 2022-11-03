/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.thread;

import com.ssa.cms.model.Users;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EmailSenderUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adeel.usmani
 */
public class UserLoginEmailThread implements Runnable 
{
    
    private Users users;
    private String password;
    private String url;
    private boolean pharmacyUser;
    
    private String emailAddress;
    private String emailSubject;

    public UserLoginEmailThread( String emailAddress, String emailSubject, 
            String emailMessage,String url, boolean pharmacyUser) 
    {
       
        this.emailAddress = emailAddress;
        this.emailSubject = emailSubject;
        this.emailMessage = emailMessage;
        this.url = url;
        this.pharmacyUser = pharmacyUser;
    }
    private String emailMessage;

    public UserLoginEmailThread(Users users, String password, String url, boolean pharmacyUser) {
        this.users = users;
        this.password = password;
        this.url = url;
        this.pharmacyUser = pharmacyUser;
    }
    
    
    

    @Override
    public void run()
    {
        try {
            if(!this.pharmacyUser)
            {
                this.sendLoginInformation();
            }
            else
            {
                EmailSenderUtil.send(emailAddress, emailSubject, emailMessage);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserLoginEmailThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendLoginInformation() throws Exception {
        String emailBody = "<table width=\"75%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "<tbody>\n"
                + "<tr valign=\"top\">\n"
                + "<td width=\"100%\" style=\"padding-top:10px;color: #000;  font-family:Verdana, Geneva, sans-serif;font-size:12px;\">"
                + "<b>Dear User,</b><br>\n"
                + "<br>Welcome to the API Rx Savings & Support Program!.\n"
                + "<br>\n"
                + "<br>Your account has been created and will be effective on " + DateUtil.dateToString(users.getCreatedOn(), "EEE, MMM dd, yyyy 'at' hh:mm") + " EDT. <br>\n"
                + "<br>Your account details are below.<br>"
                + "<br>&nbsp; &nbsp;Username:&nbsp;<b>" + users.getUserName() + "</b><br>"
                + "&nbsp; &nbsp;Password:&nbsp;<b>" + password + "</b><br>\n"
                + "<br>\n"
                + "<b>Thank you,</b><br>\n"
                + "<br>\n"
                + "The API Direct Team<br>\n"
                + "<br><i>This email was created automatically. Please do not reply to this message.</i>\n"
                + "\n</td></tr></tbody></table>";
        EmailSenderUtil.send(users.getEmailAddress(), "API Direct Savings & Support Program | User Account Created", emailBody);

    }

    
}
