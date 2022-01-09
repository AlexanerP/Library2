<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.12.2021
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="update_user_data"></fmt:message></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center"><h1><fmt:message key="update_user_data"></fmt:message></h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="UpdateUser">
    <div align="center">
        <table>
            <tr>
                <td><fmt:message key="user_email"></fmt:message></td>
                <td><input type="text" name="email" placeholder="<fmt:message key="user_email"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="user_second_name"></fmt:message></td>
                <td><input type="text" name="second_name" placeholder="<fmt:message key="user_second_name"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="user_last_name"></fmt:message></td>
                <td><input type="text" name="last_name" placeholder="<fmt:message key="user_last_name"></fmt:message>"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="<fmt:message key="button_update"></fmt:message>" name="updateUser"></td>
            </tr>
        </table>
    </div>
</form>

<br>
<br>
<br>
<br>
<div align="center"><h1><fmt:message key="update_user_password"></fmt:message></h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="UpdateUserPassword">
    <div align="center">
        <table>
            <tr>
                <td><fmt:message key="user_email"></fmt:message></td>
                <td><input type="text" name="email" placeholder="<fmt:message key="user_email"></fmt:message>"/></td>
            </tr>
            <tr>
                <td><fmt:message key="old_password"></fmt:message></td>
                <td><input type="password" name="old_password" placeholder="<fmt:message key="old_password"></fmt:message>"/></td>
            </tr>
            <tr>
                <td><fmt:message key="new_password"></fmt:message></td>
                <td><input type="password" name="new_password" placeholder="<fmt:message key="new_password"></fmt:message>"/></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p><fmt:message key="message_for_registration"></fmt:message></p>
                </i>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="<fmt:message key="update_command"></fmt:message>" name="updatePassword"></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
