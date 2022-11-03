///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ssa.cms.dto;
//
//import com.ssa.cms.model.DrugBrand;
////import com.ssa.cms.model.DrugCategory;
//import com.ssa.cms.model.DrugGenericTypes;
//import com.ssa.cms.model.DrugTherapyClass;
//import com.ssa.cms.service.PatientProfileService;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
///**
// *
// * @author Haider Ali
// */
//public class DrugModelDtoConversion {
//
//    private static final Log logger = LogFactory.getLog(PatientProfileService.class.getName());
//    
//    public DrugModelDtoConversion() {
//    }
//    
////    public List<DrugCategoryDTO> modelToDtoDrugCategory(List drugCategories){
////    
////        List<DrugCategoryDTO> lst_DrugCategoryDTO = new ArrayList<>();
////        for(int i = 0;i < drugCategories.size();i++){
//////            DrugCategory drugCategory = (DrugCategory)drugCategories.get(i);
////            DrugCategoryDTO drugCategoryDTO = new DrugCategoryDTO();
//////            drugCategoryDTO.setId(drugCategory.getId());
//////            drugCategoryDTO.setDrugCategoryName(drugCategory.getDrugCategoryName());
//////            List<DrugTherapyClassDTO> lst_drugTherapyClassDTO = modelToDtoDrugTherapyClassDTO(drugCategory.getDrugTherapyClass());
//////            drugCategoryDTO.setDrugTherapyClass(lst_drugTherapyClassDTO);
////            
////            lst_DrugCategoryDTO.add(drugCategoryDTO);
////        }
////        return lst_DrugCategoryDTO;
////    }
////    
//    
//    
////    private List<DrugTherapyClassDTO> modelToDtoDrugTherapyClassDTO(Set<DrugTherapyClass> a_drugTherapyClass){
////    
////        List<DrugTherapyClassDTO> lst_drugTherapyClassDTO = new ArrayList<>();
////        
////        for(DrugTherapyClass drugTherapyClass : a_drugTherapyClass){
////        
////            DrugTherapyClassDTO drugTherapyClassDTO = new DrugTherapyClassDTO();
////            drugTherapyClassDTO.setId(drugTherapyClass.getId());
////            drugTherapyClassDTO.setDrugTherapyClassName(drugTherapyClass.getDrugTherapyClassName());
////            drugTherapyClassDTO.setDrugGenericTypes(modelToDtoDrugGenericTypesDTO(drugTherapyClass.getDrugGenericTypes()));
////            lst_drugTherapyClassDTO.add(drugTherapyClassDTO);
////        }
////        return lst_drugTherapyClassDTO;
////    }
//    
//    private List<DrugGenericTypesDTO> modelToDtoDrugGenericTypesDTO(Set<DrugGenericTypes> a_drugGenericTypes){
//    
//        List<DrugGenericTypesDTO> lst_drugGenericTypesDTO = new ArrayList<>();
//        
//        for(DrugGenericTypes drugGenericTypes : a_drugGenericTypes){
//        
//            DrugGenericTypesDTO drugGenericTypesDTO = new DrugGenericTypesDTO();
//            drugGenericTypesDTO.setId(drugGenericTypes.getId());
//            drugGenericTypesDTO.setDrugGenericName(drugGenericTypes.getDrugGenericName());
//            drugGenericTypesDTO.setDrugBrand(modelToDtoDrugBrandDTO(drugGenericTypes.getDrugBrand()));
//            
//            lst_drugGenericTypesDTO.add(drugGenericTypesDTO);
//        }
//        
//        return lst_drugGenericTypesDTO;
//    }
//    
//    private List<DrugBrandDTO> modelToDtoDrugBrandDTO(Set<DrugBrand> a_drugBrand){
//    
//        List<DrugBrandDTO> lst_drugBrandDTO = new ArrayList<>();
//        
//        for(DrugBrand drugBrand : a_drugBrand){
//        
//            DrugBrandDTO drugBrandDTO = new DrugBrandDTO();
//            drugBrandDTO.setId(drugBrand.getId());
//            drugBrandDTO.setDrugBrandName(drugBrand.getDrugBrandName());
//            
//            lst_drugBrandDTO.add(drugBrandDTO);
//        }
//        return lst_drugBrandDTO;
//    }
//    
//    /**
//     * This method used for search
//     * @param drugCategories
//     * @return 
//     */
//    public List<DrugCategoryDTO> modelToDtoDrugCategorySearch(List drugCategories) {
//
//        List lst_DrugCategory = new ArrayList();
//        if (drugCategories != null && drugCategories.size() > 0) {
//             Object object = drugCategories.get(0);
//
//            /*********************  DrugCategory  *********************/
//             if (object instanceof DrugCategory) {
//                lst_DrugCategory.add(object);
//            }
//
//             /*********************  DrugTherapyClass  *********************/
//            if (object instanceof DrugTherapyClass) {
//            
//                DrugTherapyClass drugTherapyClass = (DrugTherapyClass)object;
//                DrugCategory drugCategory = drugTherapyClass.getDrugCategory();
//                Set<DrugTherapyClass> setDrugTherapyClass = new HashSet<>();
//                setDrugTherapyClass.add(drugTherapyClass);
//                drugCategory.setDrugTherapyClass(setDrugTherapyClass);
//                
//                lst_DrugCategory.add(drugCategory);
//            }
//            
//            /*********************  DrugGenericTypes  *********************/
//            
//            if (object instanceof DrugGenericTypes) {
//            
//                DrugGenericTypes drugGenericTypes = (DrugGenericTypes)object;
//                
//                DrugTherapyClass drugTherapyClass = drugGenericTypes.getDrugTherapyClass();
//                
//                Set<DrugGenericTypes> setDrugGenericTypes = new HashSet<>();
//                setDrugGenericTypes.add(drugGenericTypes);
//                
//                drugTherapyClass.setDrugGenericTypes(setDrugGenericTypes);
//                
//                DrugCategory drugCategory = drugTherapyClass.getDrugCategory();
//                
//                Set<DrugTherapyClass> setDrugTherapyClass = new HashSet<>();
//                setDrugTherapyClass.add(drugTherapyClass);
//                drugCategory.setDrugTherapyClass(setDrugTherapyClass);
//                
//                lst_DrugCategory.add(drugCategory);
//            }
//            
//            /*********************  Brand Name  *********************/
//            if (object instanceof DrugBrand) {
//            
//                DrugBrand drugBrand = (DrugBrand)object;
//                DrugGenericTypes drugGenericTypes = drugBrand.getDrugGenericTypes();
//                
//                Set<DrugBrand> setDrugBrand = new HashSet<>();
//                setDrugBrand.add(drugBrand);
//                drugGenericTypes.setDrugBrand(setDrugBrand);
//                
//                
//                Set<DrugGenericTypes> setDrugGenericTypes = new HashSet<>();
//                setDrugGenericTypes.add(drugGenericTypes);
//                
//                DrugTherapyClass drugTherapyClass = drugGenericTypes.getDrugTherapyClass();
//                drugTherapyClass.setDrugGenericTypes(setDrugGenericTypes);
//                
//                DrugCategory drugCategory = drugTherapyClass.getDrugCategory();
//                Set<DrugTherapyClass> setDrugTherapyClass = new HashSet<>();
//                setDrugTherapyClass.add(drugTherapyClass);
//                drugCategory.setDrugTherapyClass(setDrugTherapyClass);
//                
//                lst_DrugCategory.add(drugCategory);
//            }
//        }
//        return this.modelToDtoDrugCategory(lst_DrugCategory);
//    }
//    
//}
