/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.model;

import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;
//
///**
// *
// * @author adeel.usmani
// */
//@Entity
//@Table(name = "druggeneric2")
public class DrugGeneric extends AuditFields implements java.io.Serializable {
    
    private Integer drugGenericID;
    private String genericName;
    //private String demandBrand;
    private String therapeuticClass;
    private Integer brandNameOnly;
    private Set<DrugBasic> drugBasicSet;
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "DrugGenericID", unique = true, nullable = false)
    public Integer getDrugGenericID() {
        return drugGenericID;
    }

    public void setDrugGenericID(Integer drugGenericID) {
        this.drugGenericID = drugGenericID;
    }

    
    //@Column(name = "GenericName")
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }
    
   
    //@Column(name = "TherapeuticClass")
    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    //@Column(name = "BrandNameOnly")
    public Integer getBrandNameOnly() {
        return brandNameOnly;
    }

    public void setBrandNameOnly(Integer brandNameOnly) {
        this.brandNameOnly = brandNameOnly;
    }

   
    
    //@OneToMany(mappedBy = "drugGeneric")
    public Set<DrugBasic> getDrugBasicSet() {
        return drugBasicSet;
    }

    public void setDrugBasicSet(Set<DrugBasic> drugBasicSet) {
        this.drugBasicSet = drugBasicSet;
    }

    
   
    
    
    
    
}
