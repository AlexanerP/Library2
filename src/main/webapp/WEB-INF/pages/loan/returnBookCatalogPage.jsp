<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 23:13
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
<div align="center"><h1>Возврат книги</h1></div>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td>Введите ID карты выдачи: </td>
            <td><input type="number" name="loanCardId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td>Введите ID пользователя: </td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td>Введите ID книги: </td>
            <td><input type="number" name="bookId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td>Показать по городу: </td>
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
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <input type="submit" value="Показать все открытые выдачи" name="getAll">
        </tr>
    </table>
</form>
<div align="center">
    <form>
        <c:if test="${not empty loanCardSize}">
            <p>Количество найденных результатов - <c:out value="${loanCardSize}"/></p>
        </c:if>
        <c:if test="${not empty loanCards}">
        <table cellpadding="5">
            <tr>
                <td>#</td>
                <td>ID книги</td>
                <td>Название</td>
                <td>ISBN</td>
                <td>Взятие книги</td>
                <td>Срок возврата книги</td>
                <td>Книга возвращена</td>
                <td>Библиотека</td>
                <td>Тип использования</td>
                <td>Статус</td>
                <td>Возврат книги</td>
            </tr>
            <c:forEach var="card" items="${loanCards}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${card.bookId}"></c:out></td>
                    <td><c:out value="${card.title}"></c:out></td>
                    <td><c:out value="${card.isbn}"></c:out></td>
                    <td><c:out value="${card.takingBook}"></c:out></td>
                    <td><c:out value="${card.returnBook}"></c:out></td>
                    <td><c:out value="${card.deadline}"></c:out></td>
                    <td><c:out value="${card.cityLibrary}"></c:out></td>
                    <td><c:out value="${card.typeUse}"></c:out></td>
                    <td><c:out value="${card.status}"></c:out></td>
                    <td><a href="?command=ActionReturnBook&loanCardId=${orders.orderDtoId}">Возврат</a></td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
