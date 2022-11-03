/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.model;

import java.util.Date;
import java.util.List;
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

/**
 *
 * @author adeel.usmani
 */
//@Entity
//@Table(name = "drugpacking")
public class DrugPacking extends AuditFields implements java.io.Serializable
{
    private Integer drugPackingId;
    private String packingDescr;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DrugPackingId", unique = true, nullable = false)
    public Integer getDrugPackingId() {
        return drugPackingId;
    }

    public void setDrugPackingId(Integer drugPackingId) {
        this.drugPackingId = drugPackingId;
    }

    @Column(name = "PackingDescr")
    public String getPackingDescr() {
        return packingDescr;
    }

    public void setPackingDescr(String packingDescr) {
        this.packingDescr = packingDescr;
    }
    
    
}
