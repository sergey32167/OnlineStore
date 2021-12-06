package com.issoft.training.stores.command;

import com.issoft.training.stores.listCategories.Store;

public class AllShopProducts implements Command {
    Store store;

    public AllShopProducts(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        store.getAllShopProducts();
    }
}
