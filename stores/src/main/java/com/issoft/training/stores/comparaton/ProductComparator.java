package com.issoft.training.stores.comparaton;

import com.issoft.training.domain.shop.Product;

import java.util.Comparator;
import java.util.List;

public class ProductComparator implements Comparator<Product> {
    private final List<String> orderingValue;
    private final List<String> orderingName;

    public ProductComparator(List<String> orderingValue, List<String> orderingName) {
        this.orderingValue = orderingValue;
        this.orderingName = orderingName;
    }

    public int compare(Product product1, Product product2) {
        int i;
        for (String name : orderingName) {
            for (String value : orderingValue) {
                if (name.equalsIgnoreCase("name")) {
                    if (value.equals("asc")) {
                        i = product1.getName().compareToIgnoreCase(product2.getName());
                    } else {
                        i = product2.getName().compareToIgnoreCase(product1.getName());
                    }
                    if (i != 0) {
                        return i;
                    }
                }
                if (name.equalsIgnoreCase("price")) {
                    if (value.equals("asc")) {
                        i = Double.compare(product1.getPrice(), product2.getPrice());
                    } else {
                        i = Double.compare(product2.getPrice(), product1.getPrice());
                    }
                    if (i != 0) {
                        return i;
                    }
                }
                if (name.equalsIgnoreCase("rate")) {
                    if (value.equals("asc")) {
                        i = Double.compare(product1.getPrice(), product2.getPrice());
                    } else {
                        i = Double.compare(product2.getPrice(), product1.getPrice());
                    }
                    if (i != 0) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }
}

