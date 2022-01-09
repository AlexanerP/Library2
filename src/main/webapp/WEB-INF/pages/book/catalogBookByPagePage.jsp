<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title_book_by_page_catalog"></fmt:message></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center"><h1><fmt:message key="title_book_by_page_catalog"></fmt:message></h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="CatalogBookByPage">
    </div>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th><fmt:message key="book_title"></fmt:message></th>
            <th><fmt:message key="book_author"></fmt:message></th>
            <th><fmt:message key="book_genre"></fmt:message></th>
            <th><fmt:message key="book_isbn"></fmt:message></th>
            <th><fmt:message key="book_publisher"></fmt:message></th>
            <th><fmt:message key="book_year"></fmt:message></th>
            <th><fmt:message key="book_shelf"></fmt:message></th>
            <th>Order this Item</th>
            <th>Add to Wishlist</th>
        </tr>
        <c:forEach var="bookDTO" items="${bookList}">
            <tr>
                <td><c:out value="${bookDTO.bookDtoId}"></c:out></td>
                <td><c:out value="${bookDTO.title}"></c:out></td>
                <td>
                    <c:forEach var="author" items="${bookDTO.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="genre" items="${bookDTO.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
                <td><c:out value="${bookDTO.isbn}"></c:out></td>
                <td><c:out value="${bookDTO.publisher}"></c:out></td>
                <td><c:out value="${bookDTO.year}"></c:out></td>
                <td><c:out value="${bookDTO.shelf}"></c:out></td>

                <td><c:if test="${bookDTO.borrow < bookDTO.quantity}">
                    <a href="?command=GoToRequestMaterials&bookId=${bookDTO.bookDtoId}"><fmt:message key="order_command"></fmt:message></a>
                    </a>
                </c:if></td>
                <td><a href="?command=GoToWishList&bookId=${bookDTO.bookDtoId}"><fmt:message key="add_to_wish_list"></fmt:message></a></td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="10">
                <p align="center">"Page"</p>
                <c:out value="${pageCatalog}"></c:out>
            </th>
        </tr>
        <tfoot>
        <tr>
            <th align="center">
                <a href="?command=CatalogBookByPage&pageCatalog=${pageCatalog}&back=back"><fmt:message key="back"></fmt:message></a>
            </th>
            <th colspan="8" align="center">
                <input type="number" name="jumpPage" value="jump" placeholder="<fmt:message key="page"></fmt:message>" min="1"><input type="submit" value="<fmt:message key="button_go"></fmt:message>">
            </th>
            <th align="center">
                <a href="?command=CatalogBookByPage&pageCatalog=${pageCatalog}&next=next"><fmt:message key="next"></fmt:message></a>
            </th>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
