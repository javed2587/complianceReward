/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.servlet;

import com.ssa.cms.common.Constants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rizwan.Munir
 */
@Controller
@RequestMapping
public class PMSGenericYesResponseServlet {
     private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PMSGenericYesResponseServlet.class);
     @RequestMapping(value = "/PMSGenericYesResponseWs", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/PMSGenericYesResponseWs", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String phoneNumber;
         String message;
         String qualifyAmount = "";
          if (request.getParameter("from") == null || "".equals(request.getParameter("from")) || request.getParameter("message") == null || "".equals(request.getParameter("message"))) {
            phoneNumber = request.getParameter("PhoneNumber") == null ? "" : request.getParameter("PhoneNumber").trim();
            message = request.getParameter("Message") == null ? "" : request.getParameter("Message").trim();//message Save,Yes,Initials,N 
            qualifyAmount = request.getParameter("QualifyAmount") == null ? "" : request.getParameter("QualifyAmount");

            if ("".equals(phoneNumber)) {
                phoneNumber = request.getAttribute("from") == null ? "" : request.getAttribute("from").toString();
                logger.info("Phone number getAttribute:"+phoneNumber);
            }
            if ("".equals(message)) {
                message = request.getAttribute("message") == null ? "" : request.getAttribute("message").toString();//message Save,Yes,Initials,N  
                logger.info("message getAttribute:"+message);
            }
            if ("".equals(qualifyAmount)) {
                qualifyAmount = request.getAttribute("QualifyAmount") == null ? "" : request.getAttribute("QualifyAmount").toString();
            }

        } else { //from system
            phoneNumber = request.getParameter("from") == null ? "" : request.getParameter("from").trim();
            message = request.getParameter("message") == null ? "" : request.getParameter("message").trim();//message Save,Yes,Initials,N      
        }
           if (phoneNumber.length() == 0) {
                logger.info("Phone number empty");
              response.getOutputStream().print("Phone number empty");
              return;
          }
        if (message.length() == 0) {
            logger.info("Message is empty");
            response.getOutputStream().print("Message is empty");
            return;
        }
         String[] urlList ={Constants.QA,Constants.LIVE,Constants.PRODUCTION};
//         Url url 
         for (int i = 0; i < urlList.length; i++) {
	    try {
		  String url = urlList[i];					
                    //get lsit all instce url for db table. if ststys active 
		  logger.info("Record: url = " + url.trim()+"phoneNumber= "+phoneNumber+"message= "+message);
	          sendYesReponseTOAllEnviorments(url.trim(),phoneNumber,message);
		  logger.info("count: " + i);
		  Thread.sleep(100);
					
		} catch (Exception ex) {
		     logger.info("Exception#PMSGenericYesResponse"+ ex.getMessage());
                     
		 }
	    }
            
            
         
    }
    
    public static boolean sendYesReponseTOAllEnviorments(String uri,String phoneNumber,String message) throws Exception {
		
        
		boolean flag = true;
		String data ="PhoneNumber="+phoneNumber+"&Message="+message;
		try {
			URL url = new URL(uri);

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(data);
			wr.flush();

			// Getting Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			String responseString = "";
			while ((line = rd.readLine()) != null) {
				responseString = responseString.concat(line);
			}

			logger.info("Result: " + phoneNumber + "|" + responseString);
			wr.close();
			rd.close();

		} catch (Exception e) {
			flag = false;
			logger.info("#sendYesReponseTOAllEnviorments"+e.getMessage());
			e.printStackTrace();
		}

		return flag;
	}
}
