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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jandal
 */
@Entity
@Table(name="survey_themes")
public class SurveyTheme implements Serializable {
    
    private Long id;
    private String theme_Title;
    private String theme_Logo;
    private String header_Text;
    private String footer_Text;
    private String header_Image;
    private String footer_Image;
    private Date created_At;
    private Date updated_At;
    private Survey2 survey;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="theme_title")
    public String getTheme_Title() {
        return theme_Title;
    }

    public void setTheme_Title(String theme_Title) {
        this.theme_Title = theme_Title;
    }

    @Column(name="theme_logo")
    public String getTheme_Logo() {
        return theme_Logo;
    }

    public void setTheme_Logo(String theme_Logo) {
        this.theme_Logo = theme_Logo;
    }

    @Column(name="header_text")
    public String getHeader_Text() {
        return header_Text;
    }

    public void setHeader_Text(String header_Text) {
        this.header_Text = header_Text;
    }

    @Column(name="footer_text")
    public String getFooter_Text() {
        return footer_Text;
    }

    public void setFooter_Text(String footer_Text) {
        this.footer_Text = footer_Text;
    }

    @Column(name="header_image")
    public String getHeader_Image() {
        return header_Image;
    }

    public void setHeader_Image(String header_Image) {
        this.header_Image = header_Image;
    }

    @Column(name="footer_image")
    public String getFooter_Image() {
        return footer_Image;
    }

    public void setFooter_Image(String footer_Image) {
        this.footer_Image = footer_Image;
    }

    @Column(name="created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    @Column(name="updated_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false, insertable = true, updatable = true)
    public Survey2 getSurvey() {
        return survey;
    }

    public void setSurvey(Survey2 survey) {
        this.survey = survey;
    }
    
    
}
