<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="admin_menu_issuing_books"></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center"><h1><fmt:message key="admin_menu_issuing_books"></fmt:message></h1></div>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <td><fmt:message key="order_enter_id"></fmt:message></td>
            <td><input type="number" name="orderId" min="1" placeholder="<fmt:message key="order_enter_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <td><fmt:message key="order_show_by_city"></fmt:message></td>
            <td>
                <select name="library">
                    <c:forEach var="libraries" items="${libraries}">
                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <td><fmt:message key="order_show_by_city_and_status"></fmt:message></td>
            <td>
                <select name="library">
                    <c:forEach var="libraries" items="${libraries}">
                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="status">
                    <option value="opened"><fmt:message key="order_status_opened"></fmt:message>"></option>
                    <option value="approved"><fmt:message key="order_status_approved"></fmt:message>"></option>
                    <option value="rejected"><fmt:message key="order_status_rejected"></fmt:message>"></option>
                    <option value="arrived"><fmt:message key="order_status_arrived"></fmt:message>"></option>
                    <option value="closed"><fmt:message key="order_status_closed"></fmt:message>"></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="GiveOutBookUser">
    <table>
        <tr>
            <input type="submit" value="<fmt:message key="order_show_all_approved"></fmt:message>" name="getAll">
        </tr>
    </table>
</form>

<div align="center">
    <form>
        <c:if test="${not empty orders}">
            <p><fmt:message key="message_count_found_result"></fmt:message> <c:out value="${ordersSize}"/></p>
        </c:if>

        <c:if test="${not empty orders}">
            <table border="1" cellpadding="5">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="id"></fmt:message></th>
                    <th><fmt:message key="enter_user_id"></fmt:message></th>
                    <th><fmt:message key="order_id_admin"></fmt:message></th>
                    <th><fmt:message key="book_id"></fmt:message></th>
                    <th><fmt:message key="book_title"></fmt:message></th>
                    <th><fmt:message key="book_isbn"></fmt:message></th>
                    <th><fmt:message key="book_year"></fmt:message></th>
                    <th><fmt:message key="order_date"></fmt:message></th>
                    <th><fmt:message key="library_city"></fmt:message></th>
                    <th><fmt:message key="order_comment"></fmt:message></th>
                    <th><fmt:message key="status"></fmt:message></th>
                    <th><fmt:message key="order_issuing_book"></fmt:message></th>
                </tr>
                <c:forEach var="orders" items="${orders}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.index + 1}"></c:out></td>
                        <td><c:out value="${orders.orderDtoId}"></c:out></td>
                        <td> <a href="?command=UserCatalog&userId=${orders.userId}"><c:out value="${orders.userId}"></c:out> </a></td>
                        <td> <a href="?command=UserCatalog&userId=${orders.adminId}"><c:out value="${orders.adminId}"></c:out> </a></td>
                        <td> <a href="?command=CatalogBook&bookId=${orders.bookId}"><c:out value="${orders.bookId}"></c:out> </a></td>
                        <td><c:out value="${orders.title}"></c:out></td>
                        <td><c:out value="${orders.isbn}"></c:out></td>
                        <td><c:out value="${orders.year}"></c:out></td>
                        <td><c:out value="${orders.date}"></c:out></td>
                        <td><c:out value="${orders.cityLibrary}"></c:out></td>
                        <td><c:out value="${orders.comment}"></c:out></td>
                        <td><c:out value="${orders.status}"></c:out></td>
                        <td>
                            <div>
                                <p><a href="?command=ActionGiveOutBook&orderId=${orders.orderDtoId}&type_use=take_home"><fmt:message key="order_issuing_home"></fmt:message>"></a></p>
                                <p><a href="?command=ActionGiveOutBook&orderId=${orders.orderDtoId}&type_use=read_room"><fmt:message key="order_issuing_room"></fmt:message>"></a></p>
                            </div>
                        </td>
                    </tr>
                    <tr></tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</div>
</body>
</html>
