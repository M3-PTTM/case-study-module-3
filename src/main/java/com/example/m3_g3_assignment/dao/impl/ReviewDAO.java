package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.IReviewDAO;
import com.example.m3_g3_assignment.model.Review;
import com.example.m3_g3_assignment.utils.JDBCConnection;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements IReviewDAO {
    public static final String SHOW_PRODUCT_REVIEW = "SELECT * FROM review where product_id=? ORDER BY review_id desc";
    public static final String GET_CUSTOMER_NAME = "SELECT customer_name FROM customer WHERE customer_id=?";
    public static final String ADD_NEW_REVIEW = "INSERT INTO review(product_id,customer_id,review_content) VALUES(?,?,?)";
    public static final String SHOW_REVIEW_BY_ID = "SELECT COUNT(*) as total FROM review where product_id=?";
    public static final String GET_TOP_3 = "SELECT * FROM review WHERE product_id=? ORDER BY review_id desc LIMIT 3";
    public static final String DELETE_BY_ID = "DELETE FROM review WHERE review_id=?";
    public static final String UPDATE_REVIEW = "UPDATE review SET review_content=? WHERE product_id=?";
    public static final String GET_ALL_REVIEW = "SELECT * FROM review";


    @Override
    public String getCustomerReview(int customer_id) {
        String customer_name = "";
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_NAME);
            preparedStatement.setInt(1, customer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer_name = resultSet.getString("customer_name");
            }
        } catch (Exception e) {
            System.out.println("Error in getCustomerName");
        }
        return customer_name;
    }

    @Override
    public int countReviewByProductId(int product_id) {
        int count = 0;
        System.out.println("countReviewByProductId is running");
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_REVIEW_BY_ID);
            preparedStatement.setInt(1, product_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    @Override
    public List<Review> getTopThreeReview(int review_id) {
        List<Review> reviewList = new ArrayList<>();
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOP_3);
            preparedStatement.setInt(1, review_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setReview_id(resultSet.getInt("review_id"));
                review.setProduct_id(resultSet.getInt("product_id"));
                review.setCustomer_id(resultSet.getInt("customer_id"));
                review.setCustomer_name(getCustomerReview(resultSet.getInt("customer_id")));
                review.setReview_content(resultSet.getString("review_content"));
                reviewList.add(review);
            }
        } catch (Exception e) {
            System.out.println("Error in showAllReview");
        }
        return reviewList;
    }

    @Override
    public void updateReview(int id, String review_content) {
        try{
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW);
            preparedStatement.setString(1,review_content);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("Error in updateReview");
        }
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REVIEW);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setReview_id(resultSet.getInt("review_id"));
                review.setProduct_id(resultSet.getInt("product_id"));
                review.setCustomer_id(resultSet.getInt("customer_id"));
                review.setCustomer_name(getCustomerReview(resultSet.getInt("customer_id")));
                review.setReview_content(resultSet.getString("review_content"));
                reviewList.add(review);
            }
        } catch (Exception e) {
            System.out.println("Error in showAllReview");
        }
        return reviewList;
    }

    @Override
    public List<Review> showProductReview(int review_id) {
        List<Review> reviewList = new ArrayList<>();
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_PRODUCT_REVIEW);
            preparedStatement.setInt(1, review_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setReview_id(resultSet.getInt("review_id"));
                review.setProduct_id(resultSet.getInt("product_id"));
                review.setCustomer_id(resultSet.getInt("customer_id"));
                review.setCustomer_name(getCustomerReview(resultSet.getInt("customer_id")));
                review.setReview_content(resultSet.getString("review_content"));
                reviewList.add(review);
            }
        } catch (Exception e) {
            System.out.println("Error in showAllReview");
        }
        return reviewList;
    }


    @Override
    public void addReview(Review review) {
        int product_id = review.getProduct_id();
        int customer_id = review.getCustomer_id();
        String review_content = review.getReview_content();
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_REVIEW);
            preparedStatement.setInt(1, product_id);
            preparedStatement.setInt(2, customer_id);
            preparedStatement.setString(3, review_content);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in addReview");
        }
    }

    @Override
    public void deleteReview(int review_id) {
        try {
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,review_id);
            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            System.out.println("Error in deleteReview");
        }
    }
}
