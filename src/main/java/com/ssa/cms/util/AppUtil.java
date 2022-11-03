/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

/**
 *
 * @author Muhammad Mohsin Aziz
 */
import com.ssa.cms.common.Constants;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.springframework.context.ApplicationContext;

public class AppUtil {

    private static ApplicationContext ctx;

    public static void setAppContext(ApplicationContext context) {
        ctx = context;
    }

    public static ApplicationContext getAppContext() {
        return ctx;
    }

    /**
     *
     * @param s value which we want to convert in string after eliminating
     * leading & trailing spaces
     * @param alt in case of null in s parameter, value in alt will be returned
     * @return in case of null, returns alt parameter to avoid null pointer
     * exception, otherwise returns string after removing leading & trailing
     * spaces
     */
    public static String getSafeStr(String s, String alt) {
        if (s == null || s.trim().length() == 0) {
            return alt;
        }
        return s.trim();
    }

    /**
     * Purpose: convert a string to int
     *
     * @param s value which we want to parse
     * @param alt in case of invalid value in s parameter, value in alt will be
     * returned
     * @return Converted number in case of valid string otherwise returns alt
     * parameter
     */
    public static int getSafeInt(String s, int alt) {
        try {
            return Integer.parseInt(getSafeStr(s, "0"));
        } catch (Exception e) {
            return alt;
        }
    }

    /**
     * Purpose: convert a string to Long
     *
     * @param s value which we want to parse
     * @param alt in case of invalid value in s parameter, value in alt will be
     * returned
     * @return Converted number in case of valid string otherwise returns alt
     * parameter
     */
    public static Long getSafeLong(String s, Long alt) {
        try {
            return Long.parseLong(getSafeStr(s, "0"));
        } catch (Exception e) {
            return alt;
        }
    }

    /**
     * Purpose: convert a string to Double
     *
     * @param s value which we want to parse
     * @param alt in case of invalid value in s parameter, value in alt will be
     * returned
     * @return Converted number in case of valid string otherwise returns alt
     * parameter
     */
    public static Double getSafeDouble(String s, Double alt) {
        try {
            return Double.parseDouble(getSafeStr(s, "0"));
        } catch (Exception e) {
            return alt;
        }
    }

    /**
     * Purpose: convert a string to Float
     *
     * @param s value which we want to parse
     * @param alt in case of invalid value in s parameter, value in alt will be
     * returned
     * @return Converted number in case of valid string otherwise returns alt
     * parameter
     */
    public static Float getSafeFloat(String s, Float alt) {
        try {
            return Float.parseFloat(getSafeStr(s, "0"));
        } catch (Exception e) {
            return alt;
        }
    }

    public static String truncateStringToSpecificLength(String s, int length) {
        try {
            s = getSafeStr(s, "");
            if (s.length() > length) {
                s = s.substring(0, length);
                s += "...";
            }
            return s;
        } catch (Exception e) {

            return s;
        }
    }

    public static String convertNumberToThousandSeparatedFormat(Number number) {
        try {
            if (number != null) {
                return NumberFormat.getNumberInstance().format(number);
            }
            return "0";
        } catch (Exception e) {
            return "" + number;
        }
    }

