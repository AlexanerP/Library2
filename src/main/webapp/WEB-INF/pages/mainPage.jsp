<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 22.12.2021
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>


<c:if test="${empty user.role}">
    <form action="Controller" width="400" height="300" align="right" method="POST">
        <input type="hidden" name="command" value="SignIn">
        <p><input type="text" placeholder="Введите E-mail" name="email"></p>
        <p><input type="password" placeholder="Введите пароль" name="password"></p>
        <p><input type="submit" value="Sign In"></p>
    </form>
    <p><div align="right">
    <a href="Controller?command=GoToRegistration"><input type="submit" value="Registration"></a>
</div></p>
</c:if>
<c:if test="${not empty user.role}">
    <p>
        <a href="Controller?command=GoToHome"><input type="submit" value="Личный кабинет"></a>
    </p>
</c:if>
<br><br><br><br>
<form action="Controller">
    <input type="hidden" name="command" value="SearchBooks">
    <table>
        <th>
            <td>Введите название книги</td>
            <td><input type="text" placeholder="Название книги" name="title"></td>
            <td>Введите ISBN книги</td>
            <td><input type="text" placeholder="ISBN" name="isbn"></td>
            <td>Выбрать категорию</td>
            <td><input type="text" placeholder="Genre" name="genre"></td>
        </th>
        <th>
            <input type="submit" value="SearchBook">
        </th>
    </table>
</form>

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
    <c:if test="${user != null}">
        <th>Add to Wishlist</th>
    </c:if>
    </tr>
    <c:forEach var="booksDTO" items="${booksDTO}">
        <tr>
            <td><c:out value="${booksDTO.bookDtoId}"></c:out></td>
            <td><c:out value="${booksDTO.title}"></c:out></td>
            <td>
                <c:forEach var="author" items="${booksDTO.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach>
            </td>
            <td>
<%--                <c:forEach var="genre" items="${booksDTO.genres}">--%>
<%--                    <c:out value="${genre.category}"></c:out>--%>
<%--                </c:forEach>--%>
            </td>
            <td><c:out value="${booksDTO.publisher}"></c:out></td>
            <td><c:out value="${booksDTO.year}"></c:out></td>
            <td><c:out value="${booksDTO.isbn}"></c:out></td>
            <td>
<%--                <c:if test="${booksDTO.borrow < bookDTO.quantity}">--%>
                <a href="?command=GoToOrder&bookId=${booksDTO.bookDtoId}">Order</a>
<%--                <a href="?command=GoToOrder"><input type="submit" value="Request this Book">--%>
<%--                    <c:set var="bookId" scope="request" value="${bookDTO.bookId}"></c:set>--%>
                </a>
<%--            </c:if>--%>
            </td>
            <c:if test="${user != null}">
                <td><a href="?command=GoToWishList&bookId=${booksDTO.bookDtoId}"><input type="submit" value="Request this Book"></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </table>
</c:if>





<br><br><br><br>
<a href="Controller?command=UserCatalog"><input type="submit" value="Catalog user"></a>

<br><br><br><br>
<a href="Controller?command=GoToPrivateRoom"><input type="submit" value="Privaaet room"></a>

<br><br><br><br>
<a href="Controller?command=GoToHello"><input type="submit" value="Catalog"></a>

<br><br><br><br>
<a href="Controller?command=GoToStoryLoanCardUser"><input type="submit" value="Personal card story user"></a>
<br><br><br><br>
<a href="Controller?command=GoToRequestsUser" target="_blank"><input type="submit" value="Requests"></a>
<br><br><br><br>
<a href="Controller?command=GoToRequestsCatalog" target="_blank"><input type="submit" value="Requests Catalog"></a>


</body>
</html>
