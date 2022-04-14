<%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 06/04/2022
  Time: 1:27 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Calendar" %>
<html>
<jsp:include page="/views/component/head.jsp"/>
<body>
<jsp:include page="/views/component/jqueryToast.jsp"/>
<body>
<div class="container">
    <a class="btn btn-primary mb-2">Admin dashboard</a>
    <b>Today is: <%= (new java.util.Date()).toLocaleString()%></b>
    <table class="table table-hover table-bordered text-center">
        <thead style="background: azure;">
        <tr>

            <th>
                <span class="staffId">Staff Id</span>
            </th>
            <th></th>
            <th>
                <span class="staffName">Name</span>
            </th>
            <th>
                <span>Timekeeping action</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${staffs}">
            <tr>
                <td>
                    <span class="staffId">${item.id}</span>
                </td>
                <th>
                    <img class="staffImage" src="${item.image}" height="50" width="50" alt="">
                </th>
                <td>
                    <span class="staffName">${item.name}</span>
                </td>
                <td>
                    <form method="post">
                        <button formaction="/practice_2022_04_06_war_exploded/admin/timekeeping/begin?id=${item.id}"
                                class="btn btn-outline-primary mr-5">Begin
                        </button>
                        <button formaction="/practice_2022_04_06_war_exploded/admin/timekeeping/end?id=${item.id}"
                                class="btn btn-outline-primary">End
                        </button>
                    </form>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

</body>
</html>
