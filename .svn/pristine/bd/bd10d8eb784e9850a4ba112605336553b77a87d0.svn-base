/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="physician_language")
public class DocLanguage implements Serializable {
    private Long id;
    private String languageExp;
    private Physician physician;
    private Date createdOn;
    private Date updatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="language_grip")
    public String getLanguageExp() {
        return languageExp;
    }

    public void setLanguageExp(String languageExp) {
        this.languageExp = languageExp;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id", nullable = false, insertable = true, updatable = true)
    public Physician getDoctor() {
        return physician;
    }

    public void setDoctor(Physician physician) {
        this.physician = physician;
    }

    @Column(name="created_on")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="updated_on")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
}
