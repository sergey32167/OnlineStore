package com.issoft.training.consoleApp;

import com.issoft.training.http.HttpServerLocal;
import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.utils.CommandReadingScanner;

public class StoreApp {
    public static void main(String[] args) {
        HttpServerLocal httpServerLocal = new HttpServerLocal();
        httpServerLocal.createServer();

        Store store = Store.getInstance();

        CommandReadingScanner scanner = new CommandReadingScanner(store);
        scanner.scannerCommand();
    }
}