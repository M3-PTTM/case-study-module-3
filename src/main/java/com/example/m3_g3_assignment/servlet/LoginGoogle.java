package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ICustomerDAO;
import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;
import com.example.m3_g3_assignment.model.GooglePojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login-google")
public class LoginGoogle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginGoogle() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code == null || code.isEmpty()) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfoGoogle(accessToken);
            System.out.println(googlePojo.getId());
            System.out.println(googlePojo.getEmail());
            Customer customer = new Customer(googlePojo.getId(), googlePojo.getEmail());
            ICustomerDAO customerDAO = new CustomerDAO();
            try {
                customerDAO.replaceCustomer(customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getSession().setAttribute("customer", customer);
            resp.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
