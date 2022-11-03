package com.ssa.cms.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "IntervalsType", uniqueConstraints = @UniqueConstraint(columnNames = "IntervalsTypeTitle")
)
public class IntervalsType extends AuditFields implements java.io.Serializable {

    private Integer intervalsTypeId;
    private String intervalsTypeTitle;
    private Integer unitInSecond;
    private List<Intervals> intervals;

    public IntervalsType() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IntervalsTypeId", unique = true, nullable = false)
    public Integer getIntervalsTypeId() {
        return this.intervalsTypeId;
    }

    public void setIntervalsTypeId(Integer intervalsTypeId) {
        this.intervalsTypeId = intervalsTypeId;
    }

    @Column(name = "IntervalsTypeTitle", unique = true, nullable = false, length = 20)
    public String getIntervalsTypeTitle() {
        return this.intervalsTypeTitle;
    }

    public void setIntervalsTypeTitle(String intervalsTypeTitle) {
        this.intervalsTypeTitle = intervalsTypeTitle;
    }

    @Column(name = "UnitInSecond", nullable = false)
    public Integer getUnitInSecond() {
        return this.unitInSecond;
    }

    public void setUnitInSecond(Integer unitInSecond) {
        this.unitInSecond = unitInSecond;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intervalsType")
    @OrderBy(clause = "IntervalValue asc")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Intervals> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Intervals> intervals) {
        this.intervals = intervals;
    }

}
