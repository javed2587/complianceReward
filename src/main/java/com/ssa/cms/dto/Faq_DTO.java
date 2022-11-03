/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import java.util.Date;

/**
 *
 * @author Jandal
 */
public class Faq_DTO {
   private Integer id;
   private String Question;
   private String Answer;
   private Date CreatedOn;
   private Date UpdateOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public Date getUpdateOn() {
        return UpdateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.UpdateOn = UpdateOn;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
   
}
