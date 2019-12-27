<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>index.jsp</title>
      <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.4.1.min.js"></script>
      <script type="text/javascript">
          $(function () {
              $("#testJson").click(function () {
                    var url = this.href;
                    var args = {};
                    $.post(url, args, function (data) {
                        for (var i = 0; i < data.length; ++i) {
                            var id = data[i].id;
                            var lastName = data[i].lastName;
                            alert(id + ": " + lastName);
                        }
                    });
                    return false;
              });
          })
      </script>
  </head>
  <body>

  <h2>HiddenHttpMethodFilter</h2>
      <h3>POST请求</h3>
      <form action="rest/test" method="post">
          <input type="submit" value="post请求">
      </form>

      <h3>POST隐藏域转换成delete请求</h3>
      <form action="rest/test" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="_method -> delete">
      </form>

      <h3>POST隐藏域转换成PUT请求</h3>
      <form action="rest/test" method="post">
          <input type="hidden" name="_method" value="POST">
          <input type="submit" value="_method -> post">
      </form>

  <h2>POJO映射</h2>
  <form action="test/pojo" method="post">
      id: <input type="text" name="id"><br/>
      username: <input type="text" name="username"><br/>
      province: <input type="text" name="addr.province"><br/> <%-- 注意这里级联属性 --%>
      city: <input type="text" name="addr.city"><br/>         <%-- 注意这里级联属性 --%>
      <input type="submit" value="submit"><br/>
  </form>

  <h2>@ModelAttribute测试</h2>
  <form action="test/model/model_attribute" method="post"><br/>
      <%-- id: <input type="text" name="id"><br/> 不更新此字段，不要填写--%>
      <%-- 或者通过隐藏域提交：<input type="hidden" name="id" value="1"> --%>
      username: <input type="text" name="username"><br/>
      province: <input type="text" name="addr.province"><br/>
      city: <input type="text" name="addr.city"><br/>
      <input type="submit" value="submit"><br/>
  </form>

  <h2>国际化测试——直接访问</h2>
  <fmt:message key="i18n.username"/><br/>
  <fmt:message key="i18n.password"/><br/>

  <h2>转发&重定向</h2>
  <a href="${pageContext.request.contextPath}/view/forward">转发</a><br/>
  <a href="${pageContext.request.contextPath}/view/redirect">重定向</a><br/>
  <a href="${pageContext.request.contextPath}/view/web_inf/forward">WEB-INF：转发</a><br/>
  <a href="${pageContext.request.contextPath}/view/web_inf/redirect">WEB-INF：重定向</a><br/>

  <h2>CRUD</h2>
  <a href="${pageContext.request.contextPath}/crud/emps">显示所有员工列表</a>


  <h2>ConvertService</h2>
  <form action="cvt/convert_service" method="post">
      格式：lastName-email-gender-department.id<br/>
      employee: <input type="text" name="employee"><br/>
      <input type="submit" value="submit">
  </form>

  <h2>数据格式化</h2>
  <a href="${pageContext.request.contextPath}/format/format_employee">格式化数据</a>

  <h2>数据校验</h2>
  <a href="${pageContext.request.contextPath}/validate/validate_employee">校验数据</a>
  </body>

  <h2>json返回</h2>
  <a href="${pageContext.request.contextPath}/json/all_employees" id="testJson">返回Json</a>

  <h2>i18n</h2>
  <a href="${pageContext.request.contextPath}/i18n/change?locale=zh_CN">中文</a>
  <a href="${pageContext.request.contextPath}/i18n/change?locale=en_US">英文</a>

  <h2>文件上传</h2>
  <form:form action="${pageContext.request.contextPath}/file_upload" method="post" enctype="multipart/form-data">
      File:  <input type="file" name="file">
      Desc: <input type="text" name="desc">
      <input type="submit" value="上传">
  </form:form>
</html>