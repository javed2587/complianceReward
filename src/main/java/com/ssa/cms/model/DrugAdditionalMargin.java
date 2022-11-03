package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "DrugAdditionalMargin")
public class DrugAdditionalMargin extends AuditFields implements Serializable {

    private Integer id;
    private DrugCategory drugCategory;
    private List<DrugAdditionalMarginPrices> drugAdditionalMarginPricesList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drugAdditionalMargin", orphanRemoval = true)
    public List<DrugAdditionalMarginPrices> getDrugAdditionalMarginPricesList() {
        return drugAdditionalMarginPricesList;
    }

    public void setDrugAdditionalMarginPricesList(List<DrugAdditionalMarginPrices> drugAdditionalMarginPricesList) {
        this.drugAdditionalMarginPricesList = drugAdditionalMarginPricesList;
    }

    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "DrugCategoryId", insertable = true, updatable = true)
    public DrugCategory getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(DrugCategory drugCategory) {
        this.drugCategory = drugCategory;
    }

}
