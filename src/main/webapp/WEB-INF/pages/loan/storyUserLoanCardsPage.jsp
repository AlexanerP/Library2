<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 23.12.2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="user_menu_story_book_user"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="user_menu_story_book_user"></fmt:message></h1></div>
<div align="center"><h3><c:out value="${secondName} ${lastName}"></c:out></h3></div>
<br><br><br>
    <div align="center">
        <form>
            <c:if test="${not empty loanCards}">
            <table cellpadding="5">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="book_id"></fmt:message></th>
                    <th><fmt:message key="book_title"></fmt:message></th>
                    <th><fmt:message key="book_isbn"></fmt:message></th>
                    <th><fmt:message key="loan_card_taking_date"></fmt:message></th>
                    <th><fmt:message key="loan_card_deadline"></fmt:message></th>
                    <th><fmt:message key="loan_card_return"></fmt:message></th>
                    <th><fmt:message key="library_city"></fmt:message></th>
                    <th><fmt:message key="loan_card_type_use"></fmt:message></th>
                    <th><fmt:message key="status"></fmt:message></th>
                </tr>
                    <c:forEach var="card" items="${loanCards}" varStatus="status">
                        <tr>
                            <td><c:out value="${status.index + 1}"></c:out></td>
                            <td><c:out value="${card.bookId}"></c:out></td>
                            <td><c:out value="${card.title}"></c:out></td>
                            <td><c:out value="${card.isbn}"></c:out></td>
                            <td><c:out value="${card.takingBook}"></c:out></td>
                            <td><c:out value="${card.returnBook}"></c:out></td>
                            <td><c:out value="${card.deadline}"></c:out></td>
                            <td><c:out value="${card.cityLibrary}"></c:out></td>
                            <td><c:out value="${card.typeUse}"></c:out></td>
                            <td><c:out value="${card.status}"></c:out></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
</body>
</html>
