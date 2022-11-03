/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

/**
 *
 * @author Jandal
 */
public class DeleteRecord {
   private String value;
   private String Type;
   private String RecordId;
   private String jspName;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String RecordId) {
        this.RecordId = RecordId;
    }

    public String getJspName() {
        return jspName;
    }

    public void setJspName(String jspName) {
        this.jspName = jspName;
    }
   
}
