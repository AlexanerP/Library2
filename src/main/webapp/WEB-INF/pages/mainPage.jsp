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
        <p><input type="submit" value="Войти"></p>
    </form>
    <p><div align="right">
    <a href="Controller?command=GoToRegistration"><input type="submit" value="Регистрация"></a>
</div></p>
</c:if>
<c:if test="${not empty user.role}">
    <p>
        <a href="Controller?command=GoToHome"><input type="submit" value="Личный кабинет"></a>
    </p>
    <form width="400" height="300" align="right">
        <input type="hidden" name="command" value="Выйти">
        <p><input type="submit" value="Выйти"></p>
    </form>
</c:if>
<br><br><br><br>
<div align="center">
    <form action="Controller">
        <input type="hidden" name="command" value="SearchBooks">
        <table>
            <%--        <tbody>--%>
            <tr>
                <td>Введите название книги</td>
                <td><input type="text" placeholder="Название книги" name="title"></td>
                <td>Введите ISBN книги</td>
                <td><input type="text" placeholder="ISBN" name="isbn"></td>
            </tr>
            <tr>
                <td>Введите категорию</td>
                <td><input type="text" placeholder="Жанр" name="genre"></td>
                <td>Введите автора</td>
                <td><input type="text" placeholder="Автор" name="author"></td>
            </tr>
            <tr>
                <%--            <input type="submit" value="Поиск">--%>
                <%--            <input type="reset" value="Сбросить">--%>
            </tr>
            <%--        <tr>--%>
            <%--            <td>Введите категорию</td>--%>
            <%--            <td><input type="text" placeholder="Жанр" name="genre"></td>--%>
            <%--            <td>Введите автора</td>--%>
            <%--            <td><input type="text" placeholder="Автор" name="author"></td>--%>
            <%--        </tr>--%>
            <%--        </tbody>--%>
            <%--        <tfoot>--%>
            <%--            <input type="submit" value="Поиск">--%>
            <%--            <input type="reset" value="Сбросить">--%>
            <%--        </tfoot>--%>
        </table>
        <input type="submit" value="Поиск">
        <input type="reset" value="Сбросить">
    </form>
</div>

<table border="1" align="center">
  <div align="center">
      <c:if test="${not empty booksDTO}">
        <p>Количество найденных результатов - <c:out value="${booksDTOSize}"/></p>
      </c:if>
  </div>
<c:if test="${not empty booksDTO}">
    <tr>
    <th>#</th>
    <th>ID</th>
    <th>Название</th>
    <th>Автор</th>
    <th>ISBN</th>
    <th>Жанр</th>
    <th>Издательство</th>
    <th>Год</th>
    <c:if test="${not empty user}">
            <th>Заказать</th>
                <th>Добавить в избранные</th>
            </c:if>
            </tr>
                 <c:forEach var="booksDTO" items="${booksDTO}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.index + 1}"></c:out></td>
                        <td><c:out value="${booksDTO.bookDtoId}"></c:out></td>
                        <td><c:out value="${booksDTO.title}"></c:out></td>
                        <td>
                            <c:forEach var="author" items="${booksDTO.authors}">
                                <c:out value="${author.name}"></c:out>
                            </c:forEach>
                        </td>
                        <td><c:out value="${booksDTO.isbn}"></c:out></td>
                        <td>
                            <c:forEach var="genre" items="${booksDTO.genres}">
                                <c:out value="${genre.category}"></c:out>
                            </c:forEach>
                        </td>
                        <td><c:out value="${booksDTO.publisher}"></c:out></td>
                        <td><c:out value="${booksDTO.year}"></c:out></td>
                        <c:if test="${not empty user}">
                        <td>
            <%--                <c:if test="${booksDTO.borrow < bookDTO.quantity}">--%>
                            <a href="?command=GoToOrder&bookId=${booksDTO.bookDtoId}">Заказать</a>
            <%--                <a href="?command=GoToOrder"><input type="submit" value="Request this Book">--%>
            <%--                    <c:set var="bookId" scope="request" value="${bookDTO.bookId}"></c:set>--%>
                            </a>
            <%--            </c:if>--%>
                        </td>
                            <td><a href="?command=ActionWishBook&bookId=${booksDTO.bookDtoId}">В избранные</a></td>
                        </c:if>
                    </tr>
    </c:forEach>
    </table>
</c:if>
<table>
    <em>Наши представительства</em>
    <c:if test="${not empty libraries}">
        <c:forEach var="library" items="${libraries}">
            <tr>
                <td><c:out value="${library.city}"></c:out></td>
                <td><c:out value="${library.street}"></c:out></td>
            </tr>
        </c:forEach>
    </c:if>
</table




<%--<br><br><br><br>--%>
<%--<a href="Controller?command=UserCatalog"><input type="submit" value="Catalog user"></a>--%>

<%--<br><br><br><br>--%>
<%--<a href="Controller?command=GoToPrivateRoom"><input type="submit" value="Privaaet room"></a>--%>

<%--<br><br><br><br>--%>
<%--<a href="Controller?command=GoToHello"><input type="submit" value="Catalog"></a>--%>

<%--<br><br><br><br>--%>
<%--<a href="Controller?command=GoToStoryLoanCardUser"><input type="submit" value="Personal card story user"></a>--%>
<%--<br><br><br><br>--%>
<%--<a href="Controller?command=GoToRequestsUser" target="_blank"><input type="submit" value="Requests"></a>--%>
<%--<br><br><br><br>--%>
<%--<a href="Controller?command=GoToRequestsCatalog" target="_blank"><input type="submit" value="Requests Catalog"></a>--%>


</body>
</html>
