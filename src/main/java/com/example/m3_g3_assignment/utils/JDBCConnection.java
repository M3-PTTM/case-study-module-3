package com.example.m3_g3_assignment.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/study_case?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "12345678";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
