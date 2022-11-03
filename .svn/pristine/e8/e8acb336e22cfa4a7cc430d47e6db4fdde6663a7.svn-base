package com.ssa.cms.util;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;
import com.ssa.cms.common.LakerDailyDataDictionary;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.DataDictionary;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.InstantRedemption;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import org.apache.log4j.Logger;

public class RedemptionUtil {

    private static final Logger log = Logger.getLogger(RedemptionUtil.class);

    public static String MD5(String info) {

        String encryptedInfo = "";
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(info.getBytes(), 0, info.length());
            encryptedInfo = new BigInteger(1, mdEnc.digest()).toString(16);

            if (encryptedInfo.length() != 32) {
                encryptedInfo = "0" + encryptedInfo;
            }
        } catch (Exception e) {
            log.error(e);
        }

        return encryptedInfo;
    }

    public static String getIPAddress() {
        String remoteIp = "127.0.0.1";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            remoteIp = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e);
        }
        return remoteIp;
    }

    public static String formatPhone(String pharmacyPhone) {

        String phone = "";

        try {

            if (pharmacyPhone == null) {
                return phone;
            }

            pharmacyPhone = pharmacyPhone.trim();

            if (pharmacyPhone.length() == 11) {
                pharmacyPhone = pharmacyPhone.substring(1);
            }

            if (pharmacyPhone.length() != 10) {
                return pharmacyPhone;
            }

            String a1 = pharmacyPhone.substring(0, 3);
            String a2 = pharmacyPhone.substring(3, 6);
            String a3 = pharmacyPhone.substring(6, 10);

            phone = a1 + "-" + a2 + "-" + a3;

        } catch (Exception e) {
            phone = pharmacyPhone;
            log.error(e);
        }

        return phone;
    }

    public static Date parseDate(String dateStr) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.EFFECTIVE_DATE_FORMAT);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {

        }

        return date;
    }

    public static Date parseDateShort(String dateStr) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {

        }

        return date;
    }

    public static String formatDateShort(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.DATE_FORMATE_SHORT);
        return simpleDateFomrat.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFomrat = new SimpleDateFormat(Constants.EFFECTIVE_DATE_FORMAT);
        return simpleDateFomrat.format(date);
    }

    public static int getCardholderDOBYear(Date cardholderDOB) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(cardholderDOB);

        int dobYear = calendar.get(Calendar.YEAR);

        return dobYear;
    }

    public static String[] getIRFArrayFromDRFArray(String[] drfArray) {

        String[] irfArray = new String[29];

        irfArray[DataDictionary.FILL_DATE_INDEX] = drfArray[LakerDailyDataDictionary.FILL_DATE_INDEX]; // 
        irfArray[DataDictionary.PHARMACY_NPI_INDEX] = drfArray[LakerDailyDataDictionary.PHARMACY_NPI_INDEX]; //
        irfArray[DataDictionary.PHARMACY_NAME_INDEX] = drfArray[LakerDailyDataDictionary.PHARMACY_NAME_INDEX]; //
        irfArray[DataDictionary.PRESCRIPTION_NUMBER_INDEX] = drfArray[LakerDailyDataDictionary.PRESCRIPTION_NUMBER_INDEX]; //
        irfArray[DataDictionary.CLAIM_STATUS_INDEX] = drfArray[LakerDailyDataDictionary.CLAIM_STATUS_INDEX]; //
        irfArray[DataDictionary.RX_GROUP_NUMBER_INDEX] = drfArray[LakerDailyDataDictionary.RX_GROUP_NUMBER_INDEX]; //
        irfArray[DataDictionary.SUBMITTED_ID_INDEX] = drfArray[LakerDailyDataDictionary.SUBMITTED_ID_INDEX]; //
        irfArray[DataDictionary.CARDHOLDER_FIRST_NAME_INDEX] = drfArray[LakerDailyDataDictionary.CARDHOLDER_FIRST_NAME_INDEX]; //
        irfArray[DataDictionary.CARDHOLDER_LAST_NAME_INDEX] = drfArray[LakerDailyDataDictionary.CARDHOLDER_LAST_NAME_INDEX]; //
        irfArray[DataDictionary.CARDHOLDER_DOB_INDEX] = drfArray[LakerDailyDataDictionary.CARDHOLDER_DOB_INDEX]; //
        irfArray[DataDictionary.CARDHOLDER_GENDER_INDEX] = drfArray[LakerDailyDataDictionary.CARDHOLDER_GENDER_INDEX]; //
        irfArray[DataDictionary.NDC_NUMBER_INDEX] = drfArray[LakerDailyDataDictionary.NDC_NUMBER_INDEX]; //
        irfArray[DataDictionary.FDB_GCN_INDEX] = drfArray[LakerDailyDataDictionary.FDB_GCN_INDEX]; //
        irfArray[DataDictionary.QUANTITY_INDEX] = drfArray[LakerDailyDataDictionary.QUANTITY_INDEX]; //
        irfArray[DataDictionary.DAYS_SUPPLY_INDEX] = drfArray[LakerDailyDataDictionary.DAYS_SUPPLY_INDEX]; //
        irfArray[DataDictionary.REFILLS_USED_INDEX] = drfArray[LakerDailyDataDictionary.REFILLS_USED_INDEX]; //
        irfArray[DataDictionary.TOTAL_DRUG_COST_PAID_TO_PHARMACY_INDEX] = drfArray[LakerDailyDataDictionary.TOTAL_DRUG_COXT_PAID_TO_PHARMACY_INDEX]; //
        irfArray[DataDictionary.TRANSACTION_NUMBER_INDEX] = drfArray[LakerDailyDataDictionary.TRANSACTION_NUMBER_INDEX]; //
        irfArray[DataDictionary.REVERSAL_DATE_INDEX] = drfArray[LakerDailyDataDictionary.REVERSAL_DATE_INDEX]; //
        irfArray[DataDictionary.POSTING_DATE_INDEX] = drfArray[LakerDailyDataDictionary.POSTING_DATE_INDEX]; //
        irfArray[DataDictionary.DEA_NUMBER_INDEX] = drfArray[LakerDailyDataDictionary.DEA_NUMBER_INDEX]; //
        irfArray[DataDictionary.PRESCRIBER_NPI_INDEX] = drfArray[LakerDailyDataDictionary.PRESCRIBER_NPI_INDEX]; //
        irfArray[DataDictionary.PATIENT_OUT_OF_POCKET_INDEX] = drfArray[LakerDailyDataDictionary.INGREDIENT_COST_PAID_INDEX]; //
        irfArray[DataDictionary.PRICE_SOUCE_INDEX] = drfArray[LakerDailyDataDictionary.PRICE_SOURCE_INDEX]; //
        irfArray[DataDictionary.PHARMACY_ZIP_CODE_INDEX] = drfArray[LakerDailyDataDictionary.PHARMACY_ZIP_CODE_INDEX]; //
        irfArray[DataDictionary.SUBMITTED_CARD_HOLDER_FIRST_NAME_INDEX] = drfArray[LakerDailyDataDictionary.SUBMITTED_CARDHODLER_FIRST_NAME_INDEX]; //
        irfArray[DataDictionary.SUBMITTED_CARD_HOLDER_LAST_NAME_INDEX] = drfArray[LakerDailyDataDictionary.SUBMITTED_CARDHODLER_LAST_NAME_INDEX]; //
        irfArray[DataDictionary.SUBMITTED_CARD_HOLDER_DOB_INDEX] = drfArray[LakerDailyDataDictionary.SUBMITTED_CARD_HOLDER_DOB_INDEX]; //
        irfArray[DataDictionary.SUBMITTED_CARD_HOLDER_GENDER_INDEX] = drfArray[LakerDailyDataDictionary.SUBMITTED_CARD_HOLDER_GENDER_INDEX]; //

        return irfArray;

    }

    public static String prepareUnsubscribeURL(String email, String host) {
        email = RedemptionUtil.MD5(email);
        host = RedemptionUtil.MD5(host);
        String actionURL = Constants.APP_PATH + "/PMSGenericEmailFlow?from=" + email + "&host=" + host + "&message=stop";
        log.info("Action URL : " + actionURL);
        return actionURL;
    }

    public static String prepareActionURL(String email, String host) {
        email = RedemptionUtil.MD5(email);
        host = RedemptionUtil.MD5(host);
        String actionURL = Constants.APP_PATH + "/PMSGenericEmailFlow?from=" + email + "&host=" + host + "&message=Yes";
        log.info("Action URL : " + actionURL);
        return actionURL;
    }

    public static String prepareAcceptanceURL(String email, String host) {
        email = RedemptionUtil.MD5(email);
        host = RedemptionUtil.MD5(host);
        String placeOrderURL = Constants.APP_PATH + "/PMSGenericEmailFlow?from=" + email + "&host=" + host + "&message=YES";
        log.info("Place Order URL : " + placeOrderURL);
        return placeOrderURL;
    }

    public static String preparePlaceOrderURL(String transactionNumber) {
        String placeOrderURL = Constants.APP_PATH + "/order/placeorder/" + transactionNumber;
        log.info("Place Order URL : " + placeOrderURL);
        return placeOrderURL;
    }

    public static String prepareOrderStatusURL(String orderId) {
        String orderStatusURL = Constants.APP_PATH + "/order/status/" + orderId;
        String shortenURL = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(orderStatusURL)).getShortUrl();
        log.info("Order Status URL : " + shortenURL);
        return shortenURL;
    }

    public static String prepareSurveyURL(int surveyId, String communicationId) {
        String surveyURL = Constants.APP_PATH + "/survey/takeSurvey/" + surveyId + "/" + communicationId;
        //String shortenURL = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(surveyURL)).getShortUrl();
        log.info("Survey URL : " + surveyURL);
        return surveyURL;
    }

    public static String placeHolders(String emailBody, String unsubscribeUrl, String actionURL) {

        emailBody = emailBody.replace("[_stop_]", unsubscribeUrl);

        if (actionURL != null) {
            emailBody = emailBody.replace("[_clickHere_]", actionURL);
        }
        return emailBody;
    }

    public static String calculateMemberId(InstantRedemption redemptionFile) {

        String memberID = redemptionFile.getSubmittedId();

        int fnameLength = redemptionFile.getCardholderFirstName().length();
        int lnameLength = redemptionFile.getCardholderLastName().length();

        try {
            long phoneNumber = Long.parseLong(redemptionFile.getSubmittedId());
            double memberIdNumeric = (phoneNumber * fnameLength * lnameLength) / 3.1415;
            memberID = Math.round(memberIdNumeric) + "";

        } catch (Exception e) {
            log.error(e);
        }
        log.info("Member Id generated : " + memberID);

        return memberID;
    }

    public static String calculateMemberId(DailyRedemption redemptionFile) {

        String memberID = redemptionFile.getSubmittedId();

        int fnameLength = redemptionFile.getCardholderFirstName().length();
        int lnameLength = redemptionFile.getCardholderLastName().length();

        try {
            long phoneNumber = Long.parseLong(redemptionFile.getSubmittedId());
            double memberIdNumeric = (phoneNumber * fnameLength * lnameLength) / 3.1415;
            memberID = Math.round(memberIdNumeric) + "";

        } catch (Exception e) {
            log.error(e);
        }
        log.info("Member Id generated : " + memberID);

        return memberID;
    }

    public static String preparePatientProfileURL(String communicationId) {
        String patientProfileURL = Constants.APP_PATH + "/patient/registration?from=" + communicationId;
        String shortenURL = as("sshabbir", "R_2b1116cc472218ae6e51ff60d87c338e").call(shorten(patientProfileURL)).getShortUrl();
        log.info("Order Status URL : " + shortenURL);
        return shortenURL;
    }

    public static int getRandomNumber() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));
    }

    public static String getRandomToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        return RedemptionUtil.MD5(Arrays.toString(bytes));
    }

    public static Long generateRandomCardNumber(int prefix) {
        Random rand = new Random();
        long x = (long) (rand.nextDouble() * 100000000000000L);
        String s = String.valueOf(prefix) + String.format("%014d", x);
        return Long.valueOf(s);
    }
        public static Long generateRandomAuthNumber() {
        Random rand = new Random();
        long x = (long) (rand.nextDouble() * 10000000000L);
        String s =  String.format("%011d", x);
        return Long.valueOf(s);
    }
}
