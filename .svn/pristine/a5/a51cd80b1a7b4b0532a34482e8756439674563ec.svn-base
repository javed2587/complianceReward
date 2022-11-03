/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.common;

import com.ssa.cms.model.DrugBrand;
//import com.ssa.cms.model.DrugCategory;
import com.ssa.cms.model.DrugGenericTypes;
//import com.ssa.cms.model.DrugTherapyClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ISPIN
 */
public class JsonRequestParser {
    
    private static final Log logger = LogFactory.getLog(JsonRequestParser.class.getName());
//    public static DrugCategory parseDrugCategoryRequest(String str_jsonRequest){
//    
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            
//            JsonNode rootNode = objectMapper.readTree(str_jsonRequest);
//            
//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
//            
//            DrugCategory drugCategory = new DrugCategory();
//            drugCategory.setDrugCategoryName(str_name);
//            return drugCategory;
//        }catch(Exception ex){
//             logger.error("Exception: JsonRequestParser -> parseDrugCategoryRequest", ex);
//        }
//        return null;
//    }
    
//    public static DrugTherapyClass parseDrugTherapyClassRequest(String str_jsonRequest){
//    
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            
//            JsonNode rootNode = objectMapper.readTree(str_jsonRequest);
//            
//            JsonNode catIdNode = rootNode.path("catId");
//            Integer i_catId = catIdNode.asInt();
//            
//            JsonNode nameNode = rootNode.path("name");
//            String str_name = nameNode.asText();
//            
//            DrugCategory drugCategory = new DrugCategory();
//           drugCategory.setId(i_catId);
//           
//           DrugTherapyClass drugTherapyClass = new DrugTherapyClass();
//           drugTherapyClass.setDrugTherapyClassName(str_name);
//           drugTherapyClass.setDrugCategory(drugCategory);
//           
//            return drugTherapyClass;
//        }catch(Exception ex){
//             logger.error("Exception: JsonRequestParser -> parseDrugTherapyClassRequest", ex);
//        }
//        return null;
//    }
    
    public static DrugGenericTypes parseDrugGenericTypesRequest(String str_jsonRequest){
    
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            
            JsonNode rootNode = objectMapper.readTree(str_jsonRequest);
            
            JsonNode therapyClassIdNode = rootNode.path("catId");
            Integer i_therapyClassId = therapyClassIdNode.asInt();
            
            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();
            
//            DrugTherapyClass drugTherapyClass = new DrugTherapyClass();
//           drugTherapyClass.setId(i_therapyClassId);
           
           DrugGenericTypes drugGenericTypes = new DrugGenericTypes();
           drugGenericTypes.setDrugGenericName(str_name);
//           drugGenericTypes.setDrugTherapyClass(drugTherapyClass);
           
            return drugGenericTypes;
        }catch(Exception ex){
             logger.error("Exception: JsonRequestParser -> parseDrugGenericTypesRequest", ex);
        }
        return null;
    }
    
    public static DrugBrand parseDrugBrandRequest(String str_jsonRequest){
    
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            
            JsonNode rootNode = objectMapper.readTree(str_jsonRequest);
            
            JsonNode therapyClassIdNode = rootNode.path("catId");
            Integer i_genericNameId = therapyClassIdNode.asInt();
            
            JsonNode nameNode = rootNode.path("name");
            String str_name = nameNode.asText();

            DrugGenericTypes drugGenericTypes = new DrugGenericTypes();
           drugGenericTypes.setId(i_genericNameId);
           
           DrugBrand drugBrand = new DrugBrand();
           drugBrand.setDrugBrandName(str_name);
           drugBrand.setDrugGenericTypes(drugGenericTypes);
           
            return drugBrand;
        }catch(Exception ex){
             logger.error("Exception: JsonRequestParser -> parseDrugGenericTypesRequest", ex);
        }
        return null;
    }
}
