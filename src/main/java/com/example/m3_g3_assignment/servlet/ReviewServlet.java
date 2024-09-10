package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.ReviewDAO;
import com.example.m3_g3_assignment.model.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "reviewController", value = "/ReviewController")
public class ReviewServlet extends HttpServlet {
    ReviewDAO reviewDAO = new ReviewDAO();
    List<Review> reviews = new ArrayList<Review>();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        String id = request.getParameter("id");
        if (action.equals("deleteReview")) {
            reviewDAO.deleteReview(Integer.parseInt(id));
            response.sendRedirect("/ReviewController");
        }
        else {
            reviews = reviewDAO.getAllReviews();
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/review.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}