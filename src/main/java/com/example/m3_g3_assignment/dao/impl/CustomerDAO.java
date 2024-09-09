package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.ICustomerDAO;
import com.example.m3_g3_assignment.model.Customer;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private static final String INSERT_CUSTOMER_SQL =
            "INSERT INTO customer (username, password, customer_name, customer_email, customer_phone, customer_citizen) " +
                    "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_CUSTOMER_BY_ID =
            "SELECT username, customer_name, customer_email, customer_phone, customer_citizen, customer_role " +
                    "FROM customer WHERE customer_id = ?";
    private static final String SELECT_ALL_CUSTOMERS =
            "SELECT customer_id, username, customer_name, customer_email, customer_phone, customer_citizen, customer_role " +
                    "FROM customer WHERE customer_role = 'CUSTOMER' OR customer_role = 'CUSTOMER-VIP'";
    private static final String DELETE_CUSTOMER_SQL =
            "DELETE FROM customer WHERE customer_id = ?";
    private static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer SET username = ?, customer_name = ?, customer_email = ?, customer_phone = ?, customer_citizen = ?, customer_role = ? WHERE customer_id = ?";
    private static final String EXISTS_USERNAME =
            "SELECT 1 FROM customer WHERE username = ?";
    private static final String EXISTS_EMAIL =
            "SELECT 1 FROM customer WHERE customer_email = ?";
    private static final String EXISTS_PHONE =
            "SELECT 1 FROM customer WHERE customer_phone = ?";
    private static final String EXISTS_CITIZEN =
            "SELECT 1 FROM customer WHERE customer_citizen = ?";
    private static final String ACCOUNT_CUSTOMER =
            "SELECT * FROM customer WHERE username = ? AND password = ?";
    private static final String FIND_CUSTOMER_EMAIL =
            "SELECT * FROM customer WHERE customer_email = ?";
    private static final String UPDATE_PASSWORD =
            "UPDATE customer SET password = ? WHERE customer_email = ?";
    private static final String REPLACE_CUSTOMER = "replace into customer (username, customer_name) values (?,?)";

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getCustomer_name());
            preparedStatement.setString(4, customer.getCustomer_email());
            preparedStatement.setString(5, customer.getCustomer_phone());
            preparedStatement.setString(6, customer.getCustomer_citizen());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updatePassword(String customer_email, String newPassword) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, customer_email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean existsUsername(String username) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXISTS_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    @Override
    public boolean existsEmail(String customer_email) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXISTS_EMAIL)) {
            preparedStatement.setString(1, customer_email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    @Override
    public boolean existsPhone(String customer_phone) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXISTS_PHONE)) {
            preparedStatement.setString(1, customer_phone);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    @Override
    public boolean existsCitizen(String customer_citizen) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EXISTS_CITIZEN)) {
            preparedStatement.setString(1, customer_citizen);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getCustomer_name());
            preparedStatement.setString(3, customer.getCustomer_email());
            preparedStatement.setString(4, customer.getCustomer_phone());
            preparedStatement.setString(5, customer.getCustomer_citizen());
            preparedStatement.setString(6, customer.getCustomer_role());
            preparedStatement.setInt(7, customer.getCustomer_id());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteCustomer(int customer_id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            preparedStatement.setInt(1, customer_id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public Customer selectCustomer(int customer_id) throws SQLException {
        Customer customer = null;
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, customer_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String customer_name = resultSet.getString("customer_name");
                    String customer_email = resultSet.getString("customer_email");
                    String customer_phone = resultSet.getString("customer_phone");
                    String customer_citizen = resultSet.getString("customer_citizen");
                    String customer_role = resultSet.getString("customer_role");
                    customer = new Customer(customer_id, username, customer_name, customer_email, customer_phone, customer_citizen, customer_role);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomers() throws SQLException {
        List<Customer> listCustomers = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int customer_id = resultSet.getInt("customer_id");
                String username = resultSet.getString("username");
                String customer_name = resultSet.getString("customer_name");
                String customer_email = resultSet.getString("customer_email");
                String customer_phone = resultSet.getString("customer_phone");
                String customer_citizen = resultSet.getString("customer_citizen");
                String customer_role = resultSet.getString("customer_role");
                Customer customer = new Customer(customer_id, username, customer_name, customer_email, customer_phone, customer_citizen, customer_role);
                listCustomers.add(customer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listCustomers;
    }

    @Override
    public void replaceCustomer(Customer customer) throws SQLException {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(REPLACE_CUSTOMER)) {
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getCustomer_name());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Customer loginCustomer(String username, String password) throws SQLException {
        Customer customer = null;
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNT_CUSTOMER)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                customer.setCustomer_name(resultSet.getString("customer_name"));
                customer.setCustomer_email(resultSet.getString("customer_email"));
                customer.setCustomer_phone(resultSet.getString("customer_phone"));
                customer.setCustomer_citizen(resultSet.getString("customer_citizen"));
                customer.setCustomer_role(resultSet.getString("customer_role"));
            }
        }
        return customer;
    }

    @Override
    public Customer findCustomerByEmail(String customer_email) throws SQLException {
        Customer customer = null;
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_EMAIL)) {
            preparedStatement.setString(1, customer_email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int customer_id = resultSet.getInt("customer_id");
                String username = resultSet.getString("username");
                String customer_name = resultSet.getString("customer_name");
                String customer_phone = resultSet.getString("customer_phone");
                String customer_citizen = resultSet.getString("customer_citizen");
                String customer_role = resultSet.getString("customer_role");
                customer = new Customer(customer_id, username, customer_name, customer_email, customer_phone, customer_citizen, customer_role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}