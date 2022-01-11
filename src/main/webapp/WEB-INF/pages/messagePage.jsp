<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="message_page"></fmt:message></title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<div align="center">
    <h1><fmt:message key="message_page"></fmt:message></h1>
    <h2>
        <c:if test="${not empty sessionScope.successfulMessage}">
            <c:out value="${sessionScope.successfulMessage}" />
                <fmt:message key="successful_message"></fmt:message>
            <c:remove var="successfulMessage" scope="session" />
        </c:if>
    </h2>
</div>
<div align="center">
    <h2>
        <c:if test="${not empty negativeMessage}">
            <c:out value="${sessionScope.negativeMessage}" />
                <fmt:message key="negative_message"></fmt:message>
            <c:remove var="negativeMessage" scope="session" />
        </c:if>
    </h2>
</div>

</body>
</html>
