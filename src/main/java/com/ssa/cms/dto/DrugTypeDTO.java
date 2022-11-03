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
public class DrugTypeDTO 
{
    private int defQty;
    private String formPrefix;
    private String type;
    private String[] qtyValues;

    public int getDefQty() {
        return defQty;
    }

    public void setDefQty(int defQty) {
        this.defQty = defQty;
    }
    
    
    
    public String getFormPrefix() {
        return formPrefix;
    }

    public void setFormPrefix(String formPrefix) {
        this.formPrefix = formPrefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getQtyValues() {
        return qtyValues;
    }

    public void setQtyValues(String[] qtyValues) {
        this.qtyValues = qtyValues;
    }
    
    
    
    
}
