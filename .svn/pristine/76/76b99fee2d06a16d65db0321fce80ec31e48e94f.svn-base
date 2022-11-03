package com.ssa.cms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LandLineValidationService {

    private static final Log logger = LogFactory.getLog(LandLineValidationService.class.getName());
    private String landlineValidationURL = "";

    public LandLineValidationService() {

    }

    public LandLineValidationService(String landlineValidationURL) {
        this.landlineValidationURL = landlineValidationURL;
    }

    public boolean validateLandLinePhone(String phoneNumber, String appCode, String programCode) {
        boolean flag = true;

        String responseString = "";
        StringBuilder dataPost = new StringBuilder();

        try {

            if (phoneNumber == null || phoneNumber.trim().length() == 0) {
                phoneNumber = "";
            }

            if (programCode == null || programCode.trim().length() == 0) {
                programCode = "";
            }

            dataPost.append(URLEncoder.encode("from", "UTF-8")).append("=").append(URLEncoder.encode(phoneNumber, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("appName", "UTF-8")).append("=").append(URLEncoder.encode(appCode, "UTF-8"));
            dataPost.append("&");
            dataPost.append(URLEncoder.encode("programCode", "UTF-8")).append("=").append(URLEncoder.encode(programCode, "UTF-8"));

            URL url = new URL(landlineValidationURL);
            logger.info("Landline Validation URL : " + landlineValidationURL);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())) {
                wr.write(dataPost.toString());
                wr.flush();
                logger.info("Final Data Post For Land Line Validation : " + dataPost);
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    responseString = responseString.concat(line);
                }   if (responseString != null && !responseString.equals("true")) {
                    flag = false;
                }
            }
            rd.close();

            logger.info("Land Line Validation Response String : " + responseString);

        } catch (Exception e) {
            flag = true;
            logger.error(e);
        }

        return flag;
    }
}
