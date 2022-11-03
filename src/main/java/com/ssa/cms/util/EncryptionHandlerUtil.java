/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import org.apache.log4j.Logger;

/**
 *
 * @author adeel.usmani
 */
public class EncryptionHandlerUtil {

    private static Logger log = Logger.getLogger(EncryptionHandlerUtil.class);

    public static String getEncryptedString(String encString) {
        try {

            if (encString != null) {
                boolean isBase64 = true;
//				Base64.Decoder decoder = Base64.getDecoder();

                try {//just to check if string is alreay encrypted
//					decoder.decode(encString);
                    AESEncryptionUtil.decrypt(encString);
                } catch (Exception e) {
                    isBase64 = false;
                }
                if (!isBase64)//if not encrypted already
                {
                    return AESEncryptionUtil.encrypt(encString);
//					log.info("Actual communication id is "+communicationId);
                }
//        		encFldsI.setCommonEncryptedFields(commonEncFlds);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
//			e.printStackTrace();

        }
        return encString;
    }

    public static String getDecryptedString(String encryptedString) {
        try {
            return AESEncryptionUtil.decrypt(encryptedString);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return encryptedString;
        }
    }
}
