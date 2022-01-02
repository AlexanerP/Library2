<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.12.2021
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить новую книгу</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Добавление новой книги</h1></div>

<div align="center">
    <table>
        <th>
        <td>
            <table>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="CreateBook">
                    <thead>
                    <tr>
                        <th colspan="2">Добавление книги в библиотеку</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Введите название произведения: </td>
                        <td><input type="text" placeholder="Название произведения" name="title"></td>
                    </tr>
                    <tr>
                        <td>ISBN: </td>
                        <td><input type="text" placeholder="ISBN" name="isbn"></td>
                    </tr>
                    <tr>
                        <td>Издательство: </td>
                        <td><input type="text" placeholder="Издательство" name="publisher"></td>
                    </tr>
                    <tr>
                        <td>Год издательства: </td>
                        <td><input type="text" placeholder="Год" name="year"></td>
                    </tr>
                    <tr>
                        <td>Количество экземпляров: </td>
                        <td><input type="number" placeholder="Количество книг" name="count" min="1"></td>
                    </tr>
                    <tr>
                        <td>Местонахождения книги</td>
                        <td>
                            <select name="city">
                                <c:forEach var="cities" items="${cities}">
                                    <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Полка: </td>
                        <td><input type="text" placeholder="Полка" name="shelf"></td>
                    </tr>
                    <tr>
                        <td>Имя автора (авторов): </td>
                        <td><input type="text" placeholder="Автор" name="author"></td>
                    </tr>
                    <tr>
                        <td>Категория (категории): </td>
                        <td><input type="text" placeholder="Категория" name="category"></td>
                    </tr>
                    <tr>
                        <td>Описание: </td>
                        <td><textarea rows="5" cols="50" name="description"></textarea></td>
                        <%--                        <td><input type="number" placeholder="Описание" name="description"></td>--%>
                    </tr>
                    <tr>
                        <td colspan="2">Авторов и жанры (Если несколько значений)) разделять символом "/"</td>
                    </tr>
                    <tfoot align="center">
                    <tr>
                        <th colspan="2"><input type="submit" VALUE="Добавить книгу" name="addBook"></th>
                    </tr>
                    </tfoot>
                </form>
            </table>
        </td>
        <td>

            <table>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="CatalogBook">
                    <thead>
                    <tr>
                        <th colspan="2">Поиск книги по названию</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Название книги</td>
                        <td><input type="text" placeholder="Название книги" name="title"></td>
                    </tr>
                    <tr>
                        <th colspan="2"><input type="submit" VALUE="Найти книгу" name="search">
                    </tr>
                </form>
            </table>
        </td>
        </th>
    </table>
    </form>
</div>

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
