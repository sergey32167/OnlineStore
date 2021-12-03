package com.issoft.training.stores.comparaton;

import com.issoft.training.domain.shop.Product;

import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {
    private final Map<String, String> orderingNameValue;

    public ProductComparator(Map<String, String> orderingNameValue) {
        this.orderingNameValue = orderingNameValue;
    }

    public int compare(Product product1, Product product2) {
        int i = 0;
        for (Map.Entry<String, String> item : orderingNameValue.entrySet()) {
            String key = item.getKey();
            String value = item.getValue();
            switch (key) {
                case "name":
                    if (value.equalsIgnoreCase("asc")) {
                        i = product1.getName().compareToIgnoreCase(product2.getName());
                    } else {
                        i = product2.getName().compareToIgnoreCase(product1.getName());
                    }
                    if (i != 0) {
                        return i;
                    } else {
                        break;
                    }
                case "price":
                    if (value.equalsIgnoreCase("asc")) {
                        i = Double.compare(product1.getPrice(), product2.getPrice());
                    } else {
                        i = Double.compare(product2.getPrice(), product1.getPrice());
                    }
                    if (i != 0) {
                        return i;
                    } else {
                        break;
                    }
                case "rate":
                    if (value.equalsIgnoreCase("asc")) {
                        i = Double.compare(product1.getRate(), product2.getRate());
                    } else {
                        i = Double.compare(product2.getRate(), product1.getRate());
                    }
                    if (i != 0) {
                        return i;
                    } else {
                        break;
                    }
            }
            break;
        }
        return i;
    }
}

