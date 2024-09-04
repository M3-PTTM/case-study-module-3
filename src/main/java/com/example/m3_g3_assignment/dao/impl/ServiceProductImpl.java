package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServiceProductImpl implements ServiceProduct {
    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String SELECT_PRODUCT_LIMIT_3 = "select * from product limit 3";
    private static final String SELECT_PRODUCT_NEXT_LIMIT_3 = "select * from product order by product_id desc limit ?,3";
    private static final String SELECT_PRODUCT_NEW_LIMIT_3 = "select * from product order by product_id desc limit 3";
    private static final String SELECT_PRODUCT_BY_NAME_OR_MODEL = "select * from product where product_name like ? or category_name like ?";
    private static final String SELECT_PRODUCT = "select * from product where product_id = ?";
    private static final String UPDATE_PRODUCT = "update product set product_name = ?, product_price = ?, category_name = ?, product_img = ?, product_description = ?, product_inventory = ? where product_id = ?";
    private static final String DELETE_PRODUCT = "delete from product where product_id = ?";
    private static final String INSERT_PRODUCT = "insert into product (product_name, product_price, category_name, product_img, product_description) values (?, ?, ?, ?, ?)";

    @Override
    public List<Product> getAllProducts() throws SQLException {
        Map<Integer, Product> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                String categoryName = resultSet.getString("category_name");
                String productImg = resultSet.getString("product_img");
                String productDescription = resultSet.getString("product_description");
                int productInventory = resultSet.getInt("product_inventory");
                Product product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                map.put(resultSet.getInt("product_id"), product);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Product> getProductsLimit3() throws SQLException {
        Map<Integer, Product> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_LIMIT_3); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                String categoryName = resultSet.getString("category_name");
                String productImg = resultSet.getString("product_img");
                String productDescription = resultSet.getString("product_description");
                int productInventory = resultSet.getInt("product_inventory");
                Product product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                map.put(productId, product);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Product> getProductsNewLimit3() throws SQLException {
        Map<Integer, Product> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_NEW_LIMIT_3); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                String categoryName = resultSet.getString("category_name");
                String productImg = resultSet.getString("product_img");
                String productDescription = resultSet.getString("product_description");
                int productInventory = resultSet.getInt("product_inventory");
                Product product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                map.put(productId, product);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Product> getProductsNextLimit3(int amount) throws SQLException {
        Map<Integer, Product> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_NEXT_LIMIT_3)) {
            preparedStatement.setInt(1, amount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productPrice = resultSet.getDouble("product_price");
                    String categoryName = resultSet.getString("category_name");
                    String productImg = resultSet.getString("product_img");
                    String productDescription = resultSet.getString("product_description");
                    int productInventory = resultSet.getInt("product_inventory");
                    Product product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                    map.put(productId, product);
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Product> searchProductsByNameOrModel(String keyword) throws SQLException {
        Map<Integer, Product> map = new LinkedHashMap<>();
        String keywordLike = "%" + keyword + "%";
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME_OR_MODEL)) {
            preparedStatement.setString(1, keywordLike);
            preparedStatement.setString(2, keywordLike);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productPrice = resultSet.getDouble("product_price");
                    String categoryName = resultSet.getString("category_name");
                    String productImg = resultSet.getString("product_img");
                    String productDescription = resultSet.getString("product_description");
                    int productInventory = resultSet.getInt("product_inventory");
                    Product product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                    map.put(productId, product);
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Product getProduct(int id) throws SQLException {
        Product product = null;
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productPrice = resultSet.getDouble("product_price");
                    String categoryName = resultSet.getString("category_name");
                    String productImg = resultSet.getString("product_img");
                    String productDescription = resultSet.getString("product_description");
                    int productInventory = resultSet.getInt("product_inventory");
                    product = new Product(productId, productName, productPrice, categoryName, productImg, productDescription, productInventory);
                }
            }
        }
        return product;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setString(4, product.getImg());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setString(4, product.getImg());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getInventory());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
