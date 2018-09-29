package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:27
 */
@Component
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> finfAllCustomer() {
        return customerDao.finfAllCustomer();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void delCustomer(Customer customer) {
        customer=customerDao.findById(customer.getId());
        customerDao.delCustomer(customer);
    }
}
