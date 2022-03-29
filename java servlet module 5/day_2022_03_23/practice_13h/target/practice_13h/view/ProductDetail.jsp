<%@ page import="com.entiry.Product" %><%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 24/03/2022
  Time: 1:42 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<% Product product = (Product) request.getAttribute("product"); %>
<div class="productDetail container row mt-2">
    <div class="productDetail__img col-4 h-100 border">
        <img src="<%= product.getImage()%>" alt="">
    </div>
    <div class="productDetail__info col-8">
        <h1 class="text-center"><%= product.getName()%></h1>
        <p><b>Description:</b>
            <span><%= product.getDescription()%>f</span>
        </p>
        <p><b>Price:</b>
            <span><%= product.getPrice()%>$</span>
            <br>
            <a href="" class="btn btn-outline-primary p-1 mt-2">Add Cart</a>
    </div>
</div>
<style>
    .productDetail {
        height: 300px;

        margin: 0 auto;
        box-shadow: 1px 1px 1px;
    }

    img {
        height: 100%;
        width: 100%;
    }

    .productDetail .productDetail__info {
        border: 1px solid gainsboro;
    }
</style>
</body>

</html>
