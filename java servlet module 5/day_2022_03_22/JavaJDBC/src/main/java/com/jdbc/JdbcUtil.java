package com.jdbc;

import java.sql.*;

public class JdbcUtil {
    static final String DB_URL = "jdbc:mysql://localhost:3306/module5_lap1";
    static final String USER = "root";
    static final String PASS = "";

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        Connection con = new JdbcUtil().getConnect();

        try {
            Statement stm = con.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM STUDENT");

            PreparedStatement preparedQuery = con.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
            preparedQuery.setLong(1, 1l);

            ResultSet rs = preparedQuery.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
            }

//            //  insert statement
//            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO STUDENT(name, phone, email) values(?,?,?)");
//            preparedStatement.setString(1, "Student 3");
//            preparedStatement.setString(2, "09141284914");
//            preparedStatement.setString(3, "Student@fa.co");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
