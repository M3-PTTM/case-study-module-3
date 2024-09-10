package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.dao.impl.ReviewDAO;
import com.example.m3_g3_assignment.dao.impl.ServiceProductImpl;
import com.example.m3_g3_assignment.model.Customer;
import com.example.m3_g3_assignment.model.Product;
import com.example.m3_g3_assignment.model.Review;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "reviewServlet", value = "/ReviewServlet")
public class ReviewController extends HttpServlet {
    ReviewDAO reviewDAO = new ReviewDAO();
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();
    private static final ReviewDAO REVIEW_DAO = new ReviewDAO();
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        List<Review> reviews = null;
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "showAll":
                    int id = Integer.parseInt(request.getParameter("id_showAll"));
                    reviews = reviewDAO.showProductReview(id);
                    int countReviewById= REVIEW_DAO.countReviewByProductId(id);
                    Product product = SERVICE_PRODUCT.getProduct(id);
                    request.setAttribute("countReviewById", countReviewById);
                    List<Product> products = SERVICE_PRODUCT.getProductsNewLimit3();
                    request.setAttribute("product", product);
                    request.setAttribute("products", products);
                    request.setAttribute("reviewList", reviews);
                    request.getRequestDispatcher("/view.jsp").forward(request, response);
                    break;
                case "delete":
                    int id_delete = Integer.parseInt(request.getParameter("id_delete"));
                    int product_id= Integer.parseInt(request.getParameter("product_id"));
                    reviewDAO.deleteReview(id_delete);
                    String url = "home?action=view&id=" + product_id;
                    response.sendRedirect(url);
                    break;
                case "update":
                    int id_update = Integer.parseInt(request.getParameter("id_update"));
                    int product_id2 = Integer.parseInt(request.getParameter("current_id"));
                    String reviewContent = request.getParameter("reviewContent");
                    System.out.println(reviewContent);
                    reviewDAO.updateReview(id_update,reviewContent);
                    String url_update = "home?action=view&id=" + product_id2;
                    response.sendRedirect(url_update);
                    break;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "review":
                int product_id = Integer.parseInt(request.getParameter("id_product"));
                Customer customer = (Customer) request.getSession().getAttribute("customer");
                if (customer == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    int customer_id = customer.getCustomer_id();
                    System.out.println();
                    String content = request.getParameter("reviewContent");
                    Review review = new Review(product_id, customer_id, content);
                    reviewDAO.addReview(review);
                    String url = "home?action=view&id=" + product_id;
                    response.sendRedirect(url);
                }
                break;
            case "delete":
                break;

        }

    }

    public void destroy() {
    }
}



