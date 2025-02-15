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

@WebServlet("/cart")
public class LoadCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceCart SERVICE_CART = new ServiceCartImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        int productId = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = customer.getCustomer_id();
        try {
            SERVICE_CART.removeProduct(customerId, productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = customer.getCustomer_id();
        int productId = Integer.parseInt(req.getParameter("id"));
        Cart cart = new Cart(customerId, productId, quantity);
        try {
            SERVICE_CART.updateCart(cart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
