/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import com.ssa.cms.util.AppUtil;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author adeel.usmani
 */
//@Entity
//@Table(name = "drugdetail")
public class DrugDetail extends AuditFields implements java.io.Serializable {

    private Long drugDetailId;
    private Long drugNDC;
    private Long drugGPI;
    private Long drugGCN;
    private String brandName;
    private String strength;
    private String packageSizeValues;
    private float marginPercent;
    private float basePrice;
    private Float pkgCost;
    private Float unitMarkupAmt;
    private float additionalFee;
    private int defQty;
    private String inStock;
    private String archived;
    private String demandBrand;
    private float goodRx;
    private int archivedBy;
    private Date archivedOn;
    private DrugBasic drugBasic;
    private DrugPacking drugPacking;
    private DrugForm drugForm;
    private String formDesc;
    //private String formPrefix;
    private String packingDesc;
    private String genericName;
    private String therapy;
    private String pdf;
    private String image;
    private List<DrugDetailDocuments> docs;
    private Set<DrugPackingDesc> drugPackingDescSet;
    private List<Order> orders;
    private Float totalPrice;
    private Float finalPrice;
    private Long redeemedPoints;
    private Float redeemedPointsPrice;
    private Long lifeTimePoints;
    private int drugQty;
    private Float additionalMargin;
    private Float profitValue;
    private double drugProfit;
    private Float drugCost;
    private String requiresHandDelivery;
    private String monthRxExpiration;
    private String imgUrl;
    private String pdfDocUrl;
    private String drugDocUrl;
    private String drugDoc;
    private Date drugDocUpdatedOn;
    private Date pdfDocUpdatedOn;
    private Date imgUpdatedOn;
    private String gnn;
    private Float mktSurcharge;
    private float profitShare;
    private int pointsFromShare;
    private float costFromPoints;
    private int excelSheetRowNumber;
    private String keyText;
    private String rxNo;
    private String pymentType;
    private Date lastRefillDate;
    private int daysSupply;
    private int refillsRemaining;
    private Date expiryDate;
    private Float rxAcqCost;
    private Double finalDrugCost;

    public DrugDetail() {
    }

//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "drugdetailid", unique = true, nullable = false)
    public Long getDrugDetailId() {
        return drugDetailId;
    }

    public void setDrugDetailId(Long drugDetailId) {
        this.drugDetailId = drugDetailId;
    }

   // @Column(name = "DrugNdc")
    public Long getDrugNDC() {
        return drugNDC;
    }

    public void setDrugNDC(Long drugNDC) {
        this.drugNDC = drugNDC;
    }

    //@Column(name = "DrugGPI")
    public Long getDrugGPI() {
        return drugGPI;
    }

    public void setDrugGPI(Long drugGPI) {
        this.drugGPI = drugGPI;
    }

    //@Column(name = "DrugGCN")
    public Long getDrugGCN() {
        return drugGCN;
    }

    public void setDrugGCN(Long drugGCN) {
        this.drugGCN = drugGCN;
    }

    //@Column(name = "Strength")
    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    //@Column(name = "PackageSizeValues")
    public String getPackageSizeValues() {
        return packageSizeValues;
    }

    public void setPackageSizeValues(String packageSizeValues) {
        this.packageSizeValues = packageSizeValues;
    }

    //@Column(name = "MarginPercent")
    public float getMarginPercent() {
        return marginPercent;
    }

    public void setMarginPercent(float marginPercent) {
        this.marginPercent = marginPercent;
    }

    //@Column(name = "BasePrice")
    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    //@Column(name = "PkgCost")
    public Float getPkgCost() {
        return pkgCost;
    }

    public void setPkgCost(Float pkgCost) {
        this.pkgCost = pkgCost;
    }

    //@Column(name = "UnitMarkupAmt")
    public Float getUnitMarkupAmt() {
        return unitMarkupAmt;
    }

    public void setUnitMarkupAmt(Float unitMarkupAmt) {
        this.unitMarkupAmt = unitMarkupAmt;
    }

    //@Column(name = "AdditionalFee")
    public float getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(float additionalFee) {
        this.additionalFee = additionalFee;
    }

    //@Column(name = "DefaultQty")
    public int getDefQty() {
        return defQty;
    }

    public void setDefQty(int defQty) {
        this.defQty = defQty;
    }

    //@Column(name = "InStock")
    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    //@Column(name = "Archived")
    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    //@Column(name = "GoodRx")
    public float getGoodRx() {
        return goodRx;
    }

    public void setGoodRx(float goodRx) {
        this.goodRx = goodRx;
    }

    //@Column(name = "ArchivedBy")
    public int getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(int archivedBy) {
        this.archivedBy = archivedBy;
    }

