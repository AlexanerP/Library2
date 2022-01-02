<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="Controller?command=GoToMainPage"><input type="submit" value="На главную страницу"></a>
</p>
<div align="center"><h1>Welcome ${user.secondName}</h1></div>
<br><br>
<div align="center">
    <table border="1" cellpadding="5">
        <c:if test="${not empty user.role and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
            <p>
                <tr>
                    <td>Страница администратора</td>
                    <td><a href="Controller?command=AdminPage"><input type="submit" value="Перейти"></a></td>
                </tr>
            </p>
        </c:if>
        <tr>
            <td>Просмотр каталога</td>
            <td><a href="Controller?command=GoToCatalog"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Поиск книги</td>
            <td><a href="index.jsp"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>История моих книг</td>
            <td><a href="Controller?command=GoToStoryLoanCardUser&userId=${user.userId}"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Обновить пользователя</td>
            <td><a href="Controller?command=GoToUpdateUser"><input type="submit" value="Перейти"></a></td>
        </tr>
<%--        <tr>--%>
<%--            <td>Работа с пользователями</td>--%>
<%--            <td><a href="Controller?command=GoToWorkWithUsers"><input type="submit" value="Перейти"></a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Добавить книгу</td>--%>
<%--            <td><a href="Controller?command=GoToAddBook"><input type="submit" value="Перейти"></a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Каталог запросов материала</td>--%>
<%--            <td><a href="Controller?command=GoToRequestsCatalog" target="_blank"><input type="submit" value="Requests Catalog"></a></td>--%>
<%--        </tr>--%>
        <tr>
            <td>Мои запросы</td>
            <td><a href="Controller?command=GoToOrderUser&userId=${user.userId}"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Мои избранные книги</td>
            <td><a href="Controller?command=GoToWishBooksUserPage"><input type="submit" value="Перейти"></a></td>
        </tr>
<%--        <tr>--%>
<%--            <td>Personal card</td>--%>
<%--            <td><a href="Controller?command=GoToPersonalCard"><input type="submit" value="Personal card"></a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Catalog User</td>--%>
<%--            <td><a href="Controller?command=UserCatalog"><input type="submit" value="Catalog user"></a></td>--%>
<%--        </tr>--%>
    </table>
</div>
</body>
</html>
