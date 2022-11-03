package com.ssa.cms.service;

import com.ssa.cms.enumeration.StatusEnum;
import com.ssa.cms.model.GatewayInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.log4j.Logger;

public class GatewayService {

    private static final Logger log = Logger.getLogger(GatewayService.class);
    private String gatewayURL = "";

    public GatewayService(String gatewayURL) {
        this.gatewayURL = gatewayURL;
    }

    public boolean informToGatewayForAnonymousYES(String phoneNumber, String keyword, String forwardToCampaign, String source) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        dataPost.append(URLEncoder.encode("xml", "UTF-8") + "=" + URLEncoder.encode(getXml(phoneNumber, keyword), "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("forwardToCampaign", "UTF-8") + "=" + URLEncoder.encode(forwardToCampaign, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8"));

        System.err.println("Final Data Post For Gate Keeper " + dataPost);

        try {
            URL url = new URL(gatewayURL);
            log.info("Gateway URL : " + gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            // Getting Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            log.info("Response String : " + responseString);

            wr.close();
            rd.close();

            System.err.println("Gateway Response : " + responseString);

        } catch (Exception e) {
            flag = false;
            log.error("Exception: GatewayService -> informToGatewayForAnonymousYES", e);
        }

        return flag;
    }

    public boolean initiateTextFlowFromWidget(String phoneNumber, String keyword, String forwardToCampaign, String source, String requestData) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        dataPost.append(URLEncoder.encode("xml", "UTF-8") + "=" + URLEncoder.encode(getXml(phoneNumber, keyword), "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("forwardToCampaign", "UTF-8") + "=" + URLEncoder.encode(forwardToCampaign, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8"));
        dataPost.append("&");
        dataPost.append(requestData);

        System.err.println("Final Data Post For Gateway " + dataPost);

        try {
            URL url = new URL(gatewayURL);
            log.info("Gateway URL : " + gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            // Getting Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            log.info("Response String : " + responseString);

            wr.close();
            rd.close();

            System.err.println("Gateway Response : " + responseString);

        } catch (Exception e) {
            flag = false;
            log.error("Exception: GatewayService -> initiateTextFlowFromWidget", e);
        }

        return flag;
    }

