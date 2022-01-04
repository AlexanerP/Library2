<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">Назад</button>

<div align="center">
    <h1>Message text</h1>
    <h2>
        <c:if test="${not empty successfulMessage}">
            <c:out value="${sessionScope.successfulMessage}" />
            <c:remove var="successfulMessage" scope="session" />
        </c:if>
    </h2>
</div>
<div align="center">
    <h2>
        <c:if test="${not empty successfulMessage}">
            <c:out value="${sessionScope.negativeMessage}" />
            <c:remove var="negativeMessage" scope="session" />
        </c:if>
    </h2>
</div>

</body>
</html>
