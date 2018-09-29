package com.itheima.dao;

import com.itheima.domain.Customer;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:28
 */
public interface CustomerDao {
    List<Customer> finfAllCustomer();

    void addCustomer(Customer customer);

    void delCustomer(Customer customer);

    Customer findById(Integer id);
}
