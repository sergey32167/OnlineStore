package com.issoft.training.consoleApp;

import com.issoft.training.stores.command.AllShopProducts;
import com.issoft.training.stores.command.SortProducts;
import com.issoft.training.stores.command.TopProductsByPrice;
import com.issoft.training.stores.command.User;
import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.utils.CommandReadingScanner;

public class StoreApp {

    public static void main(String[] args) {
        Store store = Store.getInstance();
//        store.printListCategories();

        CommandReadingScanner scanner = new CommandReadingScanner(store);
//        scanner.scannerCommand();

        User user = new User(new AllShopProducts(store), new SortProducts(store), new TopProductsByPrice(store));
        user.getAllProduct();
        System.out.println("_______________");
        user.getSortProduct();
        System.out.println("_______________");
        user.getTopByPrice();
    }
}