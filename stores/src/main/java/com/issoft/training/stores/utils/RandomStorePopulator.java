package com.issoft.training.stores.utils;

import com.github.javafaker.Faker;
import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Categories;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {
    private static final Faker faker = new Faker();

    private static Product createProductByCategoriesName(String categoryName) {
        int price = (int) faker.number().randomNumber();
        int rate = (int) faker.number().randomNumber();
        switch (categoryName) {
            case "food":
                return new Product(faker.food().vegetable(), price, rate);
            case "animal":
                return new Product(faker.animal().name(), price, rate);
            case "book":
                return new Product(faker.book().genre(), price, rate);
            case "beer":
                return new Product(faker.beer().name(), price, rate);
            case "color":
                return new Product(faker.color().name(), price, rate);
            default:
                return new Product(faker.name().firstName(), price, rate);
        }
    }

    private static List<Product> createListProduct(String categoryName) {
        List<Product> listProduct = new ArrayList<>();
        int a1 = (int) (1 + Math.random() * 10);
//        System.out.println(a1);
        for (int a = 0; a < a1; a++) {
            Product productName = createProductByCategoriesName(categoryName);
            listProduct.add(productName);
        }
//        System.out.println(listProduct);
        return listProduct;
    }

    public static List<Categories> createListCategories() {
        List<Categories> categoryList = new ArrayList<>();
        Reflections reflections = new Reflections("com.issoft.training.domain.shop.categories");
        for (Class<? extends Categories> detector : reflections.getSubTypesOf(Categories.class)) {
            try {
                Categories newCategory = detector.newInstance();
                newCategory.setProductList(createListProduct(newCategory.getName()));
                categoryList.add(newCategory);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(categoryList);
        return categoryList;
    }
}
