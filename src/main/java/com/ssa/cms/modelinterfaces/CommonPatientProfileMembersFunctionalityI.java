/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modelinterfaces;

import java.util.Date;

/**
 *
 * @author mzubair
 */
public interface CommonPatientProfileMembersFunctionalityI {
    public String getFirstName();
    public void setFirstName(String s);
    public String getLastName();
    public void setLastName(String s);
    public Date getDob();
    public void setDob(Date dob);
    public String getFrontPOAImage();
    public void setFrontPOAImage(String frontPOAImage);
    public String getBackPOAImage();
    public void setBackPOAImage(String backPOAImage);
    public String getBirthDate();
    public void setBirthDate(String birthDate);
    public String getEmail();
    public void setEmail(String s);
}
