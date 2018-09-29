package com.itheima.service;

import com.itheima.domain.Customer;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:26
 */
public interface CustomerService {
    List<Customer> finfAllCustomer();

    void addCustomer(Customer customer);

    void delCustomer(Customer customer);
}
