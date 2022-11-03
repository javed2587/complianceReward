/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author arsalan.ahmad
 */
public class PropertiesUtil {

    private final static Log logger = LogFactory.getLog(PropertiesUtil.class);
    public static String SERVER_MODE = "";

    private static Properties load() {
        Properties prop = new Properties();
        try {
            logger.info("SERVER_MODE: " + SERVER_MODE);
            if ("Prod".equalsIgnoreCase(AppUtil.getSafeStr(SERVER_MODE, ""))) {
                InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/prod.application.properties");
                prop.load(inputStream);
            } else {
                InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/application.properties");
                prop.load(inputStream);
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getStackTrace());
            logger.error(ioe);
        }
        return prop;
    }

    public static void loadServerModeProp() throws IOException {
        Properties prop = new Properties();
        try {
            //get the enviroment variable from the server
            SERVER_MODE=System.getenv("SERVER_MODE");
            logger.info("SERVER_MODE: " + SERVER_MODE);
            if (CommonUtil.isNullOrEmpty(SERVER_MODE)) {
                InputStream is = PropertiesUtil.class.getResourceAsStream("/environment.properties");
                prop.load(is);
                SERVER_MODE = prop.getProperty("SERVER_MODE");
                is.close();
            }
        } catch (Exception e) {
            logger.error("Exception#loadServerModeProp# ", e);
        }

    }

    public static String getProperty(String name) {
        try {
            System.out.println("Reading Property ############################# " + load().getProperty(name));
            return load().getProperty(name);
        } catch (Exception e) {

        }
        return "";
    }

}
