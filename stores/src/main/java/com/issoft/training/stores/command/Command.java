package com.issoft.training.stores.command;

import com.issoft.training.stores.listCategories.Store;

public interface Command {
    void execute(Store store);

    String getName();
}
