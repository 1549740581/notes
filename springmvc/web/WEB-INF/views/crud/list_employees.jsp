<%--
  Created by IntelliJ IDEA.
  User: sherman2571
  Date: 2019/12/9
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>list all employees</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action", href).submit();
                return false;
            });
        })
    </script>
</head>
<body>
<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE">
</form>
<c:if test="${empty requestScope.employees}">
    没有任何员工信息!
</c:if>
<c:if test="${!empty requestScope.employees}">
    <table border="1" cellpadding="10" cellspacing="0" align="center" width="50">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Department</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender == 0? 'Female': 'Male'}</td>
                <td>${emp.department.departmentName}</td>
                <td><a href="${pageContext.request.contextPath}/crud/emp/${emp.id}">Edit</a></td>
                <td><a class="delete" href="${pageContext.request.contextPath}/crud/emp/${emp.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="${pageContext.request.contextPath}/crud/emp">添加员工</a>
</body>
</html>
