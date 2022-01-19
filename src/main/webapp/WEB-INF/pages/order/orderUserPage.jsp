<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="user_menu_my_order"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="user_menu_my_order"></fmt:message></h1></div>
<div align="center"><h3><c:out value="${secondName} ${lastName}"></c:out></h3></div>
<div align="center">
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="GoToOrderUser">
        <table border="1" cellpadding="5">
            <tr>
                <th>#</th>
                <th><fmt:message key="id"></fmt:message></th>
                <th><fmt:message key="book_id"></fmt:message></th>
                <th><fmt:message key="book_title"></fmt:message></th>
                <th><fmt:message key="book_isbn"></fmt:message></th>
                <th><fmt:message key="book_year"></fmt:message></th>
                <th><fmt:message key="order_date"></fmt:message></th>
                <th><fmt:message key="library_city"></fmt:message></th>
                <th><fmt:message key="order_comment"></fmt:message></th>
                <th><fmt:message key="status"></fmt:message></th>
                <th><fmt:message key="delete_command"></fmt:message></th>
            </tr>
            <c:forEach var="orders" items="${orders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${orders.orderDtoId}"></c:out></td>
                    <td><c:out value="${orders.bookId}"></c:out></td></td>
                    <td><c:out value="${orders.title}"></c:out></td>
                    <td><c:out value="${orders.isbn}"></c:out></td>
                    <td><c:out value="${orders.year}"></c:out></td>
                    <td><c:out value="${orders.date}"></c:out></td>
                    <td><c:out value="${orders.cityLibrary}"></c:out></td>
                    <td><c:out value="${orders.comment}"></c:out></td>
                    <td><c:out value="${orders.status}"></c:out></td>
                    <td>
                        <c:if test="${orders.status eq 'OPENED' or 'CLOSED'}">
                            <a href="?command=ActionUserOrder&orderUserId=${orders.orderDtoId}&action=remove"><input type="button" value="<fmt:message key="delete_command"></fmt:message>"></a> </td>
                        </c:if>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
