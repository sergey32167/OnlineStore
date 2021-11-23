package com.issoft.training.domain.shop.categories;

public class Animal extends Categories {

    public Animal() {
        super("animal");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}' + '\n';
    }
}
