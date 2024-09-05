package com.example.m3_g3_assignment.dao;

import com.example.m3_g3_assignment.model.Review;

import java.util.List;

public interface IReviewDAO {
    List<Review> showProductReview(int review_id);
    void addReview(Review review);
    void deleteReview(int review_id);
    String getCustomerReview(int customer_id);
}

