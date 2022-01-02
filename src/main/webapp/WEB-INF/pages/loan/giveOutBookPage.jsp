<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div align="center"><h1>Выдача книги</h1></div>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <td>Введите ID заказа: </td>
            <td><input type="number" name="orderId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <td>Показать заказы по городу: </td>
            <td>
                <select name="city">
                    <c:forEach var="libraries" items="${libraries}">
                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <input type="submit" value="Показать все одобренные" name="getAll">
        </tr>
    </table>
</form>

<div align="center">
    <form>
        <c:if test="${not empty orders}">
            <p>Количество найденных результатов - <c:out value="${ordersSize}"/></p>
        </c:if>

        <c:if test="${not empty orders}">
            <table border="1" cellpadding="5">
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
                    <td>Выдать книгу</td>
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
                            <div>
                                <p><a href="?command=ActionGiveOutBook&orderId=${orders.orderDtoId}&type_use=read_home">Выдать на дом</a></p>
                                <p><a href="?command=ActionGiveOutBook&orderId=${orders.orderDtoId}&type_use=read_room">Выдать вчитальный зал</a></p>
                            </div>
                        </td>
                    </tr>
                    <tr></tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</div>
</body>
</html>
