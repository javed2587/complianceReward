/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.bean;

import java.io.Serializable;

/**
 * this class is used to prepare conditions to query database and pull data according to the needs.
 * @author adeel.usmani
 */
public class BusinessObject implements Serializable
{
    private String name;
    private Object value;
    private int comparisonOperator;

    /**
     * This is constructor of BusinessObject class
     * @param name
     * @param value
     * @param comparisonOperator 
     */
    public BusinessObject(String name, Object value, int comparisonOperator) {
        this.name = name;
        this.value = value;
        this.comparisonOperator = comparisonOperator;
    }

    /**
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    /**
     * 
     * @param value 
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 
     * @return 
     */
    public int getComparisonOperator() {
        return comparisonOperator;
    }

    /**
     * 
     * @param comparisonOperator 
     */
    public void setComparisonOperator(int comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }
    
}
