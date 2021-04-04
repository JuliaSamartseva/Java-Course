<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <%@ include file="../bootstrap-css.html" %>
</head>

<body>
<div>
    <div class="container align-content-center">
        <br>
        <h3 class="text-center">Registration Form</h3>
        <hr>

        <div class="card bg-light mx-auto" style="max-width: 600px;">
            <article class="card-body mx-auto" style="max-width: 400px;">
                <h3 class="text-center">Create Account</h3>
                <br>
                <form:form method="POST" modelAttribute="userForm">
                    <div class="form-group">
                        <label for="name">User Name:</label>
                        <form:input type="text"
                                    id="name"
                                    path="name"
                                    placeholder="Username"
                                    autofocus="true"
                                    class="form-control col"/>
                        <form:errors path="name"/>
                            ${usernameError}
                    </div>
                    <div class="form-group">
                        <label for="type">
                            Please select the role:
                        </label>
                        <select name="type" id="type" class="form-control w-auto">
                            <option value="client" id="client">Client</option>
                            <option value="administrator" id="administrator">Administrator</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <form:input id="password"
                                    type="password"
                                    path="password"
                                    placeholder="Password"
                                    class="form-control col"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary col">Register</button>
                    </div>
                    <p class="text-center">Have an account? <a href="${pageContext.request.contextPath}/login">Log
                        In</a></p>
                </form:form>
            </article>
        </div>
    </div>
</div>
</body>
<%@ include file="../bootstrap-js.html" %>
</html>