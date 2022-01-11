<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.12.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br><br>
<table>
    <tr>
        <td><fmt:message key="message_count_new_order"></fmt:message></td>
        <td>${countOrders}</td>
    </tr>
</table>
<div align="center">
    <table border="1" cellpadding="5">
        <c:if test="${not empty user.role and user.role eq 'MANAGER'}">
            <p>
                <tr>
                    <td><fmt:message key="admin_menu_action_with-admin"></fmt:message></td>
                    <td><a href="Controller?command=ManagerCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
                </tr>
                <tr>
                    <td><fmt:message key="admin_menu_libraries"></fmt:message></td>
                    <td><a href="Controller?command=LibraryCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
                </tr>
            </p>
        </c:if>
        <tr>
            <td><fmt:message key="admin_menu_issuing_books"></fmt:message></td>
            <td><a href="Controller?command=GiveOutBookUser"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_return_books"></fmt:message></td>
            <td><a href="Controller?command=ReturnBookCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_books"></fmt:message></td>
            <td><a href="Controller?command=CatalogBook"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_loan_card"></fmt:message></td>
            <td><a href="Controller?command=LoanCardCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_user"></fmt:message></td>
            <td><a href="Controller?command=UserCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_add_book"></fmt:message></td>
            <td><a href="Controller?command=CreateBook"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_order"></fmt:message></td>
            <td><a href="Controller?command=OrderCatalog"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_statistic"></fmt:message></td>
            <td><a href="Controller?command=GoToStatistics"><input type="submit" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
    </table>
</div>
</body>
</html>
