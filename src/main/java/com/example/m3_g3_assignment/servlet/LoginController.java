package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Customer customer = null;
        try {
            customer = customerDAO.loginCustomer(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (customer == null) {
            req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không chính xác");
            req.setAttribute("username", username);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("customer", customer);
            String role = customer.getCustomer_role();
            System.out.println(role);
            if ("ADMIN".equals(role)) {
                resp.sendRedirect("dashboard.jsp");
            } else if ("CUSTOMER".equals(role)) {
                resp.sendRedirect("index.jsp");
            } else {
                resp.sendRedirect("login.jsp");
            }
        }
    }
}
