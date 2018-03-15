package com.fix.dao.fabric;

import com.fix.dao.ProductDao;
import com.fix.dao.impl.ProductDaoImpl;

public class ProductDaoFabric {

    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/product_db";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PASSWORD = "qwerty007";

    public static ProductDao getProductDao(){
        return new ProductDaoImpl(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
    }

}
