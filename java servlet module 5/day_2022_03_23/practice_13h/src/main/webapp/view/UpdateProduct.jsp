<%@ page import="com.entiry.Product" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<html>
<head>
    <title>Update Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<%
    Product p = (Product) request.getAttribute("productUpdate");
    if (p == null)
        p = new Product();

%>
<form method="post" style="width: 500px; margin: 0 auto">
    <h3 class="text-center">Update product</h3>
    <input class="d-none" type="number" name="id" id="id" value="<%= p.getId()%>">

    <div clas="input-group mt-2 mb-2">
        <label class="input-group-text">Product name:</label>
        <input type="text" name="name" class="form-control" required value="<%= p.getName()%>">
    </div>

    <div clas="input-group">
        <label class="input-group-text">Price:</label>
        <input type="number" name="price" class="form-control" required value="<%= p.getPrice()%>">
    </div>

    <div>
        <button formaction="/practice_13h/products/update"
                class="btn btn-outline-primary" type="submit">Update
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
