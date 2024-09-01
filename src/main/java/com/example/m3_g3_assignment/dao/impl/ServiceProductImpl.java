package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceProductImpl implements ServiceProduct {
    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String SELECT_PRODUCT_LIMIT_3 = "select * from product limit 3";
    private static final String SELECT_PRODUCT_NEXT_LIMIT_3 = "select * from product order by product_id desc limit ?,3";
    private static final String SELECT_PRODUCT_NEW_LIMIT_3 = "select * from product order by product_id desc limit 3";
    private static final String SELECT_PRODUCT_BY_NAME_OR_MODEL = "select * from product where product_name like ? or category_name like ?";

    @Override
    public Map<Product, Integer> getAllProducts() throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(
                        new Product(resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getDouble("product_price"),
                                resultSet.getString("category_name"),
                                resultSet.getString("product_img"),
                                resultSet.getString("product_description")), resultSet.getInt("product_inventory"));
            }
        }
        return map;
    }

    @Override
    public Map<Product, Integer> getProductsLimit3() throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_LIMIT_3); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(
                        new Product(resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getDouble("product_price"),
                                resultSet.getString("category_name"),
                                resultSet.getString("product_img"),
                                resultSet.getString("product_description")), resultSet.getInt("product_inventory"));
            }
        }
        return map;
    }

    @Override
    public Map<Product, Integer> getProductsNewLimit3() throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_NEW_LIMIT_3);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(
                        new Product(resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getDouble("product_price"),
                                resultSet.getString("category_name"),
                                resultSet.getString("product_img"),
                                resultSet.getString("product_description")), resultSet.getInt("product_inventory"));
            }
        }
        return map;
    }

    @Override
    public Map<Product, Integer> getProductsNextLimit3(int amount) throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_NEXT_LIMIT_3)) {
            preparedStatement.setInt(1, amount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    map.put(
                            new Product(resultSet.getInt("product_id"),
                                    resultSet.getString("product_name"),
                                    resultSet.getDouble("product_price"),
                                    resultSet.getString("category_name"),
                                    resultSet.getString("product_img"),
                                    resultSet.getString("product_description")), resultSet.getInt("product_inventory"));
                }
            }
        }
        return map;
    }

    @Override
    public Map<Product, Integer> searchProductsByNameOrModel(String keyword) throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        String keywordLike = "%" + keyword + "%";
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME_OR_MODEL)) {
            preparedStatement.setString(1, keywordLike);
            preparedStatement.setString(2, keywordLike);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    map.put(
                            new Product(resultSet.getInt("product_id"),
                                    resultSet.getString("product_name"),
                                    resultSet.getDouble("product_price"),
                                    resultSet.getString("category_name"),
                                    resultSet.getString("product_img"),
                                    resultSet.getString("product_description")), resultSet.getInt("product_inventory"));
                }
            }
        }
        return map;
    }
}
