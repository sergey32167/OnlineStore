package com.issoft.training.stores.utils;

import com.issoft.training.domain.shop.Product;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cleaner implements Runnable {
    private final List<Product> cleanList;
    private Lock lockDelete = new ReentrantLock();

    public Cleaner(List<Product> cleanList) {
        this.cleanList = cleanList;
    }


    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            lockDelete.lock();
            cleanList.clear();
            lockDelete.unlock();
        }, 1, 120, TimeUnit.SECONDS);
    }
}
