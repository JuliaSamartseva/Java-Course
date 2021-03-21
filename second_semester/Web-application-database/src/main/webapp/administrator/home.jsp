<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product List</title>
    <%@ include file="/bootstrap-css.html" %>
    <script src="../products/scripts/products-administrator.js"></script>
</head>
<body>
<%
    String userName = null;
    String type = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) userName = cookie.getValue();
            else if (cookie.getName().equals("type")) type = cookie.getValue();
        }
    }
    if (userName == null || !type.equals("administrator")) response.sendRedirect("http://localhost:8080/Web_application_database_war/login.jsp");
%>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <a type="button" class="btn btn-primary" href="http://localhost:8080/Web_application_database_war/administrator/users-list.jsp">Users</a>
    <a class="btn btn-secondary" href="http://localhost:8080/Web_application_database_war" role="button">Log out</a>
    <a type="button" class="btn btn-dark float-right" href="http://localhost:8080/Web_application_database_war/products/product-manager.jsp">Add product</a>
    <hr>
    <%@ include file = "../products/products.html" %>
</div>
</body>
<%@ include file="/bootstrap-js.html" %>
</html>