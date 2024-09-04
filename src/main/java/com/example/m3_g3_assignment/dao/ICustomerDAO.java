package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    void insertCustomer(Customer customer) throws SQLException;
    boolean existsCustomer(String username) throws SQLException;
    boolean updateCustomer(Customer customer) throws SQLException;
    boolean deleteCustomer(int customer_id) throws SQLException;
    Customer selectCustomer(int customer_id) throws SQLException;
    List<Customer> selectAllCustomers() throws SQLException;
}
