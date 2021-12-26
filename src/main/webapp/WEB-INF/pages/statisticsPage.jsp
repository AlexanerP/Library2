<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 26.12.2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center"><h1>Статистика библиотеки</h1></div>
<table>
    <tr>
        <td>
            <tr>
                <td>
                    <tr>
                        <td>Количество пользователей в системе</td>
                        <td>${countUsers}</td>
                    </tr>
                    <tr>
                        <td>Количество активных пользователей в системе</td>
                        <td>${countUsersActive}</td>
                    </tr>
                    <tr>
                        <td>Количество заблокированных пользователей в системе</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                    <tr>
                        <td>Количество новых запросов на книги</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                </td>
                <td>
                    <tr>
                        <td>Количество новых запросов на книги</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                </td>
            </tr>
        </td>
    </tr>
</table>
</body>
</html>
