package com.example.m3_g3_assignment.model;

public class Order {
    private Product product;
    private Customer customer;
    private int quantity;
    private String code;

    public Order() {
    }

    public Order(Product product, Customer customer, int quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public Order(Product product, Customer customer, int quantity, String code) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
