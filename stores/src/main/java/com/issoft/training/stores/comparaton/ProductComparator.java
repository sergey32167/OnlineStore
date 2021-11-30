package com.issoft.training.stores.comparaton;

import com.issoft.training.domain.shop.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    private final String orderingName;
    private final String orderingPrice;
    private final String orderingRate;

    public ProductComparator(String orderingName, String orderingPrice, String orderingRate) {
        this.orderingName = orderingName;
        this.orderingPrice = orderingPrice;
        this.orderingRate = orderingRate;
    }

    public int compare(Product product1, Product product2) {
        int i;
        if (orderingName.equals("asc")) {
            i = product1.getName().compareToIgnoreCase(product2.getName());
        } else {
            i = product2.getName().compareToIgnoreCase(product1.getName());
        }
        if (i != 0) {
            return i;
        }
        if (orderingPrice.equals("asc")) {
            i = Double.compare(product1.getPrice(), product2.getPrice());
        } else {
            i = Double.compare(product2.getPrice(), product1.getPrice());
        }
        if (i != 0) {
            return i;
        }
        if (orderingRate.equals("asc")) {
            i = Double.compare(product1.getRate(), product2.getRate());
        } else {
            i = Double.compare(product2.getRate(), product1.getRate());
        }
        return 0;
    }
}

