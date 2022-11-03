/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Haider Ali
 */
//@Entity
//@Table(name = "DrugGenericTypes")
public class DrugGenericTypes extends AuditFields implements Serializable {

    private Integer id;
    private String drugGenericName;

    private DrugTherapyClass drugTherapyClass;
    private Set<DrugBrand> drugBrand;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DrugGenericName")
    public String getDrugGenericName() {
        return drugGenericName;
    }

    public void setDrugGenericName(String drugGenericName) {
        this.drugGenericName = drugGenericName;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DrugTherapyClassId", nullable = false, insertable = true, updatable = true)
    public DrugTherapyClass getDrugTherapyClass() {
        return drugTherapyClass;
    }

    public void setDrugTherapyClass(DrugTherapyClass drugTherapyClass) {
        this.drugTherapyClass = drugTherapyClass;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "drugGenericTypes")
//    @OrderBy(clause = "id asc")
//
    @Transient
    public Set<DrugBrand> getDrugBrand() {
        return drugBrand;
    }

    public void setDrugBrand(Set<DrugBrand> drugBrand) {
        this.drugBrand = drugBrand;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DrugGenericTypes)) {
            return false;
        }
        DrugGenericTypes castOther = (DrugGenericTypes) other;
        return new EqualsBuilder().append(getId(), castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}
