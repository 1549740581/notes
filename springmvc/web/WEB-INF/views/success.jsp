<%--
  Created by IntelliJ IDEA.
  User: sherman2571
  Date: 2019/12/5
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>success.jsp</title>
</head>
<body>
<h2>success.jsp</h2>

<h2>一.处理模型数据</h2>
<h3>1.1 ModelAndView ======>>> time: ${requestScope.time}</h3>
<h3>1.2 Map ======>>> names: ${requestScope.names}</h3>
<h3>1.3 SessionAttributes ======= >>></h3>
    <h4>【request score】: addr: ${requestScope.addr}</h4>
    <h4>【session score】: addr: ${sessionScope.addr}</h4>
    <h4>【request scope】: magic: ${requestScope.magic}</h4>
    <h4>【session scope】: magic: ${sessionScope.magic}</h4>
<h3>1.4 ModelAttribute ====== >>></h3>
    <h4>original: ${requestScope.original}</h4>
    <h4>modified: ${requestScope.modified}</h4>

<h2>国际化测试——转发方式</h2>
<fmt:message key="i18n.username"/><br/>
<fmt:message key="i18n.password"/><br/>
</body>
</html>
