/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.response;

import java.util.List;

/**
 *
 * @author ahsan.sharif
 */
public class BMIMonthFilterWsMainResponse {
    
    List<BMIMonthFilterWsResponse> searchResult;

    public List<BMIMonthFilterWsResponse> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<BMIMonthFilterWsResponse> searchResult) {
        this.searchResult = searchResult;
    }
    
}
