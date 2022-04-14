package com.practice_2022_04_06.controller.admin;

import com.practice_2022_04_06.dao.impl.DepartmentDao;
import com.practice_2022_04_06.dao.impl.StaffDao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Staff;
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
import java.util.Map;

@WebServlet(value = {"/admin/departments/*"}, loadOnStartup = 0)
public class DepartmentController extends HttpServlet {

    private final DepartmentDao departmentDao;
    private final StaffDao staffDao;

    public DepartmentController() {
        this.departmentDao = new DepartmentDao();
        this.staffDao = new StaffDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        System.out.println("lastPath: " + lastPath);
        switch (lastPath) {
            case "departments":
                showDepartmentDashboard(req, resp);
                break;
            case "delete":
                String departId = req.getParameter("id");
                String toastType ="error";
                if(departId.matches("\\d+")) {
                    departmentDao.deleteById(Integer.parseInt(departId));
                    req.setAttribute("message", "Delete department successfully!");
                }
                else
                    req.setAttribute("message", "Delete department failed!");
                req.setAttribute("toastType", toastType);
                resp.sendRedirect(req.getContextPath() + "/admin/departments");
                break;
            default:
                break;
        }
    }

    protected void showDepartmentDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        req.setAttribute("departments", departmentDao.findAll(page.getPage(), 10, page.getBy(), page.getDirection()));
        req.setAttribute("staffs", staffDao.findAll(0, 100000, null, null));
        req.setAttribute("pageTitle", "Department");
        req.getRequestDispatcher("/views/admin/department/departments.jsp").forward(req, resp);
    }

    protected void showAddDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void showEditDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastPath = StringUtil.getLastPath(req.getRequestURI());
        System.out.println("lastPath: " + lastPath);
        switch (lastPath) {
            case "departments":
                handleAddDepartment(req, resp);
                break;
            case "edit":
                handleEditDepartment(req, resp);
                break;
            default:
                break;
        }
    }

    protected void handleAddDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long managerId = Long.valueOf(req.getParameter("manager_id"));
        System.out.println("managerId: " + managerId);

        Map<String, String[]> parameters = req.getParameterMap();
        Department department = new Department();
        try {
            BeanUtils.populate(department, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String toastType = "error";
        if (managerId != 0l)
            department.setManager(staffDao.findById(managerId));
        if (departmentDao.save(department) != null) {
            toastType = "success";
            req.setAttribute("message", "Department added successfully");
        } else
            req.setAttribute("message", "Department not added");
        req.setAttribute("toastType", toastType);

        resp.sendRedirect(req.getContextPath() + "/admin/departments");
    }

    protected void handleEditDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleAddDepartment(req, resp);
    }

}
