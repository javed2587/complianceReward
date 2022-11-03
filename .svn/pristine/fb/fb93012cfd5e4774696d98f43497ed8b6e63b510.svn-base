/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Abid
 */
public class RandomString {

    private static final Random RANDOM = new SecureRandom();
    /**
     * Length of password. @see #generateRandomPassword()
     */
    public static final int PASSWORD_LENGTH = 8;

    /**
     * Generate a random String suitable for use as a temporary password.
     *
     * @return String suitable for use as a temporary password
     * @since 2.4
     */
    public static String generateRandomPassword() {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "ABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }

    public static String generatePassword() {
        int noOfCAPSAlpha = 1;
        int noOfDigits = 1;
        int noOfSplChars = 1;
        int minLen = 10;
        int maxLen = 10;
        char[] pswd = RandomPasswordGenerator.generatePswd(minLen, maxLen,
                noOfCAPSAlpha, noOfDigits, noOfSplChars);
        //System.out.println("RandomPassword is :" + new String(pswd) + " RandomPassword length is: " + pswd.length);
        return new String(pswd);
    }
}
