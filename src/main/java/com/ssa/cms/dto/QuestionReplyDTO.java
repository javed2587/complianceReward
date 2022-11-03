/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.ArrayList;

/**
 *
 * @author Javed Iqbal
 */
public class QuestionReplyDTO {

    private ArrayList<QuestionAnswerReplyDetailDTO> questionReply;


    public ArrayList<QuestionAnswerReplyDetailDTO> getQuestionReply() {
        return questionReply;
    }
    public void setQuestionReply(ArrayList<QuestionAnswerReplyDetailDTO> questionReply) {
        this.questionReply = questionReply;
    }
}
