package com.issoft.training.stores.utils;

import com.issoft.training.stores.listCategories.Store;

import java.util.Scanner;

public class ConsoleScanner {
    private Store store;

    public ConsoleScanner(Store store) {
        this.store = store;
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String name;

        do {
            System.out.println("Enter command : sort, top, quite");
            name = scanner.nextLine();
        } while (!name.equalsIgnoreCase("top") && !name.equalsIgnoreCase("sort") && !name.equalsIgnoreCase("quite"));


        if (name.equalsIgnoreCase("sort")) {
            store.sortProducts();
        } else if (name.equalsIgnoreCase("top")) {
            store.topProducts();
        } else if (name.equalsIgnoreCase("quite")) {
            scanner.close();
        }

    }
}

