package com.lap2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getInput")
public class GetinPutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        writer.write("<form action=\"getInput\">\n" +
//                "        <label for=\"Name\">Enter your name: </label>\n" +
//                "        <input type=\"text\" name=\"name\" id=\"name\">\n" +
//                "        <button>Send</button>\n" +
//                "    </form>");
//        if(!req.getParameter("name").isEmpty())
//            resp.getWriter().write("<br><hr> Hello, "+ req.getParameter("name") + " , welcome to servlet!");
        req.getRequestDispatcher("/view/test.jsp").forward(req, resp);
    }
}
