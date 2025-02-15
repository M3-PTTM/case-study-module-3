package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ICustomerDAO;
import com.example.m3_g3_assignment.dao.ServiceCart;
import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.dao.impl.ReviewDAO;
import com.example.m3_g3_assignment.dao.impl.ServiceCartImpl;
import com.example.m3_g3_assignment.dao.impl.ServiceProductImpl;
import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Customer;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();
    private static final ServiceCart SERVICE_CART = new ServiceCartImpl();
    private static final ReviewDAO REVIEW_DAO=new ReviewDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "view":
                    viewProduct(req, resp);
                    break;
                case "add-to-cart":
                    addToCart(req, resp);
                    break;
                case "cart":
                    showCart(req, resp);
                    break;
                default:
                    productsList(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            int customerId = customer.getCustomer_id();
            List<Product> products = SERVICE_PRODUCT.getProductsNewLimit3();
            List<Product> productsCart = SERVICE_CART.getAllProducts(customerId);
            req.setAttribute("productsCart", productsCart);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
        }
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);
        if (customer == null) {
            resp.sendRedirect("");
        } else {
            int customerId = customer.getCustomer_id();
            int productId = Integer.parseInt(req.getParameter("id"));
            Cart cart = new Cart(customerId, productId);
            SERVICE_CART.insertCart(cart);
        }
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = SERVICE_PRODUCT.getProduct(id);
        List<Product> products = SERVICE_PRODUCT.getProductsNewLimit3();

        //review
        List<Review> reviewList=REVIEW_DAO.getTopThreeReview(id);
        req.setAttribute("reviewList", reviewList);
        int countReviewById= REVIEW_DAO.countReviewByProductId(id);
        req.setAttribute("countReviewById", countReviewById);

        req.setAttribute("product", product);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/view.jsp").forward(req, resp);
    }

    private void productsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Product> products = SERVICE_PRODUCT.getProductsNewLimit3();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
