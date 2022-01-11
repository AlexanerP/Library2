<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 22.12.2021
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="main_page"></fmt:message></title>

</head>
<body>
<div align="center">
    <table>
        <tr>
            <td> <a href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
                <a href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a>
            </td>
        </tr>
    </table>
</div>
            <c:if test="${empty user.role}">
    <form action="Controller" width="400" height="300" align="right" method="POST">
        <input type="hidden" name="command" value="SignIn">
        <p><input type="text" placeholder="<fmt:message key="enter_email"></fmt:message>" name="email"></p>
        <p><input type="password" placeholder="<fmt:message key="enter_password"></fmt:message>" name="password"></p>
        <p><input type="submit" value="<fmt:message key="login_sign_in"></fmt:message>"></p>
    </form>
    <p><div align="right">
    <a href="Controller?command=Registration"><input type="submit" value="<fmt:message key="registration"></fmt:message>"></a>
</div></p>
</c:if>
<c:if test="${not empty user.role}">
    <p>
        <a href="Controller?command=GoToHome"><input type="submit" value="<fmt:message key="private_room"></fmt:message>"></a>
    </p>
    <form width="400" height="300" align="right">
        <input type="hidden" name="command" value="SignOut">
        <p><input type="submit" value="<fmt:message key="login_sign_out"></fmt:message>"></p>
    </form>
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
        <input type="submit" value="<fmt:message key="book_search"></fmt:message>">
        <input type="reset" value="<fmt:message key="book_reset"></fmt:message>">
    </form>
</div>

<table border="1" align="center">
  <div align="center">
      <c:if test="${not empty booksDTO}">
        <p><fmt:message key="message_count_found_result"></fmt:message><c:out value="${booksDTOSize}"/></p>
      </c:if>
  </div>
<c:if test="${not empty booksDTO}">
    <tr>
        <th>#</th>
        <th><fmt:message key="book_id"></fmt:message></th>
        <th><fmt:message key="book_title"></fmt:message></th>
        <th><fmt:message key="book_isbn"></fmt:message></th>
        <th><fmt:message key="book_author"></fmt:message></th>
        <th><fmt:message key="book_genre"></fmt:message></th>
        <th><fmt:message key="book_publisher"></fmt:message></th>
        <th><fmt:message key="book_year"></fmt:message></th>
        <c:if test="${not empty user}">
                <th><fmt:message key="order_book"></fmt:message></th>
                    <th><fmt:message key="add_to_wish_list"></fmt:message></th>
                </c:if>
    </tr>
        <c:forEach var="booksDTO" items="${booksDTO}" varStatus="status">
            <tr>
                <td><c:out value="${status.index + 1}"></c:out></td>
                <td><c:out value="${booksDTO.bookDtoId}"></c:out></td>
                <td><c:out value="${booksDTO.title}"></c:out></td>
                <td><c:out value="${booksDTO.isbn}"></c:out></td>
                <td>
                    <c:forEach var="author" items="${booksDTO.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="genre" items="${booksDTO.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
                <td><c:out value="${booksDTO.publisher}"></c:out></td>
                <td><c:out value="${booksDTO.year}"></c:out></td>
                <c:if test="${not empty user}">
                    <td>
                        <c:if test="${user.status != 'BLOCKED'}">
                            <a href="?command=GoToOrder&bookId=${booksDTO.bookDtoId}"><fmt:message key="order_command"></fmt:message></a>
                        </c:if>
                    </td>
                        <td><a href="?command=ActionWishBook&bookId=${booksDTO.bookDtoId}&add=add"><fmt:message key="wish_command"></fmt:message></a></td>
                </c:if>
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
