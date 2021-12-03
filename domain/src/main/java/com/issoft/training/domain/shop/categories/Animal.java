package com.issoft.training.domain.shop.categories;

public class Animal extends Category {

    public Animal() {
        super("animal");
    }

    @Override
    public String toString() {
        return "\n Animal{" +
                "name=" + name +
                ", productList='" + listProduct + '\'' +
                '}';
    }
}
