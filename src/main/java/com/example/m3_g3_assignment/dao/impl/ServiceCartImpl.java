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
import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceCartImpl implements ServiceCart {
    private static final String INSERT_CART = "replace into cart (customer_id, product_id) values (?,?)";
    private static final String SELECT_CART = "select * from cart where customer_id = ?";
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();

    @Override
    public Map<Product, Integer> getCart(int id) throws SQLException {
        Map<Product, Integer> map = new LinkedHashMap<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = SERVICE_PRODUCT.getProduct(resultSet.getInt("product_id"));
                    int quantity = resultSet.getInt("quantity");
                    map.put(product, quantity);
                }
            }
        }
        return map;
    }

    @Override
    public void insertCart(Cart cart) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART)) {
            preparedStatement.setInt(1, cart.getCustomer().getCustomer_id());
            preparedStatement.setInt(2, cart.getProduct().getId());
            preparedStatement.executeUpdate();
        }
    }
}
