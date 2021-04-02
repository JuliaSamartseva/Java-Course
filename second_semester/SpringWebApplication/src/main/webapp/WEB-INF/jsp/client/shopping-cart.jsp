<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product Cart</title>
    <%@ include file="../../bootstrap-css.html" %>
    <script src="${pageContext.request.contextPath}/js/display-cart.js"></script>
</head>
<body>
<div class="container" id="container">
    <br>
    <h3 class="text-center">Products View</h3>
    <hr>
    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/client/home">Home</a>
    <sec:authorize access="isAuthenticated()">
        <a class="btn btn-secondary" id="logout" role="button" href="${pageContext.request.contextPath}/logout">Log out</a>
    </sec:authorize>
    <hr>
    <table id="product-cart" class="table table-striped">
        <thead class="thead-dark">
        <th>
            Product name
        </th>
        <th>
            Quantity
        </th>
        <th>
            Full price
        </th>
        <th>
            Actions
        </th>
        </thead>
    </table>

    <p id="total-price">Total price = 0</p>
    <a type="button" id="place-order" class="btn btn-success">Place order</a>
</div>
</body>
<%@ include file="../../bootstrap-js.html" %>
</html>