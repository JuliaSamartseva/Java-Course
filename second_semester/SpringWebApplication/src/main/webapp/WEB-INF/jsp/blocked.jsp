<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blocked</title>
    <%@ include file="../bootstrap-css.html" %>
</head>
<body>

</body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Users View</h3>
    <hr>
    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-secondary" id="logout" role="button" href="${pageContext.request.contextPath}/logout">Log out</a>
    </sec:authorize>
    <hr>
</div>
</html>
