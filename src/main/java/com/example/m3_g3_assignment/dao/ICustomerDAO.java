package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    void insertCustomer(Customer customer) throws SQLException;
    void updatePassword(String customer_email, String newPassword) throws SQLException;
    boolean existsUsername(String username) throws SQLException;
    boolean existsEmail(String customer_email) throws SQLException;
    boolean existsPhone(String customer_phone) throws SQLException;
    boolean existsCitizen(String customer_citizen) throws SQLException;
    boolean updateCustomer(Customer customer) throws SQLException;
    boolean deleteCustomer(int customer_id) throws SQLException;
    Customer selectCustomer(int customer_id) throws SQLException;
    Customer loginCustomer(String username, String password) throws SQLException;
    Customer findCustomerByEmail(String customer_email) throws SQLException;
    List<Customer> selectAllCustomers() throws SQLException;
}
