package com.practice_2022_04_06.dao.impl;

import com.practice_2022_04_06.dao.Dao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Staff;
import com.practice_2022_04_06.util.SessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;


public class StaffDao implements Dao<Staff, Long> {
    private final String HQL_SELECT = "from staffs";

    @Override
    public Staff findById(Long id) {
        Staff staff = null;
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            staff = session.find(Staff.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return staff;
    }

    @Override
    public Staff save(Staff entity) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            if (entity.getId() == 0) {
                entity.setWorkDates(0);
                entity = session.find(Staff.class, (Long) session.save(entity));
            } else {
                Staff original = session.find(Staff.class, entity.getId());
                session.detach(original);
                if (entity.getImage() == null)
                    entity.setImage(original.getImage());
                if (original.getDateWorks() != null)
                    entity.setDateWorks(original.getDateWorks());
                entity.setWorkDates(original.getWorkDates());
                session.update(entity);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public boolean delete(Staff entity) {
        Session session = null;
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            Staff entity = session.find(Staff.class, id);
            if (entity == null) return false;
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        session.close();
        return true;
    }

    @Override
    public List<Staff> findAll(int page, int size, String sortBy, String sortDirection) {
        Session session = null;
        List<Staff> staffs = null;
        try {
            session = SessionFactoryUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(Staff.class);
            Root<Department> root = criteriaQuery.from(Staff.class);

            Order order = null;
            if (!(sortBy == null)) {
                if (sortDirection.equalsIgnoreCase("asc"))
                    order = builder.asc(root.get(sortBy));
                else
                    order = builder.desc(root.get(sortBy));
            } else
                order = builder.asc(root.get("id"));
            criteriaQuery.orderBy(order);
            Query query = session.createQuery(criteriaQuery);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            staffs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return staffs == null ? Collections.emptyList() : staffs;
    }

    @Override
    public Long count() {
        Long total = 0l;
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            org.hibernate.query.Query query = session.createQuery("SELECT count(id) " + HQL_SELECT);
            total = (Long) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return total;
    }

    public static void main(String[] args) {
        StaffDao pos = new StaffDao();
//        System.out.println("count pos: " + pos.count());
//        pos.findAll(0, 10, null, null).forEach(System.out::println);
//        pos.findAll(0, 10, "id", "asc").forEach(System.out::println);
//        pos.findAll(0, 10, "id", "desc").forEach(System.out::println);
        System.out.println(pos.findById(3l));
    }
}