    public static String roundOffNumberToTwoDecimalPlaces(Number number) {
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(number);
        } catch (Exception e) {
            return number.toString();
        }
    }

    /**
     * Purpose: Convert a number into desired currencu format
     *
     * @param number number to be converted
     * @param lang language, by default it is en
     * @param country country, by default it is US
     * @return
     */
    public static String roundOffNumberToCurrencyFormat(Number number, String lang, String country) {
        try {
            if (getSafeStr(lang, "") == "") {
                lang = "en";
            }
            if (getSafeStr(country, "") == "") {
                country = "US";
            }

            Locale locale = new Locale(lang, country);
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            String amount = currencyFormatter.format(number);
//            String[] arr=amount.split(".");
//            if(arr.length==2 && arr[1]=="00")
//            {
//                return arr[0];
//            }
            return amount;
        } catch (Exception e) {
            return number.toString();
        }
    }

    public static String getOneStringFromBrandAndGeneric(String brand, String generic, Integer brandIndicator) {
        if (brandIndicator == null || brandIndicator == 0) {
            return getSafeStr(brand, "") + "(" + getSafeStr(generic, "") + ")";
        }
        return getSafeStr(brand, "") + "**" + Constants.BRAND_NAME_ONLY + "**";
    }

    public static String getInsuranceTypeText(String insType) {
        insType = getSafeStr(insType, "");
        if (insType.equalsIgnoreCase("Public")) {
            return "PUBLIC REIMBURSED";
        }
        if (insType.equalsIgnoreCase("Commercial")) {
            return "COMMERCIAL INS";
        }
        return insType;
    }

    public static String numberFormat(Number number) {
        NumberFormat nf_out = NumberFormat.getNumberInstance(Locale.US);
        nf_out.setMaximumFractionDigits(2);
        String output = nf_out.format(number);
        System.out.println("NumberFormat:: " + output);
        return output;
    }

    public static String getStringPortion(String str, int length) {
        try {
            String s = getSafeStr(str, "");
            if (s.length() > length) {
                s = s.substring(0, length - 1);
                s = s + "...";
            }
            return s;
        } catch (Exception e) {
            return str;
        }
    }

    /**
     * Receives drugname. If drug has generic name then parameter will be in
     * format brand{generic} otherwise format will be brand *brand name only*.
     *
     *
     * @param drugName
     * @return Function returns an array containing brand on first index &
     * generic if exists then generic name on 2nd index
     */
    public static String[] getBrandAndGenericFromDrugName(String drugName) {
        String arr[] = drugName.split("\\{");
        if (arr != null && arr.length == 2) {
            String[] drugArr = new String[2];
            drugArr[0] = arr[0];
            drugArr[1] = arr[1].replace("}", "");
            return drugArr;
        } else {
            if (drugName.indexOf(Constants.BRAND_NAME_ONLY_WITH_STARIC) > 0) {
                String[] drugArr = new String[1];
                drugArr[0] = drugName.substring(0, drugName.indexOf(Constants.BRAND_NAME_ONLY_WITH_STARIC));
                drugArr[0] = drugArr[0].trim();
                return drugArr;
            }
            return arr;
        }
    }

    public static String getProperDrugName(String drugName, String drugType, String drugStrength) {
        drugName = AppUtil.getSafeStr(EncryptionHandlerUtil.getDecryptedString(drugName), "");
        String arr[] = AppUtil.getSafeStr(drugName, "").split("\\{");
        if (AppUtil.getSafeStr(drugName, "").length() == 0
                && AppUtil.getSafeStr(drugType, "").length() == 0
                && AppUtil.getSafeStr(drugStrength, "").length() == 0) {
            return "N/A";
        }
        if (arr != null && arr.length == 2) {
            if (drugName.indexOf(Constants.BRAND_NAME_ONLY) >= 0) {
                return arr[0] + " " + AppUtil.getSafeStr(drugType, "") + " "
                        + AppUtil.getSafeStr(drugStrength, "")
                        + "<h5> <small>Generic For <span class=\"blue\"> Brand Name Only</span></small></h5>";
            } else {
                return arr[1].replace("}", "") + " " + AppUtil.getSafeStr(drugType, "") + " "
                        + AppUtil.getSafeStr(drugStrength, "")
                        + "<h5> <small>Generic For <span class=\"red\"> " + arr[0] + "</span></small></h5>";
            }
        } else {
            if (drugName.indexOf(Constants.BRAND_NAME_ONLY_WITH_STARIC) > 0) {
                String[] drugArr = new String[1];
                drugArr[0] = drugName.substring(0, drugName.indexOf(Constants.BRAND_NAME_ONLY_WITH_STARIC));
                drugArr[0] = drugArr[0].trim();
                return drugArr[0] + " " + AppUtil.getSafeStr(drugType, "") + " "
                        + AppUtil.getSafeStr(drugStrength, "")
                        + "<h5> <small>Generic For <span class=\"blue\"> Brand Name Only</span></small></h5>";
            }
            return drugName + " " + AppUtil.getSafeStr(drugType, "") + " "
                    + AppUtil.getSafeStr(drugStrength, "")
                    + "<h5> <small>Generic For <span class=\"blue\"> User Inputted " + "</span></small></h5>";
        }

    }

}
