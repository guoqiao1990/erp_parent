<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 45169
  Date: 2018/7/9
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script
            src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <title>客户列表信息</title>

    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
      }

      .bg {
        max-width: 530px;
        padding: 15px;
        margin: 0 auto;
      }
    </style>

    <script type="text/javascript">
        var currPage=1;
        var cid;
      function findOrder(customer_id) {
          cid=customer_id;
        $.post("${pageContext.request.contextPath}/order/findOrder",
            {"cid":cid,"currPage":currPage},function (data){
            var html="";
            $(data.list).each(function (i,n){
                html+="<tr><td>"+n.orderNum+"</td><td>"+n.receiverInfo+"</td><td>"+n.price+"</td><td>"+n.customer.cusName+"</td><td>订单操作</td></tr>";
            });
            $("#msg").html(html);

            // 数据收集
            currPage=data.currPage;
            var totalPage=data.totalPage;
            var begin1=data.begin;
            var end1=data.end;

            // $("#page").html();

            var pageHtml="<ul class=\"pagination\">";
            //1.判断是否可以向上翻页
            if(currPage==1){
                pageHtml+="<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
            }else{
                pageHtml+="<li><a href=\"#\" onclick=\"prePage()\">&laquo;</a></li>";
            }

            //2.判断中间页码
            for(var i=begin1;i<=end1;i++){
                if(i==currPage){
                    pageHtml+="<li class=\"active\"><a href=\"#\" >"+i+"</a></li>";
                }else{
                    pageHtml+="<li><a href=\"#\" onclick=\"selectPage("+i+")\">"+i+"</a></li>";
                }
            }

            //3.判断是否可以向下翻页
            if(currPage==totalPage){
                pageHtml+="<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>";
            }else{
                pageHtml+="<li><a href=\"#\" onclick=\"nextPage()\">&raquo;</a></li>";
            }
            pageHtml+="</ul>";

            $("#page").html(pageHtml);

        },"json");
      }
      
      function prePage() {
          currPage=currPage-1;
          findOrder(cid);
      }
      function selectPage(i) {
          currPage=i;
          findOrder(cid);
      }
      function nextPage() {
          currPage=currPage+1;
          findOrder(cid);
      }
      
      
    </script>

  </head>
  <body>

    <table class="table table-hover table-bordered bg">
      <tr>
        <td colspan="5">
          <a href="${pageContext.request.contextPath}/addCustomer.jsp"
             class="btn btn-primary btn-lg active" role="button">Add Customer</a>

        </td>
      </tr>
      <tr>
        <td>编号</td>
        <td>客户照片</td>
        <td>客户名称</td>
        <td>联系电话</td>
        <td>操作</td>
      </tr>
      <s:iterator value="cs" var="c" status="vs">
        <tr>
          <td><s:property value="#vs.index+1"/></td>
          <td><s:property value="#c.cusName"/></td>
          <td><img src="<s:property value='#c.cusImgsrc'/>" class="img-circle" width="70px" height="70px"></td>
          <td><s:property value="#c.cusPhone"/></td>
          <td>
            <a href="${pageContext.request.contextPath}/customer/delCustomer?id=<s:property value='#c.id'/>" class="btn btn-primary btn-sm">删除客户</a>
            <!-- Button trigger modal -->
            <a href="javascript:void(0)" onclick="findOrder(<s:property value="#c.id"/>)" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">查看订单</a>
          </td>
        </tr>
      </s:iterator>
    </table>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">订单详情</h4>
          </div>
          <div class="modal-body" >

            <table class="table table-bordered .table-hover">
              <tr>
                <td>订单编号</td>
                <td>收货地址</td>
                <td>订单价格</td>
                <td>客户名称</td>
                <td>操作</td>
              </tr>
              <tbody id="msg"></tbody>
            </table>

          </div>

          <div class="modal-footer">
            <nav id="page"></nav>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </body>
</html>
