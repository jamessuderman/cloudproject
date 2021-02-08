<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <meta charset="UTF-8">
    <style><%@include file="../css/login.css"%></style>
    <title>Login</title>
</head>
<body>

    <div class="pageContainer">
        <div class="loginContainer">
            <div class="logoContainer">
                <div class="logo">
                    <i class="fa fa-cog fa-5x" style="color:white;" aria-hidden="true"></i>
                </div>
                <span class="appName">ROBO PARTS</span>
            </div>
            <div class="formContainer">
                <form class="loginForm" method="POST" action="/app">
                    <div class="form-group">
                        <label for="username">username</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group">
                        <label for="password">password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-dark">Submit</button>
                </form>
                <c:if test="${invalidCreds}">
                    <div class="alert alert-danger" role="alert">
                        Invalid Credentials
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</body>
</html>