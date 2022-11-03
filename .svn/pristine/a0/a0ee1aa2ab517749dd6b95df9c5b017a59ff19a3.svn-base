package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mzubair
 */
@Entity
@Table(name = "PatientRewardLevel")
public class PatientRewardLevel extends AuditFields implements Serializable {

    private Integer id;
    private String type;
    private Integer fromLevel;
    private Integer toLevel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "FromLevel")
    public Integer getFromLevel() {
        return fromLevel;
    }

    public void setFromLevel(Integer fromLevel) {
        this.fromLevel = fromLevel;
    }

    @Column(name = "ToLevel")
    public Integer getToLevel() {
        return toLevel;
    }

    public void setToLevel(Integer toLevel) {
        this.toLevel = toLevel;
    }

}
