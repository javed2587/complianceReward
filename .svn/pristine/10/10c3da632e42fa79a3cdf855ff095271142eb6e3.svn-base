/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.TimeOfDay;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Javed Iqbal
 */
@Entity
@Table(name = "patient_dependents")
public class PatientDependent implements Serializable {
    private Integer id;
    private PatientProfile profile;
    private AppointmentRequest request; 
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String dependentAllergy;
    private String relationShip;
    private Date createdAt;
    private Date updateAt;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "patient_id")
    public PatientProfile getProfile() {
        return profile;
    }

    public void setProfile(PatientProfile profile) {
        this.profile = profile;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    public AppointmentRequest getRequest() {
        return request;
    }

    public void setRequest(AppointmentRequest request) {
        this.request = request;
    }
@Column(name = "dependent_fname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
@Column(name = "dependent_lname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
@Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
@Column(name = "dependent_dob")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
@Column(name = "dependent_allergy")
    public String getDependentAllergy() {
        return dependentAllergy;
    }

    public void setDependentAllergy(String dependentAllergy) {
        this.dependentAllergy = dependentAllergy;
    }
@Column(name = "relationship")
    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }
    @Temporal(TemporalType.TIMESTAMP)
@Column(name = "crated_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Temporal(TemporalType.TIMESTAMP)
@Column(name = "updated_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
