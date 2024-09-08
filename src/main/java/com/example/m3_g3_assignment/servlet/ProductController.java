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

@WebServlet("/product")
public class ProductController extends HttpServlet {
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
                case "delete":
                    deleteProduct(req, resp);
                    break;
                default:
                    productList(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String category = req.getParameter("category");
        String image = req.getParameter("img");
        String description = req.getParameter("description");
        int inventory = Integer.parseInt(req.getParameter("inventory"));
        Product product = new Product(name, price, category, image, description, inventory);
        SERVICE_PRODUCT.insertProduct(product);
        resp.sendRedirect("/product");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        SERVICE_PRODUCT.deleteProduct(id);
        resp.sendRedirect("/product");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String paramPrice = req.getParameter("price");
        String formattedPrice = paramPrice.replaceAll("[. VND]", "");
        double price = Double.parseDouble(formattedPrice);
        String category = req.getParameter("category");
        String image = req.getParameter("img");
        String description = req.getParameter("description");
        int inventory = Integer.parseInt(req.getParameter("inventory"));
        Product product = new Product(id, name, price, category, image, description, inventory);
        SERVICE_PRODUCT.updateProduct(product);
        resp.sendRedirect("/product");
    }

    private void productList(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Product> products = SERVICE_PRODUCT.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "edit":
                    updateProduct(req, resp);
                    break;
                case "add":
                    insertProduct(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
