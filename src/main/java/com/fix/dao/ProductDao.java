package com.fix.dao;

import com.fix.models.Product;

import java.sql.SQLException;
import java.util.List;


public interface ProductDao {
    List<Product> findAll() throws SQLException;

    boolean create(Product product) throws SQLException;
}
