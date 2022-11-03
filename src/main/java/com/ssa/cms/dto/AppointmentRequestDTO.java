/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import com.ssa.cms.model.PatientProfile;

/**
 *
 * @author Javed Iqbal
 */
public class AppointmentRequestDTO {
    private int appointmentId;
    private PatientProfile profile;
    private String insuranceUsageFlage;
    private String reasonForAppointment;
    private String statusOfAppointment;



    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public PatientProfile getProfile() {
        return profile;
    }

    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }

    public String getInsuranceUsageFlage() {
        return insuranceUsageFlage;
    }

    public void setInsuranceUsageFlage(String insuranceUsageFlage) {
        this.insuranceUsageFlage = insuranceUsageFlage;
    }

    public String getReasonForAppointment() {
        return reasonForAppointment;
    }

    public void setReasonForAppointment(String reasonForAppointment) {
        this.reasonForAppointment = reasonForAppointment;
    }

    public String getStatusOfAppointment() {
        return statusOfAppointment;
    }

    public void setStatusOfAppointment(String statusOfAppointment) {
        this.statusOfAppointment = statusOfAppointment;
    }
}
