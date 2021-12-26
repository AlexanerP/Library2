<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <h1>Loan cards User</h1>
    <button type="button" name="back" onclick="history.back()">back</button>
    <form action="Controller">
        <input type="hidden" name="command" value="GoToLoanCardUser">

        <c:if test="${personalCard == null}">Null</c:if>

        <c:if test="${personalCard != null}">
            <table>
                <tr>
                    <td>Book ID</td>
                    <td>Title</td>
                    <td>ISBN</td>
                    <td>Taking bookDTO</td>
                    <td>Deadline bookDTO</td>
                    <td>Return bookDTO</td>
                    <td>Type use</td>
                    <td>Status</td>
                    <td>Change status</td>
                </tr>
                <c:forEach var="card" items="${personalCard}">
                    <tr>
                        <td><c:out value="${card.bookId}"></c:out></td>
                        <td><c:out value="${card.title}"></c:out></td>
                        <td><c:out value="${card.isbn}"></c:out></td>
                        <td><c:out value="${card.takingBook}"></c:out></td>
                        <td><c:out value="${card.returnBook}"></c:out></td>
                        <td><c:out value="${card.deadline}"></c:out></td>
                        <td><c:out value="${card.typeUse}"></c:out></td>
                        <td><c:out value="${card.status}"></c:out></td>
                        <td>
                            <input type="submit" value="${statusOpen}">
                            <input type="submit" value="${statusClosed}">
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </form>
</head>
<body>

</body>
</html>
