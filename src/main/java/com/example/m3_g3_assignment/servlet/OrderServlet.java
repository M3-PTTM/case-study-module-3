package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceCart;
import com.example.m3_g3_assignment.dao.impl.ServiceCartImpl;
import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceCart SERVICE_CART = new ServiceCartImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = customer.getCustomer_id();
        try {
            List<Cart> carts = SERVICE_CART.getAllCarts(customerId);
            SERVICE_CART.payment(carts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
