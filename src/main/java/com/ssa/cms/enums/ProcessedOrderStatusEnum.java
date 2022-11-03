/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.enums;

/**
 *
 * @author adeel.usmani
 */
public enum ProcessedOrderStatusEnum 
{
    DELIVERY(5),Shipped(6),Filled(8),Pickup(15);
    
    private int value;
    
    private ProcessedOrderStatusEnum(int value) 
    {
        this.value = value; 
    }

    public int getValue()
    {
        return this.value;
    }

}
