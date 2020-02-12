<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: sherman2571
  Date: 2019/12/9
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add employee</title>
</head>
<%--
    使用Spring MVC form标签：
        1. 开发效率高
        2. 表单值回显
    注意：
        Spring MVC的form标签要能正常使用，request域中必须要有一个bean，bean的属性需要和表单字段一一对应。
        该bean对象由modelAttribute指定，默认bean名称为command，所以在Controller中，也需要加入一个空的Employee
--%>
<body>
<%--
     注意这里的action的路径要写成绝对路径，相对路径总是相对于当前访问资源
     实际开发过程中也应该使用绝对路径
 --%>
<form:form action="${pageContext.request.contextPath}/crud/emp" method="post" modelAttribute="employee">
    <%-- path属性就对应html表单中name属性值 --%>
    <c:if test="${employee.id == null}">
        LastName: <form:input path="lastName"/><br/>
    </c:if>
    <c:if  test="${employee.id != null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>
    Email: <form:input path="email"/><br/>
    <%
        Map<String, String> genders = new HashMap<>();
        genders.put("1", "Male");
        genders.put("0", "FeMale");
        request.setAttribute("genders", genders);
    %>
    Gender: <form:radiobuttons path="gender" items="${genders}"/><br/>
    <%-- 1、如果不指定id，id默认和path相同
         2、itemLabel：下拉菜单显示的属性
         3、itemValue：下来菜单最终传递给后台的属性
     --%>
    Department: <form:select path="department.id" id="deptId"
                             items="${departments}" itemLabel="departmentName" itemValue="id"/><br/>
    <input type="submit" value="提交"/>
</form:form>
</body>
</html>
