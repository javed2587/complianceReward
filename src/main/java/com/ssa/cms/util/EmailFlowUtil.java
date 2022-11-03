/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;
import com.ssa.cms.model.CampaignMessagesResponse;
import com.ssa.cms.model.Campaigns;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.service.RedemptionService;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author SSASOFTDHA
 */
public class EmailFlowUtil {

    private static final Log logger = LogFactory.getLog(EmailFlowUtil.class);

    public static boolean isHierarchyEnd(CampaignMessagesResponse campaignMessageResponses) {
        boolean flag = false;

        try {
            if (campaignMessageResponses == null) {
                flag = true;
            } else {

                Integer nmtId = campaignMessageResponses.getNextMessage();
                if (nmtId == null || nmtId.intValue() == 0) {
                    flag = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static final String prepareMessage(Double copay, BigDecimal outOfPocket, String pharmacyName,
            String pharmacyPhone, String messageText,
            String prescriptionNumber, Integer remainingRefill,
            String acceptanceUrl, Double payment, String unsubscribeUrl,
            String actionURL, String placeOrderUrl, String orderStatusUrl,
            String cardType, String cardNumber, String surveyUrl) {

        DecimalFormat df = new DecimalFormat("#.00");

        if (copay != null) {
            messageText = messageText.replace("ZZ.ZZ", df.format(copay));
        }

        if (payment != null) {
            messageText = messageText.replace("WW.WW", df.format(payment));
        }

        if (outOfPocket != null) {
            messageText = messageText.replace("XX.XX", df.format(outOfPocket.doubleValue()));
        }

        if (pharmacyName != null) {
            messageText = messageText.replaceAll("PHARMACY_NAME", pharmacyName);
        }

        if (pharmacyPhone != null) {
            String formatedPharmacyPhone = RedemptionUtil.formatPhone(pharmacyPhone);
            messageText = messageText.replaceAll("###-###-####", formatedPharmacyPhone);
        }

        if (prescriptionNumber != null) {
            messageText = messageText.replaceAll("RX_NO", prescriptionNumber);
        }

        if (remainingRefill != null) {
            messageText = messageText.replaceAll("YY", remainingRefill.toString());
        }

        if (acceptanceUrl != null) {
            messageText = messageText.replaceAll("_acceptoffer_", acceptanceUrl);
        }

        if (placeOrderUrl != null) {
            messageText = messageText.replaceAll("_placeorder_", placeOrderUrl);
        }

        messageText = messageText.replace("_stop_", unsubscribeUrl);

        if (actionURL != null) {
            messageText = messageText.replace("_optin_", actionURL);
        }

        if (orderStatusUrl != null) {
            logger.info("Before Uri: " + orderStatusUrl);
            try {
                orderStatusUrl = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(orderStatusUrl)).getShortUrl();
            } catch (Exception ex) {
                logger.error(ex.getStackTrace());
            }
            logger.info("Shortened Uri: " + orderStatusUrl);
            messageText = messageText.replace("_orderstatus_", orderStatusUrl);
        }

        if (cardType != null) {
            messageText = messageText.replace("CARD_TYPE", cardType);
        }

        if (cardNumber != null) {
            messageText = messageText.replace("NNNN", cardNumber.substring(cardNumber.length() - 4));
        }

        if (surveyUrl != null) {
            messageText = messageText.replace("_surveyurl_", surveyUrl);
        }
        return messageText;
    }

    public static void writeResponseHTML(HttpServletResponse response, String message) {

        try {
            String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                    + "<title>Rx Saving & Support Program!</title>\n"
                    + "</head>\n"
                    + "<body alink=\"blue\" vlink=\"blue\" link=\"blue\">\n"
                    + "<div id=\"content\" align=\"center\">\n"
                    + "<div style=\"width: 894px; margin: 10px 0;\" align=\"left\"></div>\n"
                    + "<div style=\"width: 894px; background-color: #EEEEEE; height: 2px; margin-top: 10px; margin-bottom: 20px;\"></div>\n"
                    + "<div class=\"terms\" style=\"width: 894px; border: 1px solid rgb(181,181,181); -moz-box-shadow: inset 0 0 17px 8px #DDDDDD; -webkit-box-shadow: inset 0 0 17px 8px #DDDDDD; box-shadow: inset 0 0 17px 8px #DDDDDD;\">\n"
                    + "<div style=\"height: 425px;overflow:scroll;\">\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<h3 style='color: rgb(66,113,182);'>\n"
                    + "[_message_]"
                    + "</h3>\n"
                    + "\n"
                    + "\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</body>\n"
                    + "</html>";

            html = html.replace("[_message_]", message);
            PrintWriter out = response.getWriter();

            out.write(html);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static final String prepareOrderPlaceHolder(String messageText, String cardholderName, String address1, String address2, String city, String state, String zip) {
        if (cardholderName != null) {
            messageText = messageText.replaceAll("Cardholder Name", cardholderName);
        }
        if (address1 != null) {
            messageText = messageText.replaceAll("Address Line1", address1);
        }
        if (address2 != null) {
            messageText = messageText.replaceAll("Address Line2", address2);
        }
        if (city != null) {
            messageText = messageText.replaceAll("City", city);
        }
        if (state != null) {
            messageText = messageText.replaceAll("State", state);
        }
        if (zip != null) {
            messageText = messageText.replaceAll("Zip", zip);
        }
        return messageText;
    }
}
