package com.example.m3_g3_assignment.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String jdbcURL="jdbc:mysql://localhost:3306/study_case";
    private static final String jdbcUser = "root";
    private static final String jdbcPass = "12345678";
    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUser,jdbcPass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
