package com.ssa.cms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.SmtpServerInfo;
import com.sun.mail.smtp.SMTPTransport;

public class EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    public static boolean send(String toEmail, String subject, String body) {
        String port=PropertiesUtil.getProperty("SMTP_PORT");
        System.out.println("SMTP PORT "+port);
        boolean flag = true;
        Properties props = new Properties();

        props.put("mail.smtp.host", Constants.SMTP_HOST_API);
        props.put("mail.smtp.port",port);//Constants.SMTP_PORT_587);// Constants.SMTP_PORT);//_587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", Constants.SMTP_FROM_EMAIL);
        props.put("mail.smtp.starttls.enable", "true");
        /*Session session = Session.getDefaultInstance(props, null);*/
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.SMTP_FROM_EMAIL, Constants.SMTP_KEY);
                
            }
        });
        
        session.setDebug(true);
        ArrayList<String> toList = new ArrayList<>();
        toList.add(toEmail);
        ArrayList<String> ccList = new ArrayList<>();

        try {

            body = body.replace("Â", "");
            body = body.replace("Ã", "");

            Message message = new MimeMessage(session);
            InternetAddress fromAddress = new InternetAddress(Constants.SMTP_FROM_EMAIL, Constants.SMTP_FROM_NAME);

            String toAddress = toList.toString();
            String ccAddress = ccList.toString();

            for (String to : toList) {
                toAddress += String.valueOf(to);
                toAddress += ",";
            }

            for (String cc : ccList) {
                ccAddress += String.valueOf(cc);
                ccAddress += ",";
            }

            message.setSubject(subject);
            message.setFrom(fromAddress);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddress));

            message.setContent(body, "text/html; charset=utf-8");
            SMTPTransport tr = (SMTPTransport) session.getTransport("smtp");

            try {
            	   
                try {
					//tr.connect(Constants.SMTP_HOST_API, Constants.SMTP_FROM_EMAIL, Constants.SMTP_KEY);
                                        tr.connect(Constants.SMTP_HOST_API, Constants.SMTP_USER_NAME, Constants.SMTP_KEY);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                message.saveChanges();
                tr.sendMessage(message, message.getAllRecipients());
            	
              

            } catch (AuthenticationFailedException | NoSuchProviderException afe) {
                logger.error("Exception: EmailSender -> send", afe);
                return false;
            } finally {
                tr.close();
            }
        } catch (UnsupportedEncodingException | MessagingException e) {
            logger.error("Exception: EmailSender -> send", e);
            return false;
        }
        return flag;

    }

    public static boolean sendTest(String toEmail, String subject, String body, SmtpServerInfo setupVO) {
        boolean flag = true;
        Properties props = new Properties();
        props.put(setupVO.getOutGoingSmtp(), "true");
        Session session = Session.getInstance(props, null);
        session.setDebug(true);
        ArrayList<String> toList = new ArrayList<>();
        toList.add(toEmail);
        ArrayList<String> ccList = new ArrayList<>();
        try {

            body = body.replace("Â", "");
            body = body.replace("Ã", "");

            Message message = new MimeMessage(session);
            InternetAddress fromAddress = new InternetAddress(setupVO.getFromEmail());
            //fromAddress.setPersonal("RxCDI");

            String toAddress = toList.toString();
            String ccAddress = ccList.toString();

            for (String to : toList) {
                toAddress += String.valueOf(to);
                toAddress += ",";
            }

            for (String cc : ccList) {
                ccAddress += String.valueOf(cc);
                ccAddress += ",";
            }

            message.setSubject(subject);
            message.setFrom(fromAddress);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddress));

            message.setContent(body, "text/html; charset=utf-8");
            Transport tr = session.getTransport("smtp");

            try {
                tr.connect(setupVO.getOutGoingSmtp(), setupVO.getSmtpUserName(), setupVO.getSmtpPassword());
                message.saveChanges();
                tr.sendMessage(message, message.getAllRecipients());

            } catch (AuthenticationFailedException | NoSuchProviderException afe) {
                logger.error("Exception: EmailSender -> sendTest", afe);
                return false;
            } finally {
                tr.close();
            }
        } catch (Exception e) {
            logger.error("Exception: EmailSender -> sendTest", e);
            return false;
        }
        return flag;
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static boolean sendEmail(String to, String subject, String body, SmtpServerInfo smtpServerInfo, Campaigns campaigns) {
        //String emailBody = makeEmailBody(body);
        return sendEmail(to, null, null, subject, body, smtpServerInfo, campaigns);
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static boolean sendEmail(String to, String cc, String subject, String body, SmtpServerInfo smtpServerInfo, Campaigns campaigns) {
        return sendEmail(to, cc, null, subject, body, smtpServerInfo, campaigns);
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static boolean sendEmail(String to, String cc, String bcc, String subject, String body, SmtpServerInfo smtpServerInfo, Campaigns campaign) {
        boolean flag = true;

        String host = smtpServerInfo.getOutGoingSmtp();
        String port = smtpServerInfo.getSmtpPort();
        String from = smtpServerInfo.getFromEmail();
        String fromName = smtpServerInfo.getFromName();
        String password = smtpServerInfo.getSmtpPassword();

        logger.info("SMTP Host : " + host);
        logger.info("SMTP Port : " + port);
        logger.info("From Email : " + from);

        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", from);

        try {

            body = body.replace("Â", "");
            body = body.replace("Ã", "");

            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(false);

            MimeMessage message = new MimeMessage(session);

            InternetAddress addressFrom = new InternetAddress(from, fromName);
            message.setFrom(addressFrom);

            InternetAddress[] toAddresses = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, toAddresses);

            if (cc != null && cc.trim().length() > 0) {
                InternetAddress[] ccAddress = InternetAddress.parse(cc);
                message.setRecipients(Message.RecipientType.CC, ccAddress);
            }

            if (bcc != null && bcc.trim().length() > 0) {
                InternetAddress[] bccAddress = InternetAddress.parse(bcc);
                message.setRecipients(Message.RecipientType.BCC, bccAddress);
            }

            message.setSentDate(new Date());
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=utf-8");

            multipart.addBodyPart(messageBodyPart);

            if (campaign != null) {
                byte[] logoBytes = campaign.getCampaignLogo();

                if (logoBytes != null) {

                    String logoPath = Constants.LOGO_DIR + campaign.getCampaignName() + "." + campaign.getCampaignLogoType();

                    File logoFile = new File(logoPath);

                    if (logoFile.exists()) {
                        boolean deleteFile = logoFile.delete();
                        logger.info("Existing logo file delete flag : " + deleteFile);
                    }

                    FileOutputStream logoFileOutputStream = new FileOutputStream(logoFile);
                    logoFileOutputStream.write(logoBytes);
                    logoFileOutputStream.close();
                }
            }

            if (body.contains("cid:RxDirect_Logo")) {
                messageBodyPart = new MimeBodyPart();
                DataSource source2 = new FileDataSource(new File(Constants.IMAGE_PATH + "/RxDirect_Logo.png"));
                messageBodyPart.setDataHandler(new DataHandler(source2));
                messageBodyPart.setFileName("YES");
                messageBodyPart.addHeader("Content-ID", "<RxDirect_Logo>");
                multipart.addBodyPart(messageBodyPart);
            }

            if (body.contains("cid:completeBtn")) {
                messageBodyPart = new MimeBodyPart();
                DataSource source2 = new FileDataSource(new File(Constants.IMAGE_PATH + "/completeBtn.png"));
                messageBodyPart.setDataHandler(new DataHandler(source2));
                messageBodyPart.setFileName("YES");
                messageBodyPart.addHeader("Content-ID", "<completeBtn>");
                multipart.addBodyPart(messageBodyPart);
            }

            if (body.contains("cid:rightline")) {
                messageBodyPart = new MimeBodyPart();
                DataSource source2 = new FileDataSource(new File(Constants.IMAGE_PATH + "/rightline.jpg"));
                messageBodyPart.setDataHandler(new DataHandler(source2));
                messageBodyPart.setFileName("YES");
                messageBodyPart.addHeader("Content-ID", "<rightline>");
                multipart.addBodyPart(messageBodyPart);
            }

            if (body.contains("cid:separator")) {
                messageBodyPart = new MimeBodyPart();
                DataSource source2 = new FileDataSource(new File(Constants.IMAGE_PATH + "/separator.png"));
                messageBodyPart.setDataHandler(new DataHandler(source2));
                messageBodyPart.setFileName("YES");
                messageBodyPart.addHeader("Content-ID", "<separator>");
                multipart.addBodyPart(messageBodyPart);
            }

            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setContent(multipart);

            Transport tr = session.getTransport("smtp");
            tr.connect(host, from, password);

            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();

        } catch (IOException | MessagingException e) {
            flag = false;
            logger.error("Exception: EmailSender -> sendEmail", e);
        }

        return flag;

    }

    public static String makeEmailBody(String eBody) {
        String emailBody = "<html>"
                + "<body>"
                + "<table style='width:100%;'>"
                + "<thead>"
                + "     <tr>"
                + "         <th style='text-align: left; padding-bottom: 4px;'>"
                + "             <img src='cid:RxDirect_Logo'><div/><div/>"
                + "         </th>"
                + "         <th style='text-align: right; font-weight: normal; padding-bottom: 4px; color: rgb(32,113,182);'>"
                + "              <b>Patient Support:</b><br/>"
                + "              1-800-581-9085<br/>"
                + "              <a href='#'>www.API-Direct.com</a>"
                + "         </th>"
                + "     </tr>"
                + "</thead>"
                + "<tbody>"
                + "<tr>"
                + "     <td colspan='2' style='background-color:#F7F7F7; padding: 5px 10px 5px 10px; border-top: 2px solid #2071b6; border-bottom: 2px solid #2071b6;'>"
                + eBody
                + "     </td>"
                + "</tr>"
                + "</tbody>"
                + "<tfoot>"
                + "<tr>"
                + "         <td colspan='2'>"
                + "              <i style='font-size:11px;'>"
                + "                  You are receiving this email because you have opted to receive emails regarding the API Direct program.  If you no longer wish to receive these communications, you can <a href='_stop_'>unsubscribe.</a><br/>"
                + "                  Please add noreply@api-direct.com to your address book or safe sender list so our emails get to your inbox.  This message was sent on behalf of American Pharmaceutical Ingredients (API), 4800 North Federal Highway - Suite A 302 , Boca Raton, Florida, 33431."
                + "              </i>"
                + "          </td>"
                + "</tr>"
                + "</tfoot>"
                + "</table>"
                + "</body>"
                + "</html>";
        return emailBody;
    }
}
