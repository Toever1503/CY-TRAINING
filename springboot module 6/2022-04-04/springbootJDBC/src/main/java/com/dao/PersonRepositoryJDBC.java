package com.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PersonRepositoryJDBC extends JdbcDaoSupport {
    public PersonRepositoryJDBC(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Object findByName(String name) {
//        return getJdbcTemplate().queryForObject("select * from person where name = ?", new Object[]{name}, (rs, rowNum) -> {
//            Person person = new Person();
//            person.setId(rs.getLong("id"));
//            person.setName(rs.getString("name"));
//            person.setAge(rs.getInt("age"));
//            return person;
//        });
//        return getJdbcTemplate().queryForObject("select * from person", (rs, col)->{
//            Person person = new Person();
//            person.setId(rs.getLong("id"));
//            person.setName(rs.getString("name"));
//            person.setAge(rs.getInt("age"));
//            return person;
//        });

        String sql = "delete from person where id = ?";
//        this.getJdbcTemplate().execute()
        return null;
//        return getJdbcTemplate().queryForObject("select distinct * from person where name = ?", Person.class, name);
    }
}
