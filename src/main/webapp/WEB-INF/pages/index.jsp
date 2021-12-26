<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="Controller" width="400" height="300" align="right" method="POST">
    <input type="hidden" name="command" value="SignIn">
    <p><input type="text" placeholder="Введите E-mail" name="email"></p>
    <p><input type="password" placeholder="Введите пароль" name="password"></p>
    <p><input type="submit" value="Sign In"></p>
  </form>
  <p><div align="right">
    <a href="Controller?command=GoToRegistration"><input type="submit" value="Registration"></a>
  </div></p>
  </body>
</html>
