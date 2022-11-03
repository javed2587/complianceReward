/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dto;

/**
 *
 * @author Javed Iqbal
 */
public class PatientAppointmentDTO {
   private Integer id ;
   private Integer appointmentNumber;
    private int profileId;
    private int requestId;
    private int physicianId;
    private Long userId;
    private String reasonToVist;
    private String appointmentDateTime;
    private String physicianName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(Integer appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReasonToVist() {
        return reasonToVist;
    }

    public void setReasonToVist(String reasonToVist) {
        this.reasonToVist = reasonToVist;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
        public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }
}
