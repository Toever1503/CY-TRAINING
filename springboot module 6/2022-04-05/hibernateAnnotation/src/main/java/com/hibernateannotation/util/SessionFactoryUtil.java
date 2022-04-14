package com.hibernateannotation.util;

import com.hibernateannotation.entity.Authority;
import com.hibernateannotation.entity.USERS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Properties;


public class SessionFactoryUtil {

    private static SessionFactory instance;

    private SessionFactoryUtil() {
    }

    private static Session getSession() {
        if (instance == null)
            createSessionFactory();
        return instance.openSession();
    }

    private static void createSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernatetest");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.globally_quoted_identifiers", "true");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(USERS.class).addAnnotatedClass(Authority.class);
        instance = configuration.buildSessionFactory();
    }

    public static void main(String[] args) {
        Session session = getSession();
        session.beginTransaction();
        Authority authority = new Authority(1, "ROLE_USER");
//        session.save(authority);


        USERS user = new USERS("user1", "12341", authority, true, 5);
        user.setId(2l);

//        USERS user1 = new USERS("user2", "12341", authority, true, 5);
//        USERS user2 = new USERS("user3", "12341", authority, true, 5);
//        USERS user3 = new USERS("user4", "12341", authority, true, 5);
//        session.save(user);
//        session.save(user1);
//        session.save(user2);
//        session.save(user3);

//        authority.setUsers(Arrays.asList(user, user1, user2, user3));


//        Query<USER> query =  session.createQuery("from USER");
//        query.getResultList().forEach(System.out::println);

//        Authority auth = session.find(Authority.class, 1);
//        System.out.println("users: "+auth.getUsers().size());

//        Authority auth = session.get(Authority.class,1);
//        System.out.println(auth);

//        session.merge(authority);

        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

}
