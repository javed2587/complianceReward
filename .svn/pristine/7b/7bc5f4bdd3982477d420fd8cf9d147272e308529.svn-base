package com.ssa.cms.model;
// Generated Apr 1, 2013 1:01:46 PM by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

//@Entity
//@Table(name = "DrugBrand")
public class DrugBrand extends AuditFields implements java.io.Serializable {

    private Integer id;
    private String drugBrandName;
    private DrugGenericTypes drugGenericTypes;
    private Set<Campaigns> campaignses = new HashSet<>(0);
    //@Valid
    //@NotNull
    //@Size(min = 1)
    private List<Drug> drugs = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DrugBrandName")
    public String getDrugBrandName() {
        return drugBrandName;
    }

    public void setDrugBrandName(String drugBrandName) {
        this.drugBrandName = drugBrandName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DrugGenericId", nullable = false, insertable = true, updatable = true)
    public DrugGenericTypes getDrugGenericTypes() {
        return drugGenericTypes;
    }

    public void setDrugGenericTypes(DrugGenericTypes drugGenericTypes) {
        this.drugGenericTypes = drugGenericTypes;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drugBrands")
    public Set<Campaigns> getCampaignses() {
        return this.campaignses;
    }

    public void setCampaignses(Set<Campaigns> campaignses) {
        this.campaignses = campaignses;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drugBrand")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy(clause = "drugId ASC")
    public List<Drug> getDrugs() {
        return this.drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DrugBrand)) {
            return false;
        }
        DrugBrand castOther = (DrugBrand) other;
        return new EqualsBuilder().append(getId(), castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }
}
