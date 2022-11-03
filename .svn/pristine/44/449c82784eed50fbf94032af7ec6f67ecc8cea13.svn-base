package com.ssa.cms.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ssa.cms.common.Constants;
import com.ssa.cms.common.JsonResponseComposer;
import com.ssa.cms.dao.ConsumerPortalDAO;
import com.ssa.cms.dto.DrugDetailDTO;
import com.ssa.cms.model.CMSDocuments;
import com.ssa.cms.model.CMSPageContent;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.WidgetLog;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zubair
 */
@Service
@Transactional
public class ConsumerPortalService {

    @Autowired
    private ConsumerPortalDAO consumerPortalDAO;
    private static final Log logger = LogFactory.getLog(ConsumerPortalService.class.getName());

    public List<CMSDocuments> getCMSDocumentsList(int index) {
        List<CMSDocuments> list = new ArrayList<>();
        try {
            List<CMSDocuments> dblist = consumerPortalDAO.getDocumentsList(index);
            for (CMSDocuments cmsd : dblist) {
                cmsd.setFileSize(FileUtils.byteCountToDisplaySize(cmsd.getAttachment().length));
                list.add(cmsd);
            }
        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getCMSDocumentsList", e);
        }
        return list;
    }

    public List<CMSDocuments> getCMSDocumentsByTitle(String title) {
        List<CMSDocuments> list = new ArrayList<>();
        try {
            if (!"".equals(title)) {
                List<CMSDocuments> dblist = consumerPortalDAO.getCMSDocumentsByTitle(title);
                for (CMSDocuments cmsd : dblist) {
                    cmsd.setFileSize(FileUtils.byteCountToDisplaySize(cmsd.getAttachment().length));
                    list.add(cmsd);
                }
            }
        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getCMSDocumentsByTitle", e);
        }
        return list;
    }

    public List<CMSPageContent> getCMSPageContents() {
        List<CMSPageContent> list = new ArrayList<>();
        try {
            list = consumerPortalDAO.getCMSPageContents();
        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getCMSPageContents", e);
        }
        return list;
    }

    public List<SmtpServerInfo> getSmtpServerInfos() {
        List<SmtpServerInfo> list = new ArrayList<>();
        try {
            list = consumerPortalDAO.getSmtpServerInfos();
        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getSmtpServerInfos", e);
        }
        return list;
    }

    public List<CMSDocuments> getCMSDocumentsList() {
        List<CMSDocuments> list = new ArrayList<>();
        try {
            list = consumerPortalDAO.getCMSDocumentsList();

        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getCMSDocumentsList", e);
        }
        return list;
    }

    public WidgetLog getWidgetLog(String communicationMethod) {
        WidgetLog widgetLog = new WidgetLog();
        try {
            if (communicationMethod.equalsIgnoreCase("text")) {
                communicationMethod = "T";
            } else {
                communicationMethod = "E";
            }
            widgetLog = consumerPortalDAO.getWidgetLog(communicationMethod).get(0);
        } catch (Exception e) {
            logger.error("Exception: ConsumerPortalService -> getWidgetLog", e);
        }
        return widgetLog;
    }

    public String calculateDrugPrice(Long drugDetailId, Integer qty) {
        String json = null;
        try {
            DrugDetail drugDetail = (DrugDetail) consumerPortalDAO.findRecordById(new DrugDetail(), drugDetailId);
            List<DrugDetailDTO> list = new ArrayList<>();
            if (drugDetail != null) {
                DrugDetailDTO drugDetailDTO = new DrugDetailDTO();
                populateDrugDetail(drugDetailDTO, drugDetail, qty);
                list.add(drugDetailDTO);
            }
            json = new ObjectMapper().writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception:: ConsumerPortalService:: calculateDrugPrice", e);
        }
        return json;
    }

