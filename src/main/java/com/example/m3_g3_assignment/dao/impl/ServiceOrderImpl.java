package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.ServiceOrder;
import com.example.m3_g3_assignment.model.Customer;
import com.example.m3_g3_assignment.model.Order;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderImpl implements ServiceOrder {
    private static final String SELECT_ORDER = "select product.product_id, product.product_price, product.product_name, customer.customer_name, customer.customer_id, orders.quantity, orders.order_code from orders join customer on customer.customer_id = orders.customer_id join product on product.product_id = orders.product_id order by orders.order_code desc";
    private static final String DELETE_ORDER = "delete from orders where  customer_id = ? and  product_id= ? ";

    @Override
    public List<Order> getAllOrder() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("product_price"));
                Customer customer = new Customer(resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"));
                int quantity = resultSet.getInt("quantity");
                String orderCode = resultSet.getString("order_code");
                String result = orderCode.replaceAll("[-\\s:]+", "");
                Order order = new Order(product, customer, quantity, result);
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public void removeOrder(int customerId, int productId) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }
}
