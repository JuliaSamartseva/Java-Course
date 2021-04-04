<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blocked page</title>
    <%@ include file="../../bootstrap-css.html" %>
</head>
<body>
<div class="container" id="container">
    <br>
    <div class="alert alert-danger" role="alert">
        You have been blocked. Home page is not accessible.
    </div>
    <hr>
    <sec:authorize access="isAuthenticated()">
    <a class="btn btn-secondary" id="logout" role="button" href="${pageContext.request.contextPath}/logout">Log out</a>
    </sec:authorize>
</div>
</body>
<%@ include file="../../bootstrap-js.html" %>
</html>
