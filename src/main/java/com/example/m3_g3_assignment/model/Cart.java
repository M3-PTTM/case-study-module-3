package com.example.m3_g3_assignment.model;

public class Cart {
    private Customer customer;
    private Product product;

    public Cart() {
    }

    public Cart(Customer customer, Product product) {
        this.product = product;
        this.customer = customer;
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
}
