<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Shoping</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.css">

    <script src="js/index.js"></script>
</head>

<body class="position-relative">
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
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Enter title product...">
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="mainBody" style="background-color: seashell;">
    <!-- Carousel -->
    <div id="demo" class="carousel slide" data-bs-ride="carousel">
        <!-- Indicators/dots -->
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
            <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
            <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
        </div>

        <!-- The slideshow/carousel -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://imgaz1.chiccdn.com/os/202202/20220221234136_155.jpg" alt="Los Angeles"
                     class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="https://imgaz1.chiccdn.com/nc/myos_newchic_affiliate_activity/202202/20220221214735_229.jpg"
                     alt="Chicago" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="https://imgaz1.chiccdn.com/os/202202/20220222003716_232.jpg" alt="New York"
                     class="d-block w-100">
            </div>
        </div>

        <!-- Left and right controls/icons -->
        <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
            <span class="carousel-control-next-icon"></span>
        </button>
    </div>

    <div>
        <div class="text-center mt-2"><b style="font-size: 25px; color: darkturquoise;">Recommend for you</b></div>
        <div class="listProducts p-4">
            <div class="listItems row">
                <c:forEach var="product" items="${latestProduct}">
                    <div class="card col-2 p-1 shadow">
                        <img class="card-img-top"
                             src="${product.Image}"
                             alt="">
                        <div class="card-body p-0">
                            <div class="card-text text-center" style="font-size: 12px;">${product.getName}
                            </div>
                            <div class="price w-100">
                                <strike class="float-left">$${product.OldPrice}</strike>
                                <b class="text-danger" style="float: right;">$${product.Price}</b>
                            </div>
                        </div>
                        <div class="card-footer w-100">
                            <div class="btn btn btn-outline-primary">Buy Now</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<div id="detailProduct" style="background: #00000029; display: none;">
    <div class="productInfor col-8 row">
        <div class="productInfoLeft col-5 p-3 shadow h-100">
            <img src="https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/DA/9E/707a04e4-865b-4f08-baf9-5d7ac23d1c28.jpg.webp?s=360x480"
                 alt="">
        </div>
        <div class="productInfoRight col-7 p-3">
            <h3 class="text-center mt-3">
                Button Lapel Solid Casual Shirt
            </h3>
            <div class="price">
                <strike>$777</strike>
                <b class="text-danger">$599</b>
            </div>
            <div>
                <p><b>COLOR:</b></p>
                <span class="badge bg-primary">Primary</span>
                <span class="badge bg-secondary">Secondary</span>
                <span class="badge bg-success">Green</span>
            </div>

            <div>
                <p><b>Desciption:</b></p>
                <table class="table table-striped border">
                    <tbody>
                    <tr>
                        <td>Color:</td>
                        <td>Red</td>
                    </tr>
                    <tr>
                        <td>Material:</td>
                        <td>Polyester</td>
                    </tr>
                    <tr>
                        <td>Occasion:</td>
                        <td>Occasion</td>
                    </tr>
                    <tr>
                        <td>Style:</td>
                        <td>Occasion</td>
                    </tr>
                    <tr>
                        <td>Sleeves Length:</td>
                        <td>Long Sleeve </td>
                    </tr>
                    <tr>
                        <td>Collar:</td>
                        <td>V-neck </td>
                    </tr>
                    <tr>
                        <td>Season:</td>
                        <td>Spring </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="action">
                <button class="btn btn-outline-primary">Buy Now</button>
                <button class="btn btn-outline-primary">Add to your Cart</button>
            </div>
        </div>
        <button id="closeBtn" class="btn-outline-primary btn-close" aria-label="Close"></button>
    </div>
</div>

<footer style="background-color: #0dcaf0;">
    <h3 class="text-center p-2" style="color: cornsilk ;">Powered by CY</h3>
</footer>
</body>

</html>