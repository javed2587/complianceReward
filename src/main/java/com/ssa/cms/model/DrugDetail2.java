package com.ssa.cms.model;

import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "drugs_new")
public class DrugDetail2 implements java.io.Serializable {

    private Integer drugId;
    private String gcnSeq;
    private String ndc;
    private String rxLabelName;
    private String strength;
    private String dosageForm;
    private String defaultRtXQty;
    private String unitPrice;
    private String genericOrBrand;
    private String genericProId;
    private String majorReportingCat;
    private String minorreportingClass;
    private String dea;
    private Integer rxExpire;
    private Integer refillsAllowed;
    private String marketer;
    private String genericName;
    private String sponsoredProduct;
    private String brandReference;
    private Integer totalSale;
    private List<Order> orders;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    @Column(name = "gcn_seq", unique = true, nullable = false)
    public String getGcnSeq() {
        return gcnSeq;
    }

    public void setGcnSeq(String gcnSeq) {
        this.gcnSeq = gcnSeq;
    }

    @Column(name = "ndc", unique = true, nullable = false)
    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    @Column(name = "rx_label_name", unique = true, nullable = false)
    public String getRxLabelName() {
        return rxLabelName;
    }

    public void setRxLabelName(String rxLabelName) {
        this.rxLabelName = rxLabelName;
    }

    @Column(name = "strength", unique = true, nullable = false)
    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Column(name = "dosage_form", unique = true, nullable = false)
    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    @Column(name = "default_rx_qty", unique = true, nullable = false)
    public String getDefaultRtXQty() {
        return defaultRtXQty;
    }

    public void setDefaultRtXQty(String defaultRtXQty) {
        this.defaultRtXQty = defaultRtXQty;
    }

    @Column(name = "unit_price", unique = true, nullable = false)
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "generic_or_brand", unique = true, nullable = false)
    public String getGenericOrBrand() {
        return genericOrBrand;
    }

    public void setGenericOrBrand(String genericOrBrand) {
        this.genericOrBrand = genericOrBrand;
    }

    @Column(name = "generic_pro_id", unique = true, nullable = false)
    public String getGenericProId() {
        return genericProId;
    }

    public void setGenericProId(String genericProId) {
        this.genericProId = genericProId;
    }

    @Column(name = "major_reporting_cat", unique = true, nullable = false)
    public String getMajorReportingCat() {
        return majorReportingCat;
    }

    public void setMajorReportingCat(String majorReportingCat) {
        this.majorReportingCat = majorReportingCat;
    }

    @Column(name = "minor_reporting_class", unique = true, nullable = false)
    public String getMinorreportingClass() {
        return minorreportingClass;
    }

    public void setMinorreportingClass(String minorreportingClass) {
        this.minorreportingClass = minorreportingClass;
    }

    @Column(name = "dea", unique = true, nullable = false)
    public String getDea() {
        return dea;
    }

    public void setDea(String dea) {
        this.dea = dea;
    }

    @Column(name = "rx_expire", unique = true, nullable = false)
    public Integer getRxExpire() {
        return rxExpire;
    }

    public void setRxExpire(Integer rxExpire) {
        this.rxExpire = rxExpire;
    }

    @Column(name = "refills_allowed", unique = true, nullable = false)
    public Integer getRefillsAllowed() {
        return refillsAllowed;
    }

    public void setRefillsAllowed(Integer refillsAllowed) {
        this.refillsAllowed = refillsAllowed;
    }

    @Column(name = "marketer", unique = true, nullable = false)
    public String getMarketer() {
        return marketer;
    }

    public void setMarketer(String marketer) {
        this.marketer = marketer;
    }

    @Column(name = "generic_name", unique = true, nullable = false)
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Column(name = "sponsored_product", unique = true, nullable = false)
    public String getSponsoredProduct() {
        return sponsoredProduct;
    }

    public void setSponsoredProduct(String sponsoredProduct) {
        this.sponsoredProduct = sponsoredProduct;
    }

    @Column(name = "brand_reference", unique = true, nullable = false)
    public String getBrandReference() {
        return brandReference;
    }

    public void setBrandReference(String brandReference) {
        this.brandReference = brandReference;
    }

    @Column(name = "total_sale")
    public Integer getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Integer totalSale) {
        this.totalSale = totalSale;
    }

    @OneToMany(mappedBy = "drugDetail2", fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
