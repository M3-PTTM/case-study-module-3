package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.IOrdersDAO;
import com.example.m3_g3_assignment.model.Orders;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersDAO implements IOrdersDAO {
    @Override
    public boolean insertOrder(Orders order) {
        String query = "INSERT INTO orders (user_id, orders_quantity, orders_status) VALUES (?, ?, ?)";
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getOrdersQuantity());
            stmt.setString(3, order.getOrdersStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
