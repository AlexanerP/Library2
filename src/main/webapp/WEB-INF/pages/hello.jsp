<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 16.12.2021
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form action="Controller">
    <input type="hidden" name="command" value="Hello">
    </div>
    <table border="1" align="center">
        <c:if test="${booksDTO != null}">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Genre</th>
            <th>Publisher</th>
            <th>year</th>
            <th>Request this Item</th>
            <th>Add to Wishlist</th>
        </tr>
        <c:forEach var="bookDTO" items="${bookDtos}">
            <tr>
                <td><c:out value="${bookDTO.bookId}"></c:out></td>
                <td><c:out value="${bookDTO.title}"></c:out></td>
                <td><c:out value="${bookDTO.authors}"></c:out></td>
                <td><c:out value="${bookDTO.genres}"></c:out></td>
                <td><c:out value="${bookDTO.publisher}"></c:out></td>
                <td><c:out value="${bookDTO.year}"></c:out></td>
                <td><c:out value="${bookDTO.isbn}"></c:out></td>
                <td><c:if test="${bookDTO.borrow < bookDTO.quantity}">
                    <a href="?command=GoToRequestMaterials"><input type="submit" value="Request this Book">
                    <c:set var="bookId" scope="request" value="${bookDTO.bookId}"></c:set>
                    </a>
                </c:if></td>
                <td><a href="?command=GoToWishList"><input type="submit" value="Request this Book"></a></td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="9">
                <p align="center">"Page"</p>
            </th>
        </tr>
    <tfoot>
    <tr>
    <th align="center">
    <input type="submit" value="back page" name="back">
    </th>
    <th colspan="7" align="center">
    <input type="number" name="jump"><input type="submit" value="Перейти">
    </th>
    <th align="center">
    <input type="submit" value="next page" name="next"/>
    </th>
    </tr>
    </tfoot>
    </table>
    </c:if>
</form>

    <a href="Controller?command=GoToRequestMaterials"><input type="submit" value="Send"></a>

</body>
</html>
