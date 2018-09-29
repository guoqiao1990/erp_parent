package com.itheima.action;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:30
 */
@Component
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerMultiAction extends ActionSupport{

    @Autowired
    private CustomerService customerService;

    private File cusImg;
    private String cusImgFileName;
    private String cusImgContentType;
    private String cusName;
    private String cusPhone;

    public File getCusImg() {
        return cusImg;
    }

    public void setCusImg(File cusImg) {
        this.cusImg = cusImg;
    }

    public String getCusImgFileName() {
        return cusImgFileName;
    }

    public void setCusImgFileName(String cusImgFileName) {
        this.cusImgFileName = cusImgFileName;
    }

    public String getCusImgContentType() {
        return cusImgContentType;
    }

    public void setCusImgContentType(String cusImgContentType) {
        this.cusImgContentType = cusImgContentType;
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

    /**添加客户*/
    @Action(value = "addCustomer", results = {
            @Result(name = "success", location = "findAllCustomer", type = "redirectAction"),
            @Result(name = "input", location = "/error.jsp") })
    public String addCustomer(){
        //处理文件上传
        File destFile=new File(ServletActionContext.getServletContext().getRealPath("/upload"),cusImgFileName);
        try {
            FileUtils.copyFile(cusImg,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加客户信息
        Customer customer=new Customer();
        customer.setCusName(cusName);
        customer.setCusPhone(cusPhone);
        String cusImgsrc=ServletActionContext.getRequest().getContextPath()+"/upload/"+cusImgFileName;
        customer.setCusImgsrc(cusImgsrc);

        customerService.addCustomer(customer);
        return SUCCESS;
    }

}
