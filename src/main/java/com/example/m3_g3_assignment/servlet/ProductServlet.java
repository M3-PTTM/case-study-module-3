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
import java.util.List;

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
                    productsList(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void productsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
       List<Product> products = SERVICE_PRODUCT.getProductsNewLimit3();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/man/home.jsp").forward(req, resp);
    }
}
