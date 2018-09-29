package com.itheima.service;

import com.itheima.domain.Order;
import com.itheima.domain.PageBean;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:26
 */
public interface OrderService {

    PageBean<Order> findOrderByCustomerPage(Integer customer_id, Integer currPage);
}
