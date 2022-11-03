package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author mzubair
 */
//@Entity
//@Table(name = "DeliveryDistances")
public class DeliveryDistances extends AuditFields implements Serializable {

    private Integer id;
    private Integer distanceFrom;
    private Integer distanceTo;
    private List<DeliveryDistances> deliveryDsitanceses;
    private List<DeliveryDistanceFee> deliveryDistanceFeeList;

    public DeliveryDistances() {
    }

    public DeliveryDistances(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DistanceFrom")
    public Integer getDistanceFrom() {
        return distanceFrom;
    }

    public void setDistanceFrom(Integer distanceFrom) {
        this.distanceFrom = distanceFrom;
    }

    @Column(name = "DistanceTo")
    public Integer getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(Integer distanceTo) {
        this.distanceTo = distanceTo;
    }

    @Transient
    public List<DeliveryDistances> getDeliveryDsitanceses() {
        return deliveryDsitanceses;
    }

    public void setDeliveryDsitanceses(List<DeliveryDistances> deliveryDsitanceses) {
        this.deliveryDsitanceses = deliveryDsitanceses;
    }

    //TODO @OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryDistances")
    @Transient
    public List<DeliveryDistanceFee> getDeliveryDistanceFeeList() {
        return deliveryDistanceFeeList;
    }

    public void setDeliveryDistanceFeeList(List<DeliveryDistanceFee> deliveryDistanceFeeList) {
        this.deliveryDistanceFeeList = deliveryDistanceFeeList;
    }

}
