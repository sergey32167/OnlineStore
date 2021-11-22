package shop.category;

import utils.RandomStorePopulator;

public class Animal extends Category {

    public Animal() {
        this.name = "animal";
        this.productList = RandomStorePopulator.createListProduct(name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
