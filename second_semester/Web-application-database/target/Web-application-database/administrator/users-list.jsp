<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product List</title>
    <%@ include file="/bootstrap-css.html" %>
    <script src="scripts/display-users.js"></script>
</head>
<body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <a type="button" class="btn btn-primary" href="http://localhost:8080/Web_application_database_war/administrator/home.jsp">Home</a>
    <a class="btn btn-secondary" href="http://localhost:8080/Web_application_database_war" role="button">Log out</a>
    <hr>
    <table id="users" class="table table-striped">
        <thead class="thead-dark">
        <th>
            User name
        </th>
        <th>
            Actions
        </th>
        </thead>
    </table>
</div>
</body>
<%@ include file="/bootstrap-js.html" %>
</html>