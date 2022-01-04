<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.12.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Работа с администраторами</h1></div>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td>Поиск по id</td>
            <td><input type="number" name="userIdFind" min="1"></td>
            <td><input type="submit" value="Найти"></td>
<%--            <td><a href="?command=ManagerCatalog&userIdFind=${userIdFind}">Найти</a></td>--%>
        </tr>
    </table>
</form>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td>Поиск по email</td>
            <td><input type="text" name="email"></td>
            <td><input type="submit" value="Найти"></td>
<%--            <td><a href="?command=ManagerCatalog&email=email">Найти</a></td>--%>
        </tr>
    </table>
</form>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td>Показать всех администраторов</td>
            <td><input type="submit" value="Найти" name="allAdmins"></td>
<%--            <td><a href="?command=ManagerCatalog&allAdmins=allAdmins">Найти</a></td>--%>
        </tr>
    </table>
</form>

<c:if test="${not empty users}">

<table align="center">
    <tr>
        <td>#</td>
        <td>User id</td>
        <td>Second name</td>
        <td>Last name</td>
        <td>Email</td>
        <td>Date registration</td>
        <td>Number of violations</td>
        <td>Role</td>
        <td>Status</td>
        <td>Изменить роль</td>
    </tr>
    <c:forEach var="users" items="${users}" varStatus="status">
    <tr>
        <td><c:out value="${status.index + 1}"></c:out></td>
        <td><c:out value="${users.userId}"></c:out></td>
        <td><c:out value="${users.secondName}"></c:out></td>
        <td><c:out value="${users.lastName}"></c:out></td>
        <td><c:out value="${users.email}"></c:out></td>
        <td><c:out value="${users.dateRegistration}"></c:out></td>
        <td><c:out value="${users.countViolations}"></c:out></td>
        <td><c:out value="${users.role}"></c:out></td>
        <td><c:out value="${users.status}"></c:out></td>
        <td>
            <a href="?command=ActionAdminCommand&userId=${users.userId}&role=user">User</a>
            <a href="?command=ActionAdminCommand&userId=${users.userId}&role=admin">Admin</a>
            <a href="?command=ActionAdminCommand&userId=${users.userId}&role=manager">Manager</a>
        </td>
    </tr>
    </c:forEach>
        </table>
</c:if>
</body>
</html>
