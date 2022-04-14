package com.practice_2022_04_06.dao.impl;

import com.practice_2022_04_06.dao.Dao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.util.SessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

public class DepartmentDao implements Dao<Department, Integer> {
    private final String HQL_SELECT = "from departments";

    @Override
    public Department findById(Integer id) {
        Session session = null;
        Department department = null;
        try {
            session = SessionFactoryUtil.getSession();
            department = session.find(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return department;
    }

    @Override
    public Department save(Department entity) {
        Session session = null;

        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            if (entity.getId() == 0) {
                entity = session.find(Department.class, (Integer) session.save(entity));
            } else {
                Department original = session.find(Department.class, entity.getId());
                session.detach(original);
                entity.setDepartmentStaffs(original.getDepartmentStaffs());
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
    public boolean delete(Department entity) {
//        Session session = null;
//        session.beginTransaction();
//
//        session.getTransaction().commit();
//        session.close();
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            Department entity = session.find(Department.class, id);
            if(entity == null) {
                return false;
            };
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return true;
    }

    @Override
    public List<Department> findAll(int page, int size, String sortBy, String sortDirection) {
        List<Department> departments = null;
        Session session = null;

        try {
            session = SessionFactoryUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);

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
            departments = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return departments == null ? Collections.emptyList() : departments;
    }

    @Override
    public Long count() {
        Long total = 0l;
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            org.hibernate.query.Query query = session.createQuery("SELECT count(id) "+HQL_SELECT);
            total = (Long) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return total;
    }

    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.deleteById(6);
    }
}
