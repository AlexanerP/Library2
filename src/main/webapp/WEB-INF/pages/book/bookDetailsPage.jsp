<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 24.12.2021
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Детали книги</title>
    <button type="button" name="back" onclick="history.back()">Назад</button>
    <br><br><br>
    <div align="center"><h1>Описание книги</h1></div>
  <div align="center">
      <table>
          <c:if test="${book != null}">
              <tr>
                  <td>Book ID</td>
                  <td><c:out value="${book.bookDtoId}"></c:out></td>
              </tr>
              <tr>
                  <td>Title</td>
                  <td><c:out value="${book.title}"></c:out></td>
              </tr>
              <tr>
                  <td>ISBN</td>
                  <td><c:out value="${book.isbn}"></c:out></td>
              </tr>
                  <td>Publisher</td>
                  <td><c:out value="${book.publisher}"></c:out></td>
              <tr>
                  <td>Year</td>
                  <td><c:out value="${book.year}"></c:out></td>
              </tr>
              <tr>
                  <td>Authors</td>
                  <td>
                      <c:forEach var="author" items="${book.authors}">
                          <c:out value="${author.name}"></c:out>
                      </c:forEach>
                  </td>
              </tr>
              <tr>
                  <td>Genres</td>
                  <td>
                      <c:forEach var="genre" items="${book.genres}">
                          <c:out value="${genre.category}"></c:out>
                      </c:forEach>
                  </td>
              </tr>
              <tr>
                  <td>City library</td>
                  <td><c:out value="${book.cityLibrary}"></c:out></td>
              </tr>
              <tr>
                  <td>Shelf</td>
                  <td><c:out value="${book.shelf}"></c:out></td>
              </tr>
              <tr>
                  <td>Added</td>
                  <td><c:out value="${book.added}"></c:out></td>
              </tr>
              <tr>
                  <td>Quantity</td>
                  <td><c:out value="${book.quantity}"></c:out></td>
              </tr>
              <tr>
                  <td>Borrow</td>
                  <td><c:out value="${book.borrow}"></c:out></td>
              </tr>
              <tr>
                  <td>Description</td>
                  <td><c:out value="${book.description}"></c:out></td>
              </tr>
          </c:if>
      </table>
  </div>
</head>
<body>

</body>
</html>
