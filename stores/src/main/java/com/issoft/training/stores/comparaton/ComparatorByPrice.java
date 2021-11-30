package com.issoft.training.stores.comparaton;

import com.issoft.training.domain.shop.Product;

import java.util.Comparator;

public class ComparatorByPrice implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {
        return Double.compare(product2.getPrice(), product1.getPrice());
    }
}
