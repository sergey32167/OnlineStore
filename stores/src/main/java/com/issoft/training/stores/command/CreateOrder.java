package com.issoft.training.stores.command;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.listCategories.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreateOrder implements Command {
    public final List<Product> boughtProduct = new ArrayList<>();
    private final String name = "order";

    @Override
    public void execute(Store store) {
        long timeOfBuy = (long) (1 + Math.random() * 30);
        Random random = new Random();
        List<Product> allProduct = new ArrayList<>(store.getAllShopProducts());
        int size = allProduct.size();
        Product product = allProduct.get(random.nextInt(size));
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timeOfBuy * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Please shut down correctly");
                }
                boughtProduct.add(product);
            }
        };

        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Please shut down correctly");
        }
        System.out.println("Bought Product" + boughtProduct);
    }

    public void cleanBasket() {

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    System.out.println("Bought Product" + boughtProduct);
                    boughtProduct.clear();
                    System.out.println("Bought Product" + boughtProduct);
                }, 0, 120, TimeUnit.SECONDS);
            }
        };

        thread3.start();
    }

    @Override
    public String getName() {
        return name;
    }
}
