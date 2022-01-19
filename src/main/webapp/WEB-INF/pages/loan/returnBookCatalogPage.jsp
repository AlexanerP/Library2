<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_return_books"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="admin_menu_return_books"></fmt:message></h1></div>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_enter_id"></fmt:message></td>
            <td><input type="number" name="loanCardId" min="1" placeholder="<fmt:message key="placeholder_loan_card_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="enter_user_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="placeholder_user_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="book_enter_book_id"></fmt:message></td>
            <td><input type="number" name="bookId" min="1" placeholder="<fmt:message key="placeholder_book_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_by_city"></fmt:message></td>
            <td>
                <select name="city">
                    <c:forEach var="library" items="${libraries}">
                        <option value="${library.city}"><c:out value="${library.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <input type="submit" value="<fmt:message key="loan_card_show_all_open_positions"></fmt:message>" name="getAll">
        </tr>
    </table>
</form>
<div align="center">
    <form>
        <c:if test="${not empty loanCards}">
            <p><fmt:message key="message_count_found_result"></fmt:message><c:out value="${loanCardSize}"/></p>
        </c:if>
        <c:if test="${not empty loanCards}">
        <table border="1" cellpadding="5">
            <tr>
                <th>#</th>
                <th><fmt:message key="id"></fmt:message></th>
                <th><fmt:message key="book_id"></fmt:message></th>
                <th><fmt:message key="user_id"></fmt:message></th>
                <th><fmt:message key="book_id"></fmt:message></th>
                <th><fmt:message key="book_isbn"></fmt:message></th>
                <th><fmt:message key="loan_card_taking_date"></fmt:message></th>
                <th><fmt:message key="loan_card_deadline"></fmt:message></th>
                <th><fmt:message key="loan_card_return"></fmt:message></th>
                <th><fmt:message key="library_city"></fmt:message></th>
                <th><fmt:message key="loan_card_type_use"></fmt:message></th>
                <th><fmt:message key="status"></fmt:message></th>
                <th><fmt:message key="return_book"></fmt:message></th>
            </tr>
            <c:forEach var="card" items="${loanCards}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${card.loanCardDtoId}"></c:out></td>
                    <td>
                        <a href="?command=CatalogBook&bookId=${card.bookId}"><c:out value="${card.bookId}"></c:out></a>
                        <p>
                            <input type="button" value="<fmt:message key="details"></fmt:message>">
                        </p>
                    </td>
                    <td>
                        <a href="?command=UserCatalog&userId=${card.userId}"><c:out value="${card.userId}"></c:out></a>
                        <input type="button" value="<fmt:message key="details"></fmt:message>">
                    </td>
                    <td><c:out value="${card.title}"></c:out></td>
                    <td><c:out value="${card.isbn}"></c:out></td>
                    <td><c:out value="${card.takingBook}"></c:out></td>
                    <td><c:out value="${card.returnBook}"></c:out></td>
                    <td><c:out value="${card.deadline}"></c:out></td>
                    <td><c:out value="${card.cityLibrary}"></c:out></td>
                    <td><c:out value="${card.typeUse}"></c:out></td>
                    <td><c:out value="${card.status}"></c:out></td>
                    <td>
                        <c:if test="${card.loanCardDtoId > 0}">
                            <a href="?command=ActionReturnBook&loanCardId=${card.loanCardDtoId}"><input type="button" value="<fmt:message key="return_book_command"></fmt:message>"></a>
                            <a href="?command=ActionReturnBook&loanCardId=${card.loanCardDtoId}&violation=yes"><input type="button" value="<fmt:message key="book_return_with_violation"></fmt:message>"></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
