/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.List;
import java.util.Map;

/**
 *
 * @author adeel.usmani
 */
public class ViewDTO 
{
    private String filter;
    private Map<String, Integer> mapStatusCount;

   
  
   public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Map<String, Integer> getMapStatusCount() {
        return mapStatusCount;
    }

    public void setMapStatusCount(Map<String, Integer> mapStatusCount) {
        this.mapStatusCount = mapStatusCount;
    }
       
    
}
