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
        Connection con = conPool.get(Thread.currentThread().getId());
        if (con != null)
            return con;
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

    public static Boolean save(String sql, Object... args) {
        Connection con = JdbcUtil.getConnect();
        if (con == null) {
            return false;
        }
        int id = 0;
        try {
            PreparedStatement prepareStm = con.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                prepareStm.setObject(i + 1, args[i]);
            }
            id = prepareStm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id != 0;
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
            Connection con = conPool.get(threadId);
            if (con != null) {
                con.close();
                conPool.remove(threadId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
