package com.ssa.cms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "OrderStatus")
public class OrderStatus implements Serializable {

    private Integer id;
    private String name;
    private List<Order> orderList;
    private List<OrderHistory> orderHistoryList;
    private int sortBy;


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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderStatus")
    @OrderBy(clause = "id asc")
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    //TODO @JsonBackReference
    //TODO @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderStatus")
    //TODO @OrderBy(clause = "id asc")
    @Transient
    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
    @Column(name = "SortBy")
    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }
}
