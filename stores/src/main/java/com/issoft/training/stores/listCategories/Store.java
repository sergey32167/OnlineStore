package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.comparaton.ProductComparator;
import com.issoft.training.stores.pars.DocPars;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;
    private final DocPars doc;

    public Store() {
        this.categoryList = RandomStorePopulator.createListCategories();
        this.doc = new DocPars();
        doc.parseFile();
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

    private List<Product> getAllShopProducts() {
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

    public void topProducts() {
        List<Product> listTopProduct = getAllShopProducts();
        listTopProduct.sort(new ProductComparator(doc.getPriceSort()));
        for (int i = 0; i < 5; i++) {
            System.out.println(listTopProduct.get(i));
        }
    }
}
