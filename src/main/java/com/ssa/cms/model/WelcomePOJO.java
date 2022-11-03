/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mohsin
 */
public class WelcomePOJO {
    
    private String campaignName;
    private String brandAndStrengths;
    private BigInteger totalRedemption;
    private BigInteger totalOptIn;
    private BigInteger totalActivation;
    private Map<Object, Number> activationGraphPoints = new HashMap<Object, Number>();
    private Map<Object, Number> redemptionGraphPoints = new HashMap<Object, Number>();
    private Map<Object, Number> optInGraphPoints = new HashMap<Object, Number>();
    private String xml;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getBrandAndStrengths() {
        return brandAndStrengths;
    }

    public void setBrandAndStrengths(String brandAndStrengths) {
        this.brandAndStrengths = brandAndStrengths;
    }

    public BigInteger getTotalRedemption() {
        return totalRedemption;
    }

    public void setTotalRedemption(BigInteger totalRedemption) {
        this.totalRedemption = totalRedemption;
    }

    public BigInteger getTotalOptIn() {
        return totalOptIn;
    }

    public void setTotalOptIn(BigInteger totalOptIn) {
        this.totalOptIn = totalOptIn;
    }

    public BigInteger getTotalActivation() {
        return totalActivation;
    }

    public void setTotalActivation(BigInteger totalActivation) {
        this.totalActivation = totalActivation;
    }

    public Map<Object, Number> getActivationGraphPoints() {
        return activationGraphPoints;
    }

    public void setActivationGraphPoints(Map<Object, Number> activationGraphPoints) {
        this.activationGraphPoints = activationGraphPoints;
    }

    public Map<Object, Number> getRedemptionGraphPoints() {
        return redemptionGraphPoints;
    }

    public void setRedemptionGraphPoints(Map<Object, Number> redemptionGraphPoints) {
        this.redemptionGraphPoints = redemptionGraphPoints;
    }

    public Map<Object, Number> getOptInGraphPoints() {
        return optInGraphPoints;
    }

    public void setOptInGraphPoints(Map<Object, Number> optInGraphPoints) {
        this.optInGraphPoints = optInGraphPoints;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    
}
