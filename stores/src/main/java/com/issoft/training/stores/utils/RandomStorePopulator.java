package com.issoft.training.stores.utils;

import com.github.javafaker.Faker;
import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator implements Populator {
    private static final Faker faker = new Faker();

    private static String createProductName(String categoryName) {
        String name;
        switch (categoryName) {
            case "food":
                return name = faker.food().vegetable();
            case "animal":
                return name = faker.animal().name();
            case "book":
                return name = faker.book().genre();
            default:
                return name = faker.name().firstName();
        }
    }

    private static Product createProductByCategoriesName(String categoryName) {
        return new Product(RandomStorePopulator.createProductName(categoryName), faker.number().randomNumber(), faker.number().randomNumber());
    }

    public static List<Product> createListProduct(String categoryName) {
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

    public List<Category> createListCategories() {
        List<Category> categoryList = new ArrayList<>();
        Reflections reflections = new Reflections("com.issoft.training.domain.shop.categories");
        for (Class<? extends Category> detector : reflections.getSubTypesOf(Category.class)) {
            try {
                Category newCategory = detector.newInstance();
                newCategory.setListProduct(createListProduct(newCategory.getName()));
                categoryList.add(newCategory);
            } catch (InstantiationException e) {
                throw new RuntimeException("The specified class object cannot be instantiated");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("The currently executing method does not have access");
            }
        }
//        System.out.println(categoryList);
        return categoryList;
    }
}
