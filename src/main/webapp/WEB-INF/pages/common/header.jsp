<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 08.01.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title>Header</title>

    <div align="center">
        <table>
            <tr>
                <td> <a href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
                    <a href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a></td>
                <td>                            </td>
                <td><button type="button" name="back" onclick="history.back()"><fmt:message key="back"></fmt:message></button></td>
                <td><a href="Controller?command=GoToMainPage"><input type="submit" value="<fmt:message key="main_page"></fmt:message>"></a></td>
                <c:if test="${not empty user}">
                    <td><a href="?command=GoToHome"><input type="submit" value="<fmt:message key="private_room"></fmt:message>"></a></td>
                    <td>
                        <c:if test="${not empty user.role and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
                            <td><a href="?command=GoToAdminPage"><input type="submit" value="<fmt:message key="admin_room"></fmt:message>"></a></td>
                        </c:if>
                    </td>
                    <td><button type="button" name="back" onclick="history.forward()"><fmt:message key="next"></fmt:message></button></td>
                    <td>                            </td>
                    <td>
                        <form width="400" height="300" align="right">
                            <input type="hidden" name="command" value="SignOut">
                            <p><input type="submit" value="<fmt:message key="login_sign_out"></fmt:message>"></p>
                        </form>
                    </td>
                </c:if>
            </tr>
        </table>
    </div>
</head>
</body>
</html>
