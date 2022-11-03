/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author adeel.usmani
 */

//@Entity
//@Table(name = "drugbasic")
public class DrugBasic extends AuditFields implements java.io.Serializable
{
    private Integer drugBasicId;
    private DrugGeneric drugGeneric;
    private String brandName;
    private String therapeuticCategory; 
    private String brandIndicator;
    private String genericName;
    private Set<DrugDetail> drugDetailSet;
    
    
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "DrugBasicId", unique = true, nullable = false)
    public Integer getDrugBasicId() {
        return drugBasicId;
    }

    public void setDrugBasicId(Integer drugBasicId) {
        this.drugBasicId = drugBasicId;
    }
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugGenericID", nullable = true, insertable = true, updatable = true)
    public DrugGeneric getDrugGeneric() {
        return drugGeneric;
    }

    public void setDrugGeneric(DrugGeneric drugGeneric) {
        this.drugGeneric = drugGeneric;
    }
    
    //@Column(name = "BrandName")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    //@Column(name = "TherapeuticCategory")
    public String getTherapeuticCategory() {
        return therapeuticCategory;
    }

    public void setTherapeuticCategory(String therapeuticCategory) {
        this.therapeuticCategory = therapeuticCategory;
    }

    //@Column(name = "BrandIndicator")
    public String getBrandIndicator() {
        return brandIndicator;
    }

    public void setBrandIndicator(String brandIndicator) {
        this.brandIndicator = brandIndicator;
    }
    
    

//    @OneToMany(mappedBy = "drugBasic")
//    @OrderBy("strength")
    public Set<DrugDetail> getDrugDetailSet() {
        return drugDetailSet;
    }

    public void setDrugDetailSet(Set<DrugDetail> drugDetailSet) {
        this.drugDetailSet = drugDetailSet;
    }

    //@Transient
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }
    
    
    
    
}
