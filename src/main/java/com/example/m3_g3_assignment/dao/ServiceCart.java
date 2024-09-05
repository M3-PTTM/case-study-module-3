package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ServiceCart {
    List<Product> getAllProducts(int id) throws SQLException;

    void insertCart(Cart cart) throws SQLException;

    void removeProduct(int customerId, int productId) throws SQLException;

    void payment(List<Cart> carts) throws SQLException;

    void updateCart(Cart cart) throws SQLException;

    List<Cart> getAllCarts(int id) throws SQLException;
}
