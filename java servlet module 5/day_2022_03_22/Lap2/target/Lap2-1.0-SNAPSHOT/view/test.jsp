<%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 23/03/2022
  Time: 9:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    This is test jsp file
<%
    out.println("hello world");
    pageContext.include("test-part.jsp", true);
    out.println(config.getServletName());

    Object s = this;
    out.println(s.getClass());


%>
</body>
</html>
