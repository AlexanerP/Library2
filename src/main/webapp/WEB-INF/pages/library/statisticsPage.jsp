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
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToAdminPage">Кабинет администратора</a></td>
    </tr>
</table>
<div align="center"><h1>Статистика библиотеки</h1></div>
<div align="center">
    <table>
        <tr>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th>Статистика пользователей</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Количество пользователей в библиотеке</td>
                        <td>${countUsers}</td>
                    </tr>
                    <tr>
                        <td>Количество активных пользователей в библиотеке</td>
                        <td>${countUsersActive}</td>
                    </tr>
                    <tr>
                        <td>Количество заблокированных пользователей в библиотеке</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                    <tr>
                        <td>Количество удаленных пользователей в библиотеке</td>
                        <td>${countUsersDelete}</td>
                    </tr>
                    <tr>
                        <td>Количество новых пользователей в этом месяце</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                    </td>
                    <td>
                        <tr>
                            <td>Количество новых пользователей в этом году</td>
                            <td>${countUsersBlocked}</td>
                        </tr>
                </table>
            </td>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th>Статистика книг</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Количество книг в библиотеке</td>
                        <td>${countUsers}</td>
                    </tr>
                    <tr>
                        <td>Количество новых книг в этом месяце</td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                    </td>
                    <td>
                        <tr>
                            <td>Количество новых книг в этом году</td>
                            <td>${countUsersBlocked}</td>
                        </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th>Статистика карт выдачи</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Количество книг на выдаче за всё время</td>
                        <td>${countUsers}</td>
                    </tr>
                    <tr>
                        <td>Количество активных книг на выдаче</td>
                        <td>${countUsersActive}</td>
                    </tr>
                </table>
            </td>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th>Статистика заказов</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>Количество заказов на книги</td>
                        <td>${countUsers}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
