package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OrderBy;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author mzubair
 */


//@Entity
//@Table(name = "DrugCategory")
public class DrugCategory extends AuditFields implements Serializable {

    private Integer id;
    private String drugCategoryName;
    private List<DrugAdditionalMargin> drugAdditionalMarginList;
    private Set<DrugTherapyClass> drugTherapyClass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DrugCategoryName")
    public String getDrugCategoryName() {
        return drugCategoryName;
    }

    public void setDrugCategoryName(String drugCategoryName) {
        this.drugCategoryName = drugCategoryName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drugCategory")
    public List<DrugAdditionalMargin> getDrugAdditionalMarginList() {
        return drugAdditionalMarginList;
    }

    public void setDrugAdditionalMarginList(List<DrugAdditionalMargin> drugAdditionalMarginList) {
        this.drugAdditionalMarginList = drugAdditionalMarginList;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "drugCategory")
    @OrderBy(clause = "id asc")
    public Set<DrugTherapyClass> getDrugTherapyClass() {
        return drugTherapyClass;
    }

    public void setDrugTherapyClass(Set<DrugTherapyClass> drugTherapyClass) {
        this.drugTherapyClass = drugTherapyClass;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DrugCategory)) {
            return false;
        }
        DrugCategory castOther = (DrugCategory) other;
        return new EqualsBuilder().append(getId(), castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}
