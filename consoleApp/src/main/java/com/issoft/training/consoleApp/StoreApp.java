package com.issoft.training.consoleApp;

import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.utils.ConsoleScanner;

public class StoreApp {

    public static void main(String[] args) {
        Store store = new Store();
        store.printListCategories();

        ConsoleScanner scannerMy = new ConsoleScanner(store);
        scannerMy.scan();

    }
}