<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product List</title>
    <%@ include file="../../bootstrap-css.html" %>
    <script src="${pageContext.request.contextPath}/js/products-client.js"></script>
</head>
<body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-secondary" id="logout" role="button" href="${pageContext.request.contextPath}/logout">Log out</a>
    </sec:authorize>
    <a type="button" class="btn btn-dark float-right"
       href="${pageContext.request.contextPath}/client/shopping-cart">Cart</a>
    <hr>
    <%@ include file="../products/products.html" %>
</div>
</body>
<%@ include file="../../bootstrap-js.html" %>
</html>
