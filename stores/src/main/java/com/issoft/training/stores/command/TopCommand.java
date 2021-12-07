package com.issoft.training.stores.command;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.comparaton.ProductComparator;
import com.issoft.training.stores.listCategories.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopCommand implements Command {
    private final String name = "top";

    @Override
    public void execute(Store store) {
        List<Product> listTopProduct = store.getAllShopProducts();
        Map<String, String> topPriceDesc = new HashMap<>();
        topPriceDesc.put("price", "desc");
        listTopProduct.sort(new ProductComparator(topPriceDesc));
        for (int i = 0; i < 5; i++) {
            System.out.println(listTopProduct.get(i));
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
