/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

/**
 *
 * @author Javed Iqbal
 */
public class GenralDTO {
    private Long serverDate;
    private String presciberName;
    private Object orderDetial;
    private Object genralQuestionDetial;
    private String practiceLogo;
    private String physicianName;

 


    public Long getServerDate() {
        return serverDate;
    }

    public void setServerDate(Long serverDate) {
        this.serverDate = serverDate;
    }

    public String getPresciberName() {
        return presciberName;
    }

    public void setPresciberName(String presciberName) {
        this.presciberName = presciberName;
    }
      public Object getOrderDetial() {
        return orderDetial;
    }

    public void setOrderDetial(Object orderDetial) {
        this.orderDetial = orderDetial;
    }

    public Object getGenralQuestionDetial() {
        return genralQuestionDetial;
    }

    public void setGenralQuestionDetial(Object genralQuestionDetial) {
        this.genralQuestionDetial = genralQuestionDetial;
    }
    
    public String getPracticeLogo() {
        return practiceLogo;
    }

    public void setPracticeLogo(String practiceLogo) {
        this.practiceLogo = practiceLogo;
    }

     public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }
}
