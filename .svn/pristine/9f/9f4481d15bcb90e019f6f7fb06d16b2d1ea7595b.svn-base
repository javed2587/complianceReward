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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author adeel.usmani
 */
//@Entity
//@Table(name = "DrugPackingDesc")
public class DrugPackingDesc extends AuditFields implements java.io.Serializable
{
    private Long drugPackingDescrId;
    private int  drugPackingSize;
    private DrugDetail drugDetail;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DrugPackingDescID", unique = true, nullable = false)
    public Long getDrugPackingDescrId() {
        return drugPackingDescrId;
    }

    public void setDrugPackingDescrId(Long drugPackingDescrId) {
        this.drugPackingDescrId = drugPackingDescrId;
    }

    @Column(name = "DrugPackingSize")
    public int getDrugPackingSize() {
        return drugPackingSize;
    }

    public void setDrugPackingSize(int drugPackingSize) {
        this.drugPackingSize = drugPackingSize;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugNdc", nullable = true, insertable = true, updatable = true)
    public DrugDetail getDrugDetail() {
        return drugDetail;
    }

    public void setDrugDetail(DrugDetail drugDetail) {
        this.drugDetail = drugDetail;
    }
    
    
    
}
