package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ServiceProduct {
    List<Product> getAllProducts() throws SQLException;

    List<Product> getProductsLimit3() throws SQLException;

    List<Product> getProductsNewLimit3() throws SQLException;

    List<Product> getProductsNextLimit3(int amount) throws SQLException;

    List<Product> searchProductsByNameOrModel(String keyword) throws SQLException;

    Product getProduct(int id) throws SQLException;

    void insertProduct(Product product) throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void deleteProduct(int id) throws SQLException;
}
