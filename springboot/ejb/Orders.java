package com.ebook.springboot.ejb;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import com.ebook.springboot.pojo.Cart;

/**
 * 订单的实体类
 *
 * @author lz
 * @date 2019-5-6
 */
@Entity
@Table(name="order1")
public class Orders {
    private int orderId;
    private User user;
    private double cost;
    private String payState;
    private Timestamp orderTime;
    private Set<OrderItem> orderItems;

    public Orders(Cart cart){
        payState="paid";
        cost=cart.getCost();
    }

    public Orders(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 2)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "pay_state", nullable = true, length = 10)
    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    @Basic
    @Column(name = "order_time",insertable = false , updatable = false)
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime){
        this.orderTime=orderTime;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user=user;
    }

    @OneToMany(mappedBy = "orderId")
    public Set<OrderItem> getOrderItems(){
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems){
        this.orderItems=orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return orderId == orders.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
