package com.issoft.training.stores.utils;

import com.issoft.training.stores.command.CreateOrder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cleaner implements Runnable {

    @Override
    public void run() {
        CreateOrder createOrder = new CreateOrder();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() ->
                createOrder.getBoughtProduct().clear(), 0, 40, TimeUnit.SECONDS);
    }
}
