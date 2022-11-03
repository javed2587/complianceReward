/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author adeel.usmani
 */
@Entity
@Table(name = "StateRewardTaxes")
public class StateRewardTaxes implements Serializable
{
    private Integer Id;
    private State state;
    private Float taxPercentage;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StateID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "TaxPercentage")
    public Float getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
    
    
}
