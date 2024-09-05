package com.example.m3_g3_assignment.model;

public class Cart {
    private int customerId;
    private int productId;
    private int quantity;

    public Cart() {
    }

    public Cart(int customerId, int productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Cart(int customerId, int productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
