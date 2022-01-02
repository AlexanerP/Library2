<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.12.2021
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">Назад</button>
<div align="center"><h1>Обновление книги</h1></div>
<div align="center">
    <form>
        <input type="hidden" name="command" value="UpdateBook">
        <table>
            <tr>
                <td></td>
                <td>Старые данные</td>
                <td>Введите новое значение</td>
            </tr>
            <tr>
                <td>ID</td>
                <td><c:out value="${book.bookDtoId}"></c:out></td>
                <td><input type="text" value="${book.bookDtoId}" name="bookId"></td>
            </tr>
            <tr>
                <td>Название</td>
                <td><c:out value="${book.title}"></c:out></td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>ISBN</td>
                <td><c:out value="${book.isbn}"></c:out></td>
                <td><input type="text" name="isbn"></td>
            </tr>
            <tr>
                <td>Библиотека</td>
                <td><c:out value="${book.cityLibrary}"></c:out></td>
                <td>
                    <select name="cityLibrary">
                        <c:forEach var="libraries" items="${libraries}">
                            <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                        </c:forEach>
                    </select>
                </td>
<%--                <td><input type="text" name="cityLibrary" value="${book.cityLibrary}"></td>--%>
            </tr>
            <tr>
                <td>Авторы</td>
                <td>
                    <c:forEach var="author" items="${book.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
                <td><input type="text" name="author"></td>
            </tr>
            <tr>
                <td>Жанр</td>
                <td>
                    <c:forEach var="genre" items="${book.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
                <td> <input type="text" name="genre"></td>
            </tr>
            <tr>
                <td>Издательство</td>
                <td><c:out value="${book.publisher}"></c:out></td>
                <td><input type="text" name="publisher"></td>
            </tr>
            <tr>
                <td>Год</td>
                <td><c:out value="${book.year}"></c:out></td>
                <td><input type="text" name="year"></td>
            </tr>
            <tr>
                <td>Полка</td>
                <td><c:out value="${book.shelf}"></c:out></td>
                <td><input type="text" name="shelf"></td>
            </tr>
            <tr>
                <td>Количество</td>
                <td><c:out value="${book.quantity}"></c:out></td>
                <td><input type="number" name="quantity" min="1"></td>
            </tr>
            <tr>
                <td>Описание</td>
                <td><c:out value="${book.description}"></c:out></td>
                <td><textarea rows="5" cols="50" name="description"></textarea></td>
            </tr>
            <td colspan="3">
                <p align="center">*Если авторов (жанров) несколько, вводить через "/"</p>
            </td>
            <td colspan="3">
                <td><input type="submit" value="Обновить"></td>
            </td>
        </table>

    </form>
</div>
</body>
</html>
