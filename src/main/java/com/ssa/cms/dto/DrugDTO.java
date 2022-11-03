/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.dto;

/**
 *
 * @author adeel.usmani
 */
public class DrugDTO extends DrugTypeDTO 
{
    private Integer drugId;
    private Long    drugNDC;
    private String strength;
    private String brandName;
    private String genaricName;

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Long getDrugNDC() {
        return drugNDC;
    }

    public void setDrugNDC(Long drugNDC) {
        this.drugNDC = drugNDC;
    }
    
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenaricName() {
        return genaricName;
    }

    public void setGenaricName(String genaricName) {
        this.genaricName = genaricName;
    }
}
