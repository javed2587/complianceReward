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
//@Table(name = "DrugTherapyClass")
public class DrugTherapyClass extends AuditFields implements Serializable {

    private Integer id;
    private String drugTherapyClassName;

    private DrugCategory drugCategory;
    private Set<DrugGenericTypes> drugGenericTypes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DrugTherapyClassName")
    public String getDrugTherapyClassName() {
        return drugTherapyClassName;
    }

    public void setDrugTherapyClassName(String drugTherapyClassName) {
        this.drugTherapyClassName = drugTherapyClassName;
    }

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "DrugCategoryId", nullable = false, insertable = true, updatable = true)
    public DrugCategory getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(DrugCategory drugCategory) {
        this.drugCategory = drugCategory;
    }

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "drugTherapyClass")
    //@OrderBy(clause = "id asc")
    @Transient
    public Set<DrugGenericTypes> getDrugGenericTypes() {
        return drugGenericTypes;
    }

    public void setDrugGenericTypes(Set<DrugGenericTypes> drugGenericTypes) {
        this.drugGenericTypes = drugGenericTypes;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DrugTherapyClass)) {
            return false;
        }
        DrugTherapyClass castOther = (DrugTherapyClass) other;
        return new EqualsBuilder().append(getId(), castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}
