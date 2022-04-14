package com.practice_2022_04_06.controller.admin;

import com.practice_2022_04_06.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/admin/position/*" }, loadOnStartup = 0)
public class PositionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getContextPath());
        switch (lastPath) {
            case "staff":
                break;
            case "new":
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                break;
        }
    }
    protected void showStaffDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void showAddStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void showEditStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getContextPath());
        switch (lastPath) {
            case "new":
                break;
            case "edit":
                break;
            default:
                break;
        }
    }
}
