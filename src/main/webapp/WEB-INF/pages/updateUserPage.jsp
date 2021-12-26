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
<h1>Update User</h1>
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
                <td colspan="2" align="center"><input type="submit" value="Push" name="updateUser"></td>
            </tr>
        </table>
    </div>
</form>

<br>
<br>
<br>
<br>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="UpdateUser">
    <div align="center">
        <table>
            <tr>
                <td>Old password:</td>
                <td><input type="password" name="password" placeholder="Old password"/></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password" placeholder="New password"/></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p>* Пароль должен быть не меньше 6 символов, минимум одна буква и одна цифра</p>
                </i>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Push" name="updatePassword"></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
