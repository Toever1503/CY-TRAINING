<%@ page import="com.entiry.Product" %>
<%@ page import="com.dao.ProductDao" %><%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 23/03/2022
  Time: 2:33 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<form class="border p-2" method="post" style="width: 600px; margin: 0 auto" enctype = "multipart/form-data">
    <h3 class="text-center">Add product</h3>
    <input class="d-none" type="number" name="id" id="id">

    <div class="input-group mb-3">
        <span class="input-group-text">Product name:</span>
        <input type="text" name="name" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Price:</span>
        <input type="number" name="price" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Old price:</span>
        <input type="number" name="old_price" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Description:</span>
        <input type="text" name="description" class="form-control">
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text">Image:</span>
        <input type="file" name="image" class="form-control">
        <img style="height: 100px; width: 120px;" src="" alt="">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Color:</span>
        <input type="text" name="color" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Material:</span>
        <input type="text" name="material" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Style:</span>
        <input type="text" name="style" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Season:</span>
        <input type="text" name="season" class="form-control">
    </div>

    <div class="input-group mb-3">
        <span class="input-group-text">Year:</span>
        <input type="text" name="year" class="form-control">
    </div>

    <div>
        <button formaction="/practice_13h/products/new"
                class="btn btn-outline-primary" type="submit">Add new
        </button>
        <a href="/practice_13h/products"
                class="btn btn-outline-primary" type="submit">Back
        </a>
    </div>
    <%
        String type = (String) request.getAttribute("type");
        if (type != null)
            switch (type) {
                case "error":
                    out.println("<p class='bg-error'>" + request.getAttribute("message") + "</p>");
                    break;
                case "success":
                    out.println("<p class='bg-success'>" + request.getAttribute("message") + "</p>");
                    break;
                default:
                    break;
            }
    %>
</form>
</body>
</html>
