<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 08.01.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Header</title>

    <c:if test="${not empty user}">
        <form width="400" height="300" align="right">
            <input type="hidden" name="command" value="SignOut">
            <p><input type="submit" value="<fmt:message key="login_sign_out"></fmt:message>"></p>
        </form>
    <div align="center">
        <table>
            <tr>
                <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
                <td><a href="Controller?command=GoToMainPage"><input type="submit" value="На главную страницу"></a></td>
                <td><a href="?command=GoToHome"><input type="submit" value="Домашний кабинет"></a></td>
                <td>
                    <c:if test="${not empty user.role and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
                <td><a href="?command=GoToAdminPage"><input type="submit" value="Кабинет администратора"></a></td>
                </c:if>
                </td>
                <td><button type="button" name="back" onclick="history.forward()">Вперёд</button></td>
            </tr>
        </table>
    </div>
    </c:if>

</head>
<%--<body>--%>
<%--<div align="center">--%>
<%--    <c:if test="${not empty user}">--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td><button type="button" name="back" onclick="history.back()">Назад</button></td>--%>
<%--                <td><a href="Controller?command=GoToMainPage"><input type="submit" value="На главную страницу"></a></td>--%>
<%--                <td><a href="?command=GoToHome"><input type="submit" value="Домашний кабинет"></a></td>--%>
<%--                <td>--%>
<%--                    <c:if test="${not empty user.role and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">--%>
<%--                <td><a href="?command=GoToAdminPage"><input type="submit" value="Кабинет администратора"></a></td>--%>
<%--                </c:if>--%>
<%--                </td>--%>
<%--                <td><button type="button" name="back" onclick="history.forward()">Вперёд</button></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </c:if>--%>
<%--</div>--%>

</body>
</html>
