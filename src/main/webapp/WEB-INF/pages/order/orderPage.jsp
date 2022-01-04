<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 16.12.2021
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заказ книги</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>Заказ книги</h1></div>
<form>
    <input type="hidden" name="command" value="Order">
    <div align="center">
        <table>
            <tr>
                <td>ID книги:</td>
                <td><c:out value="${book.bookDtoId}"></c:out></td>
            </tr>
            <tr>
                <td>Название:</td>
                <td><c:out value="${book.title}"></c:out></td>
            </tr>
            <tr>
                <td>ISBN:</td>
                <td><c:out value="${book.isbn}"></c:out></td>
            </tr>
            <tr>
                <td>Издательство:</td>
                <td><c:out value="${book.publisher}"></c:out></td>
            </tr>
            <tr>
                <td>Год:</td>
                <td><c:out value="${book.year}"></c:out></td>
            </tr>
            <tr>
                <td>Автор:</td>
                <td>
                    <c:forEach var="author" items="${book.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Жанр:</td>
                <td>
                    <c:forEach var="genre" items="${book.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Полка:</td>
                <td><c:out value="${book.shelf}"></c:out></td>
            </tr>
            <tr>
                <td>Описание:</td>
                <td><c:out value="${book.description}"></c:out></td>
            </tr>
            <tr>
                <td>Select library (city)</td>
                <td>
                    <select name="city">
                        <c:forEach var="cities" items="${cities}">
                            <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Message</td>
                <td> <p><textarea rows="5" cols="30" name="text"></textarea></p></td>
            </tr>
            <tr>
                <div align="center">
                    <a href="?command=Order&bookId${book.bookDtoId}">Заказать</a>
                </div>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
