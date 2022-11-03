/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

//import com.sun.jersey.api.client.ClientResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Zubair
 */
public class ServiceConsumerUtil {

    public static String getServiceResponse(String urlStr, String acceptType, String methodType) {
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(AppUtil.getSafeStr(methodType, "GET"));
            if (CommonUtil.isNotEmpty(acceptType)) {
                conn.setRequestProperty("Accept", acceptType);
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output, respStr = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                respStr = output;
            }

            conn.disconnect();
            return respStr;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException | RuntimeException e) {

            e.printStackTrace();

        }
        return "";

    }

    public static void main(String[] args) {

//		getServiceResponse("http://rxdirectws.azurewebsites.net/api/Drug/16110003512",
//    			"application/json",Constants.ServiceConstants.GET_METHOD);
    }
}
