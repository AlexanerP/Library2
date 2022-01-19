<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 22.12.2021
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="main_page"></fmt:message></title>
    <jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">

<br><br>

<c:if test="${user.role eq 'GUEST'}">
    <form action="Controller" width="400" height="300" align="right" method="POST">
        <input type="hidden" name="command" value="SignIn">
        <p><input type="text" placeholder="<fmt:message key="enter_email"></fmt:message>" name="email"></p>
        <p><input type="password" placeholder="<fmt:message key="enter_password"></fmt:message>" name="password"></p>
        <p><input class="button" type="submit" value="<fmt:message key="login_sign_in"></fmt:message>"></p>
    </form>
    <p><div align="right">
    <a class="button" href="Controller?command=Registration"><fmt:message key="registration"></fmt:message></a>
</div></p>
</c:if>
<table>
    <tr>
        <td><fmt:message key="message_count_book_library"></fmt:message></td>
        <td>${countBooks}</td>
    </tr>
    <tr>
        <td><fmt:message key="message_count_author_library"></fmt:message></td>
        <td>${countAuthors}</td>
    </tr>
    <tr>
        <td><fmt:message key="message_count_genre_library"></fmt:message></td>
        <td>${countGenres}</td>
    </tr>
</table>
<br><br>

<div align="center">
    <form action="Controller">
        <input type="hidden" name="command" value="SearchBooks">
        <table>
            <%--        <tbody>--%>
            <tr>
                <td><fmt:message key="book_enter_title"></fmt:message></td>
                <td><input type="text" placeholder="<fmt:message key="book_enter_title"></fmt:message>" name="title"></td>
                <td><fmt:message key="book_enter_isbn"></fmt:message></td>
                <td><input type="text" placeholder="<fmt:message key="book_enter_isbn"></fmt:message>" name="isbn"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_enter_genre"></fmt:message></td>
                <td><input type="text" placeholder="<fmt:message key="book_enter_genre"></fmt:message>" name="genre"></td>
                <td><fmt:message key="book_enter_author"></fmt:message></td>
                <td><input type="text" placeholder="<fmt:message key="book_enter_author"></fmt:message>" name="author"></td>
            </tr>
        </table>
        <input class="button" type="submit" value="<fmt:message key="book_search"></fmt:message>">
        <input class="button" type="reset" value="<fmt:message key="book_reset"></fmt:message>">
    </form>
</div>

<table >
    <div align="center">
        <c:if test="${not empty books}">
            <b><fmt:message key="message_count_found_result"></fmt:message><c:out value="${booksSize}"/></b>
        </c:if>
    </div>
    <c:if test="${not empty books}">
    <c:forEach var="booksDTO" items="${books}" varStatus="status">
        <tr class="tr">
        <tr>
            <td>
                <h3><b><c:out value="${booksDTO.title}"></c:out>  (<c:out value="${booksDTO.year}"></c:out>)  <c:forEach var="author" items="${booksDTO.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach></b></h3>
            </td>
        </tr>
        <tr>
        <tr>
            <td>
                <b>#</b> <c:out value="${status.index + 1}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <strong><fmt:message key="book_id"></fmt:message></strong> - <c:out value="${booksDTO.bookDtoId}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <strong><fmt:message key="book_isbn"></fmt:message></strong> - <c:out value="${booksDTO.isbn}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_author"></fmt:message></b>

                <c:forEach var="author" items="${booksDTO.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_genre"></fmt:message></b>

                <c:forEach var="genre" items="${booksDTO.genres}">
                    <c:out value="${genre.category}"></c:out>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_publisher"></fmt:message></b> -
                <c:out value="${booksDTO.publisher}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_year"></fmt:message></b>
                <c:out value="${booksDTO.year}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_description"></fmt:message></b>
                <c:out value="${booksDTO.description}"></c:out>
            </td>

        </tr>
        <tr>
            <div align="center">
                <c:if test="${user.role != 'GUEST'}">
                    <td>
                        <c:if test="${user.status != 'BLOCKED'}">
                            <a class="button" href="?command=GoToOrder&bookId=${booksDTO.bookDtoId}"><fmt:message key="order_command"></fmt:message></a>
                        </c:if>
                        <a class="button" href="?command=ActionWishBook&bookId=${booksDTO.bookDtoId}&add=add"><fmt:message key="wish_command"></fmt:message></a>
                    </td>
                </c:if>
            </div>
        </tr>
        </tr>
    </c:forEach>
</table>
</c:if>
<table>
    <em><fmt:message key="our_library"></fmt:message></em>
    <c:if test="${not empty libraries}">
        <c:forEach var="library" items="${libraries}">
            <tr>
                <td><c:out value="${library.city}"></c:out> <c:out value="${library.street}"></c:out></td>
            </tr>
        </c:forEach>
    </c:if>
</table
</body>
</html>
