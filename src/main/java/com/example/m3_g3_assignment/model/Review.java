package com.example.m3_g3_assignment.model;

public class Review {

    private int review_id;
    private int product_id;
    private int customer_id;
    private String review_content;

    private String customer_name;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Review() {
    }

    public Review(int review_id, int product_id, int customer_id, String review_content
    , String customer_name) {
        this.review_id = review_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.review_content = review_content;
        this.customer_name = customer_name;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
}
