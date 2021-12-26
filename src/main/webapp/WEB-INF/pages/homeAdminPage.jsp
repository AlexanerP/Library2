<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.12.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">Назад</button>
<br><br><br>
<table>
    <tr>
        <td>Количество новых заказов</td>
        <td>${countOrders}</td>
    </tr>
</table>
<div align="center">
    <table border="1" cellpadding="5">
        <c:if test="${not empty user.role and user.role eq 'MANAGER'}">
            <p>
                <tr>
                    <td>Страница Директора</td>
                    <td><a href="Controller?command=UserCatalog"><input type="submit" value="Catalog user"></a></td>
                </tr>
            </p>
        </c:if>
        <tr>
            <td>Поиск книги</td>
            <td><a href="Controller?command=GoToSearchBooks"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Каталог пользователей</td>
            <td><a href="Controller?command=UserCatalog"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Добавить книгу</td>
            <td><a href="Controller?command=GoToAddBook"><input type="submit" value="Перейти"></a></td>
        </tr>
        <tr>
            <td>Каталог заказов</td>
            <td><a href="Controller?command=GoToOrdersCatalog" target="_blank"><input type="submit" value="Requests Catalog"></a></td>
        </tr>
<%--        <tr>--%>
<%--            <td>Каталог пользователей</td>--%>
<%--            <td><a href="user/userCatalogPage.jsp"><input type="submit" value="Catalog user"></a></td>--%>
<%--        </tr>--%>
        <tr>
            <td>Статистика</td>
            <td><a href="Controller?command=GoToStatistics"><input type="submit" value="Catalog user"></a></td>
        </tr>
    </table>
</div>
</body>
</html>
