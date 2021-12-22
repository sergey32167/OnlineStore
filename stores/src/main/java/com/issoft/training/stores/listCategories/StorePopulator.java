package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.RandomStorePopulator;
import dataBase.DataBaseStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class StorePopulator {

    public List<Category> storePopulator(String populator) {
        List<Category> categories = new ArrayList<>();
        switch (populator) {
            case ("dataBase"):
                DataBaseStorePopulator dataBaseStorePopulator = new DataBaseStorePopulator();
                categories = dataBaseStorePopulator.createListCategoryDB();
                break;
            case ("random"):
                RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
                categories = randomStorePopulator.createListCategories();
                break;
        }
        return categories;
    }
}
