<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="registration"></fmt:message></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center"><h1><fmt:message key="registration"></fmt:message></h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="Registration">
    <div align="center">
        <table>
            <tr>
                <td><fmt:message key="user_email"></fmt:message></td>
                <td><input type="text" name="email" placeholder="<fmt:message key="user_email"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="password_user"></fmt:message></td>
                <td><input type="password" name="password" placeholder="<fmt:message key="password_user"></fmt:message>" /></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p><fmt:message key="message_for_registration"></fmt:message></p>
                </i>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="user_second_name"></fmt:message></td>
                <td><input type="text" name="second_name" placeholder="<fmt:message key="user_second_name"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="user_last_name"></fmt:message></td>
                <td><input type="text" name="last_name" placeholder="<fmt:message key="user_last_name"></fmt:message>"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="<fmt:message key="registration"></fmt:message>"></td>
            </tr>
        </table>
    </div>
</form>
<div align="center">
    <c:out value="${requestScope.message}"></c:out>
</div>
</body>
</html>
