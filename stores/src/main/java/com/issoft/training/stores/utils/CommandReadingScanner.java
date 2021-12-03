package com.issoft.training.stores.utils;

import com.issoft.training.stores.listCategories.Store;

import java.util.Scanner;

public class CommandReadingScanner {
    private final Store store;

    public CommandReadingScanner(Store store) {
        this.store = store;
    }

    public void scannerCommand() {
        Scanner scanner = new Scanner(System.in);
        String name = null;

        System.out.println("Enter command");

        while (scanner.hasNextLine()) {
            name = scanner.nextLine().toLowerCase();

            if (name.equalsIgnoreCase("sort")) {
                store.sortProducts();
            } else if (name.equalsIgnoreCase("top")) {
                store.topProducts();
            } else if (name.equalsIgnoreCase("quite")) {
                System.exit(0);
                scanner.close();
            } else {
                System.out.println("Error command");
            }
            System.out.println("Enter command");
        }
    }
}

