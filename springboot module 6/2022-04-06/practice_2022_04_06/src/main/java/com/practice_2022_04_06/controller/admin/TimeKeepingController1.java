package com.practice_2022_04_06.controller.admin;

import com.practice_2022_04_06.dao.impl.StaffDao;
import com.practice_2022_04_06.dao.impl.TimekeepingDao;
import com.practice_2022_04_06.model.Page;
import com.practice_2022_04_06.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Map;

@WebServlet("/admin/timekeeping/*")
public class TimeKeepingController1 extends HttpServlet {
    private final StaffDao staffDao;
    private final TimekeepingDao timekeepingDao;

    public TimeKeepingController1() {
        this.staffDao = new StaffDao();
        this.timekeepingDao = new TimekeepingDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        System.out.println("Last path: " + lastPath);
        switch (lastPath) {
            case "timekeeping":
                showTimekeepingDashboard(req, resp);
                break;
            case "begin":
                handleBeginWork(req, resp);
                break;
            case "end":
                handleEndWork(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        System.out.println("Last path: " + lastPath);

        Map<String, String[]> parameters = req.getParameterMap();
        Page page = new Page();
        try {
            BeanUtils.populate(page, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (page.getPage() == null)
            page.setPage(0);
        req.setAttribute("staffs", staffDao.findAll(page.getPage(), 10, page.getBy(), page.getDirection()));
        switch (lastPath) {
            case "begin":
                handleBeginWork(req, resp);
                break;
            case "end":
                handleEndWork(req, resp);
                break;
            default:
                break;
        }
    }

    protected void showTimekeepingDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        Page page = new Page();
        try {
            BeanUtils.populate(page, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (page.getPage() == null)
            page.setPage(0);

        req.setAttribute("staffs", staffDao.findAll(page.getPage(), 10, page.getBy(), page.getDirection()));
        req.setAttribute("pageTitle", "Timekeeping");
        req.getRequestDispatcher("/views/admin/timekeeping/timekeepings.jsp").forward(req, resp);
    }

    protected void handleBeginWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Timekeeping");
        String toastType = "error";
        Long staffId = Long.parseLong(req.getParameter("id"));

        Calendar calendar = Calendar.getInstance();
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);

        System.out.println("Begin work+ "+ staffId );
        System.out.println("Current time" + currentTime);

        if ((currentTime >= 7 && currentTime <= 8) | (currentTime >= 11 && currentTime <= 13)) {
            if (timekeepingDao.beginWork(staffId)) {
                toastType = "success";
                req.setAttribute("message", "Begin work successfully!");
            } else req.setAttribute("message", "Begin work failed!");
        } else
            req.setAttribute("message", "Begin work failed!");

        req.setAttribute("toastType", toastType);
        req.getRequestDispatcher("/views/admin/timekeeping/timekeepings.jsp").forward(req, resp);
    }

    protected void handleEndWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Timekeeping");
        String toastType = "error";
        Long staffId = Long.parseLong(req.getParameter("id"));

        Calendar calendar = Calendar.getInstance();
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);

        System.out.println("End work+" + staffId);
        System.out.println("Current time" + currentTime);

        if (currentTime >= 18) {
            if (timekeepingDao.endWork(staffId)) {
                toastType = "success";
                req.setAttribute("message", "End work successfully!");
            } else req.setAttribute("message", "End work successfully!");
        } else
            req.setAttribute("message", "End work failed!");
        req.setAttribute("toastType", toastType);
        req.getRequestDispatcher("/views/admin/timekeeping/timekeepings.jsp").forward(req, resp);
    }
}
