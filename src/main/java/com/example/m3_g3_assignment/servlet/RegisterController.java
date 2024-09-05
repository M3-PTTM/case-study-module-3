package com.example.m3_g3_assignment.servlet;


import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String customer_name = req.getParameter("customer_name");
        String customer_email = req.getParameter("customer_email");
        String customer_phone = req.getParameter("customer_phone");
        String customer_citizen = req.getParameter("customer_citizen");
        String customer_role = req.getParameter("customer_role");
        if (username == null || password == null || customer_name == null || customer_email == null ||
                customer_phone == null || customer_citizen == null) {
            req.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        try {
            if (customerDAO.existsCustomer(username)) {
                req.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }
            Customer customer = new Customer(username, password, customer_name, customer_email, customer_phone, customer_citizen, customer_role);
            customerDAO.insertCustomer(customer);
            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
