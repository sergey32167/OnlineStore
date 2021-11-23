package com.issoft.training.domain.shop.categories;

public class Food extends Category {

    public Food() {
        super("food");
    }

    @Override
    public String toString() {
        return "Food{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}' + '\n';
    }
}