    //@Column(name = "RequiresHandDelivery")
    public String getRequiresHandDelivery() {
        return requiresHandDelivery;
    }

    public void setRequiresHandDelivery(String requiresHandDelivery) {
        this.requiresHandDelivery = requiresHandDelivery;
    }

    //@Column(name = "MonthRxExpiration")
    public String getMonthRxExpiration() {
        return monthRxExpiration;
    }

    public void setMonthRxExpiration(String monthRxExpiration) {
        this.monthRxExpiration = monthRxExpiration;
    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "ArchivedOn", length = 19)
    public Date getArchivedOn() {
        return archivedOn;
    }

    public void setArchivedOn(Date archivedOn) {
        this.archivedOn = archivedOn;
    }

    //@Column(name = "DrugDocUrl")
    public String getDrugDocUrl() {
        return drugDocUrl;
    }

    public void setDrugDocUrl(String drugDocUrl) {
        this.drugDocUrl = drugDocUrl;
    }

    //@Column(name = "DrugDoc")
    public String getDrugDoc() {
        return drugDoc;
    }

    public void setDrugDoc(String drugDoc) {
        this.drugDoc = drugDoc;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugBasicId", nullable = true, insertable = true, updatable = true)
    public DrugBasic getDrugBasic() {
        return drugBasic;
    }

    public void setDrugBasic(DrugBasic drugBasic) {
        this.drugBasic = drugBasic;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugPackingId", nullable = true, insertable = true, updatable = true)
    public DrugPacking getDrugPacking() {
        return drugPacking;
    }

    public void setDrugPacking(DrugPacking drugPacking) {
        this.drugPacking = drugPacking;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugFormID", nullable = true, insertable = true, updatable = true)
    public DrugForm getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(DrugForm drugForm) {
        this.drugForm = drugForm;
    }

   // @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "drugDetail")
    public List<DrugDetailDocuments> getDocs() {
        return docs;
    }

    public void setDocs(List<DrugDetailDocuments> docs) {
        this.docs = docs;
    }

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "drugDetail")
    public Set<DrugPackingDesc> getDrugPackingDescSet() {
        return drugPackingDescSet;
    }

    public void setDrugPackingDescSet(Set<DrugPackingDesc> drugPackingDescSet) {
        this.drugPackingDescSet = drugPackingDescSet;
    }

    //@Transient
    public String getFormDesc() {
        return formDesc;
    }

    public void setFormDesc(String formDesc) {
        this.formDesc = formDesc;
    }

    //@Column(name = "PackingDescr")
    public String getPackingDesc() {
        return packingDesc;
    }

    public void setPackingDesc(String packingDesc) {
        this.packingDesc = packingDesc;
    }

    //@Transient
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    //@Transient
    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String Therapy) {
        this.therapy = Therapy;
    }

    //@Column(name = "DemandBrand")
    public String getDemandBrand() {
        return demandBrand;
    }

    public void setDemandBrand(String demandBrand) {
        this.demandBrand = demandBrand;
    }

    //@Transient
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    //@Column(name = "PdfDoc")
    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    //@Column(name = "Img")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "drugDetail",fetch = FetchType.LAZY)

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "drugDetail",fetch = FetchType.LAZY)
    @Transient
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //@Transient
    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    //@Transient
    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    //@Transient
    public Long getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(Long redeemedPoints) {
        this.redeemedPoints = redeemedPoints;
    }

    //@Transient
    public Float getRedeemedPointsPrice() {
        return redeemedPointsPrice;
    }

    public void setRedeemedPointsPrice(Float redeemedPointsPrice) {
        this.redeemedPointsPrice = redeemedPointsPrice;
    }

    //@Transient
    public Long getLifeTimePoints() {
        return lifeTimePoints;
    }

    public void setLifeTimePoints(Long lifeTimePoints) {
        this.lifeTimePoints = lifeTimePoints;
    }

    //@Transient
    public int getDrugQty() {
        return drugQty;
    }

    public void setDrugQty(int drugQty) {
        this.drugQty = drugQty;
    }

    //@Transient
    public Float getAdditionalMargin() {
        return additionalMargin;
    }

    public void setAdditionalMargin(Float additionalMargin) {
        this.additionalMargin = additionalMargin;
    }

    //@Transient
    public Float getProfitValue() {
        return profitValue;
    }

    public void setProfitValue(Float profitValue) {
        this.profitValue = profitValue;
    }

    //@Transient
    public double getDrugProfit() {
        return drugProfit;
    }

    public void setDrugProfit(double drugProfit) {
        this.drugProfit = drugProfit;
    }

    //@Transient
    public Float getDrugCost() {
        return drugCost;
    }

