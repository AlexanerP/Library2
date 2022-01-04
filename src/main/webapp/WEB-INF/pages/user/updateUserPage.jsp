<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.12.2021
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновление данных пользователя</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>Обновление личных данных</h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="UpdateUser">
    <div align="center">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" placeholder="Email"></td>
            </tr>
            <tr>
                <td>Second name:</td>
                <td><input type="text" name="second_name" placeholder="Second name"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="last_name" placeholder="Last name"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Обновить" name="updateUser"></td>
            </tr>
        </table>
    </div>
</form>

<br>
<br>
<br>
<br>
<div align="center"><h1>Обновление пароля</h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="UpdateUserPassword">
    <div align="center">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" placeholder="email"/></td>
            </tr>
            <tr>
                <td>Старый пароль:</td>
                <td><input type="password" name="old_password" placeholder="Old password"/></td>
            </tr>
            <tr>
                <td>Новый пароль:</td>
                <td><input type="password" name="new_password" placeholder="New password"/></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p>* Пароль должен быть не меньше 6 символов, минимум одна буква и одна цифра</p>
                </i>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Обновить пароль" name="updatePassword"></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
