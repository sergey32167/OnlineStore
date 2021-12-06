package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.comparaton.ProductComparator;
import com.issoft.training.stores.pars.DocPars;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Store {
    private static List<Category> categoryList;
    private static DocPars doc;
    private static volatile Store instance;

    private Store() {
        categoryList = RandomStorePopulator.createListCategories();
        doc = new DocPars();
        doc.parseFile();
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

    public void sortProducts() {
        List<Product> listNewProduct = getAllShopProducts();
        listNewProduct.sort(new ProductComparator(doc.getDateSort()));
        for (Product product : listNewProduct) {
            System.out.println(product);
        }
    }

    public void topProductsByPrice() {
        List<Product> listTopProduct = getAllShopProducts();
        Map<String, String> topPriceDesc = new HashMap<>();
        topPriceDesc.put("price", "desc");
        listTopProduct.sort(new ProductComparator(topPriceDesc));
        for (int i = 0; i < 5; i++) {
            System.out.println(listTopProduct.get(i));
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "categoryList :" + "\n" + categoryList +
                '}';
    }
}
