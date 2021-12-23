package com.issoft.training.stores.utils;

import com.issoft.training.domain.shop.categories.Category;
import dataBase.DataBaseStorePopulator;

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
        return populatorMap.get(populator).createListCategories();
    }
}
