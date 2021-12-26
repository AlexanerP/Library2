<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Request Catalog</title>
</head>
<body>
<h1>Request catalog</h1>

<form action="Controller">
    <input type="hidden" name="command" value="RequestsCatalog">
    <table>
        <tr>
            <td>Поиск по id</td>
            <td><input type="number" name="userId"></td>
            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
            <td>Search by city</td>
            <td>
                <select name="city">
                    <c:forEach var="cities" items="${cities}">
                        <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
            <td>Search by status</td>
            <td>
                <select name="status">
                    <c:forEach var="statuses" items="${statuses}">
                        <option value="${statuses.value}"><c:out value="${statuses.value}"></c:out></option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="submit" value="Найти"></td>
        </tr>
        <tr>
            <td>Show all only opened</td>
            <td><input type="submit" value="Показать" name="allRequest"></td>
        </tr>
        <tr>
            <td>Показать по городу и статусу</td>
            <td>
                <select name="city">
                    <c:forEach var="cities" items="${cities}">
                        <option value="${cities.city}"><c:out value="${cities.city}"></c:out></option>
                    </c:forEach>
                </select>
                <select name="status">
                    <c:forEach var="statuses" items="${statuses}">
                        <option value="${statuses.value}"><c:out value="${statuses.value}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Показать" name="allAdmin"></td>
        </tr>
    </table>
</form>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="RequestsCatalog">
    <c:if test="${requestUser != null}">
    <table>
        <tr>
            <td>Request ID</td>
            <td>User ID</td>
            <td>Admin ID</td>
            <td>Book ID</td>
            <td>Title</td>
            <td>ISBN</td>
            <td>Year</td>
            <td>Date</td>
            <td>Library</td>
            <td>Comment</td>
            <td>Status</td>
            <td>Change Status</td>
        </tr>
        <c:forEach var="requests" items="${requests}">
            <tr>
                <td>${requests.requestId}</td>
                <td>${requests.userId}</td>
                <td>${requests.adminId}</td>
                <td>${requests.bookId}</td>
                <td>${requests.title}</td>
                <td>${requests.isbn}</td>
                <td>${requests.year}</td>
                <td>${requests.date}</td>
                <td>${requests.cityLibrary}</td>
                <td>${requests.comment}</td>
                <td>${requests.status}</td>
                <td>
                    <c:forEach var="statuses" items="${statuses}">
                        <option value="${cities.city}"><c:out value="${statuses.value}"></c:out></option>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</form>

</body>
</html>
