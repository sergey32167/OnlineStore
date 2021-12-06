package com.issoft.training.stores.command;

public class User {
    Command allShopProduct;
    Command sortProducts;
    Command topByPrice;

    public User(Command allShopProduct, Command sortProducts, Command topByPrice) {
        this.allShopProduct = allShopProduct;
        this.sortProducts = sortProducts;
        this.topByPrice = topByPrice;
    }

    public void getAllProduct() {
        allShopProduct.execute();
    }

    public void getSortProduct() {
        sortProducts.execute();
    }

    public void getTopByPrice() {
        topByPrice.execute();
    }
}
