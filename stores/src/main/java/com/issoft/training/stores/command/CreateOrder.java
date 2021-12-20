package com.issoft.training.stores.command;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.listCategories.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateOrder implements Command {
    private final String name = "order";

    @Override
    public void execute(Store store) {
        long timeOfBuy = (long) (1 + Math.random() * 30);
        List<Product> allProduct = new ArrayList<>(store.getAllShopProducts());
        Random random = new Random();
        int size = allProduct.size();
        Product product = allProduct.get(random.nextInt(size));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Thread.sleep(timeOfBuy * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Please shut down correctly");
            }
            store.getBoughtProduct().add(product);
            System.out.println("Bought Product" + store.getBoughtProduct());
        });
    }

    @Override
    public String getName() {
        return name;
    }
}
