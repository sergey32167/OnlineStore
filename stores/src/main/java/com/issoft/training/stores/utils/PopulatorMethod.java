package com.issoft.training.stores.utils;

import com.issoft.training.domain.shop.categories.Category;

import java.util.List;

public interface PopulatorMethod {

    String getName();

    List<Category> createListCategories();
}
