package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceOrder;
import com.example.m3_g3_assignment.dao.impl.ServiceOrderImpl;
import com.example.m3_g3_assignment.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orders")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceOrder SERVICE_ORDER = new ServiceOrderImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "delete":
                    deleteOrder(req, resp);
                    break;
                default:
                    showOrders(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int productId = Integer.parseInt(req.getParameter("product_id"));
        int customerId = Integer.parseInt(req.getParameter("customer_id"));
        SERVICE_ORDER.removeOrder(customerId, productId);
        resp.sendRedirect("/orders");
    }

    private void showOrders(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Order> orders = SERVICE_ORDER.getAllOrder();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders.jsp").forward(req, resp);
    }
}
