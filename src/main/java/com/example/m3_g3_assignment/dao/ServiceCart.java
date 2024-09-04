package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Product;

import java.sql.SQLException;
import java.util.Map;

public interface ServiceCart {
    Map<Product, Integer> getCart(int id) throws SQLException;

    void insertCart(Cart cart) throws SQLException;
}
