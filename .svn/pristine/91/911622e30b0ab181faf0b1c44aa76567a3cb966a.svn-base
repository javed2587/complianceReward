package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "State")
public class State implements Serializable {

    private Integer id;
    private String name;
    private String abbr;
    private List<Pharmacy> pharmacyList;
    private List<PharmacyLookup> pharmacylookupList;
    private List<PatientDeliveryAddress> patientDeliveryAddresses;

    public State() {
    }

    public State(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "StateName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Abbr")
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
    @OrderBy(clause = "id asc")
    public List<Pharmacy> getPharmacyList() {
        return pharmacyList;
    }

    public void setPharmacyList(List<Pharmacy> pharmacyList) {
        this.pharmacyList = pharmacyList;
    }

    //TODO @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "state")
    //TODO @OrderBy(clause = "id asc")
    @Transient
    public List<PharmacyLookup> getPharmacylookupList() {
        return pharmacylookupList;
    }

    public void setPharmacylookupList(List<PharmacyLookup> pharmacylookupList) {
        this.pharmacylookupList = pharmacylookupList;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
    @OrderBy(clause = "id asc")
    public List<PatientDeliveryAddress> getPatientDeliveryAddresses() {
        return patientDeliveryAddresses;
    }

    public void setPatientDeliveryAddresses(List<PatientDeliveryAddress> patientDeliveryAddresses) {
        this.patientDeliveryAddresses = patientDeliveryAddresses;
    }

}
