package com.issoft.training.domain.shop.categories;

public class Book extends Categories {

    public Book() {
        super("book");
    }

    @Override
    public String toString() {
        return "Book{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}' + '\n';
    }
}
