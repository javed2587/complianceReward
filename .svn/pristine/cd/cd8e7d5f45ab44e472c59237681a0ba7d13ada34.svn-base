package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Zubair
 */
//@Entity
//@Table(name = "Drug_Units")
public class DrugUnits implements Serializable {

    private Integer id;
    private String name;
    private List<Drug> drugList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@OneToMany(mappedBy = "drugUnits", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Transient
    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

}