    public void setDrugCost(Float drugCost) {
        this.drugCost = drugCost;
    }

    //@Column(name = "ImgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    //@Column(name = "PdfDocUrl")
    public String getPdfDocUrl() {
        return pdfDocUrl;
    }

    public void setPdfDocUrl(String pdfDocUrl) {
        this.pdfDocUrl = pdfDocUrl;
    }

//    @Column(name = "DrugDocUpdatedOn")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDrugDocUpdatedOn() {
        return drugDocUpdatedOn;
    }

    public void setDrugDocUpdatedOn(Date drugDocUpdatedOn) {
        this.drugDocUpdatedOn = drugDocUpdatedOn;
    }

//    @Column(name = "PdfDocUpdatedOn")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getPdfDocUpdatedOn() {
        return pdfDocUpdatedOn;
    }

    public void setPdfDocUpdatedOn(Date pdfDocUpdatedOn) {
        this.pdfDocUpdatedOn = pdfDocUpdatedOn;
    }

//    @Column(name = "ImgUpdatedOn")
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getImgUpdatedOn() {
        return imgUpdatedOn;
    }

    public void setImgUpdatedOn(Date imgUpdatedOn) {
        this.imgUpdatedOn = imgUpdatedOn;
    }

    //@Column(name = "GNN")
    public String getGnn() {
        return gnn;
    }

    public void setGnn(String gnn) {
        this.gnn = gnn;
    }

    //@Column(name = "MktSurcharge")
    public Float getMktSurcharge() {
        return mktSurcharge;
    }

    public void setMktSurcharge(Float mktSurcharge) {
        this.mktSurcharge = mktSurcharge;
    }

    //@Transient
    public float getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(float profitShare) {
        this.profitShare = profitShare;
    }

    //@Transient
    public int getPointsFromShare() {
        return pointsFromShare;
    }

    public void setPointsFromShare(int pointsFromShare) {
        this.pointsFromShare = pointsFromShare;
    }

    //@Transient
    public float getCostFromPoints() {
        return costFromPoints;
    }

    public void setCostFromPoints(float costFromPoints) {
        this.costFromPoints = costFromPoints;
    }

    //@Transient
    public int getExcelSheetRowNumber() {
        return excelSheetRowNumber;
    }

    public void setExcelSheetRowNumber(int excelSheetRowNumber) {
        this.excelSheetRowNumber = excelSheetRowNumber;
    }

    //@Transient
    public String getKeyText() {
        return keyText;
    }

    public void setKeyText(String keyText) {
        this.keyText = keyText;
    }

    ///////////////////////////////////////////////////
    //@Transient
    public String getRxNo() {
        return rxNo;
    }

    public void setRxNo(String rxNo) {
        this.rxNo = rxNo;
    }

    //@Transient
    public String getPymentType() {
        return pymentType;
    }

    public void setPymentType(String pymentType) {
        this.pymentType = pymentType;
    }

    //@Transient
    public Date getLastRefillDate() {
        return lastRefillDate;
    }

    public void setLastRefillDate(Date lastRefillDate) {
        this.lastRefillDate = lastRefillDate;
    }

    //@Transient
    public int getDaysSupply() {
        return daysSupply;
    }

    public void setDaysSupply(int daysSupply) {
        this.daysSupply = daysSupply;
    }

    //@Transient
    public int getRefillsRemaining() {
        return refillsRemaining;
    }

    public void setRefillsRemaining(int refillsRemaining) {
        this.refillsRemaining = refillsRemaining;
    }

    //@Transient
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash + drugDetailId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrugDetail other = (DrugDetail) obj;
        if (Objects.equals(this.drugDetailId, other.drugDetailId)
                && AppUtil.roundOffNumberToTwoDecimalPlaces(this.marginPercent).equals(
                        AppUtil.roundOffNumberToTwoDecimalPlaces(other.marginPercent))
                && AppUtil.roundOffNumberToTwoDecimalPlaces(this.basePrice).equals(
                        AppUtil.roundOffNumberToTwoDecimalPlaces(other.basePrice))
                && this.defQty == other.defQty)// && this.pkgCost.equals(other.pkgCost))//&&
        // this.additionalFee==other.additionalFee && this.mktSurcharge.equals(other.mktSurcharge)) 
        {
            return true;
        }
        return false;
    }

    //@Transient
    public Float getRxAcqCost() {
        return rxAcqCost;
    }

    public void setRxAcqCost(Float rxAcqCost) {
        this.rxAcqCost = rxAcqCost;
    }

    //@Transient
    public Double getFinalDrugCost() {
        return finalDrugCost;
    }

    public void setFinalDrugCost(Double finalDrugCost) {
        this.finalDrugCost = finalDrugCost;
    }

}
