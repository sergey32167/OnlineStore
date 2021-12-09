package com.issoft.training.consoleApp;

import com.issoft.training.stores.command.CreateOrder;
import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.utils.CommandReadingScanner;

public class StoreApp {

    public static void main(String[] args) {

        Store store = Store.getInstance();

        CreateOrder createOrder = new CreateOrder();
        createOrder.cleanBasket();

        CommandReadingScanner scanner = new CommandReadingScanner(store);
        scanner.scannerCommand();

    }
}