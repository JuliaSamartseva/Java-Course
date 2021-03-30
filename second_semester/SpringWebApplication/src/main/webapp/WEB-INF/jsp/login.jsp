<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <%@ include file="../bootstrap-css.html" %>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Login Form</h3>
        <hr>
        <div class="card bg-light mx-auto" style="max-width: 600px;">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h4 class="card-title mt-3 text-center">Log into your account</h4>
                <br>
                <form method="POST" action="/login">
                    <div class="form-group">
                        <label for="username">User Name</label>
                        <input name="username"
                               type="text"
                               id="username"
                               placeholder="Username"
                               autofocus="true"
                               class="form-control col" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input name="password"
                               type="password"
                               id="password"
                               placeholder="Password"
                               class="form-control col" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary col">Log In</button>
                    </div>
                    <p class="text-center">Don't have an account? <a href="${pageContext.request.contextPath}/registration">Register</a> </p>
                </form>
            </article>
        </div>
    </div>
</div>
</body>
<%@ include file="../bootstrap-js.html" %>
</html>