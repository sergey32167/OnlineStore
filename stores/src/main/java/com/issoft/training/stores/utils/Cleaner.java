package com.issoft.training.stores.utils;

import com.issoft.training.stores.command.CreateOrder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cleaner implements Runnable {

    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() ->
                CreateOrder.getInstance().getBoughtProduct().clear(), 1, 120, TimeUnit.SECONDS);
    }
}
