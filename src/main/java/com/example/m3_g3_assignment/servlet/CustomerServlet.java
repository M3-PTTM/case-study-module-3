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
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers-servlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> listCustomers;
        try {
            listCustomers = customerDAO.selectAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("listCustomers", listCustomers);
        request.getRequestDispatcher("/customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String customer_name = request.getParameter("customer_name");
        String customer_email = request.getParameter("customer_email");
        String customer_phone = request.getParameter("customer_phone");
        String customer_citizen = request.getParameter("customer_citizen");
        String customer_role = request.getParameter("customer_role");
        Customer customer = new Customer(username, customer_name, customer_email, customer_phone, customer_citizen, customer_role);
        try {
            if (customerDAO.existsCustomer(username)) {
                customerDAO.updateCustomer(customer);
            } else {
                customerDAO.insertCustomer(customer);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("customers-servlet");
    }
}
