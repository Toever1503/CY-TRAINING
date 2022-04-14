package com.practice_2022_04_06.controller.admin;

import com.practice_2022_04_06.dao.impl.DepartmentDao;
import com.practice_2022_04_06.dao.impl.PositionDao;
import com.practice_2022_04_06.dao.impl.StaffDao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Staff;
import com.practice_2022_04_06.model.Page;
import com.practice_2022_04_06.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(value = {"/admin/staffs/*"}, loadOnStartup = 0)
@MultipartConfig
public class StaffController extends HttpServlet {
    private final DepartmentDao departmentDao;
    private final StaffDao staffDao;
    private final PositionDao positionDao;

    public StaffController() {
        this.departmentDao = new DepartmentDao();
        this.staffDao = new StaffDao();
        this.positionDao = new PositionDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        switch (lastPath) {
            case "staffs":
                showStaffDashboard(req, resp);
                break;
            case "delete":
                String staffId = req.getParameter("id");
                String toastType = "error";
                System.out.println("id is " + staffId);
                if (staffId.matches("\\d+")) {
                    staffDao.deleteById(Long.valueOf(staffId));
                    req.setAttribute("message", "Delete department successfully!");
                } else
                    req.setAttribute("message", "Delete department failed!");
                req.setAttribute("toastType", toastType);
                resp.sendRedirect(req.getContextPath() + "/admin/staffs");
                break;
            default:
                break;
        }
    }

    protected void showStaffDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StaffDashboard");
        Map<String, String[]> parameters = req.getParameterMap();
        Page page = new Page();
        try {
            BeanUtils.populate(page, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("current page: " + page);

        if (page.getPage() == null)
            page.setPage(0);

        req.setAttribute("departments", departmentDao.findAll(page.getPage(), 9999, page.getBy(), page.getDirection()));
        req.setAttribute("positions", positionDao.findAll(page.getPage(), 9999, page.getBy(), page.getDirection()));
        req.setAttribute("staffs", staffDao.findAll(page.getPage(), 10, page.getBy(), page.getDirection()));
        req.setAttribute("pageTitle", "Staff");
        req.getRequestDispatcher("/views/admin/staff/staffs.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        switch (lastPath) {
            case "staffs":
                handleAddStaff(req, resp);
                break;
            case "edit":
                break;
            default:
                break;
        }
    }

    protected void handleAddStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer departmentId = Integer.valueOf(req.getParameter("depart_id"));
        Integer positionId = Integer.valueOf(req.getParameter("pos_id"));

        Map<String, String[]> parameters = req.getParameterMap();
        Staff staff = new Staff();
        try {
            BeanUtils.populate(staff, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String toastType = "error";
        if (departmentId != 0)
            staff.setStaffDepartment(departmentDao.findById(departmentId));
        if (positionId != 0)
            staff.setStaffPos(positionDao.findById(positionId));

        if (staffDao.save(staff) != null) {
            toastType = "success";
            req.setAttribute("message", "Staff added successfully");
        } else
            req.setAttribute("message", "Staff not added");
        req.setAttribute("toastType", toastType);

//        resp.sendRedirect(req.getContextPath() + "/admin/staffs");
    }
}