    public void populateDrugDetail(DrugDetailDTO drugDetailDTO, DrugDetail drugDetail, Integer qty) throws Exception {
        drugDetailDTO.setDrugDetailId(drugDetail.getDrugDetailId());
        drugDetailDTO.setDrugGCN(drugDetail.getDrugGCN());
        drugDetailDTO.setDrugGPI(drugDetail.getDrugGPI());
        drugDetailDTO.setStrength(drugDetail.getStrength());
        drugDetailDTO.setDemandBrand(drugDetail.getDemandBrand());
        drugDetailDTO.setInStock(drugDetail.getInStock());
        drugDetailDTO.setPackageSizeValues(drugDetail.getPackageSizeValues());
        drugDetailDTO.setGnn(drugDetail.getGnn());

        if (drugDetail.getDrugBasic() != null) {
            drugDetailDTO.setBrandName(drugDetail.getDrugBasic().getBrandName());
            drugDetailDTO.setGenericName(drugDetail.getDrugBasic().getDrugGeneric().getGenericName());
            drugDetailDTO.setTherapy(drugDetail.getDrugBasic().getTherapeuticCategory());
        }
        if (drugDetail.getDrugForm() != null && drugDetail.getDrugForm().getDrugFormId() != null) {
            drugDetailDTO.setFormDesc(drugDetail.getDrugForm().getFormDescr());
        }
        drugDetailDTO.setPackingDesc(drugDetail.getPackingDesc());
        drugDetailDTO.setStdRxQty(drugDetail.getDefQty());
        drugDetailDTO.setDefQty(qty);
        drugDetailDTO.setBrandIndicator(drugDetail.getDemandBrand());
        drugDetailDTO.setAcqPrice(drugDetail.getPkgCost()!=null?drugDetail.getPkgCost():0f);
        drugDetailDTO.setUnitMarkupAmt(drugDetail.getUnitMarkupAmt()!=null?drugDetail.getUnitMarkupAmt():0f);
        calculateDrugBrandPrice(drugDetail, drugDetailDTO, qty);

        if (CommonUtil.isNotEmpty(drugDetail.getPdfDocUrl())) {
            drugDetailDTO.setPdf(drugDetail.getDrugGCN() + ".pdf");
            drugDetailDTO.setPdfDocUrl(drugDetail.getPdfDocUrl());
        } else {
            drugDetailDTO.setPdf("");
            drugDetailDTO.setPdfDocUrl("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getImgUrl())) {
            drugDetailDTO.setDrugImageName(drugDetail.getDrugGCN() + ".png");
            drugDetailDTO.setImage(drugDetail.getImgUrl());
        } else {
            drugDetailDTO.setDrugImageName("");
            drugDetailDTO.setImage("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getDrugDocUrl())) {
            drugDetailDTO.setDrugDocName(drugDetail.getDrugGCN() + ".pdf");
            drugDetailDTO.setDrugDocUrl(drugDetail.getDrugDocUrl());
        } else {
            drugDetailDTO.setDrugDocName("");
            drugDetailDTO.setDrugDocUrl("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getMonthRxExpiration())) {
            drugDetailDTO.setRxExpire(drugDetail.getMonthRxExpiration());
        }
        if (CommonUtil.isNotEmpty(drugDetail.getRequiresHandDelivery())) {
            drugDetailDTO.setRequiresHandDelivery(drugDetail.getRequiresHandDelivery());
        }
        if (drugDetail.getUpdatedOn() != null) {
            drugDetailDTO.setLastUpdated(DateUtil.dateToString(drugDetail.getUpdatedOn(), Constants.MM_DD_YYYY_HH_MM_SS)+" CST");
            drugDetailDTO.setLastUpdatedYMD(DateUtil.dateToString(drugDetail.getUpdatedOn(), "YYYYMMdd"));
        }
         Date currentDate = new Date();
         drugDetailDTO.setSameDateFlag(DateUtil.dateToString(drugDetail.getUpdatedOn(), "YYYYMMdd").equalsIgnoreCase(DateUtil.dateToString(currentDate, "YYYYMMdd")));
//        if (DateUtil.dateToString(drugDetail.getUpdatedOn(), "YYYYMMdd").equalsIgnoreCase(DateUtil.dateToString(currentDate, "YYYYMMdd"))) {
//            drugDetailDTO.setDateColorFlag(true);
//        } else {
//            drugDetailDTO.setDateColorFlag(false);
//        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public void populateDrugDetail(DrugDetailDTO drugDetailDTO, DrugDetail drugDetail)throws Exception//, Integer qty,
                  //  float basePrice,float marginPercent) throws Exception 
    {
        drugDetailDTO.setDrugDetailId(drugDetail.getDrugDetailId());
        drugDetailDTO.setDrugGCN(drugDetail.getDrugGCN());
        drugDetailDTO.setDrugGPI(drugDetail.getDrugGPI());
        drugDetailDTO.setStrength(drugDetail.getStrength());
        drugDetailDTO.setDemandBrand(drugDetail.getDemandBrand());
        drugDetailDTO.setInStock(drugDetail.getInStock());
        drugDetailDTO.setPackageSizeValues(drugDetail.getPackageSizeValues());
        drugDetailDTO.setGnn(drugDetail.getGnn());

        if (drugDetail.getDrugBasic() != null) {
            drugDetailDTO.setBrandName(drugDetail.getDrugBasic().getBrandName());
            drugDetailDTO.setGenericName(drugDetail.getDrugBasic().getDrugGeneric().getGenericName());
            drugDetailDTO.setTherapy(drugDetail.getDrugBasic().getTherapeuticCategory());
        }
        if (drugDetail.getDrugForm() != null && drugDetail.getDrugForm().getDrugFormId() != null) {
            drugDetailDTO.setFormDesc(drugDetail.getDrugForm().getFormDescr());
        }
        drugDetailDTO.setPackingDesc(drugDetail.getPackingDesc());
        if(drugDetailDTO.getStdRxQty()<=0)
        {
            drugDetailDTO.setStdRxQty(drugDetail.getDefQty());
//            drugDetailDTO.setDefQty(qty);
        }
        drugDetailDTO.setBrandIndicator(drugDetail.getDemandBrand());

//        drugDetailDTO.setBasePrice(basePrice);
//        drugDetailDTO.setMarginPercent(marginPercent);
        calculateDrugBrandPrice(drugDetail, drugDetailDTO, drugDetailDTO.getDefQty());

        if (CommonUtil.isNotEmpty(drugDetail.getPdfDocUrl())) {
            drugDetailDTO.setPdf(drugDetail.getDrugGCN() + ".pdf");
            drugDetailDTO.setPdfDocUrl(drugDetail.getPdfDocUrl());
        } else {
            drugDetailDTO.setPdf("");
            drugDetailDTO.setPdfDocUrl("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getImgUrl())) {
            drugDetailDTO.setDrugImageName(drugDetail.getDrugGCN() + ".png");
            drugDetailDTO.setImage(drugDetail.getImgUrl());
        } else {
            drugDetailDTO.setDrugImageName("");
            drugDetailDTO.setImage("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getDrugDocUrl())) {
            drugDetailDTO.setDrugDocName(drugDetail.getDrugGCN() + ".pdf");
            drugDetailDTO.setDrugDocUrl(drugDetail.getDrugDocUrl());
        } else {
            drugDetailDTO.setDrugDocName("");
            drugDetailDTO.setDrugDocUrl("#");
        }
        if (CommonUtil.isNotEmpty(drugDetail.getMonthRxExpiration())) {
            drugDetailDTO.setRxExpire(drugDetail.getMonthRxExpiration());
        }
        if (CommonUtil.isNotEmpty(drugDetail.getRequiresHandDelivery())) {
            drugDetailDTO.setRequiresHandDelivery(drugDetail.getRequiresHandDelivery());
        }
        if (drugDetail.getUpdatedOn() != null) {
            drugDetailDTO.setLastUpdated(DateUtil.dateToString(drugDetail.getUpdatedOn(), Constants.MM_DD_YYYY_HH_MM_SS)+" CST");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateMultiRxRecords(List<DrugDetailDTO> lstDTO)throws Exception
    {
        int i=0;
        boolean updateAny=false;
        for(DrugDetailDTO dto : lstDTO)
        {
            i=i+1;
            boolean updateFlag= updateMultiRxRecords(dto,i);
            if(updateFlag)
            {
                updateAny=true;
            }
        }
        if(!updateAny)
            throw new Exception("No record was changed so no update occurred.");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean updateMultiRxRecords(DrugDetailDTO dto,int index)throws Exception
    {
        Long id=dto.getDrugDetailId();
        String basePriceStr=AppUtil.getSafeStr( dto.getBasePriceStr(),"").replace("$", "").replaceAll(",", "");
        float basePrice=AppUtil.getSafeFloat(basePriceStr, 0f);
        dto.setBasePrice(AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(basePrice),0f));
        String pkgCostStr=AppUtil.getSafeStr(dto.getAcqPpriceStr(),"").replace("$", "").replaceAll(",", "");
        float pkgCost=AppUtil.getSafeFloat(pkgCostStr, 0f);
        dto.setAcqPrice(pkgCost);
        String unitMarkupAmtStr=AppUtil.getSafeStr( dto.getUnitMarkupAmtStr(),"").replace("$", "").replaceAll(",", "");
        float  unitMarkupAmt=AppUtil.getSafeFloat(unitMarkupAmtStr, 0f);
        dto.setUnitMarkupAmt(unitMarkupAmt);
        if(basePrice<=0f)
        {
            throw new Exception("Please provide proper value for unit price, must be greater than zero for record# "+index);
        }
        if(dto.getMarginPercent()<=0f)
        {
            throw new Exception("Please provide proper value for margin percent, must be greater than zero for record# "+index);
        }
        if(dto.getDefQty()<=0)
        {
            throw new Exception("Please provide proper value for qty, must be greater than zero for record# "+index);
        }
        if(pkgCost<=0)
        {
          throw new Exception("Please provide proper value for package cost, must be greater than zero for record# "+index);  
        }
        if(unitMarkupAmt<=0)
        {
            throw new Exception("Please provide proper value for unit margin, must be greater than zero for record# "+index);
        }
        boolean isAnyUpdate=false;
        DrugDetail detail=(DrugDetail) this.consumerPortalDAO.findRecordById(new DrugDetail(), id);
        if(detail!=null && !this.equalsDrugDetailObjects(detail, dto, id))
        {
            detail.setBasePrice(basePrice);
            detail.setMarginPercent(dto.getMarginPercent());
            detail.setDefQty(dto.getDefQty());
            detail.setPkgCost(pkgCost);
            detail.setUnitMarkupAmt(unitMarkupAmt);
            detail.setUpdatedOn(new Date());
                
            this.consumerPortalDAO.saveOrUpdate(detail);
            isAnyUpdate=true;
        }
        return isAnyUpdate;
//        if(!isAnyUpdate)
//            throw new Exception("No record was changed so no update occurred");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean equalsDrugDetailObjects(DrugDetail detail,DrugDetailDTO dto,Long id) throws Exception
    {
        DrugDetail tmp=new DrugDetail();
        tmp.setDrugDetailId(id);
        tmp.setBasePrice(dto.getBasePrice());
        tmp.setDefQty(dto.getDefQty());
        tmp.setMarginPercent(dto.getMarginPercent());
//        tmp.setAdditionalFee(dto.getAdditionalFee());
//        tmp.setMktSurcharge(dto.getMktSurcharge());
        return tmp.equals(detail);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void calculateDrugBrandPrice(DrugDetail drugDetail, DrugDetailDTO drugDetailDTO, Integer qty) throws NumberFormatException {
        Float basePrice =drugDetailDTO!=null && drugDetailDTO.getBasePrice()>0f?drugDetailDTO.getBasePrice():drugDetail.getBasePrice();
        logger.info("Before Format basePrice: " + basePrice);
        String basisPrice = CommonUtil.getFloatFormat(basePrice);
        logger.info("After Format basePrice: " + basisPrice);
        drugDetailDTO.setBasePrice(Float.parseFloat(basisPrice));
        drugDetailDTO.setBasePriceStr(AppUtil.roundOffNumberToCurrencyFormat(basePrice,"en","US"));
        Float acqPrice=drugDetailDTO.getAcqPrice()!=null?drugDetailDTO.getAcqPrice():0f;//drugDetail.getPkgCost()!=null?drugDetail.getPkgCost():0f;
        if(acqPrice<=0f)
        {
            acqPrice = drugDetailDTO.getBasePrice() * qty;
        }
        float calculatedAcqPrice=AppUtil.getSafeFloat(AppUtil.roundOffNumberToTwoDecimalPlaces(drugDetailDTO.getBasePrice() * qty),0f);
        drugDetailDTO.setCalculatedAcqPrice(calculatedAcqPrice);
        drugDetailDTO.setCalculatedAcqPriceStr(AppUtil.roundOffNumberToCurrencyFormat(calculatedAcqPrice,"en","US"));
        logger.info("Before Format acqPrice: " + acqPrice);
        String acqPrices = CommonUtil.getFloatFormat(acqPrice);
        logger.info("After Format basePrice: " + acqPrices);
        drugDetailDTO.setAcqPrice(Float.parseFloat(acqPrices));
        drugDetailDTO.setAcqPpriceStr(AppUtil.roundOffNumberToCurrencyFormat(acqPrice, "en", "US"));
                
        Float additionalFee = drugDetail.getAdditionalFee();
        logger.info("Before Format additionalFee: " + additionalFee);
        String baseOverHead = CommonUtil.getFloatFormat(additionalFee);
        logger.info("After Format additionalFee: " + baseOverHead);
        drugDetailDTO.setAdditionalFee(AppUtil.getSafeFloat(baseOverHead,0f));// * qty);
        drugDetailDTO.setAddittionalFeeStr(AppUtil.roundOffNumberToCurrencyFormat(drugDetailDTO.getAdditionalFee(), "en", "US"));
        drugDetailDTO.setUnitAdditionalFee(additionalFee!=null?additionalFee:0f);
        drugDetailDTO.setUnitAdditionalFeeStr(AppUtil.roundOffNumberToCurrencyFormat(drugDetailDTO.getUnitAdditionalFee(), "en", "US"));
        if(drugDetailDTO.getMarginPercent()<=0f)//if margin is not already set for drugdetaildto
        {
            drugDetailDTO.setMarginPercent(drugDetail.getMarginPercent());
        }
        Float markUpAmt = (drugDetailDTO.getAcqPrice() * drugDetailDTO.getMarginPercent()) / 100;
        logger.info("Before Format markUpAmt: " + markUpAmt);
        String markUpAmtStr = CommonUtil.getFloatFormat(markUpAmt);
        logger.info("After Format markUpAmt: " + markUpAmtStr);
        drugDetailDTO.setMarkUpAmt(Float.parseFloat(markUpAmtStr));
        drugDetailDTO.setMarkupAmtStr(AppUtil.roundOffNumberToCurrencyFormat(drugDetailDTO.getMarkUpAmt(), "en", "US"));
        if(drugDetailDTO.getUnitMarkupAmt()<=0)
        {
            drugDetailDTO.setUnitMarkupAmt(
                    (drugDetailDTO.getBasePrice()* drugDetailDTO.getMarginPercent()) / 100);
        }
        drugDetailDTO.setUnitMarkupAmtStr(AppUtil.roundOffNumberToCurrencyFormat(drugDetailDTO.getUnitMarkupAmt(), "en", "US"));
        logger.info("Before Format mktSurcharge: " + drugDetail.getMktSurcharge());
        String mktSurcharge = CommonUtil.getFloatFormat(drugDetail.getMktSurcharge());
        logger.info("After Format mktSurcharge: " + mktSurcharge);
        drugDetailDTO.setMktSurcharge(Float.parseFloat(mktSurcharge));
        drugDetailDTO.setMktSurchatgeStr(AppUtil.roundOffNumberToCurrencyFormat(drugDetailDTO.getMktSurcharge(), "en", "US"));
        
        float unitRetailPrice=drugDetailDTO.getBasePrice()+drugDetailDTO.getUnitMarkupAmt();//
                        //     +drugDetailDTO.getUnitAdditionalFee()+drugDetailDTO.getMktSurcharge();
        
        drugDetailDTO.setUnitRetailPrice(unitRetailPrice);
        drugDetailDTO.setUnitRetailPriceStr(AppUtil.roundOffNumberToCurrencyFormat(unitRetailPrice,"en","US"));
        
        Float estimatedCashPrice = drugDetailDTO.getAcqPrice() + drugDetailDTO.getMarkUpAmt();// + drugDetailDTO.getAdditionalFee() + drugDetailDTO.getMktSurcharge();
        logger.info("Before Format estimatedCashPrice: " + estimatedCashPrice);
        String finalCashPrice = CommonUtil.getFloatFormat(estimatedCashPrice);
        logger.info("After Format estimatedCashPrice: " + finalCashPrice);
        drugDetailDTO.setEstimatedCashPrice(Float.parseFloat(finalCashPrice));
        drugDetailDTO.setEstimatedCashPriceStr(AppUtil.roundOffNumberToCurrencyFormat(estimatedCashPrice,"en","US"));
    }

    public String estimateDrugPriceList(Long drugGCN, Integer qty, String dosageForm, String brandname, String drugStrength) {
        String json = null;
        try {
            List<DrugDetail> drugDetailList = consumerPortalDAO.getDrugDetailsList(drugGCN, dosageForm, brandname, drugStrength);
            List<DrugDetailDTO> list = new ArrayList<>();
            if (!CommonUtil.isNullOrEmpty(drugDetailList)) {
                int count = 0;
                for (DrugDetail drugDetail : drugDetailList) {
                    DrugDetailDTO drugDetailDTO = new DrugDetailDTO();
                    populateDrugDetail(drugDetailDTO, drugDetail, qty);
                    if (count == 0) {

                        drugDetailDTO.setIsChk(Boolean.TRUE);
                    } else {
                        drugDetailDTO.setIsChk(Boolean.FALSE);
                    }
                    list.add(drugDetailDTO);
                    count++;
                }
            }
            json = new ObjectMapper().writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Exception:: ConsumerPortalService:: estimateDrugPriceList", e);
        }
        return json;
    }
    /////////////////////////////////////////////////////////////////////////////////////
    public List<DrugDetailDTO> populateDrugDetailDTOList(List<DrugDetail> drugDetailList) throws Exception
    {
        List<DrugDetailDTO> list = new ArrayList<>();
        if (!CommonUtil.isNullOrEmpty(drugDetailList)) 
        {
                int count = 0;
                for (DrugDetail drugDetail : drugDetailList) 
                {
                    DrugDetailDTO drugDetailDTO = new DrugDetailDTO();
                    populateDrugDetail(drugDetailDTO, drugDetail, drugDetail.getDefQty());
                    if (count == 0) 
                    {

                        drugDetailDTO.setIsChk(Boolean.TRUE);
                    }
                    else 
                    {
                        drugDetailDTO.setIsChk(Boolean.FALSE);
                    }
                    list.add(drugDetailDTO);
                    count++;
             }
        }
        return list;
    }
    
    public List estimateDrugPriceListbyGCN(String drugGCNs)throws Exception {
        List<String> lst=Arrays.asList(drugGCNs.split(","));
        List<Long> longList = Lists.transform(lst, new Function<String, Long>() {
        public Long apply(String s) {
           return Long.valueOf(s);
        }
     });
        List<DrugDetail> drugDetailList = consumerPortalDAO.getDrugDetailsListbyGCNs(longList);
        List<DrugDetailDTO> list = this.populateDrugDetailDTOList(drugDetailList);
        return list;
    }
    ///////////////////////////////////////////////////////////////////////
    public DrugDetailDTO estimateDrugPriceListbyDrugId(Long drugId,DrugDetailDTO dto)throws Exception 
    {
        
            List<DrugDetail> drugDetailList =consumerPortalDAO.getDrugDetailsListbyId(drugId);
            DrugDetail detail=null;
//            DrugDetailDTO dto=null;
            if(drugDetailList!=null && drugDetailList.size()>0)
            {
                detail=drugDetailList.get(0);
            }
            if(detail!=null)
            {
                if(dto==null)
                {
                    dto=new DrugDetailDTO();
                }
                populateDrugDetail(dto, detail);//, defQty,basePrice,marginPercent);
                return dto;
            }
            throw new Exception("No calculation found.");
            
     }
    ///////////////////////////////////////////////////////////////////////
    public List estimateDrugPriceListbyDrugName(String drugName)throws Exception {
        
            List<DrugDetail> drugDetailList =null;
            List<DrugDetailDTO> list = this.populateDrugDetailDTOList(drugDetailList);
            String[] drugArr=AppUtil.getBrandAndGenericFromDrugName(drugName);
            if(drugArr!=null)
            {
                if(drugArr.length==1)
                {
                    drugDetailList = consumerPortalDAO.getDrugDetailsListbyBrandName(drugArr[0]);
                }
                else
                {
                    drugDetailList = consumerPortalDAO.getDrugDetailsListbyGenericName(drugArr[1]);
                }
                list = this.populateDrugDetailDTOList(drugDetailList);
                return list;
            }
            return null;
            
     }
    //////////////////////////////////////////////////////////////////////
}
