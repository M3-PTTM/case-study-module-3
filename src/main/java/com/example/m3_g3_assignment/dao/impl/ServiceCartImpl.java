package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.ServiceCart;
import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceCartImpl implements ServiceCart {
    private static final String INSERT_CART = "replace into cart (customer_id, product_id) values (?,?)";
    private static final String SELECT_CART = "select * from cart where customer_id = ?";
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();
    private static final String DELETE_CART = "delete from cart where customer_id = ? and product_id = ?";
    private static final String INSERT_ORDERS = "insert into orders(customer_id, product_id, quantity) values (?,?,?)";
    private static final String UPDATE_CART = "update cart set quantity = ? where customer_id = ? and product_id = ?";

    @Override
    public List<Product> getAllProducts(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = SERVICE_PRODUCT.getProduct(resultSet.getInt("product_id"));
                    int quantity = resultSet.getInt("quantity");
                    product.setQuantity(quantity);
                    products.add(product);
                }
            }
        }
        return products;
    }

    @Override
    public void insertCart(Cart cart) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART)) {
            preparedStatement.setInt(1, cart.getCustomerId());
            preparedStatement.setInt(2, cart.getProductId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeProduct(int customerId, int productId) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void payment(List<Cart> carts) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS)) {
            for (Cart cart : carts) {
                preparedStatement.setInt(1, cart.getCustomerId());
                preparedStatement.setInt(2, cart.getProductId());
                preparedStatement.setInt(3, cart.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    @Override
    public void updateCart(Cart cart) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART)) {
            preparedStatement.setInt(1, cart.getQuantity());
            preparedStatement.setInt(2, cart.getCustomerId());
            preparedStatement.setInt(3, cart.getProductId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Cart> getAllCarts(int id) throws SQLException {
        List<Cart> carts = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = new Cart(resultSet.getInt("customer_id"), resultSet.getInt("product_id"), resultSet.getInt("quantity"));
                    carts.add(cart);
                }
            }
        }
        return carts;
    }
}
