<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Catalog</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">Назад</button>
<br><br><br>
<div align="center"><h1>Каталог пользователей</h1></div>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td>Поиск по id</td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
            <td>Поиск по email</td>
            <td><input type="text" name="email"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
            <td>Показать всех пользователей</td>
            <td><input type="submit" value="Показать" name="allUser"></td>
        </tr>
        <tr>
            <td>Показать только администраторов</td>
            <td><input type="submit" value="Показать" name="allAdmin"></td>
        </tr>
    </table>

    <br><br><br>
    <c:if test="${users != null}">
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
                <td>Change status</td>
                <td>Personal card</td>
                <td>Request of Material</td>
            </tr>

            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${user.userId}"></c:out></td>
                    <td><c:out value="${user.secondName}"></c:out></td>
                    <td><c:out value="${user.lastName}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.dateRegistration}"></c:out></td>
                    <td><c:out value="${user.countViolations}"></c:out></td>
                    <td><c:out value="${user.role}"></c:out></td>
                    <td><c:out value="${user.status}"></c:out></td>
                    <td>
                        <input type="submit" value="${statusActive}">
                        <input type="submit" value="${statusBlocked}">
                        <input type="submit" value="${statusDelete}">
                    </td>
                    <td>

                        <a href="?command=GoToStoryLoanCardUser&userId=${user.userId}">История книг</a>
                    </td>
                    <td>
                        <a href="?command=GoToOrderUser&userId=${user.userId}">Заказы</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</form>

</body>
</html>
