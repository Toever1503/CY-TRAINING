package com.dao;

import com.entity.Product;
import com.entity.User;
import com.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IDao<User, Long> {
    private final String SELECT_Q = "SELECT ID, USERNAME, PASSWORD FROM USER";

    List<User> getFromResultSet(ResultSet rs) {
        List<User> list = new ArrayList<User>();
        try {
            while (rs.next()) {
                User stu = new User();
                stu.setId(rs.getLong(1));
                stu.setUsername((String) rs.getNString(2));
                stu.setPassword(rs.getString(3));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
        return list;
    }

    @Override
    public User save(User obj) {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public User findById(Long id) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE ID = ?;"), id);
        List<User> searchList = getFromResultSet(rs);
        return searchList.isEmpty() ? null : searchList.get(0);
    }

    public User findByUserName(String username) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE USERNAME = ?;"), username);
        List<User> searchList = getFromResultSet(rs);
        return searchList.isEmpty() ? null : searchList.get(0);
    }


    @Override
    public List<User> findAll() {
        return null;
    }
}
