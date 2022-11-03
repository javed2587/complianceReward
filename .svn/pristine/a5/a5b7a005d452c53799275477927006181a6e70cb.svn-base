package com.ssa.cms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneValidationService {

    private String phoneValidationURL = "";

    public PhoneValidationService() {
    }

    public PhoneValidationService(String validationURL) {
        this.phoneValidationURL = validationURL;
    }

    public boolean checkPhoneValidity(String phoneNumber, String applicationCode, String programCode) throws Exception {

        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            phoneNumber = "";
        }

        if (applicationCode == null || applicationCode.trim().length() == 0) {
            applicationCode = "";
        }

        if (programCode == null || programCode.trim().length() == 0) {
            programCode = "";
        }

        System.out.println("Phone Number : " + phoneNumber);
        System.out.println("Application Code " + applicationCode);
        System.out.println("Program Code : " + programCode);

//        dataPost.append(URLEncoder.encode("from", "UTF-8") + "=" + URLEncoder.encode(phoneNumber, "UTF-8"));
//        dataPost.append("&");
//        dataPost.append(URLEncoder.encode("appName", "UTF-8") + "=" + URLEncoder.encode(applicationCode, "UTF-8"));
//        dataPost.append("&");
//        dataPost.append(URLEncoder.encode("programCode", "UTF-8") + "=" + URLEncoder.encode(programCode, "UTF-8"));

        try {

            URL url = new URL(this.phoneValidationURL+"?from="+phoneNumber);

            System.out.println("Validation URL " + url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println("Opening Connection");
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Response Code "+responseCode);
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //add request header
	   //con.setRequestProperty("User-Agent", USER_AGENT);

//            URLConnection connection = url.openConnection();
//            connection.setDoOutput(true);
//            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//            wr.write(dataPost.toString());
//            wr.flush();

            //System.out.println("Final Data Post PhoneValidation : " + dataPost);

            // Getting Response from 3CI
//            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;

            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }

            //wr.close();
            rd.close();

            System.out.println("Validation Response " + responseString);

            if (responseString == null || !responseString.trim().equals("true")) {
                flag = false;
            }
        } catch (Exception e) {
            System.out.println("====================>EXCEPTION COMES HERE=========================>");
            flag = false;
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }

        return flag;
    }

}
