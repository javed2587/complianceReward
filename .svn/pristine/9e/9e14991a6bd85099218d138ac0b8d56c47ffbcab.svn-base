package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import com.ssa.decorator.MTDecorator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class SMSUtil {

    private static final Log logger = LogFactory.getLog(SMSUtil.class);

    /**
     * This method is used to send SMS on specified phone number.
     *
     * @param phoneNumber
     * @param message
     * @return
     * @throws Exception
     */
    public static MTDecorator sendSmsMessage(String phoneNumber, String message) throws Exception {
        MTDecorator decorator = new MTDecorator();
        decorator.setRequestXML(null);
        decorator.setMessageText(message);
        decorator.setStatusDescription("Success");
        decorator.setResponseXML(null);
        decorator.setErrorCode("1");
        decorator.setErrorDescription(null);
        decorator.setMtsId(null);
        decorator.setSent(true);
        return decorator;
    }

    public static String implodeXML(Element container, String delim) throws Exception {
        if (container == null) {
            return "";
        }
        List objs = container.getChildren();
        StringBuilder buf = new StringBuilder();
        int size = objs.size();

        for (int i = 0; i < size - 1; i++) {
            buf.append(((Element) (objs.get(i))).getText()).append(delim);
        }

        if (size != 0) {
            buf.append(((Element) (objs.get(size - 1))).getText());
        }

        return buf.toString();
    }

    private static StringBuilder inputStreamToString(InputStream is) {
        StringBuilder total = new StringBuilder();
        try {

            String line;

            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }

        } catch (IOException e) {
            logger.error("IOException: " + e);
        }

        // Return full string
        return total;
    }

    public static MTDecorator sendSmsText(String phoneNumber, String message) throws Exception {
        MTDecorator decorator = new MTDecorator();
        try {
            message = message.replaceAll("&", "and");
            logger.info("Phone no is: " + phoneNumber);
            //String data = "User=" + Constants.USERNAME + "&Password=" + Constants.PASSWORD + "&PhoneNumbers[]=" + phoneNumber + "&Message=" + message + "&StampToSend=1305582245";
            StringBuilder dataPost = new StringBuilder();
            dataPost.append(URLEncoder.encode("User", "UTF-8")).append("=").append(URLEncoder.encode(Constants.USERNAME, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("Password", "UTF-8")).append("=").append(URLEncoder.encode(Constants.PASSWORD, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("PhoneNumbers[]", "UTF-8")).append("=").append(URLEncoder.encode(EncryptionHandlerUtil.getDecryptedString(phoneNumber), "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("Message", "UTF-8")).append("=").append(URLEncoder.encode(message, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("StampToSend", "UTF-8")).append("=").append(URLEncoder.encode("1305582245", "UTF-8"));
            dataPost.append("&");
            
            System.out.println("TARGETED URL IS " + Constants.SMS_URL);
            URL url = new URL(Constants.SMS_URL);
            System.out.println("data : " + dataPost.toString());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Method", "POST");

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(dataPost.toString());
            wr.flush();

            int responseCode = conn.getResponseCode();
            logger.info("Response code: " + responseCode);

            boolean isSuccesResponse = responseCode < 400;
            InputStream responseStream = isSuccesResponse ? conn.getInputStream() : conn.getErrorStream();

            decorator.setRequestXML(dataPost.toString());
            decorator.setMessageText(message);

            StringBuilder responseXml = inputStreamToString(responseStream);
            String response1 = new String(responseXml);
            StringReader reader = new StringReader(response1);
            SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(reader);

            Element response = document.getRootElement();
            logger.info("Status: " + response.getChildText("Status") + "Code: " + response.getChildText("Code"));

            decorator.setStatusDescription(response.getChildText("Status"));
            decorator.setResponseXML(response1);
            decorator.setErrorCode(response.getChildText("Code"));
            if (isSuccesResponse) {
                Element entry = response.getChild("Entry");
                decorator.setErrorDescription(response1);
                decorator.setMtsId(entry.getChildText("ID"));
                decorator.setSent(true);
                decorator.setErrorCode(response.getChildText("Code"));

            } else {
                decorator.setErrorDescription(implodeXML(response.getChild("Errors"), "\n"));
                decorator.setSent(false);
            }

            responseStream.close();
            wr.close();
        } catch (IOException | JDOMException e) {
            logger.error("IOException: " + e);
            decorator.setErrorDescription("There is issue with message sending " + e.getMessage());
        }
        return decorator;
    }
}
