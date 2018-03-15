package com.fix.dao.impl;


public interface ProductSqlQueries extends ProductTableColumns{
    String INSERT_PRODUCT_SQL = "INSERT INTO products (" + COLUMN_NAME + ", "
            + COLUMN_PRICE + ") VALUES (?, ?)";

    String SELECT_ALL_FROM_PRODUCTS_SQL = "SELECT * FROM products";
}
