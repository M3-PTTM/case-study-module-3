package com.example.m3_g3_assignment.dao.impl;

import com.example.m3_g3_assignment.dao.IReviewDAO;
import com.example.m3_g3_assignment.model.Review;
import com.example.m3_g3_assignment.utils.JDBCConnection;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements IReviewDAO {
    public static final String SHOW_PRODUCT_REVIEW ="SELECT * FROM review where product_id=?";
    public static final String GET_CUSTOMER_NAME="SELECT customer_name FROM customer WHERE customer_id=?";


    @Override
    public String getCustomerReview(int customer_id) {
        String customer_name="";
        try {
            Connection connection=JDBCConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_CUSTOMER_NAME);
            preparedStatement.setInt(1, customer_id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer_name=resultSet.getString("customer_name");
            }
        }catch (Exception e){
            System.out.println("Error in getCustomerName");
        }
        return customer_name;
    }

    @Override
    public List<Review> showProductReview(int review_id) {
        System.out.println("showProductReview is running");
        List<Review> reviewList= new ArrayList<>();
        try {
            Connection connection= JDBCConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SHOW_PRODUCT_REVIEW);
            preparedStatement.setInt(1, review_id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Review review=new Review();
                review.setReview_id(resultSet.getInt("review_id"));
                review.setProduct_id(resultSet.getInt("product_id"));
                review.setCustomer_id(resultSet.getInt("customer_id"));
                review.setCustomer_name(getCustomerReview(resultSet.getInt("customer_id")));
                review.setReview_content(resultSet.getString("review_content"));
                reviewList.add(review);
            }
        }catch (Exception e) {
            System.out.println("Error in showAllReview");
        }
        return reviewList;
    }


    @Override
    public void addReview(Review review) {

    }

    @Override
    public void deleteReview(int review_id) {

    }
}
