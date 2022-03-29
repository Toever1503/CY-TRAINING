<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 23/03/2022
  Time: 1:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>Product</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="p-2">
<a href="/practice_13h/products/new" class="btn btn-outline-primary">Add New</a>
<a href="/practice_13h/products" class="btn btn-outline-primary">Refresh</a>

<h3 class="text-center">Product Management</h3>

<form class="container" action="/practice_13h/products/search">
    <div clas="input-group mt-2 mb-2 d-flex" style="display: flex">
        <button class="input-group-text">Search:</button>
        <input type="text" name="q" value="<%= request.getParameter("q") == null ? "": request.getParameter("q") %>"
               class="form-control" required>
    </div>
</form>
<c:if test="${list.isEmpty()}">
    <p class='alert alert-warning text-center'>Not have any product!</p>
</c:if>

<table class="table table-striped table-hover table-border text-center mt-1 container border">
    <thead>
    <tr>
        <td>Product Name</td>
        <td>Price</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${listProduct}">
        <tr>
            <td>${p.getName()}</td>
            <td>${p.getPrice()}</td>
            <td>
                <form>
                    <input class="d-none" type="number" name="id" value="${p.getId()}">
                    <button formmethod="get" class="btn btn-outline-primary" formaction="/practice_13h/products/update"
                            type="submit">
                        Update
                    </button>
                    <button formmethod="post" class="btn btn-outline-danger" formaction="/practice_13h/products/delete"
                            type="submit">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<c:if test="${type != null}">
    <c:if test="${type == 'error'}">
        <p class='bg-error'>${message}</p>
    </c:if>
    <c:if test="${type == 'success'}">
        <p class='bg-success'>${message}</p>
    </c:if>
</c:if>
</body>
</html>
