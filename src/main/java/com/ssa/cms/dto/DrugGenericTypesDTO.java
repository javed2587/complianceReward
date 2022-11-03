/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Haider Ali
 */
public class DrugGenericTypesDTO implements Serializable{
    
    private Integer id;
    private String  drugGenericName;
    
    private List<DrugBrandDTO> drugBrand; 
    

    public DrugGenericTypesDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugGenericName() {
        return drugGenericName;
    }

    public void setDrugGenericName(String drugGenericName) {
        this.drugGenericName = drugGenericName;
    }

    public List<DrugBrandDTO> getDrugBrand() {
        return drugBrand;
    }

    public void setDrugBrand(List<DrugBrandDTO> drugBrand) {
        this.drugBrand = drugBrand;
    }
}
