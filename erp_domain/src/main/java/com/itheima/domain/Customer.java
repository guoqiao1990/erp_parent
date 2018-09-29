package com.itheima.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:25
 */
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String cusName;
    private String cusPhone;
    private String cusImgsrc;

    @OneToMany(targetEntity = Order.class,mappedBy = "customer")
    @Cascade(CascadeType.DELETE)
    private Set<Order> orders=new HashSet<Order>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getCusImgsrc() {
        return cusImgsrc;
    }

    public void setCusImgsrc(String cusImgsrc) {
        this.cusImgsrc = cusImgsrc;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", cusName='" + cusName + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", orders=" + orders +
                '}';
    }
}
