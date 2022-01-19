<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 30.12.2021
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_catalog_loan_card"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="admin_menu_catalog_loan_card"></fmt:message></h1></div>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_enter_id"></fmt:message></td>
            <td><input type="number" name="loanCardId" min="1" placeholder="<fmt:message key="placeholder_loan_card_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="book_enter_book_id"></fmt:message></td>
            <td><input type="number" name="bookId" min="1" placeholder="<fmt:message key="placeholder_book_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="enter_user_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="placeholder_user_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_by_city"></fmt:message></td>
            <td>
                <select name="city">
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
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_by_status"></fmt:message></td>
            <td>
                <select name="status">
                        <option value="open"><fmt:message key="loan_card_status_opened"></fmt:message></option>
                        <option value="closed"><fmt:message key="loan_card_status_closed"></fmt:message></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_by_city_and_status"></fmt:message></td>
            <td>
                <select name="status">
                    <option value="open"><fmt:message key="loan_card_status_opened"></fmt:message></option>
                    <option value="closed"><fmt:message key="loan_card_status_closed"></fmt:message></option>
                </select>
            </td>
            <td>
                <select name="city">
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
    <input type="hidden" name="command" value="LoanCardCatalog">
    <table>
        <tr>
            <input type="submit" value="<fmt:message key="loan_card_show_all"></fmt:message>" name="getAll">
        </tr>
    </table>
</form>

<div align="center">
    <form>
        <c:if test="${not empty loanCards}">
            <p><fmt:message key="message_count_found_result"></fmt:message><c:out value="${loanCardsSize}"/></p>
        </c:if>

        <c:if test="${not empty loanCards}">
            <table border="1" cellpadding="5">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="loan_card_id"></fmt:message></th>
                    <th><fmt:message key="user_id"></fmt:message></th>
                    <th><fmt:message key="book_id"></fmt:message></th>
                    <th><fmt:message key="book_title"></fmt:message></th>
                    <th><fmt:message key="book_isbn"></fmt:message></th>
                    <th><fmt:message key="loan_card_name_user"></fmt:message></th>
                    <th><fmt:message key="library_city"></fmt:message></th>
                    <th><fmt:message key="loan_card_taking_date"></fmt:message></th>
                    <th><fmt:message key="loan_card_deadline"></fmt:message></th>
                    <th><fmt:message key="loan_card_return"></fmt:message></th>
                    <th><fmt:message key="loan_card_type_use"></fmt:message></th>
                    <th><fmt:message key="status"></fmt:message></th>
                </tr>
                <c:forEach var="loanCards" items="${loanCards}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.index + 1}"></c:out></td>
                        <td><c:out value="${loanCards.loanCardDtoId}"></c:out></td>
                        <td> <a href="?command=UserCatalog&userId=${loanCards.userId}"><c:out value="${loanCards.userId}"></c:out> <br><input type="button" value="<fmt:message key="details"></fmt:message>"></a></td>
                        <td> <a href="?command=CatalogBook&bookId=${loanCards.bookId}"><c:out value="${loanCards.bookId}"></c:out> <br><input type="button" value="<fmt:message key="details"></fmt:message>"></a></td>
                        <td><c:out value="${loanCards.title}"></c:out></td>
                        <td><c:out value="${loanCards.isbn}"></c:out></td>
                        <td><c:out value="${loanCards.secondName} ${loanCards.lastName} "></c:out></td>
                        <td><c:out value="${loanCards.cityLibrary}"></c:out></td>
                        <td><c:out value="${loanCards.takingBook}"></c:out></td>
                        <td><c:out value="${loanCards.deadline}"></c:out></td>
                        <td><c:out value="${loanCards.returnBook}"></c:out></td>
                        <td><c:out value="${loanCards.typeUse}"></c:out></td>
                        <td><c:out value="${loanCards.status}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</div>
</body>
</html>
