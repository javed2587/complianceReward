/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.service;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.bean.drug.setup.DrugSetupAddBean;
import com.ssa.cms.bean.drug.setup.DrugSetupDrugBean;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.DrugDAO;
import com.ssa.cms.dto.DrugCategoryListDTO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.model.*;
//import com.ssa.cms.model.DrugCategory;
//import com.ssa.cms.model.DrugTherapyClass;
//import com.ssa.cms.model.DrugUnits;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ISPIN
 */
@Service
@Transactional
public class DrugService {
    
    @Autowired
    private PatientProfileService patientProfileService;
    @Autowired
    private DrugDAO drugDAO;

    private static final Log logger = LogFactory.getLog(DrugService.class.getName());
    
//    public DrugCategory saveDrugCategory(DrugCategory drugCategory) {
//        
//        try {
//            
//            drugCategory.setCreatedBy(0);
//            drugCategory.setCreatedOn(new Date());
//            drugCategory.setUpdatedBy(0);
//            drugCategory.setUpdatedOn(new Date());
//            drugDAO.save(drugCategory);
//        } catch (Exception e) {
//            logger.error("Exception: DrugService -> saveDrugCategory", e);
//            return null;
//        }
//        return drugCategory;
//    }
//    
//    public DrugTherapyClass saveDrugTherapyClass(DrugTherapyClass drugTherapyClass) {
//        
//        try {
//            
//            drugTherapyClass.setCreatedBy(0);
//            drugTherapyClass.setCreatedOn(new Date());
//            drugTherapyClass.setUpdatedBy(0);
//            drugTherapyClass.setUpdatedOn(new Date());
//            drugDAO.save(drugTherapyClass);
//            return drugTherapyClass;
//        } catch (Exception e) {
//            logger.error("Exception: DrugService -> saveDrugTherapyClass", e);
//            return null;
//        }
//        
//    }

    public DrugDetail2 saveNewDrug(DrugDetail2 drugNew) {

        try {
            drugDAO.save(drugNew);
            return drugNew;
        } catch (Exception e) {
            logger.error("Exception: DrugService -> saveDrugGenericTypes", e);
            return null;
        }

    }

    public DrugGenericTypes saveDrugGenericTypes(DrugGenericTypes drugGenericTypes) {
        
        try {
            
            drugGenericTypes.setCreatedBy(0);
            drugGenericTypes.setCreatedOn(new Date());
            drugGenericTypes.setUpdatedBy(0);
            drugGenericTypes.setUpdatedOn(new Date());
            drugDAO.save(drugGenericTypes);
            return drugGenericTypes;
        } catch (Exception e) {
            logger.error("Exception: DrugService -> saveDrugGenericTypes", e);
            return null;
        }
        
    }
    
    public DrugBrand saveDrugBrand(DrugBrand drugBrand) {
        
        try {
            
            drugBrand.setCreatedBy(0);
            drugBrand.setCreatedOn(new Date());
            drugBrand.setUpdatedBy(0);
            drugBrand.setUpdatedOn(new Date());
            drugDAO.save(drugBrand);
            return drugBrand;
        } catch (Exception e) {
            logger.error("Exception: DrugService -> saveDrugBrand", e);
            return null;
        }
        
    }
    
    public boolean saveDrug(DrugSetupAddBean drugSetupAddBean) {
        boolean isSaved;
        try {
            
            if (drugSetupAddBean != null) {
                List<DrugSetupDrugBean> lst_drugSetupDrugBean = drugSetupAddBean.getDrugSetupDrugBean();
                if (lst_drugSetupDrugBean != null && lst_drugSetupDrugBean.size() > 0) {
                    for (DrugSetupDrugBean drugSetupDrugBean : lst_drugSetupDrugBean) {
                        Drug drug = beanToEntityDrug(drugSetupAddBean, drugSetupDrugBean);
                        if (drugSetupDrugBean.isDeleteRecord()) {
                            drugDAO.delete(drug);
                        } else {
                            drugDAO.save(drug);
                        }
                    }
                }
            }
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: DrugService -> saveDrug", e);
            isSaved = false;
        }
        return isSaved;
    }
    
    private Drug beanToEntityDrug(DrugSetupAddBean drugSetupAddBean, DrugSetupDrugBean drugSetupDrugBean) {
        
        Drug drug = new Drug();
        if (drugSetupDrugBean.getDrugId() != null && !drugSetupDrugBean.getDrugId().isEmpty()) {
            drug.setDrugId(Integer.parseInt(drugSetupDrugBean.getDrugId()));
        }
        drug.setStrength(drugSetupDrugBean.getStrength());
        drug.setDrugType(drugSetupDrugBean.getType());
        drug.setDrugGcn(drugSetupDrugBean.getGcn());
        drug.setDrugGpi(drugSetupDrugBean.getGpi());
        drug.setRouteDescription(drugSetupDrugBean.getRoute());
        if (drugSetupDrugBean.getMac() != null && !drugSetupDrugBean.getMac().isEmpty()) {
            drug.setDrugMacPrice(Double.parseDouble(drugSetupDrugBean.getMac()));
        }
        if (drugSetupDrugBean.getAwp() != null && !drugSetupDrugBean.getAwp().isEmpty()) {
            drug.setDrugAwpPrice(Double.parseDouble(drugSetupDrugBean.getAwp()));
        }
        if (drugSetupDrugBean.getDeaSchedule() != null && !drugSetupDrugBean.getDeaSchedule().isEmpty()) {
            drug.setDaeSchedule(drugSetupDrugBean.getDeaSchedule());
        }
        
        DrugBrand drugBrand = new DrugBrand();
        drugBrand.setId(Integer.parseInt(drugSetupAddBean.getBrandNameId()));
        drug.setDrugBrand(drugBrand);
        
//        DrugUnits drugUnits = new DrugUnits();
//        drugUnits.setId(Integer.parseInt(drugSetupDrugBean.getMou()));
//        drug.setDrugUnits(drugUnits);
        
        drug.setCreatedBy(0);
        drug.setCreatedOn(new Date());
        drug.setUpdatedBy(0);
        drug.setUpdatedOn(new Date());
        
        return drug;
        
    }

