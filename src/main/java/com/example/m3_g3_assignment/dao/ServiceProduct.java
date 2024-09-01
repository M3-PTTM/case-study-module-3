package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Product;

import java.sql.SQLException;
import java.util.Map;

public interface ServiceProduct {
    Map<Product, Integer> getAllProducts() throws SQLException;

    Map<Product, Integer> getProductsLimit3() throws SQLException;

    Map<Product, Integer> getProductsNewLimit3() throws SQLException;

    Map<Product, Integer> getProductsNextLimit3(int amount) throws SQLException;

    Map<Product, Integer> searchProductsByNameOrModel(String keyword) throws SQLException;
}
