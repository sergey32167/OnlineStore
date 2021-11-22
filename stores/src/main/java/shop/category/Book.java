package shop.category;

import utils.RandomStorePopulator;

public class Book extends Category {

    public Book() {
        this.name = "book";
        this.productList = RandomStorePopulator.createListProduct(name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
