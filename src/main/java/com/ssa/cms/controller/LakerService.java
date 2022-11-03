package com.ssa.cms.controller;

import com.ssa.cms.common.Constants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LakerService {

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/laker/{type}/{fromdate}/{todate}", method = RequestMethod.GET)
    public void lakerDrf(@PathVariable String type, @PathVariable String fromdate, @PathVariable String todate) {
        try {
            String fileName = "PBMExpress.SelfHosted.Services.InstantRedemptionReport";
            String fileRoot = "";
            if ("irf".equals(type)) {
                fileName = "PBMExpress.SelfHosted.Services.InstantRedemptionReport";
                fileRoot = Constants.IRF_FILE_PATH;
            } else if ("drf".equals(type)) {
                fileName = "PBMExpress.SelfHosted.Services.DailyRedemptionReport";
                fileRoot = Constants.DRF_FILE_PATH;
            }

            String lakerReuest = "{\"FullName\":\"" + fileName + "\", \"Version\":1,\"ParamObjects\":{\"startdt\":" + fromdate + ", \"enddt\":" + todate + "}}";
            logger.info("Request::" + lakerReuest);

            URL url = new URL("https://cittest.pbmexpress.com/reporting");
            String authStr = Constants.LAKERUSERNAME + ":" + Constants.LAKERPASSWORD;

            String authEncoded = Base64.encodeBase64String(authStr.getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + authEncoded);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(lakerReuest);
            outputWriter.flush();

            File file = new File(fileRoot + "/" + type + "_" + System.currentTimeMillis() + ".json");
            InputStream in = (InputStream) connection.getInputStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
            logger.info("Process Executed successfully." + file.getName());

        } catch (IOException e) {
            logger.error("Exception: laker -> ", e);
        }
    }

    //@Scheduled(cron = "${LAKDER_GET_IRF_PROCESS_CRON}")
    public void getLakerIrfData() {
        Calendar calendar = new GregorianCalendar();
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
        calendar.setTimeZone(timeZone);
        String currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'").format(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        String twoHoursBack = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'").format(calendar.getTime());
        lakerDrf("irf", twoHoursBack, currentTime);
    }

    //@Scheduled(cron = "${LAKDER_GET_DRF_PROCESS_CRON}")
    public void getLakerDrfData() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String fromDate = date + "T00:00:00.000Z";
        String toDate = date + "T23:59:59.000Z";
        lakerDrf("drf", fromDate, toDate);
    }
}
