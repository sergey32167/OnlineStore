package com.issoft.training.consoleApp;

import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.utils.CommandReadingScanner;

public class StoreApp {

    public static void main(String[] args) {
        Store store = Store.getInstance();
        Store store1 = Store.getInstance();
        System.out.println(store1);
        System.out.println(store);
//        store.printListCategories();

        CommandReadingScanner scanner = new CommandReadingScanner(store);
//        scanner.scannerCommand();
    }
}