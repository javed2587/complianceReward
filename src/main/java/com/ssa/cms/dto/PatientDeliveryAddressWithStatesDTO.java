/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import com.ssa.cms.model.State;
import java.util.List;

/**
 *
 * @author Haider ali
 */
public class PatientDeliveryAddressWithStatesDTO extends PatientDeliveryAddressDTO{
    
    private List<State> states;

    public PatientDeliveryAddressWithStatesDTO() {
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
    
}
