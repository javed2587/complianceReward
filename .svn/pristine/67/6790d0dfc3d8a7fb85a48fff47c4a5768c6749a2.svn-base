package com.ssa.cms.servlet;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.WidgetLog;
import com.ssa.cms.service.PMSTextFlowService;
import com.ssa.cms.util.WidgetUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WidgetServlet {

    @Autowired
    PMSTextFlowService textFlowDAO;

    @Autowired
    PMSGenericTextFlowServlet genericTextFlowServlet;
    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/widget", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/widget", produces = "application/xml", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String communicationId = request.getParameter("communicationId") == null ? "" : request.getParameter("communicationId").trim();
        String communicationMethod = request.getParameter("communicationMethod") == null ? "" : request.getParameter("communicationMethod").trim();
        String trigger = request.getParameter("trigger") == null ? "" : request.getParameter("trigger").trim();
        String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId").trim();
        String password = request.getParameter("password") == null ? "" : request.getParameter("password").trim();
        String phoneNo = request.getParameter("phoneNo") == null ? "" : request.getParameter("phoneNo").trim();
        logger.info("PhoneNo is: " + phoneNo);
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String remoteIP = request.getRemoteAddr();
        logger.info("Remote IP Address : {0}" + remoteIP);

        Enumeration<String> parametersNames = request.getParameterNames();

        StringBuilder dataPost = prepareRequest(parametersNames, request);
        String communicationM = null;
        String postValues = dataPost.toString();
        String email = "";
        if (postValues.endsWith("&")) {
            postValues = postValues.substring(0, postValues.length() - 1);
        }
        logger.info("Data Post : " + dataPost.toString() + "Data Post : " + postValues);

        try {
            if (!"".equals(communicationMethod) && communicationMethod.equalsIgnoreCase("text")) {
                email = communicationId;
                communicationM = "T";
            } else if (!"".equals(communicationMethod) && communicationMethod.equalsIgnoreCase("email")) {
                communicationM = "E";
                email = communicationId;
                communicationId = phoneNo;
            }
            WidgetLog log = saveLog(communicationId, communicationM, trigger, userId, password, remoteIP,
                    requestURI, httpMethod, postValues, true, email);

            WidgetUtil widgetUtil = new WidgetUtil();
            widgetUtil.validateWidget(log, textFlowDAO);

            textFlowDAO.update(log);

            String notificaitonType = log.getNotificationType();
            String notification = log.getNotificationXml();

            response.setContentType("text/xml");
            response.getOutputStream().print(notification);

            if (notificaitonType.equalsIgnoreCase(Constants.FAILURE)) {
                request.setAttribute("notificaitonType", notificaitonType);
                logger.info("Widget validation failed. (System will return)");
            } else {
                request.setAttribute("from", email);
                request.setAttribute("message", trigger);
                if (log.getCommunicationMethod().equalsIgnoreCase("T")) {
                    genericTextFlowServlet.doGet(request, response);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(WidgetServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private StringBuilder prepareRequest(Enumeration<String> parametersNames, HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuilder dataPost = new StringBuilder();
        while (parametersNames.hasMoreElements()) {

            String paramName = parametersNames.nextElement();
            String paramValue = request.getParameter(paramName);

            String param = "Param Name : " + paramName + " and value : " + paramValue;
            Logger.getLogger(WidgetServlet.class.getName()).log(Level.INFO, param);

            if (paramValue == null || paramValue.trim().length() == 0) {
                dataPost.append(paramName).append("=").append(URLEncoder.encode("", Constants.UTF_8));
            } else {
                dataPost.append(paramName).append("=").append(URLEncoder.encode(paramValue.trim(), Constants.UTF_8));
            }

            dataPost.append("&");
        }
        return dataPost;
    }

    private WidgetLog saveLog(String communicationId, String communicationMethod, String trigger,
            String userId, String password, String remoteIP, String requestURI, String httpMethod,
            String postValues, boolean saved, String email) {

        WidgetLog log = new WidgetLog();
        log.setCommunicationId(communicationId);
        log.setCommunicationMethod(communicationMethod);
        log.setTriggerValue(trigger);
        log.setUserId(userId);
        log.setPassword(password);
        log.setRemoteIpAddress(remoteIP);
        log.setRequestURI(requestURI);
        log.setHttpMethod(httpMethod);
        log.setRequestDate(postValues);
        log.setEffectiveDate(new Date());
        log.setEmail(email);
        if (saved) {
            textFlowDAO.save(log);
        }
        return log;
    }
}
