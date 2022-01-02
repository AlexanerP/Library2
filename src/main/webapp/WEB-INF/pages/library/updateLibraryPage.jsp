<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div align="center"><h1>Обновление библиотеки</h1></div>
<div align="center">
    <form>
        <input type="hidden" name="command" value="UpdateLibrary">
        <table>
            <tr>
                <td>Значение</td>
                <td>Старые значения</td>
                <td>Заменить</td>
            </tr>
            <tr>
                <td>ID</td>
                <td><c:out value="${library.libraryId}"></c:out></td>
                <td><input type="number" name="libraryId" value="${library.libraryId}" placeholder="ID"></td>
            </tr>
            <tr>
                <td>Город</td>
                <td><c:out value="${library.city}"></c:out></td>
                <td><input type="text" name="city" placeholder="Город"></td>
            </tr>
            <tr>
                <td>Улица</td>
                <td><c:out value="${library.street}"></c:out></td>
                <td><input type="text" name="street" placeholder="Улица"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Обновить"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
