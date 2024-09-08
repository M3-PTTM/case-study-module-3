package com.example.m3_g3_assignment.model;

public class Customer {
    private int customer_id;
    private String username;
    private String password;
    private String customer_name;
    private String customer_email;
    private String customer_phone;
    private String customer_citizen;
    private String customer_role;

    public Customer() {
    }

    public Customer(int customer_id, String customer_name) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
    }

    public Customer(int customer_id, String username, String customer_name, String customer_email, String customer_phone, String customer_citizen, String customer_role) {
        this.customer_id = customer_id;
        this.username = username;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_citizen = customer_citizen;
        this.customer_role = customer_role;
    }

    public Customer(String username, String customer_name, String customer_email, String customer_phone, String customer_citizen, String customer_role) {
        this.username = username;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_citizen = customer_citizen;
        this.customer_role = customer_role;
    }

    public Customer(String username, String password, String customer_name, String customer_email, String customer_phone, String customer_citizen, String customer_role) {
        this.username = username;
        this.password = password;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_citizen = customer_citizen;
        this.customer_role = customer_role;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_citizen() {
        return customer_citizen;
    }

    public void setCustomer_citizen(String customer_citizen) {
        this.customer_citizen = customer_citizen;
    }

    public String getCustomer_role() {
        return customer_role;
    }

    public void setCustomer_role(String customer_role) {
        this.customer_role = customer_role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", customer_citizen='" + customer_citizen + '\'' +
                ", customer_role='" + customer_role + '\'' +
                '}';
    }
}