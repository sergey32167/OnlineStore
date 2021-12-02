package com.issoft.training.stores.comparaton;

import com.issoft.training.domain.shop.Product;

import java.util.Comparator;
import java.util.List;

public class ProductComparator implements Comparator<Product> {
    List<String> orderingValue;

    public ProductComparator(List<String> orderingValue) {
        this.orderingValue = orderingValue;
    }

    public int compare(Product product1, Product product2) {
        for (String value : orderingValue) {
            int i;
            if (value.equals("asc")) {
                i = product1.getName().compareToIgnoreCase(product2.getName());
            } else {
                i = product2.getName().compareToIgnoreCase(product1.getName());
            }
            if (i != 0) {
                return i;
            }
        }
        return 0;
    }
}

