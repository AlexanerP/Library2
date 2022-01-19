<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.12.2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="user_menu_my_wish_list"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="user_menu_my_wish_list"></fmt:message></h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="GoToOrder">
    </div>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th><fmt:message key="book_id"></fmt:message></th>
            <th><fmt:message key="book_title"></fmt:message></th>
            <th><fmt:message key="book_isbn"></fmt:message></th>
            <th><fmt:message key="book_publisher"></fmt:message></th>
            <th><fmt:message key="book_year"></fmt:message></th>
            <th><fmt:message key="book_shelf"></fmt:message></th>
            <th><fmt:message key="order_command"></fmt:message></th>
            <th><fmt:message key="delete_command"></fmt:message></th>
        </tr>
        <c:forEach var="wishBook" items="${books}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"></c:out></td>
                <td><c:out value="${wishBook.bookId}"></c:out></td>
                <td><c:out value="${wishBook.title}"></c:out></td>
                <td><c:out value="${wishBook.isbn}"></c:out></td>
                <td><c:out value="${wishBook.publisher}"></c:out></td>
                <td><c:out value="${wishBook.year}"></c:out></td>
                <td><c:out value="${wishBook.shelf}"></c:out></td>

                <td><c:if test="${wishBook.borrow < wishBook.quantity and user.status != 'BLOCKED'}">
                    <a href="?command=GoToOrder&bookId=${wishBook.bookId}"><input type="button" value="<fmt:message key="order_command"></fmt:message>"></a>
                </c:if></td>
                <td><a href="?command=ActionWishBook&wish_book_id=${wishBook.wishBooksId}&delete=delete"><input type="button" value="<fmt:message key="delete_command"></fmt:message>"></a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
