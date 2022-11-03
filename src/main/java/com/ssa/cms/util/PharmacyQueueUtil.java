/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mzubair
 */
public class PharmacyQueueUtil {

    public static String newMemberRequestTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQ<br />CONTROL&nbsp;#", "requestControlNumber1");
        tableData.put("REQ<br />POSTED", "reqPosted");
        tableData.put("MBR&nbsp;SVC<br />REQUEST", "requestType");
        tableData.put("Current<br />RX&nbsp;STATUS", "status");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("MEDICATION", "drugNameWithoutStrength");
        tableData.put("DELIVERY<br />SVC", "deliveryService");
        tableData.put("MULTI-RX", "multiRxLabel");
        tableData.put("INS", "finalPaymentMode");
        tableData.put("ALLERGY<br />NOTE", "allergies");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("ALLERGY")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }

    public static String interpretedImagesTableData() {
        StringBuilder sb = populateSubInterpretedIImageTableData();
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQUEST<br />CONTROL&nbsp;NO.", "requestControlNumber1");
        tableData.put("LAST UPDATE&nbsp;POSTED", "reqPosted");
        tableData.put("USER MAKING<br />UPDATE", "updatedBy");
        tableData.put("ORIGINAL REQUEST TYPE", "requestType");
        tableData.put("CURRENT PHARMACY&nbsp;STATUS", "status");
        tableData.put("Svc", "deliveryService");
        tableData.put(sb.toString(), "subInterpretedIImageTable");
        tableData.put("RX&nbsp;ORIG<br />DATE", "rxDateStr");
        tableData.put("MULTI<br />-RX", "multiRxLabel");
        tableData.put("REQ&nbsp;SELF<br /> PAY ?", "finalPaymentMode");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("REQ&nbsp;SELF<br /> PAY")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }

    private static StringBuilder populateSubInterpretedIImageTableData() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"full_table\" id=\"subInterpretedIImageTable\">");
        sb.append("<tr><th colspan=\"8\" class=\"interpreted_th\">INTERPRETED ENTRY</th></tr>");
        sb.append("<tr>");
        sb.append("<th>PATIENT<br />NAME</th>");
        sb.append("<th>RX&nbsp;NAME</th>");
        sb.append("<th>STRENGTH</th>");
        sb.append("<th>DOSAGE<br />TYPE</th>");
        sb.append("<th>QTY.</th>");
        sb.append("<th>DAYS<br />SUPPLY</th>");
        sb.append("<th style=\"color: red;\">RX&nbsp;INGR<br />COST&nbsp;($)</th>");
        sb.append("<th>REFILLS<br />REMAIN</th>");
        sb.append("</tr>");
        sb.append("</table>");
        return sb;
    }

    public static String waitingPtResponseTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQUEST<br />CONTROL&nbsp;NO.", "requestControlNumber1");
        tableData.put("LAST<br />UPDATE<br />POSTED", "reqPosted");
        tableData.put("SERVICE<br />REQ.", "deliveryService");
        tableData.put("MULTI<br />-RX", "multiRxLabel");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("RX&nbsp;NAME", "drugNameWithoutStrength");
        tableData.put("STRENGTH", "strength");
        tableData.put("DOSAGE<br />TYPE", "drugType");
        tableData.put("QTY.", "quantity");
        tableData.put("RX&nbsp;INGR<br />COST&nbsp;($)", "rxAcqCostStr");
        tableData.put("PATIENT<br />OOP<br />REQ.", "finalCopayStr");
        tableData.put("UNDER<br />QUOTED<br />PRICE&nbsp;($)", "underQuotedPriceStr");
        tableData.put("MEDICATION<br />SPEC&nbsp;MSG", "medicationSpecMsg");
        tableData.put("CURRENT<br />STATUS", "patientResponse");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("CURRENT<br />STATUS")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }

    public static String processRequestTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQUEST<br />CONTROL&nbsp;NO.", "requestControlNumber1");
        tableData.put("STATUS<br />POSTED", "reqPosted");
        tableData.put("SERVICE<br />REQ.", "deliveryService");
        tableData.put("MULTI<br />-RX", "multiRxLabel");
        tableData.put("RX#", "systemGeneratedRxNumber");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("RX&nbsp;PROCESSED", "drugNameWithoutStrength");
        tableData.put("DELIVERY<br />ADDRESS", "deliveryAddress");
        tableData.put("ZIP", "zip");
        tableData.put("PREMIUM<br />DELIVERY<br />MILES", "miles");
        tableData.put("PREMIUM<br />DELIVERY<br />FEE&nbsp;($)", "handlingFeeStr");
        tableData.put("PICK&nbsp;UP<br />PAPER<br />RX&nbsp;?", "pickUpPaperRx");
        tableData.put("PT&nbsp;REQSTD<br />TIME<br />WINDOW", "deliverycarrier");
        tableData.put("PATIENT<br />SPECIAL<br />INST.", "insuranceCheck");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("PATIENT<br />SPECIAL<br />INST.")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }

    public static String shippingDeliveryTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQUEST<br />CONTROL&nbsp;NO.", "requestControlNumber1");
        tableData.put("STATUS<br />POSTED", "reqPosted");
        tableData.put("SERVICE<br />REQ.", "deliveryService");
        tableData.put("MULTI<br />-RX", "multiRxLabel");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("RX&nbsp;PROCESSED", "drugNameWithoutStrength");
        tableData.put("<span class=\"redText\">INS</span>&nbsp;/<br />SELF<br />PAY", "finalPaymentMode");
        tableData.put("SHIPPING<br />STATUS", "shippingStatus");
        tableData.put("DELIVERY<br />METHOD", "deliverycarrier");
        tableData.put("TRACKING<br />NUMBER", "tracking");
        tableData.put("ZIP<br />CODE", "zip");
        tableData.put("<span class=\"redText\">PREM</span> DELIV<br />FEE<br/>COLLECTED", "miles");
        tableData.put("<span class=\"redText\">PREM</span><br />DELIV<br /><span class=\"redText\">MILES</span>", "handlingFeeStr");
        tableData.put("FINAL&nbsp;PATIENT<br />OOP<br />&nbsp;<span class=\"redText\">($)</span>", "finalCopayStr");
        tableData.put("INGREDIENT<br /><span class=\"redText\">($)</span>&nbsp;COST", "rxAcqCostStr");
        tableData.put("TOTAL <span class=\"redText\">($)</span><br />SELLING<br />PRICE", "sellingPriceStr");

        //tableData.put("PICK&nbsp;UP<br />PAPER<br />RX&nbsp;?", "pickUpPaperRx");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("TOTAL <span class=\"redText\">($)</span><br />SELLING<br />PRICE")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }

    public static String cancelledRequestTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQUEST<br />CONTROL&nbsp;NO.", "requestControlNumber1");
        tableData.put("STATUS<br />POSTED", "reqPosted");
        tableData.put("SERVICE<br />REQ.", "requestType");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("MEMBER /<br />DEPENDANT", "memberDependant");
        tableData.put("PHARMACY<br />RESPONSE", "pharmacyResponse");
        tableData.put("RX&nbsp;STATUS", "status");
        tableData.put("MEDICATION", "drugNameWithoutStrength");
        tableData.put("STRENGTH", "strength");
        tableData.put("QTY.", "quantityStr");
        tableData.put("INS/<br />SELF&nbsp;PAY", "finalPaymentMode");
        tableData.put("PUBLIC<br />INS", "insuranceCheck");
        tableData.put("DELIVERY<br />SVC REQ.", "deliveryService");

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("DELIVERY<br />SVC REQ.")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }
    
    public static String responseReceivedTableData() {
        Map<String, String> tableData = new LinkedHashMap<>();
        tableData.put("REQ<br />CONTROL&nbsp;#", "requestControlNumber1");
        tableData.put("Last Update <br>Posted", "reqPosted");
        tableData.put("DELIVERY<br />SVC", "deliveryService");
        tableData.put("Patient Response", "patientResponse");
        tableData.put("PATIENT&nbsp;NAME", "patientName");
        tableData.put("Multi<br> Rx", "multiRxLabel");
        tableData.put("Payor<br> Type", "finalPaymentMode");
        tableData.put("MEDICATION", "drugNameWithoutStrength");     

        String col = new String();
        for (String key : tableData.keySet()) {
            String temp = "  {\n";
            temp += "   \"sTitle\": '" + key + "',\n";
            temp += "   \"mDataProp\": '" + tableData.get(key) + "'\n";
            if (key.contains("ALLERGY")) {
                temp += "   }\n";
            } else {
                temp += "   },\n";
            }
            col = col.concat(temp);
        }
        return col;
    }
}
