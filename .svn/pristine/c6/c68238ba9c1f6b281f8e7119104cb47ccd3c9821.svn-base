/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "reporter_profile_detail")
public class ReporterProfile implements Serializable {
    private int id;
    private int physicianId;
    private String physicianImage;
    private String majorSpecializationOne;
    private String majorSpecializationTwo;
    private String subSpecializationOne;
    private String subSpecializationTwo;
    private String subSpecializationThree;
    private String languageOne;
    private String languageTwo;
    private String languageThree;

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "physcian_practice_id")
    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }
     @Column(name = "profile_image")
    public String getPhysicianImage() {
        return physicianImage;
    }

    public void setPhysicianImage(String physicianImage) {
        this.physicianImage = physicianImage;
    }
     @Column(name = "major_ss_1")
    public String getMajorSpecializationOne() {
        return majorSpecializationOne;
    }

    public void setMajorSpecializationOne(String majorSpecializationOne) {
        this.majorSpecializationOne = majorSpecializationOne;
    }
 @Column(name = "major_ss_2")
    public String getMajorSpecializationTwo() {
        return majorSpecializationTwo;
    }

    public void setMajorSpecializationTwo(String majorSpecializationTwo) {
        this.majorSpecializationTwo = majorSpecializationTwo;
    }
 @Column(name = "sub_ss_1")
    public String getSubSpecializationOne() {
        return subSpecializationOne;
    }

    public void setSubSpecializationOne(String subSpecializationOne) {
        this.subSpecializationOne = subSpecializationOne;
    }
 @Column(name = "sub_ss_2")
    public String getSubSpecializationTwo() {
        return subSpecializationTwo;
    }

    public void setSubSpecializationTwo(String subSpecializationTwo) {
        this.subSpecializationTwo = subSpecializationTwo;
    }
 @Column(name = "sub_ss_3")
    public String getSubSpecializationThree() {
        return subSpecializationThree;
    }

    public void setSubSpecializationThree(String subSpecializationThree) {
        this.subSpecializationThree = subSpecializationThree;
    }
 @Column(name = "lang_1")
    public String getLanguageOne() {
        return languageOne;
    }

    public void setLanguageOne(String languageOne) {
        this.languageOne = languageOne;
    }
 @Column(name = "lang_2")
    public String getLanguageTwo() {
        return languageTwo;
    }

    public void setLanguageTwo(String languageTwo) {
        this.languageTwo = languageTwo;
    }
 @Column(name = "lang_3")
    public String getLanguageThree() {
        return languageThree;
    }

    public void setLanguageThree(String languageThree) {
        this.languageThree = languageThree;
    }
}
