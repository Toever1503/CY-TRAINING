package com.practice_2022_04_06.dao.impl;

import com.practice_2022_04_06.dao.Dao;
import com.practice_2022_04_06.entity.Department;
import com.practice_2022_04_06.entity.Staff;
import com.practice_2022_04_06.entity.Timekeeping;
import com.practice_2022_04_06.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimekeepingDao implements Dao<Timekeeping, Long> {
    private final String HQL_SELECT = "from timekeepings";

    @Override
    public Timekeeping findById(Long id) {
        Timekeeping timeStaff = null;
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            timeStaff = session.find(Timekeeping.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return timeStaff;
    }

    @Override
    public Timekeeping save(Timekeeping entity) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            if (entity.getId() == null) {
                entity = session.find(Timekeeping.class, (Long) session.save(entity));
            } else {
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
    public boolean delete(Timekeeping entity) {
        Session session = null;
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = null;
        session.beginTransaction();
        session.remove(session.find(Timekeeping.class, id));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Timekeeping> findAll(int page, int size, String sortBy, String sortDirection) {
        Session session = null;
        List<Timekeeping> timeStaffs = null;
        try {
            session = SessionFactoryUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(Timekeeping.class);
            Root<Department> root = criteriaQuery.from(Timekeeping.class);

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
            timeStaffs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return timeStaffs == null ? Collections.emptyList() : timeStaffs;
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

    public boolean beginWork(Long id) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            NativeQuery query = session.createSQLQuery("select * from timekeepings as t where t.date = :date and t.workStaff_id = :staff_id");
            query.setParameter("date", dateFormat.format(currentDate));
            query.setParameter("staff_id", id);
            query.addEntity(Timekeeping.class);

            List<Timekeeping> timekeepings = query.getResultList();
            Timekeeping timekeeping = timekeepings.isEmpty() ? null : timekeepings.get(0);
            Staff staff = session.find(Staff.class, id);

            sb.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":").append(calendar.get(Calendar.MINUTE));
            if (staff == null)
                return false;
            if (timekeeping == null) {
                timekeeping = new Timekeeping();
                timekeeping.setDate(currentDate);
                timekeeping.setTimeStart(sb.toString());
                timekeeping.setWorkStaff(staff);
                session.save(timekeeping);
            } else
                timekeeping.setTimeStart(sb.toString());
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean endWork(Long id) {
        Session session = null;
        try {
            session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            NativeQuery query = session.createSQLQuery("select * from timekeepings as t where t.date = :date and t.workStaff_id = :staff_id");
            query.setParameter("date", dateFormat.format(currentDate));
            query.setParameter("staff_id", id);
            query.addEntity(Timekeeping.class);

            List<Timekeeping> timekeepings = query.getResultList();
            Timekeeping timekeeping = timekeepings.isEmpty() ? null : timekeepings.get(0);
            Staff staff = session.find(Staff.class, id);

            sb.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":").append(calendar.get(Calendar.MINUTE));
            if (staff == null | timekeeping == null)
                return false;
            timekeeping.setTimeEnd(sb.toString());
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public static void main(String[] args) {
        TimekeepingDao pos = new TimekeepingDao();
//        System.out.println("count pos: " + pos.count());
//        pos.findAll(0, 10, null, null).forEach(System.out::println);
//        pos.findAll(0, 10, "id", "asc").forEach(System.out::println);
//        pos.findAll(0, 10, "id", "desc").forEach(System.out::println);
        pos.beginWork(9l);
    }
}
