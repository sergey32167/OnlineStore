package com.issoft.training.stores.utils;

import com.issoft.training.domain.shop.categories.Category;
import dataBase.DataBaseStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class StorePopulator {

    public List<Category> storePopulator(String populator) {
        List<PopulatorMethod> populatorClass = new ArrayList<>();
        populatorClass.add(new DataBaseStorePopulator());
        populatorClass.add(new RandomStorePopulator());
        List<Category> categories = new ArrayList<>();

        for (PopulatorMethod method : populatorClass) {
            if (method.getName().equalsIgnoreCase(populator)) {
                categories.addAll(method.createListCategories());
            }
        }
        return categories;
    }
}
