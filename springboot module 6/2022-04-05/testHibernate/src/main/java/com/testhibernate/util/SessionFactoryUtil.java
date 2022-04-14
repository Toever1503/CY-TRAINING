package com.testhibernate.util;

import com.testhibernate.entity.Authority;
import com.testhibernate.entity.USER;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

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
        instance = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
    }

    public static void main(String[] args) {
        Session session = getSession();
        session.beginTransaction();

//        Authority authority = new Authority("ROLE_USER");
//        USER user = new USER("user1", "12341", null, true, 5);
//        USER user1 = new USER("user2", "12341", null, true, 5);
//        USER user2 = new USER("user3", "12341", null, true, 5);
//        USER user3 = new USER("user4", "12341", null, true, 5);
//        session.save(user);
//        session.save(user1);
//        session.save(user2);
//        session.save(user3);
//
//        authority.setUsers(Arrays.asList(user, user1, user2, user3));
//        session.save(authority);

//        Query<USER> query =  session.createQuery("from USER");
//        query.getResultList().forEach(System.out::println);

//        Authority auth = session.find(Authority.class, 1);
//        System.out.println("users: "+auth.getUsers().size());

        session.getTransaction().commit();
        session.close();
    }

}
