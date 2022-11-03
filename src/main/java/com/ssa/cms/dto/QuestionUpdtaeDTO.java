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
public class QuestionUpdtaeDTO {
    private String orderId;
    private int msgOrQuestionId;
    private int isRead;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getMsgOrQuestionId() {
        return msgOrQuestionId;
    }

    public void setMsgOrQuestionId(int msgOrQuestionId) {
        this.msgOrQuestionId = msgOrQuestionId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
