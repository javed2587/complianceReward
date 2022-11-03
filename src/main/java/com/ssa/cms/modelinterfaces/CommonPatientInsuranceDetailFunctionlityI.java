/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.modelinterfaces;

/**
 *
 * @author jiqbal
 */
public interface CommonPatientInsuranceDetailFunctionlityI {
    
     public String getProviderPhone(); 
     public void setProviderPhone(String providerPhone);
     public String getInsuranceFrontCardPath();
     public void setInsuranceFrontCardPath(String insuranceFrontCardPath);
     public String getInsuranceBackCardPath();
     public void setInsuranceBackCardPath(String insuranceBackCardPath);
     public String getCopayFrontCardPath();
     public void setCopayFrontCardPath(String copayFrontCardPath);
     public String getCopayBackCardPath();
     public void setCopayBackCardPath(String copayBackCardPath);
    
}
