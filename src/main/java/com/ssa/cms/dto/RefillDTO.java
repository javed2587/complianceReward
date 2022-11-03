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
public class RefillDTO {
    private String orderId;
    private String refillOrderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRefillOrderId() {
        return refillOrderId;
    }

    public void setRefillOrderId(String refillOrderId) {
        this.refillOrderId = refillOrderId;
    }
}
