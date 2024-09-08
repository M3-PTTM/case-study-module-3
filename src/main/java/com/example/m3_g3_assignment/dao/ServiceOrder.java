package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface ServiceOrder {
    List<Order> getAllOrder() throws SQLException;

    void removeOrder(int customerId, int productId) throws SQLException;
}
