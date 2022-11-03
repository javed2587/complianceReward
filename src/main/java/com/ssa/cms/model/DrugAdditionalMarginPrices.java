package com.ssa.cms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "DrugAdditionalMarginPrices")
public class DrugAdditionalMarginPrices extends AuditFields implements Serializable {

    private Integer id;
    private DrugAdditionalMargin drugAdditionalMargin;
    private Integer drugQtyFrom;
    private Integer drugQtyTo;
    private BigDecimal drugMarginValue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "DrugAdditionalMarginId", insertable = true, updatable = true)
    public DrugAdditionalMargin getDrugAdditionalMargin() {
        return drugAdditionalMargin;
    }

    public void setDrugAdditionalMargin(DrugAdditionalMargin drugAdditionalMargin) {
        this.drugAdditionalMargin = drugAdditionalMargin;
    }

    @Column(name = "DrugQtyFrom")
    public Integer getDrugQtyFrom() {
        return drugQtyFrom;
    }

    public void setDrugQtyFrom(Integer drugQtyFrom) {
        this.drugQtyFrom = drugQtyFrom;
    }

    @Column(name = "DrugQtyTo")
    public Integer getDrugQtyTo() {
        return drugQtyTo;
    }

    public void setDrugQtyTo(Integer drugQtyTo) {
        this.drugQtyTo = drugQtyTo;
    }

    @Column(name = "DrugMarginValue")
    public BigDecimal getDrugMarginValue() {
        return drugMarginValue;
    }

    public void setDrugMarginValue(BigDecimal drugMarginValue) {
        this.drugMarginValue = drugMarginValue;
    }

}
