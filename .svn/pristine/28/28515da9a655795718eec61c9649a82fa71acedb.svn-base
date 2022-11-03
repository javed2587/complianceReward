package com.ssa.cms.model;
// Generated Apr 1, 2013 1:01:46 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import javax.persistence.Transient;

//@Entity
//@Table(name = "Drug")
public class Drug extends AuditFields implements java.io.Serializable {

    private Integer drugId;
    private DrugBrand drugBrand;
    private String drugGpi;
    private String strength;
    private String drugType;
    private String routeDescription;
    private String drugGcn;
    private Double drugMacPrice;
    private Double drugAwpPrice;
    private String daeSchedule;
    private List<DrugSearches> drugSearchesList;
    int remove;
    private String ndcstrengthvalue;
    private Integer selectedDrugId;
    private DrugUnits drugUnits;
    private Object strengthList;
    private Object dType;
    private Object shippingFee;
    private Double cost;
    private String drugName;
    private String genericName;
    private Integer errorCode;
    private Double totalPrice;
    private Double finalPrice;
    private Long redeemedPoints;
    private String redeemedPointsPrice;
    private Long lifeTimePoints;
    private String type;
    private String qty;
    private BigDecimal additionalMargin;
    private Double drugCostWithoutMargin;
    private Long drugNdc;
    private Map drugMap;
    private Double finalDrugCost;

    public Drug() {
    }

    public Drug(DrugBrand drugBrand, String ndcnumber, String strength, String isActive, String isDelete, String createdBy, Date createOn, String lastModifiedBy, Date lastModifiedOn) {
        this.drugBrand = drugBrand;
        this.strength = strength;
    }

    public Drug(Integer drugId) {
        this.drugId = drugId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DrugId", unique = true, nullable = false)
    public Integer getDrugId() {
        return this.drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "DrugBrandId", nullable = false, insertable = true, updatable = true)
    public DrugBrand getDrugBrand() {
        return this.drugBrand;
    }

    public void setDrugBrand(DrugBrand drugBrand) {
        this.drugBrand = drugBrand;
    }

    @Column(name = "Strength", nullable = false, length = 500)
    public String getStrength() {
        return this.strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Transient
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    @Transient
    public String getNdcstrengthvalue() {
        return ndcstrengthvalue;
    }

    public void setNdcstrengthvalue(String ndcstrengthvalue) {
        this.ndcstrengthvalue = ndcstrengthvalue;
    }

    @Transient
    public Integer getSelectedDrugId() {
        return selectedDrugId;
    }

    public void setSelectedDrugId(Integer selectedDrugId) {
        this.selectedDrugId = selectedDrugId;
    }

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "DrugUnitId", nullable = false, insertable = true, updatable = true)
    public DrugUnits getDrugUnits() {
        return drugUnits;
    }

    public void setDrugUnits(DrugUnits drugUnits) {
        this.drugUnits = drugUnits;
    }

    //@Column(name = "DrugGpi", unique = true, nullable = false, length = 100)
    @Column(name = "DrugGpi", nullable = false, length = 100)
    public String getDrugGpi() {
        return drugGpi;
    }

    public void setDrugGpi(String drugGpi) {
        this.drugGpi = drugGpi;
    }

    @Column(name = "DrugType")
    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    @Column(name = "RouteDescription")
    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    @Column(name = "DrugGcn")
    public String getDrugGcn() {
        return drugGcn;
    }

    public void setDrugGcn(String drugGcn) {
        this.drugGcn = drugGcn;
    }

    @Column(name = "DrugMacPrice")
    public Double getDrugMacPrice() {
        return drugMacPrice;
    }

    public void setDrugMacPrice(Double drugMacPrice) {
        this.drugMacPrice = drugMacPrice;
    }

    @Column(name = "DrugAwpPrice")
    public Double getDrugAwpPrice() {
        return drugAwpPrice;
    }

    public void setDrugAwpPrice(Double drugAwpPrice) {
        this.drugAwpPrice = drugAwpPrice;
    }

    @Column(name = "DaeSchedule")
    public String getDaeSchedule() {
        return daeSchedule;
    }

    public void setDaeSchedule(String daeSchedule) {
        this.daeSchedule = daeSchedule;
    }

    @Transient
    public Object getStrengthList() {
        return strengthList;
    }

    public void setStrengthList(Object strengthList) {
        this.strengthList = strengthList;
    }

    @Transient
    public Object getdType() {
        return dType;
    }

    public void setdType(Object dType) {
        this.dType = dType;
    }

    @Transient
    public Object getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Object shippingFee) {
        this.shippingFee = shippingFee;
    }

    @Transient
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @Transient
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Transient
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Transient
    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Transient
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Transient
    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Transient
    public Long getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(Long redeemedPoints) {
        this.redeemedPoints = redeemedPoints;
    }

    @Transient
    public String getRedeemedPointsPrice() {
        return redeemedPointsPrice;
    }

    public void setRedeemedPointsPrice(String redeemedPointsPrice) {
        this.redeemedPointsPrice = redeemedPointsPrice;
    }

    @Transient
    public Long getLifeTimePoints() {
        return lifeTimePoints;
    }

    public void setLifeTimePoints(Long lifeTimePoints) {
        this.lifeTimePoints = lifeTimePoints;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drug")
    public List<DrugSearches> getDrugSearchesList() {
        return drugSearchesList;
    }

    public void setDrugSearchesList(List<DrugSearches> drugSearchesList) {
        this.drugSearchesList = drugSearchesList;
    }

    @Transient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Transient
    public BigDecimal getAdditionalMargin() {
        return additionalMargin;
    }

    public void setAdditionalMargin(BigDecimal additionalMargin) {
        this.additionalMargin = additionalMargin;
    }

    @Transient
    public Double getDrugCostWithoutMargin() {
        return drugCostWithoutMargin;
    }

    public void setDrugCostWithoutMargin(Double drugCostWithoutMargin) {
        this.drugCostWithoutMargin = drugCostWithoutMargin;
    }

    @Transient
    public Long getDrugNdc() {
        return drugNdc;
    }

    public void setDrugNdc(Long drugNdc) {
        this.drugNdc = drugNdc;
    }

    @Transient
    public Map getDrugMap() {
        return drugMap;
    }

    public void setDrugMap(Map drugMap) {
        this.drugMap = drugMap;
    }

    @Transient
    public Double getFinalDrugCost() {
        return finalDrugCost;
    }

    public void setFinalDrugCost(Double finalDrugCost) {
        this.finalDrugCost = finalDrugCost;
    }

}
