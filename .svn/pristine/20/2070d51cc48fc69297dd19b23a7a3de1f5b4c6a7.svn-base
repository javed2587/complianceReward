/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author arsalan.ahmad
 */
//@Entity
//@Table(name = "DrugDetailImportLogs")
public class DrugDetailImportLogs {
    
    private Integer id;
    private String tagText;
    private Integer rowNumber;
    private Date createdAt = new Date();
    private boolean successed = false;
    
    public DrugDetailImportLogs(){}
    
    public DrugDetailImportLogs(String tagText, Integer rowNumber){
        this.tagText = tagText;
        this.rowNumber = rowNumber;
    }
    

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "TagText")
    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    @Column(name = "RowNumber")
    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "Successed")
    public boolean getSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }
    
    
    
}
