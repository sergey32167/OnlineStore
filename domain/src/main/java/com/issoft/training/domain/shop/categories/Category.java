package com.issoft.training.domain.shop.categories;

import com.issoft.training.domain.shop.Product;

import java.util.List;

public abstract class Category {

    protected List<Product> listProduct;
    protected String name;

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
