package com.itheima.dao.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:28
 */
@Component
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    /**注入Session,获得HibernateTemplate*/
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory factory){
        this.setSessionFactory(factory);
    }

    @Override
    public List<Customer> finfAllCustomer() {
        return (List<Customer>) this.getHibernateTemplate().find("from Customer");

    }

    @Override
    public void addCustomer(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    @Override
    public void delCustomer(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }

    @Override
    public Customer findById(Integer id) {
        System.out.println(id);
        Customer customer = this.getHibernateTemplate().get(Customer.class, id);
        return customer;
    }
}
