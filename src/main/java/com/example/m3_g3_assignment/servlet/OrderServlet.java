package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceCart;
import com.example.m3_g3_assignment.dao.impl.ServiceCartImpl;
import com.example.m3_g3_assignment.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceCart SERVICE_CART = new ServiceCartImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = 1;
        try {
            List<Cart> carts = SERVICE_CART.getAllCarts(customerId);
            SERVICE_CART.payment(carts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
