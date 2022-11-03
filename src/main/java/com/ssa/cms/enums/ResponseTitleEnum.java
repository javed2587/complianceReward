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
public enum ResponseTitleEnum {
    Order_Cancelled("Order Cancelled"),Under_Review("Under Review"),Assistanc_DC("Assistanc DC"),Medical_Advisory("Medical Advisory"),Ph_Direct_Messgae("Ph Direct Messgae"),
    DC_PT_Discontinue("DC PT Discontinue"),Filled("Filled"),Rx_Order("Rx Order"),Rx_Refill_Initiated("Rx Refill Initiated"),RX_REPORTER("Rx Reporter Overdue"),
    REQUEST_CALL_TO_HCP_RX_REFILL("Request Call To Hcp Rx Refill"),FIRST_MBR_REMINDER("First MBR Reminder"),SECOND_MBR_REMINDER("Second MBR Reminder"),THIRD_MBR_REMINDER("Third MBR Reminder"),
    FIRST_SURVEY_REMINDER("First Survey Reminder"),SECOND_SURVEY_REMINDER("Second Survey Reminder"),RX_REPORTER_ADVISORY("Rx Reporter Advisory"),HCP_DIRECT_MESSAGE("HCP Direct Message"),HCP_GENRAL_MESSAGE("HCP General Message");
    
    /**
     *
     */
    private String value;

    ResponseTitleEnum(final String value) {
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
