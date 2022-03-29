package com.dao;

import com.entity.Log;
import com.entity.Product;
import com.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDao implements IDao<Log, Long>{
    private final String INSERT_Q = "INSERT INTO `LOG` (`id`, `MESSAGE`) VALUES (?, ?);";
    private final String SELECT_Q = "SELECT * FROM LOG";

    List<Log> getFromResultSet(ResultSet rs) {
        List<Log> list = new ArrayList<Log>();
        try {
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getLong(1));
                log.setMessage((String) rs.getNString(2));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
        return list;
    }

    @Override
    public Log save(Log obj) {
        Object[] args = {obj.getId(), obj.getMessage()};
        if (JdbcUtil.save(INSERT_Q, args)) {
            ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" ORDER BY ID DESC limit 0,1"));
            obj = getFromResultSet(rs).get(0);
        } else
            obj = null;
        JdbcUtil.closeConnection();
        return obj;
    }

    @Override
    public Log update(Log obj) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Log findById(Long id) {
        return null;
    }

    @Override
    public List<Log> findAll() {
        return null;
    }
}
