<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Request Catalog</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Каталог заказов</h1></div>

<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td>Поиск по ID пользователя</td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td>Поиск по ID заказа</td>
            <td><input type="number" name="orderId"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
            <td>Поиск по городу</td>
            <td>
                <select name="city">
                    <c:forEach var="cities" items="${cities}">
                        <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="submit" value="Найти"></td>
        </tr>
</table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td>Поиск по статусу</td>
            <td>
                <select name="status">
                    <option value="opened">Открыта</option>
                    <option value="approved">Одобрено</option>
                    <option value="rejected">Отказано</option>
                    <option value="arrived">Прибыла</option>
                </select>
            </td>
            <td><input type="submit" value="Найти"></td>
        </tr>
</table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td>Показать по городу и статусу</td>
            <td>
                <select name="city">
                    <c:forEach var="cities" items="${cities}">
                        <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                    </c:forEach>
                </select>
                <select name="status">
                    <option value="opened">Открыта</option>
                    <option value="approved">Одобрено</option>
                    <option value="rejected">Отказано</option>
                    <option value="arrived">Прибыла</option>
                </select>
            </td>
            <td><input type="submit" value="Показать"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td>Показать все заказы</td>
            <td><input type="submit" value="Показать" name="allRequest"></td>
        </tr>
</table>
</form>

<br>
<form>
    <c:if test="${not empty orders}">
        <p>Количество найденных результатов - <c:out value="${ordersSize}"/></p>
    </c:if>

    <c:if test="${not empty orders}">
    <table>
        <tr>
            <td>#</td>
            <td>ID Заказ</td>
            <td>ID Пользователя</td>
            <td>ID Админ</td>
            <td>ID Книги</td>
            <td>Название</td>
            <td>ISBN</td>
            <td>Год</td>
            <td>Дата заказа</td>
            <td>Библиотека</td>
            <td>Комментарий</td>
            <td>Статус</td>
            <td>Изменить статус</td>
        </tr>
        <c:forEach var="orders" items="${orders}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"></c:out></td>
                <td><c:out value="${orders.orderDtoId}"></c:out></td>
                <td> <a href="?command=UserCatalog&userId=${orders.userId}"><c:out value="${orders.userId}"></c:out> </a></td>
                <td> <a href="?command=UserCatalog&userId=${orders.adminId}"><c:out value="${orders.adminId}"></c:out> </a></td>
                <td> <a href="?command=CatalogBook&bookId=${orders.bookId}"><c:out value="${orders.bookId}"></c:out> </a></td>
                <td><c:out value="${orders.title}"></c:out></td>
                <td><c:out value="${orders.isbn}"></c:out></td>
                <td><c:out value="${orders.year}"></c:out></td>
                <td><c:out value="${orders.date}"></c:out></td>
                <td><c:out value="${orders.cityLibrary}"></c:out></td>
                <td><c:out value="${orders.comment}"></c:out></td>
                <td><c:out value="${orders.status}"></c:out></td>
                <td>
                    <a href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=approved">Одобрено</a>
                    <a href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=rejected">Отказано</a>
                    <a href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=arrived">Прибыло в библиотеку</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</form>
</body>
</html>
