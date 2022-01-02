<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Catalog</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Каталог пользователей</h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td>Поиск по id</td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td>Поиск по email</td>
            <td><input type="text" name="email"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td>Показать всех пользователей</td>
            <td><input type="submit" value="Показать" name="allUser"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td>Показать только администраторов</td>
            <td><input type="submit" value="Показать" name="allAdmin"></td>
        </tr>
    </table>
</form>
    <br><br><br>
    <c:if test="${not empty users}">
        <div align="center"><p>Количество найденных результатов - <c:out value="${userSize}"/></p></div>
        <table align="center">
            <tr>
                <td>#</td>
                <td>ID</td>
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Email</td>
                <td>Дата регистрации</td>
                <td>Количество штрафов</td>
                <td>Роль</td>
                <td>Статус</td>
                <td>Изменить статус</td>
                <td>Карточки выдачи книг</td>
                <td>Запросы</td>
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
                        <a href="?command=ActionUser&userId=${user.userId}&status=active">Active</a>
                        <a href="?command=ActionUser&userId=${user.userId}&status=blocked">Blocked</a>
                        <a href="?command=ActionUser&userId=${user.userId}&status=delete">Delete</a>
                    </td>
                    <td>
                        <a href="?command=GoToLoanCardUser&userId=${user.userId}">История книг</a>
                    </td>
                    <td>
                        <a href="?command=OrderCatalog&userId=${user.userId}">Заказы</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>
