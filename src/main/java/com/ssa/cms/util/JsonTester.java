/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author mzubair
 */
public class JsonTester {

    public static void main(String[] args) {
        try {
            /**
             * String str = null; JSONArray arr = new JSONArray(); JSONObject
             * outerObj = new JSONObject(); outerObj.put("clientCompanyID",
             * "4767"); outerObj.put("businessLineID", "1000");
             * outerObj.put("transmissionTime",
             * "2017-12-19T06:32:31.4141038-05:00"); JSONObject innerObj = new
             * JSONObject(); innerObj.put("orderID", "-1");
             * innerObj.put("customerOrderID", ""); innerObj.put("firstName",
             * ""); innerObj.put("lastName", ""); innerObj.put("street1", "");
             * innerObj.put("street2", ""); innerObj.put("city", "");
             * innerObj.put("state", ""); innerObj.put("postalCode", "");
             * innerObj.put("phone", ""); innerObj.put("deliveryID", "-1");
             * innerObj.put("customerDeliveryID", str);
             * innerObj.put("clientCustomerID", str); innerObj.put("smsEnabled",
             * false); innerObj.put("serviceDate", str);
             * innerObj.put("serviceCompleted", str); innerObj.put("sweepID",
             * str); innerObj.put("deliveryWindowID", str);
             * innerObj.put("packages", 0); innerObj.put("items", 0);
             * innerObj.put("pieceType", 0); innerObj.put("serviceLevelID",
             * str); innerObj.put("cases", str); innerObj.put("weight", 0.0);
             * innerObj.put("length", str); innerObj.put("width", str);
             * innerObj.put("height", str); innerObj.put("status", str);
             * innerObj.put("codExpected", str); innerObj.put("locationCode",
             * str); innerObj.put("notes", str); innerObj.put("signature", str);
             * innerObj.put("serviceTotalCost", str); innerObj.put("miles",
             * str); innerObj.put("refrigerated", str); arr.put(str);
             * arr.put(innerObj); outerObj.put("deliveries", arr); StringEntity
             * entity = new StringEntity(outerObj.toString());
             * System.out.println(IOUtils.toString(entity.getContent()));
             *
             * System.out.println("******************Print String entity
             * Json***************************");
             *
             * StringEntity params = new StringEntity("{\"clientCompanyID\":
             * \"4767\",\"businessLineID\": \"1000\"," + "\"transmissionTime\":
             * \"2017-12-19T06:32:31.4141038-05:00\"," + " \"deliveries\":
             * [null,{" + " \"orderID\": -1, \"customerOrderID\":
             * \"\",\"firstName\": \"\",\n" + " \"lastName\": \"\",\n" + "
             * \"street1\": \"\",\n" + " \"street2\": \"\",\n" + " \"city\":
             * \"\",\n" + " \"state\": \"\",\n" + " \"postalCode\": \"\",\n" + "
             * \"phone\": \"\",\n" + " \"deliveryID\": -1,\n" + "
             * \"customerDeliveryID\": null,\n" + " \"clientCustomerID\":
             * null,\n" + " \"smsEnabled\": false,\n" + " \"serviceDate\":
             * null,\n" + " \"serviceCompleted\": null,\n" + " \"sweepID\":
             * null,\n" + " \"deliveryWindowID\": null,\n" + " \"packages\":
             * 0,\n" + " \"items\": 0,\n" + " \"pieceType\": 0,\n" + "
             * \"serviceLevelID\": null,\n" + " \"cases\": null,\n" + "
             * \"weight\": 0.0,\n" + " \"length\": null,\n" + " \"width\":
             * null,\n" + " \"height\": null,\n" + " \"status\": null,\n" + "
             * \"codExpected\": null,\n" + " \"locationCode\": null,\n" + "
             * \"notes\": null,\n" + " \"signature\": null,\n" + "
             * \"serviceTotalCost\": null,\n" + " \"miles\": null,\n" + "
             * \"refrigerated\": null\n" + " }\n" + " ]}");
             * System.out.println(IOUtils.toString(params.getContent()));
             *
             * // create the albums object JsonObject albums = new
             * JsonObject(); // add a property calle title to the albums object
             * albums.addProperty("clientCompanyID", "4767");
             * albums.addProperty("businessLineID", "1000");
             * albums.addProperty("transmissionTime",
             * "2017-12-19T06:32:31.4141038-05:00"); // create an array called
             * datasets JsonArray datasets = new JsonArray();
             *
             * // create a dataset JsonObject dataset = new JsonObject();
             * dataset.addProperty("orderID", -1);
             * dataset.addProperty("customerOrderID", "");
             * dataset.addProperty("firstName", "");
             * dataset.addProperty("lastName", "");
             * dataset.addProperty("street1", "");
             * dataset.addProperty("street2", ""); dataset.addProperty("city",
             * ""); dataset.addProperty("state", "");
             * dataset.addProperty("postalCode", "");
             * dataset.addProperty("phone", "");
             * dataset.addProperty("deliveryID", -1);
             * dataset.addProperty("customerDeliveryID", str);
             * dataset.addProperty("clientCustomerID", str);
             * dataset.addProperty("smsEnabled", false);
             * dataset.addProperty("serviceDate", str);
             * dataset.addProperty("serviceCompleted", str);
             * dataset.addProperty("sweepID", str);
             * dataset.addProperty("deliveryWindowID", str);
             * dataset.addProperty("packages", 0); dataset.addProperty("items",
             * 0); dataset.addProperty("pieceType", 0);
             * dataset.addProperty("serviceLevelID", str);
             * dataset.addProperty("cases", str); dataset.addProperty("weight",
             * 0.0); dataset.addProperty("length", str);
             * dataset.addProperty("width", str); dataset.addProperty("height",
             * str); dataset.addProperty("status", str);
             * dataset.addProperty("codExpected", str);
             * dataset.addProperty("locationCode", str);
             * dataset.addProperty("notes", str);
             * dataset.addProperty("signature", str);
             * dataset.addProperty("serviceTotalCost", str);
             * dataset.addProperty("miles", str);
             * dataset.addProperty("refrigerated", str);
             *
             * datasets.add(null); datasets.add(dataset);
             *
             * albums.add("deliveries", datasets);
             *
             * // create the gson using the GsonBuilder. Set pretty printing
             * on. Allow // serializing null and set all fields to the Upper
             * Camel Case Gson gson = new
             * GsonBuilder().setPrettyPrinting().serializeNulls().create();//.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
             * System.out.println("---Gson---");
             *
             * entity = new StringEntity(gson.toJson(albums));
             * System.out.println(IOUtils.toString(entity.getContent()));
             *
             *
             * System.out.println("******************DATE
             * Format***************************"); System.out.println("Date
             * Format "+DateUtil.dateToString(new
             * Date(),"yyyy-MM-dd'T'HH:mm:ss.SSS"));*
             */
            
//$2a$10$HV/z1bZYK1.jBx3.KaDN4.fj65xuRsuPcnGL.lIDJyFAc0J0h/oeC
//z_c5rcdNpx
            
            
            String oldPassword = "$2a$10$9fJqZ0IkyhTk6DJQlRBMieI7Kn7EOmLyOh0j3JUw3F9rlpuE64C8O";
            String password = "ltuvqLv5$g";
            String psw = RandomString.generatePassword();
            
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, oldPassword)) {
                System.out.println("");
            } 
//            else {
//                String hashedPassword = passwordEncoder.encode("RxDirect@1234");
                System.out.println(passwordEncoder.encode(psw));
                System.out.println(psw);
//                System.out.println("BCryptPasswordEncoder#-> " + psw);
//                System.out.println("BCryptPasswordEncoder#-> " + hashedPassword);
//            }
        } catch (Exception e) {
        }
    }
}
