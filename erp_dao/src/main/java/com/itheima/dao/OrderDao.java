package com.itheima.dao;

import com.itheima.domain.Customer;
import com.itheima.domain.Order;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:27
 */
public interface OrderDao {
    List<Order> findByCustomer(Customer customer);

    Integer findCOunt(Customer c);

    List<Order> findOrderByCustomerPage(Customer c, Integer pageSize, Integer beginIndex);
}
