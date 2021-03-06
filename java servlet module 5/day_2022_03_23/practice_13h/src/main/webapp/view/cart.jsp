<%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 25/03/2022
  Time: 10:12 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Cart</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="http://localhost:8080/practice_13h/static/css/cart.css">
    <link rel="stylesheet" href="http://localhost:8080/practice_13h/static/css/style.css">
</head>
<body>
<header style="height: 60px; background-color: darkturquoise;">
    <nav class="navbar navbar-expand-sm navbar-dark container h-100">
        <div class="logo col-3 h-100 bg-white">
            <img src="https://pngimg.com/uploads/renault/renault_PNG38.png" alt="">
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offset-2 collapse navbar-collapse h-100" id="collapsibleNavbar">
            <ul id="mainMenu" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">New Product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About Us</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Account</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/login.html">Login</a></li>
                        <li><a class="dropdown-item" href="#">Logout</a></li>
                        <li><a class="dropdown-item" href="#">Forgot Password</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/practice_13h/my-cart">My cart</a>
                </li>
                <li class="nav-item">
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Enter title product...">
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="mainBody container">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/practice_13h">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">My Cart</li>
        </ol>
    </nav>
    <div class="cart_content">
        <table class="table table-hover table-bordered text-center">
            <thead style="background: azure;">
            <tr>
                <th></th>
                <th>
                    <span class="title">Product name</span>
                </th>
                <th>
                    <span class="price">Price</span>
                </th>
                <th>
                    <span class="quantity">Quantity</span>
                </th>
                <th>
                    <span>Action</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cart}">
                <tr>
                    <td>
                        <span class="image" style="height: 130px; width: 100px; display: block;margin: 0 auto;"><img src="${item.getImage()}" height="30px" width="30px" alt=""></span>
                    </td>
                    <td>
                        <span class="title">${item.getName()}</span>
                    </td>
                    <td>
                        <span class="price">${item.getPrice()}$</span>
                    </td>
                    <td>
                        <span class="quantity">..</span>
                    </td>
                    <td>
                        <a href="/practice_13h/my-cart/delete?id=${item.getId()}" class="btn btn-outline-primary">Delete</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<footer style="background-color: #0dcaf0;">
    <h3 class="text-center p-2" style="color: cornsilk ;">Powered by CY</h3>
</footer>
</body>
</html>
