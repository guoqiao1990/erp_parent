package com.itheima.dao.impl;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Customer;
import com.itheima.domain.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:28
 */
@Component
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

    /**注入Session,获得HibernateTemplate*/
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory factory){
        this.setSessionFactory(factory);
    }

    /**根据客户查订单*/
    @Override
    public List<Order> findByCustomer(Customer customer) {
        return (List<Order>) this.getHibernateTemplate().find("from Order o where o.customer=?",customer);
    }

    /**根据客户查询总订单数*/
    @Override
    public Integer findCOunt(Customer c) {

        Long totalCount= (Long) this.getHibernateTemplate().find("select count(*) from Order o where o.customer=?", c).iterator().next();
        return totalCount.intValue();
    }

    @Override
    public List<Order> findOrderByCustomerPage(Customer c, Integer pageSize, Integer beginIndex) {

        DetachedCriteria dc=DetachedCriteria.forClass(Order.class);
        dc.add(Restrictions.eq("customer",c));
        return (List<Order>) this.getHibernateTemplate().findByCriteria(dc,beginIndex,pageSize);
    }
}
