/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.enumeration;

/**
 *
 * @author SSASOFTDHA
 */
public enum NotificationStatusEnum {

    SUCCESS(1),
    XML_STRUCTURE_INVALID(2),
    NO_XML(3),
    INVALID_PHONE(4),
    REQUEST_FIELD(5),
    KEY_MISSING(6),
    DUPLICATE(7),
    UN_KNOWN(8);
    private int value;

    NotificationStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
