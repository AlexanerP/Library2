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
<div align="center"><h1>Добавление новой книги</h1></div>
<button type="button" name="Назад" onclick="history.back()">back</button>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="ActionBook">
    <table>
        <th>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th colspan="2">Добавление книги в библиотеку</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Имя автора (авторов): </td>
                        <td><input type="text" placeholder="Автор" name="author"></td>
                    </tr>
                    <tr>
                        <td>Категория (категории): </td>
                        <td><input type="text" placeholder="Категория" name="category"></td>
                    </tr>
                    <tr>
                        <td>Введите название произведения: </td>
                        <td><input type="text" placeholder="Название произведения" name="title"></td>
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
                        <td>ISBN: </td>
                        <td><input type="text" placeholder="ISBN" name="isbn"></td>
                    </tr>
                    <tr>
                        <td>Количество экземпляров: </td>
                        <td><input type="number" placeholder="Количество книг" name="count"></td>
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
                    <tfoot align="center">
                    <tr>
                        <th colspan="2"><input type="submit" VALUE="Добавить книгу" name="addBook"></th>
                    </tr>
                    </tfoot>
                </table>
            </td>
        <td>
                <table>
                    <thead>
                    <tr>
                        <th colspan="2">Поиск книги по названию</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Название книги / ISBN</td>
                        <td><input type="text" placeholder="Название книги/ISBN" name="title"></td>
                    </tr>
                    <tr>
                        <th colspan="2"><input type="submit" VALUE="Найти книгу" name="search">
                    </tr>
                </table>
            </td>
        <td>
            <table>
                <thead>
                <tr>
                    <th colspan="2">Обновление книги в библиотеке</th>
                </tr>
                </thead>
                <tr>
                    <td>ID Книги: </td>
                    <td><input type="number" placeholder="id" name="idBook"></td>
                </tr>
                <tr>
                    <td>Введите название произведения: </td>
                    <td><input type="text" placeholder="Название произведения" name="title"></td>
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
                    <td>ISBN: </td>
                    <td><input type="text" placeholder="ISBN" name="isbn"></td>
                </tr>
                <tr>
                    <td>Количество добавляемых книг: </td>
                    <td><input type="number" placeholder="Количество книг" name="count"></td>
                </tr>
                <tfoot align="center">
                <tr>
                    <th colspan="2"><input type="submit" VALUE="Обновить книгу" name="updateBook"></th>
                </tr>
                </tfoot>
            </table>
        </td>
            </th>
    </table>
</form>


    <c:if test="${bookDtos != null}">

    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Genre</th>
            <th>Publisher</th>
            <th>year</th>
        </tr>
        <c:forEach var="bookDTO" items="${bookDtos}">
            <tr>
                <td><c:out value="${bookDTO.bookId}"></c:out></td>
                <td><c:out value="${bookDTO.title}"></c:out></td>
                <td><c:out value="${bookDTO.authors}"></c:out></td>
                <td><c:out value="${bookDTO.genres}"></c:out></td>
                <td><c:out value="${bookDTO.publisher}"></c:out></td>
                <td><c:out value="${bookDTO.year}"></c:out></td>
                <td><c:out value="${bookDTO.isbn}"></c:out></td>
        </c:forEach>
        <tr>
            <th colspan="9">
                <p align="center">"Page"</p>
            </th>
        </tr>
    </table>
    </c:if>

</body>
</html>
