package com.issoft.training.stores.command;

import com.issoft.training.stores.listCategories.Store;

public class QuiteCommand implements Command {
    private final String name = "quite";

    @Override
    public void execute(Store store) {
        System.exit(0);
    }

    @Override
    public String getName() {
        return name;
    }
}
