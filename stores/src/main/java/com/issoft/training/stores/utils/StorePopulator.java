package com.issoft.training.stores.utils;

import com.issoft.training.domain.shop.categories.Category;
import dataBase.DataBaseStorePopulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorePopulator {
    private final Map<PopulatorKinds, Populator> populatorMap;

    public StorePopulator() {
        this.populatorMap = new HashMap<>();
        populatorMap.put(PopulatorKinds.DATABASE, new DataBaseStorePopulator());
        populatorMap.put(PopulatorKinds.RANDOM, new RandomStorePopulator());
    }

    public List<Category> storePopulator(PopulatorKinds populator) {
        List<Category> categories = new ArrayList<>();
        for (Map.Entry<PopulatorKinds, Populator> method : populatorMap.entrySet()) {
            if (method.getKey().equals(populator)) {
                categories.addAll(method.getValue().createListCategories());
            }
        }
        return categories;
    }
}
