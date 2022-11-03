/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ahsan.sharif
 */

@Entity
@Table(name = "Question")
public class Question {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer questionId;
    
    @Column(name = "Narrative")
    private String narrative;
    
    @Column(name = "Effective_Date")
    private String effectiveDate;
    
    @Column(name = "End_Date")
    private String endDate;
    
    @Column(name = "Org_Unit_ID", nullable = false, unique = true)
    private Integer orgUnitId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Integer orgUnitId) {
        this.orgUnitId = orgUnitId;
    }    
}
