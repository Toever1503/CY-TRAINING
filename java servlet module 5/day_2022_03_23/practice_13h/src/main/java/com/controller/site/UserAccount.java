package com.controller.site;

import com.dao.UserDao;
import com.entity.User;
import com.util.Func;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "userAccount", value = {"/login", "/register"}, loadOnStartup = 1)
public class UserAccount extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("here");
        switch (Func.getLastPath(req.getRequestURI())) {
            case "login":
                req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
                break;
            case "register":
                req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Login processing");
        switch (Func.getLastPath(req.getRequestURI())) {
            case "login":
                loginProcessing(req, resp);
                break;
            case "register":
                registerProcessing(req, resp);
                break;
            default:
                break;
        }
    }

    protected void loginProcessing(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.findByUserName(req.getParameter("username"));

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            req.setAttribute("result", "success");
            req.setAttribute("message", "Login success");

            HttpSession session =  req.getSession();
            session.setAttribute("userLogin", user);

            long time = Calendar.getInstance().getTimeInMillis();
            System.out.println("Time last login =>"+ time);
            System.out.println("Session id =>"+ session.getId());

            session.setAttribute("lastLogin", time);
            session.setMaxInactiveInterval(30);

            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            req.getSession().getAttributeNames();
            user = new User();
            user.setUsername(username);
            user.setPassword(password);

            req.setAttribute("userInvalid", user);

            req.setAttribute("result", "error");
            req.setAttribute("message", "Username or Password is incorrect");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    protected void registerProcessing(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        System.out.println("Register processing");
    }
}
