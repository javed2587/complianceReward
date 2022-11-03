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
public class BMIYearFilterWsMainResponse {
    
    ArrayList<BMIYearFilterWsResponse> searchResult = new ArrayList<BMIYearFilterWsResponse>();

    public ArrayList<BMIYearFilterWsResponse> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ArrayList<BMIYearFilterWsResponse> searchResult) {
        this.searchResult = searchResult;
    }
    
}