    /**
     * Haider Ali Dated : 28-05-2016
     *
     * @param brandNameId
     * @return
     */
    public DrugSetupAddBean getDrugsByBrandNameById(Integer brandNameId) {
        
        DrugSetupAddBean drugSetupAddBean = null;
        try {
            
            List<Drug> lst_drugs = drugDAO.getDrugsByBrandNameId(brandNameId);
            if (lst_drugs != null && lst_drugs.size() > 0) {
                
                Drug drug = lst_drugs.get(0); // for getting category class, therapy class and generic type and brand name
                DrugBrand drugBrand = drug.getDrugBrand();
                DrugGenericTypes drugGenericTypes = drugBrand.getDrugGenericTypes();
//                DrugTherapyClass drugTherapyClass = drugGenericTypes.getDrugTherapyClass();
//                DrugCategory drugCategory = drugTherapyClass.getDrugCategory();
                
                drugSetupAddBean = new DrugSetupAddBean();
                drugSetupAddBean.setBrandNameId("" + drugBrand.getId());
                drugSetupAddBean.setBrandNameName(drugBrand.getDrugBrandName());
//                drugSetupAddBean.setDrugCategoryId("" + drugCategory.getId());
//                drugSetupAddBean.setDrugCategoryName(drugCategory.getDrugCategoryName());
                drugSetupAddBean.setDrugSetupDrugBean(entityDrugToDrugSetupDrugBeanList(lst_drugs));
                drugSetupAddBean.setGenericNameId("" + drugGenericTypes.getId());
                drugSetupAddBean.setGenericNameName(drugGenericTypes.getDrugGenericName());
//                drugSetupAddBean.setTherapyClassId("" + drugTherapyClass.getId());
//                drugSetupAddBean.setTherapyClassName(drugTherapyClass.getDrugTherapyClassName());
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> getDrugBrandNameById", ex);
        }
        return drugSetupAddBean;
    }
    
    private List<DrugSetupDrugBean> entityDrugToDrugSetupDrugBeanList(List<Drug> drugsList) {
        
        List<DrugSetupDrugBean> lst_drugSetupDrugBean = new ArrayList<>();
        for (Drug drug : drugsList) {
            lst_drugSetupDrugBean.add(entityDrugToDrugSetupDrugBean(drug));
        }
        return lst_drugSetupDrugBean;
    }
    
    private DrugSetupDrugBean entityDrugToDrugSetupDrugBean(Drug drug) {
        
        DrugSetupDrugBean drugSetupDrugBean = new DrugSetupDrugBean();
        
        drugSetupDrugBean.setAwp("" + drug.getDrugAwpPrice());
        drugSetupDrugBean.setDeaSchedule(drug.getDaeSchedule());
        drugSetupDrugBean.setDrugId("" + drug.getDrugId());
        drugSetupDrugBean.setGcn(drug.getDrugGcn());
        drugSetupDrugBean.setGpi(drug.getDrugGpi());
        drugSetupDrugBean.setMac("" + drug.getDrugMacPrice().doubleValue());
//        drugSetupDrugBean.setMou("" + drug.getDrugUnits().getId());
        drugSetupDrugBean.setRoute(drug.getRouteDescription());
        drugSetupDrugBean.setStrength(drug.getStrength());
        drugSetupDrugBean.setType(drug.getDrugType());
        
        return drugSetupDrugBean;
        
    }

    /**
     *
     * @param drugBrandName
     * @return
     */
    public List<DrugCategoryListDTO> searchDrugBrandByName(String drugBrandName) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugBasic> lst_drugBrand = drugDAO.searchDrugBasicByBrandName(drugBrandName);
            for (DrugBasic drugBasic : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugCategoryListDTO.setBrandNameId("" + drugBasic.getDrugBasicId());
                drugCategoryListDTO.setBrandName(drugBasic.getBrandName());
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public List<String> populateDrugByNameSearch(String drugBrandName) {
        
        List<String> lstDrug = new ArrayList<>();
        try {
            
            List<DrugBasic> lst_drugBrand = drugDAO.searchDrugBrandByName(drugBrandName);
            Map map = new HashMap();
            for (DrugBasic drugBrand : lst_drugBrand) {
                if (!map.containsKey(drugBrand.getBrandName())) {
                    lstDrug.add(drugBrand.getBrandName());
                    map.put(drugBrand.getBrandName(), drugBrand.getBrandName());
                }
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lstDrug;
    }
    //////////////////////////////////////////////////

    /**
     *
     * @param a_iDrugBrandId
     * @return
     */
    public List<DrugCategoryListDTO> searchDrugByDrugBrandId(Integer a_iDrugBrandId) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<Drug> lst_drugBrand = drugDAO.searchDrugTypesByDrugBrandId(a_iDrugBrandId);
            for (Drug drug : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
//                drugModelToDrugCategoryListDTO(drug, drugCategoryListDTO);
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public List<DrugCategoryListDTO> searchDrugByDrugName(String brandName) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugDetail> lst_drugBrand = drugDAO.findByNestedProperty(new DrugDetail(), "drugBasic.brandName",
                    brandName, Constants.HIBERNATE_EQ_OPERATOR, "", 0);//.searchDrugFormByExactName(brandName);
            Map map = new HashMap();
            for (DrugDetail drugdetail : lst_drugBrand) {
                if (!map.containsKey(drugdetail.getDrugForm().getDrugFormId())) {
                    DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                    drugCategoryListDTO.setBrandNameId("" + drugdetail.getDrugForm().getDrugFormId());
                    drugCategoryListDTO.setType(drugdetail.getDrugForm().getFormDescr());
                    map.put(drugdetail.getDrugForm().getDrugFormId(), drugdetail.getDrugForm().getFormDescr());
                    lst_drugCategoryListDTO.add(drugCategoryListDTO);
                }
                
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }

    /**
     *
     * @param a_iDrugBrandId
     * @param a_drugType
     * @return
     */
    public List<DrugCategoryListDTO> searchDrugByDrugType(Integer a_iDrugBrandId, String a_drugType) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugDetail> lst_drugBrand = drugDAO.getDrugDetailListByDrugBasicId(a_iDrugBrandId);
            for (DrugDetail drugDetail : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugCategoryListDTO.setBrandNameId("" + drugDetail.getDrugNDC());
                drugCategoryListDTO.setType(drugDetail.getDrugForm().getFormDescr());
                drugCategoryListDTO.setStrength(drugDetail.getStrength());
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public List<DrugCategoryListDTO> searchDrugByDrugType(String a_iDrugBrandName, String a_drugType) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugDetail> lst_drugBrand = drugDAO.searchDrugDetailByDrugType(a_iDrugBrandName, a_drugType);
            for (DrugDetail drug : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugModelToDrugCategoryListDTO(drug, drugCategoryListDTO);
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public int updateDrugInfoInOrder(PatientProfile patientProfile,
            String a_iDrugBrandName, String a_drugType, String drugStrength, String qty, String oid,
            String rewardHistoryDesc, boolean rewardpointDeduction) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            drugStrength = drugStrength.replace(String.valueOf((char) 160), " ");
            drugStrength = drugStrength.replace(String.valueOf((char) 65533), " ");
            String strengthWithoutSpace = drugStrength;
//            String[] arr = StringUtils.split(drugStrength);
//            String unit = "MG";
//            if (arr != null && arr.length > 1) {
//                unit = arr[1];
//                strengthWithoutSpace = arr[0] + arr[1];
//            }

            List<DrugDetail> lst_drugBrandDetails = drugDAO.searchDrugBasicPrice(a_iDrugBrandName, a_drugType, drugStrength);
            if (lst_drugBrandDetails != null && lst_drugBrandDetails.size() > 0) {
                DrugDetail drugDetail = lst_drugBrandDetails.get(0);
                Order o = (Order) this.drugDAO.getObjectByIdForString(new Order(), oid);
                if (o != null) {
                    //o.setDrug(drug);
                    o.setDrugDetail(drugDetail);
                    o.setDrugName(a_iDrugBrandName);
                    o.setStrength(strengthWithoutSpace);
                    o.setDrugType(a_drugType);
                    o.setQty(qty);
                    if (patientProfile != null && drugDetail != null) {
                        OrderDetailDTO orderDTO = patientProfileService.getPlaceRxOrderDetailsWs(
                                patientProfile, drugDetail.getDrugNDC(), AppUtil.getSafeInt(qty, 0), a_drugType, strengthWithoutSpace);
                        if (orderDTO != null && orderDTO.getDrugDetail() != null) {
                            o.setDrugPrice(orderDTO.getDrugDetail().getTotalPrice().doubleValue());
                            o.setPayment(orderDTO.getDrugDetail().getFinalPrice().doubleValue());
                            o.setRedeemPoints("" + orderDTO.getDrugDetail().getRedeemedPoints());
                            o.setRedeemPointsCost(orderDTO.getDrugDetail().getRedeemedPointsPrice().doubleValue());
                            
                        }
                        PatientDeliveryAddress address = this.patientProfileService.getDefaultDeliveryPreference(patientProfile.getPatientProfileSeqNo());
                        DeliveryPreferences pref = address.getDeliveryPreferences();
                        if (address != null) {
                            String fee = patientProfileService.getZipCodeCalculations(address.getZip(), patientProfile.getPatientProfileSeqNo(), address.getDeliveryPreferences().getId());
                            BigDecimal feeNumeric = CommonUtil.getStrToBigDecimal(fee);
                            o.setHandLingFee(AppUtil.getSafeDouble(fee, 0d));
                        }
                        if (rewardpointDeduction) {
                            List lst = this.drugDAO.findByProperty(new RewardPoints(),
                                    "type", "Transfer Rx", Constants.HIBERNATE_EQ_OPERATOR, "", 0);
                            if (lst != null && lst.size() > 0) {
                                //RewardPoints rewardPoints=(RewardPoints) lst.get(0);
                                RewardHistory history = new RewardHistory();
                                history.setPatientId(patientProfile.getPatientProfileSeqNo());
                                history.setDescription(rewardHistoryDesc);
                                history.setType("MINUS");
                                history.setPoint(orderDTO.getDrugDetail().getRedeemedPoints().intValue());
                                history.setCreatedDate(new Date());
                                this.drugDAO.save(history);
                            }
                        }
                    }
                    this.drugDAO.saveOrUpdate(o);
                }
            } else {
                return 0;
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return 1;
    }
    
    private void drugBrandModelToDrugCategoryListDTO(DrugBrand drugBrand, DrugCategoryListDTO drugCategoryListDTO) {
        
        drugCategoryListDTO.setBrandNameId("" + drugBrand.getId());
        drugCategoryListDTO.setBrandName(drugBrand.getDrugBrandName());
        
    }
    
//    private void drugModelToDrugCategoryListDTO(Drug drug, DrugCategoryListDTO drugCategoryListDTO) {
//        
//        drugCategoryListDTO.setBrandNameId("" + drug.getDrugBrand().getId());
//        drugCategoryListDTO.setType(drug.getDrugType());
//        drugCategoryListDTO.setStrength(drug.getStrength());
//        drugCategoryListDTO.setDrugUnitName(drug.getDrugUnits().getName());
//        drugCategoryListDTO.setStrengthWithUnit(drug.getStrength() + " " + drug.getDrugUnits().getName());
//    }

    /**
     *
     * @param a_iDrugBrandId
     * @return
     */
    public List<DrugCategoryListDTO> searchDrugsByDrugBrandId(Integer a_iDrugBrandId) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<Drug> lst_drugBrand = drugDAO.searchDrugsByDrugBrandId(a_iDrugBrandId);
            for (Drug drug : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
//                drugModelToDrugCategoryListDTO(drug, drugCategoryListDTO);
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public boolean saveInstantNotification(Integer patientId, String messageText) {
        boolean isSaved = false;
        try {
            NotificationMessages notificationMessages = new NotificationMessages();
            notificationMessages.setPatientProfile(new PatientProfile(patientId));
            notificationMessages.setSubject("Pharmacy Portal Message");
            notificationMessages.setMessageText(messageText);
            notificationMessages.setStatus(Constants.SUCCESS);
            notificationMessages.setIsRead(false);
            notificationMessages.setCreatedOn(new Date());
            drugDAO.save(notificationMessages);
        } catch (Exception e) {
            isSaved = false;
            logger.error("Exception:: ", e);
        }
        return isSaved;
    }
    
    public DrugCategoryListDTO searchDrugsByNameStrengthType(String brand, String strength, String type) throws Exception {
        DrugCategoryListDTO dto = new DrugCategoryListDTO();
        DrugDetail drug = this.drugDAO.searchDrugsByNameStrengthType(brand, strength, type);
        drugModelToDrugCategoryListDTO(drug, dto);
        return dto;
    }
    
    public DrugCategoryListDTO searchDrugDetailByDrugNameTypeStrength(
            String drugBrandName, String a_drugType, String strength, int qty) throws Exception {
        DrugDetail drug = this.drugDAO.searchDrugDetailByDrugNameTypeStrength(drugBrandName, a_drugType, strength);
        DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
        if (drug != null) {
            drugCategoryListDTO.setAcqCost(drug.getBasePrice() * qty);
            drugCategoryListDTO.setDrugDetailId(drug.getDrugDetailId());
        }
        return drugCategoryListDTO;
    }
    
    public DrugCategoryListDTO searchDrugDetailByDrugNameTypeStrength(
            String[] drugName, String a_drugType, String strength, int qty) throws Exception {
        DrugDetail drug = null;
        List<DrugDetail> drugDetails = null;
        if (drugName != null && drugName.length > 0) {
            List lstObj = new ArrayList();
            
            if (drugName.length == 1) {
                BusinessObject obj = new BusinessObject("drugBasic.brandName", drugName[0], Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("drugBasic.brandIndicator", "BRAND", Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("strength", strength, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("drugForm.formDescr", a_drugType, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("archived", "N", Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                drugDetails = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            } else {
                BusinessObject obj = new BusinessObject("drugBasic.brandName", drugName[0], Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                obj = new BusinessObject("drugBasic.drugGeneric.genericName", drugName[1], Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                
                obj = new BusinessObject("strength", strength, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);                
                
                obj = new BusinessObject("drugForm.formDescr", a_drugType, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                
                obj = new BusinessObject("archived", "N", Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
                
                drugDetails = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            }
        }
        if (drugDetails != null && drugDetails.size() > 0) {
            drug = drugDetails.get(0);
        }
        DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
        if (drug != null) {
            float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(drug.getBasePrice()), 0f);
            //drugCategoryListDTO.setAcqCost(drug.getBasePrice() * qty);
            drugCategoryListDTO.setAcqCost(basePrice * qty);
            drugCategoryListDTO.setDrugDetailId(drug.getDrugDetailId());
        }
        return drugCategoryListDTO;
    }
    
    private void drugModelToDrugCategoryListDTO(DrugDetail drug, DrugCategoryListDTO drugCategoryListDTO) {
        
        if (drug != null) {
            drugCategoryListDTO.setNdc("" + drug.getDrugNDC());
            //drugCategoryListDTO.setBrandNameId("" + drug.getDrugId());
            drugCategoryListDTO.setBrandName(drug.getBrandName());
            // drugCategoryListDTO.setType(drug.getForm());
            drugCategoryListDTO.setStrength(drug.getStrength());
            drugCategoryListDTO.setDefaultQty(drug.getDefQty());
            //drugCategoryListDTO.setBasePrice(drug.getBasePrice());
            drugCategoryListDTO.setBasePrice(AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(drug.getBasePrice()), 0f));

            //      drugCategoryListDTO.setDrugUnitName(drug.getDrugUnits().getName());
            //      drugCategoryListDTO.setStrengthWithUnit(drug.getStrength() + " "+drug.getDrugUnits().getName());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    private void populateDrugBasic(DrugBasic drugBasic, Row row, int creatorId) {
//        try
//        {
        if (drugBasic == null) {
            drugBasic = new DrugBasic();
        }

        //drugBasic.setDemandBrand(getProperCellValue(row.getCell(4)));
        drugBasic.setBrandName(getProperCellValue(row.getCell(8)));
        drugBasic.setGenericName(row.getCell(9).getStringCellValue());
//        drugBasic.setTherapeuticCategory(row.getCell(9).getStringCellValue());
//        drugBasic.setBrandIndicator(getProperCellValue(row.getCell(4)));
        drugBasic.setCreatedBy(creatorId);
        drugBasic.setCreatedOn(new Date());
        
    }
    
    private void populateDrugDetail(DrugDetail drugDetail, Row row, int creatorId, Map drugPackingMap, Map drugFormMap) {
//        try
//        {
        if (drugDetail == null) {
            drugDetail = new DrugDetail();
        }
        if (drugPackingMap == null) {
            drugPackingMap = new HashMap();
        }
        if (drugFormMap == null) {
            drugFormMap = new HashMap();
        }
        //DataFormatter formatter = new DataFormatter();
        drugDetail.setDrugNDC(0L);//AppUtil.getSafeLong(getProperCellValue(row.getCell(23)), 0L));
        drugDetail.setDrugGCN(AppUtil.getSafeLong(getProperCellValue(row.getCell(3)), 0L)); // new column in drug file
        String packing = getProperCellValue(row.getCell(14)); // new column in drug file
        drugDetail.setPackingDesc(packing);
        drugDetail.setDrugGPI(AppUtil.getSafeLong(getProperCellValue(row.getCell(28)), 0L)); // new column in drug file
        drugDetail.setPackageSizeValues(getProperCellValue(row.getCell(12))); // new column in drug file
        drugDetail.setStrength(getProperCellValue(row.getCell(20))); // new column in drug file
        drugDetail.setCreatedBy(creatorId);
        drugDetail.setCreatedOn(new Date());
        drugDetail.setUpdatedOn(new Date());
        drugDetail.setUpdatedBy(creatorId);
        drugDetail.setArchived("N");
        drugDetail.setBrandName(getProperCellValue(row.getCell(8))); // new column in drug file
        drugDetail.setGnn(getProperCellValue(row.getCell(15))); // new column in drug file
        drugDetail.setGenericName(row.getCell(9).getStringCellValue());
        Double d = AppUtil.getSafeDouble(getProperCellValue(row.getCell(14)), 0d); // dont know
        drugDetail.setDefQty(d > 0d ? d.intValue() : 1); //ps as 
        if (AppUtil.getSafeStr(drugDetail.getPackageSizeValues(), "").length() == 0) {
            drugDetail.setPackageSizeValues("" + drugDetail.getDefQty());
        }
        
       
        
        drugDetail.setDemandBrand(""); // dont know
        
        String inStock = "Y";//AppUtil.getSafeStr(getProperCellValue(row.getCell(1)), "N");
        drugDetail.setInStock(inStock.length() > 1 ? "N" : inStock);
        String requiresHandDelivery = "";//getProperCellValue(row.getCell(2)); //notify
        String monthRxExpiration = "";//getProperCellValue(row.getCell(3)); //notify
        drugDetail.setRequiresHandDelivery(AppUtil.getSafeStr(requiresHandDelivery, "N"));
        drugDetail.setMonthRxExpiration(AppUtil.getSafeStr(monthRxExpiration, "N"));
        drugDetail.setDemandBrand(""/*getProperCellValue(row.getCell(4))*/);
        drugDetail.setBrandName(""/*getProperCellValue(row.getCell(5))*/);
        
        float margin = 0f;//AppUtil.getSafeFloat(getProperCellValue(row.getCell(8)), 0f);//*100;//to convert fraction into percentage since column type is percentage & upon getting cell numeric value, it is divided by 100
//        margin=margin.replace("%", "");
        drugDetail.setMarginPercent(margin); //notify
//                  Cell cellQty=row.getCell(12);
//            final DataFormatter df = new DataFormatter();
            String qtyStr = "";//df.formatCellValue(cellQty);
            drugDetail.setDefQty(AppUtil.getSafeInt(qtyStr, 0));
        
        drugDetail.setBasePrice(0f);  // dont know
        
        float additionalFee = 0f;//AppUtil.getSafeFloat(getProperCellValue(row.getCell(17)), 0f); // dont know
        drugDetail.setAdditionalFee(additionalFee > 0f ? additionalFee : 0f);
        drugDetail.setGoodRx(0f/*AppUtil.getSafeFloat(getProperCellValue(row.getCell(21)), 0f)*/); // notify
        String form = "";//getProperCellValue(row.getCell(12)); // dont know
        
        drugDetail.setFormDesc(form);
        
        
        
        drugDetail.setDrugPackingDescSet(populateDrugPackingDesc(drugDetail, drugDetail.getPackageSizeValues(), creatorId));
        
        drugDetail.setMktSurcharge(0f/*AppUtil.getSafeFloat(getProperCellValue(row.getCell(26)), 0f)*/); //notify
//        
        if (!drugFormMap.containsKey(form)) {
            DrugForm drugForm = new DrugForm();
            drugForm.setFormDescr(form);
            drugForm.setCreatedBy(creatorId);
            drugForm.setCreatedOn(new Date());
            drugForm.setUpdatedOn(new Date());
            drugForm.setUpdatedBy(creatorId);
            drugFormMap.put(form, drugForm);
        }

        if (!drugPackingMap.containsKey(packing)) {
            DrugPacking drugPacking = new DrugPacking();
            drugPacking.setPackingDescr(packing);
            drugPacking.setCreatedBy(creatorId);
            drugPacking.setCreatedOn(new Date());
            drugPackingMap.put(packing, drugPacking);
        }
        //      }
//        catch(IllegalStateException e  )
//        {
//            System.out.println("EXCEPTION OCCURRED AT ROW "+row.getRowNum()+" REASON "+e.getMessage());
//        }
//        catch(NullPointerException e)
//        {
//            
//            System.out.println("NullPointerException EXCEPTION OCCURRED AT ROW "+row.getRowNum()+" REASON "+e.getMessage());
//            e.printStackTrace();
//        }
    }
    
    private String getProperCellValue(Cell cell) {
//        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
//            return "";
//        }
//        if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
//            byte errorValue = cell.getErrorCellValue();
//            switch (errorValue) {
//                case ERROR_DIV_0:
//                    return "#DIV/0!";
//                case ERROR_NA:
//                    return "#N/A";
//                case ERROR_NAME:
//                    return "#NAME?";
//                case ERROR_NULL:
//                    return "#NULL!";
//                case ERROR_NUM:
//                    return "#NUM!";
//                case ERROR_REF:
//                    return "#REF!";
//                case ERROR_VALUE:
//                    return "#VALUE!";
//                default:
//                    return "Unknown error value: " + errorValue + "!";
//            }
//        } else {
//            String s = new DataFormatter().formatCellValue(cell);
//            return AppUtil.getSafeStr(AppUtil.getSafeStr(s, "").replace("$", "").replace("%", ""), "");
//        }
        return "";
    }

    private static String printCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
               return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return  String.valueOf(cell.getDateCellValue());
                } else {
                    return  String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_FORMULA:
                return String.valueOf(cell.getCellFormula());
            case Cell.CELL_TYPE_BLANK:
                System.out.print("");
            default:
                System.out.print("");
        }

        return "";
    }

    /////////////////////////////////////////////////////////////////////////////////
    private void populateDrugGeneric(DrugGeneric drugGeneric, Row row, int creatorId) {
        if (drugGeneric == null) {
            drugGeneric = new DrugGeneric();
        }
        drugGeneric.setTherapeuticClass("");//row.getCell(9).getStringCellValue());
        drugGeneric.setGenericName(row.getCell(10).getStringCellValue());
        drugGeneric.setBrandNameOnly(AppUtil.getSafeStr(drugGeneric.getGenericName(), "").indexOf(Constants.BRAND_NAME_ONLY) >= 0 ? 1 : 0);
        drugGeneric.setCreatedBy(creatorId);
        drugGeneric.setCreatedOn(new Date());
    }

//    private void populateDrugDetail(DrugDetail drugDetail,Row row,int creatorId,Map drugPackingMap,Map drugFormMap)
//    {
//        try
//        {
//            if(drugDetail==null)
//            {
//                drugDetail=new DrugDetail();
//            }
//            if(drugPackingMap==null)
//            {
//                drugPackingMap=new HashMap();
//            }
//            if(drugFormMap==null)
//            {
//                drugFormMap=new HashMap();
//            }
//            drugDetail.setDrugNDC(AppUtil.getSafeLong(row.getCell(21).getStringCellValue(),0L));
//            drugDetail.setDrugGCN(AppUtil.getSafeLong(row.getCell(0).getStringCellValue(), 0L));
//            drugDetail.setDrugGPI(AppUtil.getSafeLong(row.getCell(5).getStringCellValue(), 0L));
//            drugDetail.setInStock(row.getCell(1).getStringCellValue());
//            drugDetail.setDemandBrand(row.getCell(2).getStringCellValue());
//            drugDetail.setBrandName(row.getCell(3).getStringCellValue());
//            drugDetail.setPackageSizeValues(row.getCell(4).getStringCellValue());
//            double margin=(row.getCell(6).getNumericCellValue())*100;//to convert fraction into percentage since column type is percentage & upon getting cell numeric value, it is divided by 100
//            //margin=margin.replace("%", "");
//            drugDetail.setMarginPercent(new Double(margin).floatValue());
//  //          Cell cellQty=row.getCell(12);
////            final DataFormatter df = new DataFormatter();
////            String qtyStr = df.formatCellValue(cellQty);
////            drugDetail.setDefQty(AppUtil.getSafeInt(qtyStr, 0));
//            drugDetail.setDefQty(new Double(row.getCell(12).getNumericCellValue()).intValue());
//            drugDetail.setBasePrice(AppUtil.getSafeFloat(row.getCell(13).getStringCellValue(),0f));
//            drugDetail.setStrength(row.getCell(9).getStringCellValue());
//            double additionalFee=row.getCell(15).getNumericCellValue();
//            drugDetail.setAdditionalFee(new Double(additionalFee).floatValue());
//            drugDetail.setGoodRx(new Double(row.getCell(19).getNumericCellValue()).floatValue());
//            String form=AppUtil.getSafeStr(row.getCell(10).getStringCellValue(),"");
//            String packing=AppUtil.getSafeStr(row.getCell(11).getStringCellValue(),"");
//            drugDetail.setFormDesc(form);
//            drugDetail.setPackingDesc(packing);
//            drugDetail.setDrugPackingDescSet(populateDrugPackingDesc(drugDetail,drugDetail.getPackageSizeValues(),creatorId));
//            drugDetail.setCreatedBy(creatorId);
//            drugDetail.setCreatedOn(new Date());
//            drugDetail.setArchived("N");
//
//
//            if(!drugFormMap.containsKey(form))
//            {
//                DrugForm drugForm=new DrugForm();
//                drugForm.setFormDescr(form);
//                drugForm.setCreatedBy(creatorId);
//                drugForm.setCreatedOn(new Date());
//                drugFormMap.put(form, drugForm);
//            }
//
//            if(!drugPackingMap.containsKey(packing))
//            {
//                DrugPacking drugPacking = new DrugPacking();
//                drugPacking.setPackingDescr(packing);
//                drugPacking.setCreatedBy(creatorId);
//                drugPacking.setCreatedOn(new Date());
//                drugPackingMap.put(packing,drugPacking);
//            }
//        }
////        catch(IllegalStateException e  )
////        {
////            System.out.println("EXCEPTION OCCURRED AT ROW "+row.getRowNum()+" REASON "+e.getMessage());
////        }
//        catch(NullPointerException e)
//        {
//            System.out.println("NullPointerException EXCEPTION OCCURRED AT ROW "+row.getRowNum()+" REASON "+e.getMessage());
//        }
//    }
    private Set<DrugPackingDesc> populateDrugPackingDesc(DrugDetail drugDetail, String packingDescStr, int creatorId) {
        Set<DrugPackingDesc> set = new HashSet<>();
        String[] arr = AppUtil.getSafeStr(packingDescStr, "").split(",");
        for (int i = 0; arr != null && i < arr.length; i++) {
            DrugPackingDesc desc = new DrugPackingDesc();
            desc.setDrugPackingSize(AppUtil.getSafeInt(arr[i], 0));
            desc.setCreatedBy(creatorId);
            desc.setCreatedOn(new Date());
            desc.setDrugDetail(drugDetail);
            set.add(desc);
        }
        return set;
    }
    
    public String saveDrugData(MultipartFile file, int creatorId) throws IOException, Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        String s = file.getContentType();
        if (!AppUtil.getSafeStr(s, "").equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new IllegalArgumentException("Only .xlsx files are allowed.");
        }
        Map<String, DrugGeneric> drugGenericMap = new HashMap();
        Map<String, DrugBasic> drugBasicMap = new HashMap();
        Map<String, DrugPacking> drugPackingMap = new HashMap();
        Map<String, DrugForm> drugFormMap = new HashMap();
        Map indexMap = new HashMap();
        List<DrugDetail> drugDetailsFailure = new ArrayList<>();
        
        for (int i = 0; i < Constants.drugListIndicesArr.length; i++) {
            indexMap.put(Constants.drugListIndicesArr[i], Constants.drugListIndicesArr[i]);
        }
        Map drugDetailMap = new HashMap();
        XSSFSheet sheet = populateDrugDataFromExcelFile(bis, drugGenericMap, drugBasicMap, drugPackingMap, drugFormMap,
                indexMap, drugDetailMap, 0, creatorId, drugDetailsFailure);
        //loadSheetPictrues(drugDetailMap, sheet, creatorId);
        int suceessRecords = sheet.getPhysicalNumberOfRows();
        
        bis.close();
//        synchronizedDrugPackingMap(drugPackingMap,creatorId);
        synchronizedDrugFormMap(drugFormMap, creatorId);
        saveMapData(drugPackingMap);
        saveMapData(drugFormMap);
        saveDrugGenericData(drugGenericMap, drugPackingMap, drugFormMap, creatorId);
        saveDrugBasicData(drugGenericMap, creatorId);
        saveDrugDetailData(drugGenericMap, drugPackingMap, drugFormMap, creatorId);
        
        saveDrugDetailsImportLogs(drugDetailsFailure, suceessRecords);
        
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    private void synchronizedDrugFormMap(Map<String, DrugForm> drugFormMap, int creatorId) {
        Set<String> keySet = drugFormMap.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            DrugForm drugForm = drugFormMap.get(key);
            DrugForm tmp = (DrugForm) this.drugDAO.findByPropertyUnique(new DrugForm(), "formDescr", drugForm.getFormDescr(),
                    Constants.HIBERNATE_EQ_OPERATOR);
            if (tmp != null) {
                drugForm.setDrugFormId(tmp.getDrugFormId());
                drugForm.setCreatedBy(tmp.getCreatedBy());
                drugForm.setCreatedOn(tmp.getCreatedOn());
                drugForm.setUpdatedBy(creatorId);
                drugForm.setUpdatedOn(new Date());
            }

            // System.out.println("key: " + key + " value: " + loans.get(key));
        }
        
    }
    
    private void synchronizedDrugPackingMap(Map<String, DrugPacking> drugPackingMap, int creatorId) {
        Set<String> keySet = drugPackingMap.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            DrugPacking drugPacking = drugPackingMap.get(key);
            DrugPacking tmp = (DrugPacking) this.drugDAO.findByPropertyUnique(new DrugPacking(), "packingDescr", drugPacking.getPackingDescr(),
                    Constants.HIBERNATE_EQ_OPERATOR);
            if (tmp != null) {
                drugPacking.setDrugPackingId(tmp.getDrugPackingId());
                drugPacking.setCreatedBy(tmp.getCreatedBy());
                drugPacking.setCreatedOn(tmp.getCreatedOn());
                drugPacking.setUpdatedBy(creatorId);
                drugPacking.setUpdatedOn(new Date());
            }

            // System.out.println("key: " + key + " value: " + loans.get(key));
        }
        
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public DrugDetailDocuments retireveDrugDocument(Long detailId, boolean pdf) throws Exception {
        DrugDetailDocuments doc = (DrugDetailDocuments) drugDAO.findByPropertyUnique(new DrugDetailDocuments(), "drugDetail.drugNDC", detailId,
                Constants.HIBERNATE_EQ_OPERATOR);
        
        if (doc == null) {
            throw new Exception("No such document is associated with this drug.");
        }
        
        return doc;
    }
    
    public String uploadDrugDocument(MultipartFile file, int creatorId, Long detailId, boolean pdf) throws IOException, Exception {
        
        String s = file.getContentType();
        DrugDetailDocuments docs = (DrugDetailDocuments) drugDAO.findByPropertyUnique(new DrugDetailDocuments(), "drugDetail.drugNDC",
                detailId, Constants.HIBERNATE_EQ_OPERATOR);
        DrugDetail detail = (DrugDetail) this.drugDAO.findRecordById(new DrugDetail(), detailId);//new DrugDetail();
        if (detail != null) {
            if (docs == null) {
                docs = new DrugDetailDocuments();
                //detail.setDrugNDC(detailId);
                docs.setDrugDetail(detail);
                docs.setCreatedBy(creatorId);
                docs.setCreatedOn(new Date());
                
            }
            
            if (pdf) {
                if (!s.equalsIgnoreCase("application/pdf")) {
                    throw new Exception("Please upload pdf document.");
                }
                docs.setPdfDoc(file.getBytes());
                docs.setContentType(s);
                detail.setPdf("1");
            } else {
                s = s.toLowerCase();
                if (s.indexOf("bmp") < 0 && s.indexOf("jpeg") < 0
                        && s.indexOf("jpg") < 0 && s.indexOf("png") < 0 && s.indexOf("bitmap") < 0 && s.indexOf("tiff") < 0) {
                    throw new Exception("Please upload image.");
                }
                docs.setImage(file.getBytes());
                docs.setContentTypeImage(s);
                detail.setImage("1");
                detail.setImgUpdatedOn(new Date());
            }
        }
        
        this.drugDAO.saveOrUpdate(docs);
        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    private void synchronizeDrugBasic(DrugBasic drugBasic, int creatorId) {
        List<BusinessObject> lstObj = new ArrayList();
        BusinessObject obj = new BusinessObject("brandName", drugBasic.getBrandName(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        obj = new BusinessObject("brandIndicator", drugBasic.getBrandIndicator(), Constants.HIBERNATE_EQ_OPERATOR);
        lstObj.add(obj);
        List<DrugBasic> drugBasicLst = drugDAO.findByNestedProperty(new DrugBasic(), lstObj, "", 0);
        DrugBasic tmp = drugBasicLst != null && drugBasicLst.size() > 0 ? drugBasicLst.get(0) : null;
        
        if (tmp != null) {
            drugBasic.setDrugBasicId(tmp.getDrugBasicId());
        }
        drugBasic.setUpdatedBy(creatorId);
        drugBasic.setUpdatedOn(new Date());
        
    }
    
    private void synchronizeDrugGeneric(DrugGeneric drugGeneric, int creatorId) {
        DrugGeneric tmp = (DrugGeneric) this.drugDAO.findByPropertyUnique(new DrugGeneric(), "genericName",
                drugGeneric.getGenericName(), Constants.HIBERNATE_EQ_OPERATOR);
        if (tmp != null) {
            drugGeneric.setDrugGenericID(tmp.getDrugGenericID());
        }
        drugGeneric.setUpdatedBy(creatorId);
        drugGeneric.setUpdatedOn(new Date());
        
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    private String saveDrugDetailData(Map<String, DrugGeneric> map, Map<String, DrugPacking> drugPackingMap,
            Map<String, DrugForm> drugFormMap, int creatorId) throws Exception {
        Set<String> keySet = map.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            DrugGeneric drugGeneric = map.get(key);
            Set<DrugBasic> set = drugGeneric.getDrugBasicSet();
            Iterator<DrugBasic> iterator = set.iterator();
            while (iterator.hasNext()) {
                DrugBasic drugBasic = iterator.next();
                Set<DrugDetail> detailSet = drugBasic.getDrugDetailSet();
                Iterator<DrugDetail> iteratorDetail = detailSet.iterator();
                while (iteratorDetail.hasNext()) {
                    DrugDetail drug = iteratorDetail.next();
                    int brandNameOnly = drug.getDrugBasic().getDrugGeneric().getGenericName().toUpperCase().contains("BRAND NAME ONLY") ? 1 : 0;
                    List bussObjLst = new ArrayList();
                    bussObjLst.add(new BusinessObject("drugBasic.brandName", drug.getDrugBasic().getBrandName(), Constants.HIBERNATE_EQ_OPERATOR));
                    if (brandNameOnly == 0) {
                        bussObjLst.add(new BusinessObject("drugBasic.drugGeneric.genericName", drug.getDrugBasic().getDrugGeneric().getGenericName(), Constants.HIBERNATE_EQ_OPERATOR));
                    } else {
                        bussObjLst.add(new BusinessObject("drugBasic.drugGeneric.brandNameOnly", 1, Constants.HIBERNATE_EQ_OPERATOR));
                    }
                    
                    bussObjLst.add(new BusinessObject("drugBasic.brandIndicator", drug.getDrugBasic().getBrandIndicator(), Constants.HIBERNATE_EQ_OPERATOR));
                    //bussObjLst.add(new BusinessObject("DrugNdc", drug.getDrugNDC(), Constants.HIBERNATE_EQ_OPERATOR));
                    bussObjLst.add(new BusinessObject("DrugGCN", drug.getDrugGCN(), Constants.HIBERNATE_EQ_OPERATOR));
                    //bussObjLst.add(new BusinessObject("DrugGPI", drug.getDrugGPI(), Constants.HIBERNATE_EQ_OPERATOR));
                    List lstObj = this.drugDAO.findByProperty(new DrugDetail(), bussObjLst, "", 0);
                    drug.setDrugForm(drugFormMap.get(drug.getFormDesc()));
                    //drug.setDrugPacking(drugPackingMap.get(drug.getPackingDesc()));
                    try {
                        if (lstObj == null || lstObj.size() == 0) {
                            this.drugDAO.save(drug);//.merge(drug);//.saveOrUpdate(drug);
                        } else {
                            DrugDetail detail = (DrugDetail) lstObj.get(0);
                            drug.setDrugDetailId(detail.getDrugDetailId());
                            drug.setImage(detail.getImage());
                            drug.setImgUrl(detail.getImgUrl());
                            drug.setImgUpdatedOn(detail.getImgUpdatedOn());
                            drug.setPdf(detail.getPdf());
                            drug.setPdfDocUrl(detail.getPdfDocUrl());
                            drug.setPdfDocUpdatedOn(detail.getPdfDocUpdatedOn());
                            drug.setDrugDoc(detail.getDrugDoc());
                            drug.setDrugDocUrl(detail.getDrugDocUrl());
                            drug.setDrugDocUpdatedOn(detail.getDrugDocUpdatedOn());
                            
                            this.drugDAO.saveOrUpdate(drug);//.update(drug);//.update(drug);
                        }
                        
                    } catch (Exception e) {
//                        lstObj = this.drugDAO.findByProperty(new DrugDetail(), bussObjLst, "", 0);
                        e.printStackTrace();
                    }
                }
                
            }
            
        }
        return "";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    private String saveDrugBasicData(Map<String, DrugGeneric> map, int creatorId) throws Exception {
        Set<String> keySet = map.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            DrugGeneric drugGeneric = map.get(key);
            //synchronizeDrugGeneric(drugGeneric, creatorId);
            Set<DrugBasic> set = drugGeneric.getDrugBasicSet();
            Iterator<DrugBasic> iterator = set.iterator();
            while (iterator.hasNext()) {
                DrugBasic drugBasic = iterator.next();
                //this.drugDAO.merge(drugBasic);//.saveOrUpdate(drugGeneric);
                if (drugBasic.getDrugBasicId() != null && drugBasic.getDrugBasicId() > 0) {
                    this.drugDAO.getCurrentSession().clear();
                    this.drugDAO.update(drugBasic);
                } else {
                    this.drugDAO.save(drugBasic);
                }
                //synchronizeDrugBasic(drugBasic,creatorId);

            }

            //this.drugDAO.save(obj);
            // System.out.println("key: " + key + " value: " + loans.get(key));
        }
        return "";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    private String saveDrugGenericData(Map<String, DrugGeneric> map, Map<String, DrugPacking> drugPackingMap,
            Map<String, DrugForm> drugFormMap, int creatorId) throws Exception {
        Set<String> keySet = map.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            DrugGeneric drugGeneric = map.get(key);
            synchronizeDrugGeneric(drugGeneric, creatorId);
            Set<DrugBasic> set = drugGeneric.getDrugBasicSet();
            Iterator<DrugBasic> iterator = set.iterator();
            while (iterator.hasNext()) {
                DrugBasic drugBasic = iterator.next();
                synchronizeDrugBasic(drugBasic, creatorId);
                
            }
            if (drugGeneric.getDrugGenericID() != null && drugGeneric.getDrugGenericID() > 0) {
                this.drugDAO.getCurrentSession().clear();
                this.drugDAO.update(drugGeneric);//.saveOrUpdate(drugGeneric);
            } else {
                this.drugDAO.save(drugGeneric);
            }
            //this.drugDAO.save(obj);
            // System.out.println("key: " + key + " value: " + loans.get(key));
        }
        return "";
    }
    
    private String saveMapData(Map map) throws Exception {
        Set<String> keySet = map.keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            Object obj = map.get(key);
            this.drugDAO.getCurrentSession().clear();
            this.drugDAO.saveOrUpdate(obj);
            // System.out.println("key: " + key + " value: " + loans.get(key));
        }
        return "";
    }
    
    private XSSFSheet populateDrugDataFromExcelFile(ByteArrayInputStream bis, Map drugGenericMap, Map drugBasicMap, Map drugPackingMap, Map drugFormMap,
            Map indexMap, Map drugDetailMap,
            int sheetIndex, int creatorId, List<DrugDetail> drugDetailsFailure) throws FileNotFoundException,
            IOException,
            OpenXML4JException, Exception {
        // FileInputStream file = new FileInputStream(new File(fileName));

//            Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(bis);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        //Iterate through each rows one by one
        String name = sheet.getSheetName();
        int rowCount = sheet.getPhysicalNumberOfRows();
        
        if (drugGenericMap == null) {
            drugGenericMap = new HashMap();
        }
        
        if (drugBasicMap == null) {
            drugBasicMap = new HashMap();
        }
        
        if (drugPackingMap == null) {
            drugPackingMap = new HashMap();
        }
        
        if (indexMap == null) {
            indexMap = new HashMap();
        }
        
        if (drugDetailMap == null) {
            drugDetailMap = new HashMap();
        }
        
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            String genericName = AppUtil.getSafeStr(row.getCell(9).getStringCellValue(), ""); //done
            //String theraupeuticClass = AppUtil.getSafeStr(row.getCell(9).getStringCellValue(), "");
            String brandName = getProperCellValue(row.getCell(8)); //done
            DrugGeneric drugGeneric = null;
            DrugDetail drugDetail = new DrugDetail();
            DrugBasic drugBasic = new DrugBasic();
            populateDrugDetail(drugDetail, row, creatorId, drugPackingMap, drugFormMap);
            String key = drugDetail.getDrugGCN() + "_" + AppUtil.getSafeStr(drugDetail.getGenericName(), "").toLowerCase()
                    + "_" + AppUtil.getSafeStr(drugDetail.getBrandName(), "").toLowerCase() + "_" + AppUtil.getSafeStr(drugDetail.getDemandBrand(), "").toLowerCase();
            if (!drugDetailMap.containsKey(key)) {
                drugDetailMap.put(key, drugDetail);
            } else {
                drugDetailsFailure.add(drugDetail);
                DrugDetail dd = (DrugDetail) drugDetailMap.get(key);
                drugDetail.setKeyText("Drug at row# "
                        + row.getRowNum() + " wih GCN:" + dd.getDrugGCN()
                        + " ,Brand: " + dd.getBrandName() + " ,Generic " + dd.getGenericName()
                        + " , Brand Indicator " + dd.getDemandBrand()
                        + " is duplicate with the record at "
                        + dd.getExcelSheetRowNumber() + ".So it is beng discarded.");
            }
            
            drugDetail.setExcelSheetRowNumber(row.getRowNum());
            
            String drugBasicKey = AppUtil.getSafeStr(brandName, "").toLowerCase() + "_"
                    + AppUtil.getSafeStr(genericName, "").toLowerCase();
            String drugGenericKey = AppUtil.getSafeStr(genericName.toLowerCase(), "");//+"_"+AppUtil.getSafeStr(theraupeuticClass.toLowerCase(),"");
            if (drugGenericMap.containsKey(drugGenericKey)) {
                drugGeneric = (DrugGeneric) drugGenericMap.get(drugGenericKey);
                Set<DrugBasic> drugBasicSet = drugGeneric.getDrugBasicSet();
                if (drugBasicSet == null) {
                    drugBasicSet = new HashSet<DrugBasic>();
                }
                
                if (drugBasicMap.containsKey(drugBasicKey)) {
                    drugBasic = (DrugBasic) drugBasicMap.get(drugBasicKey);
//                    populateDrugDetail(drugDetail, row, creatorId, drugPackingMap, drugFormMap);
                    drugDetail.setDrugBasic(drugBasic);
                    Set<DrugDetail> drugDetailSet = drugBasic.getDrugDetailSet();
                    drugDetailSet.add(drugDetail);
                    //drugBasic.setDrugDetailSet(drugDetailSet);
                } else {
                    drugBasic = new DrugBasic();
                    populateDrugBasic(drugBasic, row, creatorId);
//                    populateDrugDetail(drugDetail, row, creatorId, drugPackingMap, drugFormMap);
                    drugDetail.setDrugBasic(drugBasic);
                    Set<DrugDetail> drugDetailSet = new HashSet<>();
                    drugDetailSet.add(drugDetail);
                    drugBasic.setDrugDetailSet(drugDetailSet);
                    drugBasicMap.put(drugBasicKey, drugBasic);
                }
                drugBasic.setDrugGeneric(drugGeneric);
                drugBasicSet.add(drugBasic);
                drugGeneric.setDrugBasicSet(drugBasicSet);
                //drugDetail.setDrugGeneric(drugGeneric);
                //drugBasicSet.add(drugDetailssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssscccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc);
                //DrugDetail
            } else {
                Set<DrugDetail> drugDetailSet = new HashSet<DrugDetail>();
                Set<DrugBasic> drugBasicSet = new HashSet();//drugGeneric.getDrugBasicSet();
                drugGeneric = new DrugGeneric();
                drugBasic = new DrugBasic();
                populateDrugGeneric(drugGeneric, row, creatorId); //done
                populateDrugBasic(drugBasic, row, creatorId);
                //populateDrugDetail(drugDetail, row, creatorId, drugPackingMap, drugFormMap);
                drugBasic.setDrugGeneric(drugGeneric);
                drugDetail.setDrugBasic(drugBasic);
                drugDetailSet.add(drugDetail);
                drugBasic.setDrugDetailSet(drugDetailSet);
                drugBasicSet.add(drugBasic);
                drugBasicMap.put(drugBasicKey, drugBasic);
                drugGeneric.setDrugBasicSet(drugBasicSet);
                
            }
            drugGenericMap.put(drugGenericKey, drugGeneric);
            
        }
        return sheet;
        
    }

    public ArrayList<DrugDetail2> populateDrugDataFromExcelByAmmar(InputStream  bis
                                                    ) throws FileNotFoundException,
            IOException,
            OpenXML4JException, Exception {
        // FileInputStream file = new FileInputStream(new File(fileName));

        ArrayList<DrugDetail2> drugNewArrayList=new ArrayList<>();

//            Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(bis);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        String name = sheet.getSheetName();
        int rowCount = sheet.getPhysicalNumberOfRows();


        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
//            String genericName = AppUtil.getSafeStr(row.getCell(9).getStringCellValue(), ""); //done
//            //String theraupeuticClass = AppUtil.getSafeStr(row.getCell(9).getStringCellValue(), "");
//            String brandName = getProperCellValue(row.getCell(8)); //done

            DrugDetail2 drugNew = new DrugDetail2();
            drugNew.setGcnSeq(printCellValue(row.getCell(0)));
            drugNew.setGenericProId(printCellValue(row.getCell(1)));
            drugNew.setRxLabelName(printCellValue(row.getCell(2)));
            drugNew.setStrength(printCellValue(row.getCell(3)));
            drugNew.setDosageForm(printCellValue(row.getCell(4)));
            drugNew.setNdc(printCellValue(row.getCell(5)));
            drugNew.setSponsoredProduct(printCellValue(row.getCell(6)));
            drugNew.setDefaultRtXQty(printCellValue(row.getCell(7)));
            drugNew.setUnitPrice(printCellValue(row.getCell(8)));
            drugNew.setBrandReference(printCellValue(row.getCell(9)));
            drugNew.setGenericOrBrand(printCellValue(row.getCell(10)));
            drugNew.setMajorReportingCat(printCellValue(row.getCell(11)));
            drugNew.setMinorreportingClass(printCellValue(row.getCell(12)));
            drugNew.setRxExpire(Integer.parseInt(printCellValue(row.getCell(13))));
            drugNew.setRefillsAllowed(Integer.parseInt(printCellValue(row.getCell(14))));
            drugNew.setMarketer(printCellValue(row.getCell(15)));
            drugNew.setGenericName(printCellValue(row.getCell(16)));

            drugNewArrayList.add(drugNew);
        }
        return drugNewArrayList;
    }

    ////////////////////////////////////////////////////////////////////////////////
//    private void loadSheetPictrues(Map drugDetailMap,
//            XSSFSheet sheet, int cretedBy) throws IOException, Exception {
//
//        //Get first/desired sheet from the workbook
//        Long ndc = 0L;
//        //int i=0;
//        String contentType = "";
//        try {
//            for (POIXMLDocumentPart dr : sheet.getRelations()) {
//                if (dr instanceof XSSFDrawing) {
//                    XSSFDrawing drawing = (XSSFDrawing) dr;
//                    List<XSSFShape> shapes = drawing.getShapes();
//                    for (XSSFShape shape : shapes) {
//                        XSSFPicture pic = (XSSFPicture) shape;
//                        contentType = pic.getPictureData().getMimeType();
//                        //pic.getPictureData().getData();
//                        XSSFClientAnchor anchor = pic.getPreferredSize();
//                        CTMarker ctMarker = anchor.getFrom();
//                        String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
//                        // sheetIndexPicMap.put(picIndex, pic.getPictureData());
//                        ndc = AppUtil.getSafeLong(getProperCellValue(sheet.getRow(ctMarker.getRow()).getCell(21)), 0L);
//                        // System.out.println("PIC LOCATION ROW_COL  "+picIndex);
//                        DrugDetail detail = (DrugDetail) drugDetailMap.get(ndc);
//                        if (ndc != null && ndc > 0 && detail != null) {
//                            //System.out.println("NDC FOR PICS "+ndc);
//                            DrugDetailDocuments docs = new DrugDetailDocuments();
//                            docs.setContentTypeImage(contentType);
//                            docs.setImage(pic.getPictureData().getData());
//                            contentType = CommonUtil.getLastIndexOfStr(contentType, "/");
//                            Long gcn = AppUtil.getSafeLong(getProperCellValue(sheet.getRow(ctMarker.getRow()).getCell(0)), 0L);
//                            String fileName = gcn + contentType;
//                            logger.info("File name is:: " + fileName);
//                            String path = Constants.DRUG_ATTACHMENT_PATH + Constants.IMAGES + fileName;
//                            File file = new File(path);
//                            patientProfileService.saveImageOrVideo(pic.getPictureData().getData(), file, contentType, Constants.COMMAND + Constants.IMAGES);
//                            docs.setCreatedBy(cretedBy);
//                            docs.setCreatedOn(new Date());
//                            docs.setDrugDetail(detail);
//                            if (detail.getDocs() == null) {
//                                List docLst = new ArrayList<>();
//                                docLst.add(docs);
//                                detail.setDocs(docLst);
//                            } else {
//                                detail.getDocs().add(docs);
//                            }
//                            detail.setImgUrl(Constants.INSURANCE_CARD_URL + Constants.IMAGES + fileName);
//                            detail.setImage("1");
////                           System.out.println("LENGTH DURING PICTURE LOADING "+detail.getDocs()!=null?detail.getDocs().size():0
////                                             +" DRUG "+detail.getDrugNDC());
//                            //DrugDetail detail=new DrugDetail();
//                            //detail.setDrugNDC(ndc);
//                            //docs.setDrugDetail(detail);
//                            //this.drugDAO.save(docs);
//                            //i++;
//                        }
//
//                    }
//                }
//            }
//            //System.out.println("INDEX "+i);
//        } catch (Exception e) {
//            System.out.println("Exception occurred for NDC " + ndc + " CONTENT " + contentType);
//        }
//
//    }

    ////////////////////////////////////////////////////////////////////////////////
    public List retrieveActiveDrugListData() {
        
        List lst = this.drugDAO.retrieveActiveDrugListData();
//        if (lst != null) {
//            for (Object obj : lst) {
//                DrugDetail detail = (DrugDetail) obj;
//                detail.setBrandName(detail.getDrugBasic().getBrandName());
//                detail.setGenericName(detail.getDrugBasic().getDrugGeneric().getGenericName());
//                detail.setTherapy(detail.getDrugBasic().getDrugGeneric().getTherapeuticClass());
//                detail.setFormDesc(detail.getDrugForm().getFormDescr());
//                detail.setPackingDesc(detail.getDrugPacking().getPackingDescr());
//               List<DrugDetailDocuments> lstDocs=detail.getDocs();
//               DrugDetailDocuments docs=lstDocs!=null&&lstDocs.size()>0?lstDocs.get(0):null;
//               if(docs!=null)
//               {
//                   byte[] pdfData=docs.getPdfDoc();
//                   byte[] imageData=docs.getImage();
//                   detail.setPdf(pdfData!=null&&pdfData.length>0?"1":"0");
//                   detail.setImage(imageData!=null&&imageData.length>0?"1":"0");
//               }
//               else
//               {
//                   detail.setPdf("0");
//                   detail.setImage("0");
//               }
//            }
//        }
        return lst;
        
    }

    //////////////////////////////////////////////////////////////
    public List retrieveActiveDrugListDataByDrugName(String drugNme) {
        
        List lst = this.drugDAO.retrieveActiveDrugListData(drugNme);
        //List<BusinessObject> lstObj = new ArrayList();

//        lstObj.add(new BusinessObject("archived", "N",Constants.HIBERNATE_EQ_OPERATOR));
//        if(AppUtil.getSafeStr(drugNme, "").length()>0)
//        {
//            lstObj.add(new BusinessObject("drugBasic.brandName",drugNme,Constants.HIBERNATE_LIKE_OPERATOR));
//        }
//        
//        List lst=this.drugDAO.findByProperty(new DrugDetail(),lstObj,"drugBasic.brandName", Constants.HIBERNATE_ASC_ORDER);
//        if(lst!=null)
//        {
//           for(Object obj:lst)
//           {
//               DrugDetail detail=(DrugDetail) obj;
//               detail.setBrandName(detail.getDrugBasic().getBrandName());
//               detail.setGenericName(detail.getDrugBasic().getDrugGeneric().getGenericName());
//               detail.setTherapy(detail.getDrugBasic().getDrugGeneric().getTherapeuticClass());
//               detail.setFormDesc(detail.getDrugForm().getFormDescr());
//               detail.setPackingDesc(detail.getDrugPacking().getPackingDescr());
//
//           }
//        }
        return lst;
        
    }

    /////////////////////////////////////////////////////////////
    public void updateDrugDetail(DrugDetail detail, Long id, int userId) throws Exception {
        DrugPacking drugPacking = (DrugPacking) this.drugDAO.findByPropertyUnique(new DrugPacking(), "packingDescr", detail.getPackingDesc(),
                Constants.HIBERNATE_EQ_OPERATOR);
        
        if (drugPacking == null || drugPacking.getDrugPackingId() == null) {
            drugPacking = new DrugPacking();
            drugPacking.setPackingDescr(detail.getPackingDesc());
            drugPacking.setCreatedBy(userId);
            drugPacking.setCreatedOn(new Date());
            this.drugDAO.save(drugPacking);
            //throw new Exception("No packing "+detail.getPackingDesc()+" exists.");

        }
        detail.setDrugPacking(drugPacking);
        
        DrugForm drugForm = (DrugForm) this.drugDAO.findByPropertyUnique(new DrugForm(), "formDescr", detail.getFormDesc(),
                Constants.HIBERNATE_EQ_OPERATOR);
        if (drugForm == null) {
            //throw new Exception("No form "+detail.getFormDesc()+" exists.");
            drugForm = new DrugForm();
            drugForm.setFormDescr(detail.getFormDesc());
            drugForm.setCreatedBy(userId);
            drugForm.setCreatedOn(new Date());
            this.drugDAO.save(drugForm);
        }
        detail.setDrugForm(drugForm);
        
        DrugGeneric drugGeneric = (DrugGeneric) this.drugDAO.findByPropertyUnique(new DrugGeneric(),
                "genericName", detail.getGenericName(), Constants.HIBERNATE_EQ_OPERATOR);
        if (drugGeneric == null) {
            drugGeneric = new DrugGeneric();
            drugGeneric.setGenericName(detail.getGenericName());
            drugGeneric.setTherapeuticClass(detail.getTherapy());
            drugGeneric.setCreatedBy(userId);
            drugGeneric.setCreatedOn(new Date());
            this.drugDAO.save(drugGeneric);
            //throw new Exception("No drug generic "+detail.getGenericName()+" exists.");
        }
        DrugBasic drugBasic = (DrugBasic) this.drugDAO.findByPropertyUnique(new DrugBasic(),
                "brandName", detail.getBrandName(), Constants.HIBERNATE_EQ_OPERATOR);
        
        if (drugBasic == null) {
            
            DrugDetail drugDetail = (DrugDetail) this.drugDAO.findRecordById(new DrugDetail(), id);
            if (drugDetail != null) {
                drugBasic = drugDetail.getDrugBasic();
                if (drugBasic == null) {
                    drugBasic = new DrugBasic();
                    drugBasic.setCreatedBy(userId);
                    drugBasic.setCreatedOn(new Date());
                } else {
                    drugBasic.setUpdatedBy(userId);
                    drugBasic.setUpdatedOn(new Date());
                }
                drugBasic.setBrandName(detail.getBrandName());
            } else {
                drugBasic = new DrugBasic();
                drugBasic.setBrandName(detail.getBrandName());
                drugBasic.setCreatedBy(userId);
                drugBasic.setCreatedOn(new Date());
            }
        } else {
            drugBasic.setUpdatedBy(userId);
            drugBasic.setUpdatedOn(new Date());
        }

        //detail.setDrugGeneric(drugGeneric);
        drugBasic.setDrugGeneric(drugGeneric);
        this.drugDAO.saveOrUpdate(drugBasic);
        
        detail.setDrugBasic(drugBasic);
        detail.setUpdatedBy(userId);
        detail.setUpdatedOn(new Date());
        
        if (!id.equals(detail.getDrugNDC()) && this.drugDAO.searchDrugsByNDC(detail.getDrugNDC()) != null)//user have made changes to ndc & we check uniqueness of new ndc
        {
            throw new Exception("Ndc already exists.");
        }
        this.drugDAO.saveOrUpdate(detail);
        
    }

    /////////////////////////////////////////////////////////////////////
    public DrugDetailDTO retrieveDrugDetail(Long ndc) {
        /*
        Long drugNDC, Long drugGPI, Long drugGCN, String brandName, String strength,
        String packageSizeValues, float marginPercent, float basePrice, 
        float additionalFee, int defQty, String inStock, String archived, 
        String demandBrand, float goodRx, int archivedBy, Date archivedOn, 
        String formDesc, String packingDesc, String genericName, String therapy,
        String pdf, String image
        
         */
        DrugDetail detail = (DrugDetail) this.drugDAO.findRecordById(new DrugDetail(), ndc);
        DrugDetailDTO dto = new DrugDetailDTO(detail.getDrugNDC(), detail.getDrugGPI(),
                detail.getDrugGCN(), detail.getDrugBasic().getBrandName(),
                detail.getStrength(), detail.getPackageSizeValues(),
                detail.getMarginPercent(), detail.getBasePrice(),
                detail.getAdditionalFee(), detail.getDefQty(),
                detail.getInStock(), detail.getArchived(), detail.getDemandBrand(),
                detail.getGoodRx(), detail.getArchivedBy(), detail.getArchivedOn(),
                detail.getDrugForm().getFormDescr(),
                detail.getDrugPacking().getPackingDescr(),
                detail.getDrugBasic().getDrugGeneric().getGenericName(),
                detail.getDrugBasic().getDrugGeneric().getTherapeuticClass(),
                detail.getPdf(),
                detail.getImage());
        return dto;
    }
    ////////////////////////////////////////////////////////////////////

    public void archiveDrugDetail(Long ndc) throws Exception {
        DrugDetail detail = (DrugDetail) this.drugDAO.findRecordById(new DrugDetail(), ndc);
        if (detail != null) {
            detail.setArchived("Y");
            this.drugDAO.saveOrUpdate(detail);
        } else {
            throw new Exception("Drug with ndc " + ndc + " doesn't exist.");
        }
    }

    /////////////////////////////////////////////////////////////////////
    public List populateRecordList(Object obj, List<BusinessObject> lstObj, String sortProperty, int sequence) {
        return this.drugDAO.findByProperty(obj, lstObj, sortProperty, Constants.HIBERNATE_ASC_ORDER);
    }
    
    public List<DrugCategoryListDTO> searchDrugDefaultQtyByStrength(Integer drugBasicId, String strength) {
        List<DrugCategoryListDTO> list = new ArrayList<>();
        try {
            List<DrugDetail> drugDetailsList = drugDAO.searchDrugDefaultQtyByStrength(drugBasicId, strength);
            for (DrugDetail dd : drugDetailsList) {
                DrugCategoryListDTO categoryListDTO = new DrugCategoryListDTO();
                categoryListDTO.setDefaultQty(dd.getDefQty());
                categoryListDTO.setNdc("" + dd.getDrugNDC());
                list.add(categoryListDTO);
            }
        } catch (Exception e) {
            logger.error("Exception:: ", e);
        }
        return list;
    }
    
    public List<DrugCategoryListDTO> searchDrugBasicPrice(String a_iDrugBrandName, String a_drugType, String strength) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugDetail> lst_drugBrand = drugDAO.searchDrugBasicPrice(a_iDrugBrandName, a_drugType, strength);
            for (DrugDetail drug : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                float basePrice = AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(drug.getBasePrice()), 0f);
                drugCategoryListDTO.setBasePrice(basePrice);
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public List<DrugDetail> getDrugDetailsByNDC(long ndc) {
        List<DrugDetail> list = new ArrayList<>();
        try {
            list = drugDAO.getDrugDetailsByNDC(ndc);
        } catch (Exception e) {
            logger.error("Exception:: DrugService:: getDetailsByNDC", e);
        }
        return list;
    }
    
    public boolean saveDrugPdfPath(String path, Long gcn, String type) {
        boolean result = false;
        try {
            DrugDetail drugDetail = (DrugDetail) drugDAO.findByPropertyUnique(new DrugDetail(), "drugGCN", gcn, Constants.HIBERNATE_EQ_OPERATOR);
            if (drugDetail != null && drugDetail.getDrugNDC() != null) {
                drugDetail.setPdfDocUrl(path);
                drugDetail.setPdf("1");
                drugDetail.setPdfDocUpdatedOn(new Date());
                drugDAO.update(drugDetail);
                result = true;
            }
        } catch (Exception e) {
            logger.error("Exception:: DrugService:: saveDrugPdfPath", e);
        }
        return result;
    }
    
    public boolean saveDrugDocPath(String path, Long gcn, int docType) throws Exception {
        boolean result = false;
        
        DrugDetail drugDetail = (DrugDetail) drugDAO.findByPropertyUnique(new DrugDetail(), "drugGCN", gcn, Constants.HIBERNATE_EQ_OPERATOR);
        if (drugDetail != null && drugDetail.getDrugNDC() != null) {
            switch (docType) {
                case 1:
                    drugDetail.setPdfDocUrl(path);
                    drugDetail.setPdfDocUpdatedOn(new Date());
                    drugDetail.setPdf("1");
                    break;
                case 2:
                    drugDetail.setDrugDocUrl(path);
                    drugDetail.setDrugDoc("1");
                    drugDetail.setDrugDocUpdatedOn(new Date());
                    break;
                case 3:
                    drugDetail.setImgUrl(path);
                    drugDetail.setImage("1");
                    drugDetail.setImgUpdatedOn(new Date());
                    break;
                default:
                    throw new Exception("Invalid document option.");
            }
            drugDAO.update(drugDetail);
            result = true;
        }
        
        return result;
    }
    
    public List<String> populateDrugByDrugGenericName(String str_name) {
        List<String> lstDrug = new ArrayList<>();
        try {
            
            List<DrugGeneric> lst_DrugGenerics = drugDAO.searchDrugGenericByGenericName(str_name);
            for (DrugGeneric drugGeneric : lst_DrugGenerics) {
                
                lstDrug.add(drugGeneric.getGenericName());
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService:: populateDrugByDrugGenericName", ex);
        }
        return lstDrug;
    }
    
    public List<DrugDetail> populateQtyByStrengthAndDrugName(String brandName, String genericName, String strength) {
        List<DrugDetail> lstDrugQty = new ArrayList<>();
        try {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("drugBasic.brandName", brandName, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (CommonUtil.isNotEmpty(genericName)) {
                obj = new BusinessObject("drugBasic.drugGeneric.genericName", genericName, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }
            obj = new BusinessObject("strength", strength, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            List<DrugDetail> drugDetails = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            for (DrugDetail drugDetail : drugDetails) {
                DrugDetail dd = new DrugDetail();
                dd.setDrugGCN(drugDetail.getDrugGCN());
                dd.setDefQty(drugDetail.getDefQty());
                lstDrugQty.add(dd);
            }
        } catch (Exception e) {
            logger.error("Exception:: populateQtyByStrengthAndDrugName", e);
        }
        return lstDrugQty;
    }

    /////////////////////////////////////////////////////////////////
    public List<DrugDetail> populateQtyByStrengthTypeAndDrugName(String brandName, String genericName,
            String strength, String type) {
        List<DrugDetail> lstDrugQty = new ArrayList<>();
        try {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("drugBasic.brandName", brandName, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (CommonUtil.isNotEmpty(genericName)) {
                obj = new BusinessObject("drugBasic.drugGeneric.genericName", genericName, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }
            obj = new BusinessObject("strength", strength, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            obj = new BusinessObject("drugForm.formDescr", type, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            List<DrugDetail> drugDetails = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            for (DrugDetail drugDetail : drugDetails) {
                DrugDetail dd = new DrugDetail();
                dd.setDrugGCN(drugDetail.getDrugGCN());
                dd.setDefQty(drugDetail.getDefQty());
                dd.setMonthRxExpiration(drugDetail.getMonthRxExpiration());
                lstDrugQty.add(dd);
            }
        } catch (Exception e) {
            logger.error("Exception:: populateQtyByStrengthAndDrugName", e);
        }
        return lstDrugQty;
    }

    /////////////////////////////////////////////////////////////////////
    public List<DrugCategoryListDTO> populateQtyByTypeAndDrugName(String brandName, String genericName,
            String type) {
        List<DrugDetail> lstDrugQty = new ArrayList<>();
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            List<BusinessObject> lstObj = new ArrayList();
            BusinessObject obj = new BusinessObject("drugBasic.brandName", brandName, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            if (CommonUtil.isNotEmpty(genericName)) {
                obj = new BusinessObject("drugBasic.drugGeneric.genericName", genericName, Constants.HIBERNATE_EQ_OPERATOR);
                lstObj.add(obj);
            }
            
            obj = new BusinessObject("drugForm.formDescr", type, Constants.HIBERNATE_EQ_OPERATOR);
            lstObj.add(obj);
            List<DrugDetail> drugDetails = drugDAO.findByNestedProperty(new DrugDetail(), lstObj, "", 0);
            
            for (DrugDetail drug : drugDetails) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugModelToDrugCategoryListDTO(drug, drugCategoryListDTO);
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception e) {
            logger.error("Exception:: populateQtyByStrengthAndDrugName", e);
        }
        return lst_drugCategoryListDTO;
    }
    //////////////////////////////////////////////////////////////////////

    public DrugDetail getDrugDetailByGCN(Long drugGcn) {
        DrugDetail drugDetail = new DrugDetail();
        try {
            List lst = drugDAO.getDrugDetailByGCN(drugGcn);
            if (lst != null && lst.size() > 0) {
                drugDetail = (DrugDetail) lst.get(0);
            }
        } catch (Exception e) {
            logger.error("Exception:: getDrugDetailByGCN", e);
        }
        return drugDetail;
    }
    
    public List<DrugDetail> getDrugDetailsListByDrugGCN(long drugGcn, int operator) {
        List<DrugDetail> drugDetails = new ArrayList<>();
        try {
            drugDetails = drugDAO.getDrugDetailsListByDrugGCN(drugGcn);
        } catch (Exception e) {
            logger.error("Exception:: getDrugDetailsListByDrugGCN", e);
        }
        return drugDetails;
    }
    
    public List<DrugCategoryListDTO> searchDrugFormByDrugBrandName(String brandName) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugForm> lst_drugBrand = drugDAO.searchDrugFormByDrugBrandName(brandName);
            for (DrugForm drugForm : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugCategoryListDTO.setBrandNameId("" + drugForm.getDrugFormId());
                drugCategoryListDTO.setType(drugForm.getFormDescr());
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public List<DrugCategoryListDTO> searchDrugFormByDrugBrandName(String[] drugArr) {
        
        List<DrugCategoryListDTO> lst_drugCategoryListDTO = new ArrayList<>();
        try {
            
            List<DrugForm> lst_drugBrand = null;
            if (drugArr != null) {
                if (drugArr.length == 1) {
                    lst_drugBrand = drugDAO.searchDrugFormByBrandedDrug(drugArr[0]);
                } else if (drugArr.length == 2) {
                    lst_drugBrand = drugDAO.searchDrugFormByGenericDrugs(drugArr[0], drugArr[1]);
                }
            }
            
            for (DrugForm drugForm : lst_drugBrand) {
                
                DrugCategoryListDTO drugCategoryListDTO = new DrugCategoryListDTO();
                drugCategoryListDTO.setBrandNameId("" + drugForm.getDrugFormId());
                drugCategoryListDTO.setType(drugForm.getFormDescr());
                
                lst_drugCategoryListDTO.add(drugCategoryListDTO);
            }
            
        } catch (Exception ex) {
            logger.error("Exception: DrugService -> searchDrugBrandByName", ex);
        }
        return lst_drugCategoryListDTO;
    }
    
    public void saveDrugDetailsImportLogs(List<DrugDetail> drugDetails, int totalSuccessed) {
//        List<DrugDetailImportLogs> importLogs = new ArrayList<>();
        try {
            drugDetails.forEach((dd) -> {
                try {
                    drugDAO.save(new DrugDetailImportLogs(dd.getKeyText(), dd.getExcelSheetRowNumber()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(DrugService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if (drugDetails.size() > 0) {
                drugDAO.save(new DrugDetailImportLogs("Total Successed Insertions " + totalSuccessed, 0));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: DrugService -> searchDrugBrandByName", e);
        }
    }
    
    public int isAPPMsgSend(SupportModel supportModel) {
        int response = 0;
        try {
            PatientProfile patientProfile = patientProfileService.getPatientProfileByMobileNumber(EncryptionHandlerUtil.getEncryptedString(supportModel.getPhoneNo()));
            if (patientProfile == null || patientProfile.getPatientProfileSeqNo() == null) {
                response = 1;
                return response;
            }
            CampaignMessages campaignMessages = (CampaignMessages) patientProfileService.findRecordById(new CampaignMessages(), supportModel.getMessageId());
            boolean isMsgSend = patientProfileService.saveNotificationMessages(campaignMessages, Constants.YES, patientProfile.getPatientProfileSeqNo());
            if (!isMsgSend) {
                response = 2;
                return response;
            }
        } catch (Exception e) {
            logger.error("Exception: DrugService:: isAPPMsgSend", e);
        }
        return response;
    }
}
