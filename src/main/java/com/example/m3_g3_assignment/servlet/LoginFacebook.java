package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ICustomerDAO;
import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;
import com.restfb.types.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login-facebook")
public class LoginFacebook extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            String accessToken = RestFB.getToken(code);
            User user = RestFB.getUserInfo(accessToken);
            System.out.println(user.getId());
            System.out.println(user.getName());
            Customer customer = new Customer(user.getId(), user.getName());
            ICustomerDAO customerDAO = new CustomerDAO();
            try {
                customerDAO.replaceCustomer(customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getSession().setAttribute("customer", customer);
            response.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
