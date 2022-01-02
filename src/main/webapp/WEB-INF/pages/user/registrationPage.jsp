<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registartion</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
    </tr>
</table>
<div align="center"><h1>Регистрация</h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="Registration">
    <div align="center">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p>* Пароль должен быть не меньше 6 символов, минимум одна буква и одна цифра</p>
                </i>
                </td>
            </tr>
            <tr>
                <td>Second name:</td>
                <td><input type="text" name="second_name"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="last_name"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Push"></td>
            </tr>
        </table>
    </div>
</form>
<div align="center">
    <c:out value="${requestScope.message}"></c:out>
</div>
</body>
</html>
