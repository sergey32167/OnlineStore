package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.PopulatorKinds;
import com.issoft.training.stores.utils.StorePopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Store {
    private final List<Product> boughtProduct = new CopyOnWriteArrayList<>();
    private List<Category> categoryList;
    private static volatile Store instance;

    private Store() {
        categoryList = new StorePopulator().storePopulator(PopulatorKinds.HTTP);
    }

    public static Store getInstance() {
        Store result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (instance == null) {
                instance = new Store();
            }
            return instance;
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void printListCategories() {
        System.out.println(categoryList);
    }

    public List<Product> getAllShopProducts() {
        List<Product> allProductslist = new ArrayList<>();
        for (Category cat : categoryList) {
            for (Product pro : cat.getListProduct()) {
//                System.out.println(pro);
                allProductslist.add(pro);
            }
        }
        return allProductslist;
    }

    public List<Product> getBoughtProduct() {
        return boughtProduct;
    }
}