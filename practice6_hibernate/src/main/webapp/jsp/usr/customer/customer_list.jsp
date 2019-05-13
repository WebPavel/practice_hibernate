<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/13
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Title</title>
    <%--注意JS引入方式--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax_get.js"></script>
    <script type="text/javascript">
        function orderDetail(customerId) {
            var btn = document.getElementById("btn"+customerId);
            var table = document.getElementById("table"+customerId);
            if (btn.value == "查看详情") {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState==4&& xmlHttp.status==200) {
//                        alert(xmlHttp.responseText);
                        var json = eval("("+xmlHttp.responseText+")");
                        for (var i=0; i<json.length; i++) {
                            table.innerHTML+= "<tr><td>"+json[i].name+"</td><td>"+json[i].gmtCreate+"</td><td>"+json[i].note+"</td><td>"+json[i].cost+"</td></tr>";
                        }
                    }
                };
                xmlHttp.open("GET", "${pageContext.request.contextPath}/biz/order/order_findByCustomer.action?customerId="+customerId+"&_="+new Date().getTime(), true);
                xmlHttp.send(null);
                btn.value="关闭";
            } else {
                btn.value="查看详情";
                // 防止重复添加订单，清空table内容
                table.innerHTML = "";
            }
        }
    </script>
</head>
<body>
    <center><h4>客户列表</h4></center>
    <table border="1" width="60%" align="center">
        <tr>
            <td>序号</td>
            <td>用户名</td>
            <td>昵称</td>
            <td>性别</td>
            <td>年龄</td>
            <td>头像</td>
            <td>地址</td>
            <td>简介</td>
            <td>订单详情</td>
            <td>操作</td>
        </tr>
        <s:iterator var="customer" value="customers" status="status">
            <tr>
                <td><s:property value="#status.count"/></td>
                <td><s:property value="#customer.username"/></td>
                <td><s:property value="#customer.nickname"/></td>
                <td><s:property value="#customer.gender"/></td>
                <td><s:property value="#customer.age"/></td>
                <td><s:property value="#customer.avatar"/></td>
                <td><s:property value="#customer.address"/></td>
                <td><s:property value="#customer.profile"/></td>
                <td>
                    <input id="btn<s:property value='#customer.id'/>" type="button" value="查看详情" onclick="orderDetail(<s:property value='#customer.id'/>);">
                    <table id="table<s:property value='#customer.id'/>" border="1" width="100%">

                    </table>
                </td>
                <td><a href="${pageContext.request.contextPath}/usr/customer/customer_delete.action?id=<s:property value='#customer.id'/>">删除</a></td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>
