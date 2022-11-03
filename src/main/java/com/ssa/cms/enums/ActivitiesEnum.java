/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.enums;

/**
 *
 * @author Zubair
 */
public enum ActivitiesEnum {

    PAYMENT_DONE("Payment Done"),
    ADD_BODY_MASS_ACTIVITY("Body Mass Added"), EDIT_BODY_MASS_ACTIVITY("Body Mass Edited"),
    ADD_HEART_PULS_ACTIVITY("Heart Pulse Added"), EDIT_HEART_PULS_ACTIVITY("Heart Pulse Edited"),
    ADD_GLUCOSE_ACTIVITY("Glucose Added"), EDIT_GLUCOSE_ACTIVITY("Glucose Edited"),
    ADD_BLOOD_PRESSURE_ACTIVITY("Blood Pressure Added"), EDIT_BLOOD_PRESSURE_ACTIVITY("Blood Pressure EDITED"),
    REFILL_REQUEST("Refill Activity"),ADDRESS_CHANGE("Address Changed"),
    ADD_INSURANCE_CARD("Insurance Card Added"),EDIT_INSURANCE_CARD("Insurance Card Edited"),DELETE_INSURANCE_CARD("Insurance Card Deleted"),PROFILE_EDIT("Profile Edited"),
    Add_ALLERGY("Allergy Added"),EDIT_ALLERGY("Allergy Edited"),DELETE_ALLERGY("Allergy Deleted"),
    QUESTION_POST("Question Posted"),CHANGE_PASSWORD("Change Password"),SURVEY_POST("Questionaire Submitted")
    ;

    /**
     *
     */
    private String value;

    ActivitiesEnum(final String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(final String value) {
        this.value = value;
    }
}
