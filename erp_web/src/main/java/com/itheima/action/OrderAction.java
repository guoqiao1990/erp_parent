package com.itheima.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.domain.Order;
import com.itheima.domain.PageBean;
import com.itheima.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Guo @gq451698495@163.com
 * @date 2018/7/9 19:30
 */
@Component
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport {

    @Autowired
    private OrderService orderService;

    @Action(value = "findOrder")
    public void findOrder() throws IOException {
        //设置乱码问题
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");

        //得到customer_id与当前页
        Integer currPage =Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
        Integer cid =Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));

        //查询客户对应订单
        PageBean<Order> pageBean=orderService.findOrderByCustomerPage(cid,currPage);

        //对数据进行过滤，取消循环引用
        PropertyFilter filter=new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                if ("id".equalsIgnoreCase(s)){
                    return false;
                }
                if ("cusPhone".equalsIgnoreCase(s)){
                    return false;
                }
                if ("orders".equalsIgnoreCase(s)){
                    return false;
                }
                if ("cusImgsrc".equalsIgnoreCase(s)){
                    return false;
                }
                return true;
            }
        };
        //将orders转换成json格式
        String json = JSONArray.toJSONString(pageBean,filter,SerializerFeature.DisableCircularReferenceDetect);
        //将json相应到浏览器
        System.out.println(json);
        ServletActionContext.getResponse().getWriter().println(json);

    }

}
