<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 23.12.2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>История выдачи книг</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>История выдачи книг</h1></div>
<div align="center"><h3><c:out value="${secondName} ${lastName}"></c:out></h3></div>
<br><br><br>
    <div align="center">
        <form>
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
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
</body>
</html>
