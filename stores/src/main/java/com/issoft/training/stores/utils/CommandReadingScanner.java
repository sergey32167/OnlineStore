package com.issoft.training.stores.utils;

import com.issoft.training.stores.command.Command;
import com.issoft.training.stores.command.QuiteCommand;
import com.issoft.training.stores.command.SortCommand;
import com.issoft.training.stores.command.TopCommand;
import com.issoft.training.stores.listCategories.Store;

import java.util.Arrays;
import java.util.List;
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
        List<Command> commandList = Arrays.asList(new SortCommand(), new TopCommand(), new QuiteCommand());

        while (scanner.hasNextLine()) {
            name = scanner.nextLine().toLowerCase();
            for (Command command : commandList) {
                if (command.getName().equalsIgnoreCase(name)) {
                    command.execute(store);
                }
            }
            System.out.println("Enter command");
        }
        scanner.close();
    }
}

