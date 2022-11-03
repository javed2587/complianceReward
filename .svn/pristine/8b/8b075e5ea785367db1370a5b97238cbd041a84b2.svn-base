/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author adeel.usmani
 */
//@Entity
//@Table(name = "DrugForm")
public class DrugForm extends AuditFields implements java.io.Serializable {

    private Integer drugFormId;
    private String formDescr;

    @Transient
    private List<DrugDetail> drugDetailList;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DrugFormID", unique = true, nullable = false)
    public Integer getDrugFormId() {
        return drugFormId;
    }

    public void setDrugFormId(Integer drugFormId) {
        this.drugFormId = drugFormId;
    }

    @Column(name = "FormDescr")
    public String getFormDescr() {
        return formDescr;
    }

    public void setFormDescr(String formDescr) {
        this.formDescr = formDescr;
    }

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "drugForm")
    @Transient
    public List<DrugDetail> getDrugDetailList() {
        return drugDetailList;
    }

    public void setDrugDetailList(List<DrugDetail> drugDetailList) {
        this.drugDetailList = drugDetailList;
    }

}
