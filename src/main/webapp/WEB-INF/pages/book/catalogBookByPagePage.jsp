<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td><button type="button" name="back" onclick="history.back()">Назад</button></td>
        <td><a href="?command=GoToHome">Домашний кабинет</a></td>
    </tr>
</table>
<div align="center"><h1>Просмотр каталога</h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="ShowCatalogByPage">
    </div>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>ISBN</th>
            <th>Publisher</th>
            <th>Year</th>
            <th>Shelf</th>
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
                    <a href="?command=GoToRequestMaterials&bookId=${bookDTO.bookDtoId}">Заказать</a>
                    </a>
                </c:if></td>
                <td><a href="?command=GoToWishList&bookId=${bookDTO.bookDtoId}">Добавить в избранные</a></td>
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
                <a href="?command=ShowCatalogByPage&pageCatalog=${pageCatalog}&back=back">back</a>
            </th>
            <th colspan="8" align="center">
                <input type="number" name="jumpPage" value="jump" placeholder="Page" min="1"><input type="submit" value="Перейти">
<%--                <a href="?command=ShowCatalogByPage&pageBook=jumpPage&jump=jump">jump</a>--%>
            </th>
            <th align="center">
                <a href="?command=ShowCatalogByPage&pageCatalog=${pageCatalog}&next=next">next</a>
            </th>
        </tr>
        </tfoot>
    </table>
</form>

</body>
</html>
