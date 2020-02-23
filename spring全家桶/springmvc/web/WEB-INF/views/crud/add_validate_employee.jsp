<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sherman2571
  Date: 2019/12/10
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add format employee</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/validate/show_validate_employee" method="post" modelAttribute="validateEmployee">
    <%--<form:errors path="*"/><br/>--%>
    id: <form:input path="id"/><br/>
    lastName: <form:input path="lastName"/><br/>
    email: <form:input path="email"/><br/>
    birthday: <form:input path="birthday"/><br/>
    <form:errors path="birthday"/><br/>
    salary：<form:input path="salary"/><br/>
    <input type="submit" value="提交"/>
</form:form>
</body>
</html>
