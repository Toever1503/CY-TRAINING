package com.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcUtil {
    static final String DB_URL = "jdbc:mysql://localhost:3306/module5_lap1";
    static final String USER = "root";
    static final String PASS = "";
    static final Map<Long, Connection> conPool = new HashMap<Long, Connection>();

    public static Connection getConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conPool.put(Thread.currentThread().getId(), con);
        return con;
    }

    public static ResultSet find(String sql, Object... args) {
        Connection con = JdbcUtil.getConnect();
        if (con == null) {
            return null;
        }
        ResultSet rs = null;
        try {
            PreparedStatement prepareStm = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prepareStm.setObject(i + 1, args[i]);
            }
            rs = prepareStm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static Object save(String sql, Object... args) {
        Connection con = JdbcUtil.getConnect();
        if (con == null) {
            return false;
        }
        Object id = 0;
        try {
            PreparedStatement prepareStm = con.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                prepareStm.setObject(i + 1, args[i]);
            }
            ResultSet rs = prepareStm.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean deleteById(String sql, Object id) {
        Connection con = JdbcUtil.getConnect();
        if (con == null) {
            return false;
        }
        int check = 0;
        try {
            PreparedStatement prepareStm = con.prepareStatement(sql);
            prepareStm.setObject(1, id);
            check = prepareStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
        return check != 0;
    }

    public static void closeConnection() {
        try {
            long threadId = Thread.currentThread().getId();
            conPool.get(threadId).close();
            conPool.remove(threadId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
