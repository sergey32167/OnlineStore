package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.categories.Categories;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.util.List;

public class Store {
    private List<Categories> categoryList;

    public Store() {
        this.categoryList = RandomStorePopulator.createListCategories();
    }

    public List<Categories> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Categories> categoryList) {
        this.categoryList = categoryList;
    }

    public void printListCategories() {
        System.out.println(categoryList);
    }
}
