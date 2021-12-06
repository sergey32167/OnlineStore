package com.issoft.training.stores.command;

import com.issoft.training.stores.listCategories.Store;

public class TopProductsByPrice implements Command {
    Store store;

    public TopProductsByPrice(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        store.topProductsByPrice();
    }
}
