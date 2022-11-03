package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "OrdersCarriers")
public class OrderCarriers implements Serializable {

    private Integer id;
    private String name;
    private List<Order> orderList;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
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

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderCarriers")
//    @OrderBy(clause = "id asc")
    @Transient
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
