package com.issoft.training.stores.utils;

import com.issoft.training.stores.listCategories.Store;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cleaner implements Runnable {
    private final Store store;

    public Cleaner(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(store.getBoughtProduct().size());
            store.getBoughtProduct().clear();
        }, 1, 20, TimeUnit.SECONDS);
    }
}
