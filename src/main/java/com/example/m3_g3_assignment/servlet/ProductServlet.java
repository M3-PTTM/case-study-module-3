package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.dao.impl.ServiceProductImpl;
import com.example.m3_g3_assignment.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/home")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                default:
                    productMap(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void productMap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Map<Product, Integer> map = SERVICE_PRODUCT.getProductsNewLimit3();
        req.setAttribute("map", map);
        req.getRequestDispatcher("/man/home.jsp").forward(req, resp);
    }
}
