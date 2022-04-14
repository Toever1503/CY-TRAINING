package com.practice_2022_04_06.dao.impl;

import com.practice_2022_04_06.dao.Dao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Position;
import com.practice_2022_04_06.util.SessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class PositionDao implements Dao<Position, Integer> {
    private static final String HQL_SELECT = "from positions";

    @Override
    public Position findById(Integer id) {
        Position pos = null;
        Session session = null;
        try{
            session = SessionFactoryUtil.getSession();
            pos = session.find(Position.class, id);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pos;
    }

    @Override
    public Position save(Position entity) {
        Session session = null;
        try{
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            if (entity.getId() == null) {
                entity = session.find(Position.class, (Integer) session.save(entity));
            } else {
                session.update(entity);
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public boolean delete(Position entity) {
        Session session = null;
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
        return false;
    }
    @Override
    public boolean deleteById(Integer id) {
        Session session = null;
        session.beginTransaction();
        session.remove(session.find(Position.class, id));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Position> findAll(int page, int size, String sortBy, String sortDirection) {
        Session session = null;
        List<Position> poses = null;
        try {
            session = SessionFactoryUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(Position.class);
            Root<Department> root = criteriaQuery.from(Position.class);

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
            poses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return poses == null ? Collections.emptyList() : poses;
    }

    @Override
    public Long count() {
        Long total = 0l;
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            org.hibernate.query.Query query = session.createQuery("SELECT count(id) "+ HQL_SELECT);
            total = (Long) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return total;
    }

    public static void main(String[] args) {
        PositionDao pos = new PositionDao();
        System.out.println("count pos: " + pos.count());
        pos.findAll(0, 10, null, null).forEach(System.out::println);
        pos.findAll(0, 10, "id", "asc").forEach(System.out::println);
        pos.findAll(0, 10, "id", "desc").forEach(System.out::println);
    }
}
