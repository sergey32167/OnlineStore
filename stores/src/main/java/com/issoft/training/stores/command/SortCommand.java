package com.issoft.training.stores.command;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.stores.comparaton.ProductComparator;
import com.issoft.training.stores.listCategories.Store;
import com.issoft.training.stores.pars.DocPars;

import java.util.List;

public class SortCommand implements Command {
    private final String name = "sort";
    private final DocPars doc;

    public SortCommand() {
        doc = new DocPars();
        doc.parseFile();
    }

    @Override
    public void execute(Store store) {
        List<Product> listNewProduct = store.getAllShopProducts();
        listNewProduct.sort(new ProductComparator(doc.getDateSort()));
        for (Product product : listNewProduct) {
            System.out.println(product);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
