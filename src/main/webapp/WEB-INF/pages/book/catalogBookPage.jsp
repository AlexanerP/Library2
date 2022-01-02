<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.12.2021
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Catalog book</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Каталог книг</h1></div>
<form>
    <input type="hidden" name="command" value="CatalogBook">
    <table>
        <tr>
            <td>Введите ID книги: </td>
            <td><input type="number" name="bookId" min="1"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="CatalogBook">
    <table>
        <tbody>
        <tr>
            <td>Введите название книги: </td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>Введите ISBN: </td>
            <td><input type="text" name="isbn"></td>
        </tr>
        <tr>
            <td>Введите имя автора: </td>
            <td><input type="text" name="author"></td>
        </tr>
        <tr>
            <td>Введите жанр: </td>
            <td><input type="text" name="genre"></td>
        </tr>
        </tbody>
        <tfoot>
            <td><input type="submit" value="Поиск"></td>
            <td><input type="reset" value="Сбросить"></td>
        </tfoot>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="CatalogBook">
    <table>
        <tr>
            <td>Показать все книги: </td>
            <td><input type="submit" value="Поиск" name="all"></td>
        </tr>
    </table>
</form>

<div align="center">
    <c:if test="${not empty books}">
    <c:if test="${not empty books}">
        <p>Количество найденных результатов - <c:out value="${booksSize}"/></p>
    </c:if>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Название</th>
            <th>Автор</th>
            <th>Жанр</th>
            <th>ISBN</th>
            <th>Издательство</th>
            <th>Год</th>
            <th>Полка</th>
            <th>Дата добавления</th>
            <th>Библиотека</th>
            <th>Количество</th>
            <th>На выдаче</th>
            <th>Описание</th>
            <th>Обновить</th>
        </tr>
        <c:forEach var="books" items="${books}" varStatus="status">
        <tr>
            <td><c:out value="${status.index + 1}"></c:out></td>
            <td><c:out value="${books.bookDtoId}"></c:out></td>
            <td><c:out value="${books.title}"></c:out></td>
            <td>
                <c:forEach var="author" items="${books.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="genre" items="${books.genres}">
                    <c:out value="${genre.category}"></c:out>
                </c:forEach>
            </td>
            <td><c:out value="${books.isbn}"></c:out></td>
            <td><c:out value="${books.publisher}"></c:out></td>
            <td><c:out value="${books.year}"></c:out></td>
            <td><c:out value="${books.shelf}"></c:out></td>
            <td><c:out value="${books.added}"></c:out></td>
            <td><c:out value="${books.cityLibrary}"></c:out></td>
            <td><c:out value="${books.quantity}"></c:out></td>
            <td><c:out value="${books.borrow}"></c:out></td>
            <td><c:out value="${books.description}"></c:out></td>
            <td>
                <c:if test="${books.bookDtoId != 0}">
                        <p><a href="?command=GoToUpdateBook&bookId=${books.bookDtoId}">Обновить</a></p>
                </c:if>
            </td>
            </c:forEach>
            </c:if>
        </tr>
    </table>
</div>

</body>
</html>
