package com.issoft.training.domain.shop;

public class Product {

    private String name;
    private double price;
    private double rate;
    private String categoryName;

    public Product() {
    }

    public Product(String name, double price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product {" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                '}';
    }
}
