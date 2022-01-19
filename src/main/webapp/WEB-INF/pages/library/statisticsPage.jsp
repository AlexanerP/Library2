<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 26.12.2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_statistic"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br>
<div align="center"><h1><fmt:message key="admin_menu_statistic"></fmt:message></h1></div>
<div align="center">
    <table>
        <tr>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th><fmt:message key="statistic_user"></fmt:message></th>
                    </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_user_count"></fmt:message></td>
                        <td>${countUsers}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_user_count_active"></fmt:message></td>
                        <td>${countUsersActive}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_user_count_blocked"></fmt:message></td>
                        <td>${countUsersBlocked}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_user_count_count_delete"></fmt:message></td>
                        <td>${countUsersDelete}</td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td>Количество новых пользователей в этом месяце</td>--%>
<%--                        <td>-</td>--%>
<%--&lt;%&ndash;                        <td>${countNewUsersMonth}</td>&ndash;%&gt;--%>
<%--                    </tr>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <tr>--%>
<%--                            <td>Количество новых пользователей в этом году</td>--%>
<%--                            <td>-</td>--%>
<%--&lt;%&ndash;                            <td>${countNewUsersYear}</td>&ndash;%&gt;--%>
<%--                        </tr>--%>
                </table>
            </td>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th><fmt:message key="statistic_book"></fmt:message></th>
                    </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_book_count"></fmt:message></td>
                        <td>${countBooks}</td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td>Количество новых книг в этом месяце</td>--%>
<%--                        <td>-</td>--%>
<%--&lt;%&ndash;                        <td>${countNewBookMonth}</td>&ndash;%&gt;--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td>Количество новых книг в этом году</td>--%>
<%--                        <td>${countNewBookYear}</td>--%>
<%--                    </tr>--%>
<%--                    </td>--%>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th><fmt:message key="statistic_loan_card"></fmt:message></th>
                    </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_loan_card_count_all_time"></fmt:message></td>
                        <td>${countLoanCard}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_loan_card_count_active"></fmt:message></td>
                        <td>${countLoanCardOpen}</td>
                    </tr>
                </table>
            </td>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th><fmt:message key="statistic_orders"></fmt:message></th>
                    </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_orders_count_open"></fmt:message></td>
                        <td>${ordersOpen}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_orders_count_approved"></fmt:message></td>
                        <td>${ordersApproved}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_orders_count_rejected"></fmt:message></td>
                        <td>${ordersRejected}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_orders_count_arrived"></fmt:message></td>
                        <td>${ordersArrived}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="statistic_orders_count_closed"></fmt:message></td>
                        <td>${ordersClosed}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <thead>
                    <tr>
                        <th><fmt:message key="statistic_authors"></fmt:message></th>
                    </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_author_count"></fmt:message></td>
                        <td>${countAuthor}</td>
                    </tr>
                </table>
            </td>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th><fmt:message key="statistic_genres"></fmt:message></th>
                        </tr>
                    </thead>
                    <tr>
                        <td><fmt:message key="statistic_genres_count"></fmt:message></td>
                        <td>${countGenre}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
