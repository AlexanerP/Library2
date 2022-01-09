<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.12.2021
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="title_book_catalog"></fmt:message></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center"><h1><fmt:message key="title_book_catalog"></fmt:message></h1></div>
<table>
        <td>
           <form>
               <input type="hidden" name="command" value="CatalogBook">
               <table>
                   <tr>
                       <td><fmt:message key="book_enter_book_id"></fmt:message></td>
                       <td><input type="number" name="bookId" min="1" placeholder="<fmt:message key="placeholder_book_id"></fmt:message>"></td>
                       <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                   </tr>
               </table>
           </form>
           <form>
               <input type="hidden" name="command" value="CatalogBook">
               <table>
                   <tr>
                       <td><fmt:message key="search_book_by_library"></fmt:message></td>
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
               <input type="hidden" name="command" value="CatalogBook">
               <table>
                   <tbody>
                   <tr>
                       <td><fmt:message key="enter_title"></fmt:message></td>
                       <td><input type="text" name="title" placeholder="<fmt:message key="enter_title"></fmt:message>"></td>
                   </tr>
                   <tr>
                       <td><fmt:message key="enter_isbn"></fmt:message></td>
                       <td><input type="text" name="isbn" placeholder="<fmt:message key="enter_isbn"></fmt:message>"></td>
                   </tr>
                   <tr>
                       <td><fmt:message key="enter_authors"></fmt:message></td>
                       <td><input type="text" name="author" placeholder="<fmt:message key="enter_authors"></fmt:message>"></td>
                   </tr>
                   <tr>
                       <td><fmt:message key="enter_genres"></fmt:message></td>
                       <td><input type="text" name="genre" placeholder="<fmt:message key="enter_genres"></fmt:message>"></td>
                   </tr>
                   </tbody>
                   <tfoot>
                   <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                   <td><input type="reset" value="<fmt:message key="book_reset"></fmt:message>"></td>
                   </tfoot>
               </table>
           </form>
           <form>
               <input type="hidden" name="command" value="CatalogBook">
               <table>
                   <tr>
                       <td><fmt:message key="show_all_book"></fmt:message></td>
                       <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="all"></td>
                   </tr>
               </table>
           </form>
        </td>
        <td>
            <form>
                <input type="hidden" name="command" value="ActionAuthor">
                <table>
                    <tr>
                        <td><fmt:message key="enter_author_id"></fmt:message></td>
                        <td><input type="number" name="authorId" min="1" placeholder="<fmt:message key="enter_author_id"></fmt:message>"></td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                    </tr>
                </table>
            </form>
            <form>
                <input type="hidden" name="command" value="ActionAuthor">
                <table>
                    <tr>
                        <td><fmt:message key="enter_authors"></fmt:message></td>
                        <td><input type="text" name="name" placeholder="<fmt:message key="enter_authors"></fmt:message>"></td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                    </tr>
                </table>
            </form>
            <form>
                <input type="hidden" name="command" value="ActionAuthor">
                <table>
                    <tr>
                        <td><fmt:message key="show_all_author"></fmt:message></td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="all"></td>
                    </tr>
                </table>
            </form>
        </td>
    <td>
        <form>
            <input type="hidden" name="command" value="ActionGenre">
            <table>
                <tr>
                    <td><fmt:message key="enter_genre_id"></fmt:message></td>
                    <td><input type="number" name="genreId" min="1" placeholder="<fmt:message key="enter_genre_id"></fmt:message>"></td>
                    <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                </tr>
            </table>
        </form>
        <form>
            <input type="hidden" name="command" value="ActionGenre">
            <table>
                <tr>
                    <td><fmt:message key="enter_genres"></fmt:message></td>
                    <td><input type="text" name="genre" placeholder="<fmt:message key="enter_genres"></fmt:message>"></td>
                    <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                </tr>
            </table>
        </form>
        <form>
            <input type="hidden" name="command" value="ActionGenre">
            <table>
                <tr>
                    <td><fmt:message key="show_all_genre"></fmt:message></td>
                    <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="all"></td>
                </tr>
            </table>
        </form>
    </td>
</table>

