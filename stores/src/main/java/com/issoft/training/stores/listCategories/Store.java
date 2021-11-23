package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.util.List;

public class Store {
    private List<Category> categoryList;

    public Store() {
        this.categoryList = RandomStorePopulator.createListCategories();
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
}
