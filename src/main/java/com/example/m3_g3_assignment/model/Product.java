package com.example.m3_g3_assignment.model;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private String img;
    private String description;

    public Product() {
    }

    public Product(String name, double price, String category, String img, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.img = img;
        this.description = description;
    }

    public Product(int id, String name, double price, String category, String img, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.img = img;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
