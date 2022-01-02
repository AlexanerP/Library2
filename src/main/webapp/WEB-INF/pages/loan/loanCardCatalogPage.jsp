<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 30.12.2021
  Time: 20:47
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
<div align="center"><h1>Каталог карт выдачи</h1></div>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td>Введите ID книги: </td>
            <td><input type="number" name="bookId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td>Введите ID пользователя: </td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Поиск"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td>Показать карточки выдачи по городу: </td>
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
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <input type="submit" value="Показать все" name="getAll">
        </tr>
    </table>
</form>

<div align="center">
    <form>
        <c:if test="${not empty loanCards}">
            <p>Количество найденных результатов - <c:out value="${loanCardsSize}"/></p>
        </c:if>

        <c:if test="${not empty loanCards}">
            <table border="1" cellpadding="5">
                <tr>
                    <td>#</td>
                    <td>ID Карты выдачи</td>
                    <td>ID Пользователя</td>
                    <td>ID Книги</td>
                    <td>Название</td>
                    <td>ISBN</td>
                    <td>Имя и фамилия</td>
                    <td>Город</td>
                    <td>Дата выдачи</td>
                    <td>Дата крайнего срока</td>
                    <td>Дата возврата</td>
                    <td>Тип использования</td>
                    <td>Статус</td>
                </tr>
                <c:forEach var="loanCards" items="${loanCards}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.index + 1}"></c:out></td>
                        <td><c:out value="${loanCards.loanCardDtoId}"></c:out></td>
                        <td> <a href="?command=UserCatalog&userId=${loanCards.userId}"><c:out value="${loanCards.userId}"></c:out> </a></td>
                        <td> <a href="?command=CatalogBook&bookId=${loanCards.bookId}"><c:out value="${loanCards.bookId}"></c:out> </a></td>
                        <td><c:out value="${loanCards.title}"></c:out></td>
                        <td><c:out value="${loanCards.isbn}"></c:out></td>
                        <td><c:out value="${loanCards.secondName} ${loanCards.lastName} "></c:out></td>
                        <td><c:out value="${loanCards.cityLibrary}"></c:out></td>
                        <td><c:out value="${loanCards.takingBook}"></c:out></td>
                        <td><c:out value="${loanCards.deadline}"></c:out></td>
                        <td><c:out value="${loanCards.returnBook}"></c:out></td>
                        <td><c:out value="${loanCards.typeUse}"></c:out></td>
                        <td><c:out value="${loanCards.status}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</div>
</body>
</html>
