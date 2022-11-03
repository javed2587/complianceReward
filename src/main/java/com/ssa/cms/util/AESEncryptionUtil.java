/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 *
 * @author adeel.usmani
 */
public class AESEncryptionUtil {

    static String key = Constants.AES_KEY;
    static Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
    private static Logger log = Logger.getLogger(AESEncryptionUtil.class);

    public static void main(String[] args) {
        try {
            System.out.print("Enter text: ");
            String text = "1987-04-06";
            String encryptedText = encrypt(text);
            System.err.println("Encrypted: " + encryptedText.length());
            String decryptText = decrypt(encryptedText);
            System.err.println("Encrypted: " + decryptText);

        } catch (Exception e) {
            log.error("#EXCEPTION#", e);
        }
    }

    public static String encrypt(String text) throws Exception {
        // encrypt the text
        if (AppUtil.getSafeStr(text, "").length() > 0) {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            String encryptedString = Base64.getEncoder().encodeToString(encrypted);
            return encryptedString;
        }
        return text;
    }

    public static String decrypt(String encryptedtext) throws Exception {
        // Decrypt the text
        if (AppUtil.getSafeStr(encryptedtext, "").length() > 0) {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] encryptedStringBytes = Base64.getDecoder().decode(encryptedtext);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encryptedStringBytes));
            return decrypted;
        }
        return encryptedtext;
    }

}
