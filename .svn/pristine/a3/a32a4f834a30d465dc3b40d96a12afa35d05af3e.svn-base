package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author SSASoft
 */
@Entity
@Table(name = "RedemptionIngredient")
public class RedemptionIngredient implements Serializable {

    @Size(max = 20)
    @Column(name = "TransactionNumber")
    private String transactionNumber;
    @Size(max = 19)
    @Column(name = "Ndc")
    private String ndc;
    @Column(name = "ProcessedSig")
    private String processedSig;
    @Column(name = "Covered")
    private Boolean covered;
    @Column(name = "Formulary")
    private Boolean formulary;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;

    @Size(max = 35)
    @Column(name = "Name")
    private String name;
    @Size(max = 10)
    @Column(name = "Strength")
    private String strength;
    @Size(max = 10)
    @Column(name = "Type")
    private String type;
    @Column(name = "Quantity")
    private Double quantity;
    @Column(name = "PlanCost")
    private Double planCost;
    @Column(name = "PharmacyCost")
    private Double pharmacyCost;
    @Transient
    private Boolean ndcMatch;
    @Transient
    private Integer srno;

    public RedemptionIngredient() {
    }

    public RedemptionIngredient(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String stength) {
        this.strength = stength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPlanCost() {
        return planCost;
    }

    public void setPlanCost(Double planCost) {
        this.planCost = planCost;
    }

    public Double getPharmacyCost() {
        return pharmacyCost;
    }

    public void setPharmacyCost(Double pharmacyCost) {
        this.pharmacyCost = pharmacyCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedemptionIngredient)) {
            return false;
        }
        RedemptionIngredient other = (RedemptionIngredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ssa.cms.model.GenRxRedemptionIngredient[ id=" + id + " ]";
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String redemptionNumber) {
        this.transactionNumber = redemptionNumber;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getProcessedSig() {
        return processedSig;
    }

    public void setProcessedSig(String processedSig) {
        this.processedSig = processedSig;
    }

    public Boolean getCovered() {
        return covered;
    }

    public void setCovered(Boolean covered) {
        this.covered = covered;
    }

    public Boolean getFormulary() {
        return formulary;
    }

    public void setFormulary(Boolean formulary) {
        this.formulary = formulary;
    }

    public Boolean getNdcMatch() {
        return ndcMatch;
    }

    public void setNdcMatch(Boolean ndcMatch) {
        this.ndcMatch = ndcMatch;
    }

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

}