<div align="center">
    <c:if test="${not empty books}">
        <p><fmt:message key="message_count_found_result"></fmt:message> <c:out value="${booksSize}"/></p>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th><fmt:message key="id"></fmt:message></th>
            <th><fmt:message key="book_title"></fmt:message></th>
            <th><fmt:message key="book_author"></fmt:message></th>
            <th><fmt:message key="book_genre"></fmt:message></th>
            <th><fmt:message key="book_isbn"></fmt:message></th>
            <th><fmt:message key="book_publisher"></fmt:message></th>
            <th><fmt:message key="book_year"></fmt:message></th>
            <th><fmt:message key="book_shelf"></fmt:message></th>
            <th><fmt:message key="book_added"></fmt:message></th>
            <th><fmt:message key="library_city"></fmt:message></th>
            <th><fmt:message key="book_quantity"></fmt:message></th>
            <th><fmt:message key="book_borrow"></fmt:message></th>
            <th><fmt:message key="book_description"></fmt:message></th>
            <th><fmt:message key="update_command"></fmt:message></th>
        </tr>
        <c:forEach var="books" items="${books}" varStatus="status">
        <tr>
            <td><c:out value="${status.index + 1}"></c:out></td>
            <td><c:out value="${books.bookDtoId}"></c:out></td>
            <td><c:out value="${books.title}"></c:out></td>
            <td>
                <c:forEach var="author" items="${books.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="genre" items="${books.genres}">
                    <c:out value="${genre.category}"></c:out>
                </c:forEach>
            </td>
            <td><c:out value="${books.isbn}"></c:out></td>
            <td><c:out value="${books.publisher}"></c:out></td>
            <td><c:out value="${books.year}"></c:out></td>
            <td><c:out value="${books.shelf}"></c:out></td>
            <td><c:out value="${books.added}"></c:out></td>
            <td><c:out value="${books.cityLibrary}"></c:out></td>
            <td><c:out value="${books.quantity}"></c:out></td>
            <td><c:out value="${books.borrow}"></c:out></td>
            <td><c:out value="${books.description}"></c:out></td>
            <td>
                <c:if test="${books.bookDtoId != 0}">
                        <p><a href="?command=GoToUpdateBook&bookId=${books.bookDtoId}"><fmt:message key="update_command"></fmt:message></a></p>
                </c:if>
            </td>
            </c:forEach>
            </c:if>
        </tr>
    </table>
</div>
<div align="center">

    <c:if test="${not empty authors}">
    <h2><fmt:message key="title_authors"></fmt:message></h2>
        <p><fmt:message key="message_count_found_result"></fmt:message> ${authorsSize}</p>
        <table border="1" align="center">
            <tr>
                <th>#</th>
                <th><fmt:message key="id"></fmt:message></th>
                <th><fmt:message key="author_name"></fmt:message></th>
                <th><fmt:message key="statistic"></fmt:message></th>
            </tr>
            <tr>
                <c:forEach var="author" items="${authors}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${author.authorId}</td>
                        <td>${author.name}</td>
                        <td>
                            <c:if test="${author.authorId != 0}">
                                <p><a href="?command=GoToStatisticAuthor&authorId=${author.authorId}"><fmt:message key="statistic"></fmt:message></a></p>
                            </c:if>
                        </td>
            </tr>
                </c:forEach>
            </tr>
        </table>
    </c:if>
</div>

<div align="center">

    <c:if test="${not empty statAuthors}">
    <h2><fmt:message key="title_author_statistic"></fmt:message></h2>
    <table border="1" align="center">
        <tr>
            <th><fmt:message key="id"></fmt:message></th>
            <th><fmt:message key="author_name"></fmt:message></th>
            <th><fmt:message key="message_count_books"></fmt:message></th>
        </tr>
        <tr>
                <td>${statAuthors.authorId}</td>
                <td>${statAuthors.name}</td>
                <td>${authorCountBooks}</td>
        </tr>
        </c:if>
    </table>
</div>

<div align="center">

    <c:if test="${not empty genre}">
    <h2><fmt:message key="title_genres"></fmt:message></h2>
    <p><fmt:message key="message_count_found_result"></fmt:message> ${genreSize}</p>
    <table border="1" align="center">
        <tr>
            <th>#</th>
            <th><fmt:message key="id"></fmt:message></th>
            <th><fmt:message key="genre_category"></fmt:message></th>
            <th><fmt:message key="statistic"></fmt:message></th>
        </tr>
        <tr>
            <c:forEach var="genre" items="${genre}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${genre.genreId}</td>
            <td>${genre.category}</td>
            <td>
                <c:if test="${genre.genreId != 0}">
                    <p><a href="?command=GoToStatisticGenre&genreId=${genre.genreId}"><fmt:message key="statistic"></fmt:message></a></p>
                </c:if>
            </td>
    </tr>
            </c:forEach>
        </tr>
    </table>
        </c:if>
</div>

<div align="center">

    <c:if test="${not empty statGenre}">
    <h2><fmt:message key="title_genre_statistic"></fmt:message></h2>
    <table border="1" align="center">
        <tr>
            <th><fmt:message key="id"></fmt:message></th>
            <th><fmt:message key="genre_category"></fmt:message></th>
            <th><fmt:message key="message_count_books"></fmt:message></th>
        </tr>
        <tr>
            <td>${statGenre.genreId}</td>
            <td>${statGenre.category}</td>
            <td>${genreCountBooks}</td>
        </tr>
    </table>
        </c:if>
</div>
</body>
</html>
