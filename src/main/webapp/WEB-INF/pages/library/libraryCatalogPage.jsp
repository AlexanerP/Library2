<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 20:16
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
<div align="center"><h1>Библиотеки</h1></div>
<div align="center">
    <table>
        <c:if test="${not empty library}">
            <tr>
                <td>#</td>
                <td>ID</td>
                <td>Город</td>
                <td>Улица</td>
                <td>Обновить</td>
                <td>Изменить статус</td>
            </tr>
            <c:forEach var="library" items="${library}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${library.libraryId}"></c:out></td>
                    <td><c:out value="${library.city}"></c:out></td>
                    <td><c:out value="${library.street}"></c:out></td>
                    <td>
                        <a href="?command=GoToUpdateLibrary&libraryId=${library.libraryId}&city=${library.city}&street=${library.street}">Обновить</a>
                    </td>
                    <td>
                        <a href="?command=ActionLibrary&libraryId=${library.libraryId}&status=opened">Открыто</a>
                        <a href="?command=ActionLibrary&libraryId=${library.libraryId}&status=closed">Закрыто</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
