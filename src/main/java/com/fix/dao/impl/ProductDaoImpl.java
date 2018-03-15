package com.fix.dao.impl;

import com.fix.dao.ProductDao;
import com.fix.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao, ProductSqlQueries {
    private String postgresUrl;
    private String postgresUsername;
    private String postgresPassword;
    private Connection connection;

    public ProductDaoImpl(String postgresUrl, String postgresUsername, String postgresPassword) {
        this.postgresUrl = postgresUrl;
        this.postgresUsername = postgresUsername;
        this.postgresPassword = postgresPassword;
    }

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException();
            }
            connection = DriverManager.getConnection(
                    postgresUrl, postgresUsername, postgresPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public List<Product> findAll() throws SQLException {
        List<Product> listProduct = new ArrayList<Product>();

        connect();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PRODUCTS_SQL);

        while (resultSet.next()) {
            int id = resultSet.getInt(COLUMN_ID);
            String name = resultSet.getString(COLUMN_NAME);
            double price = resultSet.getFloat(COLUMN_PRICE);

            Product product = new Product(id, name, price);
            listProduct.add(product);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listProduct;
    }

    public boolean create(Product product) throws SQLException {
        connect();

        PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

        return rowInserted;
    }
}
