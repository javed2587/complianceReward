/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.ArrayList;

/**
 *
 * @author Javed
 */
public class BMIWeeklyDetailRapperDTO {
    
    private String day;

    
   ArrayList<BMIWeeklyDetailDTO> records ;

    public ArrayList<BMIWeeklyDetailDTO> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<BMIWeeklyDetailDTO> records) {
        this.records = records;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
