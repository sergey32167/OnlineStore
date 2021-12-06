package com.issoft.training.stores.command;

import com.issoft.training.stores.listCategories.Store;

public class SortProducts implements Command {
    Store store;

    public SortProducts(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        store.sortProducts();
    }
}
