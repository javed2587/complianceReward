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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author adeel.usmani
 */
@Entity
@Table(name = "StateZipCode")
public class StateZipCode implements Serializable 
{
    private Integer id;
    private String zip;
    private String city;
    private String county;
    private State state;
    
    public StateZipCode() {
    }

    public StateZipCode(Integer id) 
    {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name = "Zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "County")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StateId")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    
    
    
}
