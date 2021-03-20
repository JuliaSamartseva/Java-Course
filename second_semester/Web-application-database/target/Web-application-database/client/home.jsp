<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product List</title>
    <%@ include file="/bootstrap-css.html" %>
    <script src="../products/scripts/products-client.js"></script>
</head>
<body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <a class="btn btn-secondary" href="http://localhost:8080/Web_application_database_war" role="button">Log out</a>
    <a type="button" class="btn btn-dark float-right" href="http://localhost:8080/Web_application_database_war/products/">Cart</a>
    <hr>
    <%@ include file = "../products/products.html" %>
</div>
</body>
<%@ include file="/bootstrap-js.html" %>
</html>