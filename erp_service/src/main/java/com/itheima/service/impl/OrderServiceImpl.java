package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import com.itheima.domain.PageBean;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:26
 */
@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public PageBean<Order> findOrderByCustomerPage(Integer customer_id, Integer currPage) {
        Integer pageSize=3;
        Customer c=customerDao.findById(customer_id);
        Integer totalCount=orderDao.findCOunt(c);
        PageBean<Order> pageBean=new PageBean<Order>(currPage, pageSize,totalCount);
        List<Order> list=orderDao.findOrderByCustomerPage(c,pageSize,pageBean.getBeginIndex());
        for (Order order : list) {
            System.out.println(order);
        }
        pageBean.setList(list);

        return pageBean;
    }
}
