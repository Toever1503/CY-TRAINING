package com.practice_2022_04_06.util;

import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Position;
import com.practice_2022_04_06.entity.Staff;
import com.practice_2022_04_06.entity.Timekeeping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class SessionFactoryUtil {

    private static SessionFactory instance;

    private SessionFactoryUtil() {
    }

    public static Session getSession() {
        if (instance == null)
            createSessionFactory();
        return instance.openSession();
    }

    private static void createSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/practice_2022_04_06");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        properties.put("hibernate.c3p0.min_size", "5");
        properties.put("hibernate.c3p0.max_size", "180");
        properties.put("hibernate.c3p0.timeout", "250");
        properties.put("hibernate.c3p0.max_statements", "50");


        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Position.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Timekeeping.class);
        configuration.setProperties(properties);
        instance = configuration.buildSessionFactory();
    }


    public static void testCallback(Object callback){
        System.out.println(callback.getClass());
    }

    public static int sum(int a, int b){return a+b;};

    public static void main(String[] args) {
        Session session = getSession();
        session.beginTransaction();

        Position position = new Position(null, "Staff", 1.0f, null);
        Integer posId = (Integer) session.save(position);
        System.out.println("posId = " + posId);
        position.setId(posId);

        Department department = new Department(null, "department 1", null, null);
        session.save(department);

        Staff staff = new Staff(null, "staff 2", "img 1", 20, 0, 1000, position, department, null);
        Staff staff1 = new Staff(null, "staff 2", "img 1", 22, 0, 500, position, department, null);
        session.save(staff);
        session.save(staff1);


        session.getTransaction().commit();
        session.close();

    }

}
