/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name = "FAQestions")
public class Faq implements Serializable {
   private Integer id;
   private String Question;
   private String Answer;
   private Date CreatedOn;
   private Date UpdateOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "Q_name")
    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }
@Column(name = "CreatedOn")
    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedOn")
    public Date getUpdateOn() {
        return UpdateOn;
    }

    public void setUpdateOn(Date UpdateOn) {
        this.UpdateOn = UpdateOn;
    }
    @Column(name = "Answer")
    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
   
}
