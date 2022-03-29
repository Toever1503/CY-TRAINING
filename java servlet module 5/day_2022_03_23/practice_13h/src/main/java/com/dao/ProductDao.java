package com.dao;

import com.entity.Product;
import com.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IDao<Product, Long> {

    private final String INSERT_Q = "INSERT INTO `PRODUCT` (`id`, `name`, `price`, `description`, `image`, `old_price`, `color`, `Material`, `Style`, `Season`, `year`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UPDATE_Q = "UPDATE `PRODUCT` SET NAME = ?, PRICE = ? WHERE ID=? ;";
    private final String SUM_ALL_SALARY = "SELECT SUM(SALARY) AS TOTAL FROM PRODUCT";
    private final String DELETE_Q = "DELETE FROM PRODUCT WHERE ID = ?";
    private final String SELECT_Q = "SELECT * FROM PRODUCT ";

    List<Product> getFromResultSet(ResultSet rs) {
        List<Product> list = new ArrayList<Product>();
        try {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong(1));
                p.setName((String) rs.getNString(2));
                p.setPrice(rs.getDouble(3));
                p.setDescription(rs.getNString(4));
                p.setImage(rs.getNString(5));
                p.setOldPrice(rs.getDouble(6));
                p.setColor(rs.getNString(7));
                p.setMaterial(rs.getNString(8));
                p.setStyle(rs.getNString(9));
                p.setSeason(rs.getNString(10));
                p.setYear(rs.getInt(11));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
        return list;
    }

    @Override
    public Product save(Product obj) {
        Object[] args = {obj.getId(), obj.getName(), obj.getPrice(), obj.getDescription(), obj.getImage(), obj.getOldPrice(), obj.getColor(), obj.getMaterial(), obj.getStyle(), obj.getSeason(), obj.getYear()};
        if (JdbcUtil.save(INSERT_Q, args)) {
            ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" ORDER BY ID DESC limit 0,1"));
            obj = getFromResultSet(rs).get(0);
        } else
            obj = null;
        JdbcUtil.closeConnection();
        return obj;
    }

    @Override
    public Product update(Product obj) {
        Object[] args = {obj.getName(), obj.getPrice(), obj.getId()};

        if (JdbcUtil.save(UPDATE_Q, args)) {
            obj = findById(obj.getId());
        } else
            obj = null;
        JdbcUtil.closeConnection();
        return obj;
    }

    @Override
    public boolean deleteById(Long id) {
        return JdbcUtil.deleteById(DELETE_Q, id);
    }

    @Override
    public Product findById(Long id) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE ID = ?;"), id);
        List<Product> searchList = getFromResultSet(rs);
        return searchList.isEmpty() ? null : searchList.get(0);
    }

    @Override
    public List<Product> findAll() {
        ResultSet rs = JdbcUtil.find(SELECT_Q);
        return getFromResultSet(rs);
    }

    public List<Product> findAll(int page) {
        ResultSet rs = JdbcUtil.find(SELECT_Q + " limit " + page + ",2;");
        return getFromResultSet(rs);
    }

    public List<Product> findLatestProduct() {
        ResultSet rs = JdbcUtil.find(SELECT_Q + " ORDER BY ID DESC limit 0,12");
        return getFromResultSet(rs);
    }


    public List<Product> searchByName(String name) {
        ResultSet rs = JdbcUtil.find(SELECT_Q.concat(" WHERE NAME LIKE ?;"), "%" + name + "%");
        return getFromResultSet(rs);
    }

    public Double sumTotalSalary() {
        ResultSet rs = JdbcUtil.find(SUM_ALL_SALARY);
        Double totalSalary = null;
        try {
            while (rs.next())
                totalSalary = (double) rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalary;
    }

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        Product p = new Product();
        p.setName("Product 1-1");
        p.setPrice(Double.valueOf("129812"));
        p.setDescription("Description 1-1");
        p.setImage("Image 1-1");
        p.setOldPrice(Double.valueOf("129812"));
        p.setColor("Color 1-1");
        p.setMaterial("Material 1-1");
        p.setStyle("Style 1-1");
        p.setSeason("Season 1-1");
        p.setYear(2018);

        System.out.println(dao.save(p));
    }
}
