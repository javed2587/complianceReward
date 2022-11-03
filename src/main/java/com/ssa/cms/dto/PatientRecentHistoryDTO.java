/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

import com.ssa.cms.model.BloodPressure;
import com.ssa.cms.model.PatientBodyMassResult;
import com.ssa.cms.model.PatientGlucoseResults;
import com.ssa.cms.model.PatientHeartPulseResult;
import com.ssa.cms.model.PatientProfile;
import java.util.List;

/**
 *
 * @author Jandal
 */
public class PatientRecentHistoryDTO {
   private int id;
   private List<PatientBodyMassResultDTO> patientBodyMassResult;
   private List<BloodPressureDTO> bloodPressure; 
   private List<PatientGlucoseDTO> patientGlucose;
   private List<PatientHeartPulseDTO>patientHeartPulse;
   private PatientProfile patientprofile;
   private String staringDate;
   private String endingDate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PatientBodyMassResultDTO> getPatientBodyMassResult() {
        return patientBodyMassResult;
    }

    public void setPatientBodyMassResult(List<PatientBodyMassResultDTO> patientBodyMassResult) {
        this.patientBodyMassResult = patientBodyMassResult;
    }

    public List<BloodPressureDTO> getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(List<BloodPressureDTO> bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public List<PatientGlucoseDTO> getPatientGlucose() {
        return patientGlucose;
    }

    public void setPatientGlucose(List<PatientGlucoseDTO> patientGlucose) {
        this.patientGlucose = patientGlucose;
    }

    public List<PatientHeartPulseDTO> getPatientHeartPulse() {
        return patientHeartPulse;
    }

    public void setPatientHeartPulse(List<PatientHeartPulseDTO> patientHeartPulse) {
        this.patientHeartPulse = patientHeartPulse;
    }

    public String getStaringDate() {
        return staringDate;
    }

    public void setStaringDate(String staringDate) {
        this.staringDate = staringDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public PatientProfile getPatientprofile() {
        return patientprofile;
    }

    public void setPatientprofile(PatientProfile patientprofile) {
        this.patientprofile = patientprofile;
    }
   
}
