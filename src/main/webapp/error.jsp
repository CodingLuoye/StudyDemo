<%--
  Created by IntelliJ IDEA.
  User: YCKJ1409
  Date: 2019/6/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title>统⼀异常处理</title>
</head>
<body>
<h1>Error Handler</h1>
<div th:text="${url}"></div>
<div th:text="${exception.message}"></div>
</body>
</html>
