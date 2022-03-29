package com.dao;

import com.entity.Employee;
import com.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IDao<Employee, Long> {

    private final String INSERT_UPDATE_Q = "INSERT INTO `employee` (`id`, `name`, `salary`) VALUES (?, ?, ?);";
    private final String SUM_ALL_SALARY = "SELECT SUM(SALARY) AS TOTAL FROM employee";
    private final String DELETE_Q = "DELETE FROM employee WHERE ID = ?";
    private final String SELECT_Q = "SELECT * FROM employee";

    public EmployeeDao() {
    }

    List<Employee> getFromResultSet(ResultSet rs) {
        List<Employee> list = new ArrayList<Employee>();
        try {
            while (rs.next()) {
                Employee stu = new Employee();
                stu.setId((long) rs.getInt(1));
                stu.setName((String) rs.getNString(2));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
        return list;
    }

    @Override
    public Employee save(Employee obj) {
        Object[] args = {obj.getId(), obj.getName(), obj.getSalary()};
        Long id = Long.valueOf((Integer) JdbcUtil.save(INSERT_UPDATE_Q, args));
        if (obj.getId() == null) {
            JdbcUtil.closeConnection();
            return obj;
        } else {
            JdbcUtil.closeConnection();
            return findById(id);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return JdbcUtil.deleteById(DELETE_Q, id);
    }

    @Override
    public Employee findById(Long id) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE ID = ?;"), id);
        List<Employee> searchList = getFromResultSet(rs);
        return searchList.isEmpty() ? null : searchList.get(0);
    }

    @Override
    public List<Employee> findAll() {
        ResultSet rs = JdbcUtil.find(SELECT_Q);
        return getFromResultSet(rs);
    }

    public List<Employee> searchByName(String name) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE NAME LIKE ?;"), "'%".concat(name.concat("%'")));
        return getFromResultSet(rs);
    }

    public Double sumTotalSalary() {
        ResultSet rs = JdbcUtil.find(SUM_ALL_SALARY);
        Double totalSalary = null;
        try {
            while (rs.next())
                totalSalary = (double) rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalary;
    }

    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        System.out.println(dao.save(new Employee(null, "fkasjfn", Double.valueOf("141241"))));
        ;
    }
}
