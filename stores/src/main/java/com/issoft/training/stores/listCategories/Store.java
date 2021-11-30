package com.issoft.training.stores.listCategories;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.comparaton.ComparatorByPrice;
import com.issoft.training.stores.comparaton.ProductComparator;
import com.issoft.training.stores.pars.DocPars;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static List<Category> categoryList;

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
        DocPars doc = new DocPars();
        doc.DocParse("config.xml");
        String orderingName = doc.getOrderingValueByTag("name");
        String orderingPrice = doc.getOrderingValueByTag("price");
        String orderingRate = doc.getOrderingValueByTag("rate");
        List<Product> listNewProduct = new ArrayList<>(getAllShopProducts());
        listNewProduct.sort(new ProductComparator(orderingName, orderingPrice, orderingRate));

        for (Product p : listNewProduct) {
            System.out.println(p.getName() + "|" + p.getPrice() + "|" + p.getRate());
        }
    }

    public void topProducts() {
        List<Product> listTopProduct = new ArrayList<>(getAllShopProducts());
        List<Product> list = new ArrayList<>();
        listTopProduct.sort(new ComparatorByPrice());

        for (int i = 0; i < 5; i++) {
            System.out.println(listTopProduct.get(i));
        }

    }
}