    public boolean initiateTextFlowFromIVR(String phoneNumber, String keyword, String forwardToCampaign, String source, String requestData, String ivrId, String ivrPath) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        dataPost.append(URLEncoder.encode("xml", "UTF-8") + "=" + URLEncoder.encode(getXml(phoneNumber, keyword), "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("forwardToCampaign", "UTF-8") + "=" + URLEncoder.encode(forwardToCampaign, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("ivrPath", "UTF-8") + "=" + URLEncoder.encode(ivrPath, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("ivrId", "UTF-8") + "=" + URLEncoder.encode(ivrId, "UTF-8"));
        dataPost.append("&");
        dataPost.append(requestData);

        System.err.println("Final Data Post For Gateway " + dataPost);

        try {
            URL url = new URL(gatewayURL);
            log.info("Gateway URL : " + gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            // Getting Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            log.info("Response String : " + responseString);

            wr.close();
            rd.close();

            System.err.println("Gateway Response : " + responseString);

        } catch (Exception e) {
            flag = false;
            log.error("Exception: GatewayService -> initiateTextFlowFromIVR", e);
        }

        return flag;
    }

    public boolean initiateEmailtFlowFromWidget(String email, String keyword, String source, String requestData) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        dataPost.append(URLEncoder.encode("from", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8"));
        dataPost.append("&");
        dataPost.append(requestData);

        System.err.println("Final Data Post For Gateway " + dataPost);

        try {
            URL url = new URL(gatewayURL);
            log.info("Gateway URL : " + gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            // Getting Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            log.info("Response String : " + responseString);

            wr.close();
            rd.close();

            System.err.println("Gateway Response : " + responseString);

        } catch (Exception e) {
            flag = false;
            log.error("Exception: GatewayService -> initiateEmailtFlowFromWidget", e);
        }

        return flag;
    }

    public boolean updateCampaignStatus(String phoneNumber, StatusEnum statusEnum, String programCode, String secKey) {

        boolean flag = false;

        if (phoneNumber.trim().length() == 12) {
            phoneNumber = phoneNumber.substring(1);
        } else if (phoneNumber.trim().length() == 10) {
            phoneNumber = "1" + phoneNumber;
        }

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        try {
            dataPost.append(URLEncoder.encode("from", "UTF-8") + "=" + URLEncoder.encode(phoneNumber, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(statusEnum.getValue(), "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("programCode", "UTF-8") + "=" + URLEncoder.encode(programCode, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("sec", "UTF-8") + "=" + URLEncoder.encode(secKey, "UTF-8"));

            URL url = new URL(this.gatewayURL);

            log.info("Gatekeeper URL : " + this.gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            log.info("Final Data Post For Update Status : " + dataPost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            if (responseString != null && responseString.trim().equals("true")) {
                flag = true;
            }

            wr.close();
            rd.close();

            log.info("Response of Status Update from Gatekeeper : " + responseString);
        } catch (Exception e) {
            log.error("Exception: GatewayService -> updateCampaignStatus", e);
            flag = false;
        }

        return flag;
    }

    public boolean webEnabledQuery(String phoneNumber, String appName, long inputReferenceNumber, GatewayInfo gatewayInfo) {

        boolean flag = false;

        if (phoneNumber.trim().length() == 11) {
            phoneNumber = phoneNumber.substring(1);
        }

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        try {
            dataPost.append(URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode(phoneNumber, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("appCode", "UTF-8") + "=" + URLEncoder.encode(gatewayInfo.getAppCode(), "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("appName", "UTF-8") + "=" + URLEncoder.encode(appName, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("sec", "UTF-8") + "=" + URLEncoder.encode(gatewayInfo.getSecKey(), "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("inputReferenceNumber", "UTF-8") + "=" + URLEncoder.encode(Long.toString(inputReferenceNumber), "UTF-8"));

            URL url = new URL(this.gatewayURL);

            log.info("Web Query URL : " + this.gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            log.info("Final Data Post For Update Status : " + dataPost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            log.info("Web Enabled query response : " + responseString);

            if (responseString != null && responseString.trim().equals("true")) {
                flag = true;
            }

            wr.close();
            rd.close();

        } catch (Exception e) {
            log.error("Exception: GatewayService -> webEnabledQuery", e);
            flag = false;
        }

        return flag;
    }

    public boolean initiateTextFlowFromAngel(String phoneNumber, String keyword, String source, String cardNumber) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        dataPost.append(URLEncoder.encode("xml", "UTF-8") + "=" + URLEncoder.encode(getXml55065(phoneNumber, keyword), "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("source", "UTF-8") + "=" + URLEncoder.encode(source, "UTF-8"));
        dataPost.append("&");
        dataPost.append(URLEncoder.encode("cardNumber", "UTF-8") + "=" + URLEncoder.encode(cardNumber, "UTF-8"));

        log.info("Final Data Post For Gateway " + dataPost);

        try {
            URL url = new URL(gatewayURL);
            log.info("Gateway URL : " + gatewayURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            wr.close();
            rd.close();

            log.info("Gateway Response : " + responseString);

        } catch (IOException e) {
            flag = false;
            log.error("Exception: GatewayService -> initiateTextFlowFromAngel", e);
        }

        return flag;
    }

    public static void main(String[] args) {

        try {
            GatewayInfo info = new GatewayInfo();

            info.setAppCode("test");
            info.setSecKey("kahgfkdjgfksjgafhgsfksagfwoZXCzxcZczCeuirwyoqyruwzNBZXCMBNC5435435434435433454354353asdfdffsfasdfasdfsa");

            GatewayService service = new GatewayService("http://www.text2rx.com:8088/One2OneCampaignOpenMarket37500/WebEnabledQuery37500");
            boolean result = service.webEnabledQuery("2487368163", "Test App", 0, info);

            log.info("Result : " + result);

        } catch (Exception e) {
            log.error("Exception: GatewayService -> main", e);
        }
    }

    private String getXml(String phoneNumber, String keyword) {
        String xml = "";
        xml += "<?xml version=\"1.0\" ?>";
        xml += "<request version=\"3.0\" protocol=\"wmp\" type=\"deliver\">";
        xml += "<account id=\"916-122-267-40492\" />";
        xml += "<destination ton=\"3\" address=\"37500\" />";
        xml += "<source carrier=\"383\" ton=\"0\" address=\"+1" + phoneNumber + "\" />";
        xml += "<option datacoding=\"7bit\" />";
        xml += "<message udhi=\"false\" data=\"" + getHex(keyword) + "\" />";
        xml += "<ticket id=\"HORIZON-REDEMP-T2011\" />";
        xml += "</request>";
        return xml;
    }

    private String getXml21200(String phoneNumber, String keyword) {
        String xml = "";
        xml += "<?xml version=\"1.0\" ?>";
        xml += "<request version=\"3.0\" protocol=\"wmp\" type=\"deliver\">";
        xml += "<account id=\"916-122-267-40492\" />";
        xml += "<destination ton=\"3\" address=\"21200\" />";
        xml += "<source carrier=\"383\" ton=\"0\" address=\"+1" + phoneNumber + "\" />";
        xml += "<option datacoding=\"7bit\" />";
        xml += "<message udhi=\"false\" data=\"" + getHex(keyword) + "\" />";
        xml += "<ticket id=\"HORIZON-REDEMP-T2011\" />";
        xml += "</request>";
        return xml;
    }

    private String getXml55065(String phoneNumber, String keyword) {
        String xml = "";
        xml += "<?xml version=\"1.0\" ?>";
        xml += "<request version=\"3.0\" protocol=\"wmp\" type=\"deliver\">";
        xml += "<account id=\"916-122-267-40492\" />";
        xml += "<destination ton=\"3\" address=\"55065\" />";
        xml += "<source carrier=\"383\" ton=\"0\" address=\"+1" + phoneNumber + "\" />";
        xml += "<option datacoding=\"7bit\" />";
        xml += "<message udhi=\"false\" data=\"" + getHex(keyword) + "\" />";
        xml += "<ticket id=\"HORIZON-REDEMP-T2011\" />";
        xml += "</request>";
        return xml;
    }

    private String getHex(String keyword) {
        StringBuffer buffer = new StringBuffer();
        int intValue;
        for (int x = 0; x < keyword.length(); x++) {
            int cursor = 0;
            intValue = keyword.charAt(x);
            String binaryChar = new String(Integer.toBinaryString(keyword.charAt(x)));
            for (int i = 0; i < binaryChar.length(); i++) {
                if (binaryChar.charAt(i) == '1') {
                    cursor += 1;
                }
            }
            buffer.append(Integer.toHexString(intValue) + "");
        }
        return buffer.toString();
    }

    private String getString(String hex) {
        String hexToText = "";
        int i = 0;
        try {
            while (i < hex.length()) {
                String str = hex.substring(i, i + 2);
                int a = Integer.parseInt(str, 16);
                hexToText = hexToText + ((char) a);
                i = i + 2;
            }
        } catch (NumberFormatException e) {
            log.error("Exception: GatewayService -> getString", e);
        }
        return hexToText;
    }
}
