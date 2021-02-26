<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <meta charset="UTF-8">
    <style><%@include file="../css/add.css"%></style>
    <title>Robo Parts</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="pageContainer">
    <div class="addContainer">
        <div class="headerContainer">
            <h1>EDIT ITEM</h1>
            <a class="btn btn-dark" href="/app">BACK</a>
        </div>
        <form class="loginForm" method="POST" action="/save">
            <input type="hidden" id="itemId" name="itemId" value="${item.getInventoryItemId()}">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${item.getProduct().getName()}" readonly>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" class="form-control" id="description" name="description" value="${item.getProduct().getDescription()}" readonly>
            </div>
            <div class="form-group">
                <label for="description">Amount</label>
                <input type="text" class="form-control" id="amount" name="amount" value="${item.getAmount()}">
            </div>
            <div class="editButtonContainer">
                <a class="btn btn-danger" href="/delete/${item.getInventoryItemId()}">Delete</a>
                <button type="submit" class="btn btn-dark">Submit</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>