package com.issoft.training.domain.shop.categories;

import com.issoft.training.domain.shop.Product;

import java.util.List;

public abstract class Category {

    protected List<Product> productList;
    protected String name;

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
