<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Users List</title>
    <%@ include file="../../bootstrap-css.html" %>
    <script src="scripts/display-users.js"></script>
</head>
<body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <a type="button" class="btn btn-primary"
       href="${pageContext.request.contextPath}/home">Home</a>
    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-secondary" id="logout" role="button" href="${pageContext.request.contextPath}/logout">Log out</a>
    </sec:authorize>
    <hr>
    <table id="users" class="table table-striped">
        <thead class="thead-dark">
        <th>ID</th>
        <th>UserName</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<%@ include file="../../bootstrap-js.html" %>
</html>