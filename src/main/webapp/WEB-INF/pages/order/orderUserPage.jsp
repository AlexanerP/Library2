<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы пользователя</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>Заказы пользователя</h1></div>
<div align="center"><h3><c:out value="${secondName} ${lastName}"></c:out></h3></div>
<div align="center">
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="GoToOrderUser">
        <table>
            <tr>
                <td>#</td>
                <td>Заказ ID</td>
                <td>Пользователь ID</td>
                <td>Email</td>
                <td>Книга ID</td>
                <td>Название</td>
                <td>ISBN</td>
                <td>Год</td>
                <td>Дата подачи</td>
                <td>Библиотека</td>
                <td>Комментарий</td>
                <td>Статус</td>
            </tr>
            <c:forEach var="orders" items="${orders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${orders.orderDtoId}"></c:out></td>
                    <td><c:out value="${orders.userId}"></c:out></td>
                    <td><c:out value="${orders.email}"></c:out></td>
                    <td><c:out value="${orders.bookId}"></c:out></td></td>
                    <td><c:out value="${orders.title}"></c:out></td>
                    <td><c:out value="${orders.isbn}"></c:out></td>
                    <td><c:out value="${orders.year}"></c:out></td>
                    <td><c:out value="${orders.date}"></c:out></td>
                    <td><c:out value="${orders.cityLibrary}"></c:out></td>
                    <td><c:out value="${orders.comment}"></c:out></td>
                    <td><c:out value="${orders.status}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
