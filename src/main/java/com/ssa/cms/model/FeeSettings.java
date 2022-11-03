/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Tahir Saeed
 */
//@Entity
//@Table(name = "FeeSettings")
public class FeeSettings extends AuditFields implements java.io.Serializable {

    private Integer id;
    private String type;
    private BigDecimal fee;
    private List<FeeSettings> feeSettingslist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public List<FeeSettings> getFeeSettingslist() {
        return feeSettingslist;
    }

    public void setFeeSettingslist(List<FeeSettings> feeSettingslist) {
        this.feeSettingslist = feeSettingslist;
    }

    @Column(name = "Fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
