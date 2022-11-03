/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.response;

import java.util.ArrayList;

/**
 *
 * @author ahsan.sharif
 */
public class BMIYearFilterWsResponse {
    
    private String Year;
    private ArrayList<BMIYearFilterWsDetails> records = new ArrayList<BMIYearFilterWsDetails>();

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public ArrayList<BMIYearFilterWsDetails> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<BMIYearFilterWsDetails> records) {
        this.records = records;
    }
}
