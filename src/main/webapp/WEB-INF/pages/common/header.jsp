<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 08.01.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container" >
    <div class="left">
        <a class="button" href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
        <a class="button" href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a>
    </div>
    <c:if test="${user.role != 'GUEST'}">
    <div class="right">
        <a class="button" href="?command=SignOut"><fmt:message key="login_sign_out"></fmt:message></a>
    </div>

    <div class="center">
        <button class="button" type="button" name="back" onclick="history.back()"><fmt:message key="back"></fmt:message></button>
        <a class="button" href="Controller?command=GoToMainPage"><fmt:message key="main_page"></fmt:message></a>

        <a class="button" href="?command=GoToHome"><fmt:message key="private_room"></fmt:message></a>

        <c:if test="${user.role != 'GUEST' and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
            <a class="button" href="?command=GoToAdminPage"><fmt:message key="admin_room"></fmt:message></a>
        </c:if>

        <button class="button" type="button" name="back" onclick="history.forward()"><fmt:message key="next"></fmt:message></button>
        </c:if>
    </div>
</div>
</body>
</html>
