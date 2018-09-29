package com.itheima.action;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:30
 */
@Component
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer=new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    @Autowired
    private CustomerService customerService;

    /**查询所有客户信息*/
    @Action(value = "findAllCustomer",results = {@Result(name = "success",location="/customerList.jsp")})
    public String findAllCustomer(){
        List<Customer> cs=customerService.finfAllCustomer();
        ActionContext.getContext().getValueStack().set("cs",cs);
        return SUCCESS;
    }

    /**删除客户信息*/
    @Action(value = "delCustomer",results = {@Result(name = "success",location="/findAllCustomer",type = "redirectAction")})
    public String delCustomer(){
        System.out.println(customer);
        customerService.delCustomer(customer);
        return SUCCESS;
    }

}
