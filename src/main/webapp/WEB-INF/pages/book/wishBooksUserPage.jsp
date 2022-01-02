<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.12.2021
  Time: 12:21
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
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>Мои избранные книги</h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="GoToOrder">
    </div>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th>ID книги</th>
            <th>Название</th>
            <th>ISBN</th>
            <th>Издательство</th>
            <th>Год</th>
            <th>Полка</th>
            <th>Заказать книгу</th>
            <th>Удалить из списка</th>
        </tr>
        <c:forEach var="wishBook" items="${books}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"></c:out></td>
                <td><c:out value="${wishBook.bookId}"></c:out></td>
                <td><c:out value="${wishBook.title}"></c:out></td>
                <td><c:out value="${wishBook.isbn}"></c:out></td>
                <td><c:out value="${wishBook.publisher}"></c:out></td>
                <td><c:out value="${wishBook.year}"></c:out></td>
                <td><c:out value="${wishBook.shelf}"></c:out></td>

                <td><c:if test="${wishBook.borrow < wishBook.quantity}">
                    <a href="?command=GoToOrder&bookId=${wishBook.bookId}">Заказать книгу</a>
                </c:if></td>
                <td><a href="?command=ActionWishBook&bookId=${wishBook.bookId}">Удалить книгу</a></td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
