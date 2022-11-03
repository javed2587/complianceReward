/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "practices")
public class Practices implements Serializable {

    private Integer id;
    private String practicename;
    private String practicetype;
    private String practiceaddress;
    private String practicewebsite;
    private String practicecity;
    private String practicestate;
    private Integer practicezip;
    private String practicephonenumber;
    private String practicelicenseissuer;
    private String practicelicensenumber;
    private Date createdat;
    private Date updatedat;
    private String practiceCode;
    private Integer addfromPharmacy;
    private String practiceLogo;


  public Practices(){}
  
    public Practices(Integer practiceId) {
        this.id = practiceId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "practice_name")
    public String getPracticename() {
        return practicename;
    }

    public void setPracticename(String practicename) {
        this.practicename = practicename;
    }

    @Column(name = "practice_type")
    public String getPracticetype() {
        return practicetype;
    }

    public void setPracticetype(String practicetype) {
        this.practicetype = practicetype;
    }

    @Column(name = "practice_address")
    public String getPracticeaddress() {
        return practiceaddress;
    }

    public void setPracticeaddress(String practiceaddress) {
        this.practiceaddress = practiceaddress;
    }

    @Column(name = "practice_website")
    public String getPracticewebsite() {
        return practicewebsite;
    }

    public void setPracticewebsite(String practicewebsite) {
        this.practicewebsite = practicewebsite;
    }

    @Column(name = "practice_city")
    public String getPracticecity() {
        return practicecity;
    }

    public void setPracticecity(String practicecity) {
        this.practicecity = practicecity;
    }

    @Column(name = "practice_state")
    public String getPracticestate() {
        return practicestate;
    }

    public void setPracticestate(String practicestate) {
        this.practicestate = practicestate;
    }

    @Column(name = "practice_zip")
    public Integer getPracticezip() {
        return practicezip;
    }

    public void setPracticezip(Integer practicezip) {
        this.practicezip = practicezip;
    }

    @Column(name = "practice_phone_number")
    public String getPracticephonenumber() {
        return practicephonenumber;
    }

    public void setPracticephonenumber(String practicephonenumber) {
        this.practicephonenumber = practicephonenumber;
    }

    @Column(name = "practice_license_issuer")
    public String getPracticelicenseissuer() {
        return practicelicenseissuer;
    }

    public void setPracticelicenseissuer(String practicelicenseissuer) {
        this.practicelicenseissuer = practicelicenseissuer;
    }

    @Column(name = "practice_license_number")
    public String getPracticelicensenumber() {
        return practicelicensenumber;
    }

    public void setPracticelicensenumber(String practicelicensenumber) {
        this.practicelicensenumber = practicelicensenumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }
        @Column(name = "practice_code")
    public String getPracticeCode() {
        return practiceCode;
    }

    public void setPracticeCode(String practiceCode) {
        this.practiceCode = practiceCode;
    }
     @Column(name = "added_from_pharmacy")
    public Integer getAddfromPharmacy() {
        return addfromPharmacy;
    }

    public void setAddfromPharmacy(Integer addfromPharmacy) {
        this.addfromPharmacy = addfromPharmacy;
    }

    @Column(name = "practice_logo")
    public String getPracticeLogo() {
        return practiceLogo;
    }

    public void setPracticeLogo(String practiceLogo) {
        this.practiceLogo = practiceLogo;
    }

}
