package com.issoft.training.stores.command;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.listCategories.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateOrder implements Command {
    private List<Product> boughtProduct;
    private final String name = "order";

    @Override
    public void execute(Store store) {
        boughtProduct = new ArrayList<>();
        long timeOfBuy = (long) (1 + Math.random() * 30);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Thread.sleep(timeOfBuy * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Please shut down correctly");
            }
            addProduct(store);
            System.out.println("Bought Product" + boughtProduct);
        });
    }

    @Override
    public String getName() {
        return name;
    }

    private synchronized void addProduct(Store store) {
        List<Product> allProduct = new ArrayList<>(store.getAllShopProducts());
        Random random = new Random();
        int size = allProduct.size();
        Product product = allProduct.get(random.nextInt(size));
        boughtProduct.add(product);
    }

    public List<Product> getBoughtProduct() {
        return boughtProduct;
    }
}
